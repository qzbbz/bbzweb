package com.wisdom.common.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class CustomerTaoBao implements Serializable{
	
	// Fields

		private static final long serialVersionUID = 1L;
		
		private long id;
		
		private long companyId;
		
		private String fileName;
		
		private String taobaoAccounter;
		
		private Timestamp createTime;
		
		public CustomerTaoBao(){}
		
		public CustomerTaoBao(long id, long companyId, String fileName, String taobaoAccounter, Timestamp createTime) {
			this.id = id;
			this.companyId = companyId;
			this.fileName = fileName;
			this.taobaoAccounter = taobaoAccounter;
			this.createTime = createTime;
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public long getCompanyId() {
			return companyId;
		}


		public void setCompanyId(long companyId) {
			this.companyId = companyId;
		}


		public String getFileName() {
			return fileName;
		}


		public void setFileName(String fileName) {
			this.fileName = fileName;
		}


		public String getTaobaoAccounter() {
			return taobaoAccounter;
		}


		public void setTaobaoAccounter(String taobaoAccounter) {
			this.taobaoAccounter = taobaoAccounter;
		}


		public Timestamp getCreateTime() {
			return createTime;
		}


		public void setCreateTime(Timestamp createTime) {
			this.createTime = createTime;
		}


		public static long getSerialversionuid() {
			return serialVersionUID;
		}
}
