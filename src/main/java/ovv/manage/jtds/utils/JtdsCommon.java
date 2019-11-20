package ovv.manage.jtds.utils;

import java.util.concurrent.atomic.AtomicLong;

public class JtdsCommon {
    public static int SUCCESS = 1;
    public static int FAIL = -1;

    public static int rspSuccess = 200;
    public static int rspError = 400;

    public static String sysNo = "1007";

    public static Long lastTimeMulli = System.currentTimeMillis();

    public static AtomicLong PAY_INFO_ID = new AtomicLong(100700000000000000l+lastTimeMulli);
}
