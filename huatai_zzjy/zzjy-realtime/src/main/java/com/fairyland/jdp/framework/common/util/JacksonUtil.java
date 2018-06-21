package com.fairyland.jdp.framework.common.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

/**
 * json序列化与反序列化工具类
 * 
 * @Description 反序列化时，默认对未知属性不进行处理 序列化时，默认日期格式为"yyyy-MM-dd HH:mm:ss"
 * @author XiongMiao
 * 
 */
public class JacksonUtil {
	
	private final ObjectMapper objectMapper;

	/**
	 * 缺省的日期转换格式
	 */
	private static final String DEFAULT_DATE_FORMMAT = "yyyy-MM-dd HH:mm:ss";

	public JacksonUtil() {
		objectMapper = new ObjectMapper();
		init();
	}

	/**
	 * 初始化工具对象 默认对未知属性不进行处理
	 */
	private void init() {
		unknownPropertiesUnDeserialization();
		setDateFormat(DEFAULT_DATE_FORMMAT);
	}

	public static JacksonUtil getInstance() {
		return new JacksonUtil();
	}

	/**
	 * 设置转换日期类型的时间科室,如果不设置默认打印Timestamp毫秒数.
	 * 
	 * @param pattern 时间格式化字符串
	 */
	public void setDateFormat(String pattern) {
		if (StringUtils.isNotBlank(pattern)) {
			DateFormat dateFormat = new SimpleDateFormat(pattern);
			objectMapper.setDateFormat(dateFormat);
		}
	}

	/**
	 * 序列化bean时只序列化指定属性
	 * 
	 */
	public <T> JacksonUtil addSerializeOnlyPropertiesFilter(
			final Class<T> clazz, String... props) {
		if (objectMapper.getSerializationConfig().getFilterProvider() == null) {
			final SimpleFilterProvider filterProvider = new SimpleFilterProvider();
			objectMapper.setFilters(filterProvider);

			// 注意：自定义一个JacksonAnnotationIntrospector，使得返回的filterId是被序列化的类名。
			// 如在序列化Person时，返回的是Person.class.getName()。
			// 与setFilters共同作用
			// 默认情况下会返回@JsonFilter的value。
			setAnnotationIntrospector(filterProvider);
		}

		SimpleFilterProvider filterProvider = (SimpleFilterProvider) objectMapper
				.getSerializationConfig().getFilterProvider();

		filterProvider.addFilter(clazz.getName(),
				SimpleBeanPropertyFilter.filterOutAllExcept(props));

		return this;
	}

	/**
	 * 序列化bean时只序列化指定属性
	 * 
	 */
	public <T> JacksonUtil addSerializeExceptPropertiesFilter(
			final Class<T> clazz, String... props) {
		if (objectMapper.getSerializationConfig().getFilterProvider() == null) {
			final SimpleFilterProvider filterProvider = new SimpleFilterProvider();
			objectMapper.setFilters(filterProvider);

			// 注意：自定义一个JacksonAnnotationIntrospector，使得返回的filterId是被序列化的类名。
			// 如在序列化Person时，返回的是Person.class.getName()。
			// 与setFilters共同作用
			setAnnotationIntrospector(filterProvider);
		}

		SimpleFilterProvider filterProvider = (SimpleFilterProvider) objectMapper
				.getSerializationConfig().getFilterProvider();

		filterProvider.addFilter(clazz.getName(),
				SimpleBeanPropertyFilter.serializeAllExcept(props));

		return this;
	}

	private void setAnnotationIntrospector(
			final SimpleFilterProvider filterProvider) {
		// 注意：自定义一个JacksonAnnotationIntrospector，使得返回的filterId是被序列化的类名。
		// 如在序列化Person时，返回的是Person.class.getName()。
		// 与setFilters共同作用
		// 默认情况下会返回@JsonFilter的value。
		objectMapper
				.setAnnotationIntrospector(new JacksonAnnotationIntrospector() {
					private static final long serialVersionUID = 1L;

					@Override
					public Object findFilterId(Annotated ac) {

						return ac.getName();
						// try {
						// if(filterProvider.findFilter(ac.getName()) != null){
						// return ac.getName();
						// }
						// } catch (Exception e) {
						// }
						// return super.findFilterId(ac);
					}
				});
	}

	/**
	 * bean中属性值为null的不序列化成json字符串
	 * 
	 */
	public JacksonUtil nonNullSerialize() {
		// bean中的null值不写入json字符串
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		return this;
	}

	/**
	 * 反序列化成bean时，未知属性不进行反序列化
	 * 
	 */
	public JacksonUtil unknownPropertiesUnDeserialization() {
		configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return this;
	}

	/**
	 * 配置反序列化的特征
	 * 
	 */
	private JacksonUtil configure(DeserializationFeature feature, boolean state) {
		objectMapper.configure(feature, state);
		return this;
	}

	/**
	 * 配置——Map中值为null的不序列化成json字符串，非空的value才序列化
	 * 
	 */
	public JacksonUtil nonNullMapSerialize() {
		// Map中的null值不写入json字符串
		configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
		return this;
	}

	/**
	 * 配置序列化的特征
	 * 
	 */
	private JacksonUtil configure(SerializationFeature feature, boolean state) {
		objectMapper.configure(feature, state);
		return this;
	}

	/**
	 * 不需要序列化的配置
	 * 
	 */
	@SuppressWarnings("unused")
	private JacksonUtil setSerializationInclusion(JsonInclude.Include include) {
		objectMapper.setSerializationInclusion(include);
		return this;
	}

	/**
	 * Bean转化成Json
	 */
	public String bean2Json(Object bean) throws JsonProcessingException {
		return this.objectMapper.writeValueAsString(bean);
	}

	/**
	 * Json转化成Bean
	 * 
	 */
	public <T> T json2Bean(String jsonStr, Class<T> beanClazz)
			throws JsonParseException, JsonMappingException, IOException {
		return this.objectMapper.readValue(jsonStr, beanClazz);
	}

	/**
	 * Json转化成Map
	 * 
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> json2Map(String jsonStr)
			throws JsonParseException, JsonMappingException, IOException {
		return this.objectMapper.readValue(jsonStr, Map.class);
	}

	/**
	 * Json转化成指定Bean类型的Map
	 * 
	 */
	public <T> Map<String, T> json2Map(String jsonStr, Class<T> clazz)
			throws Exception {
		Map<String, Map<String, Object>> map = this.objectMapper.readValue(
				jsonStr, new TypeReference<Map<String, T>>() {
				});
		Map<String, T> result = new HashMap<String, T>();
		for (Entry<String, Map<String, Object>> entry : map.entrySet()) {
			result.put(entry.getKey(), map2Bean(entry.getValue(), clazz));
		}
		return result;
	}

	/**
	 * Json转化成List
	 * 
	 */
	public <T> List<T> json2List(String jsonArrayStr, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {
		List<Map<String, Object>> list = this.objectMapper.readValue(
				jsonArrayStr, new TypeReference<List<T>>() {
				});
		List<T> result = new ArrayList<T>();
		for (Map<String, Object> map : list) {
			result.add(map2Bean(map, clazz));
		}
		return result;
	}

	/**
	 * Map转化成Bean
	 * 
	 */
	public <T> T map2Bean(Map<String, Object> map, Class<T> clazz) {
		return this.objectMapper.convertValue(map, clazz);
	}

}
