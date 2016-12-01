package com.hystrix.service;

import org.apache.log4j.Logger;

/**
 * User: Szymon Mezglewski
 * Date: 2016-11-30
 */
public class BasicService implements Service {
    private final static Logger logger = Logger.getLogger(BasicService.class);

    @Override
    public void execute() {
        logger.info("Executing basic task");
    }
}
