package com.wisdom.weixin.utils;

import java.io.Serializable;

public class UploadBillEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	
	private String img;
	
	private String time;
	
	private String amount;
	
	private String expenseTypeName;
	
	private String expenseTypeId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getExpenseTypeName() {
		return expenseTypeName;
	}

	public void setExpenseTypeName(String expenseTypeName) {
		this.expenseTypeName = expenseTypeName;
	}

	public String getExpenseTypeId() {
		return expenseTypeId;
	}

	public void setExpenseTypeId(String expenseTypeId) {
		this.expenseTypeId = expenseTypeId;
	}

	public UploadBillEntity(String id, String img, String time, String amount,
			String expenseTypeName, String expenseTypeId) {
		super();
		this.id = id;
		this.img = img;
		this.time = time;
		this.amount = amount;
		this.expenseTypeName = expenseTypeName;
		this.expenseTypeId = expenseTypeId;
	}

	@Override
	public String toString() {
		return "UploadBillEntity [id=" + id + ", img=" + img + ", time=" + time
				+ ", amount=" + amount + ", expenseTypeName=" + expenseTypeName
				+ ", expenseTypeId=" + expenseTypeId + "]";
	}
	
	public UploadBillEntity() {}
	
}
