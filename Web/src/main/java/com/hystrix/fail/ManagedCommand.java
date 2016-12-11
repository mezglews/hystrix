package com.hystrix.fail;

import com.hystrix.Utils;
import com.netflix.hystrix.HystrixCommand;
import org.apache.log4j.Logger;

import java.util.Random;
import java.util.UUID;

public class ManagedCommand extends HystrixCommand<String> {
    private final static Logger logger = Logger.getLogger(ManagedCommand.class);
    private static final Random RANDOM = new Random();
    private final double probability;
    private final int sleep;

    public ManagedCommand(Setter setter, double probability, int sleep) {
        super(setter);
        this.probability = probability;
        this.sleep = sleep;
    }

    @Override
    protected String run() throws Exception {
        logger.info("Executing simple command..");
        Utils.sleep(sleep);
        if(RANDOM.nextDouble() <= probability) {
            throw new RuntimeException("Command failed !");
        }
        return UUID.randomUUID().toString();
    }
}
