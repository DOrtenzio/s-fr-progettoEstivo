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
    //Metodo per aggiornare il carrello a ogni "apertura" della scena
    public void initialize() {
        aggiornaSezCarrello();
    }
    //Metodo per aggiornare il carrello
    public void aggiornaSezCarrello() {
        cartListView.getItems().clear(); // Pulisco o meglio dire cancello tutti gli elementi nella ListView
        double total = 0.0; // Variabile che indica il totale

        //3 array list per prodotti, prezzi e pesi; ArrayList inizializzati con gli arraylist salvati in ShoppingCart (Vedi il file per il perchè)
        ArrayList<String> prodotti = testClass.getProdotti(); //Nomi dei prodotti
        ArrayList<Double> prezzi = testClass.getPrezzi(); //Prezzi dei prodotti
        ArrayList<Double> pesi = testClass.getPesi(); //Pesi degli articoli

        String s; //Stringa comoda per scrivere il prodotto nella ListView

        for (int i = 0; i < prodotti.size(); i++) {
            HBox hbox = new HBox(); // Creiamo un HBox per contenere il testo e il pulsante

            // Creiamo un Label per mostrare le informazioni del prodotto, in base al tipo può essere o in pezzi singoli o in mazzi o in Kg
            if (prodotti.get(i).equalsIgnoreCase("carciofi normali") || prodotti.get(i).equalsIgnoreCase("carciofi romani") || prodotti.get(i).charAt(0) == '▸')
                s = " pezzi - "; //Carciofi e prodotti preparati indicati con la > nella posizione 0 della string sono venduti a pezzi
            else if (prodotti.get(i).equalsIgnoreCase("ravanelli"))
                s=" mazzi - "; //Solo i ravanelli li vendiamo in mazzi
            else
                s=" kg - ";
            //Label per ogni riga della ListView
            Label prodottoLabel = new Label("     "+prodotti.get(i) + " - " + arrotondaAlCent(pesi.get(i)) + s + prezzi.get(i) + " €");
            // Creiamo un pulsante "Rimuovi"
            Button removeButton = new Button("-");
            removeButton.getStyleClass().add("removeButton");
            int index = i; // Necessario per catturare l'indice corretto per cancellare da arraylist
            removeButton.setOnAction(e -> { //All'azione
                // Rimuoviamo l'elemento dal carrello e aggiorniamo la vista dello "scontrino"
                testClass.removeProduct(index); //Richiamo il metodo per cancellare passandogli l'indice
                //Metodo immErr di FruttaController, semplicemente un cambio colore per 2 secondi
                bottOk.setStyle("-fx-background-color: #E11518;"); //Rosso
                PauseTransition pause = new PauseTransition(Duration.seconds(2)); //Grigino
                pause.setOnFinished(event -> bottOk.setStyle("-fx-background-color: #828282;")); //Risistemo il colore
                pause.play();
                aggiornaSezCarrello(); //Richiamo il metodo
            });

            // Aggiungiamo il Label e il pulsante all'HBox dentro la ListView
            HBox.setHgrow(prodottoLabel, Priority.ALWAYS); // Consente al Label di occupare tutto lo spazio disponibile
            hbox.getChildren().addAll(removeButton,prodottoLabel);
            cartListView.getItems().add(hbox);

            total += prezzi.get(i); //Aggiorno il totale
        }

        total = arrotondaAlCent(total); //Arrotondo la cifra alla seconda dopo la virgola
        totalLabel.setText(total + " €"); //Aggiorno la label con il totale
    }

    //Classe per arrotondare usando BigDecimal
    public static double arrotondaAlCent(double value) {
        BigDecimal bd = new BigDecimal(value); //bd è il nostro numero
        bd = bd.setScale(2, RoundingMode.HALF_UP); // Arrotonda a 2 decimali
        return bd.doubleValue(); //Lo restituisco in double per comodità
    }

    //Scambi tra le scene
    @FXML
    private void switchToProductsView (MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AProdotti.fxml")); //All'evento del mouse, click, passo alla schermata che mostra la divisione intermedia dei prodotti
        Scene scene = new Scene(fxmlLoader.load(), 1040, 650);// Creo una nuova scena
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();//Prendo lo stage in atto e lo setto
        stage.setResizable(false); //Non permetto il ridimensionamento della finestra per estetica
        stage.setScene(scene);
    }
    @FXML
    private void switchToCart (MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ACart.fxml")); //Passo al carrello
        Scene scene = new Scene(fxmlLoader.load(), 1040, 650);// Creo una nuova scena
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();//Prendo lo stage in atto e lo setto
        stage.setResizable(false); //Non permetto il ridimensionamento della finestra x estetica
        stage.setScene(scene);
    }
    @FXML
    private void switchToFrutta(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AFrutta.fxml")); //Passo alla frutta
        Scene scene = new Scene(fxmlLoader.load(), 1040, 650); // Creo una nuova scena
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); //Prendo lo stage in atto e lo setto
        stage.setResizable(false); //Non permetto il ridimensionamento della finestra
        stage.setScene(scene);
    }
    @FXML
    private void switchToVerdura(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AVerduraSimple.fxml"));//Passo alla verdura ridotta
        Scene scene = new Scene(fxmlLoader.load(), 1040, 650); // Creo una nuova scena
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); //Prendo lo stage in atto e lo setto
        stage.setResizable(false); //Non permetto il ridimensionamento della finestra x estetica
        stage.setScene(scene);
    }
    @FXML
    private void switchToPreparati(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("APreparati.fxml"));//Passo ai preparati
        Scene scene = new Scene(fxmlLoader.load(), 1040, 650); // Creo una nuova scena
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); //Prendo lo stage in atto e lo setto
        stage.setResizable(false); //Non permetto il ridimensionamento della finestra x estetica
        stage.setScene(scene);
    }
    @FXML
    private void switchToMenu(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AMenu.fxml"));//Torno al menu (Si è inutile, ma per standardizzare leggermente lo messo)
        Scene scene = new Scene(fxmlLoader.load(), 1040, 650); // Creo una nuova scena
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); //Prendo lo stage in atto e lo setto
        stage.setResizable(false); //Non permetto il ridimensionamento della finestra x estetica
        stage.setScene(scene);
    }
}