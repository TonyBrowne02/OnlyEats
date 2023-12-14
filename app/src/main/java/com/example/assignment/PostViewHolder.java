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
    ImageView imagePost;

    public PostViewHolder(@NonNull View itemView) {
        super(itemView);

        textDishName = itemView.findViewById(R.id.textDishName);
        textPosterName = itemView.findViewById(R.id.textPosterName);
        ratingBar = itemView.findViewById(R.id.ratingBar);
        imagePost = itemView.findViewById(R.id.imagePost);
    }
}
