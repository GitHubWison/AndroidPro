package com.test.medicalsystem.medicalsystem;

/**
 * Created by Nangy-Office on 2016/3/22.
 */
public class Model {
    private String title;
    private String desc;
    public Model(String title , String desc){
        this.title = title;
        this.desc = desc;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }
}
