function skipTo(){
	var num = $("#skipPageNumber").val();
	if(num.length==0){
		num = "1";
	}
	location.href=$("#skip").attr("href")+"&page="+num;
}

function validateDigits(inputObj,maxValue){
	var r = /^\+?[1-9][0-9]*$/;//正整数
	var num = inputObj.value.trim();
	if(!r.test(num)){
	    inputObj.value="";
	}else{
	    if (Number(num) > maxValue) {
	       inputObj.value =  maxValue;
	    }else{
	       inputObj.value =  num;
	    }
	}

}
$(document).ready(function(){
		$("#skipPageNumber").each(function(){
			var totalPages = $(this).attr("totalPages");
	        var maxValue = Number(totalPages)<=0?1:totalPages;
			this.onkeyup=function(){
				validateDigits(this,maxValue);
			};
			this.onblur = function(){
				validateDigits(this,maxValue);
			};
			this.onkeydown = function(){
				if(event.keyCode == 13){//判断是否按了回车，enter的keycode代码是13
					skipTo();
				}
			}
		});
		
		$("#skip").click(function(){
			skipTo();
		});
	});