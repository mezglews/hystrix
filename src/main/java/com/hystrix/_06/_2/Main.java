package com.hystrix._06._2;

import com.hystrix.Utils;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.apache.log4j.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        HystrixRequestContext context = HystrixRequestContext.initializeContext();


        LOGGER.info("Before launching command");
        CommandUsingRequestCacheWithInvalidation commandUsingRequestCache1 = new CommandUsingRequestCacheWithInvalidation("value");
        String result1 = commandUsingRequestCache1.execute();

        CommandUsingRequestCacheWithInvalidation commandUsingRequestCache2 = new CommandUsingRequestCacheWithInvalidation("otherValue");
        String result2 = commandUsingRequestCache2.execute();

        CommandUsingRequestCacheWithInvalidation.flushCache("value");

        CommandUsingRequestCacheWithInvalidation commandUsingRequestCache3 = new CommandUsingRequestCacheWithInvalidation("value");
        String result3 = commandUsingRequestCache3.execute();

        LOGGER.info("Get value for command 1: " + result1);
        LOGGER.info("Is response from cache for command 1? " + commandUsingRequestCache1.isResponseFromCache());

        LOGGER.info("Get value for command 2: " + result2);
        LOGGER.info("Is response from cache for command 2? " + commandUsingRequestCache2.isResponseFromCache());

        LOGGER.info("Get value for command 3: " + result3);
        LOGGER.info("Is response from cache for command 3? " + commandUsingRequestCache3.isResponseFromCache());

        LOGGER.info("After launching command");

        Utils.sleep(3000);

        LOGGER.info("Applications is going to stop");
    }
}
