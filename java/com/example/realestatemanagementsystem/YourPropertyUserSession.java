package com.example.realestatemanagementsystem;

public class YourPropertyUserSession {
    private static YourPropertyUserSession instance;
    private boolean loggedIn;
    private String userEmail;
    private String userPassword;

    private YourPropertyUserSession() {
        this.loggedIn = false;
        this.userEmail = null;
        this.userPassword = null;
    }

    public static YourPropertyUserSession getInstance() {
        if (instance == null) {
            instance = new YourPropertyUserSession();
        }
        return instance;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void loginUser(String email, String password) {
        this.loggedIn = true;
        this.userEmail = email;
        this.userPassword = password;
    }

    public void logoutUser() {
        this.loggedIn = false;
        this.userEmail = null;
        this.userPassword = null;
    }

    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }
}
