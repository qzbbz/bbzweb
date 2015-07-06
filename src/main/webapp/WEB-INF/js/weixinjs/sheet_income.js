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
				url : '/getNewestSheetIncome',
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
				$('#preparedby').html(data.preparedby);
				$('#company_controller').html(data.company_controller);
				$('#finance_chief').html(data.finance_chief);
				$('#tabulator').html(data.tabulator);
				$('#operating_receipt_year').html(data.operating_receipt_year);
				$('#operating_receipt_month').html(data.operating_receipt_month);
				$('#cost_in_business_year').html(data.cost_in_business_year);
				$('#cost_in_business_month').html(data.cost_in_business_month);
				$('#business_tariff_and_annex_year').html(data.business_tariff_and_annex_year);
				$('#business_tariff_and_annex_month').html(data.business_tariff_and_annex_month);
				$('#excise_tax_year').html(data.excise_tax_year);
				$('#excise_tax_month').html(data.excise_tax_month);
				$('#sales_tax_year').html(data.sales_tax_year);
				$('#sales_tax_month').html(data.sales_tax_month);
				$('#urban_maintenance_and_construction_tax_year').html(data.urban_maintenance_and_construction_tax_year);
				$('#urban_maintenance_and_construction_month').html(data.urban_maintenance_and_construction_month);
				$('#resource_tax_year').html(data.resource_tax_year);
				$('#resource_tax_month').html(data.resource_tax_month);
				$('#land_value_increment_tax_year').html(data.land_value_increment_tax_year);
				$('#land_value_increment_tax_month').html(data.land_value_increment_tax_month);
				$('#land_house_tax_year').html(data.land_house_tax_year);
				$('#land_house_tax_month').html(data.land_house_tax_month);
				$('#education_year').html(data.education_year);
				$('#education_month').html(data.education_month);
				$('#sales_year').html(data.sales_year);
				$('#sales_month').html(data.sales_month);
				$('#maintenance_year').html(data.maintenance_year);
				$('#maintenance_month').html(data.maintenance_month);
				$('#advertise_year').html(data.advertise_year);
				$('#advertise_month').html(data.advertise_month);
				$('#manage_year').html(data.manage_year);
				$('#manage_month').html(data.manage_month);
				$('#establishment_charges_year').html(data.establishment_charges_year);
				$('#establishment_charges_month').html(data.establishment_charges_month);
				$('#business_entertainment_year').html(data.business_entertainment_year);
				$('#business_entertainment_month').html(data.business_entertainment_month);
				$('#research_year').html(data.research_year);
				$('#research_month').html(data.research_month);
				$('#finance_year').html(data.finance_year);
				$('#finance_month').html(data.finance_month);
				$('#interest_year').html(data.interest_year);
				$('#interest_month').html(data.interest_month);
				$('#equity_earnings_year').html(data.equity_earnings_year);
				$('#equity_earnings_month').html(data.equity_earnings_month);
				$('#operating_profit_year').html(data.operating_profit_year);
				$('#operating_profit_month').html(data.operating_profit_month);
				$('#nonbusiness_income_year').html(data.nonbusiness_income_year);
				$('#nonbusiness_income_month').html(data.nonbusiness_income_month);
				$('#government_grants_year').html(data.government_grants_year);
				$('#government_grants_month').html(data.government_grants_month);
				$('#non_business_expenditure_year').html(data.non_business_expenditure_year);
				$('#non_business_expenditure_month').html(data.non_business_expenditure_month);
				$('#loss_on_bad_debt_year').html(data.loss_on_bad_debt_year);
				$('#loss_on_bad_debt_month').html(data.loss_on_bad_debt_month);
				$('#loss_on_long_term_bonds_year').html(data.loss_on_long_term_bonds_year);
				$('#loss_on_long_term_bonds_month').html(data.loss_on_long_term_bonds_month);
				$('#loss_on_long_term_stock_year').html(data.loss_on_long_term_stock_year);
				$('#loss_on_long_term_stock_month').html(data.loss_on_long_term_stock_month);
				$('#loss_on_long_term_natural_calamities_year').html(data.loss_on_long_term_natural_calamities_year);
				$('#loss_on_long_term_natural_calamities_month').html(data.loss_on_long_term_natural_calamities_month);
				$('#tax_delay_charge_year').html(data.tax_delay_charge_year);
				$('#tax_delay_charge_month').html(data.tax_delay_charge_month);
				$('#total_profit_year').html(data.total_profit_year);
				$('#total_profit_month').html(data.total_profit_month);
				$('#income_tax_expense_year').html(data.income_tax_expense_year);
				$('#income_tax_expense_month').html(data.income_tax_expense_month);
				$('#net_margin_year').html(data.net_margin_year);
				$('#net_margin_month').html(data.net_margin_month);
			}