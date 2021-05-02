package com.radams.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.*;

import com.radams.entity.Lesson;
import com.radams.entity.User;
import com.radams.entity.UserMatcher;
import com.radams.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Displays users if they're a match
 * @author Robert Adams
 */
@WebServlet(name = "DisplayMatchedUsersServlet", value = "/viewMatches")
public class DisplayMatchedUsersServlet extends HttpServlet {
    private GenericDao userDao = new GenericDao(User.class);
    private GenericDao lessonDao = new GenericDao(Lesson.class);
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");

        UserMatcher matcher = new UserMatcher(user);
        Set<User> matchedUsers = new HashSet<>();
        try {
            for(User matchedUser : matcher.getMatchedUsers()) {
                matchedUsers.add(matchedUser);
            }
        } catch (Exception e) {
            logger.info(e);
        }
        session.setAttribute("matchedUsers", matchedUsers);

        Map<Integer, Integer> lessonsTaken = new HashMap<>();
        for (User otherUser : matchedUsers) {
            Map<String, User> propertiesToSearch = new HashMap<>();
            propertiesToSearch.put("teacher", otherUser);
            propertiesToSearch.put("student", user);
            List<Lesson> foundLessons = lessonDao.findByPropertiesEqual(propertiesToSearch);
            int numLessons = 0;
            for(Lesson foundLesson : foundLessons) {
                numLessons++;
            }
            lessonsTaken.put(otherUser.getUserId(), numLessons);
        }

        session.setAttribute("userLessons", lessonsTaken);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/matchedUsers.jsp");
        dispatcher.forward(request, response);
    }

//TODO remove user self from matched users
}
