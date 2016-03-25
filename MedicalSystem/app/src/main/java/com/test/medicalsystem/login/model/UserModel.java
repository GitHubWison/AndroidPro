package com.test.medicalsystem.login.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuqiwei-Office on 2016/3/25.
 * 登录后拉取到的用户信息
 */
public class UserModel {
    private boolean isInnerUser;
    private String userName;
    private String userId;
    private List<AccessFuncInfoModel> accessFuncInfoList;
    private String departmentId;
    private String loginName;
    private String applicationId;
    public UserModel(JSONObject jsonObject) {
        try {
            isInnerUser = jsonObject.getBoolean("IsInnerUser");
            userName = jsonObject.getString("UserName");
            userId = jsonObject.getString("UserId");
            accessFuncInfoList = new ArrayList<>();
            JSONArray array = jsonObject.getJSONArray("AccessFuncInfoList");
//            JSONObject jsonForArray;
            AccessFuncInfoModel accessFuncInfoModel = null;
            for (int i = 0; i < array.length(); i++)
            {
                accessFuncInfoModel = new AccessFuncInfoModel(array.getJSONObject(i));
                accessFuncInfoList.add(accessFuncInfoModel);
            }
            departmentId = jsonObject.getString("DepartmentId");
            loginName = jsonObject.getString("LoginName");
            applicationId = jsonObject.getString("ApplicationId");

        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("实例化失败","实例化失败");
        }

    }

    /**
     * 检查用户是否有此权限
     * @param referenceAccess 参照权限
     * @return
     */
    public boolean isUserHasAccess(String referenceAccess)
    {
        for (AccessFuncInfoModel access: this.accessFuncInfoList ) {
            if (access.getPermissionId().equals(referenceAccess))
            {
//                用户有权限
                return true;
            }
        }
        return false;
    }
}
