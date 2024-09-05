package com.example.demo1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AMenu.fxml")); //Scena di partenza
        Scene scene = new Scene(fxmlLoader.load(), 1040, 650);
        stage.setTitle("Frutta e altro"); //Nome app
        stage.setScene(scene);
        //Non permetto il ridemsionamento della finestra solo per questioni puramente estetiche
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}