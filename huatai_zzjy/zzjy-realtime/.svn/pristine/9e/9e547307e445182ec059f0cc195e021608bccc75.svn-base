package com.fairyland.jdp.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ehuatai.realtime.mapper.dma.PersonInsuredPremiumMapper;

@RestController
@RequestMapping("test")
public class TestController {
	@Autowired
	private PersonInsuredPremiumMapper personInsuredPremiumMapper;
	@RequestMapping("test1")
	public String test1(){
		List list = personInsuredPremiumMapper.selectByExample(null);
		return String.valueOf(list.size());
	}
}
