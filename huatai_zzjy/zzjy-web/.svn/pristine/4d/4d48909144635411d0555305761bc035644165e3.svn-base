<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/util.tld" prefix="u"%>
<%@ taglib uri="/WEB-INF/tlds/ace.tld" prefix="ace"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:if test="${dialogFlag == null}">
<html>
</c:if>
<c:if test="${dialogFlag != null}">
<html decorator="null">
</c:if>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>联系人列表</title>

<script type="text/javascript">
	$(function() {
		var dialogFlag = '${dialogFlag}';
		if(dialogFlag != null && dialogFlag != ''){
			$("#contactForm").ajaxForm(function(data){
				$("#contactForm").closest('.d-content').html(data);
			});
		}
		$("#contactId").val("${contact.contactId}");
		$("#searchBtn").click(function(){
			$("#contactForm").submit();
		});
		
		$(".business_search_list_table").each(function(){ 
			$(this).find("tr:odd").addClass("tab_a");
			$(this).find("tr:even").addClass("tab_b");
			$(this).find("tr").mouseover(function(){
			$(this).addClass("tab_c");
			}).mouseout(function(){
				$(this).removeClass("tab_c");
			  });
		});
		
		 //导出查询列表
		/* $("#exportBtn").click(function(){
			$("#contactForm").attr("action","${pageContext.request.contextPath}/contact/reportExport");
			$("#contactForm").submit();
			$("#contactForm").attr("action","${pageContext.request.contextPath}/contact/contactList");
		});  */
		
		$("#checkAll").click(function() {       
			  $('input[name="subBox"]').attr("checked",this.checked);            
		});           
		var $subBox = $("input[name='subBox']");           
		$subBox.click(function(){               
			  $("#checkAll").attr("checked",$subBox.length == $("input[name='subBox']:checked").length ? true : false);    
		});        
		$("#export").click(function(){
			
			
			  $.dialog({
				title : '请选择报表导出列',
				content: $("#columnDialog")[0],
				width:'400px',
				height:'200px',
				button : [{
					value : '导出',
					callback : function() {					
						var selectedBox = $("input[name^=subBox]").val();
					    if(selectedBox ==undefined||selectedBox == ""||selectedBox == null){
							alert("请至少选择一列导出！");
						}else{ 
							
							$("#boxVal").val(selectedBox);
							$("#contactForm").attr("action","${pageContext.request.contextPath}/contact/reportExport");
							$("#contactForm").submit();
							$("#contactForm").attr("action","${pageContext.request.contextPath}/contact/contactList");
					    } 
					}
				    },{
					    value : '关闭',
					    callback : function() {
						$(this).dialog("close");
					}
				}]
			 });//弹框
		});
				
	});
	
</script>

</head>

<body>
        <div id="columnDialog" style="display: none; ">
				       <!-- 导出报表列 -->
			<div id="mainContent_1" class="new_common_width">
					<div class="clear"></div>
					<div class="library_right_warp new_common_width">
					<div class="business_search_list_table">
						    <input id="checkAll" type="checkbox" checked="true" /> 全选
							<table border="0px;" width="100%">	
							   <tr>									 
								  <td><input name="subBox" type="checkbox" checked="true" value="0"/> 联系人类型</td> 
								  <td><input name="subBox" type="checkbox" checked="true" value="1"/> 联系人姓名</td> 
								  <td><input name="subBox" type="checkbox" checked="true" value="2"/> 名字</td> 
								  <td><input name="subBox" type="checkbox" checked="true" value="3"/> 姓氏</td> 
								  <td><input name="subBox" type="checkbox" checked="true" value="4"/> 性别</td> 
								  <td><input name="subBox" type="checkbox" checked="true" value="5"/> 手机号码</td> 
							   </tr>
							   <tr>
								  <td><input name="subBox" type="checkbox" checked="true" value="6"/> 出生日期	</td> 
								  <td><input name="subBox" type="checkbox" checked="true" value="7"/>  级别	</td> 					
								  <td><input name="subBox" type="checkbox" checked="true" value="8"/>  来源	</td> 
								  <td><input name="subBox" type="checkbox" checked="true" value="9"/>  电子邮箱</td> 
								  <td><input name="subBox" type="checkbox" checked="true" value="10"/> 家庭电话</td> 
								  <td><input name="subBox" type="checkbox" checked="true" value="11"/> 电话</td> 
							    </tr>
							    <tr>
								  <td><input name="subBox" type="checkbox" checked="true" value="12"/> 其他电话</td> 
								  <td><input name="subBox" type="checkbox" checked="true" value="13"/> 传真</td> 
								  <td><input name="subBox" type="checkbox" checked="true" value="14"/> 联系地址</td> 
								  <td><input name="subBox" type="checkbox" checked="true" value="15"/> 其他地址	</td> 					    
								  <td><input name="subBox" type="checkbox" checked="true" value="16"/> 备注</td> 
								  <td><input name="subBox" type="checkbox" checked="true" value="17"/> 创建人</td> 
								</tr>
								<tr>
								  <td><input name="subBox" type="checkbox" checked="true" value="18"/> 创建时间</td> 
								  <td><input name="subBox" type="checkbox" checked="true" value="19"/> 修改人</td> 
								  <td><input name="subBox" type="checkbox" checked="true" value="20"/> 修改时间</td> 
								  <td><input name="subBox" type="checkbox" checked="true" value="21"/> 所有人</td> 	
								  <td></td>   
								  <td></td>                      
							    </tr>
							    </table>
						 </div>
				</div>
	       </div>
	    </div>
		<form id="contactForm" action="${pageContext.request.contextPath}/contact/contactList" method="post">
		<input type="hidden" name="currentPage" value="${pager.currentPage}"/>
	    <input type="hidden" name="pageSize" value="${pager.pageSize}"/>
	    <input type="hidden" name="dialogFlag" value="${dialogFlag}"/>
	    <input type="hidden" name="boxVal" id="boxVal" value=""/>
	    	<c:if test="${dialogFlag == null }">
				<div class="business_title">
		              <a href="${pageContext.request.contextPath}/index" class="home">首页</a>
		              <img src="${pageContext.request.contextPath}/static/images/jiantou.png" />
		              <a href="${pageContext.request.contextPath}/contact/contactList">联系人</a>
	            </div>
	    	</c:if>
			<div class="col_lg_04">
			    <div class="new_common_bg"><span>联系人查询</span></div>
				<div class="business_search">
					<div class="business_search_left">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="18%" align="left">
										<span>联系人姓名：</span>
									</td>
									<td width="20%">
										<label for="select"></label> 
										<input type="text" name="contactName" value="${contact.contactName }" class="business_search_input w250"/>
									</td>
									<td width="10%"></td>
									<td width="18%" align="left">
										<span>联系人手机：</span>
									</td>
									<td width="20%">
									<label for="textfield2"></label> 
									<input type="text" name="mobile" value="${contact.mobile}" class="business_search_input w250"/>
									</td>
								</tr>	
								<tr>
									<td width="18%" align="left">
										<span>联系人类型：</span>
									</td>
									<td width="20%">
										<label for="select"></label> 
										<select name="contactType" id="contactType" value="" class="business_search_input w250">											 
									          <option value="">全部</option>
											  <option value="0">联系人</option>
											  <c:if  test="${isChannel ne '1' }">
											  	  <option value="1">客户</option>
											  </c:if>
											  <option value="2">业务伙伴</option>
										</select>	
										<script>
										    //客户经理 选择下拉框回显
											$("#contactType").val('${contactType}');
										</script>
									</td>
									<td width="10%"></td>
									<td width="18%" align="left"></td>
									<td width="20%"></td>
								</tr>										
							</table>
					</div> 
					<div class="searchBtu"><a id="searchBtn" class="submit">查询</a></div>
					<div class="clear"></div>
				</div>
			</div>
			<div class="clear"></div>
			<div class="col_lg_04">
				<div class="business_search_list_warp">
					<c:if test="${dialogFlag == null}">
					   <shiro:hasPermission name="contact.toAddContact">
					   <b class="new_business" style="float:left;" >
					     <a href="${pageContext.request.contextPath}/contact/toAddContact">新建联系人</a> 
					   </b>
					   </shiro:hasPermission>
					   <shiro:hasPermission name="contact.reportExport">
					   <b class="new_business1" style="float: left; padding-left: 4px;padding-bottom: 4px;"><a id="export">自定义报表</a></b>
					   </shiro:hasPermission>
					</c:if>					
					<div class="business_search_list_table">					  
						<table class="t-list table th_background" id="contactView" style="clear:both;" >
						       <tr>
						           <th width="15%">姓名</th>
						           <th width="15%">类型</th>
 						           <th width="16%">级别</th>
						           <th width="16%">手机</th>
						           <th width="16%">所有人</th>
						           <th >操作</th>
					           </tr>
					           <c:forEach var="contact" items="${pager.pageItems}">
					                <tr >
						                 <td>
						                     <c:choose>
						                           <c:when test="${contact.contactType==0}">
							                           <c:if test="${dialogFlag == null}">
							                              <a href="${pageContext.request.contextPath}/contact/contactDetail?contactId=${contact.contactId}">
							                           </c:if>
							                           		${contact.contactName}
							                           <c:if test="${dialogFlag == null}">
							                          	 	</a>
							                           </c:if>
						                           </c:when>
						                           <c:when test="${contact.contactType==1}">
							                           <c:if test="${dialogFlag == null}">
							                              <a href="${pageContext.request.contextPath}/account/accountDetail?accountId=${contact.contactId}&ajax=account">
							                           </c:if>
							                              ${contact.contactName}
							                           <c:if test="${dialogFlag == null}">
						                           			</a>
						                           		</c:if>
						                           </c:when>
						                           <c:when test="${contact.contactType==2}">
							                           <c:if test="${dialogFlag == null}">
							                              <a href="${pageContext.request.contextPath}/partner/partnerDetail?partnerId=${contact.contactId}&ajax=contact">
							                           </c:if>
							                              ${contact.contactName}
							                           <c:if test="${dialogFlag == null}">
							                           		</a>
	                                                   </c:if>
                                                   </c:when> 						                              
						                           <c:otherwise></c:otherwise>         
						                     </c:choose>								                 
						                 </td>
						                 <td><c:choose>
						                           <c:when test="${contact.contactType==0}">联系人</c:when>
						                           <c:when test="${contact.contactType==1}">
						                              <c:if test="${contact.isConvertFromContact==1}">客户（联系人）</c:if>
						                              <c:if test="${contact.isConvertFromContact==0}">客户</c:if>  
						                              <c:if test="${contact.isConvertFromContact==''}">客户</c:if> 
						                              <c:if test="${contact.isConvertFromContact==null}">客户</c:if> 							                           
						                           </c:when>
						                           <c:when test="${contact.contactType==2}">
						                              <c:if test="${contact.isConvertFromContact==1}">业务伙伴（联系人）</c:if>
						                              <c:if test="${contact.isConvertFromContact==0}">业务伙伴</c:if>  
						                              <c:if test="${contact.isConvertFromContact==''}">业务伙伴</c:if>
						                              <c:if test="${contact.isConvertFromContact==null}">业务伙伴</c:if>                                                             
						                           </c:when> 
						                           <c:otherwise>--</c:otherwise>         
						                     </c:choose>
						                 </td>
						                 <td>
						                    <c:choose>
						                     <c:when test="${contact.contactType==1}">
						                       <c:forEach var="vipLevel" items="${vipLevelList}">
									              <c:if test="${contact.vipLevel == vipLevel.code}">${vipLevel.name}</c:if>
								               </c:forEach>
								             </c:when>
								             <c:when test="${contact.contactType==2}">
								               <c:forEach var="title" items="${titleList}">
									              <c:if test="${contact.vipLevel == title.code}">${title.name}</c:if>
								               </c:forEach>								             
								             </c:when>
								            </c:choose>
						                 </td>
						                 <td>
						                 <c:choose>
						                    <c:when test="${contact.contactType eq '1' && contact.isEdit eq '0'}">
						                      <span style="color:red;">****</span>
						                    </c:when>
						                    <c:otherwise>
						                      ${contact.mobile}
						                    </c:otherwise>						                 
						                 </c:choose>
						                 </td>	
						                 <td>${contact.ownerName}</td>					
						                 <td class="library_table" >
						                    <span>&nbsp;</span>
						                 	<c:if test="${dialogFlag == null}">
						                 	    <c:choose>
						                 	       <c:when test="${contact.contactType==0}">
						                 	           <input type="hidden" name="itemType" value="1"/>
						                 	           <input type="hidden" name="itemId" value="${contact.contactId}"/>
						                 	           <input type="hidden" name="itemName" value="${contact.contactName}"/>
						                 	           <c:if test="${contact.isEdit eq '1'}">
						                 	            <shiro:hasPermission name="contact.toUpdateContact">
						                 	              <a href="${pageContext.request.contextPath}/contact/toUpdateContact?contactId=${contact.contactId}" ><img src="${pageContext.request.contextPath}/static/images/edit.png" width="20" height="20" title="编辑" /></a>&nbsp;						                 	           
						                                </shiro:hasPermission>
						                               </c:if>
						                               <c:if test="${isRoleNeed == ''||isRoleNeed == null}">
						                                <shiro:hasPermission name="partner.toBePartnerWithParameter">
						                                  <a href="${pageContext.request.contextPath}/partner/toBePartnerWithParameter?contactId=${contact.contactId}"><img src="${pageContext.request.contextPath}/static/images/partner.png" width="20" height="20" title="转为业务伙伴" /></a>&nbsp;&nbsp;	
						                                </shiro:hasPermission>
						                                <shiro:hasPermission name="account.toBeAccountWithParameter">
						                                  <a href="${pageContext.request.contextPath}/account/toBeAccountWithParameter?contactId=${contact.contactId}"><img src="${pageContext.request.contextPath}/static/images/account.png" width="20" height="20" title="转为客户" /></a>&nbsp;&nbsp;					                             
						                                </shiro:hasPermission>
						                               </c:if>
						                                <shiro:hasPermission name="task.taskModify">
						                                  <a href="${pageContext.request.contextPath}/task/taskModify?itemType=1&itemId=${contact.contactId}&itemName=${contact.contactName}"><img src="${pageContext.request.contextPath}/static/images/task.png" width="20" height="20" title="新建任务" /></a>						                           
						                 	            </shiro:hasPermission>
						                 	       </c:when>
						                           <c:when test="${contact.contactType==1}">
						                               <input type="hidden" name="itemType" value="2"/>
						                 	           <input type="hidden" name="itemId" value="${contact.contactId}"/>
						                 	           <input type="hidden" name="itemName" value="${contact.contactName}"/>
						                 	           <c:if test="${contact.isEdit eq '1'}">
						                 	           <shiro:hasPermission name="account.toUpdateAccount">
						                                 <a href="${pageContext.request.contextPath}/account/toUpdateAccount?accountId=${contact.contactId}"><img src="${pageContext.request.contextPath}/static/images/edit.png" width="20" height="20" title="编辑" /></a>&nbsp;&nbsp;
						                               </shiro:hasPermission>
						                               </c:if>
						                               <shiro:hasPermission name="task.taskModify">
						                                 <a href="${pageContext.request.contextPath }/task/taskModify?itemType=3&itemId=${contact.contactId}&itemName=${contact.contactName}"><img src="${pageContext.request.contextPath}/static/images/task.png" width="20" height="20" title="新建任务" /></a>
						                               </shiro:hasPermission>
						                           </c:when>
						                           <c:when test="${contact.contactType==2}">
						                               <input type="hidden" name="itemType" value="3"/>
						                 	           <input type="hidden" name="itemId" value="${contact.contactId}"/>
						                 	           <input type="hidden" name="itemName" value="${contact.contactName}"/>
						                 	           <c:if test="${contact.isEdit eq '1'}">
						                 	           <shiro:hasPermission name="partner.toUpdatePartner">
						                                 <a href="${pageContext.request.contextPath}/partner/toUpdatePartner?partnerId=${contact.contactId}"><img src="${pageContext.request.contextPath}/static/images/edit.png" width="20" height="20" title="编辑" /></a>&nbsp;&nbsp;
						                               </shiro:hasPermission>
						                               </c:if>
						                               <shiro:hasPermission name="task.taskModify">
						                                <a href="${pageContext.request.contextPath }/task/taskModify?itemType=7&itemId=${contact.contactId}&itemName=${contact.contactName}"><img src="${pageContext.request.contextPath}/static/images/task.png" width="20" height="20" title="新建任务" /></a>
						                               </shiro:hasPermission>						                             
						                           </c:when> 
						                           <c:otherwise></c:otherwise>      						                 	    
						                 	    </c:choose>
						                 	</c:if>
						                 	<c:if test="${dialogFlag != null}">
						                 		<input style="background:#eea904;padding:5px 15px;border:none;color:#fff;"  type="button" onclick="javascript:checkItem('${contact.contactId}','${contact.contactName}');" value="选择" />
						                 	</c:if>						                       						
					                      </td>
					                </tr>					           
					           </c:forEach>         
						</table>
						<input type="button" id="closeButton" class="close" style="display: none;"/>
						<ace:pager page="${pager}" formId="contactForm"/>
						<div class="clear"></div>
						
					</div>
				</div>
			</div>
		</form>
</body>
</html>