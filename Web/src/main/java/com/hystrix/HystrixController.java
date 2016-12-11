package com.hystrix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HystrixController {

    @Autowired
    private FailingService failingService;

    @RequestMapping("/failing")
    public String index(
            @RequestParam(value = "probability", defaultValue = "0.0") double probability,
            @RequestParam(value = "sleep", defaultValue = "0") int sleep) {
        return failingService.getItem(probability, sleep);
    }

}
