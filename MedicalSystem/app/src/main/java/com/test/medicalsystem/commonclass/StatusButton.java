package com.test.medicalsystem.commonclass;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.test.medicalsystem.medicalsystem.R;

/**
 * Created by xuqiwei on 2016/3/23.
 * 任务的状态显示按钮
 */
public class StatusButton extends Button {
    private int notClickBgColor;
    private int clickedBgColor;
    private int notClickFontColor;
    private int clickFontColor;
    private StatusButton statusButton;
    private StatusButtonClickedListener statusButtonClickedListener;
    public StatusButton(Context context) {
        super(context);
    }

    public StatusButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        statusButton = this;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.StatusButton);
        notClickBgColor = a.getColor(R.styleable.StatusButton_notClickBgColor, 0xFFFFFFFF);
        clickedBgColor = a.getColor(R.styleable.StatusButton_clickedBgColor, 0xFFFFFFFF);
        notClickFontColor = a.getColor(R.styleable.StatusButton_notClickFontColor, 0xFFFFFFFF);
        clickFontColor = a.getColor(R.styleable.StatusButton_clickedFontColor, 0xFFFFFFFF);
//        设置未点击时按钮的背景色和字体的颜色
        this.setBackgroundColor(notClickBgColor);
        this.setTextColor(notClickFontColor);

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("点击自定义的按钮!!!!");
//                点击后需要变换按钮的样式
                buttonChangeStyle();
                statusButtonClickedListener.onStatusButtonClicked(statusButton);
            }
        });
//        this.setStatusButtonClickedListener(new StatusButtonClickedListener() {
//            @Override
//            public void onStatusButtonClicked() {
//
//            }
//
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }



    public StatusButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs);
    }

    public StatusButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs);
    }

    public void setStatusButtonClickedListener(StatusButtonClickedListener statusButtonClickedListener) {
        this.statusButtonClickedListener = statusButtonClickedListener;
    }

    /**
     * 点击后需要对按钮进行设置:将按钮的背景色和文字颜色设置成已经点击的样式
     */
    public void buttonChangeStyle()
    {
//        设置背景色
        this.setBackgroundColor(clickedBgColor);
//        设置字体的颜色
        this.setTextColor(clickFontColor);
//        设置按钮不可点击
        this.setClickable(false);
    }
    /**
     * 当出现网络请求错误时需要恢复按钮的样式设置
     */
    public void recoverButtonStyle()
    {
//        设置背景色
        this.setBackgroundColor(notClickBgColor);
//        设置字体的颜色
        this.setTextColor(notClickFontColor);
//        设置按钮可点击
        this.setClickable(true);

    }

    public interface StatusButtonClickedListener
    {
        public void onStatusButtonClicked(StatusButton statusButton);
    }
}
