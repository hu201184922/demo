<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/util.tld" prefix="u"%>
<html>
<head>
<title>日志统计列表</title>
<script type="text/javascript">
$(function(){
	$("#runLogPager").pager(request.getContextPath()+'/admin/logqry/runLogQuery',10,$('#runLogView').view().on('add',function(temp){
	}));
	$("#seachRunLog").click(function(){
		$('#seachRunLogFrom').submit();
	});
	$("#seachRunLogFrom").ajaxForm(function(data){
		$('#runLogPager').pager().options.postData={};
		$('#runLogPager').pager().setPostData(Ace.parseQuery($('#seachRunLogFrom').serialize()));
		$('#runLogPager').pager().setJSON(data);
	});
	$("#seachRunLog").click();
	$("#exportRunLog").click(function(){
		window.location.href = "${pageContext.request.contextPath }/admin/logqry/runLogReport?"+$('#seachRunLogFrom').serialize();
	});
});
</script>
</head>
<body>
	<div class="tagContentList">
		<div class="business_title">Run日志统计</div>
		
		<div class="col_lg_04" style="width:1203px">
		<form id="seachRunLogFrom" action="${pageContext.request.contextPath }/admin/logqry/runLogQuery" method="post">
			<div style="margin-top: 20px">
				<span style="margin-left: 200px;font-size: small;">DEAL_TAB：</span><select style="width: 120px;height: 30px;" id="dealTab" name="dealTab">
																	<option value="">全部</option>
																	<c:forEach items="${dealTabs }" var="dealTab">
																		<option value="${dealTab }">${dealTab }</option>
																	</c:forEach>
																</select>
				<span style="margin-left: 30px;">开始时间：</span><input type="text" class="sousuoInput sousuoInputWidth2" style="margin-left:0;" name="beginDate" id="beginDate" date="{dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}'}"/>
				<span style="margin-left: 30px;">结束时间：</span><input type="text" class="sousuoInput sousuoInputWidth2" style="margin-left:0;" name="endDate" id="endDate" date="{dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'beginDate\')}'}"/>
				 
				<input id="seachRunLog" style="margin-left: 60px;width: 60px;height: 30px;background-color: #0076c0;border: none;color: #fff;" type="button" value="查询">
				<input id="exportRunLog" style="margin-left: 30px;width: 60px;height: 30px;background-color: #0076c0;border: none;color: #fff;" type="button" value="导出">
			</div>
		</form>
			<div class="business_search_list_warp" style="width:95%">
				<table cellspacing="0" cellpadding="0" class="t-list table" id="runLogView">
					<tr>
						<th>DEAL_ACTION</th>
						<th>DEAL_TAB</th>
						<th>ID</th>
						<th>NAME</th>
						<th>OWNER</th>
						<th>DEAL_TIME</th>
					</tr>
					<tr class="template" name="default">
						<td>{dealAction}</td>
						<td>{dealTab}</td>
						<td>{id}</td>
						<td>{name}</td>
						<td>{owner}</td>
						<td class="view-field" name="dealTime" mapping="dealTime" dataType="date" format="yyyy-MM-dd hh:mm:ss"></td>
					</tr>
				</table>
				<div id="runLogPager"></div>
			</div>
		</div>
		
	</div>
</body>
</html>