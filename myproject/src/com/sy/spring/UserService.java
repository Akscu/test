package com.sy.spring;

import com.sy.spring.annotation.Lazy;
import com.sy.spring.annotation.Service;

/**
 * @author 作者: Xu Qiang
 * @version 创建时间: 2019年11月29日 下午6:36:03
 * @description 描述: 
 */
@Service("userService")
@Lazy(true)
public class UserService {
	public UserService() {
		System.out.println("UserService()");
	}
}
