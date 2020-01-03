package com.java.model.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author 作者: Xu Qiang
 * @version 创建时间: 2019年12月11日 下午3:06:16
 * @description 描述: 
 */
/**接口*/
interface Dao{}
/**处理器*/
class Handler implements InvocationHandler{
	private Class<?> daoInterface;
	public Handler(Class<?> daoInterface) {
		this.daoInterface = daoInterface;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		return null;
	}
}
public class MapperFactory {
	public static void main(String[] args) {
		//获取接口类对象
		Class<?> cls = Dao.class;
		//创建处理器
		Handler h = new Handler(cls);
		//创建接口Dao代理对象
		Dao d =
				(Dao)Proxy.newProxyInstance(
						cls.getClassLoader(),
						new Class[] {cls}, 
						h);
		System.out.println(d.getClass());//创建的代理对象是null
		
	}
}
