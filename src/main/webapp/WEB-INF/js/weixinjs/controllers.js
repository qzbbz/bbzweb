angular.module('qzapp.controllers', [])

.controller('MyInboxController', function($scope, $http, $ionicLoading) {

	$scope.uploadImg = "img/logo.jpg";

	$ionicLoading.show({
		template: '正在上传...'
	})
	$http.jsonp('http://api.openbeerdatabase.com/v1/breweries.json?callback=JSON_CALLBACK').then(function(result) {
		$ionicLoading.hide()
	})

	$scope.captureBill = function() {
		$scope.uploadImg = "img/logo1.jpg";
	}

	$scope.uploadBill = function() {
		$scope.uploadImg = "img/logo.jpg";
	}

})

.controller('UploadBillController', function($scope, $http, $ionicLoading, $location, $ionicModal) {
	$scope.openId = "";
	$scope.mainImg = "images/logo.jpg";
	$scope.showMainContent = false;
	$scope.showNetError = false;
	$scope.showNoBind = false;
	$scope.billList = new Array();

	function billListHasComtain(ele) {
		var i = $scope.billList.length;
		while(i > 0) {
			if($scope.billList[i].img === ele) return true;
			i--;
		}
		return false;
	}
	
	$ionicLoading.show({
		template: '正在获取数据...'
	})
	
	Object.toparams = function ObjecttoParams(obj) {
	    var p = [];
	    for (var key in obj) {
	        p.push(key + '=' + encodeURIComponent(obj[key]));
	    }
	    return p.join('&');
	};
	
	$http.get('/getUserOpenId').success(function(response) {
		if (response.openId == null || response.openId == "") {
			alert("无法获取openid,请重新进入!");
			$ionicLoading.hide();
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
				if (data.error == "true") {
					alert(data.message);
				} else if(data.message == "noBind") {
					$scope.showNoBind = true;
				} else if(data.message == "hasBind") {
					$http.get('/getJsConfigInfo?url=' + encodeURIComponent(location.href.split('#')[0])).success(function(response) {
						wx.config({
							debug: true,
							appId: 'wx309df15b6ddc5371',
							timestamp: response.timestamp,
							nonceStr: response.nonceStr,
							signature: response.signature,
							jsApiList: [
								'chooseImage',
								'previewImage',
								'uploadImage'
							]
						});
						$scope.showMainContent = true;
						$ionicLoading.hide()
					}).error(function(response){
						$scope.showNetError = true;
						$ionicLoading.hide()
					})
				} else {
					$scope.showNetError = true;
				}
				$ionicLoading.hide();
			}).error(function(response) {
				$scope.showNetError = true;
				$ionicLoading.hide();
			})
		}
	}).error(function(response) {
		$scope.showNetError = true;
	})
	
	$scope.captureBill = function() {
		var images = {
			localId: [],
		    serverId: []
		};
		wx.chooseImage({
			success: function (res) {
				for(var i=0; i<res.localIds.length; i++) {
					if(!billListHasComtain(res.localIds[i])) {
						var date = new Date();
						var time = date.getFullYear() + "-" + ((date.getMonth() + 1) < 10 ? "0" + (date.getMonth() + 1) : (date.getMonth() + 1)) + 
								"-" +(date.getDate() < 10 ? "0" + date.getDate() : date.getDate()) + " " + 
								(date.getHours() < 10 ? "0" + (date.getHours() < 10) : date.getHours()) + ":" +
								(date.getMinutes() < 10 ? "0" + (date.getMinutes() < 10) : date.getMinutes()) + ":" + 
								(date.getSeconds() < 10 ? "0" + (date.getSeconds() <10) : date.getSeconds());
						var id = new Date().getTime();
						var billData = {"img":res.localIds[i], "time":time, "id":id}
						$scope.billList.push(billData);
						alert($scope.billList[0].img);
					}
				}
		    },
		    fail : function(res) {
		    	if(res.errMsg === "system:function not exist") {
		    		alert('微信版本过低，请升级！');
		    	}
		    }
		});
		
	}

	$scope.uploadBill = function() {
		var status = false;
		wx.uploadImage({
		    localId: '',
		    isShowProgressTips: 1,
		    success: function (res) {
		        var serverId = res.serverId;
		        status = true;
		    }
		
		});
	}
	
	$scope.openModal = function(bill) {
		$scope.modal.show();
		$scope.bill = bill;
	};
	$scope.closeModal = function() {
		$scope.modal.hide();
	};
	$scope.$on('$destroy', function() {
		$scope.modal.remove();
	});
	$scope.$on('modal.hide', function() {
	});
	$ionicModal.fromTemplateUrl('my-modal.html', {
		scope: $scope,
		animation: 'slide-in-up'
	}).then(function(modal) {
		$scope.modal = modal;
	});

})

.controller('BindCompanyController', function($scope, $http, $ionicLoading) {

	$scope.showHasBind = false;
	$scope.showNoBind = false;
	$scope.showNetError = false;
	$scope.companyName = "";
	$scope.deptName = "";
	$scope.openId = "";

	$scope.submitBind = function() {
		var inviteCode = angular.element(document.querySelector('#inviteCode')).val();
		if (inviteCode != "") {
			$ionicLoading.show({
				template: '正在提交数据...'
			})

			$http({
				url: '/userBindCompany',
				method: "POST",
				headers: {
					'Content-Type': 'application/x-www-form-urlencoded'
				},
				data: Object.toparams({'openId': $scope.openId,	'inviteCode' : inviteCode})
			}).success(function(data) {
				if (data.error_code == "true") {
					alert(data.error_message);
				} else {
					$scope.showNoBind = false;
					$scope.showHasBind = true;
					$scope.companyName = data.companyName;
					$scope.companyDepartment = data.companyDepartment;
				}
				$ionicLoading.hide();
			}).error(function(response) {
				$scope.showNetError = true;
				$ionicLoading.hide();
			});
		}
	}

	$ionicLoading.show({
		template: '正在获取数据...'
	})

	Object.toparams = function ObjecttoParams(obj) {
	    var p = [];
	    for (var key in obj) {
	        p.push(key + '=' + encodeURIComponent(obj[key]));
	    }
	    return p.join('&');
	};
	
	$http.get('/getUserOpenId').success(function(response) {
		if (response.openId == null || response.openId == "") {
			alert("无法获取openid,请重新进入!");
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
					alert(data.error_message);
				} else if(data.bind_status == "not_bind") {
					$scope.showNoBind = true;
				} else if(data.bind_status == "has_bind") {
					$scope.showHasBind = true;
					$scope.companyName = data.companyName;
					$scope.deptName = data.deptName;
				}
				$ionicLoading.hide();
			}).error(function(response) {
				$scope.showNetError = true;
				$ionicLoading.hide();
			})
		}
		$ionicLoading.hide();
	}).error(function(response) {
		$scope.showNetError = true;
		$ionicLoading.hide();
	})
})

.controller('MyBillsController', function($scope, $http, $ionicLoading, $ionicModal) {

	$ionicLoading.show({
		template: '正在获取数据...'
	})

	$scope.uploadedRefresh = function() {
		$scope.$broadcast('scroll.refreshComplete');
	}

	$scope.showUploadedBill = function(billId) {

	}

	$ionicModal.fromTemplateUrl('my-modal.html', {
		scope: $scope,
		animation: 'slide-in-up'
	}).then(function(modal) {
		$scope.modal = modal;
	});
	$scope.openModal = function(bill) {
		$scope.modal.show();
		$scope.bill = bill;
	};
	$scope.closeModal = function() {
		$scope.modal.hide();
	};
	//当我们用到模型时，清除它！
	$scope.$on('$destroy', function() {
		$scope.modal.remove();
	});
	// 当隐藏的模型时执行动作
	$scope.$on('modal.hide', function() {
		// 执行动作
	});
	// 当移动模型时执行动作
	$scope.$on('modal.removed', function() {
		// 执行动作
	});

	$http({
		url: 'http://localhost/getMyBills',
		method: "POST",
		headers: {
			'Content-Type': 'application/x-www-form-urlencoded'
		},
		data: {
			'Code': 'test data'
		}
	}).success(function(data) {
		//alert(JSON.stringify(data));
		var results = data.sort(function(a, b) {
			return parseInt(a.uploadedTime) - parseInt(b.uploadedTime);
		});
		$scope.uploadedLists = results;
		$ionicLoading.hide();
	}).error(function(response) {
		$ionicLoading.hide();
	});

	$scope.captureBill = function() {
		$scope.uploadImg = "img/logo1.jpg";
	}

	$scope.uploadBill = function() {
		$scope.uploadImg = "img/logo.jpg";
	}
});