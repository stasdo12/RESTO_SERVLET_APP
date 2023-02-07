package com.epam.donetc.restaurant.сontroller.command.post;

import com.epam.donetc.restaurant.сontroller.ICommand;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

public class SendEmail implements ICommand {

    /**
     * Called from doPost method in front-controller. Tries to send email from database.
     * Logs error in case if not able
     *
     * @return path to redirect to execute Get method through front-controller
     */
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp)  {
        try {
            req.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String message = req.getParameter("message");
        String username = "stanislavdonetc@gmail.com";
        String password = "ehbejtlxthtjuosb";
        String recipient = "santeh.vodavdom@gmail.com";

        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.from", "stanislavdonetc@gmail.com");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "587");
        props.setProperty("mail.debug", "true");

        Session session = Session.getInstance(props, null);
        MimeMessage msg = new MimeMessage(session);

        try {
            msg.setRecipients(Message.RecipientType.TO, recipient);
            msg.setSubject("Epam RESTAURANT");
            msg.setSentDate(new Date());
            msg.setText(name + email + message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        Transport transport = null;
        try {
            transport = session.getTransport("smtp");
        } catch (NoSuchProviderException e) {
            throw new RuntimeException(e);
        }

        try {
            transport.connect(username, password);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return req.getContextPath() + "/controller?command=info";

    }


}

