/**
 * 销售轨迹
 */
var saleTrack = (function($){
	var sales = {
		options:{
			plan:{},
			saleTraclkURL:ctx+'/customer/plan/salesTrack',
		},
		init:function(){
			var self = this;
			
			self.event();
			
			//初始化数据
			self.saleTrackList();
		},
		event:function(){
			var self = this;
			$('#plans-sel').on('change',function(){
				
				self.clearUi();
				
				self.options.plan = $('#plans-sel option:selected').data('plan');
				//改变计划的时候获取任务轨迹详情
				self.saleTrackList();
			});
		},
		clearUi:function(){
			$('#track').html('');
			$('#track-detail').html('');
		},
		saleTrackList:function(){
			var self = this,plan = self.options.plan;
			if($.isEmptyObject(plan)){
				plan = self.options.plan = $('#plans-sel option:selected').data('plan');
			}
			if(!plan) return ;
			
			$.ajax({
				url:self.options.saleTraclkURL,
				type:'post',
				dataType:'json',
				data:{cusPlanId:plan.cusPlanId},
				success:function(data){
					 
					if(data){

						//获取轨迹模板
						var trackTemplete = template('track-template',data);
						$('#track').html(trackTemplete);
						
						var salesTrack = data.salesTrack,
							ops = salesTrack.ops,
							pcs = salesTrack.pcs,
							pds = salesTrack.pds,
							//pcs = salesTrack.pcs,
							sales = [];
						
						if(ops.length > 0){
							$.each(ops,function(index,item){
								item.planName = plan.planName;
								item.cusName = plan.cusName;
								sales.push(item);
							});
						}
						if(pcs.length > 0){
							$.each(pcs,function(index,item){
								item.planName = plan.planName;
								item.cusName = plan.cusName;
								sales.push(item);
							});
						}
						if(pds.length > 0){
							$.each(pds,function(index,item){
								item.planName = plan.planName;
								item.cusName = plan.cusName;
								sales.push(item);
							});
						}
						/*if(pcs.length > 0){
							$.each(pcs,function(index,item){
								item.planName = plan.planName;
								item.cusName = plan.cusName;
								sales.push(item);
							});
						}*/
						
						//获取任务列表模板
						var trackDetails = template('track-detail-template',{saleTracks:sales})
					
						$('#track-detail').html(trackDetails);
					}
					
				},
			});
			
			
		},
	};
	
	return sales;
	
})(jQuery);