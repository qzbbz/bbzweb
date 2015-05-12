package com.wisdom.common.model;

import java.sql.Timestamp;

/**
 * Invoice entity. @author MyEclipse Persistence Tools
 */

public class Invoice implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String title;
	private Integer expenseTypeId;
	private Integer status;
	private Double amount;
	private String desc;
	private Timestamp date;
	private String costCenter;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public Invoice() {
	}

	/** minimal constructor */
	public Invoice(Long id) {
		this.id = id;
	}

	/** full constructor */
	public Invoice(Long id, String title, Integer expenseTypeId, Integer status, Double amount, 
			String desc,Timestamp date,String costCenter,
			Timestamp createTime) {
		this.id = id;
		this.title = title;
		this.expenseTypeId = expenseTypeId;
		this.status = status;
		this.amount = amount;
		this.desc = desc;
		this.date = date;
		this.costCenter = costCenter;
		this.createTime = createTime;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Integer getExpenseTypeId() {
		return this.expenseTypeId;
	}

	public void setExpenseTypeId(Integer expenseTypeId) {
		this.expenseTypeId = expenseTypeId;
	}
	
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Timestamp getDate() {
		return this.date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

}