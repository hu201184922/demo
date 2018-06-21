package com.huatai.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fairyland.jdp.core.web.MediaTypes;
import com.fairyland.jdp.framework.dictionary.domain.DictionaryItem;
import com.fairyland.jdp.framework.dictionary.service.DictionaryService;
import com.fairyland.jdp.framework.security.authc.SessionContextUtils;
import com.fairyland.jdp.orm.Pager;
import com.huatai.web.model.Inter;
import com.huatai.web.model.InterField;
import com.huatai.web.model.QueryDim;
import com.huatai.web.service.InterFieldService;
import com.huatai.web.service.InterService;
import com.huatai.web.service.QueryDimService;


/**
 * @author 胡智辉
 * 2017年7月27日
 */
@Controller
@RequestMapping("admin/interField")
public class InterFieldController {

	@Autowired
	private InterFieldService interFieldService;
	
	@Autowired
	private InterService interService;
	
	@Autowired
	private DictionaryService dictionaryService;
	
	@Autowired
	private QueryDimService queryDimService;
	
	@RequestMapping(value="index",method = RequestMethod.GET)
	public ModelAndView index(String interId,ModelAndView view) {
		List<Inter> interList= interService.findInterList();
		List<DictionaryItem> dictItem = dictionaryService.findByDictionaryCodeOrderByCode("FIELD_TYPE");
		Inter inter = interService.findInterFieldByInterId(interId).get(0);
		List<QueryDim> queryDimCodes=queryDimService.findByQueryDimCode();
		view.addObject("queryDimCodes", queryDimCodes);
		view.addObject("dictItem", dictItem);
		view.addObject("inters", interList);
		view.addObject("inter", inter);
		view.setViewName("/admin/interField/index");
		return view;
	}

	@ResponseBody
	@RequestMapping(value = "list")
	public Pager<InterField> getPager(Pager<InterField> pager, Inter record) {
		return interFieldService.findByPager(pager, record);
	}

	@RequestMapping(value = "create", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	public int create(@Valid InterField record) throws ParseException {
		if(record.getFieldId()!=null){
			try {
				InterField result=update(record);
				if(result==null){
					return 0;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return -1;
			}
			return 1;
			
		}
		record.setOpType("A");
		record.setCreatorId(SessionContextUtils.getLoginName().toString());
		record.setCreateTime(new Date());
		record.setModifyTime(new Date());
		record.setModifierId(SessionContextUtils.getLoginName().toString());
		int result=interFieldService.insert(record);
		return result;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public InterField update(@Valid InterField record) throws Exception {
		//User user = SessionContextUtils.getCurrentUser();
		//获得当前用户的id
//		record.setCreateTime(null);
		record.setModifierId(SessionContextUtils.getLoginName().toString());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		record.setModifyTime(sdf.parse(sdf.format(new Date())));
		record.setOpType("U");
		interFieldService.updateByPrimaryKeySelective(record);
		return record;
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public int delete(@Valid InterField record) {
		record.setOpType("D");
		record.setModifyTime(new Date());
		record.setModifierId(SessionContextUtils.getLoginName().toString());
		int result=interFieldService.updateByPrimaryKeySelective(record);
		return result;
	}
}
