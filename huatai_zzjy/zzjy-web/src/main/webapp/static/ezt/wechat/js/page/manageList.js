define(function(){
	var panel={
		pageNumber:5,//每页显示5条
		init:function(){
			this.getTotalNumber(function(){
				$('.ui-load-mode').remove();
				$('.ui-mode-panel').hide().css('visibility','visible').fadeIn();
				$('.ui-tab-btn').unbind('tap').bind('tap',panel.tabContent).eq(window.GlOBAL.TOOL.deParam().type==undefined?0:window.GlOBAL.TOOL.deParam().type).trigger('tap');//tab切换
				$(window).unbind('scroll').bind('scroll',panel.scrollGetContainer);//滚动加载更多数据
			});
		},
		getTotalNumber:function(callback){
			$.ajax({
				url:'getTotalNumber.do',
				type:"post",
				dataType:"json",
				success:function(data){
					
				},
				error: function(data){
					//error
					/*
					$.Bstatus({
						html:'数据加载失败,请重试',
						status:false,
						time:500
					});	*/
					
					//true 模拟数据如下
					var data={
						param:{
							receptNumber:Math.ceil(Math.random()*1000),  //待接收总量
							suggestNumber:Math.ceil(Math.random()*1000)	//待反馈总量
						}
					};
					if(data.param!=undefined){
						$('.ui-recept-number').html(data.param.receptNumber);
						$('.ui-suggest-number').html(data.param.suggestNumber);
						callback();
					}else{
						$.Bstatus({
							html:'数据问题！',
							status:false,
							time:500
						});
					};
				}
			});
		},
		tabContent:function(){//tab切换
			var $target=$(this),
				index=$target.index(),
				$box=$('.ui-fk-content').eq(index),
				page=$box.attr('data-page')==undefined?1:parseInt($box.attr('data-page'));
			$target.addClass('act').siblings().removeClass('act');
			$box.show().siblings().hide();
			if($box.children().length<=0){
				panel.getPanelData($box,{page:page,type:index});	
			};
		},
		scrollGetContainer:function(){//滚动加载更多数据
			var index=$('.ui-tab-btn.act').index(),
				$box=$('.ui-fk-content').eq(index),
				page=$box.attr('data-page')==undefined?1:parseInt($box.attr('data-page'));
			if($box.length>0){
				var t=$(window).scrollTop(),
					T=$box.offset().top+$box.height(),
					H=$('body').height(),
					h=$(window).height();
				if(($box.data('scroll')==undefined)&&t>=T-h-30){
					panel.getPanelData($box,{page:page,type:index});
				};
			};
			return false;		
		},
		getPanelData:function(o,param,callback){//获取页面数据
			$.ajax({
				url:'getPage.do',
				data:param,
				type:"post",
				dataType:"json",
				beforeSend:function(){
					o.data('scroll',true);
					$.Bstatus({html:'加载中...'});
				},
				success:function(data){
					
				},
				error: function(data){
					//error
					/*$('.ui-status-container').remove();
					$.Bstatus({
						html:'数据加载失败,请重试',
						status:false,
						time:500,
						callback:function(){
							o.removeData('scroll');	
						}
					});	*/
					
					//true 模拟数据如下
					var data={
						param:{
							totalPage:11,//一共多少条数据	
							rows:[
								{contactName:'hello',contactTell:18616897921,contactAddress:'上海市宝山区富锦路2359号3号库',type:param.type,status:true,uid:'SWL1507092702',number:Math.ceil(Math.random()*1000),time:window.GlOBAL.TOOL.deParam().time=='out'?new Date().getTime()-346573454:new Date().getTime()+346573454,name:'上海宝山宝钢贸易有限公司',wearhouse:'上海宝钢物流有限公司三冠库一号库',tig:'仓单注监管核查'},
								{contactName:'hello',contactTell:18616897921,contactAddress:'上海市宝山区富锦路2359号3号库',type:param.type,status:false,uid:'SWL1507092773',number:Math.ceil(Math.random()*1000),time:window.GlOBAL.TOOL.deParam().time=='out'?new Date().getTime()-345345442:new Date().getTime()-34534544,name:'上海宝山宝钢贸易有限公司',wearhouse:'上海宝钢物流有限公司三冠库一号库',tig:'仓单注监管核查'},
								{contactName:'hello',contactTell:18616897921,contactAddress:'上海市宝山区富锦路2359号3号库',type:param.type,status:true,uid:'SWL1507092704',number:Math.ceil(Math.random()*1000),time:window.GlOBAL.TOOL.deParam().time=='out'?new Date().getTime()-5675732434:new Date().getTime()+5675732434,name:'上海宝山宝钢贸易有限公司',wearhouse:'上海宝钢物流有限公司三冠库一号库',tig:'仓单注监管核查'},
								{contactName:'hello',contactTell:18616897921,contactAddress:'上海市宝山区富锦路2359号3号库',type:param.type,status:true,uid:'SWL1507092775',number:Math.ceil(Math.random()*1000),time:window.GlOBAL.TOOL.deParam().time=='out'?new Date().getTime()-546565633:new Date().getTime()+546565633,name:'上海宝山宝钢贸易有限公司',wearhouse:'上海宝钢物流有限公司三冠库一号库',tig:'仓单注监管核查'},
								{contactName:'hello',contactTell:18616897921,contactAddress:'上海市宝山区富锦路2359号3号库',type:param.type,status:false,uid:'SWL1507092076',number:Math.ceil(Math.random()*1000),time:window.GlOBAL.TOOL.deParam().time=='out'?new Date().getTime()-5645343354:new Date().getTime()-5645343354,name:'上海宝山宝钢贸易有限公司',wearhouse:'上海宝钢物流有限公司三冠库一号库',tig:'仓单注监管核查'}
							]
						}
					};
					setTimeout(function(){
						if(data.param!=undefined){
							if(param.page<=Math.ceil(data.param.totalPage/panel.pageNumber)){
								var D=data.param.rows;
								if(D.length>0){
									var _get=function(d,m){
										var _list='';
										for(var i=0;i<d.length;i++){
											var B={
												0:{name:'待接收',className:'green',stp:'<a class="ui-fk-list-head ui-recept-button" href="javascript:;">',str:'<a class="ui-fk-list-btn1 ui-recept-button" href="javascript:;">立即接受</a><a class="ui-fk-list-btn2 ui-return-button" href="javascript:;">退回</a>'},
												1:{name:'待反馈',className:'yellow',stp:'<a class="ui-fk-list-head" href="'+PATH+'gongdanfankui-xq.html?id='+D[i].uid+'&type=1">',str:'<a class="ui-fk-list-btn1" href="'+PATH+'gongdanfankui-xq.html?id='+D[i].uid+'&type=1">去反馈</a>'},
												2:{name:'已完成',className:'grey',stp:'<a class="ui-fk-list-head" href="'+PATH+'gongdanfankui-xq.html?id='+D[i].uid+'&type=2">',str:'<a class="ui-fk-list-btn3" href="'+PATH+'gongdanfankui-xq.html?id='+D[i].uid+'&type=2">去查看</a>'}
											}[param.type],
											C={
												0:'<span class="ui-fk-time" data-time="'+d[i].time+'"><i class="fa ui-fk-ico">&#xe922</i></span>',
												1:'<span class="ui-fk-time" data-time="'+d[i].time+'"><i class="fa ui-fk-ico">&#xe922</i></span>',
												2:d[i].status?'<span class="ui-fk-time green"><i class="fa">&#xf058</i> 结果正常</span>':'<span class="ui-fk-time red"><i class="fa">&#xed4d</i> 结果异常</span>',
											}[param.type];
											_list+='<div class="ui-fk-list-box" data-id="'+D[i].uid+'">\
												'+B.stp+'\
													<div class="ui-fk-tips ui-fk-tips-'+B.className+'">'+B.name+'</div>\
													<em class="no">'+D[i].uid+'</em>'+C+'\
												</a>\
												<div class="ui-fk-list-main">\
													<p><i class="fa">&#xf007</i>'+D[i].name+'</p>\
													<p class="cursor-pointer ui-show-contact" data-param=\'{"contactHouse":"'+d[i].wearhouse+'","contactName":"'+d[i].contactName+'","contactTell":"'+d[i].contactTell+'","contactAddress":"'+d[i].contactAddress+'"}\'><i class="fa">&#xea47</i>'+D[i].wearhouse+'<em class="fa">&#xecd6;</em></p>\
													<p><i class="fa">&#xf009</i>'+D[i].tig+'</p>\
												</div>\
												<div class="ui-fk-list-foot">\
													<div class="ui-fk-list-num">数量：<span>×'+D[i].number+'</span></div>'+B.str+'\
												</div>\
											</div>'
										};	
										return _list;
									};
									o.removeData('scroll').attr('data-page',param.page+1).append(_get(D));
									if($('.ui-fk-time',o).length>0){
										$('.ui-fk-time',o).each(function(){
											var _this=$(this),time=_this.attr('data-time');
											if(_this.children('.ui-fk-ico').length>0){
												panel.getRemainTime(_this,parseInt(time));	
											};
										});
									};
									$('.ui-status-container').remove();
									$('.ui-recept-button').unbind('tap').bind('tap',panel.receptPanelContainer);//立即接受
									$('.ui-return-button').unbind('tap').bind('tap',panel.returnPanelContainer);//退回
									$('.ui-show-contact').unbind('tap').bind('tap',panel.showContactContainer);//查看联系人
								}else{
									$('.ui-status-container').remove();
									$.Bstatus({
										html:'无数据',
										status:false,
										time:500,
										callback:function(){
											o.removeData('scroll');	
										}
									});
								};
							}else{
								$('.ui-status-container').remove();
								$.Bstatus({
									html:'已经全部加载完了！',
									status:false,
									time:500
								});
							};
						}else{
							$('.ui-status-container').remove();
							$.Bstatus({
								html:'数据问题！',
								status:false,
								time:500,
								callback:function(){
									o.removeData('scroll');	
								}
							});
						};
					},300);
				}	
			});	
			return false;		
		},
		showContactContainer:function(){//查看联系人
			var $target=$(this),param=JSON.parse($target.attr('data-param'));
			$.Bchange({
				html:'<div class="ui-contact-container">\
					<ul>\
						<li>\
							<a href="javascript:;">\
								<p class="t1">'+param.contactHouse+'</p>\
								<p class="t2">仓库名称</p>\
							</a>\
						</li>\
						<li>\
							<a href="javascript:;">\
								<p class="t1">'+param.contactName+'</p>\
								<p class="t2">联系人</p>\
							</a>\
						</li>\
						<li><a href="tel:'+param.contactTell+'" class="btn"><em class="left">'+window.GlOBAL.TOOL.getFormatTellphone(param.contactTell)+'</em><span class="right"><i class="fa">&#xea83</i></span></a></li>\
						<li><a href="javascript:;">'+param.contactAddress+'</a></li>\
					</ul>\
				</div>',
				cancelName:null
			});
			return false;
		},
		receptPanelContainer:function(){//立即接受
			var $target=$(this),
				$box=$target.parents('.ui-fk-list-box'),
				id=$box.attr('data-id');
			$.Bconfirm({
				html:'您确定要接受该工单吗',
				button:['确定','取消'],
				param:id,
				url:'recept.do', 
				sureCallback:function(o,r,data){
					r();
					$.Bstatus({
						html:'接受成功',
						time:500,
						status:true,
						callback:function(){
							window.location=PATH+'gongdanfankui-xq.html?id='+id+'f&type=1';
						}
					});	
				}
			});
			return false;
		},
		returnPanelContainer:function(){//退回
			var $target=$(this),
				$box=$target.parents('.ui-fk-list-box'),
				id=$box.attr('data-id');
			$.Bconfirm({
				html:'您确定要退回该工单吗',
				button:['确定','取消'],
				param:id,
				url:'return.do', 
				sureCallback:function(o,r){
					r();
					$.Bstatus({
						html:'退回成功',
						time:500,
						status:true,
						callback:function(){
							$box.fadeOut(function(){
								$(this).remove();	
							});
						}
					});	
				}
			});
			return false;
		},
		getRemainTime:function(o,date){//倒计时
			var plus_time=new Date(date).getTime()-new Date().getTime();
			setInterval(function(){
				plus_time = plus_time - 1000
				plus_time=plus_time<0&&plus_time>-1000?-1000:plus_time;
				var plus=Math.abs(plus_time)*0.001,
					second = Math.floor(plus % 60),            // 计算秒     
					minite = Math.floor((plus / 60) % 60),      //计算分 
					hour = Math.floor((plus / 3600) % 24),      //计算小时 
					day = Math.floor((plus / 3600) / 24);        //计算天 
				day=day==0?'':day+'天';	
				hour=hour==0?'':panel.setFormate(hour)+'时';
				minite=minite==0?'':panel.setFormate(minite)+'分';
				//second=day==''?second==0?'00秒':panel.setFormate(second)+'秒':'';
				second=second==0?'00秒':panel.setFormate(second)+'秒';
				plus_time<0?o.addClass('red'):'';
				o.html('<i class="fa">&#xe721</i> '+day+hour+minite+second);	
			},1000);
		},
		setFormate:function(s){//格式化时间
			return s<10?'0'+s:s;
		}
	};	
	return panel;
});