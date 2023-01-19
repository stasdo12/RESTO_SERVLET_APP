import com.epam.donetc.restaurant.database.ReceiptDAO;
import com.epam.donetc.restaurant.database.entity.Receipt;
import com.epam.donetc.restaurant.exeption.DBException;
import com.epam.donetc.restaurant.service.DishService;

import java.util.List;

public class TestFoeMe {
    public static void main(String[] args) throws DBException {

        DishService dishService = new DishService();
        dishService.getAllDishes();
    }
}