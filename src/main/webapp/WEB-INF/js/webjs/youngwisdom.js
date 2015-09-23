
angular.module('ywWidgets', ['ngAnimate']);
angular.module('ywLanding',['ngRoute', 'ywText','ywWidgets']);


angular.module('ywLanding')
.controller('main', ['$scope', 't', '$location', '$http', function($scope, msg, $location, $http){
	$scope.t = msg;
	$scope.companyUserEmail = '';
	$scope.companyUserPwd = '';
	$scope.hideHeader = false;
	$scope.hideContactScreen = false;
	$scope.setHideHeader = function(b){
		$scope.hideHeader = b;
	};
	$scope.setHideContactScreen = function(b){
		$scope.hideContactScreen = b;
	};
	
	$scope.companySignup2 = function() {
		var userEmail = $('#sign-up2-screen-email').val();
		var userPwd = $('#sign-up2-screen-pwd').val();
		var userType = 2;
		if(userEmail == null || !isEmail(userEmail)) {
			$("#sign-up2-screen-email-error").show();
			return;
		} else {
			$("#sign-up2-screen-email-error").hide();
		}
		if(userPwd == null || userPwd == '') {
			$("#sign-up2-screen-pwd-error").show();
			return;
		} else {
			$("#sign-up2-screen-pwd-error").hide();
		}
		$scope.userReg(userEmail, userPwd, userType, $('#signup2-error-msg'));
	};
	
	$scope.accountSignup = function() {
		var userEmail = $('#account-reg-email').val();
		var userPwd = $('#account-reg-pwd').val();
		var userType = 1;
		if(userEmail == null || !isEmail(userEmail)) {
			$("#account-reg-error-email-msg").show();
			return;
		} else {
			$("#account-reg-error-email-msg").hide();
		}
		if(userPwd == null || userPwd == '') {
			$("#account-reg-error-pwd-msg").show();
			return;
		} else {
			$("#account-reg-error-pwd-msg").hide();
		}
		$scope.userReg(userEmail, userPwd, userType, $('#account-error-msg'));
	};
	
	$scope.userLogin = function() {
		var userEmail = $('#userLoginEmail').val();
		var userPwd = $('#userLoginPwd').val();
		var loginErrorMsg = $('#user-login-error-msg');
		if(userEmail == null || !isEmail(userEmail)) {
			$("#userLoginEmailError").show();
			return;
		} else {
			$("#userLoginEmailError").hide();
		}
		if(userPwd == null || userPwd == '') {
			$("#userLoginPwdError").show();
			return;
		} else {
			$("#userLoginPwdError").hide();
		}
		loginErrorMsg.html("<p style='color: white;'>正在向服务器提交您的信息，请稍后......</p>");
		loginErrorMsg.show();
		$.ajax({
			url : '../../checkUserLogin',
			type : "POST",
			data : {
				userId : userEmail,
				userPwd : userPwd
			},
			cache : false,
			success : function(data) {
				if(data.error_code == '-1') {
					loginErrorMsg.html("<p style='color: #FD7A50;'>用户名或密码不能为空！</p>");
				} else if(data.error_code == '0') {
					var userType = parseInt(data.user_type);
					var path = '';
					switch(userType) {
					case 1:
						path = "/accounter/companyExpenseRecords";
						break;
					case 2:
						path = "../../company/expenseAccountUpload";
						break;
					case 3:
						path = "../../admin/admin";
						break;
					case 5:
						path = "../../companyUser/expenseAccountUpload";
						break;
					default:
						path = "../../error";
						break;
					}
					loginErrorMsg.html("<p style='color: #419088;'>登陆成功！3秒后系统将自动转向后台页面......</p>");
					setTimeout(function(){loginErrorMsg.hide();window.location=path;}, 3000);
				} else if(data.error_code == '4') {
					$scope.companyUserEmail = userEmail;
					$scope.companyUserPwd = userPwd;
					loginErrorMsg.html("<p style='color: #419088;'>登陆成功，您还没有完成信息录入，5秒后系统将自动转向信息录入页面......</p>");
					setTimeout(function(){$location.path('/signup-steps');$scope.$apply();loginErrorMsg.hide();}, 3000);
				} else if(data.error_code == '100') {
					loginErrorMsg.html("<p style='color: #FD7A50;'>您注册的帐号正在审核中,请稍候重试!</p>");
				} else if(data.error_code == '102') {
					loginErrorMsg.html("<p style='color: #FD7A50;'>您注册的帐号没有通过审核,请联系管理员!</p>");
				} else if(data.error_code == '1' || data.error_code == '2') {
					loginErrorMsg.html("<p style='color: #FD7A50;'>" + data.error_message + "</p>");
				}
			},
			error : function() {
				loginErrorMsg.html("<p style='color: #FD7A50;'>服务器暂时不可用，请稍后重试！</p>");
			}
		});
	};
	
	$scope.userReg = function(email, pwd, type, errorMsgObj) {
		errorMsgObj.html("<p style='color: white;'>正在向服务器提交您的信息，请稍后......</p>");
		errorMsgObj.show();
		if(type == 2) {
			$scope.companyUserEmail = email;
			$scope.companyUserPwd = pwd;
		}
		$.ajax({
			url : '../../checkUserRegister',
			type : "POST",
			data : {
				userId : email,
				userPwd : pwd,
				userTypeId : type
			},
			cache : false,
			success : function(data) {
				if(data.error_code == '-1') {
					errorMsgObj.html("<p style='color: #FD7A50;'>用户名或密码不能为空！</p>");
				} else if(data.error_code == '0') {
					if(type == 2) {
						errorMsgObj.hide();
						$location.path('/signup-steps');
						$scope.$apply();
					} else if(type == 1) {
						errorMsgObj.html("<p style='color: #419088;'>注册成功！3秒后系统将自动转向登陆界面......</p>");
						setTimeout(function(){$location.path('/signin');$scope.$apply();errorMsgObj.hide();}, 3000);
					}
				} else {
					errorMsgObj.html("<p style='color: #FD7A50;'>" + data.error_message + "</p>");
				}
			},
			error : function() {
				errorMsgObj.html("<p style='color: #FD7A50;'>服务器暂时不可用，请稍后重试！</p>");
			}
		});
		
	};
	
	$scope.companyDetailReg = function(email, pwd, type) {
		$('#company_detail_reg_msg').html("<p style='color: #419088;'>正在向服务器提交您的信息，请稍后......</p>");
		$('#company_detail_reg_msg').show();
		var userName = $('#userName').val();
		var userCompanyName = $('#userCompanyName').val();
		var userCalledTime = $('#userCalledTime option:selected').text();
		var userPhone = $('#userPhone').val();
		var userCompanyIncomes = $('#userCompanyIncomes').val();
		$.ajax({
			url : '../../comDetailRegister',
			type : "POST",
			data : {
				userName : userName,
				userCompanyName : userCompanyName,
				userCompanyIncomes : userCompanyIncomes,
				userCalledTime : userCalledTime,
				userPhone : userPhone,
				userEmail : $scope.companyUserEmail,
				userPwd : $scope.companyUserPwd
			},
			cache : false,
			//dataType : 'json',
			success : function(data) {
				if(data.error_code == '1' || data.error_code == '2') {
					$('#company_detail_reg_msg').html("<p style='color: #FD7A50;'>"+data.error_message+"</p>");
				} else {
					$('#company_detail_reg_msg').html("<p style='color: #419088;'>信息提交成功！3秒后系统将自动转向登陆界面......</p>");
					setTimeout(function(){$location.path('/signin');$scope.$apply();$('#company_detail_reg_msg').hide();}, 3000);
				}			
			},
			error : function() {
				$('#company_detail_reg_msg').html("<p style='color: #FD7A50;'>服务器暂时不可用，请稍后重试！</p>");
			}
		});
	};
	
	$scope.emailFormat = /[a-zA-Z0-9_.]+@[a-zA-Z0-9_.]+/;
}])
.controller('ywComments', ['$scope','t', function($scope, msg){
	$scope.comments = [
		{text: '没有帮帮账的帮助，不像在运营我自己的公司。'},
		{text: '我从来没有遇到一个团队对记账报税服务如此热情，帮帮账轻松为我们解决了大问题。'},
		{text: '作为公司的创业者，我业务十分繁忙，帮帮账在财税方面提供给了我十分到位的帮助和支持。'},
		{text: '我爱上了帮帮账，也推荐给了我身边创业的朋友。 我感到我的账本交给帮帮账十分放心。'}
	];
}])
.controller('homeController', ['$scope','t', '$location', '$q', '$http', function($scope, msg, $location, $q, $http){
	$scope.signupScreenReady = $q.defer();
	$scope.introScreenReady = $q.defer();
	$scope.whatScreenReady = $q.defer();
	$scope.commentsScreenReady = $q.defer();
	$scope.signup2ScreenReady = $q.defer();
	
	$scope.setHideHeader(false);
	$scope.setHideContactScreen(false);
	$scope.scroll2Top();
	
	$scope.companySignup = function() {
		var userEmail = $('#sign-up-screen-email').val();
		var userPwd = $('#sign-up-screen-pwd').val();
		var userType = 2;
		if(userEmail == null || !isEmail(userEmail)) {
			$("#sign-up-screen-email-error").show();
			return;
		} else {
			$("#sign-up-screen-email-error").hide();
		}
		if(userPwd == null || userPwd == '') {
			$("#sign-up-screen-pwd-error").show();
			return;
		} else {
			$("#sign-up-screen-pwd-error").hide();
		}
		$scope.userReg(userEmail, userPwd, userType, $('#signup-error-msg'));
	};
}])
.controller('featureController', ['$scope','t','$route', '$routeParams', '$q','$location',
		function($scope, msg,
		$route, $routeParams, $q, $location){
	$scope.signup2ScreenReady = $q.defer();
	$scope.setHideHeader(false);
	$scope.setHideContactScreen(false);
	$scope.scroll2Top();
	$scope.jump = function(anchor){
		$scope.anchorIdx = anchor;
		//$location.path('/feature/'+ anchor);
	};
}])
.controller('signupController', ['$scope', '$location', function($scope, $location){
	$scope.setHideHeader(true);
	$scope.setHideContactScreen(true);
	$scope.scroll2Top();
	$scope.signup = function(){
		if($scope.signupPageForm.$valid){
			$location.path('/signup-steps');
		}
	};	
}]).controller('signinController', ['$scope', '$location', function($scope, $location){
	$scope.setHideHeader(true);
	$scope.setHideContactScreen(true);
	$scope.scroll2Top();
	$scope.signup = function(){
		if($scope.signupPageForm.$valid){
			$location.path('/signup-steps');
		}
	};	
}]).controller('signupStepController', ['$scope', '$location','$route','$routeParams',
	function($scope, $location, $route, $routeParams){
	$scope.setHideHeader(true);
	$scope.setHideContactScreen(true);
	$scope.model1 = { name: 'Ivy', title: '会计事务资深顾问'};
	$scope.model2 = { name: 'Evan', title: '会计师'};
	$scope.model1.desc = $scope.model1.name;
	$scope.stepsUI = {
		showBasicForm: false
	};
	
	if(!$routeParams.signupStep || $routeParams.signupStep === 1)
		$scope.currStep = 1;
	else{
		var step = $scope.currStep = parseInt($routeParams.signupStep, 10);
		if(step === 2){
			
		}
	}
	
	$scope.theModel = $scope.model1;
	$scope.tipModel = function(model){
		$scope.theModel = model;
	};
	
	$scope.validate = function(){
		return $scope.signupStep3Form.$valid;
	};
}]).controller('teamController', ['$scope', function($scope){
	$scope.setHideHeader(false);
	$scope.setHideContactScreen(false);
	
	$scope.teams = [{}];
}])
.config(['$routeProvider', '$locationProvider', function($routeProvider, $locationProvider) {
	$routeProvider.when('/', {
		templateUrl: '/frontpage/home',
		controller: 'homeController'
	}).when('/feature', {
		templateUrl: '/frontpage/feature',
		controller: 'featureController'
	}).when('/feature/:anchor', {
		templateUrl: '/frontpage/feature',
		controller: 'featureController'
	}).when('/signup', {
		templateUrl: '/frontpage/signup-page',
		controller: 'signupController'
	}).when('/signup-steps', {
		templateUrl: '/frontpage/signup-steps',
		controller: 'signupStepController'
	}).when('/signup-steps/:signupStep', {
		templateUrl: '/frontpage/signup-steps',
		controller: 'signupStepController'
	}).when('/signin', {
		templateUrl: '/frontpage/signin-page',
		controller: 'signupController'
	}).when('/team', {
		templateUrl: '/frontpage/team-page',
		controller: 'teamController'
	});
}]);

angular.module('ywLanding')
.directive('ywFeatureSection', ['$timeout', function($timeout){
	return{
		template: '<div class="section"><div class="section-icon glyphicon"></div>'+
				'<div class="section-background"></div>'+
				'<h2>{{title}}</h2>'+
				'<div ng-transclude></div></div>',
		transclude: true,
		scope:{
			title: '@'
		},
		link:function(scope, el, attrs){
			el.addClass('section-wrap');
			el.find('.glyphicon').first().addClass(attrs.iconClass);
		}
	};
	}])
;

angular.module('ywLanding')
.directive('ywMain',['$timeout', '$interval', '$window', function($timeout,$interval){
	return {
		link:function(scope, el, attrs){
			//scope.$on('$viewContentLoaded', function(){
			//	$timeout(function(){
			//		$(window).scrollTop(0);
			//	}, 0, false);
			//});
			
			scope.scroll2Top = function(){
				$timeout(function(){
					$(window).scrollTop(0);
				}, 50, false);
			};
			
			var header = el.find('.header');
			$timeout(function(){
				var scrollable = new ScrollableAnim($(window));
				scrollable.scene({
						offset: 15,
						duration: 400,
						startup: function(rev){
							if(!rev){
								header.addClass('header-fixed');
							}else{
								header.removeClass('header-fixed');
							}
						}
				});
			}, 50, false);
			
			//console.log('ywMain '+ new Date().getTime());
		}
	};
	
	}])
.directive('ywHome', ['$compile', '$timeout', '$interval', '$window','$q',
function($compile, $timeout,$interval, $window, $q){
return {
	restrict: 'EAC',
	
	compile:function(el, attrs){
		return function(scope, el, attrs){
			//console.log('ywHome '+ new Date().getTime());
			$q.all([scope.signupScreenReady.promise ,
			scope.introScreenReady.promise,
			scope.whatScreenReady.promise,
			scope.commentsScreenReady.promise,
			scope.signup2ScreenReady.promise]).then(function(){
			
			//$timeout(function(){
				var scrollable = new ScrollableAnim($($window));
				
				// --- chart animation ---
				var chartPolygon = el.find('#chart-polygon');
				var line = el.find('#SvgjsPolyline1010');
				var ellipse = el.find('ellipse');
				
				
				var chartTimeline = new TimelineLite({paused: true});
				chartTimeline.to(chartPolygon, 0.8, {callback: function(ratio){
						var v = 129 - 60 * ratio;
						var v2 = 130 + 30 * ratio;
						var v4 = 65 + 70 * ratio;
						var points = '-150,213 150,209 450,'+v2+' 750,'+ v +' 1050,'+v4+' 1350,43 1650,15';
						line.attr('points', points);
						chartPolygon.attr('points', points+ ' 1950,250 -150,250');
						ellipse[3].setAttribute('cy', v);
						ellipse[2].setAttribute('cy', v2);
						ellipse[4].setAttribute('cy', v4);
					}, ease: Power2.EaseInOut
				});
				chartTimeline.to(chartPolygon, 0.8, {callback: function(ratio){
					var v4 = 135 - 30 * ratio;
					var v5 = 43 + 50 * ratio;
					var points = '-150,213 150,209 450,'+ '160' +' 750,'+ '69' +' 1050,'+v4+' 1350,'+v5+' 1650,15';
					line.attr('points', points);
					chartPolygon.attr('points', points+ ' 1950,250 -150,250');
					ellipse[4].setAttribute('cy', v4);
					ellipse[5].setAttribute('cy', v5);
					}, ease: Power2.EaseInOut
				}, '+=3');
				
				chartTimeline.play();
				$interval(function(){
					if(chartTimeline.reversed())
						chartTimeline.restart();
					else
						chartTimeline.reverse();
				}, 6500, 0, false);
				
				// --- step animation ---
				var stepTimeline = new TimelineLite({paused: true});
				var animLine = el.find('#screen-intro .anim-timeline');
				var dots = el.find('#screen-intro .line-dot');
				var steps = el.find('#screen-intro .step');
				var icons = el.find('#screen-intro .glyphicon');
				
				//stepTimeline.to(dots[0], .2, {className: '+=lightened'});
				//stepTimeline.to(steps[0], .2, {color: '#323232'}, 0);
				stepTimeline.to(animLine, 2.1, {width: '100%', ease: Linear.easeNone});
				stepTimeline.staggerTo(dots, .2, {className: '+=lightened'}, 0.7, 0);
				stepTimeline.staggerTo(steps, .2, {color: '#323232'}, 0.7, 0);
				stepTimeline.staggerFromTo(icons, .7, {scaleX:0, scaleY: 0},
					{scaleX: 1, scaleY: 1, ease: Elastic.easeOut}, 0.7,0);
				
				scrollable.scene({
						triggerElement: '#screen-intro .steps',
						delayPercent: 0,
						startup: function(rev){
							if(!rev){
								stepTimeline.restart();
							}
						}
				});
				
				var signupIconsTl = new TimelineLite({paused: true});
				signupIconsTl.staggerFromTo($('#screen-signup2 .yw-icon'), 1, {scaleX:0, scaleY:0}, {scaleX: 1, scaleY: 1, ease: Elastic.easeOut, repeat:-1, delay: 0.5, repeatDelay: 5}, 0.3);
				scrollable.scene({
						triggerElement: '#screen-signup2',
						startup: function(rev){
							if(!rev)
								signupIconsTl.restart();
						}
				});
				
				scrollable.scene({
						triggerElement: '#screen-comments',
						startup: function(rev){
							if(!rev){
								scope.commentAnimStart = 'true';
								scope.$apply();
							}
						}
				});
			//}, 1000, false);
	});
		}
	}
};
}])
.directive('ywChartAnim', ['$timeout', function($timeout){
	return {
		restrict: 'EAC',
		link: function(scope, el, attrs){
			//console.log('ywChartAnim '+ new Date().getTime());
			$timeout(function(){
				var win = $(window);
				var svg = el.find('svg');
				win.resize(function(){
					svg.attr('width', win.width());
				});
				svg.attr('width', win.width());
			}, 50, false);
		}
	};
}])
.directive('ywCommentAnim', ['$timeout', function($timeout){
	return {
		restrict: 'EAC',
		link: function(scope, el, attrs){
			//console.log('ywCommentAnim '+ new Date().getTime());
			el.addClass('yw-comment-anim');
			var bubbles, showingIdx = 0;
			
			$timeout(function(){
					bubbles = el.find('.yw-bubble');
					bubbles.addClass('yw-anim-hide');
			}, 50);
			
			attrs.$observe('ywCommentAnim', function(value){
				if(value === 'yes' || value === 'true'){
					animGroup();
				}
			});
			
			function anim(){
				if(showingIdx != null){
					var hideIdx = showingIdx - 2;
					hideIdx = hideIdx < 0 ? bubbles.length + hideIdx : hideIdx;
					TweenMax.to(bubbles[hideIdx], 0.5, {opacity: 0, yPercent: 100,
						onComplete:function(){
							var target = bubbles.eq(showingIdx);
							target.removeClass('yw-anim-hide');
							TweenMax.fromTo(target, 0.3, {opacity:0, yPercent: -100}, {opacity: 1, yPercent: 0});
							showingIdx++;
							showingIdx = showingIdx === bubbles.length ? 0 : showingIdx;
					}});
				}
				
			}
			
			function animGroup(){
				anim();
				$timeout(anim, 1000, false);
				$timeout(animGroup, 5000, false);
			}
		}
	};
}])
.directive('ywFeature', ['$compile', '$timeout', '$interval', '$window','$q',
function($compile, $timeout,$interval, $window, $q){
return {
	restrict: 'EAC',
	link: function(scope, el, attrs){
		//console.log('ywFeature '+ new Date().getTime());
		scope.$watch('anchorIdx', function(val, old){
				if(val != null){
					var offset = el.find('.section-wrap').eq(parseInt(val, 10)).offset().top;
				TweenMax.to(window, 0.5, {scrollTo:{y: offset - $('.header').outerHeight() - 35}, ease:Power2.easeOut});
				}
			});
		
		
		$q.all([scope.signup2ScreenReady.promise]).then(
		function(){
			var scrollable = new ScrollableAnim($(window));
			var signupIconsTl = new TimelineLite({paused: true});
			signupIconsTl.staggerFromTo($('#screen-signup2 .yw-icon'), 1, {scaleX:0, scaleY:0}, {scaleX: 1, scaleY: 1, ease: Elastic.easeOut, repeat:-1, delay: 0.5, repeatDelay: 5}, 0.3);
			scrollable.scene({
					triggerElement: '#screen-signup2',
					startup: function(rev){
						if(!rev)
							signupIconsTl.restart();
					}
			});
		});
	}
};
}]);

angular.module('ywLanding')
.directive('ywSignupSteps', ['$timeout', '$q', function($timeout, $q){
	return{
		scope: false,
		
		controller: ['$scope', function($scope){
			this.setAnimNameCard = function(element){
				$scope.animNameCard = element;
			};
		}],
		compile:function(){
			return function(scope, el, attrs){
				
				var namecards = el.find('[yw-name-card]');
				namecards.each(function(){
					var namecard = $(this);
					namecard.addClass('yw-name-card');
					$timeout(function(){
						var targetSel = namecard.eq(0).attr('yw-target');
						var t = $(targetSel);
						var offset = t.offset();
						var top = offset.top + t.height();
						
						var offsetParent = namecard.offsetParent();
						namecard.css({ top: top +'px', left: offset.left - offsetParent.offset().left});
						
						
					}, 50, false);
				});
				
				
				scope.$watch('currStep', function(step){
					if(step === 2){
						
						$timeout(function(){
							var nameCardTo;
							var container = el.find('.signup-meet');
							container.height(container.height());
							var nameCard = scope.animNameCard;
							nameCardTo = { x: container.offset().left,
								y: container.offset().top
							};
							
							var fadeoutDef = $q.defer();
							
							var tl = new TimelineLite({delay: 0.3, onComplete: function(){
								scope.model1.desc = scope.model1.name + '    ' + scope.model1.title;
								scope.stepsUI.showBasicForm = true;
								scope.$apply();
								fadeoutDef.resolve();
							}});
							
							
							tl.to(nameCard, 0.7, 
								{	top: container.outerHeight() - nameCard.height() + 66,
									left: '-140px', // signup-steps.less: margin-left: @portrait-width/2;
									width: container.width()/3,
									'border-radius': 0,
								}
							);
							tl.to(el.find('.signup-p-intro')[0], 0.7, {
								display: 'none'
									
							}, 0);
							
							var evan = el.find('.col-evan');
							TweenLite.set(evan,{  transformOrigin: 'center 30%'});
							tl.to(evan, 0.7, {
								height: 0
							}, 0)
							
							tl.to(el.find('.col-desc'), 0.7, { height: 0, padding: 0}, 0);
							
							var namecardEvan = el.find('[yw-name-card]').eq(1);
							tl.to(namecardEvan, 0.7, { opacity: 0, onComplete: function(){ namecardEvan.remove() } }, 0);
							
							var portrait = el.find('.portrait').eq(0);
							portrait.css({position: 'absolute'});
							tl.to(portrait, 0.7, {
								height: container.height()
							}, 0);
							
							fadeoutDef.promise.then(function(){
									console.log(el.find('.underscore'));
								TweenMax.staggerTo(el.find('.underscore'), 0.4, {width: '90%'}, 0.15);
							});
						}, 50, false);
						
					}
				});
			}
		}
	};
	}])
.directive('ywNameCard', ['$timeout', function($timeout){
	var tipboxOutPromis;	
	
return {
	require: '^ywSignupSteps',
	link: function(scope, el, attrs, ywSignupSteps){
		var tipbox;
		var icon = el.find('img');
		
		if(attrs.animInStep2 === 'LJ is awesome'){
			ywSignupSteps.setAnimNameCard(el);
		}
		
		if(scope.currStep !== 1){
			return;
		}
		el.on('mouseenter.ywNameCard', function(e){
			if(tipboxOutPromis){
				$timeout.cancel(tipboxOutPromis);
				tipboxOutPromis = null;
			}
			var off = el.offset();
			if(!tipbox)
				tipbox = $('.yw-namecard-tip');
			
			
			scope.tipModel(scope[attrs.ywNameCard]);
			scope.$apply();
			$timeout(function(){
					var op =tipbox.offsetParent().offset();
					tipbox.css({top: icon.offset().top -op.top, left: icon.offset().left - op.left, visibility: 'visible'});
				}, 0, false);
		});
		el.on('mouseleave.ywNameCard', function(e){
			tipboxOutPromis = $timeout(function(){
					tipbox.css({visibility: 'hidden'});
					tipboxOutPromis = null;
			}, 400, false);
		});
		
		$timeout(function(){
			if(!tipbox)
				tipbox = $('.yw-namecard-tip');
			tipbox.on('mouseenter', function(){
				if(tipboxOutPromis != null){
					if($timeout.cancel(tipboxOutPromis)){
						tipbox.on('mouseleave', function(){
							tipbox.css({visibility: 'hidden'});
							tipbox.off('mouseleave');
						});
						tipboxOutPromis = null;
					}
				}
			});
		}, 50, false);
		
		
	}
};
}])
;

angular.module('ywLanding')
.directive('ywTeamPage', ['$timeout', '$q', function($timeout, $q){
	return{
		link:function(scope, el, attrs){
			$timeout(function(){
				var images = el.find('.member>img');
				TweenMax.set(images, {scaleX:0, scaleY:0});
				TweenMax.staggerFromTo(images, 1,
					{scaleX:0, scaleY:0}, {scaleX: 1, scaleY: 1, ease: Elastic.easeOut, delay: .45}, 0.2);
			}, 100, false);
		}
	};
}]);

angular.module('ywWidgets')
.directive('ywLink', ['$compile', function($compile){
 return {
 	 restrict: 'C',
 	compile: function compile(el, attrs, transclude){
 		return function(scope, el, attrs){
 			//el.addClass('yw-link');
 			var underscore = angular.element('<div class="underscore"></div>');
 			el.append(underscore);
 		}
 	}
 };
}])
.directive('ywBubble', ['$compile', function($compile){
	return{
		restrict:'AE',
		template: '<div>'+
			'<div class="yw-bubble-box">'+
				'<div class="arrow"></div>'+
				'<div ng-transclude class="bubble-content"></div>'+
			'</div>'+
		'</div>',
		transclude: true,
		
		compile: function compile(el, attrs, transclude){
			el.addClass('yw-bubble');
			return function(scope, el, attrs){
			}
		}
	};
}])
.directive('ywButton', [function(){
	return {
		restrict:'AEC',
		link: function(scope, el, attrs){
			el.addClass('yw-button');
			el.on('mousedown', function(){
				el.addClass('down');
			});
			$('body').on('mouseup', function(){
				el.removeClass('down');
			});
		}
	};
}])
.directive('ywPassword', ['$compile', function($compile){
	return {
		link:function(scope, el, attrs){
			var needText = attrs.ywPassword === 'text';
			
			var toggle = $('<i class="glyphicon glyphicon-eye-open"></i>');
			el.after(toggle);
			if(needText){
				var hints = $('<span class="pwd-vis-hints">显示</span>');
				toggle.after(hints);
			}
			
			toggle.on('click', function(){
				if(el.attr('type') === 'password'){
					//el.attr('type', 'text');
					el[0].type="text";
					toggle.removeClass('glyphicon-eye-open');
					toggle.addClass('glyphicon-eye-close');
					if(hints)
						hints.html('隐藏');
				}
				else{
					//el.attr('type', 'password');
					el[0].type="password";
					toggle.removeClass('glyphicon-eye-close');
					toggle.addClass('glyphicon-eye-open');
					if(hints)
						hints.html('显示');
				}
			});
		}
	};
}])
.animation('.yw-animation', function(){
	return{
		addClass: function(element, className, done) { 
			if(className === 'ng-hide'){
				TweenMax.fromTo(element, 0.25, {opacity: 1, yPercent: 0}, {opacity: 0, yPercent: -100, onComplete: done, ease: Power2.easeOut});
			}else{
				done();
			}
		},
		removeClass: function(element, className, done) {
			if(className === 'ng-hide'){
				TweenMax.fromTo(element, 0.25, {opacity: 0, yPercent: -100}, {opacity:1, yPercent: 0, onComplete: done, ease: Power2.easeOut});
				//TweenMax.from(element, 0.3, {height: 0, onComplete: done});
			}else
				done();
		},
		leave: function(element, done) {
			done();
			TweenMax.to(element, 0.3, {opacity: 0, onComplete: function(){
				
			}});
		},
		enter: function(element, done) {
			TweenMax.fromTo(element, 0.7, {opacity: 0}, {opacity: 1, onComplete: function(){
				done();
			}});
		}
	};
});


/*!
 * VERSION: 1.1.1
 * DATE: 2014-07-17
 * UPDATES AND DOCS AT: http://www.greensock.com
 * 
 * This file is to be used as a simple template for writing your own plugin. See the 
 * notes at http://api.greensock.com/js/com/greensock/plugins/TweenPlugin.html for more details.
 *
 * You can start by doing a search for "yourCustomProperty" and replace it with whatever the name
 * of your property is. This way of defining a plugin was introduced in version 1.9.0 - previous versions
 * of TweenLite won't work with this.
 *
 * @license Copyright (c) 2008-2014, GreenSock. All rights reserved.
 * This work is subject to the terms at http://www.greensock.com/terms_of_use.html or for
 * Club GreenSock members, the software agreement that was issued with your membership.
 * 
 * @author: Jack Doyle, jack@greensock.com
 **/
var _gsScope = (typeof(module) !== "undefined" && module.exports && typeof(global) !== "undefined") ? global : this || window; //helps ensure compatibility with AMD/RequireJS and CommonJS/Node
(_gsScope._gsQueue || (_gsScope._gsQueue = [])).push( function() {
	//ignore the line above this and at the very end - those are for ensuring things load in the proper order
	"use strict";

	_gsScope._gsDefine.plugin({
		propName: "callback", //the name of the property that will get intercepted and handled by this plugin (obviously change it to whatever you want, typically it is camelCase starting with lowercase).
		priority: 0, //the priority in the rendering pipeline (0 by default). A priority of -1 would mean this plugin will run after all those with 0 or greater. A priority of 1 would get run before 0, etc. This only matters when a plugin relies on other plugins finishing their work before it runs (or visa-versa)
		API: 2, //the API should stay 2 - it just gives us a way to know the method/property structure so that if in the future we change to a different TweenPlugin architecture, we can identify this plugin's structure.
		version: "1.0.0", //your plugin's version number
		overwriteProps: [], //an array of property names whose tweens should be overwritten by this plugin. For example, if you create a "scale" plugin that handles both "scaleX" and "scaleY", the overwriteProps would be ["scaleX","scaleY"] so that if there's a scaleX or scaleY tween in-progress when a new "scale" tween starts (using this plugin), it would overwrite the scaleX or scaleY tween.

		/*
		 * The init function is called when the tween renders for the first time. This is where initial values should be recorded and any setup routines should run. It receives 3 parameters:
		 *   1) target [object] - the target of the tween. In cases where the tween's original target is an array (or jQuery object), this target will be the individual object inside that array (a new plugin instance is created for each target in the array). For example, TweenLite.to([obj1, obj2, obj3], 1, {x:100}) the target will be obj1 or obj2 or obj3 rather than the array containing them.
		 *   2) value [*] - whatever value is passed as the special property value. For example, TweenLite.to(element, 1, {yourCustomProperty:3}) the value would be 3. Or for TweenLite.to(element, 1, {yourCustomProperty:{subProp1:3, subProp2:"whatever"}});, value would be {subProp1:3, subProp2:"whatever"}.
		 *   3) tween [TweenLite] - the TweenLite (or TweenMax) instance that is managing this plugin instance. This can be useful if you need to check certain state-related properties on the tween (maybe in the set method) like its duration or time. Most of the time, however, you don't need to do anything with the tween. It is provided just in case you want to reference it.
		 *
		 * This function should return true unless you want to have TweenLite/Max skip the plugin altogether and instead treat the property/value like a normal tween (as if the plugin wasn't activated). This is rarely useful, so you should almost always return true.
		 */
		init: function(target, value, tween) {
			this._callback = value;
			
			//always return true unless we want to scrap the plugin and have the value treated as a normal property tween (very uncommon)
			return true;
		},

		//[optional] - called each time the values should be updated, and the ratio gets passed as the only parameter (typically it's a value between 0 and 1, but it can exceed those when using an ease like Elastic.easeOut or Back.easeOut, etc.). If you're using this._super._addTween() for all your tweens and you don't need to do anything special on each frame besides updating those values, you can omit this "set" function altogether.
		set: function(ratio) {
			//since we used _addTween() inside init function, it created some property tweens that we'll update by calling the parent prototype's setRatio() (otherwise, the property tweens wouldn't get their values updated). this._super refers to the TweenPlugin prototype from which the plugin inherits (not that you need to worry about that).
			this._super.setRatio.call(this, ratio);

			//now manually set the alpha
			//this._target.alpha = this._alphaStart + this._alphaChange * ratio;
			this._callback(ratio);
		}

	});

}); if (_gsScope._gsDefine) { _gsScope._gsQueue.pop()(); }

/**
attrs: object contains properties like
	- duration : number (optional)
	- offset : number - also you may use triggerElement instead
	- triggerElement: element|selector - 
		triggered when offset reaches element's offset top - viewport's height
	- delayPercent: number - used conjunction with triggerElement, 
		triggered when offset reaches delayPercent/100 * duration + element's offset top - viewport's height
	- timeline: TimelineLite
	- startup: function(reverse: boolean)  - where you may put startup animation
	- teardown: function(reverse: boolean) - where you may put teardown animation
	- onScroll: function(progress, time) - progress is a number between  0 and duration,
		time is a number between 0 and 1
*/
function AnimScene(attrs){
	_.extend(this, attrs);
	
	this.status = -1; //not started, 0 - happening, 1 - stopped
	
	if(this.triggerElement){
		if(typeof(this.triggerElement) === 'string'){
			this.triggerElement = $(this.triggerElement);
		}
		this.offset = this.triggerElement.offset().top - $( window ).height();
		
		if(!this.duration)
			this.duration = Math.max($(this.triggerElement).prop('scrollHeight'), $( window ).height());
		if(this.delayPercent){
			this.offset += this.delayPercent/100 * this.duration;
			this.duration = (1 - this.delayPercent/100) * this.duration;// new duration will be squeezed
		}
	}
	this.end = this.offset + this.duration;
	
	/** @property scrollPanel - will be setup by ScrollableAmin.addScene()*/
}



AnimScene.prototype = {
	
	_startup:function(reverse){
		if(this.startup){
			this.startup(reverse);
		}
	},
	
	_teardown:function(reverse){
		if(this.teardown){
			this.teardown(reverse);
		}
	},
	
	beat: function(position){
		// relationship between status and calling startup and teardown
		// status: -1 -> 0: startup(false)
		// status: 1 -> 0: teardown(true)
		// status: 0 -> -1: startup(true)
		// status: 1 -> -1: teardown(true) & startup(true)
		// status: 0 -> 1: teardown(false)
		// status: -1 -> 1: startup(false) & teardown(false)
		
		if(position >= this.offset && position < this.end){
			var progress = position - this.offset;
			var time = progress / this.duration;
			
			if(this.status === -1){
				this._startup(false);
			}else if(this.status === 1){
				this._teardown(true);
			}
			if(this.timeline){
				this.timeline.seek(time);
			}
			if(this.onScroll){
				this.onScroll(progress, time);
			}
			this.status = 0;
		}else if(this.status !== -1 && position < this.offset){
			if(this.status === 0){
				this._startup(true);
			}else if(this.status === 1){
				this._teardown(true);
				this._startup(true);
			}
			this.status = -1;
			if(this.timeline)
				this.timeline.seek(0);
			if(this.onScroll){
				this.onScroll(0, 0);
			}
		}else if(this.status !== 1 && position >= this.end){
			if(this.status === 0){
				this._teardown(false);
			}else if(this.status === -1){
				this._startup(false);
				this._teardown(false);
			}
			
			this.status = 1;
			if(this.timeline)
				this.timeline.seek(1);
			if(this.onScroll){
				this.onScroll(this.duration, 1);
			}
		}else{
			//console.log(position + ' ' + this.offset + ' '+ this.end + ' '+ this.status);
		}
	}
}

/**
@param el element or window
*/
function ScrollableAnim(el){
	this.panel = el;
	this.scenes = [];
	var self = this;
	//el.on('scroll', createIntervalEventChecker(16,
	//	function(e){
	//		var scrolled = el.scrollTop();
	//		if(scrolled < 0)
	//			return;
	//		self.scenes.forEach(function(sc){
	//			sc.beat(scrolled);
	//		});
	//}));
	el.on('scroll', 	function(e){
		setTimeout(function(){
			var scrolled = el.scrollTop();
			if(scrolled < 0)
				return;
			self.scenes.forEach(function(sc){
				sc.beat(scrolled);
			});
		}, 0);
	});
}

ScrollableAnim.prototype ={
	addScene: function(scene){
		this.scenes.push(scene);
		scene.scrollPanel = this.panel;
	},
	
	scene: function(attrs){
		var scene = new AnimScene(attrs);
		this.addScene(scene);
		return scene;
	},
	
	pin: function(el){
		if(typeof(el) === 'string')
			el = $(el);
		el = el.first();
		var pinPlaceholder = el.next('.scl-anim-pl');
		if(!pinPlaceholder || pinPlaceholder.length === 0){
			pinPlaceholder = $('<div class="scl-anim-pl"></div>');
			pinPlaceholder.css({
				display: 'none',
				margin: '0',
				padding: '0'
			});
			el.after(pinPlaceholder);
		}
		
	
		var scrollPanelTop = 0, scrollPanelLeft = 0;
		var scrollPanelOffset = this.panel.offset();
		if(scrollPanelOffset){
			scrollPanelTop = scrollPanelOffset.top;
			scrollPanelLeft = scrollPanelOffset.left;
		}
		
		console.log('pin at '+ (el.offset().top - scrollPanelTop - this.panel.scrollTop()));
		
		// to avoid reflow being forced during rendering, get all needed data before setting css
		var display = el.css('display');
		var ow = el.outerWidth(true);
		var oh = el.outerHeight(true);
		var w = el.css('width');
		var h = el.css('height');
		var top = el.offset().top, left = el.offset().left;
		pinPlaceholder.addClass(el.prop('className'));
		pinPlaceholder.css({
				display: display,
				width: ow,
				height: oh
		});
		
		el.css({
				top: top - scrollPanelTop - this.panel.scrollTop() + 'px',
				left: left - scrollPanelLeft - this.panel.scrollLeft() + 'px',
				position: 'fixed',
				width: w,
				height: h,
		});
	},
	
	unpin: function(el){
		if(typeof(el) === 'string')
			el = $(el);
		el.first().css({
			top: '',
			left: '',
			position: '',
			width: '',
			height: '',
		});
		
		el.first().next('.scl-anim-pl').css({
				display: 'none'
		});
	}
};
