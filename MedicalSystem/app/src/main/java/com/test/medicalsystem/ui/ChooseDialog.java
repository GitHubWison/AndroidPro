package com.test.medicalsystem.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test.medicalsystem.medicalsystem.R;
import com.test.medicalsystem.tools.Tool;

/**
 * Created by xuqiwei-Office on 2016/3/25.
 * 弹出提示框
 */
public class ChooseDialog extends Dialog implements View.OnClickListener{
    private Context context;
    /*
    提示语句
     */
    private String tipsContent;
    /*
    是否要显示取消按钮
     */
    private Boolean isShowCancelFlag;

    private TextView tipsTextView;
    private TextView confirmTextView;
    private TextView cancelTextView;
    private LinearLayout dialog_linearlayout;
    private OnSureListener onsurelistener;
    private ChooseDialog dataChoseDialog;

    public ChooseDialog(Context context, String tipsContent,Boolean isShowCancelFlag,
                        OnSureListener onsurelistener) {
        super(context);
        this.context = context;
        this.tipsContent = tipsContent;
        this.isShowCancelFlag = isShowCancelFlag;
        this.onsurelistener = onsurelistener;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_dialog);

        initViews();
        initDatas();
        initEvents();

    }
    private void initDatas()
    {
        tipsTextView.setText(tipsContent);
    }
    private void initViews()
    {
        dataChoseDialog = this;
        tipsTextView = (TextView)findViewById(R.id.tips_textview);

        tipsTextView.setLayoutParams(new LinearLayout.LayoutParams(Tool.getscreenWidth((Activity) context)*2/3, Tool.getscreenWidth((Activity) context)*1/3));
        dialog_linearlayout=(LinearLayout)findViewById(R.id.dialogs_linearlayout);
        dialog_linearlayout.setLayoutParams(new LinearLayout.LayoutParams(Tool.getscreenWidth((Activity) context)*2/3, Tool.getscreenWidth((Activity) context)*1/9));

        confirmTextView = (TextView)findViewById(R.id.confirm_textview);
        cancelTextView = (TextView)findViewById(R.id.cancel_textview);

        if (!isShowCancelFlag)
        {
            cancelTextView.setVisibility(View.GONE);
        }
        this.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失



    }
    private void initEvents()
    {
        confirmTextView.setOnClickListener(this);
        cancelTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.cancel_textview:
                if (this.onsurelistener != null)
                {
                    onsurelistener.doAfterCancle();
                }
                break;
            case R.id.confirm_textview:
                if (this.onsurelistener != null) {
                    onsurelistener.doAfterSure();
                }
                break;
            default:
                break;

        }
        dataChoseDialog.dismiss();
    }

    public interface OnSureListener {
        public void doAfterSure();
        public void doAfterCancle();
    }
}
