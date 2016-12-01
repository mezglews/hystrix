package com.hystrix.others;

import com.hystrix.service.Service;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * User: Szymon Mezglewski
 * Date: 2016-11-30
 */
public class BasicHystrixCommand extends HystrixCommand<Void> {
    private final Service service;

    public BasicHystrixCommand(Service service, HystrixCommandGroupKey group) {
        super(group);
        this.service = service;
    }

    @Override
    protected Void run() throws Exception {
        service.execute();
        return null;
    }
}
