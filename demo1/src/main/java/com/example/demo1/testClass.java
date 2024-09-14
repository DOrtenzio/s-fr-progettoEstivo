package com.example.demo1; // Definisce il package del file

import java.io.*; // Importa le classi necessarie per la gestione di input/output
import java.util.ArrayList; // Importa la classe ArrayList per le liste dinamiche
import java.util.List; // Importa la classe List, una interfaccia per liste

public class testClass { // Definizione della classe principale

    // Percorsi dei file
    private static final String prodottiFile = "src/main/java/com/example/demo1/save/1.txt"; // Percorso del file che salva i nomi dei prodotti
    private static final String pesiFile = "src/main/java/com/example/demo1/save/2.txt"; // Percorso del file che salva i pesi
    private static final String prezziFile = "src/main/java/com/example/demo1/save/3.txt"; // Percorso del file che salva i prezzi

    // Metodo per aggiungere un prodotto e salvarlo subito su file
    public static void addProduct(String prodotto, double prezzo, double peso) {
        try (
                // Apre i file per la scrittura aggiuntiva (append) utilizzando BufferedWriter
                BufferedWriter writerProdotti = new BufferedWriter(new FileWriter(prodottiFile, true));
                BufferedWriter writerPesi = new BufferedWriter(new FileWriter(pesiFile, true));
                BufferedWriter writerPrezzi = new BufferedWriter(new FileWriter(prezziFile, true))) {

            // Aggiunge il nome del prodotto su 1.txt
            writerProdotti.write(prodotto); // Scrive il nome del prodotto
            writerProdotti.newLine(); // Va a capo

            // Aggiunge il prezzo su 3.txt
            writerPrezzi.write(Double.toString(prezzo)); // Scrive il prezzo convertito in stringa
            writerPrezzi.newLine(); // Va a capo

            // Aggiunge il peso su 2.txt
            writerPesi.write(Double.toString(peso)); // Scrive il peso convertito in stringa
            writerPesi.newLine(); // Va a capo

        } catch (IOException e) { // Gestisce eventuali errori durante la scrittura
            System.out.println("Errore durante la scrittura su file: " + e.getMessage()); // Stampa un messaggio d'errore
        }
    }

    // Metodo per leggere tutti i prodotti da 1.txt
    public static ArrayList<String> getProdotti() {
        ArrayList<String> prodotti = new ArrayList<>(); // Crea una lista per i prodotti
        try (BufferedReader reader = new BufferedReader(new FileReader(prodottiFile))) { // Apre il file prodotti per la lettura
            String linea;
            while ((linea = reader.readLine()) != null) { // Legge ogni riga fino a fine file
                prodotti.add(linea); // Aggiunge ogni riga alla lista prodotti
            }
        } catch (IOException e) { // Gestisce eventuali errori durante la lettura
            System.out.println("Errore durante la lettura del file prodotti: " + e.getMessage()); // Stampa un messaggio d'errore
        }
        return prodotti; // Ritorna la lista dei prodotti
    }

    // Metodo per leggere tutti i pesi da 2.txt
    public static ArrayList<Double> getPesi() {
        ArrayList<Double> pesi = new ArrayList<>(); // Crea una lista per i pesi
        try (BufferedReader reader = new BufferedReader(new FileReader(pesiFile))) { // Apre il file pesi per la lettura
            String linea;
            while ((linea = reader.readLine()) != null) { // Legge ogni riga fino a fine file
                pesi.add(Double.parseDouble(linea)); // Converte la riga in double e la aggiunge alla lista pesi
            }
        } catch (IOException e) { // Gestisce eventuali errori durante la lettura
            System.out.println("Errore durante la lettura del file pesi: " + e.getMessage()); // Stampa un messaggio d'errore
        }
        return pesi; // Ritorna la lista dei pesi
    }

    // Metodo per leggere tutti i prezzi da 3.txt
    public static ArrayList<Double> getPrezzi() {
        ArrayList<Double> prezzi = new ArrayList<>(); // Crea una lista per i prezzi
        try (BufferedReader reader = new BufferedReader(new FileReader(prezziFile))) { // Apre il file prezzi per la lettura
            String linea;
            while ((linea = reader.readLine()) != null) { // Legge ogni riga fino a fine file
                prezzi.add(Double.parseDouble(linea)); // Converte la riga in double e la aggiunge alla lista prezzi
            }
        } catch (IOException e) { // Gestisce eventuali errori durante la lettura
            System.out.println("Errore durante la lettura del file prezzi: " + e.getMessage()); // Stampa un messaggio d'errore
        }
        return prezzi; // Ritorna la lista dei prezzi
    }

    // Metodo per rimuovere un prodotto in base all'indice
    public static void removeProduct(int index) {
        try {
            // Rimuove i dati dai tre file
            List<String> prodotti = getProdotti(); // Ottiene la lista dei prodotti
            List<Double> pesi = getPesi(); // Ottiene la lista dei pesi
            List<Double> prezzi = getPrezzi(); // Ottiene la lista dei prezzi

            // Controllo dell'indice
            if (index >= 0 && index < prodotti.size()) { // Verifica che l'indice sia valido
                prodotti.remove(index); // Rimuove il prodotto dall'indice
                pesi.remove(index); // Rimuove il peso corrispondente
                prezzi.remove(index); // Rimuove il prezzo corrispondente

                // Salva le nuove liste aggiornate nei file
                salvaListaSuFile(prodotti, prodottiFile); // Salva la lista aggiornata dei prodotti
                salvaListaSuFile(pesi, pesiFile); // Salva la lista aggiornata dei pesi
                salvaListaSuFile(prezzi, prezziFile); // Salva la lista aggiornata dei prezzi
            }
        } catch (Exception e) { // Gestisce eventuali errori durante la rimozione
            System.out.println("Errore durante la rimozione del prodotto: " + e.getMessage()); // Stampa un messaggio d'errore
        }
    }

    // Metodo per svuotare i file
    public static void clear() {
        try {
            // Svuota i file riscrivendoli vuoti
            new FileWriter(prodottiFile, false).close(); // Svuota il file dei prodotti
            new FileWriter(pesiFile, false).close(); // Svuota il file dei pesi
            new FileWriter(prezziFile, false).close(); // Svuota il file dei prezzi
        } catch (IOException e) { // Gestisce eventuali errori durante la pulizia
            System.out.println("Errore durante la pulizia dei file: " + e.getMessage()); // Stampa un messaggio d'errore
        }
    }

    // Metodo per salvare una lista su file
    private static <T> void salvaListaSuFile(List<T> lista, String filePath) {//Questa è la definizione di un tipo generico. Significa che questo metodo può lavorare con una lista di qualsiasi tipo di oggetti, che siano stringhe, numeri, o qualsiasi altra classe.
        try (
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) { // Apre il file per la scrittura
            for (T elemento : lista) { // Scorre tutti gli elementi della lista
                //T elemento: elemento è un singolo oggetto della lista, di tipo generico T
                writer.write(elemento.toString()); // Scrive l'elemento sul file
                writer.newLine(); // Va a capo
            }
        } catch (IOException e) { // Gestisce eventuali errori durante la scrittura
            System.out.println("Errore durante la scrittura della lista su file: " + e.getMessage()); // Stampa un messaggio d'errore
        }
    }

}
