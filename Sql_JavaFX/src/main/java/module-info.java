module com.example.sql_javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.sql_javafx to javafx.fxml;
    exports com.example.sql_javafx;
}