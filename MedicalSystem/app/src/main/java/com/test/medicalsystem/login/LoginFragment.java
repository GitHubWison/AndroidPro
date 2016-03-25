package com.test.medicalsystem.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.usb.UsbRequest;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.test.medicalsystem.httpmanager.DoMainModel;
import com.test.medicalsystem.httpmanager.HttpRequest;
import com.test.medicalsystem.httpmanager.HttpRequsetModel;
import com.test.medicalsystem.httpmanager.MethodModel;
import com.test.medicalsystem.login.model.UserModel;
import com.test.medicalsystem.medicalsystem.R;
import com.test.medicalsystem.staticstring.AccessFunc;
import com.test.medicalsystem.tools.SPreference;
import com.test.medicalsystem.tools.Tool;
import com.test.medicalsystem.ui.ChooseDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HeaderElement;
import cz.msebera.android.httpclient.ParseException;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.message.BasicHeader;


public class LoginFragment extends Fragment implements View.OnClickListener{
    private EditText userNameEditText ;
    private EditText passWordEditText;
    private Button loginButton;
    private String domain;
    private ConnectServerFragment connectServerFragment;
    /*
    用户信息
     */
    private UserModel userMsg;
    public LoginFragment() {
        // Required empty public constructor

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initView();
        initEvent();
    }
    private void initData()
    {

    }
    private void initView()
    {
        ((LoginActivity)getActivity()).hideBackButton();
        userNameEditText = (EditText)getView().findViewById(R.id.userName_editText);
        passWordEditText = (EditText)getView().findViewById(R.id.passWord_editText);
        loginButton = (Button)getView().findViewById(R.id.login_button);
//测试开始
        Button testButton = (Button)getView().findViewById(R.id.test_button);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Tool.isNetWorkActive(getContext()))
                {
                    Toast.makeText(getContext(), "并没哟有联网", Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(getContext(), "联网", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //测试结束
    }
    private void initEvent()
    {
        loginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.login_button:
//                登录操作
                if (isUserNameOrPasswordNotNullAndHasServerAddress())
                {
//                    执行登录操作
                    String userName = userNameEditText.getText().toString();
                    String passWord = (Tool.getMD5(passWordEditText.getText().toString())).toUpperCase();
                    try {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("LoginName", userName);
                        jsonObject.put("Password", passWord);
                        jsonObject.put("AppId", "PreHospitalCare");
                        HttpRequsetModel httpRequsetModel = new HttpRequsetModel(jsonObject, MethodModel.spellURL(domain, MethodModel.login), null, getActivity(), false);
                        HttpRequest request = new HttpRequest(httpRequsetModel);
                        request.sendRequest(new HttpRequest.GetResponseListener() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                try {
                                    userMsg = new UserModel(response.getJSONObject("Value"));
                                    if (!userMsg.isUserHasAccess(AccessFunc.PHEP_80))
                                    {
                                        Toast.makeText(getContext(), getResources().getString(R.string.user_has_no_access), Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
//                                        用户拥有权限
                                        Toast.makeText(getContext(), "用户拥有权限", Toast.LENGTH_SHORT).show();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, JSONObject response) {

                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                break;
            default:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("onResume======");
    }
//    校验用户名和密码是否为空,以及检查是否设置了服务器地址
    private boolean isUserNameOrPasswordNotNullAndHasServerAddress()
    {
//        获得服务器地址
//        SharedPreferences sharedPreferences = getContext().getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
//        String domain = sharedPreferences.getString("domain", "");
        domain = Tool.getSp(getContext(), SPreference.Login.class.getName(), SPreference.Login.domain);

        if (userNameEditText.getText().toString().equals("")||passWordEditText.getText().toString().equals(""))
        {
            Toast.makeText(getActivity(),getResources().getString(R.string.alert_login_null),Toast.LENGTH_SHORT).show();
            return false;
        }
        if (domain.equals(""))
        {
//            Toast.makeText(getActivity(), getResources().getString(R.string.alert_server_address_null), Toast.LENGTH_SHORT).show();
//            弹出是否设置地址的选择框
            new ChooseDialog(getContext(), "还没有设置服务器地址，是否去设置？", true, new ChooseDialog.OnSureListener() {
                @Override
                public void doAfterSure() {
//                    跳转到设置服务器地址的页面
                    if (connectServerFragment == null)
                    {
                        connectServerFragment = new ConnectServerFragment();
                    }
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.anim.in_from_right,
                            R.anim.out_to_left, R.anim.in_from_left,
                            R.anim.out_to_right).replace(R.id.login_frame_layout, connectServerFragment, "ConnectServerFragment").addToBackStack(null).commit();
                }

                @Override
                public void doAfterCancle() {

                }
            }).show();
            return false;
        }
        return true;
    }
}
