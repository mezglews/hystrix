package com.hystrix._05._01;

import com.hystrix.Utils;
import com.hystrix.metrics.CommandMetricsLogger;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import org.apache.log4j.Logger;
import rx.Observable;

public class ToManyCommandsMain {
    private static final Logger LOGGER = Logger.getLogger(ToManyCommandsMain.class);
    private static final String COMMAND_KEY = "command";
    private static final HystrixCommandKey HYSTRIX_COMMAND_KEY = HystrixCommandKey.Factory.asKey(COMMAND_KEY);

    public static void main(String[] args) {

        HystrixCommand.Setter setter = HystrixCommand.Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("group-key"))
                .andCommandKey(HYSTRIX_COMMAND_KEY)
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().withCoreSize(2));

        LOGGER.info("Before launching command");
        for (int i = 0; i < 3; i++) {
                FailingCommand command = new FailingCommand(setter);
                command.toObservable()
                        .onErrorResumeNext(error -> {
                            if (error instanceof HystrixRuntimeException) {
                                HystrixRuntimeException e = (HystrixRuntimeException) error;
                                LOGGER.error("Command failed: FailureType: " + e.getFailureType() + " with msg: " + e.getCause().getMessage());
                            }
                            return Observable.error(error);
                        })
                        .subscribe(next -> {}, error -> {});
        }


        Utils.sleep(3000);
        LOGGER.info("After launching command");
        CommandMetricsLogger.logMetricsForCommandKey(HYSTRIX_COMMAND_KEY);

        LOGGER.info("Applications is going to stop");
    }
}
