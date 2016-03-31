package com.test.medicalsystem.login;

import android.content.Context;
import android.content.Intent;
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

import com.test.medicalsystem.commonclass.BaseActivity;
import com.test.medicalsystem.commonclass.CommonAbstractFragment;
import com.test.medicalsystem.httpmanager.HttpRequest;
import com.test.medicalsystem.httpmanager.HttpRequsetModel;
import com.test.medicalsystem.httpmanager.MethodModel;
import com.test.medicalsystem.login.model.UserModel;
import com.test.medicalsystem.medicalsystem.R;
import com.test.medicalsystem.medicalsystem.TestActivity;
import com.test.medicalsystem.staticstring.AccessFunc;
import com.test.medicalsystem.tasklist.TaskListActivity;
import com.test.medicalsystem.tools.SPreference;
import com.test.medicalsystem.tools.Tool;
import com.test.medicalsystem.ui.ChooseDialog;
import com.test.medicalsystem.ui.LoadingDialog;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


public class LoginFragmentV2 extends CommonAbstractFragment implements View.OnClickListener{
    private EditText userNameEditText ;
    private EditText passWordEditText;
    private Button loginButton;
    private String domain;
    private ConnectServerFragmentV2 connectServerFragment;
    /*
用户信息
 */
    private UserModel userMsg;

    public LoginFragmentV2() {
        // Required empty public constructor
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_login;
    }

    @Override
    public void initDatas() {
        setShouldShowSettingIcon(true);
    }

    @Override
    public void initViews() {
        userNameEditText = (EditText)getView().findViewById(R.id.userName_editText);
        passWordEditText = (EditText)getView().findViewById(R.id.passWord_editText);
        loginButton = (Button)getView().findViewById(R.id.login_button);
        //测试开始
        Button testButton = (Button)getView().findViewById(R.id.test_button);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                actionbar测试
//                startActivity(new Intent(getContext(), TestActivity.class));
//                LoadingDialog.getInstance(getContext(), client).show();
//               JSONObject jsonObject =  Tool.getDivAndModel(19, 2);
//                Log.d("jsonObject==",jsonObject.toString());
                startActivity(new Intent(getContext(), TaskListActivity.class));


            }
        });
        //测试结束


    }

    @Override
    public void initEvents() {
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
                    Log.d("test","ceshi");

//                    执行登录操作
                    final String userName = userNameEditText.getText().toString();
                    final String passWord = Tool.getMD5(passWordEditText.getText().toString());
                    try {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("LoginName", userName);
                        jsonObject.put("Password", passWord);
                        jsonObject.put("AppId", "PreHospitalCare");
                        final HttpRequsetModel httpRequsetModel = new HttpRequsetModel(jsonObject, MethodModel.spellURL(domain, MethodModel.login), null, getActivity(), false);
                        HttpRequest request = new HttpRequest(httpRequsetModel);

                        request.sendRequest(new HttpRequest.GetResponseListener() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                                try {
//                                    保存用户信息
                                    JSONObject nameAndPwd = new JSONObject();
                                    nameAndPwd.put("userName", userName);
                                    nameAndPwd.put("passWord", passWord);
                                    Tool.saveSPByJsonObject(httpRequsetModel.getContext(), SPreference.Login.sp_name, SPreference.Login.userNameAndPwd, nameAndPwd);
                                    userMsg = new UserModel(response.getJSONObject("Value"));
                                    if (!userMsg.isUserHasAccess(AccessFunc.PHEP_80))
                                    {
                                        Toast.makeText(getContext(), getResources().getString(R.string.user_has_no_access), Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
//                                        用户拥有权限
//                                        Toast.makeText(getContext(), "用户拥有权限", Toast.LENGTH_SHORT).show();
//                                        跳转到任务列表
                                        startActivity(new Intent(getContext(), TaskListActivity.class));
                                    }
//                                    保存用户信息
                                    Tool.saveSPByJsonObject(getContext(), SPreference.Login.sp_name, SPreference.Login.userinfo, response.getJSONObject("Value"));

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
    //    校验用户名和密码是否为空,以及检查是否设置了服务器地址
    private boolean isUserNameOrPasswordNotNullAndHasServerAddress()
    {
//        获得服务器地址
        domain = Tool.getSp(getContext(), SPreference.Login.sp_name, SPreference.Login.domain);

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
                        connectServerFragment = new ConnectServerFragmentV2();
                    }
                    toNextFragment(connectServerFragment,ConnectServerFragmentV2.class);
                    }

                @Override
                public void doAfterCancle() {

                }
            }).show();
            return false;
        }
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        setActionBarTitle(R.string.login);
        setBackButtonHidden(true);

    }
}
