package com.example.phase1_1420;

import java.io.IOException;

public class TestExcelReading {
    public static void main(String[] args) throws IOException {
        ReadingExcelFile reader = new ReadingExcelFile();
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
    }
}
