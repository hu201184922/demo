<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/util.tld" prefix="u"%>
<html>
<html decorator="null">
<head>
<title>三四级机构标签管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
$(function() {
	$('#pager').pager('${pageContext.request.contextPath}/admin/organization/getTeamlist',15,$('#organizationView').view().on('add',function(data){
		(function(zhis){
			 zhis.target.find('.removed');
		})(this);
	}));
	
	$('#organizationAjaxForm').ajaxForm(function(data) {
		$('#pager').pager().setPostData(Ace.parseQuery($('#organizationAjaxForm').serialize()));
		$('#pager').pager().setJSON(data);
	});
	
	$('#pager').pager().reload();
	$("#createorganization").live('click',function(){
		var teamComCode=$(this).attr('name');
		var status = $(this).attr('status');
		var year = $(this).attr('year').split(", ");
		$("#orgCode").val(teamComCode);
		$("#status").val(status);
		$("#teamComCode").val(teamComCode);
		$("input:[type='checkbox'][name='year']").attr("checked",false);
		$(year).each(function(i){
			$("input:[type='checkbox'][name='year']").each(function(j){
				if(year[i]==$(this).attr("value")){
					 $(this).attr('checked',true);
				}
			});
		});
		
		$('#saveorganizationForm').data("dialog", $.dialog({
			title : "选择年",
			content : $('#saveorganizationForm')[0]
		}));
		$('#pager').pager().reload();
		/* if ( status==0 ) {
			$("#orgCode").val(teamComCode);
			$('#saveorganizationForm').data("dialog", $.dialog({
				title : "选择年",
				content : $('#saveorganizationForm')[0]
			}));
			$('#pager').pager().reload();
		} else {
			$.post("${pageContext.request.contextPath}/admin/organization/cancel", {"status" :status, 'teamComCode': teamComCode}, function(data){
				if(data==1){
					$.msgbox({time: 2000,msg: "取消成功!",icon:"success"});
					$('#pager').pager().reload();
				}
			});
		} */
		
	});
	
	$('#pager').pager().reload();
	
	$(".provComCode").change(function(){
		var trs='<option value="">--请选择--</option>';
		if(!$(this).val()==''){
			$('.countComCode').html('');
			$.getJSON('${pageContext.request.contextPath}/admin/organization/loadData',{provComCode:$(this).val()},function(data){
				$.each(data, function (n, value) { 
	    			trs += "<option value="+value.countComCode+">"+value.actuTeamComShortName+"</option>";    			
	    		});
	    		$(".countComCode").append(trs);
	    		return true;
			});
		}else{
			$('.countComCode').append('');	
		}	
	})
	
	$('#saveorganizationForm').ajaxForm(function(data) {
		$('#saveorganizationForm').data("dialog").close();
		$('#pager').pager().reload();
	});
});	
	function uploadTem(){
		$('#saveImportDataForm')[0].reset();
		$('#saveImportDataForm').data("dialog", $.dialog({
			title : "上传文件",
			content : $('#saveImportDataForm')[0]
		}));
	}

	function saveFileForm(){
		var cate = $('select[name="uploadCate"] option:selected').val();
		var file = $('input[name="uploadTemFile"]').val();
		if (cate == -1) {
			$.msgbox({time: 2000,msg: "请选择上传模板类型", icon: "error"});return;
		}
		if (file == "" || file == null) {
			$.msgbox({time: 2000,msg: "请上传文件", icon: "error"});return;
		}
		$("#saveImportDataForm").submit();
	}
	
	$('#saveImportDataForm').ajaxForm(function(data) {
		var ic;
		data.result==true?ic="success":ic="error";
		$.msgbox({time: 5000,msg: data.message, icon: ic});
		$('#saveImportDataForm').data("dialog").close();
		$('#pager').pager().reload();
	});
	
	function validate(){
		$('#saveorganizationForm').submit();
	}

</script>
</head>
<body>
	<div class="business_title">三四级机构管理</div>
	<div class="col_lg_04" style="width: 1203px">
		<div style="line-height: 30px; background: #EBEFF2;border-bottom: 1px solid #FFFFFF;">
			<a class="org3" href="${pageContext.request.contextPath}/admin/organization/index"
				style="padding: 0 11px;
             display: inline-block;"><b>跨越五千</b></a>
			<a class="org4" 
				style="padding: 0 11px;border: 1px #FFF solid;background-color:#FFF;
             display: inline-block;"><b>千万登峰</b></a>
		</div>	
		<div class="aa">
			<div class="business_search_list_warp" style="width: 95%">
				<div>
					<div class="business_search_left">
						<form id="organizationAjaxForm"
							action="${pageContext.request.contextPath}/admin/organization/getTeamlist"
							method="post">
							<input type="hidden" name="pageSize" value="15" />
							<table>
								<tbody>
									<tr>
										<td style="text-align: right">分公司：</td>
										<td colspan="2">
											<select class="provComCode" name="provComCode" style="width: 200px; height: 30px; border-radius: 5px; border: 1px solid #90C1E6;" class="view-field">
												<option value="">--请选择--</option>
												<c:forEach items="${organizations}" var="list">
													<option value="${list.provComCode}" provComCode="${list.provComCode}">${list.teamComShortName}</option>
												</c:forEach>
											</select>&#12288;&#12288;</td>
										<td style="text-align: right">中支：</td>
										<td colspan="2">
											<select class="countComCode" name="countComCode" style="width: 200px; height: 30px; border-radius: 5px; border: 1px solid #90C1E6;" mapping="countComCode" class="view-field">
												<option value="">--请选择--</option>
											</select>&#12288;&#12288;</td></td>
										<td>&#12288;&#12288;<input type="submit"
											value="查&#12288;询"
											style="font-size: 14px; width: 65px; height: 25px; border-radius: 5px; border: 1px solid #90C1E6;"></td>
									</tr>
									<tr height="20px"></tr>
								</tbody>
							</table>
						</form>
						<a href="${pageContext.request.contextPath}/admin/upload/downloadModel?modelName=千万登峰.xlsx" style="text-align: center;background-color: #dedede;line-height: 30px;padding: 0 11px;
            display: inline-block;width: 60px;height: 30px;border-radius: 5px;cursor: pointer;position: absolute;right: 140px;top: 304px;">下载模板</a>
						<button onclick="uploadTem()" style="font-weight: border; width: 70px;height: 30px;border-radius: 5px;color: green;cursor: pointer;position: absolute;right: 35px;
    top: 304px;">导入</button>
					</div>
				</div>
				<table cellspacing="0" cellpadding="0" class="t-list table"
					id="organizationView">
					<tbody>
						<tr>
							<th width="13%">序号</th>
							<th width="13%">成本中心编号</th>
							<th width="13%">成本中心名称</th>
							<!-- <th width="13%">设置状态</th> -->
							<th width="13%">有效年</th>
							<th style="border-right: 1px solid #ccc; width: 5%">操作</th>
						</tr>
						<tr class="tb_tr_content template" name="default">
							<td>{index:seq()}</td>
							<td>{costCenterstdCode}</td>
							<td>{costCenterdevName}</td>
							<!-- <td>{status:dict({1:'已设置',0:'未设置'})}</td> -->
							<td>{year:dict({'未设置':''})}</td>
							<td style="border-right: 1px solid #ccc"><span>			
								<a class="status" style="line-height: 25px;padding: 0 11px;border: 1px #dedede solid;
            border-radius: 3px; display: inline-block;background-color: #dedede;" year="{year}" status="{status}" id="createorganization" name="{costCenterstdCode}">{status:dict({0:'设置',1:'取消'})}</a>
							</span></td>
						</tr>

					</tbody>
				</table>
				<div id="pager"></div>
			</div>
			<div class="clear"></div>
		</div>
	</div>
	<form id="saveorganizationForm" style="display: none;"
		action="${pageContext.request.contextPath}/admin/organization/insertRelTeam"
		method="post">
		<div id="tagContent2" class="tagContent tagContent_div"
			style="display: block; background: #F7F7F7">
			<table style="line-height: 30px">
				<tbody>
					<tr>
						<input id="orgCode" type="hidden" name="orgCode" value=""/>
						<input id="teamComCode" type="hidden" name="teamComCode" value=""/>
						<td style="text-align: right">设置年：</td>
						<td colspan="2">
						<c:forEach items="${years }" var="list">
							<input type='checkbox' name='year' value="${list}"/>${list}
						</c:forEach>
					
					<!-- <input id="orgCode" type="hidden" name="orgCode" value=""/>
						<td style="text-align: right">设置年：</td>
						<td colspan="2"><select
							style="width: 150px; height: 30px; border-radius: 5px; border: 1px solid #90C1E6;"
							name="year"  class="view-field">
								<option value="0">--请选择--</option>
								<option value="2017">2017</option>
								<option value="2018">2018</option>
								<option value="2019">2019</option>
						</select></td>
						<td></td> -->
					</tr>
					<tr>
						<td height="10px" colspan="2"></td>
					</tr>
					<tr>
						<td></td>
						<td class="text_left"><input type="button"
							onclick="validate()" value="提 交" class="search_btn">&nbsp;&nbsp;<input
							type="button" value="取 消" class="search_btn close"></td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
	
	<form name="ImportDataForm" style="display: none;" id="saveImportDataForm" action="${pageContext.request.contextPath}/admin/upload/insertFileData"
			method="post" enctype="multipart/form-data">
		<div id="tagContent2" class="tagContent tagContent_div"
			style="display: block; background: #F7F7F7">
			<table style="line-height: 30px">
				<tbody>
					<tr>
						<td class="text_right">选择上传类型：</td>
						<td class="text_left">
							<select name="uploadCate" style="color: black;width: 201px; height: 30px; border-radius: 5px; border: 1px solid #90C1E6;">
								<option value="-1" selected="selected">--请选择--</option>
								<option value="8">跨越五千</option>
								<option value="9">千万登峰</option>
							</select>
						</td>
					</tr>
					<tr><td height="20px" colspan="2"></td></tr>
					<tr>
						<td class="text_right">选择上传文件：</td>
						<td class="text_left"><input type="file" name="uploadTemFile"></td>
					</tr>
					<tr>
						<td></td>
						<td class="text_left"><input type="button" value="开始导入" id="btnSave" onclick="saveFileForm()"
							class="search_btn"/>&nbsp;&nbsp;<input type="button"
							value="取 消" class="search_btn close"/></td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
</body>
</html>