package com.hystrix._03;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.util.Arrays;
import java.util.List;

/**
 * User: Szymon Mezglewski
 * Date: 2016-12-01
 */
public class CommandWithFallback extends HystrixCommand<List<String>> {
    private static final List<String> DEFAULT_RESPONSE = Arrays.asList("DEFAULT_ELEMENT");
    protected CommandWithFallback(HystrixCommandGroupKey group) {
        super(group);
    }

    @Override
    protected List<String> run() throws Exception {
        throw new RuntimeException("Cannot get list from remote service.");
    }

    @Override
    protected List<String> getFallback() {
        return DEFAULT_RESPONSE;
    }
}
