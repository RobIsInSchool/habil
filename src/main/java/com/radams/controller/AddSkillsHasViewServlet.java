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

@WebServlet(name = "AddSkillsHasViewServlet", value = "/addSkillsHasView")
public class AddSkillsHasViewServlet extends HttpServlet {
    private GenericDao skillDao = new GenericDao(Skill.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Skill> allSkills = skillDao.getAll();
        session.setAttribute("allSkills", allSkills);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/addSkillsHas.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
