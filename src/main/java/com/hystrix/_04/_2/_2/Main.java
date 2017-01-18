package com.hystrix._04._2._2;


import com.hystrix.Utils;
import com.hystrix._04._2.SimpleCommand;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import org.apache.log4j.Logger;

import java.util.Random;

import static com.netflix.hystrix.HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE;

public class Main {
    private static final int TOTAL_TASKS = 4;
    private final static Logger logger = Logger.getLogger(Main.class);
    private final static Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        HystrixCommand.Setter setter = HystrixCommand.Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("group-key"))
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter()
                                .withExecutionIsolationStrategy(SEMAPHORE)
                                .withExecutionIsolationSemaphoreMaxConcurrentRequests(3)
                );


        logger.info("Before launching command");

        for (int counter = 0; counter < TOTAL_TASKS; counter++) {

            Thread t = new Thread(() -> {

                SimpleCommand simpleCommand = new SimpleCommand(setter.andCommandKey(HystrixCommandKey.Factory.asKey("cmd")));
                simpleCommand.execute();
            });
            t.setName("Thread#"+counter);
            t.start();
        }

        logger.info("After launching command");

        Utils.sleep(10_000);
        logger.info("Applications is going to stop");
    }
}
