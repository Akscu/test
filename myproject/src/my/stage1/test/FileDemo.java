package my.stage1.test;

import java.io.File;

/**
 * @author 作者: Xu Qiang
 * @version 创建时间: 2019年10月19日 下午4:53:04
 * @description 描述: 
 */
public class FileDemo {
	public static void main(String[] args) throws Exception{
		File f = new File("C:/Users/tarena/Desktop/JSDTN201904");
		delete(f);
		int a = 0;
		a = count(f, a);
		System.out.println(a);
	}
	/**
	 * 删除文件
	 * @param file
	 */
	public static void delete(File file){
		if(file.isDirectory()){
			File[] listFiles = file.listFiles();
			for(File f : listFiles){
				delete(f);
			}
		}else{
			int temp = file.getName().lastIndexOf(".html");
			if(temp != -1){
				file.delete();
			}
		}
	}
	public static int count(File file,int a){
		if(file.isDirectory()){
			File[] listFiles = file.listFiles();
			for(File f : listFiles){
				a = count(f,a);
			}
		}else{
			a++;
		}
		return a;
	}
}
