package com.test.medicalsystem.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.test.medicalsystem.commonclass.BaseActivity;
import com.test.medicalsystem.medicalsystem.R;

public class LoginActivityV2 extends BaseActivity {
    private LoginFragmentV2 loginFragmentV2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setMiddleTitle(getResources().getString(R.string.login));
        if (loginFragmentV2 == null)
        {
            loginFragmentV2 = new LoginFragmentV2();
        }
        initFragment(loginFragmentV2, LoginFragmentV2.class);
    }

}
