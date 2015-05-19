package com.wisdom.common.model;

import java.sql.Timestamp;

public class CostCenter implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long encode;
	private String name;
	private String alias;
	private String description;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public CostCenter() {
	}

	public CostCenter(Long id, Long encode, String name, String alias,
			String description, Timestamp createTime) {
		super();
		this.id = id;
		this.encode = encode;
		this.name = name;
		this.alias = alias;
		this.description = description;
		this.createTime = createTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEncode() {
		return encode;
	}

	public void setEncode(Long encode) {
		this.encode = encode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}