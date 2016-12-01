package com.hystrix.others;

import com.hystrix.Utils;
import com.hystrix.service.Service;
import com.hystrix.service.VerySlowService;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.apache.log4j.Logger;
import rx.schedulers.Schedulers;

/**
 * User: Szymon Mezglewski
 * Date: 2016-11-30
 */
public class ExecuteVsObserve {
    private final static Logger logger = Logger.getLogger(ExecuteVsObserve.class);

    public static void main(String[] args) {
        Service service = new VerySlowService(900);
        BasicHystrixCommand basicCommand = new BasicHystrixCommand(service, HystrixCommandGroupKey.Factory.asKey("BasicCommand"));

        logger.info("About to execute service");
        basicCommand
                .observe()
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe();
        Utils.sleep(2000);
        logger.info("After executing service");

    }

}
