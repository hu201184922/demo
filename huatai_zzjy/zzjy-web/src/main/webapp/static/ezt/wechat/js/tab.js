
function nTabs2(thisObj,Num){
if(thisObj.className == "active")return;
var tabObj = thisObj.parentNode.id;
var tabList = document.getElementById(tabObj).getElementsByTagName("li");
for(i=0; i <tabList.length; i++)
{
  if (i == Num)
  {
   thisObj.className = "active"; 
      document.getElementById(tabObj+"_2Content"+i).style.display = "inline-block";
  }else{
   tabList[i].className = "normal"; 
   document.getElementById(tabObj+"_2Content"+i).style.display = "none";
  }
} 
}


function nTabs3(thisObj,Num){
	if(thisObj.className == "active")return;
	var tabObj = thisObj.parentNode.id;
	var tabList = document.getElementById(tabObj).getElementsByTagName("li");
	for(i=0; i <tabList.length; i++)
	{
		var a =i+2;
	  if (i == Num)
	  {
	   thisObj.className = "active"; 
	     document.getElementById(tabObj+"_2Content"+i).style.display = "inline-block";
	      document.getElementById(tabObj+"_2Content"+a).style.display = "inline-block";
	  }else{
	   tabList[i].className = "normal"; 
	   document.getElementById(tabObj+"_2Content"+i).style.display = "none";
	   document.getElementById(tabObj+"_2Content"+a).style.display = "none";
	  }
	} 
	
	
	
	}