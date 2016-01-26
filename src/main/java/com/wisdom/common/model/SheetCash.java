package com.wisdom.common.model;

import java.sql.Timestamp;

public class SheetCash {

	// Fields

	private Long id;
	private Long companyId;
	private String preparedby;
	private String companyController;
	private String financeChief;
	private String tabulator;
	private Double salesYear;
	private Double salesMonth;
	private Double receiveYear;
	private Double receiveMonth;
	private Double buyYear;
	private Double buyMonth;
	private Double salaryYear;
	private Double salaryMonth;
	private Double taxYear;
	private Double taxMonth;
	private Double otherPayYear;
	private Double otherPayMonth;
	private Double operatingActivitiesYear;
	private Double operatingActivitiesMonth;
	private Double takeBackYear;
	private Double takeBackMonth;
	private Double equityEarningsYear;
	private Double equityEarningsMonth;
	private Double handleYear;
	private Double handleMonth;
	private Double investmentsPayYear;
	private Double investmentsPayMonth;
	private Double coustructionYear;
	private Double coustructionMonth;
	private Double activityInvestmentYear;
	private Double activityInvestmentMonth;
	private Double loanYear;
	private Double loanMonth;
	private Double investorsToInvestYear;
	private Double investorsToInvestMonth;
	private Double paymentOfPrincipalYear;
	private Double paymentOfPrincipalMonth;
	private Double paidInterestYear;
	private Double paidInterestMonth;
	private Double distributeProfitYear;
	private Double distributeProfitMonth;
	private Double financingActivityYear;
	private Double financingActivityMonth;
	private Double netIncreaseInCashYear;
	private Double netIncreaseInCashMonth;
	private Double cashAtBeginningOfPeriodYear;
	private Double cashAtBeginningOfPeriodMonth;
	private Double endingCashBalanceYear;
	private Double endingCashBalanceMonth;
	private String createTime;

	// Constructors


    public SheetCash() {
        super();
    }


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



	public SheetCash(Long id, Long companyId, String preparedby,
			String companyController, String financeChief, String tabulator,
			Double salesYear, Double salesMonth, Double receiveYear,
			Double receiveMonth, Double buyYear, Double buyMonth,
			Double salaryYear, Double salaryMonth, Double taxYear,
			Double taxMonth, Double otherPayYear, Double otherPayMonth,
			Double operatingActivitiesYear, Double operatingActivitiesMonth,
			Double takeBackYear, Double takeBackMonth,
			Double equityEarningsYear, Double equityEarningsMonth,
			Double handleYear, Double handleMonth, Double investmentsPayYear,
			Double investmentsPayMonth, Double coustructionYear,
			Double coustructionMonth, Double activityInvestmentYear,
			Double activityInvestmentMonth, Double loanYear, Double loanMonth,
			Double investorsToInvestYear, Double investorsToInvestMonth,
			Double paymentOfPrincipalYear, Double paymentOfPrincipalMonth,
			Double paidInterestYear, Double paidInterestMonth,
			Double distributeProfitYear, Double distributeProfitMonth,
			Double financingActivityYear, Double financingActivityMonth,
			Double netIncreaseInCashYear, Double netIncreaseInCashMonth,
			Double cashAtBeginningOfPeriodYear,
			Double cashAtBeginningOfPeriodMonth, Double endingCashBalanceYear,
			Double endingCashBalanceMonth, String createTime) {
		super();
		this.id = id;
		this.companyId = companyId;
		this.preparedby = preparedby;
		this.companyController = companyController;
		this.financeChief = financeChief;
		this.tabulator = tabulator;
		this.salesYear = salesYear;
		this.salesMonth = salesMonth;
		this.receiveYear = receiveYear;
		this.receiveMonth = receiveMonth;
		this.buyYear = buyYear;
		this.buyMonth = buyMonth;
		this.salaryYear = salaryYear;
		this.salaryMonth = salaryMonth;
		this.taxYear = taxYear;
		this.taxMonth = taxMonth;
		this.otherPayYear = otherPayYear;
		this.otherPayMonth = otherPayMonth;
		this.operatingActivitiesYear = operatingActivitiesYear;
		this.operatingActivitiesMonth = operatingActivitiesMonth;
		this.takeBackYear = takeBackYear;
		this.takeBackMonth = takeBackMonth;
		this.equityEarningsYear = equityEarningsYear;
		this.equityEarningsMonth = equityEarningsMonth;
		this.handleYear = handleYear;
		this.handleMonth = handleMonth;
		this.investmentsPayYear = investmentsPayYear;
		this.investmentsPayMonth = investmentsPayMonth;
		this.coustructionYear = coustructionYear;
		this.coustructionMonth = coustructionMonth;
		this.activityInvestmentYear = activityInvestmentYear;
		this.activityInvestmentMonth = activityInvestmentMonth;
		this.loanYear = loanYear;
		this.loanMonth = loanMonth;
		this.investorsToInvestYear = investorsToInvestYear;
		this.investorsToInvestMonth = investorsToInvestMonth;
		this.paymentOfPrincipalYear = paymentOfPrincipalYear;
		this.paymentOfPrincipalMonth = paymentOfPrincipalMonth;
		this.paidInterestYear = paidInterestYear;
		this.paidInterestMonth = paidInterestMonth;
		this.distributeProfitYear = distributeProfitYear;
		this.distributeProfitMonth = distributeProfitMonth;
		this.financingActivityYear = financingActivityYear;
		this.financingActivityMonth = financingActivityMonth;
		this.netIncreaseInCashYear = netIncreaseInCashYear;
		this.netIncreaseInCashMonth = netIncreaseInCashMonth;
		this.cashAtBeginningOfPeriodYear = cashAtBeginningOfPeriodYear;
		this.cashAtBeginningOfPeriodMonth = cashAtBeginningOfPeriodMonth;
		this.endingCashBalanceYear = endingCashBalanceYear;
		this.endingCashBalanceMonth = endingCashBalanceMonth;
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

	public Double getSalesYear() {
		return this.salesYear;
	}

	public void setSalesYear(Double salesYear) {
		this.salesYear = salesYear;
	}

	public Double getSalesMonth() {
		return this.salesMonth;
	}

	public void setSalesMonth(Double salesMonth) {
		this.salesMonth = salesMonth;
	}

	public Double getReceiveYear() {
		return this.receiveYear;
	}

	public void setReceiveYear(Double receiveYear) {
		this.receiveYear = receiveYear;
	}

	public Double getReceiveMonth() {
		return this.receiveMonth;
	}

	public void setReceiveMonth(Double receiveMonth) {
		this.receiveMonth = receiveMonth;
	}

	public Double getBuyYear() {
		return this.buyYear;
	}

	public void setBuyYear(Double buyYear) {
		this.buyYear = buyYear;
	}

	public Double getBuyMonth() {
		return this.buyMonth;
	}

	public void setBuyMonth(Double buyMonth) {
		this.buyMonth = buyMonth;
	}

	public Double getSalaryYear() {
		return this.salaryYear;
	}

	public void setSalaryYear(Double salaryYear) {
		this.salaryYear = salaryYear;
	}

	public Double getSalaryMonth() {
		return this.salaryMonth;
	}

	public void setSalaryMonth(Double salaryMonth) {
		this.salaryMonth = salaryMonth;
	}

	public Double getTaxYear() {
		return this.taxYear;
	}

	public void setTaxYear(Double taxYear) {
		this.taxYear = taxYear;
	}

	public Double getTaxMonth() {
		return this.taxMonth;
	}

	public void setTaxMonth(Double taxMonth) {
		this.taxMonth = taxMonth;
	}

	public Double getOtherPayYear() {
		return this.otherPayYear;
	}

	public void setOtherPayYear(Double otherPayYear) {
		this.otherPayYear = otherPayYear;
	}

	public Double getOtherPayMonth() {
		return this.otherPayMonth;
	}

	public void setOtherPayMonth(Double otherPayMonth) {
		this.otherPayMonth = otherPayMonth;
	}

	public Double getOperatingActivitiesYear() {
		return this.operatingActivitiesYear;
	}

	public void setOperatingActivitiesYear(Double operatingActivitiesYear) {
		this.operatingActivitiesYear = operatingActivitiesYear;
	}

	public Double getOperatingActivitiesMonth() {
		return this.operatingActivitiesMonth;
	}

	public void setOperatingActivitiesMonth(Double operatingActivitiesMonth) {
		this.operatingActivitiesMonth = operatingActivitiesMonth;
	}

	public Double getTakeBackYear() {
		return this.takeBackYear;
	}

	public void setTakeBackYear(Double takeBackYear) {
		this.takeBackYear = takeBackYear;
	}

	public Double getTakeBackMonth() {
		return this.takeBackMonth;
	}

	public void setTakeBackMonth(Double takeBackMonth) {
		this.takeBackMonth = takeBackMonth;
	}

	public Double getEquityEarningsYear() {
		return this.equityEarningsYear;
	}

	public void setEquityEarningsYear(Double equityEarningsYear) {
		this.equityEarningsYear = equityEarningsYear;
	}

	public Double getEquityEarningsMonth() {
		return this.equityEarningsMonth;
	}

	public void setEquityEarningsMonth(Double equityEarningsMonth) {
		this.equityEarningsMonth = equityEarningsMonth;
	}

	public Double getHandleYear() {
		return this.handleYear;
	}

	public void setHandleYear(Double handleYear) {
		this.handleYear = handleYear;
	}

	public Double getHandleMonth() {
		return this.handleMonth;
	}

	public void setHandleMonth(Double handleMonth) {
		this.handleMonth = handleMonth;
	}

	public Double getInvestmentsPayYear() {
		return this.investmentsPayYear;
	}

	public void setInvestmentsPayYear(Double investmentsPayYear) {
		this.investmentsPayYear = investmentsPayYear;
	}

	public Double getInvestmentsPayMonth() {
		return this.investmentsPayMonth;
	}

	public void setInvestmentsPayMonth(Double investmentsPayMonth) {
		this.investmentsPayMonth = investmentsPayMonth;
	}

	public Double getCoustructionYear() {
		return this.coustructionYear;
	}

	public void setCoustructionYear(Double coustructionYear) {
		this.coustructionYear = coustructionYear;
	}

	public Double getCoustructionMonth() {
		return this.coustructionMonth;
	}

	public void setCoustructionMonth(Double coustructionMonth) {
		this.coustructionMonth = coustructionMonth;
	}

	public Double getActivityInvestmentYear() {
		return this.activityInvestmentYear;
	}

	public void setActivityInvestmentYear(Double activityInvestmentYear) {
		this.activityInvestmentYear = activityInvestmentYear;
	}

	public Double getActivityInvestmentMonth() {
		return this.activityInvestmentMonth;
	}

	public void setActivityInvestmentMonth(Double activityInvestmentMonth) {
		this.activityInvestmentMonth = activityInvestmentMonth;
	}

	public Double getLoanYear() {
		return this.loanYear;
	}

	public void setLoanYear(Double loanYear) {
		this.loanYear = loanYear;
	}

	public Double getLoanMonth() {
		return this.loanMonth;
	}

	public void setLoanMonth(Double loanMonth) {
		this.loanMonth = loanMonth;
	}

	public Double getInvestorsToInvestYear() {
		return this.investorsToInvestYear;
	}

	public void setInvestorsToInvestYear(Double investorsToInvestYear) {
		this.investorsToInvestYear = investorsToInvestYear;
	}

	public Double getInvestorsToInvestMonth() {
		return this.investorsToInvestMonth;
	}

	public void setInvestorsToInvestMonth(Double investorsToInvestMonth) {
		this.investorsToInvestMonth = investorsToInvestMonth;
	}

	public Double getPaymentOfPrincipalYear() {
		return this.paymentOfPrincipalYear;
	}

	public void setPaymentOfPrincipalYear(Double paymentOfPrincipalYear) {
		this.paymentOfPrincipalYear = paymentOfPrincipalYear;
	}

	public Double getPaymentOfPrincipalMonth() {
		return this.paymentOfPrincipalMonth;
	}

	public void setPaymentOfPrincipalMonth(Double paymentOfPrincipalMonth) {
		this.paymentOfPrincipalMonth = paymentOfPrincipalMonth;
	}

	public Double getPaidInterestYear() {
		return this.paidInterestYear;
	}

	public void setPaidInterestYear(Double paidInterestYear) {
		this.paidInterestYear = paidInterestYear;
	}

	public Double getPaidInterestMonth() {
		return this.paidInterestMonth;
	}

	public void setPaidInterestMonth(Double paidInterestMonth) {
		this.paidInterestMonth = paidInterestMonth;
	}

	public Double getDistributeProfitYear() {
		return this.distributeProfitYear;
	}

	public void setDistributeProfitYear(Double distributeProfitYear) {
		this.distributeProfitYear = distributeProfitYear;
	}

	public Double getDistributeProfitMonth() {
		return this.distributeProfitMonth;
	}

	public void setDistributeProfitMonth(Double distributeProfitMonth) {
		this.distributeProfitMonth = distributeProfitMonth;
	}

	public Double getFinancingActivityYear() {
		return this.financingActivityYear;
	}

	public void setFinancingActivityYear(Double financingActivityYear) {
		this.financingActivityYear = financingActivityYear;
	}

	public Double getFinancingActivityMonth() {
		return this.financingActivityMonth;
	}

	public void setFinancingActivityMonth(Double financingActivityMonth) {
		this.financingActivityMonth = financingActivityMonth;
	}

	public Double getNetIncreaseInCashYear() {
		return this.netIncreaseInCashYear;
	}

	public void setNetIncreaseInCashYear(Double netIncreaseInCashYear) {
		this.netIncreaseInCashYear = netIncreaseInCashYear;
	}

	public Double getNetIncreaseInCashMonth() {
		return this.netIncreaseInCashMonth;
	}

	public void setNetIncreaseInCashMonth(Double netIncreaseInCashMonth) {
		this.netIncreaseInCashMonth = netIncreaseInCashMonth;
	}

	public Double getCashAtBeginningOfPeriodYear() {
		return this.cashAtBeginningOfPeriodYear;
	}

	public void setCashAtBeginningOfPeriodYear(
			Double cashAtBeginningOfPeriodYear) {
		this.cashAtBeginningOfPeriodYear = cashAtBeginningOfPeriodYear;
	}

	public Double getCashAtBeginningOfPeriodMonth() {
		return this.cashAtBeginningOfPeriodMonth;
	}

	public void setCashAtBeginningOfPeriodMonth(
			Double cashAtBeginningOfPeriodMonth) {
		this.cashAtBeginningOfPeriodMonth = cashAtBeginningOfPeriodMonth;
	}

	public Double getEndingCashBalanceYear() {
		return this.endingCashBalanceYear;
	}

	public void setEndingCashBalanceYear(Double endingCashBalanceYear) {
		this.endingCashBalanceYear = endingCashBalanceYear;
	}

	public Double getEndingCashBalanceMonth() {
		return this.endingCashBalanceMonth;
	}

	public void setEndingCashBalanceMonth(Double endingCashBalanceMonth) {
		this.endingCashBalanceMonth = endingCashBalanceMonth;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}