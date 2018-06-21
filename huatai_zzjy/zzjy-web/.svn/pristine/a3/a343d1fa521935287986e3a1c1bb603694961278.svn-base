window.GlOBAL = window.GlOBAL || {};
window.GlOBAL.API={
	getShareUrl:function(param,isAddLoading,callback){//获取分享地址
		$.ajax({
			url:'service.do',
			data : {'SERVERID' : '180000046','STATE':param.type},
			type:"post",
			dataType:"json",
			beforeSend:function(){
				if(isAddLoading){
					$.Bstatus({
						html:'加载中...'
					});
				};
			},
			success:function(data){
				$('.ui-status-container').remove();
				if(data!=null||data!=undefined){
					callback(data.RETURN.TUIJIANURL);	
				}else{
					$.Bstatus({
						html:'获取分享地址失败',
						status:false,
						time:500
					});
				};
			},
			error:function(error){
				$('.ui-status-container').remove();
				$.Bstatus({
					html:'服务器出错啦',
					status:false,
					time:500
				});
			 }
		});		
	},
	setShareContent:function(param,type,callback){//native端设置分享内容
		if(arguments.length==0){
			$.Bstatus({
				html:'参数传递错误',
				status:false,
				time:500
			});
		}else{
			var url='';
			if(param!=null){
				var name={
					1 : 'shareqqfriends',//分享QQ好友
					2 : 'shareqzone',//分享QQ空间
					3 : 'sharewxfriends',//分享微信好友
					4 : 'sharewxtimeline',//分享微信朋友圈	
					5 : 'ismobileapp'//是否APP打开	
				}[type],
				url=name+':imgUrl='+param.imgUrl+',title='+encodeURI(param.title)+',content='+encodeURI(param.content)+',url='+param.url+','+callback;
			}else{
				url=name+':imgUrl="",title="",content="",url="",'+callback;
			};
			var elem = document.createElement("iframe");
			elem.setAttribute("src",url);
			elem.setAttribute("style","display:none");
			elem.setAttribute("width","0px");
			elem.setAttribute("height","0px");
			elem.setAttribute("frameborder","0px");
			document.body.appendChild(elem);
			elem.parentNode.removeChild(elem);
		};
	},
	getWeixinDate:function(callback){//获取公众号数据
		$.ajax({
			url:'get/wxcode.do',
			type:"post",
			data:encodeURIComponent(location.href),
			dataType:"json",
			success:function(data){
				
			},
			error: function(data){
				//error
				/*$.Bstatus({
					html:'数据加载失败,请重试',
					status:false,
					time:500
				});	*/
				
				//true
				var data={
					param:{
						appId: 'wx5296f7011ca92045', // 必填，公众号的唯一标识
						timestamp: new Date().getTime(), // 必填，生成签名的时间戳
						nonceStr: 'ff37a10a-8490-4546-9d0b-2a01101a2db2', // 必填，生成签名的随机串
						signature: '9bd5b4cd8f0dcc97f28d5cf48328c6c92d7ce423', // 必填，签名，见附录1
					}
				};
				if(data.param!=undefined){
					callback(data.param);
				}else{
					$.Bstatus({
						html:'无数据',
						status:false,
						time:500
					});
				};
			}	
		});			
	}	
};
window.GlOBAL.TOOL={
	getBrowser:function(){//判断移动设备版本
		return {
			versions:function(){
				var u = navigator.userAgent, app = navigator.appVersion;
				return {
					trident: u.indexOf('Trident') > -1, //IE内核
					presto: u.indexOf('Presto') > -1, //opera内核
					webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
					gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核
					mobile: !!u.match(/AppleWebKit.*Mobile.*/)||!!u.match(/AppleWebKit/), //是否为移动终端
					ios: !!u.match(/(i[^;]+;(U;)? CPU.+Mac OS X)/), //ios终端
					android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或者uc浏览器
					iPhone: u.indexOf('iPhone') > -1 || u.indexOf('Mac') > -1, //是否为iPhone或者QQHD浏览器
					iPod: u.indexOf('iPod') > -1 , //是否iPod
					iPad: u.indexOf('iPad') > -1, //是否iPad
					webApp: u.indexOf('Safari') == -1 //是否web应该程序，没有头部与底部
				};
			}(),
			language:(navigator.browserLanguage || navigator.language).toLowerCase()
		};
	},
	isUseWeixin:function(){//是否微信中访问
		return /micromessenger/.test(navigator.userAgent.toLowerCase())?true:false;
	},
	loadModePic:function(index,pic,callback){//批量缓存图片
		if(pic!=null&&pic!=undefined&&pic.length>0){
			var _this=this;
			$.BCacheLoadPic({
				src:pic[index],
				callback:function(src,w,h,s){
					index++;
					if(index==pic.length){
						callback();
					}else{
						_this.loadModePic(index,pic,callback);
					};	
				}
			});
		}else{
			callback();
		};
	},
	getSource:function(c){
		var imgPath= PATH+'res/images/',sourceArr=[];
		for (var i=0; i<c.length; i++) {
			sourceArr[i] = imgPath + c[i];
		};
		return sourceArr;
	},
	deParam: function (str) { // url to json
		str = str || location.search.substr(1);
		var pairs = str.split("&"),
			obj = {},
			pair,
			i;
		for (i in pairs) {
			if (pairs[i] === "") continue;
			pair = pairs[i].split("=");
			obj[decodeURIComponent(pair[0])] = decodeURIComponent(pair[1]);
		};
		return obj;
	},
	searchStr : {//操作查询url字符串
		get : location.search,//获得
		getUrl : function(){
			return encodeURI(decodeURI(this.get));
		},
		setItem: function (key, value) {//设置 参数(键,键值)
		    value = encodeURIComponent(value);

			if(this.get == ""){
				this.get = "?" + key + "=" + value;
			}else{
				this.get = this.get + "&" + key + "=" + value;
			}
		},
		getItem : function(key){//获取 参数(键),返回(指定key对应的value)
			var str = this.get.substring(this.get.indexOf("?") + 1);
			str = '{"'+ str.replace(/=/g,'":"').replace(/&/g,'","') +'"}';
			str = JSON.parse(str);
			return decodeURIComponent(decodeURI(str[key]));
		},
		removeItem : function(key){//移除 参数(键)
			var str = this.get.substring(this.get.indexOf("?") + 1);
			str = '{"'+ str.replace(/=/g,'":"').replace(/&/g,'","') +'"}';
			str = JSON.parse(str);
			delete str[key];
			str = JSON.stringify(str);
			str = "?" + str.substring(2,str.length-2);
			str = str.replace(/":"/g,'=').replace(/","/g,'&');
			this.get = str;
		},
		clear : function(){//清空
			this.get = "";
		}
	},
	cardsValid : function(pan){//验证银行卡号
		var sum = 0;
		for (var i = (pan.length - 1),j = 1; i >= 0; i--,j++) {
			 j % 2 == 1 && (sum += parseInt(pan[i]));//倒数奇数位
			 j % 2 == 0 && (sum += parseInt(pan[i] * 2 / 10 +  pan[i] * 2 % 10));//倒数偶数位
		};
		return sum % 10 == 0;
	},
	preventDefault:function(e){
		e.preventDefault();
	},
	addMoveListener:function(){
		document.body.addEventListener('touchmove',this.preventDefault,false);
	},
	removeMoveListener:function(){
		document.body.removeEventListener('touchmove',this.preventDefault,false);
	},
	getFormatTellphone:function(s){
		s=s.toString();
		return s.substr(0,3)+' '+s.substr(3,4)+' '+s.substr(7,4);
	},
	getFormatNumber:function(p){//格式化价格
		var price=p.toString();
			np=price.split('.'),
			arr=np[0],
			length=arr.length,
			n=parseInt(length/3),
			j=length%3,
			s=t=arr.substr(0,j),
			str=arr.substr(j,length);
		
		for(var i=0;i<n;i++){
			s+=','+str.substr(i*3,3);
		};
		if(t==''){
			s=s.substr(1,s.length);	
		};
		return s+(np[1]==undefined?'':'.'+np[1]);
	},
	fileReader:function(options){//读取本地图片
		var defaults={
			file:null,
			startCallback:function(){},//读取开始时触发
			progressCallback:function(){},//读取中
			successCallback:function(){},//文件读取成功完成时触发
			bortCallback:function(){},//读取完成触发，无论成功或失败
			errorCallback:function(){}//中断时触发
		};
		var options= $.extend(true,defaults,options);
		if(options.file!=null){
			var reader = new FileReader();
			reader.readAsDataURL(options.file);
			reader.onloadstart=function(e){//读取开始时触发
				options.startCallback();
			};
			reader.onprogress=function(e){//读取中
				options.progressCallback();
			};
			reader.onload=function(e){//文件读取成功完成时触发
				options.successCallback(this.result,options.file);
			};
			reader.onloadend=function(e){//读取完成触发，无论成功或失败
				options.completeCallback(this.result,options.file);
			};	
			reader.onabort=function(e){//中断时触发
				options.bortCallback();
			};	
			reader.onerror=function(e){//出错时触发
				options.errorCallback();
			};	
		}else{
			$.Bstatus({
				html:'参数错误',
				status:false,
				time:500
			});
		};
	}
};	

function publicShareCallback(param){//native回调
	var param=JSON.parse(param);
	param.callback(param);	
};



/*if(window.GlOBAL.TOOL.isUseWeixin()){//是否微信中打开
	window.GlOBAL.API.getWeixinDate(function(data){//获取公众号数据
		require(['share'],function(mode){
			mode.init({
				info:data,
				shareText:$.API.getShareData()
			});	
		});
	});
};*/



		