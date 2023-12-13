package com.example.assignment;

public class GlobalVars {
    private static GlobalVars instance;
    private String email;
    private long userID;

    private GlobalVars(){}

    public static synchronized GlobalVars getInstance() {
        if (instance == null) {
            instance = new GlobalVars();
        }
        return instance;
    }//getInstance()

    public String isSignedIn() {
        return email;
    }

    public void setUserEmail(String email) {
        this.email = email;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getUserEmail() {
        return email;
    }
}//end GlobalVars
