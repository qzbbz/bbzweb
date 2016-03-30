var companyPicker = new mui.PopPicker();
mui.createProcessingMask = function(callback) {
	var element = document.createElement('div');
	element.classList.add('upload-file');
	element.addEventListener('touchmove', mui.preventDefault);
	element.addEventListener('tap', function() {
		//mask.close();
	});
	var processingNode = document.createElement('div');
	processingNode.setAttribute('class', 'mui-loading');
	processingNode.innerHTML = "<div class='mui-spinner' style='width:60px;height:60px;'></div><div style='text-align: center;color:white;'>正在请求数据</div>";
	element.appendChild(processingNode);
	var mask = [element];
	mask._show = false;
	mask.show = function() {
		mask._show = true;
		element.setAttribute('style', 'opacity:1');
		//processingNode.style.marginLeft = window.screen.availWidth/2 + "px";
		processingNode.style.marginTop = document.documentElement.clientHeight /2 - 30 + "px";
		document.body.appendChild(element);
		return mask;
	};
	mask._remove = function() {
		if (mask._show) {
			mask._show = false;
			element.setAttribute('style', 'opacity:0');
			mui.later(function() {
				var body = document.body;
				element.parentNode === body && body.removeChild(element);
			}, 350);
		}
		return mask;
	};
	mask.close = function() {
		if (callback) {
			if (callback() !== false) {
				mask._remove();
			}
		} else {
			mask._remove();
		}
	};
	return mask;
};

mui.createTipDialog = function(info, callBack) {
	var template = "<div style='width:80%;margin:50% 10%;border:1px solid #ddd;background-color: white;border-radius: 5px;'><div style='margin-top:20px;margin-left:20px;'>提示信息</div><hr/><div style='margin-top:20px;margin-left:20px;margin-bottom:20px;margin-right:20px;height:60px;'>{{info}}</div></div>";
	var element = document.createElement('div');
	element.classList.add('dialog');
	element.innerHTML = template.replace('{{info}}', info);
	element.addEventListener('touchmove', mui.preventDefault);
	element.addEventListener('tap', function() {
		mask.close();
	});
	var mask = [element];
	mask._show = false;
	mask.show = function() {
		mask._show = true;
		element.setAttribute('style', 'opacity:1');
		document.body.appendChild(element);
		return mask;
	};
	mask._remove = function() {
		if (mask._show) {
			mask._show = false;
			element.setAttribute('style', 'opacity:0');
			mui.later(function() {
				var body = document.body;
				element.parentNode === body && body.removeChild(element);
			}, 350);
		}
		return mask;
	};
	mask.close = function() {
		if (callBack) {
			callBack();
		}
		mask._remove();
	};
	return mask;
};

mui.init();
mui('.mui-scroll-wrapper').scroll();
var userOpenId = "";

function fillDataIntoHtml(data) {
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
	$('#book_value_of_fixed_assets_ending_balance').html("<a id='showFixedAssetsInfo' style='cursor:pointer' href='javascript:getDetailData()'>"+data.book_value_of_fixed_assets_ending_balance+"</a>");
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
var dateString = "";
function getAssetSheetData(date) {
	var userId = document.getElementById('userId').value;
	var mask = mui.createProcessingMask(null);
	mask.show();
	dateString = date;
	mui.ajax({
		url: '/getNewestSheetBalance',
		type: "POST",
		data: {
			userId:userId,date:date
		},
		success: function(data) {
			if (data == null || data.result_is_null == 'true') {
				mask.close();
				mui.createTipDialog('没有查询到数据，请更换日期后重试！', null).show();
			} else {
				fillDataIntoHtml(data);
				mask.close();
			}
		},
		error: function(status, error) {
			mask.close();
			mui.createTipDialog('服务器暂时无法响应您的请求，请稍后重试！', null).show();
		}
	});
}
	function getDetailData() {
		window.location.href = "/getNewestSheetBalanceWithWeixinSetSession?date=" + dateString + "&userId=" + document.getElementById('userId').value;
	}
	document.getElementById('company_select').addEventListener('tap', function() {
		companyPicker.show(function(rs) {
			document.getElementById('companyName').innerHTML = rs[0].text;
			document.getElementById('userId').value = rs[0].value;
		});
	}, false);
mui.ajax({ 
    type : "POST", 
    url  : "/getUserOpenIdAndCheckBindCompany",
    data : {type:"0"}, 
    success : function(data) {
    	if (data.error_code != '0') {
			mui.createTipDialog(data.error_msg, null).show();
			document.getElementById("data_loading").style.display = "none";
			document.getElementById("tips_info_detail").innerHTML = data.error_msg + ",<br/>请稍后重试！";
	    	document.getElementById("tips_info").style.display = "";
		} else {
			userOpenId = data.openId;
			if(data.bind_status == "has_bind") {
				companyPicker.setData(data.companyName);
				document.getElementById('companyName').innerHTML = data.companyName[0].text;
	    		document.getElementById('userId').value = data.companyName[0].value;
				document.getElementById("data_loading").style.display = "none";
				document.getElementById("mui_main_page1").style.display = "";
			} else {
				document.getElementById("data_loading").style.display = "none";
				document.getElementById("tips_info_detail").innerHTML = "您还没有绑定公司，<br/>请先在账号设置中绑定您的公司！";
		    	document.getElementById("tips_info").style.display = "";
			}
		}
    }, 
    error : function() {
    	mui.createTipDialog('服务器暂时无法响应请求，请稍后重试！',null).show();
    	document.getElementById("data_loading").style.display = "none";
    	document.getElementById("tips_info_detail").innerHTML = "服务器暂时无法响应请求，<br/>请稍后重试！";
    	document.getElementById("tips_info").style.display = "";
    } 
});
/*document.getElementById("data_loading").style.display = "none";
document.getElementById("mui_main_page1").style.display = "";
userOpenId = "oSTV_t41ZaIuTRVHQQjSSr5aonKk";*/