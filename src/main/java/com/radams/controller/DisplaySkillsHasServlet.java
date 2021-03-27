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

@WebServlet(name = "DisplaySkillsHasServlet", value = "/skillsHas")
public class DisplaySkillsHasServlet extends HttpServlet {
    private GenericDao userDao = new GenericDao(User.class);
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User webUser = (User) session.getAttribute("user");
        User user = (User) userDao.getById(webUser.getUserId());
        Set<Skill> skillsHas = user.getSkillsHas();
        session.setAttribute("skillsHas", skillsHas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/skillsHas.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
