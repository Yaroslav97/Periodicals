package ua.nure.poliakov.utils.email;

import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
    private static final String myEmail = "yaroslav.poliakov@gmail.com";
    private static final String password = "py19970429";

    public static void sendMail(String email, String message) throws MessagingException {
        Message mimeMessage = new MimeMessage(getSession());
        mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
        mimeMessage.setSubject("Periodicals info");
        mimeMessage.setText(message);
        Transport.send(mimeMessage);
    }

    private static Session getSession() {
        Session session = Session.getDefaultInstance(getProperties(),
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(myEmail, password);
                    }
                });
        return session;
    }

    private static Properties getProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");
        return properties;
    }

    public static String restoreAccess(String name, String password) {
        return "Hello " + name + ", your new password - " + password + "." + System.lineSeparator() +
                "http://localhost:8080/index";
    }

    public static String randomPassword() {
        Random random = new Random();
        return String.valueOf(random.nextInt(8999) + 1000);
    }
}