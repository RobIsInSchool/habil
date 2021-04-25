package com.radams.controller;

import com.radams.entity.Skill;
import com.radams.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import com.radams.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Removes skill depending on skill type
 * @author Robert Adams
 */
@WebServlet(name = "RemoveSkillActionServlet", value = "/removeSkillAction")
public class RemoveSkillActionServlet extends HttpServlet {
    private GenericDao userDao = new GenericDao(User.class);
    private GenericDao skillDao = new GenericDao(Skill.class);
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        //webUser and user are used to differentiate between the session user and the same user in the database
        User webUser = (User) session.getAttribute("user");
        User user = (User) userDao.getById(webUser.getUserId());

        String skillType = request.getParameter("skillType");
        int skillId = Integer.parseInt(request.getParameter("skillId"));
        Skill skill = (Skill) skillDao.getById(skillId);

        logger.info("Skill type: " + skillType + " being removed from user " + user.getUsername());
        logger.info("said skill is: " + skill.getSkillName());

        //Determines skill type and forwards accordingly
        String forwardUrl = "";
        if (skillType.equals("has")) {
            forwardUrl = "/skillsHas";
            user.removeSkillHas(skill);
        }
        if (skillType.equals("wants")) {
            forwardUrl = "/skillsWants";
            user.removeSkillWants(skill);
        }
        userDao.saveOrUpdate(user);

        RequestDispatcher dispatcher = request.getRequestDispatcher(forwardUrl);
        dispatcher.forward(request, response);
    }
}
