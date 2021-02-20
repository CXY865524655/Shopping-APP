package com.example.vant_demo_success.service.model;

/**
 * @Package_Name: com.example.vant_demo_success.service.model
 * @ClassName: CartModel
 * @Description:
 * @Author: cxy
 * @Date: 2020/2/21 11:22
 */
public class CartModel {
    private Integer userId;

    private Integer goodsid;

    private Integer num;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(Integer goodsid) {
        this.goodsid = goodsid;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "CartModel{" +
                "userId=" + userId +
                ", goodsid=" + goodsid +
                ", num=" + num +
                '}';
    }
}
