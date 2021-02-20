package com.example.vant_demo_success.dataobject;

public class CartDO_old {

    private Integer userid;

    private String gooddetail;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getGooddetail() {
        return gooddetail;
    }

    public void setGooddetail(String gooddetail) {
        this.gooddetail = gooddetail == null ? null : gooddetail.trim();
    }
}