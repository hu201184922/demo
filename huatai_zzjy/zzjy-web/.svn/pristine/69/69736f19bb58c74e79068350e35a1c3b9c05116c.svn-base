<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/ace.tld" prefix="ace"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>数据修正</title>
<script type="text/javascript">
	var domodify_index=0;
	$(function() {
		
		/**
		 * 格式化时间
		 */
		var _formatDate =function(now) {
			var year = now.getFullYear();
			var month = now.getMonth() + 1;
			var date = now.getDate();
			var hour = now.getHours();
			var minute = now.getMinutes();
			var second = now.getSeconds();
			return year + "-" + month + "-" + date + "   " + hour + ":" + minute+ ":" + second;
		}
		
		/**
		 * 提交事件
		 */
		$("#submitButton").click(function(){
			var _dsIndex=$('#dsIndex').val();
			var _limitNum=$('#limitNum').val();
			var _beginRow=$.trim($('#beginRow').val());
			var _endRow=$.trim($('#endRow').val());
			var _sql=$.trim($('#sql').val());
			if(_limitNum=="on" && ( _beginRow =="" || _endRow == "" )){
				alert("限制行数时,行数区间不能为空!");
				return false;
			}
			if(_sql==""){
				alert("SQL为空!");
				return false;
			}
			$('#sqlHisDiv').append("<span>"+domodify_index + "# ["+_formatDate(new Date())+"] " +_sql+"</span><br>");
			domodify_index=domodify_index+1;
			$.ajax({
				type : "POST",
				async : false,
				url : "${pageContext.request.contextPath}/admin/datamodify/domodify",
				data : {
					dsIndex: _dsIndex,
					limitNum: _limitNum,
					beginRow: _beginRow,
					endRow: _endRow,
					sql: _sql
				},
				success : function(data) {
					var msg=$.parseJSON(data);
					$("#List").html(msg.msg);
					$('.mainplantip').qtip({
						position: {
						    my: 'top middle',
						    at: 'bottom center'
						},
						content: {
							attr: 'tip'
						}
						,style: {
							width:200
						}
					});
				}
			});
			
		});
		
	});
</script>
<style type="text/css">
#sqlHisDiv { overflow: auto;height: 300px;margin: 0 15px;background: #666; }
#sqlHisDiv span { color: #FF0;}
</style>
</head>
<body>
<div class="tagContent tagContent_div" style="display: block; width: 100%">
	<table style="width:100%;border-collapse:collapse;">
	<tr>
	<td width="50%">
	<form id="saveForm" action="<c:out value="${pageContext.request.contextPath}"/>/admin/datamodify/domodify" method="post">
		<table width="100%">
			<tr>
				<td>数据源：</td>
				<td>
					<select id="dsIndex" name="dsIndex">
						<option value="1">mariadb库</option>
						<option value="2">oracle库</option>
						<option value="3">impala库</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>限制行数：</td>
				<td><input type="checkbox" id="limitNum" name="limitNum" checked="checked"></td>
			</tr>
			<tr>
				<td>行数区间：</td>
				<td>
					<input type="text" id="beginRow" name="beginRow" value="1">
					 - 
					<input type="text" id="endRow" name="endRow" value="10">
				</td>
			</tr>
			<tr>
				<td colspan="2">
				<textarea id="sql" name="sql" style="width:500px; height: 200px;" ></textarea>
				</td>
			</tr>
			<tr>
			</tr>
				<td colspan="2">
				<input type="button" value="提 交" id="submitButton" class="search_btn" />
				</td>
			</tr>
		</table>
	</form>
	</td>
	<td width="50%" style="vertical-align: top;white-space:nowrap;">
		<div id="sqlHisDiv">
		</div>
	</td>
	</tr>
	</table>
</div>
<div style="width: 100%; margin-top:20px; overflow: auto;" >
<div id="List"></div>
</div>

</body>
</html>