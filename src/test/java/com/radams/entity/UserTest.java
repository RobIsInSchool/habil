package com.radams.entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {
    private User user;

    @Before
    public void setUp() {
        user = new User();
    }

    @Test
    public void testTests() {
        assertEquals(1, user.testTesting());
    }
}
