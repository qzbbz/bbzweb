 var userOpenId = "";
 Object.toparams = function ObjecttoParams(obj) {
	    var p = [];
	    for (var key in obj) {
	        p.push(key + '=' + encodeURIComponent(obj[key]));
	    }
	    return p.join('&');
	};
	$.ajax({ 
        type : "POST", 
        url  : "/getUserOpenId",  
        cache : false,
        async: false,
        data : "", 
        success : onGetUserOpenIdSuccess, 
        error : onGetUserOpenIdError 
    });
	
	function onGetUserOpenIdSuccess(data,status) {
		if (data.openId == "") {
			alert("无法获取您的微信Openid,请稍后重试！");
		} else {
			userOpenId = data.openId;
			$.ajax({ 
		        type : "POST", 
		        url  : "/checkBindCompany",  
		        cache : false,
		        async: false,
		        data : Object.toparams({openId:userOpenId}), 
		        success : checkBindCompanySuccess
		    });
		}
	}
	
	function onGetUserOpenIdError() {
		alert("无法获取您的微信Openid,请稍后重试！");
	}
	
	function checkBindCompanySuccess(data, status) {
		if(data.bind_status == "has_bind") {
			$.ajax({
				url : '/getNewestSheetBalance',
				type : "POST",
				data : Object.toparams({userOpenId:userOpenId}),
				success : function(data) {
					if(data.openid_exist == "false") {
						alert("服务器无法查询到您的openid,请稍后重试或联系系统管理员！");
						return;
					}
					if (data.result_is_null == 'true') {
						alert("目前还没有数据可以提供显示，请稍后重试！");
						return;
					} else {
						fillData(data);
					}
				},
				error : function(data) {
					alert("服务器暂时无法响应您的请求，请稍后重试或联系系统管理员！");
				}
			});
		} else {
			alert("您还没有绑定公司，因此无法获取数据，请先绑定公司！");
		}
	}
			function fillData(data) {
				$('#create_time').html(data.create_time);
				$('#expiry_date').html(data.expiry_date);
				$('#preparedby1').html(data.preparedby);
				$('#preparedby2').html(data.preparedby);
				$('#company_controller').html(data.company_controller);
				$('#finance_chief').html(data.finance_chief);
				$('#tabulator').html(data.tabulator);
				$('#monetary_resources_ending_balance').html(data.monetary_resources_ending_balance);
				$('#monetary_resources_beginning_balance').html(data.monetary_resources_beginning_balance);
				$('#short_term_investments_ending_balance').html(data.short_term_investments_ending_balance);
				$('#short_term_investments_beginning_balance').html(data.short_term_investments_beginning_balance);
				$('#bill_receivable_ending_balance').html(data.bill_receivable_ending_balance);
				$('#bill_receivable_beginning_balance').html(data.bill_receivable_beginning_balance);
				$('#accounts_receivable_ending_balance').html(data.accounts_receivable_ending_balance);
				$('#accounts_receivable_beginning_balance').html(data.accounts_receivable_beginning_balance);
				$('#prepayment_ending_balance').html(data.prepayment_ending_balance);
				$('#prepayment_beginning_balance').html(data.prepayment_beginning_balance);
				$('#dividends_receivable_ending_balance').html(data.dividends_receivable_ending_balance);
				$('#dividends_receivable_beginning_balance').html(data.dividends_receivable_beginning_balance);
				$('#interest_receivable_ending_balance').html(data.interest_receivable_ending_balance);
				$('#interest_receivable_beginning_balance').html(data.interest_receivable_beginning_balance);
				$('#other_receivables_ending_balance').html(data.other_receivables_ending_balance);
				$('#other_receivables_beginning_balance').html(data.other_receivables_beginning_balance);
				$('#stock_ending_balance').html(data.stock_ending_balance);
				$('#stock_beginning_balance').html(data.stock_beginning_balance);
				$('#stock_raw_materials_ending_balance').html(data.stock_raw_materials_ending_balance);
				$('#stock_raw_materials_beginning_balance').html(data.stock_raw_materials_beginning_balance);
				$('#stock_product_ending_balance').html(data.stock_product_ending_balance);
				$('#stock_product_beginning_balance').html(data.stock_product_beginning_balance);
				$('#stock_merchandise_inventory_ending_balance').html(data.stock_merchandise_inventory_ending_balance);
				$('#stock_merchandise_inventory_beginning_balance').html(data.stock_merchandise_inventory_beginning_balance);
				$('#stock_revolving_materials_ending_balance').html(data.stock_revolving_materials_ending_balance);
				$('#stock_revolving_materials_beginning_balance').html(data.stock_revolving_materials_beginning_balance);
				$('#other_current_assets_ending_balance').html(data.other_current_assets_ending_balance);
				$('#other_current_assets_beginning_balance').html(data.other_current_assets_beginning_balance);
				$('#total_current_assets_ending_balance').html(data.total_current_assets_ending_balance);
				$('#total_current_assets_beginning_balance').html(data.total_current_assets_beginning_balance);
				$('#long_term_investments_in_bonds_ending_balance').html(data.long_term_investments_in_bonds_ending_balance);
				$('#long_term_investments_in_bonds_beginning_balance').html(data.long_term_investments_in_bonds_beginning_balance);
				$('#long_term_investment_on_stocks_ending_balance').html(data.long_term_investment_on_stocks_ending_balance);
				$('#long_term_investment_on_stocks_beginning_balance').html(data.long_term_investment_on_stocks_beginning_balance);
				$('#fixed_assets_original_cost_ending_balance').html(data.fixed_assets_original_cost_ending_balance);
				$('#fixed_assets_original_cost_beginning_balance').html(data.fixed_assets_original_cost_beginning_balance);
				$('#accumulated_depreciation_ending_balance').html(data.accumulated_depreciation_ending_balance);
				$('#accumulated_depreciation_beginning_balance').html(data.accumulated_depreciation_beginning_balance);
				$('#book_value_of_fixed_assets_ending_balance').html(data.book_value_of_fixed_assets_ending_balance);
				$('#book_value_of_fixed_assets_beginning_balance').html(data.book_value_of_fixed_assets_beginning_balance);
				$('#construction_in_process_ending_balance').html(data.construction_in_process_ending_balance);
				$('#construction_in_process_beginning_balance').html(data.construction_in_process_beginning_balance);
				$('#construction_materials_ending_balance').html(data.construction_materials_ending_balance);
				$('#construction_materials_beginning_balance').html(data.construction_materials_beginning_balance);
				$('#removal_of_fixed_assets_ending_balance').html(data.removal_of_fixed_assets_ending_balance);
				$('#removal_of_fixed_assets_beginning_balance').html(data.removal_of_fixed_assets_beginning_balance);
				$('#biological_assets_ending_balance').html(data.biological_assets_ending_balance);
				$('#biological_assets_beginning_balance').html(data.biological_assets_beginning_balance);
				$('#intangible_assets_ending_balance').html(data.intangible_assets_ending_balance);
				$('#intangible_assets_beginning_balance').html(data.intangible_assets_beginning_balance);
				$('#development_expenditure_ending_balance').html(data.development_expenditure_ending_balance);
				$('#development_expenditure_beginning_balance').html(data.development_expenditure_beginning_balance);
				$('#long_term_prepaid_expenses_ending_balance').html(data.long_term_prepaid_expenses_ending_balance);
				$('#long_term_prepaid_expenses_beginning_balance').html(data.long_term_prepaid_expenses_beginning_balance);
				$('#other_non_current_assets_ending_balance').html(data.other_non_current_assets_ending_balance);
				$('#other_non_current_assets_beginning_balance').html(data.other_non_current_assets_beginning_balance);
				$('#total_non_current_assets_ending_balance').html(data.total_non_current_assets_ending_balance);
				$('#total_non_current_assets_beginning_balance').html(data.total_non_current_assets_beginning_balance);
				$('#total_assets_ending_balance').html(data.total_assets_ending_balance);
				$('#total_assets_beginning_balance').html(data.total_assets_beginning_balance);
				$('#short_loan_ending_balance').html(data.short_loan_ending_balance);
				$('#short_loan_beginning_balance').html(data.short_loan_beginning_balance);
				$('#notes_payable_ending_balance').html(data.notes_payable_ending_balance);
				$('#notes_payable_beginning_balance').html(data.notes_payable_beginning_balance);
				$('#payable_account_ending_balance').html(data.payable_account_ending_balance);
				$('#payable_account_beginning_balance').html(data.payable_account_beginning_balance);
				$('#deposit_received_ending_balance').html(data.deposit_received_ending_balance);
				$('#deposit_received_beginning_balance').html(data.deposit_received_beginning_balance);
				$('#employee_benefits_payable_ending_balance').html(data.employee_benefits_payable_ending_balance);
				$('#employee_benefits_payable_beginning_balance').html(data.employee_benefits_payable_beginning_balance);
				$('#taxes_payable_ending_balance').html(data.taxes_payable_ending_balance);
				$('#taxes_payable_beginning_balance').html(data.taxes_payable_beginning_balance);
				$('#interest_payable_ending_balance').html(data.interest_payable_ending_balance);
				$('#interest_payable_beginning_balance').html(data.interest_payable_beginning_balance);
				$('#profit_payable_ending_balance').html(data.profit_payable_ending_balance);
				$('#profit_payable_beginning_balance').html(data.profit_payable_beginning_balance);
				$('#accounts_payable_others_ending_balance').html(data.accounts_payable_others_ending_balance);
				$('#accounts_payable_others_beginning_balance').html(data.accounts_payable_others_beginning_balance);
				$('#other_current_liabilities_ending_balance').html(data.other_current_liabilities_ending_balance);
				$('#other_current_liabilities_beginning_balance').html(data.other_current_liabilities_beginning_balance);
				$('#total_current_liabilities_ending_balance').html(data.total_current_liabilities_ending_balance);
				$('#total_current_liabilities_beginning_balance').html(data.total_current_liabilities_beginning_balance);
				$('#long_loan_ending_balance').html(data.long_loan_ending_balance);
				$('#long_loan_beginning_balance').html(data.long_loan_beginning_balance);
				$('#long_term_payables_ending_balance').html(data.long_term_payables_ending_balance);
				$('#long_term_payables_beginning_balance').html(data.long_term_payables_beginning_balance);
				$('#deferred_income_ending_balance').html(data.deferred_income_ending_balance);
				$('#deferred_income_beginning_balance').html(data.deferred_income_beginning_balance);
				$('#other_non_current_liabilities_ending_balance').html(data.other_non_current_liabilities_ending_balance);
				$('#other_non_current_liabilities_beginning_balance').html(data.other_non_current_liabilities_beginning_balance);
				$('#total_non_current_liabilities_ending_balance').html(data.total_non_current_liabilities_ending_balance);
				$('#total_non_current_liabilities_beginning_balance').html(data.total_non_current_liabilities_beginning_balance);
				$('#total_liabilities_ending_balance').html(data.total_liabilities_ending_balance);
				$('#total_liabilities_beginning_balance').html(data.total_liabilities_beginning_balance);
				$('#paid_in_capital_ending_balance').html(data.paid_in_capital_ending_balance);
				$('#paid_in_capital_beginning_balance').html(data.paid_in_capital_beginning_balance);
				$('#capital_reserve_ending_balance').html(data.capital_reserve_ending_balance);
				$('#capital_reserve_beginning_balance').html(data.capital_reserve_beginning_balance);
				$('#earned_surplus_ending_balance').html(data.earned_surplus_ending_balance);
				$('#earned_surplus_beginning_balance').html(data.earned_surplus_beginning_balance);
				$('#undistributed_profit_ending_balance').html(data.undistributed_profit_ending_balance);
				$('#undistributed_profit_beginning_balance').html(data.undistributed_profit_beginning_balance);
				$('#total_equities_ending_balance').html(data.total_equities_ending_balance);
				$('#total_equities_beginning_balance').html(data.total_equities_beginning_balance);
				$('#total_liabilities_and_owner_equity_ending_balance').html(data.total_liabilities_and_owner_equity_ending_balance);
				$('#total_liabilities_and_owner_equity_beginning_balance').html(data.total_liabilities_and_owner_equity_beginning_balance);
			}