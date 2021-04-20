package com.radams.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import java.util.List;

import com.radams.entity.User;
import com.radams.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(name = "AdminLoginAction", value = "/adminLoginAction")
public class AdminLoginAction extends HttpServlet {
    private GenericDao userDao = new GenericDao(User.class);
    private final Logger logger = LogManager.getLogger(this.getClass());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = request.getRemoteUser();
        logger.info("Admin " + username + " has logged in");
        List<User> foundUser = (List<User>) userDao.findByPropertyEqual("username", username);
        User user = foundUser.get(0);
        request.setAttribute("username", username);
        request.setAttribute("user", user);
        session.setAttribute("user", user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
