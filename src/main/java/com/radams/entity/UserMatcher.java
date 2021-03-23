package com.radams.entity;

import java.io.IOException;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.radams.geoNames.LocationJSONResponse;
import com.radams.geoNames.PostalCodesItem;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.radams.geoNames.LocationJSONResponse;
import com.radams.geoNames.PostalCodesItem;
import com.radams.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class UserMatcher {
    private User user;
    private Properties properties;
    private GenericDao userDao;
    private final Logger logger = LogManager.getLogger(this.getClass());

    public UserMatcher(User user) {
        this.user = user;
        this.loadProperties();
    }

    public UserMatcher() {

    }

    public Set<User> matchUserSkills() {
        userDao = new GenericDao(User.class);
        Set<Skill> userSkillsHas = user.getSkillsHas();
        Set<Skill> userSkillsWants = user.getSkillsWants();
        List<User> otherUsers = userDao.getAll();
        otherUsers.remove(user);
        Set<User> matchedUsers = new HashSet<>();

        for (User otherUser : otherUsers) {
            Set<Skill> otherUserSkillsHas = otherUser.getSkillsHas();
            Set<Skill> otherUserSkillsWants = otherUser.getSkillsWants();

            if (isMatch(userSkillsHas, userSkillsWants, otherUserSkillsHas, otherUserSkillsWants)) {
                matchedUsers.add(otherUser);
            }

        }
        return matchedUsers;
    }

    private Boolean isMatch(Set<Skill> userSkillsHas,
                            Set<Skill> userSkillsWants,
                            Set<Skill> otherUserSkillsHas,
                            Set<Skill> otherUserSkillsWants) {

                if(matchUserHas(userSkillsHas, otherUserSkillsWants) &&
                        matchUserWants(userSkillsWants, otherUserSkillsHas)) {
                    return true;
                }
                return false;
            }

    private Boolean matchUserHas(Set<Skill> userSkillsHas, Set<Skill> otherUserSkillsWants) {
        for (Skill mainUserSkillHas : userSkillsHas) {
            for (Skill otherWant : otherUserSkillsWants) {
                if(otherWant.equals(mainUserSkillHas)) {
                    return true;
                }
            }
        }
        return false;
    }

    private Boolean matchUserWants(Set<Skill> userSkillsWants, Set<Skill> otherUserSkillsHas) {
        for (Skill mainUserSkillHas : userSkillsWants) {
            for (Skill otherWant : otherUserSkillsHas) {
                if(otherWant.equals(mainUserSkillHas)) {
                    return true;
                }
            }
        }
        return false;
    }

    private Set<User> getMatchedUsers() throws Exception {
        Set<User> matchedUsersBySkill = matchUserSkills();
        Set<User> matchedUsersBySkillAndZip = matchUserZipsFromResultingSkillMatches(matchedUsersBySkill);
        return matchedUsersBySkillAndZip;
    }

    private Set<User> matchUserZipsFromResultingSkillMatches(Set<User> matchedUsersBySkill) throws Exception {
        Set<User> matchedUsers = new HashSet<>();
        for (User otherUser : matchedUsersBySkill) {
            //TODO test to see if this .contains() works
            if (getPostalCodesFromPostalCodesItem().contains(otherUser.getZip())) {
                matchedUsers.add(otherUser);
            }
        }
        return matchedUsers;
    }

    private Set<String> getPostalCodesFromPostalCodesItem() throws Exception {
        Set<String> postalCodes = new HashSet<>();
        for(PostalCodesItem postalCode : getUserNearbyPostalCodes()) {
            postalCodes.add(postalCode.getPostalCode());
        }
        return postalCodes;
    }

    public List<PostalCodesItem> getUserNearbyPostalCodes() throws Exception {
        String zip = user.getZip();
        String targetString = "http://api.geonames.org/findNearbyPostalCodesJSON"
                + "?maxRows="
                + properties.getProperty("zip.code.connection.num.results")
                + "&country=US"
                + "&postalcode=" + zip
                + "&radius=30&username="
                + properties.getProperty("zip.code.connection.auth");
        Client client = ClientBuilder.newClient();
        WebTarget target =
                client.target(targetString);
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        ObjectMapper mapper = new ObjectMapper();
        LocationJSONResponse resultList = mapper.readValue(response, LocationJSONResponse.class);
        return resultList.getPostalCodes();
    }

    private void loadProperties() {
        properties = new Properties();
        try {
            properties.load (this.getClass().getResourceAsStream("/appProperties.properties"));
        } catch (IOException ioe) {
            logger.error("UserMatcher.loadProperties()...Cannot load the properties file");
            ioe.printStackTrace();
        } catch (Exception e) {
            logger.error("UserMatcher.loadProperties()..." + e);
            e.printStackTrace();
        }

    }

}
