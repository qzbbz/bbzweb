angular.module('qzapp.controllers', [])

.config(function($mdIconProvider) {
    $mdIconProvider
      .icon('home', '../../img/weixinimg/ic_home_24px.svg', 24)
      .icon('camera', '../../img/weixinimg/ic_camera_alt_24px.svg', 24)
      .icon('upload', '../../img/weixinimg/ic_cloud_upload_24px.svg', 24)
      .icon('audit', '../../img/weixinimg/ic_event_24px.svg', 24)
      .icon('inbox', '../../img/weixinimg/ic_inbox_24px.svg', 24);
})

.controller('AppCtrl', function($scope, $timeout, $mdBottomSheet, $mdDialog) {

	$scope.showMainContent = true;
	$scope.showUploadContent = false;
	$scope.showInboxContent = false;
	$scope.showAuditContent = false;
	$scope.showRefresh = false;
	$scope.hasBindCompany = true;
	
	$scope.uploadBills = new Array();
    $scope.auditBills = new Array();
	$scope.infoBills = new Array();
	
	Object.toparams = function ObjecttoParams(obj) {
	    var p = [];
	    for (var key in obj) {
	        p.push(key + '=' + encodeURIComponent(obj[key]));
	    }
	    return p.join('&');
	};
	
	
	
	$scope.cameraClick = function() {
	    $timeout(function() {
			$scope.showMainContent = false;
	      	$scope.showUploadContent = true;
	      	$scope.showInboxContent = false;
	      	$scope.showAuditContent = false;
	      	$scope.showRefresh = false;
		}, 3000);
    };
    
    $scope.refreshClick = function(ev) {
	    $mdDialog.show({
		      controller: RefreshController,
		      templateUrl: 'refresh.dialog.html',
		      targetEvent: ev
		});
		$timeout(function() {
			$mdDialog.cancel();
		}, 3000);
    };
		
	$scope.onekeyUploadPress = function(event) {
	    $mdDialog.show(
	      $mdDialog.alert()
	        .title('出错了')
	        .content('请勾选需要上传的发票，并确认发票金额已经填写！')
	        .ok('我知道了')
	        .targetEvent(event)
	    );
    };	
    
    $scope.onekeyAuditPress = function(event) {
	    $mdDialog.show(
	      $mdDialog.alert()
	        .title('出错了')
	        .content('请您勾选需要审批的发票！')
	        .ok('我知道了')
	        .targetEvent(event)
	    );
    };
		
	$scope.uploadBillListItemClick = function(ev, $index) {
	     $mdDialog.show({
		      controller: UploadBillDialogController,
		      templateUrl: 'uploadBill.dialog.html',
		      targetEvent: ev,
		      locals : {
              	content : $scope.uploadBills[$index]
              }
		 })
		 .then(function(billAmount) {
		 	  $scope.uploadBills[$index].amount = billAmount;
		 });
  	};
  	
  	$scope.infoBillListItemClick = function(ev, $index) {
	     $mdDialog.show({
		      controller: InfoBillDialogController,
		      templateUrl: 'infoBill.info.dialog.html',
		      targetEvent: ev,
		      locals : {
              	content : $scope.infoBills[$index]
              }
		 })
		 .then(function(billAmount) {
		 	  $scope.messages[$index].amount = billAmount;
		 });
  	};
	
	$scope.auditBillListItemClick = function(ev, $index) {
	     $mdDialog.show({
		      controller: AuditBillDialogController,
		      templateUrl: 'auditBill.dialog.html',
		      targetEvent: ev,
		      locals : {
              	content : $scope.auditBills[$index]
              }
		 })
		 .then(function(answer) {
		 	  $scope.alert = 'You said the information was "' + answer + '".';
		 }, function() {
		      $scope.alert = 'You cancelled the dialog.';
		 });
  	};
	
	$scope.showListBottomSheet = function($event) {
	    $mdBottomSheet.show({
	      templateUrl: 'bottom-sheet-list-template.html',
	      controller: 'ListBottomSheetCtrl',
	      targetEvent: $event
	    }).then(function(index) {
	      if(!$scope.hasBindCompany) {
		    $mdDialog.show(
		      $mdDialog.alert()
		        .title('出错了')
		        .content('您还没有绑定公司，请您先绑定公司！')
		        .ok('我知道了')
		        .targetEvent(event)
		    );
		    return;
	      }
	      switch(index) {
	      	case 0:
	      	  $scope.showMainContent = true;
	      	  $scope.showUploadContent = false;
	      	  $scope.showInboxContent = false;
	      	  $scope.showAuditContent = false;
	      	  $scope.showRefresh = false;
	      	  break;
	      	case 1:
	      	  $scope.showMainContent = false;
	      	  $scope.showUploadContent = true;
	      	  $scope.showInboxContent = false;
	      	  $scope.showAuditContent = false;
	      	  $scope.showRefresh = false;
	      	  break;
	      	case 2:
	      	  $scope.showMainContent = false;
	      	  $scope.showUploadContent = false;
	      	  $scope.showInboxContent = true;
	      	  $scope.showAuditContent = false;
	      	  $scope.showRefresh = true;
	      	  break;
	      	case 3:
	      	  $scope.showMainContent = false;
	      	  $scope.showUploadContent = false;
	      	  $scope.showInboxContent = false;
	      	  $scope.showAuditContent = true;
	      	  $scope.showRefresh = true;
	      	  break;
	      }
	    });
  	};

})

.controller('ListBottomSheetCtrl', function($scope, $mdBottomSheet) {
  $scope.items = [
    { name: '首页', icon: 'home' },
    { name: '发票上传', icon: 'upload' },
    { name: '我的收件箱', icon: 'inbox' },
    { name: '发票审核', icon: 'audit' },
  ];
  $scope.listItemClick = function($index) {
    var clickedItem = $scope.items[$index];
    $mdBottomSheet.hide($index);
  };
});

function UploadBillDialogController($scope, $mdDialog, content) {
	
  $scope.content = content;
  $scope.bill_amount = '';
    
  $scope.hide = function() {
    $mdDialog.hide();
  };
  $scope.cancel = function() {
    $mdDialog.cancel();
  };
  $scope.answer = function() {
  	if($scope.bill_amount != null && $scope.bill_amount != '')
    	$mdDialog.hide($scope.bill_amount);
  };
}

function InfoBillDialogController($scope, $mdDialog, content) {
	
  $scope.content = content;
    
  $scope.hide = function() {
    $mdDialog.hide();
  };
  $scope.cancel = function() {
    $mdDialog.cancel();
  };
  $scope.answer = function() {
    $mdDialog.hide($);
  };
}

function AuditBillDialogController($scope, $mdDialog, content) {
	
  $scope.content = content;
  
  $scope.hide = function() {
    $mdDialog.hide();
  };
  $scope.cancel = function() {
    $mdDialog.cancel();
  };
  $scope.answer = function(answer) {
    $mdDialog.hide(answer);
  };
}

function RefreshController($scope, $mdDialog) {
	  
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