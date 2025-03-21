package com.example.phase1_1420.Utilities;

import com.example.phase1_1420.SubjectManagementController;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Handles Subject Management functionality for both the Admin and User views.
 */
public class SubjectManagement {
    public static void show() { loadModule("subject-management-view.fxml", "Subject Management - Admin View", true); }
    public static void view() { loadModule("subject-management-view.fxml", "Subject Management - User View", false); }
    /**
     * Displays the Subject Management window.
     * @param title The title of the window, varying for Admin/User.
     */
    private static void loadModule(String fxmlFile, String title, boolean isAdmin) {
        try {
            FXMLLoader loader = new FXMLLoader(SubjectManagement.class.getResource(fxmlFile));
            Parent root = loader.load();

            SubjectManagementController controller = loader.getController();
            controller.setAdminMode(isAdmin);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
