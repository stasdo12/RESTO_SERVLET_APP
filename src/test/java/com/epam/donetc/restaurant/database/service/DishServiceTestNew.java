package com.epam.donetc.restaurant.database.service;

import com.epam.donetc.restaurant.database.DishDAO;
import com.epam.donetc.restaurant.database.entity.Dish;
import com.epam.donetc.restaurant.exeption.DBException;
import com.epam.donetc.restaurant.service.DishService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DishServiceTestNew {
    private DishService dishService;
    private DishDAO mockDishDAO;


    @BeforeEach
    public void setup() {
        mockDishDAO = mock(DishDAO.class);
        dishService = new DishService(mockDishDAO);
    }

    @Test
    public void testGetDishByID() throws DBException {
        Dish expectedDish = new Dish();
        when(mockDishDAO.getDishByID(1)).thenReturn(expectedDish);
        Dish actualDish = dishService.getDishByID(1);
        assertEquals(expectedDish, actualDish);
        verify(mockDishDAO).getDishByID(1);
    }

    @Test
    public void testAddDish() {
        dishService.addDish("test", 10, 20, 30, "desc");
        verify(mockDishDAO).addDish("test", 10, 20, 30, "desc");
    }

    @Test
    public void testChangeDishAllValues() {
        dishService.changeDishAllValues("newTest", 100, 200, 300, "newDesc", 1);
        verify(mockDishDAO).changeDishAllValues("newTest", 100, 200, 300, "newDesc", 1);
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
