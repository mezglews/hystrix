package com.hystrix._02;

import com.netflix.hystrix.HystrixCommand;

/**
 * User: Szymon Mezglewski
 * Date: 2016-12-01
 */
public class SimpleCommand extends HystrixCommand<Void> {

    protected SimpleCommand(Setter setter) {
        super(setter);
    }

    @Override
    protected Void run() throws Exception {
        return null;
    }
}
