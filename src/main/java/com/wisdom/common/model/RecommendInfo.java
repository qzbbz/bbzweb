package com.wisdom.common.model;


public class RecommendInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String recommender_id;
	private String ip;


	// Constructors

	/** default constructor */
	public RecommendInfo() {
	}

	/** constructor */
	public RecommendInfo(String recommender_id, String ip) {
		this.recommender_id = recommender_id;
		this.ip = ip;
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


}