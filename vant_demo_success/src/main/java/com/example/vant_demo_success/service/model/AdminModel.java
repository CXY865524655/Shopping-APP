package com.example.vant_demo_success.service.model;

/**
 * @Package_Name: com.example.vant_demo_success.service.model
 * @ClassName: AdminModel
 * @Description:
 * @Author: cxy
 * @Date: 2020/4/9 16:44
 */
public class AdminModel {
    private Integer id;

    private String name;

    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AdminModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
