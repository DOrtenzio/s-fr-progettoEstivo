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
   - **Descrizione**: Aggiorna la `ListView` che mostra i prodotti presenti nel carrello, insieme ai loro prezzi e pesi, e ricalcola il totale della spesa. Permette inoltre la rimozione dei prodotti dal carrello e aggiorna automaticamente la visualizzazione dopo ogni rimozione.

   - **Variabili**:
       - **`total`**: Variabile di tipo `double` che tiene traccia del totale della spesa, inizializzata a 0.
       - **`prodotti`**, **`prezzi`**, **`pesi`**: Tre `ArrayList` che contengono rispettivamente i nomi dei prodotti, i prezzi e i pesi degli articoli nel carrello. Questi array vengono recuperati tramite i metodi statici di `testClass`.
       - **`s`**: Stringa che viene utilizzata per determinare l'unità di misura del prodotto (ad esempio, "pezzi", "kg", "mazzi"), in base al tipo di prodotto.
       - **`hbox`**: Oggetto di tipo `HBox` che contiene l'etichetta (`Label`) con la descrizione del prodotto e un pulsante (`Button`) per la rimozione del prodotto dal carrello.
       - **`prodottoLabel`**: Etichetta (`Label`) che mostra il nome del prodotto, il suo peso, l'unità di misura e il prezzo.
       - **`removeButton`**: Pulsante (`Button`) che consente di rimuovere il prodotto dal carrello. Ha un'azione associata che permette la rimozione del prodotto e aggiorna la lista e il totale.
       - **`index`**: Variabile `int` che memorizza l'indice corrente del prodotto nell'`ArrayList`, utile per identificare quale prodotto rimuovere.

   - **Comportamento del metodo**:
       1. **Pulizia della `ListView`**: Il metodo inizia cancellando tutti gli elementi correnti dalla `ListView` tramite il metodo `cartListView.getItems().clear()`.

       2. **Inizializzazione delle variabili**: Viene inizializzato il totale a 0 e vengono popolati i tre `ArrayList` (`prodotti`, `prezzi`, `pesi`) chiamando i rispettivi metodi di `testClass`.

       3. **Ciclo sui prodotti**: Il metodo scorre ogni prodotto, prezzo e peso della lista. Per ogni iterazione:
           - Viene creato un `HBox` per contenere le informazioni del prodotto e il pulsante di rimozione.
           - A seconda del tipo di prodotto (come "carciofi", "ravanelli" o altri prodotti), viene determinata l'unità di misura da usare (pezzi, mazzi o kg) e memorizzata nella stringa `s`.
           - Viene creata una `Label` (`prodottoLabel`) che visualizza il nome del prodotto, il peso (arrotondato), l'unità di misura e il prezzo del prodotto.
           - Viene creato un pulsante di rimozione (`removeButton`) con uno stile personalizzato, che quando cliccato rimuove il prodotto dall'elenco tramite il metodo `removeProduct(index)`, aggiorna il colore del pulsante temporaneamente e chiama di nuovo `aggiornaSezCarrello()` per aggiornare la vista.

       4. **Aggiornamento della `ListView`**: Ogni `HBox`, contenente il nome del prodotto e il pulsante di rimozione, viene aggiunto alla `ListView` (`cartListView.getItems().add(hbox)`).

       5. **Calcolo del totale**: Per ogni prodotto, il prezzo viene aggiunto alla variabile `total`. Alla fine del ciclo, il totale viene arrotondato alla seconda cifra decimale con il metodo `arrotondaAlCent(total)`.

       6. **Aggiornamento del totale nella `Label`**: Il totale viene visualizzato aggiornando il testo di `totalLabel`.

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

---

> ### FruttaController.java

### Descrizione delle variabili di classe

- **`peso0, peso1, ..., peso14`**: `ComboBox<Double>` che permettono all'utente di selezionare il peso di 15 diversi prodotti.
- **`bottOk`**: `Button` per confermare l'aggiunta dei prodotti nel carrello.
- **`cartListView`**: `ListView<HBox>` che visualizza il contenuto del carrello, includendo le informazioni sui prodotti selezionati.
- **`totalLabel`**: `Label` che mostra il totale del carrello.


### Descrizione dei metodi del codice Java

1. **`initialize()`**
- **Descrizione**: Questo metodo viene chiamato automaticamente all'inizializzazione della scena. Aggiorna la sezione del carrello e inizializza i combo box per selezionare i pesi dei prodotti.
- **Variabili**: Non usa variabili locali ma richiama i metodi `aggiornaSezCarrello()` e `populateComboBox()`.

2. **`aggiornaSezCarrello()`**
    - **Descrizione**: Aggiorna la `ListView` che mostra i prodotti presenti nel carrello, insieme ai loro prezzi e pesi, e ricalcola il totale della spesa. Permette inoltre la rimozione dei prodotti dal carrello e aggiorna automaticamente la visualizzazione dopo ogni rimozione.

    - **Variabili**:
        - **`total`**: Variabile di tipo `double` che tiene traccia del totale della spesa, inizializzata a 0.
        - **`prodotti`**, **`prezzi`**, **`pesi`**: Tre `ArrayList` che contengono rispettivamente i nomi dei prodotti, i prezzi e i pesi degli articoli nel carrello. Questi array vengono recuperati tramite i metodi statici di `testClass`.
        - **`s`**: Stringa che viene utilizzata per determinare l'unità di misura del prodotto (ad esempio, "pezzi", "kg", "mazzi"), in base al tipo di prodotto.
        - **`hbox`**: Oggetto di tipo `HBox` che contiene l'etichetta (`Label`) con la descrizione del prodotto e un pulsante (`Button`) per la rimozione del prodotto dal carrello.
        - **`prodottoLabel`**: Etichetta (`Label`) che mostra il nome del prodotto, il suo peso, l'unità di misura e il prezzo.
        - **`removeButton`**: Pulsante (`Button`) che consente di rimuovere il prodotto dal carrello. Ha un'azione associata che permette la rimozione del prodotto e aggiorna la lista e il totale.
        - **`index`**: Variabile `int` che memorizza l'indice corrente del prodotto nell'`ArrayList`, utile per identificare quale prodotto rimuovere.

    - **Comportamento del metodo**:
        1. **Pulizia della `ListView`**: Il metodo inizia cancellando tutti gli elementi correnti dalla `ListView` tramite il metodo `cartListView.getItems().clear()`.

        2. **Inizializzazione delle variabili**: Viene inizializzato il totale a 0 e vengono popolati i tre `ArrayList` (`prodotti`, `prezzi`, `pesi`) chiamando i rispettivi metodi di `testClass`.

        3. **Ciclo sui prodotti**: Il metodo scorre ogni prodotto, prezzo e peso della lista. Per ogni iterazione:
            - Viene creato un `HBox` per contenere le informazioni del prodotto e il pulsante di rimozione.
            - A seconda del tipo di prodotto (come "carciofi", "ravanelli" o altri prodotti), viene determinata l'unità di misura da usare (pezzi, mazzi o kg) e memorizzata nella stringa `s`.
            - Viene creata una `Label` (`prodottoLabel`) che visualizza il nome del prodotto, il peso (arrotondato), l'unità di misura e il prezzo del prodotto.
            - Viene creato un pulsante di rimozione (`removeButton`) con uno stile personalizzato, che quando cliccato rimuove il prodotto dall'elenco tramite il metodo `removeProduct(index)`, aggiorna il colore del pulsante temporaneamente e chiama di nuovo `aggiornaSezCarrello()` per aggiornare la vista.

        4. **Aggiornamento della `ListView`**: Ogni `HBox`, contenente il nome del prodotto e il pulsante di rimozione, viene aggiunto alla `ListView` (`cartListView.getItems().add(hbox)`).

        5. **Calcolo del totale**: Per ogni prodotto, il prezzo viene aggiunto alla variabile `total`. Alla fine del ciclo, il totale viene arrotondato alla seconda cifra decimale con il metodo `arrotondaAlCent(total)`.

        6. **Aggiornamento del totale nella `Label`**: Il totale viene visualizzato aggiornando il testo di `totalLabel`.

3. **`arrotondaAlCent(double value)`**
- **Descrizione**: Arrotonda un valore numerico al centesimo (due decimali) usando `BigDecimal`.
- **Variabili**:
  - `bd`: Un oggetto `BigDecimal` usato per effettuare l'arrotondamento.

4. **`immDiConferma()`**
- **Descrizione**: Cambia il colore del bottone `bottOk` per indicare la conferma dell'aggiunta di un prodotto al carrello.
- **Variabili**:
  - `pause`: Una pausa di 2 secondi prima di riportare il colore del bottone allo stato iniziale.

5. **`immDiErrore()`**
- **Descrizione**: Cambia il colore di `bottOk` in rosso per indicare un errore, come quando non è selezionato un peso. Simile a `immDiConferma()` ma per gli errori.
- **Variabili**: Simile a `immDiConferma`.

6. **`aggiuntaProdotto(String prodotto, double prezzoAlKg, ComboBox<Double> combo)`**
- **Descrizione**: Aggiunge un prodotto al carrello. Controlla se il peso è selezionato, altrimenti segnala un errore. Se tutto è corretto, calcola il prezzo totale (peso * prezzoAlKg) e lo aggiunge al carrello.
- **Variabili**:
  - `peso`: Il peso selezionato dall'utente.
  - `combo`: Il `ComboBox` da cui viene selezionato il peso del prodotto.

7. **`aggMele()`, `aggPere()`, ..., `aggPrugne()`**
- **Descrizione**: Metodi specifici per aggiungere prodotti come mele, pere, banane, ecc. al carrello, ognuno con il proprio prezzo al kg.
- **Variabili**: Chiamano `aggiuntaProdotto()` con il nome del prodotto, il prezzo al kg e il rispettivo `ComboBox`.

8. **`aggiungiProdotto(String prodotto, double prezzo, double peso)`**
- **Descrizione**: Aggiunge un prodotto al carrello, interfacciandosi con la classe `testClass`.
- **Variabili**:
  - `prodotto`: Il nome del prodotto.
  - `prezzo`: Il prezzo totale del prodotto.
  - `peso`: Il peso selezionato.

9. **`populateComboBox(ComboBox<Double> peso)`**
- **Descrizione**: Popola un `ComboBox` con una lista di valori di peso, che va da 5.00 kg a 0.10 kg, con decrementi di 0.05.
- **Variabili**:
  - `i`: Rappresenta i valori del peso che vengono aggiunti al `ComboBox`.
  
#### Metodi per il cambio scena:

I metodi seguenti cambiano la scena visualizzata nell'interfaccia, vedi descrizione precedente per un'approfondimento maggiore:
- **switchToProductsView(MouseEvent mouseEvent)**: Passa alla schermata dei prodotti.
- **switchToCart(MouseEvent mouseEvent)**: Passa alla schermata del carrello.
- **switchToFrutta(ActionEvent event)**: Passa alla schermata dei prodotti di frutta.
- **switchToVerduraSimple(ActionEvent event)**: Passa alla schermata dei prodotti di verdura.
- **switchToPreparati(ActionEvent event)**: Passa alla schermata dei prodotti preparati.
- **switchToMenu(ActionEvent event)**: Torna al menu principale.

In ciascuno di questi metodi, vengono utilizzate variabili come `fxmlLoader`, `scene` e `stage` per caricare e impostare la nuova scena.

---

> ### PreparatiController.java

### Descrizione delle variabili di classe

- **`peso0, peso1, ..., peso3`**: `ComboBox<Double>` che permettono all'utente di selezionare il peso di 4 diversi prodotti preparati.
- **`bottOk`**: `Button` per confermare l'aggiunta dei prodotti nel carrello.
- **`cartListView`**: `ListView<HBox>` che visualizza il contenuto del carrello, includendo le informazioni sui prodotti selezionati.
- **`totalLabel`**: `Label` che mostra il totale del carrello.
- **`insalata`**: `ToggleGroup` che include tutti i diversi tipi di prodotto selezionabili con i radioButton.
- **`imInsalata`**: `ImageView` che mostra il l'immagine del tipo di insalata selezionato attraverso i radioButton.


### Descrizione dei metodi del codice Java

1. **`initialize()`**
- **Descrizione**: Questo metodo viene chiamato automaticamente all'inizializzazione della scena. Aggiorna la sezione del carrello e inizializza i combo box per selezionare i pesi dei prodotti.
- **Variabili**: Non usa variabili locali ma richiama i metodi `aggiornaSezCarrello()` e `populateComboBox()`.

2. **`aggiornaSezCarrello()`**
    - **Descrizione**: Aggiorna la `ListView` che mostra i prodotti presenti nel carrello, insieme ai loro prezzi e pesi, e ricalcola il totale della spesa. Permette inoltre la rimozione dei prodotti dal carrello e aggiorna automaticamente la visualizzazione dopo ogni rimozione.

    - **Variabili**:
        - **`total`**: Variabile di tipo `double` che tiene traccia del totale della spesa, inizializzata a 0.
        - **`prodotti`**, **`prezzi`**, **`pesi`**: Tre `ArrayList` che contengono rispettivamente i nomi dei prodotti, i prezzi e i pesi degli articoli nel carrello. Questi array vengono recuperati tramite i metodi statici di `testClass`.
        - **`s`**: Stringa che viene utilizzata per determinare l'unità di misura del prodotto (ad esempio, "pezzi", "kg", "mazzi"), in base al tipo di prodotto.
        - **`hbox`**: Oggetto di tipo `HBox` che contiene l'etichetta (`Label`) con la descrizione del prodotto e un pulsante (`Button`) per la rimozione del prodotto dal carrello.
        - **`prodottoLabel`**: Etichetta (`Label`) che mostra il nome del prodotto, il suo peso, l'unità di misura e il prezzo.
        - **`removeButton`**: Pulsante (`Button`) che consente di rimuovere il prodotto dal carrello. Ha un'azione associata che permette la rimozione del prodotto e aggiorna la lista e il totale.
        - **`index`**: Variabile `int` che memorizza l'indice corrente del prodotto nell'`ArrayList`, utile per identificare quale prodotto rimuovere.

    - **Comportamento del metodo**:
        1. **Pulizia della `ListView`**: Il metodo inizia cancellando tutti gli elementi correnti dalla `ListView` tramite il metodo `cartListView.getItems().clear()`.

        2. **Inizializzazione delle variabili**: Viene inizializzato il totale a 0 e vengono popolati i tre `ArrayList` (`prodotti`, `prezzi`, `pesi`) chiamando i rispettivi metodi di `testClass`.

        3. **Ciclo sui prodotti**: Il metodo scorre ogni prodotto, prezzo e peso della lista. Per ogni iterazione:
            - Viene creato un `HBox` per contenere le informazioni del prodotto e il pulsante di rimozione.
            - A seconda del tipo di prodotto (come "carciofi", "ravanelli" o altri prodotti), viene determinata l'unità di misura da usare (pezzi, mazzi o kg) e memorizzata nella stringa `s`.
            - Viene creata una `Label` (`prodottoLabel`) che visualizza il nome del prodotto, il peso (arrotondato), l'unità di misura e il prezzo del prodotto.
            - Viene creato un pulsante di rimozione (`removeButton`) con uno stile personalizzato, che quando cliccato rimuove il prodotto dall'elenco tramite il metodo `removeProduct(index)`, aggiorna il colore del pulsante temporaneamente e chiama di nuovo `aggiornaSezCarrello()` per aggiornare la vista.

        4. **Aggiornamento della `ListView`**: Ogni `HBox`, contenente il nome del prodotto e il pulsante di rimozione, viene aggiunto alla `ListView` (`cartListView.getItems().add(hbox)`).

        5. **Calcolo del totale**: Per ogni prodotto, il prezzo viene aggiunto alla variabile `total`. Alla fine del ciclo, il totale viene arrotondato alla seconda cifra decimale con il metodo `arrotondaAlCent(total)`.

        6. **Aggiornamento del totale nella `Label`**: Il totale viene visualizzato aggiornando il testo di `totalLabel`.

3. **`arrotondaAlCent(double value)`**
- **Descrizione**: Arrotonda un valore numerico al centesimo (due decimali) usando `BigDecimal`.
- **Variabili**:
  - `bd`: Un oggetto `BigDecimal` usato per effettuare l'arrotondamento.

4. **`immDiConferma()`**
- **Descrizione**: Cambia il colore del bottone `bottOk` per indicare la conferma dell'aggiunta di un prodotto al carrello.
- **Variabili**:
  - `pause`: Una pausa di 2 secondi prima di riportare il colore del bottone allo stato iniziale.

5. **`immDiErrore()`**
- **Descrizione**: Cambia il colore di `bottOk` in rosso per indicare un errore, come quando non è selezionato un peso. Simile a `immDiConferma()` ma per gli errori.
- **Variabili**: Simile a `immDiConferma`.

7. **`aggInsalata()`, `aggMacedonia()`, ..., `aggMelone()`**
- **Descrizione**: Metodi specifici per aggiungere prodotti come l'insalate, macedonia, melone, ecc. al carrello, ognuno con il proprio prezzo al kg.
- **Variabili**: Chiamano `aggiungiRiga()` con il nome del prodotto, il prezzo*peso e il rispettivo peso, dopo un controllo se il peso è stato inserito, richiamando in base alla presenza di esso `immDiConferma()` o `immDiErrore()`.

8. **`aggiungiRiga(String prodotto, double prezzo, double peso)`**
- **Descrizione**: Aggiunge un prodotto al carrello, interfacciandosi con la classe `testClass`.
- **Variabili**:
  - `prodotto`: Il nome del prodotto.
  - `prezzo`: Il prezzo totale del prodotto.
  - `peso`: Il peso selezionato.

9. **`populateComboBoxPezzi(ComboBox<Double> peso)`**
- **Descrizione**: Popola un `ComboBox` con una lista di valori di pezzi, che va da 10.00 a 1.00, con decrementi di 1.00.
- **Variabili**:
  - `i`: Rappresenta i valori dei pezzi che vengono aggiunti al `ComboBox`.
  
#### Metodi per il cambio scena:

I metodi seguenti cambiano la scena visualizzata nell'interfaccia, vedi descrizione precedente per un'approfondimento maggiore:
- **switchToProductsView(MouseEvent mouseEvent)**: Passa alla schermata dei prodotti.
- **switchToCart(MouseEvent mouseEvent)**: Passa alla schermata del carrello.
- **switchToFrutta(ActionEvent event)**: Passa alla schermata dei prodotti di frutta.
- **switchToVerduraSimple(ActionEvent event)**: Passa alla schermata dei prodotti di verdura.
- **switchToPreparati(ActionEvent event)**: Passa alla schermata dei prodotti preparati.
- **switchToMenu(ActionEvent event)**: Torna al menu principale.

In ciascuno di questi metodi, vengono utilizzate variabili come `fxmlLoader`, `scene` e `stage` per caricare e impostare la nuova scena.

---

> ### VerduraSimpleController.java

### Descrizione delle variabili di classe

- **`ComboBox<Double> peso1, peso2, peso3`**: ComboBox per selezionare il peso di 3 vari prodotti (in kg o pezzi).
- **`Button bottOk`**: Bottone per confermare l'aggiunta di prodotti al carrello.
- **`ListView<HBox> cartListView`**: ListView per visualizzare i prodotti aggiunti al carrello.
- **`Label totalLabel, prezzoCarciofi`**: Label per mostrare rispettivamente il totale del carrello e il prezzo dei carciofi selezionati.
- **`ToggleGroup carciofi, cavolfiore`**: Gruppi di RadioButton per selezionare varianti di carciofi o cavolfiore.
- **`ImageView imCarc, imCavol`**: ImageView per mostrare immagini dei carciofi e cavolfiori selezionati.

### Descrizione dei metodi del codice Java

1. **`initialize()`**
- **Descrizione**: Questo metodo viene chiamato automaticamente all'inizializzazione della scena. Aggiorna la sezione del carrello e inizializza i combo box per selezionare i pesi dei prodotti.
- **Variabili**: Non usa variabili locali ma richiama i metodi `aggiornaSezCarrello()` e `populateComboBox()`.

2. **`aggiornaSezCarrello()`**
    - **Descrizione**: Aggiorna la `ListView` che mostra i prodotti presenti nel carrello, insieme ai loro prezzi e pesi, e ricalcola il totale della spesa. Permette inoltre la rimozione dei prodotti dal carrello e aggiorna automaticamente la visualizzazione dopo ogni rimozione.

    - **Variabili**:
        - **`total`**: Variabile di tipo `double` che tiene traccia del totale della spesa, inizializzata a 0.
        - **`prodotti`**, **`prezzi`**, **`pesi`**: Tre `ArrayList` che contengono rispettivamente i nomi dei prodotti, i prezzi e i pesi degli articoli nel carrello. Questi array vengono recuperati tramite i metodi statici di `testClass`.
        - **`s`**: Stringa che viene utilizzata per determinare l'unità di misura del prodotto (ad esempio, "pezzi", "kg", "mazzi"), in base al tipo di prodotto.
        - **`hbox`**: Oggetto di tipo `HBox` che contiene l'etichetta (`Label`) con la descrizione del prodotto e un pulsante (`Button`) per la rimozione del prodotto dal carrello.
        - **`prodottoLabel`**: Etichetta (`Label`) che mostra il nome del prodotto, il suo peso, l'unità di misura e il prezzo.
        - **`removeButton`**: Pulsante (`Button`) che consente di rimuovere il prodotto dal carrello. Ha un'azione associata che permette la rimozione del prodotto e aggiorna la lista e il totale.
        - **`index`**: Variabile `int` che memorizza l'indice corrente del prodotto nell'`ArrayList`, utile per identificare quale prodotto rimuovere.

    - **Comportamento del metodo**:
        1. **Pulizia della `ListView`**: Il metodo inizia cancellando tutti gli elementi correnti dalla `ListView` tramite il metodo `cartListView.getItems().clear()`.

        2. **Inizializzazione delle variabili**: Viene inizializzato il totale a 0 e vengono popolati i tre `ArrayList` (`prodotti`, `prezzi`, `pesi`) chiamando i rispettivi metodi di `testClass`.

        3. **Ciclo sui prodotti**: Il metodo scorre ogni prodotto, prezzo e peso della lista. Per ogni iterazione:
            - Viene creato un `HBox` per contenere le informazioni del prodotto e il pulsante di rimozione.
            - A seconda del tipo di prodotto (come "carciofi", "ravanelli" o altri prodotti), viene determinata l'unità di misura da usare (pezzi, mazzi o kg) e memorizzata nella stringa `s`.
            - Viene creata una `Label` (`prodottoLabel`) che visualizza il nome del prodotto, il peso (arrotondato), l'unità di misura e il prezzo del prodotto.
            - Viene creato un pulsante di rimozione (`removeButton`) con uno stile personalizzato, che quando cliccato rimuove il prodotto dall'elenco tramite il metodo `removeProduct(index)`, aggiorna il colore del pulsante temporaneamente e chiama di nuovo `aggiornaSezCarrello()` per aggiornare la vista.

        4. **Aggiornamento della `ListView`**: Ogni `HBox`, contenente il nome del prodotto e il pulsante di rimozione, viene aggiunto alla `ListView` (`cartListView.getItems().add(hbox)`).

        5. **Calcolo del totale**: Per ogni prodotto, il prezzo viene aggiunto alla variabile `total`. Alla fine del ciclo, il totale viene arrotondato alla seconda cifra decimale con il metodo `arrotondaAlCent(total)`.

        6. **Aggiornamento del totale nella `Label`**: Il totale viene visualizzato aggiornando il testo di `totalLabel`.

3. **`arrotondaAlCent(double value)`**
- **Descrizione**: Arrotonda un valore numerico al centesimo (due decimali) usando `BigDecimal`.
- **Variabili**:
  - `bd`: Un oggetto `BigDecimal` usato per effettuare l'arrotondamento.

4. **`immDiConferma()`**
- **Descrizione**: Cambia il colore del bottone `bottOk` per indicare la conferma dell'aggiunta di un prodotto al carrello.
- **Variabili**:
  - `pause`: Una pausa di 2 secondi prima di riportare il colore del bottone allo stato iniziale.

5. **`immDiErrore()`**
- **Descrizione**: Cambia il colore di `bottOk` in rosso per indicare un errore, come quando non è selezionato un peso. Simile a `immDiConferma()` ma per gli errori.
- **Variabili**: Simile a `immDiConferma`.

6. **`aggiuntaProdotto(String prodotto, double prezzoAlKg, ComboBox<Double> combo)`**
- **Descrizione**: Aggiunge un prodotto al carrello. Controlla se il peso è selezionato, altrimenti segnala un errore. Se tutto è corretto, calcola il prezzo totale (peso * prezzoAlKg) e lo aggiunge al carrello.
- **Variabili**:
  - `peso`: Il peso selezionato dall'utente.
  - `combo`: Il `ComboBox` da cui viene selezionato il peso del prodotto.

7. **`aggAsparagi()`**
   - **Descrizione**: Aggiunge una quantità fissa di asparagi al carrello.
   - **Variabili**: Nessuna specifica.
   - **Azioni**:
     - Aggiunge gli asparagi al carrello con un peso fisso di 0.2 kg.

8. **`aggCarciofi()`**
   - **Descrizione**: Aggiunge carciofi al carrello in base al tipo selezionato (normali o romani).
   - **Variabili**:
     - `RadioButton radioSelezionato`: RadioButton selezionato per determinare il tipo di carciofi.
     - `double mon`: Prezzo del carciofo selezionato.
   - **Azioni**:
     - Determina il prezzo dei carciofi selezionati e li aggiunge al carrello.

9. **`radCarciofi()`**
   - **Descrizione**: Cambia l'immagine e il prezzo mostrato in base al tipo di carciofo selezionato.
   - **Variabili**:
     - `String selectedText`: Testo del RadioButton selezionato.
     - `double mon`: Prezzo variabile in base al tipo di carciofo.
   - **Azioni**:
     - Cambia l'immagine visualizzata in `imCarc`.
     - Aggiorna il prezzo visualizzato in `prezzoCarciofi`.

10. **`aggCarote()`**
    - **Descrizione**: Aggiunge carote al carrello in base al peso selezionato.
    - **Variabili**: Nessuna specifica.
    - **Azioni**:
      - Aggiunge le carote al carrello chiamando `aggiuntaProdotto`.

11. **`aggCavolfiore()`**
    - **Descrizione**: Aggiunge cavolfiori al carrello in base al tipo e peso selezionati.
    - **Variabili**:
      - `RadioButton carciofiSelectedToggle`: RadioButton selezionato per determinare il tipo di cavolfiore.
    - **Azioni**:
      - Aggiunge i cavolfiori al carrello con il peso e tipo selezionato.

12. **`radCavolfiori()`**
    - **Descrizione**: Cambia l'immagine mostrata per il cavolfiore in base al tipo selezionato.
    - **Variabili**:
      - `String selectedText`: Testo del RadioButton selezionato.
    - **Azioni**:
      - Cambia l'immagine visualizzata in `imCavol`.

13. **`aggiungiProdotto(String prodotto, double prezzo, double peso)`**
    - **Descrizione**: Aggiunge un prodotto al carrello con nome, prezzo e peso specificati.
    - **Variabili**: Nessuna specifica.
    - **Azioni**:
      - Usa il metodo di `testClass` per aggiungere il prodotto al carrello.

14. **`populateComboBox(ComboBox<Double> peso)`**
    - **Descrizione**: Popola una ComboBox con valori di peso da 5.00 a 0.10 kg.
    - **Variabili**: 
      - `i`: Rappresenta i valori del peso che vengono aggiunti al `ComboBox`.
    - **Azioni**:
      - Aggiunge i valori arrotondati alla ComboBox.

15. **`populateComboBoxPezzi(ComboBox<Double> peso)`**
    - **Descrizione**: Popola una ComboBox con valori di pezzi da 10 a 1.
    - **Variabili**:
      - `i`: Rappresenta i valori dei pezzi che vengono aggiunti al `ComboBox`.
    - **Azioni**:
      - Aggiunge i valori arrotondati alla ComboBox.

#### Metodi per il cambio scena:

I metodi seguenti cambiano la scena visualizzata nell'interfaccia, vedi descrizione precedente per un'approfondimento maggiore:
- **switchToProductsView(MouseEvent mouseEvent)**: Passa alla schermata dei prodotti.
- **switchToCart(MouseEvent mouseEvent)**: Passa alla schermata del carrello.
- **switchToFrutta(ActionEvent event)**: Passa alla schermata dei prodotti di frutta.
- **switchToVerduraSimple(ActionEvent event)**: Passa alla schermata dei prodotti di verdura semplice.
- **switchToVerdura(ActionEvent event)**: Passa alla schermata dei prodotti di verdura.
- **switchToPreparati(ActionEvent event)**: Passa alla schermata dei prodotti preparati.
- **switchToMenu(ActionEvent event)**: Torna al menu principale.

In ciascuno di questi metodi, vengono utilizzate variabili come `fxmlLoader`, `scene` e `stage` per caricare e impostare la nuova scena.

---

> ### VerduraInsalateController.java

### Descrizione delle variabili di classe

- **`ComboBox<Double> peso0, peso1, peso68`**: ComboBox per selezionare il peso di 69 vari prodotti (in kg o pezzi o in cassette).
- **`Button bottOk`**: Bottone per confermare l'aggiunta di prodotti al carrello.
- **`ListView<HBox> cartListView`**: ListView per visualizzare i prodotti aggiunti al carrello.
- **`Label totalLabel, prezzoCarciofi, ... , prezzoCuoreDiBue`**: Label per mostrare rispettivamente il totale del carrello e il prezzo del prodotto selezionato.
- **`ToggleGroup carciofi, cavolfiore, ..., datterino`**: Gruppi di RadioButton per selezionare varianti di carciofi o cavolfiore o tanti altri prodotti.
- **`ImageView imCarc, imCavol, ..., imDaterino`**: ImageView per mostrare immagini dei carciofi, cavolfiori selezionati e altri prodotti.
- **`CheckBox c54, c55, ..., c59`**: Cassetta si o no (denominazione è c+numero del peso cioè quello della combobox).

### Descrizione dei metodi del codice Java

1. **`initialize()`**
- **Descrizione**: Questo metodo viene chiamato automaticamente all'inizializzazione della scena. Aggiorna la sezione del carrello e inizializza i combo box per selezionare i pesi dei prodotti, in base alla tipologia, inoltre imposta un unico gestore di eventi per tutte le CheckBox.
- **Variabili**: Non usa variabili locali ma richiama i metodi `aggiornaSezCarrello()`, `populateComboBox()`, `populateComboBoxPezzi()`, `populateComboBoxPiccolo()`, `populateComboBoxGrande()` e `sceltaCassetta()`.

2. **`aggiornaSezCarrello()`**
    - **Descrizione**: Aggiorna la `ListView` che mostra i prodotti presenti nel carrello, insieme ai loro prezzi e pesi, e ricalcola il totale della spesa. Permette inoltre la rimozione dei prodotti dal carrello e aggiorna automaticamente la visualizzazione dopo ogni rimozione.

    - **Variabili**:
        - **`total`**: Variabile di tipo `double` che tiene traccia del totale della spesa, inizializzata a 0.
        - **`prodotti`**, **`prezzi`**, **`pesi`**: Tre `ArrayList` che contengono rispettivamente i nomi dei prodotti, i prezzi e i pesi degli articoli nel carrello. Questi array vengono recuperati tramite i metodi statici di `testClass`.
        - **`s`**: Stringa che viene utilizzata per determinare l'unità di misura del prodotto (ad esempio, "pezzi", "kg", "mazzi"), in base al tipo di prodotto.
        - **`hbox`**: Oggetto di tipo `HBox` che contiene l'etichetta (`Label`) con la descrizione del prodotto e un pulsante (`Button`) per la rimozione del prodotto dal carrello.
        - **`prodottoLabel`**: Etichetta (`Label`) che mostra il nome del prodotto, il suo peso, l'unità di misura e il prezzo.
        - **`removeButton`**: Pulsante (`Button`) che consente di rimuovere il prodotto dal carrello. Ha un'azione associata che permette la rimozione del prodotto e aggiorna la lista e il totale.
        - **`index`**: Variabile `int` che memorizza l'indice corrente del prodotto nell'`ArrayList`, utile per identificare quale prodotto rimuovere.

    - **Comportamento del metodo**:
        1. **Pulizia della `ListView`**: Il metodo inizia cancellando tutti gli elementi correnti dalla `ListView` tramite il metodo `cartListView.getItems().clear()`.

        2. **Inizializzazione delle variabili**: Viene inizializzato il totale a 0 e vengono popolati i tre `ArrayList` (`prodotti`, `prezzi`, `pesi`) chiamando i rispettivi metodi di `testClass`.

        3. **Ciclo sui prodotti**: Il metodo scorre ogni prodotto, prezzo e peso della lista. Per ogni iterazione:
            - Viene creato un `HBox` per contenere le informazioni del prodotto e il pulsante di rimozione.
            - A seconda del tipo di prodotto (come "carciofi", "ravanelli" o altri prodotti), viene determinata l'unità di misura da usare (pezzi, mazzi o kg) e memorizzata nella stringa `s`.
            - Viene creata una `Label` (`prodottoLabel`) che visualizza il nome del prodotto, il peso (arrotondato), l'unità di misura e il prezzo del prodotto.
            - Viene creato un pulsante di rimozione (`removeButton`) con uno stile personalizzato, che quando cliccato rimuove il prodotto dall'elenco tramite il metodo `removeProduct(index)`, aggiorna il colore del pulsante temporaneamente e chiama di nuovo `aggiornaSezCarrello()` per aggiornare la vista.

        4. **Aggiornamento della `ListView`**: Ogni `HBox`, contenente il nome del prodotto e il pulsante di rimozione, viene aggiunto alla `ListView` (`cartListView.getItems().add(hbox)`).

        5. **Calcolo del totale**: Per ogni prodotto, il prezzo viene aggiunto alla variabile `total`. Alla fine del ciclo, il totale viene arrotondato alla seconda cifra decimale con il metodo `arrotondaAlCent(total)`.

        6. **Aggiornamento del totale nella `Label`**: Il totale viene visualizzato aggiornando il testo di `totalLabel`.

3. **`arrotondaAlCent(double value)`**
- **Descrizione**: Arrotonda un valore numerico al centesimo (due decimali) usando `BigDecimal`.
- **Variabili**:
  - `bd`: Un oggetto `BigDecimal` usato per effettuare l'arrotondamento.

4. **`immDiConferma()`**
- **Descrizione**: Cambia il colore del bottone `bottOk` per indicare la conferma dell'aggiunta di un prodotto al carrello.
- **Variabili**:
  - `pause`: Una pausa di 2 secondi prima di riportare il colore del bottone allo stato iniziale.

5. **`immDiErrore()`**
- **Descrizione**: Cambia il colore di `bottOk` in rosso per indicare un errore, come quando non è selezionato un peso. Simile a `immDiConferma()` ma per gli errori.
- **Variabili**: Simile a `immDiConferma`.

6. **`aggiuntaProdotto(String prodotto, double prezzoAlKg, ComboBox<Double> combo)`**
- **Descrizione**: Aggiunge un prodotto al carrello. Controlla se il peso è selezionato, altrimenti segnala un errore. Se tutto è corretto, calcola il prezzo totale (peso * prezzoAlKg) e lo aggiunge al carrello.
- **Variabili**:
  - `peso`: Il peso selezionato dall'utente.
  - `combo`: Il `ComboBox` da cui viene selezionato il peso del prodotto.

7. **`aggAsparagi(), ...,aggMenta()`**
   - **Descrizione**: Aggiunge una quantità di prodotti al carrello, che può essere fissa o selezionabile, inoltre il prodotto può essere variabile nella tipologia.
   - **Variabili**: Nessuna specifica.
   - **Azioni**:
     - Aggiunge i prodotti al carrello con un peso fisso o non, attraverso `aggiuntaProdotto()`.

8. **`aggCarciofi(), ..., aggCuoreDiBue()`**
   - **Descrizione**: Aggiunge prodotti al carrello in base al tipo selezionato (normali o altri tipi), o alla scelta di prendere le cassette o non.
   - **Variabili**:
     - `RadioButton radioSelezionato`: RadioButton selezionato per determinare il tipo di prodotto.
     - `double mon`: Prezzo del prodotto selezionato.
     - `String s` : Stringa per indicare la scelta di cassetta o non.
     - `String selectedText` : Stringa per indicare il tipo di prodotto scelto.
   - **Azioni**:
     - Determina il prezzo dei prodotti selezionati e li aggiunge al carrello, variandone il prezzo in base al tipo o alla scelta della cassetta.

9. **`radCarciofi(), ..., radCiliegino()`**
   - **Descrizione**: Cambia l'immagine e il prezzo mostrato in base al tipo di prodotto selezionato.
   - **Variabili**:
     - `String selectedText`: Testo del RadioButton selezionato.
     - `double mon`: Prezzo variabile in base al tipo di prodotto selezionato.
   - **Azioni**:
     - Cambia l'immagine visualizzata in `imProdotto`.
     - Aggiorna il prezzo visualizzato in `prezzoProdotto`.
13. **`aggiungiProdotto(String prodotto, double prezzo, double peso)`**
    - **Descrizione**: Aggiunge un prodotto al carrello con nome, prezzo e peso specificati.
    - **Variabili**: Nessuna specifica.
    - **Azioni**:
      - Usa il metodo di `testClass` per aggiungere il prodotto al carrello.

14. **`populateComboBox(ComboBox<Double> peso)`**
    - **Descrizione**: Popola una ComboBox con valori di peso da 5.00 a 0.10 kg.
    - **Variabili**: 
      - `i`: Rappresenta i valori del peso che vengono aggiunti al `ComboBox`.
    - **Azioni**:
      - Aggiunge i valori arrotondati alla ComboBox.

15. **`populateComboBoxPezzi(ComboBox<Double> peso)`**
    - **Descrizione**: Popola una ComboBox con valori di pezzi da 10 a 1.
    - **Variabili**:
      - `i`: Rappresenta i valori dei pezzi che vengono aggiunti al `ComboBox`.
    - **Azioni**:
      - Aggiunge i valori arrotondati alla ComboBox.
16. **`populateComboBoxPiccolo(ComboBox<Double> peso)`**
    - **Descrizione**: Popola una ComboBox con valori di peso da 0.90 a 0.15 kg, decrementando di 0.15 Kg.
    - **Variabili**: 
      - `i`: Rappresenta i valori del peso che vengono aggiunti al `ComboBox`.
    - **Azioni**:
      - Aggiunge i valori arrotondati alla ComboBox.
17. **`populateComboBoxGrande(ComboBox<Double> peso)`**
    - **Descrizione**: Popola una ComboBox con valori di peso da 5.00 a 0.30 kg, decrementando di 0.10 Kg.
    - **Variabili**: 
      - `i`: Rappresenta i valori del peso che vengono aggiunti al `ComboBox`.
    - **Azioni**:
      - Aggiunge i valori arrotondati alla ComboBox.
18. **`sceltaCassetta(CheckBox checkBox, ComboBox<Double> comboBox, Label labPrezzi)`**
   - **Descrizione**: Questo metodo gestisce il comportamento di una ComboBox e di una Label in base allo stato di selezione di una CheckBox. Se la CheckBox è selezionata, il metodo popola la ComboBox con i                            valori di peso 1.0, 2.0, e 3.0 kg e riduce il prezzo mostrato nella Label di 1.0 euro al kg. Se la CheckBox non è selezionata, la ComboBox viene ripopolata con i valori originali, e il                            prezzo nella Label viene aumentato di 1.0 euro al kg.
   - **Variabili**:
     - `val`: Rappresenta il prezzo corrente, calcolato sottraendo o aggiungendo 1.0 euro al valore estratto dalla Label `labPrezzi`.
   
   - **Azioni**:
     - **Controllo dello stato della CheckBox**: Verifica se la CheckBox è selezionata o meno utilizzando il metodo `isSelected()`.
     - **Pulizia della ComboBox**: Usa `comboBox.getItems().clear()` per rimuovere tutti i valori attualmente presenti nella ComboBox.
     - **Popolamento della ComboBox**: 
       - Se la CheckBox è selezionata, la ComboBox viene popolata con i valori 1.0, 2.0, e 3.0 kg usando `comboBox.getItems().addAll(1.0, 2.0, 3.0)`.
       - Se la CheckBox non è selezionata, viene richiamato il metodo `populateComboBox(comboBox)` per ripristinare i valori originali della ComboBox.
     - **Modifica del prezzo nella Label**:
       - Se la CheckBox è selezionata, il metodo estrapola i primi 3 caratteri del testo della Label `labPrezzi`, li converte in un valore `double` usando `Double.parseDouble()`, sottrae 1.0, e aggiorna la Label          con il nuovo prezzo.
       - Se la CheckBox non è selezionata, viene seguito lo stesso processo, ma il prezzo viene aumentato di 1.0.
     - **Arrotondamento del prezzo**: Il metodo chiama `arrotondaAlCent(val)` per arrotondare il prezzo al centesimo.
     - **Aggiornamento della Label**: Dopo il calcolo e l'arrotondamento, il testo della Label viene aggiornato per mostrare il nuovo prezzo, formattato come "X.X€/Kg".

#### Metodi per il cambio scena:

I metodi seguenti cambiano la scena visualizzata nell'interfaccia, vedi descrizione precedente per un'approfondimento maggiore:
- **switchToProductsView(MouseEvent mouseEvent)**: Passa alla schermata dei prodotti.
- **switchToCart(MouseEvent mouseEvent)**: Passa alla schermata del carrello.
- **switchToFrutta(ActionEvent event)**: Passa alla schermata dei prodotti di frutta.
- **switchToVerduraSimple(ActionEvent event)**: Passa alla schermata dei prodotti di verdura semplice.
- **switchToPreparati(ActionEvent event)**: Passa alla schermata dei prodotti preparati.
- **switchToMenu(ActionEvent event)**: Torna al menu principale.

In ciascuno di questi metodi, vengono utilizzate variabili come `fxmlLoader`, `scene` e `stage` per caricare e impostare la nuova scena.

---

> ### CartController.java

### Descrizione delle variabili di classe

- **`cartListView`**: `ListView<HBox>` Rappresenta la lista dei prodotti nel carrello, visualizzata sotto forma di `HBox` contenenti informazioni sui prodotti e pulsanti di rimozione.
- **`totalLabel`**: `Label`  Mostra il totale dell'importo degli articoli presenti nel carrello.
- **`bottOk`, `bottConcludi`**: `Button` `bottOk` è un pulsante per confermare le operazioni, mentre `bottConcludi` è un pulsante per finalizzare l'ordine.
- **`datePicker`**: `DatePicker` Consente all'utente di selezionare una data per il ritiro degli ordini.
- **`timeComboBox`**: `ComboBox<String>` Permette all'utente di selezionare l'ora di ritiro tra quelle disponibili.
- **`nomeField`, `emailField`, `emailVerificationField`**: `TextField` Campi di testo per inserire il nome, l'email e la verifica dell'email dell'utente.
- **`contEmail`**: `int[]` Array utilizzato per gestire l'autenticazione dell'email, dove ogni indice rappresenta uno stato diverso del processo di autenticazione.

### Descrizione dei metodi del codice Java

1. **`initialize()`**
   - **Descrizione**: Metodo chiamato automaticamente durante l'inizializzazione del controller. Configura il carrello e le opzioni di tempo.
   - **Azioni**:
     - Chiama `aggiornaSezCarrello()` per aggiornare il contenuto visualizzato del carrello.
     - Chiama `populateOrariBox(timeComboBox)` per popolare il `ComboBox` degli orari con gli intervalli di tempo disponibili per il ritiro.

2. **`concludi()`**
   - **Descrizione**: Gestisce la conclusione dell'ordine e invia un'email di conferma.
   - **Variabili**:
     - `LocalDate selectedDate`: Data selezionata dall'utente tramite il `DatePicker`.
   - **Azioni**:
     - Verifica la validità dei dati inseriti chiamando `noProblemi()`.
     - Invia un'email riepilogo al commerciante e al cliente utilizzando il metodo `sendMail` della classe `invioEmail`.
     - Pulisce il contenuto del carrello sia nella vista che nel salvataggio dei dati.
     - Aggiunge un `HBox` con un messaggio di ringraziamento al `cartListView`.
     - Se ci sono problemi, cambia il colore del bottone `bottOk` per indicare un errore temporaneo.

3. **`stampaScontrinoEmail(LocalDate selectedDate)`**
   - **Descrizione**: Genera una stringa che rappresenta il riepilogo dell'ordine per l'email di conferma.
   - **Variabili**:
     - `double total`: Totale del prezzo calcolato.
     - `ArrayList<String> prodotti`: Lista dei nomi dei prodotti nel carrello.
     - `ArrayList<Double> prezzi`: Lista dei prezzi dei prodotti.
     - `ArrayList<Double> pesi`: Lista dei pesi dei prodotti.
   - **Azioni**:
     - Crea una stringa che include i dettagli dei prodotti, il loro prezzo e peso.
     - Calcola il totale e lo arrotonda.
     - Restituisce la stringa formattata con i dettagli dell'ordine.

4. **`aggiornaSezCarrello()`**
   - **Descrizione**: Aggiorna la sezione del carrello visualizzata, mostrando i prodotti attualmente nel carrello e il totale.
   - **Variabili**:
     - `double total`: Totale del prezzo calcolato.
     - `ArrayList<String> prodotti`: Lista dei nomi dei prodotti nel carrello.
     - `ArrayList<Double> prezzi`: Lista dei prezzi dei prodotti.
     - `ArrayList<Double> pesi`: Lista dei pesi dei prodotti.
   - **Azioni**:
     - Pulisce il contenuto corrente del `cartListView`.
     - Se il carrello è vuoto, aggiunge un messaggio di avviso e un'immagine.
     - Se ci sono prodotti, crea un `HBox` per ogni prodotto con un `Label` e un pulsante "Rimuovi", e aggiorna il totale.

5. **`noProblemi()`**
   - **Descrizione**: Verifica che tutti i dati inseriti siano validi e che il carrello non sia vuoto.
   - **Variabili**:
     - `ArrayList<String> prodotti`: Lista dei prodotti nel carrello.
   - **Azioni**:
     - Controlla che la data sia valida, l'email sia corretta, il nome sia valido, e che il carrello non sia vuoto.
     - Verifica che l'email sia stata autenticata.
     - Restituisce `true` se tutti i controlli sono passati, altrimenti `false`.

6. **`autenticazioneEmail()`**
   - **Descrizione**: Gestisce l'autenticazione dell'email inviando una password temporanea.
   - **Variabili**:
     - `contEmail[]`: Array per gestire lo stato dell'autenticazione email e la password temporanea.
   - **Azioni**:
     - Verifica se l'email è valida.
     - Genera una password temporanea e la invia all'email dell'utente.
     - Aggiorna lo stato dell'autenticazione e il colore del bottone `bottConcludi` per indicare un errore in caso di email non valida.

7. **`verificaAutenticazione()`**
   - **Descrizione**: Verifica se la password inserita per l'email corrisponde a quella inviata.
   - **Variabili**:
     - `contEmail[]`: Array per gestire lo stato dell'autenticazione email e la password temporanea.
   - **Azioni**:
     - Confronta la password inserita con quella temporanea.
     - Aggiorna lo stato dell'autenticazione e il colore del bottone `bottConcludi` in base al risultato della verifica.

8. **`genPasswordTemp()`**
   - **Descrizione**: Genera una password temporanea a 6 cifre.
   - **Azioni**:
     - Usa `Random` per generare un numero casuale compreso tra 0 e 999999.
     - Restituisce la password generata.

9. **`isValidDate(DatePicker datePicker)`**
   - **Descrizione**: Verifica se la data selezionata è valida, cioè non è una data passata o un giorno festivo.
   - **Variabili**:
     - `LocalDate selectedDate`: Data selezionata dall'utente.
     - `LocalDate currentDate`: Data odierna.
   - **Azioni**:
     - Controlla se la data è vuota, passata o un giorno festivo.
     - Restituisce `true` se la data è valida, altrimenti `false`.

10. **`getPasqua(int year)`**
    - **Descrizione**: Calcola la data della Pasqua per un anno specifico utilizzando l'algoritmo di Meeus/Jones/Butcher.
    - **Variabili**:
      - `int year`: Anno per cui calcolare la data della Pasqua.
    - **Azioni**:
      - Esegue calcoli complessi per determinare il giorno e il mese della Pasqua.
      - Restituisce la data della Pasqua come `LocalDate`.

11. **`isNomeValido(String nome)`**
    - **Descrizione**: Verifica se il nome inserito è valido, consentendo solo lettere e apostrofi.
    - **Variabili**:
      - `String nome`: Nome da verificare.
    - **Azioni**:
      - Usa una espressione regolare per controllare il formato del nome.
      - Restituisce `true` se il nome è valido, altrimenti `false`.

12. **`isEmailValida(String email)`**
    - **Descrizione**: Verifica se l'email inserita è valida, seguendo un pattern standard.
    - **Variabili**:
      - `String email`: Email da verificare.
    - **Azioni**:
      - Usa una espressione regolare per controllare il formato dell'email.
      - Restituisce `true` se l'email è valida, altrimenti `false`.

13. **`arrotondaAlCent(double value)`**
    - **Descrizione**: Arrotonda un valore doppio a due decimali utilizzando `BigDecimal`.
    - **Variabili**:
      - `double value`: Valore da arrotondare.
    - **Azioni**:
      - Usa `BigDecimal` per arrotondare il valore a due decimali.
      - Restituisce il valore arrotondato.

14. **`populateOrariBox(ComboBox<String> combobox)`**
    - **Descrizione**: Popola un `ComboBox` con intervalli di tempo da 9:00 a 19:00 con incrementi di 30 minuti.
    - **Variabili**:
      - `ComboBox<String> combobox`: `ComboBox` da popolare.
    - **Azioni**:
      - Usa due cicli annidati per generare intervalli di tempo di 30 minuti e aggiungerli al `ComboBox`.

#### Metodi per il cambio scena:

I metodi seguenti cambiano la scena visualizzata nell'interfaccia, vedi descrizione precedente per un'approfondimento maggiore:
- **switchToProductsView(MouseEvent mouseEvent)**: Passa alla schermata dei prodotti.
- **switchToCart(MouseEvent mouseEvent)**: Passa alla schermata del carrello.
- **switchToFrutta(ActionEvent event)**: Passa alla schermata dei prodotti di frutta.
- **switchToVerduraSimple(ActionEvent event)**: Passa alla schermata dei prodotti di verdura semplice.
- **switchToPreparati(ActionEvent event)**: Passa alla schermata dei prodotti preparati.
- **switchToMenu(ActionEvent event)**: Torna al menu principale.

In ciascuno di questi metodi, vengono utilizzate variabili come `fxmlLoader`, `scene` e `stage` per caricare e impostare la nuova scena.

---

> ### ShoppingCart.java

### Descrizione delle variabili di classe

1. **`prodottiFile`**: Costante `String` che rappresenta il percorso del file per i nomi dei prodotti.
2. **`pesiFile`**: Costante `String` che rappresenta il percorso del file per i pesi dei prodotti.
3. **`prezziFile`**: Costante `String` che rappresenta il percorso del file per i prezzi dei prodotti.

### Descrizione dei metodi del codice Java

1. **`addProduct(String prodotto, double prezzo, double peso)`**
   - **Descrizione**: Aggiunge un prodotto con il suo nome, prezzo e peso, salvandoli rispettivamente nei file associati.
   - **Variabili**:
       - **`prodotto`**: Nome del prodotto da aggiungere.
       - **`prezzo`**: Prezzo del prodotto.
       - **`peso`**: Peso del prodotto.
   - **Azioni**:
       - Apre i file in modalità append e aggiunge i dettagli del prodotto nei rispettivi file.
       - Gestisce eventuali eccezioni legate all'I/O.

2. **`getProdotti()`**
   - **Descrizione**: Legge tutti i nomi dei prodotti salvati e li restituisce in un `ArrayList` di stringhe.
   - **Variabili**:
       - Nessuna variabile di input.
   - **Azioni**:
       - Apre il file dei prodotti e legge ogni riga, aggiungendola alla lista dei prodotti.
       - Restituisce l'`ArrayList` contenente i nomi dei prodotti.

3. **`getPesi()`**
   - **Descrizione**: Legge tutti i pesi dei prodotti salvati e li restituisce in un `ArrayList` di `Double`.
   - **Variabili**:
       - Nessuna variabile di input.
   - **Azioni**:
       - Apre il file dei pesi, legge e converte ogni riga in un valore `double` aggiungendolo alla lista dei pesi.
       - Restituisce l'`ArrayList` con i pesi.

4. **`getPrezzi()`**
   - **Descrizione**: Legge tutti i prezzi dei prodotti salvati e li restituisce in un `ArrayList` di `Double`.
   - **Variabili**:
        - Nessuna variabile di input.
   - **Azioni**:
       - Apre il file dei prezzi, legge e converte ogni riga in un valore `double` aggiungendolo alla lista dei prezzi.
       - Restituisce l'`ArrayList` con i prezzi.

5. **`removeProduct(int index)`**
   - **Descrizione**: Rimuove un prodotto e i suoi dati (peso e prezzo) in base all'indice fornito e aggiorna i file corrispondenti.
   - **Variabili**:
     - **`index`**: Indice del prodotto da rimuovere.
   - **Azioni**:
     - Ottiene le liste dei prodotti, pesi e prezzi dai file.
     - Rimuove il prodotto, il peso e il prezzo corrispondenti all'indice specificato.
     - Aggiorna i file sovrascrivendo le nuove liste.

6. **`clear()`**
   - **Descrizione**: Svuota completamente i file che contengono i prodotti, pesi e prezzi.
   - **Variabili**:
       - Nessuna variabile di input.
   - **Azioni**:
       - Svuota i tre file riscrivendoli con contenuto vuoto.

7. **`salvaListaSuFile(List<T> lista, String filePath)`**
   - **Descrizione**: Salva una lista generica di oggetti in un file specificato, sovrascrivendo il contenuto esistente.
     - **Variabili**:
         - **`lista`**: Lista di elementi generici da salvare.
         - **`filePath`**: Percorso del file dove salvare la lista.
       - **Azioni**:
           - Scrive ogni elemento della lista nel file specificato.
           - Gestisce eventuali eccezioni legate all'I/O.

8. **`arrotondaAlCent(double value)`**
   - **Descrizione**: Arrotonda un valore `double` a due decimali utilizzando `BigDecimal`.
   - **Variabili**:
       - **`double value`**: Valore da arrotondare.
   - **Azioni**:
       - Usa `BigDecimal` per arrotondare il valore a due decimali.
       - Restituisce il valore arrotondato.

   
---

> ### invioEmail.java

### Descrizione delle variabili di classe

   - **`props`**: Proprietà per configurare il server SMTP di Gmail.
   - **`session`**: Sessione email con autenticazione.
   - **`message`**: Messaggio email da inviare.

### Descrizione dei metodi del codice Java

1. **`sendMail(String dest, String mitt, String oggetto, String testoEmail)`**
   - **Descrizione**: Configura e invia un'email utilizzando il server SMTP di Gmail. Questo metodo è utilizzato per inviare messaggi di posta elettronica specificando il destinatario, il mittente, l'oggetto e        il corpo del messaggio.
   - **Variabili**:
     - **`dest`**: Indirizzo email del destinatario.
     - **`mitt`**: Indirizzo email del mittente.
     - **`oggetto`**: Oggetto dell'email (titolo).
     - **`testoEmail`**: Corpo del messaggio dell'email.
     - **`props`**: Proprietà di configurazione per il server SMTP, utilizzate per impostare le credenziali e le opzioni di sicurezza.
     - **`session`**: Sessione email configurata con le proprietà e l'autenticazione.
     - **`message`**: Oggetto `MimeMessage` che rappresenta il messaggio email da inviare.
     - **`fromAddress`**: Indirizzo email del mittente creato come oggetto `InternetAddress`.
     - **`toAddress`**: Indirizzo email del destinatario creato come oggetto `InternetAddress`.
   - **Azioni**:
     - **Configurazione delle Proprietà**: Imposta le proprietà per il server SMTP di Gmail, inclusi l'host, la porta, l'autenticazione e la sicurezza TLS.
     - **Creazione della Sessione**: Crea una sessione di email utilizzando le proprietà configurate e un autenticatore con le credenziali dell'email dell'app.
     - **Creazione del Messaggio**: Utilizza la sessione per creare un messaggio email (`MimeMessage`), impostando l'oggetto e il testo del messaggio.
     - **Impostazione degli Indirizzi**: Configura l'indirizzo del mittente e del destinatario nel messaggio.
     - **Invio dell'Email**: Usa il metodo `Transport.send(message)` per inviare l'email tramite il server SMTP configurato.

---

> ## Implementazioni possibili
   Ecco al seguito una possibile lista di future implementazioni:
   - Donazioni in base alla spesa per la conclusione;
   - Implementazioni per modifica di prodotti da remoto;
   - Implementazione di azioni grafiche migliori;
   - Implementazione di servizio di consegna;
   - ~~Implementazione di salvataggio del carrello in locale;~~
   - Implementazione di servizio di registrazione account.
> ## Conclusioni
Lavoro realizzato da me con aiuto di dispense in internet.
Grazie per la lettura.
