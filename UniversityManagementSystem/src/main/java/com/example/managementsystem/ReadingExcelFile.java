package com.example.managementsystem;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;


public class ReadingExcelFile {
    public ArrayList<String> studentNamesFromExcelFile = new ArrayList<>();
    public  ArrayList<String> studentPasswordFromExcelFile = new ArrayList<>();
    public ArrayList<String> adminIDFromExcelFile = new ArrayList<>();
    public  ArrayList<String> adminPasswordFromExcelFile = new ArrayList<>();

    public void ReadingNameExcelFile() throws IOException {
        File file = new File("C:\\Users\\abdul\\OneDrive\\Desktop\\Programming\\ENGG 1420\\ManagementSystem\\src\\main\\UMS_Data.xlsx");
        FileInputStream fis = new FileInputStream(file);
        Workbook wb = WorkbookFactory.create(fis);

        //Finding Student ID
        Sheet sheet = wb.getSheetAt(2);
        Row row0 = sheet.getRow(0);
        Cell cell0 = row0.getCell(0);

        //this will clear all the names in the arraylist
        studentNamesFromExcelFile.clear();

        for (Row row : sheet) {
            Cell cell = row.getCell(0);
            if (cell != null) {
                studentNamesFromExcelFile.add(cell.toString());
            }
        }

        //This will get student password
        sheet = wb.getSheetAt(2);
        row0 = sheet.getRow(0);
        cell0 = row0.getCell(11);

        for (Row row : sheet) {
            Cell cell = row.getCell(11);
            if (cell != null) {
                studentPasswordFromExcelFile.add(cell.toString());
            }
        }

        //Reading Staff ID
        sheet = wb.getSheetAt(3);
        row0 = sheet.getRow(0);
        cell0 = row0.getCell(0);

        adminIDFromExcelFile.clear();
        for (Row row : sheet) {
            Cell cell = row.getCell(0);
            if (cell != null) {
                adminIDFromExcelFile.add(cell.toString());
            }
        }

        //Get Staff Password
        cell0 = row0.getCell(7);

        adminIDFromExcelFile.clear();
        for (Row row : sheet) {
            Cell cell = row.getCell(7);
            if (cell != null) {
                adminPasswordFromExcelFile.add(cell.toString());
            }
        }

        wb.close();
        fis.close();
    }

}
