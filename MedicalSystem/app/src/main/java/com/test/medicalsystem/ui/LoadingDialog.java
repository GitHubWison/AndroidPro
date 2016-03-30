package com.test.medicalsystem.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.test.medicalsystem.medicalsystem.R;
import com.test.medicalsystem.tools.Tool;

/**
 * Created by xuqiwei-Office on 2016/3/30.
 * 网络请求开始时显示的loading页面
 */
public class LoadingDialog extends Dialog {
    private Context context;
    /*
    提示语句
     */
    private String tips;
    private ProgressBar progressBar;
    private LinearLayout pb_linearlayout;
    private static LoadingDialog instanceLoadingBar;

    /**
     * 方便以后扩展
     * @param context
     * @param tips
     */
    public LoadingDialog(Context context, String tips) {
        super(context);
        this.context = context;
        this.tips = tips;
    }
    public LoadingDialog(Context context)
    {
        super(context);
        this.context = context;
        this.tips = "";
    }
    public synchronized  static LoadingDialog getInstance(Context context)
    {
        if (instanceLoadingBar == null)
        {
            instanceLoadingBar = new LoadingDialog(context);
        }
        return instanceLoadingBar;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress);
        initDatas();
        initViews();
        initEvents();
    }

    private void initEvents() {

    }

    private void initViews() {
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        pb_linearlayout = (LinearLayout)findViewById(R.id.pb_linearlayout);
        int width = Tool.getscreenWidth((Activity) context) < Tool.getscreenHeight((Activity) context) ? Tool.getscreenWidth((Activity) context) : Tool.getscreenHeight((Activity) context);
        pb_linearlayout.setLayoutParams(new RelativeLayout.LayoutParams(width*1/8, width*1/8));
        this.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失


    }

    private void initDatas() {
        
    }
}
