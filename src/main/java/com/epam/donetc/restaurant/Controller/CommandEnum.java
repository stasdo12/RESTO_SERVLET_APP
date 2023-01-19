package com.epam.donetc.restaurant.Controller;

import com.epam.donetc.restaurant.Controller.command.get.*;
import com.epam.donetc.restaurant.Controller.command.post.DeleteDishCommand;

public enum CommandEnum  {
    add_dish(new ManagerAddDishCommand()),
    cart(new ClientCartCommand()),
    change_dish(new ManagerChangeCommand()),
    client_menu(new ClientMenuCommand()),
    client_orders(new ClientOrdersCommand()),
    info(new InfoCommand()),
    login(new LoginPageCommand()),
    manageOrders(new ManagerOrdersCommand()),
    sign_up(new SignupPageCommand()),
    error_page(new ErrorPageCommand()),
    delete(new DeleteDishCommand());



    private ICommand command;

    CommandEnum(ICommand command) {
        this.command = command;
    }

    public ICommand getCommand() {
        return command;
    }
}
