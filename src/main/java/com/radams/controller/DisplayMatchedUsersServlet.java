package com.radams.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.radams.entity.User;
import com.radams.entity.UserMatcher;
import com.radams.geoNames.LocationJSONResponse;
import com.radams.geoNames.PostalCodesItem;
import com.radams.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(name = "DisplayMatchedUsersServlet", value = "/viewMatches")
public class DisplayMatchedUsersServlet extends HttpServlet {
    private GenericDao userDao = new GenericDao(User.class);
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("/matchedUsers.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
//TODO remove user self from matched users