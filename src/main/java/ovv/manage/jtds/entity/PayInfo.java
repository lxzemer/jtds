package ovv.manage.jtds.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PayInfo implements Serializable {
    private String id;
    private String recordUserId;
    private String recordUserName;
    private String amt;
    private String involveUserId;
    private String involveUserName;
    private String remake;
    private String payDate;
    private String updateTime;
    private String createDate;
    private String accountNo;
    private int isAccount;
}
