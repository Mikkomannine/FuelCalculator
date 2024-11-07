module org.example.fuelcalculator {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.fuelcalculator to javafx.fxml;
    exports org.example.fuelcalculator;
}