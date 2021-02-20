package com.example.vant_demo_success.service.model;

import com.example.vant_demo_success.service.model.products.ProductModel;

/**
 * @Package_Name: com.example.vant_demo_success.service.model
 * @ClassName: CartItemModel
 * @Description:
 * @Author: cxy
 * @Date: 2020/4/28 17:47
 */
public class CartItemModel {
    private ProductModel productModel;
    private Integer num;
    private Integer userid;

    public ProductModel getProductModel() {
        return productModel;
    }

    public void setProductModel(ProductModel productModel) {
        this.productModel = productModel;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "CartItemModel{" +
                "productModel=" + productModel +
                ", num=" + num +
                ", userid=" + userid +
                '}';
    }
}
