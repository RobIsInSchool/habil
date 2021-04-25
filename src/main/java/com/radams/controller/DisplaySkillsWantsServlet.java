package com.radams.controller;

import com.radams.entity.Skill;
import com.radams.entity.User;
import com.radams.persistence.GenericDao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Set;

/**
 * Displays user's skills that they want
 * @author Robert Adams
 */
@WebServlet(name = "DisplaySkillsWantsServlet", value = "/skillsWants")
public class DisplaySkillsWantsServlet extends HttpServlet {
    private GenericDao userDao = new GenericDao(User.class);
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        //webUser and user are used to differentiate between the session user and the same user in the database
        User webUser = (User) session.getAttribute("user");
        User user = (User) userDao.getById(webUser.getUserId());

        Set<Skill> skillsWants = user.getSkillsWants();

        session.setAttribute("skillsWants", skillsWants);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/skillsWants.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
