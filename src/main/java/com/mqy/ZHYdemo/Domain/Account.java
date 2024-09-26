package com.mqy.ZHYdemo.Domain;

import org.springframework.web.util.pattern.PathPattern;

import java.io.PrintWriter;

public class Account {
    private String clientId;
    private String clientSecret;

    public Account() {
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    @Override
    public String toString() {
        return "Account{" +
                "clientId='" + clientId + '\'' +
                ", clientSecret='" + clientSecret + '\'' +
                '}';
    }
}
