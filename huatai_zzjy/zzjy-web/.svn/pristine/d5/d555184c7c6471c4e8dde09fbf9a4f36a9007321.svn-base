(function($, doc) {
	$.init();
	
	$.ready(function() {
		
		/* 日历控件 日期  by: 李建 */
		$('.mui-select-date').each(function(i, btn) {
			btn.addEventListener('tap', function() {
				var optionsJson = this.getAttribute('data-options') || '{}';
				var options = JSON.parse(optionsJson);
				var callback = this.getAttribute('callback');
				var picker = new $.DtPicker(options);
				picker.show(function(rs) {
					btn.innerText = rs.text;
					if(callback!=null){
						window[callback](this);
					}
					picker.dispose();
				});
			}, false);
		});
		
		
	});
})(mui, document);

//将日期格式化成字符串
Date.prototype.format = function(format)
{
var o = {
"M+" : this.getMonth()+1, //month
"d+" : this.getDate(),    //day
"h+" : this.getHours(),   //hour
"m+" : this.getMinutes(), //minute
"s+" : this.getSeconds(), //second
"q+" : Math.floor((this.getMonth()+3)/3),  //quarter
"S" : this.getMilliseconds() //millisecond
}
if(/(y+)/.test(format)) format=format.replace(RegExp.$1,
(this.getFullYear()+"").substr(4 - RegExp.$1.length));
for(var k in o)if(new RegExp("("+ k +")").test(format))
format = format.replace(RegExp.$1,
RegExp.$1.length==1 ? o[k] :
("00"+ o[k]).substr((""+ o[k]).length));
return format;
}

$.fn.extend({
	getCalendar : function(nDate,activeDay){
		var nDate = nDate || new Date();//当前页面显示的日期
	//选择日期为本月今天时候的回显
	    var y1=nDate.getFullYear();
	    var y2=new Date().getFullYear();
		var m1=nDate.getMonth() + 1;
		var m2=new Date().getMonth() + 1;
		if(m1 == m2 && y1 == y2){
			nDate = new Date();
		}
		
		this.init = function(){	
			var html = '';
			var year = nDate.getFullYear(), 
				month = nDate.getMonth() + 1,
				wday = this.getWeekDay(year, month),
				days = this.getMonthDay(year, month),
				plan = this.plan(year, month);
				sDay = activeDay;   //已签到日期数组
				nowDate = nDate.format('yyyy-MM');//格式化当前页面日期年月
			for(var i=0; i < this.getMonthWeek(year, month); i++){
				html += '<li class="mui-table-row">\
					<div class="mui-table-cell td"></div>\
					<div class="mui-table-cell td"></div>\
					<div class="mui-table-cell td"></div>\
					<div class="mui-table-cell td"></div>\
					<div class="mui-table-cell td"></div>\
					<div class="mui-table-cell td"></div>\
					<div class="mui-table-cell td"></div>\
				</li>';
			};
			$(this).append(html);
			
			for(var i = 1; i <= this.getMonthDay(year, month); i++){
				$('.td', this).eq(wday + i - 1).append('<strong>'+ i +'</strong><p>'+ this.getYinli(year, month, i) + '</p>');
			};
			
			for(var i = 0; i < plan.length; i++){
				$('.td', this).eq(wday + parseInt(plan[i]) - 1).addClass('active');
			};
			
			$('.td', this).eq(wday + parseInt(nDate.getDate()) - 1).addClass('current');
			
			
			//回显已签到日期
			var dayList = new Array();
			var dateList = new Array();
			 for(var i=0;i<sDay.length;i++){
                 var day = sDay[i].substring(0,7);
				 dayList.push(day);
			 }
			for(var i=0;i<sDay.length;i++){
				if(dayList[i] == nowDate){
					var s = sDay[i].substring(8);
					dateList.push(s);
				}
				
			}	
			for(var i = 0; i < sDay.length; i++){	
				$('.td', this).eq(wday + parseInt(dateList[i]) - 1).addClass('check-on').removeClass('active');
			};
			
			//$('.td', this).eq(wday + parseInt(f.getDate()) - 1).addClass('check-on').removeClass('active');
			
		};
		//获取月第一天是周几
		this.getWeekDay = function(y, m){
			var dd = new Date(y, m - 1, 1);
			return dd.getDay();
		};
		//获取月的天数
		this.getMonthDay = function(y, m){
			var dd = new Date(y, m, 0);
			return dd.getDate();
		};
		//获取月的周数
		this.getMonthWeek = function(y, m){
			var dd = new Date(y, m - 1, 1);
			return Math.ceil((this.getMonthDay(y, m) - 7 + dd.getDay()) / 7) + 1;
		};
		//获取指定日期的阴历
		this.getYinli = function(y, m, d){
			return GetLunarDay(y, m, d);
		};
		//获取有设定计划的天
		this.plan = function(y, m){
			return [];
		};
		this.init();
	}
});
      