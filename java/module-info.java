module com.example.realestatemanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    //    requires json;
    requires org.json;
    requires com.fasterxml.jackson.databind;
    requires java.mail;
    requires java.desktop;
    requires javafx.base;

    opens com.example.realestatemanagementsystem to javafx.fxml;
    exports com.example.realestatemanagementsystem;
}