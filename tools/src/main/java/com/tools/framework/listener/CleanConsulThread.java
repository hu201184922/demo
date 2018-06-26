package com.tools.framework.listener;

import com.tools.framework.utils.ConsulUtil;
import com.tools.framework.utils.PropsUtil;

public class CleanConsulThread extends Thread{
	
	@Override
	public void run() {
		try {
			//启动5分钟后开始清理consul
			Thread.sleep(300000);
//			Thread.sleep(1);
			String serviceName = PropsUtil.get("spring.application.name");
			ConsulUtil.removeDeadInstanceOfService(serviceName);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		CleanConsulThread thread = new CleanConsulThread();
		thread.run();
	}
}
