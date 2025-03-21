package com.example.phase1_1420;

import java.util.HashMap;
import java.util.Map;

/**
 * User Database that handles authentication and user role management (admin, or user / student).
 */
public class UserDatabase {

    private static final Map<String, String> users = new HashMap<>();
    private static final Map<String, String> roles = new HashMap<>();
    private static final Map<String, String> names = new HashMap<>();

    static {
        // Admin
        users.put("admin", "admin123");
        roles.put("admin", "ADMIN");
        names.put("admin", "Admin");

        // Generic user
        users.put("user", "user123");
        roles.put("user", "USER");
        names.put("user", "Alice");

        // Students
        users.put("alice@example.edu", "Alice123");
        roles.put("alice@example.edu", "USER");
        names.put("alice@example.edu", "Alice");

        users.put("bob@example.edu", "Bob123");
        roles.put("bob@example.edu", "USER");
        names.put("bob@example.edu", "Bob");

        users.put("carol@example.edu", "Carol123");
        roles.put("carol@example.edu", "USER");
        names.put("carol@example.edu", "Carol");

        users.put("lucka@example.edu", "Lucka123");
        roles.put("lucka@example.edu", "USER");
        names.put("lucka@example.edu", "Lucka");

        users.put("lee@example.edu", "David123");
        roles.put("lee@example.edu", "USER");
        names.put("lee@example.edu", "David");

        users.put("brown@example.edu", "Emily123");
        roles.put("brown@example.edu", "USER");
        names.put("brown@example.edu", "Emily");

        users.put("smith@example.edu", "George123");
        roles.put("smith@example.edu", "USER");
        names.put("smith@example.edu", "George");

        users.put("jones@example.edu", "Helen123");
        roles.put("jones@example.edu", "USER");
        names.put("jones@example.edu", "Helen");

        users.put("clark@example.edu", "Isaac123");
        roles.put("clark@example.edu", "USER");
        names.put("clark@example.edu", "Isaac");

        users.put("davis@example.edu", "Jennifer123");
        roles.put("davis@example.edu", "USER");
        names.put("davis@example.edu", "Jennifer");
    }

    public static String authenticate(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password)
                ? roles.get(username)
                : null;
    }

    public static String getDisplayName(String username) {
        return names.getOrDefault(username, "User");
    }
}