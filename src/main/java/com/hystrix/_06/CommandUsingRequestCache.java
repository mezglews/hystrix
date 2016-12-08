package com.hystrix._06;

import com.netflix.hystrix.HystrixCommand;
import org.apache.log4j.Logger;

import java.util.UUID;

/**
 * User: Szymon Mezglewski
 * Date: 2016-12-08
 */
public class CommandUsingRequestCache extends HystrixCommand<String> {
    private static final Logger LOGGER = Logger.getLogger(CommandUsingRequestCache.class);

    private final String value;

    public CommandUsingRequestCache(Setter setter, String value) {
        super(setter);
        this.value = value;
    }

    @Override
    protected String run() throws Exception {
        LOGGER.info("Executing heavy task for value=" + value);
        return value + UUID.randomUUID().toString();
    }

    @Override
    protected String getCacheKey() {
        return String.valueOf(value);
    }
}
