package com.hystrix._07;

import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import org.apache.log4j.Logger;

import java.util.*;

/**
 * User: Szymon Mezglewski
 * Date: 2016-12-08
 */
//<RequestArgumentType, ResponseType>
public class BatchCommand extends HystrixCommand< Map<String, User> > {
    private static final Logger LOGGER = Logger.getLogger(BatchCommand.class);
    private final ClientSecurityGateway clientSecurityGateway;

    private final Set<HystrixCollapser.CollapsedRequest<User, String>> requests;

    public BatchCommand(Collection<HystrixCollapser.CollapsedRequest<User, String>> requests, ClientSecurityGateway clientSecurityGateway) {
        super(HystrixCommand.Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("group-key"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("BatchCommand")));

        this.requests = new HashSet<>(requests);
        this.clientSecurityGateway = clientSecurityGateway;
    }

    @Override
    protected Map<String, User> run() throws Exception {
        Map<String, User> cstToUserMap = new HashMap<>();
        LOGGER.info("Got total requests to execute: " + requests.size());
        for (HystrixCollapser.CollapsedRequest<User, String> request : requests) {
            String cst = request.getArgument();
            LOGGER.info("Sending request to CSG for given cst: " + cst);
            cstToUserMap.put(cst, clientSecurityGateway.getUserForCST(cst));
        }

        return cstToUserMap;
    }
}
