package com.example.phase1_1420;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;

public class AdminDashboardController {
    @FXML private Pane sidebarPane;
    @FXML private VBox sidebar;
    @FXML private Button toggleButton;
    @FXML private StackPane contentArea;
    private boolean sidebarVisible = true;

    @FXML
    private void toggleSidebar() {
        Stage stage = (Stage) toggleButton.getScene().getWindow(); // Get the window

        if (!sidebarVisible) {
            // Show Sidebar
            sidebarPane.setVisible(true);
            sidebarPane.setManaged(true);
            sidebarPane.setPrefWidth(200);
            contentArea.setLayoutX(200);
            toggleButton.setText("☰");
            sidebarVisible = true;
        } else {
            // Hide Sidebar
            sidebarPane.setVisible(false);
            sidebarPane.setManaged(false);
            sidebarPane.setPrefWidth(0);
            contentArea.setLayoutX(10);
            toggleButton.setText("≡");
            sidebarVisible = false;
        }

        // **Prevent Window from Shrinking**
        stage.sizeToScene(); // Recalculate size without shrinking window
    }

    @FXML
    private void handleSubjects() { loadContent("subjects-view.fxml"); }

    @FXML
    private void handleCourses() { loadContent("courses-view.fxml"); }

    @FXML
    private void handleStudents() { loadContent("students-view.fxml"); }

    @FXML
    private void handleFaculty() { loadContent("faculty-view.fxml"); }

    @FXML
    private void handleEvents() { loadContent("events-view.fxml"); }

    @FXML
    private void handleLogout() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/phase1_1420/login-view.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) sidebar.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Login Page");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadContent(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/phase1_1420/" + fxmlFile));
            contentArea.getChildren().clear();
            contentArea.getChildren().add(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}