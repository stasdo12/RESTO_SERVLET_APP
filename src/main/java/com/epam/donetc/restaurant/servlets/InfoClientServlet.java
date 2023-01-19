package com.epam.donetc.restaurant.servlets;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

@WebServlet("/info")
public class InfoClientServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        req.getRequestDispatcher("/WEB-INF/jsp/info.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
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
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        try {
            msg.setSubject("Epam RESTAURANT");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        try {
            msg.setSentDate(new Date());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        try {
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
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        try {
            transport.sendMessage(msg, msg.getAllRecipients());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        try {
            transport.close();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        req.getRequestDispatcher("/WEB-INF/jsp/info.jsp").forward(req, resp);

    }


}
