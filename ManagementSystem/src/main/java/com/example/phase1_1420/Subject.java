package com.example.phase1_1420;

public class Subject {

    protected String subjectCode;
    protected String subjectName;


    public Subject(String subjectCode, String subjectName){
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
    }

    public String getName(){
        return this.subjectName;
    }

    public String getCode(){
        return this.subjectCode;
    }

    public void setName(String subjectName){
        this.subjectName = subjectName;
    }

    public void setCode(String subjectCode){
        this.subjectCode = subjectCode;
    }

    @Override
    public String toString(){
        return "Subject Code: " + subjectCode + " | Subject Name: " + subjectName;
    }
}