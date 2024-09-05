# Simulazione di ordinazioni per un fruttivendolo
> ## Indice
1. Introduzione
2. Struttura dell'app
3. Indicazioni sui metodi realizzati
4. Implementazioni possibili
5. Conclusioni
> ## Introduzione
Il compito fondamentale del progetto è quello di simulare, dal punto di vista del cliente, la possibilità di ordinare il ritiro della spesa x un fruttivendolo. Ovviamente l'implementazione di ulteriori funzioni può essere multipla, ma comunque si può ritenere una base.
> ## Struttura dell'app
   L'app è strutturata su 7 diverse scene:
   - Menu : Rappresenta il punto d'ingresso sull'app dal quale si può accedere ed acedervi dovunque direttamente, ad esclusione di `Verdura`. Il proprio fxml corrispondente è `AMenu.fxml` gestito dal controller `MenuController.java`;
   - Prodotti : Rappresenta una schermata leggermente più nascosta alla quale si può accedervi dovunque cliccando sulla faccia in altro a destra oppure tramite il pulsante torna ai prodotti nella schermata carrello. Il proprio fxml corrispondente è `AProdotti.fxml` gestito dal controller `MenuController.java`;
   - Carrello : Rappresenta la schermata del carrello dalla quale vi si può accedere dovunque e la schermata conclusiva dell'ordine. Il proprio fxml corrispondente è `ACart.fxml` gestito dal controller `CartController.java`;
   - Frutta : Rappresenta la schermata in cui sono presenti tutti i prodotti sotto la categoria frutta. Il proprio fxml corrispondente è `AFrutta.fxml` gestito dal controller `FruttaController.java`;
   - Preparati : Rappresenta la schermata in cui sono presenti tutti i prodotti sotto la categoria preparati. Il proprio fxml corrispondente è `APreparati.fxml` gestito dal controller `PreparatiController.java`;
   - VerduraSimple : Rappresenta la schermata in cui sono presenti tutti i prodotti sotto la categoria verdura, ma in versione ridotta solo 4 prodotti infatti vi sono presenti, il tutto è fatto per velocizzare i caricamenti. Il proprio fxml corrispondente è `AVerduraSimple.fxml` gestito dal controller `VerduraSimpleController.java`;
   - Verdura : Rappresenta la schermata in cui sono presenti tutti i prodotti sotto la categoria verdura, divisi in più sezioni. Il proprio fxml corrispondente è `AVerdura.fxml` gestito dal controller `VerduraInsalateController.java`.
Quindi si può intuire la facilità di navigazione e di comprensione per l'utente nella navigazione sull'app.
> ## Indicazioni sui metodi realizzati
> ### MenuController.java

### Descrizione delle variabili di classe

- **`cartListView`**: una `ListView` che contiene gli elementi del carrello, rappresentati come oggetti `HBox`.
- **`totalLabel`**: un `Label` che mostra il totale del carrello.
- **`bottOk`**: un `Button` che cambia colore temporaneamente quando un prodotto viene rimosso dal carrello.

### Descrizione dei metodi del codice Java

1. **`initialize()`**
   - **Descrizione**: Questo metodo viene chiamato automaticamente all'apertura della scena (view). Il suo scopo è aggiornare la sezione del carrello chiamando il metodo `aggiornaSezCarrello()`.
   - **Variabili**: Non utilizza variabili locali.

2. **`aggiornaSezCarrello()`**
   - **Descrizione**: Aggiorna la ListView che mostra i prodotti presenti nel carrello, i loro prezzi e pesi, e calcola il totale della spesa. Inoltre, consente di rimuovere i prodotti dal carrello.
   - **Variabili**:
     - `total`: variabile di tipo `double` che tiene traccia del totale della spesa.
     - `prodotti`, `prezzi`, `pesi`: `ArrayList` che contengono i nomi dei prodotti, i prezzi e i pesi degli articoli nel carrello.
     - `s`: stringa utilizzata per determinare l'unità di misura del prodotto (es. "pezzi", "kg", "mazzi").
     - `hbox`: oggetto di tipo `HBox` che contiene la descrizione del prodotto e un pulsante per la rimozione.
     - `prodottoLabel`: etichetta (`Label`) che mostra il nome del prodotto, il suo peso, l'unità di misura e il prezzo.
     - `removeButton`: pulsante (`Button`) che consente di rimuovere il prodotto dal carrello.
     - `index`: variabile `int` che memorizza l'indice corrente del prodotto nell'ArrayList, usata per cancellarlo.

3. **`arrotondaAlCent(double value)`**
   - **Descrizione**: Arrotonda un valore in virgola mobile a due cifre decimali.
   - **Variabili**:
     - `bd`: oggetto di tipo `BigDecimal` che rappresenta il valore da arrotondare.

4. **`switchToProductsView(MouseEvent mouseEvent)`**
   - **Descrizione**: Cambia la scena corrente alla vista che mostra la divisione intermedia dei prodotti. È associato a un evento di click del mouse.
   - **Variabili**:
     - `fxmlLoader`: carica il file FXML della nuova vista.
     - `scene`: oggetto `Scene` che rappresenta la nuova scena.
     - `stage`: oggetto `Stage` che rappresenta la finestra attuale, dove viene caricata la nuova scena.

5. **`switchToCart(MouseEvent mouseEvent)`**
   - **Descrizione**: Cambia la scena corrente alla vista del carrello.
   - **Variabili**:
     - Come nel metodo precedente (`switchToProductsView`), utilizza `fxmlLoader`, `scene` e `stage` per caricare e mostrare la nuova scena.

6. **`switchToFrutta(ActionEvent event)`**
   - **Descrizione**: Cambia la scena alla vista che mostra la sezione "Frutta".
   - **Variabili**:
     - Come nel metodo precedente, utilizza `fxmlLoader`, `scene` e `stage`.

7. **`switchToVerdura(ActionEvent event)`**
   - **Descrizione**: Cambia la scena alla vista che mostra la sezione "Verdura ridotta".
   - **Variabili**:
     - Come nei metodi precedenti, utilizza `fxmlLoader`, `scene` e `stage`.

8. **`switchToPreparati(ActionEvent event)`**
   - **Descrizione**: Cambia la scena alla vista che mostra la sezione "Preparati".
   - **Variabili**:
     - Come nei metodi precedenti, utilizza `fxmlLoader`, `scene` e `stage`.

9. **`switchToMenu(ActionEvent event)`**
   - **Descrizione**: Cambia la scena alla vista del menu principale.
   - **Variabili**:
     - Come nei metodi precedenti, utilizza `fxmlLoader`, `scene` e `stage`.










> ### FruttaController.java

### Descrizione delle variabili di classe

- **`peso0, peso1, ..., peso14`**: `ComboBox<Double>` che permettono all'utente di selezionare il peso di 15 diversi prodotti.
- **`bottOk`**: `Button` per confermare l'aggiunta dei prodotti nel carrello.
- **`cartListView`**: `ListView<HBox>` che visualizza il contenuto del carrello, includendo le informazioni sui prodotti selezionati.
- **`totalLabel`**: `Label` che mostra il totale del carrello.


### Metodi principali:

#### 1. `initialize()`
- **Descrizione**: Questo metodo viene chiamato automaticamente all'inizializzazione della scena. Aggiorna la sezione del carrello e inizializza i combo box per selezionare i pesi dei prodotti.
- **Variabili**: Non usa variabili locali ma richiama i metodi `aggiornaSezCarrello()` e `populateComboBox()`.

#### 2. `aggiornaSezCarrello()`
- **Descrizione**: Questo metodo aggiorna il contenuto della `cartListView`, cancellando eventuali elementi precedenti e aggiungendo i prodotti presenti nel carrello.
- **Variabili**:
  - `total`: Tiene traccia del totale del carrello.
  - `prodotti`: ArrayList che contiene i nomi dei prodotti nel carrello.
  - `prezzi`: ArrayList con i prezzi dei prodotti.
  - `pesi`: ArrayList con i pesi selezionati per i prodotti.
  - `s`: Una stringa utilizzata per formattare la descrizione dei prodotti.
  - `hbox`: Un `HBox` che contiene il pulsante di rimozione e la descrizione del prodotto.
  - `prodottoLabel`: Label che contiene le informazioni sul prodotto.
  - `removeButton`: Un pulsante per rimuovere l'elemento dal carrello.
  - `index`: Variabile necessaria per gestire correttamente l'indice dell'elemento da rimuovere.

#### 3. `arrotondaAlCent(double value)`
- **Descrizione**: Arrotonda un valore numerico al centesimo (due decimali) usando `BigDecimal`.
- **Variabili**:
  - `bd`: Un oggetto `BigDecimal` usato per effettuare l'arrotondamento.

#### 4. `immDiConferma()`
- **Descrizione**: Cambia il colore del bottone `bottOk` per indicare la conferma dell'aggiunta di un prodotto al carrello.
- **Variabili**:
  - `pause`: Una pausa di 2 secondi prima di riportare il colore del bottone allo stato iniziale.

#### 5. `immDiErrore()`
- **Descrizione**: Cambia il colore di `bottOk` in rosso per indicare un errore, come quando non è selezionato un peso. Simile a `immDiConferma()` ma per gli errori.
- **Variabili**: Simile a `immDiConferma`.

#### 6. `aggiuntaProdotto(String prodotto, double prezzoAlKg, ComboBox<Double> combo)`
- **Descrizione**: Aggiunge un prodotto al carrello. Controlla se il peso è selezionato, altrimenti segnala un errore. Se tutto è corretto, calcola il prezzo totale (peso * prezzoAlKg) e lo aggiunge al carrello.
- **Variabili**:
  - `peso`: Il peso selezionato dall'utente.
  - `combo`: Il `ComboBox` da cui viene selezionato il peso del prodotto.

#### 7. `aggMele()`, `aggPere()`, ..., `aggPrugne()`
- **Descrizione**: Metodi specifici per aggiungere prodotti come mele, pere, banane, ecc. al carrello, ognuno con il proprio prezzo al kg.
- **Variabili**: Chiamano `aggiuntaProdotto()` con il nome del prodotto, il prezzo al kg e il rispettivo `ComboBox`.

#### 8. `aggiungiProdotto(String prodotto, double prezzo, double peso)`
- **Descrizione**: Aggiunge un prodotto al carrello, interfacciandosi con la classe `ShoppingCart`.
- **Variabili**:
  - `prodotto`: Il nome del prodotto.
  - `prezzo`: Il prezzo totale del prodotto.
  - `peso`: Il peso selezionato.

#### 9. `populateComboBox(ComboBox<Double> peso)`
- **Descrizione**: Popola un `ComboBox` con una lista di valori di peso, che va da 5.00 kg a 0.10 kg, con decrementi di 0.05.
- **Variabili**:
  - `i`: Rappresenta i valori del peso che vengono aggiunti al `ComboBox`.

---

### Metodi per il cambio scena:

I metodi seguenti cambiano la scena visualizzata nell'interfaccia:
- **switchToProductsView(MouseEvent mouseEvent)**: Passa alla schermata dei prodotti.
- **switchToCart(MouseEvent mouseEvent)**: Passa alla schermata del carrello.
- **switchToFrutta(ActionEvent event)**: Passa alla schermata dei prodotti di frutta.
- **switchToVerduraSimple(ActionEvent event)**: Passa alla schermata dei prodotti di verdura.
- **switchToPreparati(ActionEvent event)**: Passa alla schermata dei prodotti preparati.
- **switchToMenu(ActionEvent event)**: Torna al menu principale.

In ciascuno di questi metodi, vengono utilizzate variabili come `fxmlLoader`, `scene` e `stage` per caricare e impostare la nuova scena.

---

### Funzionamento globale:
- L'applicazione gestisce un carrello della spesa virtuale, permettendo agli utenti di selezionare i pesi per diversi prodotti e visualizzare l'importo totale.
- I prodotti possono essere aggiunti o rimossi dal carrello, e la visualizzazione del carrello viene aggiornata automaticamente.
- Il cambio delle scene consente agli utenti di navigare tra diverse categorie di prodotti.
