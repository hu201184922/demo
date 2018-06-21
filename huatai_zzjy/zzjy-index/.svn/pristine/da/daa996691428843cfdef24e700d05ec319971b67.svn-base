package com.fairyland.jdp.framework.scanner.service;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fairyland.jdp.framework.security.domain.Resource;
import com.fairyland.jdp.framework.security.service.ResourceService;
import com.fairyland.jdp.framework.spring.SpringContextUtil;
import com.fairyland.jdp.orm.util.reflect.ClassUtil;

/**
 * url资源扫描器
 * 
 * @author XiongMiao
 * 
 */
@Service
@Transactional
public class URLScannerHandlerImpl implements URLScannerHandler{
	private Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private ResourceService resourceService;
	
	private String rootURL ;

	public URLScannerHandlerImpl() {
		super();
		initRootURL();
	}
	private void initRootURL(){
//		System.out.println("this.getClass().getClassLoader().getResource(\".\")="+this.getClass().getClassLoader().getResource("."));
		if(this.getClass().getClassLoader().getResource(".")==null)
			this.rootURL = null;
		else{
			String rootPath = this.getClass().getClassLoader().getResource(".").getPath();
			this.rootURL = rootPath.substring(1);
		}
	}
	
//	public static void main(String[] args) {
//		new URLScannerHandlerImpl().scanner();
//	}

	public void scanner(String basePackage) {
		String basePackagePath = rootURL + basePackage.replaceAll("\\.", "/");
		parsePackage(basePackagePath);
	}

	public void scanner() {
		Map<String, Object> ctrls =SpringContextUtil.getApplicationContext().getBeansWithAnnotation(RestController.class);
		Method mtd = null;
		String rootPath = null, subPath = null,path = null;
		for (Iterator<Map.Entry<String, Object>> iter = ctrls.entrySet().iterator(); iter.hasNext();) {
			Map.Entry<String, Object> entry = iter.next();
			Class<?> clazz = (Class<?>) entry.getValue().getClass();
			{
				RequestMapping mapping = ClassUtil.getClassGenricType(clazz, RequestMapping.class);
				if (mapping != null && mapping.value().length > 0) {
					rootPath = mapping.value()[0].substring(0,1).equals("/")?mapping.value()[0].substring(1):mapping.value()[0];
					Method[] mtds = clazz.getMethods();
					if (mtds != null) {
						for (int i = 0; i < mtds.length; i++) {
							mtd = mtds[i];
							{
								RequestMapping methodmapping = mtd.getAnnotation(RequestMapping.class);
								if (methodmapping != null && methodmapping.value().length > 0) {
									subPath = methodmapping.value()[0];
									path = rootPath +"/"+ subPath;
									path = path.replaceAll("//", "/");
									path = path.replaceAll("//", "/");
									if(!path.startsWith("/system/") //后台资源
										&& !path.contains("{") //含变量资源
									){
										saveResource(path);
									}
								}
							}
							{
								GetMapping methodmapping = mtd.getAnnotation(GetMapping.class);
								if (methodmapping != null && methodmapping.value().length > 0) {
									subPath = methodmapping.value()[0];
									path = rootPath +"/"+ subPath;
									path = path.replaceAll("//", "/");
									path = path.replaceAll("//", "/");
									if(!path.startsWith("/system/") //后台资源
										&& !path.contains("{") //含变量资源
									){
										saveResource(path);
									}
								}
							}
							{
								PostMapping methodmapping = mtd.getAnnotation(PostMapping.class);
								if (methodmapping != null && methodmapping.value().length > 0) {
									subPath = methodmapping.value()[0];
									path = rootPath +"/"+ subPath;
									path = path.replaceAll("//", "/");
									path = path.replaceAll("//", "/");
									if(!path.startsWith("/system/") //后台资源
										&& !path.contains("{") //含变量资源
									){
										saveResource(path);
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	protected void parsePackage(String basePackagePath){
		File file = new File(basePackagePath);
		File[] childFiles = file.listFiles();
		for (File childFile : childFiles) {
			if (childFile.isDirectory()) {
				parsePackage(childFile.getPath());
			} else {
				String childFilePath = childFile.getPath();
				if(childFilePath.endsWith(".class")){//.class file
					String className = 
							childFilePath.substring(rootURL.length(),childFilePath.lastIndexOf("."))
										.replace("\\", ".");
					
//					System.err.println(className);
					
					try {
						Class<?> clazz=Class.forName(className);
						List<String> urls = parseClass(clazz);
						for (String url : urls) {
							saveResource(url);
						}
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						log.error(e.getMessage());
						throw new RuntimeException(e);
					}
					
				}
				
			}
		}
	}

	protected List<String> parseClass(Class<?> clazz) {
		if (clazz.isAnnotationPresent(RestController.class)) { // 是否为一个controller ?
			String classUrl = "";
			if (clazz.isAnnotationPresent(RequestMapping.class)) {
				RequestMapping requestMapping_clazz = (RequestMapping) clazz
						.getAnnotation(RequestMapping.class);
				classUrl = requestMapping_clazz.value()[0];

				if (classUrl.startsWith("/")) { // 如果是"/" 的话 制空
					classUrl = classUrl.substring(1);
				}
			}
			List<String> urls = new ArrayList<String>();
			//解析方法的url
			Method[] ms = clazz.getDeclaredMethods();

			for (Method m : ms) {
				if (m.isAnnotationPresent(RequestMapping.class)) {
					RequestMapping requestMapping_method = m
							.getAnnotation(RequestMapping.class);
					String methodUrl = "";
					if(requestMapping_method.value().length>0){
						methodUrl = requestMapping_method.value()[0];
					}
					if(methodUrl.startsWith("/")){
						methodUrl = methodUrl.substring(1);
					}
					
					//保存url资源
					String url = classUrl+(StringUtils.isEmpty(methodUrl)?"":("/"+methodUrl));
//					System.err.println(url);
					urls.add(url);
				}
			}
			return urls;
		}
		
		return Collections.emptyList();
	}
	
	private void saveResource(String url){
		Resource resource = resourceService.getResourceByResString(url);
		if(resource == null){
			resource = new Resource();
			resource.setName(url);
			resource.setResString(url);
			resource.setResType(Resource.RES_TYPE_URL);
			resource.setEnabled(true);
			resource.setPerString(url.replaceAll("/", "."));
			resourceService.createResource(resource);
		}
		
	}
	
	public ResourceService getResourceService() {
		return resourceService;
	}
	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

}
