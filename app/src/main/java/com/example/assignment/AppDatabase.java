package com.example.assignment;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Post.class, Comment.class, User.class}, version = 5, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PostDAO postDAO();
    public abstract CommentDAO commentDAO();
    public abstract UserDAO userDAO();




}
