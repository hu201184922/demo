package com.huatai.web.thrift;

import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fairyland.jdp.framework.util.PropsUtil;
import com.fairyland.jdp.thrift.common.pingservice.TPingService;
import com.fairyland.jdp.thrift.server.pingservice.impl.TPingServiceImpl;
import com.huatai.web.thrift.app.AppBkfwService;
import com.huatai.web.thrift.app.AppBkfwServiceImpl;
import com.huatai.web.thrift.app.AppLlqdService;
import com.huatai.web.thrift.app.AppLlqdServiceImpl;
import com.huatai.web.thrift.app.AppZtfxService;
import com.huatai.web.thrift.app.AppZtfxServiceImpl;
import com.huatai.web.thrift.app.AppZyfwService;
import com.huatai.web.thrift.app.AppZyfwServiceImpl;
import com.huatai.web.thrift.web.BkfwService;
import com.huatai.web.thrift.web.BkfwServiceImpl;
import com.huatai.web.thrift.web.DashbordService;
import com.huatai.web.thrift.web.DashbordServiceImpl;
import com.huatai.web.thrift.web.GjfwService;
import com.huatai.web.thrift.web.GjfwServiceImpl;
import com.huatai.web.thrift.web.LlqdService;
import com.huatai.web.thrift.web.LlqdServiceImpl;
import com.huatai.web.thrift.web.SsTargetService;
import com.huatai.web.thrift.web.SsTargetServiceImpl;
import com.huatai.web.thrift.web.ZtfxService;
import com.huatai.web.thrift.web.ZtfxServiceImpl;
import com.huatai.web.thrift.web.ZyfwService;
import com.huatai.web.thrift.web.ZyfwServiceImpl;

//服务端代理  
public class ThriftServerProxy {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	public void start() {
		new Thread() {
			@Override
			public void run() {
				try {
					TMultiplexedProcessor processor = new TMultiplexedProcessor();
					TServerTransport t = new TServerSocket(Integer.parseInt(PropsUtil.get("thrift.port")));
					TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(t).processor(processor));
					// 心跳服务，勿动
					processor.registerProcessor("TPingService",
							new TPingService.Processor<TPingService.Iface>(new TPingServiceImpl()));

					// 注册web端服务接口
					processor.registerProcessor("ZtfxService",
							new ZtfxService.Processor<ZtfxService.Iface>(new ZtfxServiceImpl()));
					processor.registerProcessor("LlqdService",
							new LlqdService.Processor<LlqdService.Iface>(new LlqdServiceImpl()));
					processor.registerProcessor("BkfwService",
							new BkfwService.Processor<BkfwService.Iface>(new BkfwServiceImpl()));
					processor.registerProcessor("GjfwService",
							new GjfwService.Processor<GjfwService.Iface>(new GjfwServiceImpl()));
					processor.registerProcessor("ZyfwService",
							new ZyfwService.Processor<ZyfwService.Iface>(new ZyfwServiceImpl()));
					processor.registerProcessor("DashbordService",
							new DashbordService.Processor<DashbordService.Iface>(new DashbordServiceImpl()));
					processor.registerProcessor("SsTargetService",
							new SsTargetService.Processor<SsTargetService.Iface>(new SsTargetServiceImpl()));

					// 注册app端服务接口
					processor.registerProcessor("AppBkfwService",
							new AppBkfwService.Processor<AppBkfwService.Iface>(new AppBkfwServiceImpl()));
					processor.registerProcessor("AppLlqdService",
							new AppLlqdService.Processor<AppLlqdService.Iface>(new AppLlqdServiceImpl()));
					processor.registerProcessor("AppZtfxService",
							new AppZtfxService.Processor<AppZtfxService.Iface>(new AppZtfxServiceImpl()));
					processor.registerProcessor("AppZyfwService",
							new AppZyfwService.Processor<AppZyfwService.Iface>(new AppZyfwServiceImpl()));

					server.serve();
				} catch (TTransportException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
}