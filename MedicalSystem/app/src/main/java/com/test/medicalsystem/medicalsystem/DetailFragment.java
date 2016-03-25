package com.test.medicalsystem.medicalsystem;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import java.io.File;

import cz.msebera.android.httpclient.Header;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment implements View.OnClickListener{
    private Button back;
    private Button internet;

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        initEvents();
    }
    public void initViews()
    {
        back = (Button)getView().findViewById(R.id.back_button);
        internet = (Button)getView().findViewById(R.id.internet);

    }
    private void initEvents()
    {
        back.setOnClickListener(this);
        internet.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.back_button:
//  返回到上一页
                System.out.println("返回上一页");
                getActivity().getSupportFragmentManager().popBackStack();
                break;
            case R.id.internet:
//                联网测试
                AsyncHttpClient client = new AsyncHttpClient();

                client.get("http://dwtedx.com/itshare_296.html", new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        System.out.println("success====");
                        String s = new String(responseBody);
                        System.out.println("responseBody+++++" + s);

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        System.out.println("failure===");
                        System.out.println("responseBody" + responseBody);
                    }
                });
                break;
            default:
                break;

        }
    }
}
