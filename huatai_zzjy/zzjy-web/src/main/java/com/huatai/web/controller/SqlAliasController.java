package com.huatai.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fairyland.jdp.core.web.MediaTypes;
import com.fairyland.jdp.framework.security.authc.SessionContextUtils;
import com.fairyland.jdp.orm.Pager;
import com.huatai.web.model.SqlAlias;
import com.huatai.web.service.SqlAliasService;

/**
 * @description:
 * @author     ：沈从会
 * @datetime   : 2017年7月17日 下午2:24:40
 */
@Controller
@RequestMapping("admin/sqlAlias")
public class SqlAliasController {

	@Autowired
	private SqlAliasService sqlAliasService;
	

	@RequestMapping(value="index",method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView();
		model.setViewName("/admin/sqlAlias/index");
		return model;
	}

	@ResponseBody
	@RequestMapping(value = "list")
	public Pager<SqlAlias> getPager(Pager<SqlAlias> pager, SqlAlias record) {
		return sqlAliasService.findByPager(pager, record);
	}

	@RequestMapping(value = "create", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	public SqlAlias create(@Valid SqlAlias record) throws ParseException {
		record.setTrsId(1);
		record.setOpType("A");
		record.setCreatorId(SessionContextUtils.getLoginName().toString());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		record.setCreateTime(sdf.parse(sdf.format(new Date())));
		this.sqlAliasService.insert(record);
		return record;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public SqlAlias update(@Valid SqlAlias record, RedirectAttributes redirectAttributes) throws Exception {
		//User user = SessionContextUtils.getCurrentUser();
		//获得当前用户的id
		record.setCreateTime(null);
		record.setModifierId(SessionContextUtils.getLoginName().toString());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		record.setModifyTime(sdf.parse(sdf.format(new Date())));
		record.setOpType("U");
		this.sqlAliasService.updateByPrimaryKeySelective(record);
		return record;
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public void delete(Integer id) {
		SqlAlias record=new SqlAlias();
		record.setSaId(id);
		record.setOpType("D");
		record.setModifyTime(new Date());
		record.setModifierId(SessionContextUtils.getLoginName().toString());
		sqlAliasService.update(record);
	}
}
