<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href='${ctx }/static/ezt/web/calendar/css/specialDateTemplate.css' rel='stylesheet' />
<title>Insert title here</title>
</head>
<body>
	 <div class="dialog2" id="updateimgDialog2" style="display: none;"> 
     <div class="xgtx-l">
	<%--   <form  id="form3" enctype="multipart/form-data" action="${ctx}/calendar/uploadSpecialDateTemplate"	class="form-horizontal" method="post"> 
			<p class="bdzp" style="margin-top:15px;">
			  	<img src="${pageContext.request.contextPath}/static/ezt/web/images/jbxx-tx.png" width="32" height="33" style="vertical-align:middle;" />本地照片
			  	<input type="file" id="file_input2" name="imgFile" onchange="uploadImg_2()" />
			</p>
			 <div style="display: none;width: 289px; height: 182px; top: -2px; left: -2px; z-index: 290;" id="viewImg2">
			 </div>
			<p class="xgtx-tu" style="margin-left: 0px">
			<span style='color:#f00;line-height:5px;float:left;'>支持JPG、PNG格式的图片!</span>
			</p>
	   </form> --%>
	   
     </div>
    <button id="saveimg1" class="btn-style-3" style="margin-left: 110px;">保存</button>
    <button class="btn-style-3" style="margin-left: 20px;" onclick="cancleMyImg()">取消</button>
    
     </div>
		 <form  id="form3" enctype="multipart/form-data" action="${ctx}/admin/specialDateTemplate/uploadSpecialDateTemplate"	class="form-horizontal" method="post"> 
		
		<div class="upload"  >
		    <div class="u_box" id="drop_area"  draggable="true" style="position:relative;background-image: url('${ctx}/static/images/moban.png')">
	            	  <!-- 将图片拖拽到此区域  -->
	            	 
	            	 <input id="uploadTemplate" class="inputFile" name="file"  style="opacity:0;z-index:1111;position:absolute; top: 0px; left: 0px; width: 640px; height: 140px;" data-role="magic-overlay" data-target="#pictureBtn" data-edit="insertImage" type="file" > </input>
	            	 <!-- <div style="position:absolute;top:0;left:0;font-size:30px;color:#999;">点击此处上传模板图片</div> -->
	    </div>
		</div>
		</form>
	<div class="pic">
	    <ul id="p_ul">
	    	<c:forEach items="${specialDateTemplateList}" var="attachment">
	    		<li>
	    		<!-- <div class="delephe2" onclick="deleteph(this,408)" ><img alt="" src="/static/ezt/web/personal_card/images/dele22.jpg"></div> -->
		    		<div class="delephe2" onclick="deleteph(this)" data-id="${attachment.attachmentId}"><input style="width: 25px; margin-left: -20px; opacity:0;" type="button" value="点击"/></div>
				    <a href="#"><img src="${attachment.picPath}" alt="��Ӱ" width="160" height="140"></img></a>
				</li>
	    	</c:forEach>
	    </ul>
	</div>
	<script src="${ctx}/static/ezt/web/js/layers/layer.min.js" type="text/javascript"></script>
	<script type="text/javascript">
		
		//uploadFile(); 
		/*  $("#uploadTemplate").change(function(){
			  $("#cardform").ajaxSubmit({
					url :"${ctx}/calendar/uploadSpecialDateTemplate",
					
					success : function(data,statusText){
						
					}
				});  
		  }); */
		
		 /*  $("#file_input2").change(function(){
			  alert($(this).val());
		  	  $("#viewImg2").val();
		  	  $("#viewImg2").css("display","");
		  }); */
		
		 $(function(){
			 $("#uploadTemplate").change(function(){
				var imageSize = $("#p_ul").find("li").length;
				if(imageSize>=4){
					layer.alert('图片上传不能超过4张', {
						closeBtn: 0
					}); 
					return false;
				}
				 $("#form3").ajaxSubmit({
						url :"${ctx}/admin/specialDateTemplate/uploadSpecialDateTemplate",
						dataType:"json",
						success : function(data,statusText){
							if(data.flag=='success'){
								var img = data.url;
								var str =
								"<li><div class='delephe2' onclick='deleteph(this)' data-id='{attachmentId}'><input style='width: 25px; margin-left: -20px; opacity:0;' type='button' value='点击'/></div>"+"<a href='#'>"+"<img src='"+img+"' alt='' width='160' height='140'>"+"</a>"+"</li>";
								var id =data.id;
								str = str.replace("{attachmentId}",id);
								 $("#p_ul").append(str);
								/* window.location.reload(); */
							}else{
								layer.alert("图片上传失败!");
							} 
						}
					});  
			 })
			/*  uploadFile();
			 $("#saveimg1").unbind("mousedown");
				$("#saveimg1").bind("mousedown",function(){
					 if(window.msg!=null){
						layer.alert("请选择正确的图片");
						$("#file_input").val('');
						$(".xgtx-tu").html("");
						$("#updateimgDialog2").hide();
						$("#mask").hide();
						$("#viewImg").hide();
						return false;
					}
					var imageSize = $("#p_ul").find("li").length;
					if(imageSize>=4){
						alert("图片上传不能超过4张");
						return false;
					}
					if($("#img").attr("src")!=null){
						$("#saveimg1").unbind("mousedown");
						 $("#form3").ajaxSubmit({
								url :"${ctx}/calendar/uploadSpecialDateTemplate",
								dataType:"json",
								success : function(data,statusText){
									if(data.flag=='success'){
										var img = data.url;
										var str =
										"<li><div class='delephe2' onclick='deleteph(this)' data-id='{attachmentId}'><input style='width: 25px; margin-left: -20px; opacity:0;' type='button' value='点击'/></div>"+"<a href='#'>"+"<img src='"+img+"' alt='' width='160' height='140'>"+"</a>"+"</li>";
										var id =data.id;
										str = str.replace("{attachmentId}",id);
										 $("#p_ul").append(str);
									
										window.location.reload();
									}else{
										layer.alert("图片上传失败!");
									} 
									$(".d-close").click();
								}
							});  
					}else{
						layer.alert("您还没有选择图片！");
					}
				}); */
			 
		 });
		  
		
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