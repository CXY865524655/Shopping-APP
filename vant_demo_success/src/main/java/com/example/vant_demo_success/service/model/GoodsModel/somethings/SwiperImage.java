package com.example.vant_demo_success.service.model.GoodsModel.somethings;

/**
 * @Package_Name: com.example.vant_demo_success.service.model.GoodsModel.somethings
 * @ClassName: SwiperImage
 * @Description:
 * @Author: cxy
 * @Date: 2019/11/25 17:49
 */
public class SwiperImage {
    private Integer id;
    private String imageUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "SwiperImage{" +
                "id=" + id +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
