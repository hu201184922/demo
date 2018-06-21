<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<script>
	$(function(){
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
	function _back(){
		history.back();
		//window.location.href = '${pageContext.request.contextPath}/knowledgeManager/product/toProductPage?type='+$("#type").val();
	}
	function showIframe(num){
		/* $("div[id^=iframe_]").each(function(index,e){
			if(num==index){
				$(e).show();
			}else{
				$(e).hide();
			}
		}); */
		$("#test_1").attr("src",'${pageContext.request.contextPath }/static/pdf/'+num);
	}
</script>
</head>
<body>
	<div id="mainContent_1" style="float: none;">
		<div class="clear"></div>
		<div class="new_jihui_warp tabs-content_color">
			<div class="new_common_bg" style="width:100%;"><span>详细信息</span></div>
			<div class="new_jihui">
			<input id="type" type="hidden" name="type" value="${type }"/>
				<input type="hidden" id="productId" name="productId" value="${product.productId }">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="16%" align="right" valign="middle"><b>产品代码：</b></td>
						<td width="26%" align="left" valign="middle">
							${product.productCode}
						</td>
						<td width="7%" align="left" valign="middle"></td>
						<td width="14%" align="right" valign="middle"><b>产品名称：</b></td>
						<td width="26%" align="left" valign="middle">
							${product.productName }
						</td>
						<td width="11%">&nbsp;</td>
					</tr>
					<tr>
						<td align="right" valign="middle"><b>产品简称：</b></td>
						<td align="left" valign="middle">
							${product.shortName}
							
						</td>
						<td align="left" valign="middle">&nbsp;</td>
						<td align="right" valign="middle"><b>产品系列：</b></td>
						<td align="left" valign="middle">
							${product.attribute3}
						</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td align="right" valign="middle"><b>启用：</b></td>
						<td align="left" valign="middle">
							${product.isActive ==0?'否':'是'}
						</td>
						<td align="left" valign="middle">&nbsp;</td>
						<td align="right" valign="middle">&nbsp;</td>
						<td align="left" valign="middle">
							&nbsp;
						</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td align="right" valign="middle"><b>产品描述：</b></td>
						<td colspan="4" align="left" valign="middle">${product.description}</td>
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
									<td width="17%">&nbsp;&nbsp;<input id="bank_${stauts.index }" name="onlineInfo" type="checkbox" value="${bank.code }" disabled="disabled"/>&nbsp;&nbsp;<label for="bank_${stauts.index }">${bank.name }</label> </td>
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
		<div class="new_jihui_menu" style="width:100%;">
			<a href="###" class="new_jihui_menu3" onclick="_back();">返回</a>
			<div class="clear"></div>
		</div>
	</div>
</body>
</html>