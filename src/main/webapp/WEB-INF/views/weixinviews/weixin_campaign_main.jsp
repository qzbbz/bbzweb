<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String hasAnswer = (String)session.getAttribute("hasAnswer");
String campaign_finish = (String)session.getAttribute("campaign_finish");
%>
<!DOCTYPE html>
<head>
	<html>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
	<title>与您相约“一站到底“~帮帮账</title>
</head>

<body>
	<div id="main_background" style="position:absolute;width:100%; height:100%; z-index:-1;left:0px;top:-1px;">
		<img src="/img/weixinimg/main_background.jpg" height="100%" width="100%" />
	</div>
	<div id="main_top" style="">
		<img src="/img/weixinimg/main_top.png" height="100%" width="100%" />
	</div>
	<div id="main_button" style="text-align:center;margin-top:20px;">
		<a href="javascript:checkUserHasAnswer('weixin_campaign_subject.html');"><img src="/img/weixinimg/main_button1.png" height="100%" width="60%" /></a>
	</div>
	<div id="main_button" style="text-align:center;margin-top:20px;">
		<a href="/views/weixinviews/weixin_campaign_top.html"><img src="/img/weixinimg/main_button2.png" height="100%" width="60%" /></a>
	</div>
</body>
<script src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script src="/js/weixinjs/zepto.min.js"></script>
<script>
var userHasAnswer = <%=hasAnswer%>;
var campaignFinish = <%=campaign_finish%>
function checkUserHasAnswer(page) {
	if(userHasAnswer != null && userHasAnswer != "0") {
		alert("您今天已经回答过问题了，明天再战！");
	} else {
		if(campaignFinish == "1") {
			alert("活动已经结束，感谢您的关注！");
		} else {
			window.location.href="/views/weixinviews/" + page;
		}
	}
}
var imgUrl = "http://www.bangbangzhang.com/img/weixinimg/main_top.png";
var lineLink = window.location.href;
var descContent = "关注 帮帮账“bbzhang88”公众号，参与“一站到底”游戏，抢500元红包大礼。";
var shareTitle = "帮帮账与您相约“一站到底”";
function getJsConfigInfoSuccess(data,status) {
	wx.config({
		appId: 'wxb11ddcf3a43c859b',
		timestamp: data.timestamp,
		nonceStr: data.nonceStr,
		signature: data.signature,
		jsApiList: [
			'onMenuShareQQ',
			'onMenuShareTimeline',
			'onMenuShareAppMessage'
		]
	});
}
$.ajax({ 
    type : "POST", 
    url  : "/getJsConfigInfo?url=" + encodeURIComponent(location.href.split('#')[0]),
    async: false,
    success : getJsConfigInfoSuccess 
});
wx.ready(function(){
	var appid = 'wxb11ddcf3a43c859b';
	setTimeout(function(){
		wx.onMenuShareTimeline({
		    title: shareTitle, // 分享标题
		    link: lineLink, // 分享链接
		    imgUrl: imgUrl, // 分享图标
		    success: function () { 
		        // 用户确认分享后执行的回调函数
		    },
		    cancel: function () { 
		        // 用户取消分享后执行的回调函数
		    }
		});
		wx.onMenuShareAppMessage({
		    title: shareTitle, // 分享标题
		    desc: descContent, // 分享描述
		    link: lineLink, // 分享链接
		    imgUrl: imgUrl, // 分享图标
		    type: '', // 分享类型,music、video或link，不填默认为link
		    dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
		    success: function () { 
		        // 用户确认分享后执行的回调函数
		    },
		    cancel: function () { 
		        // 用户取消分享后执行的回调函数
		    }
		});
		wx.onMenuShareQQ({
		    title: shareTitle, // 分享标题
		    desc: descContent, // 分享描述
		    link: lineLink, // 分享链接
		    imgUrl: imgUrl, // 分享图标
		    success: function () { 
		       // 用户确认分享后执行的回调函数
		    },
		    cancel: function () { 
		       // 用户取消分享后执行的回调函数
		    }
		});
	}, 500);
});
</script>

</html>