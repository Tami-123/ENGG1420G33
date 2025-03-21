package com.example.phase1_1420;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Optional;

public class SubjectManagementController {

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

    // This is the key part - use an ObservableList to store subjects
    private final ObservableList<Subject> subjects = FXCollections.observableArrayList();

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
        loadSubjectsFromDatabase();

        // Set default mode
        setAdminMode(false);
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
        Subject newSubject = new Subject(name, code);

        // Add to database
        boolean success = addSubjectToDatabase(newSubject);

        if (success) {
            // Add to the observable list - this will automatically update the table view
            subjects.add(newSubject);

            // Clear input fields
            clearFields();

            // Show success message
            showAlert(Alert.AlertType.INFORMATION, "Success",
                    "Subject added successfully.");
        }
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

        // Update in database
        boolean success = updateSubjectInDatabase(selectedSubject);

        if (success) {
            // Refresh the table (not strictly necessary with properties)
            subjectTable.refresh();

            // Clear input fields
            clearFields();

            // Show success message
            showAlert(Alert.AlertType.INFORMATION, "Success",
                    "Subject updated successfully.");
        }
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
            boolean success = deleteSubjectFromDatabase(selectedSubject);

            if (success) {
                // Remove from the observable list - this will update the table
                subjects.remove(selectedSubject);

                // Clear input fields
                clearFields();

                // Show success message
                showAlert(Alert.AlertType.INFORMATION, "Success",
                        "Subject deleted successfully.");
            }
        }
    }

    private void loadSubjectsFromDatabase() {
        // Clear existing items
        subjects.clear();

        // Add your database retrieval code here
        // For example:
        // List<Subject> loadedSubjects = subjectDAO.getAllSubjects();
        // subjects.addAll(loadedSubjects);

        // For testing, you can add some sample data
        subjects.add(new Subject("Mathematics", "MATH101"));
        subjects.add(new Subject("Computer Science", "CS101"));
        subjects.add(new Subject("Physics", "PHYS101"));
    }

    private boolean addSubjectToDatabase(Subject subject) {
        // Add your database insertion code here
        // For example:
        // return subjectDAO.insert(subject);

        // For testing, just return true
        return true;
    }

    private boolean updateSubjectInDatabase(Subject subject) {
        // Add your database update code here
        // For example:
        // return subjectDAO.update(subject);

        // For testing, just return true
        return true;
    }

    private boolean deleteSubjectFromDatabase(Subject subject) {
        // Add your database deletion code here
        // For example:
        // return subjectDAO.delete(subject);

        // For testing, just return true
        return true;
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