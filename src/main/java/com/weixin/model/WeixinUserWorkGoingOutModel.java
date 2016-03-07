package com.weixin.model;

import java.sql.Timestamp;

public class WeixinUserWorkGoingOutModel {

    private int id;
    
    private String userId;
    
    private Long userWorkGoingOutId;
    
    private int status;
    
    private int approvalStatus;
    
    private String reasons;
    
    private String approvalId;
    
    private Timestamp updateTime;
    
    private Timestamp createTime;

     
    public WeixinUserWorkGoingOutModel() {
    }

    public WeixinUserWorkGoingOutModel(int id, String userId, Long userWorkGoingOutId, int status, int approvalStatus,
            String reasons, String approvalId, Timestamp updateTime, Timestamp createTime) {
        super();
        this.id = id;
        this.userId = userId;
        this.userWorkGoingOutId = userWorkGoingOutId;
        this.status = status;
        this.approvalStatus = approvalStatus;
        this.reasons = reasons;
        this.approvalId = approvalId;
        this.updateTime = updateTime;
        this.createTime = createTime;
    }



    public int getApprovalStatus() {
        return approvalStatus;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public Long getUserWorkGoingOutId() {
        return userWorkGoingOutId;
    }

    public void setUserWorkGoingOutId(Long userWorkGoingOutId) {
        this.userWorkGoingOutId = userWorkGoingOutId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setApprovalStatus(int approvalStatus) {
        this.approvalStatus = approvalStatus;
    }


    public String getReasons() {
        return reasons;
    }

    public void setReasons(String reasons) {
        this.reasons = reasons;
    }

    public String getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(String approvalId) {
        this.approvalId = approvalId;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }


    @Override
    public String toString() {
        return "WeixinUserWorkGoingOutModel [id=" + id + ", userId=" + userId + ", userWorkGoingOutId="
                + userWorkGoingOutId + ", status=" + status + ", approvalStatus=" + approvalStatus + ", reasons="
                + reasons + ", approvalId=" + approvalId + ", updateTime=" + updateTime + ", createTime=" + createTime
                + "]";
    }

    
}
