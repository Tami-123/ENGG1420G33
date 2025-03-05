package com.example.phase1_1420;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * University Management System - Phase 1
 */
public class UniversityManagementSystem extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        SceneController.showLoginScreen(primaryStage); // Load login screen at startup
    }

    public static void main(String[] args) {
        launch(args);
    }
}