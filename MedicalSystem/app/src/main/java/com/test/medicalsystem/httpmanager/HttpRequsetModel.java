package com.test.medicalsystem.httpmanager;

import android.content.Context;

import com.loopj.android.http.RequestParams;
import com.test.medicalsystem.tools.Tool;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * 网络请求需要用到的类
 */
public class HttpRequsetModel {
    /**
     * 头部信息
     */
    private JSONObject jsonObject;
    /**
     * url
     */
    private String url;
    /**
     * url中的参数
     */
    private RequestParams params;
    /**
     * 上下文
     */
    private Context context;
    /**
     * 是否为get请求
     */
    private  boolean isGet;

    public HttpRequsetModel(JSONObject jsonObject, String url, RequestParams params, Context context, boolean isGet) {
//        this.headers = headers;
//        this.stringEntity = Tool.getEntityWithJsonObject(jsonObject);
        this.jsonObject = jsonObject;
        this.url = url;
        this.params = params;
        this.context = context;
        this.isGet = isGet;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public RequestParams getParams() {
        return params;
    }

    public void setParams(RequestParams params) {
        this.params = params;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public boolean isGet() {
        return isGet;
    }

    public void setIsGet(boolean isGet) {
        this.isGet = isGet;
    }
}
