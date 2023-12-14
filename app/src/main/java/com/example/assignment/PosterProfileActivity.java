package com.example.assignment;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


public class PosterProfileActivity extends AppCompatActivity {

    private TextView textPosterName;
    private RecyclerView recyclerCollections;
    private RecyclerView recyclerPostsAndComments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poster_profile);

        // Initialize views
        textPosterName = findViewById(R.id.textPosterName);
        recyclerCollections = findViewById(R.id.recyclerCollections);
        recyclerPostsAndComments = findViewById(R.id.recyclerPostsAndComments);

        GlobalVars globalVars = GlobalVars.getInstance();
        textPosterName.setText(globalVars.getUserEmail());

    }//end onCreate

}//end PosterProfileActivity
