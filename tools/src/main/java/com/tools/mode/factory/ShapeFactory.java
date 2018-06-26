package com.tools.mode.factory;

public class ShapeFactory extends AbstractFactory{

	Object obj=null;
	@Override
	public Object getShape(Class<? extends Shape> clazz) {
		try {
			if(clazz == null){
				return null;
			}
			return Class.forName(clazz.getName()).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Object getColor(Class<? extends Color> clazz) {
		if(clazz == null){
			return null;
		}
		try {
			return Class.forName(clazz.getName()).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
