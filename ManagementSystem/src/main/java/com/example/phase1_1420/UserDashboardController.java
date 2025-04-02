package com.example.phase1_1420;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;

public class UserDashboardController {
    @FXML private Pane sidebarPane;
    @FXML private Button toggleButton;
    @FXML private StackPane contentArea;
    @FXML private Text UserName;
    @FXML private Text UserID;
    private boolean sidebarVisible = false;
    private final ExcelFile excelReader = new ExcelFile();

    @FXML
    private void initialize() {
        try {
            // Load data from Excel
            excelReader.ReadingNameExcelFile();
            
            // Get current student from UserDatabase
            Student currentStudent = (Student) UserDatabase.CurrentUser;
            
            // Set user information
            UserName.setText(currentStudent.getUsername());
            UserID.setText(currentStudent.getId());
            
            // Initialize sidebar
            sidebarPane.setTranslateX(-200);
            toggleButton.setText("☰");
            
            // Load initial content
            loadContent("user-dashboard-view.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void toggleSidebar() {
        TranslateTransition slide = new TranslateTransition(Duration.millis(300), sidebarPane);

        if (sidebarVisible) {
            slide.setToX(-200);
            toggleButton.setText("☰");
            sidebarVisible = false;
        } else {
            slide.setToX(0);
            toggleButton.setText("≡");
            sidebarVisible = true;
        }
        slide.play();
    }

    @FXML
    private void handleDashboard() { loadContent("user-dashboard-view.fxml"); }

    @FXML
    private void handleCourses() { loadContent("course-management-view.fxml"); }

    @FXML
    private void handleSubjects() { loadContent("subject-management-view.fxml"); }

    @FXML
    private void handleEvents() { loadContent("user-events-view.fxml"); }

    @FXML
    private void handleProfile() { loadContent("user-profile-view.fxml"); }

    @FXML
    private void handleLogout() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/phase1_1420/login-view.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) sidebarPane.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Login Page");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadContent(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            contentArea.getChildren().clear();
            contentArea.getChildren().add(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}