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
    private ComboBox<Double> peso1,peso2,peso3;
    @FXML
    private Button bottOk;
    @FXML
    private ListView<HBox> cartListView;
    @FXML
    private Label totalLabel,prezzoCarciofi;
    @FXML
    private ToggleGroup carciofi,cavolfiore;
    @FXML
    private ImageView imCarc,imCavol;
    //Inizializzazione contatori peso
    @FXML
    public void initialize() {
        aggiornaSezCarrello();
        populateComboBox(peso2);populateComboBox(peso3);
        populateComboBoxPezzi(peso1);
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
        bd = bd.setScale(2, RoundingMode.HALF_DOWN); // Arrotonda a 2 decimali
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
    //Metodi aggiunta prodotti ####VERDURE####
    @FXML
    public void aggAsparagi(){
        aggiungiRiga("Asparagi",2.99*0.2,0.2); // aggiungiamo allo scontrino
        immDiConferma(); //diamo un input visivo di ciò
    }
    @FXML
    public void aggCarciofi(){
        // Ottieni il RadioButton selezionato
        RadioButton carciofiSelectedToggle = (RadioButton) carciofi.getSelectedToggle(); //Il nome me lo ha dato lui in auto io lo avrei chiamato erCarciofone
        if (peso1.getValue() == null || carciofiSelectedToggle == null)
            immDiErrore();
        else{
            String selectedText = carciofiSelectedToggle.getText();
            double peso=Double.parseDouble(String.valueOf(peso1.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            double mon;
            if (selectedText.equalsIgnoreCase("normali"))
                mon=0.99;
            else
                mon=1.29;
            aggiungiRiga("Carciofi "+selectedText,mon*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    //Metodo per il cambio immagine quando si seleziona il relativo radiobutton
    @FXML
    public void radCarciofi(){
        RadioButton carciofiSelectedToggle = (RadioButton) carciofi.getSelectedToggle();
        String selectedText = carciofiSelectedToggle.getText();
        if (selectedText.equalsIgnoreCase("normali")) {
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/galafruit_carciofi-300x300.jpg").toExternalForm());
            imCarc.setImage(newImage);
        }
        else{
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/carciofo_romano.jpg").toExternalForm());
            imCarc.setImage(newImage);
        }
        double mon;
        if (selectedText.equalsIgnoreCase("normali"))
            mon=0.99;
        else
            mon=1.29;
        prezzoCarciofi.setText(mon+"€/Pz");
    }
    @FXML
    public void aggCarote(){
        if (peso2.getValue() == null)
            immDiErrore();
        else{
            double peso=Double.parseDouble(String.valueOf(peso2.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Carote",1.99*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void aggCavolfiore(){
        RadioButton carciofiSelectedToggle = (RadioButton) cavolfiore.getSelectedToggle(); //Il nome me lo ha dato lui in auto io lo avrei chiamato erCarciofone
        if (peso3.getValue() == null || carciofiSelectedToggle == null)
            immDiErrore();
        else{
            String selectedText = carciofiSelectedToggle.getText();
            double peso=Double.parseDouble(String.valueOf(peso3.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Cavolfiore "+selectedText,2.89*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    //Metodo per il cambio immagine quando si seleziona il relativo radiobutton
    @FXML
    public void radCavolfiori(){
        RadioButton SelectedToggle = (RadioButton) cavolfiore.getSelectedToggle();
        String selectedText = SelectedToggle.getText();
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

    //Metodo per allestimenti, se così si può dire dei comboBox, si può usare adAll
    private void populateComboBoxPezzi(ComboBox<Double> peso) {
        for (double i = 10.00; i >= 1.00; i -= 1.00) {
            peso.getItems().add(0, arrotondaAlCent(i));
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
    private void switchToVerdura(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AVerdura.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1040, 650); // Creo una nuova scena
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); //Prendo lo stage in atto e lo setto
        stage.setResizable(false); //Non permetto il ridemsionamento della finestra
        stage.setScene(scene);
        //stage.setMaximized(true); // Imposta la finestra a tutto schermo
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
    private void switchToFrutta(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AFrutta.fxml"));
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

