<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<script>
var isSave = 0;
$(function(){
	$("#_back").click(function(){
		history.back();
	});
	$("#confirmBtn").click(function(){
		if(isSave ==0){
			$("#set_fm_form").submit();
			isSave++;
		}
	});
});

function setIsOpen(o){
	if(o.checked){
		$(o).next().val("0");
	}else{
		$(o).next().val("1");
	}
}
	
</script>
</head>
<body>
	<div id="mainContent_1" style="float:none;">
		<div id="searhDiv">
			<div class="library_right_warp" style="width:100%;"	>
				<div class="new_common_bg" style="width:100%;">
					<span>设置财务月</span>
				</div>
				<div class="library_right_warp_table">
					<div class="business_search_list_table" style="line-height:30px;">
					<input type="hidden" id="month" name="month" value="${currentMonth  }"/>
						<form id="set_fm_form" action="${pageContext.request.contextPath }/admin/financeMonth/saveFinanceMonth" method="post">
							<input type="hidden" id="year" name="year" value="${currentYear  }"/>
							<table class="t-list table" id="fm_tab_page">
								<tr>
									<th width="8%"><b>年份</b></th>
									<th width="6%"><b>月份</b></th>
									<th width="15%"><b>结束日期</b></th>
									<!-- <th width="15%"><b>工作日</b></th> -->
									<th width="5%"><b>开关</b></th>
								</tr>
								<c:if test="${fmSize != '0' }">
									<c:forEach var="fm" items="${financeMonths }" varStatus="status">
										<tr class="tb_tr_content" name="default">
											<td>${fm.year }</td>
											<c:if test="${(status.index+1) <= currentMonth }">
												<td>${fm.month }</td>
											    <td>${fm.endDateStr }</td>
												<!-- <td>${fm.isOpen }</td>-->
												<td><input disabled="disabled" type="checkbox" <c:if test="${fm.isOpen=='0' }"> checked="checked"</c:if>/></td>
											</c:if>
											<c:if test="${(status.index+1) > currentMonth }">
												<td>${fm.month }<input type="hidden" name="month" value="${status.index+1 }"/></td>
											    <td><input type="text" id="endDate_${status.index}" name="endDate" class="business_search_input w250  Wdate" date="{dateFmt:'yyyy-MM-dd',minDate:'${minDate }',maxDate:'${maxDate }'}" value="${fm.endDateStr }"/></td>
												<!-- <td><input type="text" name="workDay" class="business_search_input w250" value="${fm.isOpen }	"/></td>-->
												<td><input type="checkbox" id="" <c:if test="${fm.isOpen=='0' }"> checked="checked"</c:if> onclick="setIsOpen(this);"/><input type="hidden" name="isOpen" value="${fm.isOpen }"/></td>
											</c:if>
										</tr>
									</c:forEach>
								</c:if>
								<c:if test="${fmSize == '0' }">
									<c:forEach begin="1" end="12" varStatus="status" step="1">
										<tr class="tb_tr_content" name="default">
											<td>${currentYear}</td>
											<td>${status.index }<input type="hidden" name="month" value="${status.index }"/></td>
										    <td><input type="text" id="endDate_${status.index}" name="endDate" class="business_search_input w250  Wdate" date="{dateFmt:'yyyy-MM-dd',minDate:'${minDate }',maxDate:'${maxDate }'}"/></td>
											<!-- <td><input type="text" id="" name="workDay" class="business_search_input w250" /></td>-->
											<td><input type="checkbox" id="" onclick="setIsOpen(this);"/><input type="hidden" name="isOpen" value="1"/></td>
										</tr>
									</c:forEach>
								</c:if>
							</table>
						</form>
						<div class="new_jihui_menu new_jihui_menu_a" style="width:100%;">
							<p>
								<a id="confirmBtn" href="###" class="new_jihui_menu1" >保存</a> 
								<a id="_back" href="###" class="new_jihui_menu1" >返回</a> 
							<p>
							<div class="clear"></div>
						</div>
					</div>
					<div class="clear"></div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>