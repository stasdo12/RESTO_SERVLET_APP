package com.epam.donetc.restaurant.database.service;

import com.epam.donetc.restaurant.database.entity.Dish;
import com.epam.donetc.restaurant.database.interfaceDAO.ICartDAO;
import com.epam.donetc.restaurant.exeption.DBException;
import com.epam.donetc.restaurant.service.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CartServiceTest {
    @Mock
    private ICartDAO cartDAO;

    private CartService cartService;
    private int userId;
    private int dishId;
    private int amount;
    private Map<Dish, Integer> cart;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        cartService = new CartService(cartDAO);
        userId = 1;
        dishId = 2;
        amount = 3;
        cart = new HashMap<>();
        cart.put(new Dish(), amount);
    }

    @Test
    public void testGetCart() throws DBException {
        when(cartDAO.getCart(userId)).thenReturn(cart);
        Map<Dish, Integer> result = cartService.getCart(userId);
        verify(cartDAO, times(1)).getCart(userId);
        assertEquals(cart, result);
    }


    @Test
    public void testAddDishToCart() throws DBException {
        cartService.addDishToCart(userId, dishId, amount);
        verify(cartDAO, times(1)).addDishToCart(userId, dishId, amount);
    }

    @Test
    public void testChangeAmountOfDish() throws DBException {
        cartService.changeAmountOfDish(userId, dishId, amount);
        verify(cartDAO, times(1)).changeAmountOfDish(userId, dishId, amount);
    }

    @Test
    public void testDeleteDishFromCart() throws DBException {
        cartService.deleteDishFromCart(userId, dishId);
        verify(cartDAO, times(1)).deleteDishFromCart(userId, dishId);
    }

    @Test
    public void testSubmitOrder() throws DBException {
        cartService.submitOrder(userId, cart);
        verify(cartDAO, times(1)).submitOrder(userId, cart);
    }

    @Test
    public void testCleanCart() throws DBException {
        cartService.cleanCart(userId);
        verify(cartDAO, times(1)).cleanCart(userId);
    }

}
