define(function(){//微信分享
	var options={
		url:'res/js/core/jweixin-1.0.0.js',
		isDefault:true,
		info:{
			debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
			appId:null, //公众号的唯一标识
			timestamp:null, //生成签名的时间戳
			nonceStr:null, //生成签名的随机串
			signature:null, //签名
			jsApiList: ['checkJsApi','onMenuShareTimeline','onMenuShareAppMessage','onMenuShareQQ','onMenuShareWeibo'] //必填，需要使用的JS接口列表，所有JS接口列表见附录2
		},
		shareText:{
			title: null, //分享标题
			desc: null, //分享描述
			href: null, //分享链接
			imgUrl: null, //分享图标
			type: 'link', //分享类型,music、video或link，不填默认为link
			dataUrl: '', //如果type是music或video，则要提供数据链接，默认为空
			success:function(){}, //用户确认分享后执行的回调函数
			cancel:function(){} //用户取消分享后执行的回调函数
		},
		callback:function(){}  //微信组件加载成功执行的回调函数
	};
	var wx=null;
	var panel={
		init:function(ops){
			options = $.extend(true,options,ops);
			if(window.GlOBAL.TOOL.isUseWeixin()){
				require([PATH+options.url],function(mode){	
					wx=mode;
					wx.config({
						debug: options.info.debug,
						appId: options.info.appId, 
						timestamp: options.info.timestamp, 
						nonceStr: options.info.nonceStr, 
						signature: options.info.signature,
						jsApiList: options.info.jsApiList 
					});
					wx.ready(function(){
						wx.checkJsApi({
							jsApiList: options.info.jsApiList, // 需要检测的JS接口列表，所有JS接口列表见附录2,
							success: function(res) {
								options.isDefault?panel.onBridgeReady(options.shareText):options.callback();
							}
						});
					});
					wx.error(function(res){
						console.log(res.errMsg);
					});	
				});
			};	
		},
		onBridgeReady:function(param){
			panel.onMenuShareTimeline(param);
			panel.onMenuShareAppMessage(param);
			panel.onMenuShareAppMessage(param);
			panel.onMenuShareQQ(param);
		},
		onMenuShareTimeline:function(data){//分享到朋友圈
			var param=$.extend(true,options.shareText,data.shareText);
			alert(param.imgUrl);
			wx.onMenuShareTimeline({
				title: param.desc, 
				link: param.href, 
				imgUrl: param.imgUrl, 
				success: function () { 
					param.success();
				},
				cancel: function () { 
					param.cancel();
				}
			});	
		},
		onMenuShareAppMessage:function(data){//分享给朋友
			var param=$.extend(true,options.shareText,data.shareText);
			wx.onMenuShareAppMessage({
				title: param.title, 
				desc: param.desc,
				link: param.href, 
				imgUrl: param.imgUrl, 
				type: param.imgUrl, 
				dataUrl: param.dataUrl, 
				success: function () { 
					param.success();
				},
				cancel: function () { 
					param.cancel();
				}
			});	
		},
		onMenuShareQQ:function(data){//分享到QQ
			var param=$.extend(true,options.shareText,data.shareText);
			wx.onMenuShareQQ({
				title: param.title, 
				desc: param.desc, 
				link: param.href, 
				imgUrl: param.imgUrl, 
				success: function () { 
					param.success();
				},
				cancel: function () { 
					param.cancel();
				}
			});	
		},
		onMenuShareWeibo:function(data){//分享到腾讯微博
			var param=$.extend(true,options.shareText,data.shareText);
			wx.onMenuShareWeibo({
				title: param.title,
				desc: param.desc, 
				link: param.href, 
				imgUrl: param.imgUrl, 
				success: function () { 
					param.success();
				},
				cancel: function () { 
					param.cancel();
				}
			});	
		}
	};
	return panel;	
});