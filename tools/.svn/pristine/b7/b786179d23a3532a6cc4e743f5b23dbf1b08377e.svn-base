<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>CRM－后台管理系统-<sitemesh:write property="title" default="模块名" /></title>
<link href="${pageContext.request.contextPath}/static/css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.7.2.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common.js" charset="utf-8"></script>
<script type="text/javascript">
$(document).ready(function(){
	/*头部提醒模块*/
	$(".topNav li").mousemove(function(){
		$(this).children(".tanchuTs").show();	
	});
	
	$(".topNav li").mouseleave(function(){
		$(this).children(".tanchuTs").hide();	
	});
   });
	var setting = {
		data : {
			simpleData : {
				enable : true
			}
		}
		,view:{
			showIcon:false,
			showLine: false
		},
		callback:{
			onClick:zTreeOnClick
		}
	};
	var ktree;
	$(function() {
		var zNodes = ${treeJson};
		ktree = $("#businessTree").zTree(setting, zNodes);
		ktree.expandAll(true);
	});
	
	//点击树时执行的函数
	function zTreeOnClick(event, treeId, treeNode){
		$("#catalogId").val(treeNode.id);
		$("#treeForm").attr("action","${pageContext.request.contextPath}"+treeNode.tempurl);
		$("#treeForm").submit();
	};
	/* 
	
	function selectCity(zthis,str){
		//全选省
		if(str=="pall"){
			$("#provinceId").find("input").attr("checked",zthis.checked);	
			if(zthis.checked!=true){
				$("#cityId").css("display","none");
			}
			return;
		}
		//全选城市
		if(str=="call"){
			$("#cityId").find("input").attr("checked",zthis.checked);		
			return;
		}
		//全选类型
		if(str=="tall"){
			$("#typeId").find("input").attr("checked",zthis.checked);		
			return;
		}
		
		
		if(zthis.checked==true){
			$("#cityId").css("display","block");
		}else{
			$("#cityId").css("display","none");
		}
	}
	 */
	 
function viewOppAlertList(){
	location.href = "${pageContext.request.contextPath}/opportunity/opportunityList?queryType=1&fastQuery=2";
}
function viewCamAlertList(){
	location.href = "${pageContext.request.contextPath}/campaign/getCampaigns?moreEndTimeNoModify=Y";
}
</script>
<sitemesh:write property="head" />
</head>
<body>
    	<div id="mainwrapper">
		    <div id="header">
		        <div class="logo"></div>
		        <div class="topHeadeRight"> 
		            <div class="topNav">
		                <ul>
		                     <li><img src="${pageContext.request.contextPath}/static/images/index_name.png" /></li>
                         <li><span class="post_job"><shiro:principal property="userName" />&nbsp;&nbsp;${headRoleName }</span><br />
                        <span>${headDateStr }&nbsp;&nbsp;${headWeek }</span></li>
                    <li class="dividing_line" ></li>
                    <li>
                    	<shiro:hasPermission name="opportunity.opportunityList">
	                    	<div class="tanchuTs">
	                        	您有 ${oppNum } 条业务机会超过一周没有操作！
	                        </div>
	                        <div class="mailbox" onclick="viewOppAlertList()">
	                            <span class="digital">${oppNum }</span>
	                        </div>
	                    </shiro:hasPermission>
                    </li>
                    <li>
	                    <shiro:hasPermission name="campaign.getCampaigns">
	                    	<div class="tanchuTs">
	                        	您有${camNum } 个市场活动已结束但尚未修改状态！
	                        </div>
	                        <div class="message_1" onclick="viewCamAlertList()">
	                            <span class="digital">${camNum }</span>
	                        </div>
                        </shiro:hasPermission>
                    </li>
                     <li><a  class="bow_out" href="${pageContext.request.contextPath}/logout"  >退出</a></li>
		            </ul>
		            </div>
		            <div class="navigation">
		                <ul>                                      
							<c:forEach items="${menuItems}" var="menu" varStatus="idx">
								<shiro:hasPermission name="${menu.permString}">
									 <li><a href="${pageContext.request.contextPath}/${menu.url}">${menu.name}</a></li> 
								</shiro:hasPermission>
							</c:forEach>
		                </ul>
		            </div>
		        </div>
		    </div>
		    <div class="clear"></div>
		    <form action="" id="treeForm">  
					<input type="hidden" name="catalogId" id="catalogId" value="${catalogId }">
					<input type="hidden" name="type" value="${type}"/>
			</form>
				 <%-- 	<div class="knowledge_top">
						<div style="margin-top:20px;margin-left:10px">
							<div class="search_01">公司层级:</div>
							<div id="provinceId" class="provice_class">
								<input type="checkbox" name="provinceList" onclick="selectCity(this,'pall')"/><strong class="text_undefine">全部</strong>&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="checkbox" name="provinceList" /><strong class="text_undefine">上海</strong>&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="checkbox" name="provinceList" /><strong class="text_undefine">北京</strong>&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="checkbox" name="provinceList" onclick="selectCity(this)"/><strong class="text_undefine">江苏</strong>&nbsp;&nbsp;&nbsp;&nbsp;
							</div>
							<div id="cityId" class="city_select">&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="checkbox" name="cityList" onclick="selectCity(this,'call')" /><strong class="text_undefine">全部</strong>&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="checkbox" name="cityList"  /><strong class="text_undefine">南京</strong>&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="checkbox" name="cityList"  /><strong class="text_undefine">南通</strong>&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="checkbox" name="cityList"  /><strong class="text_undefine">无锡</strong>&nbsp;&nbsp;&nbsp;&nbsp;
							</div>
						</div>
					</div> --%>
	        <div style="height:15px;"></div>
	        <div class="knowledge_left knowledgebtn" style="background-color:#ebeff2">
			<div class="left_top">类别目录
			<c:if test="${type != 'A'}">
	          <a  href="${pageContext.request.contextPath}/knowledgeManager/knowledgeCollect/getKnowledgeCollects">
				<img  src="${pageContext.request.contextPath}/static/images/shoucang.png"/> 
				 <span>收藏夹</span>
		      </a>
		      </c:if>
			</div>
			<ul id="businessTree" class="ztree">
			</ul>
			<%-- <form action="${pageContext.request.contextPath}/knowledgeManager/knowledge/index" method="post">
			   <shiro:hasPermission name="knowledgeManager.knowledge.index">
				<c:if test="${type=='A'}">
					<input type="hidden" value="" name="type"/>
					<input type="submit" value="返回知识库首页" />
				</c:if>
				 </shiro:hasPermission>
				<shiro:hasPermission name="knowledgeManager.knowledge.index">
				<c:if test="${type!='A'}">
					<input type="hidden" value="A" name="type"/>
					<input type="submit" value="进入知识库管理" />
				</c:if>
			 </shiro:hasPermission>
			</form> --%>
	</div>
	<div class="knowledage_right">
		<div id="content">
			<sitemesh:write property="body" />
		</div>
	</div>
    <div class="clear"></div>
    <div id="footer">
    </div>
</div>
</body>
</html>