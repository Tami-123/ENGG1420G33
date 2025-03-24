package com.example.phase1_1420;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ReadingExcelFile {
    public ArrayList<String> studentNamesFromExcelFile = new ArrayList<>();
    public ArrayList<String> studentPasswordFromExcelFile = new ArrayList<>();
    public ArrayList<String> adminIDFromExcelFile = new ArrayList<>();
    public ArrayList<String> adminPasswordFromExcelFile = new ArrayList<>();

    public void ReadingNameExcelFile() throws IOException {
        File file = new File("com/example/phase1_1420/UMS_Data.xlsx");
        FileInputStream fis = new FileInputStream(file);
        Workbook wb = WorkbookFactory.create(fis);

        // Finding Student Names (Column 0 in Sheet 2)
        Sheet sheet = wb.getSheetAt(2);
        studentNamesFromExcelFile.clear();
        for (Row row : sheet) {
            Cell cell = row.getCell(0);
            if (cell != null) {
                studentNamesFromExcelFile.add(cell.toString());
            }
        }

        // Finding Student Passwords (Column 11 in Sheet 2)
        studentPasswordFromExcelFile.clear();
        for (Row row : sheet) {
            Cell cell = row.getCell(11);
            if (cell != null) {
                studentPasswordFromExcelFile.add(cell.toString());
            }
        }

        // Reading Admin IDs (Column 0 in Sheet 3)
        sheet = wb.getSheetAt(3);
        adminIDFromExcelFile.clear();
        for (Row row : sheet) {
            Cell cell = row.getCell(0);
            if (cell != null) {
                adminIDFromExcelFile.add(cell.toString());
            }
        }

        // Reading Admin Passwords (Column 7 in Sheet 3)
        adminPasswordFromExcelFile.clear();
        for (Row row : sheet) {
            Cell cell = row.getCell(7);
            if (cell != null) {
                adminPasswordFromExcelFile.add(cell.toString());
            }
        }
        for(String a: studentNamesFromExcelFile){
            System.out.println(a);
        }

        wb.close();
        fis.close();
    }
}
