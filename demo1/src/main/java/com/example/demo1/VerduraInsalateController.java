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

public class VerduraInsalateController {
    @FXML
    private ComboBox<Double> peso1,peso2,peso3,peso4,peso5,peso6,peso7,peso8,peso9,peso10,peso11,peso12,peso13,peso14,peso15,peso16,peso17,peso18,peso19,peso20,peso21,peso22,peso23,peso24,peso25,peso26,peso27,peso28,peso29,peso30,peso31,peso32,peso33,peso34,peso35,peso36,peso37,peso38,peso39,peso40,peso41,peso42,peso43,peso44,peso45,peso46,peso47,peso48,peso49,peso50,peso51,peso52,peso53,peso54,peso55,peso56,peso57,peso58,peso59,peso60,peso63,peso64,peso65,peso66,peso67,peso68;
    @FXML
    private Button bottOk;
    @FXML
    private ListView<HBox> cartListView;
    @FXML
    private Label totalLabel,prezzoCarciofi,prezzoSpinaci,prezzoPeperoni,prezzoSedano,prezzoRape,prezzoRadicchio,prezzoPatate,prezzoCiliegino,prezzoDatterino,prezzoPiccadilly,prezzoPachino,prezzoSanMarzano,prezzoCostoluto,prezzoCuoreDiBue;
    @FXML
    private ToggleGroup carciofi,cavolfiore,peperoni,sedano,rape,melanzane,alchechengi,cicoria,indivia,lattuga,radicchio,cavolo,bietola,zucca,patate,batate,scorzo,ciliegino,datterino;
    @FXML
    private ImageView imCarc,imCavol,imPeper,imSedan,imRape,imMelanz,imAlchec,imCicoria,imIndivia,imLattuga,imRadicchio,imCavolo,imBietola,imZucca,imPatate,imBatate,imScorzo,imCiliegino,imDatterino;
    @FXML
    private  CheckBox c4,c53,c54,c55,c56,c57,c58,c59;

    //Inizializzazione contatori peso
    @FXML
    public void initialize() {
        aggiornaSezCarrello();
        populateComboBox(peso2);populateComboBox(peso3);populateComboBox(peso4);populateComboBox(peso5);populateComboBox(peso6);populateComboBox(peso7);populateComboBox(peso8);populateComboBox(peso9);populateComboBox(peso10);populateComboBox(peso11);populateComboBox(peso12);populateComboBox(peso14);populateComboBox(peso15);populateComboBox(peso16);populateComboBox(peso17);populateComboBox(peso18);populateComboBox(peso19);populateComboBox(peso20);populateComboBox(peso21);
        populateComboBox(peso22);populateComboBox(peso23);populateComboBox(peso24);populateComboBox(peso25);populateComboBox(peso26);populateComboBox(peso27);populateComboBox(peso28);populateComboBox(peso29);populateComboBox(peso30);populateComboBox(peso31);populateComboBox(peso32);populateComboBox(peso33);populateComboBox(peso34);populateComboBox(peso35);populateComboBox(peso36);populateComboBox(peso37);populateComboBox(peso38);populateComboBox(peso39);populateComboBox(peso53);populateComboBox(peso54);populateComboBox(peso55);populateComboBox(peso56);populateComboBox(peso57);populateComboBox(peso58);populateComboBox(peso59);
        populateComboBoxPezzi(peso1);populateComboBoxPezzi(peso13);
        populateComboBoxPiccolo(peso60);populateComboBoxPiccolo(peso63);populateComboBoxPiccolo(peso64);populateComboBoxPiccolo(peso65);populateComboBoxPiccolo(peso66);populateComboBoxPiccolo(peso67);populateComboBoxPiccolo(peso68);
        populateComboBoxGrande(peso40);populateComboBoxGrande(peso41);populateComboBoxGrande(peso42);populateComboBoxGrande(peso43);populateComboBoxGrande(peso44);populateComboBoxGrande(peso45);populateComboBoxGrande(peso46);populateComboBoxGrande(peso47);populateComboBoxGrande(peso48);populateComboBoxGrande(peso49);populateComboBoxGrande(peso50);populateComboBoxGrande(peso51);populateComboBoxGrande(peso52);
        // Imposta un unico gestore di eventi per tutte le CheckBox
        c4.setOnAction(event -> sceltaCassetta(c4, peso4, prezzoSpinaci));
        c53.setOnAction(event -> sceltaCassetta(c53, peso53, prezzoCiliegino));
        c54.setOnAction(event -> sceltaCassetta(c54, peso54, prezzoDatterino));
        c55.setOnAction(event -> sceltaCassetta(c55, peso55, prezzoPiccadilly));
        c56.setOnAction(event -> sceltaCassetta(c56, peso56, prezzoPachino));
        c57.setOnAction(event -> sceltaCassetta(c57, peso57, prezzoSanMarzano));
        c58.setOnAction(event -> sceltaCassetta(c58, peso58, prezzoCostoluto));
        c59.setOnAction(event -> sceltaCassetta(c59, peso59, prezzoCuoreDiBue));
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
    //Metodo per allestimenti, se così si può dire dei comboBox, si può usare adAll

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
    @FXML
    public void aggSpinaci(){
        if (peso4.getValue() == null)
            immDiErrore();
        else{
            String s;
            double mon;
            if (c4.isSelected()) {
                s = "Cassetta";
                mon=2.79;
            }
            else {
                s = "";
                mon=3.90;
            }
            double peso=Double.parseDouble(String.valueOf(peso4.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga(s+" Spinaci",mon*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void aggZucchine(){
        if (peso5.getValue() == null)
            immDiErrore();
        else{
            double peso=Double.parseDouble(String.valueOf(peso5.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Zucchine",1.99*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void aggPeperoni(){
        RadioButton carciofiSelectedToggle = (RadioButton) peperoni.getSelectedToggle(); //Il nome me lo ha dato lui in auto io lo avrei chiamato erCarciofone
        if (peso6.getValue() == null || carciofiSelectedToggle == null)
            immDiErrore();
        else{
            String selectedText = carciofiSelectedToggle.getText();
            double peso=Double.parseDouble(String.valueOf(peso6.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            double mon;
            if (selectedText.equalsIgnoreCase("dolci"))
                mon=3.98;
            else
                mon=2.99;
            aggiungiRiga("Peperoni "+selectedText,mon*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    //Metodo per il cambio immagine quando si seleziona il relativo radiobutton
    @FXML
    public void radPeperoni(){
        RadioButton SelectedToggle = (RadioButton) peperoni.getSelectedToggle();
        String selectedText = SelectedToggle.getText();
        if (selectedText.equalsIgnoreCase("rossi")) {
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/peperone_rosso.jpg").toExternalForm());
            imPeper.setImage(newImage);
        }
        else if (selectedText.equalsIgnoreCase("gialli")) {
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/peperone_giallo.jpg").toExternalForm());
            imPeper.setImage(newImage);
        } else if (selectedText.equalsIgnoreCase("verdi")) {
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/peperone_verde.jpg").toExternalForm());
            imPeper.setImage(newImage);
        } else {
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/peperone_dolce.jpg").toExternalForm());
            imPeper.setImage(newImage);
        }
        double mon;
        if (selectedText.equalsIgnoreCase("dolci"))
            mon=3.98;
        else
            mon=2.99;
        prezzoPeperoni.setText(mon+"€/kg");
    }
    @FXML
    public void aggCavoletti(){
        if (peso7.getValue() == null)
            immDiErrore();
        else{
            double peso=Double.parseDouble(String.valueOf(peso7.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Cavoletti di Bruxelles",3.39*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void aggSedano(){
        RadioButton carciofiSelectedToggle = (RadioButton) sedano.getSelectedToggle(); //Il nome me lo ha dato lui in auto io lo avrei chiamato erCarciofone
        if (peso8.getValue() == null || carciofiSelectedToggle == null)
            immDiErrore();
        else{
            String selectedText = carciofiSelectedToggle.getText();
            double peso=Double.parseDouble(String.valueOf(peso8.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            double mon;
            if (selectedText.equalsIgnoreCase("rapa"))
                mon=2.50;
            else
                mon=2.39;
            aggiungiRiga("Sedano "+selectedText,mon*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void radSedano(){
        RadioButton SelectedToggle = (RadioButton) sedano.getSelectedToggle();
        String selectedText = SelectedToggle.getText();
        if (selectedText.equalsIgnoreCase("bianco")) {
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/sedano_bianco.jpg").toExternalForm());
            imSedan.setImage(newImage);
        }
        else if (selectedText.equalsIgnoreCase("verde")) {
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/sedano_verde.jpg").toExternalForm());
            imSedan.setImage(newImage);
        } else {
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/sedano_rapa.jpg").toExternalForm());
            imSedan.setImage(newImage);
        }
        double mon;
        if (selectedText.equalsIgnoreCase("rapa"))
            mon=2.50;
        else
            mon=2.39;
        prezzoSedano.setText(mon+"€/kg");
    }
    @FXML
    public void aggFagiolini(){
        if (peso9.getValue() == null)
            immDiErrore();
        else{
            double peso=Double.parseDouble(String.valueOf(peso9.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Fagiolini",2.99*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void aggRape(){
        RadioButton carciofiSelectedToggle = (RadioButton) rape.getSelectedToggle(); //Il nome me lo ha dato lui in auto io lo avrei chiamato erCarciofone
        if (peso10.getValue() == null || carciofiSelectedToggle == null)
            immDiErrore();
        else{
            String selectedText = carciofiSelectedToggle.getText();
            double peso=Double.parseDouble(String.valueOf(peso10.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            double mon;
            if (selectedText.equalsIgnoreCase("bianca"))
                mon=3.5;
            else
                mon=4.5;
            aggiungiRiga("Rape "+selectedText,mon*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void radRape(){
        RadioButton SelectedToggle = (RadioButton) rape.getSelectedToggle();
        String selectedText = SelectedToggle.getText();
        if (selectedText.equalsIgnoreCase("bianca")) {
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/rape_bianche.jpg").toExternalForm());
            imRape.setImage(newImage);
        }
        else {
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/rape_rosse.jpg").toExternalForm());
            imRape.setImage(newImage);
        }
        double mon;
        if (selectedText.equalsIgnoreCase("bianca"))
            mon=3.5;
        else
            mon=4.5;
        prezzoRape.setText(mon+"€/kg");
    }
    @FXML
    public void aggBarbabietole(){
        if (peso11.getValue() == null)
            immDiErrore();
        else{
            double peso=Double.parseDouble(String.valueOf(peso11.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Barbabietole",4.29*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void aggFinocchio(){
        if (peso12.getValue() == null)
            immDiErrore();
        else{
            double peso=Double.parseDouble(String.valueOf(peso12.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Finocchio",3.29*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void aggRavanelli(){
        if (peso13.getValue() == null)
            immDiErrore();
        else{
            double peso=Double.parseDouble(String.valueOf(peso13.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Ravanelli",1.40*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void aggMelanzane(){
        RadioButton carciofiSelectedToggle = (RadioButton) melanzane.getSelectedToggle(); //Il nome me lo ha dato lui in auto io lo avrei chiamato erCarciofone
        if (peso14.getValue() == null || carciofiSelectedToggle == null)
            immDiErrore();
        else{
            String selectedText = carciofiSelectedToggle.getText();
            double peso=Double.parseDouble(String.valueOf(peso14.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Melanzane "+selectedText,1.29*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void radMelanzane(){
        RadioButton SelectedToggle = (RadioButton) melanzane.getSelectedToggle();
        String selectedText = SelectedToggle.getText();
        if (selectedText.equalsIgnoreCase("viola")) {
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/melanzane_viola.jpg").toExternalForm());
            imMelanz.setImage(newImage);
        }
        else {
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/melanzane_bianche.jpg").toExternalForm());
            imMelanz.setImage(newImage);
        }
    }
    @FXML
    public void aggBroccolini(){
        if (peso15.getValue() == null)
            immDiErrore();
        else{
            double peso=Double.parseDouble(String.valueOf(peso15.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Broccolini",4.50*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void aggBroccoli(){
        if (peso16.getValue() == null)
            immDiErrore();
        else{
            double peso=Double.parseDouble(String.valueOf(peso16.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Broccoli",3.99*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void aggAlchechengi(){
        if (peso17.getValue() == null)
            immDiErrore();
        else{
            double peso=Double.parseDouble(String.valueOf(peso17.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Alchechengi",9.99*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void radAlchechengi(){
        RadioButton SelectedToggle = (RadioButton) alchechengi.getSelectedToggle();
        String selectedText = SelectedToggle.getText();
        if (selectedText.equalsIgnoreCase("selvatica")) {
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/alchechengi_selvatica.jpg").toExternalForm());
            imAlchec.setImage(newImage);
        }
        else {
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/alchechengi_peruviano.jpg").toExternalForm());
            imAlchec.setImage(newImage);
        }
    }
    @FXML
    public void aggAlche(){
        if (peso18.getValue() == null)
            immDiErrore();
        else{
            double peso=Double.parseDouble(String.valueOf(peso18.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Alghe",66.37*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void aggCavoloRapa(){
        if (peso19.getValue() == null)
            immDiErrore();
        else{
            double peso=Double.parseDouble(String.valueOf(peso19.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Cavolo-rapa",3.50*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }

    //A foglie
    @FXML
    public void aggAgretti(){
        if (peso20.getValue() == null)
            immDiErrore();
        else{
            double peso=Double.parseDouble(String.valueOf(peso20.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Agretti ",7.98*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void aggCrescione(){
        if (peso21.getValue() == null)
            immDiErrore();
        else{
            double peso=Double.parseDouble(String.valueOf(peso21.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Crescione ",9.00*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void aggCicoria(){
        RadioButton carciofiSelectedToggle = (RadioButton) cicoria.getSelectedToggle(); //Il nome me lo ha dato lui in auto io lo avrei chiamato erCarciofone
        if (peso22.getValue() == null || carciofiSelectedToggle == null)
            immDiErrore();
        else{
            String selectedText = carciofiSelectedToggle.getText();
            double peso=Double.parseDouble(String.valueOf(peso22.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Cicoria "+selectedText,3.90*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    //Metodo per il cambio immagine quando si seleziona il relativo radiobutton
    @FXML
    public void radCicoria(){
        RadioButton SelectedToggle = (RadioButton) cicoria.getSelectedToggle();
        String selectedText = SelectedToggle.getText();
        if (selectedText.equalsIgnoreCase("catalogna")) {
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/insalate/cicoria_catalogna.jpg").toExternalForm());
            imCicoria.setImage(newImage);
        }
        else {
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/insalate/cicoria_puntarelle.jpg").toExternalForm());
            imCicoria.setImage(newImage);
        }
    }
    @FXML
    public void aggIceberg(){
        if (peso23.getValue() == null)
            immDiErrore();
        else{
            double peso=Double.parseDouble(String.valueOf(peso23.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Iceberg ",2.90*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void aggRiccia(){
        if (peso24.getValue() == null)
            immDiErrore();
        else{
            double peso=Double.parseDouble(String.valueOf(peso24.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Riccia ",2.90*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void aggIndivia(){
        RadioButton carciofiSelectedToggle = (RadioButton) indivia.getSelectedToggle(); //Il nome me lo ha dato lui in auto io lo avrei chiamato erCarciofone
        if (peso25.getValue() == null || carciofiSelectedToggle == null)
            immDiErrore();
        else{
            String selectedText = carciofiSelectedToggle.getText();
            double peso=Double.parseDouble(String.valueOf(peso25.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Indivia "+selectedText,2.90*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    //Metodo per il cambio immagine quando si seleziona il relativo radiobutton
    @FXML
    public void radIndivia(){
        RadioButton SelectedToggle = (RadioButton) indivia.getSelectedToggle();
        String selectedText = SelectedToggle.getText();
        if (selectedText.equalsIgnoreCase("Belga")) {
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/insalate/indivia_belga.jpg").toExternalForm());
            imIndivia.setImage(newImage);
        } else {
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/insalate/indivia_riccia.jpg").toExternalForm());
            imIndivia.setImage(newImage);
        }
    }
    @FXML
    public void aggLattuga(){
        RadioButton carciofiSelectedToggle = (RadioButton) lattuga.getSelectedToggle(); //Il nome me lo ha dato lui in auto io lo avrei chiamato erCarciofone
        if (peso26.getValue() == null || carciofiSelectedToggle == null)
            immDiErrore();
        else{
            String selectedText = carciofiSelectedToggle.getText();
            double peso=Double.parseDouble(String.valueOf(peso26.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Lattuga "+selectedText,2.90*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    //Metodo per il cambio immagine quando si seleziona il relativo radiobutton
    @FXML
    public void radLattuga(){
        RadioButton SelectedToggle = (RadioButton) lattuga.getSelectedToggle();
        String selectedText = SelectedToggle.getText();
        if (selectedText.equalsIgnoreCase("gentile")) {
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/insalate/lattuga_gentile.jpg").toExternalForm());
            imLattuga.setImage(newImage);
        } else if (selectedText.equalsIgnoreCase("batavia")) {
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/insalate/lattuga_batavia.jpg").toExternalForm());
            imLattuga.setImage(newImage);
        } else if (selectedText.equalsIgnoreCase("cappuccio")) {
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/insalate/lattuga_cappuccio.jpg").toExternalForm());
            imLattuga.setImage(newImage);
        } else if (selectedText.equalsIgnoreCase("iceberg")) {
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/insalate/lattuga_iceberg.jpg").toExternalForm());
            imLattuga.setImage(newImage);
        } else if (selectedText.equalsIgnoreCase("romana")) {
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/insalate/lattuga_romana.jpg").toExternalForm());
            imLattuga.setImage(newImage);
        } else {
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/insalate/lattuga_lollorossa.jpg").toExternalForm());
            imLattuga.setImage(newImage);
        }
    }
    @FXML
    public void aggPortulaca(){
        if (peso27.getValue() == null)
            immDiErrore();
        else{
            double peso=Double.parseDouble(String.valueOf(peso27.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Portulaca ",8.30*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void aggRadicchio(){
        RadioButton carciofiSelectedToggle = (RadioButton) radicchio.getSelectedToggle(); //Il nome me lo ha dato lui in auto io lo avrei chiamato erCarciofone
        if (peso28.getValue() == null || carciofiSelectedToggle == null)
            immDiErrore();
        else{
            String selectedText = carciofiSelectedToggle.getText();
            double peso=Double.parseDouble(String.valueOf(peso28.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            double mon;
            if (selectedText.equalsIgnoreCase("Di treviso") || selectedText.equalsIgnoreCase("di chioggia igp"))
                mon=2.40;
            else
                mon=5.90;
            aggiungiRiga("Radicchio "+selectedText,mon*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    //Metodo per il cambio immagine quando si seleziona il relativo radiobutton
    @FXML
    public void radRadicchio(){
        RadioButton SelectedToggle = (RadioButton) radicchio.getSelectedToggle();
        String selectedText = SelectedToggle.getText();
        if (selectedText.equalsIgnoreCase("Di treviso")) {
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/insalate/radicchio_treviso.jpg").toExternalForm());
            imRadicchio.setImage(newImage);
        }
        else if (selectedText.equalsIgnoreCase("di chioggia igp")) {
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/insalate/radicchio_chioggia.jpg").toExternalForm());
            imRadicchio.setImage(newImage);
        } else if (selectedText.equalsIgnoreCase("rosso di verona")) {
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/insalate/radicchio_rossodiverona.jpg").toExternalForm());
            imRadicchio.setImage(newImage);
        } else {
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/insalate/radicchio_variegatodicastelfranco.jpg").toExternalForm());
            imRadicchio.setImage(newImage);
        }
        double mon;
        if (selectedText.equalsIgnoreCase("Di treviso") || selectedText.equalsIgnoreCase("di chioggia igp"))
            mon=2.50;
        else
            mon=5.90;
        prezzoRadicchio.setText(mon+"€/Kg");
    }
    @FXML
    public void aggRucola(){
        if (peso29.getValue() == null)
            immDiErrore();
        else{
            double peso=Double.parseDouble(String.valueOf(peso29.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Rucola ",1.70*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void aggScarola(){
        if (peso30.getValue() == null)
            immDiErrore();
        else{
            double peso=Double.parseDouble(String.valueOf(peso30.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Scarola ",1.80*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void aggSpinaciDaInsalata(){
        if (peso31.getValue() == null)
            immDiErrore();
        else{
            double peso=Double.parseDouble(String.valueOf(peso31.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Spinaci da Insalata ",2.80*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void aggValeriana(){
        if (peso32.getValue() == null)
            immDiErrore();
        else{
            double peso=Double.parseDouble(String.valueOf(peso32.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Valeriana ",2.90*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void aggCavolo(){
        RadioButton carciofiSelectedToggle = (RadioButton) cavolo.getSelectedToggle(); //Il nome me lo ha dato lui in auto io lo avrei chiamato erCarciofone
        if (peso33.getValue() == null || carciofiSelectedToggle == null)
            immDiErrore();
        else{
            String selectedText = carciofiSelectedToggle.getText();
            double peso=Double.parseDouble(String.valueOf(peso33.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Cavolo "+selectedText,2.90*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    //Metodo per il cambio immagine quando si seleziona il relativo radiobutton
    @FXML
    public void radCavolo(){
        RadioButton SelectedToggle = (RadioButton) cavolo.getSelectedToggle();
        String selectedText = SelectedToggle.getText();
        if (selectedText.equalsIgnoreCase("nero")) {
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/insalate/cavolo_nero.jpg").toExternalForm());
            imCavolo.setImage(newImage);
        }
        else if (selectedText.equalsIgnoreCase("cinese")) {
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/insalate/cavolo_cinese.jpg").toExternalForm());
            imCavolo.setImage(newImage);
        } else if (selectedText.equalsIgnoreCase("rosso")) {
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/insalate/cavolo_rosso.jpg").toExternalForm());
            imCavolo.setImage(newImage);
        } else {
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/insalate/cavolo_cappuccio.jpg").toExternalForm());
            imCavolo.setImage(newImage);
        }
    }
    @FXML
    public void aggVerza(){
        if (peso34.getValue() == null)
            immDiErrore();
        else{
            double peso=Double.parseDouble(String.valueOf(peso34.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Verza ",1.90*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void aggCardo(){
        if (peso35.getValue() == null)
            immDiErrore();
        else{
            double peso=Double.parseDouble(String.valueOf(peso35.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Cardo ",3.90*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void aggBietola(){
        RadioButton carciofiSelectedToggle = (RadioButton) bietola.getSelectedToggle(); //Il nome me lo ha dato lui in auto io lo avrei chiamato erCarciofone
        if (peso36.getValue() == null || carciofiSelectedToggle == null)
            immDiErrore();
        else{
            String selectedText = carciofiSelectedToggle.getText();
            double peso=Double.parseDouble(String.valueOf(peso36.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Bietola "+selectedText,4.50*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    //Metodo per il cambio immagine quando si seleziona il relativo radiobutton
    @FXML
    public void radBietola(){
        RadioButton SelectedToggle = (RadioButton) bietola.getSelectedToggle();
        String selectedText = SelectedToggle.getText();
        if (selectedText.equalsIgnoreCase("bianca")) {
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/insalate/bietola_bianca.jpg").toExternalForm());
            imBietola.setImage(newImage);
        }
        else if (selectedText.equalsIgnoreCase("gialla")) {
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/insalate/bietola_gialla.jpg").toExternalForm());
            imBietola.setImage(newImage);
        } else {
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/insalate/bietola_rossa.jpg").toExternalForm());
            imBietola.setImage(newImage);
        }
    }
    @FXML
    public void aggCoste(){
        if (peso37.getValue() == null)
            immDiErrore();
        else{
            double peso=Double.parseDouble(String.valueOf(peso37.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Coste bianche ",2.50*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void aggCime(){
        if (peso38.getValue() == null)
            immDiErrore();
        else{
            double peso=Double.parseDouble(String.valueOf(peso38.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Cime di rapa ",2.50*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void aggFriarielli(){
        if (peso39.getValue() == null)
            immDiErrore();
        else{
            double peso=Double.parseDouble(String.valueOf(peso39.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Friarielli ",3.00*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }


    //Tuberi
    @FXML
    public void aggZucca(){
        RadioButton carciofiSelectedToggle = (RadioButton) zucca.getSelectedToggle(); //Il nome me lo ha dato lui in auto io lo avrei chiamato erCarciofone
        if (peso40.getValue() == null || carciofiSelectedToggle == null)
            immDiErrore();
        else{
            String selectedText = carciofiSelectedToggle.getText();
            double peso=Double.parseDouble(String.valueOf(peso40.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Zucca "+selectedText,2.46*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void radZucca(){
        RadioButton SelectedToggle = (RadioButton) zucca.getSelectedToggle();
        String selectedText = SelectedToggle.getText();
        if (selectedText.equalsIgnoreCase("atlantic")) {
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/tuberi/zucca_atlantic.jpg").toExternalForm());
            imZucca.setImage(newImage);
        } else if (selectedText.equalsIgnoreCase("delica")) {
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/tuberi/zucca_delica.jpg").toExternalForm());
            imZucca.setImage(newImage);
        } else if (selectedText.equalsIgnoreCase("trombetta")) {
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/tuberi/zucca_trombetta.jpg").toExternalForm());
            imZucca.setImage(newImage);
        } else {
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/tuberi/zucca_marinadichioggia.jpg").toExternalForm());
            imZucca.setImage(newImage);
        }
    }
    @FXML
    public void aggPatate(){
        RadioButton carciofiSelectedToggle = (RadioButton) patate.getSelectedToggle(); //Il nome me lo ha dato lui in auto io lo avrei chiamato erCarciofone
        if (peso41.getValue() == null || carciofiSelectedToggle == null)
            immDiErrore();
        else{
            String selectedText = carciofiSelectedToggle.getText();
            double peso=Double.parseDouble(String.valueOf(peso41.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            double mon;
            if (selectedText.equalsIgnoreCase("normali"))
                mon=1.20;
            else
                mon=2.90;
            aggiungiRiga("Patate "+selectedText,mon*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void radPatate(){
        RadioButton SelectedToggle = (RadioButton) patate.getSelectedToggle();
        String selectedText = SelectedToggle.getText();
        if (selectedText.equalsIgnoreCase("normali")) {
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/tuberi/patate_normali.jpg").toExternalForm());
            imPatate.setImage(newImage);
        } else {
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/tuberi/patate_dolci.jpg").toExternalForm());
            imPatate.setImage(newImage);
        }
        double mon;
        if (selectedText.equalsIgnoreCase("normali"))
            mon=1.20;
        else
            mon=2.90;
        prezzoPatate.setText(mon+"€/Pz");
    }
    @FXML
    public void aggBatate(){
        RadioButton carciofiSelectedToggle = (RadioButton) batate.getSelectedToggle(); //Il nome me lo ha dato lui in auto io lo avrei chiamato erCarciofone
        if (peso42.getValue() == null || carciofiSelectedToggle == null)
            immDiErrore();
        else{
            String selectedText = carciofiSelectedToggle.getText();
            double peso=Double.parseDouble(String.valueOf(peso42.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Batate "+selectedText,3.90*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void radBatate(){
        RadioButton SelectedToggle = (RadioButton) batate.getSelectedToggle();
        String selectedText = SelectedToggle.getText();
        if (selectedText.equalsIgnoreCase("rosse")) {
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/tuberi/batate_rosse.jpg").toExternalForm());
            imBatate.setImage(newImage);
        } else {
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/tuberi/batate_viola.jpg").toExternalForm());
            imBatate.setImage(newImage);
        }
    }
    @FXML
    public void aggScorzo(){
        RadioButton carciofiSelectedToggle = (RadioButton) scorzo.getSelectedToggle(); //Il nome me lo ha dato lui in auto io lo avrei chiamato erCarciofone
        if (peso43.getValue() == null || carciofiSelectedToggle == null)
            immDiErrore();
        else{
            String selectedText = carciofiSelectedToggle.getText();
            double peso=Double.parseDouble(String.valueOf(peso43.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Scorzo"+selectedText,8.90*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void radScorzo(){
        RadioButton SelectedToggle = (RadioButton) scorzo.getSelectedToggle();
        String selectedText = SelectedToggle.getText();
        if (selectedText.equalsIgnoreCase("nera")) {
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/tuberi/scorzo_nera.jpg").toExternalForm());
            imScorzo.setImage(newImage);
        } else {
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/tuberi/scorzo_bianca.jpg").toExternalForm());
            imScorzo.setImage(newImage);
        }
    }
    @FXML
    public void aggTopinambur(){
        if (peso44.getValue() == null)
            immDiErrore();
        else{
            double peso=Double.parseDouble(String.valueOf(peso44.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Topinambur ",8.90*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    //Cipolle
    @FXML
    public void aggCipolleBianche(){
        if (peso45.getValue() == null)
            immDiErrore();
        else{
            double peso=Double.parseDouble(String.valueOf(peso45.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Cipolle bianche ",2.5*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void aggCipolleRosse(){
        if (peso46.getValue() == null)
            immDiErrore();
        else{
            double peso=Double.parseDouble(String.valueOf(peso46.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Cipolle rosse ",2.9*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void aggCipolleTropea(){
        if (peso47.getValue() == null)
            immDiErrore();
        else{
            double peso=Double.parseDouble(String.valueOf(peso47.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Cipolle di tropea ",3.0*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void aggPorri(){
        if (peso48.getValue() == null)
            immDiErrore();
        else{
            double peso=Double.parseDouble(String.valueOf(peso48.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Porri ",2.5*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void aggCipollottiBianchi(){
        if (peso49.getValue() == null)
            immDiErrore();
        else{
            double peso=Double.parseDouble(String.valueOf(peso49.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Cipollotti bianchi ",2.5*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void aggCipollottiRossi(){
        if (peso50.getValue() == null)
            immDiErrore();
        else{
            double peso=Double.parseDouble(String.valueOf(peso50.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Cipollotti rossi ",2.5*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void aggCipolline(){
        if (peso51.getValue() == null)
            immDiErrore();
        else{
            double peso=Double.parseDouble(String.valueOf(peso51.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Cipolline ",3.2*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void aggAglio(){
        if (peso52.getValue() == null)
            immDiErrore();
        else{
            double peso=Double.parseDouble(String.valueOf(peso52.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Aglio ",2.9*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    //Pomodori
    @FXML
    public void aggCiliegino(){
        RadioButton carciofiSelectedToggle = (RadioButton) ciliegino.getSelectedToggle(); //Il nome me lo ha dato lui in auto io lo avrei chiamato erCarciofone
        if (peso53.getValue() == null || carciofiSelectedToggle == null)
            immDiErrore();
        else{
            String s;
            double mon;
            if (c53.isSelected()) {
                s = "Cassetta";
                mon=1.99;
            }
            else {
                s = "";
                mon=2.99;
            }
            String selectedText = carciofiSelectedToggle.getText();
            double peso=Double.parseDouble(String.valueOf(peso53.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga(s+" Ciliegino "+selectedText,mon*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    //Metodo per il cambio immagine quando si seleziona il relativo radiobutton
    @FXML
    public void radCiliegino(){
        RadioButton SelectedToggle = (RadioButton) ciliegino.getSelectedToggle();
        String selectedText = SelectedToggle.getText();
        if (selectedText.equalsIgnoreCase("rosso")) {
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/pomodori/ciliegino_rosso.jpg").toExternalForm());
            imCiliegino.setImage(newImage);
        }
        else if (selectedText.equalsIgnoreCase("nero")) {
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/pomodori/ciliegino_nero.jpg").toExternalForm());
            imCiliegino.setImage(newImage);
        } else {
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/pomodori/ciliegino_giallo.jpg").toExternalForm());
            imCiliegino.setImage(newImage);
        }
    }
    @FXML
    public void aggDatterino(){
        RadioButton carciofiSelectedToggle = (RadioButton) datterino.getSelectedToggle(); //Il nome me lo ha dato lui in auto io lo avrei chiamato erCarciofone
        if (peso54.getValue() == null || carciofiSelectedToggle == null)
            immDiErrore();
        else{
            String s;
            double mon;
            if (c54.isSelected()) {
                s = "Cassetta";
                mon=1.99;
            }
            else {
                s = "";
                mon=2.99;
            }
            String selectedText = carciofiSelectedToggle.getText();
            double peso=Double.parseDouble(String.valueOf(peso54.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga(s+" Datterino "+selectedText,mon*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    //Metodo per il cambio immagine quando si seleziona il relativo radiobutton
    @FXML
    public void radDatterino(){
        RadioButton SelectedToggle = (RadioButton) datterino.getSelectedToggle();
        String selectedText = SelectedToggle.getText();
        if (selectedText.equalsIgnoreCase("rosso")) {
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/pomodori/datterino_rosso.jpg").toExternalForm());
            imDatterino.setImage(newImage);
        }
        else if (selectedText.equalsIgnoreCase("nero")) {
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/pomodori/datterino_nero.jpg").toExternalForm());
            imDatterino.setImage(newImage);
        } else {
            Image newImage = new Image(getClass().getResource("/com/example/demo1/img/verdura/pomodori/datterino_giallo.jpg").toExternalForm());
            imDatterino.setImage(newImage);
        }
    }
    @FXML
    public void aggPiccadilly(){
        if (peso55.getValue() == null)
            immDiErrore();
        else{
            String s;
            double mon;
            if (c55.isSelected()) {
                s = "Cassetta";
                mon=1.99;
            }
            else {
                s = "";
                mon=2.99;
            }
            double peso=Double.parseDouble(String.valueOf(peso55.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga(s+" Piccadilly ",mon*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void aggPachino(){
        if (peso56.getValue() == null)
            immDiErrore();
        else{
            String s;
            double mon;
            if (c56.isSelected()) {
                s = "Cassetta";
                mon=1.99;
            }
            else {
                s = "";
                mon=2.99;
            }
            double peso=Double.parseDouble(String.valueOf(peso56.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga(s+" Pachino ",mon*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void aggSanMarzano(){
        if (peso57.getValue() == null)
            immDiErrore();
        else{
            String s;
            double mon;
            if (c57.isSelected()) {
                s = "Cassetta";
                mon=1.99;
            }
            else {
                s = "";
                mon=2.99;
            }
            double peso=Double.parseDouble(String.valueOf(peso57.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga(s+" San Marzano ",mon*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void aggCostoluto(){
        if (peso58.getValue() == null)
            immDiErrore();
        else{
            String s;
            double mon;
            if (c58.isSelected()) {
                s = "Cassetta";
                mon=0.99;
            }
            else {
                s = "";
                mon=1.99;
            }
            double peso=Double.parseDouble(String.valueOf(peso58.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga(s+" Costoluto ",mon*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void aggCuoreDiBue(){
        if (peso59.getValue() == null)
            immDiErrore();
        else{
            String s;
            double mon;
            if (c59.isSelected()) {
                s = "Cassetta";
                mon=0.99;
            }
            else {
                s = "";
                mon=1.99;
            }
            double peso=Double.parseDouble(String.valueOf(peso59.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga(s+" Cuore di Bue ",mon*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void aggLimoni(){
        if (peso60.getValue() == null)
            immDiErrore();
        else{
            double peso=Double.parseDouble(String.valueOf(peso60.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Limoni",2.19*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void aggLime(){
        //Conversione del valore contenuto nella combobox da stringa a double
        aggiungiRiga("Lime",1.2*0.2,0.2); // aggiungiamo allo scontrino
        immDiConferma(); //diamo un input visivo di ciò
    }
    @FXML
    public void aggPeperoncino(){
        //Conversione del valore contenuto nella combobox da stringa a double
        aggiungiRiga("Peperoncini",1.2*0.1,0.1); // aggiungiamo allo scontrino
        immDiConferma(); //diamo un input visivo di ciò
    }
    @FXML
    public void aggZenzero(){
        if (peso63.getValue() == null)
            immDiErrore();
        else{
            double peso=Double.parseDouble(String.valueOf(peso63.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Zenzero",4.4*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void aggBasilico(){
        if (peso64.getValue() == null)
            immDiErrore();
        else{
            double peso=Double.parseDouble(String.valueOf(peso64.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Basilico",8.9*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void aggRosmarino(){
        if (peso65.getValue() == null)
            immDiErrore();
        else{
            double peso=Double.parseDouble(String.valueOf(peso65.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Rosmarino",6.9*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void aggSalvia(){
        if (peso66.getValue() == null)
            immDiErrore();
        else{
            double peso=Double.parseDouble(String.valueOf(peso66.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Salvia",6.9*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void aggTimo(){
        if (peso67.getValue() == null)
            immDiErrore();
        else{
            double peso=Double.parseDouble(String.valueOf(peso67.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Timo",6.9*peso,peso); // aggiungiamo allo scontrino
            immDiConferma(); //diamo un input visivo di ciò
        }
    }
    @FXML
    public void aggMenta(){
        if (peso68.getValue() == null)
            immDiErrore();
        else{
            double peso=Double.parseDouble(String.valueOf(peso68.getValue())); //Conversione del valore contenuto nella combobox da stringa a double
            aggiungiRiga("Menta",6.9*peso,peso); // aggiungiamo allo scontrino
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
    //Metodo per allestimenti, se così si può dire dei comboBox, si può usare adAll
    private void populateComboBoxPezzi(ComboBox<Double> peso) {
        for (double i = 10.00; i >= 1.00; i -= 1.00) {
            peso.getItems().add(0, arrotondaAlCent(i));
        }
    }
    //Metodo per allestimenti, se così si può dire dei comboBox, si può usare adAll
    private void populateComboBoxPiccolo(ComboBox<Double> peso) {
        peso.getItems().addAll(0.15,0.30,0.45,0.60,0.75,0.90);
    }
    //Metodo per allestimenti, se così si può dire dei comboBox, si può usare adAll
    private void populateComboBoxGrande (ComboBox<Double> peso) {
        for (double i = 5.00; i >= 0.30; i -= 0.1) {
            peso.getItems().add(0, arrotondaAlCent(i));
        }
    }
    //Metodo per cassette
    private void sceltaCassetta(CheckBox checkBox, ComboBox<Double> comboBox, Label labPrezzi) {
        if (checkBox.isSelected()) {
            // Cancella i valori esistenti e aggiungi nuovi valori
            comboBox.getItems().clear();
            comboBox.getItems().addAll(1.0,2.0,3.0);
            System.out.println(labPrezzi.getText());
            //Cambio prezzi
            double val=Double.parseDouble(labPrezzi.getText().substring(0, 3));
            val-=1.0;
            arrotondaAlCent(val);
            labPrezzi.setText(val+"€/Kg");
        } else {
            comboBox.getItems().clear();
            populateComboBox(comboBox);
            //Cambio prezzi
            double val=Double.parseDouble(labPrezzi.getText().substring(0, 3));
            val+=1.0;
            arrotondaAlCent(val);
            labPrezzi.setText(val+"€/Kg");
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

