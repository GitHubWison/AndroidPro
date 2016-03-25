package com.test.medicalsystem.medicalsystem;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Nangy-Office on 2016/3/22.
 */
public class NextListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Model>arrayList;
    private ViewHolder viewHolder;
    private DetailFragment detailFragment;
    public NextListAdapter(Context context, ArrayList<Model> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.test_item_layout, null);
            viewHolder = new ViewHolder();
            viewHolder.title_textview = (TextView) convertView
                    .findViewById(R.id.title_textview);
            viewHolder.desc_textview = (TextView) convertView
                    .findViewById(R.id.desc_textview);
//            convertView.setBackgroundResource(R.drawable.list_item_bg_style);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Model model = arrayList.get(position);
        viewHolder.title_textview.setText(model.getTitle());
        viewHolder.title_textview.setText(model.getDesc());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("进入下一页" + position);
                detailFragment = new DetailFragment();
                FragmentTransaction fragmentTransaction = ((MainActivity)context).getSupportFragmentManager().beginTransaction();
                fragmentTransaction
                        .setCustomAnimations(R.anim.in_from_right,
                                R.anim.out_to_left, R.anim.in_from_left,
                                R.anim.out_to_right)
                        .replace(R.id.content_fragmentlayout,
                                detailFragment,
                                "DetailFragment")
                        .addToBackStack(null).commit();
            }
        });
        return convertView;
    }
    private class ViewHolder {
        TextView title_textview;// 项目名称
        TextView desc_textview;// 项目详情
    }
}
