package com.hystrix._08;

import com.hystrix.Utils;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import rx.Observable;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 * User: Szymon Mezglewski
 * Date: 2016-12-01
 */
public class Main {
    private final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MDC.put("transactionGUID", UUID.randomUUID().toString());


        HystrixCommand.Setter setter = HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("BasicCommandsGroup"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("cmd"))
                .andThreadPoolPropertiesDefaults(
                        HystrixThreadPoolProperties.Setter()
                                .withCoreSize(10)
                                .withMaximumSize(19)
                                .withAllowMaximumSizeToDivergeFromCoreSize(true)
                );

        for(int i=0; i< 20;i ++) {
            SimpleCommand command = new SimpleCommand(setter);
            command.toObservable()
                    .onErrorResumeNext(error -> Observable.empty())
                    .subscribe(next -> logger.info(next));
        }


        logger.info("Before launching command");




        logger.info("After launching command");

        Utils.sleep(2000);
        logger.info("Applications is going to stop");
    }
}
