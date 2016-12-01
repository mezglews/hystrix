package com.hystrix._04._2;

import com.hystrix.Utils;
import com.netflix.hystrix.HystrixCommand;
import org.apache.log4j.Logger;

public class SimpleCommand extends HystrixCommand<Void> {
    private final static Logger logger = Logger.getLogger(SimpleCommand.class);

    protected SimpleCommand(Setter setter) {
        super(setter);
    }

    @Override
    protected Void run() throws Exception {
        logger.info("Executing simple command..");
        Utils.sleep(300);
        return null;
    }
}
