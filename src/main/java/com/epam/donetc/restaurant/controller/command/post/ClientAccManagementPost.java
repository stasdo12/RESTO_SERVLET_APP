package com.epam.donetc.restaurant.controller.command.post;

import com.epam.donetc.restaurant.database.entity.User;
import com.epam.donetc.restaurant.service.UserService;
import com.epam.donetc.restaurant.util.PropertiesUtil;
import com.epam.donetc.restaurant.controller.ICommand;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.nio.file.FileStore;

public class ClientAccManagementPost implements ICommand {

    private final UserService userService = new UserService();

    private static final String SALT = "hxSalt";
    private static final String salt = PropertiesUtil.get(SALT);
    private static final Logger log = LogManager.getLogger(ClientAccManagementPost.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String oldPass = req.getParameter("oldPass");
        String login = req.getParameter("login");
        String newPassword = req.getParameter("newPassword");
        String newEmail = req.getParameter("newEmail");
        newPassword = DigestUtils.md5Hex(newPassword + salt);
        oldPass = DigestUtils.md5Hex(oldPass + salt);
        log.trace(oldPass);
        if (oldPass.equals(user.getPassword())) {
            userService.accountManagement(login, newPassword, newEmail);
        }
        return req.getContextPath() + "/controller?command=acc_management";
    }
}
