package com.radams.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.*;

import com.radams.entity.Skill;
import com.radams.entity.User;
import com.radams.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(name = "AddMainSkillServlet", value = "/addMainSkill")
public class AddMainSkillServlet extends HttpServlet {
    private GenericDao userDao = new GenericDao(User.class);
    private GenericDao skillDao = new GenericDao(Skill.class);
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Skill> skills = (List<Skill>) skillDao.getAll();
        session.setAttribute("allSkills", skills);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/addSkillToMain.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String skillName = request.getParameter("skillToAdd");
        logger.info(skillName + " being added as skill...");
        Skill skillToAdd = new Skill(skillName);
        logger.info("Skill with id: " + skillDao.insert(skillToAdd) + " successfully added");
        List<Skill> skills = (List<Skill>) skillDao.getAll();
        session.setAttribute("allSkills", skills);
        response.sendRedirect(request.getContextPath() + "/addMainSkill");
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/addSkillToMain.jsp");
//        dispatcher.forward(request, response);
    }
}
