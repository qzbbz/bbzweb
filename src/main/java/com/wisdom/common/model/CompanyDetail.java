package com.wisdom.common.model;

import java.sql.Timestamp;

/**
 * Accounter entity. @author MyEclipse Persistence Tools
 */

public class CompanyDetail implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long companyId;
	private String companyResFile;
	private String corporation;
	private String province;
	private String city;
	private String area;
	private String extra;
	private String orgCode;
	private String orgCodeFile;
	private String taxCode;
	private String taxCodeFile;
	private String bankName;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public CompanyDetail() {
	}

	/** minimal constructor */
	public CompanyDetail(Long id) {
		this.id = id;
	}

	public CompanyDetail(Long id, Long companyId, String companyResFile, String corporation, String province,
			String city, String area, String extra, String orgCode, String orgCodeFile, String taxCode,
			String taxCodeFile, String bankName, Timestamp createTime) {
		super();
		this.id = id;
		this.companyId = companyId;
		this.companyResFile = companyResFile;
		this.corporation = corporation;
		this.province = province;
		this.city = city;
		this.area = area;
		this.extra = extra;
		this.orgCode = orgCode;
		this.orgCodeFile = orgCodeFile;
		this.taxCode = taxCode;
		this.taxCodeFile = taxCodeFile;
		this.bankName = bankName;
		this.createTime = createTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyResFile() {
		return companyResFile;
	}

	public void setCompanyResFile(String companyResFile) {
		this.companyResFile = companyResFile;
	}

	public String getCorporation() {
		return corporation;
	}

	public void setCorporation(String corporation) {
		this.corporation = corporation;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgCodeFile() {
		return orgCodeFile;
	}

	public void setOrgCodeFile(String orgCodeFile) {
		this.orgCodeFile = orgCodeFile;
	}

	public String getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	public String getTaxCodeFile() {
		return taxCodeFile;
	}

	public void setTaxCodeFile(String taxCodeFile) {
		this.taxCodeFile = taxCodeFile;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "CompanyDetail [id=" + id + ", companyId=" + companyId + ", companyResFile=" + companyResFile
				+ ", corporation=" + corporation + ", province=" + province + ", city=" + city + ", area=" + area
				+ ", extra=" + extra + ", orgCode=" + orgCode + ", orgCodeFile=" + orgCodeFile + ", taxCode=" + taxCode
				+ ", taxCodeFile=" + taxCodeFile + ", bankName=" + bankName + ", createTime=" + createTime + "]";
	}

}