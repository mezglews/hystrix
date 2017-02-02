package com.hystrix._01._4;

import com.hystrix.Utils;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.apache.log4j.Logger;

/**
 * User: Szymon Mezglewski
 * Date: 2016-12-01
 */
public class SimpleCommand extends HystrixCommand<String> {
    private final static Logger logger = Logger.getLogger(SimpleCommand.class);

    public SimpleCommand(HystrixCommandGroupKey hystrixCommandGroupKey) {
        super(hystrixCommandGroupKey);
    }

    @Override
    protected String run() throws Exception {
        logger.info("Doing some network call.. ");
        Utils.sleep(500);
        return "response";
    }
}
