<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/ace.tld" prefix="ace"%>
<html decorator="null">
<head>
<title>附件上传</title>
<script type="text/javascript">
var file_upload_limit;
		uploadFile = function(treeNode) {
			var proFlag= "${param.proFlag}";
			var sessionId = getCookieValueByName('JSESSIONID=',11);
			var fileType = '';
			file_upload_limit= 5;
			if (proFlag == 'product') {
				fileType = "*.pdf";
				file_upload_limit = 1;
			}
			$("#fileuploadDiv").html('<span id="spanButtonPlaceHolder"></span>');
			var file_size_limit = 2*1024;
			$('#upload').data("swfupload",false);
			var flag = "${flag}";
			if (flag == 'admin') {
				fileType = "*.apk;*.ipa;*.plist";
				$("#mess").text("注:单个文件大小不得超过50M");
				file_size_limit = 50 * 1024;
				$('#upload').swfupload({uploadUrl:"${pageContext.request.contextPath}/fileManager/toIosAndAndroidUploadFile;jsessionid="+sessionId+"",file_upload_limit:file_upload_limit,file_size_limit:file_size_limit,post_params:{functionType:"${functionType}",functionId:'${functionId}',flag:'${flag}'},fileTypes:fileType});
			} else {
				$('#upload').swfupload({uploadUrl:"${pageContext.request.contextPath}/fileManager/toUploadFile;jsessionid="+sessionId+"",file_upload_limit:file_upload_limit,file_size_limit:file_size_limit,post_params:{functionType:"${functionType}",functionId:'${functionId}'},fileTypes:fileType});
			}
			$(".swfupload").show();
		
		};
		$(function(){
			$('#upload_submit').click(function(){
				$('#upload').swfupload().startUpload();
			});
			
			uploadFile();
			$(".close").click(function(){
				getHTML();
			});
		});
		function getHTML(){
			var jsonData = $('#fsUploadProgress').view().getData();
				jsonData.each(function(){
					var divId= "${param.divId}";
					if(divId.length>0){
						$("#"+divId).view().add(this);
					}else{
						$('#updateFileList').view().add(this);
					}
				});

		}
		function getCookieValueByName(name,nameLength){
			var allcookies = document.cookie;
			var pos = allcookies.indexOf(name);
			if (pos != -1) {
				var start = pos + nameLength;
				var end = allcookies.indexOf(";", start); 
				if (end == -1) {
					end = allcookies.length;
				}
				var value = allcookies.substring(start, end); 
				return value;
			}
			return '';
		}
		
		function deleteFile(attachId){
			if(attachId==""){
				return;
			}else{
				$.post(window.contextPath+"/fileManager/delAttachFile",{attachId:attachId},function(data){
					$('#upload').swfupload().setFileUploadLimit(++file_upload_limit);
				});
			}
		}
</script>
</head>
<body>
	<div style="width:600px">
		<input type="file" style="display: none;" id="upload" name=fileName value="上传文件" />
		<span id="mess" style="color:red;font-size:14px">注:单个文件大小不得超过2M</span>
		 <div class="fieldset flash"  id="fsUploadProgress" style="height:120px; overflow:scroll;">
					<div class="progressWrapper template" name="default">
						<input name="fileName" type="hidden" value="{fileName}" />
						<input name="attachId" type="hidden" value="{attachId}" />
						<div class="progressContainer blue">
							<a class="progressCancel remove" href="javascript:void(-1);" onclick="deleteFile('{attachId}');" style="visibility: visible;"></a>
							<div class="progressName">
								<a href="javascript:void(-1);" class="download">{oldFileName}{fileType}</a>
							</div>
							<div class="" style="display: none; font-size：12px;">
								<img src='${pageContext.request.contextPath}/static/images/loading_16.gif' />
							</div>
						</div>
					</div>
				</div>
				<div id="fileuploadDiv">
				</div>
		<div >
			<button id="upload_submit" type="button" class="new_jihui_menu1" style="width:90px;height:30px;margin-left:38%;color:white">上传</button>
			<button id="upload_submit_but" type="button" class="new_jihui_menu3 close" style="width:90px;height:30px;color:white" onclick="javascript:/*artDialog*/;">确定</button>
		</div>
	</div>
</body>
</html>