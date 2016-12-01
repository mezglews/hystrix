package com.hystrix._01._2;

import com.hystrix._01._1.SimpleCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import org.apache.log4j.Logger;
import rx.Observable;
import rx.Subscriber;

/**
 * User: Szymon Mezglewski
 * Date: 2016-12-01
 */
public class SimpleObservableCommand extends HystrixObservableCommand<String>{
    private final static Logger logger = Logger.getLogger(SimpleObservableCommand.class);

    protected SimpleObservableCommand(HystrixCommandGroupKey group) {
        super(group);
    }

    @Override
    protected Observable<String> construct() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> observer) {
                try {
                    if (!observer.isUnsubscribed()) {
                        logger.info("Doing some network call.. ");
                        observer.onNext("RESPONSE");
                        observer.onCompleted();
                    }
                } catch (Exception e) {
                    observer.onError(e);
                }
            }
        } );
    }
}
