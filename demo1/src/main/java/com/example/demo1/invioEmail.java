package com.example.demo1;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class invioEmail {
    public static void sendMail(String dest, String mitt, String oggetto, String testoEmail) throws MessagingException {
        // Configura le proprietà per il server SMTP di Gmail
        Properties props = new Properties();

        // Imposta l'host SMTP di Gmail (il server che invia le email)
        props.put("mail.smtp.host", "smtp.gmail.com");

        // Imposta la porta 587, che è utilizzata per la comunicazione tramite TLS (Transport Layer Security)
        props.put("mail.smtp.port", "587");

        // Richiede autenticazione per inviare email tramite il server SMTP di Gmail
        props.put("mail.smtp.auth", "true");

        // Abilita la sicurezza TLS (crittografia) per la connessione SMTP
        props.put("mail.smtp.starttls.enable", "true");

        // Crea una sessione email con le proprietà definite sopra e un autenticatore per l'email
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // Restituisce l'autenticazione con il nome utente (email) e la password dell'app
                return new PasswordAuthentication("fruttaealtro.orderemail@gmail.com", "rhwb jiqp rhdv lbfg");
            }
        });

        // Creazione del messaggio email utilizzando la sessione configurata
        MimeMessage message = new MimeMessage(session);

        // Imposta l'oggetto (titolo) dell'email
        message.setSubject(oggetto);

        // Imposta il testo del corpo dell'email
        message.setText(testoEmail);

        // Creazione dell'indirizzo email del mittente e del destinatario
        InternetAddress fromAddress = new InternetAddress(mitt); // Indirizzo del mittente
        InternetAddress toAddress = new InternetAddress(dest);   // Indirizzo del destinatario

        // Imposta l'indirizzo del mittente nel messaggio
        message.setFrom(fromAddress);

        // Imposta l'indirizzo del destinatario nel messaggio
        message.setRecipient(Message.RecipientType.TO, toAddress);

        // Invio del messaggio email tramite il server SMTP configurato
        Transport.send(message);
    }

}