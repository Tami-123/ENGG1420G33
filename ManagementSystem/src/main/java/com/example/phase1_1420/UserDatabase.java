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
            ExcelFile reader = new ExcelFile();
            reader.ReadingNameExcelFile();

            for (Student student : reader.studentList) {
                userMap.put(student.getId(), student);
            }
            for (Faculty faculty : reader.facultyList){
                userMap.put(faculty.getId(), faculty);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        // Admin
        userMap.put("ADMIN", new User("ADMIN001", "admin123", "admin", "ADMIN", "admin@uofg.com"));

        // User
        userMap.put("USER", new User("USER001", "user123", "user", "USER", "user@uofg.com"));
    }

    public static String authenticate(String username, String password) {
        //TO upper case to remove case sensitivty for login id
        User user = userMap.get(username.toUpperCase());
        if (user != null && user.getPassword().equals(password)) {
            CurrentUser = user;
            return (CurrentUser.role);
        }
        return null;
    }

    public static String getDisplayName(String username) {
        User user = userMap.get(username);
        return (user != null) ? user.getId() : "Unknown";
    }
}