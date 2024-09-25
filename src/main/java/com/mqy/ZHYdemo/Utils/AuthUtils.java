package com.mqy.ZHYdemo.Utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class AuthUtils {
    @Value("${zhy.auth.url}")
    String authUrl;

    public String getAccessToken(String clientId, String clientSecret) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(authUrl);

        // 设置头
        post.setHeader("Content-Type", "application/x-www-form-urlencoded");

        // Prepare the authorization header
        String auth = clientId + ":" + clientSecret;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
        post.setHeader("Authorization", "Basic " + encodedAuth);

        // 设置body
        String body = "grant_type=client_credentials";
        post.setEntity(new StringEntity(body));

        // 执行请求
        CloseableHttpResponse response = httpClient.execute(post);
        String responseBody = EntityUtils.toString(response.getEntity());

        // 关闭请求
        response.close();
        httpClient.close();

        // 返回token
        org.json.JSONObject jsonObject = new org.json.JSONObject(responseBody);
        return jsonObject.getJSONObject("data").getString("token");
    }
}
