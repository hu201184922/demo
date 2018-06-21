//============================================================
//[描    述]  Ace.awt.Pager	主要功能：实现翻页标签
//============================================================

/**
 * 名称：Ace.awt.Pager
 */
Ace.util.jClass(Ace.util.Observable,{

	jClass : 'Ace.awt.Pager',

	initialize : function($super, target, options) {
		$super(new Array(['change']));
		this.target = target;
		this.options = Ace.merge(options || {},{
			url : null,
			postData: {},
			jsonRoot: function(data) {
				return data;
			},
			template : new Ace.util.Template(Ace.awt.Pager.TEMPLATE,{compiled:true}),
			pageSize : 15
		});
	},
	
	setPage : function(page){
		if(!this.options.url)
			this.fireEvent('change',this,[this.items.slice((page.currentPage-1)*this.options.pageSize,page.currentPage*this.options.pageSize)]);
		else
			this.fireEvent('change',this,[this.pageItems]);
		//绘制翻页标签
		if(page.currentPage==1 && page.totalPage==0){
			page.totalPage = 1;
		}
		this.html(page);
	},
	
	html:function(page){
		if(!this.target.hasClass('megas512'))
			this.target.addClass('megas512');
		this.target.html(this.options.template.applyTemplate(page));
		var current = $('span:contains("[index]")',this.target).clone();
		var pagea = $('a:contains("[index]")',this.target).clone();
		$('a:contains("[index]"),span:contains("[index]")',this.target).remove();
		this.getArray(page.totalPage,page.currentPage,1).each((function(zhis){
			return function(){
				var p = null;
				if(Ace.isString(this)){
					p = current.clone().html(this.toString()).removeClass('current');
				}else if(this == page.currentPage){
					p = current.clone().html(this.toString());
				}else{
					p = pagea.clone().html(this.toString()).attr('href',this.toString());
				}
				$('.page-next',zhis.target).before(p);
			};
		})(this));
		
		$('.page-previous',this.target).attr('href',page.currentPage<=1?'#':page.currentPage-1);
		$('.page-next',this.target).attr('href',page.currentPage>=page.totalPage?'#':page.currentPage+1);
		
		$("a",this.target).click((function(zhis){
			return function(){
				if($(this).attr("href")!='#'){
					zhis.currentPage=$(this).attr("href");
					zhis.currentPage = Ace.getMin(Ace.toInt(zhis.currentPage),page.totalPage);
					zhis.currentPage = Ace.getMax(Ace.toInt(zhis.currentPage),1);
					zhis.options.url?zhis.reload():zhis.setPage(zhis);
				}
				return false;
			};
		})(this));
		
		var rep = function(){
			$(this).val(this.value.replace(/\D/g,''));
			if($(this).val()!=''){
				$(this).val(Ace.getMax(Ace.toInt($(this).val()),1));
				$(this).val(Ace.getMin(Ace.toInt($(this).val()),page.totalPage));
			}
		};
		$(".page-go-input",this.target).bind({keyup:rep,afterpaste:rep,keydown:(function(zhis){
			return function(e){
				if((e.keyCode == 40 || e.keyCode == 38) && $(this).val()==''){
					$(this).val(0);
				}
				if(e.keyCode == 40){
					$(this).val(Ace.getMax(Ace.toInt($(this).val())-1,1));
				}else if(e.keyCode == 38){
					$(this).val(Ace.getMin(Ace.toInt($(this).val())+1,page.totalPage));
				}else if(e.keyCode == 13){
					zhis.currentPage=$(this).val();
					zhis.options.url?zhis.reload():zhis.setPage(zhis);
				}
			};
		})(this)});
		$(".page-go",this.target).click((function(zhis){
			return function(){
				if($(".page-go-input",zhis.target).val()!=""){
					zhis.currentPage=$(".page-go-input",zhis.target).val();
					zhis.options.url?zhis.reload():zhis.setPage(zhis);
				}
			};
		})(this));
		//$("a[href=#]",this.target).hide();
	},
	
	getArray : function(totalPage,currentPage,pageNumber) {
		var arraylist = new Array();
		// 循环产生页码 产生数量最多为11个 前1个 4 《当前页》4 后一个
		for ( var i = 1, size = 1; i <= totalPage && size <= (pageNumber * 2 + 3); i++) {
			if (i == 1) {// 第一页 必须
				arraylist.push(i);
				size++;
			} else if ((currentPage > totalPage - (pageNumber + 1) && i	+ (pageNumber * 2 + 2) > totalPage)// 当前页在前5位
					|| (i > currentPage - (pageNumber + 1) && i < currentPage)) {
				arraylist.push(i);
				size++;
			} else if (i == currentPage) {// 添加当前页
				arraylist.push(i);
				size++;
			} else if (i > currentPage && i < currentPage + (pageNumber + 1)
					&& size < (pageNumber * 2 + 2)) {// 当前页在后5位
				arraylist.push(i);
				size++;
			} else if (size > (pageNumber + 1) && size <= (pageNumber * 2 + 2)) {
				arraylist.push(i);
				size++;
			} else if (i == totalPage) {// 最后一个必须
				arraylist.push(i);
				size++;
			}
		}
		if (totalPage > 1 && !(arraylist[1] === 2)) {// 添加空位
			arraylist.splice(1, 0, "...");
		}
		if (totalPage > 1 && !(arraylist[arraylist.length - 2] === (totalPage - 1))) {// 添加空位
			arraylist.splice(arraylist.length - 1, 0, "...");
		}
		return arraylist;
	},
	
	setJSON : function(json,callback){
		if(jQuery.isPlainObject(json)){
			Ace.copy(this,json);
			this.options.pageSize = json.pageSize ? json.pageSize : this.options.pageSize;
			this.pageItems = json.pageItems ? json.pageItems : [];
		}else if(Ace.isArray(json)){
			json = json ? json : [];
			this.items = json;
			this.currentPage = 1;
			this.totalCount = json.length;
			this.totalPage = Ace.toInt(this.totalCount/this.options.pageSize)+(this.totalCount%this.options.pageSize!=0?1:0);
		}
		this.setPage(this);
		if(callback){
			callback.call(this);
		}
	},
	
	setJSONUrl:function(url,postData,jsonRoot){
		this.options = Ace.merge({
			url : url,
			postData : postData,
			jsonRoot : jsonRoot
		}, this.options);
		this.reload();
	},
	
	setPostData : function(postData){
		Ace.copy(this.options.postData,postData||{});
		Ace.copy(this.options.postData||{},{
			currentPage : this.currentPage,
			pageSize : this.options.pageSize
		});
	},
	
	reload : function(postData,callback) {
		if(Ace.isFunction(postData)){
			callback = postData;
			postData = {};
		}
		this.setPostData(postData);
		(function(zhis){
			jQuery.getJSON(zhis.options.url,zhis.options.postData, function(data){
				zhis.setJSON(zhis.options.jsonRoot?((typeof zhis.options.jsonRoot==='string')?data[zhis.options.jsonRoot]:zhis.options.jsonRoot.apply(zhis,[data])):data);
				callback?callback(data):null;
			});
		})(this);
	},
	
	find : function(name,value){
    	return this.items.each(function(){
    		if(this[name] == value)
    			return this;
    	});
    },
	
	update: function(name, value, data){
		for(var i=0;i<this.items.length;i++){
			if(this.items[i][name] == value)
    			this.items[i] = data;
		}
	},
	
	add : function(obj){
		this.items.push(obj);
	}
});
Ace.apply(Ace.awt.Pager, {}, {
	TEMPLATE : '<div class="text-center clearfix"><div class="pages_lf"><a class="page-previous" href="">«  上一页 </a><span class="current">[index]</span><a href="">[index]</a><a class="page-next" href=""> 下一页 »</a></div><div class="pages_turn"><span class="fr" style="margin-top:4px;"><input class="pages_go page-go" type="button" value="GO"/></span><span class="fr">共{totalCount}条&nbsp;至 <input class="page-go-input pages_input" type="text"> 页 </span></div></div>'
});