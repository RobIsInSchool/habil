package com.radams.controller;

import com.radams.entity.Lesson;
import com.radams.entity.User;
import com.radams.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "LessonAddServlet", value = "/addLesson")
public class LessonAddServlet extends HttpServlet {

    private GenericDao userDao = new GenericDao(User.class);
    private GenericDao lessonDao = new GenericDao(Lesson.class);
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        int teacherId = Integer.parseInt(request.getParameter("teacher"));

        User user = (User) session.getAttribute("user");
        logger.info("User (Student) is " + user.getUsername());
        User teacher = (User) userDao.getById(teacherId);
        logger.info("User (Teacher) is " + teacher.getUsername());

        Lesson lesson = new Lesson(user, teacher);
        int newLessonId = lessonDao.insert(lesson);

        logger.info("Lesson " + newLessonId + " added. Student: " + user.getUsername() + ", Teacher: " + teacher.getUsername());

        User updatedUser = (User) userDao.getById(user.getUserId());

        session.setAttribute("user", updatedUser);

        response.sendRedirect(request.getContextPath() + "/viewMatches");

//        RequestDispatcher dispatcher = request.getRequestDispatcher("/viewMatches");
//        dispatcher.forward(request, response);
    }
}
