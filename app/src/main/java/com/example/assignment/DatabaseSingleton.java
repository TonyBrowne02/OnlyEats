package com.example.assignment;

import android.content.Context;

import androidx.room.Room;

public class DatabaseSingleton {
    //Database is created within this class and accessed from wherever is needed using
    //AppDatabase db = DatabaseSingleton.getInstance(getApplicationContext());
    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "PostsDB").fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}//end DatabaseSingleton
