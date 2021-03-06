var userOpenId = "";


var globleEle;
function checkJsonIsEmpty(json) {
	var isEmpty = true;
	if (json == null) return true;
	for (var jsonKey in json) {
		isEmpty = false;
		break;
	}
	return isEmpty;
}

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

mui.createConfirmDialog = function(info, eleargs, cancelCallBack, acceptCallBack) {
	var template = "<div style='width:80%;margin:50% 10%;border:1px solid #ddd;background-color: white;border-radius: 5px;'><div style='margin-top:20px;margin-left:20px;'>提示信息</div><hr/><div style='margin-top:20px;margin-left:20px;margin-bottom:20px;margin-right:20px;height:60px;'>{{info}}</div><div style='text-align:right;margin-bottom:20px;margin-right:20px;'><a id='createConfirmDialog_cancel' href='javascript:void(0);' style='margin-right:20px;text-decoration:none;'>取消</a><a id='createConfirmDialog_accept' href='javascript:void(0);' style='text-decoration:none;'>确定</a></div></div>";
	var element = document.createElement('div');
	element.classList.add('dialog');
	element.innerHTML = template.replace('{{info}}', info);
	element.addEventListener('touchmove', mui.preventDefault);
	var mask = [element];
	mask._show = false;
	mask.show = function() {
		mask._show = true;
		element.setAttribute('style', 'opacity:1');
		document.body.appendChild(element);
		document.getElementById('createConfirmDialog_cancel').addEventListener('tap', function() {
			if (cancelCallBack) cancelCallBack();
			mask.close();
		});
		document.getElementById('createConfirmDialog_accept').addEventListener('tap', function() {
			if (acceptCallBack) acceptCallBack(eleargs);
			mask.close();
		});
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
		mask._remove();
	};
	return mask;
};

//invoiceIdString:12,23,32
//approvalStatus:0-pass,1-reject
function auditInvoice( id_list, approvalStatus, successCallback, failCallback) {
		mui.ajax({
			url: '/newApprovalBill',
			type: "POST",
			data: {
				openId : userOpenId,
				invoiceId : id_list,
				approvalStatus : approvalStatus
			},
			success: function(data) {
				if (data.error_code == "0") {
					if (successCallback) successCallback();
				} else {
					if (failCallback) failCallback(data.error_message);
				}
			},
			error: function(status, error) {
				if (failCallback) failCallback(null);
			}
		});
}

function auditWork( id_list, approvalStatus, successCallback, failCallback) {
	mui.ajax({
		url: '/newApprovalWork',
		type: "POST",
		data: {
			openId : userOpenId,
			workId : id_list,
			approvalStatus : approvalStatus
		},
		success: function(data) {
			if (data.error_code == "0") {
				if (successCallback) successCallback();
			} else {
				if (failCallback) failCallback(data.error_message);
			}
		},
		error: function(status, error) {
			if (failCallback) failCallback(null);
		}
	});
	
}
function bindAllPassButtonEvent() {
	mui('.mui-pull-right').on('tap', '.mui-btn-success', function(event) {
		event.stopPropagation();
		mui.createConfirmDialog("您确认要全部通过吗？", this, null, function(eleargs){
			var elem = eleargs;
			var rootNode = elem.parentNode.parentNode.parentNode.parentNode.parentNode;
			var invoice_id_list_string = "";
			var work_id_list = "";
			var idList = rootNode.getAttribute('invoice_id_list');
			var pos = idList.indexOf("#workout#");
			if (pos != -1) {
				if (pos != 0) {
					invoice_id_list_string = idList.substring(0, pos - 1);
					work_id_list = idList.substring(pos);
				} else {
					work_id_list = idList;
				} 
			} else {
				invoice_id_list_string = idList;
			}
			var person_invoice_count = parseInt(rootNode.getAttribute('person_invoice_count'));
			var person_invoice_total_amount = parseFloat(rootNode.getAttribute('person_invoice_total_amount'));
			var mask = mui.createProcessingMask(null);
			mask.show();
			if (invoice_id_list_string != "") {
				auditInvoice(invoice_id_list_string, 0, function() {
					var dataDetailNode = rootNode.parentNode;
					dataDetailNode.removeChild(rootNode);
					document.getElementById('need_audit_invoice_count').innerHTML = parseInt(document.getElementById('need_audit_invoice_count').innerHTML) - person_invoice_count;
					document.getElementById('need_audit_invoice_total_amount').innerHTML = (parseFloat(document.getElementById('need_audit_invoice_total_amount').innerHTML) - person_invoice_total_amount).toFixed(2);
					if (dataDetailNode.firstChild == null) {
						document.getElementById('data_abstract').style.display = "none";
						document.getElementById('data_details').style.display = "none";
						document.getElementById('no_data_tips').style.display = '';
					}
					mask.close();
				}, function(msg) {
					mask.close();
					if(msg == null) {
						mui.createTipDialog('服务器处理请求失败，请稍后重试!', null).show();
					} else {
						mui.createTipDialog(msg, null).show();
					}
				});
			}
			if (work_id_list != "") {
				auditWork(work_id_list, 0, function() {
					var dataDetailNode = rootNode.parentNode;
					dataDetailNode.removeChild(rootNode);
					document.getElementById('need_audit_invoice_count').innerHTML = parseInt(document.getElementById('need_audit_invoice_count').innerHTML) - person_invoice_count;
					document.getElementById('need_audit_invoice_total_amount').innerHTML = (parseFloat(document.getElementById('need_audit_invoice_total_amount').innerHTML) - person_invoice_total_amount).toFixed(2);
					if (dataDetailNode.firstChild == null) {
						document.getElementById('data_abstract').style.display = "none";
						document.getElementById('data_details').style.display = "none";
						document.getElementById('no_data_tips').style.display = '';
					}
					mask.close();
				}, function(msg) {
					mask.close();
					if(msg == null) {
						mui.createTipDialog('服务器处理请求失败，请稍后重试!', null).show();
					} else {
						mui.createTipDialog(msg, null).show();
					}
				});
			}
			
		}).show();
	});
}

function createDataList(data) {
	document.getElementById('data_loading').style.display = "none";
	document.getElementById('no_data_tips').style.display = "none";
	document.getElementById('data_details').innerHTML = "";
	var dataDetailsNode = document.getElementById('data_details');
	var all_invoice_count = 0;
	var all_invoice_total_amount = 0.0;
	var img_group = 0;
	for (var key in data) {
		all_invoice_count += data[key].bill_total;
		all_invoice_total_amount += data[key].amount;
		var rootNode = document.createElement('div');
		rootNode.setAttribute('person_invoice_count', data[key].bill_total);
		rootNode.setAttribute('person_invoice_total_amount', data[key].amount);
		rootNode.setAttribute('invoice_id_list', data[key].invoice_id_list);
		rootNode.setAttribute('submit_user_id', key);
		rootNode.style.marginBottom = '20px';
		var abstractNode = document.createElement('div');
		abstractNode.style.backgroundColor = "white";
		abstractNode.style.border = "1px solid #ddd";
		abstractNode.innerHTML = "<div style='background-color: white;border:1px solid #ddd;'><div style='height:80px;'><div class='mui-pull-left' style='margin-left:10px;margin-top:8px;'><div style='font-size:11px;color:#7D9EC0;'>发票提交人：" + data[key].user_name + "</div><div style='font-size:11px;color:#7D9EC0;'>发票总张数：" + data[key].bill_total + "</div><div style='font-size:11px;color:#7D9EC0;'>发票总金额：&#65509;<span class='person_total_amount' style='font-size:11px;'>" + data[key].amount.toFixed(2) + "</span></div></div><div class='mui-pull-right' style='margin-right:10px;margin-top:20px;'><button class='mui-btn mui-btn-block mui-btn-success' style='height:40px;width:120px;padding:0px 0px;z-index:999999999;'><span style='font-size: 15px;'>全部通过</span></button></div></div></div>";
		rootNode.appendChild(abstractNode);
		dataDetailsNode.appendChild(rootNode);
		rootNode.addEventListener("tap", function(){
			var submit_user_id = this.getAttribute("submit_user_id");
			var mask = mui.createProcessingMask(null);
			mask.show();
			mui.ajax({
				url: '/storeRequestInvoiceIds',
				type: "POST",
				data: {submit_user_id: submit_user_id},
				success: function(data) {
					mask.close();
					window.location = "/views/weixinviews/invoice_audit_detail.html";
				},
				error: function(status, error) {
					mui.createTipDialog('请求服务器数据出错，请稍后重试！', null).show();
					mask.close();
				}
			});

		});
	}
	bindAllPassButtonEvent();
	document.getElementById('need_audit_invoice_count').innerHTML = all_invoice_count;
	document.getElementById('need_audit_invoice_total_amount').innerHTML = all_invoice_total_amount.toFixed(2);
	document.getElementById('data_abstract').style.display = "";
	document.getElementById('data_details').style.display = "";
}

function getNeedAuditInvoice(ajaxCallBack) {

	mui.ajax({
		url: '/newGetNeedAuditBillsSummary?openId=' + userOpenId,
		type: "POST",
		data: {},
		success: function(data) {
			if (!checkJsonIsEmpty(data)) {
				document.getElementById('no_data_tips').style.display = 'none';
				createDataList(data);
			} else {
				document.getElementById('no_data_tips').style.display = '';
			}
			document.getElementById('data_loading').style.display = 'none';
			if (ajaxCallBack) ajaxCallBack();
		},
		error: function(status, error) {
			mui.createTipDialog('请求服务器数据出错，请稍后下拉刷新重试！', null).show();
			document.getElementById('data_loading').style.display = 'none';
			document.getElementById('no_data_tips').style.display = '';
			document.getElementById('data_abstract').style.display = "none";
			document.getElementById('data_details').style.display = "none";
			if (ajaxCallBack) ajaxCallBack();
		}
	});
}

mui.init();
mui('.mui-scroll-wrapper').scroll();
mui.initPreviewImage();
mui.previewImage({
	isUpload: false
});
var deceleration = mui.os.ios ? 0.003 : 0.0009;
mui('.mui-scroll-wrapper').scroll({
	bounce: false,
	indicators: true, //是否显示滚动条
	deceleration: deceleration
});
mui(mui('#pull_refresh')[0]).pullToRefresh({
	down: {
		callback: function() {
			var self = this;

			function ajaxCallBack() {
				self.endPullDownToRefresh();
			}
			getNeedAuditInvoice(ajaxCallBack);
		}
	}
});

mui.ajax({
	url: '/getUserOpenId',
	type: "POST",
	data: {type:"0"},
	success: function(data) {
		if (data.openId == "") {
			mui.createTipDialog('无法获取您的微信Openid,请稍后重试！', null).show();
			document.getElementById('data_loading').style.display = 'none';
			document.getElementById('no_data_tips').style.innerHTML = "无法获取您的微信Openid,<br/>请稍后重试！";
			document.getElementById('no_data_tips').style.display = '';
		} else {
			userOpenId = data.openId;
			getNeedAuditInvoice(null);
		}
	},
	error: function(status, error) {
		mui.createTipDialog('请求服务器数据出错，请稍后下拉刷新重试！', null).show();
		document.getElementById('data_loading').style.display = 'none';
		document.getElementById('no_data_tips').style.innerHTML = "请求服务器数据出错，<br/>请稍后下拉刷新重试！";
		document.getElementById('no_data_tips').style.display = '';
	}
});
//createDataList(testData);