package com.epam.donetc.restaurant.database.service;

import com.epam.donetc.restaurant.database.DishDAO;
import com.epam.donetc.restaurant.database.entity.Category;
import com.epam.donetc.restaurant.database.entity.Dish;
import com.epam.donetc.restaurant.database.interfaceDAO.IDishDAO;
import com.epam.donetc.restaurant.exeption.DBException;
import com.epam.donetc.restaurant.service.DishService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DishServiceTest {


    @Mock
    private IDishDAO dao;

    @Mock
    private final ResultSet rs = mock(ResultSet.class);
    private DishService dishService;



    @BeforeEach
    private void setUp() {
        MockitoAnnotations.initMocks(this);
        this.dishService = new DishService(dao);
    }


    @Test
    public void testGetDishByID() throws DBException {
        int id = 1;
        DishDAO dishDAO = mock(DishDAO.class);
        Dish expectedDish = createTestDish(1);
        when(dishDAO.getDishByID(id)).thenReturn(expectedDish);
        DishService dishService = new DishService();
        Dish actualDish = dishService.getDishByID(id);
        assertEquals(expectedDish, actualDish);
    }

    @Test
    public void newViewAllDishForChangeTest()throws  SQLException{
        List<Dish> expected = createTestDishes(5);
        for (Dish d: expected){
            mockResultSet(rs, d);
        }
        when(dishService.getDishesOnePage(0,5)).thenReturn(expected);
    }

    @Test
    public void shouldChangeDishAllValues() throws SQLException{
        Dish expected = createTestDish(1);
        doNothing().when(dao).changeDishAllValues(expected.getName(), expected.getPrice(),
                expected.getWeight(), expected.getCategory().getId(), expected.getDescription(), expected.getId());
    }

    @Test
    public void shouldAddDish()throws SQLException{
        Dish expected = createTestDish(1);
        doNothing().when(dao).addDish(expected.getName(), expected.getPrice(), expected.getWeight(),
                expected.getCategory().getId(),expected.getDescription());
    }


    @Test
    public void getAllDishesTest() throws DBException, SQLException{
        List<Dish> expected = createTestDishes(5);
        for (Dish d: expected){
            mockResultSet(rs, d);
        }

        when(dao.getAllDishes()).thenReturn(expected);
    }



    @Test
    public void shouldDeleteDish() throws  SQLException{
        Dish expected = createTestDish(1);
        mockResultSet(rs, expected);
        doNothing().when(dao).deleteDish(expected.getId());

    }
    @Test
    public void getDishByIdTest() throws DBException, SQLException {
        Dish expected = createTestDish(1);
        mockResultSet(rs, expected);
        when(dishService.getDishByID(expected.getId())).thenReturn(expected);
    }

    @Test
    public void sortDishesByCategoryTest(){
        List<Dish> expected = createTestDishes(6);
        when(dao.sortBy(expected, "category")).thenReturn(expected);
    }

    @Test
    public void sortDishesByNameTest(){
        List<Dish> expected = createTestDishes(6);
        when(dao.sortBy(expected,"name" )).thenReturn(expected);
    }

    @Test
    public void sortDishesByPriceTest(){
        List<Dish> expected = createTestDishes(6);
        when(dao.sortBy(expected,"price" )).thenReturn(expected);
    }



    private static Dish createTestDish(int i) {
        int categoryId = i;
        while(categoryId>6){
            categoryId-=6;
        }
        return new Dish(i, "Dish " + i, i, i, Category.getCategoryById(categoryId), "Description " + i);
    }

    private static List<Dish> createTestDishes(int amount) {
        List<Dish> dishes = new ArrayList<>();
        for (int i = 1; i <= amount; ++i) {
            dishes.add(createTestDish(i));
        }
        return dishes;
    }

    private static void mockResultSet(ResultSet rs, Dish d) throws SQLException {
        when(rs.getInt(1)).thenReturn(d.getId());
        when(rs.getString(2)).thenReturn(d.getName());
        when(rs.getInt(3)).thenReturn(d.getCategory().getId());
        when(rs.getInt(4)).thenReturn(d.getPrice());
        when(rs.getInt(5)).thenReturn(d.getWeight());
        when(rs.getString(6)).thenReturn(d.getDescription());
    }

}