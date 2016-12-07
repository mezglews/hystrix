package com.hystrix.metrics;

import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandMetrics;
import com.netflix.hystrix.HystrixEventType;
import org.apache.log4j.Logger;

/**
 * User: Szymon Mezglewski
 * Date: 2016-12-07
 */
public class CommandMetricsLogger {
    private static final Logger LOGGER = Logger.getLogger(CommandMetricsLogger.class);

    public static void logMetricsForCommandKey(HystrixCommandKey hystrixCommandKey) {

        HystrixCommandMetrics metrics = HystrixCommandMetrics.getInstance(hystrixCommandKey);

        HystrixCommandMetrics.HealthCounts healthCounts = metrics.getHealthCounts();
        LOGGER.info("==============================================");
        LOGGER.info("Total requests: " + healthCounts.getTotalRequests());
        LOGGER.info("Error count: " + healthCounts.getErrorCount());
        LOGGER.info("Error percentage: " + healthCounts.getErrorPercentage() + "%");
        LOGGER.info("==============================================");

    }
}
