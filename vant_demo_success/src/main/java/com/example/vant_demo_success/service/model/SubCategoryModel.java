package com.example.vant_demo_success.service.model;

/**
 * @Package_Name: com.example.vant_demo_success.service.model
 * @ClassName: SubCategory
 * @Description:
 * @Author: cxy
 * @Date: 2020/2/4 0:12
 */
public class SubCategoryModel {
    private Integer subcategoryid;

    private String subcategoryname;

    private Integer categoryid;

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

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    @Override
    public String toString() {
        return "SubCategoryModel{" +
                "subcategoryid=" + subcategoryid +
                ", subcategoryname='" + subcategoryname + '\'' +
                ", categoryid=" + categoryid +
                '}';
    }
}
