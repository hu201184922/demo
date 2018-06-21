package com.fairyland.jdp.orm.mybatis.proxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.binding.BindingException;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;

import com.fairyland.jdp.orm.Pager;
import com.fairyland.jdp.orm.util.common.ObjectUtil;
import com.fairyland.jdp.orm.util.reflect.ClassUtil;



public class MyBatisMapperMethod {
    protected final Log logger = LogFactory.getLog(MyBatisMapperMethod.class);
    private SqlSession sqlSession;
    private Configuration config;
    private SqlCommandType type;
    private String commandName;
    private Class<?> declaringInterface;
    private Method method;
    private Integer pageIndex;
    private List<String> paramNames;
    private List<Integer> paramPositions;
    private boolean hasNamedParameters;
    private Map<String, Map<String, ResultMapping>> resultMaps;

    public MyBatisMapperMethod(Class<?> declaringInterface, Method method, SqlSession sqlSession) {
	this.paramNames = new ArrayList<String>();
	this.paramPositions = new ArrayList<Integer>();
	this.sqlSession = sqlSession;
	this.method = method;
	this.config = sqlSession.getConfiguration();
	this.hasNamedParameters = false;
	this.declaringInterface = declaringInterface;
	setupFields();
	setupMethodSignature();
	setupCommandType();
	setupResultMap();
	validateStatement();
    }

    private void setupResultMap() {
	this.resultMaps = new HashMap<String, Map<String, ResultMapping>>();
	MappedStatement ms = this.sqlSession.getConfiguration().getMappedStatement(this.commandName);
	for (ResultMap resultMap : ms.getResultMaps()) {
	    if (this.resultMaps.containsKey(resultMap.getId()))
		continue;
	    Map<String, ResultMapping> mappings = new HashMap<String, ResultMapping>();
	    for (ResultMapping mapping : resultMap.getResultMappings()) {
		if (mappings.containsKey(mapping.getProperty()))
		    continue;
		mappings.put(mapping.getProperty(), mapping);
	    }
	    this.resultMaps.put(resultMap.getId(), mappings);
	}
    }

    @SuppressWarnings("unchecked")
    public Object execute(Object[] args) {
	Map<String, Object> param = getParam(args);
	Pager<Object> pager = (Pager<Object>) param.get("pager");
	pager.setPageItems(this.sqlSession.selectList(this.commandName, param));
	return pager;
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> getParam(Object[] args) {
	Map<String, Object> param = new HashMap<String, Object>();
	int paramCount = this.paramPositions.size();
	if ((args == null) || (paramCount == 0)) {
	    param.put("pager", getPager((Pager<Object>) args[this.pageIndex.intValue()]));
	    return param;
	}
	if ((!this.hasNamedParameters) && (paramCount == 1)) {
	    if ((ObjectUtil.isNull(args[((Integer) this.paramPositions.get(0)).intValue()])) || (ClassUtil.isPrimitiveType(args[((Integer) this.paramPositions.get(0)).intValue()].getClass())))
		param.put("value", args[((Integer) this.paramPositions.get(0)).intValue()]);
	    else {
		param.putAll(ObjectUtil.toMap(args[((Integer) this.paramPositions.get(0)).intValue()]));
	    }
	    param.put("pager", getPager((Pager<Object>) args[this.pageIndex.intValue()]));
	    return param;
	}
	for (int i = 0; i < paramCount; i++) {
	    param.put((String) this.paramNames.get(i), args[((Integer) this.paramPositions.get(i)).intValue()]);
	}
	param.put("pager", getPager((Pager<Object>) args[this.pageIndex.intValue()]));
	return param;
    }

    private Pager<Object> getPager(Pager<Object> page) {
	if (ObjectUtil.isNull(page))
	    page = new Pager<Object>();
	return page;
	/*
	 * Map<String,String> pager = new HashMap<String, String>(); if(page.isOrderBySetted()){ Map<String,ResultMapping> mappings = resultMaps.get(declaringInterface.getName().concat(".").concat(ClassUtil.getMethodGenericReturnType(method).getSimpleName()).concat("ResultMap")); StringBuffer orderBy = new StringBuffer(); String[] orderbys = page.getOrderBy().split(","); for(String orderby : orderbys){ orderBy.append((mappings!=null&&mappings.containsKey(orderby)?mappings.get(orderby).getColumn():orderby) + ","); } pager.put("orderBy", RegexpUtil.replace(orderBy.toString(), ",$", "")); if(ObjectUtil.isNotNull(page.getOrder())) pager.put("order", page.getOrder().name()); } return pager;
	 */
    }

    private void setupMethodSignature() {
	Class<?>[] argTypes = this.method.getParameterTypes();
	for (int i = 0; i < argTypes.length; i++)
	    if (Pager.class.isAssignableFrom(argTypes[i])) {
		this.pageIndex = Integer.valueOf(i);
	    } else {
		String paramName = String.valueOf(this.paramPositions.size());
		paramName = getParamNameFromAnnotation(i, paramName);
		this.paramNames.add(paramName);
		this.paramPositions.add(Integer.valueOf(i));
	    }
    }

    private String getParamNameFromAnnotation(int i, String paramName) {
	Object[] paramAnnos = this.method.getParameterAnnotations()[i];
	for (Object paramAnno : paramAnnos) {
	    if ((paramAnno instanceof Param)) {
		this.hasNamedParameters = true;
		paramName = ((Param) paramAnno).value();
	    }
	}
	return paramName;
    }

    private void setupFields() {
	this.commandName = (this.declaringInterface.getName() + "." + this.method.getName());
    }

    private void setupCommandType() {
	MappedStatement ms = this.config.getMappedStatement(this.commandName);
	this.type = ms.getSqlCommandType();
	if (this.type != SqlCommandType.SELECT)
	    throw new BindingException("Unsupport execution method for: " + this.commandName);
    }

    private void validateStatement() {
	if (!this.config.hasStatement(this.commandName))
	    throw new BindingException("Invalid bound statement (not found): " + this.commandName);
    }
}