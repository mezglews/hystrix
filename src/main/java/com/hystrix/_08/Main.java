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
 * http://netflix.github.io/Hystrix/javadoc/com/netflix/hystrix/strategy/concurrency/HystrixConcurrencyStrategy.html#wrapCallable(java.util.concurrent.Callable)
 */
public class Main {
    private final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {


        HystrixCommand.Setter setter = HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("BasicCommandsGroup"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("cmd"))
                .andThreadPoolPropertiesDefaults(
                        HystrixThreadPoolProperties.Setter()
                                .withCoreSize(20)
                );

        for(int i=0; i< 20;i ++) {
            MDC.put("transactionGUID", UUID.randomUUID().toString());
                SimpleCommand command = new SimpleCommand(setter);
//            Thread t = new Thread(() -> {
//                logger.info(command.execute());
//            });
//            t.start();

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
