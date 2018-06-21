<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<script>
	$(function(){
		//分页配置  第三个参数为 表ID
		$('#product_search_form').ajaxForm(function(data){
			$('#pager').pager().setPostData(Ace.parseQuery($('#product_search_form').serialize()));
			$('#pager').pager().setJSON(data);
		});
		//分页配置  第三个参数为 表ID
		$('#pager').pager('${pageContext.request.contextPath}/admin/product/getSimpleProductPage',15,$('#product_tab_page').view());
		$("#searchBtn").click(function(){
			$('#product_search_form').submit();
		});
		$('#product_search_form').submit();
		//全选
		$("#selectAll").click(function(){
			if("<b>全选</b>"==$("#selectAll").html()){
				$(".check_").each(function(index,e){
					e.checked = true;
				});
				$("#selectAll").html("<b>取消</b>");
			}else{
				$(".check_").each(function(index,e){
					e.checked=false;
				});
				$("#selectAll").html("<b>全选</b>");
			}
		});
		//批量删除获得 id
		$("#batchDel").click(function(){
			var ids = new Array();
			$(".check_").each(function(index,e){
				if(e.checked){
					var id = $(e).next().val();
					ids.push(id);
				}
			});
			if(ids.length==0){
				alert("请选择产品进行删除");
			}else{
				$.confirm('是否删除选中产品？ ', function() {
					
						$.post("${pageContext.request.contextPath }/admin/product/updateBatchSimpleProductDelFlag",{"delIds":ids},
							function(data) {
								if(data){
							    	$('#pager').pager().reload();
									$.msgbox({time: 1000,msg: "删除成功!",icon:"success"});
								}else{
									$('#pager').pager().reload();
									$.msgbox({time: 1000,msg: "删除部分已选产品,有关联产品不能删除!",icon:"success"});
								}
						});
					
					return true;
					//$("#delOrUpId").val(id);
					//changeAndSubmit('${pageContext.request.contextPath }/knowledgeManager/contract/updateContractDelFlag');
				});
			}
		});
	});
	//新增按钮
	function addProduct() {
		$("#delOrUpId").val("");
		changeAndSubmit('${pageContext.request.contextPath }/admin/product/toSimpleAddView');
	}
	//修改链接
	function updateProduct(id) {
		$("#delOrUpId").val(id);
		changeAndSubmit('${pageContext.request.contextPath }/admin/product/toSimpleAddView');
	}
	//删除产品
	function deleteProduct(id) {
		$.confirm('是否删除该产品？ ', function() {
			$.post("${pageContext.request.contextPath }/admin/product/updateSimpleProductDelFlag",{"delOrUpId":id},
				function(data) {
					if(data){
				    	$('#pager').pager().reload();
						$.msgbox({time: 1000,msg: "删除成功!",icon:"success"});
					}else{
						$.msgbox({time: 1000,msg: "该产品已和其他模块关联，不能删除!",icon:"error"});
					}
				});
			return true;
			//$("#delOrUpId").val(id);
			//changeAndSubmit('${pageContext.request.contextPath }/knowledgeManager/contract/updateContractDelFlag');
		});
	}
	
	function _back(){
		history.back();
	}
	
	function changeAndSubmit(url){
		$("#addUpdateForm").attr("action",url);
		$("#addUpdateForm").submit();
	}
	//查看产品详细
	function toProductDetails(id){
		window.location.href="${pageContext.request.contextPath }/admin/product/toSimpleProductDetailView?productId="+id;
	}
</script>
</head>
<body>
	<%-- <c:if test="${not empty message}">
		<div id="message" class="alert alert-success">
			<button data-dismiss="alert" class="close">×</button>
			${message}
		</div>
	</c:if> --%>
	<form id="addUpdateForm" method="post">
		<input id="type" type="hidden" name="type" value="${type }"/>
		<input id="delOrUpId" type="hidden" name="delOrUpId" value=""/>
		<input id="delIds" type="hidden" name="delIds" value=""/>
	</form>
	<div id="mainContent_1" style="float:none;">
		<div id="searhDiv">
			<div class="new_jihui_warp">
				<div class="new_common_bg" style="width:100%;"><span>请选择查询条件</span></div>
					<div class="library_left_table" style="width:100%;">
						<form id="product_search_form" action="${pageContext.request.contextPath }/admin/product/getSimpleProductPage" method="post">
							<input type="hidden" name="type" value="${type }"/>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="10%" align="right" valign="middle"><b>产品代码：</b></td>
									<td width="25%" align="left" valign="middle"><label for="select"></label>
									<input type="text" id="productCode" name="productCode" value="${paramSearch.productCode }" class="business_search_input  w250" /></td>
									<td width="10%" align="left" valign="middle">&nbsp;</td>
									<td width="10%" align="right" valign="middle"><b>产品名称：</b>
									</td>
									<td width="25%" align="left" valign="middle">
									<input type="text" id="productName" name="productName" value="${paramSearch.productName }" class="business_search_input  w250" />
									</td>
									<td  width="20%" align="center" valign="middle"></td>
								</tr>
								<tr>
									<td align="right" valign="middle"><b>产品简称：</b>
									</td>
									<td align="left" valign="middle">
									<input type="text" id="shortName" name="shortName" value="${paramSearch.shortName }" class="business_search_input  w250" />
									</td>
									<td align="left" valign="middle">&nbsp;</td>
									<td align="right" valign="middle"><b>产品系列：</b></td>
									<td align="left" valign="middle">
										<select id="family" name="family" class="business_search_input w250">
											<c:forEach var="p_type" items="${productType }">
												<option value="${p_type.code }" >${p_type.name }</option>
											</c:forEach>
										</select>
									</td>
									<td  width="20%" align="center" valign="middle"><a style=" margin-top:0px;float:none;" href="###" id="searchBtn">查询</a></td>
								</tr>
							</table>
						</form>
					</div>
					<div class="clear"></div>
			</div>
			<div class="clear"></div>
			<div class="library_right_warp" style="width:100%;"	>
				<div class="library_right_warp_table">
					<div class="new_jihui_menu new_ht_menu">
						<strong style="display:inline-block;" class="new_jihui_menu1"><a href="###" id="addProduct" onclick="addProduct();" >新增产品</a></strong>
						<strong style="display:inline-block;" class="new_jihui_menu3"><a href="###" id="batchDel" >批量删除</a></strong>
					</div>
					<div class="business_search_list_table">
						<table class="t-list table" id="product_tab_page">
							<tr>
								<th width="5%"><a href="###" id="selectAll"><b>全选</b></a></th>
								<th width="10%"><b>类型</b></th>
								<th width="15%"><b>产品代码</b></th>
								<th width="15%"><b>产品名称</b></th>
								<th width="10%"><b>产品简称</b></th>
								<th width="10%"><b>产品系列</b></th>
								<th width="15%"><b>上传日期</b></th>
								<th width="10%"><b>上传人</b></th>
								<th width="10%"><b>操作</b></th>
							</tr>
								<tr class="tb_tr_content template" name="default">
									<td><input type="checkbox" class="check_"/>
									<input type="hidden" name="productId" value="{productId}" />
									</td>
									<td>产品</td>
									<td><a onclick="toProductDetails('{productId}');">{productCode}</a></td>
									<td><a onclick="toProductDetails('{productId}');">{productName}</a></td>
									<td>{shortName}</td>
									<td>{attribute3}</td>
									<td>{createTime}</td>
									<td>{attribute5}</td>
									<td class="library_table">
										<a href="###" onclick="updateProduct('{productId}')"><img src="${pageContext.request.contextPath}/static/images/edit.png" width="20" height="20" title="编辑" /></a>
										<a href="###" onclick="deleteProduct('{productId}')"><img src="${pageContext.request.contextPath}/static/images/dell.png" width="20" height="20" title="删除" /></a>
									</td>
								</tr>
						</table>
						<div id="pager"></div>
					</div>
					<div class="clear"></div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>