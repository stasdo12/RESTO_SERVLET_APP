package com.epam.donetc.restaurant.database.service;

import com.epam.donetc.restaurant.database.entity.Dish;
import com.epam.donetc.restaurant.database.interfaceDAO.IDishDAO;
import com.epam.donetc.restaurant.exeption.DBException;
import com.epam.donetc.restaurant.service.DishService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class DishServiceTestNew {
    public static final int ID = 1;
    public static final String NAME = "test";
    public static final int PRICE = 10;
    public static final int WEIGHT = 20;
    public static final int CATEGORY = 30;
    public static final String DESC = "desc";
    @Mock
    private IDishDAO mockDishDAO;

    @InjectMocks
    private DishService dishService;

    @Test
    public void testGetDishByID() throws DBException {
        Dish expectedDish = new Dish();
        when(mockDishDAO.getDishByID(ID)).thenReturn(expectedDish);
        Dish actualDish = dishService.getDishByID(ID);
        assertEquals(expectedDish, actualDish);
        verify(mockDishDAO).getDishByID(ID);
    }

    @Test
    public void testAddDish() {
        dishService.addDish(NAME, PRICE, WEIGHT, CATEGORY, DESC);
        verify(mockDishDAO).addDish(NAME, PRICE, WEIGHT, CATEGORY, DESC);
    }

    @Test
    public void testChangeDishAllValues() {
        dishService.changeDishAllValues(NAME, PRICE, WEIGHT, CATEGORY, DESC, ID);
        verify(mockDishDAO).changeDishAllValues(NAME, PRICE, WEIGHT, CATEGORY, DESC, ID);
    }

    @Test
    public void testGetAllDishes() throws DBException {
        List<Dish> expectedDishes = Arrays.asList(new Dish(), new Dish());
        when(mockDishDAO.getAllDishes()).thenReturn(expectedDishes);
        List<Dish> actualDishes = dishService.getAllDishes();
        assertEquals(expectedDishes, actualDishes);
        verify(mockDishDAO).getAllDishes();
    }

    @Test
    public void testGetDishesByCategory() throws DBException {
        List<Dish> expectedDishes = Arrays.asList(new Dish(), new Dish());
        when(mockDishDAO.getDishesByCategory("category")).thenReturn(expectedDishes);
        List<Dish> actualDishes = dishService.getDishesByCategory("category");
        assertEquals(expectedDishes, actualDishes);
        verify(mockDishDAO).getDishesByCategory("category");
    }

    @Test
    public void testSortBy() {
        List<Dish> expectedDishes = Arrays.asList(new Dish(), new Dish());
        when(mockDishDAO.sortBy(expectedDishes, "sortBy")).thenReturn(expectedDishes);
        List<Dish> actualDishes = dishService.sortBy(expectedDishes, "sortBy");
        assertEquals(expectedDishes, actualDishes);
        verify(mockDishDAO).sortBy(expectedDishes, "sortBy");
    }
}
