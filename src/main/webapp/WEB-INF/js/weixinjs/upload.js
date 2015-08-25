
$(function(){

	jQuery.fn.center = function (obj) {
	    this.css("position","absolute");
	    this.css("top", Math.max(0, (($(window).height() - obj.height()) / 2) + 
	                                                $(window).scrollTop()) + "px");
	    this.css("left", Math.max(0, (($(window).width() - $(this).outerWidth()) / 2) + 
	                                                $(window).scrollLeft()) + "px");
	    return this;
	}
	
	Object.toparams = function ObjecttoParams(obj) {
	    var p = [];
	    for (var key in obj) {
	        p.push(key + '=' + encodeURIComponent(obj[key]));
	    }
	    return p.join('&');
	};
	
	function isNumber(str){
	     var re = /^[0-9]+.?[0-9]*$/;
	     if (!re.test(str)){
	    	 return false;
	     }
	     return true;
	}
	
	function isEmail(email) {  
	    var pattern =   /^([\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;  
	    if (!pattern.test(email)) {
	        return false;  
	    }  
	    return true;  
	}
	
	var uploadBillList = new Array();
	
	var draftBillList = new Array();
	
	var progressingBillList = new Array();
	
	var finishedBillList = new Array();
	
	var needAuditBillList = new Array();
	
	var expenseTypeList = new Array();
	
	var hasBindCompany = false;
	
	var userOpenId = '';
	
	 var box = {
	      overlay : null,
	      wrap : null,
	      content : null,
	      outer : null,
	      done_f : null,
	      cancel_f : null,
	      close : null,
	      enable: true,
	      init : function() {
	        var self = this;
	        if ($("#box-wrap").length) {
	          return;
	        }

	        $('body').append(
	          this.overlay = $('<div id="box-overlay"></div>'),
	          this.wrap = $('<div id="box-wrap"></div>')
	        );

	        this.outer = $('<div id="box-outer"></div>').appendTo( this.wrap );

	        this.outer.append(
	          this.content = $('<div id="box-content"></div>'),
	          this.close = $('<a id="box-close" href="#"><i class="icon-remove"></i></a>')
	        );

	        this.overlay.click(function(){
	        	if(self.enable){
	        		self.hide();
	        	}
	         
	        });

	        this.close.click(function() {
	          self.hide();
	          return false;
	        });
	        

	       $('#box-content').on('click', '.actions .submit', function(event) {
	          if(self.done_f){
	            self.done_f.call();
	          }
	          return false;
	        });

	       $('#box-content').on('click', '.actions .cancel', function(event) {
	          if(self.cancel_f){
	            self.cancel_f.call();
	          }
	          self.hide();
	          return false;
	      });

	      },
	      loading: function(content){
	    	 $(window).bind( 'touchmove', touchScroll );
	      	var self = this;
	      	self.html(content);
	      	self.enable = false;
	      	self.wrap.center(self.wrap);
	      	self.show();
	      },
	      clearLoading:function(){
	    	  this.enable = true;
	      	this.hide();
	      },
	      show : function(){
	        var self = this;
	          this.overlay.css({
	            'background-color' : '#777',
	            'opacity' : 0.7,
	            'height' : $(document).height()
	          }).show();
	          // fadeIn('fast', function() {
	              
	          // });
	          self.setposition();

	          $(window).bind('resize.fb',function(event) {
	            self.setposition();
	          });
	      },
	      setposition : function(){
	        var self = this;
	        var position = self.wrap.position();
	            self.wrap.css({
	              /*marginLeft: - self.wrap.width() / 2,
	              marginTop: - self.wrap.height() / 2*/
	            }).show();
	      },
	      hide : function(){
	        var self = this;
	        this.wrap.hide();
	        self.overlay.hide();
	        $(window).unbind('resize.fb');
	        $(window).unbind( 'touchmove', touchScroll );
	      },
	      html : function(html){
	        this.content.html(html);
	      },
	      done : function(f){
	        this.done_f = f;
	      },
	      cancel : function(f){
	        this.cancel_f = f;
	      }
	  }


	box.init();

	var touchScroll = function( event ) {
    	event.preventDefault();
	};

	var menu, Tcache = {}, curC;

	function menu(){
		var menuObj = $('.icon-menu.fa');
		var menuContainer = $('header .menu');
		var body = $('body');
		var self = this;

		function bindEvent(){
			menuObj.click(function(){
				handleEvent();
			})
		}

		$(document).click(function(event){
			// body.removeClass('nav-show');
		})

		function handleEvent(){
			if(!hasBindCompany) {
				alert("您还没有绑定公司，请退出点击设置菜单，进行公司绑定！");
				return;
			}
			if(body.hasClass('nav-show')){
				self.hide();
			}else{
				self.show();
			}
		}


		this.hide = function(){
			body.removeClass('nav-show');
		}

		this.show = function(){
			body.addClass('nav-show');
		}

		bindEvent();
	}
	
	menu = new menu();

	function setMenu(menu){
		getCurrentPage().find('.current-menu').html(menu);
	}

	$('[menu-page]').click(function(){
		var obj = $(this), ct = obj.attr('menu-page');
		setMenu(obj.text());
		setControler(ct);
		menu.hide();
	})

	function setControler(ct){
		if(controler[ct]){
			curC = ct;
			controler[ct].init && controler[ct].init();
		}
	}


	function getTemplate(id){
		if(Tcache[id]) return Tcache[id];
		Tcache[id] = $('#'+id).html();
		return Tcache[id];
	}

	function getCurrentPage(){
		return $(".ui-page-active[data-role='page']");
	}

	function appendTemplate(html){
		var curPage = getCurrentPage();
		var main;
		if(curPage.length){
			main = curPage.find('#content');
		}else{
			main = $('#content');
		}
		main.empty().html(html);
	}

	/*$(document).on('scrollstart', '#content', function(event) {
		setTimeout(function() {
			var cc = $(window).scrollTop();
			if (cc < 10) {
				controler[curC] && controler[curC].refresh && controler[curC].refresh();
			}
		}, 100);
	})*/

	var controler = {};

	//  我的收件箱
	controler['inbox'] = {
			draftBillListview:null,
			progressingAuditListview: null,
			finishedAuditListview: null,
			listDraftBill : function(shouldRefresh) {
				var data = null;
				if(draftBillList != null && draftBillList.length != 0) {
					data = {draftBillList:draftBillList};
				}
				var template = getTemplate('draftBillList');
				Mustache.parse(template)
				var html = Mustache.render(template, data);

				this.draftBillListview.html(html);
				if(!shouldRefresh) {
					this.draftBillListview.listview();
				} else {
					this.draftBillListview.listview('refresh');
				}
			},
			listProgressingBill : function(shouldRefresh) {
				var data = null;
				if(progressingBillList != null && progressingBillList.length != 0) {
					data = {progressingBillList:progressingBillList};
				}
				var template = getTemplate('progressingBillList');
				Mustache.parse(template)
				var html = Mustache.render(template, data);
				this.progressingAuditListview.html(html);
				if(!shouldRefresh) {
					this.progressingAuditListview.listview();
				} else {
					this.progressingAuditListview.listview('refresh');
				}
			},
			listFinishedBill : function(shouldRefresh) {
				var data = null;
				if(finishedBillList != null && finishedBillList.length != 0) {
					data = {finishedBillList:finishedBillList};
				}
				var template = getTemplate('finishedBillList');
				Mustache.parse(template)
				var html = Mustache.render(template, data);
				this.finishedAuditListview.html(html);
				if(!shouldRefresh) {
					this.finishedAuditListview.listview();
				} else {
					this.finishedAuditListview.listview('refresh');
				}
			},
			render: function(){
				var html = getTemplate('inbox');

				appendTemplate(html);

				this.draftBillListview = $('#draft-bill-list');
				this.progressingAuditListview = $('#progressing-audit-list');
				this.finishedAuditListview = $('#finished-audit-list');

				this.listDraftBill(false);
				this.listProgressingBill(false);
				this.listFinishedBill(false);

				$('[data-role="tabs"]').tabs();
			},

			refresh: function() {
				box.wrap.addClass("box-wrap-gif");
				box.content.addClass("box-content-gif");
				box.loading('<html><body><img src="../../img/weixinimg/loading1.gif"></body></html>');
				getInboxBills(false);
				this.listDraftBill(true);
				this.listProgressingBill(true);
				this.listFinishedBill(true);
				box.wrap.removeClass("box-wrap-gif");
				box.content.removeClass("box-content-gif");
				box.clearLoading();
			},
			
			bindEvent: function(){
				var self = this;
				$('#onePressSubmitAudit').click(function(){
					var ids = [];
					self.draftBillListview.find('li').each(function(index, obj){
						var obj = $(obj), check = obj.find('input[type="checkbox"]');
						if(check.is(':checked')){
							ids.push(check.val());
						}
					})
					self.submit(ids);
				})
			},
			submit: function(ids){
					if(ids == null || ids.length == 0) {
						alert("请勾选需要提交审核的发票！");
					} else {
						box.wrap.addClass("box-wrap-gif");
						box.content.addClass("box-content-gif");
						box.loading('<html><body><img src="../../img/weixinimg/loading1.gif"></body></html>');
						var isValid = true;
						var submitList = new Array();
						for(var i=0; i<ids.length && isValid; i++) {
							for(var j=0; j<draftBillList.length; j++) {
								if(draftBillList[j].invoice_id == ids[i]) {
									if(draftBillList[j].amount=="0" || draftBillList[j].amount==0) {
										isValid = false;
										alert("您勾选的发票中，还存在没有填写金额的发票，请检查！");
										break;
									} else if(draftBillList[j].bill_expenseTypeId == null) {
										isValid = false;
										alert("您勾选的发票中，还存在没有选择费用类型的发票，请检查！");
										break;
									} else {
										submitList.push(draftBillList[j]);
									}
								}
							}
						}
						if(isValid) {
							var tmp = new Array();
							for(var i=0; i<submitList.length; i++) {
								var da = {"invoice_id":submitList[i].invoice_id, "bill_amount":submitList[i].bill_amount,"bill_expenseTypeId":submitList[i].bill_expenseTypeId};
								tmp.push(da);
							}
							var jsonData = "{\"submitBillEntities\":" +  JSON.stringify(tmp) + "}";
							$.ajax({ 
						        type : "POST", 
						        url  : "/submitBillListAudit?openId=" + userOpenId,
						        cache : false,
						        data : jsonData,
						        headers : {  
				                    'Content-Type' : 'application/json;charset=utf-8'  
				                },
						        success :  submitSuccess, 
						        error : submitError 
						    });
						} else {
							box.wrap.removeClass("box-wrap-gif");
							box.content.removeClass("box-content-gif");
							box.clearLoading();
						}
						function submitSuccess(data) {
							if (data.error_code == "0") {
								alert("您提交审核的发票已经全部提交成功！");
								var tmp = data.ids.split(" ");
								if(tmp != null && tmp.length != 0) {
									for(var i=0; i<tmp.length; i++) {
										for(var j=0; j<draftBillList.length; j++) {
											if(draftBillList[j].invoice_id == tmp[i]) {
												draftBillList.splice(j,1);
											}
										}
									}
								}
							} else if(data.error_code == "1" || data.error_code == "2"){
								alert(data.error_message);
							} else {
								alert(data.error_message + data.submit_count);
								var tmp = data.ids.split(" ");
								if(tmp != null && tmp.length != 0) {
									for(var i=0; i<tmp.length; i++) {
										for(var j=0; j<draftBillList.length; j++) {
											if(draftBillList[j].invoice_id == tmp[i]) {
												draftBillList.splice(j,1);
											}
										}
									}
								}
							}
							box.wrap.removeClass("box-wrap-gif");
							box.content.removeClass("box-content-gif");
							box.clearLoading();
							controler['inbox'].render();
						}
						function submitError() {
							alert("提交审核失败，可能是网络原因，请检查！");
							box.wrap.removeClass("box-wrap-gif");
							box.content.removeClass("box-content-gif");
							box.clearLoading();
						}
					}
				},
				init: function(){
					this.render();
					this.bindEvent();
				}
		}


	//  发票上传	
	controler['uploadBill'] = {
			uploadBillListview: null,
			render: function(){
				var self = this;
				var html = getTemplate('uploadBill');
				appendTemplate(html);

				this.uploadBillListview = $('#upload-bill-list');

				function listUploadBill(){
					var data = null;
					if(uploadBillList.length != 0) {
						data = {allUploadBillList:uploadBillList};
					}
					var template = getTemplate('uploadBillList');
					Mustache.parse(template)
					var html = Mustache.render(template, data);
					self.uploadBillListview.html(html);
					self.uploadBillListview.listview();
				}				
				listUploadBill();
			},
			bindEvent: function(){
				var self = this;
				$('#onePressUpload').click(function(){
					var ids = [];
					self.uploadBillListview.find('li').each(function(index, obj){
						var obj = $(obj), check = obj.find('input[type="checkbox"]');
						if(check.is(':checked')){
							ids.push(check.val());
						}
					})
					self.check(ids);
				})
			},

			check: function(ids){
				if(ids == null || ids.length == 0) {
					alert("请勾选需要上传的发票！");
				} else {
					box.wrap.addClass("box-wrap-gif");
					box.content.addClass("box-content-gif");
					box.loading('<html><body><img src="../../img/weixinimg/loading1.gif"></body></html>');
					var isValid = true;
					var submitList = new Array();
					for(var i=0; i<ids.length && isValid; i++) {
						for(var j=0; j<uploadBillList.length; j++) {
							if(uploadBillList[j].id == ids[i]) {
								if(uploadBillList[j].amount=="0" || uploadBillList[j].amount==0) {
									isValid = false;
									alert("您勾选的发票中，还存在没有填写金额的发票，请检查！");
									break;
								} else if(uploadBillList[j].expenseTypeId == null) {
									isValid = false;
									alert("您勾选的发票中，还存在没有选择费用类型的发票，请检查！");
									break;
								} else {
									submitList.push(uploadBillList[j]);
								}
							}
						}
					}
					if(isValid) {
						var finalSubmitList = new Array();
						var index = 0;
						var syncUpload = function() {
							var localId = submitList[index].img;
							wx.uploadImage({
							    localId: localId,
							    isShowProgressTips: 0,
							    success: function (res) {
							    	submitList[index].mediaId = res.serverId;
							    	finalSubmitList.push(submitList[index]);
							    	index++;
							    	if(index < submitList.length) {
							    		syncUpload();
							    	} else {
							    		finishWeixinUpload();
							    	}
							    },
							    fail:function() {
							    	if(index == submitList.length) {
							    		finishWeixinUpload();
							    	}
							    	index++;
							    	if(index < submitList.length) {
							    		syncUpload();
							    	} else {
							    		finishWeixinUpload();
							    	}
							    }
							});
						}
						function finishWeixinUpload() {
							var shouldUpload = false;
							if(finalSubmitList.length == 0) {
								alert("微信服务器无法接受图片上传，请稍后重试！");
								box.wrap.removeClass("box-wrap-gif");
								box.content.removeClass("box-content-gif");
								box.clearLoading();
							} else if(finalSubmitList.length != submitList.length) {
								alert("微信服务器没有成功接收全部图片，当前状态：" + finalSubmitList.length + "/" + submitList.length + "。下面开始向元升服务器上传数据。");
								shouldUpload = true;
							} else {
								shouldUpload = true;
							}
							if(shouldUpload) {
								var jsonData = "{\"uploadBillEntities\":" +  JSON.stringify(finalSubmitList) + "}";
								$.ajax({ 
							        type : "POST", 
							        url  : "/downloadUserBill?openId=" + userOpenId,  
							        cache : false,
							        data : jsonData,
							        headers : {  
					                    'Content-Type' : 'application/json;charset=utf-8'  
					                },
							        success :  submitSuccess, 
							        error : submitError 
							    });
							}
						}
						syncUpload();
					} else {
						box.wrap.removeClass("box-wrap-gif");
						box.content.removeClass("box-content-gif");
						box.clearLoading();
					}
					function submitSuccess(data) {
						if (data.error_code == "0") {
							alert("您选择上传的发票已经全部上传成功！");
							var tmp = data.ids.split(" ");
							if(tmp != null && tmp.length != 0) {
								for(var i=0; i<tmp.length; i++) {
									for(var j=0; j<uploadBillList.length; j++) {
										if(uploadBillList[j].id == tmp[i]) {
											uploadBillList.splice(j,1);
										}
									}
								}
							}
						} else if(data.error_code == "1" || data.error_code == "3"){
							alert(data.error_message);
						} else {
							alert(data.error_message + data.upload_count);
							var tmp = data.ids.split(" ");
							if(tmp != null && tmp.length != 0) {
								for(var i=0; i<tmp.length; i++) {
									for(var j=0; j<uploadBillList.length; j++) {
										if(uploadBillList[j].id == tmp[i]) {
											uploadBillList.splice(j,1);
										}
									}
								}
							}
						}
						box.wrap.removeClass("box-wrap-gif");
						box.content.removeClass("box-content-gif");
						box.clearLoading();
						controler['uploadBill'].init();
					}
					function submitError() {
						alert("上传发票出错，请检查！");
						box.wrap.removeClass("box-wrap-gif");
						box.content.removeClass("box-content-gif");
						box.clearLoading();
					}
				}
			},
			init: function(){
				this.render();
				this.bindEvent();
			}
	}

	controler['help'] = {
		render: function(){
			var self = this;
			var html = getTemplate('help');
			appendTemplate(html);
			$('[data-role="tabs"]').tabs();
		},
		init: function(){
			this.render();
		}
	}


//  发票审核
	controler['auditBill'] = {

		needAuditBillListview: null,
		listAuditBills: function(shouldRefresh) {
			var data = null;
			if(needAuditBillList != null && needAuditBillList.length != 0) {
				data = {needAuditBillList:needAuditBillList};
			}
			var template = getTemplate('need-audit-bill-list');
			Mustache.parse(template)
			var html = Mustache.render(template, data);
			this.needAuditBillListview.html(html);
			if(!shouldRefresh) {
				this.needAuditBillListview.listview();
			} else {
				this.needAuditBillListview.listview('refresh');
			}
		},
		render: function() {
			var html = getTemplate('auditBill');
			appendTemplate(html);
			this.needAuditBillListview = $('#audit-bill-list');
			this.listAuditBills(false);
		},
		refresh: function() {
			box.wrap.addClass("box-wrap-gif");
			box.content.addClass("box-content-gif");
			box.loading('<html><body><img src="../../img/weixinimg/loading1.gif"></body></html>');
			getNeedAuditBills(false);
			this.listAuditBills(true);
			box.wrap.removeClass("box-wrap-gif");
			box.content.removeClass("box-content-gif");
			box.clearLoading();
		},
		bindEvent: function() {
			var self = this;
			$('#onePressPass').click(function() {
				var ids = [];
				self.needAuditBillListview.find('li').each(function(index, obj) {
					var obj = $(obj),
						check = obj.find('input[type="checkbox"]');
					if (check.is(':checked')) {
						ids.push(check.val());
					}
				})
				self.submitAudit(ids, "0");
			});
			$('#onePressReject').click(function() {
				var ids = [];
				self.needAuditBillListview.find('li').each(function(index, obj) {
					var obj = $(obj),
						check = obj.find('input[type="checkbox"]');
					if (check.is(':checked')) {
						ids.push(check.val());
					}
				})
				self.submitAudit(ids, "1");
			})
		},
		
		submitAudit: function(ids, status) {
			var self = this;
			if(ids == null || ids.length == 0) {
				alert("请勾选需要提交审核的发票！");
			} else {
				box.wrap.addClass("box-wrap-gif");
				box.content.addClass("box-content-gif");
				box.loading('<html><body><img src="../../img/weixinimg/loading1.gif"></body></html>');
				var tmp = new Array();
				for(var i=0; i<ids.length; i++) {
					for(var j=0; j<needAuditBillList.length; j++) {
						if(needAuditBillList[j].invoice_id == ids[i]) {
							var da = {"invoice_id":needAuditBillList[j].invoice_id, "approval_id":needAuditBillList[j].approval_id,"user_id":needAuditBillList[j].user_id, "approval_status": status};
							tmp.push(da);
						}
					}
				}
				var jsonData = "{\"submitAuditBillResultEntities\":" +  JSON.stringify(tmp) + "}";
				$.ajax({ 
			        type : "POST", 
			        url  : "/approvalBill?openId=" + userOpenId,
			        cache : false,
			        data : jsonData,
			        headers : {  
	                    'Content-Type' : 'application/json;charset=utf-8'  
	                },
			        success :  submitSuccess, 
			        error : submitError 
			    });
				function submitSuccess(data) {
					if (data.error_code == "0") {
						alert("您提交的发票审核已经全部提交成功！");
						var tmp = data.ids.split(" ");
						if(tmp != null && tmp.length != 0) {
							for(var i=0; i<tmp.length; i++) {
								for(var j=0; j<needAuditBillList.length; j++) {
									if(needAuditBillList[j].invoice_id == tmp[i]) {
										needAuditBillList.splice(j,1);
									}
								}
							}
						}
					} else if(data.error_code == "1" || data.error_code == "2"){
						alert(data.error_message);
					} else {
						alert(data.error_message + data.submit_count);
						var tmp = data.ids.split(" ");
						if(tmp != null && tmp.length != 0) {
							for(var i=0; i<tmp.length; i++) {
								for(var j=0; j<needAuditBillList.length; j++) {
									if(needAuditBillList[j].invoice_id == tmp[i]) {
										needAuditBillList.splice(j,1);
									}
								}
							}
						}
					}
					box.wrap.removeClass("box-wrap-gif");
					box.content.removeClass("box-content-gif");
					box.clearLoading();
					self.listAuditBills(true);
				}
				function submitError() {
					alert("您提交的发票审核失败，可能是网络原因，请检查！");
					box.wrap.removeClass("box-wrap-gif");
					box.content.removeClass("box-content-gif");
					box.clearLoading();
				}
			}
		},

		init: function() {
			this.render();
			this.bindEvent();
		}
	}


	// 
	function billDetail(){
		$(document).on("click", ".ticket-list li", function(event) {
			if(event.target.nodeName == "INPUT") return;
			var obj = $(this), src = obj.data('src');
			show(src);
		})

		function show(src){
			box.html('<div id="billImgInfoDiv" style="background-color:white;"><img id="billImgInfo" src="'+src+'" style="max-width:100%;height:auto;"/></div>');
			$('#billImgInfo').on("load", function() {
				box.wrap.center(box.wrap);
				box.show();
				$(window).bind( 'touchmove', touchScroll );
			}).each(function() {
			  if(this.complete) $(this).load();
			});
		}
	}

	function setSelectOptions(obj) {
		obj.append("<option value='null'>请选择</option>");
		for(var i=0; i<expenseTypeList.length; i++) {
			var value = expenseTypeList[i].value;
			var text = expenseTypeList[i].name;
			obj.append("<option value='"+value+"'>"+text+"</option>");	
		}
	}

	function billEdit(){

		$(document).on("click", ".bill-list-upload li", function(event) {
			if(event.target.nodeName == "INPUT") return;
			var obj = $(this), src = obj.data('src');
			edit(obj, src);
		})

		function edit(obj, src){
			var template = getTemplate('editTicket');
			Mustache.parse(template)
			var html = Mustache.render(template, {src: src});

			box.html(html);
			box.wrap.center($('#editTicket'));
			setSelectOptions($('#expenseType'));
			box.show();
			$(window).bind( 'touchmove', touchScroll );
			box.done(function(){
				var price = $('#price').val();
				var typeId = $("#expenseType  option:selected").val();
				var typeName = $("#expenseType  option:selected").text();
				var id = $(obj).find('input').val();
				if(price == "" || !isNumber(price)){
					$('.price-empty-error').show();
				} else if(typeId == null || typeName == "请选择") {
					$('.type-select-error').show();				
				} else {
					$('.price-empty-error').hide();
					$('.type-select-error').hide();
					updateBillInfo(price, typeId, typeName, id);
					box.hide();
				}
			})

			function updateBillInfo(price, typeId, typeName, id){
				for(var i=0; i<uploadBillList.length; i++) {
					if(id == uploadBillList[i].id) {
						uploadBillList[i].amount = price;
						uploadBillList[i].expenseTypeName = typeName;
						uploadBillList[i].expenseTypeId = typeId;
						break;
					}
				}
				controler['uploadBill'].init();
			}
		}
	}
	
	function draftBillEdit(){

		$(document).on("click", ".draft-bill-list li", function(event) {
			if(event.target.nodeName == "INPUT") return;
			var obj = $(this), src = obj.data('src');
			edit(obj, src);
		})

		function edit(obj, src){
			var template = getTemplate('editTicket');
			Mustache.parse(template)
			var html = Mustache.render(template, {src: src});

			box.html(html);
			box.wrap.center($('#editTicket'));
			setSelectOptions($('#expenseType'));
			box.show();
			$(window).bind( 'touchmove', touchScroll );
			box.done(function(){
				var bill_amount = $('#price').val();
				var expenseTypeId = $("#expenseType  option:selected").val();
				var expenseTypeName = $("#expenseType  option:selected").text();
				var invoice_id = $(obj).find('input').val();
				if(bill_amount == "" || !isNumber(bill_amount)){
					$('.price-empty-error').show();
				} else if(expenseTypeId == null || expenseTypeName == "请选择") {
					$('.type-select-error').show();				
				} else {
					$('.price-empty-error').hide();
					$('.type-select-error').hide();
					updateBillInfo(bill_amount, expenseTypeId, expenseTypeName, invoice_id);
					box.hide();
				}
			})

			function updateBillInfo(price, typeId, typeName, id){
				//alert("price:" + price + " typeId:" + typeId + " typeName:" + typeName + "id:" + id);
				for(var i=0; i<draftBillList.length; i++) {
					if(id == draftBillList[i].invoice_id) {
						draftBillList[i].bill_amount = price;
						draftBillList[i].bill_expenseTypeName = typeName;
						draftBillList[i].bill_expenseTypeId = typeId;
						break;
					}
				}
				controler['inbox'].init();
			}
		}
	}

	billEdit();
	draftBillEdit();
	billDetail();
	
	$.ajax({ 
        type : "POST", 
        url  : "/getJsConfigInfo?url=" + encodeURIComponent(location.href.split('#')[0]),  
        cache : false,
        async: false,
        success : getJsConfigInfoSuccess, 
        error : ajaxRequestError 
    });
	
	function getJsConfigInfoSuccess(data,status) {
		wx.config({
			appId: 'wx333ea15ba860f932',
			timestamp: data.timestamp,
			nonceStr: data.nonceStr,
			signature: data.signature,
			jsApiList: [
				'chooseImage',
				'previewImage',
				'uploadImage'
			]
		});
	}
	
	$.ajax({ 
        type : "POST", 
        url  : "/getAllExpenseType",  
        cache : false,
        async: false,
        success : getAllExpenseTypeSuccess, 
        error : ajaxRequestError 
    });
	
	function getAllExpenseTypeSuccess(data,status) {
		expenseTypeList = data;
	}
	
	function ajaxRequestError() {
		alert("网络故障,无法获取weixinJsConfig信息，请稍后重试！");
	}
	
	function billListHasComtain(ele) {
		var i = uploadBillList.length;
		while(i > 0) {
			if(uploadBillList[i-1].img === ele) {
				return true;
			}
			i--;
		}
		return false;
	}
	
	$.ajax({ 
        type : "POST", 
        url  : "/getUserOpenId",  
        cache : false,
        async: false,
        data : "", 
        success : onGetUserOpenIdSuccess, 
        error : onGetUserOpenIdError 
    });
	
	function onGetUserOpenIdSuccess(data,status) {
		if (data.openId == "") {
			alert("无法获取您的微信Openid,请稍后重试！");
		} else {
			userOpenId = data.openId;
			$.ajax({ 
		        type : "POST", 
		        url  : "/checkBindCompany",  
		        cache : false,
		        async: false,
		        data : Object.toparams({openId:userOpenId}), 
		        success : checkBindCompanySuccess
		    });
		}
	}
	
	function onGetUserOpenIdError() {
		alert("无法获取您的微信Openid,请稍后重试！");
	}
	
	function checkBindCompanySuccess(data, status) {
		if(data.bind_status == "has_bind") {
			hasBindCompany = true;
		}
	}
	
	function getNeedAuditBills(isAsync) {
		if(userOpenId != null && userOpenId != '') {
			$.ajax({ 
		        type : "POST",
		        url  : "/getNeedAuditBills?openId=" + userOpenId,  
		        cache : false,
		        async: isAsync,
		        success : getNeedAuditBills 
		    });
			function getNeedAuditBills(data){
				if(data.processingList != null) {
					needAuditBillList = data.processingList;
					needAuditBillList.sort(function(a,b){return a.bill_date<b.bill_date?1:-1});
				} else {
					needAuditBillList = null;
				}
			}
		}
	}
	
	function getInboxBills(isAsync) {
		if(userOpenId != null && userOpenId != '') {
			$.ajax({ 
		        type : "POST",
		        url  : "/getInboxBills?openId=" + userOpenId,  
		        cache : false,
		        async: isAsync,
		        success : getInboxBills 
		    });
			function getInboxBills(data) {
				if(data.uploadedList != null) {
					draftBillList = data.uploadedList;
					draftBillList.sort(function(a,b){return a.bill_date<b.bill_date?1:-1});
				} else {
					draftBillList = null;
				}
				if(data.processingList != null) {
					progressingBillList = data.processingList;
					progressingBillList.sort(function(a,b){return a.bill_date<b.bill_date?1:-1});
				}else {
					progressingBillList = null;
				}
				if(data.finishedList != null) {
					finishedBillList = data.finishedList;
					finishedBillList.sort(function(a,b){return a.bill_date<b.bill_date?1:-1});
				}else {
					finishedBillList = null;
				}
			}
		}
	}
	
	getNeedAuditBills(false);
	getInboxBills(false);
	
	$('#camera').click(function(){
		if(!hasBindCompany) {
			alert("您还没有绑定公司，请退出点击设置菜单，进行公司绑定！");
			return;
		}
		var images = {
			localId: [],
		    serverId: []
		};
		box.wrap.addClass("box-wrap-gif");
		box.content.addClass("box-content-gif");
		box.loading('<html><body><img src="../../img/weixinimg/loading1.gif"></body></html>');
		
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
						var billData = {"id":date.getTime() + Math.floor(Math.random()*10000+1), "img":res.localIds[i].toString(), "time":time, "amount": 0, "expenseTypeName":"", "expenseTypeId":null, "mediaId":null}
						uploadBillList.push(billData);
					}
				}
				box.wrap.removeClass("box-wrap-gif");
				box.content.removeClass("box-content-gif");
				box.clearLoading();
				controler['uploadBill'].init();
				setMenu('发票上传');				
		    },
		    fail : function(res) {
		    	
		    	if(res.errMsg === "system:function not exist") {
		    		alert('微信版本过低，请升级！');
		    	} else {
		    		alert(res.errMsg);
		    	}
		    	box.wrap.removeClass("box-wrap-gif");
				box.content.removeClass("box-content-gif");
				box.clearLoading();
		    }
		});
	})
	
	controler['uploadBill'].init();
	setMenu('发票上传');
})