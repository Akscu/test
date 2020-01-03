package my.stage1.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TestDemo{
	public static void main(String[] args) {
		System.out.println("".equals(""));
		System.out.println("".equals(""));
	}
	private static void test1() {
		List<Integer> NumberList = new ArrayList<Integer>();
		NumberList.add(2);
		NumberList.add(4);
		NumberList.add(1);
		NumberList.add(3);
		NumberList.add(5);
		for(int i =0;i<NumberList.size();++i){
		Integer v = NumberList.get(i);
		//集合的remove()这个方法,参数如果是int类型,则移除下标
		//如果是对象类型,则移除内容值
		boolean remove = NumberList.remove(v);
		System.out.println(v);
		 if(v%2==0){
		     NumberList.remove(v);
		  }
		}
		System.out.println(NumberList);
	}
	private static void snatch(){
		String path = "http://tts.tmooc.cn/ttsPage/CGB/CGB_A_V02/JAVAAPI2/DAY01/COURSE/JAVAJSD_V01JAVAAPI2DAY01_001.png";
//		String path = "http://tts.tmooc.cn/ttsPage/CGB/CGB_A_V02/Servlet/DAY01/COURSE/ppt.html";
		//建立连接
		URL url;
		try {
			url = new URL(path);
		
		//获取对象HttpURLConnection对象
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
		conn.setRequestMethod("GET");
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setUseCaches(false);
		conn.connect();
		InputStream in = conn.getInputStream();
		//获取文件存入路径
//		String savePath = save(path);
		FileOutputStream out = new FileOutputStream(new File("C:/Users/tarena/Desktop/ddd.png"));
		byte[] buf = new byte[1024];
		int len = 0;
		while((len=in.read(buf))!=-1){
			out.write(buf,0,len);
		}
		in.close();
		out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
