package ovv.manage.jtds.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseInfo {
    private int code;
    private String msg;
    private Object content;
    private int total;
}
