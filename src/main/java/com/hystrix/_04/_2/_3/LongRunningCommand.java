package com.hystrix._04._2._3;

import com.hystrix.Utils;
import com.netflix.hystrix.HystrixCommand;
import org.apache.log4j.Logger;

public class LongRunningCommand extends HystrixCommand<Void> {
    private final static Logger logger = Logger.getLogger(LongRunningCommand.class);

    public LongRunningCommand(Setter setter) {
        super(setter);
    }

    @Override
    protected Void run() throws Exception {
        logger.info("Executing simple command..");
        Utils.sleep(3000);
        logger.info("Command executed");
        return null;
    }
}
