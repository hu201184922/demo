<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html decorator="null">
<style>
.shijianTit{ background:#0076C0;height:34px;padding-left:5px;width:100%;}
.shijianTit span{float:left;height:34px; line-height:34px;font-size:14px; font-family:"微软雅黑";color:#fff;width:75px; text-align:center; cursor:pointer;}
.shijianTit span.hotSpan{ background:#fff; color:#008bff;margin-top:1px;margin-bottom:1px;height:32px; line-height:33px;}

.shijianCon{height:370px;border-top:none; overflow:hidden;padding:0 15px;margin:2 10px;}
.shijianTs{height:28px;border:1px solid #ffc64c; background:#fffbe3; line-height:28px; text-align:center;margin-top:15px;color:#666;}
.shijianTs strong{color:#ff8a00;font-weight:bold;}
.shijianTs  span{display:none;}

.shijianTable{margin-top:10px;height:270px; }
.shijianTable table{border-left:1px solid #e1e1e1;border-top:1px solid #e1e1e1; width:100%; display:none; }

.shijianTable th{height:30px; background:#fafafa;border-bottom:1px solid #e1e1e1;border-right:1px solid #e1e1e1; text-align:center;}
.shijianTable td{height:30px;border-bottom:1px solid #e1e1e1;border-right:1px solid #e1e1e1; background:#fff; text-align:center;}
.shijianTable tr:hover{ background:#fafafa;}
.shijianTable td img{display:inline;float:none;}
</style>
<script type="text/javascript">
$(document).ready(function(){
	/*日历切换部分*/
	$(".shijianTit span").mouseenter(function(){
		$(this).siblings().removeClass("hotSpan");	
		$(this).addClass("hotSpan");
		
		$(".shijianTs span").hide();
		$(".shijianTs span").eq($(this).index()).show();
		
		$(".shijianTable table").hide();
		$(".shijianTable table").eq($(this).index()).show();
		
	});
});
</script>
<body>
    <div class="col-xs-6" style="width:462px;height:415px;border:solid #8AB5DA 1px;padding:0px;">
        <div class="shijianTit">
            	<span class="hotSpan" id="taskTypeNum11">计划(${calendarPlansNum})</span>
                <span id="taskTypeNum00" <c:if test="${currentUser.isType=='01' }">style="display:none"</c:if>>纪念日(${memorialDaysNum})</span>
         </div>
        <!--  <ul id="myTabs" class="nav nav-tabs" role="tablist">
             <li role="presentation">
                 <a href="#plan" id="plan-tab" role="tab" data-toggle="tab" aria-controls="home" aria-expanded="false">计划</a>
             </li>
             <li role="presentation" class="active">
                 <a href="#memory" role="tab" id="memory-tab" data-toggle="tab" aria-controls="profile" aria-expanded="true">纪念日</a>
             </li>
         </ul> -->
            <div class="shijianCon">
            	<div class="shijianTs">
					<span style="display:block;">${dataStr } 星期${weekNum } 您在这天有 <strong id="taskTypeNum1">${calendarPlansNum}</strong> 个计划需要处理。
					<c:if test="${isPlanMore !=null}"><a href="${pageContext.request.contextPath}/calendar/calendarList?subType=2">查看更多</a></c:if>
					</span> 
                    <span>${dataStr } 星期${weekNum } 您在这天有 <strong>${memorialDaysNum }</strong> 个纪念日需要处理。
                    <c:if test="${isSpecialMore !=null}"><a href="${pageContext.request.contextPath}/calendar/calendarList?subType=2">查看更多</a></c:if>
                    </span>
				</div>
            	<div class="shijianTable">
            		<table cellpadding="0" cellspacing="0" style="display:table;">
                           <tr>
                               <th>类型</th>
                               <th>计划名</th>
                               <th>子类型|任务</th>
                               <th>客户</th>
                               <th>开始时间</th>
                               <th>结束时间</th>
                           </tr>
                           <c:forEach var="plan" items="${calendarPlans}">
                            <tr >
                                <td style="font-size:12px;"><c:if test="${plan.planType eq '1'}">客户</c:if><c:if test="${plan.planType eq '2'}">个人</c:if></td>	                            
                                <td style="font-size:12px;">
                                  <c:if test="${plan.planType eq '1'}">
                                    <a href="${pageContext.request.contextPath}/customer/plan/edit?cusId=${plan.cusId}&cusPlanId=${plan.planId}&taskId=${plan.taskId}&from=3">${plan.planName}</a>
                                  </c:if>
                                  <c:if test="${plan.planType eq '2'}">
                                    <a href="${pageContext.request.contextPath}/plan/personplan/updatePersonPlanPager?personPlanId=${plan.planId}">${plan.planName}</a>
                                  </c:if>
                                </td>
                                <td style="font-size:12px;">${plan.taskStage}</td>
                                <td style="font-size:12px;">${plan.cusName}</td>
                                <td style="font-size:12px;">${plan.beginTime}</td>
                                <td style="font-size:12px;">${plan.endTime}</td>
                            </tr>
                           </c:forEach>
                     </table>
                    <table cellpadding="0" cellspacing="0">
                         <tr>
                             <th>纪念日</th>
                             <th>客户</th>
                             <th>类型</th>
                             <th>日期</th>
                         </tr>
                       <c:forEach var="memorialDay" items="${memorialDays}">
	                         <tr>
	                             <td>${memorialDay.memorialDayType}</td>
	                             <td><a href="${pageContext.request.contextPath}/customer/infor-edit?id=${memorialDay.customerId}">${memorialDay.name}</a></td>
	                             <td>${memorialDay.customerType}</td>
	                             <td>${memorialDay.memorialDay}</td>
	                         </tr>
	                   </c:forEach>
                    </table>
                    
                </div>
            </div>
         <%--< div id="myTabContent" class="tab-content">
             <div role="tabpanel" class="tab-pane fade" id="plan" aria-labelledby="plan-tab">
                 <div class="table-responsive">
                     <table class="table table-hover table-striped">
                         <thead>
                             <tr>
                                 <th>类型</th>
                                 <th>计划名</th>
                                 <th>子类型|任务</th>
                                 <th>客户</th>
                                 <th>开始时间</th>
                                 <th>结束时间</th>
                             </tr>
                         </thead>
                         <tbody>
                             <c:forEach var="plan" items="${calendarPlans}">
	                             <tr >
	                                 <td><c:if test="${plan.planType eq '1'}">客户</c:if><c:if test="${plan.planType eq '2'}">个人</c:if></td>	                            
	                                 <td>${plan.planName}</td>
	                                 <td>${plan.taskStage}</td>
	                                 <td>${plan.cusName}</td>
	                                 <td style="font-size: 12px;width:8%;padding:8px;">${plan.beginTime}</td>
	                                 <td style="font-size: 12px;width:8%;padding:8px;">${plan.endTime}</td>
	                             </tr>
                             </c:forEach>
                         </tbody>
                     </table>
                 </div>
             </div>
             <div role="tabpanel" class="tab-pane fade active in" id="memory" aria-labelledby="memory-tab">
                 <table class="table table-hover table-striped">
                     <thead>
                         <tr>
                             <th>纪念日</th>
                             <th>客户</th>
                             <th>类型</th>
                             <th>日期</th>
                         </tr>
                     </thead>
                     <tbody>
                       <c:forEach var="memorialDay" items="${memorialDays}">
	                         <tr>
	                             <td>${memorialDay.memorialDayType}</td>
	                             <td>${memorialDay.name}</td>
	                             <td>${memorialDay.customerType}</td>
	                             <td>${memorialDay.memorialDay}</td>
	                         </tr>
	                   </c:forEach>
                     </tbody>
                 </table>
             </div>
         </div> --%>
    </div>


	
    
    

 </body>
</html>