package com.minicup.materialdesign;

/**
 * Created by Austin on 2017-05-09.
 */

public class ActivityInfo {
  public final String title;
  public final String desc;
  public final Class clz;

    public ActivityInfo(String title, String desc, Class clz){
        this.title = title;
        this.desc = desc;
        this.clz = clz;
    }

    public ActivityInfo(String desc) {
        this.title = "=====================华丽分割线=====================";
        this.desc = desc;
        this.clz = null;
    }

    public ActivityInfo(){
        this("");
    }
}
