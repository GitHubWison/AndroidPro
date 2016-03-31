package com.test.medicalsystem.tasklist.model;

import android.util.Log;

import com.test.medicalsystem.medicalsystem.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Nangy-Office on 2016/3/30.
 */
public class TaskListModel {
    private String patientName;
    private String patientGender;
    private String patientAge;
    private String patientNo;
    private String patientAddress;
    private String patientMPDS;
    private int backGroundColor;

    public TaskListModel(JSONObject jsonObject) {
        try {
            patientName = jsonObject.getString("PatientName");
            patientGender = jsonObject.getString("PatientSex");
            patientAge = jsonObject.getString("PatientAge") + "岁";
            patientNo = "编号：" + jsonObject.getString("MissionId");
            patientAddress = "地点：" + jsonObject.getString("Address");
            patientMPDS = "MPDS：" + jsonObject.getString("MPDS");
            backGroundColor = jsonObject.getInt("BackGroundColor");
            Log.d("backGround11Color",backGroundColor+"");

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public TaskListModel() {
        super();
        patientName = "";
        patientGender = "";
        patientAge = "";
        patientNo = "";
        patientAddress = "";
        patientMPDS = "";
        backGroundColor = R.color.app_bg_color;

    }

    public int getBackGroundColor() {
        return backGroundColor;
    }

    public void setBackGroundColor(int backGroundColor) {
        this.backGroundColor = backGroundColor;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }

    public String getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(String patientAge) {
        this.patientAge = patientAge;
    }

    public String getPatientNo() {
        return patientNo;
    }

    public void setPatientNo(String patientNo) {
        this.patientNo = patientNo;
    }

    public String getPatientAddress() {
        return patientAddress;
    }

    public void setPatientAddress(String patientAddress) {
        this.patientAddress = patientAddress;
    }

    public String getPatientMPDS() {
        return patientMPDS;
    }

    public void setPatientMPDS(String patientMPDS) {
        this.patientMPDS = patientMPDS;
    }
}
