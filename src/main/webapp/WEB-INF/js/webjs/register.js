/*-------------------------------------------------------------------------
 * IPRESSUM - Custom jQuery Scripts
 * ------------------------------------------------------------------------

	1.	Plugins Init
	2.	Site Specific Functions
	3.	Shortcodes
	4.      Other Need Scripts (Plugins config, themes and etc)
	
-------------------------------------------------------------------------*/

function centerModals() {
	$('.modal').each(
			function(i) {
				var $clone = $(this).clone().css('display', 'block').appendTo(
						'body');
				var top = Math.round(($clone.height() - $clone.find(
						'.modal-content').height()) / 2);
				top = top > 0 ? top : 0;
				$clone.remove();
				$(this).find('.modal-content').css("margin-top", top);
			});
}
$('.modal').on('show.bs.modal', centerModals);
$(window).on('resize', centerModals);

jQuery(document).ready(function($) {

	$('#step1-tip1').mouseover(function() {
		$(this).tooltip('show');
	});

	$('#step1-tip2').mouseover(function() {
		$(this).tooltip('show');
	})

	$('#step2-toip').mouseover(function() {
		$(this).tooltip('show');
	})

	initSelectPlan();
	registerSelectPlanEvents();

	/*------------------------------------------------------------------------*/
	/*
	 * 1. Plugins Init
	 * /*------------------------------------------------------------------------
	 */

	/*-----------FLEXSLIDER INIT-------------*/

	function initFlexSlider() {

		$('.introduction-slider').flexslider({
			animation : "fade",
			controlNav : false,
			directionNav : false,
			start : function(slider) {
				$('body').removeClass('loading');
			}
		});

		// Set a custom flexslider previous control
		$('.slide-prev').on('click', function() {
			$('.introduction-slider').flexslider('prev')
			return false;
		});

		// Set a custom flexslider next control
		$('.slide-next').on('click', function() {
			$('.introduction-slider').flexslider('next')
			return false;
		});

		// Testimonials Slide
		$('.testimonials-slider').flexslider({
			animation : "fade",
			controlNav : true,
			directionNav : false,
			start : function(slider) {
				$('body').removeClass('loading');
			}
		});

	}

	// initFlexSlider();

	/*-----------SCROLLTO INIT-------------*/

	/*-----------PARALLAX INIT-------------*/
	function initParallax() {
		$('#counter').parallax("100%", 0.3);
		$('#partners').parallax("100%", 0.3);
		$('#testimonials').parallax("100%", 0.1);

	}
	initParallax();

	/*-----------NICESCROLL INIT-------------*/

	function niceScrollInit() {
		$("html").niceScroll({
			autohidemode : false,
			cursorcolor : '#e04d47',
			zindex : 9999,
			cursorwidth : 6,
			cursorborder : "0px solid #e04d47",
			background : "#000000",
			scrollspeed : 60,
			mousescrollstep : 40
		});
	}

	// niceScrollInit();

	/*-----------SUPERFISH INIT-------------*/

	function toolTipInit() {

		$('.partner-logo img').tooltip({
			placement : 'bottom'
		});
	}

	toolTipInit();

	/*-----------ISOTOPE INIT-------------*/

	function isotopeInit() {
		var $container = $('#folio-container');
		// init
		$container.isotope({
			// options
			itemSelector : '.folio-item',
			animationOptions : {
				duration : 750,
				easing : 'linear',
				queue : false
			},
		});

		// filter items when filter link is clicked
		jQuery('#filters a').click(function() {
			var selector = $(this).attr('data-filter');
			$container.isotope({
				filter : selector
			});
			return false;
		});

		jQuery('#filters li a').click(function() {

			jQuery('#filters li').removeClass('current');
			jQuery(this).parent().addClass('current');
		});
	}

	isotopeInit();

	// Portfolio window
	/*
	 * jQuery('.folio-desc a').click(function() { var target_portfolio =
	 * jQuery(this).attr('href'); $.ajax({ url: target_portfolio, success:
	 * function(data) { jQuery("#folio-content").html(data);
	 * jQuery('#load-folio').fadeIn(400); var top_window =
	 * jQuery(document).scrollTop() + 20;
	 * jQuery('#folio-content').css('top',top_window);
	 * jQuery('#folio-content').css('display', 'block'); } }); return false; });
	 * 
	 * jQuery('.portfolio-close').live('click', function() {
	 * jQuery('#window').html(''); jQuery('#bg-fade').css('display','none'); });
	 */

	/** *************** Magnific Popup ******************** */

	function initMagnificPopup() {

		$('.folio-zoom').magnificPopup({
			type : 'image',
			closeOnContentClick : true,
			closeBtnInside : false,
			fixedContentPos : true,
			mainClass : 'mfp-no-margins mfp-with-zoom', // class to remove
			// default margin from
			// left and right side
			gallery : {
				enabled : true
			},
			image : {
				verticalFit : true
			},
			zoom : {
				enabled : false,
				duration : 300
			// don't foget to change the duration also in CSS
			}

		});
	}

	initMagnificPopup();

	/*------------------------------------------------------------------------*/
	/*
	 * 2. Site Specific Functions
	 * /*------------------------------------------------------------------------
	 */

	// $("#header").sticky({ topSpacing: 0 });
	// var main_menu = jQuery('#header');
	/*
	 * jQuery(window).scroll(function() { if(jQuery('.is-sticky').length > 0) {
	 * main_menu.css('height', '80px'); } else { main_menu.css('height',
	 * '130px'); } });
	 */

	/*-----------SETUP ANIMATIONS-------------*/

	$('.animated-item').each(function() {
		var timeDelay = $(this).attr('data-delay');
		$(this).appear(function() {
			var $that = $(this);
			setTimeout(function() {
				$that.addClass('animated');
			}, timeDelay);
		}, {
			accX : 0,
			accY : -150
		});

	});

	/*-----------COUNTDOWN FOR OUR TEAM-------------*/

	jQuery('.counter-item').appear(function() {
		jQuery('.counter-number').countTo();
		jQuery(this).addClass('funcionando');
		console.log('funcionando');
	});

	/*-----------PAGE PRELOADER-------------*/

	$(window).load(function() {
		$("#page-preloader").fadeOut(300, function() {
			$(this).remove();
		});

	});

});

function hidenAllRange() {
	document.getElementById('range1').style.display = 'none';
	document.getElementById('range2').style.display = 'none';
	document.getElementById('range3').style.display = 'none';
	document.getElementById('range4').style.display = 'none';
}

function showAllRange() {
	document.getElementById('range1').style.display = 'block';
	document.getElementById('range2').style.display = 'block';
	document.getElementById('range3').style.display = 'block';
	document.getElementById('range4').style.display = 'block';
}

function hidenPlan() {
	for (var i = 0; i < arguments.length; i++) {
		document.getElementById(arguments[i]).style.display = 'none';
	}
}

function hidenPlanInfo() {
	for (var i = 0; i < arguments.length; i++) {
		document.getElementById(arguments[i]).style.display = 'none';
	}
}

function showPlanInfo() {
	for (var i = 0; i < arguments.length; i++) {
		document.getElementById(arguments[i]).style.display = 'block';
	}
}

function showPlan() {
	for (var i = 0; i < arguments.length; i++) {
		document.getElementById(arguments[i]).style.display = 'inline-block';
	}
}

function removeOpenClass() {
	for (var i = 0; i < arguments.length; i++) {
		$('#' + arguments[i] + '').removeClass('open');
	}
}

function exchangeHeader() {
	document.getElementById('selectPlan1').style.display = 'none';
	document.getElementById('selectPlan2').style.display = 'block';
	document.getElementById('selectPlan3').style.display = 'block';
	document.getElementById('selectPlan4').style.display = 'block';
}

function checkRegInput() {
	var success = false;
	var calledTime = $("#userCalledTime  option:selected").text();
	var userPhone = $('#userPhone').val();
	var userName = $('#userName').val();
	var companyName = $('#userCompanyName').val();
	if (userName == null || userName == "" || userPhone == null
			|| userPhone == "" || !isTelephone(userPhone) || calledTime == null
			|| calledTime == "") {
		$('#step2-toip').unbind('mouseover');
		$('#step2-toip').mouseover(function() {
			$(this).tooltip('show');
		})
		$('#step2_btn_next').addClass('btn-disabled');
	} else {
		$('#step2-toip').unbind('mouseover');
		$('#step2-toip').mouseover(function() {
			$(this).tooltip('hide');
		})
		$('#step2_btn_next').removeClass('btn-disabled');
		success = true;
	}
	return success;
}

function checkCompanyIncomesInput() {
	var success = false;
	var companyIncomes = $('#userCompanyIncomes').val();
	if (companyIncomes == null || companyIncomes == '') {
		$('#step3_btn_next').addClass('btn-disabled');
		$('#step3-toip').mouseover(function() {
			$(this).tooltip('show');
		})
	} else {
		$('#step3-toip').unbind('mouseover');
		$('#step3-toip').mouseover(function() {
			$(this).tooltip('hide');
		})
		$('#step3_btn_next').removeClass('btn-disabled');
		success = true;
	}
	return success;
}

function initSelectPlan() {
	document.getElementById('selectPlan1').style.display = 'block';
	document.getElementById('selectPlan2').style.display = 'none';
	document.getElementById('selectPlan3').style.display = 'none';
	document.getElementById('selectPlan4').style.display = 'none';
	removeOpenClass("plan1", "plan2", "plan3", "plan4");
	showPlan("plan1", "plan2", "plan3", "plan4");
	hidenPlanInfo("plan1-info", "plan2-info", "plan3-info", "plan4-info");
	showAllRange();
	$('#step3-toip').unbind('mouseover');
	$('#step3-toip').mouseover(function() {
		$(this).tooltip('show');
	})
	$('#step2-toip').mouseover(function() {
		$(this).tooltip('show');
	})
	$('#step3_btn_next').addClass('btn-disabled');
	$('#step2_btn_next').addClass('btn-disabled');
	var addListener = window.addEventListener ? function(el, type, fn) {
		el.addEventListener(type, fn, false);
	} : function(el, type, fn) {
		el.attachEvent('on' + type, fn);
	};
	$('#userName').on('input', function() {
		checkRegInput();
	});
	$('#userPhone').on('input', function() {
		checkRegInput();
	});
	$('#userPhone').on('keyup', function() {
		checkRegInput();
	});
	$('#userCompanyName').on('input', function() {
		checkRegInput();
	});
}

function registerSelectPlanEvents() {
	var addListener = window.addEventListener ? function(el, type, fn) {
		el.addEventListener(type, fn, false);
	} : function(el, type, fn) {
		el.attachEvent('on' + type, fn);
	};
	var plan1 = document.getElementById('plan1');
	var plan2 = document.getElementById('plan2');
	var plan3 = document.getElementById('plan3');
	var plan4 = document.getElementById('plan4');
	plan1.onclick = function() {
		exchangeHeader();
		hidenPlan("plan2", "plan3", "plan4");
		document.getElementById('range1').style.display = 'none';
		$('#plan1').addClass('open');
		document.getElementById('plan1-info').style.display = 'block';
		$('#step3-toip').mouseover(function() {
			$(this).tooltip('hide');
		})
		$('#step3_btn_next').removeClass('btn-disabled');
		$('#userCompanyIncomes').val('0-10K');
		document.getElementById('plan1-info-step4').style.display = 'block';
		document.getElementById('plan2-info-step4').style.display = 'none';
		document.getElementById('plan3-info-step4').style.display = 'none';
		document.getElementById('plan4-info-step4').style.display = 'none';
	};
	plan2.onclick = function() {
		exchangeHeader();
		hidenPlan("plan1", "plan3", "plan4");
		document.getElementById('range2').style.display = 'none';
		$('#plan2').addClass('open');
		document.getElementById('plan2-info').style.display = 'block';
		$('#step3-toip').mouseover(function() {
			$(this).tooltip('hide');
		})
		$('#step3_btn_next').removeClass('btn-disabled');
		$('#userCompanyIncomes').val('10K-25K');
		document.getElementById('plan1-info-step4').style.display = 'none';
		document.getElementById('plan2-info-step4').style.display = 'block';
		document.getElementById('plan3-info-step4').style.display = 'none';
		document.getElementById('plan4-info-step4').style.display = 'none';
	};
	plan3.onclick = function() {
		exchangeHeader();
		hidenPlan("plan1", "plan2", "plan4");
		document.getElementById('range3').style.display = 'none';
		$('#plan3').addClass('open');
		document.getElementById('plan3-info').style.display = 'block';
		$('#step3-toip').mouseover(function() {
			$(this).tooltip('hide');
		})
		$('#step3_btn_next').removeClass('btn-disabled');
		$('#userCompanyIncomes').val('25K-45K');
		document.getElementById('plan1-info-step4').style.display = 'none';
		document.getElementById('plan2-info-step4').style.display = 'none';
		document.getElementById('plan3-info-step4').style.display = 'block';
		document.getElementById('plan4-info-step4').style.display = 'none';
	};
	plan4.onclick = function() {
		exchangeHeader();
		hidenPlan("plan1", "plan2", "plan3");
		document.getElementById('range4').style.display = 'none';
		$('#plan4').addClass('open');
		document.getElementById('plan4-info').style.display = 'block';
		$('#step3-toip').mouseover(function() {
			$(this).tooltip('hide');
		})
		$('#step3_btn_next').removeClass('btn-disabled');
		$('#userCompanyIncomes').val('>45K');
		document.getElementById('plan1-info-step4').style.display = 'none';
		document.getElementById('plan2-info-step4').style.display = 'none';
		document.getElementById('plan3-info-step4').style.display = 'none';
		document.getElementById('plan4-info-step4').style.display = 'block';
	};
}

function showHiden(showId, hiddenId) {
	$('#company_detail_reg_msg').hide();
	var checkRet = checkRegInput();
	if (showId == "step3") {
		initSelectPlan();
		$('#userCompanyIncomes').val('');
	}
	document.getElementById(hiddenId).style.display = 'none';
	document.getElementById(showId).style.display = 'block';
}

function finishRegister() {
	$('#step2RegisterForm').submit();
}

function isEmail(email) {
	var result = email
			.match(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/);
	if (result == null)
		return false;
	return true;
}

function isTelephone(phoneNumber) {
	var phoneStr = phoneNumber.toString();
	if(phoneStr.indexOf("170") == 0) return true;
	var isPhone = /^([0-9]{3,4}-)?[0-9]{7,8}$/;
	var isMob = /^((\+?86)|(\(\+86\)))?(13[012356789][0-9]{8}|15[012356789][0-9]{8}|18[012356789][0-9]{8}|147[0-9]{8}|1349[0-9]{7})$/;
	/* if(isMob.test(phoneNumber)||isPhone.test(phoneNumber)){ */
	if (isMob.test(phoneNumber) || isPhone.test(phoneNumber)) {
		return true;
	} else {
		return false;
	}
}