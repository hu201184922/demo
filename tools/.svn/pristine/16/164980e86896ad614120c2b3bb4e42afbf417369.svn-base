<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/util.tld" prefix="u"%>
<html>
<head>
<title>固定清单</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
	.bolder{font-weight: bolder;}
	.divarea {clear: both;width: 1026px;border: 1px solid #dee1e2;overflow: hidden;}
	.gs {float: left;width:100%; border: 1px solid #dee1e2;background-color: #F7F7F7;}
	.gs span {width: 100%;float: left;padding: 10px 0 10px 10px;}
	.in_left {width: 100px;float: left;padding: 0!important;border-bottom: 0px!important;}
	.in_right {width: 300px;height: 25px;margin-top: 6px;float: left;}
	.btn_cate{width: 212px;height: 30px;border-radius: 5px;border:1px solid #90C1E6;}
</style>
<script type="text/javascript">
	var myObj = {};
	$(function() {
		$('#pager').pager('${pageContext.request.contextPath}/admin/bill/list',15,$('#parameterView').view().on('add',function(data){
			(function(zhis){
				var deleteConfirm = new Ace.awt.ConfirmBox({
					widget : $('<div style="background-color:#FFFFFF; text-align:center; display:none; line-height:50px;width: 100px;height: 50px; border-radius: 5px;border:1px solid #90C1E6;"><span class="yesorno"><a href="javascript:void(0)" class="confirm">确定</a>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" class="cancel">取消</a></span></div>'),
					trigger : zhis.target.find('.removed'),
					positionAdjust : [ 6, -45 ]
				});	
				deleteConfirm.on('confirm',function(w, t) {
					$.post('${pageContext.request.contextPath}/admin/bill/delete',{id:data.billId},function(bit){
						if(bit==true){
							$.msgbox({time: 2000,msg: "删除成功!",icon:"success"});
						}
						$('#pager').pager().reload(function(){
							zhis.target.remove();
						});
					});
				});
			})(this);
		}));
		$('#pager').pager().reload();
		$('#parameterAjaxForm').ajaxForm(function(data) {
			$('#pager').pager().setPostData(Ace.parseQuery($('#parameterAjaxForm').serialize()));
			$('#pager').pager().setJSON(data);
		});
		
		$("#createParameter").click(function() {
			if ($('#btnCate').val() == 1) {
				$('#btnCate').val(0);
				$.removeData(myObj, "formula");
				$.removeData(myObj, "usualDim");
				$('input[name="billName1"]').val("");
				$('input[name="remark"]').val("");
				$('#billIdHas').val("-1");
			}
			$('#btnCate').val(0);
			$("select[name='deptCode']").find('option[value=-1]').attr('selected', true);
			var postData = {
				deptCode: ''
			}
			$('#pager1').pager('${pageContext.request.contextPath}/admin/bill/targetlist' ,5,$('#parameterView1').view().on('add',function(data){
				(function(zhis){
					var deleteConfirm = new Ace.awt.ConfirmBox({
						widget : $('<div style="background-color:#FFFFFF; text-align:center; display:none; line-height:50px;width: 100px;height: 50px; border-radius: 5px;border:1px solid #90C1E6;"><span class="yesorno"><a href="javascript:void(0)" class="confirm">确定</a>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" class="cancel">取消</a></span></div>'),
						trigger : zhis.target.find('.removed'),
						positionAdjust : [ 6, -45 ]
					});	
				})(this);
				sebox();
			}));
			$('#saveParameterForm').data("dialog", $.dialog({
				title : "添加",
				content : $('#saveParameterForm')[0]
			}));
			$('#pager1').pager().setPostData(postData);
			$('#pager1').pager().reload(function(){
				sebox();
			});
		});
		$('#saveParameterForm').ajaxForm(function(data) {
			$('#saveParameterForm').data("dialog").close();
			$('#pager').pager().reload();
		});
		$("input[name='formula']").click(function(){
			$(".in_right").hide();
			if (this.id == 't1') {
				$("input[name='more']").show();
			} else if (this.id == 't2') {
				$("input[name='less']").show();
			} else {
				$("input[name^='range_']").parent().show();
			}
		});
		$("u[id$='_id']").hide();
		$(".in_right").hide();
		$("div[id^='form']").hide();
		$("#pubDim").hide();
		
		//加载树
		var deptListNodes = JSON.parse('${deptInfos}');
		for(var i=0;i<deptListNodes.length;i++){
			deptListNodes[i].id = deptListNodes[i].deptCode;//添加节点id属性
			deptListNodes[i].pId = deptListNodes[i].deptPid;//添加节点pid属性
			deptListNodes[i].name = deptListNodes[i].deptName;//添加name属性
			deptListNodes[i].isParent = true;//把所有节点默认为父节点	
		}
		var treeOfTarget;
		var canSave = 0;
		var setting = {
			data : {
				simpleData : {
					enable : true,
					idKey: 'id',
					pIdKey: 'pId'
				}
			}
			,view:{
				showIcon:true,
				showLine: true
			},check:{enable:true},
			callback:{
				onClick:null
			}
		};
		treeOfTarget = $('#deptTree').zTree(setting,deptListNodes).expandAll(true);
	});	
	
	function searchForTarget() {
		var code = $('input[name="targetCode1"]').val();
		var name = $('input[name="targetName1"]').val();
		var postData = {
			targetCode: code,
			targetName: name
		}
		$('#pager1').pager('${pageContext.request.contextPath}/admin/bill/targetlist' ,5,$('#parameterView1').view().on('add',function(data){
			(function(zhis){
				var deleteConfirm = new Ace.awt.ConfirmBox({
					widget : $('<div style="background-color:#FFFFFF; text-align:center; display:none; line-height:50px;width: 100px;height: 50px; border-radius: 5px;border:1px solid #90C1E6;"><span class="yesorno"><a href="javascript:void(0)" class="confirm">确定</a>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" class="cancel">取消</a></span></div>'),
					trigger : zhis.target.find('.removed'),
					positionAdjust : [ 6, -45 ]
				});	
			})(this);
		}));
		$('#pager1').pager().setPostData(postData);
		$('#pager1').pager().reload(function(){
			sebox();
		});
	}
	
	function showDim(w){
		$("#pubDim").hide();
		if (w.checked == true) {
			$("#"+w.value+"_id").show();
		} else {
			$("#"+w.value+"_id").hide();
			$("#formu").hide();
			$('input[name="targetC"]').val("");
			var formula = $.data(myObj, "formula");
			var newf = "";
			if (typeof(formula)!="undefined") {
				var fli = formula.substring(0,formula.length-1).split(",");
				for (var i=0; i<fli.length; i++) {
					if (fli[i].indexOf(w.value) == -1) {
						newf += fli[i]+",";
					}
				}
				$.data(myObj, "formula", newf);
			}
			var usd = $.data(myObj, 'usualDim');
			if ( typeof(usd) != "undefined") {
				var sli = usd.substring(0,usd.length-1).split(",");
				var newusd = "";
				for (var i=0; i<sli.length; i++) {
					if (sli[i].indexOf(w.value) == -1) {
						newusd += sli[i]+",";
					}
				}
				$.data(myObj, 'usualDim', newusd);
			}
		} 
	}
	
	function setDime(code){
		$("input[name='formula']").attr("checked", false);
		$("#zcCode").val(code);
		$("#pubDim").hide();
		$(".in_right").hide();
		$(".in_right").val('');
		$("input[name='range_min']").val('');
		$("input[name='range_max']").val('');
		$("input[name='sort']").val('');
		$("#dimlist").empty();
		$("u[id$='_id']").removeClass('bolder');
		$("#"+code+"_id").addClass('bolder');
		$.post('${pageContext.request.contextPath}/admin/bill/getDimlist', {'targetCode':code}, function(data){
			var usualDim = $.data(myObj, 'usualDim');
			if (data.length == 0) {
				$("#dimlist").append("<span style='text-align: center;'>暂无维度</span>");
			}
			$.each(data, function(k, v){
				var check="";
				if (typeof(usualDim) != 'undefined') {
					var ulist = usualDim.split(",");
					for (var i=0;i<ulist.length;i++) {
						if(ulist[i]==v.queryDimCode + "|"+code){
							check = "checked";
						}
					}
				}
				$("#dimlist").append("<input "+check+" type='checkbox' name='dimCode'"+
						" value='" + v.queryDimCode + "|"+code+"'> " + v.queryDimName + "&nbsp;");
			});
			
			var formula = $.data(myObj, 'formula');
			if (typeof(formula) != 'undefined') {
				seraido(formula, code);
			}
		});
		$("div[id^='form']").show();
		$('input[name="targetC"]').val(code);
	}
	
	function setPubDim(){
		$("#grolist").empty();
		$.post('${pageContext.request.contextPath}/admin/bill/getPublist', function(data){
			var groupDimStr = $.data(myObj, 'groupDim');
			$.each(data, function(k, v){
				var check = "";
				if (typeof(groupDimStr) != 'undefined') {
					var ulist = groupDimStr.split(",");
					for (var i=0;i<ulist.length;i++) {
						if(ulist[i]==v.groDimTypeCode){
							check = "checked";
						}
					}
				}
				$("#grolist").append("<input onclick='grp(this)' "+check+" type='checkbox' name='groCode' "+
							"value='" + v.groDimTypeCode + "'> " + v.groDimTypeName + "&nbsp;");		
			});
		});
		$("div[id^='form']").hide();
		$("#pubDim").show();
	}
	
	//分组维度
	function grp(obj){
		if (typeof($.data(myObj, 'groupDim')) == "undefined") {
			$.data(myObj, 'groupDim', '');
		}
		var groupDimStr = $.data(myObj, 'groupDim');
		if (obj.checked == true) {
			if ( groupDimStr.indexOf(obj.value) == -1) {
				groupDimStr += obj.value+',';
			}
		} else {
			groupDimStr = groupDimStr.replace(obj.value+',', '');
		}
		$.data(myObj, "groupDim", groupDimStr);
	}
	
	//暂存
	function tempSave(){
		var targetCode = $("#zcCode").val();
		var reg = /^\d{1,8}(\.\d{1,6})?$/;
		var curTargetCode = $('input[name="targetC"]').val();
		var fc = $('input[name="formula"]:checked').val();
		var data;
		if ( typeof(fc) == "undefined" ) {
			$.msgbox({time: 2000,msg: "请选择公式并填入值",icon:"error"});
			return;
		}
		if (fc == "range") {
			var dmin = $('input[name="'+ fc +'_min"]').val();
			var dmax = $('input[name="'+ fc +'_max"]').val();
			if (!reg.test(dmin) || !reg.test(dmax)) {
				$.msgbox({time: 2000,msg: "请输入数值",icon:"error"});
				return;
			}
			if (dmin == "" || dmax == "") {
				$.msgbox({time: 2000,msg: "请输入指标范围值",icon:"error"});
				return;
			}
			if (dmin > dmax) {
				$.msgbox({time: 2000,msg: "范围值错误",icon:"error"});
				return;
			}
		} else {
			data = $('input[name="'+ fc +'"]').val();
			if ( data=="" ) {
				$.msgbox({time: 2000,msg: "请输入公式值",icon:"error"});
				return;
			}
			if (!reg.test(data)) {
				$.msgbox({time: 2000,msg: "请输入数值",icon:"error"});
				return;
			}
		}
		
		//普通维度
		var wd = $('input[name="dimCode"]').val();
		if (wd.length == 0) {
			$.msgbox({time: 2000,msg: "请选择指标维度",icon:"error"});
			return;
		}
		
		//公共维度
// 		var grodimStr = $.data(myObj, 'groupDim');
// 		if (typeof (grodimStr) == "undefined" || grodimStr == "") {
// 			$.msgbox({time: 2000,msg: "请点击右上角选择公共维度！",icon:"error"});
// 			return;
// 		}
		
		var sort = $('input[name="sort"]').val();
		var reg1 = /^[1-9]\d*$/;
		if (!reg1.test(sort)) {
			$.msgbox({time: 2000,msg: "请输入正确格式的排序！",icon:"error"});
			return;
		}
		
		//选中值放入缓存中
		//公式
		var hasf = $.data(myObj, 'formula');
		if ( typeof(hasf) != "undefined") {
			var hli = hasf.substring(0,hasf.length-1).split(",");
			var news="";
			for(var i=0;i<hli.length;i++ ){
				var tar = hli[i].split("|");
				if (tar[1] != targetCode) {
					news += hli[i]+",";
				}
			}
			if ( fc=="range" ) {
				news += fc+"&"+dmin+"~"+dmax+"#"+sort+"|"+targetCode+",";
			} else {
				news += fc+"&"+data+"#"+sort+"|"+targetCode+",";
			}
			hasf = news;
		} else {
			if (fc == "range") {
				hasf = fc+"&"+dmin+"~"+dmax+"#"+sort+"|"+targetCode+",";
			} else {
				hasf = fc+"&"+data+"#"+sort+"|"+targetCode+",";
			}
		}
		$.data(myObj, 'formula', hasf);
		
		//普通维度
		var tdim = "";
		$.each(wd, function(k, v){
			tdim += v+",";
		});
		var usd = $.data(myObj, 'usualDim');
		if ( typeof(usd) != "undefined") {
			var sli = usd.substring(0,usd.length-1).split(",");
			var newusd = "";
			for (var i=0; i<sli.length; i++) {
				var fa = sli[i].split("|");
				if (fa[1] != targetCode) {
					newusd += sli[i]+",";
				}
			}
			newusd += tdim;
			usd = newusd;
		}else{
			usd = tdim;
		}
		$.data(myObj, 'usualDim', usd);
// 		alert(JSON.stringify($.data(myObj)));
		$.msgbox({time: 2000,msg: "成功暂存指标&nbsp;"+targetCode, icon:"success"});
		return;
	}
	
	//单选选中
	function seraido(formula, code){
		var folist = formula.split(",");
		for (var i=0;i<folist.length;i++) {
			var cl = folist[i].split("|");
			if (cl[1] == code) {
				var cazhi = cl[0];
				var cz = cazhi.split("&");
				if (cz[0] == "more") {
					$("#t1").attr("checked", true);
					$("#t2").attr("checked", false);
					$("#t3").attr("checked", false);
					var c = cz[1].split("#");
					$("input[name='more']").val(c[0]);
					$("input[name='sort']").val(c[1]);
					$("input[name='more']").css("display", "block");
				} else if (cz[0] == "less") {
					$("#t1").attr("checked", false);
					$("#t2").attr("checked", true);
					$("#t3").attr("checked", false);
					var c = cz[1].split("#");
					$("input[name='less']").val(c[0]);
					$("input[name='sort']").val(c[1]);
					$("input[name='less']").css("display", "block");
				} else {
					$("#t1").attr("checked", false);
					$("#t2").attr("checked", false);
					$("#t3").attr("checked", true);
					var c = cz[1].split("#");
					$("input[name='sort']").val(c[1]);
					var rl = c[0].split("~");
					$("input[name='range_min']").val(rl[0]);
					$("input[name='range_max']").val(rl[1]);
					$("#ran").css("display", "block");
				}
			}
		}
	}
	
	// 若有缓存，则已选中
	function sebox(){
		var formula = $.data(myObj, "formula");
		if (typeof(formula)!="undefined") {
			var blist = $("input[id^='box_']");
			for (var i=0; i<blist.length;i++) {
				if (formula.indexOf(blist[i].value+",")!=-1) {
					$("#box_"+blist[i].value).attr("checked", true);
					$("#"+blist[i].value+"_id").show();
				}
			}
		}
		$("#formu").hide();
		$("#pubDim").hide();
	}
	
	// 保存清单
	function saveDim(){
		var btn = $('#btnCate').val();
		var billName = $('input[name="billName1"]').val();
		var remark = $('input[name="remark"]').val();
		var formula = $.data(myObj, "formula");
		var deptCode = $("select[name='deptCode'] option:selected").val();
		if (billName == '') {
			$.msgbox({time: 2000,msg: "请输入新增清单名称", icon:"error"});
			return;
		}
		if (typeof(formula) == "undefined" || formula=="") {
			$.msgbox({time: 2000,msg: "请给清单添加指标", icon:"error"});
			return;
		}
		if (deptCode == -1) {
			$.msgbox({time: 2000,msg: "请选择对应清单部门", icon:"error"});
			return;
		}
		if (btn == 0) {
			$.post('${pageContext.request.contextPath}/admin/bill/saveTarlistBill' , 
					{'billName': billName,'remark': remark,'deptCode': deptCode, 'dataJson': JSON.stringify($.data(myObj))}, function(data){
				if(data == true){
					$.msgbox({time: 2000,msg: "保存成功", icon:"success"});
					$('#pager').pager().reload(function(){
						$.removeData(myObj, "formula");
						$.removeData(myObj, "usualDim");
						$.removeData(myObj, "groupDim");
						$('#saveParameterForm').data("dialog").close();
					});
				}
			});
		} else {
			var billId = $("#billIdHas").val();
			$.post('${pageContext.request.contextPath}/admin/bill/updateTarlistBill' , 
					{'billName': billName,'remark': remark,'deptCode': deptCode,'billId': billId, 'dataJson': JSON.stringify($.data(myObj))}, function(data){
				if(data == true){
					$.msgbox({time: 2000,msg: "保存成功", icon:"success"});
					$('#pager').pager().reload(function(){
						$.removeData(myObj, "formula");
						$.removeData(myObj, "usualDim");
						$.removeData(myObj, "groupDim");
						$('#saveParameterForm').data("dialog").close();
					});
				}
			});
		}
		
	}
	
	// 	取消缓存
	function cancelDim(){
		$.removeData(myObj, "formula");
		$.removeData(myObj, "usualDim");
		$('input[name="billName1"]').val("");
		$('input[name="remark"]').val("");
		$('#billIdHas').val("-1");
// 		$.removeData(myObj, "groupDim");
// 		$.msgbox({time: 2000,msg: "成功清除暂存内容", icon:"success"});
	}
	
	// 修改清单
	function updateFilt(qid, deptCode){
		$("#btnCate").val(1); // 点击类型
		var postData = {
			deptCode: deptCode
		}
		$('#pager1').pager('${pageContext.request.contextPath}/admin/bill/targetlist' ,5,$('#parameterView1').view().on('add',function(data){
			(function(zhis){
				var deleteConfirm = new Ace.awt.ConfirmBox({
					widget : $('<div style="background-color:#FFFFFF; text-align:center; display:none; line-height:50px;width: 100px;height: 50px; border-radius: 5px;border:1px solid #90C1E6;"><span class="yesorno"><a href="javascript:void(0)" class="confirm">确定</a>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" class="cancel">取消</a></span></div>'),
					trigger : zhis.target.find('.removed'),
					positionAdjust : [ 6, -45 ]
				});	
			})(this);
			sebox();
		}));
		$('#saveParameterForm').data("dialog", $.dialog({
			title : "修改",
			content : $('#saveParameterForm')[0]
		}));
		$('#pager1').pager().setPostData(postData);
		$('#pager1').pager().reload(function(){
			$.post('${pageContext.request.contextPath}/admin/bill/getBtListByQid', {'qid': qid, 'deptCode': deptCode}, function(data){
				$.removeData(myObj, "formula");
				$.removeData(myObj, "usualDim");
				$('input[name="billName1"]').val(data.billName);
				$('input[name="remark"]').val(data.remark);
				$('#billIdHas').val(data.billId);
				$.data(myObj, "formula", data.formula);
				$.data(myObj, "usualDim", data.usualDim);
// 				alert(JSON.stringify($.data(myObj))+"=========");
				sebox();
			});
		});
		$("select[name='deptCode']").find('option[value='+deptCode+']').attr('selected', true);
	}
	
	// 权限配置
	function authority(billId){
		$('#giveauthority').data("dialog", $.dialog({
			title : "配置权限",
			content : $('#giveauthority')[0]
		}));
		$('#authBillId').val(billId);
		var treeObj = $('#deptTree').zTree();
		treeObj.checkAllNodes(false);  // 全部取消选中
		$.post('${pageContext.request.contextPath}/admin/bill/getAuthByBillId', {'billId': billId}, function(data){
			if (data.length != 0) {
				var ds = data[0].deptCode;
				var darr = ds.split(",");
				var nodes = treeObj.getNodes();
				for (var i=0; i<nodes.length; i++) {
					if (nodes[i].isParent) {
						var child = nodes[i].children;
						for (var j=0; j<child.length; j++) {
							for (var z=0; z<darr.length; z++) {
								if (darr[z] == child[j].id) {
									child[j].checked = true;
								}
							}
							treeObj.updateNode(child[j]);
						}
					}
				}
			}
		});
	}
	
	// 保存权限
	function saveAuthority(){
		var billId = $('#authBillId').val();
		var nodes = $('#deptTree').zTree().getCheckedNodes(true);
		var arr=[];
		nodes.each(function(){
			arr.push(this.deptCode);
		});
		$("#deptCodes").val(arr);
		var dc = $("#deptCodes").val()
		if (dc == "") {
			$.msgbox({time: 2000,msg: "请选择清单对应的权限部门", icon:"error"});
			return;
		}
		$.post('${pageContext.request.contextPath}/admin/bill/saveAuthority', {'billId': billId, 'deptCodes': $("#deptCodes").val()}, function(data){
			$.msgbox({time: 2000,msg: "保存成功", icon:"success"});
			$('#pager').pager().reload(function(){
				$('#giveauthority').data("dialog").close();
			});
		});
	}
	
</script>

</head>
<body>
	<div class="business_title">固定清单</div>
	<div name="giveauthority" id="giveauthority" enctype="multipart/form-data" style="display: none;">
		<input type="hidden" id="authBillId">
		<input type="hidden" id="deptCodes">
		<ul id="deptTree" class="ztree"></ul>
		<input type="button" value="保&#12288;存" onclick="saveAuthority()"
		 style="float: left;margin: 0 50px;cursor: pointer;width: 60px;height: 25px;border-radius: 5px;border:1px solid #90C1E6;">
	</div>
	<div class="col_lg_04" style="width: 1203px">
		<div class="business_search_list_warp" style="width: 95%">
			<input type="button" id="createParameter" value="新增" style="width: 80px;height: 30px; border-radius: 5px;border:1px solid #90C1E6;">
		 	<div class="business_search">
				<div class="business_search_left" style="margin-left: -20px;">
					<form id="parameterAjaxForm"
						action="${pageContext.request.contextPath}/admin/bill/list"
						method="post">
						<input type="hidden" name="pageSize" value="15" />
						<table>
							<tbody>
								<tr>
									<td style="text-align: right">我的清单名称：</td>
									<td><input style="width: 300px;height: 30px;border-radius: 5px;border:1px solid #90C1E6;" type="text"
										name="billName"  class="text">&nbsp;&nbsp;</td>
									<td><input type="submit" value="查&#12288;询"
										style="width: 60px;height: 25px;border-radius: 5px;border:1px solid #90C1E6;"></td>
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
						<th width="150px">清单名称</th>
						<th width="">备注</th>
						<th style="border-right: 1px solid #ccc; width: 95px">操作</th>
					</tr>
					<tr class="tb_tr_content template" name="default">
						<td>{index:seq()}</td>
						<td>{billName}</td>
						<td>{remark}</td>
						<td style="border-right: 1px solid #ccc"><span
							class="span_edit"><a href="javascript:void(0)"
								class="switch" template="update"> <img onclick="updateFilt('{billId}','{deptCode}')" style="float: left;"
								src="${pageContext.request.contextPath}/static/images/xiugai.png"
									title="修改" />
							</a><a href="javascript:void(0)" onclick="authority('{billId}')">
					  			<img src="${pageContext.request.contextPath}/static/images/shouquan.png" title="权限配置"/>
					  		</a>&nbsp;&nbsp;<a href="javascript:void(0)" class="removed"> <img
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
	<form name="coreTargetForm" id="saveParameterForm" enctype="multipart/form-data" style="display: none;"
		action="${pageContext.request.contextPath}/admin/bill/create"
		method="post">
		<input type="hidden" id="btnCate" value="0">
		<div style="margin-left: 7px;">
			<span class="text_right">清单名称：</span>
			<span class="text_left"><input class="btn_cate" type="text" name="billName1"></span>
			<span class="text_right">备注：</span>
			<span class="text_left"><input class="btn_cate" type="text" name="remark"></span>
			<span class="text_right">所属部门：</span>
			<span class="text_left">
				<select name="deptCode" class="btn_cate" id="dept">
					<option value="-1">-请选择--</option>
					<c:forEach items="${depts}" var="dept">
					   <option value="${dept.deptCode}">${dept.deptName}</option>
					</c:forEach>
				</select>
			</span>
			<input type="hidden" id="billIdHas" value="-1">
		</div>
		<div id="tagContent2" class="tagContent tagContent_div"
			style="display: block; background: #F7F7F7">
			<tbody>
				<div class="col_lg_04" style="padding: 0px 20px 0px 20px">
					<div class="business_search_list_warp">
						<div class="business_search">
							<div class="business_search_left" style="margin-left: -20px;">
								<form id="parameterAjaxForm1">
									<input type="hidden" name="pageSize" value="15" />
									<table>
										<tbody>
											<tr>
												<td style="text-align: right">指标代码：</td>
												<td><input style="width: 200px;height: 30px;border-radius: 5px;border:1px solid #90C1E6;" type="text"
													name="targetCode1"  class="text">&nbsp;&nbsp;</td>
												<td style="text-align: right">指标名称：</td>
												<td><input style="width: 200px;height: 30px;border-radius: 5px;border:1px solid #90C1E6;" type="text"
													name="targetName1"  class="text">&nbsp;&nbsp;</td>
												<td><input type="button" onclick="searchForTarget()" value="查&#12288;询"
													style="width: 60px;height: 25px;border-radius: 5px;border:1px solid #90C1E6;"></td>
											</tr>
										</tbody>
									</table>
								</form>
							</div>
							<input type="button" onclick="setPubDim()" value="公共维度"
								    style="display: none;float:right;cursor: pointer;width: 60px;height: 25px;border-radius: 5px;border:1px solid #90C1E6;">
						</div>
						<table cellspacing="0" cellpadding="0" class="t-list table" id="parameterView1">
							<tbody id="relaTt">
								<tr>
									<th width="100px">选择</th>
									<th width="150px">指标代码</th>
									<th width="150px">指标名称</th>
									<th width="100px">指标属性</th>
									<th width="100px">部门编码</th>
									<th width="100px">使用渠道</th>
									<th width="100px">专属维度</th>
								</tr>
								<tr class="tb_tr_content template" name="default">
									<td><input type="checkbox" onclick="showDim(this)" value="{targetCode}" id="box_{targetCode}"></td>
									<td>{targetCode}</td>
									<td>{targetName}</td>
									<td>{targetType:dict({0:'一级',1:'二级',2:'二级',3:'二级'})}</td>
									<td>{deptCode}</td>
									<td>{channel}</td>
									<td><u style="cursor: pointer;" id="{targetCode}_id" onclick="setDime('{targetCode}')">设置维度</u></td>
								</tr>
							</tbody>
						</table>
						<div id="pager1"></div>
						<tr><td height="20px" colspan="2"></td></tr>
					</div>
				</div>
				<div class="col_lg_04" style="padding: 0px 20px 20px 20px" id="formu">
					<input type="hidden" name="targetC">
					<span style="float: left;width:100%; font: 14px/30px 'Microsoft Yahei';">公式</span>
					<span class="gs">
						<span><p class="in_left"><input type="radio" name="formula" id="t1" value="more">指标大于</p><input class="in_right" type="text" name="more"></span>
						<span><p class="in_left"><input type="radio" name="formula" id="t2" value="less">指标小于</p><input class="in_right" type="text" name="less"></span>
						<span>
							<p class="in_left"><input type="radio" name="formula" id="t3" value="range">指标值范围</p>
							<h class="in_right" id="ran"><input type="text" style="height: 25px;" name="range_min">&nbsp;到<input style="height: 25px;float: right;margin: 0;" type="text" name="range_max"></h>
						</span>
					</span>
					<span style="float: left;width:100%; font: 14px/30px 'Microsoft Yahei';">维度</span>
					<span class="gs" id="dimlist" style="padding: 10px 0px;"></span>
					<span style="float: left;width:100%; font: 14px/30px 'Microsoft Yahei';">其他属性</span>
					<span class="gs" id="sort" style="padding: 10px 0px;">&nbsp;排序：<input style="width: 300px;height: 25px;margin-top: 6px;" type="text" name="sort"></span>
					<span style="float: left;width: calc(100% - 800px);padding: 10px 450px;"><input style="width: 100px;" type="button" value="暂存" onclick="tempSave()"
						class="search_btn"/>&nbsp;&nbsp;</span>
					<input type="hidden" id="zcCode" value="">
				</div>
				<div class="col_lg_04" style="padding: 0px 20px 20px 20px" id="pubDim">
					<span style="float: left;width:100%; font: 14px/30px 'Microsoft Yahei';">属性设置</span>
					<span class="gs" id="grolist" style="padding: 10px 0px;"></span>
				</div>
				<div style="float: left;width: calc(100% - 800px);padding: 10px 450px;"><input type="button" value="保存" onclick="saveDim()"
						class="search_btn"/>&nbsp;&nbsp;<input type="button" onclick="cancelDim()"
						value="取消" class="search_btn close"/></div>
			</tbody>
		</div>
	</form>
</body>
</html>