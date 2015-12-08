package com.wisdom.common.model;

import java.sql.Timestamp;

public class CompanyAndPayModel {

	private Long companyId;
	
	private String companyName;
	
	private Integer serviceTime;
	
	private Double payAmount;
	
	private Timestamp createTime;

	public CompanyAndPayModel(Long companyId, String companyName, Integer serviceTime, Double payAmount,
			Timestamp createTime) {
		super();
		this.companyId = companyId;
		this.companyName = companyName;
		this.serviceTime = serviceTime;
		this.payAmount = payAmount;
		this.createTime = createTime;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Integer getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(Integer serviceTime) {
		this.serviceTime = serviceTime;
	}

	public Double getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(Double payAmount) {
		this.payAmount = payAmount;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "CompanyAndPayModel [companyId=" + companyId + ", companyName=" + companyName + ", serviceTime="
				+ serviceTime + ", payAmount=" + payAmount + ", createTime=" + createTime + "]";
	}
	
}
