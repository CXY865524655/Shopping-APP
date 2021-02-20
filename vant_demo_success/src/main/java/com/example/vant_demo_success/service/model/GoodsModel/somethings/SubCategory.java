package com.example.vant_demo_success.service.model.GoodsModel.somethings;

/**
 * @Package_Name: com.example.vant_demo_success.service.model.GoodsModel.somethings
 * @ClassName: SubCategory
 * @Description:
 * @Author: cxy
 * @Date: 2019/11/29 16:52
 */
public class SubCategory {
    private String comments;
    private Integer categoryid;
    private Integer subcategoryid;
    private String subcategoryname;

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public Integer getSubcategoryid() {
        return subcategoryid;
    }

    public void setSubcategoryid(Integer subcategoryid) {
        this.subcategoryid = subcategoryid;
    }

    public String getSubcategoryname() {
        return subcategoryname;
    }

    public void setSubcategoryname(String subcategoryname) {
        this.subcategoryname = subcategoryname;
    }

    @Override
    public String toString() {
        return "SubCategory{" +
                "comments='" + comments + '\'' +
                ", categoryid=" + categoryid +
                ", subcategoryid=" + subcategoryid +
                ", subcategoryname='" + subcategoryname + '\'' +
                '}';
    }
}
