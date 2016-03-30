package com.test.medicalsystem.login;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.test.medicalsystem.commonclass.BaseActivity;
import com.test.medicalsystem.medicalsystem.R;

public class LoginActivityV2 extends BaseActivity {
    private LoginFragmentV2 loginFragmentV2;
    private ConnectServerFragmentV2 connectServerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setMiddleTitle(getResources().getString(R.string.login));

        if (loginFragmentV2 == null)
        {
            loginFragmentV2 = new LoginFragmentV2();
        }
        initFragment(loginFragmentV2, LoginFragmentV2.class);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
                if (!isShowActionSetting()){
            MenuItem actionSettings = menu.findItem(R.id.action_setting_serveradd);
            actionSettings.setVisible(false);
        }
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId())
        {
            case R.id.action_setting_serveradd:
//                Toast.makeText(this, "action_setting_serveradd", Toast.LENGTH_SHORT).show();
//                跳转到ConnectServerFragmentV2
                if (connectServerFragment == null)
                {
                    connectServerFragment = new ConnectServerFragmentV2();
                }
//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.setCustomAnimations(R.anim.in_from_right,
//                        R.anim.out_to_left, R.anim.in_from_left,
//                        R.anim.out_to_right).replace(R.id.fragment_common_layout, connectServerFragment, ConnectServerFragmentV2.class.getName()).addToBackStack(null).commit();
                jumpToFragment(connectServerFragment, ConnectServerFragment.class);
                break;

            default:
                break;
        }
        return true;

    }
}
