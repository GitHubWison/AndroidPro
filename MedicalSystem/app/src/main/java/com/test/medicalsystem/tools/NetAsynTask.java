package com.test.medicalsystem.tools;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;

/**
 * Created by xuqiwei-Office on 2016/3/29.
 * 检测网络连接是否通畅
 */
public class NetAsynTask extends AsyncTask<Object,Object, String>
{
    private String url;
    private CheckNetWorkListener listener;

    public CheckNetWorkListener getListener() {

        return listener;
    }

    public void setListener( CheckNetWorkListener listener) {
        this.listener = listener;

    }

    public NetAsynTask(String url,CheckNetWorkListener listener) {
        this.listener = listener;
        this.url = url;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s.equals("success"))
        {
            listener.netOn();
            Log.d("success==", "success");
        }else
        {
            listener.netOff();
            Log.d("fail==","fail");
        }

    }

    @Override
    protected String doInBackground(Object... params) {
//        Log.d("params00", params[0].toString());
        if (checkURL(url))
        {
            return "success";
        }
        return "failure";
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
    public static boolean checkURL(String url){
        boolean value=false;
        try {
            HttpURLConnection conn=(HttpURLConnection)new URL(url).openConnection();
            conn.setConnectTimeout(5000);
            int code=conn.getResponseCode();
            System.out.println(">>>>>>>>>>>>>>>> "+code+" <<<<<<<<<<<<<<<<<<");
            if(code!=200){
                value=false;
            }else{
                value=true;
            }
        } catch (MalformedURLException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
// TODO Auto-generated catch block
//            e.printStackTrace();
            Log.d("SocketTimeoutException","SocketTimeoutException");
        }
        return value;
    }
}
