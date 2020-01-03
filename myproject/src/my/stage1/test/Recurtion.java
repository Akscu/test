package my.stage1.test;
/**
 * @author 作者: Xu Qiang
 * @version 创建时间: 2019年10月19日 下午4:57:27
 * @description 描述: 递归
 */
public class Recurtion {
	public static void main(String[] args) {
		
	}
	public static int sum(int n){
		if(n<=1){
			return 1;
		}else{
			return sum(n-1)+n;

			//  sum(2)+3
			//  sum(1)+2+3;
			//return 1+2+3;
		}

	}
	public static int factorial(int n){
		int t = 1;
		if(n<=1){
			return 1;
		}else{
			t = n*factorial(n-1);
			return t;
		}
	}
}
