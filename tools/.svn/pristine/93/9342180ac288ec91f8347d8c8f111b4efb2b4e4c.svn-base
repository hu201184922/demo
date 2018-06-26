package com.tools.mode.factory;

public class ColorFactory extends AbstractFactory{

	Object obj=null;
	@Override
	public Object getShape(Class<? extends Shape> clazz) {
		try {
			if(clazz == null){
				return null;
			}
			obj = Class.forName(clazz.getName()).newInstance();
			return obj;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Object getColor(Class<? extends Color> clazz) {
		try {
			if(clazz == null){
				return null;
			}
			obj = Class.forName(clazz.getName()).newInstance();
			return obj;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
