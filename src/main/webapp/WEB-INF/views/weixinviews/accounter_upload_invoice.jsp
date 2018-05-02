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
	if(request.getQueryString() != null) {
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
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<title></title>
		<link href="../../css/weixincss/mui.min.css" rel="stylesheet" />
		<link href="../../css/weixincss/iconfont.css" rel="stylesheet" />
		<link href="../../css/weixincss/circle-progress.css" rel="stylesheet" />
		<link href="/css/weixincss/mui.picker.min.css" rel="stylesheet" />
		<link href="/css/weixincss/mui.poppicker.css" rel="stylesheet" />
		<link href="/css/weixincss/scan.css" rel="stylesheet" />
		<style>
			.mui-preview-image.mui-fullscreen {
				position: fixed;
				z-index: 20;
				background-color: #000;
			}
			.mui-preview-header,
			.mui-preview-footer {
				position: absolute;
				width: 100%;
				left: 0;
				z-index: 10;
			}
			.mui-preview-header {
				height: 44px;
				top: 0;
			}
			.mui-preview-footer {
				height: 50px;
				bottom: 0px;
			}
			.mui-preview-header .mui-preview-indicator {
				display: block;
				line-height: 25px;
				color: #fff;
				text-align: center;
				margin: 15px auto 4;
				width: 70px;
				background-color: rgba(0, 0, 0, 0.4);
				border-radius: 12px;
				font-size: 16px;
			}
			.mui-preview-image {
				display: none;
				-webkit-animation-duration: 0.5s;
				animation-duration: 0.5s;
				-webkit-animation-fill-mode: both;
				animation-fill-mode: both;
			}
			.mui-preview-image.mui-preview-in {
				-webkit-animation-name: fadeIn;
				animation-name: fadeIn;
			}
			.mui-preview-image.mui-preview-out {
				background: none;
				-webkit-animation-name: fadeOut;
				animation-name: fadeOut;
			}
			.mui-preview-image.mui-preview-out .mui-preview-header,
			.mui-preview-image.mui-preview-out .mui-preview-footer {
				display: none;
			}
			.mui-zoom-scroller {
				position: absolute;
				display: -webkit-box;
				display: -webkit-flex;
				display: flex;
				-webkit-box-align: center;
				-webkit-align-items: center;
				align-items: center;
				-webkit-box-pack: center;
				-webkit-justify-content: center;
				justify-content: center;
				left: 0;
				right: 0;
				bottom: 0;
				top: 0;
				width: 100%;
				height: 100%;
				margin: 0;
				-webkit-backface-visibility: hidden;
			}
			.mui-zoom {
				-webkit-transform-style: preserve-3d;
				transform-style: preserve-3d;
			}
			.mui-slider .mui-slider-group .mui-slider-item img {
				width: auto;
				height: auto;
				max-width: 100%;
				max-height: 100%;
			}
			.mui-android-4-1 .mui-slider .mui-slider-group .mui-slider-item img {
				width: 100%;
			}
			.mui-android-4-1 .mui-slider.mui-preview-image .mui-slider-group .mui-slider-item {
				display: inline-table;
			}
			.mui-android-4-1 .mui-slider.mui-preview-image .mui-zoom-scroller img {
				display: table-cell;
				vertical-align: middle;
			}
			.mui-preview-loading {
				position: absolute;
				width: 100%;
				height: 100%;
				top: 0;
				left: 0;
				display: none;
			}
			.mui-preview-loading.mui-active {
				display: block;
			}
			.mui-preview-loading .mui-spinner-white {
				position: absolute;
				top: 50%;
				left: 50%;
				margin-left: -25px;
				margin-top: -25px;
				height: 50px;
				width: 50px;
			}
			.mui-preview-image img.mui-transitioning {
				-webkit-transition: -webkit-transform 0.5s ease, opacity 0.5s ease;
				transition: transform 0.5s ease, opacity 0.5s ease;
			}
			@-webkit-keyframes fadeIn {
				0% {
					opacity: 0;
				}
				100% {
					opacity: 1;
				}
			}
			@keyframes fadeIn {
				0% {
					opacity: 0;
				}
				100% {
					opacity: 1;
				}
			}
			@-webkit-keyframes fadeOut {
				0% {
					opacity: 1;
				}
				100% {
					opacity: 0;
				}
			}
			@keyframes fadeOut {
				0% {
					opacity: 1;
				}
				100% {
					opacity: 0;
				}
			}
			p img {
				max-width: 100%;
				height: auto;
			}
			.upload-file {
				position: fixed;
				z-index: 998;
				top: 0;
				right: 0;
				bottom: 0;
				left: 0;
				background-color: rgba(0, 0, 0, .8);
			}
			.dialog {
				position: fixed;
				z-index: 998;
				top: 0;
				right: 0;
				bottom: 0;
				left: 0;
				background-color: rgba(0, 0, 0, .4);
			}
			.mui-bar~.mui-content .mui-fullscreen {
				top: 44px;
				height: auto;
			}
			.mui-pull-top-tips {
				position: absolute;
				top: -20px;
				left: 50%;
				margin-left: -25px;
				width: 40px;
				height: 40px;
				border-radius: 100%;
				z-index: 999;
				margin-top: 60px;
			}
		</style>
	</head>

	<body>
		<header class="mui-bar mui-bar-nav">
			<h1 class="mui-title">公司发票上传</h1>
		</header>
		<div class="mui-content">
            <div class="mui-card" id="company_select" style="margin:10px 5px;height:40px;line-height:40px;">
				<div class="mui-pull-left"><p style="margin-left: 15px;">公司名称</p></div>
				<div class="mui-pull-right">
					<span id="companyName" style="color:#8f8f94;"></span><span class="mui-icon mui-icon-arrowright" style="font-size: 20px;margin-top:0px;color:#8f8f94;display:inline-block"></span>
					<input id="userId" type="hidden" class="mui-input-clear" value="">
				</div>
			</div>
			<div class="mui-card" id="select_date" style="margin:10px 5px;height:40px;line-height:40px;">
				<div class="mui-pull-left"><p style="margin-left: 15px;">发票日期</p></div>
				<div class="mui-pull-right">
					<span id="invoice_date" style="color:#8f8f94;">选择</span><span class="mui-icon mui-icon-arrowright" style="font-size: 20px;margin-top:0px;color:#8f8f94;display:inline-block"></span>
				</div>
			</div>
			<div id="data_loading" class="mui-loading" style="margin-top:50%;">
				<div class="mui-spinner"></div>
				<div style="text-align: center;">正在加载数据</div>
			</div>
			<div id="tips_info" style="display:none;margin-top:50%;">
				<div style="text-align: center;" id="tips_info_detail">服务器暂时无法处理请求，<br/>请稍后重试！</div>
			</div>
		</div>
		<footer id="fapiaoluru_submit" class="mui-bar mui-bar-footer" style="display:none;"><h1 class="mui-title">上传发票</h1></footer>
		<button class="btn btn_primary" id="scanQRCode1">扫描发票二维码</button>
	</body>
	<script src="../../js/weixinjs/mui.min.js"></script>
	<script src="../../js/weixinjs/mui.previewimage.js"></script>
	<script src="../../js/weixinjs/mui.zoom.min.js"></script>
	<script src="../../js/weixinjs/jquery-1.11.1.min.js"></script>
	<script src="../../js/weixinjs/circle-progress.js"></script>
	<script src="/js/weixinjs/mui.picker.min.js"></script>
	<script src="/js/weixinjs/mui.poppicker.min.js"></script>
	<script src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
	<script src="/js/weixinjs/scan.js"></script>
	<script>
	wx.config({
		debug: false,
		appId: 'wxb11ddcf3a43c859b',
		timestamp: <%=result.get("timestamp")%>,
		nonceStr: '<%=result.get("nonceStr")%>',
		signature: '<%=result.get("signature")%>',
		jsApiList: [
			'scanQRCode'
		]
	});
	document.querySelector('#scanQRCode1').onclick = function() {
		var companyId = document.getElementById('userId').value;
		var date = document.getElementById('invoice_date').innerText;
		if(companyId == '' || companyId == undefined) {
			mui.createTipDialog('请选择公司!',null).show();
		}
		if(date == '' || date == undefined) {
			mui.createTipDialog('请选择日期!',null).show();
		}
	    wx.scanQRCode({
			needResult: 1,
	    	desc: 'scanQRCode desc',
	    	success: function (res) {
	    		if(res == undefined) {
	    			return;
	    		}
	    		var data = res.resultStr;
	    		alert('data=' + data);
	    		$.post('/xxxx', { data : data }, function() {
	    			alert('扫描已完成！');
	    		});
	    	}
	    });
	};
</script>
</html>