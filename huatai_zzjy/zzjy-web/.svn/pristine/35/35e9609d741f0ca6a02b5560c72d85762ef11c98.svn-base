define(function(){
	var panel={
		pageNumber:10,//每页显示10条
		type:window.GlOBAL.TOOL.deParam().type,
		totalNumber:0,
		status:null,
		init:function(){
			if(window.GlOBAL.TOOL.deParam().id==undefined||this.type==undefined){
				$.Bstatus({
					html:'参数错误',
					time:500,
					status:false
				});	
				return false;	
			};
			if(this.type=='1'){
				$('.ui-warper').addClass('ui-warper-marBottom');
				$('.ui-nav').addClass('ui-mode-panel');
			};
			this.getIDInfo(function(){
				panel.getPanelData($('.ui-fk-detail-list'),{page:1,id:window.GlOBAL.TOOL.deParam().id});
				$(window).unbind('scroll').bind('scroll',panel.scrollGetContainer);//滚动加载更多数据
				panel.type=='2'?$('.ui-search-code').hide():$('.ui-search-code').unbind('tap').bind('tap',panel.getSweepPanel);//扫一扫
			});
		},
		getSweepPanel:function(){//扫一扫
			$.Bconfirm({
				html:'调用native端接口！',
				button:['确定'],
				speed:300,
				closeCallback:function(){
					//mock id
					var id='KB0058159';
					$.ajax({
						url:'changeID.do',
						data:id,
						type:"post",
						dataType:"json",
						success:function(data){
							$.Bstatus({html:'加载中'});
							window.location=PATH+'gongdanfankui-mx.html?id='+id+'&type='+panel.type;
						},
						error: function(data){
							//error
							/*$.Bstatus({
								html:'数据加载失败,请重试',
								status:false,
								time:500
							});*/
							
							//success
							$.Bstatus({html:'加载中'});
							setTimeout(function(){
								window.location=PATH+'gongdanfankui-mx.html?id='+id+'&type='+panel.type;
							},500);
						}
					});
					return false;
				}
			});	
			return false;	
		},
		getIDInfo:function(callback){
			$.ajax({
				url:'getIDInfo.do',
				data:window.GlOBAL.TOOL.deParam().id,
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
							name:'上海宝山宝钢贸易有限公司',
							wearhouse:'上海宝钢物流有限公司三冠库一号库',
							tig:'仓单注监管核查',
							mid:'CHT-RZ-201506184-JK219',
							totalNumber:21,
							time:new Date().getTime()-400000000,
							status:Math.ceil(Math.random()*10)%2==0?true:false
						}
					};
					if(data.param!=undefined){
						var B={
							0:{name:'待接收',className:'green',str:'<i class="fa">&#xe721</i><span class="ui-date-time"><i class="fa ui-fk-ico">&#xe922</i></span>'},
							1:{name:'待反馈',className:'yellow',str:'<i class="fa">&#xe721</i><span class="ui-date-time"><i class="fa ui-fk-ico">&#xe922</i></span>'},
							2:{name:'已完成',className:'grey',str:data.param.status?'<i class="fa">&#xf058</i><span class="green">结果正常</span>':'<i class="fa">&#xed4d</i><span class="red">结果异常</span>'}
						}[panel.type];
						$('.ui-fk-detail-head-container').prepend('<div class="ui-fk-detail-head-title">\
							<div class="ui-fk-tips ui-fk-tips-'+B.className+'">'+B.name+'</div>\
							'+data.param.tig+'\
							<span class="ui-fk-time" style="font-size:0.8rem;"><i class="blue">共'+data.param.totalNumber+'件</i></span>\
							<div class="ui-fk-text"><span class="left">已反馈20件</span><span class="right pad-right-5">未反馈20件</span></div>\
						</div>\
						<div class="ui-fk-detail-head-open hide">\
							<p><i class="fa">&#xea47</i>'+data.param.name+'</p>\
							<p><i class="fa">&#xf007</i>'+data.param.wearhouse+'</p>\
							<p><i class="fa">&#xf009</i>'+window.GlOBAL.TOOL.deParam().id+'</p>\
							<p><i class="fa">&#xf21a</i>'+data.param.mid+'</p>\
							<p>'+B.str+'</p>\
						</div>');
						if($('.ui-date-time').length>0){
							panel.getRemainTime($('.ui-date-time'),data.param.time);		
						};
						$('.ui-fk-detail-down').unbind('tap').bind('tap',panel.moreSearchContainer);//查看更多
						$('.ui-load-mode').remove();
						$('.ui-mode-panel').hide().css('visibility','visible').fadeIn();
						panel.totalNumber=data.param.totalNumber;
						panel.status=data.param.status;
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
		moreSearchContainer:function(e){//更多搜索
			var $target=$(this);
			$target.find('i').toggleClass('down');	
			$target.prev().slideToggle();
			return false;
		},
		scrollGetContainer:function(){//滚动加载更多数据
			var $box=$('.ui-fk-detail-list'),
				page=$box.attr('data-page')==undefined?1:parseInt($box.attr('data-page'));
			if($box.length>0){
				var t=$(window).scrollTop(),
					T=$box.offset().top+$box.height(),
					H=$('body').height(),
					h=$(window).height();
				if(($box.data('scroll')==undefined)&&t>=T-h-30){
					panel.getPanelData($box,{page:page,id:window.GlOBAL.TOOL.deParam().id});
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
							rows:[
								{format:'1000*1000*C',ks:Math.ceil(Math.random()*10)%2==0?true:false,status:panel.type=='1'?Math.ceil(Math.random()*10)%2==0?true:false:panel.status,uid:'KB0058161',mid:'D642423',type:'冷轧卷',number:Math.ceil(Math.random()*100),weight:Math.random()*1000},
								{format:'0.5*1000*C',ks:Math.ceil(Math.random()*10)%2==0?true:false,status:panel.type=='1'?null:panel.status,uid:'KB0058159',mid:'D642424',type:'冷冷轧卷冷轧卷轧卷',number:Math.ceil(Math.random()*100),weight:Math.random()*1000},
								{format:'0.5*1000*C',ks:Math.ceil(Math.random()*10)%2==0?true:false,status:panel.type=='1'?null:panel.status,uid:'KB0058158',mid:'D642425',type:'冷轧卷',number:Math.ceil(Math.random()*100),weight:Math.random()*1000},
								{format:'0.5*1000*C',ks:Math.ceil(Math.random()*10)%2==0?true:false,status:panel.type=='1'?Math.ceil(Math.random()*67)%2==0?true:false:panel.status,uid:'KB0058153',mid:'D642426',type:'冷轧卷',number:Math.ceil(Math.random()*100),weight:Math.random()*1000},
								{format:'0.5*1000*C',ks:Math.ceil(Math.random()*10)%2==0?true:false,status:panel.type=='1'?Math.ceil(Math.random()*3)%2==0?true:false:panel.status,uid:'KB0058155',mid:'D642427',type:'冷轧卷',number:Math.ceil(Math.random()*100),weight:Math.random()*1000},
								{format:'0.5*1000*C',ks:Math.ceil(Math.random()*10)%2==0?true:false,status:panel.type=='1'?Math.ceil(Math.random()*67)%2==0?true:false:panel.status,uid:'KB0058156',mid:'D642428',type:'冷轧卷',number:Math.ceil(Math.random()*100),weight:Math.random()*1000},
								{format:'0.5*1000*C',ks:Math.ceil(Math.random()*10)%2==0?true:false,status:panel.type=='1'?null:panel.status,uid:'KB0058154',mid:'D642429',type:'冷轧卷',number:Math.ceil(Math.random()*100),weight:Math.random()*1000},
								{format:'0.5*1000*C',ks:Math.ceil(Math.random()*10)%2==0?true:false,status:panel.type=='1'?null:panel.status,uid:'KB0058157',mid:'D642420',type:'冷轧卷',number:Math.ceil(Math.random()*100),weight:Math.random()*1000},
								{format:'0.5*1000*C',ks:Math.ceil(Math.random()*10)%2==0?true:false,status:panel.type=='1'?null:panel.status,uid:'KB0058152',mid:'D642421',type:'冷轧卷',number:Math.ceil(Math.random()*100),weight:Math.random()*1000},
								{format:'0.5*1000*C',ks:Math.ceil(Math.random()*10)%2==0?true:false,status:panel.type=='1'?null:panel.status,uid:'KB0058150',mid:'D642422',type:'冷轧卷',number:Math.ceil(Math.random()*100),weight:Math.random()*1000}
							]
						}
					};
					setTimeout(function(){
						if(data.param!=undefined){
							if(param.page<=Math.ceil(panel.totalNumber/panel.pageNumber)){
								var D=data.param.rows;
								if(D.length>0){
									_get=function(d,m){
										var _list='';
										for(var i=0;i<d.length;i++){
											var C={
												1:{
													check:'<div class="ui-fk-detail-box-select"><a href="javascript:;" class="ui-all-select ui-single-select"><i class="fa none">&#xf1db</i><i class="fa done">&#xf058</i></a></div>',		
													href:PATH+'gongdanfankui-mx.html?id='+d[i].uid+'&type=1',
													c:'ui-fk-detail-panel-no'
												},
												2:{
													check:'',
													href:PATH+'gongdanfankui-mx.html?id='+d[i].uid+'&type=2',
													c:'ui-fk-detail-panel-complete'	
												}										
											}[panel.type],
											_stp=d[i].status==null?'':d[i].status?'<i class="fa green mar-left-5">&#xf058</i>':'<i class="fa red mar-left-5">&#xed4d</i>',
											_stb=d[i].ks?'<em class="m">防异动</em>':'';
											_list+='<div class="ui-fk-detail-panel '+C.c+'" data-id="'+d[i].uid+'">\
												<div class="ui-fk-detail-box ui-fk-detail-box-'+panel.type+'">'+C.check+'\
													<a href="'+C.href+'" class="ui-fk-detail-box-main">\
														<p class="txt1"><span class="mar-right-5">'+d[i].mid+'</span>'+d[i].uid+'<span class="ui-fk-status-ico">'+_stp+'</span>'+_stb+'</p>\
														<ul>\
															<li>'+d[i].format+'</li>\
															<li>'+d[i].type+'</li>\
															<li>'+d[i].number+'件/'+d[i].weight.toFixed(3)+'吨</li>\
														</ul>\
														<i class="fa ui-fk-right">&#xf105</i>\
													</a>\
												</div>\
											</div>'
										};	
										return _list;
									};
									o.removeData('scroll').attr('data-page',param.page+1).append(_get(D));
									if(panel.type=='1'){
										$('.ui-single-select').unbind('tap').bind('tap',panel.checkPanelContainer);//选择
										$('.ui-all-check').unbind('tap').bind('tap',panel.checkAllPanelContainer);//全选
										$('.ui-fk-detail-foot-txt').unbind('tap').bind('tap',panel.changePanelContainer);//切换正常异常
										$('.ui-submit-button').addClass('active').bind('tap',panel.submitPanelContainer);//确认反馈
									};
									$('.ui-status-container').remove();
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
		checkPanelContainer:function(){//单选
			$(this).toggleClass('active');
			panel.changeNumberContainer();
			return false;
		},
		checkAllPanelContainer:function(){//全选
			var $target=$(this);
			if($target.hasClass('active')){
				$target.removeClass('active');
				$('.ui-single-select').removeClass('active');
			}else{
				$target.addClass('active');
				$('.ui-single-select').addClass('active')
			};
			panel.changeNumberContainer();
			return false;
		},
		changeNumberContainer:function(){
			var number=$('.ui-fk-detail-box-select .active').length,
				total=$('.ui-fk-detail-box-select .ui-single-select').length;
			number==total&&number>0?$('.ui-all-check').addClass('active'):$('.ui-all-check').removeClass('active');
		},
		changePanelContainer:function(){//切换正常异常
			if($('.ui-fk-detail-box-select .active').length<=0){
				$.Bconfirm({
					html:'请至少选择一条捆包！',
					button:['确定'],
					speed:300
				});	
				return false;	
			};
			$.Bchange({
				html:'<a href="javascript:;" class="ui-changed-button green" data-type="1"><i class="fa">&#xf058</i>正常</a><a href="javascript:;" class="ui-changed-button red" data-type="0"><i class="fa">&#xed4d</i>异常</a>',
				callback:function(o,r){
					$('.ui-changed-button',o).unbind('tap').bind('tap',function(){//具体选择
						var type=$('.ui-all-check').hasClass('active')?1:0,//是否全选
							value=$('#ui-fk-status').val(),
							param={
								0:{type:type,value:value,list:[]},
								1:{type:type,value:value}
							}[type];
						if(param.list!=undefined){
							$('.ui-single-select.active').each(function(){
								param.list.push($(this).attr('data-id'));
							});
						};
						var _this=$(this),
							id=_this.attr('data-type'),
							str={
								0:{a:'核实结果全部为<br><span class="red">异常 <img src="'+PATH+'res/images/icon-02.png" /></span>',b:'<i class="fa red mar-left-5">&#xed4d</i>'},
								1:{a:'核实结果全部为<br><span class="green">正常 <img src="'+PATH+'res/images/icon-01.png" /></span>',b:'<i class="fa green mar-left-5">&#xf058</i>'}	
							}[id];
						$.ajax({
							url:PATH+'/storage/ck-fankui-alter',
							data:param,
							type:"post",
							dataType:"json",
							beforeSend:function(){
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
									time:500
								});	*/
								
								//true 模拟数据如下
								$('.ui-status-container').remove();
								$('.ui-fk-detail-foot-txt').html(str.a);
								$('#ui-fk-status').val(type);
								$('.ui-single-select.active').parents('.ui-fk-detail-box').find('.ui-fk-status-ico').html(str.b);
								r();
							}
						});
						return false;
					});
				}
			});
			return false;
		},
		submitPanelContainer:function(){//确认反馈
			$.ajax({
				url:PATH+'/storage/ck-gongdanguanli-fankui-ok',
				data:{},
				type:"post",
				dataType:"json",
				beforeSend:function(){
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
						time:500
					});	*/
					
					//true 模拟数据如下
					var data={
						1:'5',
						2:'error',
						3:'7',
						4:'true'
					}[Math.ceil(Math.random()*4)];
					$('.ui-status-container').remove();
					if(data=='5'){
						$.Bconfirm({
							html:'还有未盘点状态的捆包，请全部输入结果后提交！',
							button:['确定'],
							speed:300
						});	
					}else if(data=='7'){
						$.Bconfirm({
							html:'此工单已反馈结果！',
							button:['确定'],
							speed:300
						});	
					}else if(data=='error'){
						$.Bconfirm({
							html:'反馈不成功，请稍后再试！',
							button:['确定'],
							speed:300
						});	
					}else{
						$.Bstatus({
							html:'反馈成功',
							status:true,
							time:500,
							callback:function(){
								window.location='gongdanfankui-list.html?type=1';	
							}
						});		
					};
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
				o.html(day+hour+minite+second);	
			},1000);
		},
		setFormate:function(s){//格式化时间
			return s<10?'0'+s:s;
		}
	};	
	return panel;
});