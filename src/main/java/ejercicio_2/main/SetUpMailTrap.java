package ejercicio_2.main;



import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;

import java.util.Properties;

public class SetUpMailTrap {

    private static final String USERNAME = "7745afec1e364b";
    private static final String PASSWORD = "58c1826c0780a7";
    private static final String HOST = "sandbox.smtp.mailtrap.io";
    private static final String PORT = "587";

    public static Session crearSession() {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", HOST);
        props.put("mail.smtp.port", PORT);

        return Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });
    }

    public static String getOrigen() {
        return "test@mailtrap.io";
    }
}