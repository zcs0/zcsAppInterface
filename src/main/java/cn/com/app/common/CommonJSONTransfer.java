package cn.com.app.common;

import cn.com.app.assistcode.Page;
import cn.com.app.main.dao.pojo.TVLivingList;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 2019/3/14.
 */
public class CommonJSONTransfer {

    /**
     * 通过request接收前端请求的json字符串，转换成实体对象
     * @param request
     * @param clz
     * @return
     * @exception
     * */
    public static <T> T getObjectFromJSONStr(HttpServletRequest request,Class<T> clz) throws Exception{
        JSONObject jsonObjectbefore = getJsonObjectByRequest(request);
        return (T) JSONObject.toBean(jsonObjectbefore,clz);
    }

    /**
     * 通过response将实体对象转换成json对象输出前端
     * @param response
     * @param t
     * */
    public static <T> void responseJSONObjectToApp(HttpServletResponse response,T t) {
        try {
            response.getWriter().print(JSONObject.fromObject(t));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过request获取前端请求的json字符串，分析出json串中存在的实体对象的属性，以Map形式返回
     * @param request
     * @param clz
     * @return
     * @exception
     * */
    public static <T> Map<String,Object> getJSONFieldByRequest(HttpServletRequest request,Class<T> clz) throws Exception{
        JSONObject jsonObjectbefore = getJsonObjectByRequest(request);
        Field[] fields = clz.getDeclaredFields();
        Map<String,Object> map = null;
        for(Field f : fields){
            if(jsonObjectbefore.get(f.getName()) != null){
                if(map == null){
                    map = new HashMap<>();
                }
                map.put(f.getName(),getObjectByEspeciallyType(f.getType().getName(),jsonObjectbefore.get(f.getName()).toString()));
            }
        }
        if(jsonObjectbefore.get("id") != null){ //手动添加id属性
            if(map == null){
                map = new HashMap<>();
            }
            map.put("id",Long.valueOf(jsonObjectbefore.get("id").toString()));
        }

        return map;
    }

    /**
     * 用于将字符串转换成对应属性类型的对象返回(防止出现类似像String转换成Long异常)
     * 这里只包含常用的基本类型 如果有其他类型 需要添加
     * @param typeName
     * @param sourceStr
     * @return
     * @exception
     * */
    public static Object getObjectByEspeciallyType(String typeName,String sourceStr) throws ClassNotFoundException {
        Class clz = Class.forName(typeName);
        if(clz.isInstance(Long.valueOf("0"))){
            return Long.valueOf(sourceStr);
        }else if(clz.isInstance(Integer.valueOf("0"))){
            return  Integer.valueOf(sourceStr);
        }else if(clz.isInstance(Double.valueOf("0"))){
            return  Double.valueOf(sourceStr);
        }else if(clz.isInstance(Float.valueOf("0"))){
            return  Float.valueOf(sourceStr);
        }else if(clz.isInstance(Boolean.valueOf("false"))){
            return Boolean.valueOf(sourceStr);
        }else if(clz.isInstance(new Date())){
            return new Date(sourceStr);
        }
        return sourceStr;
    }

    /**
     * 获取带有page信息的参数，将实体参数和page分离后以Map返回
     * @param request
     * @param clz
     * @return
     * @exception
     * */
    public static <T> Map<String,Object> getPageObjectFromJSONStr(HttpServletRequest request,Class<T> clz) throws Exception{
        JSONObject jsonObjectbefore = getJsonObjectByRequest(request);
        //获取page信息 提取信息后 将page从json剔除 以便不污染实体对象
        Page page = null;
        if(jsonObjectbefore.get("count") != null){
            if(page == null){
                page = new Page();
            }
            page.setCount(Long.parseLong(jsonObjectbefore.get("count").toString()));
            jsonObjectbefore.remove("count");
        }
        if(jsonObjectbefore.get("startRow") != null){
            if(page == null){
                page = new Page();
            }
            page.setStartRow(Integer.parseInt(jsonObjectbefore.get("startRow").toString()));
            jsonObjectbefore.remove("startRow");
        }
        if(jsonObjectbefore.get("limit") != null){
            if(page == null){
                page = new Page();
            }
            page.setLimit(Integer.parseInt(jsonObjectbefore.get("limit").toString()));
            jsonObjectbefore.remove("limit");
        }

        Field[] fields = clz.getDeclaredFields();
        Map<String,Object> map = null;
        for(Field f : fields){
            if(jsonObjectbefore.get(f.getName()) != null){
                if(map == null){
                    map = new HashMap<>();
                }
                map.put(f.getName(),getObjectByEspeciallyType(f.getType().getName(), jsonObjectbefore.get(f.getName()).toString()));
            }
        }
        if(jsonObjectbefore.get("id") != null){ //手动添加id属性
            if(map == null){
                map = new HashMap<>();
            }
            map.put("id",Long.parseLong(jsonObjectbefore.get("id").toString()));
        }

        Map<String,Object> mapre = new HashMap<>();
        mapre.put("obj",map);
        mapre.put("page",page);
        return mapre;
    }

    /**
     * 通过request获取前端传递的json字符串,将字符串转换成json对象返回
     * @param request
     * @return
     * @exception
     * */
    public static JSONObject getJsonObjectByRequest(HttpServletRequest request) throws Exception{
        StringBuffer json = new StringBuffer();
        BufferedReader reader = request.getReader();
        String lineString = null;
        while((lineString=reader.readLine()) != null){
            json.append(lineString.trim());
        }
        return JSONObject.fromObject(json.toString());
    }
}
