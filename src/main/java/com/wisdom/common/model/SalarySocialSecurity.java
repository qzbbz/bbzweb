package com.wisdom.common.model;

import java.sql.Timestamp;

public class SalarySocialSecurity implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Long companyId;
	
	private String cityName;
	
	private Integer registryType;
	
	private String pensionCratio;
	
	private String pensionPratio;
	
	private String pensionBase;
	
	private String medicalCratio;
	
	private String medicalPratio;
	
	private String medicalBase;
	
	private String unemployCratio;
	
	private String unemployPratio;
	
	private String unemployBase;
	
	private String injuryCratio;
	
	private String injuryPratio;
	
	private String injuryBase;
	
	private String birthCratio;
	
	private String birthPratio;
	
	private String birthBase;
	
	private String accfundCratio;
	
	private String accfundPratio;
	
	private String accfundBase;
	
	private String salaryCratio;
	
	private String salaryPratio;
	
	private String salaryBase;
	
	private String bigmedicalCratio;
	
	private String bigmedicalPratio;
	
	private String bigmedicalBase;
	
	private String template;
	
	private Timestamp createTime;
	
	public SalarySocialSecurity() {}

	public SalarySocialSecurity(Long id, Long companyId, String cityName,
			Integer registryType, String pensionCratio, String pensionPratio,
			String pensionBase, String medicalCratio, String medicalPratio,
			String medicalBase, String unemployCratio, String unemployPratio,
			String unemployBase, String injuryCratio, String injuryPratio,
			String injuryBase, String birthCratio, String birthPratio,
			String birthBase, String accfundCratio, String accfundPratio,
			String accfundBase, String salaryCratio, String salaryPratio,
			String salaryBase, String bigmedicalCratio,
			String bigmedicalPratio, String bigmedicalBase, 
			String template, Timestamp createTime) {
		super();
		this.id = id;
		this.companyId = companyId;
		this.cityName = cityName;
		this.registryType = registryType;
		this.pensionCratio = pensionCratio;
		this.pensionPratio = pensionPratio;
		this.pensionBase = pensionBase;
		this.medicalCratio = medicalCratio;
		this.medicalPratio = medicalPratio;
		this.medicalBase = medicalBase;
		this.unemployCratio = unemployCratio;
		this.unemployPratio = unemployPratio;
		this.unemployBase = unemployBase;
		this.injuryCratio = injuryCratio;
		this.injuryPratio = injuryPratio;
		this.injuryBase = injuryBase;
		this.birthCratio = birthCratio;
		this.birthPratio = birthPratio;
		this.birthBase = birthBase;
		this.accfundCratio = accfundCratio;
		this.accfundPratio = accfundPratio;
		this.accfundBase = accfundBase;
		this.salaryCratio = salaryCratio;
		this.salaryPratio = salaryPratio;
		this.salaryBase = salaryBase;
		this.bigmedicalCratio = bigmedicalCratio;
		this.bigmedicalPratio = bigmedicalPratio;
		this.bigmedicalBase = bigmedicalBase;
		this.template = template;
		this.createTime = createTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Integer getRegistryType() {
		return registryType;
	}

	public void setRegistryType(Integer registryType) {
		this.registryType = registryType;
	}

	public String getPensionCratio() {
		return pensionCratio;
	}

	public void setPensionCratio(String pensionCratio) {
		this.pensionCratio = pensionCratio;
	}

	public String getPensionPratio() {
		return pensionPratio;
	}

	public void setPensionPratio(String pensionPratio) {
		this.pensionPratio = pensionPratio;
	}

	public String getPensionBase() {
		return pensionBase;
	}

	public void setPensionBase(String pensionBase) {
		this.pensionBase = pensionBase;
	}

	public String getMedicalCratio() {
		return medicalCratio;
	}

	public void setMedicalCratio(String medicalCratio) {
		this.medicalCratio = medicalCratio;
	}

	public String getMedicalPratio() {
		return medicalPratio;
	}

	public void setMedicalPratio(String medicalPratio) {
		this.medicalPratio = medicalPratio;
	}

	public String getMedicalBase() {
		return medicalBase;
	}

	public void setMedicalBase(String medicalBase) {
		this.medicalBase = medicalBase;
	}

	public String getUnemployCratio() {
		return unemployCratio;
	}

	public void setUnemployCratio(String unemployCratio) {
		this.unemployCratio = unemployCratio;
	}

	public String getUnemployPratio() {
		return unemployPratio;
	}

	public void setUnemployPratio(String unemployPratio) {
		this.unemployPratio = unemployPratio;
	}

	public String getUnemployBase() {
		return unemployBase;
	}

	public void setUnemployBase(String unemployBase) {
		this.unemployBase = unemployBase;
	}

	public String getInjuryCratio() {
		return injuryCratio;
	}

	public void setInjuryCratio(String injuryCratio) {
		this.injuryCratio = injuryCratio;
	}

	public String getInjuryPratio() {
		return injuryPratio;
	}

	public void setInjuryPratio(String injuryPratio) {
		this.injuryPratio = injuryPratio;
	}

	public String getInjuryBase() {
		return injuryBase;
	}

	public void setInjuryBase(String injuryBase) {
		this.injuryBase = injuryBase;
	}

	public String getBirthCratio() {
		return birthCratio;
	}

	public void setBirthCratio(String birthCratio) {
		this.birthCratio = birthCratio;
	}

	public String getBirthPratio() {
		return birthPratio;
	}

	public void setBirthPratio(String birthPratio) {
		this.birthPratio = birthPratio;
	}

	public String getBirthBase() {
		return birthBase;
	}

	public void setBirthBase(String birthBase) {
		this.birthBase = birthBase;
	}

	public String getAccfundCratio() {
		return accfundCratio;
	}

	public void setAccfundCratio(String accfundCratio) {
		this.accfundCratio = accfundCratio;
	}

	public String getAccfundPratio() {
		return accfundPratio;
	}

	public void setAccfundPratio(String accfundPratio) {
		this.accfundPratio = accfundPratio;
	}

	public String getAccfundBase() {
		return accfundBase;
	}

	public void setAccfundBase(String accfundBase) {
		this.accfundBase = accfundBase;
	}

	public String getSalaryCratio() {
		return salaryCratio;
	}

	public void setSalaryCratio(String salaryCratio) {
		this.salaryCratio = salaryCratio;
	}

	public String getSalaryPratio() {
		return salaryPratio;
	}

	public void setSalaryPratio(String salaryPratio) {
		this.salaryPratio = salaryPratio;
	}

	public String getSalaryBase() {
		return salaryBase;
	}

	public void setSalaryBase(String salaryBase) {
		this.salaryBase = salaryBase;
	}

	public String getBigmedicalCratio() {
		return bigmedicalCratio;
	}

	public void setBigmedicalCratio(String bigmedicalCratio) {
		this.bigmedicalCratio = bigmedicalCratio;
	}

	public String getBigmedicalPratio() {
		return bigmedicalPratio;
	}

	public void setBigmedicalPratio(String bigmedicalPratio) {
		this.bigmedicalPratio = bigmedicalPratio;
	}

	public String getBigmedicalBase() {
		return bigmedicalBase;
	}

	public void setBigmedicalBase(String bigmedicalBase) {
		this.bigmedicalBase = bigmedicalBase;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "SalarySocialSecurity [id=" + id + ", companyId=" + companyId
				+ ", cityName=" + cityName + ", registryType=" + registryType
				+ ", pensionCratio=" + pensionCratio + ", pensionPratio="
				+ pensionPratio + ", pensionBase=" + pensionBase
				+ ", medicalCratio=" + medicalCratio + ", medicalPratio="
				+ medicalPratio + ", medicalBase=" + medicalBase
				+ ", unemployCratio=" + unemployCratio + ", unemployPratio="
				+ unemployPratio + ", unemployBase=" + unemployBase
				+ ", injuryCratio=" + injuryCratio + ", injuryPratio="
				+ injuryPratio + ", injuryBase=" + injuryBase
				+ ", birthCratio=" + birthCratio + ", birthPratio="
				+ birthPratio + ", birthBase=" + birthBase + ", accfundCratio="
				+ accfundCratio + ", accfundPratio=" + accfundPratio
				+ ", accfundBase=" + accfundBase + ", salaryCratio="
				+ salaryCratio + ", salaryPratio=" + salaryPratio
				+ ", salaryBase=" + salaryBase + ", bigmedicalCratio="
				+ bigmedicalCratio + ", bigmedicalPratio=" + bigmedicalPratio
				+ ", bigmedicalBase=" + bigmedicalBase + ", template="
				+ template + ", createTime=" + createTime + "]";
	}

}
