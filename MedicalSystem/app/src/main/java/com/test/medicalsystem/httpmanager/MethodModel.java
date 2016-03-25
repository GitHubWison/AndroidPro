package com.test.medicalsystem.httpmanager;

import android.util.Log;

import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

/**
 * Created by xuqiwei-Office on 2016/3/24.
 * 网络请求的方法名
 */
public class MethodModel {

    public static String testConnection = "/api/TestNet/Get";
    public static String login = "/api/Public/CheckUserIsValid";
    public static String auth = "Mdsd.Phep.Api:mdsd.phep.api.2005$";
//    public static String auth = "alkdsjfaskdjlf";

    /**
     * 返回一个url
     * @param method ：方法名
     * @return 返回一个url
     */
    public static String spellURL(String doMain, String method)
    {
        String urlString = "";
//        String doMain = DoMainModel.getInstance().getDoMain();
        if (doMain != null)
        {
            urlString += doMain;
        }else
        {
            Log.d("error", "DoMainModel中的domain为空");
        }
//        开始拼接方法名
        urlString += method;
        return urlString;

    }
}


