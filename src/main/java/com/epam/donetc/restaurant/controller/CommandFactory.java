package com.epam.donetc.restaurant.controller;
import com.epam.donetc.restaurant.database.entity.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import static com.epam.donetc.restaurant.filters.AuthenticationFilter.authenticationFilter;

/**
 * CommandFactory class. Contains all available actions and method to get any of them.
 *
 * @author Stanislav Donetc
 * @version 1.0
 */

public class CommandFactory {

    private CommandFactory(){

    }

    public static ICommand getCommand(HttpServletRequest req){
        String command = req.getParameter("command");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        command =  authenticationFilter(command, user);
        ICommand iCommand = null;
        if (command != null){
            try {
                iCommand = CommandEnum.valueOf(command).getCommand();
            }catch (IllegalArgumentException e){
                e.printStackTrace();
                iCommand = CommandEnum.error_page.getCommand();
            }
        }
        return iCommand;

    }

}

