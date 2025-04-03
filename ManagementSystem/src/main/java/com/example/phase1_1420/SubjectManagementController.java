package com.example.phase1_1420;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.Optional;

public class SubjectManagementController {


    private ObservableList<Subject> subjects = FXCollections.observableArrayList();
    private final ExcelFile excelReader = new ExcelFile();


    @FXML
    private TextField subjectNameField;

    @FXML
    private TextField subjectCodeField;

    @FXML
    private TextField searchField;

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


    private final ObservableList<Subject> allSubjects = FXCollections.observableArrayList(); // master copy
    private final FilteredList<Subject> filteredSubjects = new FilteredList<>(allSubjects, p -> true);

    @FXML
    public void initialize() {
        subjectCodeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        subjectNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        subjectTable.setItems(filteredSubjects);

        subjectTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showSubjectDetails(newValue)
        );

        // Search logic
        searchField.textProperty().addListener((obs, oldValue, newValue) -> {
            String lower = newValue.toLowerCase().trim();
            filteredSubjects.setPredicate(subject -> {
                if (lower.isEmpty()) return true;
                return subject.getCode().toLowerCase().contains(lower) ||
                        subject.getName().toLowerCase().contains(lower);
            });
        });

        setSubjectList(); // load data
        setAdminMode(UserDatabase.CurrentUser.getRole().equals("ADMIN"));
    }

    private void showSubjectDetails(Subject subject) {
        if (subject != null) {
            subjectNameField.setText(subject.getName());
            subjectCodeField.setText(subject.getCode());
        } else {
            clearFields();
        }
    }

    public void setSubjectList() {
        try {
            excelReader.ReadingNameExcelFile();
            allSubjects.setAll(excelReader.subjectList);
        } catch (Exception e) {
            e.printStackTrace();
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

    public void saveSubjectsToExcel() {
        try {
            excelReader.writeSubjectsToExcel(allSubjects); // subjects is the ObservableList<Subject>
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        for(Subject s : subjects){
            if(s.getCode().equalsIgnoreCase(code)){
                showAlert(Alert.AlertType.ERROR, "Duplicate Subject Code", "A subject with this code already exists.");
                return;
            }
        }


        // Create new subject
        Subject newSubject = new Subject(code, name);
            // Add to the observable list - this will automatically update the table view
            allSubjects.add(newSubject);

            // Clear input fields
            clearFields();

            // Show success message
            showAlert(Alert.AlertType.INFORMATION, "Success",
                    "Subject added successfully.");
            saveSubjectsToExcel();

            subjectTable.refresh();
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
        for(Subject s : subjects){
            if(s.getCode().equalsIgnoreCase(code)){
                showAlert(Alert.AlertType.ERROR, "Duplicate Subject Code", "A subject with this code already exists.");
                return;
            }
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