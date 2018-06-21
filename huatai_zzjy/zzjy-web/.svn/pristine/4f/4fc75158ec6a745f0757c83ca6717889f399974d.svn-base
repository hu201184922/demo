define(function(){
	var panel={
		init:function(){
			if(window.GlOBAL.TOOL.deParam().id==undefined||window.GlOBAL.TOOL.deParam().type==undefined){
				$.Bstatus({
					html:'参数错误',
					time:500,
					status:false
				});	
				return false;	
			};
			
			this.getScreenInfo();
		},
		getScreenInfo:function(){//获取页面数据
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
							uid:'SWL1507092702',
							status:{1:{1:null,2:true,3:false}[Math.ceil(Math.random()*3)],2:{1:false,2:true}[Math.ceil(Math.random()*2)]}[window.GlOBAL.TOOL.deParam().type],
							ks:Math.ceil(Math.random()*10)%2==0?true:false,
							mid:'D642429',
							type:'冷轧卷',
							format:'1000*1000*C',
							number:Math.ceil(Math.random()*100),
							weight:Math.random()*1000,
							message:{1:null,2:'测试文字测试文字测试文字测试文字测试文字测试文字测试文字测试文字测试文字'}[window.GlOBAL.TOOL.deParam().type],
							key:[
								{id:'1',name:'锈蚀',status:false},
								{id:'2',name:'捆包丢失',status:true},
								{id:'3',name:'违规移库',status:false},
								{id:'4',name:'捆包破损',status:true},
								{id:'5',name:'库位异常',status:true},
								{id:'6',name:'其它',status:false}
							],
							upload:{1:[],2:['res/images/upload.jpg','res/images/upload.jpg','res/images/upload.jpg','res/images/upload.jpg']}[window.GlOBAL.TOOL.deParam().type]
						}
					};
					if(data.param!=undefined){
						var _s=data.param.ks?'':' hide';
						$('.ui-fk-mx-head').html('<p class="t1"><span class="mar-right-5">'+data.param.mid+'</span><span>'+window.GlOBAL.TOOL.deParam().id+'</span><em class="m'+_s+' ui-fk-status">防异动</em></p>\
						<ul>\
							<li>'+data.param.format+'</li>\
							<li>'+data.param.type+'</li>\
							<li>'+data.param.number+'件/'+data.param.weight.toFixed(3)+'吨 </li>\
						</ul>');
						var str='',B=data.param.status?' hide':'',B=data.param.status==null?{a:' hide',b:''}:data.param.status?{a:' hide',b:'cor'}:{a:' show',b:'err'};
						if(data.param.key.length>0){
							for(var i=0;i<data.param.key.length;i++){
								var _c=!data.param.status&&window.GlOBAL.TOOL.deParam().type=='2'?data.param.key[i].status?' act':'':'';
								str+='<a href="javascript:;" data-id="'+data.param.key[i].id+'" class="ui-mx-btn mar-right-5'+_c+'">'+data.param.key[i].name+'</a>';
							};
							str='<div class="ui-fk-mx-list'+B.a+'">'+str+'</div>'
						};
						if(B.b.length>0){
							$('.btn-'+B.b).addClass('act');
						};
						$('.ui-fk-mx-box').prepend(str);
						if(window.GlOBAL.TOOL.deParam().type=='1'){
							$('.ui-fk-status').unbind('tap').bind('tap',panel.changeStatusPanel);//选择状态
							$('.ui-mx-btn').unbind('tap').bind('tap',panel.checkErrorPanel);//选择异常
							$('.ui-fk-camera').unbind('tap').bind('tap',panel.selectCameraPanel);//调用相机
							$('.ui-suggest-submit').attr('data-uid',data.param.uid).unbind('tap').bind('tap',panel.submitSuggestPanel);//反馈提交
							$('.ui-fk-textarea').unbind('keyup').bind('keyup',panel.limitNumberPanel);//限制字符
							$('.ui-search-code').unbind('tap').bind('tap',panel.getSweepPanel);//扫一扫
						}else{
							$('.ui-search-code').hide();
							$('.ui-status-button').remove();
							$('.ui-fk-textarea').attr('readonly',true).val(data.param.message);
							if(data.param.upload.length>0){
								var str='';
								for(var i=0;i<data.param.upload.length;i++){
									str+='<li><img src="'+data.param.upload[i]+'" class="width-auto ui-upload-pic" /></li>';
								};
								$('.ui-fk-textarea').after('<div class="ui-fk-upload" style="margin:0;">\
									<ul>'+str+'\
										<div class="clear"></div>\
									</ul>\
								</div>');
							};
						};
						$('.ui-load-mode').remove();
						$('.ui-mode-panel').hide().css('visibility','visible').fadeIn();
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
							
						},
						error: function(data){
							//error
							/*$.Bstatus({
								html:'数据加载失败,请重试',
								status:false,
								time:500
							});*/
							
							//success
							$('.ui-fk-status').show();
						}
					});
					return false;
				}
			});	
			return false;	
		},
		limitNumberPanel:function(){//限制字符
			var chackNums=140,
				value=this.value;
			if(value.length>0){
				var B=value.split(''),n=0,str='';
				for(var i=0;i<B.length;i++){
					n+=/^[\u4e00-\u9fa5]+$/.test(B[i])?1:0.5;
					if(n>chackNums){
						break;
					}else{
						str+=B[i];	
					};	
				};
				this.value=str;
				$('.ui-fk-mx-txt-num span').html(Math.max(0,chackNums-Math.ceil(n)));
			}else{
				$('.ui-fk-mx-txt-num span').html(chackNums);
			};
		},
		submitSuggestPanel:function(){//反馈提交
			if($('.ui-fk-status.act').length>0){
				var _status=$('.ui-fk-status.act').attr('data-status'),
					uid=$(this).attr('data-uid'),
					type=_status=='true'?1:2,
					value=$('.ui-fk-textarea').val(),
					param={
						1:{
							status:true,
							message:value,
							pic:[]
						},
						2:{
							status:false,
							message:value,
							pic:[]		
						}
					}[type];
				if(_status=='false'){
					param.error=[];
					$('.ui-fk-mx-list.act').each(function(){
						param.error.push($(this).attr('data-id'));	
					});
				};
				if($('.ui-upload-pic').length>0){
					$('.ui-upload-pic').each(function(){
						param.pic.push(this.src);	
					});
				};
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
						$.Bstatus({
							html:'反馈成功',
							status:true,
							time:500,
							callback:function(){
								window.location=PATH+'gongdanfankui-xq.html?id='+uid+'&type=1';	
							}
						});	
					}
				});
			}else{
				$.Bconfirm({
					html:'请先选择盘点结果！',
					button:['确定'],
					speed:300
				});
			};
			return false;
		},
		selectCameraPanel:function(){//调用相机
			var $target=$(this),length=$('.ui-upload-pic').length;
			if(length<6){
				$.Bchange({
					html:'<a href="javascript:;" class="ui-changed-button" data-type="1">拍照</a><a href="javascript:;" class="ui-changed-button" data-type="0">从相册中选择</a>',
					callback:function(o,r){
						$('.ui-changed-button').unbind('tap').bind('tap',function(){
							r();
							var _this=$(this),type=_this.attr('data-type');
							$.Bconfirm({
								html:'调用native端接口！',
								button:['确定'],
								speed:300,
								closeCallback:function(){
									var pic={
										0:['res/images/upload.jpg','res/images/upload.jpg','res/images/upload.jpg'],
										1:['res/images/upload.jpg']
									}[type];
									if(pic.length>0){
										var str='';
										for(var i=0;i<pic.length;i++){
											str+='<li><img src="'+pic[i]+'" class="width-auto ui-upload-pic" /></li>';
										};
										if($('.ui-fk-upload').length<=0){
											$('.ui-fk-textarea').after('<div class="ui-fk-upload"><ul>'+str+'<li class="camera ui-fk-camera-button"><i class="fa ico">&#xe9c4</i></li><div class="clear"></div></ul></div>');
											$target.hide();
											$('.ui-fk-camera-button').unbind('tap').bind('tap',panel.selectCameraPanel);
										}else{
											$('.ui-fk-camera-button').before(str);	
										};
										$('.ui-upload-pic').unbind('tap').bind('tap',panel.deletePhotoPanel);//删除照片
									}else{
										$.Bstatus({
											html:'错误代码[000876]',
											status:false,
											time:500
										});	
									};
								}
							});	
							return false;
						});
					}
				});
			}else{
				$.Bconfirm({
					html:'最多上传6张！',
					button:['确定'],
					speed:300
				});	
			};
			return false;
		},
		deletePhotoPanel:function(){//删除照片
			var $target=$(this),length=$('.ui-upload-pic').length;
			$.Bconfirm({
				html:'确定要删除该张照片吗！',
				button:['确定','取消'],
				speed:300,
				sureCallback:function(o,r){
					r();
					$target.parent().fadeOut(function(){
						$(this).remove();	
					});
					if(length==1){
						$('.ui-fk-camera').show();
						$('.ui-fk-upload').remove();
					};
				}
			});
			return false;
		},
		checkErrorPanel:function(){
			$(this).toggleClass('act');	
		},
		changeStatusPanel:function(){//选择状态
			var $target=$(this),status=eval($target.attr('data-status'));
			$target.addClass('act').siblings().removeClass('act');
			!status?$('.ui-fk-mx-list').show():$('.ui-fk-mx-list').hide();
		}
	};	
	return panel;
});