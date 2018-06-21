package com.huatai.web.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.fairyland.jdp.framework.security.authc.SessionContextUtils;
import com.fairyland.jdp.orm.Pager;
import com.fairyland.jdp.orm.util.common.StringUtil;
import com.huatai.web.model.DictItem;
import com.huatai.web.model.TarInitSql;
import com.huatai.web.model.Target;
import com.huatai.web.service.TarInitSqlService;

@Controller
@RequestMapping("admin/tarInitSql")
public class TarInitSqlController {

	@Autowired
	private TarInitSqlService tarInitSqlService;
	
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView();
		List<DictItem> funlist = this.tarInitSqlService.findFuncList();
		List<Target> subPlas = this.tarInitSqlService.findSubPlates();
		model.addObject("funlist", funlist);
		model.addObject("subPlas", subPlas);
		model.setViewName("/admin/tarInitSql/index");
		return model;
	}

	@ResponseBody
	@RequestMapping(value = "list")
	public Pager<TarInitSql> getPager(Pager<TarInitSql> pager, TarInitSql record) {
		return tarInitSqlService.findByPager(pager, record);
	}

	@ResponseBody
	@RequestMapping(value = "getTarBySub")
	public List<Target> getTarBySub(@RequestParam("subPlaId") String subPlaId) {
		return tarInitSqlService.getTarBySubId(subPlaId);
	}

	@RequestMapping(value = "goAddTarInit", method = RequestMethod.GET)
	public ModelAndView goAddTarInit() {
		ModelAndView model = new ModelAndView();
		List<DictItem> funlist = this.tarInitSqlService.findFuncList();
		List<Target> subPlas = this.tarInitSqlService.findSubPlates();
		model.addObject("funlist", funlist);
		model.addObject("subPlas", subPlas);
		model.setViewName("/admin/tarInitSql/add_Tarinit");
		return model;
	}

	@ResponseBody
	@RequestMapping(value = "saveRecords", method = RequestMethod.POST)
	public boolean saveRecords(String subpla, String target, String clist, String records) throws ParseException {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String loginId = SessionContextUtils.getLoginName().toString();
			Date now = sdf.parse(sdf.format(new Date()));
			String[] initlist = records.split("&");
			for (String init : initlist) {
				TarInitSql record = new TarInitSql();
				if(StringUtil.isNotNull(init)){
					String[] re = init.split("\\#");
					String groDim = re[0];
					String groDimDetail = re[1];
					String dateType = re[2];
					String groSql = re[3];
					record.setGroupType(groDim);
					record.setGroupDetailCode(groDimDetail);
					record.setDateType(dateType);
					record.setSqlCode(groSql);
				}
				record.setTargetCode(target);
				record.setOpType("A");
				record.setCreateTime(now);
				record.setModifyTime(now);
				record.setCreatorId(loginId);
				record.setModifierId(loginId);
				String[] c = clist.split(",");
				for (int i = 0; i < c.length ; i++) {
					record.setFunIds(c[i]);
					tarInitSqlService.insert(record);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public TarInitSql update(TarInitSql record) throws Exception {
		try {
			record.setSqlCode(java.net.URLDecoder.decode(record.getSqlCode(),"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		TarInitSql tis = tarInitSqlService.selectByPrimaryKey(record.getTisId());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		tis.setModifierId(SessionContextUtils.getLoginName().toString());
		tis.setModifyTime(sdf.parse(sdf.format(new Date())));
		tis.setOpType("U");
		tis.setGroupType(record.getGroupType());
		tis.setGroupDetailCode(record.getGroupDetailCode());
		tis.setSqlCode(record.getSqlCode());
		tis.setDateType(record.getDateType());
		tarInitSqlService.updateByPrimaryKey(tis);
		return record;
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public void delete(Integer id) {
		tarInitSqlService.deleteByPrimaryKey(id);
	}

}
