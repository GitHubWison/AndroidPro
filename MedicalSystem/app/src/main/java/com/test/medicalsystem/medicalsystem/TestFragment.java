package com.test.medicalsystem.medicalsystem;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.test.medicalsystem.commonclass.StatusButton;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestFragment extends Fragment implements View.OnClickListener, StatusButton.StatusButtonClickedListener{

    private NextFragment nextFragment;
    private Button recoverButton;
    private StatusButton statusButton;
    private Button clickButton;
    private StatusButton statusButton2;
    private List<Map<String,String>> listItems;
    //webserverice请求
    private static final String targetNameSpace = "http://WebXml.com.cn/";
    private static final String getSupportProvince = "getSupportProvince";
    private static final String WSDL = "http://webservice.webxml.com.cn/WebServices/WeatherWebService.asmx?wsdl";
    public TestFragment() {
        // Required empty public constructor
        super();
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initDatas();
        initViews();
        initEvents();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.test_fragment, container, false);
    }
    private  void initDatas()
    {
        listItems = new ArrayList<Map<String,String>>();
    }
    private void initViews(){
        clickButton =  (Button)getView().findViewById(R.id.clickbutton);
        recoverButton = (Button)getView().findViewById(R.id.recover_button);

        statusButton = (StatusButton)getView().findViewById(R.id.status_button);
        statusButton2 = (StatusButton)getView().findViewById(R.id.status_button2);
    }

    private void initEvents()
    {
        clickButton.setOnClickListener(this);
        recoverButton.setOnClickListener(this);
        statusButton.setStatusButtonClickedListener(this);
        statusButton2.setStatusButtonClickedListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.clickbutton:
//                跳转到下一个页面
                nextFragment = new NextFragment();
                FragmentTransaction fragmentTransaction = getActivity()
                        .getSupportFragmentManager().beginTransaction();
                fragmentTransaction
                        .setCustomAnimations(R.anim.in_from_right,
                                R.anim.out_to_left, R.anim.in_from_left,
                                R.anim.out_to_right)
                        .replace(R.id.content_fragmentlayout,
                                nextFragment,
                                "NextFragment")
                        .addToBackStack(null).commit();
                break;
            case R.id.recover_button:
//                恢复自定义按钮的样式
                statusButton.recoverButtonStyle();
                break;
            default:
                break;
        }
    }


    @Override
    public void onStatusButtonClicked(StatusButton statusButton) {
        switch (statusButton.getId())
        {
            case R.id.status_button:
                System.out.println("点击自定义按钮后需要做的其他工作");
                new NetAsynTask().execute();

                break;
            case R.id.status_button2:
                System.out.println("点击自定义按钮后22222需要做的其他工作");
                break;
            default:

                break;
        }
    }

    class NetAsynTask extends AsyncTask<Object,Object, String>
    {
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s.equals("success"))
            {
                System.out.println("listItems==="+listItems.size());
            }
        }

        @Override
        protected String doInBackground(Object... params) {
            SoapObject soapObject = new SoapObject(targetNameSpace, getSupportProvince);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER12);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(soapObject);
            HttpTransportSE httpSE = new HttpTransportSE(WSDL);
//                开始调用远程的方法
            try {
//                httpSE.call(targetNameSpace + getSupportProvince, envelope);
                httpSE.call(null, envelope);
//                    得到远程方法返回的对象
                SoapObject resultobj = (SoapObject)envelope.getResponse();
//                    获得数据
                int count = resultobj.getPropertyCount();
                for (int i = 0; i < count; i++)
                {
                    Map<String, String> listItem = new HashMap<String, String>();
                    listItem.put("province", resultobj.getProperty(i).toString());
                    listItems.add(listItem);
                }
                System.out.println(listItems.size());
            }catch (IOException e)
            {
                e.printStackTrace();
                return "IOException";

            }catch(XmlPullParserException e)
            {
                e.printStackTrace();
                return "XmlPullParserException";
            }
            return "success";
        }
    }

}
