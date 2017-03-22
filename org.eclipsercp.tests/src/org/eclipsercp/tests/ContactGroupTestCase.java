package org.eclipsercp.tests;

import entities.User;
import junit.framework.TestCase;

public class ContactGroupTestCase extends TestCase {

    public void testConnectionDetails() throws Exception {
        System.out.println("Run As JUnit Test");

        User user = null;
        System.out.println(user.getLogin());
    }
}
