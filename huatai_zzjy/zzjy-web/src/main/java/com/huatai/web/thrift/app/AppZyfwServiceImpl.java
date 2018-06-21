package com.huatai.web.thrift.app;

import java.util.Date;

import org.apache.thrift.TException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fairyland.jdp.framework.util.SpringUtil;
import com.huatai.web.thrift.bean.ResponseBeanDto;
import com.huatai.web.thrift.service.SubjectAnalysisService;
import com.huatai.web.utils.DateUtil;

public class AppZyfwServiceImpl implements AppZyfwService.Iface {

	/**
	 * @功能 {月度指标统计接口}
	 * @作者 MaxBill
	 * @时间 2017年8月2日 下午6:45:23
	 */
	public ResponseBeanDto countMonthTarget(String json) throws TException {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			SubjectAnalysisService subjectAnalysisService = SpringUtil.getBean(SubjectAnalysisService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String role = jsonObject.getString("role");
			String by = jsonObject.getString("by");
			String code = jsonObject.getString("code");
			String parentTarget = jsonObject.getString("parentTarget");
			// 是否个业部
			if (code.equals("")) {

			} else {

			}
			// 成功响应处理
			responseBeanDto.setJson(JSON.toJSONString(null));
			responseBeanDto.setErrCode("200");
			responseBeanDto.setErrMsgs("");
			responseBeanDto.setSuccess("true");
		} catch (Exception e) {
			// 异常响应处理
			e.printStackTrace();
			responseBeanDto.setJson("");
			responseBeanDto.setErrCode("500");
			responseBeanDto.setErrMsgs(e.toString());
			responseBeanDto.setSuccess("false");
		}
		responseBeanDto.setTimeStamp(DateUtil.formatDate(new Date(), DateUtil.FULL_DATE_TIME_FORMAT));
		return responseBeanDto;
	}

	/**
	 * @功能 {月度指标列表}
	 * @作者 MaxBill
	 * @时间 2017年8月2日 下午6:45:28
	 */
	public ResponseBeanDto listMonthTarget(String json) throws TException {
		return null;
	}

}
