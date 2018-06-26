package com.tools.mode.flyweight;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class ObjectFactory<T> {
	private static Map<String, Object> objectMap = new HashMap<>();

	private static Object object = null;

	public static Object getObject(Class<? extends Object> clazz, String choice) {
		object = objectMap.get(choice);

		if (object == null) {
			try {
				Class<?> cls = Class.forName(clazz.getName());
				Constructor<? extends Object> constructor = cls.getConstructor(String.class);
				object = constructor.newInstance(choice);
				objectMap.put(choice, object);
				System.out.println("Creating circle of color : " + choice);
			} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException
					| IllegalArgumentException | InvocationTargetException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return object;
	}
}
