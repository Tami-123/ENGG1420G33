package com.example.managementsystem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.commons.io.filefilter.TrueFileFilter;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

public class LoginController {
    @FXML
    private Label userNameLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private TextField userNameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Button loginButton;

    public String userName;
    public String password;

    @FXML
    public void buttonOnClick() throws IOException {
        userName = userNameTextField.getText();
        password = passwordTextField.getText();

        ReadingExcelFile readingExcelFile = new ReadingExcelFile();
        //Putting the student ID and password into a list array
        ArrayList<String> studentNames = new ArrayList<>();
        ArrayList<String> studentPassword= new ArrayList<>();
        //Putting the staff ID and password in to a list array
        ArrayList<String> staffNames = new ArrayList<>();
        ArrayList<String> staffPassword = new ArrayList<>();


        try {
            readingExcelFile.ReadingNameExcelFile();
            studentNames = readingExcelFile.studentNamesFromExcelFile;
            studentPassword = readingExcelFile.studentPasswordFromExcelFile;
            staffNames = readingExcelFile.adminIDFromExcelFile;
            staffPassword = readingExcelFile.adminPasswordFromExcelFile;

        } catch (Exception e) {
            e.printStackTrace();
        }

        boolean Student = true;
        for (String studentName : studentNames) {
            if (studentName.contains(userName) && studentPassword.contains(password)) {
                Stage stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                stage.setTitle("Main Menu");
                stage.setScene(scene);
                stage.show();
            }
            else if (staffNames.contains(userName) && staffPassword.contains(password)) {
                Student = false;
                Stage stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                stage.setTitle("Main Menu");
                stage.setScene(scene);
                stage.show();
            }
            //Add page to say incorrect login
        }

        Stage currentStage = (Stage) loginButton.getScene().getWindow();
        currentStage.close();
    }



}