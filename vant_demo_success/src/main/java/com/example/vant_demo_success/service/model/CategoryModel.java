package com.example.vant_demo_success.service.model;

/**
 * @Package_Name: com.example.vant_demo_success.service.model
 * @ClassName: Category
 * @Description:
 * @Author: cxy
 * @Date: 2020/2/4 0:11
 */
public class CategoryModel {

    private Integer categoryid;

    private String categoryname;

    private String image;


    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "CategoryModel{" +
                "categoryid=" + categoryid +
                ", categoryname='" + categoryname + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
