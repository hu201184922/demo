Ace.util.jClass(Ace.util.Observable, {

	jClass : 'Ace.util.Cache',

	initialize : function($super,id) {
		this.cacheMap = new Ace.util.Map();
	},

	putObject : function(key,value){
		this.cacheMap.put(key,value);
	},
	
	getObject : function(key){
		if(this.cacheMap.containsKey(key)){
			return this.cacheMap.get(key);
		}
		return null;
	}

});