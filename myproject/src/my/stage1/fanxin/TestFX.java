package my.stage1.fanxin;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 作者: Xu Qiang
 * @version 创建时间: 2019年12月18日 下午4:09:47
 * @description 描述: 
 */
class A{}
class B extends A{}
class testCapacity{
	//记录扩容次数
	private int recods ;
	//当前容量值
	private int preRecords;
	public int getInitialCapacity() throws Exception{
		List<Integer> list = new ArrayList<Integer>();
		//测试插入20个数据,看扩容几次
		for(int i=0;i<20;i++) {
			list.add(i);
			//获取当前容量值,并赋值给preRecords;
			int now = getPreRecord(list);
			if(preRecords != now) {
				recods ++;
				preRecords = now;
				System.out.println("扩容啦");
			}
		}
		return recods;
	}
	private int getPreRecord(List<Integer> list) throws Exception {
		Class<? extends List> cls = list.getClass();
		Field field = cls.getDeclaredField("elementData");
		field.setAccessible(true);
		Object[] object = (Object[])field.get(list);
		return object.length;
	}
}
@SuppressWarnings("unused")
public class TestFX {
	//指定泛型上界,则可以接收指定类型或者其子类
	private static List<? extends Number> list;
	//指定泛型下界,则可以接受本身或其父类
	private static List<? super B> list2;
	public static void main(String[] args) throws Exception {
		//测试泛型上界 
		List<Number> number = new ArrayList<Number>();
		List<Integer> inte = new ArrayList<Integer>();
		list = number;
		list = inte;
		//测试泛型下界
		List<B> b = new ArrayList<B>();
		List<A> a = new ArrayList<A>();
		List<Object> obj = new ArrayList<Object>();
		list2 = b;
		list2 = a;
		list2 = obj;
		
		//测试ArrayList扩容
		int capacity = new testCapacity().getInitialCapacity();
		System.out.println(capacity);
	}
	
}

