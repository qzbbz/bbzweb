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
		setTimeout(function() {
			mui.swipeoutClose(elem);
		}, 0);
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

mui.createCommentDialog = function(info, cancelCallBack, acceptCallBack, type, id, eleargs, approvalStatus) {
	var template = "<div style='width:80%;margin:50% 10%;border:1px solid #ddd;background-color: white;border-radius: 5px;'><div style='margin-top:20px;margin-left:20px;'>审核信息</div><hr/><div style='margin-top:20px;margin-left:20px;margin-bottom:20px;margin-right:20px;height:60px;'><textarea id='comment' placeholder='输入审核信息'></textarea></div><div style='text-align:right;margin-bottom:20px;margin-right:20px;'><a id='createCommentDialog_accept' href='javascript:void(0);' style='text-decoration:none;'>确定</a>&nbsp&nbsp<a id='createCommentDialog_cancel' href='javascript:void(0);' style='text-decoration:none;'>取消</a></div></div>";
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
			if (acceptCallBack) acceptCallBack(type, id, eleargs, approvalStatus);
			mask.close();
		});
		document.getElementById('createCommentDialog_cancel').addEventListener('tap', function() {
			var elements = document.getElementsByClassName("mui-slider-handle");
			for(var key in elements){
				if(elements[key].style != undefined){
					elements[key].style.transform = "";
				}
			}
			elements = document.getElementsByClassName("mui-btn-red");
			for(var key in elements){
				if(elements[key].style != undefined){
					elements[key].style.transform = "";
				}
			}
			elements = document.getElementsByClassName("mui-table-view-cell");
			console.log(elements);
			for(var key in elements){
				if(elements[key].className != undefined){
					if(elements[key].className.indexOf("mui-selected") != -1){
						console.log(elements[key].childNodes);
						for(key2 in elements[key].childNodes){
							if(elements[key].childNodes[key2].className != undefined){
								elements[key].childNodes[key2].className = elements[key].childNodes[key2].className.replace("mui-disabled", "");
								elements[key].childNodes[key2].className = elements[key].childNodes[key2].className.replace("mui-selected", "");
							}
						}
					}
					elements[key].className = elements[key].className.replace("mui-selected", "");
					
				}
			}
			
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
function auditInvoice(reasons, type, id, mask, eleargs, approvalStatus, successCallback, failCallback) {
	if (type == "invoice") {
		mui.ajax({
			url: '/newApprovalBill',
			type: "POST",
			data: {
				openId : userOpenId,
				invoiceId : id,
				approvalStatus : approvalStatus,
				reasons:reasons
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
	} else {
		mui.ajax({
			url: '/newApprovalWork',
			type: "POST",
			data: {
				openId : userOpenId,
				workId : id,
				approvalStatus : approvalStatus,
				reasons:reasons
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
	
}
function addCommentToInvoice(type, id, eleargs, approvalStatus){
	var commentNode = document.getElementById("comment");
	var mask = mui.createProcessingMask(null);
	mask.show();
	var comment = commentNode.value;
	auditInvoice(comment, type, id, mask, eleargs, approvalStatus, function() {
		var ulNode = eleargs.parentNode;
		var personDataNode = ulNode.parentNode;
		var dataDetailNode = ulNode.parentNode.parentNode;
		ulNode.removeChild(eleargs);
		if (ulNode.firstChild == null) {
			dataDetailNode.removeChild(personDataNode);
			if (dataDetailNode.firstChild == null) {
				document.getElementById('data_details').style.display = "none";
				document.getElementById('no_data_tips').style.display = '';
			}
		}
		mask.close();
	}, function(msg) {
		mask.close();
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
function leftAndRightSliderEventCallback(element, approvalStatus) {
	var elem = element;
	var id = "";
	var type = "";
	var invoice_id = elem.getAttribute('invoice_id');
	var work_id = elem.getAttribute("work_id");
	if (invoice_id == null || invoice_id == "") {
		type = "work";
		id = work_id;
	} else {
		type ="invoice";
		id = invoice_id;
	}
	
	mui.createCommentDialog("hola", null, addCommentToInvoice, type, id, elem, approvalStatus).show();
}

function bindLeftAndRightSliderEvent() {
	mui('.mui-table-view').on('slideleft', '.mui-table-view-cell', function(event) {
		console.log("left");
		leftAndRightSliderEventCallback(this, 1);
	});
	mui('.mui-table-view').on('slideright', '.mui-table-view-cell', function(event) {
		console.log("right");
		leftAndRightSliderEventCallback(this, 0);
	});
}

function createDataList(data) {
	document.getElementById('data_loading').style.display = "none";
	document.getElementById('no_data_tips').style.display = "none";
	document.getElementById('data_details').innerHTML = "";
	var dataDetailsNode = document.getElementById('data_details');
	var img_group = 0;
		var rootNode = document.createElement('div');
		rootNode.style.marginBottom = '20px';
		var ulNode = document.createElement('div');
		ulNode.setAttribute("class", "mui-table-view");
		img_group = img_group + 1;
		for (var i in data) {
			if (data[i].upload_type == "invoice") {
				var liNode = document.createElement('li');
				liNode.setAttribute('class', 'mui-table-view-cell');
				liNode.setAttribute('invoice_id', data[i].invoice_id);
				liNode.innerHTML = "<div class='mui-slider-left mui-disabled'><a class='mui-btn mui-btn-green'>通过</a></div><div class='mui-slider-right mui-disabled'><a class='mui-btn mui-btn-red'>驳回</a></div><div class='mui-slider-handle'><img class='mui-media-object mui-pull-left' data-preview-group='" + img_group + "' data-preview-src='/files/company/" + data[i].bill_img + ".jpg' style='width:60px;height:60px;max-width:60px;border-radius: 5px;' src='/files/company/" + data[i].bill_img + "_small.jpg'><div class='mui-media-body'><div class='mui-pull-left' style='margin-top:15px;'><p>" + data[i].bill_type + "</p><p class='mui-ellipsis'>提交日期：<span>" + data[i].submit_time + "</span></p></div><div class='mui-pull-right' style='margin-top:35px;'><p>&#65509;" + data[i].bill_amount + "</p></div></div></div>";
				ulNode.appendChild(liNode);
			} 
			if (data[i].upload_type == "work_goingout") {
				var liNode = document.createElement('li');
				liNode.setAttribute('class', 'mui-table-view-cell');
				liNode.setAttribute('work_id', data[i].work_id);
				liNode.innerHTML = "<div class='mui-slider-left mui-disabled'><a class='mui-btn mui-btn-green'>通过</a></div><div class='mui-slider-right mui-disabled'><a class='mui-btn mui-btn-red'>驳回</a></div><div class='mui-slider-handle'><div style='height :65px;'><img class='mui-media-object mui-pull-left' style='width:60px;height:60px;max-width:60px;border-radius: 5px;' data-preview-group='" + img_group + "' data-preview-src='/img/weixinimg/"+data[i].img+"' src='/img/weixinimg/"+data[i].img+"'><div class='mui-media-body mui-pull-left'><div><p>&#65509;"+data[i].amount+"</p><h5   class='mui-ellipsis' style='max-width:250px;'>起始地:"+data[i].start+"</h5><h5   class='mui-ellipsis' style='max-width:250px;'>目的地:"+data[i].end+"</h5></div></div></div><div><h5><span>总公里数:"+(data[i].distance/1000).toFixed(2)+"公里</span>&nbsp;&nbsp;&nbsp;&nbsp;<span>每公里单价:"+data[i].price+"元</span></h5></div></div>";
				ulNode.appendChild(liNode);
			}
		}
		rootNode.appendChild(ulNode);
		dataDetailsNode.appendChild(rootNode);
	bindLeftAndRightSliderEvent();
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
			/*userOpenId = "oSTV_t9z_fYa7AQVYO0y5-OMFavQ";
			getNeedAuditInvoice(null);*/
//createDataList(testData);