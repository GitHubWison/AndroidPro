package com.test.medicalsystem.commonclass;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.test.medicalsystem.medicalsystem.R;

import org.w3c.dom.Text;

/*
基类
 */
public class BaseActivity extends AppCompatActivity {
    private ActionBar bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        initDatas();
        initViews();
        initEvents();

    }


    public void initDatas()
    {

    }
    public void initViews()
    {
        bar = getSupportActionBar();
        if (bar!=null)
        {

            bar.setDisplayHomeAsUpEnabled(true);
            bar.setHomeButtonEnabled(true);

            Log.d("bar", "ActionBar非空");
        }
        else
        {
            Log.d("bar","ActionBar空");
        }
    }
    public void initEvents()
    {

    }

    /**
     *
     * @param fragment
     * @param isInit 如果是初始化则不需要用动画加载(可能有问题)
     */
    private void jumpToFragment(Fragment fragment, boolean isInit, Class cls)
    {
        if (fragment == null)
        {
            fragment = new Fragment();
        }
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (isInit)
        {
            fragmentTransaction.add(R.id.login_frame_layout,fragment,cls.getName()).commit();
        }else
        {
            fragmentTransaction.setCustomAnimations(R.anim.in_from_right,
                    R.anim.out_to_left, R.anim.in_from_left,
                    R.anim.out_to_right).replace(R.id.login_frame_layout, fragment, cls.getName()).addToBackStack(null).commit();
        }

    }

    /**
     * 初始化fragment
     * @param fragment
     * @param cls
     */
    public void initFragment(Fragment fragment , Class cls)
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_common_layout, fragment, cls.getName()).commit();
    }





    /**
     * 隐藏右侧的setting按钮
     * @param isHidden
     */
    public void setSettingIconHidden(Boolean isHidden)
    {

    }
    /**
     * 不显示actionbar
     */
    public void hiddenActionBar()
    {
        getSupportActionBar().hide();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    //封装这个方法，（传入一个jsonarray同时设置好item）
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_settings:
                Toast.makeText(this, "111111", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_settings2:
                Toast.makeText(this, "222222", Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
//                触发返回事件
                if (getSupportFragmentManager().popBackStackImmediate())
                {
                    getSupportFragmentManager().popBackStack();
                }else
                {
                    finish();
                }
                break;

            default:
                break;
        }
        return true;
    }
}
