package com.example.phase1_1420;

import java.io.IOException;


//This file is to test the Reading capabilites of the excel file
public class TestExcelReading {
    public static void main(String[] args) throws IOException {
        ExcelFile reader = new ExcelFile();
        reader.ReadingNameExcelFile();

        System.out.println("=== Students ===");
        for (Student s : reader.studentList) {
            System.out.println(s);
        }
        System.out.println("=== Faculty ===");
        for (Faculty f : reader.facultyList) {
            System.out.println(f);
        }
        System.out.println("=== Events ===");
        for(Event e : reader.eventList){
            System.out.println(e);
        }
        System.out.println("=== Subjects ===");
        for(Subject sub : reader.subjectList){
            System.out.println(sub);
        }
        System.out.println("=== Courses ===");
        for(Course c : reader.courseList){
            System.out.println(c);
        }
    }
}
