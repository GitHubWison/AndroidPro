package com.test.medicalsystem.commonclass;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.test.medicalsystem.medicalsystem.R;

import org.w3c.dom.Text;

/*
基类
 */
public class BaseActivity extends AppCompatActivity {
    private TextView leftTextView;
    private TextView middleTextView;
    private TextView rightTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        initDatas();
        initViews();
        initEvents();

    }

    /**
     * 设置顶部的标题
     * @param middleTitle
     */
    public void setMiddleTitle(String middleTitle)
    {
        middleTextView.setText(middleTitle);

    }

    public void initDatas()
    {

    }
    public void initViews()
    {

        leftTextView = (TextView)findViewById(R.id.left_textview);
        middleTextView = (TextView)findViewById(R.id.middle_textview);
        rightTextView = (TextView)findViewById(R.id.right_textview);
    }
    public void initEvents()
    {
        leftTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                返回到上一个fragment
                if (getSupportFragmentManager().popBackStackImmediate())
                {
                    getSupportFragmentManager().popBackStack();
                }else
                {
                    finish();
                }

            }
        });
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
        fragmentTransaction.add(R.id.fragment_common_layout,fragment,cls.getName()).commit();
    }



}
