package com.test.medicalsystem.medicalsystem;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.test.medicalsystem.commonclass.CommonAbstractFragment;

import org.greenrobot.eventbus.EventBus;


public class FragmentTwo extends CommonAbstractFragment {
private Button button2;

    public FragmentTwo() {
        // Required empty public constructor
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_fragment_two;
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void initViews() {
        button2 = (Button)getView().findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                发送消息
                EventBus.getDefault().post(new FirstEventTest("FirstEvent btn clicked"));
            }
        });
    }

    @Override
    public void initEvents() {

    }


}
