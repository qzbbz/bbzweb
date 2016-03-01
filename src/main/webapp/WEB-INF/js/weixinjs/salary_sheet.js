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
	$('#create_time1').html(data.create_time);
	$('#preparedby1').html(data.preparedby);
	$('#company_controller1').html(data.company_controller);
	$('#finance_chief1').html(data.finance_chief);
	$('#tabulator1').html(data.tabulator);
	$('#create_time2').html(data.create_time);
	$('#preparedby2').html(data.preparedby);
	$('#company_controller2').html(data.company_controller);
	$('#finance_chief2').html(data.finance_chief);
	$('#tabulator2').html(data.tabulator);
	$('#salery_end').html(data.salery_end);
	$('#salery_begin').html(data.salery_begin);
	$('#bonus_end').html(data.bonus_end);
	$('#bonus_begin').html(data.bonus_begin);
	$('#welfare_end').html(data.welfare_end);
	$('#welfare_begin').html(data.welfare_begin);
	$('#insurance_end').html(data.insurance_end);
	$('#insurance_begin').html(data.insurance_begin);
	$('#funds_end').html(data.funds_end);
	$('#funds_begin').html(data.funds_begin);
	$('#sociaty_end').html(data.sociaty_end);
	$('#sociaty_begin').html(data.sociaty_begin);
	$('#education_end').html(data.education_end);
	$('#education_begin').html(data.education_begin);
	$('#no_cash_welfare_end').html(data.no_cash_welfare_end);
	$('#no_cash_welfare_begin').html(data.no_cash_welfare_begin);
	$('#dismiss_end').html(data.dismiss_end);
	$('#dismiss_begin').html(data.dismiss_begin);
	$('#other_end').html(data.other_end);
	$('#other_begin').html(data.other_begin);
	$('#salary_total_end').html(data.salary_total_end);
	$('#salary_total_begin').html(data.salary_total_begin);
	$('#value_added_tax_end').html(data.value_added_tax_end);
	$('#value_added_tax_begin').html(data.value_added_tax_begin);
	$('#excise_tax_end').html(data.excise_tax_end);
	$('#excise_tax_begin').html(data.excise_tax_begin);
	$('#business_tax_end').html(data.business_tax_end);
	$('#business_tax_begin').html(data.business_tax_begin);
	$('#urban_maintenance_and_construction_tax_end').html(data.urban_maintenance_and_construction_tax_end);
	$('#urban_maintenance_and_construction_tax_begin').html(data.urban_maintenance_and_construction_tax_begin);
	$('#business_income_taxes_end').html(data.business_income_taxes_end);
	$('#business_income_taxes_begin').html(data.business_income_taxes_begin);
	$('#resource_tax_end').html(data.resource_tax_end);
	$('#resource_tax_begin').html(data.resource_tax_begin);
	$('#land_value_increment_tax_end').html(data.land_value_increment_tax_end);
	$('#land_value_increment_tax_begin').html(data.land_value_increment_tax_begin);
	$('#urban_land_use_tax_end').html(data.urban_land_use_tax_end);
	$('#urban_land_use_tax_begin').html(data.urban_land_use_tax_begin);
	$('#building_taxes_end').html(data.building_taxes_end);
	$('#building_taxes_begin').html(data.building_taxes_begin);
	$('#vehicle_and_vessel_tax_end').html(data.vehicle_and_vessel_tax_end);
	$('#vehicle_and_vessel_tax_begin').html(data.vehicle_and_vessel_tax_begin);
	$('#attach_education_end').html(data.attach_education_end);
	$('#attach_education_begin').html(data.attach_education_begin);
	$('#mineral_resources_end').html(data.mineral_resources_end);
	$('#mineral_resources_begin').html(data.mineral_resources_begin);
	$('#sewage_charge_end').html(data.sewage_charge_end);
	$('#sewage_charge_begin').html(data.sewage_charge_begin);
	$('#income_tax_for_individuals_end').html(data.income_tax_for_individuals_end);
	$('#income_tax_for_individuals_begin').html(data.income_tax_for_individuals_begin);
	$('#tax_total_end').html(data.tax_total_end);
	$('#tax_total_begin').html(data.tax_total_begin);
}

function getSalarySheetData(date) {
	var mask = mui.createProcessingMask(null);
	mask.show();
	mui.ajax({
		url: '/getNewestSheetSalaryTax',
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