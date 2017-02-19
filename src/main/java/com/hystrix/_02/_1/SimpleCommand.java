package com.hystrix._02._1;

import com.netflix.hystrix.HystrixCommand;
import org.apache.log4j.Logger;

/**
 * User: Szymon Mezglewski
 * Date: 2016-12-01
 */
public class SimpleCommand extends HystrixCommand<Void> {
    private final static Logger logger = Logger.getLogger(SimpleCommand.class);

    public SimpleCommand(Setter setter) {
        super(setter);
    }

    @Override
    protected Void run() throws Exception {
        logger.info("Executing command " + this.getCommandKey().name());
        return null;
    }
}
