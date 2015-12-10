package com.wisdom.common.model;

import java.sql.Timestamp;

public class TestInvoice implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long companyId;
	private String fileName;
	private String billDate;
	private Integer isFixedAssets;
	private Timestamp createTime;
	private Timestamp modifiyTime;
	private String itemId;
	// Constructors


	/** default constructor */
	public TestInvoice() {
	}
	
	/** minimal constructor */
	public TestInvoice(Long id) {
		this.id = id;
	}

	public TestInvoice(Long id, Long companyId,
			String fileName, String billDate, Integer isFixedAssets, Timestamp createTime, Timestamp modifyTime, String itemId) {
		super();
		this.id = id;
		this.companyId = companyId;
		this.fileName = fileName;
		this.billDate = billDate;
		this.isFixedAssets = isFixedAssets;
		this.createTime = createTime;
		this.modifiyTime = modifyTime;
		this.itemId = itemId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	public Integer getIsFixedAssets() {
		return isFixedAssets;
	}

	public void setIsFixedAssets(Integer isFixedAssets) {
		this.isFixedAssets = isFixedAssets;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getModifiyTime() {
		return modifiyTime;
	}

	public void setModifiyTime(Timestamp modifiyTime) {
		this.modifiyTime = modifiyTime;
	}


	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

}
