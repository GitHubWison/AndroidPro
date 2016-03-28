package com.test.medicalsystem.commonclass;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.medicalsystem.medicalsystem.R;

/**
 * Created by xuqiwei-Office on 2016/3/28.
 */
public abstract class CommonAbstractFragment extends Fragment {
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initDatas();
        initViews();
        initEvents();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getFragmentLayout(), container, false);
    }
//返回fragment中的主布局
    public abstract int getFragmentLayout();
    public abstract void initDatas();
    public abstract void initViews();
    public abstract void initEvents();
    /*
    从本fragment跳转到下一个fragment
     */
    public void toNextFragment(Fragment fragment, Class cls)
    {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_from_right,
                R.anim.out_to_left, R.anim.in_from_left,
                R.anim.out_to_right).replace(R.id.fragment_common_layout, fragment, cls.getName()).addToBackStack(null).commit();

    }

}
