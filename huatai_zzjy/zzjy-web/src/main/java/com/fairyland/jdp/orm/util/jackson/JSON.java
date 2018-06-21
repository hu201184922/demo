package com.fairyland.jdp.orm.util.jackson;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.regex.Matcher;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fairyland.jdp.orm.util.common.DateUtil;
import com.fairyland.jdp.orm.util.common.ScriptHelp;
import com.fairyland.jdp.orm.util.regexp.RegexpUtil;




public class JSON {
	private static Logger log = LoggerFactory.getLogger(JSON.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final RegexpUtil.ReplaceCallBack replaceCallBack = new RegexpUtil.AbstractReplaceCallBack() {
	public String doReplace(String text, int index, Matcher matcher) {
	    if ("\"".equals(text)) {
		return "\\\\\"";
	    }
	    return "\\\\\\\\\\\\\\\"";
	}
    };

    static {
	objectMapper.getSerializationConfig().setDateFormat(DateUtil.getSimpleDateFormat("yyyy-MM-dd"));
    }

    public static String serializeHtml(Object object) {
	return RegexpUtil.replace(serialize(object), "((\")|(\\\\\"))", replaceCallBack);
    }

    public static String serialize(Object object) {
	StringWriter writer = new StringWriter();
	try {
	    objectMapper.writeValue(writer, object);
	} catch (Exception e) {
	    log.error(e.getMessage());
	    try {
		writer.close();
	    } catch (IOException ex) {
		ex.printStackTrace();
	    }
	} finally {
	    try {
		writer.close();
	    } catch (IOException e) {
		log.error(e.getMessage());
	    }
	}
	return writer.toString();
    }

    public static Object deserialize(String json) {
	return deserialize(json, HashMap.class);
    }

    public static <T> T deserialize(String json, Class<T> classed) {
	try {
	    ObjectMapper mapper = objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
	    mapper = mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
	    mapper = mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	    return mapper.readValue(json, classed);
	} catch (Exception e) {
	    log.error(e.getMessage());
	}
	return null;
    }
}