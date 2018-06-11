var local_data;
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
mui.initPreviewImage();
mui.previewImage({
	isUpload: true
});

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
/*document.getElementById('company_select').addEventListener('tap', function() {
	companyPicker.show(function(rs) {
		document.getElementById('companyName').innerHTML = rs[0].text;
		document.getElementById('userId').value = rs[0].value;
    	document.getElementById("tips_info").style.display = "none";
		document.getElementById("data_loading").style.display = "none";
	});
}, false);*/
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