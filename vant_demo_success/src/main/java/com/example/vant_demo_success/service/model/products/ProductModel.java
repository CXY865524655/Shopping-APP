package com.example.vant_demo_success.service.model.products;

import java.math.BigDecimal;

/**
 * @Package_Name: com.example.vant_demo_success.service.model.products
 * @ClassName: sometings
 * @Description:
 * @Author: cxy
 * @Date: 2020/2/4 14:50
 */
public class ProductModel {

    // Good ID
    private Integer goodsid;

    // 商品Name
    private String goodsname;

    // image
    private String image;


    // 正在售卖的价格
    private BigDecimal mallprice;

    // 原价
    private BigDecimal price;

    // 评分
    private BigDecimal assess;

    // 卖出多少
    private Integer sales;

    // 大标签
    private Integer goodsType;

    // 子标签
    private Integer subType;

    // 详情中介绍商品的图片
    private String details;

    // 库存
    private Integer stock;

    // 商品描述
    private String describes;

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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }
}
