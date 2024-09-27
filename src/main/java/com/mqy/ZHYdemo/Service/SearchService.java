package com.mqy.ZHYdemo.Service;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.mqy.ZHYdemo.Domain.PatentQueryParams;
import com.mqy.ZHYdemo.Domain.ResponseBody;
import com.mqy.ZHYdemo.Utils.AuthUtils;
import com.mqy.ZHYdemo.Utils.HttpUtils;
import jakarta.annotation.Resource;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class SearchService {

    @Value("${zhy.host}")
    String host;
    @Value("${zhy.path}")
    String path;

    public ResponseBody search (PatentQueryParams query) {
        String token = query.getToken();
        String apikey = query.getClientId();

        if (token == null || "".equals(token) || apikey == null || "".equals(apikey)) {
            return new ResponseBody(400, "无权限", null);
        }
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("authorization", "Bearer " + token);

        Map<String, String> querys = new HashMap<String, String>();
        querys.put("apikey", apikey);

        // 构造请求体
        PatentQueryParams body = new PatentQueryParams();

        // 校验前端传入的字段，非空就覆写
        if (query.getQueryText() == null || "".equals(query.getQueryText())) {
            return new ResponseBody(68300004, "请求参数错误（QueryText不能为空）", null);
        } else {
            body.setQueryText(query.getQueryText());
        }
        if (query.getLimit() != 0) {
            body.setLimit(query.getLimit());
        }
        if (query.getCollapseBy() != null && !"".equals(query.getCollapseBy())) {
            body.setCollapseBy(query.getCollapseBy());
        }
        if (query.getCollapseType() != null && !"".equals(query.getCollapseType())) {
            body.setCollapseType(query.getCollapseType());
        }
        if (query.getCollapseOrder() != null && !"".equals(query.getCollapseOrder())) {
            body.setCollapseOrder(query.getCollapseOrder());
        }
        if (query.getField() != null && !"".equals(query.getField())) {
            body.setField(query.getField());
        }
        if (query.getOrder() != null && !"".equals(query.getOrder())) {
            body.setOrder(query.getOrder());
        }

        JSONObject dataBody = new JSONObject();
        dataBody.put("offset", body.getOffset()); // 偏移量
        dataBody.put("limit", body.getLimit()); // 返回的专利个数，最大1000

        // 设置其他参数
        dataBody.put("query_text", body.getQueryText()); // 设置检索式
        dataBody.put("collapse_by", body.getCollapseBy()); // 选择专利去重的排序字段
        dataBody.put("collapse_type", body.getCollapseType()); // 选择专利去重条件
        dataBody.put("collapse_order", body.getCollapseOrder()); // 专利去重的排序顺序
        // 设置排序
        dataBody.put("sort", Arrays.asList(
                new JSONObject() {{
                    put("field", body.getField()); // 排序字段
                    put("order", body.getOrder()); // 排序方式
                }}
        ));
        // 输出构造的 JSON
        System.out.println("构造的 JSON 请求体：");
        System.out.println(dataBody.toJSONString());

        org.json.JSONObject res = null;
        try {
            HttpResponse response = HttpUtils.doPost(host, path, null, headers, querys, dataBody.toJSONString());
            String jsonString = EntityUtils.toString(response.getEntity());
            System.out.println(jsonString);
            res = new org.json.JSONObject(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (res.has("error_code")) {
            int errorCode = 0;
            String error_msg = null;
            try {
                errorCode = res.getInt("error_code");
                if (res.has("error_msg")) {
                    error_msg = res.getString("error_msg");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
//            if (errorCode == 67200003) {
//                System.out.println("Error code is 67200003");
//            } else {
//                System.out.println("Error code is: " + errorCode);
//            }
            return  new ResponseBody(errorCode, error_msg, res.toString());
        }

        return new ResponseBody(200, "成功", res.toString());
    }


}
