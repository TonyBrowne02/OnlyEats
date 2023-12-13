package com.example.assignment;

import android.content.Context;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.ForeignKey;

@Entity(
        tableName = "comments",
        foreignKeys = {
                @ForeignKey(
                        entity = User.class,
                        parentColumns = "userEmail",
                        childColumns = "userEmail",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(
                        entity = Post.class,
                        parentColumns = "postID",
                        childColumns = "postID",
                        onDelete = ForeignKey.CASCADE
                )},
        indices = {@Index("userEmail"), @Index("postID")})

public class Comment {

    @PrimaryKey(autoGenerate = true)
    private long commentID;
    private String userEmail;//foreign key
    private long postID;//foreign key
    private String comment;



    public Comment(String userEmail, long postID, String comment ) {
        this.userEmail = userEmail;
        this.postID = postID;
        this.comment = comment;
    }

    public long getCommentID() {
        return commentID;
    }
    public void setCommentID(long commentID) {
        this.commentID = commentID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getPostID() {
        return postID;
    }

    public void setPostID(long postID) {
        this.postID = postID;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}//end Comment
