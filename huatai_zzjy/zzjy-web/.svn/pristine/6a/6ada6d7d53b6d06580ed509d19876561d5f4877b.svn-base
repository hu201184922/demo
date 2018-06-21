define(['iscroll',PATH+'res/js/common/ouyeel.numAnimation.js'],function(){
	var panel={
		init:function(){
			window.GlOBAL.TOOL.loadModePic(0,window.GlOBAL.TOOL.getSource(PATH_PIC),function(){
				panel.getScreenInfo(false);
			});
		},
		getScreenInfo:function(status,callback){//根据仓库获取页面数据
			$.ajax({
				url:'getCountNumber.do',
				type:"post",
				data:$('#searchCount').serializeArray(),
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
						param:!status?{
							totalPrice:(Math.random()*10000).toFixed(2), //市值
							panelNumber:Math.ceil(Math.random()*10000), //仓单笔数
							panelWeight:(Math.random()*100000).toFixed(3), //仓单吨数
							totayCloseNumber:Math.ceil(Math.random()*100), //今日质押
							totayOpenNumber:Math.ceil(Math.random()*100), //今日解押
							financieNumber:Math.ceil(Math.random()*10), //金融机构
							ownerNumber:Math.ceil(Math.random()*10), //货主
							reviewNumber:Math.ceil(Math.random()*200), //待监管复核工单
							overtimeWork:Math.ceil(Math.random()*100), //已超时工单
							hotSearchKey:['宝钢一号库','云商仓库','宝钢二号库','上海自贸区运输仓库','宝钢三号库','宝钢四号库','宝钢八号库']//常用搜索关键词 
						}:{
							1:{
								totalPrice:(Math.random()*10000).toFixed(2), //市值
								panelNumber:Math.ceil(Math.random()*10000), //仓单笔数
								panelWeight:(Math.random()*100000).toFixed(3), //仓单吨数
								totayCloseNumber:Math.ceil(Math.random()*100), //今日质押
								totayOpenNumber:Math.ceil(Math.random()*100), //今日解押
								financieNumber:Math.ceil(Math.random()*10), //金融机构
								ownerNumber:Math.ceil(Math.random()*10), //货主
								reviewNumber:Math.ceil(Math.random()*200), //待监管复核工单
								overtimeWork:Math.ceil(Math.random()*100), //已超时工单
								hotSearchKey:['宝钢一号库','云商仓库','宝钢二号库','上海自贸区运输仓库','宝钢三号库','宝钢四号库','宝钢八号库']//常用搜索关键词
							},
							2:null	
						}[Math.ceil(Math.random()*2)]
					};
					if(data.param!=null){
						//$('.ui-count-number').html(panel.getNewPriceString(data.param.totalPrice));
						$('.ui-count-number').attr('data-to',data.param.totalPrice);
						$('.panelNumber').attr('data-to',data.param.panelNumber);
						$('.panelWeight').attr('data-to',data.param.panelWeight);
						$('.totayCloseNumber').html(data.param.totayCloseNumber);
						$('.totayOpenNumber').html(data.param.totayOpenNumber);
						$('.financieNumber').html(data.param.financieNumber);
						$('.ownerNumber').html(data.param.ownerNumber);
						$('.reviewNumber').html(data.param.reviewNumber);
						$('.overtimeWork').html(data.param.overtimeWork);
						if(data.param.hotSearchKey.length>0){
							if($('.count-search-hot').length<=0){
								var str='';
								for(var i=0;i<data.param.hotSearchKey.length;i++){
									str+='<a href="javascript:;" class="hot-search-btn">'+data.param.hotSearchKey[i]+'</a>';
								};
								$('.count-search-container').append('<div class="count-search-hot">'+str+'<div class="clear"></div></div>');
								$('.hot-search-btn').unbind('tap').bind('tap',panel.searchKeyPanel);//关键词搜索
							};
						};
						$('.ui-status-container').remove();
						panel.setAccountContainer(function(){
							$('.ui-count-search-input').unbind('tap').bind('tap',panel.showSearchPanel);//搜索	
						});
						if(callback){
							callback();
						};
					}else{
						$('.ui-status-container').remove();
						$('.ui-count-search-input').val('').blur();
						$.Bstatus({
							html:'没有搜索到相关信息！',
							status:false,
							time:500
						});
					};
				}
			});
		},
		showSearchPanel:function(){//搜索
			var $target=$(this),$content=$('.ui-count-search'),$box=$('.count-search-container');
			$('.count-search-list').html('').hide();
			$('.count-search-hot').show();	
			$('html,body').animate({'scrollTop':0},1);
			window.GlOBAL.TOOL.addMoveListener();
			$content.addClass('ui-count-search-focus');
			$box.show().removeClass('off').addClass('on');
			$target.unbind('keyup').bind('keyup',{box:$box,content:$content},panel.getSearchPanel);//模糊搜索查询	
			$('.ui-count-search-cancel').unbind('tap').bind('tap',panel.removeSearchPanel);//取消	
			$('.ui-count-search-submit').unbind('tap').bind('tap',panel.submitSearchPanel);//提交搜索
			$('.ui-public-clear').unbind('tap').bind('tap',panel.quickClearContainer);
			$('.ui-count-search-input').unbind('blur').bind('blur',panel.hideClearContainer);
			$('.ui-count-search-input').focus();	
			$(document).bind('keypress',panel.keyBoardSubmit);//go键
			return false;
		},
		removeSearchPanel:function(){//取消搜索	
			$('html,body').removeClass('height100');
			$('.ui-count-search').removeClass('ui-count-search-focus');
			$('.count-search-container').removeClass('on').addClass('off');
			$('.ui-count-search-input').blur();
			$('.ui-count-search-input').val('');
			$(document).unbind('keypress',panel.keyBoardSubmit);
			setTimeout(function(){
				$('.count-search-container').hide();
			},100);
			window.GlOBAL.TOOL.removeMoveListener();
			return false;
		},
		getSearchPanel:function(e){//模糊搜索查询	
			var $target=$(this),
				key=this.value,
				$content=e.data.content,
				$box=e.data.box;
			if(key.length>0){
				$target.siblings('.ui-public-clear').css('display','inline-block');
				$.ajax({
					url:'search.do',
					data:key,
					type:"post",
					dataType:"json",
					success:function(data){
						
					},
					error: function(){
						
						//true
						var data={
							param:[
								{value:'<span class="red">'+key+'</span>钢铁物流有限公司'},
								{value:'<span class="red">'+key+'</span>宝钢欧冶金融有限公司'},
								{value:'<span class="red">'+key+'</span>宝钢自贸区有限公司'},
								{value:'<span class="red">'+key+'</span>宝钢钢银有限公司'},
								{value:'<span class="red">'+key+'</span>上海宝钢物流有限公司'},
								{value:'<span class="red">'+key+'</span>宝钢钢铁物流有限公司'},
								{value:'<span class="red">'+key+'</span>宝钢自贸区有限公司'},
								{value:'<span class="red">'+key+'</span>钢铁物流有限公司'},
								{value:'<span class="red">'+key+'</span>宝钢欧冶金融有限公司'},
								{value:'<span class="red">'+key+'</span>钢铁物流有限公司'},
								{value:'<span class="red">'+key+'</span>宝钢欧冶金融有限公司'},
								{value:'<span class="red">'+key+'</span>宝钢自贸区有限公司'},
								{value:'<span class="red">'+key+'</span>宝钢钢银有限公司'},
								{value:'<span class="red">'+key+'</span>上海宝钢物流有限公司'},
								{value:'<span class="red">'+key+'</span>宝钢钢铁物流有限公司'},
								{value:'<span class="red">'+key+'</span>宝钢自贸区有限公司'},
								{value:'<span class="red">'+key+'</span>钢铁物流有限公司'},
								{value:'<span class="red">'+key+'</span>宝钢欧冶金融有限公司'}
							]	
						};
						if(data.param.length>0){
							var str='';
							for(var i=0;i<data.param.length;i++){
								str+='<li><a href="javascript:;" class="count-search-button">'+data.param[i].value+'</a></li>';
							};
							$('.count-search-list').html('<ul>'+str+'</ul>').show();
							$('.count-search-hot').hide();
							$('.count-search-button').unbind('tap').bind('tap',panel.searchKeyPanel);//关键词搜索
							new iScroll('wrapper');
						}else{
							$('.count-search-list').html('').hide();
							$('.count-search-hot').show();
						};
					}	
				});	
			}else{
				$('.count-search-list').html('').hide();
				$('.count-search-hot').show();	
				$target.siblings('.ui-public-clear').hide();
			};
			return false;
		},
		searchKeyPanel:function(){//快速搜索
			var $target=$(this);
			$('.ui-count-search-input').val($target.text());
			panel.submitSearchPanel();
			return false;
		},
		submitSearchPanel:function(){//搜索提交
			if($('.ui-count-search-input').val()==''){
				$.Bstatus({
					html:'请输入要查询的仓库名称',
					status:false,
					time:500
				});
			}else{
				panel.getScreenInfo(true,function(){
					panel.removeSearchPanel();	
				});
			};
			return false;
		},
		keyBoardSubmit:function(e){
			if(e.keyCode==13){
				panel.submitSearchPanel();
				return false;
			};		
		},
		setAccountContainer:function(callback){
			$('.ui-load-mode').remove();
			$('.ui-mode-panel').hide().css('visibility','visible').fadeIn();
			/*$('.ui-account-animate ul').css({
				'-webkit-transition':'all 1s ease-out',
				'-moz-transition':'all 1s ease-out',
				'transition':'all 1s ease-out'
			}).each(function(){
				var _this=$(this),index=Number(_this.attr('data-animate')),H=Number(_this.attr('data-height'));
				_this.css({
					'-webkit-transform':'translate(0,-'+H*index+'rem)',
					'-moz-transform':'translate(0,-'+H*index+'rem)',
					'transform':'translate(0,-'+H*index+'rem)'	
				});	
			});	*/
			$('.ui-account-num').each(function(){
				var $this = $(this),decimals=$this.attr('data-decimals');
				$this.countTo({decimals:parseInt(decimals)});
			});
			if(callback){
				callback();
			};
		},
		getNewPriceString:function(d){
			var price=d.toString(),
				length=Math.max(price.length,6),
				number=((length<=7?4.2:4.7)-(length-6)*0.4).toFixed(1),
				arr=price.split('.'),
				_s='';
			for(var j=0;j<10;j++){
				_s+='<li style="height:'+number+'rem;line-height:'+(Number(number)+0.3).toFixed(1)+'rem;">'+j+'</li>';
			};
			function _get(M){
				var str='',a=M.split('');
				for(var j=0;j<a.length;j++){
					var m='',n=parseInt(a[j]);
					for(var z=0;z<=n;z++){
						m+='<li style="height:'+number+'rem;line-height:'+(Number(number)+0.3).toFixed(1)+'rem;">'+z+'</li>';
					};
					str+='<div class="ui-account-animate" style="height:'+number+'rem;"><ul data-height="'+number+'" data-animate="'+(10+n)+'">'+_s+m+'</ul></div>';
					/*if(M.length>3&&(M.length%3-1==j||(M.length%3+2)==j||(M.length%3+5)==j)&&j!=M.length-1){
						str+='<div class="ui-account-dian" style="height:'+number+'rem;">,</div>';	
					};*/
				};
				return str;
			};
			return '<div class="ui-account-current" style="height:'+number+'rem;margin-top:'+(-0.5*number)+'rem;"><div class="ui-account-number" style="height:'+number+'rem;font-size:'+number+'rem;line-height:'+(Number(number)+0.3).toFixed(1)+'rem;">'+_get(arr[0])+'<div class="ui-account-dian" style="height:'+number+'rem;line-height:'+number+'rem;">.</div>'+_get(arr[1])+'</div></div>';
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