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
		
		private int comment_count;
		
		private String taobao_accounter;

		

		public CustomerManagement(String companyName, String createTinme, String taxStatus, Timestamp expiredTime,
				String accounterName, int comment_count, String taobao_accounter) {
			super();
			this.companyName = companyName;
			this.createTinme = createTinme;
			this.taxStatus = taxStatus;
			this.expiredTime = expiredTime;
			this.accounterName = accounterName;
			this.comment_count = comment_count;
			this.taobao_accounter = taobao_accounter;
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


		public int getComment_count() {
			return comment_count;
		}


		public void setComment_count(int comment_count) {
			this.comment_count = comment_count;
		}


		public String getTaobao_accounter() {
			return taobao_accounter;
		}


		public void setTaobao_accounter(String taobao_accounter) {
			this.taobao_accounter = taobao_accounter;
		}
		
		
		
}
