package grab;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;


/**
* @author 作者:许强
* @version 创建时间:2019年9月25日下午3:02:02
* @description 描述: 抓取达内tmooc图片，存进文件夹
* 					
* 					启动一个单线程：负责抓取所有ppt图片地址，存进链表
* 					启动一个线程池：负责复制图片    ，线程数根据图片总量创建
*/
public class Demo_Grab {
	private static Queue<String> queue = new LinkedList<>();
	private static final String URL_PRE = "http://tts.tmooc.cn/ttsPage/CGB/CGB_A_V02/";
	private static final String URL_SUF = "/COURSE/ppt.html";
	private static int count;
	
	public static void main(String[] args) throws Exception {
		//程序启动
//		start();
	}
	/**
	 * 传入的是tmooc所有页面
	 * @param list
	 * @return
	 * @throws Exception 
	 */
	public static List<String> runAllData(List<String> list) throws Exception{
		List<String> listPPT = new ArrayList<>();
		List<String> listAll = new ArrayList<>();
		for(String str : list){
			listAll = getAllPPT(listAll,str);
		}
		return listAll;
	}
	
	
	public static List<String> getAllPPT(List<String> listAll,String path) throws Exception {
//		String path = "http://tts.tmooc.cn/ttsPage/CGB/CGB_A_V02/JAVAAPI2/DAY01/COURSE/JAVAJSD_V01JAVAAPI2DAY01_001.png";
//		String path = "http://tts.tmooc.cn/ttsPage/CGB/CGB_A_V02/Servlet/DAY01/COURSE/ppt.html";
//					   http://tts.tmooc.cn/ttsPage/CGB/CGB_A_V02/DATABASE/DAY1/COURSE/ppt.html
		//拼接每一张图片前缀
		String pathPre = path.substring(0,path.indexOf("ppt"));
		//建立连接
		URL url = new URL(path);
		//获取对象HttpURLConnection对象
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setUseCaches(false);
		conn.connect();
		InputStream in = conn.getInputStream();
		path = format(path);
		FileOutputStream out = new FileOutputStream(new File(path));
		byte[] buf = new byte[1024];
		int len = 0;
		while((len=in.read(buf))!=-1){
			out.write(buf,0,len);
		}
		in.close();
		out.close();
		listAll = getData(listAll,pathPre,path);
		return listAll;
	}
	/**
	 * 获取所有图片值
	 * @param path
	 * @return
	 * @throws DocumentException 
	 */
	@Test
	public static List<String> getData(List<String> listAll,String pathPre,String path) throws Exception {
		File file = new File(path);
		Document parseRes = Jsoup.parse(file, "utf-8");
		Elements select = parseRes.select("img");
		for(org.jsoup.nodes.Element e : select){
			String imgSrc = e.attr("src");
			listAll.add(pathPre+imgSrc);
		}
		return listAll;
	}
	/**
	 * 处理格式 如：http://tts.tmooc.cn/ttsPage/CGB/CGB_A_V02/Servlet/DAY01/COURSE/ppt.html
	 * @param path
	 * @return
	 */
	public static String format(String path){
		int start = path.indexOf("CGB_A_V02");
		int end = path.lastIndexOf("/COURSE");
		path = path.substring(start, end);
		return "C:/Users/tarena/Desktop/"+path+".html";
	}


	static class runAllData extends Thread{
		private List<String> list;
		@Override
		public void run() {
			
		}
	}
	/**
	 * 负责复制所有图片内容
	 * @author tarena
	 *
	 */
	static class CopyPpt extends Thread{
		private List<String> list;
		private int start;
		private int end;

		public CopyPpt(List<String> list, int start, int end) {
			super();
			this.list = list;
			this.start = start;
			this.end = end;
		}

		@Override
			public void run() {
				for(int i=start;i<end;i++){
					if(list.get(i)!=null || list.get(i) != ""){
						try {
							snatch(list.get(i));
						} catch (IOException e) {
							e.printStackTrace();
						}
					}else{
						return;
					}
				}
			}
	}
	/**
	 * 复制图片
	 * @throws IOException
	 */
	@Test
	public static void snatch(String path) throws IOException{
//		path = "http://tts.tmooc.cn/ttsPage/CGB/CGB_A_V02/JAVAAPI2/DAY01/COURSE/JAVAJSD_V01JAVAAPI2DAY01_001.png";
//		String path = "http://tts.tmooc.cn/ttsPage/CGB/CGB_A_V02/Servlet/DAY01/COURSE/ppt.html";
		
		//建立连接
		URL url = new URL(path);
		//获取对象HttpURLConnection对象
		System.out.println(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
		conn.setRequestMethod("GET");
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setUseCaches(false);
		conn.connect();
		InputStream in = conn.getInputStream();
		//获取文件存入路径
		String savePath = save(path);
		System.out.println(savePath);
		FileOutputStream out = new FileOutputStream(new File(savePath));
		byte[] buf = new byte[1024];
		int len = 0;
		while((len=in.read(buf))!=-1){
			out.write(buf,0,len);
		}
		System.out.println("--------------复制完成--------------");
		in.close();
		out.close();
	}
	public static String save(String path) {
		//String path = "http://tts.tmooc.cn/ttsPage/CGB/CGB_A_V02/JAVAAPI2/DAY01/COURSE/JAVAJSD_V01JAVAAPI2DAY01_001.png";
		int start = path.indexOf("CGB_A_V02");
		int end = path.lastIndexOf("/COURSE");
		path = path.substring(start, end);
		long c = System.currentTimeMillis();
		return "C:/Users/tarena/Desktop/"+path+"/"+c+".png";
	}
	/**
	 * 从配置文件中tmooc.txt
	 * @return list tmooc所有页面
	 * @throws Exception 
	 */
	public static List<String> getHtml() throws Exception{
		List<String> list = new ArrayList<String>();
		String path = Demo_Grab.class.getResource("/tmooc.txt").getPath();
		BufferedReader out = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path)),"utf-8"));
		String line = "";
		while((line = out.readLine())!= null){
			list = getHtmlAll(list,line);
		}
		return list;
	}
	public static List<String> getHtmlAll(List<String> list,String line) {
		//创建文件夹
//		mkdir(line);
		String[] tempArr = line.split(":");
		int n =	Integer.parseInt(tempArr[1]);
		count += n;
		for(int i=1;i<=n;i++){
			if(i<10){
				list.add(URL_PRE+tempArr[0]+"/DAY0"+i+URL_SUF);
				continue;
			}
			list.add(URL_PRE+tempArr[0]+"/DAY"+i+URL_SUF);
		}
		return list;
	}

	public static void mkdir(String line) {
	}
	/**
	 * 程序启动
	 * @throws Exception 
	 */
	private static void start() throws Exception {
//		List<String> list = getHtml();
//		for(String s : list){
//			System.out.println(s);
//		}
//		String path = "http://tts.tmooc.cn/ttsPage/CGB/CGB_A_V02/JAVAAPI2/DAY01/COURSE/JAVAJSD_V01JAVAAPI2DAY01_001.png";
//		String str = save(path);
//		System.out.println(str);
//		//测试,取出所有tmooc所有图片
		List<String> runAllData = runAllData(getHtml());
		System.out.println(runAllData.size());
		for(String s : runAllData){
			System.out.println(s);
		}
		//总线程个数
		int nFixed = 20;
		//任务数1800
		int rwNum = runAllData.size();
		ExecutorService pool = Executors.newFixedThreadPool(nFixed);
		int zhengshu = rwNum/nFixed;
		for(int i=0;i<nFixed;i++){
			CopyPpt copyPpt = new CopyPpt(runAllData, zhengshu*i, zhengshu*(i+1));
			pool.execute(copyPpt);
		}
		pool.shutdown();
//		CopyPpt copyPpt = new CopyPpt(runAllData,zhengshu*nFixed , rwNum);
//		copyPpt.start();
	}
	/**
	 * 获取文件路径
	 * @throws IOException 
	 */
	@Test
	public void getFilePath() throws IOException{
		//达内tmooc学习网站
		String url = "http://tts.tmooc.cn/studentCenter/toMyttsPage/user/myTTS?sessionId=46514651&date=Wed%20Sep%2025%202019%2016:15:10%20GMT+0800%20(%E4%B8%AD%E5%9B%BD%E6%A0%87%E5%87%86%E6%97%B6%E9%97%B4)&courseVersion=CGB_A_V02";
		url = URLDecoder.decode(url,"utf-8");
		Document doc = Jsoup.connect(url).get();
		Elements eles = doc.select("body");
		for(Element e : eles){
			String href = e.attr("href");
			System.out.println(href);
		}
	}
}
