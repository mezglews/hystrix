package com.hystrix._01._3;

import com.hystrix.Utils;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.apache.log4j.Logger;

/**
 * User: Szymon Mezglewski
 * Date: 2016-12-01
 */
public class SimpleCommand extends HystrixCommand<Void> {
    private final static Logger logger = Logger.getLogger(SimpleCommand.class);

    public SimpleCommand(HystrixCommandGroupKey hystrixCommandGroupKey) {
        super(hystrixCommandGroupKey);
    }

    @Override
    protected Void run() throws Exception {
        logger.info("Executing simple command");
        Utils.sleep(500);
        return null;
    }
}
