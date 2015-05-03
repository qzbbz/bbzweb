angular.module('qzapp.controllers', [])

.controller('MyInboxController', function($scope, $http, $ionicLoading,  $location, $ionicModal, $ionicTabsDelegate) {

	$scope.openId = "";
	$scope.showNoBind = false;
	$scope.showNetError = false;
	$scope.showMainContent = false;
	$scope.progressingList = new Array();
	$scope.finishedList = new Array();

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
					$scope.showMainContent = true;
					$http.get('/getMyInbox?openId=' + $scope.openId).success(function(response) {
						if(response.processingList != null) {
							processingList = response.processingList;
						}
						if(response.finishedList != null) {
							finishedList = response.finishedList;
						}
						$scope.apply();
					}).error(function(response) {
						$scope.showNetError = true;
					})
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
	
	$scope.uploadedRefresh = function() {
		$scope.$broadcast('scroll.refreshComplete');
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
	$scope.$on('$destroy', function() {
		$scope.modal.remove();
	});
	$scope.$on('modal.hide', function() {
	});
	$scope.$on('modal.removed', function() {
	});

	$scope.approval = function(bill, status) {
		$ionicLoading.show({
			template: '正在提交数据...'
		})
		$http.get('/approvalBill?invoiceId=' + bill.invoice_id + '&approvalId=' + bill.approval_id + '&userId=' + bill.user_id).success(function(response) {
			$ionicLoading.hide();
			if(response.status == 'success') {
				var ele = null;
				for(var i=0; i<$scope.progressingList.length; i++) {
					if(bill === $scope.progressingList[i]) {
						ele = $scope.progressingList[i];
						$scope.progressingList.pop();
						break;
					}						
				}
				if(ele != null) {
					finishedList.push(ele);
				}
				scope.$apply();
				alert("Success!");
			} else {
				alert("Failed!");
			}
			
		}).error(function(response) {
			$ionicLoading.hide();
			alert("Network error.");
		})
	}
	
})

.controller('UploadBillController', function($scope, $http, $ionicLoading, $location, $ionicModal, $ionicTabsDelegate) {
	$scope.openId = "";
	$scope.showMainContent = false;
	$scope.showNetError = false;
	$scope.showNoBind = false;
	$scope.billList = new Array();

	function billListHasComtain(ele) {
		var i = $scope.billList.length;
		while(i > 0) {
			return true;
			if($scope.billList[i-1].img === ele) {
				return true;
			}
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
				if (data.error_code != "0") {
					alert(data.message);
				} else if(data.bind_status == "not_bind") {
					$scope.showNoBind = true;
				} else if(data.bind_status == "has_bind") {
					$http.get('/getJsConfigInfo?url=' + encodeURIComponent(location.href.split('#')[0])).success(function(response) {
						wx.config({
							//debug: true,
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
						$ionicLoading.hide();
					}).error(function(response){
						$scope.showNetError = true;
						$ionicLoading.hide();
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
								(date.getHours() < 10 ? "0" + date.getHours() : date.getHours()) + ":" +
								(date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes()) + ":" + 
								(date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds());
						var id = new Date().getTime();
						var billData = {"img":res.localIds[i].toString(), "time":time, "id":id}
						$scope.billList.push(billData);
						$scope.apply();
						$ionicTabsDelegate.select(2);
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
		wx.uploadImage({
		    localId: $scope.billList[0].img,
		    isShowProgressTips: 1,
		    success: function (res) {
		    	$http.get('/downloadUserBill?openId=' + $scope.openId + '&serverId=' + res.serverId).success(function(response) {
		    		if(response.upload_status == "success") {
		    			alert("Successed in uploading the bill.");
		    			$scope.billList.pop();
		    			$scope.apply();
		    			//$ionicTabsDelegate.select(0);
		    		} else {
		    			alert("Failed in uploading the bill, please retry!");
		    		}
		    	
		    	}).error(function(response) {
		    		alert("Failed in uploading the bill.");
		    	})
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
				if (data.error_code != "0" || data.invite_code_error != null) {
					alert(data.invite_code_error);
				} else {
					$scope.showNoBind = false;
					$scope.showHasBind = true;
					$scope.companyName = data.companyName;
					$scope.deptName = data.deptName;
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

	$scope.openId = "";
	$scope.showNoBind = false;
	$scope.showNetError = false;
	$scope.showMainContent = false;
	$scope.uploadedList = new Array();
	$scope.progressingList = new Array();
	$scope.finishedList = new Array();
	
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
					$scope.apply();
				} else if(data.bind_status == "has_bind") {
					$scope.showMainContent = true;
					$http.get('/getMyBills?openId=' + $scope.openId).success(function(response) {
						if(response.uploadedList != null) {
							for(var i=0; i<response.uploadedList.length; i++) {
								$scope.uploadedList.push(response.uploadedList[i]);
							}
							$scope.apply();
						}
						if(response.processingList != null) {
							for(var i=0; i<response.processingList.length; i++) {
								$scope.progressingList.push(response.processingList[i]);
							}
							$scope.apply();
						}
						if(response.finishedList != null) {
							for(var i=0; i<response.finishedList.length; i++) {
								$scope.finishedList.push(response.finishedList[i]);
							}
							$scope.apply();
						}
						$scope.apply();
					}).error(function(response) {
						$scope.showNetError = true;
					})
				}
				$scope.apply();
				$ionicLoading.hide();
			}).error(function(response) {
				$scope.showNetError = true;
				$ionicLoading.hide();
			})
	    }
		$ionicLoading.hide();
		$scope.apply();
	}).error(function(response) {
		$scope.showNetError = true;
		$ionicLoading.hide();
	})
	
	$scope.uploadedRefresh = function() {
		$scope.$broadcast('scroll.refreshComplete');
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
	$scope.$on('$destroy', function() {
		$scope.modal.remove();
	});
	$scope.$on('modal.hide', function() {
	});
	$scope.$on('modal.removed', function() {
	});
});