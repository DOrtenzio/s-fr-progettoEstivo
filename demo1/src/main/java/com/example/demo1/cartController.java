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
    private int [] contEmail={0,0,0}; //Metodo di controllo per la validità di un Email, sì si poteva fare meglio

    //Metodo per aggiornare il carrello a ogni "apertura" della scena e inizializzazione orari e date
    @FXML
    public void initialize() {
        aggiornaSezCarrello(); //Aggiorno il carrello
        populateOrariBox(timeComboBox); // Popola il ComboBox con gli orari disponibili per il ritiro
    }

    //Fase per concludere la prenotazione
    @FXML
    public void concludi() throws MessagingException { //Richiesto in invioEmail.java (vedi anche lì)
        LocalDate selectedDate = datePicker.getValue(); //Ricavo la data selezionata nel DatePicker dall'utente
        if (noProblemi()){ //Controllo se non ci sono problemi
            //Mi auto-invio un email riepilogo
            invioEmail.sendMail("fruttaealtro.orderemail@gmail.com", "fruttaealtro.orderemail@gmail.com", selectedDate+" / "+timeComboBox.getValue(),stampaScontrinoEmail(selectedDate)); //L'oggetto sarà 22-01-2024 / 9:30-10:00 (ad esempio ovviamente)
            //Invio un'email riepilogo al cliente con quella indicatomi e verificata
            invioEmail.sendMail(emailField.getText(), "fruttaealtro.orderemail@gmail.com", "Riepilogo ordine Frutta e Altro ","Grazie per il tuo ordine!\n"+"\n\nEcco i tuoi prodotti:\n "+stampaScontrinoEmail(selectedDate)+"\n\nQuesta email non ha valevolenza legale.");
            cartListView.getItems().clear(); // Ripulisco il carrello a schermo
            ShoppingCart.getInstance().clear(); //ma anche a livello di salvataggio
            totalLabel.setText("0.00€"); //Rimetto 0€ il totale
            //Ringrazio il cliente che ha ordinato, perchè l'educazione sempre prima di tutto, no ?
            HBox hbox = new HBox(); // Creiamo un HBox per contenere il testo
            Label labelinfo = new Label("Grazie per il tuo ordine!\nControlla la tua mail per il tuo promemoria.");
            labelinfo.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-alignment: center-left; -fx-font-family: Cambria; -fx-font-size: 18px;"); //Gli cambio temporaneamente anche lo stile grafico
            // Aggiungiamo il Label all'HBox
            HBox.setHgrow(labelinfo, Priority.ALWAYS); // Consente al Label di occupare tutto lo spazio disponibile
            hbox.getChildren().add(labelinfo);
            //Poi al listview
            cartListView.getItems().add(hbox);
        }else {
            //Metodo immErr di FruttaController, semplicemente un cambio colore per 2 secondi
            bottOk.setStyle("-fx-background-color: #E11518;"); //Rosso
            PauseTransition pause = new PauseTransition(Duration.seconds(2)); //Grigino
            pause.setOnFinished(event -> bottOk.setStyle("-fx-background-color: #828282;")); //Risistemo il colore
            pause.play();
        }
    }
    //Stampo la lista dei prodotti susseguita dal totale
    @FXML
    public String stampaScontrinoEmail(LocalDate selectedDate) {
        double total = 0.0; // Rifaccio il conto
        //ArrayList per prodotti, prezzi e quantità
        ArrayList<String> prodotti = ShoppingCart.getInstance().getProdotti();
        ArrayList<Double> prezzi = ShoppingCart.getInstance().getPrezzi();
        ArrayList<Double> pesi = ShoppingCart.getInstance().getPesi();
        String singoloProdotto,scontrinoIntero="Ordine di "+nomeField.getText()+"; contatti: "+emailField.getText()+"; per data: "+selectedDate+" per ora: "+timeComboBox.getValue()+" .\n";
        //singoloProdotto = singolo prodotto con prezzo e quantità, scontrinoIntero = scontrino intero che si stamperà
        for (int i = 0; i < prodotti.size(); i++) { //i= indice che scorre nell'array list
            if (prodotti.get(i).equalsIgnoreCase("carciofi normali") || prodotti.get(i).equalsIgnoreCase("carciofi romani") || prodotti.get(i).charAt(0) == '▸')
                singoloProdotto=" pezzi - ";
            else {
                singoloProdotto=" kg - ";
            }
            scontrinoIntero=scontrinoIntero+prodotti.get(i) + " - " + arrotondaAlCent(pesi.get(i)) + singoloProdotto + prezzi.get(i) + " €"+"\n";
            total += prezzi.get(i); //Aggiorno il totale
        }
        total = arrotondaAlCent(total);
        scontrinoIntero=scontrinoIntero+"Totale: "+total+" €";
        return scontrinoIntero;
    }
    @FXML
    public void aggiornaSezCarrello() {
        cartListView.getItems().clear(); // Pulisco tutti gli items nella lista a schermo
        double total = 0.0; // Rifaccio il conto

        ArrayList<String> prodotti = ShoppingCart.getInstance().getProdotti();
        ArrayList<Double> prezzi = ShoppingCart.getInstance().getPrezzi();
        ArrayList<Double> pesi = ShoppingCart.getInstance().getPesi();

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
                    ShoppingCart.getInstance().removeProduct(index);
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

    //Controlli
    //Controllo se ci sono mancanze da parte dell'utenza
    @FXML
    private boolean noProblemi(){
        ArrayList<String> prodotti = ShoppingCart.getInstance().getProdotti(); //ArrayList con i prodotti dell'utente
        //Se la data è valida (Vedi metodo sotto per capire meglio), se lo è l'email (Vedi sotto anche se un pò inutile qui ma un doppio controllo è sempre meglio), se lo è il nome (Vedi sotto), se l'ordine non è vuoto e se l'email è stata verificats
        if (isValidDate(datePicker) && isEmailValida(emailField.getText()) && isNomeValido(nomeField.getText()) && !prodotti.isEmpty() && contEmail[1]==1)
            return true;
        else
            return false;
    }
    //Email
    @FXML
    public void autenticazioneEmail() throws MessagingException { //Metodo per l'autenticazione dell'email (Invio email con password)
        if (isEmailValida(emailField.getText())){
            //Generò una password temporanea che salvò nell'array perchè mi servirà anche dopo
            contEmail[2]=genPasswordTemp();
            invioEmail.sendMail(emailField.getText(), "fruttaealtro.orderemail@gmail.com", "Verifica email","La tua password temporanea di questo ordine è: "+contEmail[2]);
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
    public void verificaAutenticazione (){ //Metodo per l'autenticazione dell'email (Controllo password inserita)
        if (contEmail[0]==1){
            if (contEmail[2]==Integer.parseInt(emailVerificationField.getText())){ //Converto da stringa a intero
                contEmail[1]=1; //Verificato
                bottConcludi.setStyle("-fx-background-color: #76DD4D;"); //Verde di ok
                PauseTransition pause = new PauseTransition(Duration.seconds(3));//Grigino
                pause.setOnFinished(event -> bottConcludi.setStyle("-fx-background-color: #828282;")); //Risistemo il testo
                pause.play();
            }
        }else {
            //Metodo immErr di Fruttacontroller modifica per bottConcludi
            bottConcludi.setStyle("-fx-background-color: #E11518;");//Rosso per non ok
            PauseTransition pause = new PauseTransition(Duration.seconds(3));//Grigino
            pause.setOnFinished(event -> bottConcludi.setStyle("-fx-background-color: #828282;")); //Risistemo il colore
            pause.play();
        }
    }
    public int genPasswordTemp(){ //Password a 6 cifre intere
        Random generator = new Random();
        return generator.nextInt(999999);
    }
    //Data è valida, cioè rientra nei giorni lavorativi o non è festa
    public boolean isValidDate(DatePicker datePicker) {
        LocalDate selectedDate = datePicker.getValue(); //Data selezionata

        //Controlli
        if (datePicker.getValue() == null) { //È vuota
            // Data non selezionata
            return false;
        }

        //Feste ricorrenti sempre con la stessa data

        LocalDate currentDate = LocalDate.now(); //data di oggi, infatti non puoi ordinare oggi,per oggi, dai un pò di rispetto
        // Capodanno
        LocalDate capodanno = LocalDate.of(currentDate.getYear(), Month.JANUARY, 1); //3..2..1.. Buon anno!
        // Epifania
        LocalDate epifania = LocalDate.of(currentDate.getYear(), Month.JANUARY, 6); //La befana tutte le feste porta via
        // Pasqua e Pasquetta
        LocalDate pasqua = getPasqua(currentDate.getYear()); //Tanto tra tutta la roba da mangiare non ti serve la verdura
        LocalDate pasquetta=pasqua.plusDays(1); //Facciamo anche noi la grigliata
        // Festa della Liberazione
        LocalDate festaLiberazione = LocalDate.of(currentDate.getYear(), Month.APRIL, 25);
        // Festa del Lavoro
        LocalDate festaLavoro = LocalDate.of(currentDate.getYear(), Month.MAY, 1);
        // Festa della Repubblica
        LocalDate festaRepubblica = LocalDate.of(currentDate.getYear(), Month.JUNE, 2);
        // Ferragosto
        LocalDate ferragosto = LocalDate.of(currentDate.getYear(), Month.AUGUST, 15); //È anche patrono di Curno, Assunzione della Beata Vergine
        // Ognissanti
        LocalDate ognissanti = LocalDate.of(currentDate.getYear(), Month.NOVEMBER, 1);
        // Immacolata Concezione
        LocalDate immacolata = LocalDate.of(currentDate.getYear(), Month.DECEMBER, 8);
        // Natale
        LocalDate natale = LocalDate.of(currentDate.getYear(), Month.DECEMBER, 25); //Buon natale!
        // Santo Stefano
        LocalDate santoStefano = LocalDate.of(currentDate.getYear(), Month.DECEMBER, 26); //Dobbiamo digerire
        // Verifica se la data selezionata è lo stesso giorno o precedente a quello corrente
        if (selectedDate.isBefore(currentDate) || selectedDate.isEqual(currentDate) || selectedDate.getDayOfWeek().equals(DayOfWeek.MONDAY)) {
            return false;
        }
        // Verifica se la data selezionata è festa
        if (selectedDate.equals(capodanno) || selectedDate.equals(epifania) || selectedDate.equals(pasqua) || selectedDate.equals(pasquetta) || selectedDate.equals(festaLiberazione)|| selectedDate.equals(festaLavoro)|| selectedDate.equals(festaRepubblica) || selectedDate.equals(ferragosto)|| selectedDate.equals(ognissanti)|| selectedDate.equals(immacolata)|| selectedDate.equals(natale)|| selectedDate.equals(santoStefano)) {
            return false;
        }
        return true;
    }
    public static LocalDate getPasqua(int year) { //Ovviamente preso da internet

        // Algoritmo di Meeus/Jones/Butcher per calcolare la data della Pasqua
        // 'a' è il numero dell'anno nel ciclo metonico di 19 anni
        int a = year % 19;
        // 'b' è il secolo dell'anno (divisione intera dell'anno per 100)
        int b = year / 100;
        // 'c' è l'anno all'interno del secolo (resto della divisione dell'anno per 100)
        int c = year % 100;
        // 'd' è il numero di anni bisestili nel secolo fino a quell'anno (divisione intera di 'b' per 4)
        int d = b / 4;
        // 'e' è il resto della divisione di 'b' per 4
        int e = b % 4;
        // 'f' è un aggiustamento legato alla correzione del calendario gregoriano
        int f = (b + 8) / 25;
        // 'g' è un altro aggiustamento del calendario gregoriano
        int g = (b - f + 1) / 3;
        // 'h' determina il giorno del plenilunio pasquale in termini di giorni nel ciclo lunare
        int h = (19 * a + b - d - g + 15) % 30;
        // 'i' è il numero di anni bisestili negli ultimi 100 anni (divisione intera di 'c' per 4)
        int i = c / 4;
        // 'k' è il resto della divisione di 'c' per 4
        int k = c % 4;
        // 'l' aiuta a calcolare il giorno della settimana del plenilunio pasquale
        int l = (32 + 2 * e + 2 * i - h - k) % 7;
        // 'm' serve a determinare se il plenilunio coincide o supera la data limite del 21 marzo
        int m = (a + 11 * h + 22 * l) / 451;
        // Il mese della Pasqua (marzo = 3, aprile = 4)
        int month = (h + l - 7 * m + 114) / 31;
        // Il giorno della Pasqua (tra il 22 marzo e il 25 aprile)
        int day = ((h + l - 7 * m + 114) % 31) + 1;

        // Ritorna la data della Pasqua come oggetto LocalDate
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
    //Classe per il popolamento dell'orario combobox
    @FXML
    public void populateOrariBox (ComboBox<String> combobox){
        for (int ore = 9; ore <= 19; ore++) { //Questo ciclo for imposta un contatore chiamato ore che rappresenta le ore del giorno. Parte da 9 (9:00 del mattino) e continua fino a 19 (7:00 di sera).
            // A ogni iterazione, il valore di ore viene incrementato di un ora
            for (int minuti = 0; minuti < 60; minuti += 30) {
                //Imposta un contatore minuti che rappresenta i minuti dell'ora corrente. Inizia a 0 e aumenta di 30 a ogni iterazione (quindi si occupa di intervalli di 30 minuti).
                // Il ciclo si interrompe quando minute è uguale o superiore a 60, coprendo così due intervalli per ogni ora: 00 e 30.

                // Calcola i minuti finali e l'ora finale
                int endMinute = minuti + 30; //calcola i minuti di fine intervallo aggiungendo 30 ai minuti iniziali.
                int endHour = ore;

                // Se i minuti finali superano 59, dobbiamo incrementare l'ora
                if (endMinute == 60) { //Questa condizione verifica se endMinute è uguale a 60, il che significa che abbiamo completato un'ora piena (ad esempio, passando da 9:30 a 10:00)
                    endMinute = 0; //viene impostato a 0 (che rappresenta l'inizio della nuova ora)
                    endHour++; // viene incrementato di 1 per rappresentare l'ora successiva
                }

                // Formatta e aggiungi l'intervallo di tempo alla combobox
                combobox.getItems().add(String.format("%02d:%02d - %02d:%02d", ore, minuti, endHour, endMinute));
                //%02d garantisce che l'ora e i minuti siano sempre rappresentati con due cifre (es: "09" anziché "9").
            }
        }
        //In sintesi, genera tutti gli intervalli di 30 minuti tra le 9:00 e le 19:00 e li aggiunge a una combobox per la selezione in un'interfaccia utente.
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