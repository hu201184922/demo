<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/util.tld" prefix="u"%>
<html>
<head>
<title>上传</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
	.input{width: 300px;height: 30px;border-radius: 5px;border:1px solid #90C1E6;}
	.uploadBtn{position:relative;width: 100px;height:30px; border-radius: 5px;border:1px solid #90C1E6;cursor: pointer;}
</style>
<script type="text/javascript">
	$(function(){
		$('#pager').pager('${pageContext.request.contextPath}/admin/upload/list',15,$('#parameterView').view().on('add',function(data){
			(function(zhis){
				var deleteConfirm = new Ace.awt.ConfirmBox({
					widget : $('<div style="background-color:#FFFFFF; text-align:center; display:none; line-height:50px;width: 100px;height: 50px; border-radius: 5px;border:1px solid #90C1E6;"><span class="yesorno"><a href="javascript:void(0)" class="confirm">确定</a>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" class="cancel">取消</a></span></div>'),
					trigger : zhis.target.find('.removed'),
					positionAdjust : [ 6, -45 ]
				});	
				deleteConfirm.on('confirm',function(w, t) {
					$.getJSON('${pageContext.request.contextPath}/admin/upload/delete',{id:data.itId},function(bit){
						if(bit==true){
							$.msgbox({time: 2000,msg: "删除成功!",icon:"success"});
						}
						$('#pager').pager().reload(function(){
							zhis.target.remove();
						});
					});
				});

				zhis.target.find('.span_edit .edit').on('click',function(){
					$("#itId").val(data.id);
					$('#updateTempletForm').data("dialog", $.dialog({
						title : "修改",
						content : $('#updateTempletForm')[0]
					}));
				});
				zhis.target.find('.save').click(function(){
					$.getJSON('${pageContext.request.contextPath}/admin/upload/update',zhis.getData(),function(data){
						$.msgbox({time: 2000,msg: "修改成功!",icon:"success"});
						zhis.setTemplate('default',true);
						$('#pager').pager().reload();
					});
				});
			})(this);
		}));
		$('#pager').pager().reload();
		
		$('#parameterAjaxForm').ajaxForm(function(data) {
			$('#pager').pager().setPostData(Ace.parseQuery($('#parameterAjaxForm').serialize()));
			$('#pager').pager().setJSON(data);
		});
		
		$('#updateTempletForm').ajaxForm(function(data) {
			$('#updateTempletForm').data("dialog").close();
		});
		
		$('.uploadBtn').click(function(){
			$('#saveImportTempForm')[0].reset();
			$('#saveImportTempForm').data("dialog", $.dialog({
				title : "添加",
				content : $('#saveImportTempForm')[0]
			}));
		});
	
		$('#saveImportTempForm').ajaxForm(function(data) {
			var ic;
			data.result==true?ic="success":ic="error";
			$.msgbox({time: 3000,msg: data.message, icon: ic});
			$('#saveImportTempForm').data("dialog").close();
			$('#pager').pager().reload();
		});
		
		$('#ImportDataForm').ajaxForm(function(data) {
			var ic;
			data.result==true?ic="success":ic="error";
			$.msgbox({time: 2000,msg: data.message, icon: ic});
			$('#ImportDataForm').data("dialog").close();
			$('#pager').pager().reload();
		});
		
		$('#saveImportDataForm').ajaxForm(function(data) {
			var ic;
			data.result==true?ic="success":ic="error";
			$.msgbox({time: 5000,msg: data.message, icon: ic});
			$('#saveImportDataForm').data("dialog").close();
			$('#pager').pager().reload();
		});
	});
	
	function saveForm(){
		var impTempCode = $('#impTempCode').val();
		var impTempName = $('#impTempName').val();
		var impTempFile = $('#impTempFile').val();
		var deptCode = $('#deptCode').val();
		if ($.trim(deptCode) == ''||$.trim(deptCode)==null) {
			$.dialog({time: 2000,content:  "请选择部门！"});
			return;
		}
		if ($.trim(impTempCode) == ''||$.trim(impTempCode)==null) {
			$.dialog({time: 2000,content:  "请输入导入模板编码！"});
			return;
		}
		if ($.trim(impTempFile) == '') {
			$.dialog({time: 2000,content:  "请导入模板！"});
			return;
		}
		$('#saveImportTempForm').submit();
	}
	
	// 上传文件
	function uploadTem(){
		$('#saveImportDataForm')[0].reset();
		$('#saveImportDataForm').data("dialog", $.dialog({
			title : "上传文件",
			content : $('#saveImportDataForm')[0]
		}));
	}
	
	// 上传
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
</script>

</head>
<body>
	<div class="business_title">上传模板</div>
	<div class="col_lg_04" style="width: 1203px">
		<div class="business_search_list_warp" style="width: 95%">
			<input class="uploadBtn" type="button" value="新增模板类型">
			<input type="hidden" name="itId">
		 	<div class="business_search">
				<div class="business_search_left" style="margin-left: -20px;">
					<form id="parameterAjaxForm"
						action="${pageContext.request.contextPath}/admin/upload/list"
						method="post">
						<input type="hidden" name="pageSize" value="15" />
						<table>
							<tbody>
								<tr>
									<td style="text-align: right">模板编号：</td>
									<td><input style="width: 200px;height: 30px;border-radius: 5px;border:1px solid #90C1E6;" type="text"
										name="impTempCode"  class="text">&nbsp;&nbsp;</td>
									<td style="text-align: right">模板名称：</td>
									<td><input style="width: 200px;height: 30px;border-radius: 5px;border:1px solid #90C1E6;" type="text"
										name="impTempName"  class="text">&nbsp;&nbsp;</td>
									<td><input type="submit" value="查&#12288;询"
										style="margin-left: 10px;width: 60px;height: 25px;border-radius: 5px;border:1px solid #90C1E6;"></td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
			</div>
			
			<table cellspacing="0" cellpadding="0" class="t-list table"id="parameterView">
				<tbody>
					<tr>
						<th width="100px">序号</th>
						<th width="100px">模板编号</th>
						<th width="100px">模板名称</th>
						<th width="100px">部门名称</th>
<!-- 						<th width="100px">模板路径</th> -->
						<th style="border-right: 1px solid #ccc; width: 60px">模板操作</th>
						<th style="border-right: 1px solid #ccc; width: 60px">数据操作</th>
					</tr>
					<tr class="tb_tr_content template" name="default">
						<td>{index:seq()}</td>
						<td>{impTempCode}</td>
						<td>{impTempName}</td>
						<td>{deptCode:dict({0:'',101402:'教育培训部',130101:'机构发展部',130107:'个人业务部',130105:'收展部'})}</td>
<!-- 						<td>{impTempPath}</td> -->
						<td style="border-right: 1px solid #ccc"><span
							class="span_edit"> <a href="javascript:void(0)"
								class="switch" template="update"> <img style="float: left;"
								src="${pageContext.request.contextPath}/static/images/xiugai.png"
									title="修改" />
							</a> <a href="javascript:void(0)" class="removed"> <img
									style="float: left;"
									src="${pageContext.request.contextPath}/static/images/del_icon.png"
									title="删除">
							</a></span></td>
						<td style="border-right: 1px solid #ccc">
							<a href="${pageContext.request.contextPath}/admin/upload/downloadModel?modelName={impTempName}"><u>下载模板</u></a>
							<button onclick="uploadTem()" style="font-weight: border; width: 70px;height: 30px;border-radius: 5px;
																					color: green;cursor: pointer;">上传</button>
<!-- 							<a href="javascript:void(0)"><input type="file" name="uploadFile"/> -->
<!-- 								<button style="width: 70px;height: 30px;border-radius: 5px;color: green;cursor: pointer;">开始上传</button> -->
<!-- 							</a> -->
						</td>
					</tr>
					 <tr class="tb_tr_content template" name="update">
						<td>{index:seq()}</td>
						<td><input class="text_wd180 view-field" name="impTempCode" type="text"
							/></td>
						<td>{impTempName}</td>
						<td>{deptCode:dict({0:'',101402:'教育培训部',130101:'机构发展部',130107:'个人业务部',130105:'收展部'})}
						</td>
<!-- 						<td>{impTempPath}</td> -->
						<td style="border-right: 1px solid #ccc"><span
							class="span_edit"> <a href="javascript:void(0)"
								class="save"><img style="float: left;" title="保存"
									src="${pageContext.request.contextPath}/static/images/save.png"></a>&nbsp;
								<a href="javascript:void(0)" class="switch back"
								template="default"><img style="float: left;" title="返回"
									src="${pageContext.request.contextPath}/static/images/fanhui.png"></a>
						</span></td>
						<td>数据操作</td>
					</tr>
				</tbody>
			</table>
			<div id="pager"></div>
		</div>
		<div class="clear"></div>
	</div>
	<form name="ImportTempForm" style="display: none;" id="saveImportTempForm" action="${pageContext.request.contextPath}/admin/upload/create"
			method="post" enctype="multipart/form-data">
		<div id="tagContent2" class="tagContent tagContent_div"
			style="display: block; background: #F7F7F7">
			<table style="line-height: 30px">
				<tbody>
					<tr style="display: none;">
						<td class="text_right">导入模板名称：</td>
						<td class="text_left"><input class="input" type="text" id="impTempName" name="impTempName"></td>
					</tr>
					<tr><td height="20px" colspan="2"></td></tr>
					<tr>
						<td class="text_right">部门：</td>
						<td class="text_left">
							<select class="deptCode" id="deptCode"
										style="width: 150px; height: 30px; border-radius: 5px; border: 1px solid #90C1E6;"
										name="deptCode" class="view-field">
											<option value="">请选择</option>
											<c:forEach items="${dictItem}" var="list">
												<option value="${list.code }">${list.name}</option>
											</c:forEach>
							</select>
						</td>
					</tr>
					<tr><td height="20px" colspan="2"></td></tr>
					<tr>
						<td class="text_right">导入模板编码：</td>
						<td class="text_left"><input class="input" type="text" id="impTempCode" name="impTempCode"></td>
					</tr>
					<tr><td height="20px" colspan="2"></td></tr>
					<tr>
						<td class="text_right">导入模板：</td>
						<td class="text_left"><input type="file" id="impTempFile" name="impTempFile"></td>
					</tr>
					<tr>
						<td></td>
						<td class="text_left"><input type="button" value="提 交" id="btnSave" onclick="saveForm()"
							class="search_btn"/>&nbsp;&nbsp;<input type="button"
							value="取 消" class="search_btn close"/></td>
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
								<option value="-1" selected="selected" >--请选择--</option>
								<c:forEach items="${dictItem}" var="list">
									<c:if test="${list.code =='130101'}"><option value="6">跨越五千千万登峰</option></c:if>
									<%-- <c:if test="${list.code =='130101'}"><option value="8">三级机构标签</option></c:if>
									<c:if test="${list.code =='130101'}"><option value="9">四级机构标签</option></c:if> --%>
									<c:if test="${list.code =='130107'}"><option value="1">个险部实时看板</option></c:if>
									<c:if test="${list.code =='130107'}"><option value="2">个险部</option></c:if>
									<c:if test="${list.code =='101402'}"><option value="4">培训部</option></c:if>
									<c:if test="${list.code =='130105'}"><option value="5">收展部</option></c:if>
									<c:if test="${list.code =='130105'}"><option value="7">三项福利养老金配置</option></c:if>
								<!-- <option value="3">续期部</option> -->
								</c:forEach>
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