//============================================================
//[描    述]  Observable类	主要功能：一个抽象基类,为事件机制的管理提供一个公共接口。
//============================================================
Ace.util.jClass({

	jClass : 'Ace.util.Observable',
	
	/**
	 * 构造方法,在创建实例的时候调用
	 */
	initialize : function($super,events){
		$super();
		if(events && Ace.isArray(events)){
			this.events = events;
		}else{
			this.events = new Array();
		}
	},
	
	getListeners : function(){
		return this.listeners = this.listeners ? this.listeners : new Ace.util.Map();
	},
	
	/**
	 * 添加处理事件
	 */
	addEvents : function(){
		if(arguments){
			for(var i=0,length = arguments.length;i<length;i++){
				this.events.push(arguments[i]);
			}
		}		
	},
	
	/**
	 * 获取可用的事件列表
	 */
	getEvents : function(){
		return this.events;
	},
	
	/**
	 * 移除事件
	 */
	removeEvent : function(eventName,scope){
		this.getListeners().remove(eventName);
	},
	
	/**
	 * 判断事件对应的执行方法是否存在
	 */
	has : function(eventName,fun){
		return this.hasListener(eventName, fun);
	},
	
	/**
	 * 判断事件对应的执行方法是否存在
	 */
	hasListener : function(eventName,fun){
		var retVal = false;
		if(this.getListeners().containsKey(eventName)){
			var _listener = this.getListeners().get(eventName);
			_listener.each(function(){
				if(this.method == fun)
					retVal = true;
			});
		}
		return retVal;
	},
	
	/**
	 * 触发事件
	 */
	fireEvent : function(eventName,scope,params){
		var vals = new Array();
		if(this.getListeners().containsKey(eventName)){
			var _listener = this.getListeners().get(eventName);
			return _listener.each(function(){
				this.params = this.params ? this.params : new Array();
				params = this.params.concat(params);
				var val = this.method.apply(scope?scope:this.scope,params);//如果注册事件时提供了scope则取原先的scope			
				if (!Ace.isNull(val)) {
					vals.push(val);
				}
			});
		}
		if(vals.length>0){
			return vals[vals.length-1];
		}
	},
	
	/**
	 * 为事件添加执行方法
	 */
	on : function(eventName,fun,params,scope){
		this.addListener(eventName,fun,params,scope);
		return this;
	},
	
	/**
	 * 为事件添加执行方法
	 */
	addListener : function(eventName,fun,params,scope){
		if(!Ace.isArray(params)){//params 不是数组类型的话,表示该参数代表scope
			scope = params;
			params = null;
		}
		scope = !scope ? this : scope;
		if(this.events.indexOf(eventName)>-1){
			if(!this.getListeners().containsKey(eventName)){
				this.getListeners().put(eventName,new Ace.util.Collection());
			}
			var _listener = this.getListeners().get(eventName);			
			_listener.add({method:fun,params:params,scope:scope});
		}else{
			throw new Error('Event:'+eventName+',不存在!');
		}
	},
	
	/**
	 * 移除事件的执行方法
	 */
	un : function(eventName,fun,scope){
		return this.removeListener(eventName,fun,scope);
	},
	
	/**
	 * 移除事件的执行方法
	 */
	removeListener : function(eventName,fun,scope){
		scope = !scope ? 'simple' : scope;
		var funIndexs = new Array();
		if(this.getListeners().containsKey(eventName)){
			var eventFunctions = this.getListeners().get(eventName);
			eventFunctions.each(function(index){
				if((this.method == fun && this.scope == scope) || !fun){
					funIndexs.push(index);
				}
			});
			funIndexs.each(function(){
				eventFunctions.remove(this);
			});
		}
		return this;
	}	
	
});