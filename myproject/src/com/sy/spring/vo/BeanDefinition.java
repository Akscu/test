package com.sy.spring.vo;
/**
 * @author 作者: Xu Qiang
 * @version 创建时间: 2019年11月29日 下午9:26:01
 * @description 描述: 
 */
public class BeanDefinition {
	private String id;
	private boolean lazy = false;
	private String pkgClass;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isLazy() {
		return lazy;
	}
	public void setLazy(boolean lazy) {
		this.lazy = lazy;
	}
	public String getPkgClass() {
		return pkgClass;
	}
	public void setPkgClass(String pkgClass) {
		this.pkgClass = pkgClass;
	}
	
}
