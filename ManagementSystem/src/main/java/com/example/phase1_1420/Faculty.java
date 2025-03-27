package com.example.phase1_1420;

public class Faculty extends User {

    protected String degree;
    protected String researchInterest;
    protected String officeLocation;
    protected String coursesOffered;


    public Faculty(String id, String password, String username, String email, String degree, String researchInterest, String officeLocation, String coursesOffered) {
        super(id, password,username, "FACULTY", email);
        this.degree = degree;
        this.researchInterest = researchInterest;
        this.officeLocation = officeLocation;
        this.coursesOffered = coursesOffered;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getResearchInterest() {
        return researchInterest;
    }

    public void setResearchInterest(String researchInterest) {
        this.researchInterest = researchInterest;
    }

    public String getOfficeLocation() {
        return officeLocation;
    }

    public void setOfficeLocation(String officeLocation) {
        this.officeLocation = officeLocation;
    }

    public String getCoursesOffered() {
        return coursesOffered;
    }

    public void setCoursesOffered(String coursesOffered) {
        this.coursesOffered = coursesOffered;
    }

    @Override
    public String toString() {
        return "Username: " + username +
                " | StudentID: " + id +
                " | Password: " + password +
                " | Role: " + role +
                " | Email: " + email +
                " | Degree: " + degree +
                " | Research Interest: " + researchInterest +
                " | Office Location: " + officeLocation +
                " | Courses Offered: " + coursesOffered;
    }

}
