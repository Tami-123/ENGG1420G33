package com.example.phase1_1420;

import java.util.HashMap;
import java.util.Map;

/**
 * User Database that handles authentication and user role management (admin, or user / student).
 */
public class UserDatabase {

    // Stores usernames and corresponding passwords
    private static final Map<String, String> users = new HashMap<>();
    private static final Map<String, String> roles = new HashMap<>();

    // Static block to initialize user credentials and roles
    static {
        users.put("admin", "admin123");
        roles.put("admin", "ADMIN");
        users.put("user", "user123");
        roles.put("user", "USER");
    }

    /**
     * Authenticates the user based on provided username and password.
     * @param username The entered username.
     * @param password The entered password.
     * @return The role of the user if authentication is successful, otherwise null.
     */
    public static String authenticate(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password) ? roles.get(username) : null;
    }
}