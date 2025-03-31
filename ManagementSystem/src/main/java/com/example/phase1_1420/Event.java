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

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getRegisteredStudents() {
        return registeredStudents;
    }

    public void setRegisteredStudents(String registeredStudents) {
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
