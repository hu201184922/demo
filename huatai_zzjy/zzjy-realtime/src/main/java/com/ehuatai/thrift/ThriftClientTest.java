package com.ehuatai.thrift;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.ehuatai.thrift.ZyfwService.Client;
import com.ehuatai.thrift.util.ThriftUtil;

@RestController
@RequestMapping("/thrift")
public class ThriftClientTest {

	Logger log = Logger.getLogger(ThriftClientTest.class);

	@RequestMapping("/test")
	public void test() throws Exception {
		Client client = ThriftUtil.getService(ZyfwService.Client.class);
		Map map = new HashMap();
		map.put("role", "ROLE01");
		map.put("roleOrg", "1");
		map.put("roleDept", "");
		ResponseBeanDto responseBeanDto = client.fixedMenu(JSON.toJSONString(map));
		log.error(responseBeanDto.toString());
		ThriftUtil.close(client);
	}

}
