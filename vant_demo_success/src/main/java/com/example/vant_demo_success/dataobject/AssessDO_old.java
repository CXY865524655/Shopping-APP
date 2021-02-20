package com.example.vant_demo_success.dataobject;

public class AssessDO_old {
    private Integer goodsid;


    private String assess;


    public Integer getGoodsid() {
        return goodsid;
    }


    public void setGoodsid(Integer goodsid) {
        this.goodsid = goodsid;
    }


    public String getAssess() {
        return assess;
    }


    public void setAssess(String assess) {
        this.assess = assess == null ? null : assess.trim();
    }

}