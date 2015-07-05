package com.wisdom.common.model;

import java.sql.Timestamp;

public class SheetSalaryTax {

	// Fields

	private Long id;
	private Long companyId;
	private String preparedby;
	private String companyController;
	private String financeChief;
	private String tabulator;
	private Double saleryEnd;
	private Double saleryBegin;
	private Double bonusEnd;
	private Double bonusBegin;
	private Double welfareEnd;
	private Double welfareBegin;
	private Double insuranceEnd;
	private Double insuranceBegin;
	private Double fundsEnd;
	private Double fundsBegin;
	private Double sociatyEnd;
	private Double sociatyBegin;
	private Double educationEnd;
	private Double educationBegin;
	private Double noCashWelfareEnd;
	private Double noCashWelfareBegin;
	private Double dismissEnd;
	private Double dismissBegin;
	private Double otherEnd;
	private Double otherBegin;
	private Double salaryTotalEnd;
	private Double salaryTotalBegin;
	private Double valueAddedTaxEnd;
	private Double valueAddedTaxBegin;
	private Double exciseTaxEnd;
	private Double exciseTaxBegin;
	private Double businessTaxEnd;
	private Double businessTaxBegin;
	private Double urbanMaintenanceAndConstructionTaxEnd;
	private Double urbanMaintenanceAndConstructionTaxBegin;
	private Double businessIncomeTaxesEnd;
	private Double businessIncomeTaxesBegin;
	private Double resourceTaxEnd;
	private Double resourceTaxBegin;
	private Double landValueIncrementTaxEnd;
	private Double landValueIncrementTaxBegin;
	private Double urbanLandUseTaxEnd;
	private Double urbanLandUseTaxBegin;
	private Double buildingTaxesEnd;
	private Double buildingTaxesBegin;
	private Double vehicleAndVesselTaxEnd;
	private Double vehicleAndVesselTaxBegin;
	private Double attachEducationEnd;
	private Double attachEducationBegin;
	private Double mineralResourcesEnd;
	private Double mineralResourcesBegin;
	private Double sewageChargeEnd;
	private Double sewageChargeBegin;
	private Double incomeTaxForIndividualsEnd;
	private Double incomeTaxForIndividualsBegin;
	private Double taxTotalEnd;
	private Double taxTotalBegin;
	private String createTime;

	// Constructors

	

	// Property accessors

	public Long getId() {
		return this.id;
	}

	

	public Long getCompanyId() {
		return companyId;
	}



	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}



	public SheetSalaryTax(Long id, Long companyId, String preparedby,
			String companyController, String financeChief, String tabulator,
			Double saleryEnd, Double saleryBegin, Double bonusEnd,
			Double bonusBegin, Double welfareEnd, Double welfareBegin,
			Double insuranceEnd, Double insuranceBegin, Double fundsEnd,
			Double fundsBegin, Double sociatyEnd, Double sociatyBegin,
			Double educationEnd, Double educationBegin,
			Double noCashWelfareEnd, Double noCashWelfareBegin,
			Double dismissEnd, Double dismissBegin, Double otherEnd,
			Double otherBegin, Double salaryTotalEnd, Double salaryTotalBegin,
			Double valueAddedTaxEnd, Double valueAddedTaxBegin,
			Double exciseTaxEnd, Double exciseTaxBegin, Double businessTaxEnd,
			Double businessTaxBegin,
			Double urbanMaintenanceAndConstructionTaxEnd,
			Double urbanMaintenanceAndConstructionTaxBegin,
			Double businessIncomeTaxesEnd, Double businessIncomeTaxesBegin,
			Double resourceTaxEnd, Double resourceTaxBegin,
			Double landValueIncrementTaxEnd, Double landValueIncrementTaxBegin,
			Double urbanLandUseTaxEnd, Double urbanLandUseTaxBegin,
			Double buildingTaxesEnd, Double buildingTaxesBegin,
			Double vehicleAndVesselTaxEnd, Double vehicleAndVesselTaxBegin,
			Double attachEducationEnd, Double attachEducationBegin,
			Double mineralResourcesEnd, Double mineralResourcesBegin,
			Double sewageChargeEnd, Double sewageChargeBegin,
			Double incomeTaxForIndividualsEnd,
			Double incomeTaxForIndividualsBegin, Double taxTotalEnd,
			Double taxTotalBegin, String createTime) {
		super();
		this.id = id;
		this.companyId = companyId;
		this.preparedby = preparedby;
		this.companyController = companyController;
		this.financeChief = financeChief;
		this.tabulator = tabulator;
		this.saleryEnd = saleryEnd;
		this.saleryBegin = saleryBegin;
		this.bonusEnd = bonusEnd;
		this.bonusBegin = bonusBegin;
		this.welfareEnd = welfareEnd;
		this.welfareBegin = welfareBegin;
		this.insuranceEnd = insuranceEnd;
		this.insuranceBegin = insuranceBegin;
		this.fundsEnd = fundsEnd;
		this.fundsBegin = fundsBegin;
		this.sociatyEnd = sociatyEnd;
		this.sociatyBegin = sociatyBegin;
		this.educationEnd = educationEnd;
		this.educationBegin = educationBegin;
		this.noCashWelfareEnd = noCashWelfareEnd;
		this.noCashWelfareBegin = noCashWelfareBegin;
		this.dismissEnd = dismissEnd;
		this.dismissBegin = dismissBegin;
		this.otherEnd = otherEnd;
		this.otherBegin = otherBegin;
		this.salaryTotalEnd = salaryTotalEnd;
		this.salaryTotalBegin = salaryTotalBegin;
		this.valueAddedTaxEnd = valueAddedTaxEnd;
		this.valueAddedTaxBegin = valueAddedTaxBegin;
		this.exciseTaxEnd = exciseTaxEnd;
		this.exciseTaxBegin = exciseTaxBegin;
		this.businessTaxEnd = businessTaxEnd;
		this.businessTaxBegin = businessTaxBegin;
		this.urbanMaintenanceAndConstructionTaxEnd = urbanMaintenanceAndConstructionTaxEnd;
		this.urbanMaintenanceAndConstructionTaxBegin = urbanMaintenanceAndConstructionTaxBegin;
		this.businessIncomeTaxesEnd = businessIncomeTaxesEnd;
		this.businessIncomeTaxesBegin = businessIncomeTaxesBegin;
		this.resourceTaxEnd = resourceTaxEnd;
		this.resourceTaxBegin = resourceTaxBegin;
		this.landValueIncrementTaxEnd = landValueIncrementTaxEnd;
		this.landValueIncrementTaxBegin = landValueIncrementTaxBegin;
		this.urbanLandUseTaxEnd = urbanLandUseTaxEnd;
		this.urbanLandUseTaxBegin = urbanLandUseTaxBegin;
		this.buildingTaxesEnd = buildingTaxesEnd;
		this.buildingTaxesBegin = buildingTaxesBegin;
		this.vehicleAndVesselTaxEnd = vehicleAndVesselTaxEnd;
		this.vehicleAndVesselTaxBegin = vehicleAndVesselTaxBegin;
		this.attachEducationEnd = attachEducationEnd;
		this.attachEducationBegin = attachEducationBegin;
		this.mineralResourcesEnd = mineralResourcesEnd;
		this.mineralResourcesBegin = mineralResourcesBegin;
		this.sewageChargeEnd = sewageChargeEnd;
		this.sewageChargeBegin = sewageChargeBegin;
		this.incomeTaxForIndividualsEnd = incomeTaxForIndividualsEnd;
		this.incomeTaxForIndividualsBegin = incomeTaxForIndividualsBegin;
		this.taxTotalEnd = taxTotalEnd;
		this.taxTotalBegin = taxTotalBegin;
		this.createTime = createTime;
	}



	public void setId(Long id) {
		this.id = id;
	}

	public String getPreparedby() {
		return this.preparedby;
	}

	public void setPreparedby(String preparedby) {
		this.preparedby = preparedby;
	}

	public String getCompanyController() {
		return this.companyController;
	}

	public void setCompanyController(String companyController) {
		this.companyController = companyController;
	}

	public String getFinanceChief() {
		return this.financeChief;
	}

	public void setFinanceChief(String financeChief) {
		this.financeChief = financeChief;
	}

	public String getTabulator() {
		return this.tabulator;
	}

	public void setTabulator(String tabulator) {
		this.tabulator = tabulator;
	}

	public Double getSaleryEnd() {
		return this.saleryEnd;
	}

	public void setSaleryEnd(Double saleryEnd) {
		this.saleryEnd = saleryEnd;
	}

	public Double getSaleryBegin() {
		return this.saleryBegin;
	}

	public void setSaleryBegin(Double saleryBegin) {
		this.saleryBegin = saleryBegin;
	}

	public Double getBonusEnd() {
		return this.bonusEnd;
	}

	public void setBonusEnd(Double bonusEnd) {
		this.bonusEnd = bonusEnd;
	}

	public Double getBonusBegin() {
		return this.bonusBegin;
	}

	public void setBonusBegin(Double bonusBegin) {
		this.bonusBegin = bonusBegin;
	}

	public Double getWelfareEnd() {
		return this.welfareEnd;
	}

	public void setWelfareEnd(Double welfareEnd) {
		this.welfareEnd = welfareEnd;
	}

	public Double getWelfareBegin() {
		return this.welfareBegin;
	}

	public void setWelfareBegin(Double welfareBegin) {
		this.welfareBegin = welfareBegin;
	}

	public Double getInsuranceEnd() {
		return this.insuranceEnd;
	}

	public void setInsuranceEnd(Double insuranceEnd) {
		this.insuranceEnd = insuranceEnd;
	}

	public Double getInsuranceBegin() {
		return this.insuranceBegin;
	}

	public void setInsuranceBegin(Double insuranceBegin) {
		this.insuranceBegin = insuranceBegin;
	}

	public Double getFundsEnd() {
		return this.fundsEnd;
	}

	public void setFundsEnd(Double fundsEnd) {
		this.fundsEnd = fundsEnd;
	}

	public Double getFundsBegin() {
		return this.fundsBegin;
	}

	public void setFundsBegin(Double fundsBegin) {
		this.fundsBegin = fundsBegin;
	}

	public Double getSociatyEnd() {
		return this.sociatyEnd;
	}

	public void setSociatyEnd(Double sociatyEnd) {
		this.sociatyEnd = sociatyEnd;
	}

	public Double getSociatyBegin() {
		return this.sociatyBegin;
	}

	public void setSociatyBegin(Double sociatyBegin) {
		this.sociatyBegin = sociatyBegin;
	}

	public Double getEducationEnd() {
		return this.educationEnd;
	}

	public void setEducationEnd(Double educationEnd) {
		this.educationEnd = educationEnd;
	}

	public Double getEducationBegin() {
		return this.educationBegin;
	}

	public void setEducationBegin(Double educationBegin) {
		this.educationBegin = educationBegin;
	}

	public Double getNoCashWelfareEnd() {
		return this.noCashWelfareEnd;
	}

	public void setNoCashWelfareEnd(Double noCashWelfareEnd) {
		this.noCashWelfareEnd = noCashWelfareEnd;
	}

	public Double getNoCashWelfareBegin() {
		return this.noCashWelfareBegin;
	}

	public void setNoCashWelfareBegin(Double noCashWelfareBegin) {
		this.noCashWelfareBegin = noCashWelfareBegin;
	}

	public Double getDismissEnd() {
		return this.dismissEnd;
	}

	public void setDismissEnd(Double dismissEnd) {
		this.dismissEnd = dismissEnd;
	}

	public Double getDismissBegin() {
		return this.dismissBegin;
	}

	public void setDismissBegin(Double dismissBegin) {
		this.dismissBegin = dismissBegin;
	}

	public Double getOtherEnd() {
		return this.otherEnd;
	}

	public void setOtherEnd(Double otherEnd) {
		this.otherEnd = otherEnd;
	}

	public Double getOtherBegin() {
		return this.otherBegin;
	}

	public void setOtherBegin(Double otherBegin) {
		this.otherBegin = otherBegin;
	}

	public Double getSalaryTotalEnd() {
		return this.salaryTotalEnd;
	}

	public void setSalaryTotalEnd(Double salaryTotalEnd) {
		this.salaryTotalEnd = salaryTotalEnd;
	}

	public Double getSalaryTotalBegin() {
		return this.salaryTotalBegin;
	}

	public void setSalaryTotalBegin(Double salaryTotalBegin) {
		this.salaryTotalBegin = salaryTotalBegin;
	}

	public Double getValueAddedTaxEnd() {
		return this.valueAddedTaxEnd;
	}

	public void setValueAddedTaxEnd(Double valueAddedTaxEnd) {
		this.valueAddedTaxEnd = valueAddedTaxEnd;
	}

	public Double getValueAddedTaxBegin() {
		return this.valueAddedTaxBegin;
	}

	public void setValueAddedTaxBegin(Double valueAddedTaxBegin) {
		this.valueAddedTaxBegin = valueAddedTaxBegin;
	}

	public Double getExciseTaxEnd() {
		return this.exciseTaxEnd;
	}

	public void setExciseTaxEnd(Double exciseTaxEnd) {
		this.exciseTaxEnd = exciseTaxEnd;
	}

	public Double getExciseTaxBegin() {
		return this.exciseTaxBegin;
	}

	public void setExciseTaxBegin(Double exciseTaxBegin) {
		this.exciseTaxBegin = exciseTaxBegin;
	}

	public Double getBusinessTaxEnd() {
		return this.businessTaxEnd;
	}

	public void setBusinessTaxEnd(Double businessTaxEnd) {
		this.businessTaxEnd = businessTaxEnd;
	}

	public Double getBusinessTaxBegin() {
		return this.businessTaxBegin;
	}

	public void setBusinessTaxBegin(Double businessTaxBegin) {
		this.businessTaxBegin = businessTaxBegin;
	}

	public Double getUrbanMaintenanceAndConstructionTaxEnd() {
		return this.urbanMaintenanceAndConstructionTaxEnd;
	}

	public void setUrbanMaintenanceAndConstructionTaxEnd(
			Double urbanMaintenanceAndConstructionTaxEnd) {
		this.urbanMaintenanceAndConstructionTaxEnd = urbanMaintenanceAndConstructionTaxEnd;
	}

	public Double getUrbanMaintenanceAndConstructionTaxBegin() {
		return this.urbanMaintenanceAndConstructionTaxBegin;
	}

	public void setUrbanMaintenanceAndConstructionTaxBegin(
			Double urbanMaintenanceAndConstructionTaxBegin) {
		this.urbanMaintenanceAndConstructionTaxBegin = urbanMaintenanceAndConstructionTaxBegin;
	}

	public Double getBusinessIncomeTaxesEnd() {
		return this.businessIncomeTaxesEnd;
	}

	public void setBusinessIncomeTaxesEnd(Double businessIncomeTaxesEnd) {
		this.businessIncomeTaxesEnd = businessIncomeTaxesEnd;
	}

	public Double getBusinessIncomeTaxesBegin() {
		return this.businessIncomeTaxesBegin;
	}

	public void setBusinessIncomeTaxesBegin(Double businessIncomeTaxesBegin) {
		this.businessIncomeTaxesBegin = businessIncomeTaxesBegin;
	}

	public Double getResourceTaxEnd() {
		return this.resourceTaxEnd;
	}

	public void setResourceTaxEnd(Double resourceTaxEnd) {
		this.resourceTaxEnd = resourceTaxEnd;
	}

	public Double getResourceTaxBegin() {
		return this.resourceTaxBegin;
	}

	public void setResourceTaxBegin(Double resourceTaxBegin) {
		this.resourceTaxBegin = resourceTaxBegin;
	}

	public Double getLandValueIncrementTaxEnd() {
		return this.landValueIncrementTaxEnd;
	}

	public void setLandValueIncrementTaxEnd(Double landValueIncrementTaxEnd) {
		this.landValueIncrementTaxEnd = landValueIncrementTaxEnd;
	}

	public Double getLandValueIncrementTaxBegin() {
		return this.landValueIncrementTaxBegin;
	}

	public void setLandValueIncrementTaxBegin(Double landValueIncrementTaxBegin) {
		this.landValueIncrementTaxBegin = landValueIncrementTaxBegin;
	}

	public Double getUrbanLandUseTaxEnd() {
		return this.urbanLandUseTaxEnd;
	}

	public void setUrbanLandUseTaxEnd(Double urbanLandUseTaxEnd) {
		this.urbanLandUseTaxEnd = urbanLandUseTaxEnd;
	}

	public Double getUrbanLandUseTaxBegin() {
		return this.urbanLandUseTaxBegin;
	}

	public void setUrbanLandUseTaxBegin(Double urbanLandUseTaxBegin) {
		this.urbanLandUseTaxBegin = urbanLandUseTaxBegin;
	}

	public Double getBuildingTaxesEnd() {
		return this.buildingTaxesEnd;
	}

	public void setBuildingTaxesEnd(Double buildingTaxesEnd) {
		this.buildingTaxesEnd = buildingTaxesEnd;
	}

	public Double getBuildingTaxesBegin() {
		return this.buildingTaxesBegin;
	}

	public void setBuildingTaxesBegin(Double buildingTaxesBegin) {
		this.buildingTaxesBegin = buildingTaxesBegin;
	}

	public Double getVehicleAndVesselTaxEnd() {
		return this.vehicleAndVesselTaxEnd;
	}

	public void setVehicleAndVesselTaxEnd(Double vehicleAndVesselTaxEnd) {
		this.vehicleAndVesselTaxEnd = vehicleAndVesselTaxEnd;
	}

	public Double getVehicleAndVesselTaxBegin() {
		return this.vehicleAndVesselTaxBegin;
	}

	public void setVehicleAndVesselTaxBegin(Double vehicleAndVesselTaxBegin) {
		this.vehicleAndVesselTaxBegin = vehicleAndVesselTaxBegin;
	}

	public Double getAttachEducationEnd() {
		return this.attachEducationEnd;
	}

	public void setAttachEducationEnd(Double attachEducationEnd) {
		this.attachEducationEnd = attachEducationEnd;
	}

	public Double getAttachEducationBegin() {
		return this.attachEducationBegin;
	}

	public void setAttachEducationBegin(Double attachEducationBegin) {
		this.attachEducationBegin = attachEducationBegin;
	}

	public Double getMineralResourcesEnd() {
		return this.mineralResourcesEnd;
	}

	public void setMineralResourcesEnd(Double mineralResourcesEnd) {
		this.mineralResourcesEnd = mineralResourcesEnd;
	}

	public Double getMineralResourcesBegin() {
		return this.mineralResourcesBegin;
	}

	public void setMineralResourcesBegin(Double mineralResourcesBegin) {
		this.mineralResourcesBegin = mineralResourcesBegin;
	}

	public Double getSewageChargeEnd() {
		return this.sewageChargeEnd;
	}

	public void setSewageChargeEnd(Double sewageChargeEnd) {
		this.sewageChargeEnd = sewageChargeEnd;
	}

	public Double getSewageChargeBegin() {
		return this.sewageChargeBegin;
	}

	public void setSewageChargeBegin(Double sewageChargeBegin) {
		this.sewageChargeBegin = sewageChargeBegin;
	}

	public Double getIncomeTaxForIndividualsEnd() {
		return this.incomeTaxForIndividualsEnd;
	}

	public void setIncomeTaxForIndividualsEnd(Double incomeTaxForIndividualsEnd) {
		this.incomeTaxForIndividualsEnd = incomeTaxForIndividualsEnd;
	}

	public Double getIncomeTaxForIndividualsBegin() {
		return this.incomeTaxForIndividualsBegin;
	}

	public void setIncomeTaxForIndividualsBegin(
			Double incomeTaxForIndividualsBegin) {
		this.incomeTaxForIndividualsBegin = incomeTaxForIndividualsBegin;
	}

	public Double getTaxTotalEnd() {
		return this.taxTotalEnd;
	}

	public void setTaxTotalEnd(Double taxTotalEnd) {
		this.taxTotalEnd = taxTotalEnd;
	}

	public Double getTaxTotalBegin() {
		return this.taxTotalBegin;
	}

	public void setTaxTotalBegin(Double taxTotalBegin) {
		this.taxTotalBegin = taxTotalBegin;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}