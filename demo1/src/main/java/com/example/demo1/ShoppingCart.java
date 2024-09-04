package com.example.demo1;

import java.util.ArrayList;

public class ShoppingCart {
    //Design Singleton, ovviamente ho visto su internet ed ho cercato piùo meno di capirlo
    private static final ShoppingCart instance = new ShoppingCart();
    //final = Questo modificatore indica che la variabile instance non può essere modificata una volta che è stata inizializzata.

    //Tre array list per salvare prodotti pesi e prezzi
    private ArrayList<String> prodotti = new ArrayList<>();
    private ArrayList<Double> pesi = new ArrayList<>();
    private ArrayList<Double> prezzi = new ArrayList<>();

    // Costruttore privato per prevenire la creazione di istanze al di fuori della classe
    private ShoppingCart() {}

    // Metodo pubblico statico per accedere all'istanza unica
    public static ShoppingCart getInstance() {
        return instance;
    }

    //Metodi per aggiungere prodotto
    public void addProduct(String prodotto, double prezzo, double peso) {
        prodotti.add(prodotto);
        prezzi.add(prezzo);
        pesi.add(peso);
    }

    //metodi per accedere in loco
    public ArrayList<String> getProdotti() {
        return prodotti;
    }

    public ArrayList<Double> getPesi() {
        return pesi;
    }

    public ArrayList<Double> getPrezzi() {
        return prezzi;
    }

    //Pulizia prodotti
    public void clear() {
        prodotti.clear();
        pesi.clear();
        prezzi.clear();
    }
    public void removeProduct(int index) {
        if (index >= 0 && index < prodotti.size()) {
            prodotti.remove(index);
            prezzi.remove(index);
            pesi.remove(index);
        }
    }
}
