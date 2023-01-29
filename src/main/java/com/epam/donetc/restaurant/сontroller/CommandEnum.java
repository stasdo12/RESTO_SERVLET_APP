package com.epam.donetc.restaurant.сontroller;

import com.epam.donetc.restaurant.сontroller.command.get.*;
import com.epam.donetc.restaurant.сontroller.command.post.*;

public enum CommandEnum   {
    add_dish(new ManagerAddDishCommand()),
    cart(new ClientCartCommand()),
    change_dish(new ManagerChangeCommand()),
    client_menu(new ClientMenuCommand()),
    client_orders(new ClientOrdersCommand()),
    info(new InfoCommand()),
    login(new LoginPageCommandGet()),
    manageOrders(new ManagerOrdersCommand()),
    sign_up(new SignupPageCommand()),
    error_page(new ErrorPageCommand()),
    //Post
    delete(new DeleteDishCommand()),
    change(new ChangeDishCommand()),
    login_page(new LoginPageCommandPost()),
    add(new AddDishCommand()),
    send(new SendEmail()),
    delete_dish_from_cart(new DeleteDishFromCartCommand()),
    sign_up_post(new SignUpPostCommand()),
    change_status(new ChangeStatusManagerCommand()),
    add_to_cart(new AddToCartClientCommand());




    private ICommand command;

    CommandEnum(ICommand command) {
        this.command = command;
    }

    public ICommand getCommand() {
        return command;
    }
}
