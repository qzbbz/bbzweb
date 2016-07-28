package com.wisdom.common.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class CustomerManagement implements Serializable{
	
	// Fields

		private static final long serialVersionUID = 1L;
		
		private String companyName;
		
		private String createTinme;
		
		private String taxStatus;
		
		private Timestamp expiredTime;
		
		private String accounterName;

		

		public CustomerManagement(String companyName, String createTinme, String taxStatus, Timestamp expiredTime,
				String accounterName) {
			super();
			this.companyName = companyName;
			this.createTinme = createTinme;
			this.taxStatus = taxStatus;
			this.expiredTime = expiredTime;
			this.accounterName = accounterName;
		}

		
		public Timestamp getExpiredTime() {
			return expiredTime;
		}


		public void setExpiredTime(Timestamp expiredTime) {
			this.expiredTime = expiredTime;
		}


		public String getCompanyName() {
			return companyName;
		}

		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}

		public String getCreateTinme() {
			return createTinme;
		}

		public void setCreateTinme(String createTinme) {
			this.createTinme = createTinme;
		}

		public String getTaxStatus() {
			return taxStatus;
		}

		public void setTaxStatus(String taxStatus) {
			this.taxStatus = taxStatus;
		}


		public String getAccounterName() {
			return accounterName;
		}

		public void setAccounterName(String accounterName) {
			this.accounterName = accounterName;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		
		
		
}
