package com.hystrix._07;

import java.util.Random;

/**
 * User: Szymon Mezglewski
 * Date: 2016-12-08
 */
public class ClientSecurityGateway {

    private static final Random RANDOM = new Random();

    public User getUserForCST(String cst) {
        return new User(String.valueOf(RANDOM.nextInt(Integer.MAX_VALUE)));
    }

}
