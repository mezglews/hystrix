package com.hystrix._04._2._1;


import com.hystrix.Utils;
import com.hystrix._04._2.SimpleCommand;
import com.netflix.hystrix.*;
import org.apache.log4j.Logger;

import static com.netflix.hystrix.HystrixCommandProperties.ExecutionIsolationStrategy.*;

public class Main {
    private static final int TOTAL_TASKS = 40;
    private final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        HystrixCommand.Setter setter = HystrixCommand.Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("group-key"))
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter().withExecutionIsolationStrategy(THREAD)
                                .withExecutionIsolationSemaphoreMaxConcurrentRequests(2)
                );


        logger.info("Before launching command");

        for (int i = 0; i < TOTAL_TASKS; i++) {
            SimpleCommand simpleCommand = new SimpleCommand(setter.andCommandKey(HystrixCommandKey.Factory.asKey("cmd" + i)));

            simpleCommand.execute();
        }

        logger.info("After launching command");

        Utils.sleep(10_000);
        logger.info("Applications is going to stop");
    }
}
