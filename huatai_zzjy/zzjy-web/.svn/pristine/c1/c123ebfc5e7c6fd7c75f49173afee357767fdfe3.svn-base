;(function($,window,document){

	var plan = {
			options:{
				txMapKey:txMapKey,
				ctx:ctx,				//上下文对象
				cusId:cusId,			//客户编号
				openCusPlan:openCusPlan,
				disabled:false,			//是否可以编辑
				data:{					//存储所有的异步的数据
					customers:{			//客户的异步数据

					},
				},
				urls:{							//所有的AJAX请求的路径
					customersUrl:ctx+'/customer/findCustomers',	//获取客户列表URL
					customersEditUrl:ctx+'/customer/plan/edit',		//客户计划编辑URL
					customersDelUrl:ctx+'/customer/plan/delCusPlan',//删除客户计划URL
				},
				city:'',		//城市
			},
			init:function(){

				var self = this;

				self.event();

				var collapse = $('div[data-cus-plan-id="'+self.options.openCusPlan+'"] .panel-collapse');

				if(collapse.length > 0){
					$('.panel-collapse').collapse('hide');
					collapse.collapse('toggle');
				}

			},
			event:function(){
				var self = this;

				(function(){
					//点击客户姓名添加+
					$('button[data-target="customers-modal"]').on('click',function(){
						_searchCustomer(true);
					});
					//关闭添加准客户/客户x
					$('button[data-dismiss="customers-modal"]').on('click',function(){
						$('#customers-modal').modal('hide');
					});

					//选中
					$('#cutomers-body').on('click','input[name="customer"]',function(){
						var customer = $(this).closest('td').data('customer');
						//{customerId: "1", customerAddress: "上海徐汇", customerName: "测试用户", mobilePhone: "18217032345"}
						//改变选中的值
						self.options.cusId = customer.customerId;
						/*$('#customerAddress').val(customer.customerAddress);
						$('#customerName').val(customer.customerName);
						$('#mobilePhone').val(customer.mobilePhone);*/

						self.href();

					});

					$('#customer-search-btn').on('click',function(){
						//客户搜索按钮
						_searchCustomer(true);
					});

					$('#customer-search').on('keyup',function(e){
						//搜索框的回车键
						if(e.keyCode === 13){
							_searchCustomer(true);
						}
					});

					//获取客户信息
					function _searchCustomer(load){
						self.customerData(load || true,function(){

							$('#customers-modal').modal('show');

						});

					};

					//定位图标点击事件
					$('#map-line-icon').on('click',function(){
						/*layer.alert('功能尚在开发中');
						return;*/
						self.location.search();
					});

					//新增客户计划
					$('#btn-add-cus-plan').on('click',function(){
						if(!self.completedTask()){
							layer.confirm('您有未完成的任务，是否确认添加新的计划?',{icon: 3, title:'提示'},function(){
								self.edit('cusId='+self.options.cusId+'&from=1');//跳转新增计划
							});
						}else{
							self.edit('cusId='+self.options.cusId+'&from=1');//跳转新增计划
						}
					});

					$('.btn-delete').on('click',function(e){
						e.stopPropagation();
						var that = this;
						//删除按钮
						layer.confirm('确认删除当前计划?',{title:'警告',icon:3},function(){
							var cusPlanId = $(that).closest('.plans').data('cusPlanId'),
								customersDelUrl = self.options.urls.customersDelUrl;
							if(cusPlanId === ''){
								layer.msg('获取当前计划失败');
								return ;
							}
							$.get(customersDelUrl,{id:cusPlanId},function(data){
								if(data){
									if(data.status == '500'){
										layer.msg(data.errorMsg);
									}else{
										layer.msg(data.errorMsg);
										$(that).closest('.plans').remove();
									}
								}else{
									layer.msg('删除失败');
								}
							},'json');

						});
					});

					//新增任务点击按钮
					$('.btn-add-task').on('click',function(e){
						e.stopPropagation();
						var plan = $(this).closest('.plans'),
							cusPlanId = plan.data('cusPlanId');
						if(!self.completedPlanTask(plan)){
							layer.alert('当前计划不能新建任务，可能原因：<br/>1、有任务未完成<br/>2、已经成功递送，并回执签收<br/>请确认后再添加任务!');
							return ;
						}

						self.edit('cusId='+self.options.cusId+'&cusPlanId='+cusPlanId+'&from=4');//跳转新增任务
					});
					
					$('.plans').on('click',function(){
						//展开计划
						$('.panel-collapse').collapse('hide');
						$(this).find('.panel-collapse').collapse('show');
					});

					//任务编辑
					$('.task').on('click',function(e){
						e.stopPropagation();
						var taskId = $(this).data('cusTaskId'),
							cusPlanId = $(this).closest('.plans').data('cusPlanId');
						self.edit('cusId='+self.options.cusId+'&cusPlanId='+cusPlanId+'&taskId='+taskId+'&from=3');//跳转编辑任务
					});

				})();

			},
			location:{					//规划路线
				searchCity:function(callback){	//定位城市
					var self = plan, citylocation = new qq.maps.CityService();
					citylocation.setComplete(function(cityResult) {
						self.options.city = cityResult.detail.name;
						if(callback) callback.call(this,self.options.city);
					});
					//请求失败回调函数
					citylocation.setError(function() {
					});
					citylocation.searchLocalCity();
				},
				searchRoadLine:function(address){	//路径规划

					var self = plan, 
					//url = "http://apis.map.qq.com/tools/poimarker?type=1&keyword="+address+"&region="+self.options.city+"&key="+self.options.txMapKey+"&referer=myapp";
					url='http://apis.map.qq.com/uri/v1/search?keyword='+address+'&region='+self.options.city+'&referer=myapp';
					window.location.href=encodeURI(url);
				},
				search:function(){

					var  self = plan, customerAddress = $('#customerAddress').val();
					if(customerAddress === ''){
						layer.alert('请先填写客户地址');
						return ;
					}
					var  that =this,cityName = self.options.city;

					if(cityName === ''){
						//如果为空就重新定位
						that.searchCity(function(cityName){
							that.searchRoadLine(customerAddress);
						});
					}else{
						//如果不为空就直接定位
						that.searchRoadLine(customerAddress);
					}

				},
			},
			edit:function(params){	//跳转到编辑页面
				var self = this;
				location.href=self.options.urls.customersEditUrl+'?'+(params || '');
			},
			completedTask:function(){ //检查计划任务是否全部完成
				var completed = true;//默认表示全部完成
				$('.plans').each(function(){
					$(this).find('.plan-tasks tr').each(function(){
						var status = $(this).data('status');
						if(status === ''){	//表示未结束
							completed = false;	//false表示任务未全部完成
							return false;
						}
					});

					if(!completed) return false;

				});
				return completed;
			},
			completedPlanTask:function(plan){	//判断某个计划下面的任务是否完成
				var completed = true;//默认表示全部完成
				plan.find('.plan-tasks tr').each(function(){
					var status = $(this).data('status'),
						taskStage = $(this).data('taskStage');
					if(status === ''){	//表示未结束
						completed = false;	//false表示任务未全部完成
						return false;
					}else if(Number(status) === 1 && taskStage === 'PD'){
						completed = false;	//false表示PD阶段任务已经成功递送
						return false;
					}
				});
				return completed;
			},
			customerData:function(load,callback){	//加载客户列表

				var self = this,
					params = {customerName:$('#customer-search').val()};	//请求的参数

				 if(load){
					//如果确定需要立即加载就立即加载
					$('#customer-pager').pager(self.options.urls.customersUrl,7,$('#customer-view').view());
				}
				 $('#customer-pager').pager().setPostData(params);
				 $('#customer-pager').pager().reload();

				if(callback) callback.call(this);

			},
			href:function(){	//
				var self=this, href = location.href,cusId = self.options.cusId;
				if(/&back=1/.test(href)){
					href = href.replace(/&back=1/g,'');
				}
				if(/cusId=/.test(href)){
					location.href=href.replace(/cusId=(\d+)?/,'cusId='+cusId)
				}else{
					if(/\?/.test(href)){
						href += '&cusId='+cusId;
					}else{
						href += '?cusId='+cusId;
					}
					location.href=href;
				}
			},

	};

	plan.init();


})(jQuery,window,document)