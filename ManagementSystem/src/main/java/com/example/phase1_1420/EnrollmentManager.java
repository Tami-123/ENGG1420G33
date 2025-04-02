package com.example.phase1_1420;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentManager {

    public static void showForCourse(Course course, List<Student> allStudents, ExcelFile excelFile) {
        String subjectCode = course.getCode();

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Manage Enrollments for Subject: " + subjectCode);

        ObservableList<Student> observableStudents = FXCollections.observableArrayList(allStudents);
        ListView<CheckBox> listView = new ListView<>();

        for (Student student : observableStudents) {
            CheckBox cb = new CheckBox(student.getUsername() + " (" + student.getId() + ")");
            cb.setUserData(student);

            if (student.getSubjects() != null && student.getSubjects().contains(subjectCode)) {
                cb.setSelected(true);
            }
            listView.getItems().add(cb);
        }

        Button saveButton = new Button("Save Changes");
        saveButton.setOnAction(e -> {
            for (CheckBox cb : listView.getItems()) {
                Student student = (Student) cb.getUserData();
                List<String> subjects = new ArrayList<>();

                if (student.getSubjects() != null && !student.getSubjects().isEmpty()) {
                    String[] split = student.getSubjects().split(",");
                    for (String s : split) {
                        if (!s.trim().isEmpty() && !s.trim().equals(subjectCode)) {
                            subjects.add(s.trim());
                        }
                    }
                }

                if (cb.isSelected()) {
                    subjects.add(subjectCode);
                }

                student.setSubjects(String.join(",", subjects));
            }

            try {
                excelFile.writeStudentsToExcel(excelFile.studentList);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            window.close();
        });

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> window.close());

        HBox buttonBox = new HBox(10, saveButton, cancelButton);
        VBox mainBox = new VBox(10, new Label("Select Students to Enroll in " + subjectCode), listView, buttonBox);
        mainBox.setPadding(new Insets(10));

        BorderPane root = new BorderPane();
        root.setCenter(mainBox);

        Scene scene = new Scene(root, 450, 500);
        window.setScene(scene);
        window.showAndWait();
    }
}
