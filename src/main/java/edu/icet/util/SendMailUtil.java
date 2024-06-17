package edu.icet.util;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import javafx.scene.control.Alert;

import java.util.Properties;

public class SendMailUtil {

    private String tomail;

    private String otp;

    private String reason;

    public SendMailUtil(String tomail,String otp,String reason){
        this.tomail = tomail;
        this.otp = otp;
        this.reason = reason;
    }
    public void SendMail(){
        final String username = "madurasinghekldp@gmail.com";
        final String password = "uincwwivxrtdgtva";

        try {
            Properties prop = new Properties();
            prop.put("mail.smtp.host", "smtp.gmail.com");
            prop.put("mail.smtp.port", "465");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.socketFactory.port", "465");
            prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

            Session session = Session.getInstance(prop,
                    new jakarta.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });



            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("madurasinghekldp@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(tomail)
            );
            message.setSubject("Welcome to Clothify Store");
            message.setText("Hi,"
                    + "\n\n Please use "+otp+" as OTP for "+reason+""
                    + "\nThank you.");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            new Alert(Alert.AlertType.ERROR,"Network Error!").show();
        }
    }
}
