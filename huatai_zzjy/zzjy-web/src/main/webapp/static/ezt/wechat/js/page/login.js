define(['validate'],function(){
	var panel={
		init:function(){
			window.GlOBAL.TOOL.addMoveListener();
			window.GlOBAL.TOOL.loadModePic(0,window.GlOBAL.TOOL.getSource(PATH_PIC),function(){
				$('.ui-load-mode').remove();
				$('.ui-mode-panel').hide().css('visibility','visible').fadeIn();
				panel.addTextEvent();
				$('.ui-public-clear').unbind('tap').bind('tap',panel.quickClearContainer);
				$('.ui-login-submit').unbind('tap').bind('tap',panel.submitLoginContainer);//登陆提交
				$('.ui-eye-button').unbind('tap').bind('tap',panel.checkEyeContainer);
				$(document).unbind('keypress',panel.keyBoardSubmit).bind('keypress',panel.keyBoardSubmit);//go键
			});
		},
		addTextEvent:function(){
			$('.ui-login-input').unbind('keyup').bind('keyup',panel.showClearContainer);
			$('.ui-login-input').unbind('focus').bind('focus',panel.showClearContainer);
			$('.ui-login-input').unbind('blur').bind('blur',panel.hideClearContainer);
		},
		checkEyeContainer:function(){
			var $target=$(this),
				str=function(c,d){
					return '<input class="ui-login-input password" type="'+c+'" value="'+d+'" name="password" valtype="password" placeholder="登录密码" />';
				},
				value=$('.password').val();
			$('.password').remove();
			if($target.hasClass('close')){
				$target.removeClass('close').addClass('open');
				$target.prev().prepend(str('text',value));
			}else{
				$target.removeClass('open').addClass('close');
				$target.prev().prepend(str('password',value));
			};	
			panel.addTextEvent();
		},
		keyBoardSubmit:function(e){
			if(e.keyCode==13){
				panel.submitLoginContainer();
				return false;
			};		
		},
		submitLoginContainer:function(){//登陆提交
			if(!$('.username').Bvalidate()){
				$.Bconfirm({
					html:'用户名格式不正确！',
					button:['确定'],
					speed:300
				});	
				return false;	
			};
			if(!$('.password').Bvalidate()){
				$.Bconfirm({
					html:'密码格式不正确！',
					button:['确定'],
					speed:300
				});	
				return false;	
			};
			$.ajax({
				url:'login.do',
				data:$('#ui-login-form').serializeArray(),
				type:"post",
				dataType:"json",
				beforeSend:function(){
					$.Bstatus({
						html:'加载中...'
					});
				},
				success:function(data){
					
				},
				error:function(){
					//error
					/*$('.ui-status-container').remove();
					$.Bstatus({
						html:'数据加载失败,请重试',
						status:false,
						time:500
					});*/
					
					//true	
					setTimeout(function(){
						$('.ui-status-container').remove();
						var data={
							1 : {status:false,msg:'用户名或密码错误'},
							2 : {status:true}
						}[Math.ceil(Math.random()*2)];
						if(data.status){
							$.Bstatus({
								html:'登陆成功',
								status:true,
								time:500,
								callback:function(){
									window.location=PATH+'index-file.html';
								}
							});	
						}else{
							$.Bstatus({
								html:data.msg,
								status:false,
								time:500
							});		
						};
					},300);
				 }
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