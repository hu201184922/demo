/** 
 * CopyRright © 2013 上海复深蓝信息技术有限公司
 * Homepage：http://www.fulan.com.cn/                         
 * Project:Fairyland-JDP                                      
 * Module ID: framework   
 * Comments:                                         
 * JDK version used: JDK1.6                            
 */
package com.fairyland.jdp.framework.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

/**
 * @Description: TODO
 * @author codyzeng
 * @email zengyuekang@fulan.com.cn
 * @date 2013年9月6日 上午9:27:04
 * @version V1.0
 */
public class FreemarkerUtil {
	private Logger log = LoggerFactory.getLogger(getClass());
    private final String CONF_FILE = "freemarker.properties";

    static private FreemarkerUtil util = null;

    static private Configuration cfg = null;

    private FreemarkerUtil() {
	init();
    }

    static public FreemarkerUtil getInstance() {
	if (util == null) {
	    util = new FreemarkerUtil();
	}
	return util;
    }

    private void init() {
	try {
	    cfg = new Configuration();
	    cfg.setObjectWrapper(new DefaultObjectWrapper());
	    initTemplateLoader();
	    // 加载并设置freemarker.properties
	    Properties prop = loadFreemarkerProps();
	    cfg.setSettings(prop);
	} catch (Exception e) {
	    log.error(e.getMessage());
	}
    }

    private void initTemplateLoader() throws Exception {
	setTemplateLoader(null);
    }

    public void setTemplateLoader(String path) throws Exception {
	List<TemplateLoader> loaderList = new ArrayList<TemplateLoader>();
	if (StringUtils.isNotEmpty(path)) {
	    FileTemplateLoader ftl = new FileTemplateLoader(new File(path));
	    loaderList.add(ftl);
	}
	ClassTemplateLoader ctl = new ClassTemplateLoader(getClass(), "/");
	loaderList.add(ctl);

	TemplateLoader[] loaders = new TemplateLoader[loaderList.size()];
	loaderList.toArray(loaders);
	MultiTemplateLoader mtl = new MultiTemplateLoader(loaders);
	cfg.setTemplateLoader(mtl);
    }

    private Properties loadFreemarkerProps() throws Exception {
	Properties props = new Properties();
	InputStream is = Thread.currentThread().getContextClassLoader()
		.getResourceAsStream(CONF_FILE);
	props.load(is);

	return props;
    }

    public String render(String template, Object params) throws Exception {
	ByteArrayOutputStream bytes = new ByteArrayOutputStream();
	OutputStreamWriter writer = new OutputStreamWriter(bytes,
		cfg.getDefaultEncoding());

	write(template, params, writer);
	String outstring = new String(bytes.toByteArray(),
		cfg.getDefaultEncoding());
	writer.close();
	return outstring;
    }

    public String evaluate(String instring, Object params) throws Exception {
	Template temp = new Template(null, new StringReader(instring), cfg);
	Writer out = new StringWriter();
	temp.process(params, out);
	return out.toString();
    }

    public void write(String template, Object params, Writer out)
	    throws Exception {
	Template temp = cfg.getTemplate(template);
	temp.setEncoding("UTF-8");
	temp.process(params, out);
	out.flush();
    }
}
