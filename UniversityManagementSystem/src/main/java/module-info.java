module com.example.managementsystem {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires org.apache.poi.ooxml;
    requires java.logging;
    requires org.apache.commons.io;

    opens com.example.managementsystem to javafx.fxml;
    exports com.example.managementsystem;
}