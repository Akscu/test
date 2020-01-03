package com.sy.spring.factory;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.sy.spring.annotation.ComponentScan;
import com.sy.spring.annotation.Lazy;
import com.sy.spring.annotation.Service;
import com.sy.spring.config.SpringConfig;
import com.sy.spring.vo.BeanDefinition;

/**
 * @author 作者: Xu Qiang
 * @version 创建时间: 2019年11月29日 下午6:46:27
 * @description 描述: 
 */
public class AnnotationConfigApplicationContext {
	private Map<String,BeanPropertyDefinition> beanMap = new HashMap<>();
	private Map<String,Object> instanceMap = new HashMap<>();
	public AnnotationConfigApplicationContext(Class<?> configCls)throws Exception{
		//1.读取配置类
		ComponentScan cs =
		configCls.getDeclaredAnnotation(ComponentScan.class);
		String pkg = cs.value();
		//2.扫描指定包中的类
		String classPath = pkg.replace(".", "/");
		@SuppressWarnings("static-access")
		URL url = configCls.getClassLoader().getSystemResource(classPath);
		File fileDir = new File(url.getPath());
		File[] classFiles = fileDir.listFiles(new FileFilter() {
			@Override
			public boolean accept(File file) {
				return  file.isFile()&&file.getName().endsWith(".class");
			}
		});
		//3.封装文件信息
		processClassFiles(pkg,classFiles);
		
	}
	private void processClassFiles(String pkg,File[] classFiles) throws Exception{
		for(File f : classFiles) {
			String pkgClass = pkg+"."+f.getName().substring(0, f.getName().lastIndexOf("."));	
			Class<?> targetCls = Class.forName(pkgClass);
			if(targetCls.isAnnotationPresent(Service.class))
				continue;
			Service service = targetCls.getDeclaredAnnotation(Service.class);
			Lazy lazy = targetCls.getDeclaredAnnotation(Lazy.class);
			BeanDefinition bd = new BeanDefinition();
			bd.setId(service.value());
			if(lazy!= null ) {
				bd.setLazy(true);
			}
			bd.setPkgClass(pkgClass);
		}
		
	}
	public static void main(String[] args) throws Exception{
		AnnotationConfigApplicationContext ctx = 
				new AnnotationConfigApplicationContext(SpringConfig.class);
//		ctx.getBean(key,cls);
		
	}
}
