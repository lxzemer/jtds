package ovv.manage.jtds.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserInfo implements Serializable {
    private String id;
    private String userName;
    private String password;
    private String createDate;
    private String isAlive;
    private String sysNo;
}
