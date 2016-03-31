package com.test.medicalsystem.tools;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.system.ErrnoException;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.test.medicalsystem.commonclass.BaseActivity;
import com.test.medicalsystem.httpmanager.HttpRequest;
import com.test.medicalsystem.httpmanager.HttpRequsetModel;
import com.test.medicalsystem.httpmanager.MethodModel;
import com.test.medicalsystem.medicalsystem.R;
import com.test.medicalsystem.tasklist.model.TaskListModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
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

    public static int[] taskListBgColor = {R.color.task_bg1, R.color.task_bg2,R.color.task_bg3,R.color.task_bg4};

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

            return strBuf.toString().toUpperCase();
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



    public static void checkNetWorkStatues(final Context context, final CheckNetWorkListener checkNetWorkListener)
    {
        String domain = Tool.getSp(context, SPreference.Login.sp_name, SPreference.Login.domain);
        Log.d("domain==", domain);
        if (!domain.equals("")){
            //            已经将domain记录在了sp中
            String url = MethodModel.spellURL(domain, MethodModel.testConnection);
            new com.test.medicalsystem.tools.NetAsynTask(url, new com.test.medicalsystem.tools.NetAsynTask.CheckNetWorkListener() {
                @Override
                public void netOn() {
                    checkNetWorkListener.netOn();
                }

                @Override
                public void netOff() {
//
                    checkNetWorkListener.netOff();
                    Toast.makeText(context, "网络不通，请检查服务器设置和网络连接", Toast.LENGTH_SHORT).show();
                }
            }).execute();


        }
        else {
            Log.d("error==","没有记录domain");
        }
    }

    public static void checkNetWorkStatues(final Context context, final CheckNetWorkListener checkNetWorkListener, String url)
    {
        String domain = Tool.getSp(context, SPreference.Login.sp_name, SPreference.Login.domain);
        Log.d("domain==", domain);

            //            已经将domain记录在了sp中
//            String url = MethodModel.spellURL(domain, MethodModel.testConnection);
            new com.test.medicalsystem.tools.NetAsynTask(url, new com.test.medicalsystem.tools.NetAsynTask.CheckNetWorkListener() {
                @Override
                public void netOn() {
//                    Toast.makeText(context, "通", Toast.LENGTH_SHORT).show();
                    checkNetWorkListener.netOn();
                }

                @Override
                public void netOff() {
//
                    checkNetWorkListener.netOff();
                    Toast.makeText(context, "网络不通，请检查服务器设置和网络连接", Toast.LENGTH_SHORT).show();
                }
            }).execute();



    }

//    public static boolean checkURL(String url){
//        boolean value=false;
//        try {
//            HttpURLConnection conn=(HttpURLConnection)new URL(url).openConnection();
//            int code=conn.getResponseCode();
//            System.out.println(">>>>>>>>>>>>>>>> "+code+" <<<<<<<<<<<<<<<<<<");
//            if(code!=200){
//                value=false;
//            }else{
//                value=true;
//            }
//        } catch (MalformedURLException e) {
//// TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//// TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return value;
//    }


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
//    public static boolean isNetWorkActive(Context context)
//    {
//        boolean flag = false;
//        ConnectivityManager manager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE); //获取系统服务
//        if (manager != null)
//        {
//            flag = manager.getActiveNetworkInfo().isAvailable();
//
//        }
//        return flag;
//    }

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

//    public static void testNet(String url)
//    {
//            new NetAsynTask().execute(url);
//
//    }
    public static void hideKeyBoard(BaseActivity activity)
    {
//        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);

        boolean isOpen=imm.isActive();//isOpen若返回true，则表示输入法打开
        if (isOpen){
            try{
                ((InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }catch (NullPointerException nullE)
            {

            }


        }
    }
    public static JSONObject getDivAndModel(int x, int y)
    {
        int div = x/y;
        int model = x%y;
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("div", div);
            jsonObject.put("model", model);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * 将从网络上获取的任务列表转换成两两配对的array
     * @param originalArray
     * @return
     */
    public static JSONArray changeToArray(JSONArray originalArray)
    {
        JSONArray array = new JSONArray();
        JSONObject jsonObject;
        for(int i = 0; i < (originalArray.length()/2 + (originalArray.length()%2)); i++)
        {
            jsonObject = new JSONObject();
            try {
                jsonObject.put("FIRST",new TaskListModel((JSONObject)originalArray.get(2 * i)));
                if ((2 * i + 1) < originalArray.length()){
                    jsonObject.put("SECOND", new TaskListModel((JSONObject)originalArray.get(2 * i + 1)));
                }
                else
                {
                    Log.d("else","else");
                    jsonObject.put("SECOND", new TaskListModel());
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            array.put(jsonObject);
        }
        Log.d("array",array.toString());
        return array;
    }

}
