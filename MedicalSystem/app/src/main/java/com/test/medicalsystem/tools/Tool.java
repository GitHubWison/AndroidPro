package com.test.medicalsystem.tools;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.test.medicalsystem.httpmanager.HttpRequest;
import com.test.medicalsystem.httpmanager.HttpRequsetModel;
import com.test.medicalsystem.httpmanager.MethodModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Created by Nangy-Office on 2016/3/24.
 */
public class Tool {
    /*
    存储登录信息的sharepreference
     */
    public static String LOGIN_SP = "LOGIN";

    /**
     * MD5加密程序
     * @param info
     * @return
     */
    public static String getMD5(String info)
    {
        try
        {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(info.getBytes("UTF-8"));
            byte[] encryption = md5.digest();

            StringBuffer strBuf = new StringBuffer();
            for (int i = 0; i < encryption.length; i++)
            {
                if (Integer.toHexString(0xff & encryption[i]).length() == 1)
                {
                    strBuf.append("0").append(Integer.toHexString(0xff & encryption[i]));
                }
                else
                {
                    strBuf.append(Integer.toHexString(0xff & encryption[i]));
                }
            }

            return strBuf.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            return "";
        }
        catch (UnsupportedEncodingException e)
        {
            return "";
        }
    }

    /**
     * 将jsonobject转换成StringEntity,网络传输
     * @param jsonObject
     * @return
     */
    public static StringEntity getEntityWithJsonObject(JSONObject jsObject)
    {
        JSONObject jsonObject = new JSONObject();
        StringEntity stringEntity = null;
        try {
            stringEntity = new StringEntity(jsonObject.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return stringEntity;

    }

    /**
     * 内网检测,需要单独写个方法
     * @param CheckNetWorkListener
     */
    public static void checkNetWorkStatues(Context context, final CheckNetWorkListener checkNetWorkListener)
    {
        String domain = Tool.getSp(context, SPreference.Login.sp_name, SPreference.Login.domain);
        Log.d("domain==", domain);
        if (!domain.equals("")){
//            已经将domain记录在了sp中
            String url = MethodModel.spellURL(domain, MethodModel.testConnection);
            AsyncHttpClient client = new AsyncHttpClient();
            client.setBasicAuth("Mdsd.Phep.Api", "mdsd.phep.api.2005$");
            client.setTimeout(8000);
            client.post(url, new JsonHttpResponseHandler(){
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    checkNetWorkListener.netOn();
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    super.onFailure(statusCode, headers, throwable, errorResponse);
                    checkNetWorkListener.netOff();
                }
            });
        }
        else {
            Log.d("error==","没有记录domain");
        }



    }
    public interface CheckNetWorkListener
    {
        /**
         * 内网通畅
         */
        public void netOn();

        /**
         * 内网链接异常
         */
        public void netOff();
    }

    /**
     * 获得sharepreference
     * @param context
     * @param spClass sharepreference种类
     * @param spName sharepreference具体的名
     * @return
     */
    public static String getSp(Context context, String spClass, String spName)
    {

        SharedPreferences sharedPreferences = context.getSharedPreferences(spClass, Context.MODE_PRIVATE);
        String result = sharedPreferences.getString(spName, "");
        return result;
    }

    /**
     * 存入sharepreference
     * @param context
     * @param spClass sharepreference种类
     * @param spName sharepreference具体的名
     * @param spValue  sharepreference具体的值
     */
    public static void saveSp(Context context, String spClass, String spName, String spValue)
    {
        SharedPreferences share = context.getSharedPreferences(spClass, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = share.edit(); //编辑文件
        edit.putString(spName, spValue);
        edit.commit();  //保存数据信息
    }

    /**
     * 将返回的jsonobject保存到sharepreference
     * @param context
     * @param spClass
     * @param spName
     * @param jsonObject
     */
    public static void saveSPByJsonObject(Context context, String spClass, String spName,JSONObject jsonObject)
    {
        Log.d("spclass",spClass);
        Log.d("spName",spName);
        String spValue = jsonObject.toString();
        SharedPreferences share = context.getSharedPreferences(spClass, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = share.edit(); //编辑文件
        edit.putString(spName, spValue);
        edit.commit();  //保存数据信息
    }

    /**
     * 返回的是json字符串
     * @param context
     * @param spClass
     * @param spName
     * @return
     */
    public static JSONObject getSPInJsonObject(Context context, String spClass, String spName)
    {
        Log.d("getspclass",spClass);
        Log.d("getspName",spName);
        SharedPreferences sharedPreferences = context.getSharedPreferences(spClass, Context.MODE_PRIVATE);
        String result = sharedPreferences.getString(spName, "");
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;

    }

    /**
     * 获得屏幕的宽度
     * @param activity
     * @return
     */
    public static int getscreenWidth(Activity activity) {
        DisplayMetrics metric = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;
//        int height = metric.heightPixels;
//        float density = metric.density;
//        int densityDpi = metric.densityDpi;
        return width;
    }

    /**
     * 获得屏幕的高度
     * @param activity
     * @return
     */
    public static int getscreenHeight(Activity activity) {
        DisplayMetrics metric = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
//        int width = metric.widthPixels;
        int height = metric.heightPixels;
//        float density = metric.density;
//        int densityDpi = metric.densityDpi;
        return height;
    }
    /**
     * 检测网络是否连接
     */
    public static boolean isNetWorkActive(Context context)
    {
        boolean flag = false;
        ConnectivityManager manager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE); //获取系统服务
        if (manager != null)
        {
            flag = manager.getActiveNetworkInfo().isAvailable();

        }
        return flag;
    }

//    public boolean isConnectingToInternet(){
//        ConnectivityManager connectivity = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        if (connectivity != null)
//        {
//            Network[] networks = connectivity.getAllNetworks();
//            if (info != null)
//                for (int i = 0; i < info.length; i++)
//                    if (info[i].getState() == NetworkInfo.State.CONNECTED)
//                    {
//                        return true;
//                    }
//
//        }
//        return false;
//    }


}
