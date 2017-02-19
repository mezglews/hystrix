package com.hystrix._06._2;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixRequestCache;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import org.apache.log4j.Logger;

import java.util.UUID;

/**
 * User: Szymon Mezglewski
 * Date: 2017-02-01
 */
public class CommandUsingRequestCacheWithInvalidation extends HystrixCommand<String> {
    private static final Logger LOGGER = Logger.getLogger(CommandUsingRequestCacheWithInvalidation.class);

    private static final HystrixCommandKey GETTER_KEY = HystrixCommandKey.Factory.asKey("CommandUsingRequestCacheCommand");

    private final String value;

    public CommandUsingRequestCacheWithInvalidation(String value) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GetSetGet"))
                .andCommandKey(GETTER_KEY));
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

    public static void flushCache(String value) {
        HystrixRequestCache.getInstance(GETTER_KEY,HystrixConcurrencyStrategyDefault.getInstance())
                .clear(value);
    }
}
