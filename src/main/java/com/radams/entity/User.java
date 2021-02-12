package com.radams.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class User {
    private final Logger logger = LogManager.getLogger(this.getClass());
    public void testLogging() {
        logger.info("Logging works again?");
    }

    public int testTesting() {
        return 1;
    }

    public static void main(String[] args) {
        User user = new User();
        user.testLogging();
    }
}
