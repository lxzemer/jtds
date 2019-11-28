package ovv.manage.jtds.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PayAccount implements Serializable {
    private String id;
    private String userId;
    private String userName;
    private String hasPay;
    private String shouldPay;
    private String accountNo;
    private String updateTime;
    private int isAccount;
}
