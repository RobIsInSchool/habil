package com.radams.controller;

import com.radams.entity.User;
import com.radams.entity.UserRole;
import com.radams.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Set;

@WebServlet(name = "AdminAddRemoveServlet", value = "/adminAddRemove")
public class AdminAddRemoveServlet extends HttpServlet {
    private GenericDao userDao = new GenericDao(User.class);
    private GenericDao roleDao = new GenericDao(UserRole.class);
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        logger.info("Role Action Type: " + action);
        int userId = Integer.parseInt(request.getParameter("userId"));
        User user = (User) userDao.getById(userId);
        logger.info("User in question: " + user.getUsername());

        switch(action) {
            case("add"):
                addRole(user);
                break;
            case("remove"):
                UserRole role = (UserRole) roleDao.getById(Integer.parseInt(request.getParameter("role")));
                removeRole(user, role);
                break;
        }

        response.sendRedirect(request.getContextPath() + "/adminHome");
    }

    private void addRole(User user) {
        logger.info("Adding role for " + user.getUsername());
        UserRole role = new UserRole("admin", user);
        user.addRole(role);
        roleDao.insert(role);
        userDao.saveOrUpdate(user);
    }

    private void removeRole(User user, UserRole role) {
        logger.info("Removing role" +
                " for " +
                user.getUsername());

        user.removeRole(role);
        userDao.saveOrUpdate(user);
        roleDao.delete(role);
//        Set<UserRole> userRoles = user.getUserRoles();
//        for (UserRole role : userRoles) {
//            if (role.getRoleName().equals("admin") && role.getUser().getUsername().equals(user.getUsername())) {
//                logger.info("Found User Role: " + role.toString());
//                user.removeRole(role);
//                userDao.saveOrUpdate(user);
//            } else {
//                logger.info("Could not find role");
//            }
//        }

    }

}
