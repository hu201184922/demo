package com.fairyland.jdp.orm.util.reflect;

public abstract interface IClass<T>
{
  public abstract Property getProperty(String paramString);

  public abstract Property[] getPropertys();

  public abstract T newInstance();

  public abstract T newInstance(Object paramObject);

  public abstract T newInstance(Class<?> paramClass, Object paramObject);

  public abstract MethodProxy getMethod(String paramString);

  public abstract MethodProxy getMethod(String paramString, Class<?>[] paramArrayOfClass);

  public abstract void setValue(Object paramObject1, String paramString, Object paramObject2);

  public abstract Object getValue(Object paramObject, String paramString);

  public abstract T newInstance(Class<?>[] paramArrayOfClass, Object[] paramArrayOfObject);
}