package com.test.medicalsystem.medicalsystem;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NextFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NextFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NextFragment extends Fragment {
//    列表页面
    private PullToRefreshListView pull_to_refresh_listview;
    private ArrayList<Model> datas;
    public NextFragment() {
        // Required empty public constructor
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initViews();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_next, container, false);
    }

    private void initViews(){
        pull_to_refresh_listview = (PullToRefreshListView)getView().findViewById(R.id.pull_to_refresh_listview);
        NextListAdapter adapter = new NextListAdapter(getActivity(),datas);
        pull_to_refresh_listview.setAdapter(adapter);
    }
    private void initData(){
        datas = new ArrayList<Model>();
        Model model;
        for (int i = 0;i < 10; i ++)
        {
            model = new Model("标题" + i, "简介" + i);
            datas.add(model);
        }
    }


}
