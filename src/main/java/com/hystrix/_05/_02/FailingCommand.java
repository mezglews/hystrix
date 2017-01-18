package com.hystrix._05._02;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.exception.HystrixBadRequestException;

import java.util.List;


public class FailingCommand extends HystrixCommand<List<String>> {

    public FailingCommand(Setter setter) {
        super(setter);
    }

    @Override
    protected List<String> run() throws Exception {
        throw new HystrixBadRequestException("Wrong arg!", new IllegalArgumentException("Illegal argument provided!"));
    }

}
