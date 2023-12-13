package com.example.assignment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CreatePostActivity extends AppCompatActivity {

    private EditText editMealName;
    private EditText editMealDescription;
    private EditText editMealLocationRecipe;
    private RatingBar ratingBar;
    private Button buttonUploadMeal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_post);

        //define items by their ID's
        buttonUploadMeal = findViewById(R.id.buttonUploadMeal);
        ratingBar = findViewById(R.id.ratingBar);
        editMealLocationRecipe = findViewById(R.id.editMealLocationRecipe);
        editMealDescription = findViewById(R.id.editMealDescription);
        editMealName = findViewById(R.id.editMealName);

        //Upload button listener
        buttonUploadMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadMeal();
            }
        });
    }//end onCreate

    public void uploadMeal() {

        String mealName = editMealName.getText().toString().trim();
        String mealDescription = editMealDescription.getText().toString().trim();
        String mealLocationRecipe = editMealLocationRecipe.getText().toString().trim();
        float rating = ratingBar.getRating();

        if (mealName.isEmpty() || mealDescription.isEmpty() || mealLocationRecipe.isEmpty()) {
            Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        GlobalVars globalVars = GlobalVars.getInstance();
        // Execute the task using Executor
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new UploadMealTask(getApplicationContext(), mealName, mealDescription, mealLocationRecipe, globalVars.isSignedIn(), String.valueOf(rating)));

    }//end uploadMeal()

    // Runnable to perform database operations in the background
    private static class UploadMealTask implements Runnable {

        private final Context context;
        private final Object[] params;

        UploadMealTask(Context context, Object... params) {
            this.context = context;
            this.params = params;
        }

        @Override
        public void run() {
            String mealName = (String) params[0];
            String mealDescription = (String) params[1];
            String mealLocationRecipe = (String) params[2];
            String posterName = (String) params[3];
            float mealRating = Float.parseFloat((String) params[4]);


            AppDatabase db = DatabaseSingleton.getInstance(context.getApplicationContext());
            try {
                PostDAO postDao = db.postDAO();

                // Create a Post object with the provided data
                Post post = new Post(mealName, mealDescription, mealLocationRecipe, posterName, mealRating);

                // Insert the Post object into the database
                postDao.insertPost(post);
                Log.e("NOTIF", "POST UPLOADED");

                // Intent to navigate to HomeActivity
                Intent intent = new Intent(context, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }//end run
    }//end UploadTask


}//end CreatePostActivity
