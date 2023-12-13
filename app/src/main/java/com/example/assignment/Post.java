package com.example.assignment;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "posts")
public class Post {
    //Post post = new Post(mealName, mealDescription, mealLocationRecipe, mealRating, posterName);
    @PrimaryKey(autoGenerate = true)
    private long postID;
    private String mealName;
    private String mealDescription;
    private String mealLocationRecipe;
    private float mealRating;
    private String posterName;

    //constructor
    public Post(String mealName, String mealDescription, String mealLocationRecipe, String posterName, float mealRating) {
        this.mealName = mealName;
        this.mealDescription = mealDescription;
        this.mealLocationRecipe = mealLocationRecipe;
        this.posterName = posterName;
        this.mealRating = mealRating;

    }

    //getters and setters
    public long getPostID() {
        return this.postID;
    }

    public void setPostID(long postID) {
        this.postID = postID;
    }

    public String getMealName() {
        return this.mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getMealDescription() {
        return this.mealDescription;
    }

    public void setMealDescription(String mealDescription) {
        this.mealDescription = mealDescription;
    }

    public String getMealLocationRecipe() {
        return this.mealLocationRecipe;
    }

    public void setMealLocationRecipe(String mealLocationRecipe) {
        this.mealLocationRecipe = mealLocationRecipe;
    }

    public float getMealRating() {
        return this.mealRating;
    }

    public void setMealRating(float mealRating) {
        this.mealRating = mealRating;
    }

    public String getPosterName() {
        return this.posterName;
    }

    public void setPosterName(String posterName) {
        this.posterName = posterName;
    }
}//end Post
