package com.java.model.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
 * @author 作者: Xu Qiang
 * @version 创建时间: 2019年12月6日 下午2:33:01
 * @description 描述: mabatis动态代理对象
 */
interface SearchDao{//SearchMapper
	Object search(String msg);
}
interface SqlSession {
	Object update(String statement);
}
class DefaultSqlSession implements SqlSession{

	@Override
	public Object update(String statement) {
		System.out.println("sql语句:"+statement);
		return null;
	}
	
}
class MapperProxyHandler implements InvocationHandler{
	private SqlSession sqlSession;
	private Class<?> targetInterface;
	public MapperProxyHandler(SqlSession sqlSession,
			Class<?> targetInterface) {
		this.sqlSession = sqlSession;
		this.targetInterface = targetInterface;
	}

	/**
	 * invoke  方法是为代理对象执行具体业务的方法
	 * 
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//		System.out.println("MapperProxyHandler invoke()");
		String className = targetInterface.getName();
		String methodName = method.getName();
		String statement = className+"."+methodName+"."+args;
		return sqlSession.update(statement);
	}
	
}
class MapperProxyFactory{
	/**
	 * 借助此属性  封装要产生代理对象的目标接口
	 */
	private Class<?> targetInterface;
	public MapperProxyFactory(Class<?> targetInterface) {
		this.targetInterface = targetInterface;
	}
	/**
	 * 创建代理对象,并且为代理对象传入一个InvocationHandler对象
	 * 在MyBatis中,这个InvocationHandler对应的是MapperProxy
	 * 在这里我们定义的类是MapperProxyHandler
	 * @return
	 */
	Object newInstance(MapperProxyHandler handler){
		Object obj = Proxy.newProxyInstance(
				targetInterface.getClassLoader(), //类加载器
				new Class[] {targetInterface}, 
				handler);
		return obj;
	}
}
public class TestMapperProxy {
	public static void main(String[] args) {
		//1.构建目标接口的类对象
		Class<?> targetInterface = SearchDao.class;
		//2.获取SqlSession对象
		SqlSession sqlSession = new DefaultSqlSession();
		//3.创建一个MapperProxyHandler对象
		MapperProxyHandler proxyHandler = new MapperProxyHandler(sqlSession,targetInterface);
		//4.创建一个mapperProxyFactory
		MapperProxyFactory proxyFactory = new MapperProxyFactory(targetInterface);
		//5.基于工厂对象为目标接口对象创建代理对象
		SearchDao searchDao =(SearchDao) proxyFactory.newInstance(proxyHandler);
		Class[] interfaces = new Class[] {targetInterface};
		System.out.println(searchDao.getClass().getName());
//		SearchDao searchDao = (SearchDao)Proxy.newProxyInstance(targetInterface.getClassLoader(), interfaces, proxyHandler);
		searchDao.search("sdfsdf");
		//6.基于代理对象执行业务操作
//		searchDao.search("hello mybatis proxy");
	}
}

















