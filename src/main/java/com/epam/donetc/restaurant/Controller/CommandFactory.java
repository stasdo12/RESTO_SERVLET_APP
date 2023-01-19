package com.epam.donetc.restaurant.Controller;



import javax.servlet.http.HttpServletRequest;

public class CommandFactory {
    private CommandFactory(){

    }

    public static ICommand getCommand(HttpServletRequest req){
        String command = req.getParameter("command");
        ICommand iCommand = null;

        if (command != null){
            try {
                iCommand = CommandEnum.valueOf(command).getCommand();

            }catch (IllegalArgumentException e){
                e.printStackTrace();
                iCommand = CommandEnum.error_page.getCommand();
            }
        }
        else {
            iCommand = CommandEnum.error_page.getCommand();
        }
        return iCommand;

    }
}

