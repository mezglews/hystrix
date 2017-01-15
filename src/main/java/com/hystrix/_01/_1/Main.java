package com.hystrix._01._1;

import com.hystrix.Utils;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.apache.log4j.Logger;
import rx.Observable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * User: Szymon Mezglewski
 * Date: 2016-12-01
 */
public class Main {
    private final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        HystrixCommandGroupKey groupKey = HystrixCommandGroupKey.Factory.asKey("BasicCommandsGroup");
        SimpleCommand command = new SimpleCommand(groupKey);

        logger.info("Before launching command");



        logger.info("After launching command");

        Utils.sleep(1000);
        logger.info("Applications is going to stop");
    }
}
