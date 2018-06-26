<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>     
<%
	request.setAttribute("webctx", request.getContextPath());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>新建联系人</title>
<script type="text/javascript">
	
    var cansave = 0;
	function back(){
		window.history.back(-1);
	};
	
	$(function(){
		
		
		$("#update").click(function(){
			if(checkForm()){
			 if(cansave==0){
			   $("#contactForm").attr("action","${pageContext.request.contextPath }/contact/updateRedirectList");
	    	   $("#contactForm").submit();
	    	   cansave++;
			 }
			}
	    });	
	    $("#onlySave").click(function(){
	    	if(checkForm()){
	    	  if(cansave==0){//alert(cansave);
	    		$("#contactForm").attr("action","${pageContext.request.contextPath }/contact/saveRedirectList");
		    	$("#contactForm").submit();
		    	cansave++;
	    	  }
	    	}	    	
	    });
	    $("#saveAndCreateTask").click(function(){
	    	if(checkForm()){
	    	   if(cansave==0){
	    	    $("#contactForm").attr("action","${pageContext.request.contextPath }/contact/saveRedirectTask");
	    	    $("#contactForm").submit();
	    	    cansave++;
	    	   }
	    	}
	    });
	    	  
	  //编辑时的数据回显
	  if('${pageStatus}'=='update'){
		  $("#sex").val("${contact.sex}"); 
	  }
	   
	  
	});
	
	//保存前的检查
    function checkForm(){
    	var flag = true ;
    	$("#contactNameMsg").empty();
    	$("#firstNameMsg").empty();
    	$("#lastNameMsg").empty();
    	$("#titleMsg").empty();
    	$("#contactAddressMsg").empty();    	
    	$("#otherAddressMsg").empty();
    	$("#memoMsg").empty();
    	$("#birthDateMsg").empty();
    	$("#mobileMsg").empty();
		var contactName = $("#contactName").val();	
		var mobile = $("#mobile").val();		
		var mobileVal = stringUtils.trim($("#mobile").val());
		var firstNameVal = stringUtils.trim($("#firstName").val());
		var lastNameVal = stringUtils.trim($("#lastName").val());	
		var titleVal = stringUtils.trim($("#title").val());	
		var contactAddressVal = stringUtils.trim($("#contactAddress").val());	
		var otherAddressVal = stringUtils.trim($("#otherAddress").val());
		var memoVal = stringUtils.trim($("#memo").val());
		var birthDateVal = stringUtils.trim($("#birthDate").val());
		
		/* if(!stringUtils.isEmpty(birthDateVal)){
			var date = new Date();
			var d1 = new Date(birthDateVal.replace(/\-/g, "\/"));  
			var d2 = new Date(date.replace(/\-/g, "\/")); 
			if(d1>d2){
				$("#birthDateMsg").empty().append("生日大于今日");
			}
		}	 */	
		
		if (stringUtils.getStrLength(firstNameVal) > 40) {
			$("#firstName").focus();
			$("#firstNameMsg").empty().append("长度过长!");
			flag = false;
		}
		if (stringUtils.getStrLength(lastNameVal) > 40) {
			$("#lastName").focus();
			$("#lastNameMsg").empty().append("长度过长!");
			flag = false;
		}		
		if (stringUtils.getStrLength(titleVal) > 128) {
			$("#title").focus();
			$("#titleMsg").empty().append("长度过长!");
			flag = false;
		}
		if (stringUtils.getStrLength(contactAddressVal) > 100) {
			$("#contactAddress").focus();
			$("#contactAddressMsg").empty().append("长度过长!");
			flag = false;
		}
		if (stringUtils.getStrLength(otherAddressVal) > 100) {
			$("#otherAddress").focus();
			$("#otherAddressMsg").empty().append("长度过长!");
			flag = false;
		}
		if (stringUtils.getStrLength(memoVal) > 1000) {
			$("#memo").focus();
			$("#memoMsg").empty().append("长度过长!");
			flag = false;
		}
		
		//空校验
		if($.trim(contactName)==""){
			$("#contactName").focus();
			$("#contactNameMsg").html("必填项!");
			flag = false;
		}
		if (stringUtils.getStrLength(contactName) > 50) {
			$("#contactName").focus();
			$("#contactNameMsg").empty().append("长度过长!");
			flag = false;
		}
		if(!stringUtils.isEmpty(mobileVal)){
			if(!validateUtils.isMobil(mobileVal)){
				flag = false;
				$("#mobileMsg").html("格式错误!");
			}else{
				$("#mobile").val(mobileVal);
			}
		}else{
			$("#mobileMsg").html("必填项!");
			flag = false;
		}
		
		//正则校验
		$("#phoneMsg").html("");
		$("#emailMsg").html("");
		$("#otherPhoneMsg").html("");
		$("#faxMsg").html("");
		var phoneVal = stringUtils.trim($("#phone").val());
		if(!stringUtils.isEmpty(phoneVal)){
			if(!validateUtils.isTel(phoneVal)){
				flag = false;
				$("#phoneMsg").html("格式错误!");
			}
		}
		if (stringUtils.getStrLength(phoneVal) > 20) {
			$("#phone").focus();
			$("#phoneMsg").empty().append("长度过长!");
			flag = false;
		}
		var emailVal = stringUtils.trim($("#email").val());
		if(!stringUtils.isEmpty(emailVal)){
			if(!validateUtils.isEmail(emailVal)){
				flag = false;
				$("#emailMsg").html("格式错误!");
			}
		}
		if (stringUtils.getStrLength(emailVal) > 50) {
			$("#email").focus();
			$("#emailMsg").empty().append("长度过长!");
			flag = false;
		}
		var otherPhoneVal = stringUtils.trim($("#otherPhone").val());
		if(!stringUtils.isEmpty(otherPhoneVal)){
			if(!validateUtils.isTel(otherPhoneVal)){
				flag = false;
				$("#otherPhoneMsg").html("格式错误!");
			}
		}	
		if (stringUtils.getStrLength(otherPhoneVal) > 20) {
			$("#otherPhone").focus();
			$("#otherPhoneMsg").empty().append("长度过长!");
			flag = false;
		}
		var faxVal = stringUtils.trim($("#fax").val());
		if(!stringUtils.isEmpty(faxVal)){
			if(!validateUtils.isTel(faxVal)){
				flag = false;
				$("#faxMsg").html("格式错误!");
			}
		}
		if (stringUtils.getStrLength(faxVal) > 20) {
			$("#fax").focus();
			$("#faxMsg").empty().append("长度过长!");
			flag = false;
		}
		
		return flag; 
	}
</script>
</head>
<body>

    <form id="contactForm" action="" method="post">       
        <div class="business_title">
           <a href="${pageContext.request.contextPath}/index" class="home">首页</a>
		   <img src="${pageContext.request.contextPath}/static/images/jiantou.png" />
		   <a href="${pageContext.request.contextPath}/contact/contactList">我的联系人</a>
		   <img src="${pageContext.request.contextPath}/static/images/jiantou.png" />
		   <a href="#">
		      <c:choose>
				  <c:when test="${pageStatus == 'update'}">
					修改联系人
				  </c:when>
				  <c:otherwise>新增联系人</c:otherwise>
			  </c:choose>		   
		   </a>
        </div>
        <div class="new_jihui_warp tabs-content_color">
           <div class="new_common_bg"><span>联系人信息</span><span class="style1">（注意：<font>*</font>为必填项）</span></div>
           <div class="new_jihui">
           <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="17%" align="right" valign="middle"><b><span>*</span>联系人姓名：</b></td>
                <td width="20%" align="left" valign="middle" >
                   <input type="hidden" name = "contactId" id = "contactId" value="${contact.contactId}"/>
                   <input type="text" name="contactName" id="contactName" value="${contact.contactName}" class="business_search_input w250"/>
                </td>
                <td width="10%"><span id="contactNameMsg"></span></td>
                <td width="14%" align="right" valign="middle"><b>联系人所有人：</b></td>
                <td width="20%" align="left" valign="middle">
                   <shiro:user>
							   <input type="hidden" name="owner" id="owner" value="<shiro:principal property="account" />">
							   <shiro:principal property="userName" />
				   </shiro:user>
                </td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td align="right" valign="middle"><b>名字：</b></td>
                <td align="left" valign="middle"><input type="text" name="firstName" id="firstName" value="${contact.firstName}" class="business_search_input w250"/></td>
                <td><span id="firstNameMsg"></span></td>
                <td align="right" valign="middle"> <b>性别：</b></td>
                <td>
                    <label for="sex"></label>
					<select name="sex" id="sex" class="business_search_input w250">
					    <c:forEach var="sex" items="${sexList}">
						   <option value="${sex.code}">${sex.name}</option>
					    </c:forEach>
					</select>
                </td>
                <td></td>
              </tr>
              <tr>
                <td align="right" valign="middle"><b>姓氏：</b></td>
                <td align="left" valign="middle"><input type="text" name="lastName" id="lastName" value="${contact.lastName}" class="business_search_input w250"></td>
                <td><span id="lastNameMsg"></span></td>
                <td align="right" valign="middle"><b>出生日期：</b></td>
                <td align="left" valign="middle"><input type="text" name="birthDateStr" id="birthDate" value="${contact.birthDateStr}" class="business_search_input w250 Wdate" date="{maxDate:'${today}'}"/></td>
                <td><span id="birthDateMsg"></span></td>
              </tr>
              <tr>
                <td align="right" valign="middle"><b><span>*</span>手机：</b></td>              
                <td><input type="text" name="mobile" id="mobile" value="${contact.mobile}" class="business_search_input w250"/></td>
                <td><span id="mobileMsg"></span></td>
                <td class="new_het_liulan" align="right" valign="middle"><b>电子邮箱：</b></td>
                <td align="left" valign="middle"><input type="text" name="email" id="email" value="${contact.email}" class="business_search_input w250"/></td>
                <td><span id="emailMsg"></span></td>
              </tr>
              <tr>
                <td align="right" valign="middle"><b>职务：</b></td>              
                <td ><input type="text" name="title" id="title" value="${contact.title}" class="business_search_input w250"/></td>
                <td><span id="titleMsg"></span></td>
                <td class="new_het_liulan" align="right" valign="middle"><b>传真：</b></td>
                <td align="left" valign="middle"><input type="text" value="${contact.fax}" name="fax" id="fax" class="business_search_input w250"/></td>
                <td><span id="faxMsg"></span></td>
              </tr>
           </table>
           </div>
       </div>
       <div class="new_jihui_warp tabs-content_color">          
          <div class="new_common_bg"><span>联系信息</span></div>
          <div class="new_jihui">
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td style='width:17%' align="right" valign="middle"><b>工作电话：</b></td>
                <td style='width:8%'  align="left" valign="middle" ><input type="text" name="phone" id="phone" value="${contact.phone}" value="" class="business_search_input w250" style="float:left"/><span id="phoneMsg" style="float:left"></span></td>
                <td width="13%"></td>
                <td ></td>
                <td><input value="${contact.contactId }" style="display:none"/></td>
                <td>&nbsp;&nbsp;</td>
              </tr>
              <tr>
                <td width="17%" align="right" valign="middle"><b>其他电话：</b></td>
                <td width="8%" align="left" valign="middle" ><input type="text" name="otherPhone" id="otherPhone" value="${contact.otherPhone}" class="business_search_input w250" style="float:left"/><span id="otherPhoneMsg" style="float:left"></span></td>
                <td width="13%"></td>
                <td ></td>
                <td></td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td width="17%" align="right" valign="middle"><b>联系地址：</b></td>
                <td width="8%" align="left" valign="middle" ><input type="text" name="contactAddress" id="contactAddress" value="${contact.contactAddress}" class="business_search_input w250" style="float:left"/><span id="contactAddressMsg" style="float:left"></span></td>
                <td width="13%"></td>
                <td></td>
                <td></td>
                <td></td>
              </tr>
              <tr>
                <td width="17%" align="right" valign="middle"><b>其他地址：</b></td>
                <td width="8%" align="left" valign="middle" ><input type="text" name="otherAddress" id="otherAddress" value="${contact.otherAddress}" class="business_search_input w250" style="float:left"/><span id="otherAddressMsg" style="float:left"></span></td>
                <td width="13%"></td>
                <td></td>
                <td></td>
                <td></td>
              </tr>
              <tr>
                <td align="right" valign="middle"><b>备注：</b></td>
               <td colspan="2" align="left" valign="middle"><textarea name="memo" id="memo" cols="94" rows="5" value="${contact.memo}" style="float:left">${contact.memo}</textarea><span id="memoMsg" style="float:left"></span></td>
               <td></td>
               <td></td>
               <td></td>
              </tr>
           </table>
           </div>
        </div> 
        <div class="new_jihui_menu new_jihui_menu_a">
        
		  <c:choose>
			  <c:when test="${pageStatus=='update'}">
				<p>
				<shiro:hasPermission name="contact.updateRedirectList">
				<a id="update" href="#" class="new_jihui_menu1">修改</a>
				</shiro:hasPermission>
				<a href="javascript:history.go(-1);" class="new_jihui_menu3">取消</a> 
				<p>
			  </c:when>
			  <c:otherwise>
				 <p>
				 <shiro:hasPermission name="contact.saveRedirectList">
                 <a id="onlySave" href="#" class="new_jihui_menu1">仅保存</a> 
                 </shiro:hasPermission>
                 <shiro:hasPermission name="contact.saveRedirectTask">
			     <a id="saveAndCreateTask" href="#" class="new_jihui_menu1">保存新建任务</a>
			     </shiro:hasPermission>
			     <a href="javascript:history.go(-1);" class="new_jihui_menu1">取消</a>    
			     </p>     
			  </c:otherwise>
		   </c:choose>
		   <div class="clear"></div>
	    </div>
             
    </form>
          
</body>
</html>