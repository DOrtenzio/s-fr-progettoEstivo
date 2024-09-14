package com.example.demo1;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class VerduraSimpleController {
    @FXML
    private ComboBox<Double> peso1,peso2,peso3; //Scegli il peso dei vari prodotti in questo caso i pezzi
    @FXML
    private Button bottOk;
    @FXML
    private ListView<HBox> cartListView;
    @FXML
    private Label totalLabel,prezzoCarciofi;
    @FXML
    private ToggleGroup carciofi,cavolfiore;
    @FXML
    private ImageView imCarc,imCavol; //Immagini che cambiano in base al tipo selezionato

    //Metodo per aggiornare il carrello a ogni "apertura" della scena e inizializzazione contatori peso
    @FXML
    public void initialize() {
        aggiornaSezCarrello();
        populateComboBox(peso2);populateComboBox(peso3);
        populateComboBoxPezzi(peso1);
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

    //Metodi aggiunta verdure
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
    public void aggAsparagi(){//Prodotto con peso fisso
        aggiungiProdotto("Asparagi",2.99*0.2,0.2); // aggiungiamo allo scontrino
        immDiConferma(); //diamo un ingresso visivo di ciò
    }
    @FXML
    public void aggCarciofi(){
        // Ottieni il RadioButton selezionato
        RadioButton radioSelezionato = (RadioButton) carciofi.getSelectedToggle();
        if (peso1.getValue() == null || radioSelezionato == null)
            immDiErrore();
        else{
            String selectedText = radioSelezionato.getText();
            double mon;//Prezzo Variabile
            if (selectedText.equalsIgnoreCase("normali"))
                mon=0.99;
            else
                mon=1.29;
            aggiuntaProdotto("Carciofi "+selectedText,mon,peso1);
        }
    }
    //Metodo per il cambio immagine quando si seleziona il relativo radiobutton
    @FXML
    public void radCarciofi(){
        RadioButton radioSelezionato = (RadioButton) carciofi.getSelectedToggle();
        String selectedText = radioSelezionato.getText();
        if (selectedText.equalsIgnoreCase("normali")) {
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/galafruit_carciofi-300x300.jpg").toExternalForm());
            imCarc.setImage(newImage);
        }
        else{
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/carciofo_romano.jpg").toExternalForm());
            imCarc.setImage(newImage);
        }
        double mon; //Prezzo variabile
        if (selectedText.equalsIgnoreCase("normali"))
            mon=0.99;
        else
            mon=1.29;
        prezzoCarciofi.setText(mon+"€/Pz"); //Cambio il prezzo visibile
    }
    @FXML
    public void aggCarote(){
        aggiuntaProdotto("Carote",1.99,peso2);
    }
    @FXML
    public void aggCavolfiore(){
        RadioButton carciofiSelectedToggle = (RadioButton) cavolfiore.getSelectedToggle();
        if (peso3.getValue() == null || carciofiSelectedToggle == null)
            immDiErrore();
        else{
            String selectedText = carciofiSelectedToggle.getText();
            aggiuntaProdotto("Cavolfiore "+selectedText,2.89,peso3);
        }
    }
    //Metodo per il cambio immagine quando si seleziona il relativo radiobutton
    @FXML
    public void radCavolfiori(){
        RadioButton radioSelezionato = (RadioButton) cavolfiore.getSelectedToggle();
        String selectedText = radioSelezionato.getText();
        if (selectedText.equalsIgnoreCase("bianco")) {
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/cavolfiore.jpg").toExternalForm());
            imCavol.setImage(newImage);
        }
        else if (selectedText.equalsIgnoreCase("giallo")) {
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/cavolfiore_giallo.jpg").toExternalForm());
            imCavol.setImage(newImage);
        } else if (selectedText.equalsIgnoreCase("viola")) {
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/cavolfiore_viola.jpg").toExternalForm());
            imCavol.setImage(newImage);
        } else {
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/cavolfiore_verde.jpg").toExternalForm());
            imCavol.setImage(newImage);
        }
    }

    // Metodo per aggiungere una riga
    public void aggiungiProdotto(String prodotto, double prezzo, double peso) {
        testClass.addProduct(prodotto, arrotondaAlCent(prezzo), peso);
    }

    //Metodo per allestimenti, se così si può dire dei comboBox, si può usare adAll
    private void populateComboBox(ComboBox<Double> peso) {
        for (double i = 5.00; i >= 0.10; i -= 0.05) {
            peso.getItems().add(0, arrotondaAlCent(i));
        }
    }
    //Metodo per allestimenti, se così si può dire dei comboBox, si può usare adAll
    private void populateComboBoxPezzi(ComboBox<Double> peso) {
        for (double i = 10.00; i >= 1.00; i -= 1.00) {
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
    private void switchToVerdura(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AVerdura.fxml"));//Passo alla verdura
        Scene scene = new Scene(fxmlLoader.load(), 1040, 650); // Creo una nuova scena
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); //Prendo lo stage in atto e lo setto
        stage.setResizable(false); //Non permetto il ridimensionamento della finestra x estetica
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

