package com.tools;

import static org.junit.Assert.*;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Test {

	@org.junit.Test
	public void test() {
		//fail("Not yet implemented");
		//int i=1/0;
		String expr = "(A1 && A2 && A3 && A4 && A5)";

        ScriptEngineManager manager = new ScriptEngineManager();

        ScriptEngine engine =manager.getEngineByName("js");
        Object result;
		try {
			result = engine.eval(expr);
			System.out.println(result);
		} catch (ScriptException e) {
			e.printStackTrace();
		}

		System.out.println("9999999999999");
	}

}
