<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<script>
	$(function(){
	});
	function setFM(type){
		window.location.href = "${pageContext.request.contextPath}/admin/financeMonth/toSetFinanceMonth?type="+type;
	}
	
	function search(o){
		$(o).val();
		$("#year").val($(o).val());
		$("#fm_search_form").submit();
	}
</script>
</head>
<body>
	<div id="mainContent_1" style="float:none;">
		<div id="searhDiv">
			<div class="library_right_warp" style="width:100%;"	>
				<div class="new_common_bg" style="width:100%;">
					<span>财务月查询</span>
				</div>
				<div class="library_right_warp_table">
					<div class="business_search_list_table" style="line-height:30px;">
						<em style="float:Left;">查询年份：</em>
						<select id="seachYear" class="business_search_input" style="float:Left;height:28px;width:150px;" onchange="search(this);">
							<c:forEach items="${years }" var="year">
								<option value="${year }" <c:if test="${year==currentYear }">selected="selected"</c:if>>${year }</option>
							</c:forEach>
						</select>
						<input style="line-height:23px;padding:0 10px;margin-left:10px;" type="button" value="设置本年财务日期" onclick="setFM(1);"/>
						<input style="line-height:23px;padding:0 10px;" type="button" value="设置下年财务日期" onclick="setFM(2);"/>
						<form id="fm_search_form" action="${pageContext.request.contextPath }/admin/financeMonth/index" method="post">
							<input type="hidden" id="year" name="year" value="${currentYear  }"/>
							<table class="t-list table" id="product_tab_page">
								<tr>
									<th width="8%"><b>年份</b></th>
									<th width="6%"><b>月份</b></th>
									<th width="15%"><b>起始日期</b></th>
									<th width="15%"><b>结束日期</b></th>
<!-- 									<th width="15%"><b>工作日</b></th>
 -->								<th width="5%"><b>开关</b></th>
								</tr>
								<c:forEach var="fm" items="${financeMonths }" varStatus="status">
									<tr class="tb_tr_content" name="default">
										<td>${fm.year }</td>
										<td>${fm.month }</td>
									    <td>${fm.startDateStr }</td>
									    <td>${fm.endDateStr }</td>
										<!-- <td>${fm.isOpen }</td> -->
										<td><input type="checkbox" id="" name="isOpen" <c:if test="${fm.isOpen=='0' }"> checked="checked"</c:if> disabled="disabled"/></td>
									</tr>
								</c:forEach>
							</table>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>