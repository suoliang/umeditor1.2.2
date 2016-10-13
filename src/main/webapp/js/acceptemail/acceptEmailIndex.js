var acceptEmailPage = acceptEmailPage || {};
(function(){
	
	acceptEmailPage.initEvent = function() {
		
		$(std.findTag("btn-add-email")).click(function(){
			util.go('/acceptEmail/toInputAcceptEmail.htm');
		});
		
	};
	
	/**   list  */
	acceptEmailPage.list = acceptEmailPage.list || {};
	(function(list) {
		list.initEvent = function(){
			
			$(std.findTag("deleteAcceptEmail")).click(function(){
				list.deleteAcceptEmail(this);
			});
			
		};
		
		list.deleteAcceptEmail = function(entity){
			var acceptEmailId = std.oid(entity);/**id*/
			
			layer.confirm('确定要删除此条信息？', function(index) {
				layer.close(index);
				var fun = function(response) {
					if (response.type == dict.action.suc) {
						layer.msg("删除成功！", 2, 1, function() {
							acceptEmailPage.queryAcceptEmailList(acceptEmailPage.paging.currentpage, acceptEmailPage.paging.pagesize);
						});
					} else {
						layer.alert(response.message);
					}
				};
				ajaxCommFun(std.u("/acceptEmail/deleteAcceptEmail.htm"), "id=" + acceptEmailId, fun);
			});
		};
		
	})(acceptEmailPage.list);
	
	acceptEmailPage.queryAcceptEmailList = function(pageNo, pagesize) {
		//acceptEmailPage.currentParam = acceptEmailPage.options.serialize();
		
		acceptEmail.queryPageList("pageNo=" + pageNo + "&pageSize=" + pagesize, {
			success : function(html) { 
				$('#acceptemail-container').empty();
				$('#acceptemail-container').html(html);/**把controller返回的页面嵌入到页面*/
				/**加载时执行注册里的方法*/
				acceptEmailPage.list.initEvent();
			}
		});
		
	};
	
	acceptEmailPage.paging = acceptEmailPage.paging || {};
	(function(paging) {
		paging.initEvent = function() {
			$('#acceptemail-paging').paging({
				gotoNoImpl : function(pageNo, pagesize) {
					acceptEmailPage.queryAcceptEmailList(pageNo, pagesize);
				}
			});
		};
		
		paging.toSetInfo = function(currentpage, pagesize, totalpage, totalsize) {//设置分页信息
			paging.currentpage = currentpage;
			paging.pagesize = pagesize;
			paging.totalpage = totalpage;
			paging.totalsize = totalsize;

			if (paging.currentpage >= paging.totalpage) {
				$("#acceptemail-paging").show();
			}

			$("#acceptemail-paging").paging("setInfo", currentpage, pagesize, totalpage, totalsize);
		};
		
	})(acceptEmailPage.paging);
	
	$(function() {

		acceptEmailPage.initEvent();
		
		acceptEmailPage.list.initEvent();
		
		//paging init
		acceptEmailPage.paging.initEvent();

	});
	
})();