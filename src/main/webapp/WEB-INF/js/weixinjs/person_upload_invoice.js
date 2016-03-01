function checkJsonIsEmpty(json) {
	var isEmpty = true;
	if(json == null) return true;
	for (var jsonKey in json) {
		isEmpty = false;
		break;
	}
	return isEmpty;
}

mui.createUploadMask = function(callback) {
	var element = document.createElement('div');
	element.classList.add('upload-file');	
	element.addEventListener('touchmove', mui.preventDefault);
	element.addEventListener('tap', function() {
		//mask.close();
	});
	var progressNode = document.createElement('div');
	progressNode.id="upload_progress";
	progressNode.setAttribute('class', 'circle');
	progressNode.innerHTML = "<strong></strong>";
	element.appendChild(progressNode);
	var mask = [element];
	mask._show = false;
	mask.show = function() {
		mask._show = true;
		element.setAttribute('style', 'opacity:1');
		progressNode.style.marginLeft = document.body.clientWidth/2-50 + "px";
		progressNode.style.marginTop = document.documentElement.clientHeight /2 -50 + "px";
		document.body.appendChild(element);
		$('#upload_progress').circleProgress({
	    	value: 0,
	    	emptyFill: 'rgba(74, 197, 248, 1)',
	    	animation: false,
	    	fill: { gradient: ["red", "blue", "green"], gradientAngle: Math.PI / 4 }	
	    }).on('circle_progress_percent', function(event, progress) {
	        $(this).find('strong').html(parseInt(100 * progress) + '<i>%</i>');
	        $('#upload_progress').circleProgress('value', progress);
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

mui.createFillInfoDialog = function(info, cancelFillInCallBack, acceptFillInCallBack){
	var template = "<div style='width:80%;margin:50% 10%;border:1px solid #ddd;background-color: white;border-radius: 5px;'><div style='margin-top:20px;margin-left:20px;'>提示信息</div><hr/><div style='margin-top:20px;margin-left:20px;margin-bottom:20px;margin-right:20px;height:60px;'>{{info}}</div><div style='text-align:right;margin-bottom:20px;margin-right:20px;'><a id='createFillInDialog_cancel' href='javascript:void(0);' style='margin-right:20px;text-decoration:none;'>取消</a><a id='createFillInDialog_accept' href='javascript:void(0);' style='text-decoration:none;'>确定</a></div></div>";
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
		document.getElementById('createFillInDialog_cancel').addEventListener('tap', function() {
			if (cancelCallBack) cancelCallBack();
			mask.close();
		});
		document.getElementById('createFillInDialog_accept').addEventListener('tap', function() {
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
}

mui.init();
mui('.mui-scroll-wrapper').scroll();
mui.initPreviewImage();
mui.previewImage({
	isUpload: true
});

var userOpenId = "";

function getImageDataURL(img) {
	var canvas = document.createElement('canvas');
	canvas.width = img.width;
	canvas.height = img.height;
	var context = canvas.getContext('2d');
	context.drawImage(img, 0, 0);
	var dataURL = canvas.toDataURL('image/png');
	return dataURL;
}

function fillInInformation(){
	console.log(this);
	mui.createFillInfoDialog("hola", null).show();
}
var invoiceTypePicker = new mui.PopPicker();
mui.ajax({
	type: "POST",
	url: "/getAllExpenseTypes",
	data: {},
	success : function(data){
		if(!checkJsonIsEmpty(data)) {
			var expenseType = new Array();
			for(var key in data) {
				expenseType.push({value:key,text:data[key]});
			}
			invoiceTypePicker.setData(expenseType);
		}
	}		
});

var inputIndex = 0;
document.getElementById("fapiaoluru_addInvoiceImage").onchange = function(event) {
	inputIndex = inputIndex + 1;
	document.getElementById('tips_image').style.display = 'none';
	document.getElementById('invoice_img_list_root_div').style.display = '';
	/*document.getElementById('invoice_img_list_root_div').style.height = window.screen.availHeight - 198 + "px";*/
	var imageRootNode = document.getElementById('invoice_img_list_ul');	
	/*var liNode = document.createElement('li');
	liNode.setAttribute("class", "mui-media mui-table-view-cell");
	var imgNode = document.createElement('img');
	imgNode.setAttribute('class', 'mui-pull-left');
	imgNode.setAttribute('data-preview-group', '1');
	imgNode.setAttribute('data-preview-src', '');
	imgNode.style.width = '100px';
	imgNode.style.height = '80px';
	imgNode.style.maxWidth = '100px';
	liNode.appendChild(imgNode);
	var inputRootNode = document.createElement('div');
	inputRootNode.setAttribute('class', 'mui-media-body');
	var inputAmountNode = document.createElement('div');
	inputAmountNode.style.marginTop = "0px";
	inputAmountNode.style.marginLeft = "5px";
	inputAmountNode.style.height = "45px";
	inputAmountNode.style.color = "#8f8f94";
	//inputAmountNode.innerHTML = "发票金额:<span class='mui-pull-right' ><span class='fapiaoluru_amount' id='fapiaoluru_amount_"+inputIndex+"'>0</span><span class='mui-icon mui-icon-arrowright' style='font-size: 20px;margin-top:0px;display:inline-block'></span></span>";
	inputAmountNode.innerHTML = "<span>发票金额:</span><span class='mui-pull-right' style='height:20px;'><input class='fapiaoluru_amount' type='text' style='width:100%;height:20px;padding-top:0px;padding-bottom:0px;'/></span>";
	var inputTypeNode = document.createElement('div');
	inputTypeNode.style.marginTop = "5px";
	inputTypeNode.style.marginLeft = "5px";
	inputTypeNode.style.color = "#8f8f94";
	inputTypeNode.innerHTML = "发票类型:<span class='mui-pull-right' ><span class='fapiaoluru_type' id='fapiaoluru_type_"+inputIndex+"'>选择</span><span class='mui-icon mui-icon-arrowright' style='font-size: 20px;margin-top:0px;display:inline-block'></span></span>";
	inputRootNode.appendChild(inputAmountNode);
	inputRootNode.appendChild(inputTypeNode);
	liNode.appendChild(inputRootNode);
	inputTypeNode.addEventListener('tap', function(event) {
		var ele = this.childNodes[1].childNodes[0];
		invoiceTypePicker.show(function(items) {
			ele.innerText = items[0].text;
		});
	}, false);
	imageRootNode.appendChild(liNode);*/
	var files = event.target.files,
		file;
	if (files && files.length > 0) {
		for(var i=0; i<files.length; i++) {
			var liNode = document.createElement('li');
			liNode.setAttribute("class", "mui-media mui-table-view-cell");
			var imgNode = document.createElement('img');
			imgNode.setAttribute('class', 'mui-pull-left');
			imgNode.setAttribute('data-preview-group', '1');
			imgNode.setAttribute('data-preview-src', '');
			imgNode.style.width = '100px';
			imgNode.style.height = '80px';
			imgNode.style.maxWidth = '100px';
			liNode.appendChild(imgNode);
			var inputRootNode = document.createElement('div');
			inputRootNode.setAttribute('class', 'mui-media-body');
			var inputAmountNode = document.createElement('div');
			inputAmountNode.style.marginTop = "0px";
			inputAmountNode.style.marginLeft = "5px";
			inputAmountNode.style.height = "45px";
			inputAmountNode.style.color = "#8f8f94";
			//inputAmountNode.innerHTML = "发票金额:<span class='mui-pull-right' ><span class='fapiaoluru_amount' id='fapiaoluru_amount_"+inputIndex+"'>0</span><span class='mui-icon mui-icon-arrowright' style='font-size: 20px;margin-top:0px;display:inline-block'></span></span>";
			inputAmountNode.innerHTML = "<span>发票金额:</span><span class='mui-pull-right' style='height:20px;'><input class='fapiaoluru_amount' type='text' style='width:100px;height:20px;padding-top:0px;padding-bottom:0px;'/></span>";
			var inputTypeNode = document.createElement('div');
			inputTypeNode.style.marginTop = "5px";
			inputTypeNode.style.marginLeft = "5px";
			inputTypeNode.style.color = "#8f8f94";
			inputTypeNode.innerHTML = "发票类型:<span class='mui-pull-right' ><span class='fapiaoluru_type' id='fapiaoluru_type_"+inputIndex+"'>选择</span><span class='mui-icon mui-icon-arrowright' style='font-size: 20px;margin-top:0px;display:inline-block'></span></span>";
			inputRootNode.appendChild(inputAmountNode);
			inputRootNode.appendChild(inputTypeNode);
			liNode.appendChild(inputRootNode);
			inputTypeNode.addEventListener('tap', function(event) {
				var ele = this.childNodes[1].childNodes[0];
				invoiceTypePicker.show(function(items) {
					ele.innerText = items[0].text;
				});
			}, false);
			imageRootNode.appendChild(liNode);
			file = files[i];
			try {
				var URL = window.URL || window.webkitURL;
				imgURL = URL.createObjectURL(file);
				imgNode.src = imgURL;
				invoiceList[imgURL] = document.getElementById('fapiaoluru_addInvoiceImage').files[0];
				//URL.revokeObjectURL(imgURL);
			} catch (e) {
				try {
					var fileReader = new FileReader();
					fileReader.onload = function(event) {
						imgNode.src = event.target.result;
						invoiceList[imgNode.src] = document.getElementById('fapiaoluru_addInvoiceImage').files[0];
					};
					fileReader.readAsDataURL(file);
				} catch (e) {
					alert("Neither createObjectURL or FileReader are supported");
				}
			}
		}
	}
}

document.getElementById('fapiaoluru_submit').addEventListener('tap', function(event) {
	var isEmpty = true;
	for (var jsonKey in invoiceList) {
		isEmpty = false;
		break;
	}
	if (isEmpty) {
		mui.createTipDialog('您还没有添加发票图像，请先添加!',null).show();
		return;
	}
	var mask = mui.createUploadMask(false);

	$('#upload_progress').find('strong').html(0 + '<i>%</i>');
	var amounts = document.getElementsByClassName('fapiaoluru_amount');
	var types = document.getElementsByClassName('fapiaoluru_type');
	var typesStr = "";
	var amountsStr = "";
	for(var i=0; i<amounts.length; i++) {
		if(isNaN(amounts[i].value) || amounts[i].value == "") {
			mui.createTipDialog('上传的发票金额必须为数字且不能为空，请检查!',null).show();
			return;
		}
		amountsStr += amounts[i].value + ";";
		if(parseInt(amounts[i].value) <= 0) {
			mui.createTipDialog('上传的发票金额必须不小于0，请检查!',null).show();
			return;
		}
	}
	for(var i=0; i<types.length; i++) {
		if(types[i].innerHTML == "选择") {
			mui.createTipDialog('上传的发票类型必须指定，请检查!',null).show();
			return;
		}
		typesStr += types[i].innerHTML + ";";
	}
	mask.show();
	var formData = {openId:userOpenId, expense_amount:amountsStr, expense_type:typesStr};
	var xhr = new XMLHttpRequest();
	var fd = new FormData();
	for (var jsonKey in formData) {
		fd.append(jsonKey, formData[jsonKey]);
	}
	for (var jsonKey in invoiceList) {
		fd.append("files", invoiceList[jsonKey]);
	}
	xhr.upload.addEventListener("progress", function(evt) {
		if (evt.lengthComputable) {
			var percentComplete = Math.round(evt.loaded * 100 / evt.total) / 100;
			$('#upload_progress').trigger('circle_progress_percent', [percentComplete]);
		}
	}, false);
	xhr.addEventListener("load", function(evt) {
		var res = evt.target.responseText;
		res = JSON.parse(res);
		if (res.error_code == "0") {
			mui.createTipDialog('发票上传成功!',null).show();
			invoiceList = {};
			totalCount = 0;
			document.getElementById('tips_image').style.display = "";
			document.getElementById('invoice_img_list_ul').innerText = "";
			document.getElementById('invoice_img_list_root_div').style.display = "none";
		} else {
			mui.createTipDialog(res.error_message, null).show();
		}
		mask.close();
	}, false);
	xhr.addEventListener("error", function(evt) {
		mui.createTipDialog('发票上传失败!，请稍后重试',null).show();
		mask.close();
	}, false);
	xhr.addEventListener("abort", null, false);
	xhr.open("POST", "/uploadPersonInvoice");
	xhr.send(fd);
}, false);
/*document.getElementById("data_loading").style.display = "none";
document.getElementById("add_invoice_page").style.display = "";
document.getElementById("tips_image").style.display = "";
document.getElementById("fapiaoluru_submit").style.display = "";*/
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
				if(data.type_id != "5") {
					document.getElementById("data_loading").style.display = "none";
					document.getElementById("tips_info_detail").innerHTML = "您没有权限上传个人发票，<br/>请联系管理员！";
			    	document.getElementById("tips_info").style.display = "";
				} else {
					document.getElementById("data_loading").style.display = "none";
					document.getElementById("add_invoice_page").style.display = "";
					document.getElementById("tips_image").style.display = "";
					document.getElementById("fapiaoluru_submit").style.display = "";
				}
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