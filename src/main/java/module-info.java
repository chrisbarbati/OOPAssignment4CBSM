module com.example.oopassignment4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.oopassignment4 to javafx.fxml;
    exports com.example.oopassignment4;
}