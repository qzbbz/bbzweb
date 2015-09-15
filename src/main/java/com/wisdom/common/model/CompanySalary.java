package com.wisdom.common.model;

import java.sql.Timestamp;

/**
 * Accounter entity. @author MyEclipse Persistence Tools
 */

public class CompanySalary implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long companyId;
	private String salaryFile;
	private String salaryDate;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public CompanySalary() {
	}

	/** minimal constructor */
	public CompanySalary(Long id) {
		this.id = id;
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

	public String getSalaryFile() {
		return salaryFile;
	}

	public void setSalaryFile(String salaryFile) {
		this.salaryFile = salaryFile;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getSalaryDate() {
		return salaryDate;
	}

	public void setSalaryDate(String salaryDate) {
		this.salaryDate = salaryDate;
	}

	public CompanySalary(Long id, Long companyId, String salaryFile,
			String salaryDate, Timestamp createTime) {
		super();
		this.id = id;
		this.companyId = companyId;
		this.salaryFile = salaryFile;
		this.salaryDate = salaryDate;
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "CompanySalary [id=" + id + ", companyId=" + companyId
				+ ", salaryFile=" + salaryFile + ", createTime=" + createTime
				+ "]";
	}
}