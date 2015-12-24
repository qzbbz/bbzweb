function checkJsonIsEmpty(json) {
	var isEmpty = true;
	if(json == null) return true;
	for (var jsonKey in invoiceList) {
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
		progressNode.style.marginLeft = window.screen.availWidth/2-50 + "px";
		progressNode.style.marginTop = window.screen.availHeight/2-50 + "px";
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

document.getElementById("fapiaoluru_addInvoiceImage").onchange = function(event) {
	document.getElementById('tips_image').style.display = 'none';
	document.getElementById('invoice_img_list_root_div').style.display = '';
	document.getElementById('invoice_img_list_root_div').style.height = window.screen.availHeight - 198 + "px";
	var imageRootNode = document.getElementById('invoice_img_list_ul');
	var liNode = document.createElement('li');
	liNode.setAttribute("class", "mui-table-view-cell mui-media mui-col-xs-6");
	var imgNode = document.createElement('img');
	imgNode.setAttribute("class", "mui-media-object");
	imgNode.style.width = '300px';
	imgNode.style.height = '200px';
	imgNode.setAttribute('data-preview-group', '1');
	imgNode.setAttribute('data-preview-src', '');
	liNode.appendChild(imgNode);
	imageRootNode.appendChild(liNode);
	var files = event.target.files,
		file;
	if (files && files.length > 0) {
		file = files[0];
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
	mask.show();
	$('#upload_progress').find('strong').html(0 + '<i>%</i>');
	var formData = {openId:userOpenId};
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
				document.getElementById("add_invoice_page").style.display = "";
				document.getElementById("tips_image").style.display = "";
				document.getElementById("fapiaoluru_submit").style.display = "";
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