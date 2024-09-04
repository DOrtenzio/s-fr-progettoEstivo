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
import java.time.DayOfWeek;
import java.util.ArrayList;
import javafx.scene.control.DatePicker;

import javax.mail.MessagingException;
import java.time.LocalDate;
import java.time.Month;
import java.util.Random;
import java.util.regex.Pattern;

public class cartController {
    @FXML
    private ListView<HBox> cartListView;
    @FXML
    private Label totalLabel;
    @FXML
    private Button bottOk,bottConcludi;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ComboBox<String> timeComboBox;
    @FXML
    private  TextField nomeField,emailField,emailVerificationField;
    private int [] contEmail={0,0,0};

    @FXML
    public void initialize() {
        aggiornaSezCarrello();
        // Popola il ComboBox con gli orari disponibili
        for (int hour = 9; hour <= 19; hour++) {
            for (int minute = 0; minute < 60; minute += 30) {
                // Calcola i minuti finali e l'ora finale
                int endMinute = minute + 30;
                int endHour = hour;

                // Se i minuti finali superano 59, dobbiamo incrementare l'ora
                if (endMinute == 60) {
                    endMinute = 0;
                    endHour++;
                }

                // Formatta e aggiungi l'intervallo di tempo alla combobox
                timeComboBox.getItems().add(String.format("%02d:%02d - %02d:%02d", hour, minute, endHour, endMinute));
            }
        }

    }
    //Concludiamo
    @FXML
    public void concludi() throws MessagingException {
        ShoppingCart cart = ShoppingCart.getInstance();
        LocalDate selectedDate = datePicker.getValue(); //Data
        ArrayList<String> prodotti = cart.getProdotti();
        if (isValidDate(datePicker) && isEmailValida(emailField.getText()) && isNomeValido(nomeField.getText()) && !prodotti.isEmpty() && contEmail[1]==1){
            invioEmail.sendMail("fruttaealtro.orderemail@gmail.com", "fruttaealtro.orderemail@gmail.com", selectedDate+" / "+timeComboBox.getValue(),stampaScontrinoEmail());
            invioEmail.sendMail(emailField.getText(), "fruttaealtro.orderemail@gmail.com", "Riepilogo ordine Frutta e Altro ","Grazie per il tuo ordine!\n"+"\n\nEcco i tuoi prodotti:\n "+stampaScontrinoEmail()+"\n\nQuesta email non ha valevolenza legale.");
            cartListView.getItems().clear(); // Pulisco tutti gli items nella lista a schermo
            cart.clear();
            totalLabel.setText("0.0€");

            HBox hbox = new HBox(); // Creiamo un HBox per contenere il testo
            Label labelinfo = new Label("Grazie per il tuo ordine!\nControlla la tua mail per il tuo promemoria.");
            labelinfo.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-alignment: center-left; -fx-font-family: Cambria; -fx-font-size: 18px;");
            // Aggiungiamo il Label all'HBox
            HBox.setHgrow(labelinfo, Priority.ALWAYS); // Consente al Label di occupare tutto lo spazio disponibile
            hbox.getChildren().add(labelinfo);
            cartListView.getItems().add(hbox);
        }else {
            //Metodo immErr di Fruttacontroller modifica per bottConcludi
            bottConcludi.setStyle("-fx-background-color: #E11518;");
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(event -> bottConcludi.setStyle("-fx-background-color: #828282;")); //Risistemo il colore
            pause.play();
        }
    }
    @FXML
    public String stampaScontrinoEmail() {
        LocalDate selectedDate = datePicker.getValue(); //Data
        ShoppingCart cart = ShoppingCart.getInstance(); // Istanza carrello attuale
        double total = 0.0; // Rifaccio il conto

        ArrayList<String> prodotti = cart.getProdotti();
        ArrayList<Double> prezzi = cart.getPrezzi();
        ArrayList<Double> pesi = cart.getPesi();
        String s,l="Ordine di "+nomeField.getText()+"; contatti: "+emailField.getText()+"; per data: "+selectedDate+" per ora: "+timeComboBox.getValue()+" .\n";

        for (int i = 0; i < prodotti.size(); i++) {
            if (prodotti.get(i).equalsIgnoreCase("carciofi normali") || prodotti.get(i).equalsIgnoreCase("carciofi romani") || prodotti.get(i).charAt(0) == '▸')
                s=" pezzi - ";
            else {
                s=" kg - ";
            }
            l=l+prodotti.get(i) + " - " + arrotondaAlCent(pesi.get(i)) + s + prezzi.get(i) + " €"+"\n";
            total += prezzi.get(i); //Aggiorno il totale
        }
        total = arrotondaAlCent(total);
        l=l+"Totale: "+total+" €";
        return l;
    }
    @FXML
    public void aggiornaSezCarrello() {
        ShoppingCart cart = ShoppingCart.getInstance(); // Istanza carrello attuale
        cartListView.getItems().clear(); // Pulisco tutti gli items nella lista a schermo
        double total = 0.0; // Rifaccio il conto

        ArrayList<String> prodotti = cart.getProdotti();
        ArrayList<Double> prezzi = cart.getPrezzi();
        ArrayList<Double> pesi = cart.getPesi();

        if (prodotti.isEmpty()){
            HBox hbox = new HBox(); // Creiamo un HBox per contenere il testo
            Label labelinfo = new Label("Ciao sono carrello, mi sento vuoto dentro:\nAnzi lo sono-riempimi prima di ordinare,\n così non perdi tempo!");
            labelinfo.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-alignment: center-left; -fx-font-family: Cambria; -fx-font-size: 18px;");
            HBox hbox2 = new HBox(); // Creiamo un HBox per contenere l'immagine
            // Crea l'ImageView
            ImageView imageView = new ImageView();
            imageView.setFitWidth(300);  // Imposta la larghezza dell'immagine
            imageView.setFitHeight(300); // Imposta l'altezza dell'immagine
            imageView.setPreserveRatio(true); // Mantiene il rapporto d'aspetto dell'immagine

            // Aggiungiamo il Label all'HBox
            HBox.setHgrow(labelinfo, Priority.ALWAYS); // Consente al Label di occupare tutto lo spazio disponibile
            hbox.getChildren().add(labelinfo);
            cartListView.getItems().add(hbox);

            // Aggiungiamo l'immagine all'HBox
            HBox.setHgrow(imageView, Priority.ALWAYS); // Consente al Label di occupare tutto lo spazio disponibile
            hbox2.getChildren().add(imageView);
            cartListView.getItems().add(hbox2);

            // Crea un nuovo Image e lo imposta sull'ImageView
            Image image = new Image(getClass().getResourceAsStream("/com/example/demo1/img/cart.png")); // Assicurati che il percorso dell'immagine sia corretto
            imageView.setImage(image);
        } else {
            String s;
            for (int i = 0; i < prodotti.size(); i++) {
                HBox hbox = new HBox(); // Creiamo un HBox per contenere il testo e il pulsante

                // Creiamo un Label per mostrare le informazioni del prodotto
                if (prodotti.get(i).equalsIgnoreCase("carciofi normali") || prodotti.get(i).equalsIgnoreCase("carciofi romani") || prodotti.get(i).charAt(0) == '▸')
                    s = " pezzi - ";
                else if (prodotti.get(i).equalsIgnoreCase("ravanelli"))
                    s = " mazzi - ";
                else
                    s = " kg - ";
                Label prodottoLabel = new Label("     " + prodotti.get(i) + " - " + arrotondaAlCent(pesi.get(i)) + s + prezzi.get(i) + " €");
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
                hbox.getChildren().addAll(removeButton, prodottoLabel);
                cartListView.getItems().add(hbox);

                total += prezzi.get(i); //Aggiorno il totale
            }

            total = arrotondaAlCent(total);
            totalLabel.setText(total + " €");
        }
    }
    //COntrolli
    //Email
    @FXML
    public void autenticazioneEmail() throws MessagingException {
        if (isEmailValida(emailField.getText())){
            int i=genPasswordTemp();
            contEmail[2]=i;
            invioEmail.sendMail(emailField.getText(), "fruttaealtro.orderemail@gmail.com", "Verifica email","La tua password temporanea di questo ordine è: "+i);
            contEmail[0]=1;
        }else {
            //Metodo immErr di Fruttacontroller modifica per bottConcludi
            bottConcludi.setStyle("-fx-background-color: #E11518;");
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(event -> bottConcludi.setStyle("-fx-background-color: #828282;")); //Risistemo il colore
            pause.play();

        }
    }
    @FXML
    public void verificaAutenticazione (){
        if (contEmail[0]==1){
            if (contEmail[2]==Integer.parseInt(emailVerificationField.getText())){
                contEmail[1]=1;
                bottConcludi.setStyle("-fx-background-color: #76DD4D;");
                PauseTransition pause = new PauseTransition(Duration.seconds(3));
                pause.setOnFinished(event -> bottConcludi.setStyle("-fx-background-color: #828282;")); //Risistemo il testo
                pause.play();
            }
        }else {
            //Metodo immErr di Fruttacontroller modifica per bottConcludi
            bottConcludi.setStyle("-fx-background-color: #E11518;");
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(event -> bottConcludi.setStyle("-fx-background-color: #828282;")); //Risistemo il colore
            pause.play();
        }
    }
    public int genPasswordTemp(){
        Random generator = new Random();
        return generator.nextInt(999999);
    }
    //Data
    public boolean isValidDate(DatePicker datePicker) {
        if (datePicker.getValue() == null) {
            // Data non selezionata
            return false;
        }

        LocalDate selectedDate = datePicker.getValue(); //Data selezionata
        LocalDate currentDate = LocalDate.now(); //data di oggi
        // Capodanno
        LocalDate capodanno = LocalDate.of(currentDate.getYear(), Month.JANUARY, 1);
        // Epifania
        LocalDate epifania = LocalDate.of(currentDate.getYear(), Month.JANUARY, 6);
        // Pasqua e Pasquetta
        LocalDate pasqua = getPasqua(currentDate.getYear());
        LocalDate pasquetta=pasqua.plusDays(1);
        // Festa della Liberazione
        LocalDate festaLiberazione = LocalDate.of(currentDate.getYear(), Month.APRIL, 25);
        // Festa del Lavoro
        LocalDate festaLavoro = LocalDate.of(currentDate.getYear(), Month.MAY, 1);
        // Festa della Repubblica
        LocalDate festaRepubblica = LocalDate.of(currentDate.getYear(), Month.JUNE, 2);
        // Assunzione di Maria Vergine
        LocalDate assunzione = LocalDate.of(currentDate.getYear(), Month.AUGUST, 15);
        // Ognissanti
        LocalDate ognissanti = LocalDate.of(currentDate.getYear(), Month.NOVEMBER, 1);
        // Immacolata Concezione
        LocalDate immacolata = LocalDate.of(currentDate.getYear(), Month.DECEMBER, 8);
        // Natale
        LocalDate natale = LocalDate.of(currentDate.getYear(), Month.DECEMBER, 25);
        // Santo Stefano
        LocalDate santoStefano = LocalDate.of(currentDate.getYear(), Month.DECEMBER, 26);
        // Verifica se la data selezionata è lo stesso giorno o precedente a quello corrente
        if (selectedDate.isBefore(currentDate) || selectedDate.isEqual(currentDate) || selectedDate.getDayOfWeek().equals(DayOfWeek.MONDAY)) {
            return false;
        }
        // Verifica se la data selezionata è festa
        if (selectedDate.equals(capodanno) || selectedDate.equals(epifania) || selectedDate.equals(pasqua) || selectedDate.equals(pasquetta) || selectedDate.equals(festaLiberazione)|| selectedDate.equals(festaLavoro)|| selectedDate.equals(festaRepubblica) || selectedDate.equals(assunzione)|| selectedDate.equals(ognissanti)|| selectedDate.equals(immacolata)|| selectedDate.equals(natale)|| selectedDate.equals(santoStefano)) {
            return false;
        }
        return true;
    }
    public static LocalDate getPasqua(int year) {
        //Algoritmo di Meeus/Jones/Butcher
        int a = year % 19;
        int b = year / 100;
        int c = year % 100;
        int d = b / 4;
        int e = b % 4;
        int f = (b + 8) / 25;
        int g = (b - f + 1) / 3;
        int h = (19 * a + b - d - g + 15) % 30;
        int i = c / 4;
        int k = c % 4;
        int l = (32 + 2 * e + 2 * i - h - k) % 7;
        int m = (a + 11 * h + 22 * l) / 451;
        int month = (h + l - 7 * m + 114) / 31;
        int day = ((h + l - 7 * m + 114) % 31) + 1;

        return LocalDate.of(year, month, day);
    }
    //Nome
    // Metodo per verificare se il nome è valido
    public static boolean isNomeValido(String nome) {
        // Definisce il pattern per un nome valido: solo lettere e apostrofi
        String pattern = "^[a-zA-Z'àèéìòùÀÈÉÌÒÙ]+( [a-zA-Z'àèéìòùÀÈÉÌÒÙ]+)*$";
        // Compila il pattern
        Pattern compiledPattern = Pattern.compile(pattern);
        // Verifica se il nome corrisponde al pattern
        return compiledPattern.matcher(nome).matches();
    }
    //Email
    // Metodo per verificare se l'email è valida
    public static boolean isEmailValida(String email) {
        // Definisce il pattern per una email valida
        String pattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        // Compila il pattern
        Pattern compiledPattern = Pattern.compile(pattern);
        // Verifica se l'email corrisponde al pattern
        return compiledPattern.matcher(email).matches();
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