//Author: Ian Hamilton - 15003706
package administration;

import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * This class sends an email to a user
 * The email is either a notification that they are a new user or that
 * they have recently reset their password.
 * @author Ian Hamilton - 15003706
 */
public class Mail {

    private static int option, userID;
    static String name, password, emailAddress;

    /**
     * Constructor
     * @param option
     * @param userID
     * @param FName
     * @param LName
     * @param password
     * @param email
     * @throws MessagingException 
     */
    public Mail(int option, int userID, String FName, String LName, String password, String email) throws MessagingException {
        Mail.option = option;
        Mail.userID = userID;
        Mail.name = (FName + " " + LName);
        Mail.password = password;
        Mail.emailAddress = email;

        main();
    }

    /**
     * Mail sending functionality
     * @throws MessagingException 
     */
    public static void main() throws MessagingException {
        final String EmailUsername = "teamprojemail@gmail.com";
        final String EmailPassword = "KV6002PW";
        String toEmail = Mail.emailAddress;
        String fromEmail = "teamprojemail@gmail.com";

        //--Set the mailing properties
        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        //-Add security provider information to authenticate the sender
        java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

        //-Create a new mailing session instance
        Session mailSession = Session.getDefaultInstance(props, null);
        mailSession.setDebug(false); //Set true to debug functionality

        //-Create new message framework
        Message message = new MimeMessage(mailSession);
        //-Set the from address
        message.setFrom(new InternetAddress(fromEmail));

        //-Set the intended recipient of the email
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        
        //--Set the email subject, sent date, and message contents
        message.setSubject("Maths Interactive");
        message.setSentDate(new Date());
        String messageContent = "ERROR CREATING MESSAGE";

        if (option == 1) { //Forgot Password email
            messageContent = ("<p>Dear " + Mail.name + ",</p>"
                    + "<p>You have recently reset your password for Maths Interactive.</p>"
                    + "<p><strong>Username:</strong> " + Integer.toString(Mail.userID) + "</p>"
                    + "<p><strong>Password:</strong> " + Mail.password + "</p>"
                    + "<p>&nbsp;</p>"
                    + "<p>You will be required to create a new password upon loging in with the above credentials.</p>"
                    + "<p>Thank you.</p>");

        } else if (option == 2) { //New User email
            messageContent = ("<p>Dear " + Mail.name + ",</p>"
                    + "<p>You have recently been added as a user for Maths Interactive.</p>"
                    + "<p><strong>Username:</strong> " + Integer.toString(Mail.userID) + "</p>"
                    + "<p><strong>Password:</strong> " + Mail.password + "</p>"
                    + "<p>&nbsp;</p>"
                    + "<p>You will be required to create a new password upon logging in with the above credentials.</p>"
                    + "<p>Thank you.</p>");
        }

        message.setContent(messageContent, "text/html; charset=utf-8");

        //--Send the email using the above properties using smtp
        Transport transport = mailSession.getTransport("smtp");
        transport.connect("smtp.gmail.com", EmailUsername, EmailPassword);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
}
