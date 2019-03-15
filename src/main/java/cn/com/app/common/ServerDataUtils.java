package cn.com.app.common;

import com.encrypt.DesUtil;
import com.encrypt.DigestUtils;
import com.utils.format.json.FXJson;

/**
 * @ClassName:     ServerUtils.java
 * @author         zcs
 * @version        V1.0  
 * @Date           2019年3月12日 下午1:59:58 
 * @Description:   TODO(用一句话描述该文件做什么) 
 */
public class ServerDataUtils {
	public String encrypt(String messge,String imei,String dust) throws Exception {
		return encrypt(messge, imei, dust, 200, "");
	}
	public String encrypt(String messge,String imei,String dust,int code,String hint) throws Exception {
		return encrypt(messge, imei, dust, code, hint, -1, null);
	}
	/**
	 * 
	 * @param messge 实际数据
	 * @param imei imei
	 * @param dust 加盐
	 * @param code 状态code
	 * @param hint 给用户的提示(可不填)
	 * @param errorCode 错误code(可不填)
	 * @param errorMsg	错误提示(可不填)
	 * @return
	 * @throws Exception
	 */
	public String encrypt(String messge,String imei,String dust,int code,String hint,int errorCode,String errorMsg) throws Exception {
		if(imei==null) {
			imei = System.currentTimeMillis()+"";
		}
		FXJson mainJson = new FXJson();
		mainJson.put("encrypt", true);
		String time = System.currentTimeMillis()+"";
		mainJson.put("time", time);
		mainJson.put("imei", imei);
		String newKey = time + imei;//生成第一个key，
		String mainKey =  DesUtil.generateKey();//
		String msgKey = DesUtil.encrypt(newKey,mainKey);
//		String msgKey = AesUtils.encrypt(mainKey,newKey);
		mainJson.put("key", mainKey);//解析第二层的key
		FXJson baseMsg = new FXJson();//外层保存简单信息
		baseMsg.put("err_code", errorCode);
		baseMsg.put("err_msg", errorMsg);
		time = System.currentTimeMillis()+"";
		baseMsg.put("time", time); 
		newKey = msgKey+time; //层key+time
		newKey = DesUtil.encrypt(msgKey,newKey);
		messge = DesUtil.encrypt(messge,newKey);
		baseMsg.put("data", messge);//保存真正的加密数据
//		System.out.println("加密后的数据 "+messge);
		String md5 = DigestUtils.md5(messge);
		baseMsg.put("MD5", md5+newKey);
		baseMsg.put("dust", dust);
		String key1 = mainKey;
		mainJson.put("key", mainKey);
		mainJson.put("message", baseMsg.toString());
		mainJson.put("hint", hint);
		mainJson.put("code", code);
		return mainJson.toString();
	}
	public String decrypt(String str,String imei,String dust) throws Exception {
		FXJson json = new FXJson(str);
		String data = json.getStr("data");
		String time = json.getStr("time");
		String mainKey = DesUtil.encrypt(imei+time,time);
		String data2 = DesUtil.decrypt(data, mainKey);
//		System.out.println("data2  "+data2);
		json = new FXJson(data2);
		time = json.getStr("time");
//		String key = json.getStr("key");
		String data3 = json.getStr("data");
		
		String newKey = mainKey+time+dust;
		newKey = DesUtil.encrypt(newKey,mainKey);//上层的key加现在的时间生成新key
		String decrypt = DesUtil.decrypt(data3, newKey);
//		String data2 = DesUtil.encrypt(data,newKey);
		return decrypt;
		
	}

}
