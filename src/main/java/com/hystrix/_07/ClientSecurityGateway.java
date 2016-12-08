package com.hystrix._07;

import org.apache.log4j.Logger;

import java.util.Random;

/**
 * User: Szymon Mezglewski
 * Date: 2016-12-08
 */
public class ClientSecurityGateway {
    private static final Logger LOGGER = Logger.getLogger(ClientSecurityGateway.class);

    private static final Random RANDOM = new Random();

    public User getClientIdForCST(String cst) {
        return new User(String.valueOf(RANDOM.nextInt(Integer.MAX_VALUE)));
    }

}
