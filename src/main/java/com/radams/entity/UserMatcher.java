package com.radams.entity;

import java.io.IOException;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.radams.geoNames.LocationJSONResponse;
import com.radams.geoNames.PostalCodesItem;

import com.radams.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import org.json.simple.JSONObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 * This class compares a user against other users and determines if they're a skill match
 * @author Robert Adams
 */
public class UserMatcher {
    private User user;
    private Properties properties;
    private GenericDao userDao;
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Instantiates a new User matcher.
     *
     * @param user the user
     */
    public UserMatcher(User user) {
        this.user = user;
        this.loadProperties();
    }

    /**
     * Instantiates a new User matcher.
     */
    public UserMatcher() {

    }


    //----------------------------------SKILLS MATCHING----------------------------

    /**
     * Matches users based on their skills
     * @return the matched users
     */
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

    /**
     *
     * @param userSkillsHas skills user has
     * @param userSkillsWants skills user wants
     * @param otherUserSkillsHas skills OTHER user has
     * @param otherUserSkillsWants skills OTHER user wants
     * @return boolean indicating a match
     */
    private Boolean isMatch(Set<Skill> userSkillsHas,
                            Set<Skill> userSkillsWants,
                            Set<Skill> otherUserSkillsHas,
                            Set<Skill> otherUserSkillsWants) {

                //determines if user has/wants are complementary to other user
                if(matchUserHas(userSkillsHas, otherUserSkillsWants) &&
                        matchUserWants(userSkillsWants, otherUserSkillsHas)) {
                    return true;
                }
                return false;
            }

    /**
     * Matches user skills has against other skill wants
     * @param userSkillsHas user skill has
     * @param otherUserSkillsWants OTHER user skill wants
     * @return
     */
    private Boolean matchUserHas(Set<Skill> userSkillsHas, Set<Skill> otherUserSkillsWants) {
//        JSONObject matchedSkillsJSON = new JSONObject();
        for (Skill mainUserSkillHas : userSkillsHas) {
            for (Skill otherWant : otherUserSkillsWants) {
                if(otherWant.equals(mainUserSkillHas)) {
//                    matchedSkillsJSON.put("", "");
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Matches user skills wants agains OTHER skill has
     * @param userSkillsWants user skill wants
     * @param otherUserSkillsHas OTHER user skills has
     * @return
     */
    private Boolean matchUserWants(Set<Skill> userSkillsWants, Set<Skill> otherUserSkillsHas) {
        for (Skill mainUserSkillWants : userSkillsWants) {
            for (Skill otherHas : otherUserSkillsHas) {
                if(otherHas.equals(mainUserSkillWants)) {
                    return true;
                }
            }
        }
        return false;
    }

    //------------------------------ZIP CODE MATCHING-----------------------------------

    /**
     * Takes all skill-matched users and determines if they exist in nearby zip code
     * @param matchedUsersBySkill set of all skill-matched users
     * @return users matched by both skills and zip
     * @throws Exception
     */
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

    /**
     * gets list of nearby postal codes from returned data objects
     * @return postal codes
     * @throws Exception
     */
    private Set<String> getPostalCodesFromPostalCodesItem() throws Exception {
        Set<String> postalCodes = new HashSet<>();
        for(PostalCodesItem postalCode : getUserNearbyPostalCodes()) {
            postalCodes.add(postalCode.getPostalCode());
        }
        return postalCodes;
    }

    /**
     * Reaches out to external API and gets zip code data based on user's zip code
     * @return postal codes
     * @throws Exception
     */
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

    //------------------------------------------MATCHED USERS----------------------------------


    /**
     * gets users matched by skill and zip
     * @return set of users matched by skill and zip
     * @throws Exception
     */
    public Set<User> getMatchedUsers() throws Exception {
        Set<User> matchedUsersBySkill = matchUserSkills();
        Set<User> matchedUsersBySkillAndZip = matchUserZipsFromResultingSkillMatches(matchedUsersBySkill);
        return matchedUsersBySkillAndZip;
    }

    //--------------------------------PROPERTIES-----------------------------------------

    /**
     * loads properties from file
     */
    private void loadProperties() {
        properties = new Properties();
        try {
            properties.load (this.getClass().getResourceAsStream("/appProperties.properties"));
        } catch (IOException ioe) {
            logger.error("UserMatcher.loadProperties()...Cannot load the properties file");
            logger.error(ioe);
        } catch (Exception e) {
            logger.error("UserMatcher.loadProperties()..." + e);
            logger.error(e);
        }

    }

}
