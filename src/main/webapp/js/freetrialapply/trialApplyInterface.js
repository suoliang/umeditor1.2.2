/**
 * 首页异步接口
 * @author SUOLIANG
 */
var trialApply = trialApply || {};
(function() {

	trialApply.action = {
		queryPageList : std.u("/rpt/trialApply/queryList.htm")
	};
	
	trialApply.queryPageList = function(params, fun) {
		ajaxCommFunText(trialApply.action.queryPageList, params, function(response) {
			if (response.type == dict.action.suc) {
				fun.success(response.data);
			} else {
				alertMsg(response.message);
				if ($.isFunction(fun.error)) {
					fun.error(response.message, response.attrs);
				}
			}
		});
	};

})();