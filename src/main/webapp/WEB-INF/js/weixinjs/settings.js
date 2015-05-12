
$(function(){

	jQuery.fn.center = function (obj) {
	    this.css("position","absolute");
	    this.css("top", Math.max(0, (($(window).height() - obj.height()) / 2) + 
	                                                $(window).scrollTop()) + "px");
	    this.css("left", Math.max(0, (($(window).width() - $(this).outerWidth()) / 2) + 
	                                                $(window).scrollLeft()) + "px");
	    return this;
	}

	var companyInfo = null;

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

	$(document).on('submit', '#content form', function(event){
		var obj = $(this), formName = $(this).attr('type');
		var data = $(this).serializeArray();
		if(controler[curC]){
			controler[curC]['form'] && controler[curC]['form'][formName] && controler[curC]['form'][formName](obj, data);
		}
		event.preventDefault();
		return false;
	})

	var controler = {};

	controler['accountSet'] = {
		render: function(){
			var self = this;
			var template = getTemplate('accountSet');
			var html = Mustache.render(template, null);
			appendTemplate(html);
			$('[data-role="tabs"]').tabs();
		},
		form: {
			account: function(form, args){
				if(args[0].value == null || args[0].value == '') {
					$('.name-empty-error').show();
				}else if(args[1].value == null || args[1].value == ''){
					$('.email-empty-error').show();
				}else{
					$('.name-empty-error').hide();
					$('.email-empty-error').hide();
					box.wrap.css({width:'32px',height:'32px',background:'transparent'});
					box.content.css({width:'32px',height:'32px',background:'transparent'});
					box.loading('<html><body><img src="../../img/weixinimg/loading1.gif"></body></html>');
					setTimeout(function(){
						box.clearLoading();
						alert('设置信息成功！');
						}, 2000);
					}
			}
		},
		init: function(){
			this.render();
		}
	}

	controler['bindAccount'] = {
		render: function(){
			var self = this;
			var template = getTemplate('bindAccount');
			
			var html = Mustache.render(template, companyInfo);
			appendTemplate(html);
			$('[data-role="tabs"]').tabs();
		},
		form: {
			company: function(form, args){
				if(args[0].value == null || args[0].value == '') {
					$('.code-empty-error').show();
				}else{
					$('.code-empty-error').hide();
					box.wrap.css({width:'32px',height:'32px',background:'transparent'});
					box.content.css({width:'32px',height:'32px',background:'transparent'});
					box.loading('<html><body><img src="../../img/weixinimg/loading1.gif"></body></html>');
					setTimeout(function(){box.clearLoading();companyInfo={company:{name:'青智',branch:'IT部'}};controler['bindAccount'].init();}, 2000);
				}
				console.log(args);
			}
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
	
	controler['help'].init();
	setMenu('服务与帮助');
})