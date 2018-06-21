//============================================================
//[描    述] static		字符串处理帮助类
//============================================================
Ace.util.StringUtil = new (Ace.util.jClass({

    jClass: 'Ace.util.StringUtil',
    
    initialize: function(){
    
    },
    
    /**
     * 名称：formatHtml 属于:StringUtil 描述：需要格式化的字符串 source 根据 source 串中 {属性名} 从
     * object 对象中查找其对应的值
     *
     * @param source
     * @param object
     * @return
     */
    formatHtml: function(source, object){
        if (jQuery.browser.msie) {
            var regex = /(\value={+)?value={(\S+)}(?!\}+)?/g;
            source = source.replace(regex, function(){
                if (arguments[1]) {
                    return arguments[0];
                }
                if (!Ace.isNull(arguments[2])) {
                    return arguments[0].replace("value=", "value=\"") + "\"";
                }
            });
        }
        if (!Ace.isNull(object)) {
            var regex = /(\{+)?{(\S+)}(?!\}+)?/g;
            var source = source.replace(regex, function(){
                if (arguments[1]) {
                    return arguments[0];
                }
                if (!Ace.isNull(arguments[2])) {
                    try {
                        var value = eval("object." + arguments[2]);
                    } 
                    catch (e) {
                        value = "";
                    }
                    return Ace.isNull(value) ? "" : value;
                }
            });
        }
        else {
            var regex = /(\{+)?{(\S+)}(?!\}+)?/g;
            var source = source.replace(regex, function(){
                if (arguments[1]) {
                    return arguments[0];
                }
                if (!Ace.isNull(arguments[2])) {
                    return "";
                }
            });
        }
        return source;
    },
    
    /**
     * 字符在字符串中出现的次数
     */
    occurTimes: function(source, a){
        var count = 0;
        var offset = 0;
        do
        {
            offset = source.indexOf(a, offset);
            if(offset != -1)
            {
                count++;
                offset += a.length;
            }
        }while(offset != -1)
        return count;
    },
    
    /**
     * 名称：isNull 属于:Ace.util.StringUtil 描述：判断字符串是否为空
     *
     * @param source
     * @return
     */
    isNull: function(source){
        return typeof source == "undefined" || source == null || source == "";
    },
    
    /**
     * 名称：nullValue 属于:Ace.util.StringUtil 描述：返回字符串如果为null 同时 defaultValue 不为 Null
     * 返回defaultValue 否则返回""
     *
     * @param source
     * @param defaultValue
     * @return
     */
    nullValue: function(source, defaultValue){
        if (!source && !defaultValue) {
            return '';
        }
        return this.isNull(source) ? defaultValue.toString() : source.toString();
    },
    
    /**
     * 返回数字 如果 source 为NUll 则返回0 len 为数字保留的小数位
     */
    numberValue: function(source, len){
    	if(isNaN(source))return 0;
    	if(len==0||source.indexOf(".")==-1){
    		return parseInt(source);
    	}
    	if(len>0){
    		source = source.substring(0,source.indexOf("."))+source.substr(source.indexOf("."),len+1);
    	}
    	return parseFloat(source);
    },
    
    /**
     * 首字母大写
     */
    upperCaseFirst: function(source){
        return (source[0]==source[0].toUpperCase());
    },
    
    /**
     * 字符串中是否含有中文
     */
    includeChinese: function(source){
        return false;
    },
    
    /**
     * 名称：replaceAll 属于:String 描述：左边补零以满足长度要求
     *
     * @param source
     *            源字符串
     * @param resultLength
     *            最终长度
     * @return
     */
    addZeroLeft: function(source, resultLength){
        if (source == null) 
            return "";
        var result = this.nullValue(source);
        if (result.length < resultLength) {
            var i = result.length;
            while (i++ < resultLength) {
                result = "0" + result;
                if (result.length == resultLength) 
                    break;
            }
        }
        return result;
    },
    
    /**
     * 获取相同字符的个数；
     */
    getSameCharCount: function(str1, str2){
        return count;
    },
    
    filterHtml: function(input, length){
        var str = input.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll("<[^>]*>", "");
        str = str.replaceAll("[(/>)<]", "");
        return str;
    },
    
    /**
     * 判断字符串是否与指定字符串开始的
     */
    startsWith: function(source, prefix){
        return source.startsWith(prefix);
    },
    
    /**
     * 判断字符串是否与指定字符串结束的
     */
    endsWith: function(source, prefix){
        return source.endsWith(prefix);
    }
    
}))();

/**
 * {@link Ace.util.StringUtil#addZeroLeft}的简写方式
 * @member Ace addZeroLeft
 * @method */
Ace.addZeroLeft = Ace.util.StringUtil.addZeroLeft;

Ace.nullValue = Ace.util.StringUtil.nullValue;