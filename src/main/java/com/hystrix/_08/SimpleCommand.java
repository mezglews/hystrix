package com.hystrix._08;

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

    public SimpleCommand(Setter setter) {
        super(setter);
    }

    @Override
    protected String run() throws Exception {
        logger.info("Executing simple command");
        Utils.sleep(500);
        return "result";
    }
}
