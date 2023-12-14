package com.example.assignment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class PostFullViewActivity extends AppCompatActivity {
    private TextView textDishName;
    private TextView textPosterName;
    private TextView mealDescription;
    private TextView mealLocationRecipe;
    private RatingBar ratingBar;
    private RecyclerView recyclerComments;
    private EditText editComment;
    private Button btnSubmitComment;
    private Button btnAddCollection;
    private ImageView postImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_full_view);

        textDishName = findViewById(R.id.textDishName);
        textPosterName = findViewById(R.id.textPosterName);
        ratingBar = findViewById(R.id.ratingBar);
        mealDescription = findViewById(R.id.textMealDescription);
        mealLocationRecipe = findViewById(R.id.textMealLocationRecipe);
        recyclerComments = findViewById(R.id.recyclerComments);
        editComment = findViewById(R.id.editComment);
        btnSubmitComment = findViewById(R.id.btnSubmitComment);
        //btnAddCollection = findViewById(R.id.btnAddCollection);
        recyclerComments.setLayoutManager(new LinearLayoutManager(this));
        postImage = findViewById(R.id.imageFullPost);

        // Get post ID from Intent or any other source
        long postID = getIntent().getLongExtra("POSTID", -1);

        //handles getting the post info on a separate thread
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new GetFullPostTask(getApplicationContext(), postID));


        // Set click listener for the "Submit Comment" button
        btnSubmitComment.setOnClickListener(view -> {
            String commentText = editComment.getText().toString().trim();
            if(!commentText.isEmpty()) {
                GlobalVars globalVars = GlobalVars.getInstance();
                Comment comment = new Comment(globalVars.getUserEmail(), postID,commentText);
                Executor executor1 = Executors.newSingleThreadExecutor();
                executor1.execute(new UploadComment(getApplicationContext(), comment));
                Toast.makeText(getApplicationContext(), "Comment submitted!", Toast.LENGTH_SHORT).show();
                editComment.setText("");//clearing comment field
            }
            else {
                Toast.makeText(getApplicationContext(), "Enter a comment before submitting!", Toast.LENGTH_SHORT).show();
            }//end if else

        });//end btnSubmitComment listener


        // Set click listener for the "Add to Collection" button
        btnAddCollection.setOnClickListener(view -> {
            // TODO: Handle adding to collection
            // You can perform actions like adding the post to the user's collection, etc.
        });
    }//end onCreate

    private class GetFullPostTask implements Runnable {
        private final Context context;
        private final long postID;

        GetFullPostTask(Context context, long postID) {
            this.context = context;
            this.postID = postID;
        }

        public void run() {
            // Fetch the specific post by ID
            AppDatabase db = DatabaseSingleton.getInstance(context.getApplicationContext());
            PostDAO postDao = db.postDAO();
            CommentDAO commentDao = db.commentDAO();
            Post post = postDao.getPostById(postID);
            List<Comment> comments = commentDao.getCommentsByPostID(postID);

            // Update UI on the main thread
            runOnUiThread(() -> updateUI(post, comments));
        }//end run

        private void updateUI(Post post, List<Comment> comments) {
            if (post != null) {
                // Set values to UI elements
                textDishName.setText(post.getMealName());
                textPosterName.setText(post.getPosterName());
                ratingBar.setRating(post.getMealRating());
                ratingBar.setIsIndicator(true);
                mealLocationRecipe.setText(post.getMealLocationRecipe());
                mealDescription.setText(post.getMealDescription());
                postImage.setImageBitmap(ImageUtils.decompressBitmap(ImageUtils.decompressByteArray(post.getCompressedImageData())));
            } else {
                Log.e("ERROR","NA Post requested on updateUI in PostFullView");
            }//end update post ui

            if (comments != null) {
                //add comments to recycler view
                CommentAdapter commentAdapter = new CommentAdapter(comments);
                recyclerComments.setAdapter(commentAdapter);
                Log.e("ALERT","WE HAVE COMMENTS");
            }
        }//end UpdateUI
    }//end GetFullPostTask

    private class UploadComment implements Runnable {

        private final Context context;
        private final Comment comment;

        UploadComment(Context context, Comment comment) {
            this.context = context;
            this.comment = comment;
        }

        @Override
        public void run() {

            AppDatabase db = DatabaseSingleton.getInstance(context.getApplicationContext());
            try {
                CommentDAO commentDao = db.commentDAO();
                // Insert the comment into the database
                commentDao.insertComment(comment);
                Log.e("NOTIF", "COMMENT UPLOADED");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }//end run
    }//end UploadComment
}//end PostFullView
