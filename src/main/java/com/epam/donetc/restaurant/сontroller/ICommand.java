package com.epam.donetc.restaurant.сontroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface ICommand  {
    /**
     * Execute command with req and resp
     * @param req
     * @param resp
     * @return command
     */
    String execute(HttpServletRequest req, HttpServletResponse resp);
}
