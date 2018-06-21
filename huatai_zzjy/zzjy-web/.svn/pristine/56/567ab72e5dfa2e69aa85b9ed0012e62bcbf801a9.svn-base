<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/util.tld" prefix="u"%>
<html>
<head>

<title>预警结果</title>
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
	
	$(function() {
		$('#WarnTarget').val="";
		$('#MonitorObject').val="";
		$('#pager').pager('${pageContext.request.contextPath}/admin/userSetWarn/resultlist',15,$('#parameterView').view().on('add',function(data){
			(function(zhis){
				var deleteConfirm = new Ace.awt.ConfirmBox({
					widget : $('<div style="background-color:#FFFFFF; text-align:center; display:none; line-height:50px;width: 100px;height: 50px; border-radius: 5px;border:1px solid #90C1E6;"><span class="yesorno"><a href="javascript:void(0)" class="confirm">确定</a>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" class="cancel">取消</a></span></div>'),
					trigger : zhis.target.find('.removed'),
					positionAdjust : [ 6, -45 ]
				});	
				deleteConfirm.on('confirm',function(w, t) {
					$.getJSON('${pageContext.request.contextPath}/admin/userSetWarn/delete',{id:data.bsId},function(bit){
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
		
		$('#queryWarnForm').ajaxForm(function(data) {
			$('#pager').pager().setPostData(Ace.parseQuery($('#queryWarnForm').serialize()));
			$('#pager').pager().setJSON(data);
		});
		
		$('#saveTargetForm').ajaxForm(function(data) {
			$('#saveTargetForm').data("dialog").close();
			$('#pager').pager().reload();
		});
		
});
	
	//我的预警跳转
	function WarnIndex(){
		window.location.href="${pageContext.request.contextPath}/admin/userSetWarn/index";
		//alert(window.location);
	}
	//预警结果跳转
	function result() {
		window.location.href = "${pageContext.request.contextPath}/admin/userSetWarn/result";
	}
	
</script>
</head>
<body>
	
	<div class="col_lg_04" style="width: 100%">
		<div class="business_title" style="background-color:#90C1E6">查询条件</div>
		<form id="queryWarnForm"
			action="${pageContext.request.contextPath}/admin/userSetWarn/resultlist"
			method="post">
			<input type="hidden" name="pageSize" value="10" />
			<table>
				<tbody>
					<tr>
						<td style="text-align: right">监控对象：</td>
						<td><input style="width: 150px;height: 30px;border-radius: 5px;border:1px solid #90C1E6;" type="text"
							name="RorgName" id="RorgName" class="text">&nbsp;&nbsp;</td>
						<td style="text-align: right">预警指标：</td>
						<td><input style="width: 150px;height: 30px;border-radius: 5px;border:1px solid #90C1E6;" type="text"
							name="RwarnTarget" id="RwarnTarget" class="text">&nbsp;&nbsp;</td>
						<td><input type="submit" value="查&#12288;询"
							style="width: 60px;height: 25px;border-radius: 5px;border:1px solid #90C1E6;"></td>
					</tr>
				</tbody>
			</table>


			<div class="col_lg_04" style="width: 1203px">
				<div class="business_search_list_warp" style="width: 95%">
				 	<div class="business_search">
						<div class="business_search_left" style="margin-left: -20px;">
							<tr>
								<td>
									<input type="button" id="MyWarn" value="我的预警" 
									style="width: 80px;height: 30px; border-radius: 5px;border:1px solid #90C1E6;" 
									onclick="WarnIndex()">
								</td>
								<td>
									<input type="button" id="WarnResult" value="预警结果" 
									style="background-color:#90C1E6;width: 80px;height: 30px; border-radius: 5px;border:1px solid #90C1E6;" 
									onclick="result()">
								</td>
							</tr>
						</div>
					</div>
					
					<table cellspacing="0" cellpadding="0" class="t-list table"id="parameterView">
						<tbody>
							<tr>
								<th width="120px">监控对象</th>
								<th width="120px">预警指标</th>
								<th width="130px">预警条件</th>
								<th width="80px">指标值</th>
								<th width="90px">预警时间</th>
							</tr>
							<tr class="tb_tr_content template" name="default">
								<td>{orgName}</td>
								<td>{targetName}</td>
								<td>{warnCode}</td>
								<td>{warnVal}</td>
								<td>{warnTime}</td>
							</tr>
						</tbody>
					</table>
					<div id="pager"></div>
				</div>
				<div class="clear"></div>
			</div>
		</form>
	</div>
</body>
</html>