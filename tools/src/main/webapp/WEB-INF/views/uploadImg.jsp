<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html decorator="null">
<script type="text/javascript">
	window.msg=null;
</script>
	<div>
		<img id="cutimg" src="${pageContext.request.contextPath}${src}_src.jpg" width="${width}" height="${height}" />
<%-- 		<img id="cutimg" style="max-height:400px" src="${pageContext.request.contextPath}/${src}_src.jpg"/> --%>
		<input type="hidden" name="imageSrc" value="${src}">
	</div>
	
		<script type="text/javascript">
		$(function(){
			//console.log($("#uri").val());
			
				$("#img60").attr("src","${pageContext.request.contextPath}${src}_src.jpg");
				$("#img100").attr("src","${pageContext.request.contextPath}${src}_src.jpg");
				  var jcrop_api,boundx,boundy,
				  $pimg = $('#preview1 img'),
				  xsize = 68,
				  ysize = 68,
				  $pimg2 = $('#preview2 img'),
				  xsize2 = 108,
				  ysize2 = 108;
				
				  
			 setTimeout(function(){
				 $('#cutimg').Jcrop({
                         setSelect: [ 20, 20, 200, 200 ],
                         onChange: updatePreview,
                         onSelect: updatePreview,
                         aspectRatio: 1
//                          maxSize:[100,100],
//                          minSize:[100,100]
				},function(){
				    // Use the API to get the real image size
				    var bounds = this.getBounds();
				    boundx = bounds[0];
				    boundy = bounds[1];
				    // Store the API in the jcrop_api variable
				    jcrop_api = this;
				    
				    // Move the preview into the jcrop container for css positioning

				  });
				 $("#wt").val($("#cutimg").width());
				 $("#ht").val($("#cutimg").height());
			 },1000);
			 function updateCoords(obj){
					
			};	
			
			function updatePreview(c){
			    if (parseInt(c.w) > 0) {
			      var rx = xsize / c.w;
			      var ry = ysize / c.h;
			      
			      $pimg.css({
			        width: Math.round(rx * boundx) + 'px',
			        height: Math.round(ry * boundy) + 'px',
			        marginLeft: '-' + Math.round(rx * c.x) + 'px',
			        marginTop: '-' + Math.round(ry * c.y) + 'px'
			      });
			    }
			    if (parseInt(c.w) > 0) {
				      var rx = xsize2 / c.w;
				      var ry = ysize2 / c.h;
				      
				      $pimg2.css({
				        width: Math.round(rx * boundx) + 'px',
				        height: Math.round(ry * boundy) + 'px',
				        marginLeft: '-' + Math.round(rx * c.x) + 'px',
				        marginTop: '-' + Math.round(ry * c.y) + 'px'
				      });
				}
			    $("#x").val(c.x);
				$("#y").val(c.y);
				$("#w").val(c.w);
				$("#h").val(c.h);
			  };
			});
		
	</script>
</html>