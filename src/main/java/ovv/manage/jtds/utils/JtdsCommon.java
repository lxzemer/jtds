package ovv.manage.jtds.utils;

import java.util.concurrent.atomic.AtomicLong;
public class JtdsCommon {

    public static int YES = 1;
    public static int NO = 0;

    public static int SUCCESS = 200;
    public static int ERROR = 300;
    //过期
    public static int OVERLOAD = 400;

    public static String sysNo = "1007";

    public static Long lastTimeMulli = System.currentTimeMillis();

    public static AtomicLong PAY_INFO_ID = new AtomicLong(100700000000000000l+lastTimeMulli);
}
