package com.example.managementsystem.utils;


public class SessionManagerClass {
    private static String currentUserRole;  // Fixed casing
    private static String currentUsername;

    // Corrected setUser method
    public static void setUser(String username, String role) {
        currentUsername = username;
        currentUserRole = role;
    }

    // Getter for user role
    public static String getCurrentUserRole() {
        return currentUserRole;
    }

    // Getter for username
    public static String getCurrentUsername() {
        return currentUsername;
    }

    // Logout method to clear session
    public static void logout() {
        currentUserRole = null;
        currentUsername = null;
    }
}
