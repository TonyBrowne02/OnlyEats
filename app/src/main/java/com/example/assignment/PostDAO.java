package com.example.assignment;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PostDAO {
    @Query("SELECT * FROM posts")
    List<Post> getAllPosts();

    @Insert
    void insertPost(Post post);

    @Update
    void updatePost(Post post);

    @Query("SELECT * FROM posts WHERE postID = :postId")
    Post getPostById(long postId);

}
