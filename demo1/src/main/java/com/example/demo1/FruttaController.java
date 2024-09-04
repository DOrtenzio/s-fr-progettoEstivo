package com.example.demo1;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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

public class FruttaController {
    @FXML
    private ComboBox<Double> peso0,peso1,peso2,peso3,peso4,peso5,peso6,peso7,peso8,peso9,peso10,peso11,peso12,peso13,peso14;
    @FXML
    private Button bottOk;
    @FXML
    private ListView<HBox> cartListView;
    @FXML
    private Label totalLabel;
    //Inizializzazione contatori peso
    @FXML
    public void initialize() {
        aggiornaSezCarrello();
        populateComboBox(peso0);populateComboBox(peso1);populateComboBox(peso2);populateComboBox(peso3);populateComboBox(peso4);populateComboBox(peso5);populateComboBox(peso6);populateComboBox(peso7);populateComboBox(peso8);populateComboBox(peso9);populateComboBox(peso10);populateComboBox(peso11);populateComboBox(peso12);populateComboBox(peso13);populateComboBox(peso14);
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
                immDiErrore();
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
    //Metodi per utilizzi secondari
    @FXML
    private void immDiConferma () {
        aggiornaSezCarrello(); //Lo metto qua perchè non ho voglia di inserirlo in tutti i metodi peso ¬_¬
        bottOk.setStyle("-fx-background-color: #76DD4D;");
        // Crea una pausa di 2 secondi prima di ritornare a vecchio colore
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event -> bottOk.setStyle("-fx-background-color: #828282;")); //Risistemo il testo
        pause.play();
    }
    private void immDiErrore () {
        bottOk.setStyle("-fx-background-color: #E11518;");
        // Crea una pausa di 2 secondi prima di ritornare a vecchio colore
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event -> bottOk.setStyle("-fx-background-color: #828282;")); //Risistemo il testo
        pause.play();
    }
    //Metodi aggiunta prodotti
    @FXML
    public void aggMele(){
        if (peso0.getValue() == null)
            immDiErrore();
        else{
            double peso=Double.parseDouble(String.valueOf(peso0.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Mele",0.99*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void aggPere(){
        if (peso1.getValue() == null)
            immDiErrore();
        else{
            double peso=Double.parseDouble(String.valueOf(peso1.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Pere",0.99*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void aggBanane(){
        if (peso2.getValue() == null)
            immDiErrore();
        else{
            double peso=Double.parseDouble(String.valueOf(peso2.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Banane",1.79*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void aggArance(){
        if (peso3.getValue() == null)
            immDiErrore();
        else{
            double peso=Double.parseDouble(String.valueOf(peso3.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Arance",1.49*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void aggMandarini(){
        if (peso4.getValue() == null)
            immDiErrore();
        else{
            double peso=Double.parseDouble(String.valueOf(peso4.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Mandarini",2.74*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void aggFragole(){
        if (peso5.getValue() == null)
            immDiErrore();
        else{
            double peso=Double.parseDouble(String.valueOf(peso5.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Fragole",4.99*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void aggCiliegie(){
        if (peso6.getValue() == null)
            immDiErrore();
        else{
            double peso=Double.parseDouble(String.valueOf(peso6.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Ciliegie",7.99*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void aggUva(){
        if (peso7.getValue() == null)
            immDiErrore();
        else{
            double peso=Double.parseDouble(String.valueOf(peso7.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Uva",2.39*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void aggKiwi(){
        if (peso8.getValue() == null)
            immDiErrore();
        else{
            double peso=Double.parseDouble(String.valueOf(peso8.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Kiwi",2.39*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void aggPesche(){
        if (peso9.getValue() == null)
            immDiErrore();
        else{
            double peso=Double.parseDouble(String.valueOf(peso9.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Pesche",2.99*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void aggAlbicocche(){
        if (peso10.getValue() == null)
            immDiErrore();
        else{
            double peso=Double.parseDouble(String.valueOf(peso10.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Albicocche",3.99*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void aggSusine(){
        if (peso11.getValue() == null)
            immDiErrore();
        else{
            double peso=Double.parseDouble(String.valueOf(peso11.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Susine",2.99*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void aggAnguria(){
        if (peso12.getValue() == null)
            immDiErrore();
        else{
            double peso=Double.parseDouble(String.valueOf(peso12.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Anguria",0.99*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void aggMelone(){
        if (peso13.getValue() == null)
            immDiErrore();
        else{
            double peso=Double.parseDouble(String.valueOf(peso13.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Melone",0.99*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void aggPrugne(){
        if (peso14.getValue() == null)
            immDiErrore();
        else{
            double peso=Double.parseDouble(String.valueOf(peso14.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Prugne",1.79*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    // Metodo per aggiungere una riga
    public void aggiungiRiga(String prodotto, double prezzo, double peso) {
        ShoppingCart.getInstance().addProduct(prodotto, prezzo, peso);
    }

    //Metodo per allestimenti, se così si può dire dei comboBox, si può usare adAll
    private void populateComboBox(ComboBox<Double> peso) {
        for (double i = 5.00; i >= 0.10; i -= 0.05) {
            peso.getItems().add(0, arrotondaAlCent(i));
        }
    }

    //Scambi tra le schermate
    @FXML
    private void switchToProductsView (MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AProdotti.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1040, 650);
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setResizable(false); //Non permetto il ridemsionamento della finestra
        stage.setScene(scene);
    }
    @FXML
    private void switchToCart (MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ACart.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1040, 650);
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setResizable(false); //Non permetto il ridemsionamento della finestra
        stage.setScene(scene);
    }
    @FXML
    private void switchToMenu(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1040, 650); // Creo una nuova scena
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); //Prendo lo stage in atto e lo setto
        stage.setResizable(false); //Non permetto il ridemsionamento della finestra
        stage.setScene(scene);
    }
    @FXML
    private void switchToVerduraSimple(ActionEvent event) throws IOException {
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
}

