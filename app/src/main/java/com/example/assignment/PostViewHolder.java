package com.example.assignment;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

// ViewHolder class to hold references to the views in each item layout
public class PostViewHolder extends RecyclerView.ViewHolder {
    TextView textDishName, textPosterName;
    RatingBar ratingBar;
    ImageView imageView;

    public PostViewHolder(@NonNull View itemView) {
        super(itemView);

        textDishName = itemView.findViewById(R.id.textDishName);
        textPosterName = itemView.findViewById(R.id.textPosterName);
        ratingBar = itemView.findViewById(R.id.ratingBar);
        imageView = itemView.findViewById(R.id.imageView);

        // Set a click listener
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle item click, open post_full_view activity, etc.
                //TODO
            }
        });
    }
}
