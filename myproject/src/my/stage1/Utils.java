package my.stage1;

import java.io.File;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

/**
 * @author 作者: Xu Qiang
 * @version 创建时间: 2019年10月19日 下午4:39:26
 * @description 描述: 工具类
 * 					总结的一些感觉会常用的方法
 */
public class Utils {
	/**
	 * 解析yaml文件
	 * @param fileName   文件名，格式遵守java规定
	 * @param cla   	类名
	 * @return	map      取得文件内容，存进map容器
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> parseYaml(Class<?> cla,String fileName) throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		/*
		 * fileName    习惯写法：/xxx.xml 、/xxx.yml
		 * String path1 = cla.getResource("\\").getPath();
		 * String path2 = cla.getResource("/").getPath();
		 * 返回结果不一样         
		 */
		
		String path = cla.getResource(fileName).getPath();
		//解析出来的path如果有中文，需要对path解码
		path = URLDecoder.decode(path, "UTF-8");
		//处理yaml文件格式的工具类
		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		map = mapper.readValue(new File(path), Map.class);
		return map;
	}
}	
