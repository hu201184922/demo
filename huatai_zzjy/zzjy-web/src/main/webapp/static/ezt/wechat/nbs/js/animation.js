// JavaScript Document

	var oCon = document.getElementById('container');
	var oNextBtn = document.getElementById('nextBtn');
	var oPage1 = document.getElementById('page1');
	var oPage2 = document.getElementById('page2');
    window.onload=function(){
        firstPage();
        oNextBtn.addEventListener('click',function(){
            var p1Now=oPage1.className.search("go")>0;
            var p2Now=oPage2.className.search("go")>0;
            if( p1Now && !p2Now ){
                secondPage();
            }
            if( !p1Now && p2Now){
                 firstPage();
            }
            oNextBtn.style.right="-100%";
        },false);
    };
    function firstPage(){
        oPage1.className="page1 go";
        oPage2.className="page2";
		oCon.className = "container";
        setTimeout(function(){
			oNextBtn.innerHTML= "继续播放";
			oNextBtn.style.right="0";
        },20000);
    }
    function secondPage(){
        oPage1.className="page1 over";
        oPage2.className="page2 go";
		oCon.className = "container topcontainer";
		oNextBtn.innerHTML="重播";
        setTimeout(function(){
			oNextBtn.style.right="0";
        },6000);
		
    }