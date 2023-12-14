package com.example.assignment;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class BrowsePostsActivity extends AppCompatActivity implements PostAdapter.OnItemClickListener {

    private PostDAO postDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browse_posts);

        // Gets the database from the DatabaseSingleton class to ensure the db is only created once
        AppDatabase db = DatabaseSingleton.getInstance(getApplicationContext());
        postDao = db.postDAO(); // Gets the DAO from the db object

        // Setting up recyclerView
        RecyclerView recyclerView = findViewById(R.id.browse_postsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Execute Runnable to fetch posts in the background
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new GetPostsRunnable());
    }//end onCreate

    // Runnable to perform database operation in the background
    private class GetPostsRunnable implements Runnable {

        @Override
        public void run() {
            // Fetch posts from the PostDAO in the background
            final List<Post> posts = postDao.getAllPosts();

            // Update UI components on the main thread
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    // Creating and setting up the adapter
                    PostAdapter adapter = new PostAdapter(posts);

                    // Set the click listener to this activity
                    adapter.setOnItemClickListener(BrowsePostsActivity.this);

                    RecyclerView recyclerView = findViewById(R.id.browse_postsRecyclerView);
                    recyclerView.setAdapter(adapter);
                }//end UI Thread run
            });//end runOnUiThread
        }//end GetPostsRunnable run
    }//end GetPostsRunnable

    // Implement the onItemClick method from the interface
    @Override
    public void onItemClick(Post post) {
        // Handle item click
        Intent intent = new Intent(this, PostFullViewActivity.class);
        intent.putExtra("POSTID", post.getPostID());
        startActivity(intent);
    }
}// End BrowsePostsActivity
