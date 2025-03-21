package com.example.phase1_1420;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.Optional;

public class EventManagementController {

    @FXML
    private TextField eventNameField;

    @FXML
    private DatePicker eventDateField;

    @FXML
    private Button addButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TableView<Event> eventTable;

    @FXML
    private TableColumn<Event, String> eventNameColumn;

    @FXML
    private TableColumn<Event, LocalDate> eventDateColumn;

    private boolean isAdmin = false;

    private final ObservableList<Event> events = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        eventNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        eventDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        eventTable.setItems(events);
        eventTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showEventDetails(newValue));

        loadEventsFromDatabase();
        setAdminMode(false);
    }

    private void showEventDetails(Event event) {
        if (event != null) {
            eventNameField.setText(event.getName());
            eventDateField.setValue(event.getDate());
        } else {
            clearFields();
        }
    }

    public void setAdminMode(boolean isAdmin) {
        this.isAdmin = isAdmin;
        eventNameField.setDisable(!isAdmin);
        eventDateField.setDisable(!isAdmin);
        addButton.setDisable(!isAdmin);
        editButton.setDisable(!isAdmin);
        deleteButton.setDisable(!isAdmin);
    }

    @FXML
    private void handleAddEvent() {
        if (!isAdmin) {
            showAlert(Alert.AlertType.ERROR, "Access Denied", "You don't have permission to add events.");
            return;
        }

        String name = eventNameField.getText().trim();
        LocalDate date = eventDateField.getValue();

        if (name.isEmpty() || date == null) {
            showAlert(Alert.AlertType.WARNING, "Missing Information", "Please enter both event name and date.");
            return;
        }

        Event newEvent = new Event(name, date);
        boolean success = addEventToDatabase(newEvent);

        if (success) {
            events.add(newEvent);
            clearFields();
            showAlert(Alert.AlertType.INFORMATION, "Success", "Event added successfully.");
        }
    }

    @FXML
    private void handleEditEvent() {
        if (!isAdmin) {
            showAlert(Alert.AlertType.ERROR, "Access Denied", "You don't have permission to edit events.");
            return;
        }

        Event selectedEvent = eventTable.getSelectionModel().getSelectedItem();
        if (selectedEvent == null) {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select an event to edit.");
            return;
        }

        String name = eventNameField.getText().trim();
        LocalDate date = eventDateField.getValue();

        if (name.isEmpty() || date == null) {
            showAlert(Alert.AlertType.WARNING, "Missing Information", "Please enter both event name and date.");
            return;
        }

        selectedEvent.setName(name);
        selectedEvent.setDate(date);
        boolean success = updateEventInDatabase(selectedEvent);

        if (success) {
            eventTable.refresh();
            clearFields();
            showAlert(Alert.AlertType.INFORMATION, "Success", "Event updated successfully.");
        }
    }

    @FXML
    private void handleDeleteEvent() {
        if (!isAdmin) {
            showAlert(Alert.AlertType.ERROR, "Access Denied", "You don't have permission to delete events.");
            return;
        }

        Event selectedEvent = eventTable.getSelectionModel().getSelectedItem();
        if (selectedEvent == null) {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select an event to delete.");
            return;
        }

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirm Deletion");
        confirmation.setHeaderText("Delete Event");
        confirmation.setContentText("Are you sure you want to delete " + selectedEvent.getName() + "?");

        Optional<ButtonType> result = confirmation.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            boolean success = deleteEventFromDatabase(selectedEvent);

            if (success) {
                events.remove(selectedEvent);
                clearFields();
                showAlert(Alert.AlertType.INFORMATION, "Success", "Event deleted successfully.");
            }
        }
    }

    private void loadEventsFromDatabase() {
        events.clear();
        events.add(new Event("Conference", LocalDate.of(2025, 5, 10)));
        events.add(new Event("Workshop", LocalDate.of(2025, 6, 15)));
    }

    private boolean addEventToDatabase(Event event) {
        return true;
    }

    private boolean updateEventInDatabase(Event event) {
        return true;
    }

    private boolean deleteEventFromDatabase(Event event) {
        return true;
    }

    private void clearFields() {
        eventNameField.clear();
        eventDateField.setValue(null);
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
