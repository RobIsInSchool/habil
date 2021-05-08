package com.radams.util;

import com.radams.entity.User;
import com.radams.persistence.GenericDao;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Path("/validateUniqueUser")
public class EmailsREST {
    private Properties properties;
    private String emailKey;
    private Set<String> emails;
    private GenericDao userDao = new GenericDao(User.class);
    private final Logger logger = LogManager.getLogger(this.getClass());

    public EmailsREST() {
        this.loadProperties();
        this.emails = getAllUsers();
    }

    private Set<String> getAllUsers() {
        // Return all emails
        Set<String> emails = new TreeSet();
        for (User user : (List<User>) userDao.getAll()) {
            emails.add(user.getEmail());
        }
        return emails;
    }

    private boolean findMatch(String emailToMatch) {
        if (this.emails.contains(emailToMatch)) {
            return true;
        }
        return false;
    }

    private JSONArray buildJSON(String status) {
        JSONParser parser = new JSONParser();
        JSONArray json = new JSONArray();
        String jsonString = "{\"found\": \"" + status + "\"}";

        try {
            json = (JSONArray) parser.parse(jsonString);
            return json;
        } catch (org.json.simple.parser.ParseException e) {
            logger.error("Could not put email confirmation into json... " + e);
        }
        return json;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JSONArray serveJSON(@QueryParam("userEmail") String userEmail) {
        String keyProp = properties.getProperty("email.rest.key");
        logger.info("Passed email param: " + userEmail);
        logger.info("Key property: " + keyProp);
        if (findMatch(userEmail)) {
            logger.info("email match");
            return buildJSON("true");
        }
        logger.info("no email match");
        return buildJSON("false");
    }


    private void loadProperties() {
        properties = new Properties();
        try {
            properties.load (this.getClass().getResourceAsStream("/appProperties.properties"));
        } catch (IOException ioe) {
            logger.error("EmailsREST.loadProperties()...Cannot load the properties file");
            logger.error(ioe);
        } catch (Exception e) {
            logger.error("EmailsREST.loadProperties()..." + e);
            logger.error(e);
        }

    }
}
