//IE8,9 placeholder属性兼容
function placeHolderComp(){
	  if( !('placeholder' in document.createElement('input')) ){
	    $('input[placeholder],textarea[placeholder]').each(function(){   
	      var that = $(this),   
	      text= that.attr('placeholder');   
	      if(that.val()===""){   
	        that.val(text).addClass('placeholder');   
	      }   
	      that.focus(function(){   
	        if(that.val()===text){   
	          that.val("").removeClass('placeholder');   
	        }   
	      })   
	      .blur(function(){   
	        if(that.val()===""){   
	          that.val(text).addClass('placeholder');   
	        }   
	      })   
	      .closest('form').submit(function(){   
	        if(that.val() === text){   
	          that.val('');   
	        }   
	      });   
	    });   
	  }
}
function getRealValue(obj){
	var that = $(obj);
    text= that.attr('placeholder');
	if(that.val() === text){
        return '';
    }else{
    	return that.val();
    }
}