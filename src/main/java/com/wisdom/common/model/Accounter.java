package com.wisdom.common.model;

import java.sql.Timestamp;

/**
 * Accounter entity. @author MyEclipse Persistence Tools
 */

public class Accounter implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private Long id;
	private String userId;
	private String name;
	private Long areaId;
	private String image;
	private Long certificateId;
	private Long industryId;
	private Long careerId;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public Accounter() {
	}

	/** minimal constructor */
	public Accounter(Long id) {
		this.id = id;
	}

	/** full constructor */
	public Accounter(Long id, String userId, String name, Long areaId, String image,
			Long certificateId, Long industryId, Long careerId, 
			Timestamp createTime) {
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.areaId = areaId;
		this.image = image;
		this.certificateId = certificateId;
		this.industryId = industryId;
		this.careerId = careerId;
		this.createTime = createTime;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUSerId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getAreaId() {
		return this.areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Long getCertificateId() {
		return this.certificateId;
	}

	public void setCertificateId(Long certificateId) {
		this.certificateId = certificateId;
	}

	public Long getIndustryId() {
		return this.industryId;
	}

	public void setIndustryId(Long industryId) {
		this.industryId = industryId;
	}

	public Long getCareerId() {
		return this.careerId;
	}

	public void setCareerId(Long careerId) {
		this.careerId = careerId;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}