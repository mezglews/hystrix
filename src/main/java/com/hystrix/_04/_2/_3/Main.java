package com.hystrix._04._2._3;


import com.hystrix.Utils;
import com.hystrix._04._2.SimpleCommand;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import org.apache.log4j.Logger;

import java.util.Random;

import static com.netflix.hystrix.HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE;
import static com.netflix.hystrix.HystrixCommandProperties.ExecutionIsolationStrategy.THREAD;

public class Main {
    private final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException {
        HystrixCommand.Setter setter = HystrixCommand.Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("group-key"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("cmd"))
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter()
                                .withExecutionIsolationStrategy(THREAD)//SEMAPHORE
                                .withExecutionTimeoutInMilliseconds(40)
                );


        logger.info("Before launching command");

        Thread t = new Thread(() -> {

            LongRunningCommand longRunningCommand = new LongRunningCommand(setter); //sleep set to 3000
            longRunningCommand.execute();
        });
        t.setName("Thread#1");
        t.start();

        logger.info("After launching command");

        Utils.sleep(10_000);
        logger.info("Applications is going to stop");
    }
}
