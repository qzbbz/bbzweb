package com.wisdom.common.model;

import java.sql.Timestamp;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String userId;
	private String userName;
	private String msgEmail;
	private Integer typeId;
	private String userLevel;
	private Long companyId;
	private String userEncode;
	private Integer auditStatus;
	private String billAuditUser;
	private Timestamp createTime;

	// Constructors

	public User(Long id, String userId, String userName, String msgEmail, Integer typeId, String userLevel,
			Long companyId, String userEncode, Integer auditStatus, String billAuditUser, Timestamp createTime) {
		super();
		this.id = id;
		this.userId = userId;
		this.userName = userName;
		this.msgEmail = msgEmail;
		this.typeId = typeId;
		this.userLevel = userLevel;
		this.companyId = companyId;
		this.userEncode = userEncode;
		this.auditStatus = auditStatus;
		this.billAuditUser = billAuditUser;
		this.createTime = createTime;
	}
	
	/** default constructor */
	public User() {
	}
	
	// Property accessors

	public String getBillAuditUser() {
		return billAuditUser;
	}

	public void setBillAuditUser(String billAuditUser) {
		this.billAuditUser = billAuditUser;
	}

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	public Long getId() {
		return this.id;
	}

	public String getMsgEmail() {
		return msgEmail;
	}

	public void setMsgEmail(String msgEmail) {
		this.msgEmail = msgEmail;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return this.userId;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserName() {
		return this.userName;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	
	public String getUserEncode() {
		return this.userEncode;
	}

	public void setUserEncode(String userEncode) {
		this.userEncode = userEncode;
	}

	public Long getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}

}