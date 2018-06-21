var request = (function() {


	window.contextPath = null;

	return {
		getContextPath : function() {
			if(!contextPath) {
//				var pathName = document.location.pathname;
//				var index = pathName.substr(1).indexOf("/");
//				window.contextPath = pathName.substr(0, index + 1);
				window.contextPath='';
//				window.contextPath = '/';
			}
			return window.contextPath;
		},
	
		getQueryString : function(){
			var url = document.location.href;
			return url.indexOf("#")==-1?url.substr(url.indexOf("?")+1):url.substr(url.indexOf("?")+1,url.indexOf("#"));
		}
	};
})();
jQuery.extend({
	
	includePath : /[a-zA-Z]:$/.test(request.getContextPath())?'../':window.location.protocol+'//' + window.location.host + request.getContextPath() + '/',//WebContent/
	
	/**
	 * js 加载 Css 及 JavaScript
	 */
	include : function(file) {
		var files = typeof file == "string" ? [ file ] : file;
		for ( var i = 0; i < files.length; i++) {
			var name = files[i].replace(/^\s|\s$/g, "");
			var att = name.split('.');
			var ext = att[att.length - 1].toLowerCase();
			var isCSS = ext == "css";
			var tag = isCSS ? "link" : "script";
			var attr = isCSS ? " type='text/css' rel='stylesheet' ":" language='javascript' type='text/javascript' ";
			var link = (isCSS ? "href" : "src") + "='" + $.includePath + name +"'";
			if ($(tag + "[" + link + "]").length == 0)
				document.write("<" + tag + attr + link + " charset=\"utf-8\"" + (tag == "link" ? "/>" : ("></" + tag + ">")));
		}
	}

});


jQuery.include(['static/js/common/My97DatePicker/WdatePicker.js']);

jQuery.include(['static/js/common/artDialog/skins/blue.css','static/js/common/artDialog/jquery.artDialog.js','static/js/common/artDialog/artDialog.plugins.js']);


jQuery.include(['static/js/common/zTree/css/zTreeStyle/zTreeStyle.css','static/js/common/zTree/jquery.ztree.core-3.1.min.js','static/js/common/zTree/jquery.ztree.excheck-3.1.min.js','static/js/common/zTree/jquery.ztree.exedit-3.1.min.js']);

jQuery.include(['static/js/common/tip/jquery.tipTip.min.js','static/js/common/tip/tip.css']);

jQuery.include(['static/js/common/swfupload/default.css','static/js/common/swfupload/swfupload.js','static/js/common/swfupload/swfupload.queue.js','static/js/common/swfupload/fileprogress.js','static/js/common/swfupload/handlers.js']);

//jQuery.include(['static/js/dsb/SelectTree.js']);

jQuery.include(['static/js/common/msgbox/msgbox.css','static/js/common/msgbox/msgbox.js']);

jQuery.include(['static/js/common/formvalidator/formValidator-4.1.1.js','static/js/common/formvalidator/formValidatorRegex.js']);

jQuery.include(['static/js/common/formvalidator/themes/Default/js/theme.js']);//,'static/js/common/formvalidator/themes/SolidBox/style/style.css'

jQuery.include(['static/lib/jdp/bootstrap/3.1.1/css/bootstrap.min.css','static/lib/jdp/bootstrap/3.1.1/js/bootstrap.min.js']);

jQuery.include(['static/lib/jdp/jquery-validation/1.11.1/validate.css','static/lib/jdp/jquery-validation/1.11.1/jquery.validate.min.js']);
jQuery.include(['static/lib/jdp/ztree/3.5/css/zTreeStyle/zTreeStyle.css','static/lib/jdp/ztree/3.5/js/jquery.ztree.all-3.5.min.js']);
jQuery.include(['static/lib/jdp/date/css/datetimepicker.css','static/lib/jdp/date/js/bootstrap-datetimepicker.js']);
jQuery.include(['static/lib/jdp/fancyBox/2.1.5/jquery.mousewheel.pack.js?v=3.1.3']);
jQuery.include(['static/lib/jdp/fancyBox/2.1.5/jquery.fancybox.css?v=2.1.5','static/lib/jdp/fancyBox/2.1.5/jquery.fancybox.js?v=2.1.5']);
jQuery.include(['static/lib/jdp/fancyBox/2.1.5/helpers/jquery.fancybox-buttons.css?v=1.0.5','static/lib/jdp/fancyBox/2.1.5/helpers/jquery.fancybox-buttons.js?v=1.0.5']);
jQuery.include(['static/lib/jdp/fancyBox/2.1.5/helpers/jquery.fancybox-thumbs.css?v=1.0.7','static/lib/jdp/fancyBox/2.1.5/helpers/jquery.fancybox-thumbs.js?v=1.0.7']);
jQuery.include(['static/lib/jdp/fancyBox/2.1.5/helpers/jquery.fancybox-media.js?v=1.0.6']);
jQuery.include(['static/lib/jdp/fancyBox/2.1.5/helpers/jquery.fancybox-buttons.js?v=1.0.5']);
jQuery.include(['static/css/default.css']);
