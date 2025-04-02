package com.example.phase1_1420;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleStringProperty;
import java.net.URL;
import java.util.ResourceBundle;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UserDashboardViewController implements Initializable {
    @FXML private Text studentNameText;
    @FXML private Text studentIdText;
    @FXML private Text academicLevelText;
    @FXML private Text currentSemText;
    @FXML private Text graduationDateText;
    @FXML private ProgressBar progressBar;
    @FXML private Label progressLabel;
    
    @FXML private TableView<Course> coursesTable;
    @FXML private TableColumn<Course, String> courseCodeColumn;
    @FXML private TableColumn<Course, String> courseNameColumn;
    @FXML private TableColumn<Course, String> instructorColumn;
    @FXML private TableColumn<Course, String> scheduleColumn;
    
    @FXML private TableView<Event> eventsTable;
    @FXML private TableColumn<Event, String> eventNameColumn;
    @FXML private TableColumn<Event, String> eventDateColumn;
    @FXML private TableColumn<Event, String> eventLocationColumn;

    private final ExcelFile excelReader = new ExcelFile();
    private Student currentStudent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            System.out.println("\n=== Initializing UserDashboardViewController ===");
            
            // Load data from Excel
            excelReader.ReadingNameExcelFile();
            
            // Get current student from UserDatabase
            currentStudent = (Student) UserDatabase.CurrentUser;
            System.out.println("Current Student:");
            System.out.println("- ID: " + currentStudent.getId());
            System.out.println("- Name: " + currentStudent.getUsername());
            System.out.println("- Academic Level: " + currentStudent.getAcademicLevel());
            System.out.println("- Current Semester: " + currentStudent.getCurrentSem());
            System.out.println("- Subjects: " + currentStudent.getSubjects());
            
            // Initialize table columns
            initializeColumns();
            
            // Update UI with student information
            updateStudentInfo();
            
            // Load tables with data
            loadCoursesTable();
            loadEventsTable();
        } catch (Exception e) {
            System.err.println("Error in initialize: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void initializeColumns() {
        // Courses table
        courseCodeColumn.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getCourseCode()));
        courseNameColumn.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getCourseName()));
        instructorColumn.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getTeacherName()));
        scheduleColumn.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getLectureTime()));

        // Events table
        eventNameColumn.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getEventName()));
        eventDateColumn.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getDateTime()));
        eventLocationColumn.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getLocation()));
    }

    private void updateStudentInfo() {
        studentNameText.setText(currentStudent.getUsername());
        studentIdText.setText(currentStudent.getId());
        academicLevelText.setText(currentStudent.getAcademicLevel());
        currentSemText.setText(currentStudent.getCurrentSem());
        graduationDateText.setText(calculateGraduationDate());
        
        progressBar.setProgress(currentStudent.getProgress() / 100 );
        progressLabel.setText(currentStudent.getProgress()+ "% Complete");
    }

    private String calculateGraduationDate() {
        String academicLevel = currentStudent.getAcademicLevel();
        int currentLevel;
        
        // Convert academic level text to number
        switch (academicLevel.toLowerCase()) {
            case "freshman":
                currentLevel = 1;
                break;
            case "sophomore":
                currentLevel = 2;
                break;
            case "junior":
                currentLevel = 3;
                break;
            case "senior":
                currentLevel = 4;
                break;
            default:
                currentLevel = 1; // Default to freshman if unknown
        }
        
        // Parse semester string (e.g., "Fall 2025")
        String currentSemStr = currentStudent.getCurrentSem();
        int currentSem;
        if (currentSemStr.toLowerCase().contains("fall")) {
            currentSem = 1;
        } else if (currentSemStr.toLowerCase().contains("spring")) {
            currentSem = 2;
        } else {
            currentSem = 1; // Default to Fall if unknown
        }
        
        int remainingSemesters = (4 - currentLevel) * 2 + (2 - currentSem);
        
        // Assuming 4 months per semester
        int monthsUntilGraduation = remainingSemesters * 4;
        LocalDate graduationDate = LocalDate.now().plusMonths(monthsUntilGraduation);
        
        return graduationDate.format(DateTimeFormatter.ofPattern("MMMM yyyy"));
    }

    private void loadCoursesTable() {
        // Get the student's subjects string from the current student object
        String studentSubjectsStr = currentStudent.getSubjects();
        System.out.println("\n=== Course Loading Debug ===");
        System.out.println("Student ID: " + currentStudent.getId());
        System.out.println("Raw Subjects String: '" + studentSubjectsStr + "'");
        
        // Create an observable list to hold the enrolled courses
        ObservableList<Course> enrolledCourses = FXCollections.observableArrayList();
        
        // Check if the student has any subjects registered
        if (studentSubjectsStr == null || studentSubjectsStr.trim().isEmpty()) {
            System.out.println("WARNING: Student has no subjects registered!");
            coursesTable.setItems(enrolledCourses);
            return;
        }
        
        // Split the subjects string into an array and clean each subject
        // Convert all subjects to uppercase for consistent case-insensitive comparison
        String[] studentSubjects = studentSubjectsStr.split(",");
        for (int i = 0; i < studentSubjects.length; i++) {
            studentSubjects[i] = studentSubjects[i].trim().toUpperCase();
        }
        
        // Print total number of available courses for debugging
        System.out.println("Total Courses Available: " + excelReader.courseList.size());
        
        // Debug: Print all available courses with their details
        System.out.println("\nAvailable Courses:");
        for (Course course : excelReader.courseList) {
            System.out.println("- " + course.getCourseName() + 
                             "\n  Course Code: '" + course.getCourseCode() + "'" +
                             "\n  Subject Code: '" + course.getCode() + "'");
        }
        
        // Debug: Print the student's subjects after cleaning
        System.out.println("\nStudent's Subjects:");
        for (String subject : studentSubjects) {
            if (!subject.trim().isEmpty()) {
                System.out.println("- '" + subject + "'");
            }
        }
        
        // Create a set to keep track of processed subjects to avoid duplicates
        java.util.Set<String> processedSubjects = new java.util.HashSet<>();
        
        // First pass: Match existing courses with student's subjects
        for (Course course : excelReader.courseList) {
            // Convert course's subject code to uppercase for consistent comparison
            String subjectCode = course.getCode().trim().toUpperCase();
            System.out.println("\nChecking course: " + course.getCourseName());
            System.out.println("Subject code: '" + subjectCode + "'");
            
            boolean courseMatched = false;
            // Check each of the student's subjects
            for (String studentSubject : studentSubjects) {
                if (studentSubject.isEmpty()) continue;
                
                System.out.println("Comparing with student subject: '" + studentSubject + "'");
                
                // Check for exact match (after converting to uppercase)
                if (subjectCode.equals(studentSubject)) {
                    // Only add the course if we haven't processed this subject code before
                    if (!processedSubjects.contains(subjectCode)) {
                        System.out.println("✓ Exact match found! Adding course: " + course.getCourseName());
                        // Create a new course entry using the student's subject code
                        Course matchedCourse = new Course(
                            studentSubject,  // Use student's subject code as course code
                            course.getCourseName(),
                            studentSubject,
                            course.getSectionNumber(),
                            Double.parseDouble(course.getCapacity()),  // Convert String capacity to double
                            course.getLectureTime(),
                            course.getFinalExamDateTime(),
                            course.getLocation(),
                            course.getTeacherName()
                        );
                        enrolledCourses.add(matchedCourse);
                        processedSubjects.add(subjectCode);  // Mark this subject as processed
                        courseMatched = true;
                        break;
                    }
                }
            }
            
            if (!courseMatched) {
                System.out.println("No match found for this course");
            }
        }
        
        // Second pass: Handle subjects that don't have corresponding courses
        for (String studentSubject : studentSubjects) {
            if (studentSubject.trim().isEmpty()) continue;
            
            // Check if we already have a course for this subject
            boolean hasCorrespondingCourse = false;
            for (Course course : enrolledCourses) {
                if (course.getCode().trim().toUpperCase().equals(studentSubject)) {
                    hasCorrespondingCourse = true;
                    break;
                }
            }
            
            // If no course exists for this subject, look for it in the subject list
            if (!hasCorrespondingCourse) {
                for (Subject subject : excelReader.subjectList) {
                    if (subject.getCode().trim().toUpperCase().equals(studentSubject)) {
                        System.out.println("\nFound subject without course: " + subject.getName() + " (" + subject.getCode() + ")");
                        // Create a basic course entry with minimal information
                        Course basicCourse = new Course(
                            studentSubject,  // Use student's subject code as course code
                            subject.getName(),  // Use subject name as course name
                            studentSubject,  // Use student's subject code
                            "N/A",  // No section number available
                            0.0,    // No capacity information
                            "N/A",  // No lecture time available
                            "N/A",  // No final exam date available
                            "N/A",  // No location available
                            "N/A"   // No teacher information available
                        );
                        enrolledCourses.add(basicCourse);
                        processedSubjects.add(studentSubject);  // Mark this subject as processed
                        System.out.println("✓ Added basic course entry for: " + subject.getName());
                        break;
                    }
                }
            }
        }
        
        // Print final results and any warnings
        System.out.println("\nTotal enrolled courses: " + enrolledCourses.size());
        if (enrolledCourses.isEmpty()) {
            System.out.println("WARNING: No courses were matched!");
            System.out.println("This could be because:");
            System.out.println("1. The student's subjects are not in the correct format");
            System.out.println("2. The course subject codes don't match the student's subjects");
            System.out.println("3. There might be extra spaces or case differences");
        }
        
        // Update the courses table with the final list of courses
        coursesTable.setItems(enrolledCourses);
    }

    private void loadEventsTable() {
        // Filter events based on student's registration
        ObservableList<Event> registeredEvents = FXCollections.observableArrayList();
        String studentId = currentStudent.getId();
        
        for (Event event : excelReader.eventList) {
            if (event.getRegisteredStudents().contains(studentId)) {
                registeredEvents.add(event);
            }
        }
        
        eventsTable.setItems(registeredEvents);
    }
}
