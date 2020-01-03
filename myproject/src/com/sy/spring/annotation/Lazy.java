package com.sy.spring.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 作者: Xu Qiang
 * @version 创建时间: 2019年11月29日 下午6:41:20
 * @description 描述: 
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Lazy {
	boolean value() default true;
}
