package com.hystrix._03;

import com.hystrix.Utils;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.apache.log4j.Logger;

/**
 * User: Szymon Mezglewski
 * Date: 2016-12-01
 */
public class Main {
    private final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        HystrixCommandGroupKey groupKey = HystrixCommandGroupKey.Factory.asKey("FailingCommandGroup");
//        CommandWithFallback command = new CommandWithFallback(groupKey);
        CommandWithoutFallback command = new CommandWithoutFallback(groupKey);

        logger.info("Before launching command");
        command.toObservable()
                .doOnError(e -> logger.error("Command failed!", e))
                .subscribe(list -> logger.info("Got list: " + list));

        logger.info("After launching command");

        Utils.sleep(1000);
        logger.info("Applications is going to stop");
    }
}
