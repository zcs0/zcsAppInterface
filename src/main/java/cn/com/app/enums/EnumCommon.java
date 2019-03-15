package cn.com.app.enums;

/**
 * Created by user on 2019/3/12.
 * 公共枚举类，用于存储各个类型代码以及描述
 * 枚举常量需按照可以表达描述的英文定义名字，比如LOGIN(1，"登录")表示登录的代码是1，描述文字是登录
 */
public enum EnumCommon {
    //返回对象
    RESULTMISSING(0,"操作失败"),
    RESULTSUCCESS(1,"操作成功"),
    //tv_list的url_type
    URLTYPE(1,"测试"),
    URLTYPE2(2,"测试2"),
    //tv_list的vedio_type
    VEDIOTYPE(1,"vedio"),
    VEDIOTYPE2(2,"vedio2");


    private int code;
    private String msg;
    EnumCommon(int code,String msg){
        this.code = code;
        this.msg = msg;
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
