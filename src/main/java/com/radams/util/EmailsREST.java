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
    private JSONArray allUsers;
    private GenericDao userDao = new GenericDao(User.class);
    private final Logger logger = LogManager.getLogger(this.getClass());

    public EmailsREST() {
        this.loadProperties();
        this.allUsers = getAllUsers();
    }

    private JSONArray getAllUsers() {
        // Return all emails
        List<User> users = (List<User>) userDao.getAll();
        Set<String> jsonSet = new TreeSet();
        JSONArray json = new JSONArray();
        JSONParser parser = new JSONParser();
        for(User user : users) {
            String result = "{\"email\": \"" + user.getEmail() + "\"}";
            jsonSet.add(result);
        }
        logger.info(jsonSet);
        try {
            json = (JSONArray) parser.parse(jsonSet.toString());
        } catch (org.json.simple.parser.ParseException e) {
            logger.error("could not add json to array... " + e);
        }

        return json;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JSONArray serveJSON(@QueryParam("key") String key) {
        String keyProp = properties.getProperty("email.rest.key");
        logger.info("Passed key param: " + key);
        logger.info("Key property: " + keyProp);
        if (key.equals(keyProp)) {
            logger.info("key match...");
            logger.info("All: " + this.allUsers);
            return this.allUsers;
        }
        logger.info("key did not match");
        return new JSONArray();
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
