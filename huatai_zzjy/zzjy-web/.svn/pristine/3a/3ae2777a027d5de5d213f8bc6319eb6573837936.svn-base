<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%
	request.setAttribute("webctx", request.getContextPath());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>编辑联系人</title>
<script type="text/javascript">
	$(function(){
		
		
	});
</script>
<style></style>
<script></script>
<script type="text/javascript">
  function back(){
	window.history.back(-1);
  };
  $(function(){
	    $("#save").click(function(){
	    	$("#contactUpdateForm").submit();
	    });	   
	});
</script>
</head>
<body>
     <div class="business_title">
	          <a href="#" class="home">首页   》</a><a href="#">联系人  》</a><a href="#">${contact.contactName}信息</a>
     </div>
     <div class="tabs" id="tabs1">
	      <ul class="tabs-nav">
              <li><a href="javascript:;"><span>基本信息</span></a></li>
          </ul>
          <div class="tabs-cnt">
    	    <div class="tabs-content">
    	      <form id="contactUpdateForm" action="${pageContext.request.contextPath }/contact/updateRedirectDetail" method="post" >    	          	      
    	      <div class="new_jihui_warp">
    	            <div class="new_common_bg"><span>联系人详细信息</span></div>
    	            <div class="new_jihui">
                      <table width="100%"  cellspacing="0" cellpadding="0">
                            <tr padding="8px">
                              <td width="16%" align="right" valign="middle" ><b><span>*</span>联系人姓名：</b></td>
                              <td width="26%" align="left" valign="middle" ><input type="text" name="contactName" value="${contact.contactName}" class="business_search_input w250"/></td>
                              <td width="11%" ><b>联系人所有人：</b></td>
                              <td >admin</td>
                            </tr>
                            <tr>
                              <td align="right" valign="middle"><b>名字：</b></td>
                              <td align="left" valign="middle"><input type="text" name="firstName" value="${contact.firstName}" class="business_search_input w250"/></td>
                              <td><b>性别：</b></td>
                              <td><select name="sex" value="${contact.sex}" class="business_search_input w250">
                                     <option>男</option>
                                     <option>女</option>
                                  </select></td>
                            </tr>
                            <tr>
                              <td align="right" valign="middle"><b>姓氏：</b></td>
                              <td align="left" valign="middle"><input type="text" name="lastName" value="${contact.lastName}" class="business_search_input w250"/></td>
                              <td><b>出生日期：</b></td>
                              <td align="left" valign="middle"><input type="text" name="birthDate" value="${contact.birthDate}" class="business_search_input w250 Wdate"/></td>
                            </tr>
                            <tr>
                              <td align="right" valign="middle"><b><span>*</span>手机：</b></td>              
                              <td width="32%"><input type="text" name="mobile" value="${contact.mobile}" class="business_search_input w250"/></td>
                              <td width="9%" class="new_het_liulan"><b>电子邮箱：</b></td>
                              <td align="left" valign="middle"><input type="text" name="email" value="${contact.email}" class="business_search_input w250"/></td>
                            </tr>
                            <tr>
                              <td align="right" valign="middle"><b>职务：</b></td>              
                              <td width="32%"><input type="text" name="title" value="${contact.title}" class="business_search_input w250"/></td>
                              <td width="9%" class="new_het_liulan"><b>传真：</b></td>
                              <td align="left" valign="middle"><input type="text" name="fax" value="${contact.fax}" class="business_search_input w250"/></td>
                            </tr>
                     </table> 
                   </div>
                 </div>
                 <div class="new_jihui_warp">
    	             <div class="new_common_bg"><span>地址信息</span></div>
    	             <div class="new_jihui"> 	                
    	             <table class="gridtable" width="100%" border="0" cellspacing="5" cellpadding="5">
                            <tr>
                              <td width="16%" align="right" valign="middle"><b>工作电话：</b></td>
                              <td width="26%" align="left" valign="middle" ><input type="text" name="phone" value="${contact.phone}" class="business_search_input w250"/></td>
                              <td width="11%"></td>
                              <td><input value="${contact.contactId }" style="display:none"/></td>
                            </tr>
                            <tr>
                              <td width="16%" align="right" valign="middle"><b>其他电话：</b></td>
                              <td width="26%" align="left" valign="middle" ><input type="text" name="otherPhone" value="${contact.otherPhone}" class="business_search_input w250"/></td>
                              <td width="11%"></td>
                              <td></td>
                            </tr>
                            <tr>
                              <td width="16%" align="right" valign="middle"><b>联系地址：</b></td>
                              <td width="26%" align="left" valign="middle" ><input type="text" name="contactAddress" value="${contact.contactAddress}" class="business_search_input w250"/></td>
                              <td width="11%"></td>
                              <td></td>
                            </tr>
                            <tr>
                              <td width="16%" align="right" valign="middle"><b>其他地址：</b></td>
                              <td width="26%" align="left" valign="middle" ><input type="text" name="otherAddress" value="${contact.otherAddress}" class="business_search_input w250"/></td>
                              <td width="11%"></td>
                              <td></td>
                            </tr>
                            <tr>
                              <td align="right" valign="middle"><b>备注：</b></td>
                              <td colspan="3" align="left" valign="middle"><textarea name="memo" id="textarea" cols="120" rows="5" value="${contact.memo}"></textarea></td>
                            </tr>
                            
                     </table>
                    </div>
                  </div>
                  <div class="new_jihui_menu">
                      <a id="save" href="#" class="new_jihui_menu1">修改</a>
				      <a href="javascript:history.go(-1);" class="new_jihui_menu3">取消</a>  
				  </div>                                      
	           </form>
    	     </div>
    	  </div>
    </div>      
</body>
</html>