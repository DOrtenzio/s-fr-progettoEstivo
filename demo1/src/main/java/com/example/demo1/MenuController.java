package com.example.demo1;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class MenuController {
    @FXML
    private ListView<HBox> cartListView;
    @FXML
    private Label totalLabel;
    @FXML
    private Button bottOk;

    @FXML
    public void initialize() {
        aggiornaSezCarrello();
    }
    //carrello: Sempre il solito metodo vai avanti grazie
    public void aggiornaSezCarrello() {
        ShoppingCart cart = ShoppingCart.getInstance(); // Istanza carrello attuale
        cartListView.getItems().clear(); // Pulisco tutti gli items nella lista a schermo
        double total = 0.0; // Rifaccio il conto

        ArrayList<String> prodotti = cart.getProdotti();
        ArrayList<Double> prezzi = cart.getPrezzi();
        ArrayList<Double> pesi = cart.getPesi();
        String s;

        for (int i = 0; i < prodotti.size(); i++) {
            HBox hbox = new HBox(); // Creiamo un HBox per contenere il testo e il pulsante

            // Creiamo un Label per mostrare le informazioni del prodotto
            if (prodotti.get(i).equalsIgnoreCase("carciofi normali") || prodotti.get(i).equalsIgnoreCase("carciofi romani") || prodotti.get(i).charAt(0) == '▸')
                s = " pezzi - ";
            else if (prodotti.get(i).equalsIgnoreCase("ravanelli"))
                s=" mazzi - ";
            else
                s=" kg - ";
            Label prodottoLabel = new Label("     "+prodotti.get(i) + " - " + arrotondaAlCent(pesi.get(i)) + s + prezzi.get(i) + " €");
            // Creiamo un pulsante "Rimuovi"
            Button removeButton = new Button("-");
            removeButton.getStyleClass().add("removeButton");
            int index = i; // Necessario per catturare l'indice corretto per cancellare
            removeButton.setOnAction(e -> { //All'azione
                // Rimuoviamo l'elemento dal carrello e aggiorniamo la vista dello "scontrino"
                cart.removeProduct(index);
                //Metodo immErr di Fruttacontroller
                bottOk.setStyle("-fx-background-color: #E11518;");
                PauseTransition pause = new PauseTransition(Duration.seconds(2));
                pause.setOnFinished(event -> bottOk.setStyle("-fx-background-color: #828282;")); //Risistemo il testo
                pause.play();
                aggiornaSezCarrello();
            });

            // Aggiungiamo il Label e il pulsante all'HBox
            HBox.setHgrow(prodottoLabel, Priority.ALWAYS); // Consente al Label di occupare tutto lo spazio disponibile
            hbox.getChildren().addAll(removeButton,prodottoLabel);
            cartListView.getItems().add(hbox);

            total += prezzi.get(i); //Aggiorno il totale
        }

        total = arrotondaAlCent(total);
        totalLabel.setText(total + " €");
    }



    //Classe per arrotondare usando BigDecimal
    public static double arrotondaAlCent(double value) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP); // Arrotonda a 2 decimali
        return bd.doubleValue();
    }

    //Scambi tra le schermate
    @FXML
    private void switchToProductsView (MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AProdotti.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1040, 650);
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setResizable(false); //Non permetto il ridemsionamento della finestra
        stage.setScene(scene);
        //stage.setMaximized(false); // Non imposta la finestra a tutto schermo
    }
    @FXML
    private void switchToCart (MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ACart.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1040, 650);
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setResizable(false); //Non permetto il ridemsionamento della finestra
        stage.setScene(scene);
        //stage.setMaximized(false); // Non imposta la finestra a tutto schermo
    }
    @FXML
    private void switchToFrutta(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AFrutta.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1040, 650); // Creo una nuova scena
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); //Prendo lo stage in atto e lo setto
        stage.setResizable(false); //Non permetto il ridemsionamento della finestra
        stage.setScene(scene);
        //stage.setMaximized(true); // Imposta la finestra a tutto schermo
    }
    @FXML
    private void switchToVerdura(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AVerduraSimple.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1040, 650); // Creo una nuova scena
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); //Prendo lo stage in atto e lo setto
        stage.setResizable(false); //Non permetto il ridemsionamento della finestra
        stage.setScene(scene);
        //stage.setMaximized(true); // Imposta la finestra a tutto schermo
    }
    @FXML
    private void switchToPreparati(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("APreparati.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1040, 650); // Creo una nuova scena
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); //Prendo lo stage in atto e lo setto
        stage.setResizable(false); //Non permetto il ridemsionamento della finestra
        stage.setScene(scene);
        //stage.setMaximized(true); // Imposta la finestra a tutto schermo
    }
    @FXML
    private void switchToMenu(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1040, 650); // Creo una nuova scena
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); //Prendo lo stage in atto e lo setto
        stage.setResizable(false); //Non permetto il ridemsionamento della finestra
        stage.setScene(scene);
        //stage.setMaximized(true); // Imposta la finestra a tutto schermo
    }
}