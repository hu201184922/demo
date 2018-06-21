(function($, doc) {
	$.init();
	
	$.ready(function() {
		
		/* 日历控件 日期  by: 李建 */
		$('.mui-select-date').each(function(i, btn) {
			btn.addEventListener('tap', function() {
				var optionsJson = this.getAttribute('data-options') || '{}';
				var options = JSON.parse(optionsJson);
				var picker = new $.DtPicker(options);
				picker.show(function(rs) {
					btn.innerText = rs.text;
					picker.dispose();
				});
			}, false);
		});
		
		
	});
})(mui, document);


$.fn.extend({
	getCalendar : function(nDate){
		var nDate = nDate || new Date();
		this.init = function(){
			var html = '';
			var year = nDate.getFullYear(), 
				month = nDate.getMonth() + 1,
				wday = this.getWeekDay(year, month),
				days = this.getMonthDay(year, month),
				plan = this.plan(year, month);
				
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
				$('.td', this).eq(wday + i - 1).append('<strong>'+ i +'</strong>');
			};
			
			/*for(var i = 0; i < plan.length; i++){
				$('.td', this).eq(wday + parseInt(plan[i]) - 1).addClass('active');
			};*/
			
			$('.td', this).eq(wday + parseInt(nDate.getDate()) - 1).addClass('current');
			
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
			//需获取数据
			return [3, 5, 10, 17, 22];
		};
		this.init();
	}
});
      