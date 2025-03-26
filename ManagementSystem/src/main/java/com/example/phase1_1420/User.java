package com.example.phase1_1420;

import org.apache.poi.ddf.NullEscherSerializationListener;

public class User {

    protected String id;
    protected String password;
    protected String username;
    protected String email;


    protected final String role;

    public User(String id, String password, String username, String role, String email) {
        this.id = id;
        this.password = password;
        this.username = username;
        this.role = role;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getRole(){
        return role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Password: " + password;
    }
}
