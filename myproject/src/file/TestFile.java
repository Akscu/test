package file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;

/**
 * @author 作者: Xu Qiang
 * @version 创建时间: 2019年12月18日 下午7:44:46
 * @description 描述: 
 */
public class TestFile {
	public static void main(String[] args) throws IOException {
		File from = new File("C:\\Users\\Akscu\\Desktop\\stage-Ⅳ-qa.txt");
		File to = new File("C:\\Users\\Akscu\\Desktop\\stage-Ⅳ-qa-test.txt");
		//创建输入流,读取文件
		FileInputStream in = new FileInputStream(from);
//		BufferedInputStream bis = new BufferedInputStream(in);
//		InputStreamReader isr = new InputStreamReader(bis, "utf-8");
		Reader r = new BufferedReader(new InputStreamReader(new FileInputStream(from), "utf-8"));
		//创建输出流,写出文件
		Writer w = new PrintWriter(new OutputStreamWriter(new FileOutputStream(to), "utf-8"));
		int len;
		while((len = r.read()) != 0) {
			w.write(len);
			
		}
		w.close();
		r.close();
		in.close();
		
		
		
	}
}
