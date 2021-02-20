package com.example.vant_demo_success.service.model;

import com.example.vant_demo_success.service.model.products.ProductModel;

import java.util.List;

/**
 * @Package_Name: com.example.vant_demo_success.service.model
 * @ClassName: InitCategory
 * @Description:
 * @Author: cxy
 * @Date: 2020/2/4 15:50
 */
public class InitCategory {
    // 全部 大 标签
    private  List<CategoryModel> categoryList;

    // 默认第一个大标签下的 所有 子标签
    private List<SubCategoryModel> subCategoryModelList;

    // 默认第一个大标签下的第一个子标签的所有商品
    List<ProductModel> subProductList;

    public List<CategoryModel> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<CategoryModel> categoryList) {
        this.categoryList = categoryList;
    }

    public List<SubCategoryModel> getSubCategoryModelList() {
        return subCategoryModelList;
    }

    public void setSubCategoryModelList(List<SubCategoryModel> subCategoryModelList) {
        this.subCategoryModelList = subCategoryModelList;
    }

    public List<ProductModel> getSubProductList() {
        return subProductList;
    }

    public void setSubProductList(List<ProductModel> subProductList) {
        this.subProductList = subProductList;
    }

    @Override
    public String toString() {
        return "InitCategory{" +
                "categoryList=" + categoryList +
                ", subCategoryModelList=" + subCategoryModelList +
                ", subProductList=" + subProductList +
                '}';
    }
}
