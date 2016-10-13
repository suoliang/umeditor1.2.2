/**
 * 首页异步接口
 * @author SUOLIANG
 */
var acceptEmail = acceptEmail || {};
(function() {
	acceptEmail.action = {
		saveAcceptEmail : std.u('/acceptEmail/addAcceptEmail.htm'),
		queryPageList : std.u("/acceptEmail/queryList.htm")
	};
	
	acceptEmail.saveAcceptEmail = function(params, fun) {
		assert.isFun(fun.success, "success", "acceptEmail.saveAcceptEmail");

		var index = layer.load('正在提交请求...');

		ajaxCommFun(acceptEmail.action.saveAcceptEmail, params, function(response) {

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
	
	acceptEmail.queryPageList = function(params, fun) {
		ajaxCommFunText(acceptEmail.action.queryPageList, params, function(response) {
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