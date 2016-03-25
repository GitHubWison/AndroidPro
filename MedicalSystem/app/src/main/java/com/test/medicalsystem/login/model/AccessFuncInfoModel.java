package com.test.medicalsystem.login.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by xuqiwei-Office on 2016/3/25.
 * 用户权限一览
 */
public class AccessFuncInfoModel {
    private String url;
    private String permissionId;
    private String permissionName;

    public AccessFuncInfoModel(String url, String permissionId, String permissionName) {
        this.url = url;
        this.permissionId = permissionId;
        this.permissionName = permissionName;
    }

    public AccessFuncInfoModel(JSONObject jsonObject) {
        try {
            this.url = jsonObject.getString("Url");
            this.permissionId = jsonObject.getString("PermissionId");
            this.permissionName = jsonObject.getString("PermissionName");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }
}
