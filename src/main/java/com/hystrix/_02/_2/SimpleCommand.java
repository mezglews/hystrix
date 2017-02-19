package com.hystrix._02._2;

import com.netflix.hystrix.HystrixCommand;
import org.apache.log4j.Logger;

/**
 * User: Szymon Mezglewski
 * Date: 2016-12-01
 */
public class SimpleCommand extends HystrixCommand<Void> {
    private final static Logger logger = Logger.getLogger(SimpleCommand.class);
    private final boolean shouldFail;
    private int counter = 1;

    public SimpleCommand(Setter setter, int counter, boolean shouldFail) {
        super(setter);
        this.counter = counter;
        this.shouldFail = shouldFail;
    }

    @Override
    protected Void run() throws Exception {
        logger.info("Executing command #" + (counter));
        if(shouldFail) {
            throw new RuntimeException("failed!");
        }
        return null;
    }
}
