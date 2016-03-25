package com.test.medicalsystem.login;


import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.test.medicalsystem.medicalsystem.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private LoginFragment loginFragment;
    private TextView connectionServerButton;
    private ConnectServerFragment connectServerFragment;
    private TextView leftTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (loginFragment == null)
        {
            loginFragment = new LoginFragment();
        }
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.login_frame_layout,loginFragment,"LoginFragment").commit();
        initDatas();
        initViews();
        initEvents();

    }
    private void initDatas()
    {
        connectionServerButton = (TextView)findViewById(R.id.connect_server_button);
    }
    private void initViews()
    {
        leftTextView = (TextView)findViewById(R.id.left_textview);
    }
    private void initEvents()
    {
        connectionServerButton.setOnClickListener(this);
        leftTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (v.getId())
        {
            case R.id.connect_server_button:

                if (connectServerFragment == null)
                {
                    connectServerFragment = new ConnectServerFragment();
                }
                fragmentTransaction.setCustomAnimations(R.anim.in_from_right,
                        R.anim.out_to_left, R.anim.in_from_left,
                        R.anim.out_to_right).replace(R.id.login_frame_layout, connectServerFragment, "ConnectServerFragment").addToBackStack(null).commit();
                break;
            case R.id.left_textview:
                System.out.println("left_textviewclicked");
                getSupportFragmentManager().popBackStack();
                break;
            default:
                break;
        }
    }
//    隐藏返回按钮
    public void hideBackButton()
    {
        leftTextView.setVisibility(View.INVISIBLE);
    }
//    显示返回按钮
    public void showBackButton()
    {
        leftTextView.setVisibility(View.VISIBLE);
    }

}
