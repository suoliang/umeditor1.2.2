var trialApplyPage = trialApplyPage || {};
(function(){
	
	trialApplyPage.currentParam = "";
	
	
	trialApplyPage.initEvent = function() {
		
		$(".queryApply").click(function(event){
			var pagesize = $("#report-apply-paging").paging('option', 'pagesize');
			trialApplyPage.queryTrialApplyList(1,pagesize);
		});
		
		//状态下拉框下拉效果
		$.divselect('#divselect1', '#inputselect1');
		$.divselect('#divselect2', '#inputselect2');
		$.divselect('#divselect3', '#inputselect3');
		
	};

	
	/**   list  */
	trialApplyPage.list = trialApplyPage.list || {};
	(function(list) {
		list.initEvent = function(){
			
			$(std.findTag("changeStatus")).click(function(){
				list.changeStatus(this);
			});
			
			$(std.findTag("deleteApply")).click(function(){
				list.deleteApply(this);
			});
			
		};
		
		list.changeStatus = function(entity){
			var applyId = std.oid(entity);/**申请试用id*/
			
			layer.confirm('此条信息已经处理好啦？', function(index) {
				layer.close(index);
				var fun = function(response) {
					if (response.type == "success") {
						layer.msg("处理成功！", 2, 1, function() {
							trialApplyPage.queryTrialApplyList(1, 10);
						});
					} else {
						layer.alert(response.message);
					}
				};
				ajaxCommFun(std.u("/rpt/trialApply/changeStatus.htm"), "id=" + applyId, fun);
			});
		};
		
		list.deleteApply = function(entity){
			var applyId = std.oid(entity);/**申请试用id*/
			
			layer.confirm('确定要删除此条信息？', function(index) {
				layer.close(index);
				var fun = function(response) {
					if (response.type == "success") {
						layer.msg("删除成功！", 2, 1, function() {
							trialApplyPage.queryTrialApplyList(1, 10);
						});
					} else {
						layer.alert(response.message);
					}
				};
				ajaxCommFun(std.u("/rpt/trialApply/delete.htm"), "id=" + applyId, fun);
			});
		};
		
	})(trialApplyPage.list);
	
	
	trialApplyPage.queryTrialApplyList = function(pageNo, pagesize) {
		
		trialApplyPage.currentParam = trialApplyPage.options.serialize();
		
		trialApply.queryPageList("pageNo=" + pageNo + "&pageSize=" + pagesize+"&"+trialApplyPage.currentParam, {
			success : function(html) { 
				$('#trialapply-container').empty();
				$('#trialapply-container').html(html);//把controller返回的页面嵌入到页面
				/**加载时执行注册里的方法*/
				trialApplyPage.list.initEvent();
			}
		});
		
	};
	
	/** options */
	trialApplyPage.options = trialApplyPage.options || {};
	(function(options) {
		options.serialize = function() {//获取选项数据
			this.sync();
			return $('#trialapply-form').serialize();
		};
		options.sync = function() {//同步选项数据
			$('#source').val($('#inputselect1').val());
			$('#status').val($('#inputselect2').val());
			$('#promotionSource').val($('#inputselect3').val());
			$('#startTime').val($('#startTimeInput').val());
			$('#endTime').val($('#endTimeInput').val());
		};		
		
		var startTime, endTime;
		startTime = {
			elem : '#startTimeInput',
			format : 'YYYY-MM-DD hh:mm:ss',
			isclear : true, // 是否显示清空
			istoday : false, // 是否显示今天
			istime: true,//是否显示时间
			issure : true, // 是否显示确认
			//max : myDate.toLocaleString(),
			choose : function(datas) {
				endTime.min = datas;
				//endTime.start = datas;/**时分秒结束框需要从23:59:59开始*/
				if (new Date(datas) > new Date($('#endTimeInput').val())) {
					$('#endTimeInput').val(datas);
				}
			}
		};
		endTime = {
			elem : '#endTimeInput',
			format : 'YYYY-MM-DD hh:mm:ss',
			start : laydate.now(new Date().getTime()) + ' 23:59:59',
			isclear : true, // 是否显示清空
			istoday : false, // 是否显示今天
			istime: true,//是否显示时间
			issure : true, // 是否显示确认
			//max : myDate.toLocaleString(),
			choose : function(datas) {
				startTime.max = datas;
			}
		};
		
		options.initEvent = function() {//初始化事件
			startTime.max = laydate.now();
			endTime.max = laydate.now();
			laydate(startTime);
			laydate(endTime);
		};
		
		
	})(trialApplyPage.options);
	
	
	trialApplyPage.paging = trialApplyPage.paging || {};
	(function(paging) {
		paging.initEvent = function() {
			$('#report-apply-paging').paging({
				gotoNoImpl : function(pageNo, pagesize) {
					trialApplyPage.queryTrialApplyList(pageNo, pagesize);
				}
			});
		};
	})(trialApplyPage.paging);
	
	$(function() {

		trialApplyPage.initEvent();
		
		trialApplyPage.list.initEvent();
		
		trialApplyPage.options.initEvent();
		
		//paging init
		trialApplyPage.paging.initEvent();

	});
	
})();