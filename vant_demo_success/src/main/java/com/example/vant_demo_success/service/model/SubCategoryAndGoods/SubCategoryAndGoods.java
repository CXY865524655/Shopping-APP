package com.example.vant_demo_success.service.model.SubCategoryAndGoods;

import com.example.vant_demo_success.service.model.SubCategoryAndGoods.somtings.Product;

import java.util.List;

/**
 * @Package_Name: com.example.vant_demo_success.service.model
 * @ClassName: SubCategoryAndGoods
 * @Description:
 * @Author: cxy
 * @Date: 2020/2/4 11:36
 */
public class SubCategoryAndGoods {
    // 大标签
    private Integer goods_type;

    // 子标签
    private Integer sub_type;

    // 子标签名字
    private String subCategoryName;

    // 子标签对应的产品列表
    private List<Product> subCategory_Products;

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

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public List<Product> getSubCategory_Products() {
        return subCategory_Products;
    }

    public void setSubCategory_Products(List<Product> subCategory_Products) {
        this.subCategory_Products = subCategory_Products;
    }

    @Override
    public String toString() {
        return "SubCategoryAndGoods{" +
                "goods_type=" + goods_type +
                ", sub_type=" + sub_type +
                ", subCategoryName='" + subCategoryName + '\'' +
                ", subCategory_Products=" + subCategory_Products +
                '}';
    }
}
