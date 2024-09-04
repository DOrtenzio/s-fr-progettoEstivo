package com.example.demo1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1040, 650);
        stage.setTitle("Frutta e altro");
        stage.setScene(scene);
        stage.setResizable(false); //Non permetto il ridemsionamento della finestra
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}