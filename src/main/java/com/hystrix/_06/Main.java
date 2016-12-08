package com.hystrix._06;

import com.hystrix.Utils;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.apache.log4j.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class);
    private static final String COMMAND_KEY = "command";
    private static final HystrixCommandKey HYSTRIX_COMMAND_KEY = HystrixCommandKey.Factory.asKey(COMMAND_KEY);

    public static void main(String[] args) {
        HystrixCommand.Setter setter = HystrixCommand.Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("group-key"))
                .andCommandKey(HYSTRIX_COMMAND_KEY);

        //Since this depends on request context we must initialize the HystrixRequestContext.
//        HystrixRequestContext context = HystrixRequestContext.initializeContext();

        LOGGER.info("Before launching command");
        CommandUsingRequestCache commandUsingRequestCache1 = new CommandUsingRequestCache(setter, "value");
        CommandUsingRequestCache commandUsingRequestCache2 = new CommandUsingRequestCache(setter, "otherValue");
        CommandUsingRequestCache commandUsingRequestCache3 = new CommandUsingRequestCache(setter, "value");
        String result1 = commandUsingRequestCache1.execute();
        String result2 = commandUsingRequestCache2.execute();
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
