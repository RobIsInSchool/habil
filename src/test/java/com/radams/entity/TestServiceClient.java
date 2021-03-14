package com.radams.entity;

import org.junit.Test;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestServiceClient {

    @Test
    public void testswapiJSON() throws Exception {
        Client client = ClientBuilder.newClient();
        WebTarget target =
                client.target("http://api.geonames.org/findNearbyPostalCodesJSON?maxRows=30&country=US&postalcode=53705&radius=30&username=mirado1155");
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        assertEquals("???", response);
    }
}