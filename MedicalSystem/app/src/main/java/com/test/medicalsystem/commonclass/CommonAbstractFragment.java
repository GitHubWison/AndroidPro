package com.test.medicalsystem.commonclass;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

import com.test.medicalsystem.medicalsystem.R;

import java.lang.reflect.Field;

/**
 * Created by xuqiwei-Office on 2016/3/28.
 */
public abstract class CommonAbstractFragment extends Fragment {
    /**
     * 是否显示右侧的。。。
     */
    private boolean shouldShowSettingIcon;

    public CommonAbstractFragment() {
        shouldShowSettingIcon = false;
    }

    public void setShouldShowSettingIcon(boolean shouldShowSettingIcon) {
        this.shouldShowSettingIcon = shouldShowSettingIcon;
    }

    public boolean isShouldShowSettingIcon() {
        return shouldShowSettingIcon;
    }

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
    /**
     * 设置标题
     * @param title 标题内容
     */
    public void setActionBarTitle(int titleId)
    {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(titleId);
    }
    /**
     * 隐藏左侧的返回按钮
     * @param isHidden
     */
    public void setBackButtonHidden(Boolean isHidden)
    {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(!isHidden);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((BaseActivity)getActivity()).setShowActionSetting(isShouldShowSettingIcon());
        getActivity().invalidateOptionsMenu();

    }
}
