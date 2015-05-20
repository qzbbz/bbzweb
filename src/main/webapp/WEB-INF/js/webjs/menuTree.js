(function(window, document) {
	var menuTree = function(data,el) {
		var self = this,
			menu = $('#'+el),
			ul = document.createElement("ul");
		menu.append(ul).addClass("bui-tree-menu bui-simple-list bui-side-menu");

		var arr=[];

		$.each(data.nodes,function(i,v){
			// console.log(v)
			console.log(v.children.length);
			arr.push('<li data-id="'+v.id+'" class="menu-leaf menu-second bui-menu-item '+isExpanded(v.expanded)+'">');
			arr.push('<div class="bui-menu-title">');
			arr.push(isParent(v.children.length) ? '<s></s>':' ');
			arr.push('<span class="bui-menu-title-text">');
			arr.push('<a href="'+v.href+'">'+v.text+'</a>');
			arr.push('</span></div></li>');
			if(isParent(v.children.length)){
				if(v.expanded)
					arr.push('<ul class="child-list" style="display:none">')
				else
					arr.push('<ul class="child-list">');

				$.each(v.children,function(j,v){
					arr.push('<li class="menu-leaf bui-menu-item">')
					arr.push('<a href="'+v.href+'">'+v.text+'</a></li>');
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
		$(".bui-tree-menu li").click(function(){			
			$(".bui-tree-menu li").removeClass("bui-menu-item-selected");
			$(this).addClass("bui-menu-item-selected");
				// .siblings().removeClass("bui-menu-item-selected");
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

	function isExpanded(exp){
		return exp ? ' ':'bui-menu-item-expanded';
	}
	function isParent(len){
		if(len > 0)
			return true;
		else
			return false;
	}
})(window, document)