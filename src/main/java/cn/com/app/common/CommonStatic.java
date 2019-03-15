package cn.com.app.common;

import java.util.List;
import java.util.Map;

/**
 * Created by user on 2019/3/12.
 */
public class CommonStatic {

    /**
     * 判断对象是否为空
     * 如果空返回true
     * @param obj
     * @return boolean
     * */
    public static boolean ObjectIsEmpty(Object obj){
        if(obj == null){//必须先null值判断 要不然 下边的强行转换 会异常
            return true;
        }
       if(obj instanceof String){//字符串判断
           String str = (String) obj;
           return (str.trim().isEmpty() || str.trim().equals("")) ? true : false;
       }else if(obj instanceof Map){//Map判断
           Map map = (Map) obj;
           if(map.isEmpty()){
               return true;
           }else{
               return false;
           }
       }else if(obj instanceof List){
           List list = (List) obj;
           if(list.size() > 0 ){
               return false;
           }else{
               return true;
           }
       }else{
           return obj == null;
       }
    }

}
