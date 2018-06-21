$.extend({
	initialBigdata:function (medical,accident,life,provide,child){
		//饼图
	/*	 $('#container').highcharts({
		        chart: {
		            plotBackgroundColor: null,
		            plotBorderWidth: null,
		            plotShadow: false,
		            type: 'pie'//new
		        },
		        title: {
//	                text: 'Browser market shares January, 2015 to May, 2015'
	            },
	            tooltip: {
	                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
	            },
	            plotOptions: {
	                pie: {
	                    allowPointSelect: true,
	                    cursor: 'pointer',
	                    dataLabels: {
	                        enabled: false
	                    },
	                   // showInLegend: true
	                }
	            },
		        series: [{
		           // type: 'pie',
		            name: 'Browser share',
	                colorByPoint: true,//new

		            data: [
		                 {
		                    name: '医疗',
		                    y: medical,
		                    color:'#ceeaee'
		               	 },
		        		 {
		                    name: '意外',
		                    y: accident,
		                    color:'#a1cb3d'
		               	 },
		          		 {
		                    name: '人寿',
		                    y: life,
		                    color:'#44b564'
		               	 },
		                {
		                    name: '养老',
		                    y: provide,
		                    color:'#eb6363'
		                },
		                {
		                    name: '少儿',
		                    y: child,
		                    color:'#eb6438'
		                }
		            ]
		        }]
		    });*/
		var myChart = echarts.init(document.getElementById('container'));
		option = {  
                title : {  
                   
                },  
                tooltip : {  
                    trigger: 'item',  
                    //formatter: "{a} <br/>{b} : {c} ({d}%)" 
                    formatter: "{b} : {c}%"
                },                 
                //calculable : true, 
                color:['#ceeaee','#a1cb3d','#44b564','#eb6363','#eb6438'],
                series : [  
                    {  
                        name:'Browser share',  
                        type:'pie',  
                        radius : '50%',  
                        data:[  
                              {
      		                    name: '医疗',
      		                    value: medical,       		                  
      		               	 },
      		        		 {
      		                    name: '意外',
      		                    value: accident,    		                   
      		               	 },
      		          		 {
      		                    name: '人寿',
      		                    value: life,   		                  
      		               	 },
      		                {
      		                    name: '养老',
      		                    value: provide,     		                   
      		                },
      		                {
      		                    name: '少儿',
      		                    value: child,     		                  
      		                }
                        ],
                        itemStyle:{ 
                            normal:{ 
                                  label:{ 
                                    show: true, 
                                    formatter: '{b} : {d}%' 
                                  }, 
                                  labelLine :{show:true} 
                                } 
                            } 
                    }  
                ]  
            };  
		 myChart.setOption(option);   
	}
});