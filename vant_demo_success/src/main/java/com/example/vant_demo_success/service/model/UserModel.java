package com.example.vant_demo_success.service.model;

import com.example.vant_demo_success.dataobject.AddressDO;
import com.example.vant_demo_success.dataobject.CartDO;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Package_Name: com.example.vant_demo_success.service.model
 * @ClassName: UserModel
 * @Description:
 * @Author: cxy
 * @Date: 2019/11/19 0:56
 */
public class UserModel {
    private Integer id;

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotBlank(message = "手机号不能为空")
    private String telephone;

    @NotNull(message = "性别不能为空")
    private String sex;

    @NotNull(message = "年龄不能为空")
    @Min(value = 0, message = "年龄必须大于0岁")
    @Max(value = 150, message = "我也希望活到150岁")
    private Integer age;

    @NotNull(message = "生日不能为空")
    private String birthday;

    @NotNull(message = "请选择头像")
    private String image_url;

    private List<AddressDO> address;
    private List<CartDO> goodDetail;


    private String collects;


    private String bought;
    private String unpaid;
    private String unreceived;
    private String unassess;

    private String payPWD;


    public String getPayPWD() {
        return payPWD;
    }

    public void setPayPWD(String payPWD) {
        this.payPWD = payPWD;
    }

    public String getUnpaid() {
        return unpaid;
    }

    public void setUnpaid(String unpaid) {
        this.unpaid = unpaid;
    }

    public String getUnreceived() {
        return unreceived;
    }

    public void setUnreceived(String unreceived) {
        this.unreceived = unreceived;
    }

    public String getUnassess() {
        return unassess;
    }

    public void setUnassess(String unassess) {
        this.unassess = unassess;
    }

    public String getBought() {
        return bought;
    }

    public void setBought(String bought) {
        this.bought = bought;
    }

    public List<CartDO> getGoodDetail() {
        return goodDetail;
    }

    public void setGoodDetail(List<CartDO> goodDetail) {
        this.goodDetail = goodDetail;
    }

    public String getCollects() {
        return collects;
    }

    public void setCollects(String collects) {
        this.collects = collects;
    }


    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public List<AddressDO> getAddress() {
        return address;
    }

    public void setAddress(List<AddressDO> address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", telephone='" + telephone + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", birthday='" + birthday + '\'' +
                ", image_url='" + image_url + '\'' +
                ", address=" + address +
                ", goodDetail=" + goodDetail +
                ", collects='" + collects + '\'' +
                ", bought='" + bought + '\'' +
                ", unpaid='" + unpaid + '\'' +
                ", unreceived='" + unreceived + '\'' +
                ", unassess='" + unassess + '\'' +
                ", payPWD='" + payPWD + '\'' +
                '}';
    }
}
