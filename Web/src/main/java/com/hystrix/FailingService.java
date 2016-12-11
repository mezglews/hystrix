package com.hystrix;

import com.hystrix.fail.ManagedCommand;
import com.netflix.hystrix.*;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * User: Szymon Mezglewski
 * Date: 2016-12-11
 */
@Component
public class FailingService {
    private final static Logger logger = Logger.getLogger(FailingService.class);

    private static HystrixCommand.Setter setter = HystrixCommand.Setter
            .withGroupKey(HystrixCommandGroupKey.Factory.asKey("Failing-Command-Group"))
            .andCommandKey(HystrixCommandKey.Factory.asKey("FailingCommand"))
            .andCommandPropertiesDefaults(
                    HystrixCommandProperties.Setter()
            )
            .andThreadPoolPropertiesDefaults(
                    HystrixThreadPoolProperties.Setter()
            );

    public String getItem(double probability, int sleep) {
        ManagedCommand command = new ManagedCommand(setter, probability, sleep);

        try {
            return command.execute();
        } catch (HystrixRuntimeException e) {
            logger.error("Hystrix failure of type: " + e.getFailureType() + " with reason: " + e.getCause().getMessage());
            return null;
        }
    }
}
