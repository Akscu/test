package my.stage1.test;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
* @author 作者:许强
* @version 创建时间:2019年9月26日上午8:48:42
* @description 描述: 
*/
public class FileCount {
	public static void main(String[] args) throws IOException {
		
//		File file = new File("C:/Users/tarena/Desktop/test");
//		file.mkdir();
//		String path = "C:/Users/tarena/Desktop/CGB_A_V02";
//		System.out.println(listFile(path));
//		isDirec(new File(path));
		mkdirS();
	}
	
	public static void mkdirS(){
		String path = "C:/Users/tarena/Desktop/sfas/sdfas/sdf.txt";
		File f = new File(path);
		if(!f.exists()){
			f.mkdirs();
		}
	}
	public static void isDirec(File f_){
		File[] listFiles = f_.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				if(pathname.isDirectory()){
					return true;
				}
				return false;
			}
		});
		int a=0;
		for(File f : listFiles){
			int c =countJs(f);
			a += c;
		}
		System.out.println(a);
	}
	public static int countJs(File f_){
		File[] listFiles = f_.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				if(pathname.isDirectory()){
					return true;
				}
				return false;
			}
		});
		return isPng(listFiles);
	}
	public static int isPng(File[] file){
		int count = 0;
		for(File f:  file){
			int i = 0;
			File[] f_ = f.listFiles(new FileFilter() {
				@Override
				public boolean accept(File pathname) {
					// TODO Auto-generated method stub
					return pathname.getName().endsWith(".png");
				}
			});
			i = f_.length;
			count += i;
		}
		return count;
	}
}
