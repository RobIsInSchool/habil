package com.radams.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import java.util.List;

import com.radams.entity.Skill;
import com.radams.entity.User;
import com.radams.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Logs in Admin
 * @author Robert Adams
 */
@WebServlet(name = "AdminLoginAction", value = "/adminLoginAction")
public class AdminLoginAction extends HttpServlet {
    private GenericDao userDao = new GenericDao(User.class);
    private GenericDao skillDao = new GenericDao(Skill.class);
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String username = request.getRemoteUser();
        logger.info("Admin " + username + " has logged in");
        List<User> foundUser = (List<User>) userDao.findByPropertyEqual("username", username);
        User user = foundUser.get(0);

        List<User> allUsers = (List<User>) userDao.getAll();
        List<Skill> allSkills = (List<Skill>) skillDao.getAll();

        request.setAttribute("username", username);
        request.setAttribute("user", user);

        session.setAttribute("user", user);
        session.setAttribute("allUsers", allUsers);
        session.setAttribute("allSkills", allSkills);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin.jsp");
        dispatcher.forward(request, response);
    }

}
