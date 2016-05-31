package com.wisdom.common.model;

import java.sql.Timestamp;

/**
 * Role entity. @author MyEclipse Persistence Tools
 */

public class ExpenseType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private Timestamp createTime;
	private Integer hit;

	// Constructors

	/** default constructor */
	public ExpenseType() {
	}

	/** minimal constructor */
	public ExpenseType(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	/** full constructor */
	public ExpenseType(Long id, String name, Timestamp createTime, Integer hit) {
		this.id = id;
		this.name = name;
		this.createTime = createTime;
		this.hit = hit;
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

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getHit() {
		return hit;
	}

	public void setHit(Integer hit) {
		this.hit = hit;
	}

	
}