$(function() {
	if ($('[tag=theme][oid=transverse]').size() == 0) {
		$('.dk_sidebar').css('min-height', $(window).height() + 500);
		$(window).resize(function() {
			$('.dk_sidebar').css('min-height', $(window).height() + 500);
		});
	}

})




$(function() {
	$('#dk_sidebar .dk_menu li').bind({
		mouseover : function() {
			$(this).find('.dk_sub_menu').show();
		},
		mouseout : function() {
			$(this).find('.dk_sub_menu').hide();
		}
	});
	$('#dk_sidebar .dk_menu li ul li').bind({
		mouseover : function() {
			$(this).find('.dk_sub2_menu').show();
		},
		mouseout : function() {
			$(this).find('.dk_sub2_menu').hide();
		}
	});
//导航显示隐藏 end	
	$('#test').click(function() {
	if($('#def').size() != 0) {
		$("#def").remove();
		$("<link>").attr({ id: 'def2', rel: "stylesheet", type: "text/css", href: ctx+"/aframestyle/framework/layout.sidebar.shrinkage.css"}).appendTo("head");
	} else {
		$("#def2").remove();
		$("<link>").attr({ id: 'def', rel: "stylesheet", type: "text/css", href: ctx+"/aframestyle/framework/layout.sidebar.open.css"}).appendTo("head");
	}
    });
//侧边导航展开收起 end	
	$(document).ready(function(){	
		$(".info").hover(function(){
			$(this).find(".dk_downbox").show();
			$('.dk_user span').text('▲')
		},function(){
			$(this).find(".dk_downbox").hide();
			$('.dk_user span').text('▼')
		});
		
	});	
//头部用户管理 end

});

$(function(){	
	$('.topic-selector').click(function() {
		$.box('.topic-selector-box', {}, {
			close : {
				dom : [ '.topic-selector-box .box-close' ]
			}
		});
	});

	$('.c_alert_btn').click(function() {
		$.box('.c_alert_theme', {
			onOpen : function() {
				$('#scrollbar').tinyscrollbar();
				$('#scrollbar_002').tinyscrollbar();//里面有2个滚动条			
			}
		}, {
			close : {
				dom : [ '.close' ]
			}
		});
	});	

	$('.c_alert_btn02').click(function() {
		$.box('.c_alert_theme_02', {
			onOpen : function() {
				$('#scrollbar02').tinyscrollbar();
			}
		}, {
			close : {
				dom : [ '.close' ]
			}
		});
	});//

	$('.c_alert_btn03').click(function() {
		$.box('.c_alert_theme_03', {
			onOpen : function() {
				$('#scrollbar03').tinyscrollbar();
			}
		}, {
			close : {
				dom : [ '.close' ]
			}
		});
	}); //

	$('.c_alert_btn04').click(function() {
		$.box('.c_alert_theme_04', {
			onOpen : function() {
				$('#scrollbar04').tinyscrollbar();
			}
		}, {
			close : {
				dom : [ '.close' ]
			}
		});
	}); //
})
//弹框和弹框内的滚动条 end
$(function() {
	$.extend({
		box : function(boxSelector, options, actions) {
			var index = $.layer({
				type : 1,
				shade : [ 0.5, '#000' ],
				area : [ 'auto', 'auto' ],
				border : [ 5, 0, '#000' ],// 5, 0.3, '#000'
				title : false,
				closeBtn : false,
				page : {
					dom : boxSelector
				},
				success : function(layero) {
					if (options.onOpen && typeof options.onOpen == 'function') {
						options.onOpen(layero);
					}
				}
			});
			if (actions) {
				$.each(actions, function(prop, option) {
					$.each(option.dom, function(i, value) {
						$(value).unbind('click').click(function() {
							layer.close(index);
							if (option.fun) {
								option.fun();
							}
						});
					});
				});
			}
			return index;
		}
	});
});
//弹框的关闭按钮 end
