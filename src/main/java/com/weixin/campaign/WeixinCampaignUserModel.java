package com.weixin.campaign;

import java.sql.Timestamp;

public class WeixinCampaignUserModel {

	private String userId;
	private String userName;
	private Integer finishCount;
	private Integer rightCount;
	private Timestamp updateTime;
	private Timestamp createTime;
	private Long answerRate;
	private Integer hasAnswer;
	
	public WeixinCampaignUserModel() {}
	
	public WeixinCampaignUserModel(String userId, String userName, Integer finishCount, Integer rightCount,
			Timestamp updateTime, Timestamp createTime, Long answerRate, Integer hasAnswer) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.finishCount = finishCount;
		this.rightCount = rightCount;
		this.updateTime = updateTime;
		this.createTime = createTime;
		this.answerRate = answerRate;
		this.hasAnswer = hasAnswer;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getFinishCount() {
		return finishCount;
	}

	public void setFinishCount(Integer finishCount) {
		this.finishCount = finishCount;
	}

	public Integer getRightCount() {
		return rightCount;
	}

	public void setRightCount(Integer rightCount) {
		this.rightCount = rightCount;
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

	public Long getAnswerRate() {
		return answerRate;
	}

	public void setAnswerRate(Long answerRate) {
		this.answerRate = answerRate;
	}

	public Integer getHasAnswer() {
		return hasAnswer;
	}

	public void setHasAnswer(Integer hasAnswer) {
		this.hasAnswer = hasAnswer;
	}

	@Override
	public String toString() {
		return "WeixinCampaignUserModel [userId=" + userId + ", userName=" + userName + ", finishCount=" + finishCount
				+ ", rightCount=" + rightCount + ", updateTime=" + updateTime + ", createTime=" + createTime
				+ ", answerRate=" + answerRate + ", hasAnswer=" + hasAnswer + "]";
	}
		
}
