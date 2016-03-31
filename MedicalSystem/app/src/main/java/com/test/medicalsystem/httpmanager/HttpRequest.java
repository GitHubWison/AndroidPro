package com.test.medicalsystem.httpmanager;

import android.app.ProgressDialog;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.test.medicalsystem.medicalsystem.R;
import com.test.medicalsystem.tools.SPreference;
import com.test.medicalsystem.tools.Tool;
import com.test.medicalsystem.ui.LoadingDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.conn.ConnectTimeoutException;
import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Created by xuqiwei-Office on 2016/3/24.
 * http请求
 */
public class HttpRequest {
    private HttpRequsetModel httpRequsetModel;
    private GetResponseListener getResponseListener;
    /*
    以后需要根据情况显示ProgressDialog，先不做处理
     */
    private ProgressDialog progressDialog;
    public HttpRequest(HttpRequsetModel httpRequsetModel) {
        this.httpRequsetModel = httpRequsetModel;
    }

    public void setHttpRequsetModel(HttpRequsetModel httpRequsetModel) {
        this.httpRequsetModel = httpRequsetModel;
    }

    public void setGetResponseListener(GetResponseListener getResponseListener) {
        this.getResponseListener = getResponseListener;
    }

    public void sendRequest(final GetResponseListener listener)
    {
//        检查是否联网
//        if (Tool.isNetWorkActive(httpRequsetModel.getContext()))
//        {
        final AsyncHttpClient client = new AsyncHttpClient();
//        开始loading动画
        LoadingDialog.getInstance(httpRequsetModel.getContext(), client).show();
            //        检查网路是否通畅
            Tool.checkNetWorkStatues(httpRequsetModel.getContext(), new Tool.CheckNetWorkListener() {
                @Override
                public void netOn() {

                    client.setBasicAuth("Mdsd.Phep.Api", "mdsd.phep.api.2005$");
                    client.setTimeout(8000);
                    Log.d("url连接",httpRequsetModel.getUrl()+httpRequsetModel.getJsonObject());
                    if (httpRequsetModel.isGet()) {
//            是get请求
            /*
            client.get(httpRequsetModel.getContext(), httpRequsetModel.getUrl(), httpRequsetModel.getHeaders(), httpRequsetModel.getParams(), new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    listener.onSuccess( statusCode,  headers,  new String(responseBody));
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    listener.onFailure( statusCode,  headers, new String(responseBody) ,error);
                }
            });
            */
                    } else {
//            是post请求
                        JSONObject jsObject = httpRequsetModel.getJsonObject();
                        try {
                            StringEntity ss = new StringEntity(jsObject.toString());
                            client.post(httpRequsetModel.getContext(), httpRequsetModel.getUrl(), ss, "application/json", new JsonHttpResponseHandler() {
                                @Override
                                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                    super.onSuccess(statusCode, headers, response);
                                    Log.d("响应成功", response.toString());
                                    LoadingDialog.getInstance(httpRequsetModel.getContext(), client).dismiss();
//                                    判断响应结果
                                    try {
                                        if (response.getBoolean("IsSuccess"))
                                        {
//                                            用户操作正确
                                            listener.onSuccess(statusCode, headers, response);
                                        }else
                                        {
//                                            用户输入的信息无效
                                            Toast.makeText(httpRequsetModel.getContext(), response.getString("Message"), Toast.LENGTH_SHORT).show();
                                        }

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }


                                @Override
                                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                                    super.onFailure(statusCode, headers, throwable, errorResponse);
                                    Log.d("failure", "网络连接出错");
                                    LoadingDialog.getInstance(httpRequsetModel.getContext(), client).dismiss();
                                    listener.onFailure(statusCode, headers, errorResponse);
                                    Toast.makeText(httpRequsetModel.getContext(), httpRequsetModel.getContext().getResources().getString(R.string.normal_wrong) , Toast.LENGTH_SHORT).show();

                                }

                            });
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                            LoadingDialog.getInstance(httpRequsetModel.getContext(), client).dismiss();
                        }

                    }
                }

                @Override
                public void netOff() {
//                    Toast.makeText(httpRequsetModel.getContext(), "网路连接断开", Toast.LENGTH_SHORT).show();
                    LoadingDialog.getInstance(httpRequsetModel.getContext(), client).dismiss();
                }
            }, Tool.getSp(httpRequsetModel.getContext(), SPreference.Login.sp_name, SPreference.Login.domain));
//        }else
//        {
//            Toast.makeText(httpRequsetModel.getContext(), "没有联网", Toast.LENGTH_SHORT).show();
//        }

    }


    public interface GetResponseListener{
        public void onSuccess(int statusCode, Header[] headers, JSONObject responseString);
        public void onFailure(int statusCode, Header[] headers, JSONObject responseString);
}

}
