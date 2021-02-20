package com.example.vant_demo_success.service.model.GoodsModel.somethings;

import java.util.List;

/**
 * @Package_Name: com.example.vant_demo_success.service.model.GoodsModel.somethings
 * @ClassName: Category
 * @Description:
 * @Author: cxy
 * @Date: 2019/11/29 16:33
 */
public class Category {
    private List<SubCategory> subCategories;    // 子标签
    private String comments;                    // 评论
    private String image;                       // 图片
    private Integer categoryid;              // ID
    private String categoryname;            // 父标签名称

    public List<SubCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<SubCategory> subCategories) {
        this.subCategories = subCategories;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

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

    @Override
    public String toString() {
        return "Category{" +
                "subCategories=" + subCategories +
                ", comments='" + comments + '\'' +
                ", image='" + image + '\'' +
                ", categoryid=" + categoryid +
                ", categoryname='" + categoryname + '\'' +
                '}';
    }
}
