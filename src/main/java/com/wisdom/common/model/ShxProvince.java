package com.wisdom.common.model;

/**
 * ShxProvince entity. @author MyEclipse Persistence Tools
 */

public class ShxProvince implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String provinceId;
	private String province;

	// Constructors

	/** default constructor */
	public ShxProvince() {
	}

	/** minimal constructor */
	public ShxProvince(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public ShxProvince(Integer id, String provinceId, String province) {
		this.id = id;
		this.provinceId = provinceId;
		this.province = province;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProvinceId() {
		return this.provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

}