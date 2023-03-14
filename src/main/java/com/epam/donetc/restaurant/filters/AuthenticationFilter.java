package com.epam.donetc.restaurant.filters;

import com.epam.donetc.restaurant.database.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class AuthenticationFilter  {

    public static String authenticationFilter(String command, User user) {
        switch(command) {
            case "manageOrders":
            case "change_dish":
            case "add_dish":
            case "add_admin":
                if (user != null && user.getRoleId() == 1) {
                    command = "client_menu";
                }
                break;
            case "client_menu":
            case "cart":
            case "client_orders":
            case "info":
            case "acc_management":
                if (user != null && user.getRoleId() == 2) {
                    command = "manageOrders";
                }
                break;
        }
        return command;
    }
}