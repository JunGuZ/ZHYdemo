package com.mqy.ZHYdemo.Utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.Base64;

public class AuthUtils {
    public static String getAccessToken(String clientId, String clientSecret) throws Exception {
        String url = "https://connect.zhihuiya.com/oauth/token";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);

        // Set headers
        post.setHeader("Content-Type", "application/x-www-form-urlencoded");

        // Prepare the authorization header
        String auth = clientId + ":" + clientSecret;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
        post.setHeader("Authorization", "Basic " + encodedAuth);

        // Set body
        String body = "grant_type=client_credentials";
        post.setEntity(new StringEntity(body));

        // Execute request
        CloseableHttpResponse response = httpClient.execute(post);
        String responseBody = EntityUtils.toString(response.getEntity());

        // Close resources
        response.close();
        httpClient.close();

        return responseBody; // Return the token response
    }
}
