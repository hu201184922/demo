package com.tools.mode.flyweight;

public class Demo {

	private static final String colors[] = { "Red", "Green", "Blue", "White", "Black" };

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			Circle circle = (Circle) ObjectFactory.getObject(Circle.class, getRandomColor());
			circle.setX(getRandomX());
			circle.setY(getRandomY());
			circle.draw();
		}
	}

	private static String getRandomColor() {
		return colors[(int) (Math.random() * colors.length)];
	}

	private static int getRandomX() {
		return (int) (Math.random() * 100);
	}

	private static int getRandomY() {
		return (int) (Math.random() * 100);
	}
}
