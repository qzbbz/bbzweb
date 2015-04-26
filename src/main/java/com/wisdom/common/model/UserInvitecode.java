package com.wisdom.common.model;

import java.sql.Timestamp;

/**
 * UserInvitecode entity. @author MyEclipse Persistence Tools
 */

public class UserInvitecode implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String userId;
	private String inviteCode;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public UserInvitecode() {
	}

	/** minimal constructor */
	public UserInvitecode(Long id, String userId, String inviteCode) {
		this.id = id;
		this.userId = userId;
		this.inviteCode = inviteCode;
	}

	/** full constructor */
	public UserInvitecode(Long id, String userId, String inviteCode,
			Timestamp createTime) {
		this.id = id;
		this.userId = userId;
		this.inviteCode = inviteCode;
		this.createTime = createTime;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getInviteCode() {
		return this.inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}