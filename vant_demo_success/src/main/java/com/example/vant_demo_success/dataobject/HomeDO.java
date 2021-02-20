package com.example.vant_demo_success.dataobject;

public class HomeDO {

    private Integer id;

    private String goodprice;

    private String headerswipes;

    private String recommends;

    private Long time;

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodprice() {
        return goodprice;
    }

    public void setGoodprice(String goodprice) {
        this.goodprice = goodprice == null ? null : goodprice.trim();
    }

    public String getHeaderswipes() {
        return headerswipes;
    }

    public void setHeaderswipes(String headerswipes) {
        this.headerswipes = headerswipes == null ? null : headerswipes.trim();
    }

    public String getRecommends() {
        return recommends;
    }

    public void setRecommends(String recommends) {
        this.recommends = recommends == null ? null : recommends.trim();
    }
}