<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/util.tld" prefix="u"%>
<html>
<head>
<title>模板任务管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	$(function() {
		$('#pager').pager('${pageContext.request.contextPath}/admin/task/list',15,$('#templetView').view().on('add',function(data){
			(function(zhis){

			})(this);
		}));
		
		$('#templetAjaxForm').ajaxForm(function(data) {
			$('#pager').pager().setPostData(Ace.parseQuery($('#templetAjaxForm').serialize()));
			$('#pager').pager().setJSON(data);
		});
		
		$("#startTemplet").click(function() {
			$("#saveTempletForm")[0].reset();
			$('#saveTempletForm').data("dialog", $.dialog({
				title : "是否启动",
				content : $('#saveTempletForm')[0]
			}));
		});
		$('#redisAjaxForm').ajaxForm(function(data) {
			var str="";
			var index=1;
			$('#head').nextAll().remove();
			if(data.length!=0){
				for(var key in data){
				    str+="<tr><td>"+index+"</td><td>"+index+"</td><td>"+key+"</td><td>"+data[key]+"</td></tr>";
				    index+=1;
				}
				$('#result').append(str);
			}
		});
		$('#resultAjaxForm').ajaxForm(function(data) {
			var str="";
			var index=1;
			$('#head1').nextAll().remove();
			if(data.length!=0){
				for(var key in data){
					if(key=="data"){
						$.each(data[key],function(k,v){
							str+="<tr><td><input type='checkbox' style='margin-right: 25px;' name='keyStr' class='keyStr' value="+v+"></td><td>"+k+"</td><td>"+v+"</td></tr>";
						    index+=1;
						})
					    
					}
				}
				$('#result1').append(str);
			}
		});
		$('#saveTempletForm').ajaxForm(function(data) {
			$('#saveTempletForm').data("dialog").close();
			$.msgbox({time: 2000,msg: "已启动!",icon:"success"});
			$('#pager').pager().reload();
		});
		
		$("#clearTemplet").click(function() {
			$('#clearTempletForm').data("dialog", $.dialog({
				title : "是否删除",
				content : $('#clearTempletForm')[0]
			}));
		});
		
		$('#clearTempletForm').ajaxForm(function(data) {
			$('#clearTempletForm').data("dialog").close();
			$.msgbox({time: 2000,msg: "已删除!",icon:"success"});
			$('#pager').pager().reload();
		});
		$('#pager').pager().reload();


		$("#tempCode").bind("change",function(){
			var option='<option value="">请选择</option>';
			  $('#regCode').html('');
			  var temp='',id='';
				  $.getJSON('${pageContext.request.contextPath}/admin/temReg/findTemReg',{tempId:$("#tempCode").val()},function(data){    		 
					  $.each(data, function (n, value) { 	    			
				   			option +="<option value="+value.regCode+">"+value.regName+"</option>"		    			
				   		});	
				   		$('#regCode').html(option);
				  });
		});
		$("#subCode").bind("change",function(){
			var option='<option value="">请选择</option>';
			  $('#tarCode').html('');
			  var temp='',id='';
				  $.getJSON('${pageContext.request.contextPath}/admin/target/findTargetAll',{subCode:$("#subCode").val()},function(data){    		 
					  $.each(data, function (n, value) { 	    			
				   			option +="<option value="+value.targetCode+">"+value.targetName+"</option>"		    			
				   		});	
				   		$('#tarCode').html(option);
				  });
		});
		$('#find').click(function(){
			
			var regCode=$("#regCode").val();
			var tarCode=$("#tarCode").val();
			if($.trim(regCode)=='' || $.trim(regCode)==null){
				$.dialog({top:50,content: '请选择区域名称!', time: 2000});return;
			}
			if($.trim(tarCode)=='' || $.trim(tarCode)==null){
				$.dialog({top:50,content: '请选择指标名称!', time: 2000});return;
			}
			$('#find').submit();
			
		})
		
		$("#clearKey").click(function(){
			$.getJSON('${pageContext.request.contextPath}/admin/cache/deleteKey',{keyStr:$("input:checkbox[name='keyStr']").val(),regCode:$("#regCode").val(),subCode:$("#subCode").val()},function(data){    		 
				$('#find').click();
			});
		})
		
		
		
		/* $("#selectAll").click( 
				function(){ 
					if(this.checked){
						$("table input[type=checkbox]").attr("checked",true);
						$(this).parent().parent().next().find(".keyStr").prop("checked",true);	
						//$("input[name='selkey']").attr('checked',true);
					//	$("input[name='selkey']").each(function(){$(this).attr('checked',true)}); 
						}else{ 
							$(this).parent().parent().next().find(".keyStr").prop("checked",false);	
						} 
				} 
		); */
		$("#selectAll").click(
				function(){ 
					if(this.checked){
				 		$("#head1 input[type=checkbox]").parent().parent().nextAll().find("input").prop("checked",true) 
					}else{ 
						$("#head1 input[type=checkbox]").parent().parent().nextAll().find("input").prop("checked",false)
					} 
				}
		);
    });
	
	function start(tempLink){
		var httpStr="";
		httpStr="${pageContext.request.contextPath}/admin/cache/start"+tempLink;
		$.ajax({
			  type: 'POST',
			  url: httpStr,
			  success: function(){
				  $.msgbox({time: 2000,msg: "已启动!",icon:"success"});
			  }
		});
	}
	function clear1(tempLink){
		var httpStr="";
		httpStr="${pageContext.request.contextPath}/admin/cache/clear"+tempLink;
		$.ajax({
			  type: 'POST',
			  url: httpStr,
			  success: function(){
				  $.msgbox({time: 2000,msg: "已删除!",icon:"success"});
			  }
		});
		$('#pager').pager().reload();
	}
	
	function startSub(){		
		$('#saveTempletForm').submit();
	}
	function clearSub(){
		$('#clearTempletForm').submit();
	}
	
</script>
</head>
<body>
	<div class="business_title">任务管理</div>
	<div class="col_lg_04" style="width: 1203px">
		<div class="business_search_list_warp" style="width: 95%">
			<input type="button" id="startTemplet" value="全部启动" style="width: 80px;height: 30px; border-radius: 5px;border:1px solid #90C1E6;" >
			<input type="button" id="clearTemplet" value="全部清除" style="width: 80px;height: 30px; border-radius: 5px;border:1px solid #90C1E6;" >
			
			<table cellspacing="0" cellpadding="0" class="t-list table"id="templetView">
				<tbody>
					<tr>
						<th>序号</th>
					    <th>模板名称</th>
						<th style="border-right: 1px solid #ccc; width: 100px">操作</th>
					</tr>
					<tr class="tb_tr_content template" name="default">
					    <td>{index:seq()}</td>
					    <td>{tempName}</td>
						<td style="border-right: 1px solid #ccc"><span class="span_edit">
							<a href="${pageContext.request.contextPath}/admin/task/regTask?tempId={tempId}&tempLink={tempLink}"> <img
									style="float: left;width:17px; margin-left: 20px;"
									src="${pageContext.request.contextPath}/static/images/huatai_region.png"
									title="开启">
							</a>
						</span></td>
					 </tr>
					 <tr>
						<td>9</td>
					    <td>虚拟区域</td>
						<td style="border-right: 1px solid #ccc">
							<a href="javascript:void(0)"
								onclick="start('Virt')">开启
							</a>
							<a href="javascript:void(0)"
								onclick="clear1('Virt')">删除
							</a>
						</td>
					</tr>	
					 <tr>
						<td>10</td>
					    <td>预警监控</td>
						<td style="border-right: 1px solid #ccc">
							<a href="javascript:void(0)"
								onclick="start('Yjjk')">开启
							</a>
							<a href="javascript:void(0)"
								onclick="clear1('Yjjk')">删除
							</a>
						</td>
					</tr>	
				</tbody>
			</table>
			<div id="pager"></div>
		</div>
		<div class="clear"></div>
	</div>
	
	<div class="col_lg_04" style="width: 1203px">
		<div class="business_search_list_warp" style="width: 95%">
			<form id="redisAjaxForm"
				action="${pageContext.request.contextPath}/admin/cache/redisValue"
				method="post" style="width:900px">
				<input type="hidden" name="pageSize" value="15" />
				<table>
					<tbody>
						<tr>
							<td style="text-align: right" >查询KEY:</td>
							<td><input style="border-radius:5px; width:400px;height: 30px;border:1px solid #90C1E6;" type="text" name="keys" id="keys"/>&nbsp;&nbsp;<input type="submit" value="查&#12288;询"
						style="width: 60px;height: 25px;border-radius: 5px;border:1px solid #90C1E6;"></td>
						</tr>
					</tbody>
				</table>
			</form>
			<table cellspacing="0" cellpadding="0" class="t-list table"id="templetView">
				<tbody id="result">
					<tr id="head">
						<th>序号</th>
					    <th>KEY</th>
					    <th>VALUE</th>
					</tr>
					
				</tbody>
			</table>
		</div>
	</div>
	
	
	<div class="col_lg_04" style="width: 1203px">
		<div class="business_search_list_warp" style="width: 95%">
			<form id="resultAjaxForm"
				action="${pageContext.request.contextPath}/admin/cache/queryKey"
				method="post" style="width:900px">
				<input type="hidden" name="pageSize" value="15" />
				<table>
					<tbody>
						<tr>
							<td style="text-align: right" >模板:</td>
							<td>
								<select style="width: 201px;height: 30px;border-radius: 5px;border:1px solid #90C1E6;" name="tempCode" id="tempCode"  class="view-field">
										<option value="">请选择</option>
										<c:forEach items="${templet}" var="list">
											<option value="${list.tempId}">${list.tempName}</option>
										</c:forEach>
									</select>
							</td>
							<td style="text-align: right;padding-left: 30px;" >区域:</td>
							<td>
								<select style="width: 201px;height: 30px;border-radius: 5px;border:1px solid #90C1E6;" id="regCode" name="regCode"  class="view-field">
								</select>
							</td>
						</tr>
						<tr style="text-align: right;height: 10px;"></tr>
						<tr>
							<td style="text-align: right" >主题:</td>
							<td>
								<select style="width: 201px;height: 30px;border-radius: 5px;border:1px solid #90C1E6;" name="subCode" id="subCode" class="view-field">
										<option value="">请选择</option>
										<c:forEach items="${subject}" var="list">
											<option value="${list.targetCode}">${list.targetName}</option>
										</c:forEach>
								</select>
							</td>
							<td style="text-align: right;padding-left: 30px;" >指标:</td>
							<td>
								<select style="width: 201px;height: 30px;border-radius: 5px;border:1px solid #90C1E6;" name="tarCode" id="tarCode"  class="view-field">
								</select>
							&nbsp;&nbsp;<input type="button" id="find" value="查&#12288;询"
						style="width: 60px;height: 25px;border-radius: 5px;border:1px solid #90C1E6;"></td>
						</tr>
						<tr>
							<td style="text-align: right;height: 25px;" ></td>
						</tr>
					</tbody>
				</table>
			</form>
			<input type="button" id="clearKey" value="一键删除"
						style="width: 80px;height: 30px;border-radius: 5px;border:1px solid #90C1E6;">
			<input type="hidden" id="keyStr" name="keyStr" value="">
			<table cellspacing="0" cellpadding="0" class="t-list table" >
				<tbody id="result1">
					<tr id="head1">
						<th width="100px"><input type="checkbox" name="selkeys" id="selectAll"/>全选</th>
						<th>序号</th>
					    <th>VALUE</th>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	
	
	<form id="saveTempletForm" style="display: none;"
		  action="${pageContext.request.contextPath}/admin/cache/startAll"
		  method="post">
		<div id="tagContent2" class="tagContent tagContent_div"
			style="display: block; background: #F7F7F7">
			<table style="line-height: 30px">
				<tbody>
					<tr>
						<td class="text_left"><input type="button" value="启动" onclick="startSub()"
							class="search_btn"><input type="button"
							value="取 消" class="search_btn close"></td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
	<form id="clearTempletForm" style="display: none;"
		  action="${pageContext.request.contextPath}/admin/cache/clearAll"
		  method="post">
		  <input type="hidden" name="text" value="0">
		<div id="tagContent2" class="tagContent tagContent_div"
			style="display: block; background: #F7F7F7">
			<table style="line-height: 30px">
				<tbody>
					<tr>
						<td class="text_left"><input type="button" value="删除" onclick="clearSub()"
							class="search_btn"><input type="button"
							value="取 消" class="search_btn close"></td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
</body>
</html>