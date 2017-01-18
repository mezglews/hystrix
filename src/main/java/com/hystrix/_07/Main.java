package com.hystrix._07;

import com.hystrix.Utils;
import com.netflix.hystrix.HystrixEventType;
import com.netflix.hystrix.HystrixInvokableInfo;
import com.netflix.hystrix.HystrixRequestLog;
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

        collapser1.toObservable().subscribe(e -> LOGGER.info("Response: " + e));
        collapser2.toObservable().subscribe(e -> LOGGER.info("Response: " + e));
        collapser3.toObservable().subscribe(e -> LOGGER.info("Response: " + e));
        collapser4.toObservable().subscribe(e -> LOGGER.info("Response: " + e));
        collapser5.toObservable().subscribe(e -> LOGGER.info("Response: " + e));

        LOGGER.info("After launching command");


        Utils.sleep(15_000);
        int numExecuted = HystrixRequestLog.getCurrentRequest().getAllExecutedCommands().size();

        LOGGER.info("Total executed: " + numExecuted);

        for (HystrixInvokableInfo<?> command : HystrixRequestLog.getCurrentRequest().getAllExecutedCommands()) {
            String name = command.getCommandKey().name();
            boolean collapsed = command.getExecutionEvents().contains(HystrixEventType.COLLAPSED);
            boolean success = command.getExecutionEvents().contains(HystrixEventType.SUCCESS);
            LOGGER.info("Command " + name +" collapsed? " + collapsed + "  success? " + success);
        }


        Utils.sleep(30_000);

        LOGGER.info("Applications is going to stop");
    }
}
