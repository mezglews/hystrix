package com.hystrix.service;

import com.hystrix.Utils;
import org.apache.log4j.Logger;

/**
 * User: Szymon Mezglewski
 * Date: 2016-11-30
 */
public class VerySlowService implements Service {
    private final static Logger logger = Logger.getLogger(VerySlowService.class);

    private final int latencyInMs;

    public VerySlowService(int latencyInMs) {
        this.latencyInMs = latencyInMs;
    }


    @Override
    public void execute() {
        logger.info("Before executing very slow task");
        logger.info("Wait for " + latencyInMs +"ms...");
        Utils.sleep(latencyInMs);
        logger.info("After executing very slow task");
    }
}
