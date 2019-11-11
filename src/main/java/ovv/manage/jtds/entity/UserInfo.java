package ovv.manage.jtds.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfo {
    private String id;
    private String userName;
    private String password;
    private String createDate;
    private String isAlive;
    private String sysNo;
}
