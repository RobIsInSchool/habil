package com.radams.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import java.util.List;

import com.radams.entity.Skill;
import com.radams.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(name = "DeleteSkillsServlet", value = "/deleteSkill")
public class DeleteSkillsServlet extends HttpServlet {
    private GenericDao skillDao = new GenericDao(Skill.class);
    private final Logger logger = LogManager.getLogger(this.getClass());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int skillId = Integer.parseInt(request.getParameter("skillId"));
        String skillName = request.getParameter("skillName");
        Skill skillToDelete = (Skill) skillDao.getById(skillId);
        skillDao.delete(skillToDelete);
        logger.info("Deleted skill with ID: " + skillId + " and Name: " + skillName);
        List<Skill> allSkills = (List<Skill>) skillDao.getAll();
        session.setAttribute("allSkills", allSkills);
        String forwardURL = "/admin.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(forwardURL);
        dispatcher.forward(request, response);
    }
}
