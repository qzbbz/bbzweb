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
	private String area;
	private String city;
	private String province;
	private String image;
	private String certificate;
	private String industry;
	private String career;
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
	public Accounter(Long id, String userId, String name, String area,
			String city, String province, String image, String certificate,
			String industry, String career, Timestamp createTime) {
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.area = area;
		this.city = city;
		this.province = province;
		this.image = image;
		this.certificate = certificate;
		this.industry = industry;
		this.career = career;
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

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCertificate() {
		return this.certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public String getIndustry() {
		return this.industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getCareer() {
		return this.career;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}