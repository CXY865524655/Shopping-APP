package com.example.vant_demo_success.service.model;

/**
 * @Package_Name: com.example.vant_demo_success.service.model
 * @ClassName: CollectsModel
 * @Description:
 * @Author: cxy
 * @Date: 2020/2/10 17:49
 */
public class CollectsModel {
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
        this.collects = collects;
    }

    @Override
    public String toString() {
        return "CollectsModel{" +
                "userid=" + userid +
                ", collects='" + collects + '\'' +
                '}';
    }
}
