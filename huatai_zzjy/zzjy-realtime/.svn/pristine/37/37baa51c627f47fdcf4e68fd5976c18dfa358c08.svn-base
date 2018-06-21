package com.fairyland.jdp.orm.mybatis.bingding;


import org.apache.ibatis.binding.BindingException;
import org.apache.ibatis.binding.MapperRegistry;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;

import com.fairyland.jdp.orm.mybatis.proxy.MyBatisMapperProxy;
import com.fairyland.jdp.orm.util.reflect.ClassUtil;

public class MyBatisMapperRegistry extends MapperRegistry {
    public MyBatisMapperRegistry(Configuration config) {
	super(config);
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
	if (!hasMapper(type))
	    throw new BindingException("Type " + type + " is not known to the MapperRegistry.");
	try {
	    return MyBatisMapperProxy.newMapperProxy(type, sqlSession, (Configuration) ClassUtil.getValue(this, "config"));
	} catch (Exception e) {
	    throw new BindingException("Error getting mapper instance. Cause: " + e, e);
	}

    }
}