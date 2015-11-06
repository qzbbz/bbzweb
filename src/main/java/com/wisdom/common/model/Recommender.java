package com.wisdom.common.model;



/**
 * AccounterCertificate entity. @author MyEclipse Persistence Tools
 */

public class Recommender implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;


	// Constructors

	/** default constructor */
	public Recommender() {
	}

	/** constructor */
	public Recommender(String id, String name) {
		this.id = id;
		this.name = name;
	}



	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


}