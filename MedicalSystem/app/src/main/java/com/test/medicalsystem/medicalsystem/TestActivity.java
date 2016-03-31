package com.test.medicalsystem.medicalsystem;



import android.app.Activity;
import android.content.ClipData;
import android.support.v4.app.FragmentActivity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.test.medicalsystem.commonclass.BaseActivity;

import org.greenrobot.eventbus.EventBus;


public class TestActivity extends BaseActivity {
    private FragmentOne testFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (testFragment == null)
        {
            testFragment = new FragmentOne();
        }

        initFragment(testFragment, FragmentOne.class);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);

    }

}
