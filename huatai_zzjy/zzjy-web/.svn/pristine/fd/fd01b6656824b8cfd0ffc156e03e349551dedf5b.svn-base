<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/util.tld" prefix="u"%>
<html decorator="null">
<head>
<title>任务推送日志</title>
<script type="text/javascript">
	Date.parse=function(source, formatter){
	    try {
	        if (/^\d[\S\s]+\d$/.test(source)) {
	            return new Date(source);
	        }
	        if (!formatter || formatter == "") {
	            formatter = "yyyy-MM-dd hh:mm:ss";
	        }
	        var data = new Array();
	        var formatters = new Array('yyyy', 'MM', 'dd', 'hh', 'mm', 'ss');
	        formatters.each(function(index){
	            var of = formatter.indexOf(this);
	            data[index] = (of > -1) ? source.substring(of, of + this.length) : '0'.addZeroLeft(this.length);
	        });
	        data[1] = Number(data[1]) > 0 ? Number(data[1]) - 1 : 0;
	        return new Date(data[0], data[1], data[2], data[3], data[4], data[5]);
	    } 
	    catch (e) {
	        throw new Error(source + ',转换日期格式失败!(' + formatter ? formatter : 'Sun Mar 06 08:41:59 GMT 2011' + ')');
	    }
	};
	$(function() {
		$('#taskPushJobPager')
				.pager(
						'${pageContext.request.contextPath}/admin/quartz/getTaskJobLogList',
						{
							'jobName' : '${jobName}'
						},10 , $('#taskPushJobView').view().on('add', function() {
						}));
		$('#taskPushJobPager').pager().reload();

	});
</script>
</head>
<body>
	<div class="tagContentList">
		<div class="business_title">任务推送日志</div>
		<div class="col_lg_04" style="width: 1000px">
			<div class="business_search_list_warp" style="width: 95%">
				<table cellspacing="0" cellpadding="0" class="t-list table"
					id="taskPushJobView">
					<tr>
						<th>任务名称</th>
						<th>主机名</th>
						<th>推送时间</th>
						<th>即将到期任务推送条数</th>
						<th>逾期任务推送条数</th>
					</tr>
					<tr class="tb_tr_content template" name="default">
						<td>{jobName}</td>
						<td>{instanceName}</td>
						<td class="view-field" name="pushTime" mapping="pushTime" dataType="date" format="yyyy-MM-dd hh:mm:ss"></td>
						<td>{expireTaskNum}</td>
						<td>{overExpireTaskNum}</td>
					</tr>
				</table>
				<div id="taskPushJobPager"></div>
			</div>
		</div>

	</div>
</body>
</html>