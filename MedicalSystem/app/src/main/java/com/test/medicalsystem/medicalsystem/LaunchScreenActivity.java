package com.test.medicalsystem.medicalsystem;

import android.app.Activity;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.test.medicalsystem.commonclass.BaseActivity;
import com.test.medicalsystem.login.LoginActivity;
import com.test.medicalsystem.login.LoginActivityV2;

public class LaunchScreenActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_screen);
        hiddenActionBar();
        new Thread()
        {
            public void run()
            {
                try {
                    sleep(3000);     //等待三秒,自动进入软件主窗口
                    System.out.println("进入主程序");
                    Intent intent = new Intent(LaunchScreenActivity.this, LoginActivityV2.class);
                    startActivity(intent);
                    LaunchScreenActivity.this.finish();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
                //progressDialog.dismiss();
            }
        }.start();
    }


}
