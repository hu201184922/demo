<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="/WEB-INF/tlds/ace.tld" prefix="ace"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%
	request.setAttribute("webctx", request.getContextPath());
%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>联系人详情</title>
<script type="text/javascript">
   $(function(){
	   
		/* var oTabs=document.getElementById('tabs1');
		var aTabsNav=oTabs.querySelectorAll('.tabs-nav li');
		var aTabsCnt=oTabs.querySelectorAll('.tabs-content');
		
		for(var i=0;i<aTabsNav.length;i++){
			aTabsNav[i].index=i;
			aTabsNav[i].onclick=function(){
				for(var i=0;i<aTabsNav.length;i++){
					aTabsNav[i].className='';
					aTabsCnt[i].style.display='none';
				}
				this.className='active';
				aTabsCnt[this.index].style.display='block';
			};
		} */
		
	//新建任务
	$("#addTaskBtn").click(function(){
	    	$("#toAddTaskForm").submit();
	}); 
	$("#addTaskBtn2").click(function(){
    	$("#toAddTaskForm").submit();
    });
	   
	 //定义taskForm表单异步
		  $('#taskForm').ajaxForm(function(data){
				$('#pager').pager().setPostData(Ace.parseQuery($('#taskForm').serialize()));
				$('#pager').pager().setJSON(data);
			});
			
			$('#pager').pager('${pageContext.request.contextPath}/contact/taskSearchList?contactId=${contact.contactId}',10,$('#taskView').view().on("add",function(data){
				if(data.finishFlag=='1'){
					this.target.find("#taskFinishFlag").show();
				}else{
					this.target.find("#taskFinishFlag").hide();
				}
								
			}));
			$('#taskForm').submit();
					
			
			$("#delContactBtn").click(function(){
				var cid = $("#contactId").val();
				$.ajax({
					 type:'POST',
					 url:'${pageContext.request.contextPath}/contact/isAssociated',
					 data:{"contactId":cid},
					 success:function(msg){
						 if(msg=='1'){
							 alert("该联系人有关联关系，不可删除");
							 /* $.confirm("已关联客户,是否确定删除？", function() {
									$.post(request.getContextPath()+'/contact/delContact', {
										"contactId":cid,
										"isContacted":"1",
										ajaxType : true
									}, function(data) {
										    location.href="${pageContext.request.contextPath}/contact/contactList";
											$.msgbox({time: 2000,msg:"删除成功!",icon:"success"});
									});
									return;
							  });  */
						 }//if结束
						 if(msg=='0'){
							 $.confirm("是否确定删除？", function() {
							    $.post(request.getContextPath()+'/contact/delContact', {
									"contactId":cid,
									"isContacted":"0",									
									ajaxType : true
								}, function(data) {
									    location.href="${pageContext.request.contextPath}/contact/contactList";
										$.msgbox({time: 2000,msg:"删除成功!",icon:"success"});
							    });
							 }); 
						 }
					 }//success结束
				   }); //ajax结束
		    });//删除按钮结束
   });
   
   //删除异步表单中的某一列，异步，未调用
   function finishTask(taskId,taskStatus,tempEndTime){
		var s;
		var i=0;
		if($("input[name=finishDate]").length>1){
			if(taskStatus==1){
				$("#endTime").val(tempEndTime);
				s= $("input[name=finishDate]:first")[0];
				$("input[name=finishDate]:first").remove();
				i=1;
			}else{
				s=$("input[name=finishDate]:last")[0];
				$("input[name=finishDate]:last").remove();
				i=2;
			}
		}
		$.dialog({
			title : '请录入任务完成时间',
			content: $("#TaskFinishDialog")[0],
			width:'450px',
			height:'100px',
			button : [{
				value : '确定',
				callback : function() {
					var checkedIds =  $("#checkedId").val();
					$.ajax({
						url:"${pageContext.request.contextPath}/contact/finishTask",
						data:{
							"taskId":taskId,
							"taskStatus":taskStatus,
							"finishDate":$("#finishDate").val(),
							"proFlag":false
						},
						success:function(data){
							$.msgbox({time: 1000,msg: "操作成功！" ,icon:"success"});
							location.reload();
						},
						error:function(data){
							alert("数据加载失败");
						}
					});
				}
			},{
				value : '返回',
				callback : function() {
					if(i==1){
						$("input[name=finishDate]").before(s);
					}
					if(i==2){
						$("input[name=finishDate]").after(s);
					} 
					$(this).dialog("close");
				}
			}]
		});
			$(".d-close").remove();
	    }     
	 /* $.confirm("是否确定完成？", function() {
		   $.ajax({
			 type:'POST',
			 url:'${pageContext.request.contextPath}/contact/finishTask',
			 data:{"taskId":tid,"taskStatus":status},
			 success:function(msg){
				 $("#pager").pager().reload();
				 $.msgbox({time: 2000,msg:"已完成!",icon:"success"});
			 }
		   }); 
		   return;
		}); */
	
   
   function back(){
		window.history.back(-1);
	  };
	
</script>
<style>
*{margin:0;padding:0;}
body{font-size:14px;font-family:"Microsoft YaHei";}
ul,li{list-style:none;}
</style>

</head>
<body>

          <div class="business_title">
	          <a href="${pageContext.request.contextPath}/index" class="home">首页 </a>
	          <img src="${pageContext.request.contextPath}/static/images/jiantou.png" />
	          <a href="${pageContext.request.contextPath}/contact/contactList">联系人 </a>
	          <img src="${pageContext.request.contextPath}/static/images/jiantou.png" />
	          <a href="#">${contact.contactName}信息</a>
          </div>
          <div class="tabs" id="tabs1">
	          <ul class="tabs-nav" tabs="{selectedClass:'active'}">
                 <li class="active" tab=".tabs-content1"><a href="javascript:;"><span>活动管理</span></a></li>
                 <li tab=".tabs-content2"><a href="javascript:;"><span>基本信息</span></a></li>
              </ul>
              <div class="tabs-cnt">
    	         <div class="tabs-content1">	
    	             <div class="new_jihui_warp business_search_list_warp">
    	               <b class="new_business new_business_tag2">
    	                  <shiro:hasPermission name="task.taskModify">
    	                     <a id="addTaskBtn" class="new_jihui_menu1" href="###">新建任务</a>
    	                  </shiro:hasPermission>
    	                  <form id="toAddTaskForm" action="${pageContext.request.contextPath}/task/taskModify" method="post">					     
					        <input type="hidden" name="itemType" value="1"/>
					        <input type="hidden" name="itemId" value="${contact.contactId}"/>
					        <input type="hidden" name="itemName" value="${contact.contactName}"/>
				          </form> 
    	               </b>
    	               <div class="wrap_title_bg">
					        <em><a href="#"></a></em>
					        <span>任务</span>
				       </div>
				       <div class="new_jihui business_search_list_table t_list_top">
				          <form id="taskForm" action="${pageContext.request.contextPath}/contact/taskSearchList?contactId=${contact.contactId}" method="post">				               	           				       
    	                  <table class="t-list table t_list_top" width="100%" id="taskView">                                                     
                            <tr >
                              <th ><b>类型</b></th>
                              <th ><b>任务主题</b></th>
                              <th ><b>任务状态</b></th>
                              <th ><b>到期日期</b></th>
                              <th ><b>完成日期</b></th>
                              <th ><b>被分配人</b></th>
                              <th ><b>分配人</b></th>
                              <th ><b>操作</b></th>
                            </tr>
                            <tr class="tb_tr_content template" name="default">
						      <td ><input type="hidden" name="ral_taskId" value="{taskId}"/>{taskType:dict({0:'电话',1:'会议',2:'访约',3:'培训',4:'其他'})}</td>
						      <td >
						           <input type="hidden" name="ral_taskStatus" value="{taskStatus}"/>
						           <a href="${pageContext.request.contextPath}/task/taskDetail?taskId={taskId}&decorator=default">
						              {taskTitle}
						           </a>
						      </td>
						      <td >{taskStatus:dict({1:'逾期未完成',2:'进行中',3:'正常完成',4:'逾期完成'})}</td>
						      <td >{endTime}</td>
						      <td >{finishDateStr}</td>
						      <td >{executorName}</td>
						      <td >{createName}</td>
						      <td class="library_table">
						        <div id="taskFinishFlag"><a href="javascript:finishTask('{taskId}','{taskStatus}','{tempEndTime}');">
												<img src="${pageContext.request.contextPath}/static/images/taskFinish.png" width="20" height="20" title="完成" /></a></div>
						      
						      </td>
					        </tr>            
                          </table> 
                            <input type="button" id="closeButton" class="close" style="display: none;"/>
						    <div id="pager"></div>
						  </form>
						    <div class="clear"></div> 
                       </div>
                       <br>
                    </div>
                   <div class="new_jihui_menu">
			             <p style="text-align:center;">
			             	<c:if test="${ajax == null}">
			             	   <a href="${pageContext.request.contextPath}/contact/contactList" class="new_jihui_menu3">返回</a>
							</c:if>
							<c:if test="${ajax != null}">
							   <a href="javascript:history.back();" class="new_jihui_menu3">返回</a>
							</c:if>
			             </p>
			             <div class="clear"></div>
		            </div>
		             <div id="TaskFinishDialog" style="display: none; width: 400px;">
				       <!-- 完成任务 -->
					   <div class="new_jihui_warp" style="min-height:60px;">
						  <div class="business_search">
							<div class="business_search_left">
								<label>完成时间：</label>
								<input type="text" name="finishDate" id="finishDate" class="business_search_input w250 Wdate"
															date="{dateFmt:'yyyy-MM-dd HH:mm',maxDate:'%y-%M-%d %H:%m'}"/>
								<input type="hidden" id="endTime" name="endTime" value=""/>
								<input type="text" name="finishDate" id="finishDate" class="business_search_input w250 Wdate"
											date="{dateFmt:'yyyy-MM-dd HH:mm',minDate:'#F{$dp.$D(\'endTime\')}',maxDate:'%y-%M-%d %H:%m'}"/>
							</div>
							<div class="clear"></div>
						  </div>
					   </div>
		             </div>
    	         </div><!-- 第一个tab分页 -->
    	         <div class="tabs-content2">
    	           <div class="new_jihui_warp">
    	             <div class="new_common_bg"><span>联系人详细信息</span></div>
    	             <div class="new_jihui">
    	             <input type="hidden" name="contactId" id="contactId" value="${contact.contactId}"/>
    	             <table  width="100%"  cellspacing="0" cellpadding="0">
                            <tr padding="8px">
                              <td width="16%" align="right" valign="middle" ><b>联系人姓名：</b></td>
                              <td width="26%" align="left" valign="middle" >${contact.contactName}</td>
                              <td width="11%" ><b>联系人所有人：</b></td>
                              <td >${contact.ownerName}</td>
                            </tr>
                            <tr>
                              <td align="right" valign="middle"><b>名字：</b></td>
                              <td align="left" valign="middle">${contact.firstName}</td>
                              <td><b>性别：</b></td>
                              <td>
                                  <c:forEach var="sex" items="${sexList}">
									<c:if test="${contact.sex == sex.code}">${sex.name}</c:if>
								  </c:forEach> 
							  </td>
                            </tr>
                            <tr>
                              <td align="right" valign="middle"><b>姓氏：</b></td>
                              <td align="left" valign="middle">${contact.lastName}</td>
                              <td><b>出生日期：</b></td>
                              <td align="left" valign="middle">${contact.birthDateStr}</td>
                            </tr>
                            <tr>
                              <td align="right" valign="middle"><b>手机：</b></td>              
                              <td width="32%">${contact.mobile}</td>
                              <td width="9%" class="new_het_liulan"><b>电子邮箱：</b></td>
                              <td align="left" valign="middle">${contact.email}</td>
                            </tr>
                            <tr>
                              <td align="right" valign="middle"><b>职务：</b></td>              
                              <td width="32%">${contact.title}</td>
                              <td width="9%" class="new_het_liulan"><b>传真：</b></td>
                              <td align="left" valign="middle">${contact.fax}</td>
                            </tr>
                     </table> 
                     </div>
    	           </div>
                   <div class="new_jihui_warp">
    	             <div class="new_common_bg"><span>地址信息</span></div>
    	             <div class="new_jihui">                    
	                 <table  width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                              <td width="16%" align="right" valign="middle"><b>工作电话：</b></td>
                              <td width="26%" align="left" valign="middle" >${contact.phone}</td>
                              <td width="11%"></td>
                              <td></td>
                            </tr>
                            <tr>
                              <td width="16%" align="right" valign="middle"><b>其他电话：</b></td>
                              <td width="26%" align="left" valign="middle" >${contact.otherPhone}</td>
                              <td width="11%"></td>
                              <td></td>
                            </tr>
                            <tr>
                              <td width="16%" align="right" valign="middle"><b>联系地址：</b></td>
                              <td width="26%" align="left" valign="middle" >${contact.contactAddress}</td>
                              <td width="11%"></td>
                              <td></td>
                            </tr>
                            <tr>
                              <td width="16%" align="right" valign="middle"><b>其他地址：</b></td>
                              <td width="26%" align="left" valign="middle" >${contact.otherAddress}</td>
                              <td width="11%"></td>
                              <td></td>
                            </tr>
                            <tr>
                              <td align="right" valign="middle"><b>备注：</b></td>
                              <td colspan="3" align="left" valign="middle">${contact.memo}</td>
                            </tr>
                            <tr>
                              <td align="right" valign="middle"><b>创建人：</b></td>
                              <td align="left" valign="middle">${contact.createdByName} ${contact.createTimeStr}</td>
                              <td align="right" valign="middle"><b>上次修改人：</b></td>
                              <td align="left" valign="middle">
                                 <c:choose>
									 <c:when test="${contact.modifiedBy!=null}">
										${contact.modifiedByName} ${contact.modifyTimeStr}
									  </c:when>
									  <c:otherwise>
										--
									  </c:otherwise>
								  </c:choose>
							  </td>
                            </tr>
                     </table>
                     </div>
                     </div>     
                     <div class="new_jihui_menu">
			             <p style="text-align:center;">
			             	<c:if test="${ajax == null}">
			             	    <a id="addTaskBtn2" class="new_jihui_menu3">新建任务</a>
			             	    <a id="delContactBtn" class="new_jihui_menu3">删除</a>
			             	    <a href="${pageContext.request.contextPath}/contact/copyAndCreateContact?contactId=${contact.contactId}" class="new_jihui_menu3">复制</a>			             	    
			             		<a href="${pageContext.request.contextPath}/contact/contactList" class="new_jihui_menu3">返回</a>
							</c:if>
							<c:if test="${ajax != null}">
							    <a id="addTaskBtn2" class="new_jihui_menu3">新建任务</a>			             	    			             	    
							    <a id="delContactBtn" class="new_jihui_menu3">删除</a>
							    <a href="${pageContext.request.contextPath}/contact/copyAndCreateContact?contactId=${contact.contactId}" class="new_jihui_menu3">复制</a>
								<a href="javascript:history.back();" class="new_jihui_menu3">返回</a>
							</c:if>
			             </p>
			             <div class="clear"></div>
		            </div>                        
    	         </div><!-- 第二个tab分页    --> 	         
    	       </div>
    	  </div>       
</body>
</html>