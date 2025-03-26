package com.example.phase1_1420;

public class Event {

    protected String eventID;
    protected String eventName;
    protected String description;
    protected String location;
    protected String dateTime;
    protected double capacity;
    protected String cost;
    protected String registeredStudents;

    public Event(String eventID, String eventName, String description, String location, String dateTime, double capacity,
                 String cost, String registeredStudents){
        this.eventID = eventID;
        this.eventName = eventName;
        this.description = description;
        this.location = location;
        this.dateTime = dateTime;
        this.capacity = capacity;
        this.cost = cost;
        this.registeredStudents = registeredStudents;
    }

    @Override
    public String toString() {
        return "EventId: " + eventID +
                " | Name: " + eventName +
                " | Description: " + description +
                " | location: " + location +
                " | Date & Time: " + dateTime +
                " | Capacity: " + capacity +
                " | Cost: " + cost +
                " | Students Registered: " + registeredStudents;
    }

}
