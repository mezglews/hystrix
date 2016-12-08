package com.hystrix._07;

import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCollapserKey;
import com.netflix.hystrix.HystrixCommand;

import java.util.Collection;
import java.util.Map;

/**
 * User: Szymon Mezglewski
 * Date: 2016-12-08
 */
//<BatchReturnType, ResponseType, RequestArgumentType>
public class CommandCollapserGetClientIdForCST extends HystrixCollapser<Map<String, User>, User, String> {
    private final ClientSecurityGateway clientSecurityGateway;
    private final String cst;

    public CommandCollapserGetClientIdForCST(ClientSecurityGateway clientSecurityGateway, String cst) {
        super(Setter.withCollapserKey(HystrixCollapserKey.Factory.asKey("collapsedKey"))
            .andCollapserPropertiesDefaults(
                    com.netflix.hystrix.HystrixCollapserProperties.Setter()
                            .withTimerDelayInMilliseconds(4000)
                            .withMaxRequestsInBatch(2)
            )
        );
        this.clientSecurityGateway = clientSecurityGateway;
        this.cst = cst;
    }

    @Override
    public String getRequestArgument() {
        return cst;
    }
                                                               //CollapsedRequest<ResponseType, RequestArgumentType>
    @Override
    protected HystrixCommand< Map<String, User>> createCommand(Collection<CollapsedRequest<User, String>> requests) {
        return new BatchCommand(requests, clientSecurityGateway);
    }

    @Override
    protected void mapResponseToRequests( Map<String, User> responseMap, Collection<CollapsedRequest<User, String>> requests) {
        for (CollapsedRequest<User, String> request : requests) {
            String requestedCst = request.getArgument();
            request.setResponse(responseMap.get(requestedCst));
        }
    }
}
