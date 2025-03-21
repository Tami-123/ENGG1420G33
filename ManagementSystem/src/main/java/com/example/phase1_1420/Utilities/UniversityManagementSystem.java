package com.example.phase1_1420.Utilities;

import com.example.phase1_1420.SceneController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * University Management System
 * */
public class UniversityManagementSystem extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        SceneController.showLoginScreen(primaryStage); // Load login screen at the startup
    }

    public static void main(String[] args) {
        launch(args);
    }
}
