package com.test.medicalsystem.login;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.test.medicalsystem.commonclass.CommonAbstractFragment;
import com.test.medicalsystem.httpmanager.MethodModel;
import com.test.medicalsystem.medicalsystem.R;
import com.test.medicalsystem.tools.SPreference;
import com.test.medicalsystem.tools.Tool;
import com.test.medicalsystem.ui.ChooseDialog;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ConnectServerFragmentV2 extends CommonAbstractFragment implements View.OnClickListener{
    private Button testButton;
    private Button saveSettingButton;
    private EditText domainEditText;

    public ConnectServerFragmentV2() {
        // Required empty public constructor
    }


    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_connect_server;
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void initViews() {
        testButton = (Button)getView().findViewById(R.id.test_button);
        saveSettingButton = (Button)getView().findViewById(R.id.save_setting_button);
        domainEditText = (EditText)getView().findViewById(R.id.domain_edittext);
    }

    @Override
    public void initEvents() {
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
//                                    Tool.saveSp(getContext(), SPreference.Login.class.getName(), SPreference.LOGIN.domain.toString(), domainEditText.getText().toString());
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
