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

function getIncomeSheetData(date) {
	var mask = mui.createProcessingMask(null);
	mask.show();
	mui.ajax({
		url: '/getNewestSheetIncome',
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
			document.getElementById("tips_info_detail").innerHTML = "无法获取您的微信Openid,<br/>请稍后重试！";
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
    	document.getElementById("tips_info_detail").innerHTML = "服务器暂时无法响应请求，<br/>请稍后重试！";
    	document.getElementById("tips_info").style.display = "";
    } 
});