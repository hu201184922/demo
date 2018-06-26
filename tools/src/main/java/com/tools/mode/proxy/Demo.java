package com.tools.mode.proxy;

public class Demo {

	public static void main(String[] args) throws Exception {
		Image image = new ProxyImage("test_10mb.jpg");
		// 图像将从磁盘加载
		image.display();
		System.out.println("");
		// 图像将无法从磁盘加载
		image.display();
		Image images = new ProxyImage("test_11mb.jpg");
		images.display();
		System.out.println("");
	}

}
