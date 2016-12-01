package com.hystrix.others;

import com.netflix.hystrix.HystrixObservableCommand;
import rx.Observable;

/**
 * User: Szymon Mezglewski
 * Date: 2016-11-30
 */
public class SampleHystrixObservaleCommand extends HystrixObservableCommand<Void> {

    protected SampleHystrixObservaleCommand(Setter setter) {
        super(setter);
    }

    @Override
    protected Observable<Void> construct() {
        return null;
    }
}
