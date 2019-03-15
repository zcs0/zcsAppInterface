package cn.com.app.common;


/**
 * @ClassName:     Test.java
 * @author         zcs
 * @version        V1.0  
 * @Date           2019年3月12日 上午11:25:11 
 * @Description:   TODO(用一句话描述该文件做什么) 
 */
public class Test {
	public static void main(String[] args) throws Exception {
		String url = "https://bili.yongjiu-gaoqing.com/20190108/Ln261RBP/index.m3u8";
		ServerDataUtils server = new ServerDataUtils();
//		DataUtils util = new DataUtils();
		String imei = "345634567";
		String dust = "dust";
		String encrypt2 = server.encrypt(url, imei, dust);
//		String decrypt = server.decrypt(url, imei, dust);
		System.out.println("server 加密："+encrypt2);
//		String decrypt2 = util.decrypt(encrypt2, imei);
//		System.out.println("手机 解密："+decrypt2);
		System.out.println();
		System.out.println();
//		String encrypt = util.encrypt(url, imei, dust);
//		System.out.println("手机端 加密："+encrypt);
		//给手机端发过的的数据解密
//		String decrypt3 = server.decrypt(encrypt, imei, dust);
//		System.out.println("server 解密："+decrypt3);
	}
	private String dust="dust";//返回到用户加盐

}
