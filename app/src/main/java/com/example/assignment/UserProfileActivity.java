package com.example.assignment;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class UserProfileActivity extends AppCompatActivity {

    private PostDAO postDao;
    private CommentDAO commentDao;
    private TextView textPosterName;
    private RecyclerView recyclerPosts;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        // Initialize views
        textPosterName = findViewById(R.id.UPtextUserEmail);
        recyclerPosts = findViewById(R.id.UPrecyclerPosts);

        //setting user email from global var
        GlobalVars globalVars = GlobalVars.getInstance();
        textPosterName.setText(globalVars.getUserEmail());

        recyclerPosts.setLayoutManager(new LinearLayoutManager(this));

        //getting posts and comments for recyclerPostsAndComments
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new getPosts(getApplicationContext()));
    }//end onCreate

    private class getPosts implements Runnable {

        private final Context context;
        getPosts(Context context) {
            this.context = context;
        }

        @Override
        public void run() {
            //get the users email
            GlobalVars globalVars = GlobalVars.getInstance();

            //database access
            AppDatabase db = DatabaseSingleton.getInstance(context.getApplicationContext());
            PostDAO postDao = db.postDAO();

            //query the database once with the list of users Posts
            final List<Post> posts = postDao.getPostsByUserEmail(globalVars.getUserEmail());

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    // Creating and setting up the adapter
                    PostAdapter postAdapter = new PostAdapter(posts);

                    RecyclerView postRecyclerView = findViewById(R.id.UPrecyclerPosts);
                    postRecyclerView.setAdapter(postAdapter);
                }//end UI Thread run
            });//end runOnUiThread
        }//end getPostsComments run

    }//end getPostsComments
}//end PosterProfileActivity
