package cn.com.app.assistcode;

/**
 * Created by user on 2019/3/14.
 */
public class AppResult {
    private String doing;//做什么
    private int state;//状态
    private Object obj;//对象
    private String message;//状态文字

    public String getDoing() {
        return doing;
    }

    public void setDoing(String doing) {
        this.doing = doing;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
