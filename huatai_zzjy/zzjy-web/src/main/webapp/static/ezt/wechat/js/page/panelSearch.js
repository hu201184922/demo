define(['iscroll'],function(){
	var panel={
		init:function(){
			$('html,body').addClass('height100');
			window.GlOBAL.TOOL.addMoveListener();
			window.GlOBAL.TOOL.loadModePic(0,window.GlOBAL.TOOL.getSource(PATH_PIC),function(){
				$('.ui-search-submit').unbind('tap').bind('tap',panel.searchSubmitContainer);//搜索
				$('.ui-search-code').unbind('tap').bind('tap',panel.getSweepPanel);//扫一扫
				$('.ui-load-mode').remove();
				$('.ui-mode-panel').hide().css('visibility','visible').fadeIn();
				$('.ui-search-key').unbind('keyup').bind('keyup',panel.showClearContainer);
				$('.ui-search-key').unbind('focus').bind('focus',panel.showClearContainer);
				$('.ui-search-key').unbind('blur').bind('blur',panel.hideClearContainer);
				$('.ui-public-clear').unbind('tap').bind('tap',panel.quickClearContainer);
			});
		},
		searchSubmitContainer:function(status){//搜索
			var $key=$('.key');
			if($key.val().length>0||status==true){
				$.ajax({
					url:'search.do',
					type:"post",
					data:$('#ui-search-form').serializeArray(),
					dataType:"json",
					beforeSend: function(){
						if(status){
							$.Bstatus({html:'加载中...'});
						};
					},
					success:function(data){
						
					},
					error: function(data){
						//error
						/*
						$('.ui-status-container').remove();
						$.Bstatus({
							html:'数据加载失败,请重试',
							status:false,
							time:500
						});	*/
						
						//true 模拟数据如下
						var data={
							param:{
								id:3234234,	//捆包id
								WarehouseNo:'W140522004671',	//仓单号
								owner:'上海圣冶实业有限公司',	//货主
								name:'冷镦钢盘条',//品名
								kind:'板坯',//品种
								strappingNO:'CZ_20130703109', //牌号
								address:'宝钢', //产地
								format:'Φ273.05*8.89*1000', //规格
								number:26, //数量
								weight:(Math.random()*1000000).toFixed(3), //重量
								wearhouse:'大丰上川钢市', //仓库
								staus:{1:'已生成',2:'已质押',3:'已解押',4:'已注销',5:'注销中'}[Math.ceil(Math.random()*5)], //状态
								ks:Math.ceil(Math.random()*10)%2==0?true:false,//是否防异动 
								contactHouse:'上海市劲申仓储有限公司劲申库',//仓库名称
								contactName:'hello',//联系人
								contactTell:18616897921,//电话
								contactAddress:'上海市宝山区富锦路2359号3号库'//联系地址
							}
						};
						if(data.param!=null){
							setTimeout(function(){
								$('.ui-search-info').hide();
								var str='';
								data.param.ks&&(str='<li><span>标签</span><em class="m">防异动</em></li>');
								$('.ui-search-list').removeClass('ui-search-list-slider').show();
								$('.ui-search-wrapper').html('<ul>\
									<li><span>捆包号</span>'+data.param.id+'</li>\
									<li><span>仓单号</span>'+data.param.WarehouseNo+'</li>\
									<li><span>货主</span>'+data.param.owner+'</li>\
									<li><span>品名</span>'+data.param.name+'</li>\
									<li><span>品种</span>'+data.param.kind+'</li>\
									<li><span>牌号</span>'+data.param.strappingNO+'</li>\
									<li><span>产地</span>'+data.param.address+'</li>\
									<li><span>规格</span>'+data.param.format+'</li>\
									<li><span>数量</span>'+data.param.number+'件</li>\
									<li><span>重量</span>'+data.param.weight+'吨</li>\
									<li class="ui-show-contact cursor-pointer" data-param=\'{"contactHouse":"'+data.param.wearhouse+'","contactName":"'+data.param.contactName+'","contactTell":"'+data.param.contactTell+'","contactAddress":"'+data.param.contactAddress+'"}\'><span>仓库</span>'+data.param.wearhouse+'<i class="fa">&#xecd6;</i></li>\
									<li><span>状态</span>'+data.param.staus+'</li>'+str+'\
								</ul>');
								data.param.ks&&$('.ui-search-list').addClass('ui-search-list-slider');
								$('.ui-show-contact').unbind('tap').bind('tap',panel.showContactContainer);//查看联系人
								new iScroll('wrapper');
								$('.key').val('');
								$('.ui-status-container').remove();
								if(data.param.ks){
									$('.ui-search-label-hu').unbind('tap').bind('tap',{type:1},panel.changeSweepPanel);//换标
									$('.ui-search-label-bu').unbind('tap').bind('tap',{type:2},panel.changeSweepPanel);//补标
								};
							},300);
						}else{
							$('.ui-status-container').remove();
							$.Bstatus({
								html:'没有搜索到相关信息！',
								status:false,
								time:500
							});
						};
					}
				});
			}else{
				$.Bstatus({
					html:'请输入您要查询的捆包号',
					status:false,
					time:500
				});
			};
			return false;	
		},
		changeSweepPanel:function(e){//补换标
			$.Bconfirm({
				html:'调用native端接口！',
				button:['确定'],
				speed:300,
				closeCallback:function(){
					//mock id
					var id='KB0058159';
					var param={
						1:{text:'换标',data:{id:id},url:'change/huanbiao.do'},
						2:{text:'补标',data:{id:id},url:'change/bubiao.do'},
					}[e.data.type];
					$.Bconfirm({
						html:'您确定要'+param.text+'吗？',
						button:['确定','取消'],
						param:param.data,
						url:param.url, 
						sureCallback:function(o,r){
							r();
							$.Bstatus({
								html:param.text+'成功',
								status:true,
								time:500
							});
						}
					});
					return false;
				}
			});	
			return false;	
		},
		getSweepPanel:function(){//扫一扫
			$.Bconfirm({
				html:'调用native端接口！',
				button:['确定'],
				speed:300,
				closeCallback:function(){
					panel.searchSubmitContainer(true);
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
		showClearContainer:function(){//出现清除按钮
			var $target=$(this);
			if($target.val()!=''){
				$target.siblings('.ui-public-clear').show();
			};	
			return false;
		},
		hideClearContainer:function(){//隐藏清除按钮
			var $target=$(this);
			if($target.val()!=''){
				setTimeout(function(){
					$target.siblings('.ui-public-clear').fadeOut();
				},500);
			}else{
				$target.siblings('.ui-public-clear').fadeOut();
			};		
		},
		quickClearContainer:function(){//清除
			return $(this).siblings('input').val('').focus();
		}
	};	
	return panel;
});