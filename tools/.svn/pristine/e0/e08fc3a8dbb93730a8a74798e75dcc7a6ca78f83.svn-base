<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/ace.tld" prefix="ace"%>
<%@ taglib uri="/WEB-INF/tlds/util.tld" prefix="u"%>
<html decorator="null">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户<c:if test="${user.id == null}">添加</c:if><c:if test="${user.id != null}">编辑</c:if></title>
<style>
	.formCheck{
		vertical-align:middle;
		margin:-4px 2px 0px 2px;
	}
</style>
<script type="text/javascript" charset="UTF-8">
	var _au_adduser={};
	var deptCode = '${user.deptCode}';
	$(function() {
		//记录上次roleId，城市对象
		_au_adduser.__roleIdHis=null,_au_adduser.__orgInfo=[],_au_adduser.__citys=[];
		
		_au_adduser.getCitys = function(){
			$.ajax({
				type : "POST",
				url : "${pageContext.request.contextPath}/admin/city/getCity",
				dataType: "json",
				async: false,  //同步
				success : function(data) {
					//console.log(data);
					for(var i=0;i<data.length;i++){
						var item = data[i];
						if("86"!=item.cityCode){
							_au_adduser.__citys.push({
								"lv":item.lv,
								"code":item.cityCode,
								"name":item.shortName
							});
						}
					}
					//console.log(_au_adduser.__citys);
				}
			});
		};
		_au_adduser.getOrgInfo = function(){
			$.ajax({
				type : "GET",
				url : "${pageContext.request.contextPath}/admin/user/getOrgInfo",
				dataType: "json",
				async: false,  //同步
				success : function(data) {
					//console.log(JSON.stringify(data));
					var deptCodes = deptCode.split(",");
					$("#ulOrgInfo").empty();
					for(var i=0;i<data.length;i++){
						var item = data[i];
						/* _au_adduser.__orgInfo.push({
							"code":item.code,
							"name":item.name
						}); */
						var checked = false;
						 if(deptCodes!=null && deptCodes.length>0){
							 for(var j=0;j<deptCodes.length;j++){
								 if(deptCodes[j]==item.code){
									 checked = true;
									 break;
								 }
							 }
						}
						$("#divOrgInfo").append("<input type='checkbox' name='deptCodes' value='"+item.code+"' "+(checked?"checked":"")+" class='formCheck'/>"+item.name+"&nbsp;")
						
					}
					//console.log(_au_adduser.__citys);
				}
			});
		};
		_au_adduser.choseCityItemFn = function(obj){
			if(obj.type=="checkbox"){ //多选
				var _city_ck_val=[];
				$('.cityItem:checked').each(function(i,item){
					_city_ck_val.push($(this).val());
				})
				$('#g_city_gm').val(_city_ck_val.join(","));
			}
			if(obj.type=="radio"){ //单选
				$('#g_city_ah').val(obj.value);
			}
			
		};
		
		_au_adduser.getCitysAjax = function(){
			var roleCode = $('#roleId option:selected').data("code");
			var roleId = $('#roleId option:selected').val();
			var citys = "";
			if(roleId=='2'){
				citys = $('#g_city_gm').val();
			}else if(roleId=='3'){
				citys = $('#g_city_ah').val();
			}else{
				citys = $('#g_city_gm').val();
			}
			//console.log(_au_adduser.__citys);
			//console.log(citys);
			$('#cityspan').html('');
			if(roleId!=''){
				
				var _cityhtml="";
				if(roleCode=="CAO"){
					_cityhtml="<input type=\"text\" name=\"cityItem\" value=\"总公司\" disabled=\"disabled\"/>";
				}else if(roleCode=="AH"){
					_cityhtml="<ul>";
					for(var i=0;i<_au_adduser.__citys.length;i++){
						var item = _au_adduser.__citys[i];
						var _check = citys == item.code&&roleId=='${user.roleId}' ;
						_cityhtml += "<span><input type=\"radio\" class=\"cityItem\" name=\"\cityItem\" value=\""+item.code+"\" "+
							(_check?" checked=\"checked\"":"" )+
							" onclick=\"_au_adduser.choseCityItemFn(this);\" /><span>"+item.name+"</span></span>";
					}
					_cityhtml += "</ul>";
				}else{
					_cityhtml="<ul>";
					for(var i=0;i<_au_adduser.__citys.length;i++){
						var item = _au_adduser.__citys[i];
						var _check =(citys.indexOf(item.code)!=-1/*&&roleId=='${user.roleId}'*/) ;
						_cityhtml += "<span><input type=\"checkbox\" class=\"cityItem\" name=\"cityItem\" value=\""+item.code+"\" "+
							(_check?" checked=\"checked\"":"" )+
							" onclick=\"_au_adduser.choseCityItemFn(this);\" /><span>"+ item.name+"</span></span>";
					}
					_cityhtml += "</ul>";
				}
				$('#cityspan').html(_cityhtml);
				
			}
			
		};
		
		$.formValidator.initConfig({
			formID : "saveForm",
			onError : function(msg) {
				$.alert(msg);
			}
		});
		$("#account").formValidator().inputValidator({
			min : 1,
			onError : "用户代码不能为空,请确认!"
		}).functionValidator({
			fun : function() {
				return true;
			}
		});
		$("#plainPassword:visible").formValidator().inputValidator({
			min : 1,
			onError : "密码不能为空,请确认!"
		});

		$("#userName").formValidator().inputValidator({
			min : 1,
			onError : "用户姓名不能为空,请确认!"
		});
		
		/* $("#roleId").formValidator().inputValidator({
			min : 1,
			onError : "角色不能为空,请选择!"
		}); */
		
		/* $('#roleId').change(function(){
			if($('#roleId').val() != _au_adduser.__roleIdHis){
				//_au_adduser.getCitysAjax();
				__roleIdHis = $('#roleId').val();
			}
		}); */
		var options = {
			beforeSubmit:function(a,f,o){
				var org = [];
				if($("[name=cityItem]:checked").length>0){
					$("[name=cityItem]:checked").each(function(){
						org.push($(this).val());
					});
					if(org.length>1){
						org = org.join(",");
					}else{
						org=org[0];
					}
				}else{
					org="86";
				}
				//console.debug(org);
				a.push({name:"orgCode",value:org});
				//console.debug($('#roleId option:selected'));
				a.push({name:"isType",value:$('#roleId option:selected').attr("roleType")});
			} ,
			success:function(){
				$.msgbox({time: 2000,msg: "用户${user.id!= null?'编辑':'添加'}成功!",icon:"success"});
				$('#diaClose').click();
				$("#pager").pager().reload();
			}
		};
		$('#saveForm').ajaxForm(options);
		
		//初始化
		(function(){
			_au_adduser.getOrgInfo();
			//_au_adduser.getCitys();
			/* if('${user.roleId}'=='2'){
				$('#g_city_gm').val('${user.orgCode}');
			}else if('${user.roleId}'=='3'){
				$('#g_city_ah').val('${user.orgCode}');
			}else{
				$('#g_city_gm').val('${user.orgCode}');
			} */
			$('#roleId').val('${user.roleId}');
			/* if('${user.id}'!=''){ _au_adduser.getCitysAjax(); } */
		})();
		
	});
</script>
<style type="text/css">
#cityspan ul { border: 1px solid #e1e1e1;margin: 5px 0px;border-radius: 5px;width: 350px; }
#cityspan ul > span { margin: 0 5px 0 0; }
</style>
</head>
<body>
	<a id="diaClose" class="close" style="display: none;"></a>
	<form id="saveForm"
		action="${pageContext.request.contextPath}/admin/user/save"
		method="post">
		<input type="hidden" name="id" value="${user.id}" />
		<table style="width: 450px; line-height: 25px;">
			<tr>
				<td class="text_center bold" colspan="4">基本信息</td>
			</tr>
			<tr>
				<td class="text_right">用户代码：</td>
				<td class="text_left"
					<c:if test="${user.id!= null}">colspan="3"</c:if>><input
					type="text" id="account" name="account" class="text"
					value="${user.account}"
					<c:if test="${user.id!= null}">readonly="readonly"</c:if> />
				</td>
				<td class="text_right"
					<c:if test="${user.id!= null}">style="display: none"</c:if>>用户密码：</td>
				<td class="text_left"
					<c:if test="${user.id!= null}">style="display: none"</c:if>>
					<input type="password" id="plainPassword" name="plainPassword" class="text" <c:if test="${user.id!= null}">disabled="disabled"</c:if>/>
				</td>
			</tr>
			<tr>
				<td class="text_right">用户姓名：</td>
				<td class="text_left"><input type="text" id="userName"
					name="userName" class="text" value="${user.userName}" /></td>
				<td class="text_right">电话<span class="telTip" style="color:#f00;" title="填写微信绑定的手机号码">[?]</span>：
				</td>
				<td class="text_left">
				<input type="text" name="phone" class="text" value="${user.phone}" />
				</td>	
			</tr>
			<tr>
				<td class="text_right">电子邮箱：</td>
				<td class="text_left"><input type="text" class="text"
					name="email" value="${user.email}" /></td>
				<td class="text_right">是否启用：</td>
				<td class="text_left"><input type="radio" name="enabled"
					value="true" <c:if test="${user.enabled}">checked="checked"</c:if> />/
					<input type="radio" name="enabled" value="false"
					<c:if test="${!user.enabled}">checked="checked"</c:if> /> 启用/禁用</td>
			</tr>
			<tr>
				<td class="text_right">性别：</td>
				<td class="text_left"><select name="sex">
						<option value="0"
							<c:if test="${user.sex == '0'}" >selected="selected"</c:if>>男</option>
						<option value="1"
							<c:if test="${user.sex == '1'}" >selected="selected"</c:if>>女</option>
				</select></td>
				<td class="text_right">是否在职：</td>
				<td class="text_left"><input type="radio" name="accountstate"
					value="01" <c:if test="${user.accountstate=='01' or user.accountstate==null}">checked="checked"</c:if> />/
					<input type="radio" name="accountstate" value="03"
					<c:if test="${user.accountstate=='03'}">checked="checked"</c:if> /> 在职/离职</td>
			</tr>
			<tr>
				<td class="text_right">角色：</td>
				<td class="text_left" colspan="3">
					<select id="roleId" name="roleId">
						<option value="" data-code="">--请选择--</option>
						<c:forEach items="${searchRoles}" var="item">
						<option value="${item.id}" <c:if test="${item.id==user.roleId}">selected=selected</c:if> roleType="${item.type}" data-code="${item.code}">${item.name}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td class="text_right" valign="top">分公司：</td>
				<td class="text_left" colspan="3">
					<input type="hidden" id="g_city_gm">
					<input type="hidden" id="g_city_ah">
					<div id="cityspan" ></div>
				</td>
			</tr>
			<tr>
				<td class="text_right" valign="top">用户描述：</td>
				<td class="text_left" colspan="3"><textarea name="description"
						rows="" cols="" style="width: 348px; height: 50px;">${user.description}</textarea>
				</td>
			</tr>
			<tr>
				<td class="text_right" valign="top" style="width:66px">所属部门：</td>
				<td class="text_left" colspan="3">
					<div id="divOrgInfo">
					
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="4" class="text_center"><input type="submit"
					class="search_btn" value="保存" />
				</td>
			</tr>
		</table>
	</form>
	
	<div id="channelSelect" style="display: none;">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<c:forEach items="${clist}" var="item" varStatus="stauts" begin="1">
				<c:if test="${stauts.index%4=='1' }">
					<tr>
				</c:if>
					<td><input name="channelItem" type="checkbox" 
					<c:forEach items="${channels }" var="channel">
						<c:if test="${channel==item.agentCom }">
							checked="checked"
						</c:if>
					</c:forEach>
					value="${item.agentCom }">${item.name }</input></td>
				<c:if test="${stauts.index%4=='0' }">
					</tr>
				</c:if>
			</c:forEach>
		</table>
	</div>
</body>
</html>