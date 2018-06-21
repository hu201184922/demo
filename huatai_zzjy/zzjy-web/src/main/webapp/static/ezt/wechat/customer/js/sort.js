//自由驱动工作室
//作者：林鑫
/*$(function(){
	sortInit();
})*/

function initials(dataArray) {//公众号排序
	//alert(dataArray);
	var SortBox=$(".sort_box");
    /*var SortList=$(".sort_list");
    //SortList.sort(asc_sort).appendTo('.sort_box');//按首字母排序
    SortList.appendTo('.sort_box');//按首字母排序*/
    /*function asc_sort(a, b) {
        return makePy($(b).find('.num_name').text().charAt(0))[0].toUpperCase() < makePy($(a).find('.num_name').text().charAt(0))[0].toUpperCase() ? 1 : -1;
    }*/
    var initials = [];
    var num=0;
    for(var i=0;i<dataArray.length;i++){
    	var item = dataArray[i];
    	var initial = makePy($(item).find('.num_name').text().charAt(0))[0].toUpperCase();
        if(initial>='A'&&initial<='Z'){
            if (initials.indexOf(initial) === -1)
                initials.push(initial);
        }else{
            num++;
        }
    }
    /*SortList.each(function(i) {
        var initial = makePy($(this).find('.num_name').text().charAt(0))[0].toUpperCase();
        if(initial>='A'&&initial<='Z'){
            if (initials.indexOf(initial) === -1)
                initials.push(initial);
        }else{
            num++;
        }
        
    });*/

    SortBox.empty();
    $.each(initials, function(index, value) {//添加首字母标签
        SortBox.append('<div class="sort_letter" id="'+ value +'">' + value + '</div>');
    });
    if(num!=0){SortBox.append('<div class="sort_letter" id="default">#</div>');}
    
    for (var i =0;i<dataArray.length;i++) {//插入到对应的首字母后面
    	var item = dataArray[i];
        var letter=makePy($(item).find('.num_name').text().charAt(0))[0].toUpperCase();
        switch(letter){
            case "A":
            	appendElement(item,'A');
                break;
            case "B":
            	appendElement(item,'B');
                break;
            case "C":
            	appendElement(item,'C');
                break;
            case "D":
            	appendElement(item,'D');
                break;
            case "E":
            	appendElement(item,'E');
                break;
            case "F":
            	appendElement(item,'F');
                break;
            case "G":
            	appendElement(item,'G');
                break;
            case "H":
            	appendElement(item,'H');
                break;
            case "I":
            	appendElement(item,'I');
                break;
            case "J":
            	appendElement(item,'J');
                break;
            case "K":
            	appendElement(item,'K');
                break;
            case "L":
            	appendElement(item,'L');
                break;
            case "M":
            	appendElement(item,'M');
                break;
            case "O":
            	appendElement(item,'O');
                break;
            case "P":
            	appendElement(item,'P');
                break;
            case "Q":
            	appendElement(item,'Q');
                break;
            case "R":
            	appendElement(item,'R');
                break;
            case "S":
            	appendElement(item,'S');
                break;
            case "T":
            	appendElement(item,'T');
                break;
            case "U":
            	appendElement(item,'U');
                break;
            case "V":
            	appendElement(item,'V');
                break;
            case "W":
            	appendElement(item,'W');
                break;
            case "X":
            	appendElement(item,'X');
                break;
            case "Y":
            	appendElement(item,'Y');
                break;
            case "Z":
            	appendElement(item,'Z');
                break;
            default:
            	appendElement(item,'default');
                break;
        }
    };
}
function appendElement(item,letter){
	var target = $(item);
	target.attr('class','sort_list forwardDiv '+letter);
	var obj = $("."+letter+":last");
	if(obj.size()==0){
		$('#'+letter).after(target);
	}else{
		obj.after(target);
	}
}
function sortInit(dataArray){
	var Initials=$('.initials');
    var LetterBox=$('#letter');
    Initials.find('ul').append('<li>A</li><li>B</li><li>C</li><li>D</li><li>E</li><li>F</li><li>G</li><li>H</li><li>I</li><li>J</li><li>K</li><li>L</li><li>M</li><li>N</li><li>O</li><li>P</li><li>Q</li><li>R</li><li>S</li><li>T</li><li>U</li><li>V</li><li>W</li><li>X</li><li>Y</li><li>Z</li><li>#</li>');
    initials(dataArray);

    $(".initials ul li").click(function(){
        var _this=$(this);
        var LetterHtml=_this.html();
        LetterBox.html(LetterHtml).fadeIn();

        Initials.css('background','rgba(145,145,145,0.6)');
        
        setTimeout(function(){
            Initials.css('background','rgba(145,145,145,0)');
            LetterBox.fadeOut();
        },1000);

        var _index = _this.index()
        if(_index==0){
        	mui('.mui-scroll-wrapper').scroll().scrollTo(0,0,300);//点击第一个滚到顶部
        }else if(_index==27){
            var DefaultTop=$('#default').position().top;
            mui('.mui-scroll-wrapper').scroll().scrollTo(0,-DefaultTop,300);
            //$('html,body').animate({scrollTop: DefaultTop+'px'}, 300);//点击最后一个滚到#号
        }else{
            var letter = _this.text();
            if($('#'+letter).length>0){
                var LetterTop = $('#'+letter).position().top;
                mui('.mui-scroll-wrapper').scroll().scrollTo(0,-LetterTop+45,300);
                //$('html,body').animate({scrollTop: LetterTop-45+'px'}, 300);
            }
        }
    })

    var windowHeight=$(window).height();
    var InitHeight=windowHeight-45;
    Initials.height(InitHeight);
    var LiHeight=InitHeight/28;
    Initials.find('li').height(LiHeight);
}