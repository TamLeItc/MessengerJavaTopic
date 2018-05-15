/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author itcde
 */
public class EmailSender {

    public static boolean sendMail(String emailAddressReceived, String message) {
        String emailAddressSent = "talk.lettalk@gmail.com";
        String password = "password";
        try {
            String host = "smtp.gmail.com";
            Properties props = System.getProperties();
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.props", 587);
            props.put("mail.smtp.auth", "true");

            Authenticator auth;
            auth = new Authenticator() {

                @Override
                public PasswordAuthentication getPasswordAuthentication() {
                    PasswordAuthentication passAu = new PasswordAuthentication(emailAddressSent, password);
                    return passAu;
                }
            };

            Session session = Session.getDefaultInstance(props, auth);
            MimeMessage mimeMessage = new MimeMessage(session);
            
            mimeMessage.setFrom(new InternetAddress(emailAddressSent));
            InternetAddress toAddress = new InternetAddress();
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(emailAddressReceived));
            mimeMessage.setContent(message, "text/html");
            
            Transport.send(mimeMessage);
        } catch (MessagingException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
        return true;
    }
    
    public static void main(String[] args) {
        EmailSender.sendMail("itcdeveloper14@gmail.com", "test");
    }
}
