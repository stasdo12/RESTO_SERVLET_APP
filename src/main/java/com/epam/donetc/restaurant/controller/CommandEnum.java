package com.epam.donetc.restaurant.controller;

import com.epam.donetc.restaurant.controller.command.get.*;
import com.epam.donetc.restaurant.controller.command.post.*;


/**
 * CommandEnum  enum. contains all possible commands.
 * DoGet and DoPost method
 *
 * @author Stanislav Donetc
 * @version 1.0
 */
public enum CommandEnum {
    acc_management(new ClientAccManagement()),
    add_admin(new ManagerAddAdminGetCommand()),
    add_dish(new ManagerAddDishCommand()),
    cart(new ClientCartCommand()),
    change_dish(new ManagerChangeCommand()),
    client_menu(new ClientMenuCommand()),
    client_orders(new ClientOrdersCommand()),
    info(new InfoCommand()),
    login(new LoginPageCommandGet()),
    manageOrders(new ManagerOrdersCommand()),
    sign_up(new SignupPageCommand()),
    get_address(new GetAddressCommand()),
    error_page(new ErrorPageCommand()),
    //Post
    make_admin(new ManagerMakeAdminCommand()),
    delete(new DeleteDishCommand()),
    change(new ChangeDishCommand()),
    login_page(new LoginPageCommandPost()),
    add(new AddDishCommand()),
    send(new SendEmail()),
    delete_dish_from_cart(new DeleteDishFromCartCommand()),
    sign_up_post(new SignUpPostCommand()),
    change_status(new ChangeStatusManagerCommand()),
    add_to_cart(new AddToCartClientCommand()),
    client_make_order(new MakeOrderCommand()),
    acc_management_post(new ClientAccManagementPost()),
    add_address(new AddAddressClientCommand());


    private final ICommand command;

    CommandEnum(ICommand command) {
        this.command = command;
    }

    public ICommand getCommand() {
        return command;
    }
}
