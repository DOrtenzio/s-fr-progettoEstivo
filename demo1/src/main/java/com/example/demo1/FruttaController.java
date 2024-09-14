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
    private ComboBox<Double> peso0,peso1,peso2,peso3,peso4,peso5,peso6,peso7,peso8,peso9,peso10,peso11,peso12,peso13,peso14; //Scegli il peso dei vari prodotti in questo caso i pezzi
    @FXML
    private Button bottOk;
    @FXML
    private ListView<HBox> cartListView;
    @FXML
    private Label totalLabel;

    //Metodo per aggiornare il carrello a ogni "apertura" della scena e inizializzazione contatori peso
    @FXML
    public void initialize() {
        aggiornaSezCarrello();
        populateComboBox(peso0);populateComboBox(peso1);populateComboBox(peso2);populateComboBox(peso3);populateComboBox(peso4);populateComboBox(peso5);populateComboBox(peso6);populateComboBox(peso7);populateComboBox(peso8);populateComboBox(peso9);populateComboBox(peso10);populateComboBox(peso11);populateComboBox(peso12);populateComboBox(peso13);populateComboBox(peso14);
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

    //Metodi per utilizzi secondari
    @FXML
    private void immDiConferma () { //Metodo che conferma l'aggiunta dei prodottti
        aggiornaSezCarrello(); //Lo metto qua perchè non ho voglia di inserirlo in tutti i metodi peso ¬_¬, anche per risparmiare linee di codice
        bottOk.setStyle("-fx-background-color: #76DD4D;"); //Verde
        // Crea una pausa di 2 secondi prima di ritornare a vecchio colore
        PauseTransition pause = new PauseTransition(Duration.seconds(2)); //Grigino
        pause.setOnFinished(event -> bottOk.setStyle("-fx-background-color: #828282;")); //Risistemo il colore
        pause.play();
    }
    @FXML
    private void immDiErrore () {
        bottOk.setStyle("-fx-background-color: #E11518;"); //Rossastro
        // Crea una pausa di 2 secondi prima di ritornare a vecchio colore
        PauseTransition pause = new PauseTransition(Duration.seconds(2)); //Grigino
        pause.setOnFinished(event -> bottOk.setStyle("-fx-background-color: #828282;")); //Risistemo il colore
        pause.play();
    }

    //Metodi aggiunta prodotti di frutta
    //Nb: Quando i metodi sono tutti simili non commento
    @FXML //Metodo unico per l'aggiunta
    public void aggiuntaProdotto(String prodotto,double prezzoAlKg,ComboBox<Double> combo){
        if (combo.getValue() == null)
            immDiErrore(); //Se il peso è nullo non procedo e mostro un errore visivo
        else{
            double peso=Double.parseDouble(String.valueOf(combo.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiProdotto(prodotto,arrotondaAlCent(prezzoAlKg*peso),peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un ingresso visivo di ciò
        }
    }
    @FXML
    public void aggMele(){
        aggiuntaProdotto("Mele",0.99,peso0);
    }
    @FXML
    public void aggPere(){
        aggiuntaProdotto("Pere",0.99,peso1);
    }
    @FXML
    public void aggBanane(){
        aggiuntaProdotto("Banane",1.79,peso2);
    }
    @FXML
    public void aggArance(){
        aggiuntaProdotto("Arance",1.49,peso3);
    }
    @FXML
    public void aggMandarini(){
        aggiuntaProdotto("Mandarini",2.74,peso4);
    }
    @FXML
    public void aggFragole(){
        aggiuntaProdotto("Fragole",4.99,peso5);
    }
    @FXML
    public void aggCiliegie(){
        aggiuntaProdotto("Ciliegie",7.99,peso6);
    }
    @FXML
    public void aggUva(){
        aggiuntaProdotto("Uva",2.39,peso7);
    }
    @FXML
    public void aggKiwi(){
        aggiuntaProdotto("Kiwi",2.39,peso8);
    }
    @FXML
    public void aggPesche(){
        aggiuntaProdotto("Pesche",2.99,peso9);
    }
    @FXML
    public void aggAlbicocche(){
        aggiuntaProdotto("Albicocche",3.99,peso10);
    }
    @FXML
    public void aggSusine(){
        aggiuntaProdotto("Susine",2.99,peso11);
    }
    @FXML
    public void aggAnguria(){
        aggiuntaProdotto("Anguria",0.99,peso12);
    }
    @FXML
    public void aggMelone(){
        aggiuntaProdotto("Melone",0.99,peso13);
    }
    @FXML
    public void aggPrugne(){
        aggiuntaProdotto("Prugne",1.79,peso14);
    }

    // Metodo per aggiungere un prodotto al carrello (Vedi ShoppingCart)
    public void aggiungiProdotto(String prodotto, double prezzo, double peso) {
        testClass.addProduct(prodotto, arrotondaAlCent(prezzo), peso);
    }

    //Metodo per allestimenti, se così si può dire dei comboBox, si può usare adAll
    private void populateComboBox(ComboBox<Double> peso) {
        for (double i = 5.00; i >= 0.10; i -= 0.05) {
            peso.getItems().add(0, arrotondaAlCent(i));
        }
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
    private void switchToVerduraSimple(ActionEvent event) throws IOException {
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