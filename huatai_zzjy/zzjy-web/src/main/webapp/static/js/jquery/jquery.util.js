jQuery.userAgent = navigator.userAgent.toLowerCase();

jQuery.extend({
    /**
     * 解决jQuery判断浏览器错误的问题
     */
    browser: {
        version: (jQuery.userAgent.match(/.(?:rv|it|ra|ie)[\/: ]([\d.]+)/) || [])[1],
        safari: !/chrome/.test(jQuery.userAgent) && /webkit/.test(jQuery.userAgent),
        opera: /opera/.test(jQuery.userAgent),
        msie: /msie/.test(jQuery.userAgent) && !/opera/.test(jQuery.userAgent),
        mozilla: /mozilla/.test(jQuery.userAgent) && !/(compatible|webkit)/.test(jQuery.userAgent)
    },
    
    /**
     * 名称：getEvent		属于:jQuery
     * 描述：获取事件源
     * @return
     */
    getEvent: function(){
        if (document.all) {
            try {
                return $(window.event.srcElement);
            } 
            catch (e) {
                return null;
            }
        }
        func = jQuery.getEvent.caller;
        var i = 100;
        while (func != null && i-- > 0) {
            var event = func.arguments ? func.arguments[0] : null ;
            if (event) {
                if ((event.constructor == Event || event.constructor == MouseEvent) || (typeof(event) == "object" && event.preventDefault && event.stopPropagation)) {
                    return $(event.target);
                }
            }
            func = func.caller;
        }
        return null;
    },
    
    dialog : (function(artDialog){
    	return function(){
    		var dia = null;
    		var setting = arguments[0];
    		Ace.merge(setting,{lock : true });
			if(setting.url){
				try{
					Ace.merge(setting, {
						title : '数据加载中...'
					});
					return dia = artDialog.apply(jQuery,arguments);
				}finally{
					$.ajax({ url: setting.url,data:{'loadingType':'mask'},content: dia.dom.content, success: function(html){
					   	var regex = "<title>([^<]*)</title>";
						var raRegText = html.match(regex);
						if(!Ace.isNull(raRegText))
							dia.title(raRegText[1]);
						dia.content(html);
						init.apply(dia.dom.content);
						dia.dom.content.find('.close').bind('click',function(){
	    					dia.close();
	    				});
					}});
				}
			}else{
				try{
					return dia = artDialog.apply(jQuery,arguments);
				}finally{
	    			init.apply(dia.dom.content);
	    			dia.dom.content.find('.close').bind('click',function(){
	    				dia.close();
	    			});
	    		}
			}
    	};
    })(jQuery.dialog),
	
	
	/** 设置屏锁 */
    lock: function () {
        div = document.createElement('div'),
        $div = $(div),
        $div.css({
            zIndex: 1000,
            position: 'absolute',
            left: 0,
            top: 0,
            width: $(document).width() + 'px',
           	height: $(document).height() + 'px',
            overflow: 'hidden'
        }).addClass('d-mask');
        /*
        $div.bind('click', function () {
            that._reset();
        }).bind('dblclick', function () {
            that._click('cancel');
        });
        */
        document.body.appendChild(div);
        return $div;
    },
    
    /** 解开屏锁 */
    unlock: function () {
    	$('.d-mask').remove();
    },
    
    progress: function(){
    	window.searchProgress= new Ace.awt.Progress();
    	return searchProgress ;
    }
    
});

jQuery.fn.extend({
    
    /**
     * 扩展 Jquery 的Val 方法 使其能识别 checkbox 和 radio 如果 不是按 name 分组的话 建议通过 Class 分组
     * 例如： $(".groupclass").val("1"); $(".groupclass").val();
    */
    val: (function(val){    
        return function(value){
            if ( value === undefined ) {
            	if(this.length > 1 && !$(this).attr("type").equals("radio") && !$(this).attr("type").equals("checkbox")){
	        		var vs = [];
	        		$.each(this,function(){
	        			vs.push($(this).val())
	        		})
	        		return vs;
	        	}
                if ($(this).is('input') && $(this).attr("type").equals("radio")) {
                    var vals = null;
                    $(this).filter(":checked").each(function(){
                        if (this.checked) {
                            vals = this.value;
                            return;
                        }
                    });
                    return vals;
                }else if ($(this).is('input') && $(this).attr("type").equals("checkbox")) {
                        var vals = new Array();
                        $(this).filter(":checked").each(function(){
                            if (this.checked) {
                                vals.push(this.value);
                            }
                        });
                        return vals.length == 0 ? [] : vals;
				} else {
					return val.apply(this, arguments);
				}
            }else {
                if ($(this).is('input') && $(this).attr("type").equals("radio")) {
                    $(this).each(function(){
                        if (this.value.equals(value)) {
                            this.checked = true;
                            return;
                        }
                    });
					return $(this);
                } else  if ($(this).is('input') && $(this).attr("type").equals("checkbox")) {
					$(this).checkedNo();
					$(this).each(function(){
						var checkbox = this;
						if (Ace.isArray(value)) {
							jQuery.each(value, function(){
								if (checkbox.value.equals(this)) {
									checkbox.checked = true;
								}
							});
						} else {
							if (this.value.equals(value)) {
								this.checked = true;
							}
						}
					});
					return $(this);
				} else {
					return val.apply(this, arguments);
				}
            }
        };
    })(jQuery.fn.val),

    
    /**
     * 复选框 全选操作
     */
    checkedAll: function(){
        $(this).attr("checked", 'true');
        // $(this).change();
    },
    
    /**
     * 复选框 取消选择
     */
    checkedNo: function(){
        $(this).removeAttr("checked");
       	// $(this).change();
    },
    
    /**
     * 复选框 反选操作
     */
    checkedRev: function(){
        $.each(this,function(){
            this.checked = !this.checked;
            // this.change();
        });
    },
    
    /**
     * 判断按钮是否选中,如果有参数的话  判断传入值是否被选中
     */
    isCheck: function(value){
        var _isCheck = false;
        var len = 0;
        if ($(this).attr("type") === "checkbox") {
            if (value == null) {
                _isCheck = $(this).val() != null;
            }
            else {
                var checkValues = $(this).val();
                if (checkValues != null && checkValues.length == value.length) 
                    jQuery.each(checkValues, function(){
                        var checkValue = this;
                        jQuery.each(value, function(){
                            if (checkValue.toString() == this.toString()) {
                                len++;
                                return;
                            }
                        });
                    });
                _isCheck = len == value.length;
            }
            if (value != null) 
                _isCheck = (len == value.length && $(this).val().length == value.length);
        }
        else 
            if ($(this).attr("type") === "radio") {
                if (value == null) {
                    _isCheck = $(this).val() != null;
                }
                else {
                    _isCheck = $(this).val().toString() == value.toString();
                }
            }
        return _isCheck;
    },
	
	left: function(){
        return parseInt(this.offset().left);
    },
    
    top: function(){
        return parseInt(this.offset().top);
    },
	
    animate: (function(animate){
		return function(){
			var funIndex = Ace.isFunction(arguments[3]) ? 3 : 2;
			if(arguments.length <= funIndex){
				arguments.length = (funIndex+1)
			}
			arguments[funIndex] = (function(callback){
				return function(){
					if(Ace.isFunction(callback)){
						callback.call(this,arguments);
					}
					if ($(this).css('opacity') == '1') {
						$(this).css('filter', '');
						$(this).attr('style', Ace.nullValue($(this).attr('style')).replace(/(filter|FILTER)(\S+)[;]?/g, ''));
					}
				};
			})(arguments[funIndex]);
			animate.apply(this,arguments);
		};
	})(jQuery.fn.animate)
	
});

jQueryUtil = (function(){
    return {
        pageX: function(ele){
            return ele.offsetParent ? (ele.offsetLeft + jQueryUtil.pageX(ele.offsetParent)) : ele.offsetLeft;
        },
        
        pageY: function(ele){
            return ele.offsetParent ? (ele.offsetTop + jQueryUtil.pageY(ele.offsetParent)) : ele.offsetTop;
        }
    };
})();


jQuery.fn.extend({

	
	zTree : (function(_zTree){
		return function(settings,zNodes) {
			var zTree = this.data('zTree');
			if (!zTree) {
				settings = Ace.merge(settings || {}, {
					async : {
						enable: settings.url?true:false,
						url: settings.url,
						dataFilter : settings.dataFilter,
						autoParam: ["id"]
					},
					edit : {enable:settings.edit==true} 
				});
				settings = Ace.merge(settings || {}, {
					view : {
						dblClickExpand : false,// 双击展开父节点的功能
						selectedMulti : false,
						addHoverDom : settings.edit.enable?(function(){
							return function(treeId, treeNode){
								if(treeNode.edit == false){
									$("#" + treeNode.tId + "_span").parent().find('.edit').remove();
								}
								if(treeNode.remove == false){
									$("#" + treeNode.tId + "_span").parent().find('.remove').remove();
								}
								if(treeNode.add != false){
									var sObj = $("#" + treeNode.tId + "_span");
									if (treeNode.editNameFlag || $("#addBtn_"+treeNode.id).length>0) return;
									var addStr = "<button type='button' class='add' id='addBtn_" + treeNode.id + "' title='add node' onfocus='this.blur();'></button>";
									sObj.append(addStr);
									var btn = $("#addBtn_"+treeNode.id);
									if (btn) btn.bind("click", function(){
										if(settings.callback.add){
											settings.callback.add.apply(zTree,[treeId, treeNode]);
										}
										return false;
									});
								}
							};
						})():null,
						removeHoverDom:settings.edit.enable?(function(){
							return function(treeId, treeNode) {
								$("#addBtn_"+treeNode.id).unbind().remove();
							}
						})():null
					},
					edit: {
						 drag:{
						 	isCopy:false,
						 	isMove:false
						 },
						enable: false,
						editNameSelectAll: true
					},
					async : {
						enable : settings.async && settings.async.url?true:false,// 异步处理
						otherParam : {},
						contentType : "application/json"// 提交参数方式，这里 JSON// 格式，默认form格式
					},
					callback : {// 回调函数，在这里可做一些回调处理
						onClick : function(){
						},
						onAsyncSuccess: function(event, treeId, treeNode, msg){
						}
					},
					data : {
						keep: {
							leaf: false,
							parent: true
						},
						key: {
							checked: "checked",
							children: "children",
							name: "name",
							title: "title"
						},
						simpleData : {
							enable : true,
							idKey : "id",
							pId : "pId",
							rootPId : null
						}
					}
				});
				if(!settings.async.enable){
					var call = (function(dataFilter){
						return function(zNodes){
							return dataFilter.apply(window,[zNodes,call]);
						}
					})(settings.dataFilter);
					zNodes = settings.dataFilter?settings.dataFilter.apply(window,[zNodes,call]):zNodes;
				}
				zTree = _zTree.init($(this), settings,zNodes);
				this.data('zTree',zTree);
			}else{
				if(zNodes){
					zTree.getNodes().each(function(){
						zTree.removeNode(this, false);
					});
					zNodes.each(function(){
						zTree.addNodes(null,this);
					});
				}
			}
			return zTree;
		};
		
	})($.fn.zTree),
	
	pager : function(json,pageSize,view){
		var pager = this.data('pager');
		if(!pager){
			$(this).addClass('pages');
			var settings = {};
			if(arguments.length == 4){
				settings.postData = pageSize;
				pageSize = view;
				view = arguments[3];
			}
			if(Ace.isString(json)){
				settings.url = json;
			}
			settings.pageSize = pageSize;
			pager = new Ace.awt.Pager($(this),settings);
			if(view){
				pager.view = view;
				if(Ace.isString(json)){
					pager.view.sort = function(fieldName,desc){
						pager.reload({'order':desc?'asc':'desc','orderBy':fieldName});
					};
				}
				
			};
			pager.on('change',function(data){
				pager.view.setJSON(data,function(){
					init.apply(pager.view.target);
					$(window).resize();
				});

			});
			pager.view.on('sort',function(compare,fieldName,desc){
				if(pager.items){
					pager.setJSON(Ace.sort(pager.items,compare));
					$(window).resize();
				}else{
					pager.reload({'order':desc?'asc':'desc','orderBy':fieldName});
					$(window).resize();
				}
				return false;
			});
			this.data('pager',pager);
		}
		if(arguments.length == 0)
			return pager;
		if(view)pager.view = view;
		if(json && !Ace.isString(json)){
			pager.setJSON(json);
			$(window).resize();
		}
		return pager;
	},
	
	view : function(controlOptions){
		var view = this.data('view');
		if(!view){
			var array = new Array();
			var settings = {
				templates : [],
				templateClass : 'template'
			};
			settings = Ace.merge(controlOptions||{},settings);
			$(this).find('.'+settings.templateClass).each(function(){
				settings.templates.push({name:$(this).attr('name'),template:$(this)});
			});
			view = new Ace.awt.View($(this),settings);
			view.on('add', function(data){
				if(this.target.is('tr')){
					if(data.index%2>0){
						this.target.addClass('bg_fffab2');
					}
					this.target.hover(function(){
						$(this).css({'background-color':'#FFFF9D'});
					},function(){
						$(this).css({'background-color':''});
					});
				}
				this.target.find('.switch').attr('index',data.index).click(function(event){
					view.elements.each(function(){
						this.setTemplate('default',false);
					});
					view.get($(this).attr('index')).setTemplate($(this).attr('template'),!$(this).hasClass('back'));
					event.preventDefault();
				});
				this.target.find('.remove').attr('index',data.index).click(function(event){
					view.remove($(this).attr('index'));
					event.preventDefault();
				});
				this.target.find('.showData').attr('index',data.index).click(function(event){
					alert(Ace.encode(view.get($(this).attr('index')).getData()));
					event.preventDefault();
				});	
				this.target.find('.bindData').attr('index',data.index).click(function(event){
					this.bindData = view.get($(this).attr('index')).getData();
					event.preventDefault();
				});	
			});		
			view.on('add', function(data){
				window.init.apply(this.target);
			});
			$(this).find('.add').click(function(){
				view.add({});
			});
			var zths = $(this);
			$(this).find('.sort').click(function(){
				var order = $(this).hasClass('asc');
				zths.find('.sort').removeClass('asc').removeClass('desc');
				view.sort($(this).attr('orderBy'),!order);
				$(this).addClass(order?'desc':'asc');
			});
			view.setJSON = (function(setjson){
				return function(json){
					setjson.apply(view,arguments);
					var empty = view.target.next('.empty');
					if(empty.length == 0){
						empty = view.target.find('.empty');
					}
					if(empty.length != 0){
						if(!json || json.length == 0){
							empty.show();
						}else{
							empty.hide();
						}
					}
				};
				
			})(view.setJSON);
			this.data('view',view);
		}
		return view;
	},
	
	
	template : function (data) {
		var template = this.data('template');
		if(!template){
			template = new Ace.util.Template($(this).html(),{compiled:true});
			this.data('template',template);
		}
		if(data){
			$(this).html(template.applyTemplate(data));			
		}
		return template;
	},
	
	
    
    

	swfupload : function(settings){
		var swfupload = this.data('swfupload');
		if(!swfupload){
			settings = Ace.merge(settings||{},{
				flash_url : request.getContextPath() + "/static/js/common/swfupload/swfupload.swf",
				upload_url: settings.uploadUrl,
				/*post_params: {"PHPSESSID" : "${pageContext.session.id}"},*/
				//post_params: {"JSESSIONID" : getCookieValueByName('JSESSIONID=', 11)},
				file_size_limit : "100 MB",
				file_types : settings.fileTypes,//"*.*",
				file_types_description : "All Files",
				file_upload_limit : 100,
				file_queue_limit : 0,
				file_post_name:this.attr('name'),//settings.fileName,//加了这个，在webwork的action中要用
				custom_settings : {
					progressTarget : "fsUploadProgress"
					//cancelButtonId : "btnCancel"
				},
				debug: false,
				// Button settings
				button_image_url: request.getContextPath() + "/static/js/common/swfupload/SWFUpload.png",//images/TestImageNoText_65x29.png
				button_width: "67",
				button_height: "17",
				button_placeholder_id: "spanButtonPlaceHolder",
				//button_text: '<img src="${pageContext.request.contextPath}/css/base/img/icons/tjfj.gif" alt="" />',//<span class="theFont">浏览</span>
				//button_text_style: ".theFont { font-size: 16; }",
				//button_text_left_padding: 12,
				//button_text_top_padding: 3,
				
				// The event handler functions are defined in handlers.js
				file_queued_handler : fileQueued,
				file_queue_error_handler : fileQueueError,
				file_dialog_complete_handler : fileDialogComplete,
				upload_start_handler : uploadStart,
				upload_progress_handler : uploadProgress,
				upload_error_handler : uploadError,
				upload_success_handler : uploadSuccess,
				upload_complete_handler : uploadComplete,
				queue_complete_handler : queueComplete,	// Queue plugin event
				files:[]
			});
			$('#fsUploadProgress').view().on('add',function(){
//				this.target.find('.download').bind('click',function(){
//					$.dialog({
//						title:'提示信息',
//						content: '下载功能未实现',
//					    fixed: true
//					});
//				});
			}).on('remove',function(data){
				this.target.find('.progressBarStatus').html('等待删除服务器中的文件...').show();
				this.target.find('.progressContainer').attr('class','progressContainer red');
				setTimeout((function(zhis){
					return function(){
						zhis.target.slideUp(500,function(){
							$(this).remove();
						});
					};
				})(this),1000);
				return false;
			}).setJSON(settings.files);
			swfupload = new SWFUpload(settings);
			this.data('swfupload',swfupload);
		}
		return swfupload;
	},
	
	
	//扩展Jquery,实现select 异步级联
	//展示方式 true ,全部显示请选择
	enable : false,//隐藏没有下级分类的select元素
	
	//接收Url、func(该方法会绑定当前select元素的onchange事件)
	select : function(url,mapping,fun){
	//判断url是否为fun, 如果是方法，将该方法绑定到元素的onchange事件
		if(jQuery.isFunction(url)){
			fun = url;
			url = null;
		};
		//fun不为空，绑定onchange事件
		if(fun){
			$(this).bind('change',(function(zhis){
				return function(){
					fun.apply($(zhis),[zhis.find('option:selected').data('json')]);
				};
			})($(this)));
		}
		//返回一个带load方法的对象，用于加载该select元素的数据
		return {
			/**
			 * 方法会触发select的change事件
			 */
			load : (function(zhis){
				
				return function(param){
					if(url){//如果URl存在，异步请求数据
						zhis.find('option').remove();
						zhis.append('<option value="">请选择</option>');
						if(param){//参数不为空，执行异步请求，如果为空，触发自身的onchange事件
							jQuery.post(url,param,function(data){
								if(jQuery.isArray(data)&&data.length != 0){//如果请求结果为数组，且条数不为0时，将异步请求的结果 添加到select元素
									if(!jQuery.fn.enable){
										zhis.hide();
									}
									jQuery.each(data,function(){
										var option = $('<option value="'+this[mapping['value']]+'">'+this[mapping['text']]+'</option>');
										zhis.append(option);
										if(!jQuery.fn.enable){
											zhis.show();
										}
										option.data('json',this);
									});
								}else{
									if(!jQuery.fn.enable){
										zhis.hide();
									}
								}
								zhis.change();
							});
						}else{
							if(!jQuery.fn.enable){
								zhis.hide();
							}
							zhis.change();//触发自身的onchange事件
						}
					}
				};
			})($(this))
		};
	},
	
	// 输入框默认提示 
	defaultTxt : function(txt,emptyClass) {
		var emptyClass = emptyClass ? emptyClass : 'empty';

		// 设置默认文字
		$(this).val($.trim($(this).val()) == '' ? (function(zhis){$(zhis).addClass(emptyClass);return txt;})(this) : $(this).removeClass(emptyClass).val()).bind({
			focus : function() {
				if($(this).hasClass(emptyClass)) {
					$(this).val('');
					$(this).removeClass(emptyClass);
				}
			},
			blur : function() {
				if($.trim($(this).val()) == '' && !$(this).hasClass(emptyClass)) {
					$(this).val(txt);
					$(this).addClass(emptyClass);
				}
			}
		});
		//绑定表达提交页面
		if($(this).closest('form').length > 0){
			$(this).closest('form').submit((function(zhis){
				return function(){
					if($(zhis).hasClass(emptyClass))
						$(zhis).val('');
				}
			})(this));
		}else{
			$(this).val = (function(funVal){
				return function(){
					if(arguments.length == 0){
						return $(this).hasClass(emptyClass)?'':funVal.apply(this,arguments);
					}else{
						return funVal.apply(this,arguments);
					}
				};
			})($(this).val);
		}
	}
	
});


