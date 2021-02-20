package com.example.vant_demo_success.service.model;

import com.example.vant_demo_success.service.model.products.ProductModel;

import java.util.List;

/**
 * @Package_Name: com.example.vant_demo_success.service.model
 * @ClassName: HomeModel
 * @Description:
 * @Author: cxy
 * @Date: 2020/2/29 17:09
 */
public class HomeModel {
    private Integer id;

    private List<ProductModel> goodprice;

    private List<ProductModel> headerswipes;

    private List<ProductModel> recommends;
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

    public List<ProductModel> getGoodprice() {
        return goodprice;
    }

    public void setGoodprice(List<ProductModel> goodprice) {
        this.goodprice = goodprice;
    }

    public List<ProductModel> getHeaderswipes() {
        return headerswipes;
    }

    public void setHeaderswipes(List<ProductModel> headerswipes) {
        this.headerswipes = headerswipes;
    }

    public List<ProductModel> getRecommends() {
        return recommends;
    }

    public void setRecommends(List<ProductModel> recommends) {
        this.recommends = recommends;
    }

    @Override
    public String toString() {
        return "HomeModel{" +
                "id=" + id +
                ", goodprice=" + goodprice +
                ", headerswipes=" + headerswipes +
                ", recommends=" + recommends +
                ", time=" + time +
                '}';
    }
}
