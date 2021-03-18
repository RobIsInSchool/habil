package com.radams.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.radams.geoNames.PostalCodesItem;
import com.radams.geoNames.Response;
import com.radams.persistence.GenericDao;
import com.radams.test.util.Database;
import net.bytebuddy.description.type.TypeList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import com.radams.entity.User;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
                + "?maxRows=1&country=US"
                + "&postalcode=" + zip
                + "&radius=30&username=mirado1155";
        Client client = ClientBuilder.newClient();
        WebTarget target =
                client.target(targetString);
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        ObjectMapper mapper = new ObjectMapper();
        PostalCodesItem item = mapper.readValue(response, PostalCodesItem.class);
        String postCode = item.getPostalCode();
        assertEquals("53705", postCode);
    }
}