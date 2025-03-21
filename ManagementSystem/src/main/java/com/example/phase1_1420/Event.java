package com.example.phase1_1420;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.time.LocalDate;

public class Event {
    private final StringProperty name = new SimpleStringProperty(this, "name");
    private final ObjectProperty<LocalDate> date = new SimpleObjectProperty<>(this, "date");

    public Event(String name, LocalDate date) {
        setName(name);
        setDate(date);
    }

    // Name property methods
    public StringProperty nameProperty() {
        return name;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    // Date property methods
    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public LocalDate getDate() {
        return date.get();
    }

    public void setDate(LocalDate date) {
        this.date.set(date);
    }

    @Override
    public String toString() {
        return getName() + " (" + getDate() + ")";
    }
}
