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
			mask.close();
			if (cancelCallBack) cancelCallBack();
		});
		document.getElementById('createConfirmDialog_accept').addEventListener('tap', function() {
			mask.close();
			if (acceptCallBack) acceptCallBack();
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

var userOpenId = "";
var userName = "";
var userEmail = "";
var companyName = "";
var companyDepartmentName = "";
String.prototype.replaceAll = function(s1,s2) { 
    return this.replace(new RegExp(s1,"gm"),s2); 
}
function bindCompany() {
	var inviteCode = document.getElementById("invite_code").value;
	inviteCode = inviteCode.replaceAll("，", ",");
	if(inviteCode == null || inviteCode == "") {
		mui.createTipDialog('邀请码不能为空，请检查！',null).show();
		return;
	}
	var user_name = document.getElementById("user_name").value;
	var user_email = document.getElementById("user_email").value;
	var mask = mui.createProcessingMask(null);
	mask.show();
	mui.ajax({
		url: '/userBindCompany',
		type: "POST",
		data: {
			openId: userOpenId, inviteCode:inviteCode, name:user_name, email:user_email
		},
		success: function(data) {
			mask.close();
			if (data.error_code != "0") {
				mui.createTipDialog('无法获取您的微信Openid,请稍后重试！',null).show();
			} else if(data.invite_code_error != null ){
				mui.createTipDialog('您填写的邀请码不正确，请检查！',null).show();
			} else {
				mui.createTipDialog('绑定公司成功！',null).show();
				userName = data.userName;
				userEmail = data.userMsgEmail;
				var deptIndex = 0;
				for(var i in data.companyName) {
					companyName = companyName + data.companyName[i].text + "[" + data.deptName[deptIndex++] + "]" + "<br/>";
				}
				document.getElementById("bind_company_page").style.display = "none";
				document.getElementById("comapny_name").innerHTML = companyName;
				document.getElementById("modify_user_name").value = userName;
				document.getElementById("modify_user_email").value = userEmail;
				document.getElementById("has_bind_company_page").style.display = "";
			}
		},
		error: function(status, error) {
			mask.close();
			mui.createTipDialog('服务器暂时无法响应您的请求，请稍后重试！',null).show();
		}
	});
}

function unBindCompany() {
	mui.createConfirmDialog('您确定要解除绑定吗？',
		function(){},
		function(){
			var mask = mui.createProcessingMask(null);
			mask.show();
			mui.ajax({
				url: '/userDisbindCompany',
				type: "POST",
				data: {
					openId: userOpenId
				},
				success: function(data) {
					mask.close();
					if (data.error_code != "0") {
						mui.createTipDialog('无法获取您的微信Openid,请稍后重试！',null).show();
					} else {
						mui.createTipDialog('解除绑定成功！',null).show();
						userName = "";
						userEmail = "";
						companyName = "";
						companyDepartmentName = "";
						document.getElementById("bind_company_page").style.display = "";
						document.getElementById("has_bind_company_page").style.display = "none";
					}
				},
				error: function(status, error) {
					mask.close();
					mui.createTipDialog('服务器暂时无法响应您的请求，请稍后重试！',null).show();
				}
			});
		}
	).show();
}

function saveInfo() {
	var mask = mui.createProcessingMask(null);
	mask.show();
	var modify_user_name = document.getElementById("modify_user_name").value;
	var modify_user_email = document.getElementById("modify_user_email").value;
	mui.ajax({
		url: '/updateUserInfo',
		type: "POST",
		data: {
			openId:userOpenId, name:modify_user_name, email:modify_user_email
		},
		success: function(data) {
			mask.close();
			if (data.error_code != "0") {
				mui.createTipDialog(data.error_message,null).show();
			} else {
				mui.createTipDialog('保存信息成功',null).show();
				userName = data.userName;
				userEmail = data.userMsgEmail;
				document.getElementById("modify_user_name").value = userName;
				document.getElementById("modify_user_email").value = userEmail;
			}
		},
		error: function(status, error) {
			mask.close();
			mui.createTipDialog('服务器暂时无法响应您的请求，请稍后重试！',null).show();
		}
	});
}
userOpenId = "oSTV_t9PTe4XeEhsMXdAR7izvQWA";
document.getElementById("data_loading").style.display = "none";
document.getElementById("bind_company_page").style.display = "";
/*mui.ajax({ 
    type : "POST", 
    url  : "/getUserOpenIdAndCheckBindCompany",
    data : {type:"0"}, 
    success : function(data) {
    	if (data.error_code != '0') {
			mui.createTipDialog(data.error_msg, null).show();
			document.getElementById("data_loading").style.display = "none";
			document.getElementById("tips_info_detail").innerHTML = data.error_msg + ",<br/>请稍后重试！";
	    	document.getElementById("tips_info").style.display = "";
		} else {
			userOpenId = data.openId;
			if(data.bind_status == "has_bind") {
				userName = data.userName;
				userEmail = data.userMsgEmail;
				var deptIndex = 0;
				for(var i in data.companyName) {
					companyName = companyName + data.companyName[i].text + "[" + data.deptName[deptIndex++] + "]" + "<br/>";
				}
				document.getElementById("data_loading").style.display = "none";
				document.getElementById("comapny_name").innerHTML = companyName;
				document.getElementById("modify_user_name").value = userName;
				document.getElementById("modify_user_email").value = userEmail;
				document.getElementById("has_bind_company_page").style.display = "";
			} else {
				document.getElementById("data_loading").style.display = "none";
				document.getElementById("bind_company_page").style.display = "";
			}
		}
    }, 
    error : function() {
    	mui.createTipDialog('服务器暂时无法响应请求，请稍后重试！',null).show();
    	document.getElementById("data_loading").style.display = "none";
    	document.getElementById("tips_info_detail").innerHTML = "服务器暂时无法响应请求，<br/>请稍后重试！";
    	document.getElementById("tips_info").style.display = "";
    } 
});*/