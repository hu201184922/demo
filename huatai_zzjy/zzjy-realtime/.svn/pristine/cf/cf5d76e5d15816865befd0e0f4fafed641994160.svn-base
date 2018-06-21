package com.fairyland.jdp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

//@ImportResource(locations = "classpath:applicationContext-cache.xml")
@SpringBootApplication(scanBasePackages = {"com.ehuatai","com.fairyland.jdp","com.fairyland.bpm","com.metlife.ezt","com.fulan.demo"})
@EnableFeignClients(basePackages={"com.example"}) 
//@EnableEurekaClient
@EnableDiscoveryClient
public class ApplicationMain extends SpringBootServletInitializer {
	@Override  
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {  
        return application.sources(ApplicationMain.class);  
    }
	
	public static void main(String[] args) {
//		System.setProperty("http.proxySet", "true");    //设置使用网络代理  
//		System.setProperty("http.proxyHost", "127.0.0.1");  //设置代理服务器地址  
//		System.setProperty("http.proxyPort", "8888");
		SpringApplication.run(ApplicationMain.class, args);
	}

//	@Bean  
//    public FilterRegistrationBean filterRegistrationBean() {  
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean();  
//        InitCORSFilter httpBasicFilter = new InitCORSFilter();  
//        registrationBean.setFilter(httpBasicFilter);  
//        List<String> urlPatterns = new ArrayList<String>();  
//        urlPatterns.add("/**");  
//        registrationBean.setUrlPatterns(urlPatterns);  
//        return registrationBean;  
	// }
}
