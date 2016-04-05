var currentPage = 1;
var displayCountPerPage = 6;
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

function createDataList(data) {
	document.getElementById('data_loading').style.display = "none";
	document.getElementById('no_data_tips').style.display = "none";
	document.getElementById('cd-timeline').innerHTML = "";
	var dataDetailsNode = document.getElementById('cd-timeline');
	var img_group = 0;
	for (var i in data) {
		var timeBlockNode = document.createElement('div');
		timeBlockNode.setAttribute('class', 'cd-timeline-block');
		var timeBlockImgNode = document.createElement('div');
		timeBlockImgNode.setAttribute('class', 'cd-timeline-img');
		var timeBlockDateNode = document.createElement('div');
		timeBlockDateNode.style.marginLeft = "20px";
		timeBlockDateNode.innerHTML = data[i].submit_time;
		timeBlockNode.appendChild(timeBlockImgNode);
		timeBlockNode.appendChild(timeBlockDateNode);
		
		var timeBlockContentNode = document.createElement('div');
		timeBlockContentNode.setAttribute('class', 'cd-timeline-content');
		var timeBlockContentULNode = document.createElement('ul');
		timeBlockContentULNode.setAttribute('class', 'mui-table-view');
		timeBlockContentNode.appendChild(timeBlockContentULNode);
		timeBlockNode.appendChild(timeBlockContentNode);
		var detailDataList = data[i].list;
		for(var j in detailDataList) {
			if (detailDataList[j].upload_type == "invoice") {
				var liNode = document.createElement('li');
				liNode.setAttribute('class', 'mui-table-view-cell');
				liNode.innerHTML = "<img class='mui-media-object mui-pull-left' style='width:60px;height:60px;max-width:60px;border-radius: 5px;' data-preview-group='" + img_group + "' data-preview-src='/files/company/"+detailDataList[j].bill_img+".jpg' src='/files/company/"+detailDataList[j].bill_img+"_small.jpg'><div class='mui-media-body mui-pull-left'><div><p>&#65509;"+detailDataList[j].bill_amount+"</p><h5>"+detailDataList[j].bill_type+"</h5><p class='mui-ellipsis'>审核人：<span>"+detailDataList[j].approval_name+"</span></p></div></div><span class='mui-pull-right mui-icon iconfont icon-Audit' style='width:60px;height:50px;max-width:60px;font-size:50px;margin-top:15px;margin-right:5px;position: fixed;position: absolute;left:70%;color:black;'>";
				timeBlockContentULNode.appendChild(liNode);
			} else if (detailDataList[j].upload_type == "work_goingout") {
				var liNode = document.createElement('li');
				liNode.setAttribute('class', 'mui-table-view-cell');
				liNode.innerHTML = "<div style='height :65px;'><img class='mui-media-object mui-pull-left' style='width:60px;height:60px;max-width:60px;border-radius: 5px;' data-preview-group='" + img_group + "' data-preview-src='/img/weixinimg/"+detailDataList[j].img+"' src='/img/weixinimg/"+detailDataList[j].img+"'><div class='mui-media-body mui-pull-left'><div><p>&#65509;"+detailDataList[j].amount+"</p><h5>总公里数:"+detailDataList[j].distance+"</h5><h5>每公里单价:"+detailDataList[j].price+"</h5></div></div><span class='mui-pull-right mui-icon iconfont icon-Audit' style='width:60px;height:50px;max-width:60px;font-size:50px;margin-top:15px;margin-right:5px;position: fixed;position: absolute;left:70%;color:black;'></div><div><h5   class='mui-ellipsis' style='max-width:250px;'>起始地:"+detailDataList[j].start+"</h5><h5   class='mui-ellipsis' style='max-width:250px;'>目的地:"+detailDataList[j].end+"</h5><h5>审批人:"+detailDataList[j].approvalName+"</h5></div>";
				timeBlockContentULNode.appendChild(liNode);
			}
		}
		img_group = img_group + 1;
		dataDetailsNode.appendChild(timeBlockNode);
	}
	document.getElementById('cd-timeline').style.display = "";
}

function getWaitAuditInvoice(ajaxCallBack) {
	mui.ajax({
		url: '/getWaitAuditInvoices?openId=' + userOpenId,
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
			document.getElementById('cd-timeline').style.display = "none";
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
			getWaitAuditInvoice(ajaxCallBack);
		}
	}
});
/*userOpenId = "oSTV_t9z_fYa7AQVYO0y5-OMFavQ";
getWaitAuditInvoice(null);*/
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
			getWaitAuditInvoice(null);
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