package com.hystrix._03;

import com.hystrix.Utils;
import com.hystrix._01._1.SimpleCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * User: Szymon Mezglewski
 * Date: 2016-12-01
 */
public class Main {
    private final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        HystrixCommandGroupKey groupKey = HystrixCommandGroupKey.Factory.asKey("CommandWithFallbackGroup");
        CommandWithFallback commandWithFallback = new CommandWithFallback(groupKey);

        logger.info("Before launching command");
        List<String> result = commandWithFallback.execute();
        logger.info("Got list: " + result);

        logger.info("After launching command");

        Utils.sleep(1000);
        logger.info("Applications is going to stop");
    }
}
