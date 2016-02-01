package com.weixin.campaign;

public class WeixinCampaignSubjectModel {

	private Integer id;
	private String subject;
	private String selectOne;
	private String selectTwo;
	private String selectThree;
	private Integer subjectStatus;
	private Integer rightSelect;
	
	public WeixinCampaignSubjectModel() {}
	
	public WeixinCampaignSubjectModel(Integer id, String subject, String selectOne, String selectTwo,
			String selectThree, Integer subjectStatus, Integer rightSelect) {
		super();
		this.id = id;
		this.subject = subject;
		this.selectOne = selectOne;
		this.selectTwo = selectTwo;
		this.selectThree = selectThree;
		this.subjectStatus = subjectStatus;
		this.rightSelect = rightSelect;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSelectOne() {
		return selectOne;
	}

	public void setSelectOne(String selectOne) {
		this.selectOne = selectOne;
	}

	public String getSelectTwo() {
		return selectTwo;
	}

	public void setSelectTwo(String selectTwo) {
		this.selectTwo = selectTwo;
	}

	public String getSelectThree() {
		return selectThree;
	}

	public void setSelectThree(String selectThree) {
		this.selectThree = selectThree;
	}

	public Integer getSubjectStatus() {
		return subjectStatus;
	}

	public void setSubjectStatus(Integer subjectStatus) {
		this.subjectStatus = subjectStatus;
	}

	public Integer getRightSelect() {
		return rightSelect;
	}

	public void setRightSelect(Integer rightSelect) {
		this.rightSelect = rightSelect;
	}

	@Override
	public String toString() {
		return "WeixinCampaignSubjectModel [id=" + id + ", subject=" + subject + ", selectOne=" + selectOne
				+ ", selectTwo=" + selectTwo + ", selectThree=" + selectThree + ", subjectStatus=" + subjectStatus
				+ ", rightSelect=" + rightSelect + "]";
	}
		
}
