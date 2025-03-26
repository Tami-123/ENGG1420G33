package com.example.phase1_1420;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ReadingExcelFile {
    public ArrayList<Student> studentList = new ArrayList<>();
    public ArrayList<Faculty> facultyList = new ArrayList<>();
    public ArrayList<Event> eventList = new ArrayList<>();

    public void ReadingNameExcelFile() throws IOException {
        File file = new File("UMS_Data.xlsx");
        FileInputStream fis = new FileInputStream(file);
        Workbook wb = WorkbookFactory.create(fis);


        // Finding Students In sheet 2
        Sheet sheet = wb.getSheetAt(2);
        studentList.clear();
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {

            Row row = sheet.getRow(i);
            if (row == null) continue;

            Cell idCell = row.getCell(0);
            Cell userCell = row.getCell(1);
            Cell adressCell = row.getCell(2);
            Cell telephoneCell = row.getCell(3);
            Cell emailCell = row.getCell(4);
            Cell academicLevelCell = row.getCell(5);
            Cell semesterCell = row.getCell(6);
            Cell subjectsCell = row.getCell(8);
            Cell thesisTitleCell = row.getCell(9);
            Cell progressCell = row.getCell(10);
            Cell passCell = row.getCell(11);


            if (idCell != null) {
                Student student = new Student(idCell.toString(), passCell.toString(), userCell.toString(), emailCell.toString(), adressCell.toString(),
                        telephoneCell.toString(), academicLevelCell.toString(),semesterCell.toString(), subjectsCell.toString(), thesisTitleCell.toString(), Double.parseDouble(progressCell.toString()));
                studentList.add(student);
            }
        }

        // Finding Students In sheet 2
        sheet = wb.getSheetAt(3);
        facultyList.clear();
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {

            Row row = sheet.getRow(i);
            if (row == null) continue;

            Cell idCell = row.getCell(0);
            Cell userCell = row.getCell(1);
            Cell degreeCell = row.getCell(2);
            Cell researchCell =row.getCell(3);
            Cell emailCell =row.getCell(4);
            Cell officeCell = row.getCell(5);
            Cell coursesCell = row.getCell(6);
            Cell passCell = row.getCell(7);


            if (idCell != null) {
                Faculty faculty = new Faculty(idCell.toString(),passCell.toString(),userCell.toString(),emailCell.toString(), degreeCell.toString()
                , researchCell.toString(), officeCell.toString(), coursesCell.toString());
                facultyList.add(faculty);
            }
        }

        sheet = wb.getSheetAt(4);
        eventList.clear();
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {

            Row row = sheet.getRow(i);
            if (row == null) continue;

            Cell idCell = row.getCell(0);
            Cell nameCell = row.getCell(1);
            Cell descriptionCell = row.getCell(2);
            Cell locationCell =row.getCell(3);
            Cell dateTimeCell =row.getCell(4);
            Cell capacityCell = row.getCell(5);
            Cell costCell = row.getCell(6);
            Cell registeredStudentsCell = row.getCell(8);

            if (idCell != null) {
                Event event = new Event(idCell.toString(), nameCell.toString(), descriptionCell.toString(), locationCell.toString(),
                        dateTimeCell.toString(),Double.parseDouble(capacityCell.toString()), costCell.toString(), registeredStudentsCell.toString());
                eventList.add(event);
            }
        }

        wb.close();
        fis.close();
    }
}
