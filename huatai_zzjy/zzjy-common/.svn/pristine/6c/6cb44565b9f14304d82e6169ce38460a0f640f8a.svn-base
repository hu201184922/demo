package com.ehuatai.conts;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


/**
 * 区域/模块
 * @author ctl
 *
 */
@PropertySource(value="classpath:module.properties")
@Configuration
public class Module {
	
	//以下是分析管理的区域code
	@Value("${template.analysis.common}")
	public String analysisCommon;			//常用指标区域
	
	@Value("${template.analysis.main}")
	public String analysisMain;				//一级指标区域
	
	@Value("${template.analysis.sub}")
	public String analysisSub;				//二级指标区域
	
	@Value("${template.analysis.dist}")
	public String analysisDist;				//排名区域
	
	@Value("${template.analysis.org}")
	public String analysisOrg;				//列表区域
	
	@Value("${template.analysis.spec}")
	public String analysisSpec;				//特殊区域
	
	@Value("${template.analysis.torg}")
	public String analysisTorg;				//保费部特殊区域
	
	
	//以下是首页固定指标区域代码
	@Value("${template.index.fixed.main}")
	public String indexFixedMain;			//核心指标统计区域

	@Value("${template.index.fixed.month}")
	public String indexFixedMonth;			//当月指标统计区域

	@Value("${template.index.fixed.map}")
	public String indexFixedMap;			//地图区域

	@Value("${template.index.fixed.honor}")
	public String indexFixedHonor;			//光荣榜区域

	@Value("${template.index.fixed.rank}")
	public String indexFixedRank;			//排行榜区域

	@Value("${template.index.fixed.monthlist}")
	public String indexFixedMonthlist;		//当月指标统计列表区域

	@Value("${template.index.fixed.notice}")
	public String indexFixedNotice;			//公告区域


	//以下是板块区域代码
	@Value("${template.block.all}")
	public String blockAll; 		//板块总区域

	@Value("${template.block.down}")
	public String blockDown; 		//板块下钻区域
	
	private Map<String, String>moduleAnalysisMap = new HashMap<String,String>();
	
	private void initModuleAnalysisMap() {
		moduleAnalysisMap.put(analysisCommon, "common");
		moduleAnalysisMap.put(analysisMain, "main");
		moduleAnalysisMap.put(analysisSub, "sub");
		moduleAnalysisMap.put(analysisDist, "dist");
		moduleAnalysisMap.put(analysisOrg, "org");
		moduleAnalysisMap.put(analysisSpec, "spec");
		moduleAnalysisMap.put(analysisTorg, "torg");
	}
	/**
	 * 分析管理区域代码转换
	 * @param modules
	 * @return
	 */
	public List<String> moduleConvert(List<String>modules) {
		if(moduleAnalysisMap.isEmpty()) initModuleAnalysisMap();
		
		List<String>converts = new ArrayList<String>();
		if(modules !=null && !modules.isEmpty()) {
			modules.forEach(module -> {
				String _module = moduleAnalysisMap.get(module);

				if(_module!=null && !"".equals(_module)) {
					converts.add(moduleAnalysisMap.get(module));
				}
			});
		}
		return converts;
	}
}
