<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/util.tld" prefix="u"%>
<html>
<head>
<title>固定指标</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	$(function() {
		$('#pager').pager('${pageContext.request.contextPath}/admin/coreTarget/list',15,$('#parameterView').view().on('add',function(data){
			(function(zhis){
				var deleteConfirm = new Ace.awt.ConfirmBox({
					widget : $('<div style="background-color:#FFFFFF; text-align:center; display:none; line-height:50px;width: 100px;height: 50px; border-radius: 5px;border:1px solid #90C1E6;"><span class="yesorno"><a href="javascript:void(0)" class="confirm">确定</a>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" class="cancel">取消</a></span></div>'),
					trigger : zhis.target.find('.removed'),
					positionAdjust : [ 6, -45 ]
				});	
				deleteConfirm.on('confirm',function(w, t) {
					
					$.post('${pageContext.request.contextPath}/admin/coreTarget/delete',{id:data.ctId},function(bit){
						if(bit==true){
							$.msgbox({time: 2000,msg: "删除成功!",icon:"success"});
						}
						$('#pager').pager().reload(function(){
							zhis.target.remove();
						});
					});
				});
				zhis.target.find('.save').click(function(){
					$.getJSON('${pageContext.request.contextPath}/admin/coreTarget/update',zhis.getData(),function(data){
						$.msgbox({time: 2000,msg: "修改成功!",icon:"success"});
						zhis.setData(data);
						zhis.setTemplate('default',true);
						$('#pager').pager().reload();
					});				
				});			
			})(this);
		}));
		$('#parameterAjaxForm').ajaxForm(function(data) {
			$('#pager').pager().setPostData(Ace.parseQuery($('#parameterAjaxForm').serialize()));
			$('#pager').pager().setJSON(data);
			if (data.pageItems.length > 0) {
				if (data.pageItems[0].deptCode == '130107'||data.pageItems[0].deptCode == '101402') {
					$('.img').attr('title','关联月度指标');
					$('#table1').show();
					$('#table2').hide();
					$('#seve').show();
				} else {
					$('.img').attr('title','查看二级指标');
					$('#table1').hide();
					$('#table2').show();
					$('#seve').hide();
				}
			}
		});
		
		$("#createParameter").click(function() {
			$('#saveParameterForm').data("dialog", $.dialog({
				title : "添加",
				content : $('#saveParameterForm')[0]
			}));
		});
		$('#saveParameterForm').ajaxForm(function(data) {
			$('#saveParameterForm').data("dialog").close();
			$('#pager').pager().reload();
		});
		$("#btnSave").click(function(){
			var obj = $('select[name="targets"]').find('option:selected').val();
			if(obj == 0){
				$.msgbox({time: 2000,msg: "请选择指标", icon: "error"});return;
			}
			$.post('${pageContext.request.contextPath}/admin/coreTarget/findByTargets', {'targetCode': obj}, function(data){
				if(!data){
					$.msgbox({time: 2000,msg: "该指标已存在", icon: "error"});return;
				} else {
					$('#targetCode').val(obj);
					$('#targetName').val($('select[name="targets"]').find('option:selected').html());
					$("#saveParameterForm").submit();
				}
			});
		});
});	

	function relation(pid,deptCode, type){
		if(type == 1){
			var st = $("#hasGo").val()+"";
			var tar = $('input[name="targetName1"]').val();
			pid = $("#pid").val();
			var postData = {
				targetName: tar,
				pid: pid
			}
			$('#pager1').pager('${pageContext.request.contextPath}/admin/coreTarget/relationlist',15,$('#parameterView1').view().on('add',function(data){
				if (data.regCode == "1") {
					if (st.indexOf(data.targetCode+"|"+pid)==-1) {
						st += data.targetCode+"|"+pid+",";
					}
					$("#hasGo").val(st);
				}
			}));
			$('#pager1').pager().setPostData(postData);
			$('#pager1').pager().reload(function(){
				var alist = $("input[name$='_two']");
				for(var i=0; i<alist.length; i++){
					if (st.indexOf(alist[i].value) > -1) {
						$("input[name="+alist[i].name+"]").attr("checked","checked");
					}
				}
			});
		}else{
			st="";
			$("#hasGo").val("");
			$('#pid').val(pid);
			$('#relationForm').data("dialog", $.dialog({
				title : "关联月度指标",
				content : $('#relationForm')[0]
			}));
			var postData = {
				targetName: $('#sername').val(),
				pid: pid,
				deptCode: $('#dept option:selected').val()
			}
			if($("#dept").val()=='130107'||$("#dept").val()=='101402'){
				$('#pager1').pager('${pageContext.request.contextPath}/admin/coreTarget/relationlist' ,15,$('#parameterView1').view().on('add',function(data){
					if (data.regCode == "1") {
						if (st.indexOf(data.targetCode)==-1) {
							st += data.targetCode+"|"+pid+",";
						}
						$("#hasGo").val(st);
					}
				}));
				$('#pager1').pager().setPostData(postData);
				$('#pager1').pager().reload(function(){
					var alist = $("input[name$='_two']");
					for(var i=0; i<alist.length; i++){
						if (alist[i].id == 1) {
							$("input[name="+alist[i].name+"]").attr("checked","checked");
						}
					}
				});
			}else{
				$('#pager1').pager('${pageContext.request.contextPath}/admin/coreTarget/relationlist' ,15,$('#parameterView2').view().on('add',function(data){
					if (data.regCode == "1") {
						if (st.indexOf(data.targetCode)==-1) {
							st += data.targetCode+"|"+pid+",";
						}
						$("#hasGo").val(st);
					}
				}));
				$('#pager1').pager().setPostData(postData);
				$('#pager1').pager().reload(function(){
					var alist = $("input[name$='_two']");
					for(var i=0; i<alist.length; i++){
						if (alist[i].id == 1) {
							$("input[name="+alist[i].name+"]").attr("checked","checked");
						}
					}
				});
			}
			
			
		}
	}
	
	function confirm(){
		var has = $("#hasGo").val();
		var deptCode = $("#dept option:selected").val();
		if(has.length==0){
			$.msgbox({time: 2000,msg: "请选择关联的二级指标",icon:"error"});
			return;
		}
		var pid = $("#pid").val();
		$.post('${pageContext.request.contextPath}/admin/coreTarget/relationTwo', {'pid': pid, 'twoStr': has, 'deptCode': deptCode}, function(data){
			$.msgbox({time: 2000,msg: "保存成功!",icon:"success"});
			$('#relationForm').data("dialog").close();
		});
	}
	
	function cancel(){
		$('#relationForm').data("dialog").close();
	}
	
	function change(box){
		var pid = $("#pid").val();
		var has = $("#hasGo").val();
		if (box.checked == true) {
			if(has.indexOf(box.value+"|"+pid) == -1){
				has += box.value+"|"+pid+",";
				$("#hasGo").val(has)
			};
		} else {
			if(has.indexOf(box.value+"|"+pid) > -1){
				has = has.replace(box.value+"|"+pid+",", "");
				$("#hasGo").val(has)
			};
		}
	}
	
</script>

</head>
<body>
	<div class="business_title">固定指标</div>
	<div class="col_lg_04" style="width: 1203px">
		<div class="business_search_list_warp" style="width: 95%">
			<input type="button" id="createParameter" value="新增" style="display: none;width: 80px;height: 30px; border-radius: 5px;border:1px solid #90C1E6;">
			<input type="hidden" id="sername" value="">
			<input type="hidden" id="hasGo" value="">
		 	<div class="business_search">
				<div class="business_search_left" style="margin-left: -20px;">
					<form id="parameterAjaxForm"
						action="${pageContext.request.contextPath}/admin/coreTarget/list"
						method="post">
						<input type="hidden" name="pageSize" value="15" />
						<table>
							<tbody>
								<tr>
									<td style="text-align: right">指标名称：</td>
									<td><input style="width: 300px;height: 30px;border-radius: 5px;border:1px solid #90C1E6;" type="text"
										name="targetName"  class="text">&nbsp;&nbsp;</td>
									<td style="text-align: right">选择部门：</td>
									<td>
										<select style="width: 100px;height: 30px;border-radius: 5px;border:1px solid #90C1E6;" name="deptCode" class="view-field" id="dept">
											<option value="-1">--请选择--</option>
											<c:forEach items="${depts}" var="dept">
											   <option value="${dept.deptCode}">${dept.deptName}</option>
											</c:forEach>
										</select>
									</td>
									<td><input type="submit" value="查&#12288;询"  id="re"
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
						<th>序号</th>
						<th>部门名称</th>
						<th>核心指标</th>
						<th style="border-right: 1px solid #ccc; width: 70px">操作</th>
					</tr>
					<tr class="tb_tr_content template" name="default">
						<td>{index:seq()}</td>
						<td>{deptCode:dict({0:'',101402:'教育培训部',130101:'机构发展部',130107:'个人业务部',130105:'收展部'})}</td>	
						<td>{targetName}</td>
						<td style="border-right: 1px solid #ccc"><span
							class="span_edit"><a href="javascript:void(0)" onclick="relation('{targetCode}','{deptCode}', 0)"
								class="relation" template="relation"> 
								<img style="float: left; width: 18px;height: 18px;" class="img"
								src="${pageContext.request.contextPath}/static/images/huatai_look.png" title="关联月度指标"/>
							</a> <a href="javascript:void(0)" style="display: none;"
								class="switch" template="update"> <img style="float: left;width:17px;margin-left: 5px;"
								src="${pageContext.request.contextPath}/static/images/huatai_edit.png"
									title="修改" />
							</a> <a href="javascript:void(0)" class="removed" style="display: none;width:17px;"> <img
									style="float: left;"
									src="${pageContext.request.contextPath}/static/images/huatai_delete.png"
									title="删除">
							</a>
						</span></td>
					</tr>
					 <tr class="tb_tr_content template" name="update">
						<td>{deptCode}</td>
						<td>
							<select name="targetCode"  class="view-field">
							  <option value="targetCode"  selected="true">--请选择--</option>
								<c:forEach items="${deptTargets}" var="deptTarget">
								   <option value="${deptTarget.targetCode}">${deptTarget.targetName}</option>
								</c:forEach>
						</select>
						</td>
						<td>{opType:dict({A:'新增',D:'删除',U:'更新',R:'查看'})}</td>
						<td>{createTime}</td>
						<td>{modifyTime}</td>
						<td>{creatorName}</td>
						<td>{modifierName}</td>
						<td style="border-right: 1px solid #ccc"><span
							class="span_edit"> <a href="javascript:void(0)"
								class="save"><img style="float: left;" title="保存"
									src="${pageContext.request.contextPath}/static/images/huatai_save.png"></a>&nbsp;
								<a href="javascript:void(0)" class="switch back"
								template="default"><img style="float: left;" title="返回"
									src="${pageContext.request.contextPath}/static/images/huatai_back.png"></a>
						</span></td>
					</tr>

				</tbody>
			</table>
			<div id="pager"></div>
		</div>
		<div class="clear"></div>
	</div>
	<form name="coreTargetForm" id="saveParameterForm" enctype="multipart/form-data" style="display: none;"
		action="${pageContext.request.contextPath}/admin/coreTarget/create"
		method="post">
		<div id="tagContent2" class="tagContent tagContent_div"
			style="display: block; background: #F7F7F7">
			<table style="line-height: 30px">
				<tbody>
					<tr>
						<td class="text_right">核心指标：</td>
						<td class="text_left">
							<select name="targets" style="border-radius:5px; width:202px;height: 32px;border:1px solid #90C1E6;">
								<option value="0">--请选择--</option>
								<c:forEach items="${deptTargets}" var="deptTarget">
								   <option value="${deptTarget.targetCode}">${deptTarget.targetName}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr><td height="20px" colspan="2"></td></tr>
					<tr>
						<td></td>
						<td class="text_left"><input type="button" value="提 交" id="btnSave"
							class="search_btn"/>&nbsp;&nbsp;<input type="button"
							value="取 消" class="search_btn close"/></td>
					</tr>
				</tbody>
			</table>
		</div>
		<input type="hidden" name="deptCode" id="deptCode" value=""/>
		<input type="hidden" name="targetCode" id="targetCode" value=""/>
		<input type="hidden" name="targetName" id="targetName" value=""/>
	</form>
	<!--二级指标 -->
	<form name="relationForm" id="relationForm" enctype="multipart/form-data" style="display: none;">
		<div class="business_search_list_warp" style="width: 95%">
		 	<div>
				<div class="business_search_left" style="margin-left: -20px;">
					<input type="hidden" id="pid">
				</div>
			</div>
			<form id="searchTwoTarget">
				<input type="hidden" name="pageSize" value="15" />
				<table>
					<tbody>
						<tr>
							<td style="text-align: right">指标名称：</td>
							<td><input style="width: 300px;height: 30px;border-radius: 5px;border:1px solid #90C1E6;" type="text"
								name="targetName1" class="text">&nbsp;&nbsp;</td>
							<td><input type="button" onclick="relation('{targetCode}', 1)" value="查&#12288;询"
								style="width: 60px;height: 25px;border-radius: 5px;border:1px solid #90C1E6;"></td>
						</tr>
					</tbody>
				</table>
			</form>
			<div id="table1">
				<table cellspacing="0" cellpadding="0" class="t-list table"id="parameterView1">
					<tbody id="relaTt">
						<tr >
							<th id="th1" width="100px">选择</th>
							<th width="150px">指标代码</th>
							<th width="150px">指标名称</th>
							<th width="100px">使用渠道</th>
						</tr>
						<tr class="tb_tr_content template" name="default">
							<td >
								<input type="checkbox"  value="{targetCode}" id="{regCode}" name="{targetCode}_two" onclick="change(this)">
							</td>
							<td>{targetCode}</td>
							<td>{targetName}</td>
							<td>{channel}</td>
						</tr>					
					</tbody>
				</table>
				<div id="pager1"></div>
				<div style="text-align: center;margin-top: 4px;" id="seve">
					<input type="button" onclick="confirm()" value="确&#12288;认" style="margin-right: 10px;width: 60px;height: 25px;border-radius: 5px;border:1px solid #90C1E6;">
					<input type="button" onclick="cancel()" value="取&#12288;消" style="margin-left: 10px;width: 60px;height: 25px;border-radius: 5px;border:1px solid #90C1E6;">
				</div>
			</div>
			<dir id="table2">
				<table cellspacing="0" cellpadding="0" class="t-list table"id="parameterView2">
					<tbody id="relaTt">
						<tr >
							<th width="150px">指标代码</th>
							<th width="150px">指标名称</th>
							<th width="100px">使用渠道</th>
						</tr>
						<tr class="tb_tr_content template" name="default">
							<td>{targetCode}</td>
							<td>{targetName}</td>
							<td>{channel}</td>
						</tr>					
					</tbody>
				</table>
			</dir>
		</div>
	</form>
</body>
</html>