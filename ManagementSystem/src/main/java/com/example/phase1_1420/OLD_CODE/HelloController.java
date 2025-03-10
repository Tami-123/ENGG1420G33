package com.example.phase1_1420;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {

    @FXML
    private Label welcomeText; // Matches fx:id="welcomeText" in FXML

    @FXML
    private void onHelloButtonClick() {
        welcomeText.setText("Hello, JavaFX!"); // Updates label when button is clicked
    }
}