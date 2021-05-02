package com.radams.controller;

import com.radams.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.lang.*;

/**
 * Sends the user home
 * @author Robert Adams
 */
@WebServlet(name = "GoHomeServlet", value = "/home")
public class GoHomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int lessonsTaken = user.getNumLessonsTaken();
        int lessonsTaught = user.getNumLessonsTaught();
        Double lessonRatio;
        if (lessonsTaken == 0) {
            lessonRatio = 0.0;
        } else {
            lessonRatio =
                    new BigDecimal((double) lessonsTaught / lessonsTaken)
                            .round(new MathContext(2))
                            .doubleValue();

        }

        request.setAttribute("numTaken", lessonsTaken);
        request.setAttribute("numTaught", lessonsTaught);
        request.setAttribute("lessonRatio", lessonRatio);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
        dispatcher.forward(request, response);
    }
}
