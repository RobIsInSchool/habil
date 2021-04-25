package com.radams.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Logs out user
 * @author Robert Adams
 */
@WebServlet(name = "LogoutAction", value = "/logout")
public class LogoutAction extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        logger.info("User " + request.getRemoteUser() + " is logging out");
        session.invalidate();

        RequestDispatcher dispatcher = request.getRequestDispatcher("/logoutPage.jsp");
        dispatcher.forward(request, response);

    }
}
