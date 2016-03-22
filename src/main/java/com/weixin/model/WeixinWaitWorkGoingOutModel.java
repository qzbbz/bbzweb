package com.weixin.model;

import java.sql.Timestamp;

public class WeixinWaitWorkGoingOutModel {
    
    private int workId;
    
    private String userId;
    
    private String reasons;
    
    private Timestamp createTime;
    
    private String start;
    
    private String end;
    
    private String distance;
    
    private String amount;
    
    private String price;
    
    private Timestamp date;
    
    private String approvalName;

    public String getReasons() {
        return reasons;
    }

    public void setReasons(String reasons) {
        this.reasons = reasons;
    }

    
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getApprovalName() {
        return approvalName;
    }

    public void setApprovalName(String approvalName) {
        this.approvalName = approvalName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getWorkId() {
        return workId;
    }

    public void setWorkId(int workId) {
        this.workId = workId;
    }

    public WeixinWaitWorkGoingOutModel(int workId, String userId, String reasons, Timestamp createTime, String start,
            String end, String distance, String amount, String price, Timestamp date, String approvalName) {
        super();
        this.workId = workId;
        this.userId = userId;
        this.reasons = reasons;
        this.createTime = createTime;
        this.start = start;
        this.end = end;
        this.distance = distance;
        this.amount = amount;
        this.price = price;
        this.date = date;
        this.approvalName = approvalName;
    }

    @Override
    public String toString() {
        return "WeixinWaitWorkGoingOutModel [workId=" + workId + ", userId=" + userId + ", reasons=" + reasons
                + ", createTime=" + createTime + ", start=" + start + ", end=" + end + ", distance=" + distance
                + ", amount=" + amount + ", price=" + price + ", date=" + date + ", approvalName=" + approvalName + "]";
    }

    
}
