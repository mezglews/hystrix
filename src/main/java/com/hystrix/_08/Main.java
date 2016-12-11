package com.hystrix._08;

import com.hystrix.Utils;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 * User: Szymon Mezglewski
 * Date: 2016-12-01
 */
public class Main {
    private final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MDC.put("transactionGUID", UUID.randomUUID().toString());


        HystrixCommandGroupKey groupKey = HystrixCommandGroupKey.Factory.asKey("BasicCommandsGroup");
        SimpleCommand command = new SimpleCommand(groupKey);

        logger.info("Before launching command");


        command.toObservable()
                .subscribe(next -> logger.info(next));

        logger.info("After launching command");

        Utils.sleep(2000);
        logger.info("Applications is going to stop");
    }
}
