Ace.util.jClass(Ace.util.Map,{
    
        jClass: 'Ace.awt.Element',
        
        initialize: function($super,view){
        	$super();
        	this.view = view;
        },
        
        setState:function(state){
        	this.state = state;
        },
        
        getIndex :function(){
			var _index = this.view.elements.indexOf(this);
            return this.index = (_index == undefined ? this.data.index : _index);
        },
        
        /**
         * 设置元素项的模板
         * @param {Ace.util.Template}
         * @param {boolean|json} data		如果为boolean表示是否返回原来的数据,如果为json就以json数据载人当前
         * @param {number} index				如果提供该参数,表示更新为index指定的下标
         */
        setTemplate: function(template, data, index){
        	var oldData = this.getData();
			if(Ace.isBoolean(data) || typeof data == 'undefined'){
				data = data ? this.getData() : this.data ;				
			}			
			data = data == null ? {} : data ;
			this.template = template;
			var $template = template.template.clone();
			
			index = index == null ? this.getIndex() : index;
            data['index'] = index;//索引
			this.view.fireEvent('dataFilter', this.view, [data,oldData]);
			
            $template.html(template.applyTemplate(data));
            this.clear();
            
            (function(zhis){
            	template.dataFields.each(function(){//以后可能需要修改	每个view-field 应该是新的
					var dataField = this.value;
	                var $el = $template.find('[name="' + dataField.getFullName().replace('{index}',index) + '"]');
	                if($el.length==0){
	                	$el = $($template.find('a,img,p,span,label,select,input,textarea,li').filter(function(){
	                		return this.name == dataField.getFullName().replace('[{index}]','[' + index + ']');                		 
	                	}));                	
	                }
	                var field = new Ace.awt.Field($el,dataField);
	                if ($el.size() > 1) {
	                    $el.each(function(){
	                        if ($(this).hasClass(zhis.template.fieldClass)) {
	                            if (dataField.getFullName() != dataField.getName()) {
	                                field.target.attr('name', dataField.getFullName().replace('{index}',index));
	                            }
	                        }
	                    });
	                }
	                else {
	                    if ($el.hasClass(zhis.template.fieldClass)) {
	                        if (dataField.getFullName() != dataField.getName()) {
	                            field.target.attr('name',dataField.getFullName().replace('{index}',index));
	                        }
	                    }
	                }
	                zhis.put(field.getName(), field);
	            });
            }(this));
            
            if (this.target) {
            	var oldMess = this.target.after($template).remove();//TODO jquery 1.4 replaceWith 方法与jquery 1.3.2 不一致所以这样修改
            }
            this.target = $template;
            //$template.data('view.element',this);
            if (data) 
                this.setData(data);  
        },
        
        /**
         * 更新数据
         * @param {json|number} index
         */
        update : function(data){
			if(typeof data == 'undefined' || typeof data == 'object'){
				this.setData(data);
			}else if(typeof data == 'number'){
	            this.each((function(zhis){ 
	            	return function(){
	                	this.value.target.attr('name', this.value.getFullName().replace('{index}',(data != null ? data : zhis.getIndex())));
	            	};
	            })(this));
			}
        },
		
		/**
		 * 该方法会重建html,效率上可能会慢些
		 * @param {Object} data
		 */
		refresh : function(data){
			this.setState('refresh');
			this.setTemplate(this.template,data?data:this.getData());
			this.setState('none');
		},
        
        /**
         * 设置数据
         * @param {json} data
         */
        setData: function(data){
            this.data = data ? data : this.getDate();
            this.each(function(){
                var field = this.value;
				field.setValue(data);
            });
        },
		
		getValue:function(name){
			var f = this.get(name);
			return f ? f.setValue(value) : this.data[name];
		},
		
		setValue:function(name,value){
			this.data[name] =  value;
			var f = this.get(name);
			if(f)
				f.setValue(value);
		},
        
        /**
         * 获取数据
         */
        getData: function(){
            if (!this.target)
                return null;
            var retVal = Ace.clone(this.data);
            this.each(function(){
                retVal[this.value.getMapping()] = this.value.getValue();
            });
            return retVal;
        },
        
        /**
         * 移除当前对象
         */
        remove: function(){
            this.target.detach();
        }
    });
