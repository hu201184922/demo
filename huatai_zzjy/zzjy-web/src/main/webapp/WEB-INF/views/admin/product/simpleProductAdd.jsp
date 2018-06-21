<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<script>
var canSave = 0;
var setting1 = {
		data : {
			simpleData : {
				enable : true
			}
		},
		view:{
			showIcon:true,
			showLine: true
		},
		callback : {
			onClick : onClick1
		}
	};
	
	var tree1 ;
	function showMenu() {
		var cityObj = $("#catalogName");
		var cityOffset = $("#catalogName").offset();
		$("#menuContent").css({left:"117px", top:"36px"}).slideDown("fast");

		$("body").bind("mousedown", onBodyDown1);
	}
	
	function hideMenu1() {
		$("#menuContent").fadeOut("fast");
		$("body").unbind("mousedown", onBodyDown1);
	}
	function onBodyDown1(event) {
		if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
			hideMenu1();
		}
	}
	
	function onClick1(e,treeId, treeNode){
		var cityObj = $("#catalogName");
		cityObj.attr("value", treeNode.name);
		$("#catalogIdProduct").val(treeNode.id);
		hideMenu1();
	}
	$(function(){
		$("#confirmBtn").click(function(){
			if(validate()){
				var productId = $("#productId").val();
				if(productId!=null&&productId!=""){
					$('#productAddForm').attr("action",'${pageContext.request.contextPath }/admin/product/updateSimpleProduct');
					if(canSave==0){
						$("#productAddForm").submit();
						canSave++;
					}
				}else{
					$('#productAddForm').attr("action",'${pageContext.request.contextPath }/admin/product/insertSimpleProduct');
					if(canSave==0){
						$("#productAddForm").submit();
						canSave++;
					}
				}
			}
		});
		var pNodes = ${productTree};
		tree1 = $("#treeProduct").zTree(setting1,pNodes);
		var banks = "${product.onlineInfo}";
		var bank =banks.split(","); 
		$("input[id^=bank_]").each(function(index,e){
			for (var i=0;i<bank.length ;i++ ) { 
				if(bank[i]==$(e).attr("value")){
					e.checked = true;
				}
			
			}
		});
	});
	
	function validate(){
		var flag = true;
		/**产品代码校验*/
		var productCode = stringUtils.trim($("#productCode").val());//2侧去空
		if(stringUtils.isEmpty(productCode)){
			$("#productCode").focus();
			flag = false;
			$("#productCode").parent().next().html("<span>不能为空!</span>");
		}/* else if(!validateUtils.isLetterOrNumber(productCode)){
			$("#productCode").focus();
			flag = false;
			$("#productCode").parent().next().html("<span>格式错误!</span>");
		} */else if(stringUtils.getStrLength(productCode)>20){
			$("#productCode").focus();
			flag = false;
			$("#productCode").parent().next().html("<span>长度过长!</span>");
		}else{
			$("#productCode").parent().next().html("");
			$("#productCode").val(productCode);
		}
		/**产品名称校验*/
		var productName = stringUtils.trim($("#productName").val());//2侧去空
		if(stringUtils.isEmpty(productName)){
			if(flag){
				$("#productName").focus();
			}
			flag = false;
			$("#productName").parent().next().html("<span>不能为空!</span>");
		}/* else if(!validateUtils.isSearch(productName)){
			if(flag){
				$("#productName").focus();
			}
			flag = false;
			$("#productName").parent().next().html("<span>包含特殊字符!</span>");
		} */else if(stringUtils.getStrLength(productName)>200){
			if(flag){
				$("#productName").focus();
			}
			flag = false;
			$("#productName").parent().next().html("<span>长度过长!</span>");
		}else{
			$("#productName").parent().next().html("");
			$("#productName").val(productName);
		}
		
		/**产品简称校验*/
		var shortName = stringUtils.trim($("#shortName").val());//2侧去空
		if(stringUtils.isEmpty(shortName)){
			if(flag){
				$("#shortName").focus();
			}
			flag = false;
			$("#shortName").parent().next().html("<span>不能为空!</span>");
		}/* else if(!validateUtils.isSearch(shortName)){
			if(flag){
				$("#shortName").focus();
			}
			flag = false;
			$("#shortName").parent().next().html("<span>包含特殊字符!</span>");
		} */else if(stringUtils.getStrLength(shortName)>20){
			if(flag){
				$("#shortName").focus();
			}
			flag = false;
			$("#shortName").parent().next().html("<span>长度过长!</span>");
		}else{
			$("#shortName").parent().next().html("");
			$("#shortName").val(shortName);
		}
		/**产品简称校验*/
		var family = stringUtils.trim($("#family").val());//2侧去空
		if(stringUtils.isEmpty(family)){
			if(flag){
				$("#family").focus();
			}
			flag = false;
			$("#family").parent().next().html("<span>不能为空!</span>");
		}else{
			$("#family").parent().next().html("");
		}
		/**产品系列校验*/
		var catalogIdProduct = stringUtils.trim($("#catalogIdProduct").val());//2侧去空
		if(stringUtils.isEmpty(catalogIdProduct)){
			if(flag){
				$("#catalogIdProduct").focus();
			}
			flag = false;
			$("#catalogIdProduct").parent().next().html("<span>不能为空!</span>");
		}else{
			$("#catalogIdProduct").parent().next().html("");
		}
		/**备注校验*/
		var description = stringUtils.trim($("#description").val());//2侧去空
		if(stringUtils.getStrLength(description)>255){
			if(flag){
				$("#description").focus();
			}
			flag = false;
			$("#description").parent().next().html("<span>长度过长!</span>");
		}else{
			$("#description").parent().next().html("");
			$("#description").val(description);
		}
		return flag;
	}
	/**
	*返回按钮方法
	*/
	function _back(){
		history.back();
	}
	
	function changActive(e){
		if(e.checked){
			$(e).next().css("color",'black').html("&nbsp;&nbsp;启用");
		}else{
			$(e).next().css("color",'red').html("&nbsp;&nbsp;禁用");
		}
	}
	
</script>
</head>
<body>
	<div id="mainContent_1" style="float:none;">
		<form id="productAddForm" action="${pageContext.request.contextPath }/knowledgeManager/product/insertSimpleProduct" method="post">
			<div class="clear"></div>
			<div class="new_jihui_warp tabs-content_color">
				<div class="new_common_bg" style="width:100%;"><span>产品信息</span><span class="style1">（注意：<font>*</font>为必填项）</span></div>
				<div class="new_jihui">
					<input id="type" type="hidden" name="type" value="${type }"/>
					<input type="hidden" id="productId" name="productId" value="${product.productId }">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="12%" align="right" valign="middle"><b><span>*</span>产品代码：</b></td>
							<td width="25%" align="left" valign="middle">
								<input type="text" id="productCode" name="productCode" <c:if test="${product.productId !=null}">disabled="disabled"</c:if> value="${product.productCode }" class="business_search_input w250"/>
							</td>
							<td width="13%" align="left" valign="middle"></td>
							<td width="12%" align="right" valign="middle"><b><span>*</span>产品名称：</b></td>
							<td width="25%" align="left" valign="middle">
								<input type="text" id="productName" name="productName" <c:if test="${product.productId !=null}">disabled="disabled"</c:if> value="${product.productName }" class="business_search_input w250"/></td>
							<td width="13%">&nbsp;</td>
						</tr>
						<tr>
							<td align="right" valign="middle"><b><span>*</span>产品简称：</b></td>
							<td align="left" valign="middle">
								<input type="text" id="shortName" name="shortName" value="${product.shortName}"  class="business_search_input w250"/>
							</td>
							<td align="left" valign="middle">&nbsp;</td>
							<td align="right" valign="middle"><b><span>*</span>产品系列：</b></td>
							<td align="left" valign="middle">
								<select id="family" name="family" class="business_search_input w250">
									<c:forEach var="p_type" items="${productType }">
										<option value="${p_type.code }" >${p_type.name }</option>
									</c:forEach>
								</select>
								<script>
									$("#family").val("${product.family}");
								</script>
							</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td align="right" valign="middle" style="position:relative;"><b><span>*</span>目录：</b>
								<div id="menuContent" class="menuContent" style="display:none; border:1px solid #dadada;width:250px; text-align:left;color:#666; background:#fff; position: absolute;z-index: 10000;">
									<ul id="treeProduct" class="ztree" style="margin-top:0; width:160px;"></ul>
								</div>
							</td>
							<td align="left" valign="middle">
								<input type="hidden" id="catalogIdProduct" name="catalogIdProduct" value="${product.catalogId}" />
								<input type="text" id="catalogName" name="catalogName" value="${product.attribute4}"  class="business_search_input w250" onclick="showMenu();" readonly="readonly"/>
							</td>
							<td align="left" valign="middle">&nbsp;</td>
							<td align="right" valign="middle"><b><span>*</span>是否启用：</b></td>
							<td align="left" valign="middle">&nbsp;
							<input type="radio" id="isActive1" name="isActive" checked="checked" value="0"/><label for="isActive1">否</label> 
							&nbsp;<input type="radio" id="isActive2" name="isActive" value="1"/><label for="isActive2">是</label> 
							</td>
							<script>
									if('${product.isActive}'=='0'){
										$("#isActive1").attr("checked",true);
									}else{
										$("#isActive2").attr("checked",true);
									}
								</script>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td align="right" valign="middle"><b>产品描述：</b></td>
							<td colspan="4" align="left" valign="middle">
							<textarea name="description" id="description" cols="120" rows="5">${product.description}</textarea>
							</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td align="right" valign="middle"><b>银保通上线银行：</b></td>
							<td colspan="4">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<c:forEach items="${onlinebank }" var="bank" varStatus="stauts" begin="1">
									<c:if test="${stauts.index%6=='1' }">
										<tr>
									</c:if>
									<td width="17%">&nbsp;&nbsp;<input id="bank_${stauts.index }" name="onlineInfo" type="checkbox" value="${bank.code }"/>&nbsp;&nbsp;<label for="bank_${stauts.index }">${bank.name }</label> </td>
									<c:if test="${stauts.index%6=='0' }">
										</tr>
									</c:if>
								</c:forEach>
								</table>
							</td>
							<td>&nbsp;</td>
						</tr>
					</table>
					<div class="clear"></div>
				</div>
			</div>
			<div class="new_jihui_menu new_jihui_menu_a" style="width:100%;">
				<p>
					<a id="confirmBtn" href="###" class="new_jihui_menu1">发布</a> 
					<a href="###" class="new_jihui_menu3" onclick="_back();">返回</a>
				<p>
				<div class="clear"></div>
			</div>
		</form>
	</div>
	 
</body>
</html>