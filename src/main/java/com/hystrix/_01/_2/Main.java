package com.hystrix._01._2;

import com.hystrix.Utils;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import org.apache.log4j.Logger;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * User: Szymon Mezglewski
 * Date: 2016-12-01
 */
public class Main {
    private final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        HystrixCommandGroupKey groupKey = HystrixCommandGroupKey.Factory.asKey("BasicObservableCommandsGroup");
        HystrixObservableCommand<String> command = new SimpleObservableCommand(groupKey);

        logger.info("Before launching command");
        Observable<String> observe = command.observe();

        logger.info("After launching command");
        Utils.sleep(1000);
        logger.info("Applications is going to stop");
    }
}
