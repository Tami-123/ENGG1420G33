module com.example.phase1_1420 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;


    opens com.example.phase1_1420 to javafx.fxml;
    exports com.example.phase1_1420;
    exports com.example.phase1_1420.Utilities;
    opens com.example.phase1_1420.Utilities to javafx.fxml;
}