define(function(){
	var panel={
		init:function(){
			if(window.GlOBAL.TOOL.deParam().type==undefined){
				$.Bstatus({
					html:'参数错误',
					time:500,
					status:false
				});	
				return false;	
			};
			$('.ui-load-mode').remove();
			$('.ui-mode-panel').hide().css('visibility','visible').fadeIn();
			require.config({
				paths:{ 
					'echarts':'common/echarts',
					'echarts/chart/gauge':'common/echarts',
					'echarts/chart/bar':'common/echarts',
					'echarts/chart/line':'common/echarts',
					'echarts/chart/pie':'common/echarts',
					'echarts/chart/funnel':'common/echarts'
				}
			});
			panel.getChartPanel(window.GlOBAL.TOOL.deParam().type);
		},
		getChartPanel:function(type){//图表
			$.ajax({
				url:'getChart.do',
				data:type,
				type:"post",
				dataType:"json",
				beforeSend: function(){
					if(status){
						$.Bstatus({html:'加载中...'});
					};
				},
				success:function(data){
					
				},
				error: function(data){
					//error
					/*
					$('.ui-status-container').remove();
					$.Bstatus({
						html:'服务器出错！',
						status:false,
						time:500
					});
					*/
					
					//true
					var data={
						param:{
							'1':{
								name:['中国工商银行外滩分行','中国建设银行外滩分行','招商银行光谷分行','上海银行','中国银行','北京银行','中国农业银行','民生银行'],
								list:[3201, 3022, 3011, 1242, 1390, 2330, 1320, 2572],
								picName:'家'
							},
							'2':{
								name:['上海宝山钢铁贸易有限公司','上海钢联运输有限公司','上海钢铁有限公司','上海欧冶金融有限公司'],
								list:[132330, 203332, 303431, 334324],
								picName:'笔'
							}
						}[type]
					};
					if(data.param!=null||data.param!=undefined){
						var param=[],str='',color=['#9fdc5d','#86cffc','#f56363','#e6d96c','#2cc5cb','#b5a2da','#54b6f7','#febb84'],n=0;
						for(var i=0;i<data.param.list.length;i++){
							param.push({value:data.param.list[i], name:data.param.name[i]});
							str+='<li><span style="background-color:'+color[i]+';"></span><em>'+data.param.name[i]+'</em>'+window.GlOBAL.TOOL.getFormatNumber(data.param.list[i])+'</li>'
							n+=data.param.list[i];
						};
						require(['echarts','echarts/chart/pie'],function(ec){
							$('.ui-chart-content').html('<div class="ui-chart-panel" id="ui-chart-content"></div><div class="ui-chart-title"><span>质押仓单('+data.param.picName+')</span>'+window.GlOBAL.TOOL.getFormatNumber(n)+'</div>');
							$('.ui-chart-legend').html('<ul>'+str+'</ul>');
							var myChart = ec.init(document.getElementById('ui-chart-content')); 
							var option = {
								color:color,
								tooltip : {
									show: false
								},
								toolbox: {
									show : false
								},
								calculable : false,
								series : [
									{
										name:'质押仓单',
										type:'pie',
										center:['50%','50%'],
										radius : ['50%', '80%'],
										data:param,
										itemStyle : {
											normal : {
												label : {
													show : false
												},
												labelLine : {
													show : false
												}
											}
										}
									}
								]
							};
							myChart.setOption(option); 		
						});
					}else{
						$('.ui-status-container').remove();
						$.Bstatus({
							html:'数据问题！',
							status:false,
							time:500
						});
					};
				}
			});
		}
	};	
	return panel;
});