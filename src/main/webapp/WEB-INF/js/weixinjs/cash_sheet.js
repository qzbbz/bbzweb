mui.createProcessingMask = function(callback) {
	var element = document.createElement('div');
	element.classList.add('upload-file');
	element.addEventListener('touchmove', mui.preventDefault);
	element.addEventListener('tap', function() {
		//mask.close();
	});
	var processingNode = document.createElement('div');
	processingNode.setAttribute('class', 'mui-loading');
	processingNode.innerHTML = "<div class='mui-spinner' style='width:60px;height:60px;'></div><div style='text-align: center;color:white;'>正在处理请求</div>";
	element.appendChild(processingNode);
	var mask = [element];
	mask._show = false;
	mask.show = function() {
		mask._show = true;
		element.setAttribute('style', 'opacity:1');
		//processingNode.style.marginLeft = window.screen.availWidth/2 + "px";
		processingNode.style.marginTop = window.screen.availHeight / 2 - 30 + "px";
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

function getCashSheetData(date) {
	var mask = mui.createProcessingMask(null);
	mask.show();
	mui.ajax({
		url: '/getNewestSheetCash',
		type: "POST",
		data: {
			userOpenId:userOpenId,date:date
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

mui.ajax({ 
    type : "POST", 
    url  : "/getUserOpenIdAndCheckBindCompany",
    data : {}, 
    success : function(data) {
    	if (data == null || data.openId == null || data.openId == "") {
			mui.createTipDialog('无法获取您的微信Openid,请稍后重试！',null).show();
			document.getElementById("data_loading").style.display = "none";
			document.getElementById("tips_info_detail").innerHTML = "无法获取您的微信Openid,请稍后重试！";
	    	document.getElementById("tips_info").style.display = "";
		} else {
			userOpenId = data.openId;
			if(data.bind_status == "has_bind") {
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
    	document.getElementById("tips_info_detail").innerHTML = "服务器暂时无法响应请求，请稍后重试！";
    	document.getElementById("tips_info").style.display = "";
    } 
});