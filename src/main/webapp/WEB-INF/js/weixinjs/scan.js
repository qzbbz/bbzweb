var local_data;
var code = [ "144031539110","131001570151","133011501118","111001571071"];
function alxd(zCGm42) {
	var LgiBIQrTk43;
	var KIFMnvh44 = "99";
	if (zCGm42["length"] == 12) {
		LgiBIQrTk43 = zCGm42["substring"](7, 8);
    for (var VznVTyb45 = 0; VznVTyb45 < code["length"]; VznVTyb45++) {
      if (zCGm42 == code[VznVTyb45]) {
        KIFMnvh44 = "10";
        break
      }
    }
    if (KIFMnvh44 == "99") {
      if (zCGm42["charAt"](0) == "0"
        && zCGm42["substring"](10, 12) == "11") {
        KIFMnvh44 = "10"
      }
      if (zCGm42["charAt"](0) == "0"
        && (zCGm42["substring"](10, 12) == "04" || zCGm42["substring"]
          (10, 12) == "05")) {
        KIFMnvh44 = "04"
      }
      if (zCGm42["charAt"](0) == "0"
        && (zCGm42["substring"](10, 12) == "06" || zCGm42["substring"]
          (10, 12) == "07")) {
        KIFMnvh44 = "11"
      }
      if (zCGm42["charAt"](0) == "0"
        && zCGm42["substring"](10, 12) == "12") {
        KIFMnvh44 = "14"
      }
    }
    if (KIFMnvh44 == "99") {
      if (zCGm42["substring"](10, 12) == "17"
        && zCGm42["charAt"](0) == "0") {
        KIFMnvh44 = "15"
      }
      if (KIFMnvh44 == "99" && LgiBIQrTk43 == 2
        && zCGm42["charAt"](0) != "0") {
        KIFMnvh44 = "03"
      }
    }
  } else if (zCGm42["length"] == 10) {
    LgiBIQrTk43 = zCGm42["substring"](7, 8);
    if (LgiBIQrTk43 == 1 || LgiBIQrTk43 == 5) {
      KIFMnvh44 = "01"
    } else if (LgiBIQrTk43 == 6 || LgiBIQrTk43 == 3) {
      KIFMnvh44 = "04"
    } else if (LgiBIQrTk43 == 7 || LgiBIQrTk43 == 2) {
      KIFMnvh44 = "02"
    }
  }
  return KIFMnvh44
}
var companyPicker = new mui.PopPicker();
Date.prototype.Format = function(formatStr) {
	var str = formatStr;
	var Week = ['日', '一', '二', '三', '四', '五', '六'];

	str = str.replace(/yyyy|YYYY/, this.getFullYear());
	str = str.replace(/yy|YY/, (this.getYear() % 100) > 9 ? (this.getYear() % 100).toString() : '0' + (this.getYear() % 100));

	str = str.replace(/MM/, (this.getMonth() + 1) > 9 ? (this.getMonth() + 1).toString() : '0' + (this.getMonth()+1));
	str = str.replace(/M/g, this.getMonth());

	str = str.replace(/w|W/g, Week[this.getDay()]);

	str = str.replace(/dd|DD/, this.getDate() > 9 ? this.getDate().toString() : '0' + this.getDate());
	str = str.replace(/d|D/g, this.getDate());

	str = str.replace(/hh|HH/, this.getHours() > 9 ? this.getHours().toString() : '0' + this.getHours());
	str = str.replace(/h|H/g, this.getHours());
	str = str.replace(/mm/, this.getMinutes() > 9 ? this.getMinutes().toString() : '0' + this.getMinutes());
	str = str.replace(/m/g, this.getMinutes());

	str = str.replace(/ss|SS/, this.getSeconds() > 9 ? this.getSeconds().toString() : '0' + this.getSeconds());
	str = str.replace(/s|S/g, this.getSeconds());

	return str;
}
function checkJsonIsEmpty(json) {
	var isEmpty = true;
	if(json == null) return true;
	for (var jsonKey in invoiceList) {
		isEmpty = false;
		break;
	}
	return isEmpty;
}
var createFragment = function(data) {
	var li = '';
	for (var i = 0; i < data.length; i++) {
		var jsonData = JSON.stringify(data[i]);
		var item = "<li class='mui-table-view-cell mui-media' data='"+jsonData+"'><a class='mui-navigate-right'><img class='mui-media-object mui-pull-left' src='/img/weixinimg/timg.jpg'><div class='mui-media-body'><p class='mui-ellipsis'>发票代码："+data[i].fpdm+"</p><p class='mui-ellipsis'>发票号码："+data[i].fphm+"</p></div></a></li>";
		li += item;
	}
	return li;
};
function init_data() {
	$.ajax({
		url: 'http://www.bangbangzhang.com:8088/weixin_gzh_rec/wechat/getInvoice', 
		headers: {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		},
		data: {user_id: accounter_user_id, page : 1}, 
		success: function(data) {
			index_ = 2;
			var ul = document.querySelector('.mui-table-view');
			var ulHtml = createFragment(data);
			ul.innerHTML = ulHtml;
			mui(".mui-table-view").on('tap','.mui-media',function(){
				var data = this.getAttribute("data");
				var obj = JSON.parse(data);
				document.getElementById("fpdm").value = obj.fpdm;
				document.getElementById("fphm").value = obj.fphm;
				document.getElementById("kprq").value = obj.kprq;
				document.getElementById("jym").value = obj.jym;
				document.getElementById("kpje").value = obj.kpje;
				document.getElementById("bill_date").value = obj.bill_date;
				document.getElementById("is_fa").value = obj.is_fixed_assets;
				document.getElementById("invoice_id").value = obj.id;
				document.querySelector(".mui-modal").classList.add("mui-active");
			});
		}
	});
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
mui.init();
mui('.mui-scroll-wrapper').scroll();

var userOpenId = "";
var currentDate = new Date();
currentDate = currentDate.Format("yyyy-MM");
document.getElementById('invoice_date').innerText = currentDate;
var options = {
	'type': 'month',
	'value': currentDate
};
var datePicker = new mui.DtPicker(options);
document.getElementById('select_date').addEventListener('tap', function() {
	datePicker.show(function(rs) {
		document.getElementById('invoice_date').innerText = rs.text;
	});
}, false);

function getImageDataURL(img) {
	var canvas = document.createElement('canvas');
	canvas.width = img.width;
	canvas.height = img.height;
	var context = canvas.getContext('2d');
	context.drawImage(img, 0, 0);
	var dataURL = canvas.toDataURL('image/png');
	return dataURL;
}

var globalTypeId;
var adminUserId;
var accounter_user_id = '';
var index_ = 1;
mui.ajax({
    type : "POST", 
    url  : "/getUserOpenIdAndBindCompanys",
    success : function(data) {
    	if (data.error_code != '0') {
			/*mui.createTipDialog(data.error_message,null).show();
			document.getElementById("data_loading").style.display = "none";
			document.getElementById("tips_info_detail").innerHTML = data.error_message;
	    	document.getElementById("tips_info").style.display = "";*/
		} else {
			local_data = data.companyBriefInfoList;
			userOpenId = data.openId;
			accounter_user_id = data.accounter_user_id;
			console.log('accounter_user_id : ' + accounter_user_id);
			init_data();
			/*globalTypeId = data.type_id;
			companyPicker.setData(data.companyBriefInfoList);
			document.getElementById('companyName').innerHTML = data.companyBriefInfoList[0].text;
    		document.getElementById('userId').value = data.companyBriefInfoList[0].value;
			document.getElementById("data_loading").style.display = "none";
			document.getElementById("select_date").style.display = "";*/
		}
    }, 
    error : function() {
    	mui.createTipDialog('服务器暂时无法响应请求，请稍后重试！',null).show();
    	document.getElementById("data_loading").style.display = "none";
    	document.getElementById("tips_info_detail").innerHTML = "服务器暂时无法响应请求，<br/>请稍后重试！";
    	document.getElementById("tips_info").style.display = "";
    } 
});

window.onload = function () {
    var u = navigator.userAgent;
    if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) {//安卓手机
    	$("#os_andr").prepend("<input id='fapiaoluru_addInvoiceImage' type='file' capture='camera' accept='image/*' multiple='true' hidefocus='true'>");
    // window.location.href = "mobile/index.html";
    } else if (u.indexOf('iPhone') > -1) {//苹果手机
    // window.location.href = "mobile/index.html";
    	$("#os_andr").prepend("<input id='fapiaoluru_addInvoiceImage' type='file' accept='image/*;capture=camera' multiple='true' hidefocus='true'>");
    } else if (u.indexOf('Windows Phone') > -1) {//winphone手机
    	$("#os_andr").prepend("<input id='fapiaoluru_addInvoiceImage' type='file' capture='camera' accept='image/*;' multiple='true' hidefocus='true'>");
    }
}