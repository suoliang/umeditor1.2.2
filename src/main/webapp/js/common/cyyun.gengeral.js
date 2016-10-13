$(function(){
	
/*以下下拉菜单*/
jQuery.divselect = function(divselectid, inputselectid, callback) {
	var inputselect = $(inputselectid);

	// init
	var text, selectid;
	if ($(divselectid + " ul li a[def=true]").size() != 0) {
		text = $(divselectid + " ul li a[def=true]").text();
		selectid = $(divselectid + " ul li a[def=true]").attr('selectid');

	} else if ($(divselectid + " ul li a").size() != 0) {
		text = $(divselectid + " ul li a").first().text();
		selectid = $(divselectid + " ul li a").first().attr('selectid');

	} else {
		text = "没有可选项";
		selectid = "";
	}
	$(divselectid + " div div[class=citeT]").text(text);
	inputselect.val(selectid);

	// show
	$(divselectid + " div").unbind('click').click({
		viewid : divselectid,
		inputid : inputselectid
	}, function(e) {
		var ul = $(e.data.viewid + " ul");
		if (ul.css("display") == "none") {
			ul.slideDown("fast");
		} else {
			ul.slideUp("fast");
		}
		e.stopPropagation();
	});

	// click
	$(divselectid + " ul li a").unbind('click').click({
		viewid : divselectid,
		inputid : inputselectid
	}, function(e) {
		var txt = $(this).text();
		$(e.data.viewid + " div div[class=citeT]").html(txt);
		var value = $(this).attr("selectid");
		inputselect.val(value);
		$(e.data.viewid + " ul").hide();

		if (callback) {
			callback(txt, value);
		}
		e.stopPropagation();
	});

	// hide
	$(document).unbind('click').click({
		viewid : divselectid,
		inputid : inputselectid
	}, function(e) {
		$(e.data.viewid + " ul").hide();
		e.stopPropagation();
	});
	$(divselectid).hover(function(){
	   //$(this).find("ul").show();
      },function(){
	      $(this).find("ul").hide();
      });//鼠标松开就隐藏

};
	
	
	
	
$.divselect("#divselect1", "#inputselect1", function(txt, val) {//反馈弹框
		//onclick
	});	
	
	
	
	
	
	
	
	
	
	
	})




