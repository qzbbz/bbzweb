package com.wisdom.common.model;

import java.sql.Timestamp;

public class SheetBalance {

	// Fields

	private Long id;
	private Long companyId;
	private String expiryDate;
	private String preparedby;
	private String companyController;
	private String financeChief;
	private String tabulator;
	private Double monetaryResourcesEndingBalance;
	private Double monetaryResourcesBeginningBalance;
	private Double shortTermInvestmentsEndingBalance;
	private Double shortTermInvestmentsBeginningBalance;
	private Double billReceivableEndingBalance;
	private Double billReceivableBeginningBalance;
	private Double accountsReceivableEndingBalance;
	private Double accountsReceivableBeginningBalance;
	private Double prepaymentEndingBalance;
	private Double prepaymentBeginningBalance;
	private Double dividendsReceivableEndingBalance;
	private Double dividendsReceivableBeginningBalance;
	private Double interestReceivableEndingBalance;
	private Double interestReceivableBeginningBalance;
	private Double otherReceivablesEndingBalance;
	private Double otherReceivablesBeginningBalance;
	private Double stockEndingBalance;
	private Double stockBeginningBalance;
	private Double stockRawMaterialsEndingBalance;
	private Double stockRawMaterialsBeginningBalance;
	private Double stockProductEndingBalance;
	private Double stockProductBeginningBalance;
	private Double stockMerchandiseInventoryEndingBalance;
	private Double stockMerchandiseInventoryBeginningBalance;
	private Double stockRevolvingMaterialsEndingBalance;
	private Double stockRevolvingMaterialsBeginningBalance;
	private Double otherCurrentAssetsEndingBalance;
	private Double otherCurrentAssetsBeginningBalance;
	private Double totalCurrentAssetsEndingBalance;
	private Double totalCurrentAssetsBeginningBalance;
	private Double longTermInvestmentsInBondsEndingBalance;
	private Double longTermInvestmentsInBondsBeginningBalance;
	private Double longTermInvestmentOnStocksEndingBalance;
	private Double longTermInvestmentOnStocksBeginningBalance;
	private Double fixedAssetsOriginalCostEndingBalance;
	private Double fixedAssetsOriginalCostBeginningBalance;
	private Double accumulatedDepreciationEndingBalance;
	private Double accumulatedDepreciationBeginningBalance;
	private Double bookValueOfFixedAssetsEndingBalance;
	private Double bookValueOfFixedAssetsBeginningBalance;
	private Double constructionInProcessEndingBalance;
	private Double constructionInProcessBeginningBalance;
	private Double constructionMaterialsEndingBalance;
	private Double constructionMaterialsBeginningBalance;
	private Double removalOfFixedAssetsEndingBalance;
	private Double removalOfFixedAssetsBeginningBalance;
	private Double biologicalAssetsEndingBalance;
	private Double biologicalAssetsBeginningBalance;
	private Double intangibleAssetsEndingBalance;
	private Double intangibleAssetsBeginningBalance;
	private Double developmentExpenditureEndingBalance;
	private Double developmentExpenditureBeginningBalance;
	private Double longTermPrepaidExpensesEndingBalance;
	private Double longTermPrepaidExpensesBeginningBalance;
	private Double otherNonCurrentAssetsEndingBalance;
	private Double otherNonCurrentAssetsBeginningBalance;
	private Double totalNonCurrentAssetsEndingBalance;
	private Double totalNonCurrentAssetsBeginningBalance;
	private Double totalAssetsEndingBalance;
	private Double totalAssetsBeginningBalance;
	private Double shortLoanEndingBalance;
	private Double shortLoanBeginningBalance;
	private Double notesPayableEndingBalance;
	private Double notesPayableBeginningBalance;
	private Double payableAccountEndingBalance;
	private Double payableAccountBeginningBalance;
	private Double depositReceivedEndingBalance;
	private Double depositReceivedBeginningBalance;
	private Double employeeBenefitsPayableEndingBalance;
	private Double employeeBenefitsPayableBeginningBalance;
	private Double taxesPayableEndingBalance;
	private Double taxesPayableBeginningBalance;
	private Double interestPayableEndingBalance;
	private Double interestPayableBeginningBalance;
	private Double profitPayableEndingBalance;
	private Double profitPayableBeginningBalance;
	private Double accountsPayableOthersEndingBalance;
	private Double accountsPayableOthersBeginningBalance;
	private Double otherCurrentLiabilitiesEndingBalance;
	private Double otherCurrentLiabilitiesBeginningBalance;
	private Double totalCurrentLiabilitiesEndingBalance;
	private Double totalCurrentLiabilitiesBeginningBalance;
	private Double longLoanEndingBalance;
	private Double longLoanBeginningBalance;
	private Double longTermPayablesEndingBalance;
	private Double longTermPayablesBeginningBalance;
	private Double deferredIncomeEndingBalance;
	private Double deferredIncomeBeginningBalance;
	private Double otherNonCurrentLiabilitiesEndingBalance;
	private Double otherNonCurrentLiabilitiesBeginningBalance;
	private Double totalNonCurrentLiabilitiesEndingBalance;
	private Double totalNonCurrentLiabilitiesBeginningBalance;
	private Double totalLiabilitiesEndingBalance;
	private Double totalLiabilitiesBeginningBalance;
	private Double paidInCapitalEndingBalance;
	private Double paidInCapitalBeginningBalance;
	private Double capitalReserveEndingBalance;
	private Double capitalReserveBeginningBalance;
	private Double earnedSurplusEndingBalance;
	private Double earnedSurplusBeginningBalance;
	private Double undistributedProfitEndingBalance;
	private Double undistributedProfitBeginningBalance;
	private Double totalEquitiesEndingBalance;
	private Double totalEquitiesBeginningBalance;
	private Double totalLiabilitiesAndOwnerEquityEndingBalance;
	private Double totalLiabilitiesAndOwnerEquityBeginningBalance;
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



	public SheetBalance(Long id, Long companyId, String expiryDate,
			String preparedby, String companyController, String financeChief,
			String tabulator, Double monetaryResourcesEndingBalance,
			Double monetaryResourcesBeginningBalance,
			Double shortTermInvestmentsEndingBalance,
			Double shortTermInvestmentsBeginningBalance,
			Double billReceivableEndingBalance,
			Double billReceivableBeginningBalance,
			Double accountsReceivableEndingBalance,
			Double accountsReceivableBeginningBalance,
			Double prepaymentEndingBalance, Double prepaymentBeginningBalance,
			Double dividendsReceivableEndingBalance,
			Double dividendsReceivableBeginningBalance,
			Double interestReceivableEndingBalance,
			Double interestReceivableBeginningBalance,
			Double otherReceivablesEndingBalance,
			Double otherReceivablesBeginningBalance, Double stockEndingBalance,
			Double stockBeginningBalance,
			Double stockRawMaterialsEndingBalance,
			Double stockRawMaterialsBeginningBalance,
			Double stockProductEndingBalance,
			Double stockProductBeginningBalance,
			Double stockMerchandiseInventoryEndingBalance,
			Double stockMerchandiseInventoryBeginningBalance,
			Double stockRevolvingMaterialsEndingBalance,
			Double stockRevolvingMaterialsBeginningBalance,
			Double otherCurrentAssetsEndingBalance,
			Double otherCurrentAssetsBeginningBalance,
			Double totalCurrentAssetsEndingBalance,
			Double totalCurrentAssetsBeginningBalance,
			Double longTermInvestmentsInBondsEndingBalance,
			Double longTermInvestmentsInBondsBeginningBalance,
			Double longTermInvestmentOnStocksEndingBalance,
			Double longTermInvestmentOnStocksBeginningBalance,
			Double fixedAssetsOriginalCostEndingBalance,
			Double fixedAssetsOriginalCostBeginningBalance,
			Double accumulatedDepreciationEndingBalance,
			Double accumulatedDepreciationBeginningBalance,
			Double bookValueOfFixedAssetsEndingBalance,
			Double bookValueOfFixedAssetsBeginningBalance,
			Double constructionInProcessEndingBalance,
			Double constructionInProcessBeginningBalance,
			Double constructionMaterialsEndingBalance,
			Double constructionMaterialsBeginningBalance,
			Double removalOfFixedAssetsEndingBalance,
			Double removalOfFixedAssetsBeginningBalance,
			Double biologicalAssetsEndingBalance,
			Double biologicalAssetsBeginningBalance,
			Double intangibleAssetsEndingBalance,
			Double intangibleAssetsBeginningBalance,
			Double developmentExpenditureEndingBalance,
			Double developmentExpenditureBeginningBalance,
			Double longTermPrepaidExpensesEndingBalance,
			Double longTermPrepaidExpensesBeginningBalance,
			Double otherNonCurrentAssetsEndingBalance,
			Double otherNonCurrentAssetsBeginningBalance,
			Double totalNonCurrentAssetsEndingBalance,
			Double totalNonCurrentAssetsBeginningBalance,
			Double totalAssetsEndingBalance,
			Double totalAssetsBeginningBalance, Double shortLoanEndingBalance,
			Double shortLoanBeginningBalance, Double notesPayableEndingBalance,
			Double notesPayableBeginningBalance,
			Double payableAccountEndingBalance,
			Double payableAccountBeginningBalance,
			Double depositReceivedEndingBalance,
			Double depositReceivedBeginningBalance,
			Double employeeBenefitsPayableEndingBalance,
			Double employeeBenefitsPayableBeginningBalance,
			Double taxesPayableEndingBalance,
			Double taxesPayableBeginningBalance,
			Double interestPayableEndingBalance,
			Double interestPayableBeginningBalance,
			Double profitPayableEndingBalance,
			Double profitPayableBeginningBalance,
			Double accountsPayableOthersEndingBalance,
			Double accountsPayableOthersBeginningBalance,
			Double otherCurrentLiabilitiesEndingBalance,
			Double otherCurrentLiabilitiesBeginningBalance,
			Double totalCurrentLiabilitiesEndingBalance,
			Double totalCurrentLiabilitiesBeginningBalance,
			Double longLoanEndingBalance, Double longLoanBeginningBalance,
			Double longTermPayablesEndingBalance,
			Double longTermPayablesBeginningBalance,
			Double deferredIncomeEndingBalance,
			Double deferredIncomeBeginningBalance,
			Double otherNonCurrentLiabilitiesEndingBalance,
			Double otherNonCurrentLiabilitiesBeginningBalance,
			Double totalNonCurrentLiabilitiesEndingBalance,
			Double totalNonCurrentLiabilitiesBeginningBalance,
			Double totalLiabilitiesEndingBalance,
			Double totalLiabilitiesBeginningBalance,
			Double paidInCapitalEndingBalance,
			Double paidInCapitalBeginningBalance,
			Double capitalReserveEndingBalance,
			Double capitalReserveBeginningBalance,
			Double earnedSurplusEndingBalance,
			Double earnedSurplusBeginningBalance,
			Double undistributedProfitEndingBalance,
			Double undistributedProfitBeginningBalance,
			Double totalEquitiesEndingBalance,
			Double totalEquitiesBeginningBalance,
			Double totalLiabilitiesAndOwnerEquityEndingBalance,
			Double totalLiabilitiesAndOwnerEquityBeginningBalance,
			String createTime) {
		super();
		this.id = id;
		this.companyId = companyId;
		this.expiryDate = expiryDate;
		this.preparedby = preparedby;
		this.companyController = companyController;
		this.financeChief = financeChief;
		this.tabulator = tabulator;
		this.monetaryResourcesEndingBalance = monetaryResourcesEndingBalance;
		this.monetaryResourcesBeginningBalance = monetaryResourcesBeginningBalance;
		this.shortTermInvestmentsEndingBalance = shortTermInvestmentsEndingBalance;
		this.shortTermInvestmentsBeginningBalance = shortTermInvestmentsBeginningBalance;
		this.billReceivableEndingBalance = billReceivableEndingBalance;
		this.billReceivableBeginningBalance = billReceivableBeginningBalance;
		this.accountsReceivableEndingBalance = accountsReceivableEndingBalance;
		this.accountsReceivableBeginningBalance = accountsReceivableBeginningBalance;
		this.prepaymentEndingBalance = prepaymentEndingBalance;
		this.prepaymentBeginningBalance = prepaymentBeginningBalance;
		this.dividendsReceivableEndingBalance = dividendsReceivableEndingBalance;
		this.dividendsReceivableBeginningBalance = dividendsReceivableBeginningBalance;
		this.interestReceivableEndingBalance = interestReceivableEndingBalance;
		this.interestReceivableBeginningBalance = interestReceivableBeginningBalance;
		this.otherReceivablesEndingBalance = otherReceivablesEndingBalance;
		this.otherReceivablesBeginningBalance = otherReceivablesBeginningBalance;
		this.stockEndingBalance = stockEndingBalance;
		this.stockBeginningBalance = stockBeginningBalance;
		this.stockRawMaterialsEndingBalance = stockRawMaterialsEndingBalance;
		this.stockRawMaterialsBeginningBalance = stockRawMaterialsBeginningBalance;
		this.stockProductEndingBalance = stockProductEndingBalance;
		this.stockProductBeginningBalance = stockProductBeginningBalance;
		this.stockMerchandiseInventoryEndingBalance = stockMerchandiseInventoryEndingBalance;
		this.stockMerchandiseInventoryBeginningBalance = stockMerchandiseInventoryBeginningBalance;
		this.stockRevolvingMaterialsEndingBalance = stockRevolvingMaterialsEndingBalance;
		this.stockRevolvingMaterialsBeginningBalance = stockRevolvingMaterialsBeginningBalance;
		this.otherCurrentAssetsEndingBalance = otherCurrentAssetsEndingBalance;
		this.otherCurrentAssetsBeginningBalance = otherCurrentAssetsBeginningBalance;
		this.totalCurrentAssetsEndingBalance = totalCurrentAssetsEndingBalance;
		this.totalCurrentAssetsBeginningBalance = totalCurrentAssetsBeginningBalance;
		this.longTermInvestmentsInBondsEndingBalance = longTermInvestmentsInBondsEndingBalance;
		this.longTermInvestmentsInBondsBeginningBalance = longTermInvestmentsInBondsBeginningBalance;
		this.longTermInvestmentOnStocksEndingBalance = longTermInvestmentOnStocksEndingBalance;
		this.longTermInvestmentOnStocksBeginningBalance = longTermInvestmentOnStocksBeginningBalance;
		this.fixedAssetsOriginalCostEndingBalance = fixedAssetsOriginalCostEndingBalance;
		this.fixedAssetsOriginalCostBeginningBalance = fixedAssetsOriginalCostBeginningBalance;
		this.accumulatedDepreciationEndingBalance = accumulatedDepreciationEndingBalance;
		this.accumulatedDepreciationBeginningBalance = accumulatedDepreciationBeginningBalance;
		this.bookValueOfFixedAssetsEndingBalance = bookValueOfFixedAssetsEndingBalance;
		this.bookValueOfFixedAssetsBeginningBalance = bookValueOfFixedAssetsBeginningBalance;
		this.constructionInProcessEndingBalance = constructionInProcessEndingBalance;
		this.constructionInProcessBeginningBalance = constructionInProcessBeginningBalance;
		this.constructionMaterialsEndingBalance = constructionMaterialsEndingBalance;
		this.constructionMaterialsBeginningBalance = constructionMaterialsBeginningBalance;
		this.removalOfFixedAssetsEndingBalance = removalOfFixedAssetsEndingBalance;
		this.removalOfFixedAssetsBeginningBalance = removalOfFixedAssetsBeginningBalance;
		this.biologicalAssetsEndingBalance = biologicalAssetsEndingBalance;
		this.biologicalAssetsBeginningBalance = biologicalAssetsBeginningBalance;
		this.intangibleAssetsEndingBalance = intangibleAssetsEndingBalance;
		this.intangibleAssetsBeginningBalance = intangibleAssetsBeginningBalance;
		this.developmentExpenditureEndingBalance = developmentExpenditureEndingBalance;
		this.developmentExpenditureBeginningBalance = developmentExpenditureBeginningBalance;
		this.longTermPrepaidExpensesEndingBalance = longTermPrepaidExpensesEndingBalance;
		this.longTermPrepaidExpensesBeginningBalance = longTermPrepaidExpensesBeginningBalance;
		this.otherNonCurrentAssetsEndingBalance = otherNonCurrentAssetsEndingBalance;
		this.otherNonCurrentAssetsBeginningBalance = otherNonCurrentAssetsBeginningBalance;
		this.totalNonCurrentAssetsEndingBalance = totalNonCurrentAssetsEndingBalance;
		this.totalNonCurrentAssetsBeginningBalance = totalNonCurrentAssetsBeginningBalance;
		this.totalAssetsEndingBalance = totalAssetsEndingBalance;
		this.totalAssetsBeginningBalance = totalAssetsBeginningBalance;
		this.shortLoanEndingBalance = shortLoanEndingBalance;
		this.shortLoanBeginningBalance = shortLoanBeginningBalance;
		this.notesPayableEndingBalance = notesPayableEndingBalance;
		this.notesPayableBeginningBalance = notesPayableBeginningBalance;
		this.payableAccountEndingBalance = payableAccountEndingBalance;
		this.payableAccountBeginningBalance = payableAccountBeginningBalance;
		this.depositReceivedEndingBalance = depositReceivedEndingBalance;
		this.depositReceivedBeginningBalance = depositReceivedBeginningBalance;
		this.employeeBenefitsPayableEndingBalance = employeeBenefitsPayableEndingBalance;
		this.employeeBenefitsPayableBeginningBalance = employeeBenefitsPayableBeginningBalance;
		this.taxesPayableEndingBalance = taxesPayableEndingBalance;
		this.taxesPayableBeginningBalance = taxesPayableBeginningBalance;
		this.interestPayableEndingBalance = interestPayableEndingBalance;
		this.interestPayableBeginningBalance = interestPayableBeginningBalance;
		this.profitPayableEndingBalance = profitPayableEndingBalance;
		this.profitPayableBeginningBalance = profitPayableBeginningBalance;
		this.accountsPayableOthersEndingBalance = accountsPayableOthersEndingBalance;
		this.accountsPayableOthersBeginningBalance = accountsPayableOthersBeginningBalance;
		this.otherCurrentLiabilitiesEndingBalance = otherCurrentLiabilitiesEndingBalance;
		this.otherCurrentLiabilitiesBeginningBalance = otherCurrentLiabilitiesBeginningBalance;
		this.totalCurrentLiabilitiesEndingBalance = totalCurrentLiabilitiesEndingBalance;
		this.totalCurrentLiabilitiesBeginningBalance = totalCurrentLiabilitiesBeginningBalance;
		this.longLoanEndingBalance = longLoanEndingBalance;
		this.longLoanBeginningBalance = longLoanBeginningBalance;
		this.longTermPayablesEndingBalance = longTermPayablesEndingBalance;
		this.longTermPayablesBeginningBalance = longTermPayablesBeginningBalance;
		this.deferredIncomeEndingBalance = deferredIncomeEndingBalance;
		this.deferredIncomeBeginningBalance = deferredIncomeBeginningBalance;
		this.otherNonCurrentLiabilitiesEndingBalance = otherNonCurrentLiabilitiesEndingBalance;
		this.otherNonCurrentLiabilitiesBeginningBalance = otherNonCurrentLiabilitiesBeginningBalance;
		this.totalNonCurrentLiabilitiesEndingBalance = totalNonCurrentLiabilitiesEndingBalance;
		this.totalNonCurrentLiabilitiesBeginningBalance = totalNonCurrentLiabilitiesBeginningBalance;
		this.totalLiabilitiesEndingBalance = totalLiabilitiesEndingBalance;
		this.totalLiabilitiesBeginningBalance = totalLiabilitiesBeginningBalance;
		this.paidInCapitalEndingBalance = paidInCapitalEndingBalance;
		this.paidInCapitalBeginningBalance = paidInCapitalBeginningBalance;
		this.capitalReserveEndingBalance = capitalReserveEndingBalance;
		this.capitalReserveBeginningBalance = capitalReserveBeginningBalance;
		this.earnedSurplusEndingBalance = earnedSurplusEndingBalance;
		this.earnedSurplusBeginningBalance = earnedSurplusBeginningBalance;
		this.undistributedProfitEndingBalance = undistributedProfitEndingBalance;
		this.undistributedProfitBeginningBalance = undistributedProfitBeginningBalance;
		this.totalEquitiesEndingBalance = totalEquitiesEndingBalance;
		this.totalEquitiesBeginningBalance = totalEquitiesBeginningBalance;
		this.totalLiabilitiesAndOwnerEquityEndingBalance = totalLiabilitiesAndOwnerEquityEndingBalance;
		this.totalLiabilitiesAndOwnerEquityBeginningBalance = totalLiabilitiesAndOwnerEquityBeginningBalance;
		this.createTime = createTime;
	}



	public void setId(Long id) {
		this.id = id;
	}

	public String getExpiryDate() {
		return this.expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
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

	public Double getMonetaryResourcesEndingBalance() {
		return this.monetaryResourcesEndingBalance;
	}

	public void setMonetaryResourcesEndingBalance(
			Double monetaryResourcesEndingBalance) {
		this.monetaryResourcesEndingBalance = monetaryResourcesEndingBalance;
	}

	public Double getMonetaryResourcesBeginningBalance() {
		return this.monetaryResourcesBeginningBalance;
	}

	public void setMonetaryResourcesBeginningBalance(
			Double monetaryResourcesBeginningBalance) {
		this.monetaryResourcesBeginningBalance = monetaryResourcesBeginningBalance;
	}

	public Double getShortTermInvestmentsEndingBalance() {
		return this.shortTermInvestmentsEndingBalance;
	}

	public void setShortTermInvestmentsEndingBalance(
			Double shortTermInvestmentsEndingBalance) {
		this.shortTermInvestmentsEndingBalance = shortTermInvestmentsEndingBalance;
	}

	public Double getShortTermInvestmentsBeginningBalance() {
		return this.shortTermInvestmentsBeginningBalance;
	}

	public void setShortTermInvestmentsBeginningBalance(
			Double shortTermInvestmentsBeginningBalance) {
		this.shortTermInvestmentsBeginningBalance = shortTermInvestmentsBeginningBalance;
	}

	public Double getBillReceivableEndingBalance() {
		return this.billReceivableEndingBalance;
	}

	public void setBillReceivableEndingBalance(
			Double billReceivableEndingBalance) {
		this.billReceivableEndingBalance = billReceivableEndingBalance;
	}

	public Double getBillReceivableBeginningBalance() {
		return this.billReceivableBeginningBalance;
	}

	public void setBillReceivableBeginningBalance(
			Double billReceivableBeginningBalance) {
		this.billReceivableBeginningBalance = billReceivableBeginningBalance;
	}

	public Double getAccountsReceivableEndingBalance() {
		return this.accountsReceivableEndingBalance;
	}

	public void setAccountsReceivableEndingBalance(
			Double accountsReceivableEndingBalance) {
		this.accountsReceivableEndingBalance = accountsReceivableEndingBalance;
	}

	public Double getAccountsReceivableBeginningBalance() {
		return this.accountsReceivableBeginningBalance;
	}

	public void setAccountsReceivableBeginningBalance(
			Double accountsReceivableBeginningBalance) {
		this.accountsReceivableBeginningBalance = accountsReceivableBeginningBalance;
	}

	public Double getPrepaymentEndingBalance() {
		return this.prepaymentEndingBalance;
	}

	public void setPrepaymentEndingBalance(Double prepaymentEndingBalance) {
		this.prepaymentEndingBalance = prepaymentEndingBalance;
	}

	public Double getPrepaymentBeginningBalance() {
		return this.prepaymentBeginningBalance;
	}

	public void setPrepaymentBeginningBalance(Double prepaymentBeginningBalance) {
		this.prepaymentBeginningBalance = prepaymentBeginningBalance;
	}

	public Double getDividendsReceivableEndingBalance() {
		return this.dividendsReceivableEndingBalance;
	}

	public void setDividendsReceivableEndingBalance(
			Double dividendsReceivableEndingBalance) {
		this.dividendsReceivableEndingBalance = dividendsReceivableEndingBalance;
	}

	public Double getDividendsReceivableBeginningBalance() {
		return this.dividendsReceivableBeginningBalance;
	}

	public void setDividendsReceivableBeginningBalance(
			Double dividendsReceivableBeginningBalance) {
		this.dividendsReceivableBeginningBalance = dividendsReceivableBeginningBalance;
	}

	public Double getInterestReceivableEndingBalance() {
		return this.interestReceivableEndingBalance;
	}

	public void setInterestReceivableEndingBalance(
			Double interestReceivableEndingBalance) {
		this.interestReceivableEndingBalance = interestReceivableEndingBalance;
	}

	public Double getInterestReceivableBeginningBalance() {
		return this.interestReceivableBeginningBalance;
	}

	public void setInterestReceivableBeginningBalance(
			Double interestReceivableBeginningBalance) {
		this.interestReceivableBeginningBalance = interestReceivableBeginningBalance;
	}

	public Double getOtherReceivablesEndingBalance() {
		return this.otherReceivablesEndingBalance;
	}

	public void setOtherReceivablesEndingBalance(
			Double otherReceivablesEndingBalance) {
		this.otherReceivablesEndingBalance = otherReceivablesEndingBalance;
	}

	public Double getOtherReceivablesBeginningBalance() {
		return this.otherReceivablesBeginningBalance;
	}

	public void setOtherReceivablesBeginningBalance(
			Double otherReceivablesBeginningBalance) {
		this.otherReceivablesBeginningBalance = otherReceivablesBeginningBalance;
	}

	public Double getStockEndingBalance() {
		return this.stockEndingBalance;
	}

	public void setStockEndingBalance(Double stockEndingBalance) {
		this.stockEndingBalance = stockEndingBalance;
	}

	public Double getStockBeginningBalance() {
		return this.stockBeginningBalance;
	}

	public void setStockBeginningBalance(Double stockBeginningBalance) {
		this.stockBeginningBalance = stockBeginningBalance;
	}

	public Double getStockRawMaterialsEndingBalance() {
		return this.stockRawMaterialsEndingBalance;
	}

	public void setStockRawMaterialsEndingBalance(
			Double stockRawMaterialsEndingBalance) {
		this.stockRawMaterialsEndingBalance = stockRawMaterialsEndingBalance;
	}

	public Double getStockRawMaterialsBeginningBalance() {
		return this.stockRawMaterialsBeginningBalance;
	}

	public void setStockRawMaterialsBeginningBalance(
			Double stockRawMaterialsBeginningBalance) {
		this.stockRawMaterialsBeginningBalance = stockRawMaterialsBeginningBalance;
	}

	public Double getStockProductEndingBalance() {
		return this.stockProductEndingBalance;
	}

	public void setStockProductEndingBalance(Double stockProductEndingBalance) {
		this.stockProductEndingBalance = stockProductEndingBalance;
	}

	public Double getStockProductBeginningBalance() {
		return this.stockProductBeginningBalance;
	}

	public void setStockProductBeginningBalance(
			Double stockProductBeginningBalance) {
		this.stockProductBeginningBalance = stockProductBeginningBalance;
	}

	public Double getStockMerchandiseInventoryEndingBalance() {
		return this.stockMerchandiseInventoryEndingBalance;
	}

	public void setStockMerchandiseInventoryEndingBalance(
			Double stockMerchandiseInventoryEndingBalance) {
		this.stockMerchandiseInventoryEndingBalance = stockMerchandiseInventoryEndingBalance;
	}

	public Double getStockMerchandiseInventoryBeginningBalance() {
		return this.stockMerchandiseInventoryBeginningBalance;
	}

	public void setStockMerchandiseInventoryBeginningBalance(
			Double stockMerchandiseInventoryBeginningBalance) {
		this.stockMerchandiseInventoryBeginningBalance = stockMerchandiseInventoryBeginningBalance;
	}

	public Double getStockRevolvingMaterialsEndingBalance() {
		return this.stockRevolvingMaterialsEndingBalance;
	}

	public void setStockRevolvingMaterialsEndingBalance(
			Double stockRevolvingMaterialsEndingBalance) {
		this.stockRevolvingMaterialsEndingBalance = stockRevolvingMaterialsEndingBalance;
	}

	public Double getStockRevolvingMaterialsBeginningBalance() {
		return this.stockRevolvingMaterialsBeginningBalance;
	}

	public void setStockRevolvingMaterialsBeginningBalance(
			Double stockRevolvingMaterialsBeginningBalance) {
		this.stockRevolvingMaterialsBeginningBalance = stockRevolvingMaterialsBeginningBalance;
	}

	public Double getOtherCurrentAssetsEndingBalance() {
		return this.otherCurrentAssetsEndingBalance;
	}

	public void setOtherCurrentAssetsEndingBalance(
			Double otherCurrentAssetsEndingBalance) {
		this.otherCurrentAssetsEndingBalance = otherCurrentAssetsEndingBalance;
	}

	public Double getOtherCurrentAssetsBeginningBalance() {
		return this.otherCurrentAssetsBeginningBalance;
	}

	public void setOtherCurrentAssetsBeginningBalance(
			Double otherCurrentAssetsBeginningBalance) {
		this.otherCurrentAssetsBeginningBalance = otherCurrentAssetsBeginningBalance;
	}

	public Double getTotalCurrentAssetsEndingBalance() {
		return this.totalCurrentAssetsEndingBalance;
	}

	public void setTotalCurrentAssetsEndingBalance(
			Double totalCurrentAssetsEndingBalance) {
		this.totalCurrentAssetsEndingBalance = totalCurrentAssetsEndingBalance;
	}

	public Double getTotalCurrentAssetsBeginningBalance() {
		return this.totalCurrentAssetsBeginningBalance;
	}

	public void setTotalCurrentAssetsBeginningBalance(
			Double totalCurrentAssetsBeginningBalance) {
		this.totalCurrentAssetsBeginningBalance = totalCurrentAssetsBeginningBalance;
	}

	public Double getLongTermInvestmentsInBondsEndingBalance() {
		return this.longTermInvestmentsInBondsEndingBalance;
	}

	public void setLongTermInvestmentsInBondsEndingBalance(
			Double longTermInvestmentsInBondsEndingBalance) {
		this.longTermInvestmentsInBondsEndingBalance = longTermInvestmentsInBondsEndingBalance;
	}

	public Double getLongTermInvestmentsInBondsBeginningBalance() {
		return this.longTermInvestmentsInBondsBeginningBalance;
	}

	public void setLongTermInvestmentsInBondsBeginningBalance(
			Double longTermInvestmentsInBondsBeginningBalance) {
		this.longTermInvestmentsInBondsBeginningBalance = longTermInvestmentsInBondsBeginningBalance;
	}

	public Double getLongTermInvestmentOnStocksEndingBalance() {
		return this.longTermInvestmentOnStocksEndingBalance;
	}

	public void setLongTermInvestmentOnStocksEndingBalance(
			Double longTermInvestmentOnStocksEndingBalance) {
		this.longTermInvestmentOnStocksEndingBalance = longTermInvestmentOnStocksEndingBalance;
	}

	public Double getLongTermInvestmentOnStocksBeginningBalance() {
		return this.longTermInvestmentOnStocksBeginningBalance;
	}

	public void setLongTermInvestmentOnStocksBeginningBalance(
			Double longTermInvestmentOnStocksBeginningBalance) {
		this.longTermInvestmentOnStocksBeginningBalance = longTermInvestmentOnStocksBeginningBalance;
	}

	public Double getFixedAssetsOriginalCostEndingBalance() {
		return this.fixedAssetsOriginalCostEndingBalance;
	}

	public void setFixedAssetsOriginalCostEndingBalance(
			Double fixedAssetsOriginalCostEndingBalance) {
		this.fixedAssetsOriginalCostEndingBalance = fixedAssetsOriginalCostEndingBalance;
	}

	public Double getFixedAssetsOriginalCostBeginningBalance() {
		return this.fixedAssetsOriginalCostBeginningBalance;
	}

	public void setFixedAssetsOriginalCostBeginningBalance(
			Double fixedAssetsOriginalCostBeginningBalance) {
		this.fixedAssetsOriginalCostBeginningBalance = fixedAssetsOriginalCostBeginningBalance;
	}

	public Double getAccumulatedDepreciationEndingBalance() {
		return this.accumulatedDepreciationEndingBalance;
	}

	public void setAccumulatedDepreciationEndingBalance(
			Double accumulatedDepreciationEndingBalance) {
		this.accumulatedDepreciationEndingBalance = accumulatedDepreciationEndingBalance;
	}

	public Double getAccumulatedDepreciationBeginningBalance() {
		return this.accumulatedDepreciationBeginningBalance;
	}

	public void setAccumulatedDepreciationBeginningBalance(
			Double accumulatedDepreciationBeginningBalance) {
		this.accumulatedDepreciationBeginningBalance = accumulatedDepreciationBeginningBalance;
	}

	public Double getBookValueOfFixedAssetsEndingBalance() {
		return this.bookValueOfFixedAssetsEndingBalance;
	}

	public void setBookValueOfFixedAssetsEndingBalance(
			Double bookValueOfFixedAssetsEndingBalance) {
		this.bookValueOfFixedAssetsEndingBalance = bookValueOfFixedAssetsEndingBalance;
	}

	public Double getBookValueOfFixedAssetsBeginningBalance() {
		return this.bookValueOfFixedAssetsBeginningBalance;
	}

	public void setBookValueOfFixedAssetsBeginningBalance(
			Double bookValueOfFixedAssetsBeginningBalance) {
		this.bookValueOfFixedAssetsBeginningBalance = bookValueOfFixedAssetsBeginningBalance;
	}

	public Double getConstructionInProcessEndingBalance() {
		return this.constructionInProcessEndingBalance;
	}

	public void setConstructionInProcessEndingBalance(
			Double constructionInProcessEndingBalance) {
		this.constructionInProcessEndingBalance = constructionInProcessEndingBalance;
	}

	public Double getConstructionInProcessBeginningBalance() {
		return this.constructionInProcessBeginningBalance;
	}

	public void setConstructionInProcessBeginningBalance(
			Double constructionInProcessBeginningBalance) {
		this.constructionInProcessBeginningBalance = constructionInProcessBeginningBalance;
	}

	public Double getConstructionMaterialsEndingBalance() {
		return this.constructionMaterialsEndingBalance;
	}

	public void setConstructionMaterialsEndingBalance(
			Double constructionMaterialsEndingBalance) {
		this.constructionMaterialsEndingBalance = constructionMaterialsEndingBalance;
	}

	public Double getConstructionMaterialsBeginningBalance() {
		return this.constructionMaterialsBeginningBalance;
	}

	public void setConstructionMaterialsBeginningBalance(
			Double constructionMaterialsBeginningBalance) {
		this.constructionMaterialsBeginningBalance = constructionMaterialsBeginningBalance;
	}

	public Double getRemovalOfFixedAssetsEndingBalance() {
		return this.removalOfFixedAssetsEndingBalance;
	}

	public void setRemovalOfFixedAssetsEndingBalance(
			Double removalOfFixedAssetsEndingBalance) {
		this.removalOfFixedAssetsEndingBalance = removalOfFixedAssetsEndingBalance;
	}

	public Double getRemovalOfFixedAssetsBeginningBalance() {
		return this.removalOfFixedAssetsBeginningBalance;
	}

	public void setRemovalOfFixedAssetsBeginningBalance(
			Double removalOfFixedAssetsBeginningBalance) {
		this.removalOfFixedAssetsBeginningBalance = removalOfFixedAssetsBeginningBalance;
	}

	public Double getBiologicalAssetsEndingBalance() {
		return this.biologicalAssetsEndingBalance;
	}

	public void setBiologicalAssetsEndingBalance(
			Double biologicalAssetsEndingBalance) {
		this.biologicalAssetsEndingBalance = biologicalAssetsEndingBalance;
	}

	public Double getBiologicalAssetsBeginningBalance() {
		return this.biologicalAssetsBeginningBalance;
	}

	public void setBiologicalAssetsBeginningBalance(
			Double biologicalAssetsBeginningBalance) {
		this.biologicalAssetsBeginningBalance = biologicalAssetsBeginningBalance;
	}

	public Double getIntangibleAssetsEndingBalance() {
		return this.intangibleAssetsEndingBalance;
	}

	public void setIntangibleAssetsEndingBalance(
			Double intangibleAssetsEndingBalance) {
		this.intangibleAssetsEndingBalance = intangibleAssetsEndingBalance;
	}

	public Double getIntangibleAssetsBeginningBalance() {
		return this.intangibleAssetsBeginningBalance;
	}

	public void setIntangibleAssetsBeginningBalance(
			Double intangibleAssetsBeginningBalance) {
		this.intangibleAssetsBeginningBalance = intangibleAssetsBeginningBalance;
	}

	public Double getDevelopmentExpenditureEndingBalance() {
		return this.developmentExpenditureEndingBalance;
	}

	public void setDevelopmentExpenditureEndingBalance(
			Double developmentExpenditureEndingBalance) {
		this.developmentExpenditureEndingBalance = developmentExpenditureEndingBalance;
	}

	public Double getDevelopmentExpenditureBeginningBalance() {
		return this.developmentExpenditureBeginningBalance;
	}

	public void setDevelopmentExpenditureBeginningBalance(
			Double developmentExpenditureBeginningBalance) {
		this.developmentExpenditureBeginningBalance = developmentExpenditureBeginningBalance;
	}

	public Double getLongTermPrepaidExpensesEndingBalance() {
		return this.longTermPrepaidExpensesEndingBalance;
	}

	public void setLongTermPrepaidExpensesEndingBalance(
			Double longTermPrepaidExpensesEndingBalance) {
		this.longTermPrepaidExpensesEndingBalance = longTermPrepaidExpensesEndingBalance;
	}

	public Double getLongTermPrepaidExpensesBeginningBalance() {
		return this.longTermPrepaidExpensesBeginningBalance;
	}

	public void setLongTermPrepaidExpensesBeginningBalance(
			Double longTermPrepaidExpensesBeginningBalance) {
		this.longTermPrepaidExpensesBeginningBalance = longTermPrepaidExpensesBeginningBalance;
	}

	public Double getOtherNonCurrentAssetsEndingBalance() {
		return this.otherNonCurrentAssetsEndingBalance;
	}

	public void setOtherNonCurrentAssetsEndingBalance(
			Double otherNonCurrentAssetsEndingBalance) {
		this.otherNonCurrentAssetsEndingBalance = otherNonCurrentAssetsEndingBalance;
	}

	public Double getOtherNonCurrentAssetsBeginningBalance() {
		return this.otherNonCurrentAssetsBeginningBalance;
	}

	public void setOtherNonCurrentAssetsBeginningBalance(
			Double otherNonCurrentAssetsBeginningBalance) {
		this.otherNonCurrentAssetsBeginningBalance = otherNonCurrentAssetsBeginningBalance;
	}

	public Double getTotalNonCurrentAssetsEndingBalance() {
		return this.totalNonCurrentAssetsEndingBalance;
	}

	public void setTotalNonCurrentAssetsEndingBalance(
			Double totalNonCurrentAssetsEndingBalance) {
		this.totalNonCurrentAssetsEndingBalance = totalNonCurrentAssetsEndingBalance;
	}

	public Double getTotalNonCurrentAssetsBeginningBalance() {
		return this.totalNonCurrentAssetsBeginningBalance;
	}

	public void setTotalNonCurrentAssetsBeginningBalance(
			Double totalNonCurrentAssetsBeginningBalance) {
		this.totalNonCurrentAssetsBeginningBalance = totalNonCurrentAssetsBeginningBalance;
	}

	public Double getTotalAssetsEndingBalance() {
		return this.totalAssetsEndingBalance;
	}

	public void setTotalAssetsEndingBalance(Double totalAssetsEndingBalance) {
		this.totalAssetsEndingBalance = totalAssetsEndingBalance;
	}

	public Double getTotalAssetsBeginningBalance() {
		return this.totalAssetsBeginningBalance;
	}

	public void setTotalAssetsBeginningBalance(
			Double totalAssetsBeginningBalance) {
		this.totalAssetsBeginningBalance = totalAssetsBeginningBalance;
	}

	public Double getShortLoanEndingBalance() {
		return this.shortLoanEndingBalance;
	}

	public void setShortLoanEndingBalance(Double shortLoanEndingBalance) {
		this.shortLoanEndingBalance = shortLoanEndingBalance;
	}

	public Double getShortLoanBeginningBalance() {
		return this.shortLoanBeginningBalance;
	}

	public void setShortLoanBeginningBalance(Double shortLoanBeginningBalance) {
		this.shortLoanBeginningBalance = shortLoanBeginningBalance;
	}

	public Double getNotesPayableEndingBalance() {
		return this.notesPayableEndingBalance;
	}

	public void setNotesPayableEndingBalance(Double notesPayableEndingBalance) {
		this.notesPayableEndingBalance = notesPayableEndingBalance;
	}

	public Double getNotesPayableBeginningBalance() {
		return this.notesPayableBeginningBalance;
	}

	public void setNotesPayableBeginningBalance(
			Double notesPayableBeginningBalance) {
		this.notesPayableBeginningBalance = notesPayableBeginningBalance;
	}

	public Double getPayableAccountEndingBalance() {
		return this.payableAccountEndingBalance;
	}

	public void setPayableAccountEndingBalance(
			Double payableAccountEndingBalance) {
		this.payableAccountEndingBalance = payableAccountEndingBalance;
	}

	public Double getPayableAccountBeginningBalance() {
		return this.payableAccountBeginningBalance;
	}

	public void setPayableAccountBeginningBalance(
			Double payableAccountBeginningBalance) {
		this.payableAccountBeginningBalance = payableAccountBeginningBalance;
	}

	public Double getDepositReceivedEndingBalance() {
		return this.depositReceivedEndingBalance;
	}

	public void setDepositReceivedEndingBalance(
			Double depositReceivedEndingBalance) {
		this.depositReceivedEndingBalance = depositReceivedEndingBalance;
	}

	public Double getDepositReceivedBeginningBalance() {
		return this.depositReceivedBeginningBalance;
	}

	public void setDepositReceivedBeginningBalance(
			Double depositReceivedBeginningBalance) {
		this.depositReceivedBeginningBalance = depositReceivedBeginningBalance;
	}

	public Double getEmployeeBenefitsPayableEndingBalance() {
		return this.employeeBenefitsPayableEndingBalance;
	}

	public void setEmployeeBenefitsPayableEndingBalance(
			Double employeeBenefitsPayableEndingBalance) {
		this.employeeBenefitsPayableEndingBalance = employeeBenefitsPayableEndingBalance;
	}

	public Double getEmployeeBenefitsPayableBeginningBalance() {
		return this.employeeBenefitsPayableBeginningBalance;
	}

	public void setEmployeeBenefitsPayableBeginningBalance(
			Double employeeBenefitsPayableBeginningBalance) {
		this.employeeBenefitsPayableBeginningBalance = employeeBenefitsPayableBeginningBalance;
	}

	public Double getTaxesPayableEndingBalance() {
		return this.taxesPayableEndingBalance;
	}

	public void setTaxesPayableEndingBalance(Double taxesPayableEndingBalance) {
		this.taxesPayableEndingBalance = taxesPayableEndingBalance;
	}

	public Double getTaxesPayableBeginningBalance() {
		return this.taxesPayableBeginningBalance;
	}

	public void setTaxesPayableBeginningBalance(
			Double taxesPayableBeginningBalance) {
		this.taxesPayableBeginningBalance = taxesPayableBeginningBalance;
	}

	public Double getInterestPayableEndingBalance() {
		return this.interestPayableEndingBalance;
	}

	public void setInterestPayableEndingBalance(
			Double interestPayableEndingBalance) {
		this.interestPayableEndingBalance = interestPayableEndingBalance;
	}

	public Double getInterestPayableBeginningBalance() {
		return this.interestPayableBeginningBalance;
	}

	public void setInterestPayableBeginningBalance(
			Double interestPayableBeginningBalance) {
		this.interestPayableBeginningBalance = interestPayableBeginningBalance;
	}

	public Double getProfitPayableEndingBalance() {
		return this.profitPayableEndingBalance;
	}

	public void setProfitPayableEndingBalance(Double profitPayableEndingBalance) {
		this.profitPayableEndingBalance = profitPayableEndingBalance;
	}

	public Double getProfitPayableBeginningBalance() {
		return this.profitPayableBeginningBalance;
	}

	public void setProfitPayableBeginningBalance(
			Double profitPayableBeginningBalance) {
		this.profitPayableBeginningBalance = profitPayableBeginningBalance;
	}

	public Double getAccountsPayableOthersEndingBalance() {
		return this.accountsPayableOthersEndingBalance;
	}

	public void setAccountsPayableOthersEndingBalance(
			Double accountsPayableOthersEndingBalance) {
		this.accountsPayableOthersEndingBalance = accountsPayableOthersEndingBalance;
	}

	public Double getAccountsPayableOthersBeginningBalance() {
		return this.accountsPayableOthersBeginningBalance;
	}

	public void setAccountsPayableOthersBeginningBalance(
			Double accountsPayableOthersBeginningBalance) {
		this.accountsPayableOthersBeginningBalance = accountsPayableOthersBeginningBalance;
	}

	public Double getOtherCurrentLiabilitiesEndingBalance() {
		return this.otherCurrentLiabilitiesEndingBalance;
	}

	public void setOtherCurrentLiabilitiesEndingBalance(
			Double otherCurrentLiabilitiesEndingBalance) {
		this.otherCurrentLiabilitiesEndingBalance = otherCurrentLiabilitiesEndingBalance;
	}

	public Double getOtherCurrentLiabilitiesBeginningBalance() {
		return this.otherCurrentLiabilitiesBeginningBalance;
	}

	public void setOtherCurrentLiabilitiesBeginningBalance(
			Double otherCurrentLiabilitiesBeginningBalance) {
		this.otherCurrentLiabilitiesBeginningBalance = otherCurrentLiabilitiesBeginningBalance;
	}

	public Double getTotalCurrentLiabilitiesEndingBalance() {
		return this.totalCurrentLiabilitiesEndingBalance;
	}

	public void setTotalCurrentLiabilitiesEndingBalance(
			Double totalCurrentLiabilitiesEndingBalance) {
		this.totalCurrentLiabilitiesEndingBalance = totalCurrentLiabilitiesEndingBalance;
	}

	public Double getTotalCurrentLiabilitiesBeginningBalance() {
		return this.totalCurrentLiabilitiesBeginningBalance;
	}

	public void setTotalCurrentLiabilitiesBeginningBalance(
			Double totalCurrentLiabilitiesBeginningBalance) {
		this.totalCurrentLiabilitiesBeginningBalance = totalCurrentLiabilitiesBeginningBalance;
	}

	public Double getLongLoanEndingBalance() {
		return this.longLoanEndingBalance;
	}

	public void setLongLoanEndingBalance(Double longLoanEndingBalance) {
		this.longLoanEndingBalance = longLoanEndingBalance;
	}

	public Double getLongLoanBeginningBalance() {
		return this.longLoanBeginningBalance;
	}

	public void setLongLoanBeginningBalance(Double longLoanBeginningBalance) {
		this.longLoanBeginningBalance = longLoanBeginningBalance;
	}

	public Double getLongTermPayablesEndingBalance() {
		return this.longTermPayablesEndingBalance;
	}

	public void setLongTermPayablesEndingBalance(
			Double longTermPayablesEndingBalance) {
		this.longTermPayablesEndingBalance = longTermPayablesEndingBalance;
	}

	public Double getLongTermPayablesBeginningBalance() {
		return this.longTermPayablesBeginningBalance;
	}

	public void setLongTermPayablesBeginningBalance(
			Double longTermPayablesBeginningBalance) {
		this.longTermPayablesBeginningBalance = longTermPayablesBeginningBalance;
	}

	public Double getDeferredIncomeEndingBalance() {
		return this.deferredIncomeEndingBalance;
	}

	public void setDeferredIncomeEndingBalance(
			Double deferredIncomeEndingBalance) {
		this.deferredIncomeEndingBalance = deferredIncomeEndingBalance;
	}

	public Double getDeferredIncomeBeginningBalance() {
		return this.deferredIncomeBeginningBalance;
	}

	public void setDeferredIncomeBeginningBalance(
			Double deferredIncomeBeginningBalance) {
		this.deferredIncomeBeginningBalance = deferredIncomeBeginningBalance;
	}

	public Double getOtherNonCurrentLiabilitiesEndingBalance() {
		return this.otherNonCurrentLiabilitiesEndingBalance;
	}

	public void setOtherNonCurrentLiabilitiesEndingBalance(
			Double otherNonCurrentLiabilitiesEndingBalance) {
		this.otherNonCurrentLiabilitiesEndingBalance = otherNonCurrentLiabilitiesEndingBalance;
	}

	public Double getOtherNonCurrentLiabilitiesBeginningBalance() {
		return this.otherNonCurrentLiabilitiesBeginningBalance;
	}

	public void setOtherNonCurrentLiabilitiesBeginningBalance(
			Double otherNonCurrentLiabilitiesBeginningBalance) {
		this.otherNonCurrentLiabilitiesBeginningBalance = otherNonCurrentLiabilitiesBeginningBalance;
	}

	public Double getTotalNonCurrentLiabilitiesEndingBalance() {
		return this.totalNonCurrentLiabilitiesEndingBalance;
	}

	public void setTotalNonCurrentLiabilitiesEndingBalance(
			Double totalNonCurrentLiabilitiesEndingBalance) {
		this.totalNonCurrentLiabilitiesEndingBalance = totalNonCurrentLiabilitiesEndingBalance;
	}

	public Double getTotalNonCurrentLiabilitiesBeginningBalance() {
		return this.totalNonCurrentLiabilitiesBeginningBalance;
	}

	public void setTotalNonCurrentLiabilitiesBeginningBalance(
			Double totalNonCurrentLiabilitiesBeginningBalance) {
		this.totalNonCurrentLiabilitiesBeginningBalance = totalNonCurrentLiabilitiesBeginningBalance;
	}

	public Double getTotalLiabilitiesEndingBalance() {
		return this.totalLiabilitiesEndingBalance;
	}

	public void setTotalLiabilitiesEndingBalance(
			Double totalLiabilitiesEndingBalance) {
		this.totalLiabilitiesEndingBalance = totalLiabilitiesEndingBalance;
	}

	public Double getTotalLiabilitiesBeginningBalance() {
		return this.totalLiabilitiesBeginningBalance;
	}

	public void setTotalLiabilitiesBeginningBalance(
			Double totalLiabilitiesBeginningBalance) {
		this.totalLiabilitiesBeginningBalance = totalLiabilitiesBeginningBalance;
	}

	public Double getPaidInCapitalEndingBalance() {
		return this.paidInCapitalEndingBalance;
	}

	public void setPaidInCapitalEndingBalance(Double paidInCapitalEndingBalance) {
		this.paidInCapitalEndingBalance = paidInCapitalEndingBalance;
	}

	public Double getPaidInCapitalBeginningBalance() {
		return this.paidInCapitalBeginningBalance;
	}

	public void setPaidInCapitalBeginningBalance(
			Double paidInCapitalBeginningBalance) {
		this.paidInCapitalBeginningBalance = paidInCapitalBeginningBalance;
	}

	public Double getCapitalReserveEndingBalance() {
		return this.capitalReserveEndingBalance;
	}

	public void setCapitalReserveEndingBalance(
			Double capitalReserveEndingBalance) {
		this.capitalReserveEndingBalance = capitalReserveEndingBalance;
	}

	public Double getCapitalReserveBeginningBalance() {
		return this.capitalReserveBeginningBalance;
	}

	public void setCapitalReserveBeginningBalance(
			Double capitalReserveBeginningBalance) {
		this.capitalReserveBeginningBalance = capitalReserveBeginningBalance;
	}

	public Double getEarnedSurplusEndingBalance() {
		return this.earnedSurplusEndingBalance;
	}

	public void setEarnedSurplusEndingBalance(Double earnedSurplusEndingBalance) {
		this.earnedSurplusEndingBalance = earnedSurplusEndingBalance;
	}

	public Double getEarnedSurplusBeginningBalance() {
		return this.earnedSurplusBeginningBalance;
	}

	public void setEarnedSurplusBeginningBalance(
			Double earnedSurplusBeginningBalance) {
		this.earnedSurplusBeginningBalance = earnedSurplusBeginningBalance;
	}

	public Double getUndistributedProfitEndingBalance() {
		return this.undistributedProfitEndingBalance;
	}

	public void setUndistributedProfitEndingBalance(
			Double undistributedProfitEndingBalance) {
		this.undistributedProfitEndingBalance = undistributedProfitEndingBalance;
	}

	public Double getUndistributedProfitBeginningBalance() {
		return this.undistributedProfitBeginningBalance;
	}

	public void setUndistributedProfitBeginningBalance(
			Double undistributedProfitBeginningBalance) {
		this.undistributedProfitBeginningBalance = undistributedProfitBeginningBalance;
	}

	public Double getTotalEquitiesEndingBalance() {
		return this.totalEquitiesEndingBalance;
	}

	public void setTotalEquitiesEndingBalance(Double totalEquitiesEndingBalance) {
		this.totalEquitiesEndingBalance = totalEquitiesEndingBalance;
	}

	public Double getTotalEquitiesBeginningBalance() {
		return this.totalEquitiesBeginningBalance;
	}

	public void setTotalEquitiesBeginningBalance(
			Double totalEquitiesBeginningBalance) {
		this.totalEquitiesBeginningBalance = totalEquitiesBeginningBalance;
	}

	public Double getTotalLiabilitiesAndOwnerEquityEndingBalance() {
		return this.totalLiabilitiesAndOwnerEquityEndingBalance;
	}

	public void setTotalLiabilitiesAndOwnerEquityEndingBalance(
			Double totalLiabilitiesAndOwnerEquityEndingBalance) {
		this.totalLiabilitiesAndOwnerEquityEndingBalance = totalLiabilitiesAndOwnerEquityEndingBalance;
	}

	public Double getTotalLiabilitiesAndOwnerEquityBeginningBalance() {
		return this.totalLiabilitiesAndOwnerEquityBeginningBalance;
	}

	public void setTotalLiabilitiesAndOwnerEquityBeginningBalance(
			Double totalLiabilitiesAndOwnerEquityBeginningBalance) {
		this.totalLiabilitiesAndOwnerEquityBeginningBalance = totalLiabilitiesAndOwnerEquityBeginningBalance;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}