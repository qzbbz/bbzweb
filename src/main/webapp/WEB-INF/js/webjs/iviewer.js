function init() {
	var viewer = $("#iviewer .viewer").width($(window).width() - 80).height(
			$(window).height()).iviewer({
		ui_disabled : true,
		zoom : 'fit',
		onFinishLoad : function(ev) {
			$("#iviewer .loader").fadeOut();
			$("#iviewer .viewer").fadeIn();
		}
	});

	$("#iviewer .zoomin").click(function(e) {
		e.preventDefault();
		viewer.iviewer('zoom_by', 1);
	});

	$("#iviewer .zoomout").click(function(e) {
		e.preventDefault();
		viewer.iviewer('zoom_by', -1);
	});
	$("#iviewer .angle").click(function(e) {
		e.preventDefault();
		viewer.iviewer('angle', 90);
	});
}

function open(src) {
	$("#iviewer").fadeIn().trigger('fadein');
	$("#iviewer .loader").show();
	$("#iviewer .viewer").hide();

	var viewer = $("#iviewer .viewer").iviewer('loadImage', src).iviewer(
			'set_zoom', 'fit');
}

function close() {
	$("#iviewer").fadeOut().trigger('fadeout');
}

function openBillImage(src) {
	open(src);
}

$("#iviewer .close").click(function(e) {
	e.preventDefault();
	close();
});

$("#iviewer").bind('fadein', function() {
	$(window).keydown(function(e) {
		if (e.which == 27)
			close();
	});
});

init();
