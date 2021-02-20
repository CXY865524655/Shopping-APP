package com.example.vant_demo_success.service.model;

import java.math.BigDecimal;


public class GoodDetailsModel {
    private Integer goodsid;


    private String goodsname;


    private String image;


    private BigDecimal mallprice;


    private BigDecimal price;


    private BigDecimal assess;


    private Integer sales;


    private Integer goodsType;


    private Integer subType;


    private BigDecimal details;

    private Integer stock;

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(Integer goodsid) {
        this.goodsid = goodsid;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public BigDecimal getMallprice() {
        return mallprice;
    }

    public void setMallprice(BigDecimal mallprice) {
        this.mallprice = mallprice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAssess() {
        return assess;
    }

    public void setAssess(BigDecimal assess) {
        this.assess = assess;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(Integer goodsType) {
        this.goodsType = goodsType;
    }

    public Integer getSubType() {
        return subType;
    }

    public void setSubType(Integer subType) {
        this.subType = subType;
    }

    public BigDecimal getDetails() {
        return details;
    }

    public void setDetails(BigDecimal details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "GoodDetailsModel{" +
                "goodsid=" + goodsid +
                ", goodsname='" + goodsname + '\'' +
                ", image='" + image + '\'' +
                ", mallprice=" + mallprice +
                ", price=" + price +
                ", assess=" + assess +
                ", sales=" + sales +
                ", goodsType=" + goodsType +
                ", subType=" + subType +
                ", details=" + details +
                ", stock=" + stock +
                '}';
    }
}
