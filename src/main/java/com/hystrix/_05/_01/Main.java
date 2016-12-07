package com.hystrix._05._01;

import com.hystrix.Utils;
import com.hystrix.metrics.CommandMetricsLogger;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import org.apache.log4j.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class);
    private static final String COMMAND_KEY = "command";
    private static final HystrixCommandKey HYSTRIX_COMMAND_KEY = HystrixCommandKey.Factory.asKey(COMMAND_KEY);

    public static void main(String[] args) {

        HystrixCommand.Setter setter = HystrixCommand.Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("group-key"))
                .andCommandKey(HYSTRIX_COMMAND_KEY);
//                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withCircuitBreakerForceOpen(true));

        LOGGER.info("Before launching command");
        try {
            FailingCommand command = new FailingCommand(setter);
            command.execute();
        } catch (HystrixRuntimeException e) {
            LOGGER.error("Command failed: ", e);
            LOGGER.error("FailureType: " + e.getFailureType());
        }

        LOGGER.info("After launching command");

        Utils.sleep(3000);
        CommandMetricsLogger.logMetricsForCommandKey(HYSTRIX_COMMAND_KEY);

        LOGGER.info("Applications is going to stop");
    }
}
