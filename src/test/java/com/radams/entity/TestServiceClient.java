package com.radams.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.radams.geoNames.LocationJSONResponse;
import com.radams.geoNames.PostalCodesItem;
import com.radams.persistence.GenericDao;
import com.radams.test.util.Database;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.*;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestServiceClient {

    GenericDao userDao;

    @BeforeEach
    void setUp() {
        userDao = new GenericDao(User.class);
        Database db = Database.getInstance();
        db.runSQL("cleandb.sql");
    }

    @Test
    public void testNearbyPostCodesJSON() throws Exception {
        User testUser = (User) userDao.getById(1);
        String zip = testUser.getZip();
        String targetString = "http://api.geonames.org/findNearbyPostalCodesJSON"
                + "?maxRows=5&country=US"
                + "&postalcode=" + zip
                + "&radius=30&username=";
        Client client = ClientBuilder.newClient();
        WebTarget target =
                client.target(targetString);
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        ObjectMapper mapper = new ObjectMapper();
        LocationJSONResponse resultList = mapper.readValue(response, LocationJSONResponse.class);
        assertNotNull(resultList);
        List<PostalCodesItem> postalCodes = resultList.getPostalCodes();
        PostalCodesItem testPostalCode = postalCodes.get(1);
//        assertEquals("53782", testPostalCode.getPostalCode());
    }

    @Test
    public void testNearbyPostCodesByUserFromClass() throws Exception {
        User testUser = (User) userDao.getById(1);
        UserMatcher matcher = new UserMatcher(testUser);
        assertNotNull(matcher.getUserNearbyPostalCodes());
    }
}