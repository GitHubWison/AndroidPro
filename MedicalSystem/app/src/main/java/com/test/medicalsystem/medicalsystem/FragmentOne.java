package com.test.medicalsystem.medicalsystem;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.test.medicalsystem.commonclass.CommonAbstractFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.w3c.dom.Text;


public class FragmentOne extends CommonAbstractFragment {
private Button button;
    public TextView textView9;
    private FragmentTwo fragmentTwo;
    // TODO: Rename parameter arguments, choose names that match


    public FragmentOne() {
        // Required empty public constructor
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_fragment_one;
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void initViews() {
        //        注册eventbus
//        initFragment(testFragment, FragmentOne.class);
        button = (Button)getView().findViewById(R.id.button);
        textView9 = (TextView)getView().findViewById(R.id.textView9);
        if (fragmentTwo == null)
        {
            fragmentTwo = new FragmentTwo();
            EventBus.getDefault().register(this);

        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                toNextFragment(fragmentTwo, FragmentTwo.class);
            }
        });







    }
    @Subscribe
    public void onEventMainThread(FirstEventTest event) {

        String msg = "onEventMainThread收到了消息：" + event.getmMsg();
        Log.d("ha111rvic", msg);
        textView9.setText(msg);

        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }
    @Override
    public void initEvents() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
