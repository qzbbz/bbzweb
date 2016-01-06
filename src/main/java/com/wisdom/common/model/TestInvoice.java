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
	private String status;
	private String costCenter;
	private String type;
	private Integer generated;
	private String comment;
	// Constructors


	/** default constructor */
	public TestInvoice() {
	}
	
	/** minimal constructor */
	public TestInvoice(Long id) {
		this.id = id;
	}

	public TestInvoice(Long id, Long companyId,
			String fileName, String billDate, Integer isFixedAssets, Timestamp createTime, Timestamp modifyTime, String itemId, String status, String costCenter, String type, Integer generated, String comment) {
		super();
		this.id = id;
		this.companyId = companyId;
		this.fileName = fileName;
		this.billDate = billDate;
		this.isFixedAssets = isFixedAssets;
		this.createTime = createTime;
		this.modifiyTime = modifyTime;
		this.itemId = itemId;
		this.status = status;
		this.costCenter = costCenter;
		this.type = type;
		this.generated = generated;
		this.comment = comment;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getGenerated() {
		return generated;
	}

	public void setGenerated(Integer generated) {
		this.generated = generated;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	

}
