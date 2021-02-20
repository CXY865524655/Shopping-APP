package com.example.vant_demo_success.service.model;

/**
 * @Package_Name: com.example.vant_demo_success.service.model
 * @ClassName: BoughtModel
 * @Description:
 * @Author: cxy
 * @Date: 2020/2/20 12:08
 */
public class BoughtModel {

    private Integer userid;


    private String bought;


    private String unpaid;


    private String unreceived;


    private String unassess;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getBought() {
        return bought;
    }

    public void setBought(String bought) {
        this.bought = bought;
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

    @Override
    public String toString() {
        return "BoughtModel{" +
                "userid=" + userid +
                ", bought='" + bought + '\'' +
                ", unpaid='" + unpaid + '\'' +
                ", unreceived='" + unreceived + '\'' +
                ", unassess='" + unassess + '\'' +
                '}';
    }
}
