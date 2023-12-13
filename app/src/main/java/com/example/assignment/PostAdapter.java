package com.example.assignment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostViewHolder>{
    private List<Post> postList;

    public interface OnItemClickListener {
        void onItemClick(Post post);
    }
    private OnItemClickListener mListener;
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
    // Constructor to initialize the list
    public PostAdapter(List<Post> postList) {
        this.postList = postList;
    }

    // Override onCreateViewHolder to inflate the item layout and create the ViewHolder
    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item layout and create the ViewHolder
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_layout, parent, false);
        return new PostViewHolder(view);
    }

    // Override onBindViewHolder to bind data to the ViewHolder
    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post post = postList.get(position);

        // Set data to the views in ViewHolder
        holder.textDishName.setText(post.getMealName());
        holder.textPosterName.setText(post.getPosterName());
        holder.ratingBar.setRating(post.getMealRating());
        //Read only star rating on browse posts
        holder.ratingBar.setIsIndicator(true);
        // Set a click listener for the item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(post);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return postList.size();
    }
}
