module com.example.inventorysystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;


    exports main;
    opens main to javafx.fxml;
    exports controller;
    opens controller to javafx.fxml;
    exports model;
    opens model to javafx.base;
}