package com.wisdom.common.model;

import java.sql.Timestamp;

/**
 * Company entity. @author MyEclipse Persistence Tools
 */

public class CompanyInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private Long id;
	private String name;
	private String perfectMoment;
	private String monthExpense;
	private String createTime;
	private int typeId;
	private String phone;

	// Constructors

	/** default constructor */
	public CompanyInfo() {
	}

	/** minimal constructor */
	public CompanyInfo(Long id) {
		this.id = id;
	}

	/** full constructor */
	public CompanyInfo(Long id, String name, String perfectMoment, String monthExpense, String createTime, int typeId,
			String phone) {
		super();
		this.id = id;
		this.name = name;
		this.perfectMoment = perfectMoment;
		this.monthExpense = monthExpense;
		this.createTime = createTime;
		this.typeId = typeId;
		this.phone = phone;
	}
	// Property accessors

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPerfectMoment() {
		return perfectMoment;
	}

	public void setPerfectMoment(String perfectMoment) {
		this.perfectMoment = perfectMoment;
	}

	public String getMonthExpense() {
		return monthExpense;
	}

	public void setMonthExpense(String monthExpense) {
		this.monthExpense = monthExpense;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	
}