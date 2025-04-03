package com.example.phase1_1420;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class FacultyDashboardViewController implements Initializable {
    @FXML private Text studentNameText;
    @FXML private Text studentIdText;
    @FXML private Text DegreeLevelText;
    @FXML private Text CoursesOffText;

    private final ExcelFile excelReader = new ExcelFile();
    private Faculty currentFaculty;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            System.out.println("\n=== Initializing UserDashboardViewController ===");
            
            // Load data from Excel
            excelReader.ReadingNameExcelFile();
            
            // Get current student from UserDatabase
            currentFaculty = (Faculty) UserDatabase.CurrentUser;
            System.out.println("Current Student:");
            System.out.println("- ID: " + currentFaculty.getId());
            System.out.println("- Name: " + currentFaculty.getUsername());
            System.out.println("- Degree Level: " + currentFaculty.getDegree());
            System.out.println("- Courses Offered: " + currentFaculty.getCoursesOffered());

            
            // Update UI with student information
            updateStudentInfo();

        } catch (Exception e) {
            System.err.println("Error in initialize: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void updateStudentInfo() {
        studentNameText.setText(currentFaculty.getUsername());
        studentIdText.setText(currentFaculty.getId());
        DegreeLevelText.setText(currentFaculty.getDegree());
        CoursesOffText.setText(currentFaculty.getCoursesOffered());

    }

}
