package com.fairyland.jdp.framework.service;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fairyland.jdp.orm.util.common.ObjectUtil;
import com.fairyland.jdp.orm.util.common.StringUtil;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateHashModel;

public class FreemarkerService
{
    private static final Log log = LogFactory.getLog(FreemarkerService.class);
	
	private Map<String,Object> utils = new HashMap<String, Object>();
	private String encoding = "UTF-8";
	private boolean cacheEnabled = true;
	
	private Map<String,Template> templates = new HashMap<String, Template>();
	
	private Template getTemplate(String directory,String templateName) throws IOException {
		String key = directory.concat("/").concat(templateName);
		if(!templates.containsKey(key)){
			Configuration cfg = new Configuration();
	        cfg.setObjectWrapper(new DefaultObjectWrapper());
	        cfg.setDirectoryForTemplateLoading(new File(directory));
	        cfg.setEncoding(cfg.getLocale(),encoding);
	        templates.put(key, cfg.getTemplate(templateName));
		}
		return templates.get(key);
	}
	
	private Template getTemplate(Class<?> classTemplate, String pathPrefix, String templateName) throws IOException{
		String key = classTemplate.getName().concat("/").concat(pathPrefix).concat("/").concat(templateName);
		if(!templates.containsKey(key)||!cacheEnabled){
			Configuration cfg = new Configuration();
	        cfg.setObjectWrapper(new DefaultObjectWrapper());
	        cfg.setClassForTemplateLoading(classTemplate,pathPrefix);
	        cfg.setEncoding(cfg.getLocale(),encoding);
	        templates.put(key, cfg.getTemplate(templateName));
		}
		return templates.get(key);
	}
	
	public void addUtil(String utilName,Object util){
		this.utils.put(utilName, util);
	}
	
	public void setUtils(Map<String, Object> utils) {
		this.utils = utils;
	}

	/**
	 * 
	 * @param data	初始数据
	 * @param directory		目录
	 * @param templateName	模板文件名
	 * @param writer		
	 * @throws TemplateException
	 * @throws IOException
	 */
	public void writer(Object data,String directory,String templateName,Writer out){
		try{
			Template t = getTemplate(directory,templateName);
	        Map<String,Object> rootMap = ObjectUtil.toMap(data);
	        for(Map.Entry<String,Object> entry : utils.entrySet()){
	        	if(entry.getValue() instanceof String){
	        		rootMap.put(entry.getKey(),useStaticPackage(StringUtil.nullValue(entry.getValue())));
	        	}else{
	        		rootMap.put(entry.getKey(), entry.getValue());
	        	}
	        }
			t.process(rootMap, out);
		} catch (TemplateException e) {
			log.error(e);
		} catch (IOException e) {
			log.error(e);
		} finally{
    	//IOUtils.closeQuietly(out);
    }		
	}

	/**
	 * 
	 * @param data	初始数据
	 * @param classTemplate 通过class指定目录位置
	 * @param pathPrefix	附加目录
	 * @param templateName  模板文件名
	 * @param encoding		编码格式
	 * @param writer		
	 * @throws Exception
	 */
	public void writer(Object data,Class<?> classTemplate,String pathPrefix,String templateName,Writer out){
		try{
			Template t = getTemplate(classTemplate,pathPrefix,templateName);
	        Map<String,Object> rootMap = ObjectUtil.toMap(data);
	        for(Map.Entry<String,Object> entry : utils.entrySet()){	        
	        	if(entry.getValue() instanceof String){
	        		rootMap.put(entry.getKey(),useStaticPackage(StringUtil.nullValue(entry.getValue())));
	        	}else{
	        		rootMap.put(entry.getKey(), entry.getValue());
	        	}
	        }
			t.process(rootMap, out);
		} catch (TemplateException e) {
			log.error(e);
		} catch (IOException e) {
			log.error(e);
		} finally{
    	//IOUtils.closeQuietly(out);
    }		
	}
	
	/**
	 * 生成Freemarker 静态方法类
	 * @param packageName	为类的全路径
	 * @return
	 */
	protected TemplateHashModel useStaticPackage(String packageName) {
		try {
			BeansWrapper wrapper = BeansWrapper.getDefaultInstance();
			TemplateHashModel staticModels = wrapper.getStaticModels();
			TemplateHashModel fileStatics = (TemplateHashModel) staticModels.get(packageName);
			return fileStatics;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}
	
	public void setCacheEnabled(boolean cacheEnabled) {
		this.cacheEnabled = cacheEnabled;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

}
