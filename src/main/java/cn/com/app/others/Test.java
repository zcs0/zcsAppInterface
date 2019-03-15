package cn.com.app.others;

import cn.com.app.enums.EnumCommon;
import cn.com.app.main.dao.pojo.TVLivingList;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 2019/3/12.
 */
public class Test {
    public static void main(String[] args) {
        //System.out.println(EnumCommon.URLTYPE.getCode()+";"+EnumCommon.VEDIOTYPE2.getMsg());
//        Map<String,Object> map = new HashMap<>();
//        System.out.println(map.isEmpty());
//        map.put("1","a");
//        System.out.println(map.size());
//        map.put("2","b");
//        System.out.println(map.size());
//        Field[] fields =TVLivingList.class.getDeclaredFields();
//        System.out.println("test");
//        for(Field f : fields){
//            System.out.println(f.getName()+";;;");
//            try {
//                System.out.println(f.getType().getName() + "###" + Class.forName(f.getType().getName()).isInstance(new Date()));
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
        String str = "2";
        System.out.println(Long.valueOf(str));
    }
}
