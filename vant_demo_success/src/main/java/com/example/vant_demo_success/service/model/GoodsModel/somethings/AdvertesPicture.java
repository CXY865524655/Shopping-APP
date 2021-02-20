package com.example.vant_demo_success.service.model.GoodsModel.somethings;

/**
 * @Package_Name: com.example.vant_demo_success.service.model.GoodsModel.somethings
 * @ClassName: advertesPicture
 * @Description:
 * @Author: cxy
 * @Date: 2019/11/30 10:47
 */
public class AdvertesPicture {
    private String PICTURE_ADDRESS;

    public String getPICTURE_ADDRESS() {
        return PICTURE_ADDRESS;
    }

    public void setPICTURE_ADDRESS(String PICTURE_ADDRESS) {
        this.PICTURE_ADDRESS = PICTURE_ADDRESS;
    }

    @Override
    public String toString() {
        return "advertesPicture{" +
                "PICTURE_ADDRESS='" + PICTURE_ADDRESS + '\'' +
                '}';
    }
}
