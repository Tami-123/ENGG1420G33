package com.example.phase1_1420;

public class Student extends User{

    protected String address;
    protected String telephone;
    protected String academicLevel;
    protected String currentSem;
    protected String subjects;
    protected String thesisTitle;
    protected double progress;


    public Student(String id, String password, String username, String email, String address, String telephone,
                   String academicLevel, String currentSem, String subjects, String thesisTitle, double progress) {
        super(id, password,username, "USER", email);
        this.address = address;
        this.telephone = telephone;
        this.academicLevel = academicLevel;
        this.currentSem = currentSem;
        this.subjects = subjects;
        this.thesisTitle = thesisTitle;
        this.progress = progress * 100;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAcademicLevel() {
        return academicLevel;
    }

    public void setAcademicLevel(String academicLevel) {
        this.academicLevel = academicLevel;
    }

    public String getCurrentSem() {
        return currentSem;
    }

    public void setCurrentSem(String currentSem) {
        this.currentSem = currentSem;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    public String getThesisTitle() {
        return thesisTitle;
    }

    public void setThesisTitle(String thesisTitle) {
        this.thesisTitle = thesisTitle;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = (double) (progress * 100);
    }

    @Override
    public String toString() {
        return "Username: " + username +
                " | StudentID: " + id +
                " | Password: " + password +
                " | Role: " + role +
                " | Email: " + email +
                " | Address: " + address +
                " | Telephone: " + telephone +
                " | Academic Level: " + academicLevel +
                " | Current Semester: " + currentSem +
                " | Subjects: " + subjects +
                " | Thesis Title: " + thesisTitle +
                " | Progress: " + progress + "%";
    }
}
