package com.wisdom.common.model;

public class CompanyFixedAssets implements java.io.Serializable  {

	private Long id;
	private Long companyIid;
	private String month;
	private Integer category;
	private String name;
	private double assetsValue;
	private double expectRemainingValue;
	private Integer amortizationDepreciationMonth;
	private double remainingRate;
	private double monthDepreciationValue;
	private Integer amortizationMonth;
	private double provisionDepreciation;
	private double cumulativeClepreciation;
	private double netWorth;
	
	
	public CompanyFixedAssets() {
	}


	public CompanyFixedAssets(Long id, Long companyIid, String month, Integer category, String name, double assetsValue,
			double expectRemainingValue, Integer amortizationDepreciationMonth, double remainingRate,
			double monthDepreciationValue, Integer amortizationMonth, double provisionDepreciation,
			double cumulativeClepreciation, double netWorth) {
		this.id = id;
		this.companyIid = companyIid;
		this.month = month;
		this.category = category;
		this.name = name;
		this.assetsValue = assetsValue;
		this.expectRemainingValue = expectRemainingValue;
		this.amortizationDepreciationMonth = amortizationDepreciationMonth;
		this.remainingRate = remainingRate;
		this.monthDepreciationValue = monthDepreciationValue;
		this.amortizationMonth = amortizationMonth;
		this.provisionDepreciation = provisionDepreciation;
		this.cumulativeClepreciation = cumulativeClepreciation;
		this.netWorth = netWorth;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getCompanyIid() {
		return companyIid;
	}


	public void setCompanyIid(Long companyIid) {
		this.companyIid = companyIid;
	}


	public String getMonth() {
		return month;
	}


	public void setMonth(String month) {
		this.month = month;
	}


	public Integer getCategory() {
		return category;
	}


	public void setCategory(Integer category) {
		this.category = category;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public double getAssetsValue() {
		return assetsValue;
	}


	public void setAssetsValue(double assetsValue) {
		this.assetsValue = assetsValue;
	}


	public double getExpectRemainingValue() {
		return expectRemainingValue;
	}


	public void setExpectRemainingValue(double expectRemainingValue) {
		this.expectRemainingValue = expectRemainingValue;
	}


	public Integer getAmortizationDepreciationMonth() {
		return amortizationDepreciationMonth;
	}


	public void setAmortizationDepreciationMonth(Integer amortizationDepreciationMonth) {
		this.amortizationDepreciationMonth = amortizationDepreciationMonth;
	}


	public double getRemainingRate() {
		return remainingRate;
	}


	public void setRemainingRate(double remainingRate) {
		this.remainingRate = remainingRate;
	}


	public double getMonthDepreciationValue() {
		return monthDepreciationValue;
	}


	public void setMonthDepreciationValue(double monthDepreciationValue) {
		this.monthDepreciationValue = monthDepreciationValue;
	}


	public Integer getAmortizationMonth() {
		return amortizationMonth;
	}


	public void setAmortizationMonth(Integer amortizationMonth) {
		this.amortizationMonth = amortizationMonth;
	}


	public double getProvisionDepreciation() {
		return provisionDepreciation;
	}


	public void setProvisionDepreciation(double provisionDepreciation) {
		this.provisionDepreciation = provisionDepreciation;
	}


	public double getCumulativeClepreciation() {
		return cumulativeClepreciation;
	}


	public void setCumulativeClepreciation(double cumulativeClepreciation) {
		this.cumulativeClepreciation = cumulativeClepreciation;
	}


	public double getNetWorth() {
		return netWorth;
	}


	public void setNetWorth(double netWorth) {
		this.netWorth = netWorth;
	}
	
	
	
}
