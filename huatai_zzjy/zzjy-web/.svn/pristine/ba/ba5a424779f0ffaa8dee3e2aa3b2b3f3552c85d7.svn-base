define(function(){
	var panel={
		init:function(){
			this.getScreenInfo();
		},
		getScreenInfo:function(){//获取页面数据
			$.ajax({
				url:'getAccountInfo.do',
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
							name:'hello',
							company:'上海欧冶物流股份有限公司',
							avatar:'avatars.jpg',
							finger:false //手势密码开关状态-native提供
						}
					};
					if(data.param!=undefined){
						$('.ui-account-head').html('<div class="ui-account-photo">\
								<img src="'+PATH+'res/images/'+data.param.avatar+'"/>\
							</div>\
							<p class="name">'+data.param.name+'</p>\
							<p>'+data.param.company+'</p>');
						$('.ui-account-check').addClass(data.param.finger?'on':'off');
						$('.ui-load-mode').remove();
						$('.ui-mode-panel').hide().css('visibility','visible').fadeIn();
						$('.ui-clear-cache').unbind('tap').bind('tap',panel.changeStatusPanel);//清除缓存
						$('.ui-check-updata').unbind('tap').bind('tap',panel.checkUpdataPanel);//检查更新
						$('.ui-account-check').unbind('tap').bind('tap',panel.checkNativePanel);//修改手势密码
						$('.ui-login-off').unbind('tap').bind('tap',panel.loginOffPanel);//注销登陆
						$('.ui-account-photo').unbind('tap').bind('tap',panel.modifyPhotoPanel);//修改头像
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
		modifyPhotoPanel:function(){//修改头像
			var $target=$(this);
			$.Bchange({
				html:'<a href="javascript:;" class="ui-changed-button" data-type="1">拍照</a><a href="javascript:;" class="ui-changed-button" data-type="0">从相册中选择</a>',
				callback:function(o,r){
					$('.ui-changed-button').unbind('tap').bind('tap',function(){
						r();
						$.Bconfirm({
							html:'调用native端接口！',
							button:['确定'],
							speed:300,
							closeCallback:function(){
								var pic=['res/images/avatars_02.jpg'];
								$.BCacheLoadPic({
									src:PATH+pic[0],
									callback:function(src){
										$target.html('<img src="'+src+'" style="dislpay:none;"/>').children().fadeIn();	
									}
								});
							}
						});
						return false;
					});
				}
			});
			return false;
		},
		changeStatusPanel:function(){//清除缓存
			$.Bconfirm({
				html:'调用native端接口！',
				button:['确定'],
				speed:300,
				closeCallback:function(){
					$.Bstatus({
						html:'清除成功',
						status:true,
						time:300
					});
				}
			});	
			return false;	
		},
		checkUpdataPanel:function(){//检查更新
			$.Bconfirm({
				html:'调用native端接口检查更新！',
				button:['确定'],
				speed:300,
				closeCallback:function(){
					var data={1:true,2:false}[Math.ceil(Math.random()*2)]
					if(data){
						$.Bconfirm({
							html:'检测到您当前不是最新版本，是否立即更新？',
							button:['确定','取消'],
							speed:300,
							sureCallback:function(){
								var version=window.GlOBAL.TOOL.getBrowser().versions;
								if(version.ios||version.iPhone||version.iPod||version.iPad){
									str='http://itunes.apple.com/cn/app/id995966598';
								}else if(version.android){
									str='http://lc.ouyeelf.com/oycfmobile/download/ouyeelf.apk';
								};
								window.location=str;	
							}
						});		
					}else{
						$.Bstatus({
							html:'当前版本已经是最新',
							status:true,
							time:300
						});
					};
				}
			});	
			return false;		
		},
		checkNativePanel:function(){//修改手势密码
			var $target=$(this),status=$target.hasClass('on');
			$.ajax({
				url:PATH+'/storage/ck-fankui-alter',
				data:!status,
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
					status?$target.removeClass('on').addClass('off'):$target.removeClass('off').addClass('on');
				}
			});
			return false;	
		},
		loginOffPanel:function(){//注销登陆
			$.Bconfirm({
				html:'确定要退出登录吗？',
				button:['确定','取消'],
				speed:300,
				url:'logout.do',
				sureCallback:function(){
					$.Bstatus({
						html:'退出成功',
						status:true,
						time:300,
						callback:function(){
							window.location=PATH+'login-file.html';	
						}
					});
				}
			});		
			return false;		
		}
	};	
	return panel;
});