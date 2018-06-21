<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	request.setAttribute("webctx", request.getContextPath());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html decorator="null">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>指标设置</title>
<script type="text/javascript">
  function back(){
	window.history.back(-1);
  };
  $(function(){
	    $(".targetSwitch,.targetGroupSwitch,.targetThemeSwitch,.twoTargetSwitch,.oneTargetSwitch").hide();  
	    $(":radio").click(function(){
	 	   if($(this).val()==0){$(".targetThemeSwitch,.targetGroupSwitch,.targetCount,.oneTargetSwitch,.twoTargetSwitch").hide();
	 		  $(".targetSwitch,.deptSwitch").show();
	 	   }
	 	  	if($(this).val()==1){$(".targetSwitch,.deptSwitch,.twoTargetSwitch,.targetGroupSwitch").hide();
		 		$(".oneTargetSwitch,.targetSwitch,.targetThemeSwitch").show();
	 	   }
	 	  	if($(this).val()==2){$(".twoTargetSwitch,.targetThemeSwitch,.targetSwitch,.deptSwitch,.oneTargetSwitch").hide();
		 		$(".targetGroupSwitch,.targetThemeSwitch").show();
	 	   }
	 	    if($(this).val()==3){$(".targetGroupSwitch,.twoTargetSwitch,.targetThemeSwitch,.deptSwitch").hide();
		 	  	$(".oneTargetSwitch,.targetSwitch,.targetThemeSwitch").show();
		   }
	 	  	if($(this).val()==4){$(".targetGroupSwitch,.targetThemeSwitch,.targetCount,.oneTargetSwitch,.twoTargetSwitch").hide();
		 		$(".targetSwitch,.deptSwitch").show();
		   }
	 	  	if($(this).val()==5){$(".targetGroupSwitch,.targetThemeSwitch,.targetCount,.oneTargetSwitch,.twoTargetSwitch").hide();
	 		$(".targetSwitch,.deptSwitch").show();
	   }
	});
			
  	$(".slt").click(function(){
	   	var gdId=$(this).closest('tr').attr("gdid");
	   	$('tr[gdid="'+gdId+'"] td.append').html('');
	   	var id="tr[gdid="+gdId+"] :last";
	   	var trs='';		    	
	   		$.getJSON('${pageContext.request.contextPath}/admin/target/targetPage',{gdId:gdId,parentCode:$("#targetGroup").val()},function(data){    		 
	    		$.each(data["groDimDetail"], function (n, value) { 
	    			if('${tarGroDim}'.indexOf(value.gddId)>-1){
	    				trs+="<input name='gddIds' checked='checked'";
	    			}
	    			else{
	    				trs+="<input name='gddIds'";
	    			}
	    			trs += "style='margin-left: 30px;' type='checkbox' gddIds="+value.gddId+"  value='"+value.gddId+"' /><b><a href='javascript:void(0)' >"+value.groDimName+"</a></b>";    			
	    		});
	    		$(id).html(trs);
	    		//所有区域维度
	    		if($('#oldTargetCode').val()==''){
	    			$.each(data["groDimDetail"], function (n, value) {
	    	   			if(data["gddIds"].indexOf(value.gddId)>-1){
	    	   				$('.gridtable input:checkbox[gddIds="'+value.gddId+'"]').attr("checked",'checked');
	        			}	 			
	    	   		});
	    		}
	    	})
   }); 
  if($(":radio").val()==0){$(".targetGroupSwitch,.targetThemeSwitch,.targetCount,.oneTargetSwitch,.twoTargetSwitch").hide();$(".targetSwitch,.deptSwitch").show();}if($(":radio").val()==1){$(".targetSwitch,.deptSwitch,.twoTargetSwitch,.targetGroupSwitch").hide();$(".oneTargetSwitch,.targetSwitch,.targetThemeSwitch").show();}if($(":radio").val()==2){$(".twoTargetSwitch,.targetSwitch,.deptSwitch,.oneTargetSwitch").hide();$(".targetGroupSwitch,.targetThemeSwitch").show();}if($(":radio").val()==3){$(".targetGroupSwitch,.deptSwitch").hide();
  	  var option='<option value="">请选择</option>';
	  $('.targetGro').html('');
	  var temp='',id='';
	  if($("#targetGroup").val()!=""){
		  $.getJSON('${pageContext.request.contextPath}/admin/target/targetGroup',{parentCode:$("#targetGroup").val(),oldTargetCode:$('#oldTargetCode').val()},function(data){    		 
		   		$.each(data["targetGroup"], function (n, value) {
		   			if('${target.parentCode}'==value.targetCode){
		   				option +="<option selected='selected'";
		   			}else{
		   				option +="<option ";
		   			}
		   			option +="value='"+value.targetCode+"'>"+value.targetName+"</option>";
		   		});	
	   			$('.targetGro').html(option);
		   		$.each(data["targets"], function (n, value) {
		   			$('.deptCode').val(value.deptCode);
		   			$(".isStatis").val(value.isStatis);  			
		   			$("#isPlate").html("<input  type='hidden' name='isPlates' value="+value.isPlate+">");
		   		});
		   		//所有筛选维度
		   		$(".queryDimTr input[name='qdIds']").attr("checked",false);
		   		$.each(data["queryDim"], function (n, value) {
		   			if(data["qdIds"].indexOf(value.qdId)>-1){
		   				$('.queryDimTr input:checkbox[qdIds="'+value.qdId+'"]').attr("checked",'checked');
	    			}	 			
		   		});
		   		//$(".slt").click();
		  });
		  if($(":radio").val()==3){
		  $(".twoTargetSwitch").show();}
	  }else{
		  $('.targetGro').html(option);
	  }
  $(".oneTargetSwitch,.targetSwitch,.targetThemeSwitch,.twoTargetSwitch").show();}if($(":radio").val()==4||$(":radio").val()==5){$(".targetThemeSwitch,.targetGroupSwitch,.targetCount,.oneTargetSwitch,.twoTargetSwitch").hide();$(".targetSwitch,.deptSwitch").show();}    
  $(".slt").click();
  $(".isStatis").val('${target.isStatis}');
  });
 	/* 加载指标组 */
  	$(".targetGroup").bind("change",function(){
	  var option='<option value="">请选择</option>';
	  $('.targetGro').html('');
	  var temp='',id='';
	  if($("#targetGroup").val()!=""){
		  $.getJSON('${pageContext.request.contextPath}/admin/target/targetGroup',{parentCode:$("#targetGroup").val(),oldTargetCode:$('#oldTargetCode').val()},function(data){    		 
		   		$.each(data["targetGroup"], function (n, value) { 	    			
		   			option +="<option value="+value.targetCode+">"+value.targetName+"</option>"		    			
		   		});	
		   		$.each(data["targets"], function (n, value) {
		   			$('.deptCode').val(value.deptCode);
		   			$(".isStatis").val(value.isStatis);  			
		   			$("#isPlate").html("<input  type='hidden' name='isPlates' value="+value.isPlate+">");
		   		});
		   		
		   		$('.targetGro').html(option);
		   		//所有筛选维度
		   		$(".queryDimTr input[name='qdIds']").attr("checked",false);
		   		$.each(data["queryDim"], function (n, value) {
		   			if(data["qdIds"].indexOf(value.qdId)>-1){
		   				$('.queryDimTr input:checkbox[qdIds="'+value.qdId+'"]').attr("checked",'checked');
	    			}	 			
		   		});
		   		$(".slt").click();
		  });
		  if($(":radio").val()==3){
		  $(".twoTargetSwitch").show();}
	  }else{
		  $('.targetGro').html(option);
	  }
	  
	  
  })
  function sel(obj){
	 var flag= $(obj).prop("checked");
	 var gdId=$(obj).closest('tr').attr("gdid");
	 $('.gridtable tr[gdid="'+gdId+'"] input:checkbox[name=gddIds]').attr("checked",flag==false?false:"checked");
  }
$("#save").click(function(){
	var id = $('#targetCode').val();
	var name = $("#targetName").val();
	var sort = $("#sort").val();
	var targetType = $("#targetType input[name='targetType']:checked").val();
	var targetGroup= $("#targetGroup").find('option:selected').val();
	var groupParentCode= $(".twoTargetSwitch select[name='groupParentCode']").find('option:selected').val();
	if($.trim(id)==null || $.trim(id) == ''){
		$.dialog({content: '请输入指标编号!', time: 2000});return;
	}
	if($.trim(name)=='' || $.trim(name)==null){
		$.dialog({top:50,content: '请输入指标名称!', time: 2000});return;
	}
	if($.trim(targetType)=='1'){
		if(targetGroup ==null ||targetGroup ==''){
			$.dialog({content: '请选择板块或主题!', time: 2000});return;
		}		
	}if($.trim(targetType)=='3'){
		if(targetGroup ==null ||targetGroup ==''){
			$.dialog({content: '请选择板块或主题!', time: 2000});return;
		}
		if(groupParentCode==null ||groupParentCode=='')
		{
			$.dialog({content: '请选择指标组!', time: 2000});return;
		}
	}
	if($.trim(sort)=='' || $.trim(sort)==null){
		$.dialog({top:50,content: '请输入排序号!', time: 2000});return;
	}
    $("#contactUpdateForm").submit();
});

</script>
</head>
<body>
	<div class="tabs" id="tabs1">
		<ul class="tabs-nav">
			<li><a href="javascript:;"><span>基本属性</span></a></li>
		</ul>
		<div class="tabs-cnt">
			<div class="tabs-content">

				<form id="contactUpdateForm"
					action="${pageContext.request.contextPath }/admin/target/create"
					method="post">
					<div class="tagContent tagContent_div" style="display: block;">
						<div class="new_common_bg">
							<span>指标详细信息</span>
						</div>
						<div id="isPlate"></div>
						<div class="new_jihui" style="line-height: 60px;">
							<div id="isPlate"></div>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr padding="8px">
									<td width="10%" align="right" valign="middle"><b><span>*</span>名称：</b></td>
									<td width="450px" align="left" valign="middle"><input style="color: black;width: 201px; height: 30px; border-radius: 5px; border: 1px solid #90C1E6;"
										type="text" id="targetName" name="targetName"
										value="${target.targetName}"
										class="business_search_input w250" /></td>
									<td><b>&nbsp;&nbsp;指标编号：</b><input type="text"
										style="width: 201px; height: 30px; border-radius: 5px; border: 1px solid #90C1E6;"
										id="targetCode" name="targetCode" value="${target.targetCode}"
										class="business_search_input w250" /><input id="oldTargetCode" type="hidden"
										name="oldTargetCode" value="${target.targetCode}"
										class="business_search_input w250" /></td>
									</td>
								</tr>
								<tr>
									<td align="right" valign="middle"><b>分类：</b></td>
									<td id="targetType"><span style="color: black"><input
											class="targetType" type="radio" value="0"
											<c:if test="${target.targetType=='0'}">checked="checked"</c:if>
											name="targetType">主题</span>&nbsp;&nbsp; <span
										style="color: black"><input class="targetType"
											type="radio" value="4"
											<c:if test="${target.targetType=='4'}">checked="checked"</c:if>
											name="targetType">板块</span>&nbsp;&nbsp; <span
										style="color: black"><input class="targetType"
											type="radio" value="5"
											<c:if test="${target.targetType=='5'}">checked="checked"</c:if>
											name="targetType">开发主题</span>&nbsp;&nbsp; <span
										style="color: black"><input class="targetType"
											type="radio" value="1"
											<c:if test="${target.targetType=='1'}">checked="checked"</c:if>
											name="targetType">一级指标</span>&nbsp;&nbsp; <span
										style="color: black"><input class="targetType"
											type="radio" value="2"
											<c:if test="${target.targetType=='2'}">checked="checked"</c:if>
											name="targetType">指标组</span>&nbsp;&nbsp; <span
										style="color: black"><input class="targetType"
											type="radio" value="3"
											<c:if test="${target.targetType=='3'}">checked="checked"</c:if>
											name="targetType">二级指标</span>&nbsp;&nbsp;</td>
										<td align="left" valign="middle"><b>&nbsp;&nbsp;排序：&nbsp;&nbsp;</b><span style="color: black"><input id="sort" style="width: 201px; height: 30px; border-radius: 5px; border: 1px solid #90C1E6;" type="text" name="sort" class="sort" value="${target.sort}" /></span></td>
						
								</tr>
								<!-- 主题板块 -->
								<tr class="deptSwitch">
									<td align="right" valign="middle"><b>部门：</b></td>
									<td><select class="deptCode"
										style="width: 150px; height: 30px; border-radius: 5px; border: 1px solid #90C1E6;"
										name="deptCode" class="view-field">
											<option value="">请选择</option>
											<c:forEach items="${dictItem}" var="list">
												<option value="${list.code }"
													<c:if test="${target.deptCode==list.code}">selected="selected"</c:if>>${list.name}</option>
											</c:forEach>
									</select>
									
									<b>&nbsp;&nbsp;是否实时：</b><select class="isRealtime"
										style="width: 200px; height: 30px; border-radius: 5px; border: 1px solid #90C1E6;"
										name="isRealtime" class="view-field">
											<option value="0"
												<c:if test="${target.isRealtime=='0'}">selected="selected"</c:if>>否</option>
											<option value="1"
												<c:if test="${target.isRealtime=='1'}">selected="selected"</c:if>>是</option>

									</select>
									</td>
									

									<td>&nbsp;&nbsp;<b>是否为区间统计：</b><select class="isStatis"
										style="width: 200px; height: 30px; border-radius: 5px; border: 1px solid #90C1E6;"
										name="isStatis" class="view-field">
											<option value="0">否</option>
											<option value="1">是</option>
											<%-- <c:forEach items="${target}" var="list">
												<option value="${list.sort}"
													
											</c:forEach> --%>
									</select>&nbsp;&nbsp;
									</td>

								</tr>

								<!-- 指标组 -->
								<tr class="targetThemeSwitch">
									<td align="right" valign="middle"><b>选择主题或板块：</b></td>
									<td><select id="targetGroup"
										style="width: 301px; height: 30px; border-radius: 5px; border: 1px solid #90C1E6;"
										name="parentCode" class="view-field targetGroup">
											<option value="" onchange='$(".twoTargetSwitch").hide()'>请选择</option>
											<c:forEach items="${targetType}" var="list">
												<option
													<c:if test="${fn:substring(target.parentCode,0,3)==list.targetCode && target.targetType=='3'}">selected="selected" onclick='$(".twoTargetSwitch").show()'</c:if>
													<c:if test="${target.parentCode==list.targetCode && target.targetType=='1'}">selected="selected"</c:if>
													<c:if test="${fn:substring(target.parentCode,0,7)==list.targetCode && target.isPlate=='2'}">selected="selected"</c:if>
													<c:if test="${fn:substring(target.parentCode,0,7)==list.targetCode && target.targetType=='2'}">selected="selected"</c:if>
													value="${list.targetCode}">${list.targetName}</option>
											</c:forEach>
									</select></td>
								</tr>

								<!-- 一，二级指标分组 -->

								<tr class="twoTargetSwitch">
									<td align="right" valign="middle"><b>选择指标组：</b></td>
									<td><select
										style="width: 301px; height: 30px; border-radius: 5px; border: 1px solid #90C1E6;"
										name="groupParentCode" class="view-field targetGro">
											<option value="">请选择</option>
											 <c:forEach items="${targetGroup}" var="list">
												<option <c:if test="${target.parentCode==list.targetCode && target.targetType=='3'}">selected="selected"</c:if> value="${list.targetCode}">${list.targetName}</option>
											</c:forEach>
									</select></td>
								</tr>

								<tr class="oneTargetSwitch">
									<td align="right" valign="middle"><b>部门：</b></td>
									<td><select class="deptCode"
										style="width: 150px; height: 30px; border-radius: 5px; border: 1px solid #90C1E6;"
										name="deptCode" class="view-field">
											<option value="">请选择</option>
											<c:forEach items="${dictItem}" var="list">
												<option value="${list.code }"
													<c:if test="${target.deptCode==list.code}">selected="selected"</c:if>>${list.name}</option>
											</c:forEach>
									</select>
									<td>&nbsp;&nbsp;<b>是否实时：</b><select class="isRealtime"
										style="width: 200px; height: 30px; border-radius: 5px; border: 1px solid #90C1E6;"
										name="isRealtime" class="view-field">
											<option value="0"
												<c:if test="${target.isRealtime=='0'}">selected="selected"</c:if>>否</option>
											<option value="1"
												<c:if test="${target.isRealtime=='1'}">selected="selected"</c:if>>是</option>

									</select>&nbsp;&nbsp;</td>
								</tr>
								<tr class="oneTargetSwitch">
									<td align="right" valign="middle">&nbsp;&nbsp;<b>计算频率：</b></td>
									<td><select
										style="width: 200px; height: 30px; border-radius: 5px; border: 1px solid #90C1E6;"
										name="calRate" class="view-field">
											<option value="">请选择</option>
											<c:forEach items="${calRates}" var="list">
												<option value="${list.code }"
													<c:if test="${target.calRate==list.code}">selected="selected"</c:if>>${list.name}</option>
											</c:forEach>
									</select>&nbsp;&nbsp;&nbsp;<b>单位：</b><select
										style="width: 150px; height: 30px; border-radius: 5px; border: 1px solid #90C1E6;"
										name="fieldCode" class="view-field">
											<option value="">请选择</option>
											<c:forEach items="${fieldCode}" var="list">
												<option value="${list.code }"
													<c:if test="${target.fieldCode==list.code}">selected="selected"</c:if>>${list.name}</option>
											</c:forEach>
									</select></td>
									<td style="float: left;"><b>是否区间统计指标：</b><select
										style="width: 200px; height: 30px; border-radius: 5px; border: 1px solid #90C1E6;"
										name="isStatisTarget" class="view-field">
											<option value="0" <c:if test="${target.isStatisTarget=='0'}">selected="selected"</c:if>>否</option>
											<option value="1" <c:if test="${target.isStatisTarget=='1'}">selected="selected"</c:if>>是</option>
									</select>&nbsp;&nbsp;</td>
								</tr>
								<tr class="oneTargetSwitch">
									<td align="right" valign="middle"><b>是否预警：</b></td>
									<td><select class="deptCode"
										style="width: 150px; height: 30px; border-radius: 5px; border: 1px solid #90C1E6;"
										name="isWarnTarget" class="view-field">
												<option value="0" <c:if test="${target.isWarnTarget=='0'}">selected="selected"</c:if>>否</option>
												<option value="1" <c:if test="${target.isWarnTarget=='1'}">selected="selected"</c:if>>是</option>
									</select>
									<b style="margin-left: 25px;">是否数值指标：</b>
									<select class="isMath"
										style="width: 150px; height: 30px; border-radius: 5px; border: 1px solid #90C1E6;"
										name="isMath" class="view-field">
												<option value="1" <c:if test="${target.isMath=='1'}">selected="selected"</c:if>>是</option>
												<option value="0" <c:if test="${target.isMath=='0'}">selected="selected"</c:if>>否</option>
									</select>
									</td>
									
									<td align="left" valign="middle"><b>是否经营分析：</b>
										<select
										style="width: 200px; height: 30px; border-radius: 5px; border: 1px solid #90C1E6;"
										name="isManageTarget" class="view-field">
											<option value="0" <c:if test="${target.isManageTarget=='0'}">selected="selected"</c:if>>否</option>
											<option value="1" <c:if test="${target.isManageTarget=='1'}">selected="selected"</c:if>>是</option>
										</select>&nbsp;&nbsp;
									</td>
								</tr>
								<tr class="oneTargetSwitch">
									<td align="right" valign="middle"><b>指标计算公式说明：</b></td>
									<td><input type="text" name="calFormula"
										value="${target.calFormula}"
										style="width: 100%; height: 30px; border-radius: 5px; border: 1px solid #90C1E6;" /></td>
								</tr>
								<tr class="oneTargetSwitch">
									<td align="right" valign="middle"><b>指标定义说明：</b></td>
									<td><textarea rows="3" cols="50"
											style="width: 100%; border-radius: 5px; border: 1px solid #90C1E6;"
											name="tarDefSpec">${target.tarDefSpec}</textarea></td>
								</tr>
							</table>
						</div>
					</div>
					<div class="targetSwitch">
						<div class="new_jihui_warp" style="margin-right: 9px;">
							<div class="new_common_bg">
								<span>分组维度</span>
							</div>
							<div class="new_jihui">
								<table class="gridtable" width="100%" border="0" cellspacing="0"
									cellpadding="0">
									<c:forEach items="${groDim}" var="list">									
										<tr gdId="${list.gdId}">
											<td width="8%" align="right" valign="middle"></td>
											<td width="8%" valign="middle"><input name="gdIds"
												type="checkbox" checked="checked" onchange="sel(this)" value="${list.gdId}" /> <b><u><a
														href="javascript:void(0)" class="slt">${list.groDimTypeName}:</a></u></b></td>
											&nbsp;&nbsp;
											<td width="8%" gdId="${list.gdId}" style='width: 60px'
												class='append'></td>				
										</tr>
									</c:forEach>
								</table>
							</div>
							<div class="new_common_bg" width="none">
								<span>筛选维度</span>
							</div>
							<div class="new_jihui" >
								<table style="table-layout: fixed;" class="gridtable"
									width='1026px' border="0" cellspacing="0" cellpadding="0">
									<tr class="queryDimTr">
										<td width='95px'></td>
										<td>
										<c:forEach items="${queryDim}" var="list" varStatus="status">
										<div style='display: inline-block;width: 135px;'>
											<input
											qdIds="${list.qdId}" name="qdIds" type="checkbox"
											<c:if test="${fn:contains(qdIds,list.qdId)}">checked="checked"</c:if>
											value="${list.qdId}" /> <b><a href="javascript:void(0)"
												onclick="" style="margin-right: 10px;">${list.queryDimName}</a></b>
										</div>
												</c:forEach>
													</td>
										
									</tr>
								</table>
							</div>
						</div>
					</div>
					<div class="new_jihui_menu" style="width: 99.3%;">
						<a id="save" class="new_jihui_menu1">确认</a> 
						<a class="new_jihui_menu3 a_post backPage" href="javascript:void(-1);">取消</a>
					</div>
				</form>
			</div>
		</div>


	</div>
</body>
</html>