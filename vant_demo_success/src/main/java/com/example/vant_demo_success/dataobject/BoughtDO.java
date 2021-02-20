package com.example.vant_demo_success.dataobject;

public class BoughtDO {

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
        this.bought = bought == null ? null : bought.trim();
    }


    public String getUnpaid() {
        return unpaid;
    }


    public void setUnpaid(String unpaid) {
        this.unpaid = unpaid == null ? null : unpaid.trim();
    }


    public String getUnreceived() {
        return unreceived;
    }


    public void setUnreceived(String unreceived) {
        this.unreceived = unreceived == null ? null : unreceived.trim();
    }


    public String getUnassess() {
        return unassess;
    }


    public void setUnassess(String unassess) {
        this.unassess = unassess == null ? null : unassess.trim();
    }

}