<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/util.tld" prefix="u" %>
<html decorator="null">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>URL资源选择框</title>
<script type="text/javascript">

	$('#checkView').view().on('remove',function(data){
		$('.check[name=id][value='+data.id+']').val(false);
	});

	$('.checkAll').click(function(){
		if(this.checked){
			$('#boxResourceView').view().getData().each(function(){
				var elem = $('#checkView').view().find('id',this.id);
				if(!elem)$('#checkView').view().add(this);
			});
		}else{
			$('#boxResourceView').view().getData().each(function(){
				var elem = $('#checkView').view().find('id',this.id);
				if(elem)$('#checkView').view().remove(elem.getIndex());
			});
		}
	});
    
	$('#boxPager').pager(request.getContextPath()+'/admin/sec/resource/urlbox/list',1,$('#boxResourceView').view().on('add',function(data){
		var vs = $('.hide[name=id]').val();
		vs = Ace.isArray(vs)?vs:[vs];
		this.target.find('.check').attr('checked',(vs.indexOf(data.id) > -1));
		this.target.find('.check').click(function(){
			if(this.checked){
				$('#checkView').view().add(data);
			}else{
				var elem = $('#checkView').view().find('id',data.id);
				if(elem){
					$('#checkView').view().remove(elem.getIndex());
				}
			}
		});
	})).on('change',function(){
		if($($('.checkAll').attr('expr')).val() && $($('.checkAll').attr('expr')).val().length == $($('.checkAll').attr('expr')).length){
			$('.checkAll').attr("checked", 'true');
		}else{
			$('.checkAll').removeAttr("checked");
		}
	}).setJSON({});

	$('#boxResourceAjaxForm').ajaxForm(function(data){
		$('#boxPager').pager().setPostData(Ace.parseQuery($('#boxResourceAjaxForm').serialize()));
		$('#boxPager').pager().setJSON(data);
	});
	
	var treeNode = $('#saveResourceForm').data('node');

	var exists = function(id){
		return treeNode.children && treeNode.children.each(function(){
			if(this.id == id)
				return true;
		});
	};
	
	$('#saveUrls').click(function(){
		var ids = [];
		var datas = $('#checkView').view().getData();
		var newNodes = [];
		datas.each(function(){
			if(!exists(this.id)){
				this.isParent = false;
				this.edit = false;
				this.add = false;
				newNodes.push(this);
				ids.push(this.id);
			}
		});
		if(ids.length == 0){
			$('#saveResourceForm').data('dialog').close();
			return;
		}
		$.getJSON(request.getContextPath()+'/admin/sec/resource/saveUrls',{ids:ids,groupId:'${param.id}'},function(){
			$('#saveResourceForm').data('newNodes',newNodes);
			$('#resourceTree').zTree().addNodes(treeNode,newNodes);
			$.msgbox({time: 2000,msg: "URL资源添加成功!",icon:"success"});
			$('#saveResourceForm').data('dialog').close();
		});
	});
	
</script>
</head>
<body>
<div style="width:700px;">
<div class="business_search">
<div class="business_search_left">
<form id="boxResourceAjaxForm" action="${pageContext.request.contextPath}/admin/sec/resource/urlbox/list" method="post">
<input type="hidden" name="search_ISNULL_pid" value="null">
<table cellspacing="0" cellpadding="0" style="border: 0px;" class="tab_serach">
	<tbody>
		<tr>
			<td style="text-align: right">资源名称：</td>
			<td colspan="3" ><input type="text" name="search_LIKE_name" class="text"></td>

		</tr>
		<tr>
			<td style="text-align: right">链接地址：</td>
			<td style="text-align: left;" colspan="3">
				<input style="width:300px;" type="text" name="search_LIKE_resString" class="text">
			</td>
			<td colspan="2" style="text-align: center"><input type="submit" value="查&#12288;询" class="button_bg2"></td>
		</tr>
	</tbody>
</table>
</form>
</div>
</div>

<div class="dsb_sele_table" style="float: left;">
<table id="boxResourceView" cellspacing="0" cellpadding="0" class="t-list table"">
	<tbody>
		<tr>
			<th style="background-color: #666; height: 2px" colspan="7"></th>
		</tr>
		<tr class="tb_tr_header">
			<th width="20px"><input class="checkAll" type="checkbox" expr=".check[name=id]"/></th>
			<th>序号</th>
			<th>资源名称</th>
			<th>资源值</th>
		</tr>
		<tr class="tb_tr_content template" name="default">
			<td><input class="check" name="id" index="{index}" type="checkbox" value="{id}"/></td>
			<td>{index:seq()}</td>
			<td><div title="名称:{name}<br/>描述:{description}">{name:ellipsis(28,'...')}</div></td>
			<td>{resString}</td>
		</tr>
	</tbody>
</table>
<div id="boxPager"></div>
</div>
<div class="dsb_sele_div" style="margin-left: 20px;">
    <dl>
		<dt>您已选选择了：</dt>
		<dd id="checkView" style="height:377px;">
			<a class="template" name="default" href="javascript:void(-1);">
				<div title="{name}" class="remove sel_force2">
				<input class="hide" type="hidden" name="id" value="{id}"/>
				{name:ellipsis(14,'...')}
				</div>
			</a>
		</dd>
	</dl>
</div>
<div style="width:100%; float:left; height:20px; margin-top:20px">
<input id="saveUrls" type="button" value="提 交" style="margin-right:40px" class="upload_btn">
</div>
</div>
</body>
</html>