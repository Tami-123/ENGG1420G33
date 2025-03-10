package com.example.phase1_1420;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginController {
    @FXML private TextField userField;
    @FXML private PasswordField passField;
    @FXML private Label errorLabel;

    @FXML
    private void handleLogin() {
        String username = userField.getText();
        String password = passField.getText();
        String role = UserDatabase.authenticate(username, password);

        if (role != null) {
            loadDashboard(role);
        } else {
            errorLabel.setText("Invalid Login!");
        }
    }

    private void loadDashboard(String role) {
        try {
            String fxmlFile = "ADMIN".equals(role) ? "admin-dashboard.fxml" : "user-dashboard.fxml";
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/phase1_1420/" + fxmlFile));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) userField.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle(role + " Dashboard");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}