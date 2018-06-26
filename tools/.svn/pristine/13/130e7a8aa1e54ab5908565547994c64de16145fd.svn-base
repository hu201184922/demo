<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags" %>  
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<nav class="navbar  " role="navigation" style="margin-top:30px">
<p class="text-center">Copyright &copy; 2004-2013 <a  href="http://www.fulan.com.cn">上海复深蓝信息技术有限公司</a></p>
</nav>
<script src="${ctx}/static/lib/jdp/jquery/jquery.i18n.properties-min-1.0.9.js" type="text/javascript"></script>
<script>
$(document).ready(function(){
    		//国际化加载属性文件
    		var language="<sp:message code="i18n.locale"/>";
    		  jQuery.i18n.properties({
                  name:'message',
                  path:'${ctx}/static/js/i18n/',
                  mode:'map',
                  language:language,
                  callback: function() {
                  	//alert($.i18n.prop('alert.success'));
                  }
              });
});
</script>
