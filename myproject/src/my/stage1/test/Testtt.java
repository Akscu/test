package my.stage1.test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

/**
 * @author 作者: Xu Qiang
 * @version 创建时间: 2019年11月6日 下午6:25:20
 * @description 描述: 
 */
public class Testtt {
	static final int init = 1237;
	static LinkedList<Integer> list = new LinkedList<Integer>();
	public static void main(String[] args) {
		int n = addN(init);
		jianN(n);
	}
	public static int addN(int n) {
		list.addFirst(n);
		if(n>5000) {
			return n;
		}
		n *=2;
		addN(n);
		return n;
	}
	public static int jianN(int n) {
		if(n==init) {
			System.out.println(n);
			return n;
		}
		n/=2;
		jianN(n);
		return n;
	}
	@Test
	public void test() {
		List<? extends Object> list = new ArrayList<String>();
//		list.;
	}
}
