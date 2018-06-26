package com.tools.mode.factory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Tests {
	@Test
	public static void main(String[] args) {
		AbstractFactory factory = FactoryProducer.getFactory("Color");
		System.out.println("=============");
		Color color = (Green)factory.getColor(Green.class);
		color.fill();
		System.out.println("==============");
	}

}
