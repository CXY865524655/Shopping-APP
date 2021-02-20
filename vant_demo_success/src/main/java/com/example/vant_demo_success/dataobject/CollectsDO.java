package com.example.vant_demo_success.dataobject;

public class CollectsDO {

    private Integer userid;

    private String collects;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getCollects() {
        return collects;
    }

    public void setCollects(String collects) {
        this.collects = collects == null ? null : collects.trim();
    }
}