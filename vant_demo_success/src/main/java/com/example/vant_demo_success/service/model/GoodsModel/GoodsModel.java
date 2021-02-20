package com.example.vant_demo_success.service.model.GoodsModel;

import com.example.vant_demo_success.service.model.GoodsModel.somethings.*;

import java.util.List;

/**
 * @Package_Name: com.example.vant_demo_success.service.model
 * @ClassName: GoodsModel
 * @Description:
 * @Author: cxy
 * @Date: 2019/11/25 17:46
 */
public class GoodsModel {
    private List<SwiperImage> swiperImage;
    private List<RecommendGoods> recommendGoods;
    private List<HotSellGoods> hotSellGoods;
    private List<GoodsFloor> floor1;
    private List<GoodsFloor> floor2;
    private List<GoodsFloor> floor3;
    private String[] floorsName;
    private List<Category> categories;
    private AdvertesPicture advertesPicture;

    public AdvertesPicture getAdvertesPicture() {
        return advertesPicture;
    }

    public void setAdvertesPicture(AdvertesPicture advertesPicture) {
        this.advertesPicture = advertesPicture;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String[] getFloorsName() {
        return floorsName;
    }

    public void setFloorsName(String[] floorsName) {
        this.floorsName = floorsName;
    }

    public List<GoodsFloor> getFloor1() {
        return floor1;
    }

    public void setFloor1(List<GoodsFloor> floor1) {
        this.floor1 = floor1;
    }

    public List<GoodsFloor> getFloor2() {
        return floor2;
    }

    public void setFloor2(List<GoodsFloor> floor2) {
        this.floor2 = floor2;
    }

    public List<GoodsFloor> getFloor3() {
        return floor3;
    }

    public void setFloor3(List<GoodsFloor> floor3) {
        this.floor3 = floor3;
    }

    public List<HotSellGoods> getHotSellGoods() {
        return hotSellGoods;
    }

    public void setHotSellGoods(List<HotSellGoods> hotSellGoods) {
        this.hotSellGoods = hotSellGoods;
    }

    public List<SwiperImage> getSwiperImage() {
        return swiperImage;
    }

    public void setSwiperImage(List<SwiperImage> swiperImage) {
        this.swiperImage = swiperImage;
    }

    public List<RecommendGoods> getRecommendGoods() {
        return recommendGoods;
    }

    public void setRecommendGoods(List<RecommendGoods> recommendGoods) {
        this.recommendGoods = recommendGoods;
    }
}
