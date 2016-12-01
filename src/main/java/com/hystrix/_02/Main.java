package com.hystrix._02;

import com.hystrix.Utils;
import com.netflix.hystrix.*;
import org.apache.log4j.Logger;
import rx.Observable;

/**
 * User: Szymon Mezglewski
 * Date: 2016-12-01
 */
public class Main {
    private final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        SimpleCommand simpleCommand = new SimpleCommand(
                HystrixCommand.Setter.withGroupKey(
                        HystrixCommandGroupKey.Factory.asKey("hystrix-command-group")
                )
                .andCommandKey(HystrixCommandKey.Factory.asKey("CommandKey"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("ThreadPool-key"))
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter()
                            .withCircuitBreakerEnabled(true)
                            .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)
                            .withCircuitBreakerSleepWindowInMilliseconds(100)
                )
        );

        Observable<Void> observable = simpleCommand.toObservable();
        observable.subscribe(aVoid -> logger.info("Done!"));

        Utils.sleep(1000);
        logger.info("Applications is going to stop");
    }
}
