/***********************************************************\
 *			Ace.util									*
 *[描    述] JS 扩展jQuery的Ajax增加事件监听功能				*
 \***********************************************************/
Ace.Ajax = new (Ace.util.jClass(Ace.util.Observable, (function(jQueryAjax){

    return {
    
        jClass: 'Ace.Ajax',
        
        initialize: function($super){
            $super(new Array(['before', 'success', 'error','send','complete','stop']));
            this.addEvents('200','203','301','400','401','403','404','504','500','503');//全局事件
            
			var zthis = this;
			
            /**
             * 注册 Ace.Ajax before 事件,该事件会在ajax请求之前被调用
             * @param {Object} s
             */
            this.on("before", function(s){
				Ace.merge(s,{
					'form' : jQuery.getEvent(),
					data : s.data ? s.data : '',
					'cache':false,
					dataType : (function(){
						try {
							return !s.dataType && s.form && s.form.is('form') ? s.form.hasClass('nyroModal') ? 'html' : 'json' : s.dataType;
						} catch (e) {
		                    return s.dataType;
		                }
					})()
				});
				switch(s.dataType){
					case 'text':
						if(s.contentType=="application/json"){
							s.data = Ace.decode(s.data);
							s.data['isAjax'] = true;
							s.dataType = 'json';
							delete s.contentType;
						}
						break;
					default:
						s.data = Ace.isString(s.data) ? Ace.parseQuery(s.data) : s.data;
						
						s.data['isAjax'] = true;
				}
				for(var f in s.data){
					if(s.data[f]==null)
						delete s.data[f];
				}
				(function(zhis,success){
					s.success = function(data,status,xhr){
		                zhis.statusCodeCallBack(data, xhr, xhr.status, null, s);
		               	try {
							if (status = "success" && (!xhr || xhr.status == 200)) {
								success ? success.apply(this, arguments) : null;
							}
						} finally {
							Ace.Ajax.fireEvent('success', Ace.Ajax, [data, xhr, xhr.status, s.form]);
						}
					};
				})(this,s.success);
				(function(success){	
					s.success = function(data,status){
						if(data && s.dataType == 'json'){
							if(!(data['success'] == false)){
								data['success'] = true;
								success ? success.apply(this,arguments) : null ;
							}else{
								s.error ? s.error.apply(this,arguments) : null ;
							}
						}else{
							success ? success.apply(this,arguments) : null ;
						}
					};					
				})(s.success);
				(function(zhis,error){
					s.error = function(xhr, status, e){
						try{
			            	zthis.statusCodeCallBack(null, xhr, xhr.status, e, s);
						}catch(e){//
			            	zthis.statusCodeCallBack(null, xhr, 408, e, s);
						}
						error ? error.apply(this,arguments) : null ;
					};
				})(this,s.error);
                s.dataFilter = s.dataFilter ? (function(dataFilter){
                    return function(data, type){
                        //data = dataFilter(data, type);  jquery 1.7.2 改动
                        //return typeof data === "string" && "json" == type ? jQuery.parseJSON(data) : data;
                    	return dataFilter(data, type);
                    };
                })(s.dataFilter) : function(data, type){
                    return data;//typeof data === "string" && "json" == type ? jQuery.parseJSON(data) : data;
                };
            }, this);
            
            /**
             * 注册 Ace.Ajax error 事件,该事件会在ajax请求出现错误时被调用
             * @param {Object} xhr
             * @param {Object} status
             * @param {Object} err
             * @param {Object} errorHeader
             */
            this.on("error", function(xhr, status, err, errorHeader){
				errHeader = errorHeader['errorHeader'] || 'Error-Json';
				var header = null;
				try{xhr.getResponseHeader(errHeader);}catch(e){console.log(e);};
				var errMsg = header == null || header == '' ? null : window['eval']('(' + header + ')');
				if (errMsg && errMsg.length > 0)
					alert(errMsg);
            }, this);
            
            /**
             * 注册 Ace.Ajax success 事件,该事件会在ajax请求成功后调用
             * @param {Object} data
             * @param {Object} xhr
             * @param {Object} status
             * @param {Object} form
             */
            this.on("success", (function(){
                var tipCss = {
                    error: 'onError',
                    success: 'success'
                };
                return function(data, xhr, status, form){
                	/*
                    try {
                        if (form instanceof jQuery && form && form.is('form') && form.html()) {
                            form.find('.fm-error').removeClass('fm-error');
                            form.find('.fm-explain').addClass('fn-hide');
                            
                            if (data['success'] == false) {
    	                        if (data['fieldErrors']) {                        	
    	                            for (var f in data['fieldErrors']) {
    	                            	var $f = form.find('[name='+f+']');
    	                            	$f.parent('.fm-item').addClass('fm-error');
    	                            	$f.next('.fm-explain').removeClass('fn-hide').html(data['fieldErrors'][f][0]);
    	                            }
    	                        }
    	                        form.find('.loading-text').addClass('fn-hide');
    	                    }
                        }
                    } catch (e) {
                    	if(console)
                    		console.log(e);
                        //throw e;
                    }*/
                };
            })(), this);
            
            var $ajaxGlobal = $('<span></span>');//用于捕获ajax请求时的全局事件
            $ajaxGlobal.bind('ajaxStart', function(event){//AJAX 请求开始时执行函数
            });
            $ajaxGlobal.bind('ajaxSend', function(event, xhr, s){//AJAX 请求发送前执行函数
            	s.form?(function(){
            		//s.form.data('ajaxLoading')?xhr.abort():s.form.data('ajaxLoading',true);
            	})():null;
            	Ace.Ajax.fireEvent('send',Ace.Ajax, [event, xhr, s]);
            });
            $ajaxGlobal.bind('ajaxSuccess', function(event, xhr, s){//AJAX 请求成功时执行函数
            	s.form?s.form.data('ajaxLoading',false):null;
            });
            $ajaxGlobal.bind('ajaxError', function(event, xhr, s, e){//AJAX 请求发生错误时执行函数
            	s.form?s.form.data('ajaxLoading',false):null;
	            try{
	            	Ace.Ajax.fireEvent('error', Ace.Ajax, [xhr, xhr.status, e, s.errorHeader || {}]);
				}catch(e){
	            	Ace.Ajax.fireEvent('error', Ace.Ajax, [xhr, 408, e, s.errorHeader || {}]);
	            }
            });
            $ajaxGlobal.bind('ajaxComplete', function(event, xhr, s){//AJAX 请求完成时执行函数
            	Ace.Ajax.fireEvent('complete',Ace.Ajax, [event, xhr, s]);
            });
            $ajaxGlobal.bind('ajaxStop', function(event, xhr, s){//AJAX 请求结束时执行函数
            	Ace.Ajax.fireEvent('stop',Ace.Ajax, [event, xhr, s]);
            });
        },
        
        ajax: function(s){
            this.fireEvent('before', this, s);
            var xhr = jQueryAjax(s);
            xhr.setting = s;
            return xhr;
        },
        
        statusCodeCallBack: function(data, xhr, status, err, s){
            s.statusCode ? s.statusCode[status] ? s.statusCode[status](xhr, status, err) : undefined : undefined;//触发 ajaxSetings 中定义的响应码事件
            Ace.Ajax.fireEvent(String(status), Ace.Ajax, [data, xhr, err, s]);//触发响应码 事件
        }
    };
    
})(jQuery.ajax)));

/**
 * 修改 jQuery 的 ajax 函数
 * @param {Object} s
 */
jQuery.extend({

    ajax: function(s){
        return Ace.Ajax.ajax(s);
    },
    
    getJSON: function(url, data, callback){//防止get请求时的中文乱码        
        return jQuery.post(url, data, callback, "json");
    },
 
    getScript: function(url, callback){
        return jQuery.get(url, null, callback, "script");
    },
    
    post: function(url, data, callback, type){
        if (jQuery.isFunction(data)) {
            callback = data;
            data = {};
        }
        return jQuery.ajax({
            type: "POST",
            url: url,
            data: data,
            cache: false,
            success: callback,
            dataType: type
        });
    },
    
    get: function(url, data, callback, type){
        if (jQuery.isFunction(data)) {
            callback = data;
            data = null;
        }
        return jQuery.ajax({
            type: "GET",
            url: url,
            data: data,
            success: callback,
            dataType: type
        });
    },
    
	param: function( a ) {
		var s = [ ];
		function add( key, value ){
			s[ s.length ] = encodeURIComponent(key) + '=' + encodeURIComponent(value);//escape();
		};
		if ( jQuery.isArray(a) || a.jquery )
			jQuery.each( a, function(){
				add( this.name, this.value );
			});
		else
			for ( var j in a )
				if ( jQuery.isArray(a[j]) )
					jQuery.each( a[j], function(){
						add( j, this );
					});
				else
					add( j, jQuery.isFunction(a[j]) ? a[j]() : a[j] );
		return s.join("&").replace(/%20/g, "+");
	},
    
    /**
     * 覆盖 jQuery 1.7.2 自带的 parseJSON 方法
     */
    parseJSON: (function(parseJSON){
    	return function(data){
    		if(jQuery.isPlainObject(data)){
    			return data;
    		}else{
    			return parseJSON.apply(this,arguments);
    		}
    	};
    })(jQuery.parseJSON),
    
    /**
     * jquery 1.7.2 废除的方法
     */
    httpData : function( xhr, type, s ) {
		var ct = xhr.getResponseHeader("content-type") || "", xml = type === "xml" || !type && ct.indexOf("xml") >= 0, data = xml ? xhr.responseXML : xhr.responseText;

		if (xml && data.documentElement.nodeName === "parsererror") {
			jQuery.error("parsererror");
		}

		// Allow a pre-filtering function to sanitize the response
		// s is checked to keep backwards compatibility
		if (s && s.dataFilter) {
			data = s.dataFilter(data, type);
		}

		// The filter can actually parse the response
		if (typeof data === "string") {
			// Get the JavaScript object, if JSON is used.
			if (type === "json" || !type && ct.indexOf("json") >= 0) {
				data = jQuery.parseJSON(data);

				// If the type is "script", eval it in global context
			} else if (type === "script" || !type
					&& ct.indexOf("javascript") >= 0) {
				jQuery.globalEval(data);
			}
		}

		return data;
	}
    
});
