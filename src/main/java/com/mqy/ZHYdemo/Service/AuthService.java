package com.mqy.ZHYdemo.Service;

import com.mqy.ZHYdemo.Domain.Account;
import com.mqy.ZHYdemo.Utils.AuthUtils;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Resource
    AuthUtils authUtils;

    public String auth (Account account) {
        String tokenResponse = null;
        try {
            tokenResponse = authUtils.getAccessToken(account.getClientId(), account.getClientSecret());
            System.out.println("Token Response: " + tokenResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tokenResponse;
    }
}
