package com.test.medicalsystem.medicalsystem;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.test.medicalsystem.login.LoginActivity;

public class LaunchScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_screen);
        new Thread()
        {
            public void run()
            {
                try {
                    sleep(3000);     //等待三秒,自动进入软件主窗口
                    System.out.println("进入主程序");
                    Intent intent = new Intent(LaunchScreenActivity.this, LoginActivity.class);
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
