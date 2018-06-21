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

jQuery.include('static/js/ace/Ace.js');
jQuery.include('static/js/ace/String.js');
jQuery.include('static/js/ace/Array.js');
jQuery.include('static/js/ace/Date.js');
jQuery.include('static/js/ace/Function.js');

/***********************************************************\
 *			Ace.util									*
 *[描    述] JS 框架	公共包									*
 \***********************************************************/
jQuery.include('static/js/ace/Ace.util.jClass.js');
jQuery.include('static/js/ace/Ace.util.Object.js');
jQuery.include('static/js/ace/Ace.util.Class.js'); 
jQuery.include('static/js/ace/Ace.util.Observable.js'); 
jQuery.include('static/js/ace/Ace.data.Property.js'); 
jQuery.include('static/js/ace/Ace.util.Collection.js'); 
jQuery.include('static/js/ace/Ace.util.Map.js'); 
jQuery.include('static/js/ace/Ace.util.StringUtil.js'); 
jQuery.include('static/js/ace/Ace.util.ObjectUtil.js'); 
jQuery.include('static/js/ace/Ace.util.MathUtil.js'); 
jQuery.include('static/js/ace/Ace.util.DateUtil.js'); 
jQuery.include('static/js/ace/Ace.util.JSON.js');
jQuery.include('static/js/ace/Ace.util.Format.js');
jQuery.include('static/js/ace/Ace.util.Template.js');
jQuery.include('static/js/ace/Ace.util.Cache.js');
jQuery.include('static/js/ace/Ace.state.Provider.js');
jQuery.include('static/js/ace/Ace.state.CookieProvider.js');
jQuery.include('static/js/ace/Ace.util.Cookie.js'); 
jQuery.include('static/js/ace/Ace.data.SortTypes.js');
jQuery.include('static/js/ace/Ace.data.Field.js');
jQuery.include('static/js/ace/Ace.Ajax.js');
jQuery.include('static/js/ace/Ace.awt.Field.js');
jQuery.include('static/js/ace/Ace.awt.Element.js');
jQuery.include('static/js/ace/Ace.awt.View.js');
jQuery.include('static/js/ace/Ace.awt.Pager.js');
jQuery.include('static/js/ace/Ace.awt.Progress.js');
jQuery.include('static/js/ace/Ace.awt.Box.js');
jQuery.include('static/js/ace/Ace.awt.ConfirmBox.js');


/*  jQuery  */
jQuery.include(['static/js/common/loadMask/jquery.loadmask.js','static/js/common/loadMask/jquery.loadmask.css']);

jQuery.include('static/js/jquery/jquery.form.js');

jQuery.include(['static/js/common/My97DatePicker/WdatePicker.js']);

jQuery.include(['static/js/common/artDialog/skins/blue.css','static/js/common/artDialog/jquery.artDialog.js','static/js/common/artDialog/artDialog.plugins.js']);


jQuery.include(['static/js/common/zTree/css/zTreeStyle/zTreeStyle.css','static/js/common/zTree/jquery.ztree.core-3.1.min.js','static/js/common/zTree/jquery.ztree.excheck-3.1.min.js','static/js/common/zTree/jquery.ztree.exedit-3.1.min.js']);

jQuery.include(['static/js/common/tip/jquery.tipTip.min.js','static/js/common/tip/tip.css']);

jQuery.include(['static/js/common/swfupload/default.css','static/js/common/swfupload/swfupload.js','static/js/common/swfupload/swfupload.queue.js','static/js/common/swfupload/fileprogress.js','static/js/common/swfupload/handlers.js']);

//jQuery.include(['static/js/dsb/SelectTree.js']);

jQuery.include(['static/js/common/msgbox/msgbox.css','static/js/common/msgbox/msgbox.js']);

jQuery.include('static/js/jquery/jquery.util.js');

jQuery.include(['static/js/isp.js']);
jQuery.include(['static/js/common/formvalidator/formValidator-4.1.1.js','static/js/common/formvalidator/formValidatorRegex.js']);

jQuery.include(['static/js/common/formvalidator/themes/Default/js/theme.js']);//,'static/js/common/formvalidator/themes/SolidBox/style/style.css'

//jQuery.include(['static/js/file/swfobject.js','static/js/file/flexpaper_flash.js']);
jQuery.include(['static/js/jquery/jquery.qtip.min.js','static/css/jquery.qtip.min.css']);

//jQuery.include(['static/js/common/thickbox/thickbox-compressed.js','static/js/common/thickbox/thickbox.css']);
//jQuery.include(['static/js/common/applelike/styles.css','static/js/common/applelike/script.js']);

//jQuery.include(['static/js/common/modernizr/modernizr.custom.js','static/js/common/modernizr/waypoints.js']);

jQuery.include(['static/js/common/mobiscroll/css/mobiscroll-2.0.1.full.min.css']);
jQuery.include(['static/js/common/mobiscroll/dev/js/mobiscroll.core.js']);
jQuery.include(['static/js/common/mobiscroll/dev/js/mobiscroll.datetime.js']);
jQuery.include(['static/js/common/mobiscroll/dev/js/mobiscroll.select.js']);
//jQuery.include(['static/js/common/mobiscroll/dev/js/mobiscroll.zepto.js']);
jQuery.include(['static/js/common/mobiscroll/dev/js/mobiscroll.ios.js']);
jQuery.include(['static/js/common/mobiscroll/dev/js/mobiscroll.jqm.js']);
jQuery.include(['static/js/common/mobiscroll/dev/js/mobiscroll.android.js']);
jQuery.include(['static/js/common/mobiscroll/dev/js/mobiscroll.android-ics.js']);
jQuery.include(['static/js/validateJs.js']);