package com.example.phase1_1420.Utilities;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardController {
    @FXML private Label welcomeLabel;

    @FXML private Button subjectsButton;
    @FXML private Button coursesButton;
    @FXML private Button studentsButton;
    @FXML private Button facultyButton;
    @FXML private Button eventsButton;

    private String userRole;

    public void setUserRole(String role) {
        this.userRole = role;
        welcomeLabel.setText("Welcome, " + role);

        // Restrict access if user is not an admin
        if (!"ADMIN".equals(role)) {
            subjectsButton.setDisable(true);
            coursesButton.setDisable(true);
            studentsButton.setDisable(true);
            facultyButton.setDisable(true);
            eventsButton.setDisable(true);
        }
    }

    @FXML
    private void handleSubjects() {
        if (!userRole.equals("ADMIN")) {
            showAccessDenied();
            return;
        }
        System.out.println("Opening Subjects Management...");
        // Load Subjects Management scene
    }

    @FXML
    private void handleCourses() {
        if (!userRole.equals("ADMIN")) {
            showAccessDenied();
            return;
        }
        System.out.println("Opening Course Management...");
    }

    @FXML
    private void handleStudents() {
        if (!userRole.equals("ADMIN")) {
            showAccessDenied();
            return;
        }
        System.out.println("Opening Student Management...");
    }

    @FXML
    private void handleFaculty() {
        if (!userRole.equals("ADMIN")) {
            showAccessDenied();
            return;
        }
        System.out.println("Opening Faculty Management...");
    }

    @FXML
    private void handleEvents() {
        if (!userRole.equals("ADMIN")) {
            showAccessDenied();
            return;
        }
        System.out.println("Opening Event Management...");
    }

    @FXML
    private void handleLogout() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login-view.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) welcomeLabel.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Login Page");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAccessDenied() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Access Denied");
        alert.setHeaderText(null);
        alert.setContentText("You do not have permission to access this section.");
        alert.showAndWait();
    }
}