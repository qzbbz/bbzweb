
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
	$('#preparedby').html(data.preparedby);
	$('#company_controller').html(data.company_controller);
	$('#finance_chief').html(data.finance_chief);
	$('#tabulator').html(data.tabulator);
	$('#operating_receipt_year').html("<a id='detailIncome_year' style='cursor:pointer' href='javascript:getDetailData(\"p01\",0,\"营业收入\") '> "+data.operating_receipt_year+"</a>");
	$('#operating_receipt_month').html("<a id='detailIncome_month' style='cursor:pointer' href='javascript:getDetailData(\"p01\",1,\"营业收入\") '>"+data.operating_receipt_month+"</a>");
	$('#cost_in_business_year').html("<a id='detailCost_year' style='cursor:pointer' href='javascript:getDetailData(\"p02\",0,\"营业成本\") '>"+data.cost_in_business_year+"</a>");
	$('#cost_in_business_month').html("<a id='detailCost_month' style='cursor:pointer' href='javascript:getDetailData(\"p02\",1,\"营业成本\") '>"+data.cost_in_business_month+"</a>");
	$('#business_tariff_and_annex_year').html(data.business_tariff_and_annex_year);
	$('#business_tariff_and_annex_month').html(data.business_tariff_and_annex_month);
	$('#excise_tax_year').html("<a id='detailsExciseTax_year' style='cursor:pointer' href='javascript:getDetailData(\"p0304\",0,\"消费税\") '>"+data.excise_tax_year+"</a>");
	$('#excise_tax_month').html("<a id='detailsExciseTax_month' style='cursor:pointer' href='javascript:getDetailData(\"p0304\",1,\"消费税\") '>"+data.excise_tax_month+"</a>");
	$('#sales_tax_year').html( "<a id='detailSalesTax_year' style='cursor:pointer' href='javascript:getDetailData(\"p03 \",0,\"营业税金及附加\") '>"+  data.sales_tax_year+"</a>");
	$('#sales_tax_month').html("<a id='detailSalesTax_month' style='cursor:pointer' href='javascript:getDetailData(\"p03\",1,\"营业税金及附加\") '>"+data.sales_tax_month+"</a>");
	$('#urban_maintenance_and_construction_tax_year').html("<a id='detailsUrban_year' style='cursor:pointer' href='javascript:getDetailData(\"p0306\",0,\"城市维护建设税\") '>"+data.urban_maintenance_and_construction_tax_year+"</a>");
	$('#urban_maintenance_and_construction_month').html("<a id='detailsUrban_month' style='cursor:pointer' href='javascript:getDetailData(\"p0306\",1,\"城市维护建设税\") '>"+data.urban_maintenance_and_construction_month+"</a>");
	$('#resource_tax_year').html("<a id='detailsResourceTax_year' style='cursor:pointer' href='javascript:getDetailData(\"p0307\",0,\"资源税\") '>"+data.resource_tax_year+"</a>");
	$('#resource_tax_month').html("<a id='detailsResourceTax_month' style='cursor:pointer' href='javascript:getDetailData(\"p0307\",1,\"资源税\") '>"+data.resource_tax_month+"</a>");
	$('#land_value_increment_tax_year').html("<a id='detailsLandValueIncrementTax_year' style='cursor:pointer' href='javascript:getDetailData(\"p0308\",0,\"土地增值税\") '>"+data.land_value_increment_tax_year+"</a>");
	$('#land_value_increment_tax_month').html("<a id='detailsLandValueIncrementTax_month' style='cursor:pointer' href='javascript:getDetailData(\"p0308\",1,\"土地增值税\") '>"+data.land_value_increment_tax_month+"</a>");
	$('#land_house_tax_year').html("<a id='detailsLandHouseTax_year' style='cursor:pointer' href='javascript:getDetailData(\"p0309\",0,\"城镇土地使用税、房产税、车船税、印花税\") '>"+data.land_house_tax_year+"</a>");
	$('#land_house_tax_month').html("<a id='detailsLandHouseTax_month' style='cursor:pointer' href='javascript:getDetailData(\"p0309\",1,\"城镇土地使用税、房产税、车船税、印花税\") '>"+data.land_house_tax_month+"</a>");
	$('#education_year').html("<a id='detailsEducation_year' style='cursor:pointer' href='javascript:getDetailData(\"p0310\",0,\"教育费附加、矿产资源补偿费、排污费\") '>"+data.education_year+"</a>");
	$('#education_month').html("<a id='detailsEducation_month' style='cursor:pointer' href='javascript:getDetailData(\"p0310\",1,\"教育费附加、矿产资源补偿费、排污费\") '>"+data.education_month+"</a>");
	$('#sales_year').html("<a id='detailsSales_year' style='cursor:pointer' href='javascript:getDetailData(\"p11\",0,\"销售费用\") '>"+data.sales_year+"</a>");
	$('#sales_month').html("<a id='detailsSales_month' style='cursor:pointer'href='javascript:getDetailData(\"p11\",1,\"销售费用\") '>"+data.sales_month+"</a>");
	$('#maintenance_year').html("<a id='detailsMaintenance_year' style='cursor:pointer' href='javascript:getDetailData(\"p1112\",0,\"商品维修费\") '"+data.maintenance_year+"</a>");
	$('#maintenance_month').html("<a id='detailsMaintenance_month' style='cursor:pointer' href='javascript:getDetailData(\"p1112\",1,\"商品维修费\") '>"+data.maintenance_month+"</a>");
	$('#advertise_year').html("<a id='detailsAdvertise_year' style='cursor:pointer' href='javascript:getDetailData(\"p1113\",0,\"广告费和业务宣传费\") '"+data.advertise_year+"</a>");
	$('#advertise_month').html("<a id='detailsAdvertise_month' style='cursor:pointer' href='javascript:getDetailData(\"p1113\",1,\"广告费和业务宣传费\") '>"+data.advertise_month+"</a>");
	$('#manage_year').html("<a id='detailsManage_year' style='cursor:pointer' href='javascript:getDetailData(\"p14\",0,\"管理费用\") '>"+data.manage_year+"</a>");
	$('#manage_month').html("<a id='detailsManage_month' style='cursor:pointer' href='javascript:getDetailData(\"p14\",1,\"管理费用\") '>"+data.manage_month+"</a>");
	$('#establishment_charges_year').html("<a id='detailsEstablishment_year' style='cursor:pointer' href='javascript:getDetailData(\"p1415\",0,\"开办费\") '>"+data.establishment_charges_year+"</a>");
	$('#establishment_charges_month').html("<a id='detailsEstablishment_month' style='cursor:pointer' href='javascript:getDetailData(\"p1415\",1,\"开办费\") '>"+data.establishment_charges_month+"</a>");
	$('#business_entertainment_year').html("<a id='detailsInfoBusinessEntertainment_year' style='cursor:pointer'  href='javascript:getDetailData(\"p1416\",0,\"业务招待费\") '>"+data.business_entertainment_year+"</a>");
	$('#business_entertainment_month').html("<a id='detailsInfoBusinessEntertainment_month' style='cursor:pointer'  href='javascript:getDetailData(\"p1416\",1,\"业务招待费\") '>"+data.business_entertainment_month+"</a>");
	$('#research_year').html("<a id='detailsResearch_year' style='cursor:pointer'  href='javascript:getDetailData(\"p1417\",0,\"研究费用\") '>"+data.research_year+"</a>");
	$('#research_month').html("<a id='detailsResearch_month' style='cursor:pointer'  href='javascript:getDetailData(\"p1417\",1,\"研究费用\") '>"+data.research_month+"</a>");
	$('#finance_year').html("<a id='detailsFinance_year' style='cursor:pointer'  href='javascript:getDetailData(\"p18\",0,\"财务费用\") '>"+data.finance_year+"</a>");
	$('#finance_month').html("<a id='detailsFinance_month' style='cursor:pointer'  href='javascript:getDetailData(\"p18\",1,\"财务费用\") '>"+data.finance_month+"</a>");
	
	$('#interest_year').html("<a id='detailsInterest_year' style='cursor:pointer'  href='javascript:getDetailData(\"p1819\",0,\"利息费用\") '>"+data.interest_year+"</a>");
	$('#interest_month').html("<a id='detailsInterest_month' style='cursor:pointer'  href='javascript:getDetailData(\"p1819\",1,\"利息费用\") '>"+data.interest_month+"</a>");
	
	$('#equity_earnings_year').html("<a id='detailsEquityEarmimg_year' style='cursor:pointer'  href='javascript:getDetailData(\"p20\",0,\"投资收益\") '>"+data.equity_earnings_year+"</a>");
	$('#equity_earnings_month').html("<a id='detailsEquityEarmimg_month' style='cursor:pointer'  href='javascript:getDetailData(\"p20\",1,\"投资收益\") '>"+data.equity_earnings_month+"</a>");
	
	$('#operating_profit_year').html("<a id='detailsOperatingProfit_year' style='cursor:pointer' href='javascript:getDetailData(\"p21\",0,\"营业利润\") '>"+data.operating_profit_year+"</a>");
	$('#operating_profit_month').html("<a id='detailsOperatingProfit_month' style='cursor:pointer' href='javascript:getDetailData(\"p21\",1,\"营业利润\") '>"+data.operating_profit_month+"</a>");
	
	$('#nonbusiness_income_year').html("<a id='detailsNonbusinessIncome_year' style='cursor:pointer' href='javascript:getDetailData(\"p22\",0,\"营业外收入\") '>"+data.nonbusiness_income_year+"</a>");
	$('#nonbusiness_income_month').html("<a id='detailsNonbusinessIncome_month' style='cursor:pointer' href='javascript:getDetailData(\"p22\",1,\"营业外收入\") '>"+data.nonbusiness_income_month+"</a>");
	
	$('#government_grants_year').html("<a id='detailsGovernment_year' style='cursor:pointer'  href='javascript:getDetailData(\"p2223\",0,\"政府补助\") '>"+data.government_grants_year+"</a>");
	$('#government_grants_month').html("<a id='detailsGovernment_month' style='cursor:pointer'  href='javascript:getDetailData(\"p2223\",1,\"政府补助\") '>"+data.government_grants_month+"</a>");
	
	$('#non_business_expenditure_year').html("<a id='detailsNonBusinessExpenditure_year' style='cursor:pointer'  href='javascript:getDetailData(\"p24\",0,\"营业外支出\") '>"+data.non_business_expenditure_year+"</a>");
	$('#non_business_expenditure_month').html("<a id='detailsNonBusinessExpenditure_month' style='cursor:pointer'  href='javascript:getDetailData(\"p24\",1,\"营业外支出\") '>"+data.non_business_expenditure_month+"</a>");
	
	$('#loss_on_bad_debt_year').html("<a id='detailsLossBad_year' style='cursor:pointer'  href='javascript:getDetailData(\"p2425\",0,\"坏账损失\") '>"+data.loss_on_bad_debt_year+"</a>");
	$('#loss_on_bad_debt_month').html("<a id='detailsLossBad_month' style='cursor:pointer'  href='javascript:getDetailData(\"p2425\",1,\"坏账损失\") '>"+data.loss_on_bad_debt_month+"</a>");
	
	$('#loss_on_long_term_bonds_year').html("<a id='detailsLongTermBonds_year' style='cursor:pointer'  href='javascript:getDetailData(\"p2426\",0,\"无法收回的长期债券投资损失\") '>"+data.loss_on_long_term_bonds_year+"</a>");
	$('#loss_on_long_term_bonds_month').html("<a id='detailsLongTermBonds_month' style='cursor:pointer'  href='javascript:getDetailData(\"p2426\",1,\"无法收回的长期债券投资损失\") '>"+data.loss_on_long_term_bonds_month+"</a>");
//------------------------------------------------


	$('#loss_on_long_term_stock_year').html("<a id='detailsLongTermStock_year' style='cursor:pointer'  href='javascript:getDetailData(\"p2427\",0,\"无法收回的长期股权投资损失\") '>"+data.loss_on_long_term_stock_year+"</a>");
	$('#loss_on_long_term_stock_month').html("<a id='detailsLongTermStock_month' style='cursor:pointer'  href='javascript:getDetailData(\"p2427\",1,\"无法收回的长期股权投资损失\") '>"+data.loss_on_long_term_stock_month+"</a>");
	
	$('#loss_on_long_term_natural_calamities_year').html("<a id='detailsNaturalCalamities_year' style='cursor:pointer'  href='javascript:getDetailData(\"p2428\",0,\"自然灾害等不可抗力因素造成的损失\") '>"+data.loss_on_long_term_natural_calamities_year+"</a>");
	$('#loss_on_long_term_natural_calamities_month').html("<a id='detailsNaturalCalamities_month' style='cursor:pointer'  href='javascript:getDetailData(\"p2428\",1,\"自然灾害等不可抗力因素造成的损失\") '>"+data.loss_on_long_term_natural_calamities_month+"</a>");
	
	$('#tax_delay_charge_year').html("<a id='detailsTaxDelayCharge_year' style='cursor:pointer'  href='javascript:getDetailData(\"p2429\",0,\"税收滞纳金\") '>"+data.tax_delay_charge_year+"</a>");
	$('#tax_delay_charge_month').html("<a id='detailsTaxDelayCharge_month' style='cursor:pointer'  href='javascript:getDetailData(\"p2429\",1,\"税收滞纳金\") '>"+data.tax_delay_charge_month+"</a>");
	
	$('#total_profit_year').html("<a id='detailsTotalProfit_year' style='cursor:pointer' href='javascript:getDetailData(\"p30\",0,\"利润总额\") '>"+data.total_profit_year+"</a>");
	$('#total_profit_month').html("<a id='detailsTotalProfit_month' style='cursor:pointer' href='javascript:getDetailData(\"p30\",1,\"利润总额\") '>"+data.total_profit_month+"</a>");
	
	$('#income_tax_expense_year').html("<a id='detailsIncomeTaxExpense_year' style='cursor:pointer'  href='javascript:getDetailData(\"p31\",0,\"所得税费用\") '>"+data.income_tax_expense_year);
	$('#income_tax_expense_month').html("<a id='detailsIncomeTaxExpense_month' style='cursor:pointer'  href='javascript:getDetailData(\"p31\",1,\"所得税费用\") '>"+data.income_tax_expense_month);
	
	$('#net_margin_year').html("<a id='detailsNetMargin_year' style='cursor:pointer' href='javascript:getDetailData(\"p32\",0,\"净利润\")'>"+data.net_margin_year+"</a>");
	$('#net_margin_month').html("<a   id='detailsNetMargin_month' style='cursor:pointer' href='javascript:getDetailData(\"p32\",1,\"净利润\")'>"+data.net_margin_month+"</a>");
}
var dateString = "";
function getIncomeSheetData(date) {
	dateString = date;
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
function getDetailData(project,type,title){
	
//	var date = $("#current_select_date").val();
//	$('#fixedAssetsInfoData').html("");
//	detailsInfoForm1.set('title', title);
//	detailsInfoForm1.show();
//	
	window.location.href = "/getNewestSheetIncomeWithWeixinSetSession?date=" + dateString + "&&type=" + type + "&&project=";
	$.ajax({
		url : '/getNewestSheetIncomeWithWeixinSetSession',
		type : "POST",
		data : {
			date:dateString,
			type:type,
			project:project
		},
		success : function(data) {
			var hasData = false;
			for(var index in data){
				hasData = true;
			}
			if (!hasData) {
				//BUI.Message.Alert('未获取到数据,请稍候重试！', 'success');
			} else {
				var dataList="";
				for(var index in data){
					dataList=dataList+"<tr><td title='"+data[index].dateTime+"'>"	+data[index].dateTime+"</td><td title='"+data[index].voucherType+"'>"	+data[index].voucherType+"</td><td title='"+data[index].voucherNo+"'>"	+data[index].voucherNo+"</td><td title='"+data[index].attachmentNums+"'>"	+data[index].attachmentNums
					+"</td><td title='"+data[index].abstracts+"'>"	+data[index].abstracts+"</td><td title='"+data[index].subject+"'>"+data[index].subject+"</td><td title='"+data[index].borrower+"'>"	+data[index].borrower+"</td><td title='"+data[index].lender+"'>"	+data[index].lender
					+"</td><td title='"+data[index].touching+"'>"	+data[index].touching+"</td><td  title='"+data[index].pchk+"'>"	+data[index].pchk+"</td><td  title='"+data[index].customer+"'>"	+data[index].customer
							+"</td></tr>"
					
				}
				$('#fixedAssetsInfoData').html(dataList);
			}
		},
		error : function(data) {
			//BUI.Message.Alert("未获取到数据!", 'warning');
		}
	})
}
//mui.ajax({ 
//    type : "POST", 
//    url  : "/getUserOpenIdAndCheckBindCompany",
//    data : {}, 
//    success : function(data) {
//    	if (data == null || data.openId == null || data.openId == "") {
//			mui.createTipDialog('无法获取您的微信Openid,请稍后重试！',null).show();
//			document.getElementById("data_loading").style.display = "none";
//			document.getElementById("tips_info_detail").innerHTML = "无法获取您的微信Openid,<br/>请稍后重试！";
//	    	document.getElementById("tips_info").style.display = "";
//		} else {
//			userOpenId = data.openId;
//			if(data.bind_status == "has_bind") {
//				document.getElementById("data_loading").style.display = "none";
//				document.getElementById("mui_main_page1").style.display = "";
//			} else {
//				document.getElementById("data_loading").style.display = "none";
//				document.getElementById("tips_info_detail").innerHTML = "您还没有绑定公司，<br/>请先在账号设置中绑定您的公司！";
//		    	document.getElementById("tips_info").style.display = "";
//			}
//		}
//    }, 
//    error : function() {
//    	mui.createTipDialog('服务器暂时无法响应请求，请稍后重试！',null).show();
//    	document.getElementById("data_loading").style.display = "none";
//    	document.getElementById("tips_info_detail").innerHTML = "服务器暂时无法响应请求，<br/>请稍后重试！";
//    	document.getElementById("tips_info").style.display = "";
//    } 
//});
document.getElementById("data_loading").style.display = "none";
document.getElementById("mui_main_page1").style.display = "";
userOpenId = "oSTV_t41ZaIuTRVHQQjSSr5aonKk";