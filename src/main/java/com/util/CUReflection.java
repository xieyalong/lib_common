package com.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 获取private和protected值
 */
public class CUReflection{
    private CUReflection(){}
    private  static CUReflection instance;
    public   static CUReflection getInstance(){
        if (null==instance){
            instance=new CUReflection();
        }
        return  instance;
    }
    /***
     * 获取私有成员变量的值
     *
     */
    public  Object getValue(Object instance, String fieldName)  {
        Field field = null;
        try {
            field = instance.getClass().getDeclaredField(fieldName);
            field.setAccessible(true); // 参数值为true，禁止访问控制检查
            return field.get(instance);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     * 设置私有成员变量的值
     *
     */
    public  void setValue(Object instance, String fileName, Object value){
        try {
            Field field = instance.getClass().getDeclaredField(fileName);
            field.setAccessible(true);
            field.set(instance, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     * 访问私有方法
     *
     */
    public  Object callMethod(Object instance, String methodName, Class[] classes, Object[] objects) {
        try {
            Method method = instance.getClass().getDeclaredMethod(methodName, classes);
            method.setAccessible(true);
            return method.invoke(instance, objects);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }

}
