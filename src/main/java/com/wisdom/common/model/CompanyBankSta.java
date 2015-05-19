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
	private String ideName;
	private String ideBankName;
	private String ideAccount;
	private String ideDate;
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

	

	public CompanyBankSta(Long id, Long companyId, String date, String ideName,
			String ideBankName, String ideAccount, String ideDate,
			String fileName, Integer identifyStatus, Timestamp createTime) {
		super();
		this.id = id;
		this.companyId = companyId;
		this.date = date;
		this.ideName = ideName;
		this.ideBankName = ideBankName;
		this.ideAccount = ideAccount;
		this.ideDate = ideDate;
		this.fileName = fileName;
		this.identifyStatus = identifyStatus;
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "CompanyBankSta [id=" + id + ", companyId=" + companyId
				+ ", date=" + date + ", ideName=" + ideName + ", ideBankName="
				+ ideBankName + ", ideAccount=" + ideAccount + ", ideDate="
				+ ideDate + ", fileName=" + fileName + ", identifyStatus="
				+ identifyStatus + ", createTime=" + createTime + "]";
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

	public String getIdeName() {
		return ideName;
	}

	public void setIdeName(String ideName) {
		this.ideName = ideName;
	}

	public String getIdeBankName() {
		return ideBankName;
	}

	public void setIdeBankName(String ideBankName) {
		this.ideBankName = ideBankName;
	}

	public String getIdeAccount() {
		return ideAccount;
	}

	public void setIdeAccount(String ideAccount) {
		this.ideAccount = ideAccount;
	}

	public String getIdeDate() {
		return ideDate;
	}

	public void setIdeDate(String ideDate) {
		this.ideDate = ideDate;
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