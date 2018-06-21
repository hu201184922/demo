/**
 *
 */
String.prototype.endWith=function(str){
		if(str==null||str==""||this.length==0||str.length>this.length)
		  return false;
		if(this.substring(this.length-str.length)==str)
		  return true;
		else
		  return false;
		return true;
		}
mui.init();
mui.ready(function(){
	
	(function(mui,$){
		
		var remind = {
			options:{
				ctx:ctx,
				deceleration:mui.os.ios?0.003:0.0009,//阻尼系数
				timeout:5000,	//ajax超时时间
				pullRefreshtimeout:500,//上拉加载延迟时间
				initDateFirst:true,	//是否页面加载的时就加载数据
				currentPage:{
					planListCurrentPage:0,			//计划的当前页
					//sharePlanListCurrentPage:0,		//收到的共享当前页
					policyRemindCurrentPage:0,
					planTrackAlertsCurrentPage:0,	//销售轨迹的当前页
					specialDateListCurrentPage:0	//纪念日当前页
				},
				pageSize:10,//默认分页大小
			},
			event:function(){

				var self = this;

				//收到的共享点击事件
				mui('#sharePlanData').on('tap','.item2OpBut',function(){
					var shareId = $(this).data('shareId'),
						type = $(this).data('type'),
						that = this;
					mui.ajax(self.options.ctx+'/ezt/wechat/remind/updateShare',{
						data:{shareId : shareId,opType : type},
						dataType:'json',
						type:'post',
						timeout:self.options.timeout,
						success:function(data){
							if(data && data.runFlag === 'S'){
								//$(that).closest('span').empty();
								var butDesc = "";
								if(opType == 'A'){
									butDesc = "<button class='common-btn'>已同意</button>";
								}else{
									butDesc = "<button class='common-btn'>已拒绝</button>";
								}
								$(that).closest('span').html(butDesc);
							}
						},
						error:self.error,
					});
				});

				//销售轨迹的点击事件
				mui('#planTrackData').on('tap','.item3A',function(){
					var that = this;
					 var ids = $(this).data('cus-plan-id').split(",");
					cusPlanId = ids[0];
					mui.ajax(self.options.ctx+'/ezt/wechat/remind/getMyPlanTrackDetail',{
						data:{cusPlanId : cusPlanId},
						dataType:'json',
						type:'post',
						timeout:self.options.timeout,
						success:function(data){
							//console.log(data);
							if(data && data.runFlag === 'S'){
								$(that).removeClass("item3A");
								var ul = "<ul class='mui-table-view mui-table-view-chevron share-timetree'>";
								mui.each(data.detailList, function(i,item){
									var detail = "<li><p>"+item.title +item.desc +"</p><em>"+item.time+"</em></li>";
									ul = ul + detail;
								});
								
								ul = ul +"<button style='right:65px;bottom:15px;' type='button' class='btn-not-notify item3Button' ref='"+data.taskId+"'>不再提醒</button>"
										+"</ul>";
								$(that).append(ul);
								$(that).off("tap");
							}
						},
						error:self.error,
					});

				});

				$("ul").on('tap','.toPlanDetail',function(){
					var ids =$(this).attr("id").split(",");
					var planId = ids[0];
					var planType = ids[1];
					var cusId = ids[2];
					 window.location.href=ctx+"/ezt/wechat/plan/getPlanDetail?planId="+planId+"&planType="+planType+"&cusId="+cusId;
				})
				
				//不在提醒点击事件
				mui('ul').on('tap','.item3Button', function(){
					var _obj = $(this);
					var cusTaskId = _obj.attr("ref");
					var ids = $(this).parent().parent().data('cus-plan-id').split(",");
					var alertType = ids[1];
					//var cusTaskId =ids[0];
					mui.confirm('将不再提醒该销售轨迹','警告',['是','否'],function(e){
						if(e.index == 0){
							mui.ajax(self.options.ctx+'/ezt/wechat/remind/updateTask',{
								data:{cusTaskId : cusTaskId,alertType:alertType},
								dataType:'json',
								type:'post',
								timeout:self.options.timeout,
								success:function(data){
									if(data.runFlag === 'S'){
										_obj.parent().parent().remove();
									}
								},
								error:self.error,
							});
						}
					}); 
					/*mui.ajax(self.options.ctx+'/ezt/wechat/remind/updateTask',{
						data:{cusTaskId : cusTaskId,alertType:alertType},
						dataType:'json',
						type:'post',
						timeout:self.options.timeout,
						success:function(data){
							if(data.runFlag === 'S'){
								_obj.parent().parent().remove();
							}
						},
						error:self.error,
					});*/
				});


			},
			init:function(){
				
				/*$("#back_remind").on('tap',function(){
					$("#remind_specialDate").css("display","none");
					$("#slider").css("display","");
				})*/
				var that = this;
				//阻尼系数
				var deceleration = mui.os.ios?0.003:0.0009;
				$('.mui-scroll-wrapper').scroll({
					bounce: false,
					indicators: true,		 //是否显示滚动条
					deceleration:that.options.deceleration
				});
				
				//循环初始化上拉加载事件
				mui.each(document.querySelectorAll('.mui-slider-group .mui-scroll'),function(index, pullRefreshEl){
					mui(pullRefreshEl).pullToRefresh({
						up: {
							callback:function() {
								var  self = this;

								setTimeout(function(){
									switch (index) {
										case 0:
											that.planList(function(finished){
												self.endPullUpToRefresh(finished);
											});
											break;
										case 1:
											that.sharePlanList(function(finished){
												self.endPullUpToRefresh(finished);
											});
											break;
										case 2:
											that.planTrackAlerts(function(finished){
												self.endPullUpToRefresh(finished);
											});
											break;
										case 3:
											that.specialDateList(function(finished){
												self.endPullUpToRefresh(finished);
											});
											break;
										default:
											console.log('异常数据');
											break;
									}
								},that.options.pullRefreshtimeout);
							}
						},
					});

				});


				//点击tab是显示page
				mui('#sliderSegmentedControl').on('tap','a',function(){
		           var lang = this.getAttribute('lang');

		           mui('#slider').slider().gotoItem(lang);
		         });

				//初始化加载数据
				that.initDateFirst();

				//初始化事件
				that.event();
			},
			initDateFirst:function(){
				if(this.options.initDateFirst){
					//初始化加载数据
					mui.each(document.querySelectorAll('.mui-slider-group .mui-scroll'),function(){
						mui(this).pullToRefresh().pullUpLoading();
					});
				}
			},
			planList:function(callback){	//计划
				
				var self = this,
				params = {currentPage:self.options.currentPage.planListCurrentPage + 1,pageSize:self.options.pageSize},
				templete =
				' <li class="mui-table-view-cell mui-media toPlanDetail" id="{id}">'+
                    '<a href="javascript:;"> <img class="mui-media-object mui-pull-left" src="{src}">'+
                    '<div class="mui-media-body">{title}'+
                        '<button class="btn-tip">{subTitle}</button><br/>'+
                        '<em class="mui-ellipsis mui-pull-left">{time}</em>'+
                    '</div>'+
                    '</a>'+
                '</li>',
                srcTemplete = self.options.ctx+'/static/ezt/wechat/images/img.png';
				mui.ajax(self.options.ctx+'/ezt/wechat/remind/planAlertList',{
					data:params,
					type:'post',
					dataType:'json',
					timeout:self.options.timeout,
					success:function(data){
						console.log(data);
						if(data){
							//设置当前页
							self.options.currentPage.planListCurrentPage = data.currentPage;
							//判断是否还有数据
							if(self.options.currentPage.planListCurrentPage === data.totalPage){
								callback.call(this,true);
							}else{
								callback.call(this,false);
							}

							var lis = [];
							mui.each(data.pageItems,function(index,item){
								var temp = templete;
								var id = item.planId+","+item.planType+","+item.cusId+","+item.taskId;
								var src = item.headPath;
								if(src.endWith(".jpg") || src.endWith(".png")){
									
								}else{
									src = src+"_70.jpg";
								}
								temp = temp.replace('{src}',src).replace('{title}',item.title).replace('{subTitle}',item.subTitle).replace('{time}',item.beginTime+'&nbsp;&nbsp ~ &nbsp;&nbsp'+item.endTime).replace('{id}',id);
								lis.push(temp);
							});
							$('#planAlertData').append(lis.join(''));

						}else{
                			callback.call(this,true);
                		}
					},
					error:self.error,
				});

			},
			/*sharePlanList:function(callback){	//收到的共享
				var self = this,
				params = {currentPage:self.options.currentPage.sharePlanListCurrentPage + 1,pageSize:self.options.pageSize},
				templete =
				'<li class="mui-table-view-cell mui-collapse">'+
                    '<a class="mui-navigate-right" href="#">{title}'+
                    '<button class="btn-tip">{subTitle}</button>'+
                    '</a>'+
                    '<ul class="mui-table-view mui-table-view-chevron share-plan">'+
                        '<li>'+
                            '<p>开始时间 <span>{sharerId}</span></p>'+
                        '</li>'+
                        '<li>'+
                            '<p>{beginTime} <span class="text-red">{conflic}</span></p>'+
                        '</li>'+
                        '<li>'+
                            '<p>结束时间</p>'+
                        '</li>'+
                        '<li>'+
                            '<p>{endTime} <span>{btns}</span>'+
                            '</p>'+
                        '</li>'+
                    '</ul>'+
                '</li>',
                btn1 = '<span class="text-red">已被其他计划占用！</span>',
                btn2 = '<button class="common-btn text-red item2OpBut" data-share-id="{shareId}" data-type="R">拒绝</button>' +
					  '<button class="common-btn item2OpBut" data-share-id="{shareId}" data-type="A">同意</button>',
			  	btn3 = '<button class="common-btn">已同意</button>',
			  	btn4 = '<button class="common-btn">已拒绝</button>',
			  	btn5 = '<button class="common-btn">已过期</button>';
			  	mui.ajax(self.options.ctx+'/ezt/wechat/remind/sharePlanList',{
			  		data:params,
			  		type:'post',
			  		dataType:'json',
			  		success:function(data){
			  			if(data && data.runFlag === 'S'){
			  				self.options.currentPage.sharePlanListCurrentPage = data.currentPage;
			  				if(self.options.currentPage.sharePlanListCurrentPage === data.totalPage){
			  					callback.call(this,true);
			  				}else{
			  					callback.call(this,false);
			  				}

			  				if(data.isHaveData === '1'){
			  					var btn2Temp = btn2,lis=[];
					  			mui.each(data.pageItems,function(index,item){
					  				var temp = templete;
					  				var conflicStatus = item.conflicStatus,
					  				 status = item.status;
				  				 	if(conflicStatus === 'Y'){
					  				 	temp = temp.replace('{conflic}','已被其他计划占用！');
									}

									if(status === 'O'){
										temp = temp.replace('{btns}',btn5)
									}else if(status === 'U'){
										temp = temp.replace('{btns}',btn2Temp.replace(/\{shareId\}/g,item.shareId))
									}else if(status == 'A'){
										temp = temp.replace('{btns}',btn3)
									}else if(status == 'R'){
										temp = temp.replace('{btns}',btn4)
									}

									temp = temp.replace('{title}',item.title).replace('{subTitle}',item.subTitle).replace('{sharerId}',item.sharerId).replace('{beginTime}',item.beginTime).replace('{endTime}',item.endTime);

									lis.push(temp);
					  			});

					  			$('#sharePlanData').append(lis.join(''));
					  		}

			  			}else{
                			callback.call(this,true);
                		}
			  		},
			  		error:self.error,
			  	});
			},*/
			
			sharePlanList:function(callback){	//收到的共享
				var self = this,
				params = {currentPage:self.options.currentPage.policyRemindCurrentPage + 1,pageSize:self.options.pageSize},
				templete =
					'<li class="mui-table-view-cell mui-collapse mui-font-x2">'+
                '<a class="mui-navigate-right" href="#">保单号:{policyNum}&nbsp;&nbsp;<br/>客户姓名:{cusName}&nbsp;&nbsp;<label class="mui-media-body-label">承保日期:{insureDate}</label></a>'+
                '<ul class="mui-table-view mui-table-view-chevron share-plan">'+
                  '<li>'+
                        '<p>主险:  {insuranceName}</p>'+
                    '</li>'+
                    '<li>'+
                        '<p><i>交费频率:{paymentMethod}</i><i>保单状态:{status}</i></p>'+
                    '</li>'+
                    '<li>'+
                        '<p><i>首期交费方式:{firstParment}</i><i>本期应交保费:{premium}</i></p>'+
                    '</li>'+
                    '<li>'+
                        '<p><i>续期交费方式:{renewalFee}</i><i>垫交情况:{cushionCondition}</i></p>'+
                    '</li>'+                                    
                '</ul>'+
                '</li>';
				  mui.ajax(self.options.ctx+'/ezt/wechat/remind/getPolicyRemind',{
	               		data:params,
	               		type:'post',
	               		dataType:'json',
	               		success:function(data){
	               			if(data && data.isHaveData === '1'){
	               			self.options.currentPage.policyRemindCurrentPage = data.currentPage;
				  				if(self.options.currentPage.policyRemindCurrentPage === data.totalPage){
				  					callback.call(this,true);
				  				}else{
				  					callback.call(this,false);
				  				}
	               				var lis = [];
	               				mui.each(data.policyInfoList.pageItems,function(index,item){
	               					var temp = templete; 
	               					var paymentMethod =item.paymentMethod||'';
	               					var cusName =item.cusName||'';
	               					var status =item.status||'';
	               					var firstParment =item.firstParment||'';
	               					var premium = item.premium ||'';
		               				var renewalFee = item.renewalFee||'';
		               				var cushionCondition = item.cushionCondition||'';
		               				
									temp = temp.replace('{policyNum}',item.policyNum).replace('{cusName}',cusName).replace('{insureDate}',item.insureDate).replace('{insuranceName}',item.insuranceName).replace('{paymentMethod}',paymentMethod).replace('{status}',status).replace('{firstParment}',firstParment)
									.replace('{premium}',premium).replace('{renewalFee}',renewalFee).replace('{cushionCondition}',cushionCondition);

									lis.push(temp);
	               				});

	               				$('#policyData').append(lis.join(''))
	               			}else{
	                			callback.call(this,true);
	                		}
	               		},
	               		error:self.error
	               })
			  
			},
			planTrackAlerts:function(callback){		//销售轨迹
				var self = this,
				params = {currentPage:self.options.currentPage.planTrackAlertsCurrentPage + 1,pageSize:self.options.pageSize},
				templete=
				'<li class="mui-table-view-cell mui-collapse item3A" data-cus-plan-id={cusPlanId},{alertType}>'+
                    '<a href="#" data-cus-taskId={taskId} data-cus-cusId={cusId} onclick="toCustomerPlanDetail(this)">客户{desc}'+
                    '</a><span class="mui-navigate-right word-break"></span>'+
               '</li>';
               mui.ajax(self.options.ctx+'/ezt/wechat/remind/planTrackAlerts',{
               		data:params,
               		type:'post',
               		dataType:'json',
               		success:function(data){
               			if(data && data.isHaveData === '1'){

               				self.options.currentPage.planTrackAlertsCurrentPage = data.currentPage;
			  				if(self.options.currentPage.planTrackAlertsCurrentPage === data.totalPage){
			  					callback.call(this,true);
			  				}else{
			  					callback.call(this,false);
			  				}

               				var lis = [];
               				mui.each(data.planTrackAlertList.pageItems,function(index,item){
               					var temp = templete;
               					var title = item.customerName;
               					var desc = "";
								if(item.alertType === '1'){
									title = title + item.taskStage; 
									 if(item.taskStage == 'OP'){
										 title = title + "（初次晤谈）";
									 }else if(item.taskStage == 'PC'){
										 title = title + "（建议书说明及促成）";
									 }else if(item.taskStage == 'PD'){
										 title = title + "（保单递送）";
									 }
									 title = title + "环节时间超过一个月啦！";
								}else{
									title = title + item.taskStage; 
									 if(item.taskStage == 'OP'){
										 title = title + "（初次晤谈）到PC（建议书说明及促成）";
									 }else if(item.taskStage == 'PC'){
										 title = title + "（建议书说明及促成）到PD（保单递送）";
									 }else if(item.taskStage == 'PD'){
										 title = title + "（保单递送）";
									 }
									 title = title + "环节转化时间超过一个月啦！";
								}
								temp = temp.replace('{cusPlanId}',item.cusPlanId).replace('{desc}',title).replace('{alertType}',item.alertType).replace('{taskId}',item.taskId).replace('{cusId}',item.cusId);

								lis.push(temp);
               				});

               				$('#planTrackData').append(lis.join(''))
               			}else{
                			callback.call(this,true);
                		}
               		},
               		error:self.error
               })
			},
			specialDateList:function(callback){		//纪念日
				var self = this,
				params = {currentPage:self.options.currentPage.specialDateListCurrentPage + 1,pageSize:self.options.pageSize},
				templete=
				'<li class="mui-table-view-cell mui-media">'+
                    '<a href="javascript:;"> <img class="mui-media-object mui-pull-left" src="{src}">'+
                    '<div class="mui-media-body" >'+
                        '{name} <br/>'+
                        '<em class="mui-ellipsis mui-pull-left">{day}</em> <span>'+
                        '<button class="common-btn text-red btn-delete"  data-cusid="{cusid}" data-type="{type}" onclick="deleteSpecialDate(this)">删除</button>'+
                        '<button class="common-btn btn-edit" onclick="updateSpecialDate(this)">编辑</button>'+
                        '</span>'+
                    '</div>'+
                    '</a>'+
                '</li>',
                srcTemplete = self.options.ctx+'/static/ezt/wechat/images/img.png';
                mui.ajax(self.options.ctx+'/ezt/wechat/remind/specialDateList',{
                	data:params,
                	type:'post',
                	dataType:'json',
                	success:function(data){
                		console.log(data);
                		//var datas = data.specialDayInfoList
                		if(data && data.runFlag === 'S' && data.isHaveData === '1'){

                			self.options.currentPage.specialDateListCurrentPage = data.specialDayInfoList.currentPage;
			  				if(self.options.currentPage.specialDateListCurrentPage === data.specialDayInfoList.totalPage){
			  					callback.call(this,true);
			  				}else{
			  					callback.call(this,false);
			  				}

                			var lis = [];
                			
                			mui.each(data.specialDayInfoList.pageItems,function(index,item){
                				var temp = templete;
                				var image = item.headPath;
                				var name ;
                				if(item.type=='B'){
                					name = item.name+"生日提醒"
                				}else{
                					name = item.name+"纪念日提醒"
                				}
                				if(image.endWith(".jpg") || image.endWith(".png")){
									
								}else{
									image = image+"_70.jpg";
								}
                				temp = temp.replace('{src}',image).replace('{name}',name).replace('{day}',item.specialDay).replace('{cusid}',item.cusid).replace('{type}',item.type);
                				lis.push(temp);
                			});
                			$('#specialDateData').append(lis.join(''));

                		}else{
                			callback.call(this,true);
                		}
                	},
                	error:self.error
                })
			},
			error:function(event, xhr, settings, thrownError){	//ajax异常处理
				console.log(event);
				console.log(xhr);
				console.log(settings);
				console.log(thrownError);
			},
		};

		//执行
		remind.init();

	})(mui,$);
});
if(mui.os.plus){
	mui.plusReady(function () {
		if(plus.networkinfo.getCurrentType()==plus.networkinfo.CONNECTION_NONE){
			mui.toast('网络异常，请确认网络连接');
		}
	});
}