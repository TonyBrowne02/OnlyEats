package com.example.assignment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SignInSignUpActivity extends AppCompatActivity {
    private EditText editEmailSI, editPasswordSI, editEmailSU, editPasswordSU, editNameSU;
    private Button buttonSignIn, buttonSignUp, buttonTOS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_signup);

        editEmailSI = findViewById(R.id.editEmailSI);
        editEmailSU = findViewById(R.id.editEmailSU);
        editPasswordSI = findViewById(R.id.editPasswordSI);
        editPasswordSU = findViewById(R.id.editPasswordSU);
        buttonSignIn = findViewById(R.id.buttonSignIn);
        buttonSignUp = findViewById(R.id.buttonSignUp);
        buttonTOS = findViewById(R.id.toggleButtonTOS);

        // Set click listener for the Sign In button
        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editEmailSI.getText().toString().trim();
                String password= editPasswordSI.getText().toString().trim();
                if(email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(SignInSignUpActivity.this, "Fill in all fields!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Executor executor = Executors.newSingleThreadExecutor();
                    executor.execute(new SignIn(SignInSignUpActivity.this, email, password));
                }//end if else

            }//end onClick

        });//end buttonSignIn listener

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                String email = editEmailSU.getText().toString().trim();
                String password= editPasswordSU.getText().toString().trim();
                if(email.isEmpty() || password.isEmpty() || buttonTOS.isActivated()) {
                    Toast.makeText(SignInSignUpActivity.this, "Fill in all fields and agree to TOS!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Executor executor = Executors.newSingleThreadExecutor();
                    executor.execute(new SignUp(SignInSignUpActivity.this, email, password));
                }//end if else
            }
        });//end buttonSignUp listener

    }//end onCreate

    private static class SignIn implements Runnable {
        private final AppCompatActivity  context;
        private final Object[] params;

        SignIn(AppCompatActivity  context, Object... params) {
            this.context = context;
            this.params = params;
        }//end SignIn constructor

        @Override
        public void run() {
            String email = (String) params[0];
            String password = (String) params[1];

            AppDatabase db = DatabaseSingleton.getInstance(context.getApplicationContext());
            try {
                UserDAO userDao = db.userDAO();
                if(userDao.getUser(email, password) != null) {
                    ((AppCompatActivity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "Sign in successful!", Toast.LENGTH_SHORT).show();
                            // Open HomeActivity
                            GlobalVars globalVars = GlobalVars.getInstance();
                            globalVars.setUserEmail(email);
                            Log.i("GLOBAL SIGN IN SET", email);

                            Intent intent = new Intent(context, HomeActivity.class);
                            context.startActivity(intent);
                            ((AppCompatActivity) context).finish();//finish to ensure sign in page not on backstack
                        }

                    });
                }
                else {
                    ((AppCompatActivity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "Incorrect email or password!", Toast.LENGTH_SHORT).show();
                            Log.e("ALERT","USER DOESN'T EXIST");
                        }
                    });//show a toast if the user doesn't exist with that password and email
                }//end if else
            }
            catch(Exception e) {
                e.printStackTrace();
            }//end tryCatch
        }//end run
    }//end SignIn

    private static class SignUp implements Runnable {
        private final AppCompatActivity  context;
        private final Object[] params;

        SignUp(AppCompatActivity  context, Object... params) {
            this.context = context;
            this.params = params;
        }//end SignIn constructor

        @Override
        public void run() {
            String email = (String) params[0];
            String password = (String) params[1];

            AppDatabase db = DatabaseSingleton.getInstance(context.getApplicationContext());
            try {
                UserDAO userDao = db.userDAO();
                if(userDao.getUser(email, password) == null) {
                    User user = new User(email,password);
                    userDao.insertUser(user);
                    long userID = userDao.getUserIDByEmail(email);

                    ((AppCompatActivity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "Sign Up successful!", Toast.LENGTH_SHORT).show();
                            // Open HomeActivity
                            GlobalVars globalVars = GlobalVars.getInstance();
                            globalVars.setUserEmail(email);
                            globalVars.setUserID(userID);
                            Log.i("GLOBAL SIGN IN SET", email);
                            Intent intent = new Intent(context, HomeActivity.class);
                            context.startActivity(intent);
                            ((AppCompatActivity) context).finish();//finish to ensure sign in page not on backstack
                        }//end run
                    });//end runOnUiThread
                }
                else {
                    ((AppCompatActivity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "User exists with that email!", Toast.LENGTH_SHORT).show();
                            Log.e("ALERT","USER ALREADY EXISTS");
                        }
                    });
                }//end if else
            }
            catch(Exception e) {
                e.printStackTrace();
            }//end tryCatch
        }//end run
    }//end SignUp
}//end SignInSignUpActivity
