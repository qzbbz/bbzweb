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
				url : '/getNewestSheetSalaryTax',
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