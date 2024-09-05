package com.example.demo1;

import java.util.ArrayList;

public class ShoppingCart {
    //Design Singleton, ovviamente ho visto su internet ed ho cercato più o meno di capirlo
    private static final ShoppingCart instance = new ShoppingCart();
    //Immutabile: Il modificatore final indica che la variabile non può essere modificata dopo che è stata inizializzata. In altre parole, una volta assegnata l'istanza a instance, questa non potrà mai essere cambiata o riassegnata.

    //Tre array list per salvare prodotti pesi e prezzi
    private ArrayList<String> prodotti = new ArrayList<>();
    private ArrayList<Double> pesi = new ArrayList<>();
    private ArrayList<Double> prezzi = new ArrayList<>();

    // Metodo pubblico statico per accedere all'istanza unica
    // Questo metodo restituisce l'unica istanza della classe ShoppingCart.
    // È statico, quindi può essere chiamato senza creare un'istanza della classe.
    public static ShoppingCart getInstance() {
        return instance;  // Ritorna l'istanza unica della classe (singleton)
        //Quando rendi il costruttore di una classe privato, impedisci che chiunque al di fuori di
        //quella classe possa usare il costruttore per creare oggetti (istanze) di quella classe.
    }

    // Metodi per aggiungere prodotto
    // Questo metodo permette di aggiungere un prodotto al carrello.
    // I parametri includono il nome del prodotto, il prezzo e il peso.
    public void addProduct(String prodotto, double prezzo, double peso) {
        // Aggiunge il nome del prodotto alla lista prodotti.
        prodotti.add(prodotto);

        // Aggiunge il prezzo del prodotto alla lista prezzi.
        prezzi.add(prezzo);

        // Aggiunge il peso del prodotto alla lista pesi.
        pesi.add(peso);
    }

    // Metodo per accedere alla lista dei prodotti
    // Restituisce la lista di stringhe contenente i nomi dei prodotti.
    public ArrayList<String> getProdotti() {
        return prodotti;  // Restituisce la lista di prodotti
    }

    // Metodo per accedere alla lista dei pesi
    // Restituisce la lista di numeri che rappresentano i pesi dei prodotti.
    public ArrayList<Double> getPesi() {
        return pesi;  // Restituisce la lista dei pesi
    }

    // Metodo per accedere alla lista dei prezzi
    // Restituisce la lista di numeri che rappresentano i prezzi dei prodotti.
    public ArrayList<Double> getPrezzi() {
        return prezzi;  // Restituisce la lista dei prezzi
    }

    // Pulizia del carrello
    // Questo metodo rimuove tutti i prodotti, i pesi e i prezzi dal carrello.
    // Viene chiamato quando si vuole svuotare il carrello.
    public void clear() {
        prodotti.clear();  // Svuota la lista dei prodotti
        pesi.clear();      // Svuota la lista dei pesi
        prezzi.clear();    // Svuota la lista dei prezzi
    }

    // Rimozione di un prodotto specifico
    // Questo metodo rimuove un prodotto specifico dal carrello in base all'indice fornito.
    // Controlla che l'indice sia valido, cioè che rientri nel numero di elementi presenti.
    public void removeProduct(int index) {
        // Se l'indice è valido, rimuove l'elemento corrispondente da tutte e tre le liste.
        if (index >= 0 && index < prodotti.size()) {
            prodotti.remove(index);  // Rimuove il prodotto dalla lista dei prodotti
            prezzi.remove(index);    // Rimuove il prezzo dalla lista dei prezzi
            pesi.remove(index);      // Rimuove il peso dalla lista dei pesi
        }
    }

}
