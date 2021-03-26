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

@WebServlet(name = "DisplaySkillsWantsServlet", value = "/skillsWants")
public class DisplaySkillsWantsServlet extends HttpServlet {
    private GenericDao userDao = new GenericDao(User.class);
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Set<Skill> skillsWants = user.getSkillsWants();
        session.setAttribute("skillsWants", skillsWants);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/skillsWants.jsp");
        dispatcher.forward(request, response);
    }

}
