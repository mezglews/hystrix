package com.hystrix.metrics;

import com.hystrix.Utils;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import rx.Notification;
import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import java.util.UUID;

/**
 * User: Szymon Mezglewski
 * Date: 2017-01-21
 */
public class RxTest {
    private static final Logger LOGGER = Logger.getLogger(RxTest.class);

    public static void main(String[] args) {
        MDC.put("transactionGUID", UUID.randomUUID().toString());

        Observable.fromCallable(() -> {
            return "a";
        }).doOnEach(notification -> LOGGER.info(notification.getValue()))
                .subscribeOn(Schedulers.io())
                .subscribe(next -> LOGGER.info(next));


        Utils.sleep(2000);
    }

}
