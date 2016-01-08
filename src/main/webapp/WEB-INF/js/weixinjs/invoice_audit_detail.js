var userOpenId = "";

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

mui.createConfirmDialog = function(info, cancelCallBack, acceptCallBack) {
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
			if (acceptCallBack) acceptCallBack();
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

mui.createCommentDialog = function(info, cancelCallBack, acceptCallBack, invoice_id) {
	var template = "<div style='width:80%;margin:50% 10%;border:1px solid #ddd;background-color: white;border-radius: 5px;'><div style='margin-top:20px;margin-left:20px;'>审核信息</div><hr/><div style='margin-top:20px;margin-left:20px;margin-bottom:20px;margin-right:20px;height:60px;'><textarea id='comment' placeholder='输入审核'></textarea></div><div style='text-align:right;margin-bottom:20px;margin-right:20px;'><a id='createCommentDialog_accept' href='javascript:void(0);' style='text-decoration:none;'>确定</a></div></div>";
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
		document.getElementById('createCommentDialog_accept').addEventListener('tap', function() {
			if (acceptCallBack) acceptCallBack(invoice_id);
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
function auditInvoice(invoiceId, approvalStatus, successCallback, failCallback) {
	mui.ajax({
		url: '/newApprovalBill',
		type: "POST",
		data: {
			openId : userOpenId,
			invoiceId : invoiceId,
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
function addCommentToInvoice(invoice_id){
	var commentNode = document.getElementById("comment");
	console.log(commentNode);
	if(commentNode !== null){
		var comment = commentNode.value;
		mui.ajax({
			url: '/addCommentToInvoice',
			type: "POST",
			data: {'invoice_id': invoice_id, 'comment': comment},
			success: function(data) {
				console.log(data);
				console.log("comment updated");
				//if (ajaxCallBack) ajaxCallBack();
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
	
}
function leftAndRightSliderEventCallback(element, approvalStatus) {
	var elem = element;
	var invoice_id = elem.getAttribute('invoice_id');
	var invoice_amount = parseFloat(elem.getAttribute('invoice_amount'));
	//var mask = mui.createProcessingMask(null);
	mui.createCommentDialog("hola", null, addCommentToInvoice, invoice_id).show();
	//mask.show();
	auditInvoice(invoice_id, approvalStatus, function() {
		var ulNode = elem.parentNode;
		var personDataNode = ulNode.parentNode;
		var dataDetailNode = ulNode.parentNode.parentNode;
		ulNode.removeChild(elem);
		document.getElementById('need_audit_invoice_count').innerHTML = parseInt(document.getElementById('need_audit_invoice_count').innerHTML) - 1;
		document.getElementById('need_audit_invoice_total_amount').innerHTML = (parseFloat(document.getElementById('need_audit_invoice_total_amount').innerHTML) - invoice_amount).toFixed(2);
		ulNode.parentNode.childNodes[0].childNodes[0].childNodes[0].childNodes[0].childNodes[1].innerHTML = (parseFloat(ulNode.parentNode.childNodes[0].childNodes[0].childNodes[0].childNodes[0].childNodes[1].innerHTML) - invoice_amount).toFixed(2);
		if (ulNode.firstChild == null) {
			dataDetailNode.removeChild(personDataNode);
			if (dataDetailNode.firstChild == null) {
				document.getElementById('data_abstract').style.display = "none";
				document.getElementById('data_details').style.display = "none";
				document.getElementById('no_data_tips').style.display = '';
			}
		}
		//mask.close();
	}, function(msg) {
		//mask.close();
		setTimeout(function() {
			mui.swipeoutClose(elem);
		}, 0);
		if(msg == null) {
			mui.createTipDialog('服务器处理请求失败，请稍后重试!', null).show();
		} else {
			mui.createTipDialog(msg, null).show();
		}
	});
}

function bindLeftAndRightSliderEvent() {
	mui('.mui-table-view').on('slideleft', '.mui-table-view-cell', function(event) {
		leftAndRightSliderEventCallback(this, 1);
	});
	mui('.mui-table-view').on('slideright', '.mui-table-view-cell', function(event) {
		leftAndRightSliderEventCallback(this, 0);
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
		var invoice_id_list_string = "";
		var person_invoice_count = 0;
		all_invoice_count = all_invoice_count + parseInt(data.invoice_count);
		all_invoice_total_amount = all_invoice_total_amount + parseFloat(data.invoice_total_amount);
		var rootNode = document.createElement('div');
		rootNode.setAttribute('person_invoice_count', data.invoice_count);
		rootNode.setAttribute('person_invoice_total_amount', data.invoice_total_amount);
		rootNode.style.marginBottom = '20px';
		var abstractNode = document.createElement('div');
		abstractNode.style.backgroundColor = "white";
		abstractNode.style.border = "1px solid #ddd";
		abstractNode.innerHTML = "<div style='background-color: white;border:1px solid #ddd;'><div style='height:60px;'><div class='mui-pull-left' style='margin-left:10px;margin-top:8px;'><div style='font-size:11px;color:#7D9EC0;'>发票提交人：" + data.user_name + "</div><div style='font-size:11px;color:#7D9EC0;'>发票总金额：&#65509;<span class='person_total_amount' style='font-size:11px;'>" + data.invoice_total_amount + "</span></div></div><div class='mui-pull-right' style='margin-right:10px;margin-top:10px;'></div></div></div>";
		rootNode.appendChild(abstractNode);
		var ulNode = document.createElement('div');
		ulNode.setAttribute("class", "mui-table-view");
		img_group = img_group + 1;
		var detailDataList = data.list;
		for (var j in detailDataList) {
			if(detailDataList[j].bill_amount == 0){
				continue;
			}
			if(invoice_id_list_string != "") {invoice_id_list_string = invoice_id_list_string + ","}
			var liNode = document.createElement('li');
			liNode.setAttribute('class', 'mui-table-view-cell');
			liNode.setAttribute('invoice_id', detailDataList[j].invoice_id);
			liNode.setAttribute('invoice_amount', detailDataList[j].bill_amount);
			liNode.innerHTML = "<div class='mui-slider-left mui-disabled'><a class='mui-btn mui-btn-green'>通过</a></div><div class='mui-slider-right mui-disabled'><a class='mui-btn mui-btn-red'>驳回</a></div><div class='mui-slider-handle'><img class='mui-media-object mui-pull-left' data-preview-group='" + img_group + "' data-preview-src='' style='width:60px;height:60px;max-width:60px;border-radius: 5px;' src='" + detailDataList[j].bill_img + "'><div class='mui-media-body'><div class='mui-pull-left' style='margin-top:15px;'><p>" + detailDataList[j].bill_title + "</p><p class='mui-ellipsis'>提交日期：<span>" + detailDataList[j].submit_time + "</span></p></div><div class='mui-pull-right' style='margin-top:35px;'><p>&#65509;" + detailDataList[j].bill_amount + "</p></div></div></div>";
			ulNode.appendChild(liNode);
			invoice_id_list_string = invoice_id_list_string + detailDataList[j].invoice_id;
			person_invoice_count = person_invoice_count + 1;
		}
		rootNode.setAttribute('invoice_id_list_string', invoice_id_list_string);
		rootNode.appendChild(ulNode);
		invoice_id_list_string = data.invoice_id_list_string;
		person_invoice_count = data.person_invoice_count;
		//rootNode.setAttribute('user_name', data[i].user_name);
		rootNode.setAttribute('person_invoice_count', person_invoice_count);
		rootNode.setAttribute('invoice_id_list_string', invoice_id_list_string);
		dataDetailsNode.appendChild(rootNode);
	
	bindLeftAndRightSliderEvent();
	document.getElementById('need_audit_invoice_count').innerHTML = all_invoice_count;
	document.getElementById('need_audit_invoice_total_amount').innerHTML = all_invoice_total_amount.toFixed(2);
	document.getElementById('data_abstract').style.display = "";
	document.getElementById('data_details').style.display = "";
}

function getNeedAuditInvoice(ajaxCallBack) {
	mui.ajax({
		url: '/getInovicesByIds',
		type: "POST",
		data: {"open_id": userOpenId},
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
	data: {},
	success: function(data) {
		if (data.openId == "") {
			mui.createTipDialog('无法获取您的微信Openid,请稍后重试！', null).show();
			document.getElementById('data_loading').style.display = 'none';
			document.getElementById('no_data_tips').style.innerHTML = "无法获取您的微信Openid,请稍后重试！";
			document.getElementById('no_data_tips').style.display = '';
		} else {
			userOpenId = data.openId;
			getNeedAuditInvoice(null);
		}
	},
	error: function(status, error) {
		mui.createTipDialog('请求服务器数据出错，请稍后下拉刷新重试！', null).show();
		document.getElementById('data_loading').style.display = 'none';
		document.getElementById('no_data_tips').style.innerHTML = "请求服务器数据出错，请稍后下拉刷新重试！";
		document.getElementById('no_data_tips').style.display = '';
	}
});
var testData = [{
	"user_name": "小明",
	"invoice_count": "2",
	"invoice_total_amount": "678.89",
	list: [{
		"bill_img": "/img/weixinimg/shuijiao.jpg",
		"invoice_id": "11",
		"bill_amount": "258.79",
		"submit_time": "2015-12-09",
		"expense_type_name": "餐饮消费"
	}, {
		"bill_img": "/img/weixinimg/shuijiao.jpg",
		"invoice_id": "14",
		"bill_amount": "22.79",
		"submit_time": "2015-12-08",
		"expense_type_name": "餐饮消费"
	}]
}, {
	"user_name": "薛玉洁",
	"invoice_count": "2",
	"invoice_total_amount": "4678.89",
	list: [{
		"bill_img": "/img/weixinimg/shuijiao.jpg",
		"invoice_id": "21",
		"bill_amount": "158.79",
		"submit_time": "2015-11-09",
		"expense_type_name": "餐饮消费"
	}, {
		"bill_img": "/img/weixinimg/shuijiao.jpg",
		"invoice_id": "167",
		"bill_amount": "3822.79",
		"submit_time": "2015-12-08",
		"expense_type_name": "餐饮消费"
	}]
}, {
	"user_name": "小明",
	"invoice_count": "2",
	"invoice_total_amount": "678.89",
	list: [{
		"bill_img": "/img/weixinimg/shuijiao.jpg",
		"invoice_id": "11",
		"bill_amount": "258.79",
		"submit_time": "2015-12-09",
		"expense_type_name": "餐饮消费"
	}, {
		"bill_img": "/img/weixinimg/shuijiao.jpg",
		"invoice_id": "14",
		"bill_amount": "22.79",
		"submit_time": "2015-12-08",
		"expense_type_name": "餐饮消费"
	}]
}, {
	"user_name": "薛玉洁",
	"invoice_count": "2",
	"invoice_total_amount": "4678.89",
	list: [{
		"bill_img": "/img/weixinimg/shuijiao.jpg",
		"invoice_id": "21",
		"bill_amount": "158.79",
		"submit_time": "2015-11-09",
		"expense_type_name": "餐饮消费"
	}, {
		"bill_img": "/img/weixinimg/shuijiao.jpg",
		"invoice_id": "167",
		"bill_amount": "3822.79",
		"submit_time": "2015-12-08",
		"expense_type_name": "餐饮消费"
	}]
}];
//createDataList(testData);