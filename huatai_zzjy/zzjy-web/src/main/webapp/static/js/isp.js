//全局js 设置
$(function(){
	var inits = [];
	window.init = function(){
		context = this;
		inits.each(function(index){
			this.apply(context);
		});
	};
	/*-----------------------滚动条start-----------------------*/

	Ace.Ajax.on('401',function(){
		$.alert('您还没有登录<br/>确定:将为您转到登录页面!',function(){
			window.location.href=window.contextPath;
		});
	});
	Ace.Ajax.on('403',function(){
		$.alert('您无权访问该页面!');
	});
	Ace.Ajax.on('error',function(data, xhr, e, s){
		if(data.responseText){
		var errorstr = data.responseText.replace(/[\f\r\v]/g,'').match(/(\<html((.|\n)*?)\<\/html\>)/gi);
		if(!errorstr)return;
		$.alert(errorstr[0]);
		}
		//$.alert(jQuery.httpData(data,null,s));
		//$.alert(exceptions[e]||"服务器正在忙......请稍后再试！");
	});
	Ace.Ajax.on('500',function(){
		$.alert("服务器正在忙......请稍后再试！");
		/*--$.alert("服务器内部错误");--*/
	});
	Ace.Ajax.on('send',function(event, xhr, s){
		if (!(s.data && (Ace.isString(s.data)?Ace.parseQuery(s.data):s.data)['proFlag'] == 'false')) {
			s.content = s.content ? s.content : (s.form instanceof jQuery && s.form)?s.form.closest('.d-content'):[];
			var otherParams = Ace.parseQuery(s.url);
			if(s.content.length == 0 && otherParams.loadingType != 'mask'){
				s.progress = jQuery.progress().start();
			}else if(s.content.length > 0){
				s.content.mask('数据加载中....');
			}
		}
	});
	Ace.Ajax.on('complete',function(event, xhr, s){
		if (!(s.data && (Ace.isString(s.data)?Ace.parseQuery(s.data):s.data)['proFlag'] == 'false')) {
			if(s.progress){
				s.progress.stop();
			}else if(s.content.length != 0){
				s.content.unmask();
			}
		}
		$(window).resize();
	});
	Ace.Ajax.on('success',function(data, xhr,status,form){
		if(typeof data === 'string'){
			//pageCache.putObject(xhr.setting.url,data);

			/*
			if(jQuery.browser.safari){
				var raRegText = data.replace(/[\f\r\v]/g,'').match(/(\<script((.|\n)*?)\<\/script\>)/gi);
				if(!Ace.isNull(raRegText)){
					raRegText.each(function(i){
						var jstxt = raRegText[i].replace(/<script[^>]*>|<\/script>|<SCRIPT[^>]*>|<\/SCRIPT>/g,'');
						eval(jstxt);
					});
				}
			}
			*/
			init.apply(document.body);
		}
	});
	var pageCache = new Ace.util.Cache('pageCache'); 
	Ace.Ajax.on('send',function(event, xhr, s){
		var data = pageCache.getObject(s.url);
		if(data){
			xhr.abort();
			s.success(data);
			init.apply(document.body);
		}
	});
	/*-----------------------滚动条end-----------------------*/
	/*-----------------------滚动按钮start-----------------------*/
	/*
	inits.push(function(){
		$('.sliderBox',this).each(function(){
			if($(this).data('sliderBox'))return;
			(function(sliderBox,zhis){
				$(zhis).data('sliderBox',sliderBox);
				$(zhis).find('.back').click(function(){
					sliderBox.prev();
				});
				$(zhis).find('.forward').click(function(){
					sliderBox.next();
				});
				wrappers.push($(zhis).data('sliderBox'));
			})(TouchSliderBox($(this).find('.box')[0]),this);
		});
	});*/
	/*-----------------------滚动按钮end-----------------------*/
	/*-----------------------菜单左栏start-----------------------*/
	window.hideANDShow = function(visible){
		(visible?$.fn.show:$.fn.hide).apply($('#east'));
		$('#west').width(visible?660:916).removeClass(visible?'page_main_right970':'page_main_right').addClass(visible?'page_main_right':'page_main_right970');
		$('#content').width($('#west').width());
		$('#pageBottom').removeClass(visible?'page_main_bottom_2':'page_main_bottom').addClass(visible?'page_main_bottom':'page_main_bottom_2');
		$('#east-seperate').removeClass(visible?'uncllapseImg':'cllapseImg').addClass(visible?'cllapseImg':'uncllapseImg');
		Ace.Cookie.put('visible',visible);
		$(window).resize();
		//wrappersRefresh.apply(window);
	};
	$('#east-seperate').click(function(){
		hideANDShow.apply(document.body, [!$('#east').is(':visible')]);
	});
	if(typeof Ace.Cookie.get('visible')=='boolean'){
		hideANDShow.apply(document.body, [Ace.Cookie.get('visible')]);
	};
	/*-----------------------菜单左栏end-----------------------*/
	/*-----------------------元素的显示隐藏start-----------------------*/
	var groups = {};
	inits.push(function(){
		$('[target]',this).each(function(){
			if($(this).data('target'))return;
			$(this).data('target',true);
			var groupname = $(this).attr('group');
			var t = $(this);
			if($(this).is('option')){
				groupname = 'select-'+$(this).parent().attr('name');
				t = t.parent();
				if(t.data('Target-chenge'))
					return;
			}
			(function(t,w,g){
				var ws = w;
				
				if(w == '_blank' || w == '_self'  || w == '_parent' || w == '_top'){
					return;
				}
				
				w = /[)]$/.test(w)?eval('(t.' + w + ')'):$(w);
				var disabledFun =  function(){
					if(w.find('[target]').length == 0){
						w.find('input,select').attr('disabled',w.is(':hidden'));
					}
				};
				var _F = function(hidden){
					(hidden?t.removeClass:t.addClass).apply(t,['checked']);
					(hidden?w.slideUp:w.slideDown).apply(w,[500,function(){
						 disabledFun();
//						 wrappersRefresh();
					 }]);
				};
				var group = null;
				if(g){
					group = groups[g]?groups[g]:groups[g]=new Array();
					group.push(function(soh){
						_F.apply(this,[soh]);
					});
				}
				if(t.is('select')){
					t.bind('change',function(){
						$(this).children().each(function(){
							$($(this).attr('target')).hide();
						});
						$(t.children('[value='+$(this).val()+']').attr('target')).show();
						wrappersRefresh();
					});
					t.data('Target-chenge',true);
				}else{
					t.bind('click',function(event){
						if($(this).is('a'))
							event.preventDefault();
						 if(group){
							 group.each(function(){
								 this.apply(window,[true]);
							 });
						 }
						 _F.apply(this,[!w.is(':hidden')]);
					});
				}
	
			})(t,$(this).attr('target'),groupname);
		});
	});
	/*-----------------------元素的显示隐藏end-----------------------*/
	/*inits.push(function(){
		$("a[title], div[title], span[title]",this).tipTip();提示效果
	});*/
	inits.push(function(){
		$('.checkAll',this).each(function(){/*checkbox全选效果*/
			var check = $(this);
			check.click(function() {
				var items = $(check.attr('expr'));
				if (this.checked) {
					items.checkedAll();
				} else {
					items.checkedNo();
				}
				items.click(function() {
					check.attr('checked', items.val().length == items.length);
				});
			});
		});	
	});
	/*-----------------------tab页切换start-----------------------*/
	inits.push(function(){
		$("[tabs]",this).each(function(){
			if($(this).data('tabs'))return;
			(function(zhis){
				zhis.data('tabs',true);
				var settings = Ace.merge(eval('(' + zhis.attr('tabs') + ')')||{},{
					selectedClass : 'selected',
					show:'show'//fadeIn
				});
				var un = function(){
					zhis.find('[tab]').removeClass(settings.selectedClass);
					zhis.find('[tab]').each(function(){
						$($(this).attr('tab')).hide();
					});
				};
				var click = function(event){
					if($(this).hasClass(settings.selectedClass)&&$($(this).attr('tab')).is(':visible'))return;
					un.apply(window);
					$(this).addClass(settings.selectedClass);
					eval('$.fn.'+settings.show).apply($($(this).attr('tab')));
					try{
						event.preventDefault();
					}catch(e){}
				};
				zhis.find('[tab]').bind('click',click);
				un.apply(window);
				click.apply(zhis.find('.'+settings.selectedClass).length == 0?zhis.find('[tab]').first()[0]:zhis.find('.'+settings.selectedClass)[0],[{preventDefault:function(){}}]);
			})($(this));
		});
	});
	/*-----------------------tab页切换end-----------------------*/
	/*-----------------------日期控件start-----------------------*/
	inits.push(function(){
		$('.Wdate, [date]',this).each(function(){
			if($(this).data('wdata'))return;
			$(this).data('wdata',true);
			(function(zhis){
				zhis.addClass('Wdate');
				zhis.attr('readonly',true);
				var settings = Ace.merge(eval('(' + zhis.attr('date') + ')')||{},{//{maxDate:$(this).attr('maxDate'),minDate:$(this).attr('minDate')}
					maxDate:'2099-10-01',
					minDate:'1900-01-01',
					dateFmt:'yyyy-MM-dd',
					skin:'blue',
					lang:'zh-cn',
					qsEnabled:false
				});
				if(/ipad/.test(jQuery.userAgent)){
					var fmt = settings.dateFmt.split(' ')[0];
					var timeWheels = settings.dateFmt.split(' ').length > 1 ? settings.dateFmt.split(' ')[1] : null;
					var _setting = {
		                dateFormat: fmt.replaceAll('MM','mm'),
		                theme:'ios', 
		                mode:'scroller',
		                endYear:new Date().getFullYear() + 100
		             };
					_setting.preset = timeWheels?'datetime':'date';
					_setting.dateOrder = fmt;
					if(timeWheels){
						_setting.timeWheels=timeWheels.replaceAll('mm','ii');//'HHii';
						_setting.timeFormat=timeWheels.replaceAll('mm','ii');
					}
					$(zhis).scroller(_setting);
					zhis.click(function(){
						$(zhis).scroller('show');
					});
				}else{
					zhis.click(function(){
						//WdatePicker(settings);
					});
				}
			})($(this));
		});
	});
	/*-----------------------日期控件end-----------------------*/
	/*-----------------------对含有ajax属性的链接的处理start-----------------------*/
	inits.push((function(){
		
		var $ = (function($){
			return function(target,context){
				return target?(target instanceof jQuery?target:$(target,context)):null;
			};
		})(jQuery);
		
		return function(){
			$('a[ajax]',this).each(function(){
				if($(this).data('ajax'))return;
				$(this).data('ajax',true);
				$(this).bind('click',function(event){
					var setting = Ace.merge(eval('(' + $(this).attr('ajax') + ')')||{},{
						target : '#content',
						type : 'insertAfter'
					});
					event.preventDefault();//阻止默认事件
					
					
					var href = $(this).attr('href');
					if(setting.type == 'dialog'){
						jQuery.post(href,function(data){
							jQuery.dialog({content:data,lock:true,top:'10%'});
						});
					}else if(!(setting.type == 'cache' && $(setting.target).html().length != 0)){
						var newHref = href.indexOf('?') > 0 ? href.substring(0,href.indexOf('?')) : href;
						jQuery.post(newHref,Ace.parseQuery(href.substring(href.indexOf('?')+1)),function(html){
							 var capolnumDiv = $('<div></div>');
							 switch(setting.type){
							 case 'insertAfter':
								 capolnumDiv.insertAfter($(setting.target));
								 capolnumDiv.html(html);
								 if($(".page_main_right_h3:last").length>0&&$(".infiniteCarousel:last .selectTag").length>0){
									 $(".page_main_right_h3:last span:last").text(">"+$(".infiniteCarousel:last .selectTag").text());
								 }
								 capolnumDiv.find('.backPage').click(function(){
									 var s = $(this).closest('.d-content').size();
									 capolnumDiv.remove();
									 $(setting.target).show();
									 if(s>0){
										 var dialogs = artDialog.list;
										  for (var id in dialogs) {
										        dialogs[id]._reset();
										    };
									 }
									 return false;
								 });
								 $(setting.target).hide();
								 break;
							 case 'html':
								 $(setting.target).html(capolnumDiv);
								 capolnumDiv.html(html);
								 if($(".page_main_right_h3:last").length>0&&$(".infiniteCarousel:last .selectTag").length>0){
									 $(".page_main_right_h3:last span:last").text(">"+$(".infiniteCarousel:last .selectTag").text());
								 }
								 break;
							 case 'cache':
								 $(setting.target).html(capolnumDiv);
								 break;
							 case 'pager':
								 var pager =  eval('(' + setting.pager + ')');
								 pager.setJSON(html);
								 $(setting.hideTarget).hide();
								 $(setting.target).show();
								 break;
							 case 'dialog':
								 break;
							 case 'submit':
								 jQuery.msgbox({time: 2000,msg: "操作成功!",icon:"success"});
								 $(setting.target).submit();
								 break;
							 }
							 init.apply(capolnumDiv);
						 });
					}
					if($(this).closest('[tab]').length > 0){
						$(this).closest('[tab]').click();
					}
				});
			});
		};
		
	})());
	/*-----------------------对title及tab页翻页的处理start-----------------------*/
	

	
	
	
	inits.push(function() {
		if(!($.browser.msie&&($.browser.version=="7.0"||$.browser.version=="6.0"))){ 
			var titlelist =$("a[title], div[title], span[title]",this);
			if(!/ipad/.test(jQuery.userAgent)){
				titlelist = $("a[title], div[title], span[title]",this).not("[noTitle]");
			}
			titlelist.each(function(){
			if($(this).data('isQtip'))return;
			$(this).attr('title',jQuery.trim($(this).attr('title')));
			if(/\{[\S\s]+\}/.test($(this).attr('title'))||$(this).attr('title')==''){
				return;
			}
			if($(this).attr("alert")!="false"){
				$(this).dblclick(function(){
					$.dialog({
				        id: 'Alert',
				        fixed: true,
				        width:500,
				        lock: true,
				        content: "<div style='width:500px;text-align:center;'>"+$(this).attr("title")+"</div>"
				        }
					);
				});
			}
			$(this).qtip({
				position: {
				    my: 'top middle',
				    at: 'bottom center'
				},
				show:{
					solo:true
				},
				content: {
					attr: 'title'
				},
				overwrite: true,
				style: {
	
				}
			});
			$(this).data('isQtip',true);
		});
		$("[noTitle]",this).each(function(){
			if($(this).data('isQtip'))return;
			$(this).attr('title',jQuery.trim($(this).attr('title')));
			if(/\{[\S\s]+\}/.test($(this).attr('title'))||$(this).attr('title')==''){
				return;
			}
			if($(this).attr("alert")!="false"){
				$(this).dblclick(function(){
					$.dialog({
				        id: 'Alert',
				        fixed: true,
				        lock: true,
				        width:500,
				        content: "<div style='width:500px;text-align:center;'>"+$(this).attr("title")+"</div>"
				        }
					);
				});
			}
			$(this).data('isQtip',true);
		});
		}else{
			$("a[title], div[title], span[title]",this).each(function(){
				if($(this).data('isQtip'))return;
				$(this).attr('title',jQuery.trim($(this).attr('title')));
				if(/\{[\S\s]+\}/.test($(this).attr('title'))||$(this).attr('title')==''){
					return;
				}
				if($(this).attr("alert")!="false"){
					$(this).dblclick(function(){
						$.dialog({
					        id: 'Alert',
					        fixed: true,
					        lock: true,
					        width:500, 
					        content: "<div style='width:500px;text-align:center;'>"+$(this).attr("title")+"</div>"
					        }
						);
					});
				}
				$(this).data('isQtip',true);
			});
		}
		
//		//绑定展开按钮事件
//		if(!$(".cllapseImg").data('cllapseImg')){
//			$(".cllapseImg").data('cllapseImg',true);
//		$(".cllapseImg").click(function(i,n){
//			$(".infiniteCarousel").each(function(i,n){
//				var _max=$(n).find(".iconnTags:first").width();
//				var _tagsWidth=0;
//				$(n).find(".iconnTags:first li").each(function(i,n){
//					if($(this).css('display')!='none'){
//						_tagsWidth+=($(n).width()+2);
//					}
//				});
////				alert(i+"|"+_tagsWidth+"|"+_max+"|"+$(n).find(".iconnTags:first li").length);
//				if(_tagsWidth<=_max){
//					$(n).find(".red_jiantou").hide();
//				}else{
//					$(n).find(".red_jiantou").show();
//				}
//			});
//		});
//		}
	});
	/*-----------------------对title及tab页翻页的处理end-----------------------*/
	/*-----------------------输入框的默认提示-----------------------*/
	inits.push(function(){
		$('[defaultTxt]',this).each(function(){
			if($(this).data('defaultTxt'))
				return;
			$(this).data('defaultTxt',true);
			$(this).defaultTxt($(this).attr('defaultTxt'));
		});
	});
	inits.push(function(){
		$("input[readOnly]").each(function(){
			if($(this).data("readOnly"))return;
			$(this).data("readOnly",true);
			$(this).keydown(function(e){
				var curKey = e.which;
				if (curKey == 8) {
					e.preventDefault();
				}
			});
		});
	})
	init.apply(document.body);
});