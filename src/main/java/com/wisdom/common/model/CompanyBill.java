package com.wisdom.common.model;

import java.sql.Timestamp;

/**
 * Accounter entity. @author MyEclipse Persistence Tools
 */

public class CompanyBill implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long companyId;
	private String fileName;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public CompanyBill() {
	}

	/** minimal constructor */
	public CompanyBill(Long id) {
		this.id = id;
	}

	public CompanyBill(Long id, Long companyId, String fileName,
			Timestamp createTime) {
		super();
		this.id = id;
		this.companyId = companyId;
		this.fileName = fileName;
		this.createTime = createTime;
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "CompanyBill [id=" + id + ", companyId=" + companyId
				+ ", fileName=" + fileName + ", createTime=" + createTime + "]";
	}

}