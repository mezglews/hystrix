package com.hystrix._04._1;

import com.hystrix.Utils;
import com.netflix.hystrix.HystrixCommand;
import org.apache.log4j.Logger;

/**
 * User: Szymon Mezglewski
 * Date: 2016-12-01
 */
public class SimpleCommand extends HystrixCommand<Void> {
    private final static Logger logger = Logger.getLogger(SimpleCommand.class);

    protected SimpleCommand(HystrixCommand.Setter setter) {
        super(setter);
    }

    @Override
    protected Void run() throws Exception {
        logger.info("Executing simple command..");
        Utils.sleep(300);
        return null;
    }
}
