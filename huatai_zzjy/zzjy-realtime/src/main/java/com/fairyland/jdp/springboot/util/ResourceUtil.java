package com.fairyland.jdp.springboot.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.CollectionUtils;

public class ResourceUtil {
	private static Map<String,Resource[]> cacheMap = new HashMap<String,Resource[]>();
	public static Resource getResource(String resourcePath){
		ResourcePatternResolver resolver = (ResourcePatternResolver) new PathMatchingResourcePatternResolver();  
        //只加载一个绝对匹配Resource,且通过ResourceLoader.getResource进行加载  
        Resource resource=resolver.getResource(resourcePath);
        return resource;
	}
	public static Resource[] getResources(String urlPattern){
		Resource[] resources = new Resource[0];
		if(cacheMap.containsKey(urlPattern)){
			resources = cacheMap.get(urlPattern);
		}else{
			ResourcePatternResolver resolver = (ResourcePatternResolver) new PathMatchingResourcePatternResolver();  
			try {
				resources = (Resource[]) resolver.getResources(urlPattern);
				cacheMap.put(urlPattern, resources);
			} catch(FileNotFoundException e1){
				
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
        return resources;
	}
	public static Resource[] getResources(String... urlPatterns){
        List<Resource> list = new ArrayList<Resource>();
		for (String urlPattern : urlPatterns) {
			Resource[] resources = getResources(urlPattern);
			list.addAll(CollectionUtils.arrayToList(resources));
		}
        return list.toArray(new Resource[0]);
	}
}
