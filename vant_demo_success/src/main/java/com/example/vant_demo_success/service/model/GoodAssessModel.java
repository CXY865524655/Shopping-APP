package com.example.vant_demo_success.service.model;

/**
 * @Package_Name: com.example.vant_demo_success.service.model
 * @ClassName: GoodAssessModel
 * @Description:
 * @Author: cxy
 * @Date: 2020/2/24 17:12
 */
public class GoodAssessModel {

    private String assess;

    private Integer starNum;

    private String sendTime;

    private String image_url;

    private String username;


    public Integer getStarNum() {
        return starNum;
    }

    public void setStarNum(Integer starNum) {
        this.starNum = starNum;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getAssess() {
        return assess;
    }

    public void setAssess(String assess) {
        this.assess = assess;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "GoodAssessModel{" +
                "assess='" + assess + '\'' +
                ", starNum=" + starNum +
                ", sendTime='" + sendTime + '\'' +
                ", image_url='" + image_url + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
