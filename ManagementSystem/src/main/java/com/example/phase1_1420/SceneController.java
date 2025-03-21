package com.example.phase1_1420;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * SceneController handles navigation between different UI screens.
 */
public class SceneController {

    public static void showLoginScreen(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        // Display login screen where user enter credentials:
        Label userLabel = new Label("Username:");
        TextField userField = new TextField();
        Label passLabel = new Label("Password:");
        PasswordField passField = new PasswordField();
        Label errorLabel = new Label();
        Button loginBtn = new Button("Login");

        grid.add(userLabel, 0, 0);
        grid.add(userField, 1, 0);
        grid.add(passLabel, 0, 1);
        grid.add(passField, 1, 1);
        grid.add(loginBtn, 1, 2);
        grid.add(errorLabel, 1, 3);

        // Handle login button click event
        loginBtn.setOnAction(e -> {
            String username = userField.getText();
            String password = passField.getText();
            String role = UserDatabase.authenticate(username, password);

            //Case if username or password is invalid
            if (role != null) {
                showDashboard(primaryStage, role, username);
            } else {
                errorLabel.setText("Invalid Login!");
            }
        });

        // Displays login page
        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login Page");
        primaryStage.show();
    }

    /**
     * Displays the dashboard based on the user's input (admin or user)
     */
    public static void showDashboard(Stage primaryStage, String role, String username) {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        Label welcomeLabel = new Label("Welcome, " + UserDatabase.getDisplayName(username) + "!");
        Button logoutButton = new Button("Logout");
        layout.getChildren().addAll(welcomeLabel, logoutButton);
        logoutButton.setOnAction(e -> showLoginScreen(primaryStage));

        // Case if user logs in as admin
        if (role.equals("ADMIN")) {
            layout.getChildren().addAll(
                    createModuleButton("Subjects Management", primaryStage, com.example.phase1_1420.Utilities.SubjectManagement::show),
                    createModuleButton("Course Management", primaryStage, com.example.phase1_1420.Utilities.CourseManagement::show),
                    createModuleButton("Student Management", primaryStage, com.example.phase1_1420.Utilities.StudentManagement::show),
                    createModuleButton("Faculty Management", primaryStage, com.example.phase1_1420.Utilities.FacultyManagement::show),
                    createModuleButton("Event Management", primaryStage, com.example.phase1_1420.Utilities.EventManagement::show)
            );
        }

        // Case if user logs in as user (or student)
        if (role.equals("USER") || role.equals("Alice") || role.equals("Bob") || role.equals("Carol") || role.equals("Lucka") || role.equals("David") || role.equals("Emily") || role.equals("George") || role.equals("Helen") || role.equals("Isaac") || role.equals("Jennifer")) {
            layout.getChildren().addAll(
                    createModuleButton("Subjects Management", primaryStage, com.example.phase1_1420.Utilities.SubjectManagement::view),
                    createModuleButton("Course Management", primaryStage, com.example.phase1_1420.Utilities.CourseManagement::view),
                    createModuleButton("Student Management", primaryStage, com.example.phase1_1420.Utilities.StudentManagement::view),
                    createModuleButton("Faculty Management", primaryStage, com.example.phase1_1420.Utilities.FacultyManagement::view),
                    createModuleButton("Event Management", primaryStage, com.example.phase1_1420.Utilities.EventManagement::view)
            );
        }

        // Display University Management System Dashboard
        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("University Management System - Dashboard");
    }

    /**
     * Helper method to create module buttons.
     */
    private static Button createModuleButton(String title, Stage stage, Runnable action) {
        Button button = new Button(title);
        button.setOnAction(e -> action.run());
        return button;
    }
}
