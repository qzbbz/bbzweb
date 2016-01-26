package com.wisdom.common.model;

import java.sql.Timestamp;

	public class TestInvoiceArtifact implements java.io.Serializable {

		// Fields

		private static final long serialVersionUID = 1L;
		private long id;
		private long invoiceId;
		private double amount;
		private String type;
		private String supplierName;
		private String itemId;
		private Timestamp createdTime;
		private double tax;
		private Integer number;

		// Constructors


		/** default constructor */
		public TestInvoiceArtifact() {
		}
		
		/** minimal constructor */
		public TestInvoiceArtifact(long id) {
			this.id = id;
		}

		public TestInvoiceArtifact(long id, long invoiceId,
				double amount, double tax, String type, String supplierName, String itemId, Timestamp createdTime, Integer number) {
			super();
			this.id = id;
			this.invoiceId = invoiceId;
			this.amount = amount;
			this.tax = tax;
			this.type = type;
			this.supplierName = supplierName;
			this.itemId = itemId;
			this.number = number;
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public long getInvoiceId() {
			return invoiceId;
		}

		public void setInvoiceId(long invoiceId) {
			this.invoiceId = invoiceId;
		}

		public double getAmount() {
			return amount;
		}

		public void setAmount(double amount) {
			this.amount = amount;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getSupplierName() {
			return supplierName;
		}

		public void setSupplierName(String supplierName) {
			this.supplierName = supplierName;
		}

		public String getItemId() {
			return itemId;
		}

		public void setItemId(String itemId) {
			this.itemId = itemId;
		}

		public Timestamp getCreatedTime() {
			return createdTime;
		}

		public void setCreatedTime(Timestamp createdTime) {
			this.createdTime = createdTime;
		}

		public double getTax() {
			return tax;
		}

		public void setTax(double tax) {
			this.tax = tax;
		}

		public Integer getNumber() {
			return number;
		}

		public void setNumber(Integer number) {
			this.number = number;
		}
		
		
		
	}