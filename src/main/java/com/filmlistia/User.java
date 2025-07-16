package com.filmlistia;

public class User {
    private String username;
    private String password;

    // Constructor method
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Username return
    public String getUsername() {
        return username;
    }

    // Password Return
    public String getPassword() {
        return password;
    }
}
