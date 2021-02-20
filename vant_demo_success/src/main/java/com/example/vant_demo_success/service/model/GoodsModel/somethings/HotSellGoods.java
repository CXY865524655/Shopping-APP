package com.example.vant_demo_success.service.model.GoodsModel.somethings;

import java.math.BigDecimal;

/**
 * @Package_Name: com.example.vant_demo_success.service.model.GoodsModel.somethings
 * @ClassName: HotSellGoods
 * @Description:
 * @Author: cxy
 * @Date: 2019/11/29 0:19
 */
public class HotSellGoods {
    private Integer goodsid;


    private String goodsname;


    private String image;


    private BigDecimal mallprice;


    private BigDecimal price;

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

    @Override
    public String toString() {
        return "HotSellGoods{" +
                "goodsid=" + goodsid +
                ", goodsname='" + goodsname + '\'' +
                ", image='" + image + '\'' +
                ", mallprice=" + mallprice +
                ", price=" + price +
                '}';
    }
}
