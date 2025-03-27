package com.example.phase1_1420;

public class Subject {

    protected String subjectCode;
    protected String subjectName;


    public Subject(String subjectCode, String subjectName){
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
    }

    public String getSubName(){
        return this.subjectName;
    }

    public void setSubName(String subjectName){
        this.subjectName = subjectName;
    }

    public String getSubCode(){
        return this.subjectCode;
    }

    public void setSubCode(String subjectCode){
        this.subjectCode = subjectCode;
    }


    @Override
    public String toString(){
        return "Subject Code: " + subjectCode + " | Subject Name: " + subjectName;
    }
}