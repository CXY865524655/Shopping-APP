package com.example.vant_demo_success.service.model.GoodsModel.somethings;

/**
 * @Package_Name: com.example.vant_demo_success.service.model.GoodsModel.somethings
 * @ClassName: GoodsFloor
 * @Description:
 * @Author: cxy
 * @Date: 2019/11/29 9:44
 */
public class GoodsFloor {
    private Integer goodsid;

    private String image;


    public Integer getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(Integer goodsid) {
        this.goodsid = goodsid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    @Override
    public String toString() {
        return "GoodsFloor{" +
                "goodsid=" + goodsid +
                ", image='" + image + '\'' +
                '}';
    }
}
