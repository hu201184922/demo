<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=10">
<title>大都会E展通系统-<sitemesh:write property="title" default="模块名" /></title>
<link href="${pageContext.request.contextPath}/static/ezt/web/css/bootstrap.css" type="text/css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/static/ezt/web/css/common.css" type="text/css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/static/ezt/web/css/index.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/static/ezt/web/js/jquery-1.9.1.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/ezt/web/js/bootstrap.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/ezt/web/js/html5shiv.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/ezt/web/js/respond.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/ezt/web/js/jquery.Jcrop/jquery.Jcrop.js"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/ezt/web/css/jquery.Jcrop.css"></link> 
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common.js" charset="utf-8"></script> 

<!-- Include all compiled plugins (below), or include individual files as needed --> 
<sitemesh:write property="head" />
<style type="text/css">
	 .d-buttons{
	 display:none;
	 }
	 .backcolor a:hover{
	 	background-color:#2193db !important;
	 }
	</style>
</head>

<body>
<div class="container" style="width:1200px;">
    <aside class="aside" style="WIDTH: 220px; BACKGROUND: #0076C0; OVERFLOW: hidden">
        <div class="bg-color">
        </div>
        <div class="navbar-brand-logo" style="padding-top:38px;">
            <img src="${pageContext.request.contextPath}/static/ezt/web/images/logo.png">
        </div>
        <div class="man-info">
            <div class="postcard updImg" style= "cursor: pointer;">
               <c:choose>
                 <c:when test="${picPath !=null}"><img src="${pageContext.request.contextPath}${picPath}_70.jpg" id="head"></c:when>
                 <c:otherwise><img src="${pageContext.request.contextPath}/static/ezt/web/images/defualtHead.png" id="head"></c:otherwise>
               </c:choose>
            </div>
            <h4>${userName}</h4>
            <p>AGENCY-${gradeName}<c:if test="${orgShortName != null}">（${orgShortName}）</c:if></p>
        </div>
        <ul class="nav">
            <li class="active">
                <a href="javascript:void(0);" style="font-weight:bold;"><span class="icons01" aria-hidden="true"></span>新建快捷</a>
            </li>
            <c:if test="${currentUser.isType=='02' }">
	            <li class="backcolor">
	                <a href="${pageContext.request.contextPath}/customer/input-your-clients"  >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class="fastcreate icons02" id="fastcreate1 " aria-hidden="true" ></span>潜在客户</a>
	            </li>            
	            <li class="backcolor">
	                <a href="${pageContext.request.contextPath}/customer/plan/edit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="fastcreate icons04 " aria-hidden="true"></span>客户计划</a>
	            </li>
            </c:if>
            <shiro:hasPermission name="plan.personplan.addPager">
            <li class="backcolor">
                <a href="${pageContext.request.contextPath}/plan/personplan/addPager">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="fastcreate icons05 " aria-hidden="true"></span>个人计划</a>
            </li>
            </shiro:hasPermission>
            <!-- <li>
                <a href="javascript:void(0);"><span class="fastcreate icons06" aria-hidden="true"></span>目标设置</a>
            </li> -->
            <c:if test="${currentUser.isType=='02' }">
	            <li class="active " >
	                <a href="javascript:void(0);" style="font-weight:bold;"><span class="icons07" aria-hidden="true"></span>个人中心</a>
	            </li>
	            <li class="backcolor">
	                <a href="${pageContext.request.contextPath}/person/pascard/index">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="fastcreate icons08 " aria-hidden="true"></span>个人名片</a>
	            </li>
            </c:if>
            <shiro:hasPermission name="approver.index">
	            <li class="backcolor">
	                <a href="${pageContext.request.contextPath}/approver/index">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="fastcreate icons08 " aria-hidden="true"></span>个人名片审核</a>
	            </li>
            </shiro:hasPermission>
        </ul>
    </aside>
    <main class="main">
        <div class="main-top">
            <span>${today }</span>
            <ul class="nav navbar-nav pull-right">
            	<li>
                    <a href="${pageContext.request.contextPath}/sign/index">
                    	<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                    	<c:if test="${isSign == 0 }"><span class="badge bg-danger" style="font-size:10px;">签</span></c:if>
                    </a>
                    <ul class="nav">
                        <li>
                            <c:choose>
                              <c:when test="${isSign == 0 }"><a href="${pageContext.request.contextPath}/sign/index">点击签到!</a></c:when>
                              <c:otherwise><a href="${pageContext.request.contextPath}/sign/index">查看签到!</a></c:otherwise>
                            </c:choose>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="javascript:void(0);" id="planAlert">
                    	<span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
                    	<span class="badge bg-danger">${allAlertNum}</span>
                    </a>
                    <ul class="nav">
                        <li>
                            <a href="${pageContext.request.contextPath}/remind/index">您有${planAlertNum}条计划提醒!</a>
                        </li>
                       
                        <li id="isShowPolicyAlert" <c:if test="${currentUser.isType=='01' }">style="display:none"</c:if>>
                            <a href="${pageContext.request.contextPath}/remind/index?type=2">您有${policyAlertNum}条保单周年提醒!</a>
                        </li>
              
                        <li <c:if test="${currentUser.isType=='01' }">style="display:none"</c:if>>
                            <a href="${pageContext.request.contextPath}/remind/index?type=3">您有${planTrackAlertNum}条销售轨迹提醒!</a>
                        </li>
                        <li <c:if test="${currentUser.isType=='01' }">style="display:none"</c:if>>
                            <a href="${pageContext.request.contextPath}/remind/index?type=4">您有${specialDayAlertNum}条纪念日提醒!</a>
                        </li>
                    </ul>
                </li>
                <%-- <li>
                    <a href="javascript:void(0);">
                        <span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span>
                        <span class="badge bg-danger">${specialDayAlertNum}</span>
                    </a>
                    <ul class="nav" style="width:138px;">
                        <li>
                            <a href="${pageContext.request.contextPath}/remind/index?type=4">您有${specialDayAlertNum}条纪念日提醒!</a>
                        </li>
                    </ul>
                </li> --%>
                <li>
                    <a href="${pageContext.request.contextPath}/logout" class="text-info">退出系统</a>
                </li>
            </ul>
        </div>
        <div class="main-nav">
            <ul class="nav navbar-nav" id="navBar">
                <c:forEach items="${menuItems}" var="menu" varStatus="idx">
                	<shiro:hasPermission name="${menu.permString}">
					 	<li name="/${menu.url}"><a href="${pageContext.request.contextPath}/${menu.url}">${menu.name}</a></li> 
					</shiro:hasPermission>
				</c:forEach>
                <!-- <li class="active"><a href="#">首页</a></li>-->
            </ul>
        </div>
        <div class="main-content">
          <sitemesh:write property="body" />
        </div>
    </main>
</div>
<style type="text/css">  
/*修改头像弹出层样式*/
.d-content{padding:10px 10px !important;}
.xgtx-tk{ width:560px; height:420px; background:#fff; border-radius:5px; border:1px solid #ddd; margin:auto; }
.xgtx-l{ width:330px; float:left; padding-left:30px; height:420px;font-size:18px; line-height:65px; }

.bdzp{ width:136px; height:42px; line-height:42px; border:1px solid #ddd; text-align:center; font-size:16px; border-radius:5px; position:relative;}
.bdzp input{ width:136px; height:42px; line-height:42px; border:1px solid #ddd; position:absolute; top:0; left:0;opacity:0;filter:alpha(opacity=0);}

.xgtx-tu{ display:block;  width:300px; height:260px;margin-top:20px; margin-left:50px; }
.xgtx-r{ width:195px; float:right;font-size:18px; line-height:65px; border-left:1px solid #ddd;  } 
.yl-tu { margin:auto; display:block; text-align:center;}
.yl-wz{ font-size:12px; text-align:center; line-height:25px; color:#333;} 

.btn-style-3{ background:none; border:none; padding:0 15px; *padding:0 7px; text-align:center; display:block; float:left; height:30px; color:#fff; line-height:30px; background:#f15555; border:1px solid #f15555; border-radius:2px; font-size:14px; cursor:pointer;}
</style>  
<div class="xgtx-tk" id="updateimgDialog" style="display: none;"> 
  <div class="xgtx-l">
	  <form id="form2" action="${pageContext.request.contextPath}/upImage" enctype="multipart/form-data" 	class="form-horizontal" method="post"> 
			<p class="bdzp" style="margin-top:15px;">
			  	<img src="${pageContext.request.contextPath}/static/ezt/web/images/jbxx-tx.png" width="32" height="33" style="vertical-align:middle;" />本地照片
			  	<input type="file" id="file_input" name="imgFile"  onchange="uploadImg_1()"/>
			</p>
			<input type="hidden" id="x" name="x" /> 
			<input type="hidden" id="y" name="y" /> 
			<input type="hidden" id="w" name="w" />
			<input type="hidden" id="h" name="h" />
			<input type="hidden" id="wt" name="wt"/>
			<input type="hidden" id="ht" name="ht"/>
	  
			<p class="xgtx-tu" style="margin-left: 0px">
			  		<span style='color:#f00;line-height:5px;float:left;'>支持JPG、PNG格式的图片!</span>
			</p>
		<input type="hidden" name="ecardhisId" id="pecardhisId" value="${eCardHisInfo.ecardhisId }"/>		
			
	   </form>
  </div>
  <div class="xgtx-r" style="display: none;" id="viewImg">
      <h2 style="text-indent:30px;">预览</h2>
      <div class="yl-tu" id="preview1"><div style="overflow: hidden;width:68px;height:68px;margin-left: 60px;"><img class="jcrop-preview"  id="img60" src="" /></div> <p class="yl-wz">70X70</p></div>
 
      <div class="yl-tu" id="preview2"><div style="overflow: hidden;width:108px;height:108px;margin-left: 40px;"><img class="jcrop-preview" id="img100" src="" /></div><p class="yl-wz">100X100</p></div>

      <p class="yl-wz"><img src="${pageContext.request.contextPath}/static/images/wdzh/jbxx-tctu_14.jpg" width="15" height="16" style="vertical-align:middle;" /> 我的高清头像</p>
      <button id="saveimg" class="btn-style-3" style="margin-left: 40px;">保存</button>
      <button class="btn-style-3" style="margin-left: 20px;" onclick="cancleImg()">取消</button>
  </div>
</div>
<input type="hidden" id="uri" value="${uri }"/>
<script>

$(function(){
	var uri = $("#uri").val();
	
	//alert(uri);
	$("#navBar li[name='"+uri+"']").addClass("active");
	$(".updImg").click(function(){
		//上传头像控件共用,点击保存按钮的功能在点击图片时重新绑定
		$("#saveimg").unbind("mousedown");
		$("#saveimg").bind("mousedown",saveImage);
	  	$.dialog({
			title : '编辑头像',
			content: $("#updateimgDialog")[0],
			width:'560px', 
	  	    height:'420px',
			lock: true,
			button : [{
				value : '关闭',
				callback : function() {
					$(this)
					$("#file_input").val('');
					$("#cutdiv").html("");
					$("#showbutton").attr("style","display:none");
				}
			}]
		}); 
	  	$("#file_input").val('');
		$("#img60").attr("src","");
		$("#img100").attr("src","");
		$("#viewImg").hide();
		$("#cutimg").attr("src","");
		$(".xgtx-tu").empty().html("<span style='color:#f00;line-height:5px;float:left;'>支持JPG、PNG格式的图片!</span>");
	});
	
});

function uploadImg_1(){
	var imgurl = $("#file_input").val();
	if(imgurl==""){
		return false;
	}
	var index = imgurl.lastIndexOf(".");
	var str = imgurl.substring(index+1);
	if(str == 'jpg'||str == 'png'||str == 'JPG'||str == 'PNG'){
		$(".xgtx-tu").html("");
		$("#form2").submit();
	}else{
		$("#file_input").val('');
		$("#img60").attr("src","");
		$("#img100").attr("src","");
		$("#viewImg").hide();
		$(".xgtx-tu").html("<span style='color:#f00;line-height:38px;float:left;'>格式错误，请上传JPG、PNG格式的图片!</span>");
		return false;
	}	
}
$("#form2").ajaxForm(function(data){
	$(".xgtx-tu").html(data);
	if(data.indexOf('格式错误')>0){
		$("#file_input").val('');
		$("#img60").attr("src","");
		$("#img100").attr("src","");
		$("#viewImg").hide();
	}else{
		window.setTimeout(function(){
		$("#viewImg").show();
		},500);
	}
});
function cancleImg(){
	$(".d-close").click();
}
$("#saveimg").mousedown(saveImage);
function saveImage(){
	if($("#file_input").val() == ""){
		return false;
	}
	if(window.msg!=null){
		$.alert("请选择正确的图片");
		$("#file_input").val('');
		$(".xgtx-tu").html("");
		$("#img60").attr("src","");
		$("#img100").attr("src","");
		//imgdialog.close();
		$("#updateimgDialog").hide();
		$("#mask").hide();
		$("#viewImg").hide();
		return false;
	}
	
	$.post("${pageContext.request.contextPath}/uploadHeadImage",$("#form2").serialize(),function(data){
			$("#head").attr("src","${pageContext.request.contextPath}"+data.src+"_70.jpg");
			$("#updateimgDialog").css("display","none");
			$(".d-close").click();
			if(!data.success){}
	});
	$("#file_input").val('');
	$(".xgtx-tu").html("");
	$("#img60").attr("src","");
	$("#img100").attr("src","");
	$("#viewImg").hide();
}
</script>

</body>

</html>