Ace.util.jClass(Ace.util.Observable, {

	jClass : 'Ace.awt.Progress',

	initialize : function($super, target, options) {
		this.options = Ace.merge(options || {}, {
			theme : 'default'
		});
	},

	start : function() {
		// 设置焦点防止多次提交
		// try{
		// var _4focus=document.getElementById("ispLog4focus");
		// if(_4focus!=null)_4focus.focus();
		// }catch(e){}

		// var w = 258;
		// var speeds = [
		// [[20,120],[50,500],[70,1000],[80,100],[95,10000]],
		// [[10,100],[40,200],[65,800],[70,100],[95,10000]],
		// [[10,800],[60,500],[70,200],[80,100],[95,10000]],
		// [[50,110],[60,500],[70,200],[80,100],[95,10000]]
		// ];
		this.progress = $(Ace.awt.Progress.TEMPLATE);
		this.lock = $.lock().after(this.progress);
		// lock.css('top',document.documentElement.scrollTop);
		// $('body').addClass('masked');
		// $(window).scroll(function(e){
		// e = e || window.event;
		// if (e && e.preventDefault) {
		// e.preventDefault();
		// e.stopPropagation();
		// } else {
		// e.returnvalue = false;
		// return false;
		// }
		// });
		var height = window['innerHeight']
				|| document.documentElement.clientHeight;
		this.progress.css({
			position : 'fixed',
			left : (this.lock.width() / 2) - (this.progress.width() / 2),
			top : (height / 2) - (this.progress.height() / 2)
		});
		// this.progress.find('.progress1_red').width(0);
		// var i = 0;
		// var bs = speeds[Ace.random(4)];
		// var prFun = (function(progress){
		// return function(){
		// progress.find('.progress1_red').animate({width:(w/100)*bs[i][0]},bs[i][1],function(){
		// if(++i < bs.length)
		// prFun();
		// });
		// };
		// })(this.progress);
		// prFun();
		return this;
	},

	stop : function() {
		zhis = this;
		this.lock.fadeOut(400, function() {
			zhis.lock.remove();
		});
		this.progress.fadeOut(400, function() {
			zhis.progress.remove();
		});
		// $('.progress1_red1').remove();
		// zhis.progress.fadeOut(function(){
		// zhis.progress.remove();
		// });
		$(window).unbind('scroll');
	}

});
Ace
		.apply(
				Ace.awt.Progress,
				{},
				{
					TEMPLATE : '<div style="width:441px;height:161px;z-index:5000;margin:0 auto;">'
							// +'<div align="center">'
							// +'<p style="height:18px; color:#666;
							// text-align:center; line-height:18px;margin-top:
							// 10px;">'
							// +'请稍等，正在为您读取进度中...'
							// +'</p>'
							// +'</div>'
							// +'<div align="center" style="margin-top: 15px">'
							+ '<div class="progress1_red1">'
							// +'<img
							// src="/isp/static/images/loading_33_03.png">'
							+ '<img style="display:block;margin:0px auto" src="'+window.contextPath+'/static/images/gundong.gif">'
							+ '</div>' + '</div>'

				});