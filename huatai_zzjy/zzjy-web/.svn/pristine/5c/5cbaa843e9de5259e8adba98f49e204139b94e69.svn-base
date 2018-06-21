//============================================================
//[描    述]  Ace.awt.View		主要功能：动态生成列表
//============================================================
Ace.util.jClass(Ace.util.Observable, (function(){

    /**
     * 根据模板返回元素的 mappings
     * @param {jQuery} $template
     * @param {String} fieldClass 元素的class查找方式
     */
    var getDataFields = function($template, fieldClass){
        if ($template) {
            var dataFields = new Ace.util.Map();
            $.each($template.find('.' + fieldClass), function(){
                var dataField = getDataField($(this));
                if (dataFields.containsKey(dataField.getName())) {
                    throw new Error('view-field  name = ' + dataField.getName() + ',的重复定义!');
                }
                dataFields.put(dataField.getName(), dataField);
            });
            return dataFields;
        } else {
            throw new Error('Ace.awt.View.getDataFields 模板不存在!');
        }
    };
    
    /**
     * 根据jquery的对象获取元素的定义
     * @param {jQuery} $element
     */
    var getDataField = function($element){
        var fullName = $element.attr('name');
        var name = fullName.replace('[{index}]', '');
        var mapping = $element.attr('mapping');
        var defaultValue = $element.attr('defaultValue');
        var type = getType($element);
        var dataType = $element.attr('dataType');
		var dateFormat = $element.attr('format');
		var dataField =  new Ace.data.Field({
            name: name,
            mapping: Ace.util.Format.defaultValue(mapping, name),
            fullName: fullName,
            defaultValue: Ace.util.Format.undef(defaultValue),
            type: Ace.util.Format.defaultValue(type, 'label'),
            dataType: dateFormat ? 'date' : dataType,
			dateFormat : dateFormat
        });
        return dataField;
    };
    
    
    /**
     * 定义中的标签类型
     */
    var types = [{
        key: 'a',
        value: 'a'
    }, {
        key: 'img',
        value: 'img'
    }, {
        key: 'input',
        value: 'input'
    }, {
        key: 'label',
        value: 'p,span,label,li'
    }, {
        key: 'select',
        value: 'select'
    } , {
    	key:'text',
    	value:'textarea'
    }];
    
    /**
     * 获取 view-field 的标签类型
     * @param {jQuery} $element
     */
    var getType = function($element){
        return types.each(function(){
            if ($element.is(this.value)) {
                return this.key;
            }
        });
    };
    
    return {
    
        jClass: 'Ace.awt.View',
        
        /**
         * 构造方法
         * @param {Object} $super	父类方法
         * @param {Object} options	初始设置
         */
        initialize: function($super, target, options){
			
            $super();
            this.addEvents('add', 'remove', 'dataFilter', 'beforeinsert','insert','sort');
            
            if(target.length == 0){
            	throw new Error('target 参数为NULL 或者未匹配到DOM元素 : ' + target);
            }
            
            this.target = target;
            
			Ace.copy(this,Ace.merge(options || {},{
				url: null,
				postData: {},
				jsonRoot: function(data) {
					return data;
				},
                data: new Array(),//初始化数据
                elements: new Ace.util.Collection(),//Ace.awt.Element的集合
                templates: (function(zhis,options){//提取模板
                	zhis.templates = new Ace.util.Map();
                	if (options.template) {
		                var _t = new Object();
		                _t.name = 'default';
		                _t.template = options.template;
		                _t.fieldClass = options.fieldClass;
		                zhis.addTemplate(_t);
		            } else if (options.templates) {
		               	options.templates.each((function(){
		                    return function(){
		                    	this.fieldClass = options.fieldClass;
		                    	zhis.addTemplate(this);
		                    }
		                })());
		                options.templates = null;
		            }
                	return zhis.templates;
                })(this,options),//模板集合
                templateDefault: 'default',//默认模板类
				state:'none'//当前状态(TODO带修改)
            }));
            //采用事件机制处理添加,删除元素
            //将单元添加到 target 里
            this.elements.on('add',(function(zhis){
            	return function(element, index){
	                if (index < (this.size() - 1)) {
	                    var next = this.get(index + 1).target;
	                    if (next && next.html()) {
							element.target.insertBefore(next);
	                    }
	                    this.each(function(_index){ //重置序列号
	                        if (_index > index) {
	                            this.refresh();//this.update();
	                        }
	                    });
	                }
	                else {
	                    var prev = this.size() > 1 ? this.get(index - 1).target : zhis.prev ;
	                    if (prev && prev.html()) {
							element.target.insertAfter(prev);
	                    }else {
	                        zhis.parent.prepend(element.target);
	                    }
	                }
            	};
            }(this)), this);
            //将单元从到 target 里移除掉
            this.elements.on('remove', function(element, index){
                this.each(function(_index){
                    if (_index > (index - 1)) {
                        this.refresh();//this.update();
                    }
                });
            }, this);
            
            if(this.url){
            	this.reload();
            }
        },
        
        setState:function(state){
        	this.state = state;
        },
        
        setTemplateDefault: function (tname) {
        	this.templateDefault = tname;
        	this.elements.each(function(){
            	this.setTemplate(name);
            });
        	return this;
        },
        
        getData: function() {
        	var data = new Array();
        	this.elements.each(function(){
          		data.push(this.getData());
          	});
          	return data;
        },
        
        /**
         * 添加一个新的模板到 Ace.util.View 中
         * @param {json} object
         * 				格式如下 ：{
         * 							name：'xxx',
         * 						   	template:$('xxx'),
         * 							fieldClass:'xxxx',
         * 						  }
         */
        addTemplate: function(o){
        	if(o.template.length == 0){
        		throw new Error('template 参数为NULL 或者未匹配到DOM元素 : ' + template);
        	}
			var $template = o.template.clone();
			if (!o.name||o.name=='default') {
                this.prev = o.template.prev();
				this.parent = o.template.parent() || this.target;
            }
			o.template.remove();
			o.template = null;
            var t = new Ace.util.Template($template.html(),Ace.merge(o,{
                compiled: true,
                name: 'default',
                fieldClass: 'view-field',
                template:$template,
                dataFields:getDataFields($template,o.fieldClass||'view-field')
            }));
            this.templates.put(t.name, t);
        },
        
		setJSON:function(json,callback){
			this.clear();
        	this.update(json);
        	callback?callback(json):null;
		},
		
		setJSONUrl:function(url,postData,jsonRoot){
			Ace.copy(this, Ace.merge({
				url : url,
				postData : postData,
				jsonRoot : jsonRoot
			}, {
				url : this.url,
				postData : this.postData,
				jsonRoot : this.jsonRoot
			}));
			this.reload();
		},
		
		reload : function(postData,callback) {
			(function(zhis){
				jQuery.getJSON(zhis.url,jQuery.isFunction(zhis.postData)?postData.apply(zhis,postData):Ace.merge(zhis.postData,postData), function(data){
					zhis.setJSON(zhis.jsonRoot?((typeof zhis.jsonRoot==='string')?data[zhis.jsonRoot]:zhis.jsonRoot.apply(zhis,[data])):data);
					callback?callback(data):null;
				});
			})(this);
		},
		
        /**
         * 添加单元,默认为添加到末端
         * @param {json} obj
         */
        add: function(index, data){
        	if(jQuery.isPlainObject(index)){
        		data = index;
        		index = this.elements.size();
        	}
        	this.setState('add');        	
        	try{
            	return this.insert(index, data);
           	}finally{
            	this.setState('none');
            }
        },
        
        /**
         * 添加单元
         * @param {number} index 添加到的位置
         * @param {json} data 要初始化的数据
         */
        insert: function(index, data, templateName){				
            if (typeof index == 'number') {
                index = index > this.elements.size() ? this.elements.size() : index;
            }
            else 
                if (typeof index == 'object') {
                    data = index;
                    index = this.elements.size();
                }
            if (!data) {
                data = new Objet();
            }
            var element = new Ace.awt.Element(this);
            element['setTemplate'] = (function(setTemplate,zhis){
                return function(){
                    var template = Ace.isString(arguments[0]) ? zhis.getTemplate(arguments[0]) : arguments[0];
                    if (template && template.getClass() == Ace.util.Template.getClass()) {
                        setTemplate.apply(element, [template, arguments[1], arguments[2]]);
                        zhis.fireEvent('add', element, [element.getData(), arguments[0] , zhis]);//触发add事件
                    }
                };
            })(element['setTemplate'],this);
            element.setTemplate((templateName ? templateName : this.templateDefault), data, index);//为子项设置模板及初始数据
            this.fireEvent('beforeinsert',element,[data, data.index]);
            this.elements.add(index, element);//将子项添加到 elements 属性中
            this.fireEvent('insert',element,[data, data.index]);           
            return element;
        },
        
        /**
         * 更新数据
         * @param {number} index 	如果 index 为数组对象 更新从下标为0开始的数据
         * @param {json} data		如果 data 为数组 则冲下标为index开始更新
         */
        update: function(index, data){
        	this.setState('update');
            var zhis = this;
            if (index == null || (typeof index == 'number' && index >= this.elements.size())) 
                return;
            if (typeof index == 'number') {
                if (Object.prototype.toString.call(data) === '[object Array]') {
                    data.each(function(i){
                        zhis.elements.get((index + i)).refresh(this);
                    });
                } else {
                    this.elements.get(index).refresh(data);
                }
            } else if (Object.prototype.toString.call(index) === '[object Array]') {
            	data = index;
                if (data.length > this.elements.size()) {
                    data.each(function(i){
                        if (zhis.elements.get(i)) {
                        	zhis.elements.get(i).refresh(this);
                        } else {
                        	zhis.insert(this);
                        }
                    });
                } else {
                    var removeElements = new Array();
                    this.elements.each(function(i){
                        if (data[i]) {
                            this.refresh(data[i]);
                        } else {
                            removeElements.push(i);
                        }
                    });
                    removeElements.each(function(i){
                        zhis.remove(this - i);
                    });
                }
            }
            this.setState('none');
        },
        
        /**
         * 获取索引对应的 Ace.awt.View.Element 对象
         * @param {Object} index
         */
        get: function(index){
            var element = this.elements.get(index);
            if (!element) 
                throw new Error('Index = ' + index + '对应的 Ace.awt.View.Element 不存在!');
            return element;
        },
        
        find : function(name,value){
        	return this.elements.each(function(){
        		if(this.getData()[name] == value)
        			return this;
        	});
        },
        
        /**
         * 获取key对应的 模板,如果不提供参数获取默认模板
         * @param {String} name
         */
        getTemplate: function(name){
            name = name ? name : this.templateDefault;
            return typeof this.templates == 'undefined' ? null : this.templates.get(name);
        },
        
		clear: function(){
			this.setState('clear');
			for(var i=this.elements.size()-1;i>-1;i--){
				this.elements.remove(i).target.remove();
			}
			this.setState('none');
		},
		
        /**
         * 移除子项 根据index
         */
        remove: function(index){
			this.setState('remove');
            var delObj = this.elements.remove(index);
            if (typeof delObj !== 'undefined') {
            	var val = this.fireEvent('remove', delObj, delObj.getData());
                if( val != false){
                	delObj.target.remove();
                }
            }
            this.setState('none');
            return delObj;
        },
		
		/**
		 * 排序方法
		 * @param {Object} fieldNamea
		 */
		sort: function(fieldName,desc){
			alert(11);
			var dataFields = this.getTemplate().dataFields;			
			var dataField = dataFields.get(fieldName);
			if(this.url){
				var flag = this.fireEvent('sort',this,[compare,fieldName,desc,dataField]);
				if( flag != false){
					this.reload(jQuery.isFunction(this.postData)?postData.apply(this,{order:desc,orderBy:fieldName}):Ace.merge(this.postData,{order:desc,orderBy:fieldName}));
				}
			}else{
				var compare = (function(){
					return function(to){
						if(desc){
							return dataField ? dataField.sortType(this[fieldName]) > dataField.sortType(to[fieldName]) : this[fieldName] > to[fieldName];
						}else{
							return  dataField ? dataField.sortType(this[fieldName]) < dataField.sortType(to[fieldName]) : this[fieldName] < to[fieldName];
						}
					};
				})();
				var flag = this.fireEvent('sort',this,[compare,fieldName,desc,dataField]);
	           	if( flag != false){
	           		this.update(Ace.sort(this.getData(),compare));
	           }
           }
		}
		
    };
})());