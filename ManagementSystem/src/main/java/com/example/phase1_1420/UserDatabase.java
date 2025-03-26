package com.example.phase1_1420;

import java.util.HashMap;
import java.util.Map;

/**
 * User Database that handles authentication and user role management (admin, or user / student).
 */
public class UserDatabase {

    private static final Map<String, User> userMap = new HashMap<>();

    public static User CurrentUser = null;

    static {

        //Read Excel Sheet for Students & populate into map
        try {
            ReadingExcelFile reader = new ReadingExcelFile();
            reader.ReadingNameExcelFile();

            for (Student student : reader.studentList) {
                userMap.put(student.getId(), student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Admin
        userMap.put("admin", new User("ADMIN001", "admin123", "admin", "ADMIN", "admin@uofg.com"));

        // Student
        userMap.put("user", new User("USER001", "user123", "user", "USER", "user@uofg.com"));
    }

    public static String authenticate(String username, String password) {
        User user = userMap.get(username);
        if (user != null && user.getPassword().equals(password)) {
            CurrentUser = user;
            return (user instanceof Student) ? "USER" : "ADMIN";
        }
        return null;
    }

    public static String getDisplayName(String username) {
        User user = userMap.get(username);
        return (user != null) ? user.getId() : "Unknown";
    }
}