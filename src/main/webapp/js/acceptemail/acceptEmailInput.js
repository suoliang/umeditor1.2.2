var acceptEmailInput = acceptEmailInput || {};
(function(){
	
	acceptEmailInput.initEvent = function() {
		
		$(std.findTag('saveEmailInfo')).click(function() {
			acceptEmailInput.saveAcceptEmail();
		});
		
	};
	
	acceptEmailInput.saveAcceptEmail = function() {
		
		if (!acceptEmailInput.checkForm()) {
			return;
		}
		
		acceptEmail.saveAcceptEmail(acceptEmailInput.options.serialize(), {
			success : function() {
				util.go('/acceptEmail/index.htm');
			}
		});
		
	};
	
	acceptEmailInput.checkForm = function() {
		if (util.isBlank($('#email').val())) {
			$.msg.warning('邮箱不能为空');
			return false;
		}
		var reg_email =/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
		
		if (!reg_email.test($.trim($('#email').val()))) {
			$.msg.warning("邮箱格式不正确");
			return false;
		}
		return true;
	};
	
	/**   options  */
	acceptEmailInput.options = acceptEmailInput.options || {};
	(function(options) {
		/**获取选项数据*/
		options.serialize = function() {
			this.sync();
			return $('#acceptEmailForm').serialize();
		};
		/**同步选项数据*/
		options.sync = function() {
			$('input[name=email]').val($.trim($('#email').val()));
//			$('input[name=type]').val($('#type').val());
		};
		
	})(acceptEmailInput.options);	
	
	$(function() {

		acceptEmailInput.initEvent();
		
	});
	
})();