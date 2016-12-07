package com.hystrix._04._1;

import com.hystrix.Utils;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import org.apache.log4j.Logger;

//requests per second at peak when healthy × 99th percentile latency in seconds + some breathing room
public class Main {
    private static final int TOTAL_TASKS = 11;
    private final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        HystrixCommand.Setter setter = HystrixCommand.Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("group-key"))
                .andThreadPoolPropertiesDefaults(
                        HystrixThreadPoolProperties.Setter().withCoreSize(1)

//                        .withMaximumSize(3)
//                        .withAllowMaximumSizeToDivergeFromCoreSize(true) //both properties together

//                        .withMaxQueueSize(3)
//                        .withQueueSizeRejectionThreshold(1)
                );
        logger.info("Before launching command");

        for(int i=0; i<TOTAL_TASKS; i++) {
            SimpleCommand simpleCommand = new SimpleCommand(setter.andCommandKey(HystrixCommandKey.Factory.asKey("cmd")));
            simpleCommand.observe();
            logger.info(simpleCommand.getCommandKey().name() + " rejected? " + simpleCommand.isResponseRejected());
        }

        logger.info("After launching command");

        Utils.sleep(10_000);
        logger.info("Applications is going to stop");
    }
}
