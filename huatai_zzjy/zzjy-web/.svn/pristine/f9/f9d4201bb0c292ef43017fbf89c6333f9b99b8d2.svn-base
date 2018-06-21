/**
 *
 */
mui.init();
mui.ready(function(){

	(function(mui,$){

		var options = {
			ctx:ctx,//jsp上下文对象
			currentPage:0,	//当前页
			isEnd:true,		//ajax加载完成的标识
			totalPage:1,
			noData:false,	//判断是否还需要加载
		};

		var sharing = {
			init:function(){
				this.event();
				this.shareLoad();


			},
			shareLoad:function(){

				var deceleration = mui.os.ios?0.003:0.0009;
				mui('.mui-scroll-wrapper').scroll({
					bounce: false,
					indicators: true, //是否显示滚动条
					deceleration:deceleration
				});

				//var end = true;
				mui.each(document.querySelectorAll('.mui-slider-group .mui-scroll'), function(index, pullRefreshEl) {
					//console.log(pullRefreshEl);


						mui(pullRefreshEl).pullToRefresh({

							up: {
								callback: function() {

									var self = this;

									setTimeout(function(){
										//获取数据
										sharing.getData(index,function(index,data){
											//options.isEnd = true;
											if(data.currentPage == data.totalPage){
												options.noData = true;
											}else{
												options.noData = false;
											}
											self.endPullUpToRefresh(options.noData);
										});

									},500);
								}

							}
						});
					});

				//初始化加载数据
				mui.each(document.querySelectorAll('.mui-slider-group .mui-scroll'),function(){
					mui(this).pullToRefresh().pullUpLoading();
				});

				//点击tab是显示page
				mui('#sliderSegmentedControl').on('tap','a',function(){
		           var lang = this.getAttribute('lang');

		           mui('#slider').slider().gotoItem(lang);
		         });

			},
			getData:function(index,callback){	//获取数据


				var that = this;

				mui.post(options.ctx+'/ezt/wechat/planShare/sharePager',{tab:index,currentPage:options.currentPage + 1},function(data){
					//创建节点
					that.createElement(index,data);

					if(callback)
						callback.call(this,index,data);
				},'json');

			},
			createElement:function(index,data){	//创建节点
				if(!data && data.length < 0)
					return ;

				options.currentPage = data.currentPage;
				var templete =
					'<li data-sha-id = "{shareId}" data-share-status = "{shareStatus}" class="mui-table-view-cell">'+
						'<div class="mui-table">'+
                        '<div class="mui-table-cell mui-col-xs-10">'+
                            '<h4 class="mui-ellipsis">{planName}<button>{stage}</button></h4>'+
                            '<p class="mui-h6 mui-ellipsis mui-color-black mui-font-x2">来自{from}向{to}分享<em class="mui-color-gray mui-font-x2 pl10">{time}</em></p>'+
                        '</div>'+
                        '<div class="mui-table-cell mui-col-xs-2 mui-text-right">'+
                            '{status}'+
                        '</div>'+
                    '</div>'+
                '</li>';
                var span1 = '<span class="mui-h5 mui-table-cell-accept"><button class="blue">已接受</button></span>',
                	span2 = '<span class="mui-h5 mui-table-cell-delect"><button class="red btn-delete">删除</button></span><span class="mui-h5 mui-table-cell-accept"><button class="gray">已拒绝</button></span>',
                	span3 = '<span class="mui-h5 mui-table-cell-refuse"><button class="red btn-refuse">拒绝</button></span><span class="mui-h5 mui-table-cell-accept"><button class="blue btn-accept">接受</button></span>';
				var ul ;

				if(index === 0) ul = $('#send_share');
				else if(index === 1) ul = $('#accept_share');
				var shares = data.pageItems,size = shares.length,i = 0,lis = [];

				for(;i < size;i++){
					var share  = shares[i],temp = templete;
					var str = temp.replace('{shareId}',share.shareId).replace('{shareStatus}',share.shareStatus);

					if(share.planType === '1'){
						//客户计划
						str = str.replace('{planName}',share.cusName+'客户计划 '+share.planName).replace('{stage}',share.taskStage);
						if(index === 0){
							str = str.replace('{from}','您').replace('{to}',share.sharedUsername || '神秘用户');
						}else if(index === 1){
							str = str.replace('{from}',share.shareUsername|| '神秘用户').replace('{to}','您');
						}

					}else if(share.planType === '2'){
						//个人计划
						str = str.replace('{planName}',share.cusName).replace('{stage}',share.taskStage || '');
						if(index === 0){
							str = str.replace('{from}','您').replace('{to}',share.sharedUsername || '神秘用户');
						}else if(index === 1){
							str = str.replace('{from}',share.shareUsername|| '神秘用户').replace('{to}','您');
						}

					}else{
						continue;
					}

					str = str.replace('{time}',share.shareTime);
					if(share.shareStatus === 'U'){
						if(index === 0) str =  str.replace('{status}','')
						else str = str.replace('{status}',span3);
					}else if(share.shareStatus === 'R'){
						str = str.replace('{status}',span2);
					}else if(share.shareStatus === 'A'){
						str = str.replace('{status}',span1);
					}

					lis.push(str);
				}

				ul.append(lis.join(''));


			},
			event:function(){

				var that = this;

				//删除
				mui('ul').on('tap','.btn-delete',function(e){
					var shaId = $(this).closest('li').data('shaId'),
						opType = 'D';
						shareStatus = $(this).closest('li').data('shareStatus');
					that.modifyShare(shaId,opType,shareStatus,this);
				});
				//拒绝
				mui('ul').on('tap','.btn-refuse',function(){
					var shaId = $(this).closest('li').data('shaId'),
						opType = 'U';
						shareStatus = $(this).closest('li').data('shareStatus');
					that.modifyShare(shaId,opType,'R',this);
				});
				//接收
				mui('ul').on('tap','.btn-accept',function(){
					var shaId = $(this).closest('li').data('shaId'),
						opType = 'U';
						shareStatus = $(this).closest('li').data('shareStatus');
					that.modifyShare(shaId,opType,'A',this);
				});
			},
			modifyShare:function(shareId,opType,shareStatus,that){
				if(!shareId || shareId == '' ){
					mui.toast('当前对象获取失败');
					return ;
				}
				var tooptis = '确定删除?',
					tooltips_title = '警告',
					btn = ['是', '否'],
					span_text = '已删除',
					active = 'blue',
					btn_class = 'btn-delete',
					isConflic = false;
				switch (opType) {
					case 'D':
						tooptis = "确定删除?";
						tooltips_title = '警告';
						span_text = '已删除';
						active = 'gray';
						btn_class = 'btn-delete';
						isConflic = false;
						break;
					case 'U':
						if(shareStatus === 'A'){
							tooptis = "确认接收?";
							tooltips_title = '提示';
							span_text = '已接收';
							active = 'blue';
							btn_class = 'btn-accept';
							isConflic = true;
						}else if(shareStatus === 'R'){
							tooptis = "确认拒绝?";
							tooltips_title = '提示';
							span_text = '已拒绝';
							active = 'gray';
							btn_class = 'btn-refuse';
							isConflic = false;
						}
						break;
				}

				mui.confirm(tooptis,tooltips_title,btn,function(e){
					if(e.index === 0){
						var params = {
							id:shareId,
							optype:opType,
							shareStatus:shareStatus,
							isConflic:isConflic
						};
						sharing.ajax(params,function(data){

							if(opType === 'D'){
								mui.toast('删除成功');
								$(that).closest('li').remove();
							}else if(shareStatus === 'A'){
								mui.toast('成功接收');
								var span = $(that).closest('span');

								if(span.next('span').length > 0)
							 		span.next('span').remove();
							 	else span.prev('span').remove();

							 	$(that).text(span_text);
							 	$(that).removeClass('red').removeClass(btn_class).addClass(active);
							}else if(shareStatus === 'R'){
								mui.toast('已拒绝');
								var span = $(that).closest('span');
								span.next('span').find('button').removeClass('btn-accept').addClass('gray').text('已拒绝');
								$(that).removeClass('btn-refuse').addClass('btn-delete').addClass('red').text('删除');
							}

						});

					}
				});
			},
			ajax:function(params,callback){
				mui.post(options.ctx+'/ezt/wechat/planShare/dealShare',params,function(data){
					if(data){
						if(data.status === 200){
							if(callback) callback.call(this,data);
						}else if(data.status === 500){
							if(data.conflic){
								//说明有冲突
								mui.confirm('当前共享的计划与已知的计划有冲突,是否确认接受?','提示', ['接受', '拒绝'],function(e){
									if(e.index === 0){
										params.isConflic = false;
										params.shareStatus ='A';
									}else if(e.index === 1){
										params.isConflic = false;
										params.shareStatus ='R';
									}
									sharing.ajax(params,callback);
								});
							}else{
								mui.toast('操作失败！');
							}
						}
					}else
						mui.toast('操作失败');
				},'json');
			}


		};

		sharing.init();


	})(mui,jQuery);


});