Ace.util.Cookie = new (Ace.util.jClass((function(cookieProvider){

    return {
        jClass: 'Ace.util.Cookie',
        
        initialize: function(){
        },
        
        /**
         * 返回 Cookie 中指定属性的属性值
         */
        get: function(name){
            return cookieProvider.get(name);
        },
        
        /**
         * 设置 Cookie 中属性的属性值
         */
        put: function(name, value){
            cookieProvider.set(name, value);
        },
        
        /**
         * 删除 Cookie 中指定的属性
         */
        remove: function(name){
            cookieProvider.clear(name);
        }
        
    };
})(new Ace.state.CookieProvider())))();

Ace.Cookie = Ace.util.Cookie;
