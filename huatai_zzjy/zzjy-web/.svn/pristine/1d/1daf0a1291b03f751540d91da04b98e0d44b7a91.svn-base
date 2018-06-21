<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/util.tld" prefix="u"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>模板管理</title>
<!-- Bootstrap --> 
<link href="${ctx}/static/ezt/web/css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${ctx}/static/ezt/web/css/common.css"> 
<%--  <link rel="stylesheet" type="text/css" href="${ctx}/static/ezt/web/plan/css/plan.css">
<script src="${ctx}/static/ezt/web/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script> --%>
<script src="${ctx}/static/ezt/web/js/layers/layer.min.js" type="text/javascript"></script>
<link href='${ctx }/static/ezt/web/admin/specialDateTemplate/css/specialDateTemplate.css' rel='stylesheet' />
 
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="${ctx}/static/ezt/web/js/html5shiv.min.js"></script>
      <script src="${ctx}/static/ezt/web/js/respond.min.js"></script>
    <![endif]-->
<style type="text/css">
	.topHeadeRight{width:1048px;}
	div.topNav {padding-bottom: 59px;}	
	div.navigation {width: 1048px;}
	  *{
	    margin:0px;
	    padding:0px;
	  }
	  img{border:0;}
	  .upload{width:800px;height:150px;margin:50px auto;}
	  .upload .u_logo{
	    text-align:center;
	  }
	  .upload .u_box{
	    width:640px;height:130px;border:2px dashed #E6E6E6;margin:0 auto;font-size:36px;color:#D1D1D1;text-align:center;line-height:130px;padding:5px
	  }

	  .pic{
	    width:840px;height:300px;margin:0 auto;
	  } 
	  
	  .pic ul li{ border:1px solid #eaeaea;color:#fff;background:#fff;list-style:none;float:left;margin-left:15px;padding:50px 10px 20px 15px;}
	  .pic ul li a:hover img{ -webkit-transform:scale(1.2)}
	  
	  
	/*  .delephe{display:block; cursor:pointer; width:30px; height:30px; background: url(${ctx}/static/ezt/web/personal_card/images/dele22.jpg) no-repeat; position: relative; margin-left: 140px;margin-top: -10px; z-index: 999;opacity:0;}
 */	 .d-buttons{
	 display:none;
	 }
	 .dialog2{
	 width: 360px;
    height: 420px;
    background: #fff;
    border-radius: 5px;
    border: 1px solid #ddd;
    margin: auto;
	 }
	 
	 .delephe2 {
    cursor: pointer;
    width: 25px;
    height: 25px;
    background: url(/static/ezt/web/personal_card/images/dele22.jpg) no-repeat;
    position: relative;
    margin-left: 140px;
    margin-top: -40px;
    margin-bottom:10px;
    z-index: 9999;
}
</style>
</head>
<body>
            <div class="panel">
                <div class="panel-body">
                    <form id="form3" class="form-horizontal clearfix" enctype="multipart/form-data" action="${ctx}/admin/uploadSpecialDateTemplate"	method="post">
                        <div class=" col-sm-6">
                            <div class="form-group">
                                <label class="col-sm-3 control-label"><span style="color: red">*</span>特殊日类型:</label>
                                <div class="col-sm-6" id="specialDateWishType">
                                <c:if test="${flag!=0}">
                            	<select class="form-control"  id="type" name="type">
                            		<c:forEach items="${specialDateWishList}" var="specialDateWish">
                            			<option id="${specialDateWish.type }" value="${specialDateWish.type}">${specialDateWish.specialDateName}</option>
                            		</c:forEach>
                            	</select>
                            	</c:if>
                            	</div>
                            </div>
                        
                            <div class="form-group">
                                <label  class="control-label col-sm-3">默认祝福语:</label>
                                <div class=" col-sm-9" >
                                    <textarea class="form-control" id="wishContent" name="wishContent" style="min-height:120px;">${specialDateWish.wishContent}</textarea>
                                </div>
                            </div>
                            
                            <div class="form-group">
                                 <label  class="control-label col-sm-3">上传模板:</label>
                           </div>
                            <div class="pic">
							    <ul id="p_ul">
								    <li  id="default" style="display:none; position:relative ; top: 0px; left: 0px;background:url('/static/ezt/web/personal_card/images/add-pic.jpg') no-repeat center #e0e0e0;height:187px; ">
								    	<input id="uploadTemplate" class="inputFile" name="file"  style="opacity:0;z-index:1111; width: 140px; height: 120px; data-role="magic-overlay" data-target="#pictureBtn" data-edit="insertImage" type="file" > </input>
								    </li>
							    </ul>
							</div>
                        </div>
                         <c:if test="${flag==0}"><input type="hidden" id="type" name="type" value="${type}"></c:if>
                    </form>
                    <c:if test="${flag==0}">
                    	<div class=" text-center ">
	                        <button type="button" id="saveSpecialDateWish" class="btn btn-info" style="margin-top: 20px">保存</button>
	                    	<button type="button" id="backSpecialDateWish" class="btn btn-info" style="margin-top: 20px">返回</button>
	                    </div>
	                 </c:if>
	                  <c:if test="${flag!=0}">
                    	<div class=" text-center " style="">
                    		<button type="button" id="addSpecialDateWish" class="btn btn-info" >新增</button>
	                    	<button type="button" id="updateSpecialDateWish" class="btn btn-info" >更新</button>
	                    	<button type="button" id="deleteSpecialDateWish" class="btn btn-info" >删除</button>
	                    </div>
	                 </c:if>
                </div>
            </div>
	

<script type="text/javascript">
$(function(){
	var flag = '${flag}';
	 if(flag!=''&&flag==0){
		 $("#specialDateWishType").append("<input type='text' id='types'  class='form-control' />");
		 $("#default").css("display","");
	 }else{
		 toSpecialDateWishDetail();
	 }
	 var imageSize = '${templateListSize}';
	 if(imageSize<4){
		 $("#default").css("display","");
	 }
	 $("#uploadTemplate").change(function(){
		 if($("#types").val()==''){
			 layer.alert("请输入类型");
			 return false;
		 }else{
			 $("#types").attr("readonly",true);
		 }
		 $("#form3").ajaxSubmit({
				url :"${ctx}/admin/specialDateTemplate/uploadSpecialDateTemplate",
				dataType:"json",
				success : function(data,statusText){
					if(data.flag=='success'){
						var img = data.url;
						var str =
						"<li><div class='delephe2' onclick='deleteph(this)' data-id='{attachmentId}'><input style='width: 25px; margin-left: -20px; opacity:0;' type='button' value='点击'/></div>"+"<a href='#'>"+"<img src='"+img+"' alt='' width='140' height='120'>"+"</a>"+"</li>";
						var id =data.id;
						str = str.replace("{attachmentId}",id);
						 $("#p_ul").append(str);
						 var imageSize = $("#p_ul").find("li").length;
							if(imageSize>=5){
								$("#default").css("display","none");
							}
					}else{
						layer.alert("图片上传失败!");
					} 
				}
			});  
	 })
	 
	 
	 $("#saveSpecialDateWish").click(function(){
		 if($("#types").val()==''){
			 layer.alert("类型不能为空");
			 return false;
		 }
		 var wishContent = $("#wishContent").val();
		 if(wishContent.length>800){
			 layer.alert("祝福语字数不能超过800字符");
		 }
		 var params = {
			specialDateName:$("#types").val(),
			wishContent:$("#wishContent").val(),
			type:'${type}'
		 }
		 $.ajax({
				type : "POST",
				url : "${ctx}/admin/specialDateTemplate/saveSpecialDateWish",
				data : params,
				dataType : "json",
				success : function(data) {
					layer.alert("保存成功");
					window.location.href ="${ctx}/admin/specialDateTemplate/toSpecialDateManage";
				}
			})
	 })
	 
	 $("#addSpecialDateWish").click(function(){
		 window.location.href ="${ctx}/admin/specialDateTemplate/toSpecialDateManage?flag=0";
	 })
	 
	 $("#backSpecialDateWish").click(function(){
		 window.location.href ="${ctx}/admin/specialDateTemplate/toSpecialDateManage";
		  $("#p_ul").find("li").each(function(){
			 var id =$(this).find(".delephe2").attr("data-id");
			 if(id!=''&&id!=undefined){
				 $.ajax({
						type : "POST",
						url : "${ctx}/admin/specialDateTemplate/deleteSpecialDateTemplate",
						data : {attachmentId: id},
						dataType : "json",
						success : function(data) {
							if(data.result=='success'){
								$("#default").siblings().remove();
								$("#default").css("display","");
								window.location.href ="${ctx}/admin/specialDateTemplate/toSpecialDateManage";
							}
						}
					})  
			 }
		 })
	 })
	 
	 $("#updateSpecialDateWish").click(function(){
		 var type = $("#type").val();
		 var wishContent = $("#wishContent").val();
		 if(wishContent.length>800){
			 layer.alert("祝福语字数不能超过800字符");
		 }
		 $.ajax({
				type : "POST",
				url : "${ctx}/admin/specialDateTemplate/updateSpecialDateWish",
				data : {type: type,wishContent:wishContent},
				dataType : "json",
				success : function(data) {
					if(data.msg=="success"){
						layer.alert("更新成功");
					}else{
						layer.alert("更新失败");
					}
				}
		})
	 })
	 
	 $("#deleteSpecialDateWish").click(function(){
		 var type = $("#type").val();
		 if(type=='0'||type=='1'){
			 layer.alert("类型不可删除");
			 return false;
		 }
		 $.ajax({
				type : "POST",
				url : "${ctx}/admin/specialDateTemplate/deleteSpecialDateWish",
				data : {type: type},
				dataType : "json",
				success : function(data) {
					if(data.msg=="success"){
						layer.alert("删除成功");
						window.location.href ="${ctx}/admin/specialDateTemplate/toSpecialDateManage";
					}else{
						layer.alert("删除失败");
					}
				}
		})
	 })
	 
	 $("#type").change(function(){
		 toSpecialDateWishDetail();
	 })
	 
});
 
function toSpecialDateWishDetail(){
	var type = $("#type").val();
	$("#default").siblings().remove();
	$.ajax({
		type : "POST",
		url : "${ctx}/admin/specialDateTemplate/toSpecialDateWishDetail",
		data : {type:type},
		dataType : "json",
		success : function(data) {
			console.log(data);
			var wishCotent = data.wishCotent||'';
			$("#wishContent").val(wishCotent);
			var imagesList = data.specialDateTemplateList;
			for(var i=0;i<imagesList.length;i++){
				var img = imagesList[i].picPath;
				var str =
				"<li><div class='delephe2' onclick='deleteph(this)' data-id='{attachmentId}'><input style='width: 25px; margin-left: -20px; opacity:0;' type='button' value='点击'/></div>"+"<a href='#'>"+"<img src='"+img+"' alt='' width='140' height='120'>"+"</a>"+"</li>";
				var id =imagesList[i].attachmentId;
				str = str.replace("{attachmentId}",id);
				 $("#p_ul").append(str);
			}
			 var imageSize = $("#p_ul").find("li").length;
				if(imageSize>=5){
					$("#default").css("display","none");
				}else{
					$("#default").css("display","");
				}
		}
	})
}

function deleteph(content){
	var id =$(content).attr("data-id");
	layer.confirm('确定要删除图片吗?', {
		  btn: ['确定','取消'], 
		  icon: 1, 
		},  function(e){
			$.ajax({
				type : "POST",
				url : "${ctx}/admin/specialDateTemplate/deleteSpecialDateTemplate",
				data : {attachmentId: id},
				dataType : "json",
				success : function(data) {
					layer.close(e);
					if(data.result=='success'){
						$(content).parent().remove();
						$("#default").css("display","");
					}else if(data.result=='fail'){
						layer.alert("图片删除失败");
					}
				}
			})
		}, function(e){
			layer.close(e);
		});
}
</script>
</body>
</html>
