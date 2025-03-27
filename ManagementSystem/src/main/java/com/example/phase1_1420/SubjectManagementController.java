package com.example.phase1_1420;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.Optional;

public class SubjectManagementController {


    private ObservableList<Subject> subjects = FXCollections.observableArrayList();
    private final ExcelFile excelReader = new ExcelFile();

    public void saveSubjectsToExcel() {
        try {
            excelReader.writeSubjectsToExcel(subjects); // subjects is the ObservableList<Subject>
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setSubjectList() {
        try {
            excelReader.ReadingNameExcelFile(); // or whatever your method is called
            this.subjects = FXCollections.observableArrayList(excelReader.subjectList);
            subjectTable.setItems(subjects);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private TextField subjectNameField;

    @FXML
    private TextField subjectCodeField;

    @FXML
    private Button addButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TableView<Subject> subjectTable;

    @FXML
    private TableColumn<Subject, String> subjectNameColumn;

    @FXML
    private TableColumn<Subject, String> subjectCodeColumn;

    private boolean isAdmin = false;

    @FXML
    public void initialize() {
        // Configure table columns with correct property names
        subjectNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        subjectCodeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));

        // Link the observable list to the table view
        subjectTable.setItems(subjects);

        // Add selection listener for the table
        subjectTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showSubjectDetails(newValue));

        // Load existing subjects
        setSubjectList();

        // Set default mode
        if (UserDatabase.CurrentUser.getRole().equals("ADMIN")) {
            setAdminMode(true);
        }else {
            setAdminMode(false);
        }
    }

    private void showSubjectDetails(Subject subject) {
        if (subject != null) {
            subjectNameField.setText(subject.getName());
            subjectCodeField.setText(subject.getCode());
        } else {
            clearFields();
        }
    }

    public void setAdminMode(boolean isAdmin) {
        this.isAdmin = isAdmin;

        // Enable or disable controls based on role
        subjectNameField.setDisable(!isAdmin);
        subjectCodeField.setDisable(!isAdmin);
        addButton.setDisable(!isAdmin);
        editButton.setDisable(!isAdmin);
        deleteButton.setDisable(!isAdmin);
    }

    @FXML
    private void handleAddSubject() {
        if (!isAdmin) {
            showAlert(Alert.AlertType.ERROR, "Access Denied",
                    "You don't have permission to add subjects.");
            return;
        }

        String name = subjectNameField.getText().trim();
        String code = subjectCodeField.getText().trim();

        if (name.isEmpty() || code.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Missing Information",
                    "Please enter both subject name and code.");
            return;
        }

        // Create new subject
        Subject newSubject = new Subject(code, name);
            // Add to the observable list - this will automatically update the table view
            subjects.add(newSubject);

            // Clear input fields
            clearFields();

            // Show success message
            showAlert(Alert.AlertType.INFORMATION, "Success",
                    "Subject added successfully.");
            saveSubjectsToExcel();

    }

    @FXML
    private void handleEditSubject() {
        if (!isAdmin) {
            showAlert(Alert.AlertType.ERROR, "Access Denied",
                    "You don't have permission to edit subjects.");
            return;
        }

        Subject selectedSubject = subjectTable.getSelectionModel().getSelectedItem();
        if (selectedSubject == null) {
            showAlert(Alert.AlertType.WARNING, "No Selection",
                    "Please select a subject to edit.");
            return;
        }

        String name = subjectNameField.getText().trim();
        String code = subjectCodeField.getText().trim();

        if (name.isEmpty() || code.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Missing Information",
                    "Please enter both subject name and code.");
            return;
        }

        // Update subject properties
        selectedSubject.setName(name);
        selectedSubject.setCode(code);


            // Refresh the table (not strictly necessary with properties)
            subjectTable.refresh();

            // Clear input fields
            clearFields();

            // Show success message
            showAlert(Alert.AlertType.INFORMATION, "Success",
                    "Subject updated successfully.");
        saveSubjectsToExcel();

    }

    @FXML
    private void handleDeleteSubject() {
        if (!isAdmin) {
            showAlert(Alert.AlertType.ERROR, "Access Denied",
                    "You don't have permission to delete subjects.");
            return;
        }

        Subject selectedSubject = subjectTable.getSelectionModel().getSelectedItem();
        if (selectedSubject == null) {
            showAlert(Alert.AlertType.WARNING, "No Selection",
                    "Please select a subject to delete.");
            return;
        }

        // Confirm deletion
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirm Deletion");
        confirmation.setHeaderText("Delete Subject");
        confirmation.setContentText("Are you sure you want to delete " +
                selectedSubject.getName() + " (" + selectedSubject.getCode() + ")?");

        Optional<ButtonType> result = confirmation.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {

                // Remove from the observable list - this will update the table
                subjects.remove(selectedSubject);

                // Clear input fields
                clearFields();

                // Show success message
                showAlert(Alert.AlertType.INFORMATION, "Success",
                        "Subject deleted successfully.");
            saveSubjectsToExcel();

        }
    }


    private void clearFields() {
        subjectNameField.clear();
        subjectCodeField.clear();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}