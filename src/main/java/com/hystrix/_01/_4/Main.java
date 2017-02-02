package com.hystrix._01._4;

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

        HystrixCommandGroupKey groupKey = HystrixCommandGroupKey.Factory.asKey("BasicObservableCommandsGroup");
        SimpleCommand command = new SimpleCommand(groupKey);

        logger.info("Before launching command");

        command.toObservable()
                .doOnNext(next -> logger.info("First on next: " + next))
                .map(next -> "modified " + next)
                .doOnNext(next -> logger.info("Second onNext: " + next))
                .map(next -> "heavy modified " + next)
                .subscribe(next -> logger.info("Subscribed and got: " + next));


        logger.info("After launching command");
        Utils.sleep(1000);
        logger.info("Applications is going to stop");
    }
}
