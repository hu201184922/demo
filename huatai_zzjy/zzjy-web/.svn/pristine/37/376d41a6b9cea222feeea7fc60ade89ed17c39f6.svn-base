$(function(){
	;(function($,window,document,layer){
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

		var task = {
			options:{
				txMapKey:txMapKey,
				ctx:ctx,				//上下文对象
				cusId:cusId,			//客户编号
				from:from,				//标识，判断从哪里来，需要什么操作
				cusPlanId:cusPlanId,	//计划编号
				cusTaskId:cusTaskId,	//任务编号
				_endTime:_endTime,		//当前计划最后一个任务的结束时间
				_taskStage:_taskStage,	//当前计划的所有任务阶段
				_subStatus:_subStatus,	//任务阶段
				disabled:false,			//是否可以编辑
				data:{					//存储所有的异步的数据
					customers:{			//客户计划的异步数据

					},
					taskStages:{		//计划内容

					},
					timeSetting:{		//提醒设置

					},
					remProduct:{		//推荐产品

					},
					subStatus:{			//任务结果

					},
				},
				successStatus:{	//任务成功的状态
					OP:[1,2,3,4],
					PC:[1],
					PD:[1],
				},
				urls:{							//所有的AJAX请求的路径
					customersUrl:ctx+'/customer/findCustomers',		//获取客户列表URL
					taskStagesUrl:ctx+'/customer/plan/taskStage',		//计划内容URL
					shareUserUrl:ctx+'/plan/share/users',				//分享URL
					timeSettingUrl:ctx+'/customer/plan/timeSetting',	//提醒设置URL
					sharedUsreUrl:ctx+'/plan/share/getSharedUsers',		//编辑回显分享的URL
					remProductUrl:ctx+'/customer/plan/product',			//推荐产品URL
					subStatusURL:ctx+'/customer/plan/subStatus',		//获取任务结果的URL
				},
				city:'',		//城市
				disabledMinutes:[],	//被禁用的分钟
				beginTimeSetting:{skin:'blue',dateFmt:'yyyy-MM-dd HH:mm',minDate:'1970-01-01 08:00',onpicked:function(){
					var v = $dp.cal.getNewDateStr();//获取选中后的时间
					task.setDate(v);
				}},//任务开始时间设置
				endTimeSetting:{skin:'blue',dateFmt:'yyyy-MM-dd HH:mm',minDate:'#F{$dp.$D(\'beginTime\',{m:30}) || \'1970-01-01 08:00\'}'},//任务结束时间设置
			},
			init:function(){
				var self = this;

				self.event();

				//获取计划内容
				self.taskStages(false,function(data){
					if(data && data.length > 0){
						var ops = [];
							//taskStage = $('#task-stage').data('taskStage');
						$.each(data,function(index,item){
							var temp_op = '';
							if(index === 0){
								temp_op = '<option selected="selected" value="'+item.value+'">'+item.text+'</option>';
							}else{
								temp_op = '<option value="'+item.value+'">'+item.text+'</option>';
							}
							ops.push(temp_op);
						});

						$('#task-stage').html(ops.join(''));
					}
				});

				//提醒设置
				self.timeSetting(false,function(data){
					if(data && data.length > 0){
						var ops = [];
						$.each(data,function(index,item){
							var temp_op = '';
							if(index === 0){
								temp_op = '<option selected="selected" value="'+item.value+'">'+item.text+'</option>';
							}else{
								temp_op = '<option value="'+item.value+'">'+item.text+'</option>';
							}
							ops.push(temp_op);
						});
						$('#time-setting').html(ops.join(''));
					}
				});

				//推荐产品
				self.remProduct(false,function(data){

					if(data && data.length > 0){
						var ops = [],
							remProduct = $('#remProduct').data('remProduct');
						//$('#remProduct')
						ops.push('<option value="">请选择</option>');
						$.each(data,function(index,item){
							var temp = '';

							if(item.value === remProduct){
								temp = '<option selected="selected" value="'+item.value+'">'+item.text+'</option>';
							}else{
								temp = '<option value="'+item.value+'">'+item.text+'</option>';
							}
							ops.push(temp);
						});
						 $('#remProduct').html(ops.join(','));
					}
				});

				setTimeout(function(){

					//判断从哪里来，需要什么操作
					self.from();

				},200);
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
						$('#customerAddress').val(customer.customerAddress);
						$('#customerName').val(customer.customerName);
						$('#mobilePhone').val(customer.mobilePhone);
						
						window.location.href =self.options.ctx+"/customer/plan/redirect?cusId="+self.options.cusId;
						//选中后就隐藏
						$('#customers-modal').modal('hide');

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
						self.customerData(load || true,function(data){
							$('#customers-modal').modal('show');
						});
					};

					$('#map-line-icon').on('click',function(){
						/*layer.alert('功能尚在开发中...');
						return;*/
						self.location.search();
					});
				})();

				(function(){
					//时间控制
					$('#beginTime').on('click',function(){
						if(self.options.disabled) return ;
						self.options.beginTimeSetting.disabledDates = self.disabledMinutes();
						WdatePicker(self.options.beginTimeSetting);
					})
					$('#endTime').on('click',function(){
						if(self.options.disabled) return ;
						self.options.endTimeSetting.disabledDates = self.disabledMinutes();
						WdatePicker(self.options.endTimeSetting);
					})

				})();

				(function(){
					//任务结果
					$('#task-stage').on('change',function(){
						var that =this;

						var code = $(that).val();
						self.subStatus(code);

					});

					$('#subStatus').on('click',function(){

						//防止未加载出来的情况
						if($(this).find('option').length <=1){

							var that = this,
							_substatus = $(this).data('subStatus'),
							taskStage = $('#task-stage').val();
							self.subStatus(taskStage,function(){
								$(that).val(_substatus);
							});

						}

					});

					$('#subStatus').on('change',function(){
						var val = this.value,
							ref = $('#ref');
						if($(this).attr('disabled') !== 'disabled'){
							layer.confirm('任务结果选择后不可再次修改.',{btn:['确认','取消']});
						}

						if(val === ''){
							ref.attr('disabled','disabled');
						}else{
							ref.removeAttr('disabled');
						}
					});
				})();

				(function(){
					$('#plan-task-save').on('click',function(){
						//保存按钮
						if($(this).hasClass('disabled')){
							return ;
						}

						var that = this;

						$(this).addClass('disabled');

						setTimeout(function(){
							$(that).removeClass('disabled');
						},1000);

						self.submit();
					});
					$('#plan-task-cancel').on('click',function(){
						//取消按钮
						self.href();
					});
				})();

			},
			disabledMinutes:function(){		//分钟禁用
				var self =this, minutes = self.options.disabledMinutes || [];
				if(minutes.length > 0){
					return minutes;
				}
				for(var i=0;i<60;i++){
					var temp = '....-..-.. ..\:';
					if(i === 0) continue;
					else if(i === 30) continue;
					else {
						if(i < 10){
							temp += '0'+i;
						}else{
							temp += i;
						}
					}
					minutes.push(temp);
				}
				self.options.disabledMinutes = minutes;
				return minutes;
			},
			setDate:function(time){
				var date ;
				if(time){
					date = new Date(time.replace(/-/g,'/'));
				}else{
					date = new Date();
				}

				minutes = date.getMinutes();
				if(minutes > 0 && minutes <= 30){
					date.setMinutes(30);
				}else if(minutes>30 && minutes<=60){
					date.setHours(date.getHours() + 1);
					date.setMinutes(0);
				}

				$('#beginTime').val(date.Format('yyyy-MM-dd hh:mm'));
				date.setMinutes(date.getMinutes()+30);
				$('#endTime').val(date.Format('yyyy-MM-dd hh:mm'));

			},
			from:function(){
				var self = this,temp = Number(self.options.from);

				self.options.disabled = false;

				switch (temp) {
				case 1:
					//获取任务结果
					self.subStatus('OP',function(){

					});

					self.setDate();

					$('#remProduct').val('');
					$('#anp').val('');
					$('#policyNo').val('');

					break;
				case 2:
					//如果是共享的客户计划不可以编辑
					self.options.disabled = true;
					self.disabled();

					break;
				case 3:		//编辑任务

					var _endTime = self.options._endTime,		//当前计划最后一个任务的结束时间

					beginTimeSetting = self.options.beginTimeSetting;
					beginTimeSetting.minDate = _endTime;	//重新设置时间


					 $('#task-stage').val($('#task-stage').data('taskStage'));

					//计划内容
					var  subStatus = $('#subStatus').data('subStatus');//当前的任务结果

					//获取任务结果
					self.subStatus($('#task-stage').val(),function(){
						$('#subStatus').val(Number(subStatus));

						if(subStatus ===''){
							self.editTaskDisabled(false);
						}else{
							//如果计划任务结束，则所有的不可以更改
							self.disabled(true);
						}
						//自动触发任务结果改变事件
						//$('#subStatus').trigger('change')
					});


					//提醒设置
					var timeSetting = $('input[name="time-setting"]:checked').val(),
						remindMinute = $('input[name="time-setting"]:checked').data('remindMinute');
					if(timeSetting === '0'){
						$('#time-setting').val(remindMinute);
					}else if(timeSetting === 'other'){
						var day = parseInt(remindMinute / (60 * 24)),
						hour = parseInt(remindMinute % (60 * 24) / 60 ),
						minute = remindMinute - (day * 24 * 60 + hour * 60) ;
						$('#day').val(day);
						$('#hour').val(hour);
						$('#minute').val(minute);
					}
					break;
				case 4:	//新增任务
					var _endTime = self.options._endTime,		//当前计划最后一个任务的结束时间
					_taskStage=self.options._taskStage,			//当前计划的所有任务阶段
					_subStatus = self.options._subStatus,		//任务阶段结果
					beginTimeSetting = self.options.beginTimeSetting;
					//endTimeSetting = self.options.beginTimeSetting;
					beginTimeSetting.minDate = _endTime;	//重新设置时间

					self.setDate(_endTime);

					self.disabledPlan();//计划相关的不可以编辑

					if(_taskStage === 'OP' && _subStatus == 1){
						//表示OP阶段签单成功
						//OP和PC都不能再建
						$('#task-stage option[value="OP"]').remove();
						$('#task-stage option[value="PC"]').remove();
					}else if(_taskStage === 'PC'){
						if(_subStatus == 1){
							$('#task-stage option[value="PC"]').remove();
						}
						$('#task-stage option[value="OP"]').remove();
					}else if(_taskStage === 'PD'){
						$('#task-stage option[value="OP"]').remove();
						$('#task-stage option[value="PC"]').remove();
						if(_subStatus == 1){
							//$('#task-stage option[value="PD"]').remove();
							self.disabled();
						}
					}

					//获取任务结果
					self.subStatus($('#task-stage').val(),function(){

					});

					break;
				default:
					break;
				}
			},
			taskSuccess:function(taskStage,subStatus){
				var self = this,
					successStatus = self.options.successStatus[taskStage];
				if($.inArray(subStatus,successStatus) > -1){
					return true;
				}
				return false;
			},
			disabledPlan:function(){	//计划有关的和某些任务有关的不可以编辑

				if($('#remProduct').val() != '') $('#remProduct').attr('disabled','disabled');
				if($('#anp').val() != '') $('#anp').attr('disabled','disabled');
				if($('#policyNo').val() != '') $('#policyNo').attr('disabled','disabled');

			},
			editTaskDisabled:function(isEnd){	//编辑任务的时候不可编辑的情况	isEnd：表示任务是否结束
				if(isEnd){
					//如果任务完成则，计划内容，时间，结果，ref等不可改
					$('#subStatus').attr('disabled','disabled');
					$('#beginTime').attr('disabled','disabled');
					$('#endTime').attr('disabled','disabled');
					$('#ref').attr('disabled','disabled');
				}
				$('#task-stage').attr('disabled','disabled');

				this.disabledPlan();
			},
			disabled:function(){		//所有的不可以编辑
				this.options.disabled = true;
				$('.main-content :input').not('#plan-task-cancel').attr('disabled','disabled');
				$('button[data-target="customers-modal"]').hide();
				//$('button[data-target="share-user-modal"]').hide();
				$('#plan-task-save').addClass('disabled');

			},
			location:{					//规划路线
				searchCity:function(callback){	//定位城市
					var self = task, citylocation = new qq.maps.CityService();
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

					var self = task, 
					//url = "http://apis.map.qq.com/tools/poimarker?type=1&keyword="+address+"&region="+self.options.city+"&key="+self.options.txMapKey+"&referer=myapp";
					url='http://apis.map.qq.com/uri/v1/search?keyword='+address+'&region='+self.options.city+'&referer=myapp';
					//window.location.href=url;
					//window.open(url);
					window.location.href=encodeURI(url);
				},
				search:function(){

					var  self = task,that = this, customerAddress = $('#customerAddress').val();
					if(customerAddress === ''){
						layer.alert('请先填写客户地址');
						return ;
					}

					var  cityName = self.options.city;

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
			taskStages:function(load,callback){	//获取计划内容
				var self = this,
					taskStagesUrl = self.options.urls.taskStagesUrl,
					tempData = self.options.data.taskStages;
				if($.isEmptyObject(tempData)){

					//为空的情况下就立即加载数据
					_ajaxCustomer();

				}else if(load){

					//如果确定需要立即加载就立即加载
					_ajaxCustomer();

				}else{

					if(callback) callback.call(this,tempData);
				}

				function _ajaxCustomer(){
					var params = {};	//请求的参数
					$.get(taskStagesUrl,params,function(data){
						self.options.data.taskStages = data;
						if(callback) callback.call(this,data);
					},'json');
				}
			},

			timeSetting:function(load,callback){	//提醒设置
				var self =this,
					timeSettingUrl = self.options.urls.timeSettingUrl,
					tempData = self.options.data.timeSetting;
				if($.isEmptyObject(tempData)){
					_ajaxTimeSetting();
				}else if(load){
					_ajaxTimeSetting();
				}else{
					if(callback) callback.call(this,tempData);
				}
				function _ajaxTimeSetting(){
					var params = {};	//请求的参数
					$.get(timeSettingUrl,params,function(data){
						self.options.data.timeSetting = data;
						if(callback) callback.call(this,data);
					},'json');
				}
			},
			remProduct:function(load,callback){	//推荐产品

				var self =this,
				remProductUrl = self.options.urls.remProductUrl,
				tempData = self.options.data.remProduct;
				if($.isEmptyObject(tempData)){
					_ajaxRemProduct();
				}else if(load){
					_ajaxRemProduct();
				}else{
					if(callback) callback.call(this,tempData);
				}
				function _ajaxRemProduct(){
					var params = {};	//请求的参数
					$.get(remProductUrl,params,function(data){
						self.options.data.remProduct = data;
						if(callback) callback.call(this,data);
					},'json');
				}
			},
			subStatus:function(code,callback){

				var self =this,
				subStatusUrl = self.options.urls.subStatusURL;

				var params = {code:code};	//请求的参数
				$.get(subStatusUrl,params,function(data){

					var
					subStatus = $('#subStatus'),
					ops = [];

					ops.push('<option value="">请选择任务结果</option>');
					$.each(data,function(index,item){
						ops.push('<option value="'+item.value+'">'+item.text+'</option>');
					});
					$('#subStatus option').remove();
					subStatus.html(ops.join(','));


					if(callback) callback.call(this);


				},'json');
			},
			submit:function(){	//提交
				var self = this, url = self.options.ctx+'/customer/plan/addCusPlan',fn = Number(self.options.from) ;

				if(self.options.disabled){
					return ;
				}
				if(fn === 1 ){
					//新增计划及任务
					url = self.options.ctx+'/customer/plan/addCusPlan';
				}else if(fn === 4){
					//新增任务
					url = self.options.ctx+'/customer/plan/modifyPlanAndTask';
				}else if(fn === 3){
					//修改任务
					url = self.options.ctx+'/customer/plan/modifyPlans';
				}

				var	cusId = self.options.cusId,
					planId = self.options.cusPlanId,
					taskId =self.options.cusTaskId,
					cusName = $('#customerName').val(),
					phone = $('#mobilePhone').val(),
					address = $('#customerAddress').val(),
					taskStage = $('#task-stage').val(),
					beginTime = $('#beginTime').val(),
					endTime = $('#endTime').val(),
					sharedUser = $('#shared-user').data('sharedUser'),
					subStatus = $('#subStatus').val(),
					ref = $('#ref').val(),
					remindTimeType = $('input[name="time-setting"]:checked').val(),
					remindMinute = 0,
					remark = $('#remark').val(),
					_endTime = self.options._endTime,
					remProduct = $('#remProduct').val(),
					policyNo = $('#policyNo').val(),
					anp = $('#anp').val(),
					status;

				
				if(subStatus === ''){
					status = 'N';	//未选择结果

				}else if($.inArray(Number(subStatus),self.options.successStatus[taskStage]) > -1){
					status = 'C';	//完成
				}else{
					status = 'O';	//失败
				}
				
				if(subStatus !== '' && ref === ''){
					layer.alert('请输入REF数量!');
					return ;
				}else{
					if(subStatus !== '' && !/\d+/.test(ref)){
						layer.alert('REF数量只能是数字');
						return ;
					}
				}
				debugger;
				if(policyNo !=='' && !/^[a-zA-Z0-9]{1,20}$/.test(policyNo)){
					layer.alert('保单号只能由1~20字母或数字组成');
					return ;
				}
				
				if(anp !== '' && anp.length > 20){
					layer.alert('预期ANP不能超过20位数');
					return ;
				}

				if(!cusId || cusId === ''){
					layer.alert('请选择客户!');
					return ;
				}


				//获取提醒设置的分钟数量
				if(remindTimeType === '0'){
					var _remindMinute = $('#time-setting').val();
					remindTimeType = _remindMinute;
					if(_remindMinute !== ''){
						remindMinute = Number(_remindMinute) * 60;
					}
				}else if(remindTimeType === 'other'){
					var day = $('#day').val(),
						hour = $('#hour').val(),
						minute = $('#minute').val();
					if(Number(hour) && Number(hour) > 23){
						layer.alert('小时只能小于24');
						return ;
					}
					if(Number(minute) && Number(minute) > 59){
						layer.alert('分钟只能小于60');
						return ;
					}
					if(day !== '') remindMinute+=Number(day) * 24 * 60;
					if(hour !== '') remindMinute+=Number(hour) * 60;
					if(minute !== '') remindMinute+=Number(minute);
				}

				var required = false;
				//判断不能为空
				$('.required').each(function(){
					if($(this).val() === ''){

						layer.alert(($(this).closest('div').prev('label').text().replace('*','')+'' || '' )+'不能为空');
						required = true;
						return false;
					}
				});

				if(required) return ;

				//手机号是否合法
				if(phone !='' && !/1[0-9]{10}/.test(phone)){
					layer.alert('手机号码不合法');
					return ;
				}


				if(beginTime >= endTime){
					layer.alert('结束时间不能小于开始时间');
					return;
				}
				if(_endTime > beginTime ){
					//layer.alert('当前任务的开始时间不能小于上一个任务的结束时间');
					//return;
				}
				var params = {
						planType:1,
						subType:taskStage,
						taskStage:taskStage,
						cusName:cusName,
						phone:phone,
						address:address,
						beginTime:beginTime,
						endTime:endTime,
						status:status,
						ref:ref,
						subStatus:subStatus,
						remindTimeType:remindTimeType,
						remindMinute:remindMinute,
						remark:remark,
						cusId:cusId,
						planId:planId,
						taskId:taskId,
						remProduct:remProduct,
						anp:anp,
						policyNo:policyNo
					};
				$.ajax({
					url:url,
					type:'post',
					dataType:'json',
					data:params,
					success:function(data){
						if(data.status === 500){
							if(data.conflict){
								//询问框
								layer.confirm(data.errorMsg,
								{icon: 3, title:'警告',btn:['修改冲突计划','修改当前计划']},
								function(){
									if(data.personPlanId){
										//跳转到个人计划
										var personPlanId = data.personPlanId;
										location.href=self.options.ctx+'/plan/personplan/updatePersonPlanPager?personPlanId='+personPlanId;
									}else{

										var _cusplanid =data.cusPlanId,
											_custaskid = data.cusTaskId,
											_cusId = data.cusId;
										location.href=self.options.ctx+'/customer/plan/edit?cusId='+_cusId+'&cusPlanId='+_cusplanid+'&taskId='+_custaskid+'&from=3';
									}
								},
								function(){

								});
							}else{
								layer.alert(data.errorMsg);
							}
						}else{
							if(params.subStatus === ''){
								layer.alert('保存成功');
								self.href();
							}else if(params.subStatus === '1'){
								layer.confirm('恭喜你，成功完成任务！是否设置新的任务?',{btn:['确定','取消']},function(index){
									layer.close(index);
									location.href=self.options.ctx+'/customer/plan/edit?cusId='+cusId+'&cusPlanId='+data.cusPlanId+'&from=4';
								},function(index){
									layer.close(index);
									self.href();
								});
							}else if(params.subStatus !== 1 && params.subStatus !== '' ){
								layer.confirm('是否设置新的任务?',{btn:['确定','取消']},function(index){
									layer.close(index);
									location.href=self.options.ctx+'/customer/plan/edit?cusId='+cusId+'&cusPlanId='+data.cusPlanId+'&from=4';
								},function(index){
									layer.close(index);
									self.href();
								});
							}

						}
					},
					error:function(xhr,type,errorThrown){
						//异常处理；
						layer.alert('保存失败');
					},
					complete:function(){
						setTimeout(function(){
							$('#plan-task-save').removeClass('disabled');
						},1500)
					},
				});

			},
			href:function(){	//

				var self=this,cusPlanId = '';
				if(self.options.cusPlanId){
					cusPlanId = '&cusPlanId='+self.options.cusPlanId;
				}
				var href =self.options.ctx+'/customer/plan/customers?cusId='+self.options.cusId+cusPlanId+'&toBackRemind';
				location.href=href;
			},
		};

		task.init();

		(function($){
			//控制只允许输入数字
			$('.number-only').on('keyup',function(e){
				$(this).val($(this).val().replace(/\D+/g,''));
			});

			$('.mobile').on('keydown',function(e){

				var mobile = $(this).val();
				if( ((e.keyCode >= 48 && e.keyCode <= 57) || (e.keyCode >= 96 && e.keyCode <= 105) ) && mobile.length > 10){
					return false;
				}

			});
		})($);



	})(jQuery,window,document,layer);
});
