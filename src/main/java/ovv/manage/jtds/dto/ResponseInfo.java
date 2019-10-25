package ovv.manage.jtds.dto;

public class ResponseInfo {
    private int code;
    private String msg;
    private Object content;

    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public void setMsg(String msg){
        this.msg = msg;
    }
    public String getMsg(){
        return msg;
    }
    public Object getContent(){
        return content;
    }
    public void setContent(Object content){
        this.content = content;
    }
}
