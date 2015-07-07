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
			/*$.ajax({
				url : '/getNewestSheetCash',
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
			});*/
		} else {
			alert("您还没有绑定公司，因此无法获取数据，请先绑定公司！");
		}
	}
	
	$('#searchData').click(
			function() {
				var date = $('#table_date').val();
				if (date == null || date == "") {
					alert('请先输入日期！', 'warning');
				} else {
					$.ajax({
						url : '/getNewestSheetCash',
						type : "POST",
						data : {userOpenId:userOpenId,date:date},
						success : function(data) {
							if (data == null || data.result_is_null == 'true') {
								alert('没有查询到数据，请更换日期后重试！',
										'warning');
							} else {
								fillData(data);
							}
						},
						error : function(data) {
							alert('服务器无法获取到数据，请稍后重试！',
									'warning');
						}
					});
				}
			});
	
			function fillData(data) {
				$('#create_time').html(data.create_time);
				$('#preparedby').html(data.preparedby);
				$('#company_controller').html(data.company_controller);
				$('#finance_chief').html(data.finance_chief);
				$('#tabulator').html(data.tabulator);
				$('#sales_year').html(data.sales_year);
				$('#sales_month').html(data.sales_month);
				$('#receive_year').html(data.receive_year);
				$('#receive_month').html(data.receive_month);
				$('#buy_year').html(data.buy_year);
				$('#buy_month').html(data.buy_month);
				$('#salary_year').html(data.salary_year);
				$('#salary_month').html(data.salary_month);
				$('#tax_year').html(data.tax_year);
				$('#tax_month').html(data.tax_month);
				$('#other_pay_year').html(data.other_pay_year);
				$('#other_pay_month').html(data.other_pay_month);
				$('#operating_activities_year').html(data.operating_activities_year);
				$('#operating_activities_month').html(data.operating_activities_month);
				$('#take_back_year').html(data.take_back_year);
				$('#take_back_month').html(data.take_back_month);
				$('#equity_earnings_year').html(data.equity_earnings_year);
				$('#equity_earnings_month').html(data.equity_earnings_month);
				$('#handle_year').html(data.handle_year);
				$('#handle_month').html(data.handle_month);
				$('#investments_pay_year').html(data.investments_pay_year);
				$('#investments_pay_month').html(data.investments_pay_month);
				$('#coustruction_year').html(data.coustruction_year);
				$('#coustruction_month').html(data.coustruction_month);
				$('#activity_investment_year').html(data.activity_investment_year);
				$('#activity_investment_month').html(data.activity_investment_month);
				$('#loan_year').html(data.loan_year);
				$('#loan_month').html(data.loan_month);
				$('#investors_to_invest_year').html(data.investors_to_invest_year);
				$('#investors_to_invest_month').html(data.investors_to_invest_month);
				$('#payment_of_principal_year').html(data.payment_of_principal_year);
				$('#payment_of_principal_month').html(data.payment_of_principal_month);
				$('#paid_interest_year').html(data.paid_interest_year);
				$('#paid_interest_month').html(data.paid_interest_month);
				$('#distribute_profit_year').html(data.distribute_profit_year);
				$('#distribute_profit_month').html(data.distribute_profit_month);
				$('#financing_activity_year').html(data.financing_activity_year);
				$('#financing_activity_month').html(data.financing_activity_month);
				$('#net_increase_in_cash_year').html(data.net_increase_in_cash_year);
				$('#net_increase_in_cash_month').html(data.net_increase_in_cash_month);
				$('#cash_at_beginning_of_period_year').html(data.cash_at_beginning_of_period_year);
				$('#cash_at_beginning_of_period_month').html(data.cash_at_beginning_of_period_month);
				$('#ending_cash_balance_year').html(data.ending_cash_balance_year);
				$('#ending_cash_balance_month').html(data.ending_cash_balance_month);
			}