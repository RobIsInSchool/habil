package com.radams.controller;

import com.radams.entity.User;
import com.radams.entity.UserRole;
import com.radams.persistence.GenericDao;
import org.apache.catalina.CredentialHandler;
import org.apache.catalina.Globals;
import org.apache.catalina.realm.MessageDigestCredentialHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.security.NoSuchAlgorithmException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

/**
 * Creates new user
 * @author Robert Adams
 */
@WebServlet(name = "SignupUserServlet", value = "/signupUser")
public class SignupUserServlet extends HttpServlet {

    private GenericDao userDao = new GenericDao(User.class);
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        MessageDigestCredentialHandler credentialHandler = new MessageDigestCredentialHandler();
        try {
            credentialHandler.setAlgorithm("sha-256");
        } catch (NoSuchAlgorithmException e) {
            logger.info("unable to implement hash algorithm");
        }
        credentialHandler.setEncoding("UTF-8");

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String userName = request.getParameter("username");
        String email = request.getParameter("email");
        String password = credentialHandler.mutate(request.getParameter("password"));
        String zip = request.getParameter("zip");
        logger.info("Hashed password length: " + password.length());
        User user = new User(firstName, lastName, userName, email, password, true, zip, Date.valueOf(LocalDate.now()));
        UserRole role = new UserRole("regUser", user);
        user.addRole(role);
        int userId = userDao.insert(user);
        logger.info("regUser with id " + userId + " has signed up: " + user);

        session.setAttribute("userName", user.getUsername());

        //TODO add username uniqueness check
        //TODO add postal code validation
        RequestDispatcher dispatcher = request.getRequestDispatcher("/signupConfirm.jsp");
        dispatcher.forward(request, response);
    }
}
