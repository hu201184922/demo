<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<c:set var="ctx" value="${ pageContext.request.contextPath }" />
<html>
<head>
<title>sql语句执行</title>


	
<style type="text/css">
ul, li, p { margin:0; padding:0; }
body { font:12px/1.5em arial; }
 
.tab { border:1px solid #D5D5D5; }
.tab ul {
 list-style:none;
 padding:4px 5px 0;
 border-bottom:1px solid #ddd;
 background:#eee;
}
.tab li {
 float:left;
 padding:2px 20px 5px;
}
.tab li:hover {
 margin-bottom:-1px;  /*用于盖住ul的下边框*/
 font-weight:bold;
 background:#FFF;
 border:1px solid #ddd;
 border-bottom:none;
}
 
.tab li a, .tab li a:visited {
 padding:5px 0;
 color:#333;
 text-decoration:none;
}
.tab li a:hover {
 text-decoration:underline;
}
 
.tab div { padding:10px; }
 
.clearfix:after {
    content: "\20";
    clear: both;
    height: 0;
    visibility: hidden;
    display: block;
}
.clearfix { *zoom:1; }
</style>
</head>
 
<body>
<div class="panel panel-default">
	<div class="panel-body">
		<div class="col-md-10">
			<textarea id="sqlLang" style="width:1070px;height:200px"></textarea>
		</div>
	</div>
</div>
<!-- <nav class="navbar navbar-default" role="navigation"> -->
<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
	<input type="button" class="btn btn-default" value="执&nbsp&nbsp&nbsp行" onclick="executeSQL()"/>
<!-- 	<a id="exec" class="btn btn-default" href="#">执行</a> -->
</div>
<!-- </nav> -->
<div class="tab">
 	<ul class="clearfix">
    	<li><a href="#" id="sqlExecute">执行结果</a></li>
    	<li><a href="#" id="logShow">日志</a></li>
	</ul>
 	<div class="execTab" style="display: none">	
 	</div>
 	<div class="logTab">
 	</div>
 </div>
    <script type="text/javascript">
    String.prototype.startWith=function(s){ 
    	if(s==null||s==""||this.length==0||s.length>this.length) 
    	return false; 
    	if(this.substr(0,s.length)==s) 
    	return true; 
    	else 
    	return false; 
    	return true; 
    	}

    	function executeSQL(){
    		var sqls=$("#sqlLang").val();
    		if(sqls==""){
    			alert("没有可执行的SQL语句");
    			return;
    		}
    		if(sqls.indexOf("delete")!=sqls.lastIndexOf("delete")&&sqls.indexOf("delete")!=-1){
    			alert("每次只能出现一次删除操作");
    			$(".execTab").htmt("");
    			return;
    		}
    		if(sqls.indexOf("update")!=-1){
    			var r = window.confirm("确定要更新吗？");
    			if(!r)
    				return;
    		}
    		if(sqls.indexOf("delete")!=-1){
    			var r = window.confirm("确定要删除吗？");
    			if(!r)
    				return;
    		}
    		var nsqls=sqls.split(";");
    		for(var i=0;i<nsqls.length;i++){
    			var sql=nsqls[i].trim();
    			if(sql!=""){
    				if(sql.startWith("select")||sql.startWith("insert")||sql.startWith("update")||sql.startWith("delete")){
    							$.ajax({
                				type : "post",
            					url : "${ctx}/admin/sql/execute",
            					data : "sqls="+sqls,
            					success : function(msg){
            						$(".execTab").html(msg);
            						$(".execTab").show();
            						$(".logTab").hide();
            					}
                			});
            		}else{
            			alert("錯誤的操作");
            		}
    			}
    		}
    	}
    	
    	$("#logShow").click(function(){
    		$(".execTab").hide();
    		$(".logTab").html("");
    		$.ajax({
    			type:"get",
				url:"${ctx}/admin/sql/showLog",
				success:function(msg){
					$(".logTab").append(msg);
					$(".logTab").show();
				}
    		});
    	});
    	
    	$("#sqlExecute").click(function(){
    		$(".logTab").hide();
    		$(".execTab").show();
    	});
    	
</script>

</body>
</html>