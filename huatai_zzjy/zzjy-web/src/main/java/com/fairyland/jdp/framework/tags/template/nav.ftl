<h3 class="page_main_right_h3">
	<a href="#">首页</a> &gt; 
	<#assign index = 0><#t> 
	<#list menus as menu><#t>
		<#if menu_has_next><#t>
			<a href="#">${RegexpUtil.replace(menu.name,"\\([0-9]+/[0-9]+\\)$","")}</a> &gt; 
		<#else><#t>
			${RegexpUtil.replace(menu.name,"\\([0-9]+/[0-9]+\\)$","")}
		</#if>
	</#list>