package com.hystrix._07;

import com.hystrix.Utils;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.apache.log4j.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        LOGGER.info("Before launching command");
        ClientSecurityGateway csg = new ClientSecurityGateway();

        HystrixRequestContext context = HystrixRequestContext.initializeContext();

        CommandCollapserGetClientIdForCST collapser1 = new CommandCollapserGetClientIdForCST(csg, "aaa");
        CommandCollapserGetClientIdForCST collapser2 = new CommandCollapserGetClientIdForCST(csg, "bbb");
        CommandCollapserGetClientIdForCST collapser3 = new CommandCollapserGetClientIdForCST(csg, "aaa");
        CommandCollapserGetClientIdForCST collapser4 = new CommandCollapserGetClientIdForCST(csg, "ccc");
        CommandCollapserGetClientIdForCST collapser5 = new CommandCollapserGetClientIdForCST(csg, "aaa");

        collapser1.observe();
        collapser2.observe();
        collapser3.observe();
        collapser4.observe();
        collapser5.observe();

        LOGGER.info("After launching command");

        Utils.sleep(30_000);

        LOGGER.info("Applications is going to stop");
    }
}
