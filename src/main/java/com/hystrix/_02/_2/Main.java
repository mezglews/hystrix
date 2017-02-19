package com.hystrix._02._2;

import com.hystrix.Utils;
import com.netflix.hystrix.*;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import org.apache.log4j.Logger;

import java.util.Random;

/**
 * User: Szymon Mezglewski
 * Date: 2016-12-01
 */
public class Main {
    private final static Logger logger = Logger.getLogger(Main.class);
    private static Random random = new Random();


    public static void main(String[] args) {
        HystrixCommand.Setter setter = HystrixCommand.Setter.withGroupKey(
                HystrixCommandGroupKey.Factory.asKey("hystrix-command-group")
        )
                .andCommandKey(HystrixCommandKey.Factory.asKey("CommandKey"))
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter()
                                .withCircuitBreakerEnabled(true)
//                                .withCircuitBreakerForceOpen(true)
                                .withCircuitBreakerRequestVolumeThreshold(10) //20
                                .withCircuitBreakerErrorThresholdPercentage(30) //50
                                .withCircuitBreakerSleepWindowInMilliseconds(300) //5000
                                .withMetricsRollingStatisticalWindowInMilliseconds(1000) //10_000
                                .withMetricsRollingStatisticalWindowBuckets(10) //10
                                .withMetricsHealthSnapshotIntervalInMilliseconds(10) //500
                );

        boolean isFirst = true;
        long startTime = 0;

        for(int i=1; i<=51; i++) {
            logger.info("---------------------" + i +"-----------------------");
            boolean shouldFail = shouldFailWithProbability(80);
            SimpleCommand simpleCommand = new SimpleCommand(setter, i, shouldFail);
            try {
                Utils.sleep(100);
                if(isFirst) {
                    startTime = System.currentTimeMillis();
                    isFirst = false;
                }
                simpleCommand.execute();

                logger.info("Passed");
            } catch (HystrixRuntimeException e) {
                logger.info("Failed because of " + e.getFailureType());
            }
            printHealthData(simpleCommand);
//            printMetrics(simpleCommand);
            logger.info("--------------------------------------------");
            logger.info("Delta ms: " + (System.currentTimeMillis() - startTime));
        }


        Utils.sleep(100000);
        logger.info("Applications is going to stop");
    }


    private static void printHealthData(SimpleCommand simpleCommand) {
        HystrixCommandMetrics metrics = simpleCommand.getMetrics();
        HystrixCommandMetrics.HealthCounts healthCounts = metrics.getHealthCounts();

        logger.info("Error percentage " + healthCounts.getErrorPercentage());
        logger.info("Total requests " + healthCounts.getTotalRequests());
        logger.info("Total errors " + healthCounts.getErrorCount());
    }
    private static void printMetrics(SimpleCommand simpleCommand) {
        HystrixCommandMetrics metrics = simpleCommand.getMetrics();

        logger.info("Max concurrent requests executions " + metrics.getRollingMaxConcurrentExecutions());
        logger.info("Total time percentile 0.9 =  " + metrics.getTotalTimePercentile(0.9));
        logger.info("Total time percentile 0.1 =  " + metrics.getTotalTimePercentile(0.1));
        logger.info("Execution time percentile 0.9 =  " + metrics.getExecutionTimePercentile(0.9));
        logger.info("Execution time percentile 0.1 =  " + metrics.getExecutionTimePercentile(0.1));
        logger.info("Execution time mean =  " + metrics.getExecutionTimeMean());
        logger.info("Total time mean =  " + metrics.getTotalTimeMean());
    }

    private static boolean shouldFailWithProbability(int probability) {
        return probability >= random.nextInt(100);
    }
}
