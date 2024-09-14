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
    //Scegli il peso dei vari prodotti in questo caso i pezzi
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
    //Immagini che cambiano in base al tipo selezionato
    @FXML
    private ImageView imCarc,imCavol,imPeper,imSedan,imRape,imMelanz,imAlchec,imCicoria,imIndivia,imLattuga,imRadicchio,imCavolo,imBietola,imZucca,imPatate,imBatate,imScorzo,imCiliegino,imDatterino;
    @FXML
    private  CheckBox c4,c53,c54,c55,c56,c57,c58,c59; //Cassetta si o no (denominazione è c+numero del peso cioè quello della combobox)

    //Metodo per aggiornare il carrello a ogni "apertura" della scena e inizializzazione contatori peso
    @FXML
    public void initialize() {
        aggiornaSezCarrello();
        //Peso normale Kg
        populateComboBox(peso2);populateComboBox(peso3);populateComboBox(peso4);populateComboBox(peso5);populateComboBox(peso6);populateComboBox(peso7);populateComboBox(peso8);populateComboBox(peso9);populateComboBox(peso10);populateComboBox(peso11);populateComboBox(peso12);populateComboBox(peso14);populateComboBox(peso15);populateComboBox(peso16);populateComboBox(peso17);populateComboBox(peso18);populateComboBox(peso19);populateComboBox(peso20);populateComboBox(peso21);
        populateComboBox(peso22);populateComboBox(peso23);populateComboBox(peso24);populateComboBox(peso25);populateComboBox(peso26);populateComboBox(peso27);populateComboBox(peso28);populateComboBox(peso29);populateComboBox(peso30);populateComboBox(peso31);populateComboBox(peso32);populateComboBox(peso33);populateComboBox(peso34);populateComboBox(peso35);populateComboBox(peso36);populateComboBox(peso37);populateComboBox(peso38);populateComboBox(peso39);populateComboBox(peso53);populateComboBox(peso54);populateComboBox(peso55);populateComboBox(peso56);populateComboBox(peso57);populateComboBox(peso58);populateComboBox(peso59);
        //N° di pezzi
        populateComboBoxPezzi(peso1);populateComboBoxPezzi(peso13);
        //Solo pesi piccoli
        populateComboBoxPiccolo(peso60);populateComboBoxPiccolo(peso63);populateComboBoxPiccolo(peso64);populateComboBoxPiccolo(peso65);populateComboBoxPiccolo(peso66);populateComboBoxPiccolo(peso67);populateComboBoxPiccolo(peso68);
        //Solo pesi grandi
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
    @FXML
    public void aggSpinaci(){
        if (peso4.getValue() == null)
            immDiErrore();
        else{
            String s;//Stringa per scontrino
            double mon;//Prezzo variabile
            if (c4.isSelected()) { //Se cassetta selezionata cambia anche il prezzo conviene
                s = "Cassetta";
                mon=2.79;
            }
            else {
                s = "";
                mon=3.90;
            }
            aggiuntaProdotto(s+" Spinaci",mon,peso4);
        }
    }
    @FXML
    public void aggZucchine(){
        aggiuntaProdotto("Zucchine",1.99,peso5);
    }
    @FXML
    public void aggPeperoni(){
        RadioButton radioSelezionato = (RadioButton) peperoni.getSelectedToggle();
        if (peso6.getValue() == null || radioSelezionato == null)
            immDiErrore();
        else{
            String selectedText = radioSelezionato.getText();
            double mon;
            if (selectedText.equalsIgnoreCase("dolci"))
                mon=3.98;
            else
                mon=2.99;
            aggiuntaProdotto("Peperoni "+selectedText,mon,peso6);
        }
    }
    //Metodo per il cambio immagine quando si seleziona il relativo radiobutton
    @FXML
    public void radPeperoni(){
        RadioButton radioSelezionato = (RadioButton) peperoni.getSelectedToggle();
        String selectedText = radioSelezionato.getText();
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
        aggiuntaProdotto("Cavoletti di Bruxelles",3.39,peso7);
    }
    @FXML
    public void aggSedano(){
        RadioButton radioSelezionato = (RadioButton) sedano.getSelectedToggle();
        if (peso8.getValue() == null || radioSelezionato == null)
            immDiErrore();
        else{
            String selectedText = radioSelezionato.getText();
            double mon;
            if (selectedText.equalsIgnoreCase("rapa"))
                mon=2.50;
            else
                mon=2.39;
            aggiuntaProdotto("Sedano "+selectedText,mon,peso8);
        }
    }
    @FXML
    public void radSedano(){
        RadioButton radioSelezionato = (RadioButton) sedano.getSelectedToggle();
        String selectedText = radioSelezionato.getText();
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
        aggiuntaProdotto("Fagiolini",2.99,peso9);
    }
    @FXML
    public void aggRape(){
        RadioButton radioSelezionato = (RadioButton) rape.getSelectedToggle();
        if (peso10.getValue() == null || radioSelezionato == null)
            immDiErrore();
        else{
            String selectedText = radioSelezionato.getText();
            double mon;
            if (selectedText.equalsIgnoreCase("bianca"))
                mon=3.5;
            else
                mon=4.5;
            aggiuntaProdotto("Rape "+selectedText,mon,peso10);
        }
    }
    @FXML
    public void radRape(){
        RadioButton radioSelezionato = (RadioButton) rape.getSelectedToggle();
        String selectedText = radioSelezionato.getText();
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
        aggiuntaProdotto("Barbabietole",4.29,peso11);
    }
    @FXML
    public void aggFinocchio(){
        aggiuntaProdotto("Finocchio",3.29,peso12);
    }
    @FXML
    public void aggRavanelli(){
        aggiuntaProdotto("Ravanelli",1.40,peso13);
    }
    @FXML
    public void aggMelanzane(){
        RadioButton radioSelezionato = (RadioButton) melanzane.getSelectedToggle();
        if (peso14.getValue() == null || radioSelezionato == null)
            immDiErrore();
        else{
            String selectedText = radioSelezionato.getText();
            aggiuntaProdotto("Melanzane "+selectedText,1.29,peso14);
        }
    }
    @FXML
    public void radMelanzane(){
        RadioButton radioSelezionato = (RadioButton) melanzane.getSelectedToggle();
        String selectedText = radioSelezionato.getText();
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
        aggiuntaProdotto("Broccolini",4.50,peso15);
    }
    @FXML
    public void aggBroccoli(){
        aggiuntaProdotto("Broccoli",3.99,peso16);
    }
    @FXML
    public void aggAlchechengi(){
        RadioButton radioSelezionato = (RadioButton) alchechengi.getSelectedToggle();
        if (peso17.getValue() == null || radioSelezionato == null)
            immDiErrore();
        else{
            String selectedText = radioSelezionato.getText();
            aggiuntaProdotto("Alchechengi "+selectedText,9.99,peso17);
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
        aggiuntaProdotto("Alghe",66.37,peso18);
    }
    @FXML
    public void aggCavoloRapa(){
        aggiuntaProdotto("Cavolo-Rapa",3.50,peso19);
    }

    //A foglie
    @FXML
    public void aggAgretti(){
        aggiuntaProdotto("Agretti",7.98,peso20);
    }
    @FXML
    public void aggCrescione(){
        aggiuntaProdotto("Crescione",9.00,peso21);
    }
    @FXML
    public void aggCicoria(){
        RadioButton radioSelezionato = (RadioButton) cicoria.getSelectedToggle();
        if (peso22.getValue() == null || radioSelezionato == null)
            immDiErrore();
        else{
            String selectedText = radioSelezionato.getText();
            aggiuntaProdotto("Cicoria"+selectedText,3.90,peso22);
        }
    }
    //Metodo per il cambio immagine quando si seleziona il relativo radiobutton
    @FXML
    public void radCicoria(){
        RadioButton radioSelezionato = (RadioButton) cicoria.getSelectedToggle();
        String selectedText = radioSelezionato.getText();
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
        aggiuntaProdotto("Iceberg",2.90,peso23);
    }
    @FXML
    public void aggRiccia(){
        aggiuntaProdotto("Riccia ",2.90,peso24);
    }
    @FXML
    public void aggIndivia(){
        RadioButton radioSelezionato = (RadioButton) indivia.getSelectedToggle();
        if (peso25.getValue() == null || radioSelezionato == null)
            immDiErrore();
        else{
            String selectedText = radioSelezionato.getText();
            aggiuntaProdotto("Indivia "+selectedText,2.90,peso25);
        }
    }
    //Metodo per il cambio immagine quando si seleziona il relativo radiobutton
    @FXML
    public void radIndivia(){
        RadioButton radioSelezionato = (RadioButton) indivia.getSelectedToggle();
        String selectedText = radioSelezionato.getText();
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
        RadioButton radioSelezionato = (RadioButton) lattuga.getSelectedToggle();
        if (peso26.getValue() == null || radioSelezionato == null)
            immDiErrore();
        else{
            String selectedText = radioSelezionato.getText();
            aggiuntaProdotto("Lattuga "+selectedText,2.90,peso26);
        }
    }
    //Metodo per il cambio immagine quando si seleziona il relativo radiobutton
    @FXML
    public void radLattuga(){
        RadioButton radioSelezionato = (RadioButton) lattuga.getSelectedToggle();
        String selectedText = radioSelezionato.getText();
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
        aggiuntaProdotto("Portulaca ",8.30,peso27);
    }
    @FXML
    public void aggRadicchio(){
        RadioButton radioSelezionato = (RadioButton) radicchio.getSelectedToggle();
        if (peso28.getValue() == null || radioSelezionato == null)
            immDiErrore();
        else{
            String selectedText = radioSelezionato.getText();
            double mon;
            if (selectedText.equalsIgnoreCase("Di treviso") || selectedText.equalsIgnoreCase("di chioggia igp"))
                mon=2.40;
            else
                mon=5.90;
            aggiuntaProdotto("Radicchio "+selectedText,mon,peso28);
        }
    }
    //Metodo per il cambio immagine quando si seleziona il relativo radiobutton
    @FXML
    public void radRadicchio(){
        RadioButton radioSelezionato = (RadioButton) radicchio.getSelectedToggle();
        String selectedText = radioSelezionato.getText();
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
        aggiuntaProdotto("Rucola",1.70,peso29);
    }
    @FXML
    public void aggScarola(){
        aggiuntaProdotto("Scarola",1.80,peso30);
    }
    @FXML
    public void aggSpinaciDaInsalata(){
        aggiuntaProdotto("Spinaci da Insalata ",2.80,peso31);
    }
    @FXML
    public void aggValeriana(){
        aggiuntaProdotto("Valeriana",2.90,peso32);
    }
    @FXML
    public void aggCavolo(){
        RadioButton radioSelezionato = (RadioButton) cavolo.getSelectedToggle();
        if (peso33.getValue() == null || radioSelezionato == null)
            immDiErrore();
        else{
            String selectedText = radioSelezionato.getText();
            aggiuntaProdotto("Cavolo "+selectedText,2.90,peso33);
        }
    }
    //Metodo per il cambio immagine quando si seleziona il relativo radiobutton
    @FXML
    public void radCavolo(){
        RadioButton radioSelezionato = (RadioButton) cavolo.getSelectedToggle();
        String selectedText = radioSelezionato.getText();
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
        aggiuntaProdotto("Verza ",1.90,peso34);
    }
    @FXML
    public void aggCardo(){
        aggiuntaProdotto("Cardo ",3.90,peso35);
    }
    @FXML
    public void aggBietola(){
        RadioButton radioSelezionato = (RadioButton) bietola.getSelectedToggle();
        if (peso36.getValue() == null || radioSelezionato == null)
            immDiErrore();
        else{
            String selectedText = radioSelezionato.getText();
            aggiuntaProdotto("Bietola "+selectedText,4.50,peso36);
        }
    }
    //Metodo per il cambio immagine quando si seleziona il relativo radiobutton
    @FXML
    public void radBietola(){
        RadioButton radioSelezionato = (RadioButton) bietola.getSelectedToggle();
        String selectedText = radioSelezionato.getText();
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
        aggiuntaProdotto("Coste bianche ",2.50,peso37);
    }
    @FXML
    public void aggCime(){
        aggiuntaProdotto("Cime di rapa ",2.50,peso38);
    }
    @FXML
    public void aggFriarielli(){
        aggiuntaProdotto("Friarielli ",3.00,peso39);
    }

    //Tuberi
    @FXML
    public void aggZucca(){
        RadioButton radioSelezionato = (RadioButton) zucca.getSelectedToggle();
        if (peso40.getValue() == null || radioSelezionato == null)
            immDiErrore();
        else{
            String selectedText = radioSelezionato.getText();
            aggiuntaProdotto("Zucca "+selectedText,2.46,peso40);
        }
    }
    @FXML
    public void radZucca(){
        RadioButton radioSelezionato = (RadioButton) zucca.getSelectedToggle();
        String selectedText = radioSelezionato.getText();
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
        RadioButton radioSelezionato = (RadioButton) patate.getSelectedToggle();
        if (peso41.getValue() == null || radioSelezionato == null)
            immDiErrore();
        else{
            String selectedText = radioSelezionato.getText();
            double mon;
            if (selectedText.equalsIgnoreCase("normali"))
                mon=1.20;
            else
                mon=2.90;
            aggiuntaProdotto("Patate "+selectedText,mon,peso41);
        }
    }
    @FXML
    public void radPatate(){
        RadioButton radioSelezionato = (RadioButton) patate.getSelectedToggle();
        String selectedText = radioSelezionato.getText();
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
        RadioButton radioSelezionato = (RadioButton) batate.getSelectedToggle();
        if (peso42.getValue() == null || radioSelezionato == null)
            immDiErrore();
        else{
            String selectedText = radioSelezionato.getText();
            aggiuntaProdotto("Batate "+selectedText,3.90,peso42);
        }
    }
    @FXML
    public void radBatate(){
        RadioButton radioSelezionato = (RadioButton) batate.getSelectedToggle();
        String selectedText = radioSelezionato.getText();
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
        RadioButton radioSelezionato = (RadioButton) scorzo.getSelectedToggle();
        if (peso43.getValue() == null || radioSelezionato == null)
            immDiErrore();
        else{
            String selectedText = radioSelezionato.getText();
            aggiuntaProdotto("Scorzo"+selectedText,8.90,peso43);
        }
    }
    @FXML
    public void radScorzo(){
        RadioButton radioSelezionato = (RadioButton) scorzo.getSelectedToggle();
        String selectedText = radioSelezionato.getText();
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
        aggiuntaProdotto("Topinambur ",8.90,peso44);
    }

    //Cipolle
    @FXML
    public void aggCipolleBianche(){
        aggiuntaProdotto("Cipolle bianche ",2.5,peso45);
    }
    @FXML
    public void aggCipolleRosse(){
        aggiuntaProdotto("Cipolle rosse ",2.9,peso46);
    }
    @FXML
    public void aggCipolleTropea(){
        aggiuntaProdotto("Cipolle di tropea ",3.0,peso47);
    }
    @FXML
    public void aggPorri(){
        aggiuntaProdotto("Porri ",2.5,peso48);
    }
    @FXML
    public void aggCipollottiBianchi(){
        aggiuntaProdotto("Cipollotti bianchi ",2.5,peso49);
    }
    @FXML
    public void aggCipollottiRossi(){
        aggiuntaProdotto("Cipollotti rossi ",2.5,peso50);
    }
    @FXML
    public void aggCipolline(){
        aggiuntaProdotto("Cipolline ",3.2,peso51);
    }
    @FXML
    public void aggAglio(){
        aggiuntaProdotto("Aglio ",2.9,peso52);
    }
    //Pomodori
    @FXML
    public void aggCiliegino(){
        RadioButton radioSelezionato = (RadioButton) ciliegino.getSelectedToggle();
        if (peso53.getValue() == null || radioSelezionato == null)
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
            String selectedText = radioSelezionato.getText();
            aggiuntaProdotto(s+" Ciliegino "+selectedText,mon,peso53);
        }
    }
    //Metodo per il cambio immagine quando si seleziona il relativo radiobutton
    @FXML
    public void radCiliegino(){
        RadioButton radioSelezionato = (RadioButton) ciliegino.getSelectedToggle();
        String selectedText = radioSelezionato.getText();
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
        RadioButton radioSelezionato = (RadioButton) datterino.getSelectedToggle();
        if (peso54.getValue() == null || radioSelezionato == null)
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
            String selectedText = radioSelezionato.getText();
            aggiuntaProdotto(s+" Datterino "+selectedText,mon,peso54);
        }
    }
    //Metodo per il cambio immagine quando si seleziona il relativo radiobutton
    @FXML
    public void radDatterino(){
        RadioButton radioSelezionato = (RadioButton) datterino.getSelectedToggle();
        String selectedText = radioSelezionato.getText();
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
            aggiuntaProdotto(s+" Picadilly ",mon,peso55);
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
            aggiuntaProdotto(s+" Pachino ",mon,peso56);
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
            aggiuntaProdotto(s+" San Marzano ",mon,peso57);
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
            aggiuntaProdotto(s+" Costoluto ",mon,peso58);
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
            aggiuntaProdotto(s+" Cuore di Bue ",mon,peso59);
        }
    }
    @FXML
    public void aggLimoni(){
        aggiuntaProdotto("Limoni",2.19,peso60);
    }
    @FXML
    public void aggLime(){
        //Conversione del valore contenuto nella combobox da stringa a double
        aggiungiProdotto("Lime",1.2*0.2,0.2); // aggiungiamo allo scontrino
        immDiConferma(); //diamo un input visivo di ciò
    }
    @FXML
    public void aggPeperoncino(){
        //Conversione del valore contenuto nella combobox da stringa a double
        aggiungiProdotto("Peperoncini",1.2*0.1,0.1); // aggiungiamo allo scontrino
        immDiConferma(); //diamo un input visivo di ciò
    }
    @FXML
    public void aggZenzero(){
        aggiuntaProdotto("Zenzero",4.40,peso63);
    }
    @FXML
    public void aggBasilico(){
        aggiuntaProdotto("Basilico",8.9,peso64);
    }
    @FXML
    public void aggRosmarino(){
        aggiuntaProdotto("Rosmarino",6.9,peso65);
    }
    @FXML
    public void aggSalvia(){
        aggiuntaProdotto("Salvia",6.9,peso66);
    }
    @FXML
    public void aggTimo(){
        aggiuntaProdotto("Timo",6.9,peso67);
    }
    @FXML
    public void aggMenta(){
        aggiuntaProdotto("Menta",6.9,peso68);
    }

    // Metodo per aggiungere una riga
    public void aggiungiProdotto(String prodotto, double prezzo, double peso) {
        testClass.addProduct(prodotto, prezzo, peso);
    }

    //ComboBox
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
            // Cancella i valori esistenti e aggiungi nuovi valori nella checkbox
            comboBox.getItems().clear(); //Pulisco la combobox
            comboBox.getItems().addAll(1.0,2.0,3.0); //1,2,3 Kg
            double val=Double.parseDouble(labPrezzi.getText().substring(0, 3))-1.0;//Da stringa lo passo a double e cambio il prezzo ad uno più vantaggioso, per politiche aziendali esso viene diminuito di 1€
            arrotondaAlCent(val); //Arrotondiamo sempre per sicurezza
            labPrezzi.setText(val+"€/Kg"); //Modifichiamo la label per i prezzi
        } else { //Rimetto il prezzo senza cassetta
            comboBox.getItems().clear(); //Pulisco la combobox
            populateComboBox(comboBox); //Richiamo il metodo originale per il popolamento del menu a tendina
            double val=Double.parseDouble(labPrezzi.getText().substring(0, 3))+1.0; //Cambio prezzi
            arrotondaAlCent(val); //Arrotondo per sicurezza
            labPrezzi.setText(val+"€/Kg"); //Modifico la label
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
