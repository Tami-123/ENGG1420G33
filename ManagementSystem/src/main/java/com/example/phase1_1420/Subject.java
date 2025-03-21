package com.example.phase1_1420;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Subject {
    private final StringProperty name = new SimpleStringProperty(this, "name");
    private final StringProperty code = new SimpleStringProperty(this, "code");

    public Subject(String name, String code) {
        setName(name);
        setCode(code);
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

    // Code property methods
    public StringProperty codeProperty() {
        return code;
    }

    public String getCode() {
        return code.get();
    }

    public void setCode(String code) {
        this.code.set(code);
    }

    @Override
    public String toString() {
        return getName() + " (" + getCode() + ")";
    }
}