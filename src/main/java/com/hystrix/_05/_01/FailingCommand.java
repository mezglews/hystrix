package com.hystrix._05._01;

import com.hystrix.Utils;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.util.Collections;
import java.util.List;

/**
 * User: Szymon Mezglewski
 * Date: 2016-12-07
 */
public class FailingCommand extends HystrixCommand<List<String>> {
    private final int sleepInMs;

    public FailingCommand(Setter setter) {
        super(setter);
        sleepInMs = 0;
    }

    public FailingCommand(Setter setter, int sleepInMs) {
        super(setter);
        this.sleepInMs = sleepInMs;
    }
    @Override
    protected List<String> run() throws Exception {
        if(sleepInMs > 0) {
            Utils.sleep(sleepInMs);
        }
        throw new RuntimeException("Cannot get something from remote service.");
//        return Collections.emptyList();
    }

}
