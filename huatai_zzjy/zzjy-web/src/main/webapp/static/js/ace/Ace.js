/***********************************************************\
 *			Ace											*
 *[描    述] JS 框架											*
 *			 部分内容来自于网络								*
 \***********************************************************/
Ace = (function(){
    return {
    
        version: '1.0',
        
        /**
         * 定义命名空间
         * @param {Object} namespace
         */
        namespace: function(namespace){
            var namespaceArray = namespace.split('.');
            for (var i = 0; i < namespaceArray.length; i++) {
                namespace = i == 0 ? namespaceArray[i] : namespace += ("." + namespaceArray[i]);
                if (eval('typeof ' + namespace + ' == \"undefined\"')) {
                    if (i == 0) {
                        eval('var ' + namespace + ' = {};');
                    }
                    else {
                        eval(namespace + ' = {};');
                    }
                }
            }
        },
        
        /**
         * 为 Class 扩展方法与属性
         * @param {Object} jClass
         * @param {Object} prototype
         * @param {Object} defaults		静态方法
         */
        apply: function(jClass, prototypes, defaults){
            if (jClass && prototypes && typeof prototypes == 'object') {
                for (var fName in prototypes) {
                    jClass.prototype[fName] = prototypes[fName];
                }
                if (Object.prototype.toString != prototypes['toString']) {//TODO IE在json对象中的toString不能遍历的问题
                    jClass.prototype['toString'] = prototypes['toString'];
                }
            }
            if (defaults) {
                for (var fName in defaults) {
                    jClass[fName] = defaults[fName];
                }
                if (Object.prototype.toString != defaults['toString']) {//TODO IE在json对象中的toString不能遍历的问题
                    jClass['toString'] = defaults['toString'];
                }
            }
        },
        
        merge : function () {
			var args = arguments;
			if(args.length >= 2){
				for(var f in args[1]){
					if(Ace.isNull(args[0][f])){
						args[0][f] = args[1][f];
					}else if(!Ace.isFunction(args[0][f]) && Ace.isFunction(args[1][f])){
						var fun = args[1][f];
						//fun.apply();
					}else if(jQuery.isPlainObject(args[1][f])){
						if(!jQuery.isPlainObject(args[0][f]))
							args[0][f] = {};
						Ace.merge.apply(this,[args[0][f],args[1][f]]);
					}
				}
				if(args.length > 2){
					var argArray = new Array();
					for(var i=0;i<args.length;i++){
						if(i!=1)
							argArray.push(args[i]);
					}
					Ace.merge.apply(this,argArray);
				}
				return args[0];
			}
		}
		
    };
    
})();
