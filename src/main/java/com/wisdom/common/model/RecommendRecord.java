package com.wisdom.common.model;

import java.sql.Timestamp;

/**
 * AccounterCertificate entity. @author MyEclipse Persistence Tools
 */

public class RecommendRecord implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String recommender_id;
	private String customer_email;
	private Timestamp created_time;


	// Constructors

	/** default constructor */
	public RecommendRecord() {
	}

	/** constructor */
	public RecommendRecord(String recommender_id, String customer_email, Timestamp created_time) {
		this.recommender_id = recommender_id;
		this.customer_email = customer_email;
		this.created_time = created_time;
	}



	// Property accessors

	public String getRecommenderId() {
		return this.recommender_id;
	}

	public void setRecommenderId(String recommender_id) {
		this.recommender_id = recommender_id;
	}

	public String getCustomerEmail() {
		return this.customer_email;
	}

	public void setCustomerEmail(String customer_email) {
		this.customer_email = customer_email;
	}
	
	public Timestamp getCreatedTime() {
		return this.created_time;
	}
	
	public void setCreatedTime(Timestamp created_time){
		this.created_time = created_time;
	}


}