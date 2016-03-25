package com.test.medicalsystem.httpmanager;

import java.io.Serializable;

/**
 * Created by Nangy-Office on 2016/3/24.
 */
public class DoMainModel{
    private String doMain;
    private static DoMainModel instance = null;

    public static synchronized DoMainModel getInstance()
    {
        if (instance == null)
        {
            instance = new DoMainModel();
        }

        return instance;
    }
    public DoMainModel() {
    }

    public String getDoMain() {
        return doMain;
    }

    public void setDoMain(String doMain) {
        this.doMain = doMain;
    }
}
