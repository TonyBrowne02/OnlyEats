package com.example.assignment;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CommentDAO {
    @Insert
    void insertComment(Comment comment);

    @Query("Select * FROM comments where postID = :postID")
    List<Comment> getCommentsByPostID(long postID);
}
