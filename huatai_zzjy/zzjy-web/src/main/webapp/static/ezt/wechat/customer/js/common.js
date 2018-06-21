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
      