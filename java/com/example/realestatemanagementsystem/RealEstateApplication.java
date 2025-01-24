package com.example.realestatemanagementsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RealEstateApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(RealEstateApplication.class.getResource("signInPage.fxml"));
        Scene scene1 = new Scene(fxmlLoader1.load(), 707, 487);

        FXMLLoader fxmlLoader2 = new FXMLLoader(RealEstateApplication.class.getResource("addPropertyPage.fxml"));
        Scene scene2 = new Scene(fxmlLoader2.load(), 1000, 689);

        FXMLLoader fxmlLoader3 = new FXMLLoader(RealEstateApplication.class.getResource("homepage.fxml"));
        Scene scene3 = new Scene(fxmlLoader3.load(), 1000, 689);


        stage.setTitle("Your Property");
        stage.setScene(scene3);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}