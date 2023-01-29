import com.epam.donetc.restaurant.database.ReceiptDAO;
import com.epam.donetc.restaurant.database.entity.Dish;
import com.epam.donetc.restaurant.database.entity.Receipt;
import com.epam.donetc.restaurant.exeption.DBException;
import com.epam.donetc.restaurant.service.DishService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

public class TestFoeMe {
    public static void main(String[] args) throws DBException {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("ITextPdfHelloWorld"));
            document.open();
            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
            Chunk chunk = new Chunk("Hello world", font);
            document.add(chunk);
            document.close();
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}