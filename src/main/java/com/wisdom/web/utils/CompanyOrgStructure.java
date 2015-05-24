package com.wisdom.web.utils;

import java.util.ArrayList;
import java.util.List;

public class CompanyOrgStructure {

	private String text;
	
	private String id;
	
	private String companyId;
	
	private String parentCompanyId;
	
	private String costCenter;
	
	private boolean expanded;
	
	private boolean typeIsDept;
	
	private boolean typeIsSubCompany;
	
	private List<CompanyOrgStructure> children;

	public CompanyOrgStructure(){
		this.children = new ArrayList<>();
		typeIsDept = false;
		typeIsSubCompany = false;
	}
	
	public CompanyOrgStructure(String text, String id, boolean expanded,
			String costCenter, String companyId, boolean typeIsDept,
			boolean typeIsSubCompany,
			String parentCompanyId, List<CompanyOrgStructure> children) {
		super();
		this.text = text;
		this.id = id;
		this.expanded = expanded;
		this.children = children;
		this.costCenter = costCenter;
		this.companyId = companyId;
		this.parentCompanyId = parentCompanyId;
		this.typeIsDept = typeIsDept;
		this.typeIsSubCompany = typeIsSubCompany;
	}

	public boolean isTypeIsSubCompany() {
		return typeIsSubCompany;
	}

	public void setTypeIsSubCompany(boolean typeIsSubCompany) {
		this.typeIsSubCompany = typeIsSubCompany;
	}

	public boolean isTypeIsDept() {
		return typeIsDept;
	}

	public void setTypeIsDept(boolean typeIsDept) {
		this.typeIsDept = typeIsDept;
	}

	public String getParentCompanyId() {
		return parentCompanyId;
	}

	public void setParentCompanyId(String parentCompanyId) {
		this.parentCompanyId = parentCompanyId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	public List<CompanyOrgStructure> getChildren() {
		return children;
	}

	public void setChildren(List<CompanyOrgStructure> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "CompanyOrgStructure [text=" + text + ", id=" + id
				+ ", companyId=" + companyId + ", parentCompanyId="
				+ parentCompanyId + ", costCenter=" + costCenter
				+ ", expanded=" + expanded + ", typeIsDept=" + typeIsDept
				+ ", typeIsSubCompany=" + typeIsSubCompany + ", children="
				+ children + "]";
	}
	
}
