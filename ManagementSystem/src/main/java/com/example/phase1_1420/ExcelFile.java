package com.example.phase1_1420;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelFile {
    public List<Student> studentList = new ArrayList<>();
    public List<Faculty> facultyList = new ArrayList<>();
    public List<Event> eventList = new ArrayList<>();
    public List<Subject> subjectList = new ArrayList<>();
    public List<Course> courseList = new ArrayList<>();

    //Write the subject's back to the excel file after edit in GUI
    public void writeSubjectsToExcel(List<Subject> updatedSubjects) throws IOException {
        File file = new File("UMS_Data.xlsx");
        FileInputStream fis = new FileInputStream(file);
        Workbook wb = WorkbookFactory.create(fis);
        Sheet sheet = wb.getSheetAt(0); // Subjects in sheet 0

        // Clear old data (except header)
        for (int i = sheet.getLastRowNum(); i > 0; i--) {
            Row row = sheet.getRow(i);
            if (row != null) sheet.removeRow(row);
        }

        // Write updated subject list starting from row 1
        int rowIndex = 1;
        for (Subject subject : updatedSubjects) {
            Row row = sheet.createRow(rowIndex++);
            Cell codeCell = row.createCell(0);
            Cell nameCell = row.createCell(1);


            codeCell.setCellValue(subject.getCode());
            nameCell.setCellValue(subject.getName());
        }

        fis.close();

        // Save changes
        FileOutputStream fos = new FileOutputStream(file);
        wb.write(fos);
        fos.close();
        wb.close();
    }


    //Read all new data, from all sheets whenever reading
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

            Cell idCell = row.getCell(0, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            Cell userCell = row.getCell(1, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            Cell adressCell = row.getCell(2, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            Cell telephoneCell = row.getCell(3, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            Cell emailCell = row.getCell(4, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            Cell academicLevelCell = row.getCell(5, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            Cell semesterCell = row.getCell(6, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            Cell subjectsCell = row.getCell(8, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            Cell thesisTitleCell = row.getCell(9, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            Cell progressCell = row.getCell(10, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            Cell passCell = row.getCell(11, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);


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

            Cell idCell = row.getCell(0, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            Cell userCell = row.getCell(1, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            Cell degreeCell = row.getCell(2, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            Cell researchCell =row.getCell(3, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            Cell emailCell =row.getCell(4, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            Cell officeCell = row.getCell(5, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            Cell coursesCell = row.getCell(6, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            Cell passCell = row.getCell(7, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);


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

            Cell idCell = row.getCell(0, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            Cell nameCell = row.getCell(1, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            Cell descriptionCell = row.getCell(2, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            Cell locationCell =row.getCell(3, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            Cell dateTimeCell =row.getCell(4, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            Cell capacityCell = row.getCell(5, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            Cell costCell = row.getCell(6, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            Cell registeredStudentsCell = row.getCell(8, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);

            if (idCell != null) {
                Event event = new Event(idCell.toString(), nameCell.toString(), descriptionCell.toString(), locationCell.toString(),
                        dateTimeCell.toString(),Double.parseDouble(capacityCell.toString()), costCell.toString(), registeredStudentsCell.toString());
                eventList.add(event);
            }
        }

        sheet = wb.getSheetAt(0);
        subjectList.clear();
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {

            Row row = sheet.getRow(i);
            if (row == null) continue;

            //Wont enter Blank Cells into the object
            Cell idCell = row.getCell(0, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            Cell passCell = row.getCell(1, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);


            if (idCell != null) {
                Subject subject = new Subject(idCell.toString(),passCell.toString());
                subjectList.add(subject);
            }
        }

        sheet = wb.getSheetAt(1);
        courseList.clear();
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {

            Row row = sheet.getRow(i);
            if (row == null) continue;

            //Wont enter Blank Cells into the object
            Cell courseCodecell = row.getCell(0, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            Cell courseNameCell = row.getCell(1, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            Cell subjectCodeCell = row.getCell(2, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            Cell sectionNumberCell = row.getCell(3, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            Cell CapacityCell = row.getCell(4, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            Cell lectureTimeCell = row.getCell(5, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            Cell finalTimeCell = row.getCell(6, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            Cell locationCell = row.getCell(7, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            Cell teacherCell = row.getCell(8, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);


            if (courseCodecell != null) {
                Course course = new Course(courseCodecell.toString(), courseNameCell.toString(), subjectCodeCell.toString(), sectionNumberCell.toString(), Double.parseDouble(CapacityCell.toString()),
                        lectureTimeCell.toString(), finalTimeCell.toString(), locationCell.toString(), teacherCell.toString());
                courseList.add(course);
            }
        }

        wb.close();
        fis.close();
    }
}
