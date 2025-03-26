package com.example.phase1_1420;

public class Course extends Subject {
    private String courseCode;
    private String courseName;
    private String sectionNumber;
    private double capacity;
    private String lectureTime;
    private String finalExamDateTime;
    private String location;
    private String teacherName;

    public Course(String courseCode, String courseName, String subjectCode,
                  String sectionNumber, double capacity, String lectureTime,
                  String finalExamDateTime, String location, String teacherName) {

        super(subjectCode, subjectCode.replaceAll("\\d", "")); // Remove numbers to make subject name
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.sectionNumber = sectionNumber;
        this.capacity = capacity;
        this.lectureTime = lectureTime;
        this.finalExamDateTime = finalExamDateTime;
        this.location = location;
        this.teacherName = teacherName;
    }

    @Override
    public String toString() {
        return "Course: " + courseName +
                " | Subject: " + subjectName +
                " | Section: " + sectionNumber +
                " | Teacher: " + teacherName;
    }
}