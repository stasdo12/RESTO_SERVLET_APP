package com.epam.donetc.restaurant.controller.command.post;

import com.epam.donetc.restaurant.controller.ICommand;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Pattern;

public class SendEmail implements ICommand {


    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final Document document = new Document();
    private static final Pattern USERNAME_PATTERN_REGEX = Pattern.compile("[a-zA-Z0-9]+$");
    private static final Pattern EMAIL_PATTERN_REGEX = Pattern.compile("^[\\w.-]+@[a-zA-Z\\d-]+(?:\\.[a-zA-Z\\d-]+)*$");

    private static final Pattern USERNAME_PATTERN = Pattern.compile(String.valueOf(USERNAME_PATTERN_REGEX));

    private static final Pattern EMAIL_PATTERN = Pattern.compile(String.valueOf(EMAIL_PATTERN_REGEX));


    /**
     * Called from doPost method in front-controller. Tries to send email from database.
     * Logs error in case if not able
     *
     * @return path to redirect to execute Get method through front-controller
     */
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        if (!USERNAME_PATTERN.matcher(name).matches())
            throw new IllegalArgumentException("Invalid name format");
        String email = req.getParameter("email");
        if (!EMAIL_PATTERN.matcher(email).matches())
            throw new IllegalArgumentException("Invalid email format");
        String message = req.getParameter("message");
        String username = "stanislavdonetc@gmail.com";
        String password = "ehbejtlxthtjuosb";
        String recipient = "santeh.vodavdom@gmail.com";


        try {
            Image image = Image.getInstance("C:\\Users\\sante\\IdeaProjects\\restourant\\src\\main\\webapp\\images\\forEmail.jpg");
            image.setAbsolutePosition(450, 700);
            image.scaleToFit(200, 200);
            PdfPTable pdfPTable = new PdfPTable(3);
            pdfPTable.addCell("Name");
            pdfPTable.addCell("Email");
            pdfPTable.addCell("Message");
            pdfPTable.addCell(name);
            pdfPTable.addCell(email);
            pdfPTable.addCell(message);
            pdfPTable.setWidths(new float[]{1, 2, 2});
            pdfPTable.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfWriter.getInstance(document, outputStream);
            document.open();
            document.add(image);
            document.add(pdfPTable);
//            document.add(new Paragraph("Email : " + email));
//            document.add(new Paragraph("Message : " + message));
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        byte[] pdfBytes = outputStream.toByteArray();

        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.from", "stanislavdonetc@gmail.com");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "587");
        props.setProperty("mail.debug", "true");

        Session session = Session.getInstance(props, null);
        MimeMessage msg = new MimeMessage(session);
        DataSource dataSource = new ByteArrayDataSource(pdfBytes, "application/pdf");
        BodyPart pdfAttachment = new MimeBodyPart();
        Multipart multipart = new MimeMultipart();

        try {
            msg.setRecipients(Message.RecipientType.TO, recipient);
            msg.setSubject("Epam RESTAURANT");
            msg.setSentDate(new Date());
            msg.setText(name + email + message);
            pdfAttachment.setDataHandler(new DataHandler(dataSource));
            pdfAttachment.setFileName("EPAM_RESTO.pdf");
            multipart.addBodyPart(pdfAttachment);
            msg.setContent(multipart);
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