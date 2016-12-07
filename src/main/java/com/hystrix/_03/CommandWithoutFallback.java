package com.hystrix._03;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.util.List;

/**
 * User: Szymon Mezglewski
 * Date: 2016-12-07
 */
public class CommandWithoutFallback extends HystrixCommand<List<String>> {
    protected CommandWithoutFallback(HystrixCommandGroupKey group) {
        super(group);
    }

    @Override
    protected List<String> run() throws Exception {
        throw new RuntimeException("Cannot get list from remote service.");
    }

}
