package com.test.medicalsystem.tasklist;

import android.util.Log;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.test.medicalsystem.commonclass.CommonAbstractFragment;
import com.test.medicalsystem.medicalsystem.R;
import com.test.medicalsystem.tasklist.model.TaskListModel;
import com.test.medicalsystem.tools.Tool;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class TaskListFragment extends CommonAbstractFragment {
    private PullToRefreshListView taskPullToRefreshList;
    private JSONArray datas;


    public TaskListFragment() {
        // Required empty public constructor
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_task_list;
    }

    @Override
    public void initDatas() {
        JSONObject jsonObject;
//        datas = new ArrayList<TaskListModel>();
        datas = new JSONArray();
        TaskListModel taskListModel;
        for (int i = 0; i < 13; i ++)
        {
            jsonObject = new JSONObject();
            try {
                jsonObject.put("PatientName","王二麻子");
                jsonObject.put("PatientSex","男性");
                jsonObject.put("PatientAge","100");
                jsonObject.put("MissionId","MissionId"+i);
                jsonObject.put("Address","Address"+i);
                jsonObject.put("MPDS","MPDS"+i);
                //后来加上去的item的背景色(不从网络获取)
                jsonObject.put("BackGroundColor",Tool.taskListBgColor[i % 4]);
                Log.d("taskListBgColor", (i % 4) + "***");
//                taskListModel = new TaskListModel(jsonObject);
                datas.put(jsonObject);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        datas = Tool.changeToArray(datas);

    }

    @Override
    public void initViews() {
        setBackButtonHidden(true);
        setActionBarTitle(R.string.task_list_title);
        taskPullToRefreshList = (PullToRefreshListView)getView().findViewById(R.id.pull_to_refresh_task_listview);
        TaskListAdapter adapter = new TaskListAdapter(getContext(), datas);
        taskPullToRefreshList.setAdapter(adapter);



    }

    @Override
    public void initEvents() {

    }

}
