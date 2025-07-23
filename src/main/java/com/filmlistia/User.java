package com.filmlistia;

public class User {
    private String username;
    private String password;
    private int userID;

    // Constructor method
    public User(String username, String password, int userID) {
        this.username = username;
        this.password = password;
        this.userID = userID;
    }

    // Username return
    public String getUsername() {
        return username;
    }

    // Password Return
    public String getPassword() {
        return password;
    }

    // User ID return
    public int getUserID() {
        return userID;
    }


    // Username setter
    public void setUsername(String username) {
        this.username = username;
    }
    // Password setter
    public void setPassword(String password) {
        this.password = password;
    }
}
