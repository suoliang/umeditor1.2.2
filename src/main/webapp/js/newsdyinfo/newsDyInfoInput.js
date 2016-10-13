var newsDyInfoInput = newsDyInfoInput || {};
(function(){
	
	newsDyInfoInput.initEvent = function() {
		
		$(std.findTag('saveNewsInfo')).click(function() {
			newsDyInfoInput.saveNewsInfo();
		});
		
	};
	
	newsDyInfoInput.saveNewsInfo = function() {
		var description = editor.getContent();
		$("#content").val(description);
		
		if (!newsDyInfoInput.checkForm()) {
			return;
		}
		var formData = new FormData();  
		var fileObj = document.getElementById("file").files[0]; // 获取文件对象
		var id=$("#id").val();
		/**id为空说明是添加操作,缩略图不能空*/
		if (util.isBlank(id)) {
			if (fileObj == null) {
				$.msg.warning('缩略图不能为空');
				return;
			}
		}
		formData.append("file", fileObj);    
		formData.append("id", id);    
		formData.append("title", $('#title').val());  
		formData.append("dynamicTime", $('#dynamicTime').val()); 
		formData.append("type", $('#type').val());  
		formData.append("status", $('#status').val());  
		formData.append("content", $('#content').val());  
		formData.append("keyWords", $('#keyWords').val());  
	     $.ajax({  
	          url: std.u("/news/addNewsInfo.htm") ,  
	          type: 'POST',  
	          data: formData, 
	          dataType:"json",
	          async: false,  
	          cache: false,  
	          contentType: false,  
	          processData: false,  
	          success: function (response) { 
	        	  if (response.type == dict.action.suc) {
	        		  $.msg.success(response.message);
	        		  util.go('/news/index.htm');
	        	  } else {
	        		  $.msg.error(response.message);
	        	  }
	          },  
	          error: function (response) {  
	        	  $.msg.error(response.message);
	          }  
	     });  
		
//		newsDyInfo.saveNewsDyInfo(newsDyInfoInput.options.serialize(), {
//			success : function() {
//				util.go('/news/index.htm');
//			}
//		});
		
	};
	
	newsDyInfoInput.checkForm = function() {
		if (util.isBlank($.trim($('#title').val()))) {
			$.msg.warning('标题名称不能为空');
			return false;
		}
		if (util.isBlank($.trim($('#dynamicTime').val()))) {
			$.msg.warning('时间不能为空');
			return false;
		}
		if (util.isBlank($.trim($('#content').val()))) {
			$.msg.warning('内容不能为空');
			return false;
		}
		return true;
	};
	
	/**   options  */
	newsDyInfoInput.options = newsDyInfoInput.options || {};
	(function(options) {
		/**获取选项数据*/
		options.serialize = function() {
			this.sync();
			return $('#newsContentForm').serialize();
		};
		/**同步选项数据*/
		options.sync = function() {
			$('input[name=title]').val($('#title').val());
			$('input[name=dynamicTime]').val($('#dynamicTime').val());
			$('input[name=type]').val($('#type').val());
			$('input[name=status]').val($('#status').val());
			$('input[name=thumbnailPicUrl]').val($('#thumbnailPicUrl').val());
			$('input[name=content]').val($('#content').val());
		};
		
		var dynamicDateOption;
		dynamicDateOption = {
			elem : '#dynamicTime',
			format : 'YYYY-MM-DD hh:mm:ss',
			start : laydate.now(0, 'YYYY-MM-DD hh:mm:ss'),
			isclear : true, //是否显示清空
			istoday : false, //是否显示今天
			istime: true,//是否显示时间
			issure : true, //是否显示确认
			choose : function(datas) {
				//$("input[name=dynamicTime]").val(datas);
			}
		};
		
		options.initEvent = function() {//初始化事件
			dynamicDateOption.max = laydate.now();
			laydate(dynamicDateOption);
		};
		
	})(newsDyInfoInput.options);	
	
	$(function() {

		newsDyInfoInput.initEvent();
		
		newsDyInfoInput.options.initEvent();
		
	});
	
})();