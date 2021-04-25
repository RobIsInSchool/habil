package com.radams.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import com.radams.entity.Skill;
import com.radams.entity.User;
import com.radams.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This servlet adds a user skill based on type
 * @author Robert Adams
 */
@WebServlet(name = "AddSkillActionServlet", value = "/addSkillAction")
public class AddSkillActionServlet extends HttpServlet {

    private GenericDao userDao = new GenericDao(User.class);
    private GenericDao skillDao = new GenericDao(Skill.class);
    private final Logger logger = LogManager.getLogger(this.getClass());
    //TODO condense this and remove skill servlet into ONE servlet
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession webSession = request.getSession();

        //"webUser" and "user" are used to distinguish between the session user and the same user in the database
        User webUser = (User) webSession.getAttribute("user");
        User user = (User) userDao.getById(webUser.getUserId());

        String skillType = request.getParameter("skillType");
        int skillId = Integer.parseInt(request.getParameter("skillId"));
        Skill skill = (Skill) skillDao.getById(skillId);

        logger.info("Skill type: " + skillType + " being added for user " + user.getUsername());
        logger.info("said skill is: " + skill.getSkillName());

        String redirectURL = "";
        //determine skill type and redirect accordingly
        if (skillType.equals("has")) {
            redirectURL = "/skillsHas";
            user.addSkillHas(skill);
        }
        if (skillType.equals("wants")) {
            redirectURL = "/skillsWants";
            user.addSkillWants(skill);
        }
        userDao.saveOrUpdate(user);
        webSession.setAttribute("user", user);
        response.sendRedirect(request.getContextPath() + redirectURL);
    }
}
