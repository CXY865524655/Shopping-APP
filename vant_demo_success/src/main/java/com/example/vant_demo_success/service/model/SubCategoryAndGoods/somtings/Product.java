package com.example.vant_demo_success.service.model.SubCategoryAndGoods.somtings;

/**
 * @Package_Name: com.example.vant_demo_success.service.model.SubCategoryAndGoods.somtings
 * @ClassName: Product
 * @Description:
 * @Author: cxy
 * @Date: 2020/2/4 11:45
 */
public class Product {

    // 大标签
    private Integer goods_type;

    // 子标签
    private Integer sub_type;

    // 商品名称
    private String productName;

    public Integer getGoods_type() {
        return goods_type;
    }

    public void setGoods_type(Integer goods_type) {
        this.goods_type = goods_type;
    }

    public Integer getSub_type() {
        return sub_type;
    }

    public void setSub_type(Integer sub_type) {
        this.sub_type = sub_type;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "Product{" +
                "goods_type=" + goods_type +
                ", sub_type=" + sub_type +
                ", productName='" + productName + '\'' +
                '}';
    }
}
