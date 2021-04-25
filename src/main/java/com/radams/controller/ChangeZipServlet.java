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

@WebServlet(name = "ChangeZipServlet", value = "/changeZip")
public class ChangeZipServlet extends HttpServlet {
    private GenericDao userDao = new GenericDao(User.class);
    private final Logger logger = LogManager.getLogger(this.getClass());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User webUser = (User) session.getAttribute("user");
        User user = (User) userDao.getById(webUser.getUserId());

        String newZip = request.getParameter("newZip");
        user.setZip(newZip);

        userDao.saveOrUpdate(user);
        session.setAttribute("user", user);
        response.sendRedirect(request.getContextPath() + "/home");
    }
}
