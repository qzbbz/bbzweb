<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@ page import="org.springframework.web.context.WebApplicationContext"%>
<%@ page import="java.util.*"%>
<%@ page import="com.wisdom.weixin.utils.WeixinTools"%>
<%
	WebApplicationContext wac = WebApplicationContextUtils
			.getRequiredWebApplicationContext(this.getServletContext());
	StringBuffer url = new StringBuffer();
	if (request.getQueryString() != null) {
		url.append('?');
		url.append(request.getQueryString());
	}
	String jsUrl = url.toString();
	Map<String, String> result = new HashMap<>();
	result = WeixinTools.getSign("http://www.bangbangzhang.com/getOpenIdRedirect" + jsUrl);
%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<title></title>
<meta name="viewport"
	content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
<title></title>
<link href="/css/weixincss/mui.min.new.css" rel="stylesheet" />
<link href="/css/weixincss/iconfont.css" rel="stylesheet" />
<link href="/css/weixincss/mui.picker.min.css" rel="stylesheet" />
<link href="/css/weixincss/mui.poppicker.css" rel="stylesheet" />
<link href="/css/weixincss/circle-progress.css" rel="stylesheet" />
<link href="/css/weixincss/scan.css" rel="stylesheet" />
<link href="/css/weixincss/app.css" rel="stylesheet" />
<style>
<
style>html, body {
	background-color: #efeff4;
}

.mui-content .mui-fullscreen {
	top: 44px;
	height: 100%;
}

.mui-pull-top-tips {
	position: absolute;
	top: -20px;
	left: 50%;
	margin-left: -25px;
	width: 40px;
	height: 40px;
	border-radius: 100%;
	z-index: 100000;
}

.mui-pull-top-tips {
	top: 24px;
}

.mui-pull-top-wrapper {
	width: 42px;
	height: 42px;
	display: block;
	text-align: center;
	background-color: #efeff4;
	border: 1px solid #ddd;
	border-radius: 25px;
	background-clip: padding-box;
	box-shadow: 0 4px 10px #bbb;
	overflow: hidden;
}

.mui-pull-top-tips.mui-transitioning {
	-webkit-transition-duration: 200ms;
	transition-duration: 200ms;
}

.mui-pull-top-tips .mui-pull-loading {
	/*-webkit-backface-visibility: hidden;
				-webkit-transition-duration: 400ms;
				transition-duration: 400ms;*/
	margin: 0;
}

.mui-pull-top-wrapper .mui-icon, .mui-pull-top-wrapper .mui-spinner {
	margin-top: 7px;
}

.mui-pull-top-wrapper .mui-icon.mui-reverse {
	/*-webkit-transform: rotate(180deg) translateZ(0);*/
	
}

.mui-pull-bottom-tips {
	text-align: center;
	background-color: #efeff4;
	font-size: 15px;
	line-height: 40px;
	color: #777;
}

.mui-pull-top-canvas {
	overflow: hidden;
	background-color: #fafafa;
	border-radius: 40px;
	box-shadow: 0 4px 10px #bbb;
	width: 40px;
	height: 40px;
	margin: 0 auto;
}

.mui-pull-top-canvas canvas {
	width: 40px;
}

.mui-slider-indicator.mui-segmented-control {
	background-color: #efeff4;
}
</style>
</head>

<body>
	<header class="mui-bar mui-bar-nav">
		<h1 class="mui-title">会计上传发票</h1>
	</header>
	<nav class="mui-bar mui-bar-tab">
		<a class="mui-tab-item mui-active" href="#upload"> <span
			class="mui-icon mui-icon-camera"></span> <span class="mui-tab-label">上传</span>
		</a> <a class="mui-tab-item" href="#not_rec"> <span
			class="mui-icon mui-icon-loop"></span> <span class="mui-tab-label">未识别</span>
		</a>
	</nav>
	<div class="mui-content mui-fullscreen">
		<div class="mui-content mui-control-content mui-active" id="upload">
			<div class="mui-card"
				style="margin: 10px 5px; height: 40px; line-height: 40px;">
				<div class="mui-pull-left">
					<p style="margin-left: 15px;">公司ID</p>
				</div>
				<div class="mui-pull-right">
					<input id="company_id" type="text"
						style="border: 1px solid rgba(0, 0, 0, 0); text-align: right" />
					<!-- <span id="companyName" style="color:#8f8f94;"></span><span class="mui-icon mui-icon-arrowright" style="font-size: 20px;margin-top:0px;color:#8f8f94;display:inline-block"></span> -->
					<!-- <input id="userId" type="hidden" class="mui-input-clear" value=""> -->
				</div>
			</div>
			<div class="mui-card" id="select_date"
				style="margin: 10px 5px; height: 40px; line-height: 40px;">
				<div class="mui-pull-left">
					<p style="margin-left: 15px;">发票日期</p>
				</div>
				<div class="mui-pull-right">
					<span id="invoice_date" style="color: #8f8f94;">选择</span><span
						class="mui-icon mui-icon-arrowright"
						style="font-size: 20px; margin-top: 0px; color: #8f8f94; display: inline-block"></span>
				</div>
			</div>
			<div class="mui-card"
				style="margin: 10px 5px; height: 40px; line-height: 40px;">
				<div class="mui-pull-left">
					<p style="margin-left: 15px;">固定资产</p>
				</div>
				<div class="mui-pull-right">
					<input type="radio" name="isFa" value='1'>是 <input
						type="radio" name="isFa" value='0' style="margin-left: 10px">否
				</div>
			</div>
			<div style="text-align: center;" class="scan_div">
				<button class="btn btn_primary" id="scanQRCode1">扫描发票二维码</button>
				<a class="chooseImg" href="#"
					style="color: rgb(45, 140, 240); font-size: 13px">无法识别二维码？拍照上传</a>
				<div id="os_andr" style="display:none"></div>
			</div>
			<!-- <div id="data_loading" class="mui-loading" style="margin-top:50%;">
				<div class="mui-spinner"></div>
				<div style="text-align: center;">正在加载数据</div>
			</div> -->
			<div id="tips_info" style="display: none; margin-top: 50%;">
				<div style="text-align: center;" id="tips_info_detail">
					服务器暂时无法处理请求，<br />请稍后重试！
				</div>
			</div>
		</div>
		<!-- <div id="upload" class="mui-control-content mui-active">
			<div>测试页面</div>
			<div id="btn">点击打开对话框</div>
		</div> -->
		<div id="not_rec" class="mui-control-content">
			<div class="mui-scroll-wrapper">
				<div class="mui-scroll">
					<ul class="mui-table-view">
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="mui-modal">
		<div class="mui-pull-right close_modal"><span
			class="mui-icon mui-icon-close" style="font-size:40px;margin-top:10px"></span>
		</div>
		<div class="mui-input-group" style="margin-top:60px">
			<div class="mui-input-row">
				<label>发票代码：</label> <input type="text" class="mui-input-clear" id="fpdm">
			</div>
			<div class="mui-input-row">
				<label>发票号码：</label> <input type="text" class="mui-input-clear" id="fphm">
			</div>
			<div class="mui-input-row">
				<label>开票日期：</label> <input type="text" class="mui-input-clear" id="kprq">
			</div>
			<div class="mui-input-row">
				<label>校验码：</label> <input type="text" class="mui-input-clear" id="jym">
			</div>
			<div class="mui-input-row">
				<label>开票金额：</label> <input type="text" class="mui-input-clear" id="kpje">
			</div>
			<input type="hidden" class="mui-input-clear" id="bill_date">
			<input type="hidden" class="mui-input-clear" id="invoice_id">
			<input type="hidden" class="mui-input-clear" id="is_fa">
		</div>
		<div style="text-align:center;margin-top:20px">
			<button type="button" class="mui-btn mui-btn-blue submit_info" style="width:80%;">提交信息</button>
		</div>
	</div>
</body>
<script src="/js/weixinjs/mui.min.js"></script>
<script src="/js/weixinjs/jquery-1.11.1.min.js"></script>
<script src="/js/weixinjs/mui.picker.min.js"></script>
<script src="/js/weixinjs/mui.poppicker.min.js"></script>
<script src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script src="/js/weixinjs/circle-progress.js"></script>
<script src="/js/weixinjs/mui.pullToRefresh.min.js"></script>
<script src="/js/weixinjs/mui.pullToRefresh.material.js"></script>
<script src="/js/weixinjs/scan.js?201808281109"></script>
<script>
	wx.config({
		debug: false,
		appId: 'wxb11ddcf3a43c859b',
		timestamp: <%=result.get("timestamp")%>,
		nonceStr: '<%=result.get("nonceStr")%>',
		signature: '<%=result.get("signature")%>',
		jsApiList : [ 'scanQRCode', 'chooseImage', 'uploadImage' ]
	});
	document.getElementById("scanQRCode1").addEventListener("click",function() {
		var companyId = document.getElementById('company_id').value;
		var company;
		for ( var i in local_data) {
			if (local_data[i].value == companyId) {
				company = local_data[i].text;
			}
		}
		if (company == undefined || company == '') {
			alert('您所输入的公司ID不存在，请查询后重新输入！');
			return;
		}
		var date = document.getElementById('invoice_date').innerText;
		var isFA = ($("input[name='isFa']:checked").val() == '1' ? '1'
				: '0');
		if (companyId == '' || companyId == undefined) {
			mui.createTipDialog('请选择公司!', null).show();
		}
		if (date == '' || date == undefined) {
			mui.createTipDialog('请选择日期!', null).show();
		}
		wx.scanQRCode({
			needResult : 1,
			desc : 'scanQRCode desc',
			success : function(res) {
				if (res == undefined || res == null) {
					alert('无法识别本张发票，请更换一张！');
					return;
				}
				var data = res.resultStr;
				var dataArr = data.split(',');
				if ((getNow() - parseInt(dataArr[5])) >= 10000) {
					alert('只支持一年内的发票扫描！');
					return;
				}
				var fpdm = dataArr[2];
				var fphm = dataArr[3];
				var kprq = dataArr[5];
				var kpje = 0.0;
				var jym = '';
				if(alxd(fpdm) == '01') {
					kpje = dataArr[4];
				} else if(alxd(fpdm) == '04') {
					jym = dataArr[6].substring(dataArr[6].length - 6);
				}
				var invoice_json_data = {
					'fpdm': fpdm,
		          	'fphm': fphm,
		          	'kprq': kprq,
		          	'kpje': kpje,
		          	'jym': jym
		        }; 
				if (confirm('公司 ： ' + company + '\n开票日期 ： ' + date + '\n固定资产  ： ' + (isFA == '0' ? '否' : '是') + '\ndata ： ' + data)) {
					$.ajax({
						url: 'http://www.bangbangzhang.com:8088/weixin_gzh_rec/wechat/submitInvoice', 
						headers: {
							'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
						},
						data: {
							company_id: companyId,
				            is_fa: isFA,
				            bill_date: date,
				            invoice_json_data: JSON.stringify(invoice_json_data)
				        }, 
						success: function(data) {
							document.querySelector(".mui-modal").classList.remove("mui-active");
							init_data();
						}
					});
				}
			}
		});
	});
	mui('.scan_div').on("tap", '.chooseImg', function() {
		var companyId = document.getElementById('company_id').value;
		var company;
		for ( var i in local_data) {
			if (local_data[i].value == companyId) {
				company = local_data[i].text;
			}
		}
		if (company == undefined || company == '') {
			alert('您所输入的公司ID不存在，请查询后重新输入！');
			return;
		}
		var date = document.getElementById('invoice_date').innerText;
		var isFA = ($("input[name='isFa']:checked").val() == '1' ? '1' : '0');
		if (companyId == '' || companyId == undefined) {
			mui.createTipDialog('请选择公司!', null).show();
		}
		if (date == '' || date == undefined) {
			mui.createTipDialog('请选择日期!', null).show();
		}
		document.getElementById('fapiaoluru_addInvoiceImage').click();
		var invoiceList = [];
		document.getElementById("fapiaoluru_addInvoiceImage").onchange = function(event) {
			var mask = mui.createUploadMask(false);
			$('#upload_progress').find('strong').html(0 + '<i>%</i>');
			var files = event.target.files;
			if (files && files.length > 0) {
				for(var i=0; i<files.length; i++) {
					invoiceList.push(files[i]);
				}
				mask.show();
				var formData = {
					company_id : companyId, 
					billDate : date, 
					is_fa : isFA	
				};
				var xhr = new XMLHttpRequest();
				var fd = new FormData();
				for (var jsonKey in formData) {
					fd.append(jsonKey, formData[jsonKey]);
				}
				for (var index in invoiceList) {
					fd.append("files", invoiceList[index]);
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
					if (res.error_code == "200") {
						mui.createTipDialog('发票上传成功!',null).show();
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
				xhr.open("POST", "http://www.bangbangzhang.com:8088/weixin_gzh_rec/wechat/uploadImage");
				xhr.send(fd);
			}
		}
	});
	function uploadImage(localIds) {
		var _this = this;
		if (localIds.length == 0) {
			console.log("全部上传成功serverIds：" + serverIds);
		} else {
			var localId = localIds.pop();
			console.log("wx.uploadImage");
			wx.uploadImage({
				localId : localId, // 需要上传的图片的本地ID，由chooseImage接口获得
				isShowProgressTips : 1, // 默认为1，显示进度提示
				success : function(res) {
					serverIds.push(res.serverId);//// 返回图片的服务器端ID
					console.log("排队上传图片到微信服务器成功serverId：" + res.serverId);
					_this.uploadImage(localIds);
				}
			});
		}
	}
	function getNow() {
		var myDate = new Date();
		var year = myDate.getFullYear();
		var month = myDate.getMonth() + 1 < 10 ? "0" + (myDate.getMonth() + 1)
				: myDate.getMonth() + 1;
		var day = myDate.getDate() < 10 ? "0" + myDate.getDate() : myDate
				.getDate();
		return parseInt(year + month + day);
	}
</script>
<script>
	var height = document.documentElement.clientHeight - 44 - 50;
	document.getElementById("not_rec").style.height = height + "px";
	mui.init();
	(function($) {
		//阻尼系数
		var deceleration = mui.os.ios ? 0.003 : 0.0009;
		$('.mui-scroll-wrapper').scroll({
			bounce : false,
			indicators : true, //是否显示滚动条
			deceleration : deceleration
		});
		$.ready(function() {
			$.each(document.querySelectorAll('.mui-scroll'), function(index, pullRefreshEl) {
				$(pullRefreshEl).pullToRefresh({
					down: {
						callback: function() {
							console.log('4:' + accounter_user_id);
							var self = this;
							var aid = accounter_user_id;
							$.ajax({
								url: 'http://www.bangbangzhang.com:8088/weixin_gzh_rec/wechat/getInvoice', 
								headers: {
									'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
								},
								data: {user_id: aid, page : 1}, 
								success: function(data) {
									self.endPullDownToRefresh(false);
									index_ = 2;
									var ul = self.element.querySelector('.mui-table-view');
									var ulHtml = createFragment(data);
									ul.innerHTML = ulHtml;
									document.querySelector(".mui-media").addEventListener("click", function() {
										document.querySelector(".mui-modal").classList.add("mui-active");
									});
								}
							});
						}
					},
					up: {
						callback: function() {
							var self = this;
							var aid = accounter_user_id;
							$.ajax({
								url: 'http://www.bangbangzhang.com:8088/weixin_gzh_rec/wechat/getInvoice', 
								headers: {
									'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
								},
								data: {user_id: aid, page : index_}, 
								success: function(data) {
									self.endPullUpToRefresh(false);
									var ul = self.element.querySelector('.mui-table-view');
									var ulHtml = createFragment(data);
									if(ulHtml != '') index_++;
									ul.innerHTML += ulHtml;
									document.querySelector(".mui-media").addEventListener("click", function() {
										document.querySelector(".mui-modal").classList.add("mui-active");
									});
								}
							});
						}
					}
				});
			});
			mui('.mui-modal').on('tap', '.submit_info', function() {
				var fpdm = document.getElementById("fpdm").value;
				var fphm = document.getElementById("fphm").value;
				var kprq = document.getElementById("kprq").value;
				var jym = document.getElementById("jym").value;
				var kpje = document.getElementById("kpje").value;
				var invoice_id = document.getElementById("invoice_id").value;
				var bill_date = document.getElementById("bill_date").value;
				var is_fa = document.getElementById("is_fa").value;
				var invoice_json_data = {
					'fpdm': fpdm,
		          	'fphm': fphm,
		          	'kprq': kprq,
		          	'kpje': kpje,
		          	'jym': jym
		        };
				$.ajax({
					url: 'http://www.bangbangzhang.com:8088/weixin_gzh_rec/wechat/submitNotRecInvoice', 
					headers: {
						'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
					},
					data: {
			            invoice_id: invoice_id,
			            is_fa: is_fa,
			            bill_date: bill_date,
			            invoice_json_data: JSON.stringify(invoice_json_data)
			        }, 
					success: function(data) {
						document.querySelector(".mui-modal").classList.remove("mui-active");
						init_data();
					}
				});
			});
			mui('.mui-modal').on('tap', '.close_modal', function() {
				document.querySelector(".mui-modal").classList.remove("mui-active");
			});
		});
	})(mui);
</script>
</html>