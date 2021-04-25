package com.radams.controller;

import com.radams.entity.User;
import com.radams.persistence.GenericDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

/**
 * Displays all users
 * @author Robert Adams
 */
@WebServlet(name = "UserDisplayServlet", value = "/displayUsers")
public class UserDisplayServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        GenericDao<User> userDao = new GenericDao(User.class);
        List<User> users = userDao.getAll();

        request.setAttribute("users", users);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/displayUsers.jsp");
        dispatcher.forward(request, response);
    }
}
