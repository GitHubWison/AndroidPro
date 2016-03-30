package com.test.medicalsystem.medicalsystem;



import android.app.Activity;
import android.content.ClipData;
import android.support.v4.app.FragmentActivity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ActionBar bar = getSupportActionBar();
        if (bar!=null)
        {

            bar.setDisplayHomeAsUpEnabled(true);
            bar.setHomeButtonEnabled(true);

            Log.d("bar", "非空");
        }
        else
        {
            Log.d("bar","空");
        }
//        View item = (View)findViewById(R.id.home);
//        item.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(TestActivity.this, "home", Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
//            case R.id.action_settings:
//                Toast.makeText(this, "111111", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.action_settings2:
//                Toast.makeText(this, "222222", Toast.LENGTH_SHORT).show();
//                break;
//            case android.R.id.home:
//                Log.d("home", "hhhhhhh");
//                Toast.makeText(this, "home", Toast.LENGTH_SHORT).show();
//                break;

            default:
                break;
        }
        return true;
    }
}
