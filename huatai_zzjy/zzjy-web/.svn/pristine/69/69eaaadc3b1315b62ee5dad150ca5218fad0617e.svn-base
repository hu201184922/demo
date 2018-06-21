document.body.addEventListener('touchstart',function(){}); 
;(function(){
	$.extend($,{
		BCacheLoadPic:function(options){//缓存图片
			var defaults={
				src:null,
				param:null,
				callback:function(){}
			};
			var options  = $.extend(true,defaults,options);
			var img = new Image();
			$(img).load(function() {
				options.callback(options.src,this.width,this.height,options.param);
			}).attr("src",options.src);	
		},
		Bstatus:function(options){//提示状态框
			var defaults = {
				html:'成功提示',
				status:null,
				id:'ui-status-'+new Date().getTime(),
				path:PATH,
				time:null,
				speed:300,
				imgbox:{
					loadBackground:'res/images/load.gif',
					trueBackground:'res/images/ico_true.png',
					errorBackground:'res/images/ico_error.png'
				},
				callback:function(){}
			};
			var options=$.extend(true,defaults,options); 
			if($('#'+options.id).length<=0){
				var str=options.status==null?'<img src="'+options.path+options.imgbox.loadBackground+'"/>':options.status?'<img src="'+options.path+options.imgbox.trueBackground+'"/>':'<img src="'+options.path+options.imgbox.errorBackground+'"/>';
				$('body').append('<div class="ui-status-container"id="'+options.id+'"style="z-index:'+new Date().getTime()+';"><div class="ui-status-content"><div class="ui-status-main">'+str+'<p>'+options.html+'</p></div></div></div>');
				var $obj=$('#'+options.id),$content=$obj.children();
				$obj.show().css({'visibility':'hidden','height':Math.max($(window).height(),$('html').height())});
				$content.css({'margin-left':-0.5*$content.width()});
				$obj.css({'visibility':'visible'}).hide().fadeIn(function(){
					function _remove(){
						$obj.fadeOut(options.speed,function(){
							$obj.remove();
							options.callback($obj);	
						});	
					};
					if(options.time!=null){
						setTimeout(function(){
							_remove();
						},options.time);	
					}else{
						options.callback($obj);
					};	
				});
				window.onresize=function(){
					$content.css({'margin-left':-0.5*$content.width()});
				};
			};
		},
		Bconfirm:function(options){//提示状态框
			var defaults = {
				html:'成功提示',
				button:['确定'],
				id:'ui-confirm-'+new Date().getTime(),
				param:null,
				url:null, 
				isAddSureEvent:true,
				path:PATH,
				speed:300,
				callback:function(){},
				sureCallback:function(){},
				closeCallback:function(){}
			};
			var options=$.extend(true,defaults,options); 
			var S={
				init:function(){
					if($('#'+options.id).length<=0){
						var str='',length=options.button.length;
						switch(length){
							case  1 : str='<a href="javascript:;" class="blue ui-confirm-sure">'+options.button[0]+'</a>'; break ;
							case  2 : str='<a href="javascript:;" class="gray ui-confirm-cancel">'+options.button[1]+'</a><a href="javascript:;" class="blue ui-confirm-sure">'+options.button[0]+'</a>';break ;
							default : break ;
						};
						$('body').append('<div class="ui-confirm-container"id="'+options.id+'"style="z-index:'+new Date().getTime()+';"><div class="ui-confirm-content"><div class="ui-confirm-main">'+options.html+'</div><div class="ui-confirm-btn ui-confirm-btn-'+length+'">'+str+'</div></div></div>');
						var $obj=$('#'+options.id),
							$content=$obj.children();
						S.setInitMargin($obj,$content);
						$obj.hide().css({'visibility':'visible'}).fadeIn(function(){
							if(length==1){
								if(options.isAddSureEvent){
									$('.ui-confirm-sure',$obj).unbind('tap').bind('tap',S.removeConfirm);//关闭	
								};
							}else{
								if(options.isAddSureEvent){
									$('.ui-confirm-sure',$obj).unbind('tap').bind('tap',{obj:$obj},S.sureConfirm);//确定
								};
								$('.ui-confirm-cancel',$obj).unbind('tap').bind('tap',S.removeConfirm);//关闭	
							};
							options.callback($obj,S.removeConfirm);
						});
						window.GlOBAL.TOOL.addMoveListener();
						/*window.onresize=function(){
							S.setInitMargin($obj,$content);
						};*/
					};
				},
				setInitMargin:function(a,b){
					var H=b.height();
					a.css('height',Math.max($(window).height(),$('html').height()));
					b.css({'margin-top':-H*0.5});
				},
				sureConfirm:function(e){//确定
					var $target=$(this),
						$obj=e.data.obj;
					if(options.url==null){
						options.sureCallback($obj,S.removeConfirm);
					}else{
						$.ajax({
							url:options.url,
							data:options.param,
							type:"post",
							dataType:"json",
							beforeSend:function(){
								$obj.hide();
								$.Bstatus({
									html:'加载中...',
									width:100
								});
							},
							success:function(data){
								$('.ui-status-container').remove();
								options.sureCallback($obj,S.removeConfirm,data);
							},
							error: function(data){
								/*$('.ui-status-container').remove();
								$.Bstatus({
									html:'数据加载失败,请重试',
									status:false,
									width:180,
									speed:300,
									time:500,
									callback:function(){
										S.removeConfirm();
									}
								});	*/
								
								$('.ui-status-container').remove();
								options.sureCallback($obj,S.removeConfirm,data);
							}	
						});			
					};
					return false;
				},
				removeConfirm:function(){//取消
					$('#'+options.id).fadeOut(options.speed,function(){
						$(this).remove();
						options.closeCallback();	
						window.GlOBAL.TOOL.removeMoveListener();
					});	
					return false;
				}
			};
			S.init();
		},
		Bselect:function(options){//选择组件
			var defaults = {
				button:['取消','确定'],
				id:'ui-select-'+new Date().getTime(),
				path:PATH,
				param:null,
				imgbox:{
					loadBackground:'res/images/load.png'
				},
				callback:function(){},
				sureCallback:function(){},
				closeCallback:function(){}
			};
			var options=$.extend(true,defaults,options); 
			var S={
				init:function(){
					require(['iscroll'],function(){
						if($('#'+options.id).length<=0){
							$('body').append('<div class="ui-select-master"id="'+options.id+'"style="z-index:'+new Date().getTime()+';">\
								<div class="ui-select-control">\
									<div class="ui-select-button">\
										<a href="javascript:;" class="left black ui-select-cancel">'+options.button[0]+'</a>\
										<a href="javascript:;" class="right blue ui-select-sure">'+options.button[1]+'</a>\
									</div>\
									<div class="ui-select-container">\
										<div class="ui-select-content">\
											<img src="'+options.path+options.imgbox.loadBackground+'" class="ui-select-loding ui-reload-btn-animate"/>\
										</div>\
									</div>\
								</div>\
							</div>');
							var $obj=$('#'+options.id),$content=$obj.children(),$box=$('.ui-select-content',$content);
							window.GlOBAL.TOOL.addMoveListener();
							$obj.show().css({'height':Math.max($(window).height(),$('html').height())}).fadeIn(300,function(){
								if(options.param.length>0){
									var str='';
									for(var i=0;i<options.param.length;i++){
										var sp='',B=options.param[i].list;
										if(B.length>0){
											for(var j=0;j<B.length;j++){
												sp+=B[j].acr==undefined?'<li data-id="'+B[j].id+'">'+B[j].name+'</li>':'<li data-id="'+B[j].acr+'"><img src="'+options.path+'res/images/bank_ico_'+B[j].acr+'_s.png"/>'+B[j].name+'</li>';
											};
											str+='<div class="ui-select-panel"id="ui-select-panel-'+i+'"><ul><li></li><li></li>'+sp+'<li></li><li></li></ul></div>'
										};
									};
									$box.addClass('ui-select-row-'+options.param.length+'').html(str+'<div class="ui-select-line"><div class="ui-select-change"></div></div>');
									var H=$('.ui-select-line',$box).height(),A=[];
									$('.ui-select-panel',$box).each(function(index){
										var $box=$(this),
											n=0;
										$('li',$box).each(function(i){
											var _this=$(this);
											if(_this.attr('data-id')==options.param[index].value){
												n=i-2;
												return;
											};
										});
										S.setScrollControl(this.id,n,H,function(o,value,text){
											A[index]={value:value,text:text};
											if(options.param.length==1){
												o.unbind('tap').bind('tap',{param:A},S.sureControl);//确定	
											};
										});
									});	
									$('.ui-select-sure',$content).unbind('tap').bind('tap',{param:A},S.sureControl);//确定	
									
								}else{
									$box.html('<div class="ui-select-null">无数据</div>');	
								};
								$obj.unbind('tap').bind('tap',S.removeControl);	
								$content.unbind('tap').bind('tap',function(e){
									e.stopPropagation();
								});	
								$('.ui-select-cancel',$content).unbind('tap').bind('tap',S.removeControl);	//取消	
							});
							$content.addClass('show');
						};
					});
					return false;
				},
				setScrollControl:function(id,index,h,callback){//定位
					var o=$('#'+id),Bscroll=null;
					Bscroll=new iScroll(id,{
						snap:"li",
						vScrollbar:false,
						onScrollEnd:function(){
							if(callback){
								var index=Math.round(Math.abs(this.y)/h)+2,
									$li=$('li',o).eq(index),
									value=$li.attr('data-id'),
									text=$li.text();
								callback($li,value,text);
							};
						}	
					});	
					Bscroll.scrollTo(0,h*(index),200,true);	
				},
				setInitMargin:function(a,callback){
					a.css('height',Math.max($(window).height(),$('html').height()));
					if(callback){
						callback();
					};
				},
				sureControl:function(e){//确定
					S.removeControl();
					options.sureCallback(e.data.param);
					return false;
				},
				removeControl:function(){//取消
					var $box=$('#'+options.id);
					$box.children().addClass('leave');	
					setTimeout(function(){
						$box.remove();
						options.closeCallback();	
						window.GlOBAL.TOOL.removeMoveListener();
					},300);
					return false;
				}
			};
			S.init();
		},
		Bchange:function(options){//选择组件
			var defaults = {
				id:'ui-changed-'+new Date().getTime(),
				html:null,
				cancelName:'取消',
				callback:function(){},
				closeCallback:function(){}
			};
			var options=$.extend(true,defaults,options); 
			var S={
				init:function(){
					if(options.html!=null){
						if($('#'+options.id).length<=0){
							var str=options.cancelName==null?'':'';
							if(options.cancelName==null){
								$('body').append('<div class="ui-changed-master" id="'+options.id+'" style="z-index:'+new Date().getTime()+';">\
								<div class="ui-changed-container">'+options.html+'</div>\
							</div>');
							}else{
								$('body').append('<div class="ui-changed-master" id="'+options.id+'" style="z-index:'+new Date().getTime()+';">\
									<div class="ui-changed-container pad-left-5 pad-right-5">\
										<div class="ui-changed-content">'+options.html+'</div>\
										<div class="ui-changed-content">\
											<a href="javascript:;" class="ui-changed-cancel black">'+options.cancelName+'</a>\
										</div>\
									</div>\
								</div>');
							};
							var $obj=$('#'+options.id),$content=$obj.children();
							$obj.unbind('tap').bind('tap',S.removeControl);	
							$obj.addClass('in');
							$content.addClass('show').unbind('tap').bind('tap',function(e){
								e.stopPropagation();
							});
							if(options.cancelName!=null){
								$('.ui-changed-cancel',$content).unbind('tap').bind('tap',S.removeControl);	//取消		
							};
							options.callback($content,S.removeControl);
							window.GlOBAL.TOOL.addMoveListener();
						};
					}else{
						$.Bstatus({
							html:'参数错误',
							time:500,
							status:false
						});	
					};
					return false;
				},
				removeControl:function(){//取消
					var $box=$('#'+options.id);
					$box.addClass('out').children().addClass('leave');	
					setTimeout(function(){
						$box.remove();
						options.closeCallback();
						window.GlOBAL.TOOL.removeMoveListener();	
					},300);
					return false;
				}
			};
			S.init();
		}									
	});	
})(jQuery);
