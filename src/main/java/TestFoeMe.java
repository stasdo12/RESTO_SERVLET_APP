import com.epam.donetc.restaurant.database.ConnectionManager;
import com.epam.donetc.restaurant.database.DBManager;
import com.epam.donetc.restaurant.database.DishDAO;
import com.epam.donetc.restaurant.database.entity.Category;
import com.epam.donetc.restaurant.database.entity.Dish;
//import com.epam.donetc.restaurant.database.entity.Receipt;
//import com.epam.donetc.restaurant.exeption.DBException;
//import com.epam.donetc.restaurant.service.CartService;
//import com.epam.donetc.restaurant.service.DishService;
//import com.epam.donetc.restaurant.service.ReceiptService;
//import com.epam.donetc.restaurant.util.PropertiesUtil;
//import org.apache.commons.codec.digest.DigestUtils;
//import org.postgresql.util.MD5Digest;
//
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Connection;
import java.util.*;
import java.util.concurrent.BlockingQueue;

//
//import com.epam.donetc.restaurant.database.ConnectionManager;
//import com.epam.donetc.restaurant.database.DBManager;
//import com.epam.donetc.restaurant.exeption.DBException;
//
//import javax.mail.*;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class TestFoeMe {
//    public static void main(String[] args) throws DBException, SQLException, IOException, MessagingException {
//
////        User user1 = UserManager.signUp("user", "user");
////        System.out.println(user1);
////
////        User user = UserDAO.logIn("user", "user");
////        System.out.println(user);
////        User login = UserDAO.getUserById(1);
////        System.out.println(login);
//
////        User login1 = UserManager.getUserByLogin("login1");
////        System.out.println(login1);
////        User login1 = UserManager.cre
//////
////        Dish dishByID = DishManager.getDishByID(1);
////        System.out.println(dishByID);
////        List<Dish> dishesByCategory = DishManager.getDishesByCategory("Pizza");
////        System.out.println(dishesByCategory);
////
////        List<Dish> dishList = DishDAO.getAllDishes();
////        System.out.println(dishList);
//////
////        Connection con= ConnectionManager.get();
////        System.out.println(con);
//
////        List<Dish> dishes = DishManager.getDishesOnePage(DishManager.getAllDishes(), 1);
////        System.out.println(dishes);
//
////        CartManager.addDishToCart(2,13,2);
////
////        List<Receipt> allReceipt = ReceiptManager.getAllReceipt();
////        System.out.println(allReceipt);
//
////        List<Receipt> receiptByUserId = ReceiptManager.getReceiptByUserId(2);
////        List<Receipt> allReceipt = ReceiptManager.getAllReceipt();
//
////        ReceiptManager.getReceiptOnPage(allReceipt, 1);
////        String username = "stanislavdonetc@gmail.com";
////        String password = "ehbejtlxthtjuosb";
////        String recipient = "santeh.vodavdom@gmail.com";
////
////        Properties props = new Properties();
////
////        props.put("mail.smtp.host", "smtp.gmail.com");
////        props.put("mail.from", "stanislavdonetc@gmail.com");
////        props.put("mail.smtp.starttls.enable", "true");
////        props.put("mail.smtp.port", "587");
////        props.setProperty("mail.debug", "true");
////
////        Session session = Session.getInstance(props, null);
////        MimeMessage msg = new MimeMessage(session);
////
////        msg.setRecipients(Message.RecipientType.TO, recipient);
////        msg.setSubject("JavaMail hello world example");
////        msg.setSentDate(new Date());
////        msg.setText("Hello, world!\n");
////
////        Transport transport = session.getTransport("smtp");
////
////        transport.connect(username, password);
////        transport.sendMessage(msg, msg.getAllRecipients());
////        transport.close();
//
//
////        boolean orange_juice = DishDAO.changeDishByName("Orange juice1",  "Orange juice");
////        System.out.println(orange_juice);
//
//
////        DishDAO.changePriceInDish(999, "Orange juice1");
////        DishDAO.changePriceInDish(12333, "Orange juice1");
//
//        int pageSize = 10;
//
//        // The page index, or page number (1-based index)
//        int pageIndex = 2;
//
//        // Calculate the offset for the page based on the page size and page index
//        int offset = (pageIndex - 1) * pageSize;
//
//
//
//        try (Connection conn = ConnectionManager.get()) {
//            // Create a prepared statement and set the placeholders to the calculated offset and page size
//            PreparedStatement stmt = conn.prepareStatement(DBManager.PAGINATION);
//            stmt.setBo(1, (3*5) -4);
//            stmt.setInt(2, 3*5);
//
//            // Execute the query and process the result set
//            ResultSet rs = stmt.executeQuery();
//            rs = stmt.executeQuery();
//            while (rs.next()) {
//                System.out.print(rs.getInt(1)+" ");
//                System.out.println(rs.getString(2));
//            }
//
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//            }
//        }
//
//
//
//
//public class TestFoeMe {
//
//        public static void main(String[] args) throws UnsupportedEncodingException, DBException {
//
//                ReceiptService receiptService = new ReceiptService();
//                String address = receiptService.getAddress(64);
//                System.out.println(address);
//
////                CartService cartService = new CartService();
////                cartService.changeAddress(2, "New addresss");
//                DishService dishService = new DishService();
//                dishService.addDish("Имя", 1,1,1,"Слово");

//DishService dishService = new DishService();
////dishService.changeDishAllValues("Orange Juce", 1, 2, Category.DRINK, "aa", 2);
//
//
//                CartService cartService = new CartService();
//                cartService.changeAddress(18, "DDDD");
//                Locale locale = request.getLocale();
//                String language = locale.getLanguage();
//                String country = locale.getCountry();


//                DishService dishService = new DishService();
//                List<Dish> allDishes = dishService.getAllDishes();
//                System.out.println(allDishes);
//                String name = "VeraDonetc";
//                System.out.println(name.length());
//            String password = "adsdadadadasd";
//            try {
//                MessageDigest md = MessageDigest.getInstance("SHA-1");
//                md.update(password.getBytes());
//                byte[] hash = md.digest();
//                StringBuilder sb = new StringBuilder();
//                for (byte b : hash) {
//                    sb.append(String.format("%02x", b));
//                }
//                String encryptedPassword = sb.toString();
//                System.out.println("Encrypted password: " + encryptedPassword);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            String password = "user";
////
//            String md5Hex = DigestUtils.md5Hex("clientsalt4322");
//            System.out.println(md5Hex);
//                DishDAO dishDAO = new DishDAO();
//            List<Dish> dishes = dishDAO.newViewAllDishForChange(5, 2);
//            System.out.println(dishes);

//            String SALT = "hxSalt";
//            String salt = PropertiesUtil.get(SALT);
//            System.out.println(salt);
