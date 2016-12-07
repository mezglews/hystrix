package com.hystrix._05._02;

import com.hystrix.Utils;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.exception.HystrixBadRequestException;

import java.util.Collections;
import java.util.List;

/**
 https://netflix.github.io/Hystrix/javadoc/com/netflix/hystrix/exception/HystrixBadRequestException.html
 An exception representing an error with provided arguments or state rather than an execution failure.
 Unlike all other exceptions thrown by a HystrixCommand this will not trigger fallback, not count against failure metrics and thus not trigger the circuit breaker.

 NOTE: This should only be used when an error is due to user input such as IllegalArgumentException otherwise it defeats the purpose of fault-tolerance and fallback behavior.
 */
public class FailingCommand extends HystrixCommand<List<String>> {

    public FailingCommand(Setter setter) {
        super(setter);
    }

    @Override
    protected List<String> run() throws Exception {
        throw new HystrixBadRequestException("Wrong arg!", new IllegalArgumentException("Illegal argument provided!"));
//        return Collections.emptyList();
    }

}
