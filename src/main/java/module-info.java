module com.example.bbbbb {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.bbbbb to javafx.fxml;
    exports com.example.bbbbb;
}