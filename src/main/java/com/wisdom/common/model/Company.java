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
	private Integer monthExpense;
	private String perfectMoment;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public Company() {
	}

	/** minimal constructor */
	public Company(Long id) {
		this.id = id;
	}

	/** full constructor */
	public Company(Long id, String name, Long parentId, Integer monthExpense,
			String perfectMoment, Timestamp createTime) {
		this.id = id;
		this.name = name;
		this.parentId = parentId;
		this.monthExpense = monthExpense;
		this.perfectMoment = perfectMoment;
		this.createTime = createTime;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Integer getMonthExpense() {
		return this.monthExpense;
	}

	public void setMonthExpense(Integer monthExpense) {
		this.monthExpense = monthExpense;
	}

	public String getPerfectMoment() {
		return this.perfectMoment;
	}

	public void setPerfectMoment(String perfectMoment) {
		this.perfectMoment = perfectMoment;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}