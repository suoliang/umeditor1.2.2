/**
 * 首页异步接口
 * @author SUOLIANG
 */
var newsDyInfo = newsDyInfo || {};
(function() {
	newsDyInfo.action = {
		saveNewsDyInfo : std.u('/news/addNewsInfo.htm'),
		queryPageList : std.u("/news/queryList.htm")
	};
	
	newsDyInfo.saveNewsDyInfo = function(params, fun) {
		assert.isFun(fun.success, "success", "news.saveNewsDyInfo");

		var index = layer.load('正在提交请求...');

		ajaxCommFun(newsDyInfo.action.saveNewsDyInfo, params, function(response) {

			layer.close(index);

			if (response.type == dict.action.suc) {
				fun.success(response.data, response.message);
			} else {
				alertMsg(response.message);
				if ($.isFunction(fun.error)) {
					fun.error(response.message, response.attrs);
				}
			}
		});
	};
	
	newsDyInfo.queryPageList = function(params, fun) {
		ajaxCommFunText(newsDyInfo.action.queryPageList, params, function(response) {
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