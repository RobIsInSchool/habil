package com.radams.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.radams.entity.User;
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
        String zip = user.getZip();
        String targetString = "http://api.geonames.org/findNearbyPostalCodesJSON"
                + "?maxRows=30&country=US"
                + "&postalcode=" + zip
                + "&radius=30&username=mirado1155";
        Client client = ClientBuilder.newClient();
        WebTarget target =
                client.target(targetString);
        String jsonResp = target.request(MediaType.APPLICATION_JSON).get(String.class);
        ObjectMapper mapper = new ObjectMapper();
        LocationJSONResponse resultList = mapper.readValue(jsonResp, LocationJSONResponse.class);
        List<PostalCodesItem> postalCodes = resultList.getPostalCodes();

        //TODO make a thing for matched users by zip AND by skill/wants
        List<User> users = userDao.getAll();
        List<User> matchedUsers = new ArrayList<>();
        for (User otherUser : users) {
            for (PostalCodesItem postalCode : postalCodes) {
                if (otherUser.getZip().equals(postalCode.getPostalCode()) ){
                    matchedUsers.add(otherUser);
                }
            }
        }
        session.setAttribute("matchedUsers", matchedUsers);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/matchedUsers.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
