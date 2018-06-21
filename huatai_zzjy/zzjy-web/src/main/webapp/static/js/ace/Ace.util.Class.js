//============================================================
//[描    述]	主要用于JS对象的反射
//============================================================
Ace.util.jClass({

    jClass: 'Ace.util.Class',
    
    initialize: function(jClass){
        this.jClass = jClass;
        this.jClassName = Ace.util.Library.each(function(){
            if (this.value == jClass) {
                return this.key;
            }
        });
        if (!this.jClassName) {
            throw new Error('没找到类:' + jClass);
        }
    },
    
    getFunctionName: function(fun){
    	if(typeof fun == 'function'){
			for (var p in this.jClass.prototype) {
				if(this.jClass.prototype[p] == fun){
					return p;
				}
			}
			throw new Error('没有找到'+fun);
		}
    },
    
    getClass: function(){
        return this.jClass;
    },
    
    toString: function(){
        return '[object ' + this.jClassName + ']';
    }
    
});

Ace.apply(Ace.util.Class, {}, (function(){
    var Library, getLibrary = function(){
        if (!Library) 
            Library = new Ace.util.Map();
        return Library;
    }
    return {
    
        forName: function(jClass){
            if (Ace.isString(jClass)) {
                return Ace.util.Class.forName(eval(jClass));
            }
            if (getLibrary().containsKey(jClass)) {
                return getLibrary().get(jClass);
            }
            else {
                var Class = new Ace.util.Class(jClass);
                getLibrary().put(jClass, Class);
                return Class;
            }
        }
    };
})());
