
$(function(){

	jQuery.fn.center = function (obj) {
	    this.css("position","absolute");
	    this.css("top", Math.max(0, (($(window).height() - obj.height()) / 2) + 
	                                                $(window).scrollTop()) + "px");
	    this.css("left", Math.max(0, (($(window).width() - $(this).outerWidth()) / 2) + 
	                                                $(window).scrollLeft()) + "px");
	    return this;
	}

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
	controler['ticketUpload'] = {
		render: function(){
			var self = this;
			var html = getTemplate('ticketUpload');
			appendTemplate(html);

			this.waitVerifyListview = $('#verify-list');

			function waitVerifyTicket(){
				var data = self.getTickets();
				var template = getTemplate('ticketList');
				Mustache.parse(template)
				var html = Mustache.render(template, data);
				self.waitVerifyListview.html(html);
				self.waitVerifyListview.listview();
			}

			waitVerifyTicket();
		},

		refresh: function(){
			alert(1);
		},
		getTickets: function(){
			var test = {
				upload: true,
				tickets : [{
						price : 6400,
						avatar : '../../img/weixinimg/60.jpeg',
						name: 'aaa',
						time: 'ccc'
					},{
						price : 6400,
						avatar : '../../img/weixinimg/60.jpeg',
						name: 'aaa',
						time: 'ccc'
					},{
						price : 6400,
						avatar : '../../img/weixinimg/60.jpeg',
						name: 'aaa',
						time: 'ccc'
					},{
						price : 6400,
						avatar : '../../img/weixinimg/60.jpeg',
						name: 'aaa',
						time: 'ccc'
					},{
						price : 6400,
						avatar : '../../img/weixinimg/60.jpeg',
						name: 'aaa',
						time: 'ccc'
					},{
						price : 6400,
						avatar : '../../img/weixinimg/60.jpeg',
						name: 'aaa',
						time: 'ccc'
					},{
						price : 6400,
						avatar : '../../img/weixinimg/60.jpeg',
						name: 'aaa',
						time: 'ccc'
					},{
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
	function ticketDetail(){
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

	function getExpenseType() {
		var expense_type = [{id:1,name:'交通费'},{id:2,name:'住宿费'},{id:3,name:'电话费'},{id:4,name:'餐饮费'}]
		return expense_type;
	}

	function setSelectOptions(obj) {
		var types = getExpenseType();
		for(var i=0; i<types.length; i++) {
			var value = types[i].id;
			var text = types[i].name;
			obj.append("<option value='"+value+"'>"+text+"</option>");	
		}
	}

	function ticketEdit(){

		$(document).on("click", ".ticket-list-upload li", function(event) {
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
				if(price == ""){
					$('.price-empty-error').show();
				}else{
					setNewPrice(price);
					box.hide();
				}
			})

			// 编辑之后的价格在这里
			function setNewPrice(price){
				console.log(price);
			}
		}
	}


	ticketEdit();
	ticketDetail();

	controler['help'].init();
	setMenu('服务与帮助');
})