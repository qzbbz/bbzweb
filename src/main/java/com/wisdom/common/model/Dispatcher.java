package com.wisdom.common.model;

import java.sql.Timestamp;

/**
 * Dispatcher entity. @author MyEclipse Persistence Tools
 */

public class Dispatcher implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String userId;
	private String userName;
	private Long objectTypeId;
	private Long invoiceId;
	private Integer channelTypeId;
	private Integer status;
	private String reciever;
	private String openId;
	private Timestamp createTime;
	private Timestamp updateTime;

	// Constructors

	/** default constructor */
	public Dispatcher() {
	}

	/** minimal constructor */
	public Dispatcher(Long id, Long objectTypeId, Long invoiceId,
			Integer channelTypeId, Integer status, String reciever) {
		this.id = id;
		this.objectTypeId = objectTypeId;
		this.invoiceId = invoiceId;
		this.channelTypeId = channelTypeId;
		this.status = status;
		this.reciever = reciever;
	}

	/** full constructor */
	public Dispatcher(Long id,String userId, Long objectTypeId, Long invoiceId,
			Integer channelTypeId, Integer status, String reciever,String openId,
			Timestamp createTime, Timestamp updateTime) {
		this.id = id;
		this.userId = userId;
		this.objectTypeId = objectTypeId;
		this.invoiceId = invoiceId;
		this.channelTypeId = channelTypeId;
		this.status = status;
		this.reciever = reciever;
		this.openId = openId;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getObjectTypeId() {
		return this.objectTypeId;
	}

	public void setObjectTypeId(Long objectTypeId) {
		this.objectTypeId = objectTypeId;
	}

	public Long getInvoiceId() {
		return this.invoiceId;
	}

	public void setInvoiceId(Long objectId) {
		this.invoiceId = objectId;
	}

	public Integer getChannelTypeId() {
		return this.channelTypeId;
	}

	public void setChannelTypeId(Integer channelTypeId) {
		this.channelTypeId = channelTypeId;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getReciever() {
		return this.reciever;
	}

	public void setReciever(String reciever) {
		this.reciever = reciever;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}