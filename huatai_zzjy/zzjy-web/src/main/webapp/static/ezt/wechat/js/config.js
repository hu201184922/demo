require.config({
　　 baseUrl:PATH+'res/js/',
　　 paths:{
		jquery:'core/jquery-1.7.2.min',
		jqueryMobile:'core/jquery.mobile.custom.min',
		share:'common/ouyeel.share',
		validate:'common/ouyeel.validate',
		common:'common/ouyeel.common',
		touchSwipe:'core/jquery.touchSwipe.min',
		iscroll:'core/jquery.iscroll',
		base:'common/ouyeel.base'
	},
	shim:{
		jqueryMobile:{
			deps:['jquery']
		},
		touchSwipe:{
			deps:['jquery','jqueryMobile']
		},
		base:{
			deps:['jquery','jqueryMobile']
		},
		common:{
			deps:['jquery','jqueryMobile']
		},
		iscroll:{
			deps:['jquery','jqueryMobile']
		},
		validate:{
			deps:['jquery']
		}
	}
});

function _loadMode(status,callback){
	if(status){
		var div=document.createElement("div")
		div.className='ui-load-mode';
		div.innerHTML='<i class="fa ui-load-ico">&#xe922</i>';
		document.body.appendChild(div);
		callback();
	}else{
		callback();
	};
};

_loadMode(true,function(){
	require(['base','common'],function(){
		var S={
			init:function(){

				window.onresize=this.setInitFontSize;
				this.setInitFontSize();	
				$('.ui-load-mode').remove();
			},
			setInitFontSize:function(){
				document.getElementsByTagName("html")[0].style.fontSize=Math.min(document.documentElement.clientWidth,960)/20+"px";	
			}
		};	
		S.init();
	});
});