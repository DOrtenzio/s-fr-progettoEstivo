package com.example.demo1;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class invioEmail {
    public static void sendMail (String dest, String mitt, String oggetto, String testoEmail)
            throws MessagingException
    {
        // Configura le proprietà per il server SMTP di Gmail
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // Host SMTP di Gmail
        props.put("mail.smtp.port", "587"); // Porta per TLS
        props.put("mail.smtp.auth", "true"); // Richiede autenticazione
        props.put("mail.smtp.starttls.enable", "true"); // Abilita TLS

        // Crea una sessione con l'autenticazione
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // Inserisci il tuo indirizzo email e la password di applicazione
                return new PasswordAuthentication("fruttaealtro.orderemail@gmail.com", "rhwb jiqp rhdv lbfg");
            }
        });

        // Creazione del messaggio da inviare
        MimeMessage message = new MimeMessage(session);
        message.setSubject(oggetto);
        message.setText(testoEmail);

        // Aggiunta degli indirizzi del mittente e del destinatario
        InternetAddress fromAddress = new InternetAddress(mitt);
        InternetAddress toAddress = new InternetAddress(dest);
        message.setFrom(fromAddress);
        message.setRecipient(Message.RecipientType.TO, toAddress);

        // Invio del messaggio
        Transport.send(message);
    }
}