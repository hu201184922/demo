/**
 * @class Ace.data.Field所传入字段定义对象的信息
 * @param {Object} config
 * 					fullName		html 的全名
 * 					name			简写名称
 * 					mapping			对应json数据的name
 * 					defaultValue	默认值
 * 					type			html 的类型
 * 					dataType		数据类型
 * 					convert			转换格式
 */
Ace.util.jClass(Ace.data.Property,{

    jClass: 'Ace.data.Field',
    
    initialize: function($super,config){
		$super(new Array(this, ['fullName','name','mapping','defaultValue','dateFormat','type','dataType','convert']))
		this.setPropertyValue(config, {
            defaultValue: '',
            type: 'span',
			dateFormat : 'yyyy-MM-dd',
            dataType: 'auto',
			convert : null
        });
        var st = Ace.data.SortTypes;
        if (!this.sortType) {
            switch (this.dataType) {
                case "string":
                    this.sortType = st.asUCString;
                    break;
                case "date":
                    this.sortType = st.asDate;
                    break;
				case "int":
					this.sortType = st.asInt;
				case "float":
					this.sortType = st.asFloat;
                default:
                    this.sortType = st.none;
            }
        }
//        if (typeof this.sortType == "string") {
//            this.sortType = st[this.sortType];
//        }
		var stripRe = /[\$,%]/g;
        if (!this.convert) {
            var cv, dateFormat = this.dateFormat;
            switch (this.dataType) {
                case "":
                case "auto":
                case undefined:
                    cv = function(v){
                        return v;
                    };
                    break;
                case "string":
                    cv = function(v){
                        return (v === undefined || v === null) ? '' : String(v);
                    };
                    break;
                case "int":
                    cv = function(v){
                        return v !== undefined && v !== null && v !== '' ? parseInt(String(v).replace(stripRe, ""), 10) : '';
                    };
                    break;
                case "float":
                    cv = function(v){
                        return v !== undefined && v !== null && v !== '' ? parseFloat(String(v).replace(stripRe, ""), 10) : '';
                    };
                    break;
                case "bool":
                case "boolean":
                    cv = function(v){
                        return v === true || v === "true" || v == 1;
                    };
                    break;
                case "date":
                    cv = function(v){
                        if (!v) {
                            return '';
                        }						
                        if (Ace.isDate(v)) {
                            return v;
                        }
                        if (dateFormat) {
                            if (dateFormat == "timestamp") {
                                return new Date(v * 1000);
                            }
                            if (dateFormat == "time") {
                                return new Date(parseInt(v, 10));
                            }
                            return Date.parse(v, dateFormat);
                        }
                        var parsed = Date.parse(v);
                        return parsed ? new Date(parsed) : null;
                    };
                    break;                    
            }
			this.convert = cv;
        }        
    },
	
	setValue : function(json){
		var rev = this.convert(Ace.getValue(json,this.mapping));
		if(Ace.isDate(json)||Ace.isString(json)){
			rev = rev ? rev : json ;
		}
		return this.dataType == 'date' && Ace.isDate(rev) ? rev.format(this.dateFormat) : Ace.defaultValue(rev,'');
	},
	
	getValue : function(html){
		return this.convert(html);
	}
	
});