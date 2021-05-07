package com.radams.util;
import com.radams.entity.User;
import com.radams.persistence.GenericDao;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Path("/usernames")
public class UsersREST {
    private GenericDao userDao = new GenericDao(User.class);
    private final Logger logger = LogManager.getLogger(this.getClass());

    public UsersREST() {

    }

    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces(MediaType.APPLICATION_JSON)
    public JSONArray getAllUsers() {
        // Return all usernames
        List<User> users = (List<User>) userDao.getAll();
        Set<String> jsonSet = new TreeSet();
        JSONArray json = new JSONArray();
        JSONParser parser = new JSONParser();
        for(User user : users) {
            String result = "{\"username\": \"" + user.getUsername() + "\"}";
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

}
