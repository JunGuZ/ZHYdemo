package com.mqy.ZHYdemo.Domain;

import org.json.JSONArray;
import org.json.JSONObject;

public class PatentQueryParams {
    private String token; // token
    private String clientId; // id
    private int offset; // 偏移量
    private int limit; // 返回的专利个数
    private String queryText; // 检索式
    private String collapseBy; // 专利去重的排序字段
    private String collapseType; // 专利去重条件
    private String collapseOrder; // 专利去重的排序顺序
    private String field; // 排序字段
    private String order; // 排序方式

    public PatentQueryParams() {
        this.offset = 0; // 默认偏移量为 0
        this.limit = 10; // 默认返回的专利个数为 10
        this.queryText = ""; // 默认检索式为空字符串
        this.collapseBy = "PBD"; // 默认专利去重的排序字段
        this.collapseType = "DOCDB"; // 默认专利去重条件
        this.collapseOrder = "LATEST"; // 默认专利去重的排序顺序
        this.field = "PBDT_YEARMONTHDAY";// 默认排序字段
        this.order = "DESC";// 默认排序方式
    }

    // Getter 和 Setter 方法
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getQueryText() {
        return queryText;
    }

    public void setQueryText(String queryText) {
        this.queryText = queryText;
    }

    public String getCollapseBy() {
        return collapseBy;
    }

    public void setCollapseBy(String collapseBy) {
        this.collapseBy = collapseBy;
    }

    public String getCollapseType() {
        return collapseType;
    }

    public void setCollapseType(String collapseType) {
        this.collapseType = collapseType;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getCollapseOrder() {
        return collapseOrder;
    }

    public void setCollapseOrder(String collapseOrder) {
        this.collapseOrder = collapseOrder;
    }

}
