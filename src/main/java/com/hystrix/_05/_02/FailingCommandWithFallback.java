package com.hystrix._05._02;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.exception.HystrixBadRequestException;

import java.util.Collections;
import java.util.List;

/**
 * User: Szymon Mezglewski
 * Date: 2016-12-07
 */
public class FailingCommandWithFallback extends HystrixCommand<List<String>> {
    public FailingCommandWithFallback(Setter setter) {
        super(setter);
    }

    @Override
    protected List<String> run() throws Exception {
        throw new HystrixBadRequestException("Wrong arg!", new IllegalArgumentException("Illegal argument provided!"));
    }

    @Override
    protected List<String> getFallback() {
        return Collections.emptyList();
    }
}
