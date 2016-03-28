package com.test.medicalsystem.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.system.ErrnoException;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.test.medicalsystem.httpmanager.DoMainModel;
import com.test.medicalsystem.httpmanager.HttpRequest;
import com.test.medicalsystem.httpmanager.HttpRequsetModel;
import com.test.medicalsystem.httpmanager.MethodModel;
import com.test.medicalsystem.medicalsystem.R;
import com.test.medicalsystem.tools.SPreference;
import com.test.medicalsystem.tools.Tool;
import com.test.medicalsystem.ui.ChooseDialog;

import org.json.JSONObject;

import java.lang.reflect.Method;
import java.net.ConnectException;
import java.security.DomainCombiner;

import cz.msebera.android.httpclient.Header;


public class ConnectServerFragment extends Fragment implements View.OnClickListener{
    private Button testButton;
    private Button saveSettingButton;
    private EditText domainEditText;

    public ConnectServerFragment() {
        // Required empty public constructor

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_connect_server, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        initDatas();
//        initViews();
//        initEvents();

    }
    private void initDatas()
    {

    }
    private void initViews()
    {
        ((LoginActivity)getActivity()).showBackButton();
        testButton = (Button)getView().findViewById(R.id.test_button);
        saveSettingButton = (Button)getView().findViewById(R.id.save_setting_button);
        domainEditText = (EditText)getView().findViewById(R.id.domain_edittext);

    }
    private void initEvents()
    {
        testButton.setOnClickListener(this);
        saveSettingButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.test_button:
                if (isInputDomainNotNull())
                {
//                    try {
                        AsyncHttpClient client = new AsyncHttpClient();
                        client.setTimeout(5000);
                        client.setBasicAuth("Mdsd.Phep.Api", "mdsd.phep.api.2005$");
                        client.post(domainEditText.getText().toString() + MethodModel.testConnection, new JsonHttpResponseHandler(){
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                super.onSuccess(statusCode, headers, response);
                                //                            弹出是否要保存到sp的选择框
                                new ChooseDialog(getContext(), getResources().getString(R.string.alert_issave_internet_setting), true, new ChooseDialog.OnSureListener() {
                                    @Override
                                    public void doAfterSure() {
//                                    保存到sp
                                        Tool.saveSp(getContext(), SPreference.Login.sp_name, SPreference.Login.domain, domainEditText.getText().toString());
                                        Toast.makeText(getContext(), getResources().getString(R.string.save_success), Toast.LENGTH_SHORT).show();

                                    }

                                    @Override
                                    public void doAfterCancle() {

                                    }
                                }).show();
                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                                super.onFailure(statusCode, headers, throwable, errorResponse);
                                Toast.makeText(getContext(), getResources().getString(R.string.alert_wrong_internet), Toast.LENGTH_SHORT).show();
                            }
                        });

//                    }
//                    catch (Exception e )
//                    {
//                        Log.d("error_msg","出错啦");
//                    }




                }

                break;
            case R.id.save_setting_button:
                if (isInputDomainNotNull())
                {
//                    只保存正确的服务器地址
                    Tool.saveSp(getContext(), SPreference.Login.sp_name, SPreference.Login.domain, domainEditText.getText().toString());
                }
                break;
            default:
                break;
        }
    }
//    校验是否输入了domain
    private boolean isInputDomainNotNull()
    {
        if (domainEditText.getText().toString().equals("")){
            Toast.makeText(getActivity(), getResources().getString(R.string.server_domain),Toast.LENGTH_LONG).show();
            return false;
        }
        return true;

    }
}
