package com.fsun.common.utils;

import java.lang.reflect.Method;
import java.lang.reflect.Field;

/**
 * 反射工具类
 * @author sunxiaolei
 *
 */
public class ReflexUtil {
	
	
	
	/***************************************         反射执行对象方法(public)           **********************************************/
	
	/**
	 * 调用对象方法
	 * @param owner
	 * @param methodName
	 * @param args
	 * @return
	 * @throws Exception
	 */
	public static Object invokeMethod(Object owner, String methodName, Object[] args) throws Exception {
		
		Class<?> ownerClass = owner.getClass();   
		Class<?>[] argsClass = new Class[args.length];   
		for (int i = 0; i < args.length; i++) {
			argsClass[i] = args[i].getClass(); 
		}
		Method method = ownerClass.getMethod(methodName,argsClass);
		return method.invoke(owner, args);   
	}
	
	
	/**
	 * 调用对象方法
	 * @param owner
	 * @param beforeMethod
	 * @param args
	 * @param parameterTypes
	 * @return
	 * @throws Exception
	 */
	public static Object invokeMethod(Object owner, String methodName, 
		Object[] args, Class<?>[] parameterTypes) throws Exception {
		
		Method method = owner.getClass().getMethod(methodName, parameterTypes);
		return method.invoke(owner, args);
	}
	
	/***************************************         反射执行静态方法(public)           **********************************************/

	/**
	 * 调用静态方法
	 * @param ownerClass
	 * @param methodName
	 * @param args
	 * @return
	 * @throws Exception
	 */
	public static Object invokeStaticMethod(Class<?> ownerClass, String methodName, Object[] args) throws Exception {		
		//设置参数
		Class<?>[] argsClass = new Class[args.length];
		for (int j = 0; j < argsClass.length; j++) {
			argsClass[j] = args[j].getClass();
		}
		//方法调用		
		Method method = ownerClass.getMethod(methodName, argsClass);
		return method.invoke(null, args);
	}

	
	/**
	 * 调用静态方法
	 * @param className
	 * @param methodName
	 * @param args
	 * @return
	 * @throws Exception
	 */
	public static Object invokeStaticMethod(String className, String methodName, Object[] args) throws Exception {			
		return invokeStaticMethod(Class.forName(className), methodName, args);
	}

	
	/**
	 * 调用静态方法
	 * @param obj
	 * @param beforeMethod
	 * @param args
	 * @param parameterTypes
	 * @return
	 * @throws Exception
	 */
	public static Object invokeStaticMethod(String className, String methodName, 
		Object[] args, Class<?>[] parameterTypes) throws Exception {
		
		Method method = Class.forName(className).getMethod(methodName, parameterTypes);
		return method.invoke(null, args);
	}
	
	
	/***************************************************    得到某个属性        *************************************************/
	
	/**
	 * 得到某个类的静态属性
	 * @param className
	 * @param fieldName
	 * @return
	 * @throws Exception 
	 */
	public static Object getStaticProperty(String className, String fieldName) throws Exception {		
		Class<?> ownerClass = Class.forName(className);
		Field field = ownerClass.getField(fieldName);
		Object property = field.get(ownerClass);   
		return property; 
	}
	
	/**
	 * 得到某个对象的属性
	 * @param className
	 * @param fieldName
	 * @return
	 * @throws Exception 
	 */
	public static Object getProperty(Object owner, String fieldName) throws Exception {   
		Class<?> ownerClass = owner.getClass();   
		Field field = ownerClass.getField(fieldName);
		Object property = field.get(ownerClass);   
		return property; 
	}
	
}
