(function(window, document) {
	var menuTree = function(data,el) {
		var self = this,
			menu = $('#'+el),
			ul = document.createElement("ul");
		menu.append(ul).addClass("bui-tree-menu bui-simple-list bui-side-menu");

		var arr=[];

		$.each(data.nodes,function(i,v){
			// console.log(v)
			// console.log(v.children.length);
			arr.push('<li class="menu-leaf menu-second bui-menu-item '+isExpanded(v.expanded)+'">');
			arr.push('<div class="bui-menu-title">');
			arr.push(isParent(v.children.length) ? '<s></s>':' ');
			arr.push('<span class="bui-menu-title-text">');
			arr.push('<a href="'+v.href+'" id="'+v.id+'">'+v.text+'</a>');
			arr.push('</span></div></li>');
			if(isParent(v.children.length)){
				if(v.expanded)
					arr.push('<ul class="child-list">');
				else
					arr.push('<ul class="child-list" style="display:none">')
				$.each(v.children,function(j,v){
					arr.push('<li class="menu-leaf bui-menu-item">')
					arr.push('<a href="'+v.href+'" id="'+v.id+'">'+v.text+'</a></li>');
				});
				arr.push('</ul>')
			}
		})		
		$(arr.join("")).appendTo(ul);

		$.each($(".menu-second"),function(){
			var that = $(this);
			if(that.find('s').length>0){
				that.addClass('js-parent');
			}
		})

		$('.js-parent').click(function(){
			var that = $(this),
				dis = that.next().css('display');
			
			if(dis=="none"){
				that.addClass('bui-menu-item-expanded');
				that.next().css('display','block');
			}else{
				that.removeClass('bui-menu-item-expanded');
				that.next().css('display','none')
			}
		})

		// bui-menu-item-expanded
	}
	window.menuTree = menuTree;

	BUI.use('bui/cookie', function(Cookie){
      $(".bui-tree-menu li a").click(function(){	
			Cookie.set("menuId",$(this).attr("id"));
      })
      // Cookie.remove("menuId")
      if(Cookie.get("menuId")){
      	$("#"+Cookie.get("menuId")).parent().addClass("bui-menu-item-selected")
      } 
    })
	function isExpanded(exp){
		return exp ? 'bui-menu-item-expanded':' ';
	}
	function isParent(len){
		if(len > 0)
			return true;
		else
			return false;
	}
})(window, document)
// // 本地存储
// (function() {
//     var Storage = {
//         MENUID: 'MENUID',
//         get: function(key, isSession) {
//             if (!this.isLocalStorage()) {
//                 return;
//             }
//             var value = this.getStorage(isSession).getItem(key);
//             if (value) {
//                 return JSON.parse(value);
//             } else {
//                 return undefined;
//             }
//         },
//         set: function(key, value, isSession) {
//             if (!this.isLocalStorage()) {
//                 return;
//             }

//             value = JSON.stringify(value);
//             this.getStorage(isSession).setItem(key, value);
//         },
//         remove: function(key, isSession) {
//             if (!this.isLocalStorage()) {
//                 return;
//             }
//             this.getStorage(isSession).removeItem(key);
//         },
//         getStorage: function(isSession) {
//             return isSession ? sessionStorage : localStorage;
//         },
//         isLocalStorage: function() {
//             try {
//                 if (!window.localStorage) {
//                     log('不支持本地存储');
//                     return false;
//                 }
//                 return true;
//             } catch (e) {
//                 log('本地存储已关闭');
//                 return false;
//             }
//         }
//     };

//     window.Storage = Storage;

//     function log(msg){
//     	console.log(msg);
//     }
// })();