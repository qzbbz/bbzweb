package com.wisdom.common.model;

import java.sql.Timestamp;

public class TestInvoiceRecord  implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private long invoiceId;
	private long companyId;
	private String type;
	private double amount;
	private Timestamp createdTime;
	private Integer isFa;
	private String billDate;
	private String supplierName;
	private String fileName;
	
	
	public TestInvoiceRecord(long invoiceId, long companyId, String type, double amount, Timestamp createdTime, Integer isFa, String billDate, String supplierName, String fileName){
		super();
		this.invoiceId = invoiceId;
		this.companyId = companyId;
		this.type = type;
		this.amount = amount;
		this.createdTime = createdTime;
		this.isFa = isFa;
		this.billDate = billDate;
		this.supplierName = supplierName;
		this.fileName = fileName;
	}
	
	public long getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(long invoiceId) {
		this.invoiceId = invoiceId;
	}
	public long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Timestamp getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}
	public Integer getIsFa() {
		return isFa;
	}
	public void setIsFa(Integer isFa) {
		this.isFa = isFa;
	}
	public String getBillDate() {
		return billDate;
	}
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	

}
