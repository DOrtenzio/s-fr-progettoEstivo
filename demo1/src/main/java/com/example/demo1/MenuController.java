package com.example.demo1;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class MenuController {
    @FXML
    private Label labHome,labBurg,labPack,labNews,labCart;

    @FXML
    protected void overBurger() {
        labBurg.setText("Prodotti");
        pauseAndCancel(labBurg);
    }
    @FXML
    protected void overCart() {
        labCart.setText("Il tuo carrello");
        pauseAndCancel(labCart);
    }
    @FXML
    protected void overnews() {
        labNews.setText("Volantini");
        pauseAndCancel(labNews);
    }
    @FXML
    protected void overPack() {
        labPack.setText("Ordini precedenti");
        pauseAndCancel(labPack);
    }
    @FXML
    protected void overHome() {
        labHome.setText("Torna indietro");
        pauseAndCancel(labHome);
    }
    @FXML
    protected void pauseAndCancel(Label s) {
        // Crea una pausa di 5 secondi
        PauseTransition pause = new PauseTransition(Duration.seconds(5));
        pause.setOnFinished(event -> s.setText("")); //Risistemo il testo
        pause.play();
    }
    @FXML
    private void switchToHelloMouse(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        //stage.setMaximized(false); // Non imposta la finestra a tutto schermo
    }
}