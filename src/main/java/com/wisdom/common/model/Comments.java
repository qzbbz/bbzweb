package com.wisdom.common.model;

import java.sql.Timestamp;

/**
 * Comments entity. @author MyEclipse Persistence Tools
 */

public class Comments implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long communityId;
	private String comment;
	private String userId;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public Comments() {
	}

	/** minimal constructor */
	public Comments(Long id, Long communityId, String comment, String userId) {
		this.id = id;
		this.communityId = communityId;
		this.comment = comment;
		this.userId = userId;
	}

	/** full constructor */
	public Comments(Long id, Long communityId, String comment, String userId,
			Timestamp createTime) {
		this.id = id;
		this.communityId = communityId;
		this.comment = comment;
		this.userId = userId;
		this.createTime = createTime;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCommunityId() {
		return this.communityId;
	}

	public void setCommunityId(Long communityId) {
		this.communityId = communityId;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}