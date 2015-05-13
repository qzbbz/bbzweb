
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



	$(document).on('scrollstart', '#content', function(event){
		setTimeout(function(){
			var cc =  $(window).scrollTop();
			if(cc < 10){
				controler[curC] && controler[curC].refresh && controler[curC].refresh();
			}
		}, 100);
	})

	var controler = {};

	//  我的发票
	controler['myTicket'] = {
		verifyedListview: null,
		waitVerifyListview: null,
		render: function(){
			var self = this;
			var html = getTemplate('myTicket');
			

			appendTemplate(html);

			this.waitVerifyListview = $('#waitverifyed-list');
			this.verifyedListview = $('#verifyed-list');

			function verifyedTicket(){
				var data = self.getVerifyedTicket();
				var template = getTemplate('ticketList');
				Mustache.parse(template)
				var html = Mustache.render(template, data);

				self.verifyedListview.html(html);
				self.verifyedListview.listview();
			}

			function waitVerifyTicket(){
				var data = self.getWaitVerifyTicket();
				var template = getTemplate('ticketList');
				Mustache.parse(template)
				var html = Mustache.render(template, data);
				self.waitVerifyListview.html(html);
				self.waitVerifyListview.listview();
			}

			waitVerifyTicket();
			verifyedTicket();

			$('[data-role="tabs"]').tabs();
		},

		bindEvent: function(){

		},
		getWaitVerifyTicket: function(){
			var test = {
				audit: false,
				verifyRet: true,
				tickets : [{
						price : 600,
						avatar : '../../img/weixinimg/60.jpeg',
						name: 'aaa',
						time: 'ccc',
						status: true
					}, {
						price : 600,
						avatar : '../../img/weixinimg/60.jpeg',
						name: 'aaa',
						time: 'ccc',
						status: false
					}]
				};
			return test;
		},

		getVerifyedTicket: function(){
			var test = {
				tickets : [{
						price : 6400,
						avatar : '../../img/weixinimg/60.jpeg',
						name: 'aaa',
						time: 'ccc'
					}]
				};
			return test;
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
				$('#uploadBill button').click(function(){
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
					alert("选择上传的数量" + ids.length);
					for(var i=0; i<ids.length && isValid; i++) {						
						alert(ids[i]);
						for(var j=0; j<uploadBillList.length; j++) {
							alert(uploadBillList[j].id);
							if(uploadBillList[j].id == ids[i]) {
								alert("发票金额" + uploadBillList[j].amount);
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
						var jsonData = new Array();
						var tmp = "{\"uploadBillEntities\":" +  JSON.stringify(submitList) + "}";
						jsonData.push(tmp);
						alert(tmp);
						$.ajax({ 
					        type : "POST", 
					        url  : "/downloadUserBill?openId=" + userOpenId,  
					        cache : false,
					        data : tmp,
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
							alert("您选择上传的发票已经全部上传成功！");
						} else if(data.error_code == "1" || data.error_code == "3"){
							alert(data.error_message);
						} else {
							alert("data.error_message" + data.upload_count);
							var tmp = data.ids.split(" ");
							if(tmp != null && tmp.length != 0)
							for(var i=0; i<tmp.length; i++) {
								for(var j=0; j<uploadBillList.length; j++) {
									if(uploadBillList[j].id === tmp[i]) {
										uploadBillList.splice(j,1);
									}
								}
							}
						}
						controler['uploadBill'].init();
						box.wrap.removeClass("box-wrap-gif");
						box.content.removeClass("box-content-gif");
						box.clearLoading();
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
	controler['ticketVerify'] = {

		waitVerifyListview: null,

		render: function(){
			var self = this;
			var html = getTemplate('ticketVerify');
			appendTemplate(html);

			this.waitVerifyListview = $('#verify-list');

			function waitVerifyTicket(){
				var data = self.getWaitVerifyTicket();
				var template = getTemplate('ticketList');
				Mustache.parse(template)
				var html = Mustache.render(template, data);
				self.waitVerifyListview.html(html);
				self.waitVerifyListview.listview();
			}

			waitVerifyTicket();
		},

		bindEvent: function(){
			var self = this;
			$('#ticketVerify button').click(function(){
				var ids = [];
				self.waitVerifyListview.find('li').each(function(index, obj){
					var obj = $(obj), check = obj.find('input[type="checkbox"]');
					if(check.is(':checked')){
						ids.push(check.val());
					}
				})
				self.verify(ids);
			})
		},

		verify: function(ids){
			console.log(ids);
		},

		getWaitVerifyTicket: function(){
			var test = {
				audit: true,
				verifyRet: false,
				tickets : [{
						price : 6400,
						avatar : '../../img/weixinimg/60.jpeg',
						name: 'aaa',
						time: 'ccc'
					},
				{
						price : 6400,
						avatar : '../../img/weixinimg/60.jpeg',
						name: 'aaa',
						time: 'ccc'
					}
				,{
						price : 6400,
						avatar : '../../img/weixinimg/60.jpeg',
						name: 'aaa',
						time: 'ccc'
					},{
						price : 6400,
						avatar : '../../img/weixinimg/60.jpeg',
						name: 'aaa',
						time: 'ccc'
					}
					,{
						price : 6400,
						avatar : '../../img/weixinimg/60.jpeg',
						name: 'aaa',
						time: 'ccc'
					}]
				};
			return test;
		},

		init: function(){
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
						alert(JSON.stringify(uploadBillList[i]));
						break;
					}
				}
				controler['uploadBill'].init();
			}
		}
	}


	billEdit();
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
			appId: 'wx309df15b6ddc5371',
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
						var billData = {"id":date.getTime(), "img":res.localIds[i].toString(), "time":time, "amount": 0, "expenseTypeName":"", "expenseTypeId":null}
						uploadBillList.push(billData);
					}
				}
				box.wrap.removeClass("box-wrap-gif");
				box.content.removeClass("box-content-gif");
				box.clearLoading();
				controler['uploadBill'].init();
				
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
	
	controler['help'].init();
	setMenu('服务与帮助');
})