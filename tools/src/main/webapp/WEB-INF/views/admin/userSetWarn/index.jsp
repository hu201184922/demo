<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/util.tld" prefix="u"%>
<html>
<head>

<title>预警</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
.bolder {
	font-weight: bolder;
}

.divarea {
	clear: both;
	width: 1026px;
	border: 1px solid #dee1e2;
	overflow: hidden;
}

.gs {
	float: left;
	width: 100%;
	border: 1px solid #dee1e2;
	background-color: #F7F7F7;
}

.gs span {
	width: 100%;
	float: left;
	padding: 10px 0 10px 10px;
}

.zt span {
	width: 200px;
	float: none;
	padding: 10px 0 10px 10px;
}

.in_left {
	width: 100px;
	float: left;
	padding: 0 !important;
	border-bottom: 0px !important;
}

.in_right {
	width: 150px;
	height: 25px;
	margin-top: 6px;
	float: left;
}

.btn_cate {
	width: 212px;
	height: 30px;
	border-radius: 5px;
	border: 1px solid #90C1E6;
}
.unit_text{
	width: 80px;
	height: 25px;
	float: left; 
	border:none;
	background-color:transparent;
}
</style>
<script type="text/javascript">
	var targetCode = "";
	$(function() {
		$('#pager').pager('${pageContext.request.contextPath}/admin/userSetWarn/list',15,$('#templetView').view().on('add',function(data) {
			(function(zhis) {
				var deleteConfirm = new Ace.awt.ConfirmBox(
						{
							widget : $('<div style="background-color:#FFFFFF; text-align:center; display:none; line-height:50px;width: 100px;height: 50px; border-radius: 5px;border:1px solid #90C1E6;"><span class="yesorno"><a href="javascript:void(0)" class="confirm">确定</a>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" class="cancel">取消</a></span></div>'),
							trigger : zhis.target
									.find('.removed'),
							positionAdjust : [
									6, -45 ]
						});
				deleteConfirm.on('confirm',	function(w, t) {
					$.getJSON('${pageContext.request.contextPath}/admin/userSetWarn/delete',{
							id : data.bsId
						},
						function(
								bit) {
							if (bit == true) {
								$.msgbox({
									time : 2000,
									msg : "删除成功!",
									icon : "success"
								});
							}
							$('#pager')	.pager().reload(function() {
								zhis.target.remove();
								});
						});
				});

			})(this);
		}));
		$('#pager').pager().reload();

		$('#parameterAjaxForm').ajaxForm(
				function(data) {
					$('#pager').pager()
							.setPostData(
									Ace.parseQuery($('#parameterAjaxForm')
											.serialize()));
					$('#pager').pager().setJSON(data);
				});

		$('#saveTargetForm').ajaxForm(function(data) {
			$('#saveTargetForm').data("dialog").close();
			$('#pager').pager().reload();
		});

		//清空预警内容
		$('#WarnZB').val("");
		//加载指标树
		var TargetListNodes = JSON.parse('${targets}');
		for (var i = 0; i < TargetListNodes.length; i++) {
			TargetListNodes[i].id = TargetListNodes[i].targetCode;//添加节点id属性
			TargetListNodes[i].pId = TargetListNodes[i].parentCode;//添加节点pid属性
			TargetListNodes[i].name = TargetListNodes[i].targetName;//添加name属性
			//TargetListNodes[i].isParent = true;//把所有节点默认为父节点	
		}
		var treeOfTarget;
		var setting = {
			data : {
				simpleData : {
					enable : true,
					idKey : 'id',
					pIdKey : 'pId'
				}
			},
			view : {
				showIcon : false,
				showLine : true
			},
			callback : {
				beforeClick : zTreeBeforeClick
			}
		};
		treeOfTarget = $('#targetTree').zTree(setting, TargetListNodes).expandAll(true);
		//加载部门树
		var OrgListNodes = JSON.parse('${organization}');
		for (var i = 0; i < OrgListNodes.length; i++) {
			OrgListNodes[i].id = OrgListNodes[i].deptCode;//添加节点id属性
			OrgListNodes[i].pId = OrgListNodes[i].deptPid;//添加节点pid属性
			OrgListNodes[i].name = OrgListNodes[i].deptName;//添加name属性
			//TargetListNodes[i].isParent = true;//把所有节点默认为父节点	
		}
		var treeOfTargeOrg;
		var setting = {
			data : {
				simpleData : {
					enable : true,
					idKey : 'id',
					pIdKey : 'pId'
				}
			},
			view : {
				showIcon : false,
				showLine : true
			},
			callback : {
				onClick : zTreeOnClick
			}
		};
		treeOfTargeOrg = $('#organizationTree').zTree(setting, OrgListNodes);
	});

	//指标树单击事件
	function zTreeBeforeClick(treeId, treeNode, clickFlag) {
		if(treeNode.isParent){
			return !treeNode.isParent;//当是父节点 返回false 不让选取
		}else{
			targetCode = treeNode.id;
			field(targetCode);
			$('#WarnZB').val(treeNode.name);
		}
	}
	
	
	//部门树单击事件
	var orgNames = [];
	var orgCodes = [];
	function zTreeOnClick(event, treeId, treeNode) {
		if (orgNames.length > 0) {
			for (var i = 0; i < orgNames.length; i++) {
				if (orgNames[i] == treeNode.name) {
					orgNames.splice(i, 1);
					break;
				} else if (i == (orgNames.length - 1)) {
					orgCodes.push([treeNode.id])
					orgNames.push([treeNode.name]);
					break;
				}
			}
		} else {
			orgCodes.push([treeNode.id]);
			orgNames.push([treeNode.name]);
		}
		var html = '';
		for (var j = 0; j < orgNames.length; j++) {
			html += '<li>' + orgNames[j] + '</li>';
		}
		$('#queryJG').html(html);
	};

	//预警结果跳转
	function result() {
		window.location.href = "${pageContext.request.contextPath}/admin/userSetWarn/result";
	}
	
	function warnIndex(){
		window.location.href = "${pageContext.request.contextPath}/admin/userSetWarn/index";
	}

	
	function field(targetCode){
		$.ajax({
			type : "GET",
			url : '${pageContext.request.contextPath}/admin/userSetWarn/findField',
			data : {
				"targetCode" : targetCode
			},
			dataType : 'json',
			array : function(data){
				$.msgbox({time: 2000,msg: "查询单位失败！",icon:"error"})
			},
			success : function(data) {	
				fiele = data;
				if(data != null ){
					$('#Unit').val("单位:(" + fiele + ")");
				}else{
					$('#Unit').val("单位:( )");
				}
			}
		});
	}
	//弹出添加窗口
	$(function() {
		$("#AddWarn").click(function() {
			$('#addParameterForm').data("dialog", $.dialog({
				title : "新增预警",
				content : $('#addParameterForm')[0]
			}));
			cancel();
			AlertType();
		});
		$('#addParameterForm').ajaxForm(function(data) {
			$('#addParameterForm').data("dialog").close();
			$('#pager').pager().reload();
		});
	});
	
	//弹出编辑窗口
	function updateWarn(bsId) {
		$.ajax({
			type : "POST",
			url : '${pageContext.request.contextPath}/admin/userSetWarn/findById',
			data : {
				"bsId" : bsId
			},
			dataType : 'json',
			success : function(data) {
				cancel();
				AlertType();
				targetCode=data.targetCode;
				field(targetCode);
				orgCode = data.orgCode;
				var targetTreeObj = $('#targetTree').zTree();
				var OrgTreeObj = $('#organizationTree').zTree();
				var Tnode = targetTreeObj.getNodeByParam("id",targetCode, null);
				targetTreeObj.selectNode(Tnode);
				var Onode = OrgTreeObj.getNodeByParam("id",orgCode, null);
				OrgTreeObj.selectNode(Onode);
				orgNames = [];
				orgNames.push(data.orgName);
				orgCodes.push(data.orgCode);
				$('#WarnZB').val(data.targetName);
				if (data.minVal != null && data.minVal != ''
						&& data.maxVal != null && data.maxVal != '') {
					$('#BigToSmall').attr("checked", "checked");
					$('#range_min').val(data.maxVal);
					$('#range_max').val(data.minVal);
				} else if (data.minVal != null && data.minVal != '') {
					$('#Big').attr("checked", "checked");
					$('#more').val(data.minVal);
				} else if (data.maxVal != null && data.maxVal != '') {
					$('#Small').attr("checked", "checked");
					$('#less').val(data.maxVal);
				}
				var html = '<li>' + data.orgName + '</li>';
				$('#queryJG').html(html);
				if (data.alertType == "1") {
					$('#select1').attr("checked", "checked");
				} else if (data.alertType == "2") {
					$('#select2').attr("checked", "checked");
				} 
				$('#uid').val(data.bsId);
			},
		});
		$('#addParameterForm').data("dialog", $.dialog({
			title : "编辑预警",
			content : $('#addParameterForm')[0]
		}));
		$('#addParameterForm').ajaxForm(function(data) {
			$('#addParameterForm').data("dialog").close();
			$('#pager').pager().reload();
		});
	}
	//修改预警状态
	function updateStatus(status, bsId) {
		$.getJSON('${pageContext.request.contextPath}/admin/userSetWarn/updateStatus',{status : status,bsId:bsId},
		function(bit) {
			location.reload();
		})
	}

	//添加预警信息
	function AddWarn() {
		var bsId = $('#uid').val();
		var WarnNR = "";
		WarnNR = $("#WarnZB").val();
		var WarnBig = "";
		var WarnSmall = "";
		var WarnCode = "";
		var OrgName = "";
		var AlertType = "";

		if ($('#Big').attr('checked')) {
			WarnBig = $("#more").val();
			WarnCode = 1;
		}

		if ($('#Small').attr('checked')) {
			WarnSmall = $("#less").val();
			WarnCode = 2;
		}
		if ($('#BigToSmall').attr('checked')) {
			WarnBig = $("#range_max").val();
			WarnSmall = $("#range_min").val();
			WarnCode = 3;
		}

		if ($('#select1').attr('checked') && $('#select2').attr('checked')) {
			AlertType = '3';
		} else if ($('#select1').attr('checked')) {
			AlertType = '1'
		} else if ($('#select2').attr('checked')) {
			AlertType = '2'
		}
		//判断是否为空
		if ($.trim(WarnNR) == "") {
			$.dialog({content: '请选择预警内容！', time: 2000});
			return;
		} else if ($.trim(WarnCode) == "") {
			$.dialog({content: '请选择预警条件！', time: 2000});
			return;
		} else if (orgNames.length < 1) {
			$.dialog({content: '请选择监控对象！', time: 2000});
			return;
		} else if (!$('#select1').attr('checked')
				&& !$('#select2').attr('checked')) {
			$.dialog({content: '请选择预警方式！', time: 2000});
			return;
		} else {
			$.ajax({
				type : "POST",
				url : '${pageContext.request.contextPath}/admin/userSetWarn/addwarn',
				data : {
					"bsId" : bsId,
					"targetName" : WarnNR,
					"targetCode":targetCode,
					"warnCode" : WarnCode,
					"orgNames" : orgNames,
					"orgCodes" : orgCodes,
					"alertType" : AlertType,
					"valMin" : WarnBig,
					"valMax" : WarnSmall
				},
				async : true,
				error : function(request) {
					$.msgbox({time: 2000,msg: "预警添加失败!",icon:"error"});
					return;
				},
				success : function(data) {
					location.reload();
				}
			});
		}
	}

	//删除预警
	function deleteWarn(bsId) {
		$.ajax({
			type : "POST",
			url : '${pageContext.request.contextPath}/admin/userSetWarn/deletewarn',
			data : {
				"bsId" : bsId
			},
			async : false,
			error : function(data) {
				$.msgbox({time: 2000,msg: "删除预警失败！",icon:"error"});
				location.reload();
			},
			success : function(data) {
				//alert("状态修改成功！");
				location.reload();
			}
		});
	}
	
	//清空弹框
	function cancel(){
		$(':input','#addParameterForm') 
		.not(':button, :submit, :reset, :hidden') 
		.val('') 
		.removeAttr('checked') 
		.removeAttr('selected');
		$('#queryJG').empty();
		$('#targetTree').zTree().cancelSelectedNode();
		$('#organizationTree').zTree().cancelSelectedNode();
		orgNames = [];
		orgCodes = [];
	}
	
// 	取消缓存
	function cancelDim(){
		$('#addParameterForm').data("dialog").close();
		cancel()
// 		location.reload();
	}
	//获取预警方式
	function AlertType(){
		$.ajax({
			type : "GET",
			url : '${pageContext.request.contextPath}/admin/userSetWarn/alertType',
			dataType : 'json',
			success : function(alertType) {
				$('#s1').html(alertType[0]);
				$('#s2').html(alertType[1]);
				
			},
		});
	}
	
</script>
</head>
<body>
	<div class="col_lg_04" style="width: 100%">
		<div class="business_title">查询条件</div>
		<form id="parameterAjaxForm"
			action="${pageContext.request.contextPath}/admin/userSetWarn/list"
			method="post">
			<input type="hidden" name="pageSize" value="10" />
			<table>
				<tbody>
					<tr>
						<td style="text-align: right">监控对象：</td>
						<td><input
							style="width: 150px; height: 30px; border-radius: 5px; border: 1px solid #90C1E6;"
							type="text" name="orgName" class="text" autocomplete="off">&nbsp;&nbsp;</td>
						<td style="text-align: right">预警指标：</td>
						<td><input
							style="width: 150px; height: 30px; border-radius: 5px; border: 1px solid #90C1E6;"
							type="text" name="warnTarget" class="text" autocomplete="off">&nbsp;&nbsp;</td>
						<td style="text-align: right">预警状态：</td>
						<td><select name="warnStatus"
							style="width: 150px; height: 30px; border-radius: 5px; border: 1px solid #90C1E6;">
								<option value="">请选择</option>
								<option value="0">已关闭</option>
								<option value="1">运行中</option>
						</select></td>
						<td>&#12288;&#12288;<input type="submit" value="查&#12288;询"
							style="width: 60px; height: 25px; border-radius: 5px; border: 1px solid #90C1E6;"></td>
					</tr>
				</tbody>
			</table>

			<div style="width: 1203px">
				<div class="business_search_list_warp" style="width: 95%;margin-left: 40px;">
					<div>
							<tr>
								<td><input type="button" id="MyWarn" value="我的预警"
									style="background-color: #90C1E6; width: 80px; height: 30px; border-radius: 5px; border: 1px solid #90C1E6;"
									onclick="warnIndex()">
								</td>
								<td><input type="button" id="WarnResult" value="预警结果"
									style="width: 80px; height: 30px; border-radius: 5px; border: 1px solid #90C1E6;"
									onclick="result()"></td>
								<td><input type="button" id="AddWarn" value="+新增预警"
									style="width: 80px; height: 30px; border-radius: 5px; border: 1px solid #90C1E6;">
								</td>
							</tr>
					</div>
				</div>
			</div>

			<table cellspacing="0" cellpadding="0" class="t-list table"
				id="templetView">
				<tbody>
					<tr>
						<th width="130px">监控对象</th>
						<th width="130px">预警指标</th>
						<th width="150px">预警条件</th>
						<th width="80px">预警方式</th>
						<th width="50px">预警状态</th>
						<th style="border-right: 1px solid #ccc; width: 90px">操作</th>
					</tr>
					<tr class="tb_tr_content template" name="default">
						<td>{orgName}</td>
						<td>{targetName}</td>
						<td>{warnCode}</td>
						<td>{alertType:dict({1:'消息盒子',2:'持续监控',3:'消息盒子+持续监控'})}</td>
						<td>{warnStatus:dict({0:'已关闭',1:'运行中'})}</td>
						<td style="border-right: 1px solid #ccc; width: 40px"><span
							> <a href="javascript:void(0)"
								class="switch"
								onclick="updateStatus(Status='{warnStatus}',bsId='{bsId}')">
									{warnStatus:dict({0:'开启',1:'关闭'})} </a> <a
								href="javascript:void(0)" onclick="updateWarn(bsId='{bsId}')"
								class="switch" id="update" >| 修改 |
							</a> <a href="javascript:void(0)" class="removed"> 删除
							</a>
						</span></td>
					</tr>
				</tbody>
			</table>
		</form>
		<div id="pager"></div>
	</div>
	<div class="clear"></div>


	<form name="coreTargetForm" id="addParameterForm"
		enctype="multipart/form-data"
		style="display: none; width: 800px; height: 500px" method="post">
		<input type="hidden" id="btnCate" value="0">

		<div id="tagContent2" class="tagContent tagContent_div"
			style="display: block; background: #F7F7F7">

			<table align="center">
				<tbody>
					<tr>
						<td width="200px"><span 14px/30px 'MicrosoftYahei';">预警内容设置</span>
							<div id="formu1" class="gs" style="overflow: auto;">
								<input type="hidden" name="targetC"> <span class="zt" style="height:144px">
									<ul id="targetTree" class="ztree" style="height: 110px; width: 300px"></ul> 
									<input type="hidden" id="uid" value="" autocomplete="off">
									<input type="hidden" name="WarnZB" id="WarnZB" value="">
								</span>
							</div>
						</td>
						<td>
							<div>
								<input type="hidden" name="targetC"> <span 14px/30px 'MicrosoftYahei';">
								公式								
								</span> <span class="gs">
								<input type="text" id="Unit" class="unit_text" value="" autocomplete="off">
									<span><p class="in_left">
											<input type="radio" name="WarnRange" id="Big" value="Big" autocomplete="off">指标大于
										 </p>
										 <input class="in_right" type="text" autocomplete="off" name="more" id="more">
										 	
								</span>
								<span><p class="in_left">
											<input type="radio" name="WarnRange" id="Small" value="Small" autocomplete="off">指标小于
										</p> <input class="in_right" type="text" name="less" id="less" autocomplete="off">
								</span> 
								<span>
										<p class="in_left">
											<input type="radio" name="WarnRange" id="BigToSmall"
												value="BigToSmall" autocomplete="off">指标值范围
										</p> 
											<input type="text"style="height: 25px;" name="range_min" id="range_min" autocomplete="off">&nbsp;到
											<input style="height: 25px;  margin: 0;"type="text" name="range_max" id="range_max" autocomplete="off">
								</span>
								</span>
							</div>
						</td>
					</tr>
					<tr>
						<td><span 14px/30px 'MicrosoftYahei';">预警监控对象选择</span>
							<div id="formu3" class="gs" style="overflow: auto;">
								<input type="hidden" name="targetC" autocomplete="off">  <span class="zt"">
									<ul id="organizationTree" class="ztree"
										style="height: 110px; width: 300px"></ul>
								</span>
							</div></td>

						<td><span 14px/30px 'MicrosoftYahei';">已选机构</span>
							<div id="formu4" style="height: 160px">
								<input type="hidden" name="targetC"> 
								<span class="gs"style="height: 150px;"> 
									<div id=queryJG align="center" line-height:25px;  overflow:hidden; ></div>
								</span>
							</div></td>
					</tr>
					<tr>
						<td colspan="2"><span 14px/30px 'MicrosoftYahei';">预警方式选择</span>
							<div id="formu5"
								style="height: 25px; border: 1px solid #dee1e2; text-align: center;">
								<input type="radio" name="alertType" id="select1" autocomplete="off"><span id="s1"></span>
								<input type="radio" name="alertType" id="select2" autocomplete="off"><span id="s2"></span>
							</div></td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="button"value="保存" style="width: 100px; height: 40px" onclick="AddWarn()" />
							<input type="button"value="取消"  style="width: 100px; height: 40px" onclick="cancelDim()"/>
						</td>
					</tr>
				</tbody>
			</table>
			<input type="hidden" id="zcCode" value="">
		</div>
	</form>

</body>
</html>