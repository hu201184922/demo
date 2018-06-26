package com.tools.mode.factory;

public abstract class AbstractFactory {
	public abstract Object getShape(Class<? extends Shape> shape);
	
	public abstract Object getColor(Class<? extends Color> color);
}
