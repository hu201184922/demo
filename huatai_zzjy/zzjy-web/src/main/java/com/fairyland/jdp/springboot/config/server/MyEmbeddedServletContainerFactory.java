package com.fairyland.jdp.springboot.config.server;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.stereotype.Component;

@Component()  
public class MyEmbeddedServletContainerFactory extends TomcatEmbeddedServletContainerFactory  
{  
	@Value("${server.tomcat.max-connections}")
    private Integer maxConnctions;
	
	@Value("${server.tomcat.max-threads}")
	private Integer maxThreads;
	
    protected void customizeConnector(Connector connector)  
    {  
        super.customizeConnector(connector);  
        Http11NioProtocol protocol = (Http11NioProtocol)connector.getProtocolHandler();  
        //设置最大连接数  
        protocol.setMaxConnections(maxConnctions);  
        //设置最大线程数  
        protocol.setMaxThreads(maxThreads);  
        protocol.setConnectionTimeout(30000);  
    }  
}  