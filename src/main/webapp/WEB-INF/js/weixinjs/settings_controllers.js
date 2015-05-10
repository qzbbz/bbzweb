angular.module('qzapp.controllers', [])

.config(function($mdIconProvider) {
    $mdIconProvider
      .icon('home', '../../img/weixinimg/ic_home_24px.svg', 24)
      .icon('bind', '../../img/weixinimg/bind.svg', 24)
      .icon('settings', '../../img/weixinimg/settings.svg', 24);
})

.controller('AppCtrl', function($scope, $timeout, $mdBottomSheet, $mdDialog) {

	$scope.showMainContent = true;
	$scope.showBindContent = false;
	$scope.showSettingsContent = false;
	$scope.showHelp = false;
	$scope.hasBindCompany = false;
	$scope.inviteCode = '';
	$scope.settingName = '';
	$scope.settingEmail = '';
	$scope.companyName = '';
	$scope.companyDeptName = '';
	$scope.openId = '';
	
	Object.toparams = function ObjecttoParams(obj) {
	    var p = [];
	    for (var key in obj) {
	        p.push(key + '=' + encodeURIComponent(obj[key]));
	    }
	    return p.join('&');
	};
		
	$scope.helpClick = function() {
		$scope.showMainContent = true;
		$scope.showBindContent = false;
		$scope.showSettingsContent = false;
		$scope.showHelp = false;
	}
	
	function showNetErrorDialog() {
		$mdDialog.show(
				$mdDialog.alert()
					.title('出错了')
					.content('网络错误,请检查您的网络,并重新进入!')
					.ok('我知道了')
					.targetEvent(event)
		);
	}
	
	function showTipDialog(tips) {
		$mdDialog.show(
			$mdDialog.alert()
				.title('出错了')
				.content(tips)
				.ok('我知道了')
				.targetEvent(event)
		);
	}
	
	$http.get('/getUserOpenId').success(function(response) {
		$mdDialog.show({
		      controller: LoadingController,
		      templateUrl: 'refresh.dialog.html'
		});
		if (response.openId == null || response.openId == "") {
			$mdDialog.cancel();
			showTipDialog('无法获取您的openid,请重新进入!');
		} else {
			$scope.openId = response.openId;
			$http({
				url: '/checkBindCompany',
				method: "POST",
				headers: {
					'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
				},
				data: Object.toparams({openId: $scope.openId})
			}).success(function(data) {
				if (data.error_code != "0") {
					$mdDialog.cancel();
					showTipDialog(data.error_message);
				} else if(data.bind_status == "not_bind") {
					$scope.hasBindCompany = false;
				} else if(data.bind_status == "has_bind") {
					$scope.hasBindCompany = true;
				}
			}).error(function(response) {
				$mdDialog.cancel();
				showNetErrorDialog();
			})
	    }
	}).error(function(response) {
		$mdDialog.cancel();
		showNetErrorDialog();
	})
	$mdDialog.cancel();
	
	$scope.bindClick = function() {
		if($scope.inviteCode == '') {
			$http({
				url: '/userBindCompany',
				method: "POST",
				headers: {
					'Content-Type': 'application/x-www-form-urlencoded'
				},
				data: Object.toparams({'openId': $scope.openId,	'inviteCode' : $scope.inviteCode})
			}).success(function(data) {
				if (data.error_code != "0" || data.invite_code_error != null) {
					showTipDialog(data.invite_code_error);
				} else {
					$scope.hasBindCompany = true;
					$scope.companyName = data.companyName;
					$scope.companyDeptName = data.deptName;
				}
			}).error(function(response) {
				showNetErrorDialog();
			});
		}
	}	
	
	$scope.settingsClick = function() {
		if($scope.settingName == '' || $scope.settingEmail == '') {
			return;
		} else {
			return;
		}
	}
		
	$scope.showListBottomSheet = function($event) {
	    $mdBottomSheet.show({
	      templateUrl: 'bottom-sheet-list-template.html',
	      controller: 'ListBottomSheetCtrl',
	      targetEvent: $event
	    }).then(function(index) {
	      switch(index) {
	      	case 0:
	      	  $scope.showMainContent = true;
			  $scope.showBindContent = false;
			  $scope.showSettingsContent = false;
			  $scope.showHelp = false;
	      	  break;
	      	case 1:
	      	  $scope.showMainContent = false;
			  $scope.showBindContent = true;
			  $scope.showSettingsContent = false;
			  $scope.showHelp = true;
	      	  break;
	      	case 2:
	      	  if(!$scope.hasBindCompany) {
			    $mdDialog.show(
			      $mdDialog.alert()
			        .title('出错了')
			        .content('您还没有绑定公司，请您先绑定公司！')
			        .ok('我知道了')
			        .targetEvent(event)
			    );
		      } else {
		      	$scope.showMainContent = false;
			    $scope.showBindContent = false;
			    $scope.showSettingsContent = true;
			    $scope.showHelp = true;
		      }
	      	  break;
	      }
	    });
  	};

})

.controller('ListBottomSheetCtrl', function($scope, $mdBottomSheet) {
  $scope.items = [
    { name: '首页', icon: 'home' },
    { name: '绑定公司', icon: 'bind' },
    { name: '账号设定', icon: 'settings' },
  ];
  $scope.listItemClick = function($index) {
    var clickedItem = $scope.items[$index];
    $mdBottomSheet.hide($index);
  };
});

function LoadingController($scope, $mdDialog) {
	  $scope.hide = function() {
	    $mdDialog.hide();
	  };
	  $scope.cancel = function() {
	    $mdDialog.cancel();
	  };
	  $scope.answer = function() {
	    $mdDialog.hide();
	  };
}