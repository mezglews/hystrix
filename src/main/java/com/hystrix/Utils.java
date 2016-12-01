package com.hystrix;

/**
 * User: Szymon Mezglewski
 * Date: 2016-11-30
 */
public class Utils {
    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {}
    }

}
