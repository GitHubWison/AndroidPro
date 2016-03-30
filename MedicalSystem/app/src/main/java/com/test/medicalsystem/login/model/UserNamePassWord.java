package com.test.medicalsystem.login.model;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by xuqiwei-Office on 2016/3/30.
 * 存储用户的username和password
 */
public class UserNamePassWord {
    private String userName;
    private String passWord;

    public UserNamePassWord(String passWord, String userName) {
        this.passWord = passWord;
        this.userName = userName;
    }

    public UserNamePassWord(JSONObject jsonObject) {
        try {
            this.passWord = jsonObject.getString("passWord");
            this.userName = jsonObject.getString("userName");
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("实例化失败", "实例化失败");
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
