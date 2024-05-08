package com.ggkps.superflix.models;

public class LoginSchema {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        return "LoginSchema{username='" + this.username + "', password='" + this.password + "'}";
    }
}

