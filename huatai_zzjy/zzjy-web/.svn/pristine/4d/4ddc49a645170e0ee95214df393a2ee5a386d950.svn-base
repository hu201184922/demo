mui.init();
mui.ready(function(){
	var WEIXINAPI = {
		options:{
			ctx:ctx,
			readOnly:readOnly,		//当前页面是否为只读
			shareContent:{			//分享的内容
				title:'',
				desc:'',
				link:'',
				imageUrl:''
			}
		},
		init:function(){
			var self = this,url = location.href;
		  
			mui.ajax(self.options.ctx+'/ezt/weixin/jsconfig',{
				data:{url:url+''},
				type:'post',
				dataType:'json',
				success:self.ready,
				error:function(xhr,type,errorThrown){
					//异常处理
					mui.toast(type);
				}
			});
		
			if(!self.options.readOnly){
				//发送贺卡
				self.event();
			}
		},
		event:function(){
			var self = this;
			mui('div').on('tap','#btnSubmit',function(){
				//获取贺卡的内容
				var card = JSON.parse(document.querySelector('body').getAttribute('data-card'));
			
				var cusName = card.cusName,
					id=card.id,
					content = card.content,
					cardName = card.cardName,
					imageUrl = card.imageUrl,
					cardType = card.cardType,
					shareContent = self.options.shareContent,
					title = '',
					server = self.server();
				
				//设置贺卡的内容
				shareContent.title = cardName;
				shareContent.desc = content;
				shareContent.link = server.origin+self.options.ctx+'/ezt/birthday/card?id='+id;
				shareContent.imageUrl = server.origin+self.options.ctx+imageUrl;
				
				//发送
				self.shareAppMessage();
			});
		},
		server:function(){	//获取服务地址的信息
			var url = document.location.href,
				pathname = document.location.pathname,
				origin = document.location.origin;
			return {url:url,pathname:pathname,origin:origin};
		},
		ready:function(data){	//配置微信API
			
			if(!data) return ;
			
			var self = WEIXINAPI;
			wx.config({
				debug:false,
				appId:data.appid,
				timestamp:data.timestamp,
				nonceStr:data.noncestr,
				signature:data.signature,
				jsApiList:['onMenuShareAppMessage','showOptionMenu','hideAllNonBaseMenuItem'],
			});
			wx.ready(function(){
				//页面需要初始化就调用的
				
				if(self.options.readOnly && self.options.readOnly === 'true'){
					
					//如果是只读页面，即客户查看贺卡
					wx.hideAllNonBaseMenuItem();
				}
			});
			wx.error(function(res){
				cosnole.log(res);
			});
		},
		shareAppMessage:function(){	//分享

			//获取分享的内容
			var shareContent = this.options.shareContent;
			
			wx.onMenuShareAppMessage({
			      title: shareContent.title,
			      desc: shareContent.desc,
			      link: shareContent.link,
			      imgUrl: shareContent.imageUrl,
			      trigger: function (res) {
			        // 不要尝试在trigger中使用ajax异步请求修改本次分享的内容，因为客户端分享操作是一个同步操作，这时候使用ajax的回包会还没有返回
			      
			      },
			      success: function (res) {
			    	  mui.toast('分享成功');
			      },
			      cancel: function (res) {
			         
			      },
			      fail: function (res) {
			       
			      }
		    });

		    //显示菜单
			wx.showOptionMenu();
		},
	};
	
	WEIXINAPI.init();
});
