package com.java.model.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
 * @author 作者: Xu Qiang
 * @version 创建时间: 2019年12月11日 下午2:28:30
 * @description 描述: 基于接口,手动创建mapper代理对象
 */

interface TestMapper{
	Object test(String msg);
}
interface SqlSession1{
	Object select(String msg);
}
class DefaultSqlSession1 implements SqlSession1{

	@Override
	public Object select(String msg) {
		System.out.println("成功调用:"+msg);
		return null;
	}
	
}
class MapperProxy implements InvocationHandler{
	private Class<?> targetInterface;
	private SqlSession1 sqlSession;
	public MapperProxy(Class<?> targetInterface,SqlSession1 sqlSession2) {
		this.targetInterface = targetInterface;
		this.sqlSession = sqlSession2;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String msg = targetInterface.getName()+"."+method.getName();
		
		sqlSession.select(msg);
		return null;
	}
	
}
class MapperProxyFactory1{
	private Class<?> targetInterface;
	public MapperProxyFactory1(Class<?> targetInterface) {
		this.targetInterface = targetInterface;
	}
	public Object create(MapperProxy mapperProxy) {
		return Proxy.newProxyInstance(targetInterface.getClassLoader(),
				new Class[] {targetInterface}, 
				mapperProxy);
		
	}
}
public class TestMapperProxy1 {
	public static void main(String[] args) {
		Class<?> targetInterface = TestMapper.class;
		//创建SqlSession对象
		SqlSession1 sqlSession  = new DefaultSqlSession1();
		//创建MapperProxy
		MapperProxy hander = new MapperProxy(targetInterface,sqlSession);
		//创建工厂对象
		MapperProxyFactory1 fac = new MapperProxyFactory1(targetInterface);
		TestMapper mapper = (TestMapper)fac.create(hander);
		System.out.println(mapper);
	}
}
