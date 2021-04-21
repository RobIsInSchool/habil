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

@WebServlet(name = "DeleteUsersServlet", value = "/deleteUser")
public class DeleteUsersServlet extends HttpServlet {
    private GenericDao userDao = new GenericDao(User.class);
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int userId = Integer.parseInt(request.getParameter("userToDelete"));
        String username = request.getParameter("username");
        User userToDelete = (User) userDao.getById(userId);
        userDao.delete(userToDelete);
        logger.info("Deleted user with ID: " + userId + " and username: " + username);
        List<User> allUsers = (List<User>) userDao.getAll();
        session.setAttribute("allUsers", allUsers);
        String forwardURL = "/admin.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(forwardURL);
        dispatcher.forward(request, response);
    }
}
