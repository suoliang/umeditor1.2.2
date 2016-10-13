var newsDyInfoPage = newsDyInfoPage || {};
(function(){
	
	newsDyInfoPage.initEvent = function() {
		
		$(std.findTag("btn-add-news")).click(function(){
			util.go('/news/toInputNewsInfo.htm');
		});
		
	};
	
	/**   list  */
	newsDyInfoPage.list = newsDyInfoPage.list || {};
	(function(list) {
		list.initEvent = function(){
			
			$(std.findTag("deleteNews")).click(function(){
				list.deleteNews(this);
			});
			
			$(std.findTag("updateNews")).click(function(){
				list.updateNews(this);
			});
			
		};
		
		list.deleteNews = function(entity){
			var newsId = std.oid(entity);/**id*/
			
			layer.confirm('确定要删除此条信息？', function(index) {
				layer.close(index);
				var fun = function(response) {
					if (response.type == dict.action.suc) {
						layer.msg("删除成功！", 2, 1, function() {
							newsDyInfoPage.queryNewsDyInfoList(newsDyInfoPage.paging.currentpage, newsDyInfoPage.paging.pagesize);
						});
					} else {
						layer.alert(response.message);
					}
				};
				ajaxCommFun(std.u("/news/deleteNewsInfo.htm"), "id=" + newsId, fun);
			});
		};
		
		list.updateNews = function(entity){
			var newsId = std.oid(entity);/**id*/
			util.go("/news/toInputNewsInfo.htm?id="+newsId);
		};
		
	})(newsDyInfoPage.list);
	
	newsDyInfoPage.queryNewsDyInfoList = function(pageNo, pagesize) {
		//newsDyInfoPage.currentParam = newsDyInfoPage.options.serialize();
		
		newsDyInfo.queryPageList("pageNo=" + pageNo + "&pageSize=" + pagesize, {
			success : function(html) { 
				$('#newsdyinfo-container').empty();
				$('#newsdyinfo-container').html(html);/**把controller返回的页面嵌入到页面*/
				/**加载时执行注册里的方法*/
				newsDyInfoPage.list.initEvent();
			}
		});
		
	};
	
	newsDyInfoPage.paging = newsDyInfoPage.paging || {};
	(function(paging) {
		paging.initEvent = function() {
			$('#newsdyinfo-paging').paging({
				gotoNoImpl : function(pageNo, pagesize) {
					newsDyInfoPage.queryNewsDyInfoList(pageNo, pagesize);
				}
			});
		};
		
		paging.toSetInfo = function(currentpage, pagesize, totalpage, totalsize) {//设置分页信息
			paging.currentpage = currentpage;
			paging.pagesize = pagesize;
			paging.totalpage = totalpage;
			paging.totalsize = totalsize;

			if (paging.currentpage >= paging.totalpage) {
				$("#newsdyinfo-paging").show();
			}

			$("#newsdyinfo-paging").paging("setInfo", currentpage, pagesize, totalpage, totalsize);
		};
		
	})(newsDyInfoPage.paging);
	
	$(function() {

		newsDyInfoPage.initEvent();
		
		newsDyInfoPage.list.initEvent();
		
		//paging init
		newsDyInfoPage.paging.initEvent();

	});
	
})();