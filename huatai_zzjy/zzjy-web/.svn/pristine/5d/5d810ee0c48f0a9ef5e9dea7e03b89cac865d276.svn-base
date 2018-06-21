/**
* @class Ace.util.Template
* 将一段Html片段呈现为模板。可将模板编译以获取更高的性能。
* @param {config} html HTML片断
*/
Ace.util.jClass((function(){
	
	/**
    * 匹配模板变量的正则表达式。
    * @type RegExp
    * @property re
    */
    var re = /\{([\w-]+)(?:\:([\w\.]*)(?:\((.*?)?\))?)?\}/g;
	
	return {

		jClass: 'Ace.util.Template',
	    
		initialize: function(html,options){
			this.setHtml(html);
			Ace.copy(this,options);
	    	if(this.compiled){
	        	this.compile();
	    	}  	
		},
		
		setHtml:function (html) {
		  	html = html.replaceAll('%7B','{').replaceAll('%7D','}');
			html = html.replaceAll('%28','(').replaceAll('%29',')');
			html = html.replaceAll('%28','(').replaceAll('%29',')');
			html = html.replaceAll('%27','\'');
			if (jQuery.browser.msie) {					
				var regex = /(\value={+)?value={(\S+)}(?!\}+)?/g;
				html = html.replace(regex, function() {
					if (arguments[1]) {
						return arguments[0];
					}
					if (!Ace.isNull(arguments[2])) {
						return arguments[0].replace("value=", "value=\"") + "\"";
					}
				});
			}
			this.html = Ace.util.Format.htmlEncode(html);
		},
		
		/**
	     * 返回HTML片段,这块片断是由数据填充模板之后而成的。
	     * @param {Object/Array} values 模板填充值。该参数可以是一个数组(如果参数是数值型,如{0},或是一个对象,如{foo: 'bar'}.
	     * @return {String} HTML片段
	     */	
	    applyTemplate : function(values){
	        if(this.compiled){
	            return Ace.util.Format.htmlDecode(this.compiled(values));
	        }
	        var useF = this.disableFormats !== true;
	        var fm = Ace.util.Format, tpl = this;
	        var fn = function(m, name, format, args){
	            if(format && useF){
	                if(format.substr(0, 5) == "this."){
	                    return tpl.call(format.substr(5), values[name], values);
	                }else{
	                    if(args){
	                        var re = /^\s*['"](.*)["']\s*$/;
	                        args = args.split(',');
	                        for(var i = 0, len = args.length; i < len; i++){
	                            args[i] = args[i].replace(re, "$1");
	                        }
	                        args = [values[name]].concat(args);
	                    }else{
	                        args = [values[name]];
	                    }
						var val = fm[format].apply(fm, args);
	                    return val !== undefined ? val : '';
	                }
	            }else{
	                return values[name] !== undefined ? values[name] : "";
	            }
	        };
	        return Ace.util.Format.htmlDecode(this.html.replace(re, fn));
	    },
		
		/**
		 * 将模板编译成内置调用函数
		 * 		编译完成后,如果改变html的值后,不调用改方法,将会显示上次编译的html代码
		 */
		compile : function() {
			var fm = Ace.util.Format;
			var prevOffset = 0;
			var arr = [];
			var tpl = this;
			var fn = function(m,name,format,args,offset,s){
				if (prevOffset != offset) {
					var action = {type: 1, value: s.substr(prevOffset, offset - prevOffset)};
					arr.push(action);
				}
				prevOffset = offset + m.length;
				if(format){
					if (args) {
						var re = /^\s*['"](.*)["']\s*$/;
						args = args.split(/,(?=(?:[^"]*"[^"]*")*(?![^"]*"))/);
						for(var i = 0, len = args.length; i < len; i++){
							args[i] = args[i].replace(re, "$1");
						}
						args = [''].concat(args);
					} else {
						args = [''];
					}
					if(format.substr(0, 5) != "this."){
						var action = {type: 3, value:name, format: fm[format], args: args, scope: fm};
						arr.push(action);					
					}else{
						var action = {type: 3, value:name, format:tpl[format.substr(5)], args:args, scope: tpl};
						arr.push(action);					
					}
				}else{
					var action  = {type: 2, value: name};
					arr.push(action);				
				}
				return m;
			};
		
			var s = this.html.replace(re, fn);
			if (prevOffset != (s.length - 1)) {
				var action = {type: 1, value: s.substr(prevOffset, s.length - prevOffset)};
				arr.push(action);
			}
	
			this.compiled = function(values) {
				function applyValues() {
					switch (this.type) {
						case 1:
						return this.value;
						case 2:
						return (values[this.value]!== undefined ? values[this.value] : '');
						default:
							this.args[0] = values[this.value];
						return 'undefined' != typeof(this.format) ? this.format.apply(this.scope, this.args) : this.args[0] ;
					}
				}
				return ArrayTmp.mapTmp.apply(arr,[applyValues]).join('');
			};
			return this;
		},
		
	    call : function(fnName, value, allValues){
	        return this[fnName](value, allValues);
	    }
	};
	
})());