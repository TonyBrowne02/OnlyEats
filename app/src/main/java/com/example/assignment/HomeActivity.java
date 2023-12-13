package com.example.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GlobalVars globalVars = GlobalVars.getInstance();

        if (globalVars.isSignedIn() == null || globalVars.isSignedIn().isEmpty()) {
            startActivity(new Intent(this, SignInSignUpActivity.class));
            finish(); // Finish the current activity to prevent going back to it
        }
        setContentView(R.layout.activity_home);

        // Button to browse posts
        Button btnBrowsePosts = findViewById(R.id.btnBrowsePosts);
        btnBrowsePosts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to BrowsePostsActivity
                Intent intent = new Intent(HomeActivity.this, BrowsePostsActivity.class);
                startActivity(intent);
            }
        });

        // Button to create a new post
        Button btnCreatePost = findViewById(R.id.btnCreatePost);
        btnCreatePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to CreatePostActivity
                Intent intent = new Intent(HomeActivity.this, CreatePostActivity.class);
                startActivity(intent);
            }
        });

        // Button to view poster profile
        Button btnPosterProfile = findViewById(R.id.btnPosterProfile);
        btnPosterProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to PosterProfileActivity
                Intent intent = new Intent(HomeActivity.this, PosterProfileActivity.class);
                startActivity(intent);
            }
        });
    }//end onCreate
} // end HomeActivity
