package com.example.vant_demo_success.service.model.HomeData;

import com.example.vant_demo_success.service.model.SubCategoryAndGoods.somtings.Product;
import com.example.vant_demo_success.service.model.products.ProductModel;

import java.util.List;

/**
 * @Package_Name: com.example.vant_demo_success.service.model.HomeData
 * @ClassName: HomeDataModel
 * @Description:
 * @Author: cxy
 * @Date: 2020/2/28 23:39
 */
public class HomeDataModel {
    private List<ProductModel> headerSwipes;

    private List<ProductModel> goodPrice;

    private List<ProductModel> recommends;

    public List<ProductModel> getHeaderSwipes() {
        return headerSwipes;
    }

    public void setHeaderSwipes(List<ProductModel> headerSwipes) {
        this.headerSwipes = headerSwipes;
    }

    public List<ProductModel> getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(List<ProductModel> goodPrice) {
        this.goodPrice = goodPrice;
    }

    public List<ProductModel> getRecommends() {
        return recommends;
    }

    public void setRecommends(List<ProductModel> recommends) {
        this.recommends = recommends;
    }

    @Override
    public String toString() {
        return "HomeDataModel{" +
                "headerSwipes=" + headerSwipes +
                ", goodPrice=" + goodPrice +
                ", recommends=" + recommends +
                '}';
    }
}
