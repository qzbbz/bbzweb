package com.wisdom.common.model;

import java.sql.Timestamp;

public class SalesComment implements java.io.Serializable{
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer salesId;
    private String comment;
    private Timestamp updatedTime;



 
    public SalesComment() {
        super();
    }
 
    public SalesComment(Integer id, Integer salesId, String comment, Timestamp updatedTime) {
        super();
        this.id = id;
        this.salesId = salesId;
        this.comment = comment;
        this.updatedTime = updatedTime;

    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSalesId() {
		return salesId;
	}

	public void setSalesId(Integer salesId) {
		this.salesId = salesId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Timestamp getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Timestamp updatedTime) {
		this.updatedTime = updatedTime;
	}

	
	
	
 

 
   
}
