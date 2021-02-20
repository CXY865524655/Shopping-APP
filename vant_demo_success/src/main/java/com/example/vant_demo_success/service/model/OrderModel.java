package com.example.vant_demo_success.service.model;

import java.math.BigDecimal;

/**
 * @Package_Name: com.example.vant_demo_success.service.model
 * @ClassName: OrderModel
 * @Description:
 * @Author: cxy
 * @Date: 2020/3/14 17:44
 */
public class OrderModel {

    // 订单编号
    private String orderID;

    // 商品图片
    private String image;

    // 商品名称
    private String goodName;

    // 商品售价
    private BigDecimal mallPrice;

    // 收货人
    private String getGoodPeople;

    // 收货地址
    private String address;

    // 收货人电话
    private String telephone;

    // 订单状态
    private String orderStatus;

    // 用户ID
    private Integer userid;

    private Integer num;

    private String OrderDate;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String orderDate) {
        OrderDate = orderDate;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public BigDecimal getMallPrice() {
        return mallPrice;
    }

    public void setMallPrice(BigDecimal mallPrice) {
        this.mallPrice = mallPrice;
    }

    public String getGetGoodPeople() {
        return getGoodPeople;
    }

    public void setGetGoodPeople(String getGoodPeople) {
        this.getGoodPeople = getGoodPeople;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "OrderModel{" +
                "orderID='" + orderID + '\'' +
                ", image='" + image + '\'' +
                ", goodName='" + goodName + '\'' +
                ", mallPrice=" + mallPrice +
                ", getGoodPeople='" + getGoodPeople + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", userid=" + userid +
                ", num=" + num +
                ", OrderDate='" + OrderDate + '\'' +
                '}';
    }
}
