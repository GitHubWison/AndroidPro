package com.test.medicalsystem.tasklist;

import android.os.Bundle;

import com.test.medicalsystem.commonclass.BaseActivity;

public class TaskListActivity extends BaseActivity {
    private TaskListFragment taskListFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (taskListFragment == null)
        {
            taskListFragment = new TaskListFragment();
        }
        initFragment(taskListFragment, TaskListFragment.class);
        
    }
}
