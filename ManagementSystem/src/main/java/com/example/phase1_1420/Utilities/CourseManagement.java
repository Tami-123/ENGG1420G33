package com.example.phase1_1420.Utilities;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Handles Course Management functionality for Admin and User views.
 */
public class CourseManagement {
    public static void show() { showModule("Course Management - Admin View"); }
    public static void view() { showModule("Course Management - User View"); }

    /**
     * Displays the Course Management window.
     * @param title The title of the window, varying for Admin/User.
     */
    private static void showModule(String title) {
        Stage stage = new Stage();
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().add(new Label(title));
        Scene scene = new Scene(layout, 300, 200);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }
}
