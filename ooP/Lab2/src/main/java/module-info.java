module school.lab2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens school.lab2 to javafx.fxml;
    exports school.lab2;
}