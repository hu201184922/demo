<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/util.tld" prefix="u"%>
<html>
<head>
<title>公告维护</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
.file-box{ position:relative;width:340px} 
.txt{ height:22px; border:1px solid #cdcdcd; width:180px;} 
.btn{ background-color:#FFF; border:1px solid #CDCDCD;height:24px; width:70px;} 
.file{ position:absolute; right:120px; height:24px; filter:alpha(opacity:0);opacity: 0;width:253px;left:0px }
</style>
<script type="text/javascript">
	$(function() {
		$('#pager').pager('${pageContext.request.contextPath}/admin/notice/list',15,$('#noticeView').view().on('add',function(data){
			(function(zhis){
				var deleteConfirm = new Ace.awt.ConfirmBox({
					widget : $('<div style="background-color:#FFFFFF; display:none; text-align:center;  line-height:50px;width: 100px;height: 50px; border-radius: 5px;border:1px solid #90C1E6;"><span class="yesorno"><a href="javascript:void(0)" class="confirm">确定</a>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" class="cancel">取消</a></span></div>'),
					trigger : zhis.target.find('.removed'),
					positionAdjust : [ 6, -45 ]
				});	
				deleteConfirm.on('confirm',function(w, t) {
					$.getJSON('${pageContext.request.contextPath}/admin/notice/delete',{noticeId:data.noticeId},function(bit){
						if(bit==true){
							$.msgbox({time: 2000,msg: "删除成功!",icon:"success"});
						}
						$('#pager').pager().reload(function(){
							zhis.target.remove();
						});
					});
				});
				zhis.target.find('.edit').click(function(){
					var noticeId=$('#noticeId').val("");
					var deptCode=$("input[name='deptCode']").val("");
					var title=$('#title').val("");
					var content=$('textarea').val("");
					$('#savenoticeForm').data("dialog", $.dialog({
						title : "修改公告",
						content : $('#savenoticeForm')[0]
					}));
					var str=$(this).attr('name').split(",");
					var noticeId=$('#noticeId').val(str[0]);
					var deptCode=data.deptCode.split(",");
					$("input[name='deptCode']").val(deptCode);
					//var deptCode=$('#deptCode').val(str[1]);
					var title=$('#title').val(str[1]);
					var content=$('#content').val(str[2]);
					$(".file_tr").remove();
					$.ajax({
						url:'${pageContext.request.contextPath}/admin/notice/findFile',
						data:{noticeId:str[0]},
						success:function(data){
							$.each(data,function(i,v){
								var temp="<tr class='control file_tr'><td class='text_right'>附件地址：</td><td class='text_left'><div class='file-box'> <input type='text' name='textfield' class='txt' value="+v.fileName+" readonly/><input type='hidden' name='nfId' value='"+v.nfId+"'/><input type='button' class='btn' value='删除' onclick='delFile(this)'/></div></td></tr>";		
								$("#appendFile").append(temp);
							})
						}
					})	
					
				});
				
				zhis.target.find('.save').click(function(){
					$.getJSON('${pageContext.request.contextPath}/admin/notice/update',zhis.getData(),function(data){
						$.msgbox({time: 2000,msg: "修改成功!",icon:"success"});
						zhis.setData(data);
						zhis.setTemplate('default',true);
					});
					$('#pager').pager().reload();
				});
			})(this);
		}));
		
		$('#noticeAjaxForm').ajaxForm(function(data) {
			$('#pager').pager().setPostData(Ace.parseQuery($('#noticeAjaxForm').serialize()));
			$('#pager').pager().setJSON(data);
		});
		
		$("#createnotice").click(function() {
			var noticeId=$('#noticeId').val("");
			//var deptCode=$('#deptCode').val("");
			var deptCode=$("input[name='deptCode']").val("");
			var title=$('#title').val("");
			var content=$('textarea').val("");
			$('.txt').val('');
			$(".control").remove();
			$(".file").val("");
			$('#savenoticeForm').data("dialog", $.dialog({
				title : "添加公告",
				content : $('#savenoticeForm')[0]
			}));
		});
		$('#savenoticeForm').ajaxForm(function(data) {
			$('#savenoticeForm').data("dialog").close();
			$('#pager').pager().reload();
		});
		
		$('#pager').pager().reload();
});	
	
	
	
	$('#savenoticeFileForm').ajaxForm(function(data) {
		var ic;
		data.result==true?ic="success":ic="error";
		$.msgbox({time: 3000,msg: data.message, icon: ic});
		$('#savenoticeFileForm').data("dialog").close();
		$('#pager').pager().reload();
	});
	
	function validate(){
		var title = $('#title').val();
		var content = $("#content").val();
		var dept=$("input[name='deptCode']").val();
		if($.trim(dept)=='' || $.trim(dept)==null){
			$.dialog({content: '请选择所属部门名称', time: 2000});return;
		}
		if($.trim(title) == 0 || $.trim(title) == ''){
			$.dialog({content: '标题不能为空', time: 2000});return;
		}
		if($.trim(content)=='' || $.trim(content)==null){
			$.dialog({content: '内容不能为空', time: 2000});return;
		}		
		$('#savenoticeForm').submit();
	}
	
	$("#savenoticeForm").ajaxForm(function(data){
		var msg; 
		if(data==-2 ||data== -3){
			msg = data==-2?"请上传图片格式文件，如：jpg,png等":"文件过大";
		}
		$.dialog({content: msg, time: 2000});
     	window.history.go(0);
  	});
	
	$(".addFile").live("click",function(){
		//$('.txt').val('');
		var temp="<tr class='control file_tr'><td class='text_right'>附件地址：</td><td class='text_left'><div class='file-box'> <input type='text' name='textfield' id='textfield' class='txt' /><input type='button' class='btn' value='浏览...' /><input type='file' name='file1' class='file' class='fileField' size='28' onchange='onChangeFile(this)'/><input type='button' class='btn' value='删除' onclick='delFile(this)'/></div></td></tr>'";		
		$("#appendFile").append(temp);
		/* console.log($(".file"))
		$(".file").data("onchange",function(){
			alert($(this).val())
			this.$('.textfield').value=$(this).val();
		}) */
	})
	/* $(".delFile").live("click",function(){
			$(".file").val("");
	}) */
	function delFile(obj){
		$(obj).parent().parent().parent().remove();
	}
	function onChangeFile(obj){
		var txtField = $(obj).parent().children("#textfield");
		txtField.val(obj.value);
	}
</script>
</head>
<body>
	<div class="business_title">公告管理</div>
	<div class="col_lg_04" style="width: 1203px">
		<div class="business_search_list_warp" style="width: 95%">
			<input type="button" style="width: 60px; height: 25px; border-radius: 5px; border: 1px solid #90C1E6;" id="createnotice" value="新建">
			<table cellspacing="0" cellpadding="0" class="t-list table"id="noticeView">
				<tbody>
					<tr>
						<th width="13%">序号</th>
						<th width="13%">部门</th>
						<th width="13%">公告主题</th>
						<th width="13%">创建时间</th>
						<th width="15%">发布人</th>
						<th style="border-right: 1px solid #ccc; width: 7%">操作</th>
					</tr>
					<tr class="tb_tr_content template" name="default">
						<td>{index:seq()}</td>
						<td>{deptCode:dict({101402:'教育培训部',130101:'机构发展部',130107:'个人业务部',130105:'收展部','130101,101402':'机构发展部,教育培训部','130107,101402':'个人业务部,教育培训部','101402,130105':'教育培训部,收展部','130107,130101':'个人业务部,机构发展部','130101,130105':'机构发展部,收展部','130107,130105':'个人业务部,收展部','130107,130101,101402':'个人业务部,机构发展部,教育培训部','130101,101402,130105':'机构发展部,教育培训部,收展部','130107,101402,130105':'个人业务部,教育培训部,收展部','130107,130101,130105':'个人业务部,机构发展部,收展部','130107,130101,101402,130105':'个人业务部,机构发展部,教育培训部,收展部'})}</td>
						<td>{title}</td>
						<td>{createTime}</td>
						<td>{creatorId}</td>
						<td style="border-right: 1px solid #ccc"><span
							class="span_edit"><a href="javascript:void(0)"
								class="edit" name="{noticeId},{title},{content}"> <img style="float: left;"
								src="${pageContext.request.contextPath}/static/images/xiugai.png"
									title="修改" />
							</a> <a href="javascript:void(0)" class="removed"> <img
									style="float: left;"
									src="${pageContext.request.contextPath}/static/images/del_icon.png"
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
	
	<!-- 添加附件 -->
	<form id="savenoticeForm" style="display: none;"
		action="${pageContext.request.contextPath}/admin/notice/create"
		method="post" enctype="multipart/form-data" target="hidden_frame">
		<div id="tagContent2" class="tagContent tagContent_div"
			style="display: block; background: #F7F7F7">
			<table style="line-height: 30px">
				<tbody id="appendFile">
					<tr>
						<input type="hidden" id="noticeId" name="noticeId"/>
						<td class="text_right">部门:</td>
						<td width="400px;">
											<c:forEach items="${dictItem}" var="list">
												<input type='checkbox' name='deptCode' value='${list.code}' 
												<c:if test="${target.deptCode==list.code}">checked="checked"</c:if>  
												class='formCheck'/>${list.name}&nbsp;
											</c:forEach>
									<!-- 
									<select id="deptCode"
										style="width: 150px; height: 30px; border-radius: 5px; border: 1px solid #90C1E6;"
										name="deptCode" class="view-field">
											<option value="">请选择</option>
											<c:forEach items="${dictItem}" var="list">
												<option value="${list.code }"
													<c:if test="${target.deptCode==list.code}">selected="selected"</c:if>>${list.name}</option>
											</c:forEach>
									</select>
									 -->
						</td>					
					</tr>
					<tr>
						<td height="10px" colspan="2"></td>
					</tr>
					<tr>
						<td class="text_right">标题：</td>
						<td class="text_left"><input type="text" id="title" name="title" style="width: 150px; height: 30px; border-radius: 5px; border: 1px solid #90C1E6;"/>
						</td>
					</tr>
					<tr>
						<td height="10px" colspan="2"></td>
					</tr>
					<tr>
						<td class="text_right">内容：</td>
						<td class="text_left"><textarea name="content" id="content" cols="50" rows="10" style="border-radius: 5px; border: 1px solid #90C1E6;"></textarea>
						</td>
					</tr>
					<tr>
						<td height="10px" colspan="2"></td>
					</tr>
					
					<tr><input type="hidden" name="noticeId" />
						<td class="text_right">附件：</td>
						<td class="text_left">
						<!-- <div class="file-box"> 
						<input type='text' name='textfield' id='textfield' class='txt' /> 
						<input type='button' class='btn' value='浏览...' /> 
						<input type="file" name="file1" class="file" id="fileField" size="28" onchange="document.getElementById('textfield').value=this.value" />
						</div> -->
						<input type="button" style="width: 60px; height: 25px; border-radius: 5px; border: 1px solid #90C1E6;" class="addFile" value="添加"/>
						</td>
					</tr>
					
				</tbody>
				<!-- <div class="file-box"> 
				<form action="" method="post" enctype="multipart/form-data">
				<input type='text' name='textfield' id='textfield' class='txt' /> 
				<input type='button' class='btn' value='浏览...' /> 
				<input type="file" name="fileField" class="file" id="fileField" size="28" onchange="document.getElementById('textfield').value=this.value" /> 
				<input type="submit" name="submit" class="btn" value="上传" /> 
				</form> </div> -->
			</table>
		</div>
		<div class="text_left"><input style="margin-left: 120px;" type="button" onclick="validate()" value="提 交"
							class="search_btn">&nbsp;&nbsp;<input type="button"
							value="取 消" class="search_btn close"></div>
	</form>
</body>
</html>