package org.micromoo.account;

import java.util.logging.*;

public final class UserService {
    public static void main(String[] str) {
        Logger LOGGER = Logger.getLogger(UserService.class.getName());

        try {
            LOGGER.info("Starting user service.");
            UserRoutes u = new UserRoutes(new UserOperations());
        } catch(Exception e) {
          System.out.println(e);
        }
    }
}
