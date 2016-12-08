package com.hystrix._07;

/**
 * User: Szymon Mezglewski
 * Date: 2016-12-08
 */
public class User {
    private final String clientId;

    public User(String clientId) {
        this.clientId = clientId;
    }

    public String getClientId() {
        return clientId;
    }

    @Override
    public String toString() {
        return "User{" +
                "clientId='" + clientId + '\'' +
                '}';
    }
}
