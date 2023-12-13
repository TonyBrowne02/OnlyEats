package com.example.assignment;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "users", indices = {@Index(value = "userEmail", unique = true)})
public class User {

    @PrimaryKey(autoGenerate = true)
    private long userID;
    private String userEmail;
    private String password;

    public User(String userEmail, String password) {
        this.userEmail = userEmail;
        this.password = password;
    }//end User

    // Getters and Setters for userID and name
    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setuserEmail(String email) {
        this.userEmail = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
