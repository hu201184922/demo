<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/util.tld" prefix="u"%>
<html>
<head>
<title>接口字段维护</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	function back(){
		window.history.back(-1);
	};
	$(function() {
		$('#pager').pager('${pageContext.request.contextPath}/admin/interField/list?interId=${inter.interId}',15,$('#interFieldView').view().on('add',function(data){
			(function(zhis){
				var deleteConfirm = new Ace.awt.ConfirmBox({
					widget : $('<div style="background-color:#FFFFFF; display:none; text-align:center;  line-height:50px;width: 100px;height: 50px; border-radius: 5px;border:1px solid #90C1E6;"><span class="yesorno"><a href="javascript:void(0)" class="confirm">确定</a>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" class="cancel">取消</a></span></div>'),
					trigger : zhis.target.find('.removed'),
					positionAdjust : [ 6, -45 ]
				});	
				deleteConfirm.on('confirm',function(w, t) {
					$.getJSON('${pageContext.request.contextPath}/admin/interField/delete',{fieldId:data.fieldId},function(bit){
						if(bit==true){
							$.msgbox({time: 2000,msg: "删除成功!",icon:"success"});
						}
						$('#pager').pager().reload(function(){
							zhis.target.remove();
						});
					});
				});
				zhis.target.find('.save').click(function(){
					$.getJSON('${pageContext.request.contextPath}/admin/interField/update',zhis.getData(),function(data){
						$.msgbox({time: 2000,msg: "修改成功!",icon:"success"});
						zhis.setData(data);
						zhis.setTemplate('default',true);
					});
				});
				
				zhis.target.find('.edit').click(function(){
					$('#saveInterForm').data("dialog", $.dialog({
						title : "添加接口表字段",
						content : $('#saveInterForm')[0]
					}));
					var str=$(this).attr('name').split(",");
					var fieldId=$('#fieldId').val(str[0]);
					var fieldCode=$('#fieldCode').val(str[1]);
					var fieldName=$('#fieldName').val(str[2]);
					var fieldType=$('#fieldType').val(str[3]);
					var fieldType=$('#isDim').val(str[4]);
					var dimCode=$('#dimCode').val(str[5]);
				});
			})(this);
		}));
		$('#pager').pager().reload();
		$('#deptAjaxForm').ajaxForm(function(data) {
			$('#pager').pager().setPostData(Ace.parseQuery($('#deptAjaxForm').serialize()));
			$('#pager').pager().setJSON(data);
		});
		
		$("#createdept").click(function() {
			$('#saveInterForm')[0].reset();
			$('#fieldId').val("");
			$('#saveInterForm').data("dialog", $.dialog({
				title : "添加接口表字段",
				content : $('#saveInterForm')[0]
			}));
		});
		$('#saveInterForm').ajaxForm(function(data) {
			$('#saveInterForm').data("dialog").close();
			$('#pager').pager().reload();
		});
		query();
		$('.interId').val('${inter.interId}');
});	
	function query(){
		
		var postData ={
				"interId":"${inter.interId}",
		}
		$('#pager').pager().setPostData(postData);
		$('#pager').pager().reload();
	}
	function validate(){
		var interId = $('.interId').val();
		var fieldCode = $("#tagContent2 input[name='fieldCode']").val();
		var fieldName = $("#tagContent2 input[name='fieldName']").val();
		var fieldType = $("#tagContent2 select[name='fieldType']").find('option:selected').val();
		var dimCode = $("#tagContent2 select[name='dimCode']").val();
		if($.trim(interId)=='' ||$.trim(interId)==null){
			$.dialog({content: '请选择表名', time: 2000});return;
		}
		if($.trim(fieldCode)=='' ||$.trim(fieldCode)==null){
			$.dialog({content: '请输入字段编号', time: 2000});return;
		}
		if($.trim(fieldName)=='' ||$.trim(fieldName)==null){
			$.dialog({content: '请输入字段名称', time: 2000});return;
		}
		$('#saveInterForm').submit();
	}
</script>
</head>
<body>
	<div class="business_title">接口字段维护</div>
	<div class="col_lg_04" style="width: 1203px">
		<div class="business_search_list_warp" style="width: 95%">
			<input type="button" id="createdept" value="新建" style="width: 80px;height: 30px; border-radius: 5px;border:1px solid #90C1E6;">
		 	<a style="position: absolute; margin-left: 83%; line-height: 25px;padding: 0 11px;border: 1px #26bbdb solid;
            				border-radius: 3px; display: inline-block;" onclick="back()">返回</a>
		 	<div class="business_search">
				<div class="business_search_left">
					<form id="deptAjaxForm"
						action="${pageContext.request.contextPath}/admin/interField/list"
						method="post">
						<input type="hidden" name="pageSize" value="15" />
						<input type="hidden" name="interId" value="${inter.interId}">
						<table>
							<tbody>
								<tr>
									<td style="text-align: right">字段编号：</td>
									<td colspan="2"><input type="text" style="width: 250px;height: 30px;border-radius: 5px;border:1px solid #90C1E6;" style="width: 200px;"
										name="name" class="text">&#12288;&#12288;</td>
									<td><input type="submit"  value="查&#12288;询"
										style="width: 60px;height: 25px;border-radius: 5px;border:1px solid #90C1E6;"></td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
			</div>
			
			<table cellspacing="0" cellpadding="0" class="t-list table"id="interFieldView">
				<tbody>
					<tr>
						<th width="">序号</th>
						<th width="">字段编码</th>
						<th width="">字段名称</th>
						<th width="">字段类型</th>
						<th width="">是否维度字段</th>
						<th width="">维度编码</th>
						<th style="border-right: 1px solid #ccc; width: 8%">操作</th>
					</tr>
					<tr class="tb_tr_content template" name="default">
						<td style="display:none">{fieldId}</td>
						<td>{index:seq()}</td>
						<td>{fieldCode}</td>
						<td>{fieldName}</td>
						<td>{fieldType:dict({01:'CHAR',2:'VARCHAR2',3:'NUMBER',4:'DATE'})}</td>
						<td>{isDim:dict({1:'是',0:'否','':''})}</td>
						<td>{dimCode}</td>
						<td style="border-right: 1px solid #ccc"><span
							class="span_edit"> <a href="javascript:void(0)"
								class="edit" name="{fieldId},{fieldCode},{fieldName},{fieldType},{isDim},{dimCode}"> <img style="float: left;width:17px;"
								src="${pageContext.request.contextPath}/static/images/huatai_edit.png"
									title="修改" />
							</a> <a href="javascript:void(0)" class="removed"> <img
									style="float: left;width:17px;margin-left: 5px;"
									src="${pageContext.request.contextPath}/static/images/huatai_delete.png"
									title="删除">
							</a>
						</span></td>
					</tr>
				</tbody>
			</table>
			<div id="pager"></div>
		</div>
		<div class="clear"></div>
	</div>
	<form id="saveInterForm" style="display: none;"
		action="${pageContext.request.contextPath}/admin/interField/create"
		method="post">
		<div id="tagContent2" class="tagContent tagContent_div"
			style="display: block; background: #F7F7F7">
			<table style="line-height: 30px">
				<tbody>
				
					<tr>
						<td class="text_right">表名编号：</td>
						<td class="text_left">
						<input type="hidden" class="interId" id="interId"  name="interId" value="${inter.interId}"><input type="hidden" class="fieldId" id="fieldId" name="fieldId" value="">${inter.interName }
						</td>
					</tr>
						<tr>
						<td class="text_right">字段编号：</td>
						<td class="text_left"><input style="width: 178px;height: 25px;border-radius: 5px;border:1px solid #90C1E6;" id="fieldCode" name="fieldCode" type="text" />
						</td>
					</tr>
					</tr>
						<tr>
						<td class="text_right">字段名称：</td>
						<td class="text_left"><input style="width: 178px;height: 25px;border-radius: 5px;border:1px solid #90C1E6;" id="fieldName" name="fieldName" type="text" />
						</td>
					</tr>
					<tr>
						<td class="text_right">字段类型：</td>
						<td class="text_left">
						<select style="width: 180px;height: 25px;border-radius: 5px;border:1px solid #90C1E6;"id="fieldType" name="fieldType" class="view-field">
							<option value="">--请选择--</option>
							<c:forEach items="${dictItem }" var="list">
								<option value="${list.code}">${list.name}</option>
							</c:forEach>
			
						</select>
						</td>
					</tr>
					<tr>
						<td class="text_right">是否维度字段：</td>
						<td class="text_left"><select style="width: 180px;height: 25px;border-radius: 5px;border:1px solid #90C1E6;" id="isDim" name="isDim" class="view-field">
							<option value="">--请选择--</option>
							<option value="1">是</option>
							<option value="0">否</option>
						</select></td>
					</tr>
					</tr>
						<tr>
						<td class="text_right">维度名称：</td>
						<td class="text_left"><select style="width: 180px;height: 25px;border-radius: 5px;border:1px solid #90C1E6;" id="dimCode" name="dimCode" class="view-field">
							<option value="">--请选择--</option>
							<c:forEach items="${queryDimCodes}" var="list">
								<option value="${list.queryDimCode}">${list.queryDimName}</option>
							</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td height="10px" colspan="2"></td>
					</tr>
					<tr>
						<td></td>
						<td class="text_left"><input type="button" onclick="validate()" value="提 交"
							class="search_btn">&nbsp;&nbsp;<input type="button"
							value="取 消" class="search_btn close"></td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
</body>
</html>