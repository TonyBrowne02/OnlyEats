package com.example.assignment;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDAO {
    @Insert
    void insertUser(User user);

    @Query("SELECT * FROM users WHERE userID = :userID")
    User getUserById(long userID);

    @Query("SELECT * FROM users where userEmail = :email AND password = :password")
    User getUser(String email, String password);

    @Query("SELECT userID FROM users where userEmail = :email")
    long getUserIDByEmail(String email);

}
