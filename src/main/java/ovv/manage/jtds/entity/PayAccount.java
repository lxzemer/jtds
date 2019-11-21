package ovv.manage.jtds.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PayAccount implements Serializable {
    private Long id;
    private Long userId;
    private String userName;
    private String hasPay;
    private String shouldPay;
    private int accountNo;
    private String startDate;
    private String endDate;
    private int isAccount;
}
