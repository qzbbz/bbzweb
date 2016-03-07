package com.weixin.model;

import java.sql.Timestamp;

public class WeixinWorkGoingOutModel {
    
    private Long id;
    
    private String start;
    
    private String end;
    
    private String distance;
    
    private String amount;
    
    private Timestamp date;
    
    private Timestamp createTime;
    
    private String costCenter;
    
    private String price;//单价
    public WeixinWorkGoingOutModel() {
    }

    
    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }


    public String getPrice() {
        return price;
    }


    public void setPrice(String price) {
        this.price = price;
    }


    public WeixinWorkGoingOutModel(Long id, String start, String end, String distance, String amount, Timestamp date,
            Timestamp createTime, String costCenter, String price) {
        super();
        this.id = id;
        this.start = start;
        this.end = end;
        this.distance = distance;
        this.amount = amount;
        this.date = date;
        this.createTime = createTime;
        this.costCenter = costCenter;
        this.price = price;
    }


    @Override
    public String toString() {
        return "WeixinWorkGoingOutModel [id=" + id + ", start=" + start + ", end=" + end + ", distance=" + distance
                + ", amount=" + amount + ", date=" + date + ", createTime=" + createTime + ", costCenter=" + costCenter
                + ", price=" + price + "]";
    }
    

}
