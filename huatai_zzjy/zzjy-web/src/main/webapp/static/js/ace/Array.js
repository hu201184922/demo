var ArrayTmp = {};
ArrayTmp.map=new Array().map;
ArrayTmp.filter=new Array().filter;
Ace.apply(Array, {

    /**
     * 名称：indexOf		属于:Array
     * 描述：返回指定项首次出现的索引，未找到返回-1
     * @param obj
     * @return
     */
    indexOf: function(obj){
        if (Object.prototype.toString.call(obj) === '[object String]' || /^[]{0,1}[0-9]{0,}[.]{0,1}[0-9]{0,}$/.test(obj)) {
            var re = new RegExp("," + obj + ",", [""]);
            return ','.concat(this.toString()).concat(',').replace(re, "┢").replace(/[^,┢]/g, "").indexOf("┢");
        }
        else {
            var flag = -1;
            jQuery.each(this, function(index){
            	if(jQuery.isPlainObject(this)){
	                if (flag < 0 && Ace.encode(this).equals(Ace.encode(obj))) {
	                    flag = index;
	                }
            	}else{
	                if (flag < 0 && this == obj) {
	                    flag = index;
	                }
            	}
            });
            return flag;
        }
    },
    /**
     * 名称：remove		属于:Array
     * 描述：从数组中移除对象
     * @param obj
     * @return
     */
    remove: function(obj){
        if (obj != null) {
            var index = !isNaN(obj) ? obj : this.indexOf(obj);
            if (index > -1) {
                this.splice(index, 1);
                return true;
            }
            return false;
        }
    },
    
    /**
     * 清空数组
     */
    clear: function(){
        this.length = 0;
    },
    
    /**
     * 向数组中插入元素
     * @param index 要插入的位置
     * @param value	要插入的元素
     */
    insert: function(index, value){
        if (index == null || value == null) {
            throw new Error('index 和 value,参数都必须不能为空!');
        }
        if (index > this.length) {
            index = this.length;
        }
        if (index == this.length) {
            this.push(value);
            return this;
        }
        else {
            var part1 = this.slice(0, index);
            var part2 = this.slice(index);
            part1.push(value);
            this.clear();
            this.pushAll((part1.concat(part2)));
            return this;
        }
    },
    
    /**
     * 向数组中添加另一个数组
     * @param arry 要添加的数组
     */
    pushAll: function(arry){
        if (typeof arry == "undefined" || arry == null) {
            return;
        }
        if (Object.prototype.toString.call(arry) === '[object Array]') {
            var zthis = this;
            arry.each(function(){
                zthis.push(this);
            });
        }
        else {
            this.push(arry);
        }
    },
    /**
     * 在数组中的每个项上运行一个函数
     * @param {Object} fun	如果fun函数有返回值的,fil函数为null,each方法返回该值
     * @param {Object} fil	如果fun函数有返回值的,fil函数将会触发,如果fil 也有返回值,该值为each方法的返回值.
     */
    each: function(fun, fil){
        for (var i = 0, length = this.length; i < length; i++) {
            var retVal = fun.call(this[i], i, this);
            if (!fil) {
                if (!(typeof retVal == "undefined" || retVal == null)) {
                    return retVal;
                }
            }
            else {
                retVal = fil.call(this[i], i, this, retVal);
                if (!(typeof retVal == "undefined" || retVal == null)) {
                    return retVal;
                }
            }
        }
    },
    
    /**
     * 在数组中的每个项上运行一个函数，并将函数返回真值的项作为数组返回。
     * @param {Object} fun
     */
   /* filter: function(fun){
        var newArray = new Array();
   
        $(this).each(fun, function(index, array, funRet){
            if (funRet) {
                newArray.push(array[index]);
            }
        });
        return newArray;
    },*/
    
    /**
     * 在数组中的每个项上运行一个函数，若所有结果都返回真值，此方法亦返回真值。
     * @param {Object} fun
     */
    every: function(fun){
        var retVal = this.each(fun, function(index, array, funRet){
            if (funRet == false) {
                return funRet;
            }
        });
        return retVal == false ? false : true;
    },
    /**
     * 返回指定项最后一次出现的索引。
     * @param {Object} obj
     */
    lastIndexOf: function(obj){
        var lastIndex = -1;
        for(var i=0,len=this.length;i<len;i++){
        	if(this[i] == obj){
        		lastIndex = i;
        	}
        }
        return lastIndex;
    },

    some: function(fun){
		this.each(fun,function(index,array,funRet){
			if(funRet)
				return true;
		});
		return false;
    },
	/**
     * 在数组中的每个项上运行一个函数，并将全部结果作为数组返回。
     * @param {Object} fun
     */
    map: function(fun){
		var a = new Array();
		this.each(fun,function(index,array,funRet){
			a.push(funRet); 
		});
		return a;
    },
	
	clone: function(){
		return this.slice(0);
	},
	
	swap:function(item1,item2){
		var index1,index2,item;
		if(!isNaN(item1)&&!isNaN(item2)){
			index1 = item1;
			index2 = item2;
		}else{
			index1 = this.indexOf(item1);
			index2 = this.indexOf(item2);
		}
		item = this[index1];
		this[index1] = this[index2];
		this[index2] = item;
	}
});
ArrayTmp.mapTmp=new Array().map;