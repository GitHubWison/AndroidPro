package com.test.medicalsystem.tasklist;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test.medicalsystem.medicalsystem.Model;
import com.test.medicalsystem.medicalsystem.R;
import com.test.medicalsystem.tasklist.model.TaskListModel;
import com.test.medicalsystem.tools.Tool;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Nangy-Office on 2016/3/30.
 */
public class TaskListAdapter extends BaseAdapter {
    private Context context;
//    private ArrayList<TaskListModel> arrayList;
    private JSONArray taskListArray;
    private ViewHolder viewHolder;
    private JSONObject divModel;

    public TaskListAdapter(Context context, JSONArray taskListArray) {
        this.context = context;
        this.taskListArray = taskListArray;
//        this.divModel = Tool.getDivAndModel(arrayList.size(), 2);
    }

    @Override
    public int getCount() {
        return taskListArray.length();
    }

    @Override
    public Object getItem(int position) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = taskListArray.getJSONObject(position);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.task_list_item, null);
            viewHolder = new ViewHolder();
            viewHolder.patient_name = (TextView) convertView
                    .findViewById(R.id.patient_name);
            viewHolder.patient_gender = (TextView) convertView
                    .findViewById(R.id.patient_gender);
            viewHolder.patient_age = (TextView) convertView
                    .findViewById(R.id.patient_age);
            viewHolder.patient_no = (TextView) convertView
                    .findViewById(R.id.patient_no);
            viewHolder.patient_address = (TextView) convertView
                    .findViewById(R.id.patient_address);
            viewHolder.patient_MPDS = (TextView) convertView
                    .findViewById(R.id.patient_MPDS);



            viewHolder.patient_name2 = (TextView) convertView
                    .findViewById(R.id.patient_name2);
            viewHolder.patient_gender2 = (TextView) convertView
                    .findViewById(R.id.patient_gender2);
            viewHolder.patient_age2 = (TextView) convertView
                    .findViewById(R.id.patient_age2);
            viewHolder.patient_no2 = (TextView) convertView
                    .findViewById(R.id.patient_no2);
            viewHolder.patient_address2 = (TextView) convertView
                    .findViewById(R.id.patient_address2);
            viewHolder.patient_MPDS2 = (TextView) convertView
                    .findViewById(R.id.patient_MPDS2);


            viewHolder.left_linear_layout = (LinearLayout)convertView.findViewById(R.id.left_linear_layout);
            viewHolder.right_linear_layout = (LinearLayout)convertView.findViewById(R.id.right_linear_layout);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        JSONObject taskJSONObject = new JSONObject();
        try {
            taskJSONObject = taskListArray.getJSONObject(position);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        TaskListModel taskListModel1 = null;
        TaskListModel taskListModel2 = null;
        try {
            taskListModel1 = (TaskListModel)taskJSONObject.get("FIRST");
//            if (!taskJSONObject.has("SECOND")){
//                taskJSONObject.put("SECOND",new TaskListModel());
//
//            }
            taskListModel2 = (TaskListModel)taskJSONObject.get("SECOND");

//            taskListModel2 = arrayList.get(divModel.getInt("div") + 1);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        Log.d("taskListModel1bg",taskListModel1.getBackGroundColor() + "");
        Log.d("taskListModel2bg", taskListModel2.getBackGroundColor() + "");
//        viewHolder.left_linear_layout.setBackgroundColor(convertView.getResources().getColor(taskListModel1.getBackGroundColor()));
//        ContextCompat.getColor(context, R.color.my_color)
        viewHolder.left_linear_layout.setBackgroundColor(ContextCompat.getColor(context, taskListModel1.getBackGroundColor()));

            viewHolder.patient_name.setText(taskListModel1.getPatientName());
            viewHolder.patient_gender.setText(taskListModel1.getPatientGender());
            viewHolder.patient_age.setText(taskListModel1.getPatientAge());
            viewHolder.patient_no.setText(taskListModel1.getPatientNo());
            viewHolder.patient_address.setText(taskListModel1.getPatientAddress());
            viewHolder.patient_MPDS.setText(taskListModel1.getPatientMPDS());


//            viewHolder.right_linear_layout.setBackgroundColor(convertView.getResources().getColor(taskListModel2.getBackGroundColor()));
        viewHolder.right_linear_layout.setBackgroundColor(ContextCompat.getColor(context,taskListModel2.getBackGroundColor()));
            viewHolder.patient_name2.setText(taskListModel2.getPatientName());
            viewHolder.patient_gender2.setText(taskListModel2.getPatientGender());
            viewHolder.patient_age2.setText(taskListModel2.getPatientAge());
            viewHolder.patient_no2.setText(taskListModel2.getPatientNo());
            viewHolder.patient_address2.setText(taskListModel2.getPatientAddress());
            viewHolder.patient_MPDS2.setText(taskListModel2.getPatientMPDS());





        return convertView;
    }
    private class ViewHolder {
        TextView patient_name;// 病患姓名
        TextView patient_gender;//病患性别
        TextView patient_age;//病患年龄
        TextView patient_no;//病患编号
        TextView patient_address;//病患住址
        TextView patient_MPDS;//病患MPDS

        TextView patient_name2;// 病患姓名
        TextView patient_gender2;//病患性别
        TextView patient_age2;//病患年龄
        TextView patient_no2;//病患编号
        TextView patient_address2;//病患住址
        TextView patient_MPDS2;//病患MPDS

        LinearLayout left_linear_layout;
        LinearLayout right_linear_layout;


    }
}
