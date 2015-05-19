package com.wisdom.common.model;

import java.sql.Timestamp;

/**
 * Accounter entity. @author MyEclipse Persistence Tools
 */

public class CompanyBankSta implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long companyId;
	private String date;
	private String fileName;
	private Integer identifyStatus;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public CompanyBankSta() {
	}

	/** minimal constructor */
	public CompanyBankSta(Long id) {
		this.id = id;
	}

	public CompanyBankSta(Long id, Long companyId, String date,
			String fileName, Integer identifyStatus, Timestamp createTime) {
		super();
		this.id = id;
		this.companyId = companyId;
		this.date = date;
		this.fileName = fileName;
		this.identifyStatus = identifyStatus;
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "CompanyBankSta [id=" + id + ", companyId=" + companyId
				+ ", date=" + date + ", fileName=" + fileName
				+ ", identifyStatus=" + identifyStatus + ", createTime="
				+ createTime + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Integer getIdentifyStatus() {
		return identifyStatus;
	}

	public void setIdentifyStatus(Integer identifyStatus) {
		this.identifyStatus = identifyStatus;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}