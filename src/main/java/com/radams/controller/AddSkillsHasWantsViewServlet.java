package com.radams.controller;

import com.radams.entity.Skill;
import com.radams.persistence.GenericDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@WebServlet(name = "AddSkillsViewServlet", value = "/addSkillsHasWantsView")
public class AddSkillsHasWantsViewServlet extends HttpServlet {
    private GenericDao skillDao = new GenericDao(Skill.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Skill> allSkills = skillDao.getAll();
        session.setAttribute("allSkills", allSkills);
        String forwardUrl;
        if (request.getParameter("viewType").equals("has")) {
            forwardUrl = "/addSkillsHas.jsp";
        }
        else {
            forwardUrl = "/addSkillsWants.jsp";
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(forwardUrl);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}