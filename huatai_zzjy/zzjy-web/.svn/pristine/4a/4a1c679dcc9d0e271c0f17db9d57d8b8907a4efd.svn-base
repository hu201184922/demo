mui.init();
mui.ready(function(){

	(function(mui,$){

		//日期格式化
		Date.prototype.Format = function(fmt){
		  var o = {
		    "M+" : this.getMonth()+1,                 //月份
		    "d+" : this.getDate(),                    //日
		    "h+" : this.getHours(),                   //小时
		    "m+" : this.getMinutes(),                 //分
		    "s+" : this.getSeconds(),                 //秒
		    "q+" : Math.floor((this.getMonth()+3)/3), //季度
		    "S"  : this.getMilliseconds()             //毫秒
		  };
		  if(/(y+)/.test(fmt))
		    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
		  for(var k in o)
		    if(new RegExp("("+ k +")").test(fmt))
		  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
		  return fmt;
		}

		var date1 = new Date('1970/01/01 08:00');

		var options = {
			date:{
				beginTime:{
					beginDate:date1,
					selectedValue:'',
				},
				endTime:{
					beginDate:date1,
					selectedValue:'',

				}
			},

			cusplanid:'',
			custaskid:'',

			fn:1,		//fn 1:添加计划及任务 2:仅仅新增任务 3:修改任务
			cusId:cusId,
			successStatus:{
				OP:[1,2,3,4],
				PC:[1],
				PD:[1]
			},
			datas:{		//存储数据使用
				share:[],		//分享列表数据
				customer:[],	//客户信息数据
				taskStage:[], 	//任务状态/计划内容数据
				tempTaskStage:[],
				timeSetting:[],	//提醒设置数据
				product:[],		//提醒设置数据

			},
			ctx:ctx,	//jsp上下文对象
			txMapKey:txMapKey,
			from:from,	//1 新建客户计划，没有客户信息 2 共享的客户计划只能查看 3非共享的客户计划，可以编辑 默认0
			editCusPlanId:editCusPlanId,	//编辑的计划ID
			editCusTaskId:editCusTaskId,	//需要编辑的任务ID
			pickers:{	//存储选择器对象
				planTaskStagePicker:{},	//计划内容选择器
				beginTimePicker:{},		//开始时间选择器对象
				endTimePicker:{},		//结束时间选择器对象
				timeSettingPicker:{},	//提醒设置选择器时间
				productPicker:{},		//推荐产品选择器对象
				subStatusPicker:{},			//任务结果选择器对象

			},
			city:'',
			network:true,		//网络状态的判断
		};
		var defaultPage = '#customer_plan';
		/*console.log(options.from);
		if(Number(options.from) === 3 || Number(options.from) === 4){
			console.log('122');
			defaultPage = '#edit_customer_plan';
		}*/

		//初始化单页view
		var viewApi = mui('#app').view({
			defaultPage: defaultPage
		});
		//初始化单页的区域滚动
	//	mui('.mui-scroll-wrapper').scroll();

		var view = viewApi.view;
		(function($) {
			//处理view的后退与webview后退
			var oldBack = $.back;
			$.back = function() {

				if (viewApi.canBack()) { //如果view可以后退，则执行view的后退
					viewApi.back();
				} else { //执行webview后退
					oldBack();
				}
			};

		})(mui);


		var plan = {
			init:function(){

				var that = this;

				//初始化客户列表
				that.customerUser();

				//计划内容
				that.planTaskStage();

				//提醒设置
				that.timeSetting();

				//推荐产品
				that.product();

				//初始化事件
				that.event();


				if(Number(options.from) !== 0){
					//判断从哪里来，需要什么操作
					that.from();
				}



			},
			from:function(){	//判断从哪里来需要什么操作
				var temp = Number(options.from);

				switch(temp){
					case 1://新增计划

						//mui.trigger(document.getElementById('edit_customer_plan_btn'),'tap');
					break;
					case 2:
						//如果是共享的客户计划不可以编辑

						$('.btn-delete').hide();
						$('.edit_customer_plan_btn').hide();
						$('#edit_customer_plan_btn').hide();
						$('#btnSubmit').hide();

					break;
					case 3:

						//非共享可以编辑的情况
						/*var editCusPlanId = options.editCusPlanId,editCusTaskId = options.editCusTaskId;

						if(editCusPlanId){
							var lis = mui('.plans li[data-cusplanid="'+editCusPlanId+'"]');
							if(lis.length > 0){
								lis[0].classList.add('mui-active');

								var sublis = mui('.plans li[data-cusplanid="'+editCusPlanId+'"] ul li[data-custaskid="'+editCusTaskId+'"]');
								if(sublis.length > 0){
									setTimeout(function(){
										mui.trigger(sublis[0],'tap');
									}, 200);
								}

							}
						}*/

					break;
					case 4://新增任务
						/*var editCusPlanId = options.editCusPlanId;
						if(editCusPlanId){
							var lis = mui('.plans li[data-cusplanid="'+editCusPlanId+'"] .edit_customer_plan_btn');
							if(lis.length > 0){
								mui.trigger(lis[0],'tap');
							}
						}*/
					break;

				}
			},

			//初始化所有的数据
			initData:{

				//客户数据
				customer:function(callback){
					var params = {customerName:$('#search-customer').val()};

					mui.get(options.ctx+'/ezt/wechat/customers/findCustomers',params,function(data){
						options.datas.customer = data;
						if(callback) callback.call(this,data);
					},'json');
				},
				//计划任务内容
				planTaskStage:function(callback){
					mui.get(options.ctx+'/ezt/wechat/customerPlan/taskStage',function(data){
						options.datas.taskStage = options.datas.tempTaskStage = data;

						if(callback) callback.call(this,data);
					},'json');
				},
				//提醒设置
				timeSetting:function(callback){
					mui.get(options.ctx+'/ezt/wechat/customerPlan/timeSetting',function(data){
						options.datas.timeSetting = data;
						if(callback) callback.call(this,data);
					},'json');
				},
				//推荐产品
				product:function(callback){
					mui.get(options.ctx+'/ezt/wechat/customerPlan/product',function(data){

						options.datas.product = data;
						if(callback) callback.call(this,data);
					},'json');
				},
				//任务结果
				subStatus:function(code,callback){
					var params = {code:code};
					mui.get(options.ctx+'/ezt/wechat/customerPlan/subStatus?code='+code,function(data){
						options.datas.subStatus = data;
						if(callback) callback.call(this,data);
					},'json');
				},
				//初始化所有的数据
				get:function(){
					this.share();
					if(options.cusId == '') this.customer();
					this.planTaskStage();
					this.timeSetting();
					this.product();
				}
			},
			event:function(){

				var that = this;

				$('#search-customer-form').submit(function(e){
					if(e && e.preventDefault) {
			        　　//阻止默认浏览器动作(W3C)
			        　　e.preventDefault();
			        } else {
			        　　//IE中阻止函数器默认动作的方式
			        　　window.event.returnValue = false;
			        }

					options.datas.customer = {};
					//搜索

					that.customerUser();

					return false;
				});

				/*计划任务的显示与隐藏*/
				$('body').on('tap', '.cust-plan-action>a', function(e){
					e.preventDefault();
					$(this).parent().hasClass('mui-active') ? (
						$(this).parent().removeClass('mui-active')
					):(
						$(this).parent().addClass('mui-active')
					)
				});

				//点击新增客户计划跳转到新增客户计划页面
				mui('ul').on('tap','#edit_customer_plan_btn',function(e){



					if(!options.cusId){
						mui.alert('请先选择客户');
						return ;
					}

					if($('#cusName').val() === ''){
						mui.alert('请填写客户名称');
						return ;
					}
					/*if($('#phone').val() === ''){
						mui.alert('请填写客户电话');
						return ;
					}*/
					if($('#phone').val() !='' && !/^1[0-9]{10}$/.test($('#phone').val())){
						mui.alert('电话不合法');
						return ;
					}
					/*if($('#address').val() === ''){
						mui.alert('请填写客户地址');
						return ;
					}*/


					that.clear();

					options.datas.taskStage = $.extend([],options.datas.taskStage,options.datas.tempTaskStage,true);
					//重新初始化
					that.planTaskStage();

					options.fn = 1;

					//设置时间
					that.setDate(options.date.beginTime.beginDate.Format('yyyy-MM-dd hh:mm'));



					$('#ref').attr('disabled','disabled');


					$('#plan-name-title').html('计划');


					if(!that.isCompleteTask()){
						mui.confirm('您有未完成的任务,是否新建一个计划?','警告',['是','否'],function(e){
							if(e.index == 0){
								viewApi.go('#edit_customer_plan');
							}
						});
					}else{
						viewApi.go('#edit_customer_plan');
					}

				});

				//点击新增任务跳转到新增客户计划页面
				mui('ul').on('tap','.edit_customer_plan_btn',function(e){

					var plans = that.getPlanInfo($(this).closest('li'));

					if(!that.isCompleteTask(plans.cusplanid)){
						mui.alert('当前计划不能新建任务，可能原因：<br/>1、有未完成的任务<br/>2、已成功递送，并回执签收<br/>请确认后再添加新任务');
						return ;
					}



					options.fn = 2;

					that.clear();

					$('#ref').attr('disabled','disabled');


					options.cusplanid = plans.cusplanid;


					var  ul = $(this).closest('li').find('ul'),
						lis = ul.find('li'),
						taskStage = options.datas.taskStage,
						stages = [],
						removeStages = [];


						mui.each(lis,function(index,item){
							var planTask = that.getPlanTaskInfo($(item)),


								_taskStage = planTask.taskStage,
								subStatus = planTask.subStatus;

							if(_taskStage === 'OP' && Number(subStatus) === 1){

								//OP阶段签单成功，只能创建PD阶段
								removeStages.push('OP');
								removeStages.push('PC');
								return false;

							}else if(_taskStage === 'PC'){

								removeStages.push('OP');

								if(Number(subStatus) === 1){
									//PC阶段签单成功，只能创建PD阶段
									removeStages.push('PC');
									return false;
								}

							}else if(_taskStage === 'PD'){
								removeStages.push('OP');
								removeStages.push('PC');
								return false;
							}
						});

					if(removeStages.length > 0){
						$.each(taskStage,function(i,item){

							if($.inArray(item.value,removeStages) == -1){
								stages.push(item);
							}
						});
						options.datas.taskStage = stages;

						//重新初始化
						that.planTaskStage();
					}else{
						options.datas.taskStage = $.extend([],options.datas.taskStage,options.datas.tempTaskStage,true);
						//重新初始化
						that.planTaskStage();
					}

					//重新初始化开始日期和结束日期
					var endTimeTemp = that.getPlanLastEndTime(ul);
						//设置时间
						that.setDate(endTimeTemp,endTimeTemp);


						if(plans.remProduct){
							
							var product_text = options.pickers.productPicker.pickers[0];
							product_text.setSelectedValue(plans.remProduct);
							$('#pickerProduct').text(product_text.getSelectedText(plans.remProduct));
							
							$('#pickerProduct').data('remProduct',plans.remProduct);
						}

					$('#pre-anp').val(plans.anp);
					$('#policyNo').val(plans.policyno);
					$('#plan-name-title').html(plans.planName);

					that.disabledPlan();//计划相关的禁用掉

					viewApi.go('#edit_customer_plan');
				});
				//点击任务跳转到任务编辑页面
				mui('.tasks-list').on('tap','li',that.editTask);

				/* 删除计划 */
				$('.btn-delete').on('tap', function(){
					var _obj = $(this),
						btnArray = ['是', '否'];
					mui.confirm('确定要删除'+ $.trim($(this).prev().text())+ '吗？', '提醒', btnArray, function(e) {
						if (e.index === 0) {

							var cusplanid = _obj.closest('li').data('cusplanid');
							if(!cusplanid || cusplanid == '') {
								mui.toast('当前对象获取失败');
								return ;
							}
							mui.get(options.ctx+'/ezt/wechat/customerPlan/delCusPlan',{id:cusplanid},function(data){

								if(data){
									if(data.status == '500'){
										mui.toast(data.errorMsg);
									}else{
										mui.toast(data.errorMsg);
										_obj.closest('li').remove();
									}
								}else{
									mui.toast('删除失败');
								}
							},'json');

						}
					})
				});

				//提交按钮点击事件
				var submited = false;

				$("#btnSubmit").on('tap',function(){
					if(submited) return ;

					if($('#btnSubmit').hasClass('disabled')){

						return ;
					}

					$('#btnSubmit').addClass('btn-disabled disabled');


					submited = true;

					that.submit();

					setTimeout(function(){

						submited = false;

						$('#btnSubmit').removeClass('btn-disabled disabled');

					}, 2000);

				});

				//开始时间
				mui('li').on('tap','#beginTime',function(){

					if(!$('#beginTime').hasClass('mui-select-date')){
						return ;
					}

					var obj = this, picker = that.beginTime();


					picker.show(function(rs) {

						that.setDate(options.date.beginTime.beginDate.Format('yyyy-MM-dd hh:mm'),rs.value);

						obj.innerText = rs.text;
						picker.dispose();
					});


				});
				//结束时间
				mui('li').on('tap','#endTime',function(){

					if(!$('#endTime').hasClass('mui-select-date')){
						return ;
					}

					var obj = this, picker =that.endTime();

					picker.show(function(rs) {
						obj.innerText = rs.text;
						picker.dispose();
					});
				});

				//定位图标点击事件
				mui('li').on('tap','#road-line',function(){
					var address = $('#address').val();
					if(address == ''){
						mui.toast('请填写地址信息');
						return ;
					}
					plan.searchCity(function(){
						plan.searchRoadLine(address);
					});

				});

				//返回计划管理
				mui('.mui-bar').on('tap','button.mui-action-back',function(){
					viewApi.back();
				});
				
				mui('.customer-time').on('tap','#day',function(){
					plan.emptyRemind();
				});
				mui('.customer-time').on('tap','#hour',function(){
					plan.emptyRemind();
				});
				mui('.customer-time').on('tap','#minute',function(){
					plan.emptyRemind();
				});

			},
			emptyRemind:function(){
			 
				$('#pickerSet').text('');
				$('#pickerSet').data('remindTimeType','');
			},
			emptyCusRemind:function(){
				$('#day,#hour,#minute').val('');
			},
			setDate:function(beginDate,beginTimeValue,endTimeValue){
				var date ,self = this;

				if(beginDate){
					date = new Date(beginDate.replace(/-/g,'/'));
				}else{
					date = new Date('1970/01/01 08:00:00');
				}

				var minute = date.getMinutes();
				if(minute > 0 && minute <= 30){
					date.setMinutes(30);
				}else if(minute > 30 && minute <= 59){
					date.setHours(date.getHours() + 1);
					date.setMinutes(0);
				}



				//可选择的开始时间
				options.date.beginTime.beginDate = date;

				var tempDate =new Date(date);
					tempDate.setMinutes(tempDate.getMinutes()+30);
				//可选择的结束时间
				options.date.endTime.beginDate = tempDate;


				if(!beginTimeValue){
					//如果没有设置开始时间，默认当前时间
					var beginTempDate = new Date();
					minute = beginTempDate.getMinutes();
					if(minute > 0 && minute <= 30){
						beginTempDate.setMinutes(30);
					}else if(minute > 30 && minute <= 59){
						beginTempDate.setHours(beginTempDate.getHours() + 1);
						beginTempDate.setMinutes(0);
					}

					beginTimeValue = beginTempDate.Format('yyyy-MM-dd hh:mm');
				}


				if(beginTimeValue){
					//设置默认选择的开始时间
					options.date.beginTime.selectedValue = beginTimeValue;
					$('#beginTime').text(beginTimeValue);


					//设置默认选择的结束时间
					if(endTimeValue){
						options.date.endTime.selectedValue = endTimeValue;
						$('#endTime').text(endTimeValue)
					}else{
						var d = new Date(beginTimeValue.replace(/-/g,'/'));
							d.setMinutes(d.getMinutes()+30);
						options.date.endTime.selectedValue = d.Format('yyyy-MM-dd hh:mm');
						$('#endTime').text(d.Format('yyyy-MM-dd hh:mm'))

					}


				}

			},
			searchCity:function(callback){	//定位城市


				var citylocation = new qq.maps.CityService();
					citylocation.setComplete(function(cityResult) {
						options.city = cityResult.detail.name;
						console.log(options.city);
						if(callback) callback.call(this,options.city);
					});
					//请求失败回调函数
					citylocation.setError(function() {
					    mui.toast('获取城市失败');
					});
					citylocation.searchLocalCity();
			},
			searchRoadLine:function(address){	//路径规划

				var url = "http://apis.map.qq.com/tools/poimarker?type=1&keyword="+address+"&region="+options.city+"&key="+options.txMapKey+"&referer=myapp";
				window.location.href=url;
				//mui.openWindow(url);
				//window.open(url);
			},
			editTask:function(e){//任务编辑

				var that = plan;

				options.fn = 3;

				that.clear();

				var tasks = that.getPlanTaskInfo($(this)),

				status = tasks.status;
				subStatus = tasks.subStatus;

				options.datas.taskStage = $.extend([],options.datas.taskStage,options.datas.tempTaskStage,true);

				//重新初始化
				that.planTaskStage();

				options.cusplanid = tasks.cusplanid;
				options.custaskid = tasks.custaskid;

				//设置值
				//计划内容
				var plan_text = options.pickers.planTaskStagePicker.pickers[0];

				plan_text.setSelectedValue(tasks.taskStage);
				$('#pickerPlan').text(plan_text.getSelectedText(tasks.taskStage));


				//推荐产品
				var product_text = options.pickers.productPicker.pickers[0];
				if(tasks.remProduct!=''){
					product_text.setSelectedValue(tasks.remProduct);
					$('#pickerProduct').text(product_text.getSelectedText(tasks.remProduct));
				}
				$('#pre-anp').val(tasks.anp);
				$('#policyNo').val(tasks.policyno);
				$('#remark').val(tasks.remark);

				$('#plan-name-title').html(tasks.planName);

				//计划内容 subStatus subStatusPicker

				that.subStatus(tasks.taskStage,function(data){

					if(subStatus !== ''){
						var sub_status_text = options.pickers.subStatusPicker.pickers[0];
						sub_status_text.setSelectedValue(tasks.subStatus);
						$('#pickSubStatus').text(sub_status_text.getSelectedText(tasks.subStatus));
					}

				});


				$('#ref').val(tasks.ref || '');

				//提醒设置
				var remindTimeType = tasks.remindTimeType;

				if(remindTimeType != ''){
					if(remindTimeType === 'other'){
						var remindMinute = Number(tasks.remindMinute) || 0;
						
						if(remindMinute !==0){
							var day = parseInt(remindMinute / (60 * 24)),
							hour = parseInt(remindMinute % (60 * 24) / 60 ),
							minute = remindMinute - (day * 24 * 60+ hour * 60) ;
							
							$('#day').val(day);
							$('#hour').val(hour);
							$('#minute').val(minute);
						}
					}else{

						options.pickers.timeSettingPicker.pickers[0].setSelectedValue(remindTimeType);

						$('#pickerSet').text(remindTimeType+'小时');
					}
				}

				//重新初始化开始日期和结束日期
				var endTimeTemp = that.getPlanLastEndTime($(this).closest('ul'));
					that.setDate(endTimeTemp,tasks.beginTime,tasks.endTime);


				$('#pickerPlan').data('taskStage',tasks.taskStage);
				$('#pickerSet').data('remindTimeType',tasks.remindTimeType);
				if(tasks.remProduct!=''){
					$('#pickerProduct').data('remProduct',tasks.remProduct);
				}
				//计划部分内容不能修改

				if(status === 'N' && subStatus === ''){
					//未完成
					that.disabledTasks(false);
				}else{
					that.disabledElement();
				}

				viewApi.go('#edit_customer_plan');

			},
			isCompleteTask:function(cusPlanId){	//判断任务是否完成
				var complete = true,that = this;
				$('.plans>li').each(function(){
					var cusplanid = $(this).data('cusplanid');
					if(cusPlanId){
						if(cusPlanId == cusplanid){
							var uls = $(this).find('ul>li').each(function(){
								var planTask = that.getPlanTaskInfo($(this)),
									status = planTask.status,
									subStatus = planTask.subStatus,
									taskStage = planTask.taskStage;
								if(status === 'N' && subStatus===''){
									//未完成
									complete = false;
									return false;
								}else if(taskStage === 'PD' && Number(subStatus) === 1){
									//已成功递送，并回执签收
									complete = false;
									return false;
								}
							});
						}
					}else{
						var uls = $(this).find('ul>li').each(function(){
							var planTask = that.getPlanTaskInfo($(this)),
								status = planTask.status,
								subStatus = planTask.subStatus;
							if(status === 'N' && subStatus===''){
								//未完成
								complete = false;
								return false;
							}
						});

					}
				});

				return complete;
			},

			customerUser:function(){ //客户列表

				if(options.cusId == ''){

					$('#customers-list li').remove();
					var templete = '<li data-userid="{userid}" data-name="{name}" data-phone="{phone}" class="mui-input-row  mui-radio mui-left"><input name="radios" type="radio"><label>{name} <i class="mui-text-right">{phone}</i></label></li>';
					var size = 0,data = options.datas.customer;

					if(plan.isEmptyObject(data)){
						plan.initData.customer(function(d){
							data = d;
							_customer();
						});
					}else{
						_customer();
					}

					function _customer(){
						if(data && data.length > 0){
							var html = [];
							size = data.length;
							for(var i=0;i<size;i++){

								var user = data[i],temp = templete;
								html.push(temp.replace('{userid}',user.customerId).replace(/\{name\}/g,user.customerName).replace(/\{phone\}/g,user.mobilePhone==null?'':user.mobilePhone));
							}
							$('#customers-list').html(html.join(''));
						}else{
							$('#customers-list').html('');
						}
					}

					//跳转到客户列表页面
					$('ul #customers').off('tap');
					$('ul').on('tap','#customers',function(e){
						/*if(options.disabled){
							return ;
						}*/

						viewApi.go('#customer_user');
						//$('#customer_user').show();
					});
					$('#customers-list li').off('tap');
					$('#customers-list').on('tap','li',function(){

						var li = $(this).closest('li');
						options.cusId = userid = li.data('userid');

						//重新查询
						plan.href();
						//viewApi.back();
					});

				}
			},

			clear:function(){	//清理信息
				options.custaskid = '';
				options.cusplanid = '';
				options.date.endTime.selectedValue = '';
				options.date.beginTime.selectedValue = '';
				$('#pickerPlan').data('taskStage','');
				$('#pickerSet').data('remindTimeType','');
				$('#pickerProduct').data('remProduct','');
				$('#pickSubStatus').data('subStatus','');

				$('#pickerPlan').text('');
				$('#beginTime').text('');
				$('#endTime').text('');
				$('#pickerSet').text('');
				$('#pickerProduct').text('');
				$('#pickSubStatus').text('');
				$('#pre-anp').val('');
				$('#policyNo').val('');
				$('#remark').val('');
				$('.mui-table-cell :input').val('');
				$('#ref').val('');

				$('#pickerPlan').addClass('mui-selects');
				$('#beginTime').addClass('mui-select-date');
				$('#endTime').addClass('mui-select-date');
				$('#pickSubStatus').addClass('mui-selects');
				$('#pickerSet').addClass('mui-selects');
				$('#pickerProduct').addClass('mui-selects');

				options.date.beginTime.beginDate = date1;
				options.date.endTime.beginDate = date1;

				$('#btnSubmit').show().addClass('mui-btn-primary').removeClass('mui-btn-outlined disabled');
				$('.mui-checkbox-ul li input[type="checkbox"]').each(function(){
					this.checked = false;
				});
			},
			disabledTasks:function(isEnd){	//isEnd:表示任务是否结束 true结束 false 未结束
				//编辑的时候那些需要禁止点击
				if(isEnd){
					$('#beginTime').removeClass('mui-select-date');
					$('#endTime').removeClass('mui-select-date');
					$('#pickSubStatus').removeClass('mui-selects');
					$('#pickerSet').removeClass('mui-selects');
					$('#ref').attr('disabled','disabled');
				}

				$('#pickerPlan').removeClass('mui-selects');

				this.disabledPlan();

			},
			disabledPlan:function(){
				if($('#pickerProduct').data('remProduct') !== '') $('#pickerProduct').removeClass('mui-selects');
				if($('#pre-anp').val() !== '') $('#pre-anp').attr('disabled','disabled');
				if($('#policyNo').val() !== '') $('#policyNo').attr('disabled','disabled');
			},
			disabledElement:function(){//信息不可更改

					//options.disabled = true;
					$('#edit_customer_plan li :input').attr('disabled','disabled');
					this.disabledTasks(true);

					$('#btnSubmit').hide();
					$('#btnSubmit').removeClass('mui-btn-primary').addClass('mui-btn-default  disabled');

			},
			getPlanLastEndTime:function(ul){	//获取计划最后一个任务的结束时间

				var self = this, li = ul.find('li').last();

				return self.getPlanTaskInfo(li).endTime;
			},
			getPlanInfo:function(li){	//得到计划信息
				var remProduct = li.data('planRemproduct'),
				anp = li.data('planAnp'),
				policyno = li.data('planPolicyno'),
				cusplanid = li.data('cusplanid'),
				planName = li.data('planName');
				return {
					remProduct:remProduct,
					anp:anp,
					policyno:policyno,
					cusplanid:cusplanid,
					planName:planName,
				};
			},
			getPlanTaskInfo:function(li){	//得到任务信息
				var planTask = li.data('planTask'),
					beginTime = planTask.beginTime,
					endTime = planTask.endTime,
					remindTimeType = planTask.remindTimeType,
					remindMinute = planTask.remindMinute,
					custaskid = planTask.cusTaskId,
					status = planTask.status,
					taskStage = planTask.taskStage,
					remark = planTask.remark,
					subStatus = planTask.subStatus,
					ref = planTask.ref,

					tasks = this.getPlanInfo(li.closest('ul').closest('li'));
					tasks.beginTime = beginTime;
					tasks.endTime = endTime;
					tasks.remindTimeType = remindTimeType;
					tasks.remindMinute = remindMinute;
					tasks.custaskid = custaskid;
					tasks.status = status;
					tasks.taskStage = taskStage;
					tasks.remark=remark;
					tasks.subStatus=subStatus;
					tasks.ref=ref;

				return tasks;
			},
			subStatus:function(code,callback){		//获取任务结果信息
				var subStatusPicker = options.pickers.subStatusPicker;
				if(plan.isEmptyObject(subStatusPicker)){
					options.pickers.subStatusPicker = subStatusPicker = new mui.PopPicker();
				}
				var subStatusData = {};


				plan.initData.subStatus(code,function(data){
					subStatusData =data;

					_subStatus();
					if(callback) callback.call(this,data);
				});

				function _subStatus(){
					if(!subStatusData) return ;

					subStatusPicker.setData(subStatusData);
					mui('li').on('tap','#pickSubStatus',function(e){
						if(!$(this).hasClass('mui-selects')){
							return ;
						}

						var _obj = this;

						subStatusPicker.show(function(items) {
							mui.confirm('任务结果选择后不可再次修改','提示',['确认','取消'],function(e){
								if(e.index == 0){
									$('#pickSubStatus').data('subStatus',items[0].value);
									_obj.innerText = items[0].text;
									$('#ref').removeAttr('disabled');
								}
							});
						});
					});
				}
			},
			planTaskStage:function(){	//计划内容/任务状态picker

				var dataPicker = options.pickers.planTaskStagePicker;
				if(plan.isEmptyObject(dataPicker)){
					options.pickers.planTaskStagePicker = dataPicker = new mui.PopPicker();
				}
				var taskStageData = options.datas.taskStage;

				if(plan.isEmptyObject(taskStageData)){
					//如果数据为空就重新加载
					plan.initData.planTaskStage(function(data){
						taskStageData =data;
						_taskStage();
					});
				}else{
					_taskStage();
				}

				function _taskStage(){
					if(!taskStageData) return ;
					dataPicker.setData(taskStageData);
					mui('li').on('tap','#pickerPlan',function(e){
						if(!$(this).hasClass('mui-selects')){
							return ;
						}
						var _obj = this;
						dataPicker.show(function(items) {

							$('#pickerPlan').data('taskStage',items[0].value);

							plan.subStatus(items[0].value);

							_obj.innerText = items[0].text;
						});
					});
				}

			},
			beginTime:function(){	//开始时间picker

				//debugger;

				var ops = $('#beginTime').data('options') || '{}';


				ops = mui.extend({},ops,options.date.beginTime);

				//console.log(ops);

				var picker = options.pickers.beginTimePicker;

				if(picker.disposed !== undefined && !picker.disposed && !plan.isEmptyObject(picker)){
					//如果存在就销毁
					picker.dispose();
				}

				//从新创建
				picker = options.pickers.beginTimePicker  = new mui.DtPicker(ops);

				if(options.date.beginTime.selectedValue){

					picker.setSelectedValue(options.date.beginTime.selectedValue);
				}

				return picker;

			},

			endTime:function(){		//结束时间picker

				var ops = $('#endTime').data('options') || '{}';


				ops = mui.extend({},ops,options.date.endTime);

				var picker = options.pickers.endTimePicker;

				if(picker.disposed !== undefined && !picker.disposed && !plan.isEmptyObject(picker)){
					//如果存在就销毁

					picker.dispose();

				}

				picker = options.pickers.endTimePicker = new mui.DtPicker(ops);

				if(options.date.endTime.selectedValue){
					picker.setSelectedValue(options.date.endTime.selectedValue);
				}

				return picker;


			},
			timeSetting:function(){	//提醒设置picker

				var timeSetPicker = options.pickers.timeSettingPicker;
				if(plan.isEmptyObject(timeSetPicker)){
					options.pickers.timeSettingPicker = timeSetPicker = new mui.PopPicker();
				}
				var timeSettingData = options.datas.timeSetting;
				if(plan.isEmptyObject(timeSettingData)){
					plan.initData.timeSetting(function(data){
						_timeSetting();
					});
				}else{
					_timeSetting();
				}
				function _timeSetting(){
					timeSetPicker.setData(options.datas.timeSetting);

					mui('li').on('tap','#pickerSet', function(e){
						if(!$(this).hasClass('mui-selects')){
							return ;
						}
						var _obj = this;
						timeSetPicker.show(function(items) {
							$('#pickerSet').data('remindTimeType',items[0].value);
							_obj.innerText = items[0].text;
							plan.emptyCusRemind();
						});
					});
				}

			},

			product:function(){	//推荐产品picker

				var productPicker = options.pickers.productPicker;
				if(plan.isEmptyObject(productPicker)){
					options.pickers.productPicker = productPicker = new mui.PopPicker();
				}

				var productData = options.datas.product;

				if(plan.isEmptyObject(productData)){
					plan.initData.product(function(data){
						productData = data;
						_product();
					});
				}else{
					_product();
				}

				function _product(){
					if(!productData) return ;
					productPicker.setData(productData);
					mui('li').on('tap','#pickerProduct', function(e){
						if(!$(this).hasClass('mui-selects')){
							return ;
						}
						var _obj = this;
						productPicker.show(function(items) {

							if(plan.isEmptyObject(items)) return ;

							$('#pickerProduct').data('remProduct',items[0].value);
							_obj.innerText = items[0].text;
						});
					});
				}

			},
			isEmptyObject:function(obj){	//判断对象是否为空

			   for (var name in obj)
			    {
			       return false;
			    }
			    return true;
			},
			href:function(){	//页面跳转
				var href = location.href,cusId = options.cusId;
				if(/cusId=/.test(href)){
					location.href=location.href.replace(/cusId=(\d+)?/,'cusId='+cusId)
				}else{
					if(/\?/.test(href)){
						href += '&cusId='+cusId;
					}else{
						href += '?cusId='+cusId;
					}
					location.href=href;
				}
			},
			submit:function(){	//数据提交

				var self = this;

				var url,fn = options.fn;
				if(fn === 1){
					//新增计划及任务
					url = options.ctx+'/ezt/wechat/customerPlan/addCusPlan';
				}else if(fn === 2){
					//新增任务
					url = options.ctx+'/ezt/wechat/customerPlan/modifyPlanAndTask';
				}else if(fn === 3){
					//修改任务
					url = options.ctx+'/ezt/wechat/customerPlan/modifyPlans';
				}

				var cusId = options.cusId,
					taskStage = $('#pickerPlan').data('taskStage')|| '',
					beginTime = $('#beginTime').text(),
					endTime = $('#endTime').text(),
					subStatus = $('#pickSubStatus').data('subStatus') || '',
					ref = $('#ref').val(),
					status = '' ,
					cusName=$('#cusName').val(),
					phone=$('#phone').val(),
					address=$('#address').val(),
					policyNo = $('#policyNo').val(),
					anp = $('#pre-anp').val();

				$('.number').each(function(e){
					$(this).val($(this).val().replace(/\D+/g,''));
				});

				if(!cusId || cusId ==''){
					mui.toast('请险选择客户');
					return ;
				}

				if(cusName === ''){
					mui.toast('请填写客户名称');
					return ;
				}

				/*if(phone === ''){
					mui.toast('请填写客户电话');

					return ;
				}*/

				if(phone !='' && !/^1[0-9]{10}$/.test(phone)){
					mui.toast('电话不合法');
					return ;
				}

				/*if(address === ''){
					mui.toast('请填写客户地址');
					return ;
				}*/

				if(!taskStage || taskStage == ''){
					mui.toast('请选择任务内容');
					return;
				}
			
				if(subStatus === ''){
					status = 'N';
				}else if($.inArray(Number(subStatus),options.successStatus[taskStage]) > -1){
					status = 'C';
				}else{
					status = 'O';
				}
				
				if(subStatus !== '' && ref === '') {
					if(ref === ''){
						mui.toast('请填写REF数量');
						return;
					}else if(!/\d+/.test(ref)){
						mui.toast('REF数量只能是数字');
						return;
					}
				}
				
				if(policyNo !=='' && !/^[a-zA-Z0-9]{1,20}$/.test(policyNo)){
					mui.toast('保单号只能由1~20字母或数字组成');
					return ;
				}
				if(anp !=='' && anp.length > 20){
					mui.toast('预期ANP不能超过20位数');
					return ;
				}
				
				if(beginTime == ''){
					mui.toast('请选择任务开始时间');
					return;
				}
				if(endTime == ''){
					mui.toast('请选择任务结束时间');
					return;
				}
				if(beginTime >= endTime){
					mui.toast('结束时间不能小于开始时间');
					return;
				}

				//提醒设置
				var remindTimeType = $('#pickerSet').data('remindTimeType') || '',remindMinute = 0;
				if(remindTimeType == '' || remindTimeType == 'other'){

					remindTimeType = 'other';

					var day = $('#day').val(),
					hour = $('#hour').val(),
					minute = $('#minute').val();
					
					if(day!=''){
						remindMinute+=Number(day) * 24 * 60 ;
					}
					if(hour != ''){
						if(Number(hour) > 23){
							mui.toast('小时只能小于24');
							return;
						}
						remindMinute += Number(hour) * 60;
					}
					if(minute != ''){
						if(Number(minute) > 59){
							mui.toast('分钟只能小于60');
							return;
						}
						remindMinute+=Number(minute);
					}
				}else{
					remindMinute  =( Number(remindTimeType) || 0) * 60;
				}

				var params = {
					planType:1,
					subType:taskStage,
					taskStage:taskStage,
					cusName:cusName,
					phone:phone,
					address:address,
					remProduct:$('#pickerProduct').data('remProduct') || '',
					beginTime:beginTime,
					endTime:endTime,
					status:status,
					remindTimeType:remindTimeType,
					remindMinute:remindMinute,
					anp:anp,
					policyNo:policyNo,
					remark:$('#remark').val(),
					cusId:options.cusId,
					planId:options.cusplanid,
					taskId:options.custaskid,
					subStatus:subStatus,
					ref:ref,
				};
				console.log(params);
				console.log(url);

				mui.ajax(url,{
					data:params,
					dataType:'json',
					type:'post',
					timeout:10000,
					success:function(data){
						if(data.status === 500){
							if(data.conflict){
								//有冲突，解决方案
								mui.confirm(data.errorMsg,'警告',['修改冲突计划','修改当前计划'],function(e){
									if(e.index == 0){
										if(data.personPlanId){
											//跳转到个人计划
											var personPlanId = data.personPlanId;
											console.log('跳转到个人计划');
											location.href=options.ctx+'/ezt/wechat/plan/getPlanDetail?planType=2&planId='+personPlanId+'&shaId=1';
										}else{

											var cusplanid =data.cusPlanId,
											custaskid = data.cusTaskId,
											cusId = data.cusId;
											var li = mui('#customer_plan ul li[data-cusplanid="'+cusplanid+'"] ul li[data-custaskid="'+custaskid+'"]');
											if(li.length > 0){
												console.log('当前页面');
												viewApi.back();

												var timeout = setTimeout(function(){
													clearTimeout(timeout);
													mui.trigger(li[0],'tap');
												},300);

											}
											else{
												console.log('URL跳转');
												//如果当前的页面没有内容就实现URL跳转
												location.href=options.ctx+'/ezt/wechat/customerPlan/cusPlan?cusId='+cusId+'&cusPlanId='+cusplanid+'&cusTaskId='+custaskid+'&from=3&toBack';
											}

										}
									}
								});

							}else{
								mui.alert(data.errorMsg || '未知错误');
							}
						}else{

							if(params.subStatus === ''){
								mui.toast('保存成功');
								plan.href();
							}else if(params.subStatus === '1'){
								mui.confirm('恭喜你，成功完成任务！是否设置新的任务?','提示',['确认','取消'],function(e){
									if(e.index === 0){
										location.href=options.ctx+'/ezt/wechat/customerPlan/cusPlan?cusId='+options.cusId+'&cusPlanId='+data.cusPlanId+'&from=4'
									}else{
										plan.href();
									}

								});
							}else if(params.subStatus !== 1 && params.subStatus !== '' ){
								mui.confirm('是否设置新的任务?','提示',['确认','取消'],function(e){
									if(e.index === 0){
										location.href=options.ctx+'/ezt/wechat/customerPlan/cusPlan?cusId='+options.cusId+'&cusPlanId='+data.cusPlanId+'&from=4'
									}else{
										plan.href();
									}

								});
							}

						}

					},
					error:function(xhr,type,errorThrown){
						//异常处理；
						console.log(type);
						console.log(errorThrown);
						mui.toast('保存失败');
					},
					complete:function(){

						$('#btnSubmit').addClass('mui-btn-primary').removeClass('mui-btn-outlined disabled');

					},

				});


			},

		};

		plan.init();

		//控制只允许输入数字
		(function($){
			$('.number').on('keyup',function(e){
				$(this).val($(this).val().replace(/\D+/g,''));
			});
			$('.number').on('blur',function(e){
				$(this).val($(this).val().replace(/\D+/g,''));
			});
		})($);






	})(mui,jQuery);


});