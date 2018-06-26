<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/util.tld" prefix="u"%>
<html>
<head>
<title>日志统计列表</title>
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
$(function(){
	$("#logPager").pager(request.getContextPath()+'/admin/logqry/logQuery',10,$('#logView').view().on('add',function(temp){
	}));
	
	$("#seachLog").click(function(){
		$("#logPager").pager().options.postData.beginDate=$("#beginDate").val();
		$("#logPager").pager().options.postData.endDate=$("#endDate").val();
		$('#seachLogFrom').submit();
	});
	$("#seachLogFrom").ajaxForm(function(data){
		$('#logPager').pager().options.postData={};
		$('#logPager').pager().setPostData(Ace.parseQuery($('#seachLogFrom').serialize()));
		$('#logPager').pager().setJSON(data);
	});
	$("#exportLog").click(function(){
		window.location.href = "${ctx }/admin/logqry/logQueryReport?"+$('#seachLogFrom').serialize();
	});
});
</script>
</head>
<body>
	<div class="tagContentList">
		<div class="business_title">日志统计列表</div>
		
		<div class="col_lg_04" style="width:1203px">
		<form id="seachLogFrom" action="${pageContext.request.contextPath }/admin/logqry/logQuery" method="post">
			<div style="margin-top: 20px">
				<span style="margin-left: 270px;">内外勤：</span><select name="isType" id="isType" class="sousuoInput sousuoInputWidth2" style="width: 60px;height: 30px;">
										<option value=''>全部</option>
										<option value='0'>内勤</option>
										<option value='1'>外勤</option>
									</select>
				<span style="margin-left: 30px;">开始时间：</span><input type="text" class="sousuoInput sousuoInputWidth2" style="margin-left:0;" name="beginDate" id="beginDate" date="{dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}'}"/>
				<span style="margin-left: 30px;">结束时间：</span><input type="text" class="sousuoInput sousuoInputWidth2" style="margin-left:0;" name="endDate" id="endDate" date="{dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'beginDate\')}'}"/>
				<input id="seachLog" style="margin-left: 60px;width: 60px;height: 30px;background-color: #0076c0;border: none;color: #fff;" type="button" value="查询">
				<input id="exportLog" style="margin-left: 30px;width: 60px;height: 30px;background-color: #0076c0;border: none;color: #fff;" type="button" value="导出">
			</div>
		</form>
			<div class="business_search_list_warp" style="width:95%">
				<table cellspacing="0" cellpadding="0" class="t-list table" id="logView">
					<tr>
						<th>机构(城市)</th>
						<th width="180px">渠道</th>
						<th>客户经理代码</th>
						<th>客户经理名称</th>
						<th>登录次数</th>
						<th>最近登录时间</th>
						<th>最近系统数据更新时间</th>
						<th>网点数</th>
						<th>理财经理数</th>
					</tr>
					<tr class="template" name="default">
						<td>{orgName}</td>
						<td>{channel}</td>
						<td>{loginName}</td>
						<td>{userName}</td>
						<td>{loginCount}</td>
						
						<td class="view-field" name="lastLoginTime" mapping="lastLoginTime" dataType="date" format="yyyy-MM-dd hh:mm:ss"></td>
						<td class="view-field" name="lastUpdateTime" mapping="lastUpdateTime" dataType="date" format="yyyy-MM-dd hh:mm:ss"></td>
						<td>{compCount}</td>
						<td>{lacomtoagentCount}</td>
					</tr>
				</table>
				<div id="logPager"></div>
			</div>
		</div>
		
	</div>
</body>
</html>