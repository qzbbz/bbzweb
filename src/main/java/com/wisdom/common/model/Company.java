package com.wisdom.common.model;

import java.sql.Timestamp;

/**
 * Company entity. @author MyEclipse Persistence Tools
 */

public class Company implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private Long parentId;
	private String monthExpense;
	private String perfectMoment;
	private String takeType;
	private String accounterId;
	private String companyCode;
	private Timestamp createTime;
	private String taxStatus;

	// Constructors

	/** default constructor */
	public Company() {
	}

	/** minimal constructor */
	public Company(Long id) {
		this.id = id;
	}

	/** full constructor */
	public Company(Long id, String name, Long parentId, String monthExpense,
			String perfectMoment, String takeType, String accounterId, String companyCode, Timestamp createTime,String taxStatus) {
		this.id = id;
		this.name = name;
		this.parentId = parentId;
		this.monthExpense = monthExpense;
		this.perfectMoment = perfectMoment;
		this.takeType = takeType;
		this.accounterId = accounterId;
		this.companyCode = companyCode;
		this.createTime = createTime;
		this.taxStatus = taxStatus;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccounterId() {
		return accounterId;
	}

	public void setAccounterId(String accounterId) {
		this.accounterId = accounterId;
	}

	public String getTakeType() {
		return takeType;
	}

	public void setTakeType(String takeType) {
		this.takeType = takeType;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getParentId() {
		return this.parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getMonthExpense() {
		return this.monthExpense;
	}

	public void setMonthExpense(String monthExpense) {
		this.monthExpense = monthExpense;
	}

	public String getPerfectMoment() {
		return this.perfectMoment;
	}

	public void setPerfectMoment(String perfectMoment) {
		this.perfectMoment = perfectMoment;
	}
	
	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getTaxStatus() {
		return taxStatus;
	}

	public void setTaxStatus(String taxStatus) {
		this.taxStatus = taxStatus;
	}
	
	

}