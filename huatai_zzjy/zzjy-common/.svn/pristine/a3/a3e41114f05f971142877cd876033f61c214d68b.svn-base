package com.ehuatai.thrift;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.ehuatai.thrift.ZtfxService.Client;
import com.ehuatai.thrift.util.ThriftUtil;

@RestController
@RequestMapping("/thrift")
public class ThriftClientTest {

	Logger log = Logger.getLogger(ThriftClientTest.class);

	@RequestMapping("/test")
	public void test() throws Exception {

		Client client = ThriftUtil.getService(ZtfxService.Client.class);

		ResponseBeanDto responseBeanDto02 = client.getFastMenu("");
		System.out.println(responseBeanDto02.toString());
		System.out.println("---------------------------------------------------------------");

		Map map01 = new HashMap();
		map01.put("org", "");
		map01.put("subject", "BFDC");
		map01.put("groupbyDate", "YEAR");
		String param01 = JSON.toJSONString(map01);
		ResponseBeanDto responseBeanDto03 = client.getCommonData(param01);
		System.out.println(responseBeanDto03.toString());
		System.out.println("---------------------------------------------------------------");

		Map map02 = new HashMap();
		map02.put("org", "");
		map02.put("subject", "BFDC");
		String param = JSON.toJSONString(map02);
		ResponseBeanDto responseBeanDto04 = client.getSubject(param);
		System.out.println(responseBeanDto04.toString());
		System.out.println("---------------------------------------------------------------");

		Map map03 = new HashMap();
		String[] target101 = new String[1];
		String[] filter01 = new String[1];
		filter01[0] = "GX";
		target101[0] = "BFDC_0101";
		map03.put("org", "");
		map03.put("subject", "BFDC");
		map03.put("target1", target101);
		map03.put("groupbyDate", "YEAR");
		map03.put("otherFilters", filter01);
		ResponseBeanDto responseBeanDto05 = client.getMainData(JSON.toJSONString(map03));
		System.out.println(responseBeanDto05.toString());
		System.out.println("---------------------------------------------------------------");

		Map map04 = new HashMap();
		String[] target201 = new String[1];
		String[] filter02 = new String[1];
		filter02[0] = "GX";
		target201[0] = "BFDC_0101";
		map04.put("org", "");
		map04.put("subject", "BFDC");
		map04.put("target2", target201);
		map04.put("level", "BFDC_01");
		map04.put("groupbyDate", "YEAR");
		map04.put("otherFilters", filter02);
		ResponseBeanDto responseBeanDto06 = client.getSubData(JSON.toJSONString(map04));
		System.out.println(responseBeanDto06.toString());
		System.out.println("---------------------------------------------------------------");

		Map map05 = new HashMap();
		String[] filter03 = new String[1];
		filter03[0] = "GX";
		map05.put("keywords", "");
		map05.put("subject", "BFDC");
		map05.put("target1", "BFDC_0101");
		map05.put("groupbyDate", "YEAR");
		map05.put("otherFilters", filter03);
		ResponseBeanDto responseBeanDto07 = client.getDistData(JSON.toJSONString(map05));
		System.out.println(responseBeanDto07.toString());
		System.out.println("---------------------------------------------------------------");

		Map map07 = new HashMap();
		String[] filter05 = new String[1];
		filter05[0] = "GX";
		map07.put("keywords", "");
		map07.put("subject", "BFDC");
		map07.put("target1", "BFDC_0101");
		map07.put("groupbyDate", "YEAR");
		map07.put("otherFilters", filter05);
		ResponseBeanDto responseBeanDto09 = client.getSpecData(JSON.toJSONString(map07));
		System.out.println(responseBeanDto09.toString());
		System.out.println("---------------------------------------------------------------");

		ThriftUtil.close(client);
	}

}
