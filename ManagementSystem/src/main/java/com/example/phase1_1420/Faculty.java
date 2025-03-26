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
