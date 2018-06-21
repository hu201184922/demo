package com.ehuatai.biz.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.ehuatai.biz.mapper.GdzbMapper;
import com.ehuatai.biz.mapper.SQLMapper;
import com.fairyland.jdp.framework.dictionary.domain.DictionaryItem;
import com.fairyland.jdp.framework.dictionary.service.DictionaryService;
import com.fairyland.jdp.orm.Pager;

@Service
public class SQLServiceImpl implements SQLService {
	@Autowired
	private DictionaryService dictionaryService;// 数据字典表

	@Autowired
	private SQLMapper sqlMapper;

	@Autowired
	private GdzbMapper gdzbMapper;

	@Override
	public List<Map<String, Object>> findBySQL(String sql) {

		return sqlMapper.findBySQL(sql);
	}

	@Override
	public Pager<Map<String, Object>> findPagerBySQL(Pager<Map<String, Object>> pager, String sql) {

		return sqlMapper.findPagerBySQL(pager, sql);
	}

	/**
	 * @功能 {固定指标清单}
	 * @作者 MaxBill
	 * @时间 2017年9月4日 下午3:37:45
	 */
	public Pager<Map<String, Object>> findGdqdData(String code, Pager<Map<String, Object>> pager, HashMap paramMap) {
		String roleOrg = paramMap.get("roleOrg").toString();
		String roleDept = paramMap.get("roleDept").toString();
		int roleOrgType = roleOrg.length();
		paramMap.put("roleOrgType", roleOrgType);
		pager.setTotalPage(0);
		pager.setTotalCount(0);
		Integer pageNo = pager.getCurrentPage();
		Integer pageSize = pager.getPageSize();
		List<Map<String, Object>> dataList = null;
		Integer jgtopnumber;
		switch (code) {
		case "R1301070001":
			dataList = this.gdzbMapper.R1301070001(paramMap);
			break;
		case "R1301070002":
			dataList = this.gdzbMapper.R1301070002(paramMap);
			break;
		case "R1301070003":
			dataList = this.gdzbMapper.R1301070003(paramMap);
			break;
		case "R1301070004":
			dataList = this.gdzbMapper.R1301070004(paramMap);
			break;
		case "R1301070005":
			dataList = this.gdzbMapper.R1301070005(paramMap);
			break;
		case "R1301070006":
			dataList = this.gdzbMapper.R1301070006(paramMap);
			break;
		case "R1301070007":
			dataList = this.gdzbMapper.R1301070007(paramMap);
			break;
		case "R1301070008":
			dataList = this.gdzbMapper.R1301070008(paramMap);
			break;
		case "R1301070009":
			dataList = this.gdzbMapper.R1301070009(paramMap);
			break;
		case "R1301070010":
			dataList = this.gdzbMapper.R1301070010(paramMap);
			break;
		case "R1301070011":
			dataList = this.gdzbMapper.R1301070011(paramMap);
			break;
		case "R1301070012":
			dataList = this.gdzbMapper.R1301070012(paramMap);
			break;
		case "R1301070013":
			dataList = this.gdzbMapper.R1301070013(paramMap);
			break;
		case "R1301070014":
			dataList = this.gdzbMapper.R1301070014(paramMap);
			break;
		case "R1301070015":
			dataList = this.gdzbMapper.R1301070015(paramMap);
			break;
		case "R1301070016":
			dataList = this.gdzbMapper.R1301070016(paramMap);
			break;
		case "R1301010001":
			dataList = this.gdzbMapper.R1301010001(paramMap);
			break;
		case "R1301010002":
			dataList = this.gdzbMapper.R1301010002(paramMap);
			break;
		case "R1301010003":
			dataList = this.gdzbMapper.R1301010003(paramMap);
			break;
		case "R1301010004":
			dataList = this.gdzbMapper.R1301010004(paramMap);
			break;
		// 三级机构标保TOP
		case "R1301010005":
			switch (roleOrgType) {
			case 3:
				String provcomcode = roleOrg;
				paramMap.put("provcomcode", provcomcode);
				break;				
            case 5:
            	String countcomcode = roleOrg;
            	paramMap.put("countcomcode", countcomcode);
				break;	
			default:
				break;
			}
			if (!StringUtils.isEmpty(paramMap.get("top").toString())) {
				String top = paramMap.get("top").toString();
				jgtopnumber = Integer.parseInt(top);
				paramMap.put("jgtopnumber", jgtopnumber);
			} else {
				DictionaryItem JG_TOP3 = dictionaryService.findByDictCodeAndDictItemCode("JG_TOP", "JG_TOP3");
				if (!StringUtils.isEmpty(JG_TOP3.getDescript())) {
					String descript = JG_TOP3.getDescript();
					jgtopnumber = Integer.parseInt(descript);
					paramMap.put("jgtopnumber", jgtopnumber);
				} else {
					jgtopnumber = 60;
					paramMap.put("jgtopnumber", jgtopnumber);
				}
			}
			dataList = this.gdzbMapper.R1301010005(paramMap);
			break;
		// 四级机构标保TOP
		case "R1301010006":
			switch (roleOrgType) {
			case 3:
				String provcomcode = roleOrg;
				paramMap.put("provcomcode", provcomcode);
				break;				
            case 5:
            	String countcomcode = roleOrg;
            	paramMap.put("countcomcode", countcomcode);
				break;
            case 10:
            	if(roleDept.equals("130107")||roleDept.equals("101402")) {
            		break;
            	}else {
            		String costcenterstdcode = roleOrg;
                	paramMap.put("costcenterstdcode", costcenterstdcode);
            	}          	
            	break;
			default:
				break;
			}
			if (!StringUtils.isEmpty(paramMap.get("top").toString())) {
				String top = paramMap.get("top").toString();
				jgtopnumber = Integer.parseInt(top);
				paramMap.put("jgtopnumber", jgtopnumber);
			} else {
				DictionaryItem JG_TOP4 = dictionaryService.findByDictCodeAndDictItemCode("JG_TOP", "JG_TOP4");
				if (!StringUtils.isEmpty(JG_TOP4.getDescript())) {
					String descript = JG_TOP4.getDescript();
					jgtopnumber = Integer.parseInt(descript);
					paramMap.put("jgtopnumber", jgtopnumber);
				} else {
					jgtopnumber = 100;
					paramMap.put("jgtopnumber", jgtopnumber);
				}
			}
			dataList = this.gdzbMapper.R1301010006(paramMap);
			break;
		case "R1301050001":
			List<String> channelList01 = (List<String>) paramMap.get("channel");
			if (null != channelList01 && !channelList01.isEmpty()) {
				String channlTemp = "";
				for (String channlStr : channelList01) {
					channlTemp = "'" + channlStr + "', ";
				}
				paramMap.put("channel", channlTemp.substring(0, channlTemp.length() - 2));
			}
			dataList = this.gdzbMapper.R1301050001(paramMap);
			break;
		case "R1301050002":
			List<String> channelList02 = (List<String>) paramMap.get("channel");
			if (null != channelList02 && !channelList02.isEmpty()) {
				String channlTemp = "";
				for (String channlStr : channelList02) {
					channlTemp = "'" + channlStr + "', ";
				}
				paramMap.put("channel", channlTemp.substring(0, channlTemp.length() - 2));
			}
			dataList = this.gdzbMapper.R1301050001(paramMap);
			break;
		case "R1301050003":
			List<String> channelList03 = (List<String>) paramMap.get("channel");
			if (null != channelList03 && !channelList03.isEmpty()) {
				String channlTemp = "";
				for (String channlStr : channelList03) {
					channlTemp = "'" + channlStr + "', ";
				}
				paramMap.put("channel", channlTemp.substring(0, channlTemp.length() - 2));
			}
			dataList = this.gdzbMapper.R1301050001(paramMap);
			break;
		}
		pager.setPageItems(dataList);
		return pager;
	}

}
