package ovv.manage.jtds.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PayInfo implements Serializable {
    private String id;
    private String recordUserid;
    private String amt;
    private String persionsId;
    private String persionsName;
    private String remake;
    private String createDate;
    private String isJiezhang;
}
