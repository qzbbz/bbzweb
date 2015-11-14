package com.wisdom.common.model;

import java.sql.Timestamp;

public class RecommendInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String recommender_id;
	private String ip;
	private Timestamp createdTime;


	// Constructors

	/** default constructor */
	public RecommendInfo() {
	}

	/** constructor */
	public RecommendInfo(String recommender_id, String ip, Timestamp createdTime) {
		this.recommender_id = recommender_id;
		this.ip = ip;
		this.createdTime = createdTime;
	}



	// Property accessors

	public String getRecommenderId() {
		return this.recommender_id;
	}

	public void setId(String recommender_id) {
		this.recommender_id = recommender_id;
	}

	public String getIP() {
		return this.ip;
	}

	public void setIP(String ip) {
		this.ip = ip;
	}
	
	public Timestamp getCreatedTime(){
		return this.createdTime;
	}
	
	public void setCreatedTime(Timestamp createdTime){
		this.createdTime = createdTime;
		
	}


}